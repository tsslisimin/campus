package cn.csjava.campus.common.configures;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import org.apache.http.client.HttpClient;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author：hcqi .
 * des:rest http 配置文件
 * email:hechuanqi.top@gmail.com
 * date: 2017/10/10
 */
@Configuration
public class RestTemplateConfigure {
    /**
     * 读写超时时间
     */
    private static final int READ_TIMEOUT = 10 * 10000;
    /**
     * 链接超时时间
     */
    private static final int CONNECT_TIMEOUT = 10 * 10000;
    /**
     * 连接数
     */
    private static final int CONN_POOL_SIZE = 100;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter jackson2HttpMessageConverter,
                                     ClientHttpRequestFactory factory, StringHttpMessageConverter stringHttpMessageConverter) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setMessageConverters(Arrays.asList(jackson2HttpMessageConverter, stringHttpMessageConverter));
        restTemplate.setInterceptors(Lists.newArrayList((ClientHttpRequestInterceptor) (request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            logger.info("request uri={} method={} headers ={}   body ={}  " +
                            "response headers={}  ", request.getURI(),
                    request.getMethod(), request.getHeaders().entrySet(), new String(body),
                    response.getHeaders());

            return response;
        }));
        return restTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 忽略json字符串中不识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setTimeZone(TimeZone.getDefault());
        return objectMapper;
    }

    @Bean
    ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        requestFactory.setReadTimeout(READ_TIMEOUT);
        requestFactory.setHttpClient(httpClient);
        requestFactory.setConnectionRequestTimeout(500);
        return requestFactory;
    }

    @Bean
    public HttpClient httpClient() {
        //cpu 核数
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(2, TimeUnit.MINUTES);
        connectionManager.setDefaultSocketConfig(SocketConfig.custom()
                .setSoKeepAlive(true)
                .setTcpNoDelay(true)
                .build());
        connectionManager.setMaxTotal(CONN_POOL_SIZE);
        connectionManager.setDefaultMaxPerRoute(CONN_POOL_SIZE / 10);
        return HttpClients.custom()
                .setRetryHandler(new DefaultHttpRequestRetryHandler(2, true))
                .setConnectionManager(connectionManager).build();
    }
}
