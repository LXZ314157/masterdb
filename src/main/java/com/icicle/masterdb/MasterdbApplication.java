package com.icicle.masterdb;

import com.icicle.masterdb.util.LanguageUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author liumingming
 */
@EnableWebMvc
@SpringBootApplication
@Import({LanguageUtil.class})
public class MasterdbApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MasterdbApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MasterdbApplication.class);
    }
}








