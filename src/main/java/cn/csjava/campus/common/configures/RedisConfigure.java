package cn.csjava.campus.common.configures;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author：hcqi .
 * describe:
 * email:hechuanqi.top@gmail.com
 * date: 2018/7/1
 */
@Configuration
public class RedisConfigure {

//    @Bean public  RedisConnectionFactory redisConnectionFactory(){
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setDatabase();
//        return  factory;
//
//
//    }
//
//    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        return redisTemplate;
//    }


}
