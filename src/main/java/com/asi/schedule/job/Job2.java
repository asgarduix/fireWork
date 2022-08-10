package com.asi.schedule.job;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class Job2 implements Job {

	public static String jobName = Job2.class.getName();
//	public static String cronDefine = "0 30 * ? * * *";
	public static String cronDefine = "0/10 0/10 * ? * * *";
	private static Logger log = LogManager.getLogger(Job2.class);

//	@Autowired
//	private DpssystembatchService dpssystembatchService;

	@Override
	public void execute(JobExecutionContext context) {
		log.debug("job2_2-" + new Date() + "-" + cronDefine);
	}

}