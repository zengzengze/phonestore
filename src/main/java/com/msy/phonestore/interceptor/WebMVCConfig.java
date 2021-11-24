package com.msy.phonestore.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/10:21
 * @Description:
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    /**添加拦截器函数
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**需要拦截的路径，是一个字符串数组
         * */
        String[] addPathPatterns={"/"};

        /**不需要拦截的路径
         * */
        String[] excludePathterns={"/boot/select"};

        /**给对应的拦截器进行配置
         * */
        registry.addInterceptor(new CorsInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathterns);
    }
}