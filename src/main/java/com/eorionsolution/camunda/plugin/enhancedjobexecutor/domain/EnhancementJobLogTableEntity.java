package com.eorionsolution.camunda.plugin.enhancedjobexecutor.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@TableName(value = "ENHANCEMENT_JOB_LOG", autoResultMap = true)
public class EnhancementJobLogTableEntity extends EnhancementJobLogEntity {
    @TableId(value = "ID_", type = IdType.INPUT)
    protected String id;
    @TableField(value = "JOB_ID_")
    protected String jobId;
    @TableField(value = "JOB_EXCEPTION_MSG_")
    protected String exceptionMessage;
    @TableField(value = "JOB_STATE_")
    protected int jobState;
    @TableField(value = "TIMEOUT_")
    protected long timeout;
    @TableField(value = "JOB_DEF_ID_")
    protected String jobDefinitionId;
    @TableField(value = "JOB_DEF_TYPE_")
    protected String jobDefinitionType;
    @TableField(value = "ACT_ID_")
    protected String activityId;
    @TableField(value = "EXECUTION_ID_")
    protected String executionId;
    @TableField(value = "ROOT_PROC_INST_ID_")
    protected String rootProcessInstanceId;
    @TableField(value = "PROCESS_INSTANCE_ID_")
    protected String processInstanceId;
    @TableField(value = "PROCESS_DEF_ID_")
    protected String processDefinitionId;
    @TableField(value = "PROCESS_DEF_KEY_")
    protected String processDefinitionKey;
    @TableField(value = "DEPLOYMENT_ID_")
    protected String deploymentId;
    @TableField(value = "TENANT_ID_")
    protected String tenantId;
    @TableField(value = "HOSTNAME_")
    protected String hostName;
    @TableField(value = "EXECUTION_THREAD_NAME_")
    protected String executionThreadName;
    @TableField(value = "EXECUTION_START_TIMESTAMP_")
    protected Date startTime;
    @TableField(value = "EXECUTION_COMPLETE_TIMESTAMP_")
    protected Date endTime;
    @TableField(value = "EXECUTION_DURATION_")
    protected long duration;

    public static EnhancementJobLogTableEntity getInstance(JobEntity jobEntity, long timeout) {
        var instance = new EnhancementJobLogTableEntity();
        instance.setId(UUID.randomUUID().toString());
        instance.setJobState(1);
        try {
            instance.setHostName(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            instance.setHostName("UnknownHostName");
        }
        instance.setJobId(jobEntity.getId());
        instance.setJobDefinitionId(jobEntity.getJobDefinitionId());
        instance.setJobDefinitionType(jobEntity.getJobHandlerType());
        instance.setActivityId(jobEntity.getActivityId());
        instance.setExecutionId(jobEntity.getExecutionId());
        instance.setRootProcessInstanceId(jobEntity.getRootProcessInstanceId());
        instance.setProcessInstanceId(jobEntity.getProcessInstanceId());
        instance.setProcessDefinitionId(jobEntity.getProcessDefinitionId());
        instance.setProcessDefinitionKey(jobEntity.getProcessDefinitionKey());
        instance.setDeploymentId(jobEntity.getDeploymentId());
        instance.setTenantId(jobEntity.getTenantId());
        instance.setTimeout(timeout);
        instance.setStartTime(new Date());
        instance.setExecutionThreadName(Thread.currentThread().getName());
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EnhancementJobLogTableEntity entity)) return false;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EnhancementJobLogTableEntity.class.getSimpleName() + "[", "]")
                .add("id='" + getId() + "'")
                .add("jobId='" + getJobId() + "'")
                .add("exceptionMessage='" + getExceptionMessage() + "'")
                .add("jobState=" + getJobState())
                .add("timeout=" + getTimeout())
                .add("jobDefinitionId='" + getJobDefinitionId() + "'")
                .add("jobDefinitionType='" + getJobDefinitionType() + "'")
                .add("activityId='" + getActivityId() + "'")
                .add("executionId='" + getExecutionId() + "'")
                .add("rootProcessInstanceId='" + getRootProcessInstanceId() + "'")
                .add("processInstanceId='" + getProcessInstanceId() + "'")
                .add("processDefinitionId='" + getProcessDefinitionId() + "'")
                .add("processDefinitionKey='" + getProcessDefinitionKey() + "'")
                .add("deploymentId='" + getDeploymentId() + "'")
                .add("tenantId='" + getTenantId() + "'")
                .add("hostName='" + getHostName() + "'")
                .add("executionThreadName='" + getExecutionThreadName() + "'")
                .add("startTime=" + getStartTime())
                .add("endTime=" + getEndTime())
                .add("duration=" + getDuration())
                .toString();
    }
}
