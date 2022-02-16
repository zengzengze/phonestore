package com.msy.phonestore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/01/18:22
 * @Description:
 */
@Configuration
public class MyWebsocketConfig {
    @Bean
    //注入ServerEndpointExporter Bean对象，自动注册使用了@ServerEndpoint注解的Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
