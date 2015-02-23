package org.hospital.core.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.UnknownHostException;

/**
 * Created by thiago on 2/22/15.
 */
@Configuration
@EnableMongoRepositories(basePackages = "org.hospital.core.repository")
@Import(RepositoryRestMvcConfiguration.class)
public class MongoConfig {

    @Bean
    public MongoClientURI mongoClientURI() {
        return new MongoClientURI("mongodb://localhost/hospital");
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        UserCredentials userCredentials = new UserCredentials("", "");
        return new SimpleMongoDbFactory(new MongoClient(mongoClientURI()), "hospital", userCredentials);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory());
    }
}