package com.eorionsolution.camunda.plugin.enhancedjobexecutor.service;

import com.eorionsolution.camunda.plugin.enhancedjobexecutor.ConditionalOnFeatureEnabled;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.adpater.outbound.EnhancementJobLogRepository;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.domain.EnhancementJobLogEntity;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.domain.EnhancementJobLogTableEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@ConditionalOnFeatureEnabled
public class SpringEnhancementJobLogService extends EnhancementJobLogService {
    private static final Logger log = LoggerFactory.getLogger(SpringEnhancementJobLogService.class);
    private final EnhancementJobLogRepository repository;

    public SpringEnhancementJobLogService(EnhancementJobLogRepository repository) {
        super(false);
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(EnhancementJobLogEntity entity) {
        if (entity instanceof EnhancementJobLogTableEntity ent) {
            var result = repository.save(ent);
            log.debug("save EnhancementJobLogTableEntity {} result: {}", ent, result);
        } else {
            throw new IllegalCallerException("Must supply EnhancementJobLogTableEntity");
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(EnhancementJobLogEntity entity) {
        if (entity instanceof EnhancementJobLogTableEntity ent) {
            var result = repository.updateById(ent);
            log.debug("update EnhancementJobLogTableEntity {} result: {}", ent, result);
        } else {
            throw new IllegalCallerException("Must supply EnhancementJobLogTableEntity");
        }
    }

    @Override
    public EnhancementJobLogEntity generateInstance(JobEntity jobEntity, long timeoutMs) {
        return EnhancementJobLogTableEntity.getInstance(jobEntity, timeoutMs);
    }
}
