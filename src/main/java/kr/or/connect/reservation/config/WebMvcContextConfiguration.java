package kr.or.connect.reservation.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import kr.or.connect.reservation.argumentresolver.HeaderMapArgumentResolver;
import kr.or.connect.reservation.interceptor.LogInterceptor;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kr.or.connect.reservation"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		// +++++++++++++++++++++
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/views/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	}
	
	// default servlet handler를 사용하게 합니다.
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
		@Override
		public void addViewControllers(final ViewControllerRegistry registry) {
//			System.out.println("addViewControllers가 호출됩니다. ");
			registry.addViewController("/").setViewName("index");
		}

		@Bean
		public InternalResourceViewResolver getInternalResourceViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/views/");
			resolver.setSuffix(".jsp");
			return resolver;
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
	    		registry.addInterceptor(new LogInterceptor());
		}
		
		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			System.out.println("아규먼트 리졸버 등록..");
			argumentResolvers.add(new HeaderMapArgumentResolver());
		}

		@Bean
		public MultipartResolver multipartResolver() {
			org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
			multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
			return multipartResolver;

		}

	}

