package the.wuxjian.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;

//
// How to tweak NamingStrategy for Spring Data JDBC
// https://stackoverflow.com/questions/53334685/how-to-tweak-namingstrategy-for-spring-data-jdbc/53335830#53335830
//

@Configuration
public class R2dbcConfig {

	@Bean
	public NamingStrategy namingStrategy() {
		return new NamingStrategy() {
			@Override
			public String getTableName(Class<?> type) {
				return type.getSimpleName();
			}

			@Override
			public String getColumnName(RelationalPersistentProperty property) {
				return property.getName();
			}
		};
	}
}
