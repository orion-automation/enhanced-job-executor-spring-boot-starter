package com.eorionsolution.camunda.plugin.enhancedjobexecutor.adpater.outbound;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.ConditionalOnFeatureEnabled;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.domain.EnhancementJobLogTableEntity;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.mapper.EnhancementJobLogMapper;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnFeatureEnabled
public class EnhancementJobLogRepository extends ServiceImpl<EnhancementJobLogMapper, EnhancementJobLogTableEntity> {
    private final EnhancementJobLogMapper enhancementJobLogMapper;

    public EnhancementJobLogRepository(EnhancementJobLogMapper enhancementJobLogMapper) {
        this.enhancementJobLogMapper = enhancementJobLogMapper;
    }
}
