package com.asi.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asi.schedule.job.Job1;
import com.asi.schedule.job.Job2;
import com.asi.schedule.job.Job3;
import com.asi.schedule.job.Job4CleanCache;

@Configuration
public class JobConfigure {
	/**
	 * Spring boot - Quartz will combine bean, and params of trigger method will be
	 * mapping by "job name"!(if name not mapping, then will mapping by params for
	 * only one job!(not only one will throw exception))
	 * 
	 */

	// =====group_start=====
	// jobDetails
	@Bean
	public JobDetail job1() {
		return JobBuilder.newJob(Job4CleanCache.class).withIdentity(Job4CleanCache.jobName).storeDurably().build();
	}

	// triggers
	@Bean
	public Trigger trigger1(JobDetail job1) {
		return TriggerBuilder.newTrigger().forJob(job1).withIdentity(Job4CleanCache.jobName)
				.withSchedule(CronScheduleBuilder.cronSchedule(Job4CleanCache.cronDefine)).build();
	}
	// =====group_end=====

	@Bean
	public JobDetail job2_1() {
		return JobBuilder.newJob(Job1.class).withIdentity(Job1.jobName).storeDurably().build();
	}

	@Bean
	public Trigger trigger2_1(JobDetail job2_1) {
		return TriggerBuilder.newTrigger().forJob(job2_1).withIdentity(Job1.jobName)
				.withSchedule(CronScheduleBuilder.cronSchedule(Job1.cronDefine)).build();
	}

	@Bean
	public JobDetail job2_2() {
		return JobBuilder.newJob(Job2.class).withIdentity(Job2.jobName).storeDurably().build();
	}

	@Bean
	public Trigger trigger2_2(JobDetail job2_2) {
		return TriggerBuilder.newTrigger().forJob(job2_2).withIdentity(Job2.jobName)
				.withSchedule(CronScheduleBuilder.cronSchedule(Job2.cronDefine)).build();
	}

	@Bean
	public JobDetail job3() {
		return JobBuilder.newJob(Job3.class).withIdentity(Job3.jobName).storeDurably().build();
	}

	@Bean
	public Trigger trigger3(JobDetail job3) {
		return TriggerBuilder.newTrigger().forJob(job3).withIdentity(Job3.jobName)
				.withSchedule(CronScheduleBuilder.cronSchedule(Job3.cronDefine)).build();
	}

}
