package com.lm.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
public class RedisConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * 初始化redisTemplate   基础操作会使用redisTemplate
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> funactionRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate,redisConnectionFactory);
        return redisTemplate;
    }


    /**
     * 配置序列化
     * @param redisTemplate
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate,RedisConnectionFactory redisConnectionFactory) {
        redisTemplate.setStringSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        //添加redis的工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }

    /**
     * 实例化 HashOperations 对象,可以使用 Hash 类型操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String,String,Object> hashOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForHash();
    }

    /**
     * 实例化ValueOperations 对象，可以使用String类型操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String,Object> valueOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForValue();
    }


    /**
     * 实例化ListOperations对象，可以使用list类型
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String,Object> listOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForList();
    }

    /**
     * 实例化SetOperations对象,可以使用set对象
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String,Object> stringObjectSetOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForSet();
    }


    /**
     * 实例化ZSetOperations对象，可以使用zset对象
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForZSet();
    }
}
