package com.origin.admin.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Kevin
 * @Email: 1129759063@qq.com
 * @Description:
 * @Date 2023/11/13 17:10
 */
@Configuration
@OpenAPI31
public class SwaggerConfig {

    @Value("${application.name}")
    private String NAME;

    @Value("${application.author}")
    private String AUTHOR;

    @Value("${application.version}")
    private String VERSION;

    @Value("${application.address}")
    private String ADDRESS;

    @Value("${application.email}")
    private String EMAIL;

    /**
     * 设置文档信息
     * @return OpenAPI
     */
    @Bean
    public OpenAPI springOpenAPI(){
       return new OpenAPI().info(openAPIInfo());
    }

    private Info openAPIInfo(){
        Contact contact = new Contact();
        contact.setName(AUTHOR);
        contact.setEmail(EMAIL);
        contact.setUrl(ADDRESS);
        License license = new License();
        license.setName(AUTHOR);
        license.setUrl(ADDRESS);
        Info info = new Info();
        info.setTitle(NAME);
        info.setDescription("Spring Boot 企业级管理平台");
        info.setLicense(license);
        info.setContact(contact);
        info.setVersion(VERSION);
        return info;
    }
}
