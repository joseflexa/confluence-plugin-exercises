package br.rnp.confluence.plugins.job;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.scheduler.JobRunnerRequest;
import com.atlassian.scheduler.JobRunnerResponse;

import javax.inject.Named;

@ExportAsService ({JobRunner.class})
@Named ("myJobRunner")
public class JobRunner implements com.atlassian.scheduler.JobRunner
{
    public JobRunnerResponse runJob(JobRunnerRequest var1) {
        System.out.println("RNP JOB EXECUTED at " + new java.util.Date());
        return JobRunnerResponse.success();
    }
}