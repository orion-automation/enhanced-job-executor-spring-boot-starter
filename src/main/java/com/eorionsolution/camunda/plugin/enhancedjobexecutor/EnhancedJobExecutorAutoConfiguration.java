package com.eorionsolution.camunda.plugin.enhancedjobexecutor;

import com.eorionsolution.camunda.plugin.enhancedjobexecutor.mapper.EnhancementJobLogMapper;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.service.SpringEnhancedJobExecutorPlugin;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.service.SpringEnhancementJobLogService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
@EnableConfigurationProperties(EnhancedJobExecutorConfigurationProperties.class)
@ConditionalOnFeatureEnabled
public class EnhancedJobExecutorAutoConfiguration {
    @Bean
    public SpringEnhancedJobExecutorPlugin springEnhancedJobExecutorPlugin(
            SpringEnhancementJobLogService service, EnhancedJobExecutorConfigurationProperties properties) {
        return new SpringEnhancedJobExecutorPlugin(properties.timeoutMs(), properties.enableDbLog(), service);
    }

    @Bean
    public MapperFactoryBean<EnhancementJobLogMapper> enhancementJobLogMapperMapper(SqlSessionFactory sqlSessionFactory) {
        var factoryBean = new MapperFactoryBean<>(EnhancementJobLogMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory);
        return factoryBean;
    }
}
