package com.msy.phonestore;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/17:29
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor page=new PaginationInterceptor();
//        page.setDialectType("oracle");
//        return page;
//    }
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
//        //连表插件
//        interceptor.addInnerInterceptor(new MPJInterceptor());
//        //多租户,垃圾sql拦截插件......
        return interceptor;
    }
    //主键自增时使用
    @Bean
    public IKeyGenerator keyGenerator() {
        return new OracleKeyGenerator();
    }

}
