package com.hotel.booking.configuration;

import com.hotel.booking.service.admin.RoleService;
import com.hotel.booking.service.admin.RoleServiceImpl;
import com.hotel.booking.service.manager.HotelService;
import com.hotel.booking.service.manager.HotelServiceImpl;
import com.hotel.booking.service.user.MessageService;
import com.hotel.booking.service.user.MessageServiceImpl;
import com.hotel.booking.service.user.UserService;
import com.hotel.booking.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableSpringDataWebSupport
@ComponentScan("com.hotel.booking")
@EnableJpaRepositories("com.hotel.booking.repository")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//    @Bean
//    public UserRepository userRepository() {
//        return new UserRepositoryImpl();
//    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

//    @Bean
//    public RoleRepository roleRepository() {
//        return new RoleRepositoryImpl();
//    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl();
    }

//    @Bean
//    public MessageRepository messageRepository() {
//        return new MessageRepositoryImpl();
//    }

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl();
    }
    @Bean
    public HotelService hotelService() {return new HotelServiceImpl();}

    //Thymeleaf Configuration
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    //JPA configuration
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.hotel.booking.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        logger.info("call model");
        return em;
    }

    @Autowired
    Environment evn;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(evn.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(evn.getProperty("jdbc.url"));
        dataSource.setUsername(evn.getProperty("jdbc.username"));
        dataSource.setPassword(evn.getProperty("jdbc.password"));
        return dataSource;
    }

    Properties additionalProperties() {

        return new Properties() {
            {
                setProperty("hibernate.dialect", evn.getProperty("hibernate.dialect"));
                setProperty("hibernate.format_sql", evn.getProperty("hibernate.format_sql"));
                setProperty("hibernate.show_sql", evn.getProperty("hibernate.show_sql"));
                setProperty("hibernate.hbm2ddl.auto", evn.getProperty("hibernate.hbm2ddl.auto"));
            }

        };
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    // Cấu hình để sử dụng các file nguồn tĩnh (css, image, js..)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fileUpload = evn.getProperty("file_upload").toString();
        // Image resource.
        registry.addResourceHandler("/i/**") //
                .addResourceLocations("file:" + fileUpload);
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/classes/resources/", "classpath:/resources/",
                        "classpath:/static/", "classpath:/public/");
        registry.addResourceHandler("/css_index/**")
                .addResourceLocations("classpath:/META-INF/classes/resources/",
                        "classpath:/resources/", "classpath:/index/", "classpath:/public/");
        registry.addResourceHandler("/css_hotel/**")
                .addResourceLocations("classpath:/META-INF/classes/resources/",
                        "classpath:/resources/", "classpath:/hotel/", "classpath:/public/");
    }


    // cau hinh file upload
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5000000);
        return multipartResolver;
    }

    @Bean
    public CustomSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }



}