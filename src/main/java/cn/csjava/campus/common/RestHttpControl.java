package cn.csjava.campus.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * @author：hcqi .
 * describe: http 请求控制
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
@Component
public class RestHttpControl {
    @Autowired
    private RestTemplate restTemplate;
    // GET

    /**
     * Retrieve a representation by doing a GET on the specified URL.
     * The response (if any) is converted and returned.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     *
     * @param url          the URL
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     */
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplate.getForObject(url, responseType, uriVariables);
    }


    /**
     * Retrieve a representation by doing a GET on the URI template.
     * The response (if any) is converted and returned.
     * <p>URI Template variables are expanded using the given map.
     *
     * @param url          the URL
     * @param responseType the type of the return value
     * @param uriVariables the map containing variables for the URI template
     * @return the converted object
     */
    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return restTemplate.getForObject(url, responseType, uriVariables);
    }

    /**
     * Retrieve a representation by doing a GET on the URL .
     * The response (if any) is converted and returned.
     *
     * @param url          the URL
     * @param responseType the type of the return value
     * @return the converted object
     */
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        return restTemplate.getForObject(url, responseType);
    }


    /**
     * Retrieve an document by doing a GET on the specified URL.
     * The response is converted and stored in an {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     *
     * @param url          the URL
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the document
     * @since 3.0.2
     */
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplate.getForEntity(url, responseType, uriVariables);
    }


    /**
     * Retrieve a representation by doing a GET on the URI template.
     * The response is converted and stored in an {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given map.
     *
     * @param url          the URL
     * @param responseType the type of the return value
     * @param uriVariables the map containing variables for the URI template
     * @return the converted object
     * @since 3.0.2
     */
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        return restTemplate.getForEntity(url, responseType, uriVariables);
    }


    /**
     * Retrieve a representation by doing a GET on the URL .
     * The response is converted and stored in an {@link ResponseEntity}.
     *
     * @param url          the URL
     * @param responseType the type of the return value
     * @return the converted object
     * @since 3.0.2
     */
    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {
        return restTemplate.getForEntity(url, responseType);
    }

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the representation found in the response.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url          the URL
     * @param request      the Object to be POSTed (may be {@code null})
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     */
    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        return restTemplate.postForObject(url, request, responseType, uriVariables);
    }

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the representation found in the response.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url          the URL
     * @param request      the Object to be POSTed (may be {@code null})
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     */
    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        return restTemplate.postForObject(url, request, responseType, uriVariables);
    }

    /**
     * Create a new resource by POSTing the given object to the URL,
     * and returns the representation found in the response.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url          the URL
     * @param request      the Object to be POSTed (may be {@code null})
     * @param responseType the type of the return value
     * @return the converted object
     * @see HttpEntity
     */
    public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return restTemplate.postForObject(url, request, responseType);
    }

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the response as {@link ResponseEntity}.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url          the URL
     * @param request      the Object to be POSTed (may be {@code null})
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     * @since 3.0.2
     */
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        return restTemplate.postForEntity(url, request, responseType, uriVariables);
    }

    /**
     * Create a new resource by POSTing the given object to the URI template,
     * and returns the response as {@link HttpEntity}.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url          the URL
     * @param request      the Object to be POSTed (may be {@code null})
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     * @since 3.0.2
     */
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        return restTemplate.postForEntity(url, request, responseType, uriVariables);
    }

    /**
     * Create a new resource by POSTing the given object to the URL,
     * and returns the response as {@link ResponseEntity}.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url     the URL
     * @param request the Object to be POSTed (may be {@code null})
     * @return the converted object
     * @see HttpEntity
     * @since 3.0.2
     */
    public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType) throws RestClientException {
        return restTemplate.postForEntity(url, request, responseType);
    }


    // PUT

    /**
     * Create or update a resource by PUTting the given object to the URI.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url          the URL
     * @param request      the Object to be PUT (may be {@code null})
     * @param uriVariables the variables to expand the template
     * @see HttpEntity
     */
    public void put(String url, Object request, Object... uriVariables) throws RestClientException {
        restTemplate.put(url, request, uriVariables);
    }

    /**
     * Creates a new resource by PUTting the given object to URI template.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url          the URL
     * @param request      the Object to be PUT (may be {@code null})
     * @param uriVariables the variables to expand the template
     * @see HttpEntity
     */
    public void put(String url, Object request, Map<String, ?> uriVariables) throws RestClientException {
        restTemplate.put(url, request, uriVariables);
    }

    /**
     * Creates a new resource by PUTting the given object to URL.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     *
     * @param url     the URL
     * @param request the Object to be PUT (may be {@code null})
     * @see HttpEntity
     */
    public void put(URI url, Object request) throws RestClientException {
        restTemplate.put(url, request);
    }


    // PATCH

    /**
     * Update a resource by PATCHing the given object to the URI template,
     * and return the representation found in the response.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * <p><b>NOTE: The standard JDK HTTP library does not support HTTP PATCH.
     * You need to use the Apache HttpComponents or OkHttp request factory.</b>
     *
     * @param url          the URL
     * @param request      the object to be PATCHed (may be {@code null})
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     * @see RestTemplate#setRequestFactory
     * @see org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory
     * @see org.springframework.http.client.OkHttp3ClientHttpRequestFactory
     * @since 4.3.5
     */
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        return restTemplate.patchForObject(url, request, responseType, uriVariables);
    }

    /**
     * Update a resource by PATCHing the given object to the URI template,
     * and return the representation found in the response.
     * <p>URI Template variables are expanded using the given map.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * <p><b>NOTE: The standard JDK HTTP library does not support HTTP PATCH.
     * You need to use the Apache HttpComponents or OkHttp request factory.</b>
     *
     * @param url          the URL
     * @param request      the object to be PATCHed (may be {@code null})
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @return the converted object
     * @see HttpEntity
     * @see RestTemplate#setRequestFactory
     * @see org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory
     * @see org.springframework.http.client.OkHttp3ClientHttpRequestFactory
     * @since 4.3.5
     */
    public <T> T patchForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        return restTemplate.patchForObject(url, request, responseType, uriVariables);
    }

    /**
     * Update a resource by PATCHing the given object to the URL,
     * and return the representation found in the response.
     * <p>The {@code request} parameter can be a {@link HttpEntity} in order to
     * add additional HTTP headers to the request.
     * <p><b>NOTE: The standard JDK HTTP library does not support HTTP PATCH.
     * You need to use the Apache HttpComponents or OkHttp request factory.</b>
     *
     * @param url          the URL
     * @param request      the object to be PATCHed (may be {@code null})
     * @param responseType the type of the return value
     * @return the converted object
     * @see HttpEntity
     * @see RestTemplate#setRequestFactory
     * @see org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory
     * @see org.springframework.http.client.OkHttp3ClientHttpRequestFactory
     * @since 4.3.5
     */
    public <T> T patchForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return restTemplate.patchForObject(url, request, responseType);
    }

    // DELETE

    /**
     * Delete the resources at the specified URI.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     *
     * @param url          the URL
     * @param uriVariables the variables to expand in the template
     */
    public void delete(String url, Object... uriVariables) throws RestClientException {
        restTemplate.delete(url, uriVariables);
    }

    /**
     * Delete the resources at the specified URI.
     * <p>URI Template variables are expanded using the given map.
     *
     * @param url          the URL
     * @param uriVariables the variables to expand the template
     */
    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {
        restTemplate.delete(url, uriVariables);
    }

    /**
     * Delete the resources at the specified URL.
     *
     * @param url the URL
     */
    public void delete(URI url) throws RestClientException {
        restTemplate.delete(url);
    }


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
