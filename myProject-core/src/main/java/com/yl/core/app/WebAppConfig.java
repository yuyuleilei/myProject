package com.yl.core.app;

import com.zhixindu.commons.client.JedisClient;
import com.zhixindu.commons.mq.MeProducer;
import com.zhixindu.commons.mq.bean.MqConfig;
import com.zhixindu.commons.oss.ZxdOssClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * @author Richard Xue
 * @version 1.0
 * @date 03/12/2016
 * @description
 */
@Configuration
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.yl.core"})
@PropertySource(value = {"classpath:/my-core.properties"})
@ImportResource({"classpath:/spring-dubbo.xml"})
public class WebAppConfig {
    @Value("${redis.hostName}")
    private String redisHostName;
    @Value("${redis.port}")
    private int redisPort;
    @Value("${redis.timeout}")
    private int redisTimeout;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${redis.database}")
    private int redisDatabase;

    @Value("${redis.pool.maxIdle}")
    private int redisMaxIdle;
    @Value("${redis.pool.minIdle}")
    private int redisMinIdle;
    @Value("${redis.pool.maxTotal}")
    private int redisMaxTotal;
    @Value("${redis.pool.maxWaitMillis}")
    private int redisMaxWaitMillis;

    @Value("${aliyun.onsAccessKey}")
    private String onsAccessKey;
    @Value("${aliyun.onsSecretKey}")
    private String onsSecretKey;

    @Value("${aliyun.oss.accessKey}")
    private String ossAccessKey;
    @Value("${aliyun.oss.secretKey}")
    private String ossSecretKey;
    @Value("${aliyun.oss.endpoint}")
    private String ossEndpoint;

    @Value("${app.env}")
    private String env;

    @Bean
    public JedisClient jedisClient() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisMaxIdle);
        jedisPoolConfig.setMinIdle(redisMinIdle);
        jedisPoolConfig.setMaxTotal(redisMaxTotal);
        jedisPoolConfig.setMaxWaitMillis(redisMaxWaitMillis);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisHostName,redisPort,redisTimeout,redisPassword,redisDatabase);
        JedisClient jedisClient = new JedisClient();
        jedisClient.setJedisPool(jedisPool);
        return jedisClient;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.mxhichina.com");
        javaMailSender.setPort(25);
        javaMailSender.setUsername("zxd@zhixindu.com");
        javaMailSender.setPassword("1qaz@WSX");
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.auth","false");
        properties.setProperty("mail.smtp.starttls.enable","false");
        properties.setProperty("mail.debug","false");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(25);
        taskExecutor.setQueueCapacity(2000);
        return taskExecutor;
    }

    @Bean
    public MeProducer meProducer() {
        MqConfig mqConfig = new MqConfig();
        mqConfig.setOnsAccessKey(onsAccessKey);
        mqConfig.setOnsSecretKey(onsSecretKey);
        mqConfig.setAppId("PID_zxd_crm_app");
        mqConfig.setEnv(env);
        return new MeProducer(mqConfig);
    }

//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor(){
//        return new MethodValidationPostProcessor();
//    }

    @Bean
    public ZxdOssClient ossClient() {
        return new ZxdOssClient(ossEndpoint, ossAccessKey, ossSecretKey, env);
    }

}
