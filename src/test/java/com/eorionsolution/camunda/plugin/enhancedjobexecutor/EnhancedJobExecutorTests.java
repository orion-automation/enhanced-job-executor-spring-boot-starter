package com.eorionsolution.camunda.plugin.enhancedjobexecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.adpater.outbound.EnhancementJobLogRepository;
import com.eorionsolution.camunda.plugin.enhancedjobexecutor.domain.EnhancementJobLogTableEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;

@SpringBootTest
class EnhancedJobExecutorTests {
    private static final Logger log = LoggerFactory.getLogger(EnhancedJobExecutorTests.class);

    @Autowired
    private EnhancementJobLogRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldExecuteNormalProcessWithoutExceptions() throws InterruptedException {
        var pid = runtimeService().startProcessInstanceByKey("NormalProcess");
        Thread.sleep(1_000);
        assertThat(pid).isEnded();

        var result = repository.getOne(
                new QueryWrapper<EnhancementJobLogTableEntity>()
                        .eq("PROCESS_INSTANCE_ID_", pid.getProcessInstanceId()), true
        );
        log.info("result: {}", result);
        Assertions.assertThat(result.getJobState()).isEqualTo(2);
        Assertions.assertThat(result.getTimeout()).isEqualTo(500);
    }

    @Test
    void shouldExecuteProcessWithErrorAndRetries() throws InterruptedException {
        var pid = runtimeService().startProcessInstanceByKey("ErrorProcess");
        Thread.sleep(1_000);
        assertThat(pid).isWaitingAt("ErrorActivity");
        var results = repository.list(
                new QueryWrapper<EnhancementJobLogTableEntity>()
                        .eq("PROCESS_INSTANCE_ID_", pid.getProcessInstanceId())
        );
        Assertions.assertThat(results)
                .hasSize(3)
                .extracting(EnhancementJobLogTableEntity::getJobState)
                .containsOnly(3);
    }

    @Test
    void shouldExecuteTimeoutProcessWithoutRetry() throws InterruptedException {
        var pid = runtimeService().startProcessInstanceByKey("TimeoutProcess");
        Thread.sleep(2_000);
        assertThat(pid).isWaitingAt("TimeoutActivity");

        var results = repository.list(
                new QueryWrapper<EnhancementJobLogTableEntity>()
                        .eq("PROCESS_INSTANCE_ID_", pid.getProcessInstanceId())
        );
        Assertions.assertThat(results)
                .hasSize(2)
                .extracting(EnhancementJobLogTableEntity::getJobState)
                .containsOnly(1, 3);
    }
}
