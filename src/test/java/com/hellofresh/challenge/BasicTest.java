package com.hellofresh.challenge;

import com.hellofresh.challenge.config.TestConfig;
import com.hellofresh.challenge.factory.RestResponseFactory;
import com.hellofresh.challenge.util.builder.RestRequestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class BasicTest {
    protected final RestResponseFactory restResponseFactory = new RestResponseFactory();

    protected RestRequestBuilder restRequestBuilder = new RestRequestBuilder();

    protected ConfigurableApplicationContext context;
    protected String url;
    protected String endpoint;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(TestConfig.class);
        this.url = (String) context.getBean("url");
        this.endpoint = (String) context.getBean("endpoint");
    }

    @After
    public void destroyThings() {
        context.close();
    }
}
