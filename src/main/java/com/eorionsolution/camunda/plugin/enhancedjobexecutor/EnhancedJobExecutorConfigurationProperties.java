package com.eorionsolution.camunda.plugin.enhancedjobexecutor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "eorion.plugin.enhancedjobexecutor")
public record EnhancedJobExecutorConfigurationProperties(
        @DefaultValue("false") boolean enabled,
        @DefaultValue("false") boolean enableDbLog,
        @DefaultValue("600000") long timeoutMs
) {
}
