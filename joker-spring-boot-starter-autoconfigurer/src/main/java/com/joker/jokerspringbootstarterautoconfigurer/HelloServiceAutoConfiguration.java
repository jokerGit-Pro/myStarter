package com.joker.jokerspringbootstarterautoconfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(HelloPoperties.class)
public class HelloServiceAutoConfiguration {

    @Autowired
    HelloPoperties helloPoperties;

   @Bean
    public HelloService helloService(){
       HelloService helloService = new HelloService();
       helloService.setHelloPoperties(helloPoperties);
       return helloService;
   }
}
