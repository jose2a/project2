package com.revature.ctb.config;

import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.revature.ctb.utils.SessionFactoryUtil;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan("com.revature.ctb")
public class CTBAppConfig implements WebMvcConfigurer {

	@Bean
	public SessionFactory sessionFactory() {
		return SessionFactoryUtil.getSessionFactory();
	}

	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		DozerBeanMapper dozerBean = new DozerBeanMapper();

		return dozerBean;
	}
}
