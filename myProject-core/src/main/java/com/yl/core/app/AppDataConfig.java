package com.yl.core.app;

import com.alibaba.druid.pool.DruidDataSource;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.zhixindu.commons.pagination.PaginationInterceptor;
import com.zhixindu.commons.repository.DefaultPageRepository;
import com.zhixindu.commons.repository.PageRepository;
import org.apache.ibatis.plugin.Interceptor;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.mapping.MapperOptions;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Richard Xue
 * @version 1.0
 * @date 03/13/2016
 * @description
 */
@Configuration
@MapperScan("com.yl.core.dao")
@PropertySource(value = {"classpath:/my-core.properties"})
@EnableTransactionManagement
public class AppDataConfig {

    @Value("${connection.url}")
    private String url;
    @Value("${connection.username}")
    private String username;
    @Value("${connection.password}")
    private String password;
    @Value("${druid.initialSize}")
    private int initialSize;
    @Value("${druid.minIdle}")
    private int minIdle;
    @Value("${druid.maxActive}")
    private int maxActive;
    @Value("${druid.maxWait}")
    private int maxWait;
    @Value("${druid.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${druid.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${druid.validationQuery}")
    private String validationQuery;
    @Value("${druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${druid.testOnReturn}")
    private boolean testOnReturn;
    @Value("${druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${druid.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${druid.removeAbandonedTimeoutMillis}")
    private long removeAbandonedTimeoutMillis;
    @Value("${druid.logAbandoned}")
    private boolean logAbandoned;
    @Value("${druid.filters}")
    private String filters;
    @Value("${druid.connectionProperties}")
    private String connectionProperties;

    @Value("${mongo.uri}")
    private String mongoUri;
    @Value("${mongo.database}")
    private String mongoDatabase;

    @Bean
    public DataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        /** 基本属性 url、user、password */
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        /** 配置初始化大小、最小、最大 */
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        /** 配置获取连接等待超时的时间 */
        dataSource.setMaxWait(maxWait);
        /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        /** 配置一个连接在池中最小生存的时间，单位是毫秒 */
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        /** 关闭PSCache，并且指定每个连接上PSCache的大小，mysql可以配置为false*/
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        /** 配置removeAbandoned对性能会有一些影响，建议怀疑存在泄漏之后再打开。 */
        /*// 打开removeAbandoned功能
        dataSource.setRemoveAbandoned(removeAbandoned);
        // 1800秒，也就是30分钟
        dataSource.setRemoveAbandonedTimeoutMillis(removeAbandonedTimeoutMillis);
        // 关闭abandoned连接时输出错误日志
        dataSource.setLogAbandoned(logAbandoned);*/
        /** 配置监控统计拦截的filters */
        dataSource.setFilters(filters);
        /** 配置数据库密码加密 */
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.yl.core.domain");
        Interceptor paginationInterceptor = new PaginationInterceptor();
        Properties properties = new Properties();
        properties.setProperty("stmtIdRegex", "*.*ByPage");
        properties.setProperty("dialect", "mysql");
        paginationInterceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{paginationInterceptor});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/*Mapper.xml"));
        return sessionFactory;
    }

    @Bean
    public PageRepository pageRepository() throws Exception{
        return new DefaultPageRepository(new SqlSessionTemplate(sqlSessionFactory().getObject()));
    }

    @Bean
    public MongoClient mongoClient() {
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
//        builder.minConnectionsPerHost(20);
//        builder.connectionsPerHost(100);
//        builder.connectTimeout(30000);
//        builder.writeConcern(WriteConcern.JOURNALED);
        MongoClientURI mongoClientURI = new MongoClientURI(mongoUri, builder);
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(mongoDatabase);
    }

    @Bean
    public Datastore datastore() {
        MapperOptions mapperOptions = new MapperOptions();
        // 设置扫描子目录
        mapperOptions.setMapSubPackages(true);
        final Morphia morphia = new Morphia(new Mapper(mapperOptions));
        morphia.mapPackage("com.yl.core.bean");
        final Datastore datastore = morphia.createDatastore(mongoClient(), mongoDatabase);
        datastore.ensureIndexes();
        return datastore;
    }
}
