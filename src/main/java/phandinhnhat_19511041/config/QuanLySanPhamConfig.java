package phandinhnhat_19511041.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("phandinhnhat_19511041")
public class QuanLySanPhamConfig implements WebMvcConfigurer{
	
//	@Autowired
//	private Environment env;

	
	@Bean
	public DataSource myDataSource() {
		ComboPooledDataSource myDataSource = new ComboPooledDataSource();
		
		try {
			myDataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		myDataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=onthicuoiky_dethimau1");
		myDataSource.setUser("sa");
		myDataSource.setPassword("sapassword");
		
//		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
//		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
//		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
//		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		
		return myDataSource;
		
	}
//	
//	public int getIntProperty(String propName) {
//		String propVal = env.getProperty(propName);
//		int intPropVal = Integer.parseInt(propVal);
//		return intPropVal;
//	}
	
	public Properties getHibernativeProperties() {
		Properties props = new Properties();
		
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
		props.setProperty("hibernate.show_sql", "true");
		
		return props;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan("phandinhnhat_19511041.entity");
		sessionFactory.setHibernateProperties(getHibernativeProperties());
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	
		

}
