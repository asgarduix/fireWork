package com.asi.schedule.job;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class Job1 implements Job {

	public static String jobName = Job1.class.getName();
	public static String cronDefine = "0 0 * ? * * *";
	private static Logger log = LogManager.getLogger(Job1.class);

	@Override
	public void execute(JobExecutionContext context) {
		log.debug("job2_1-" + new Date() + "-" + cronDefine);
	}

}