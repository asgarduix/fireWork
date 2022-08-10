package com.asi.schedule.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class Job3 implements Job {
	public static String jobName = "JOB_SYNC_PARAM";
	public static String cronDefine = "* 0/1 * ? * * *";
	private static Logger log = LogManager.getLogger(Job3.class);

	@Override
	public void execute(JobExecutionContext context) {
		// ...TOOD add code for pr
		// 定時回報機制
//		log.debug("job3_" + new Date());
	}

}
