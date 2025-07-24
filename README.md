# Enhanced Job Executor for Springboot Camunda 7 Application

This is a Springboot starter of [Enhanced Job Executor](https://github.com/orion-automation/enhanced-job-executor).

For detail information about the Camunda plugin itself, please refer to [Enhanced Job Executor](https://github.com/orion-automation/enhanced-job-executor).

## Installation

> Tested Environment:
>* Camunda Platform 7 v7.22.0
>* Java 21

This is a Springboot starter that could be involved in a Springboot application as the same as other starters.

For example, when using maven, you may add the following codes to the `pom.xml` file:

```xml
<dependency>
    <groupId>com.eorion.bo.plugin</groupId>
    <artifactId>enhanced-job-executor-spring-boot-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Configuration

Using the following configuration properties:

* prefix: `eorion.plugin.enhancedjobexecutor`

| Name        | Type    | Default Value | Desc.                                                            |
|-------------|---------|---------------|------------------------------------------------------------------|
| enabled     | boolean | false         | Whether or not enable the plugin                                 |
| timeoutMs   | long    | 6000000       | Timeout of job worker thread  (*)                                |
| enableDBLog | boolean | false         | whether or not record enhanced job execution information into db |

For setting different timeout than the default value for each job, an `extension properties` is used in BPMN diagram.
You can add the following information for the starting element of a job which is usually `timer-event` or `async-continuation`:
```xml
<bpmn:extensionElements>
  <camunda:properties>
    <camunda:property name="timeout" value="30000" />
  </camunda:properties>
</bpmn:extensionElements>
```