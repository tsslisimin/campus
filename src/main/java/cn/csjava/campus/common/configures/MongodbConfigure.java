package cn.csjava.campus.common.configures;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.event.CommandFailedEvent;
import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import com.mongodb.event.CommandSucceededEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * @author：hcqi .
 * des:mongodb 配置
 * email:hechuanqi.top@gmail.com
 * date: 2018/3/5
 */
@Configuration
public class MongodbConfigure {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${mongodb.host}")
    private String host;
    @Value("${mongodb.port}")
    private int port;
    @Value("${mongodb.username}")
    private String username;
    @Value("${mongodb.password}")
    private String password;
    @Value("${mongodb.database}")
    private String database;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port),
                MongoCredential.createCredential(username, database, password.toCharArray()),
                new MongoClientOptions.Builder().maxConnectionIdleTime(6000).addCommandListener(new MongodbCommandListener()).build());
        logger.info("connection mongodb... host={} port={}  username={}  password={} database={} ", host, port, username, password, database);
        return new SimpleMongoDbFactory(mongoClient, database);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()), new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDbFactory(), converter);
    }

    private class MongodbCommandListener implements CommandListener {
        @Override
        public void commandStarted(CommandStartedEvent commandStartedEvent) {
            String command = commandStartedEvent.getCommand().toString();
            logger.debug("mongodb commandStarted command:{}", command);
        }

        @Override
        public void commandSucceeded(CommandSucceededEvent commandSucceededEvent) {
            String response = commandSucceededEvent.getResponse().toString();
            logger.debug("mongodb commandSucceeded  data={}", response);
        }

        @Override
        public void commandFailed(CommandFailedEvent commandFailedEvent) {
            logger.error("mongodb commandFailed：", commandFailedEvent.getThrowable());
        }

    }
}
