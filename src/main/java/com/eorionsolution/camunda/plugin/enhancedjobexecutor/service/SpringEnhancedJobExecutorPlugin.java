package com.eorionsolution.camunda.plugin.enhancedjobexecutor.service;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cmd.JobTrackingInterceptor;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

import java.util.ArrayList;
import java.util.List;

public class SpringEnhancedJobExecutorPlugin extends AbstractProcessEnginePlugin {
    private final long timeoutMs;
    private final boolean enableDbLog;
    private final SpringEnhancementJobLogService springEnhancementJobLogService;

    public SpringEnhancedJobExecutorPlugin(long timeoutMs, boolean enableDbLog, SpringEnhancementJobLogService springEnhancementJobLogService) {
        this.timeoutMs = timeoutMs;
        this.enableDbLog = enableDbLog;
        this.springEnhancementJobLogService = springEnhancementJobLogService;
    }

    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        List<CommandInterceptor> customInterceptors = processEngineConfiguration.getCustomPostCommandInterceptorsTxRequired();
        if (customInterceptors == null) {
            customInterceptors = new ArrayList<>();
        }

        if (this.enableDbLog) {
            customInterceptors.add(new JobTrackingInterceptor(processEngineConfiguration, springEnhancementJobLogService, this.timeoutMs));
        } else {
            customInterceptors.add(new JobTrackingInterceptor(processEngineConfiguration, this.timeoutMs));
        }

        processEngineConfiguration.setCustomPostCommandInterceptorsTxRequired(customInterceptors);
    }
}
