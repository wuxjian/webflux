package the.wuxjian.webflux.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import dev.miku.r2dbc.mysql.constant.SslMode;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryMetadata;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * Created by wuxjian 2021/7/19
 */
@Configuration
public class ApplicationConfiguration extends AbstractR2dbcConfiguration {

    @Override
    @Bean("mysql")
    public ConnectionFactory connectionFactory() {
        MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration
                .builder()
                .host("localhost")
                .port(3306)
                .user("root")
                .password("123456")
                .database("demo")
                .sslMode(SslMode.DISABLED)
                .build();
        return MySqlConnectionFactory.from(configuration);
    }

    @Bean
    public R2dbcEntityTemplate mysqlR2dbcEntityOperations(@Qualifier("mysql") ConnectionFactory connectionFactory) {

        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);

        return new R2dbcEntityTemplate(databaseClient);
    }
}
