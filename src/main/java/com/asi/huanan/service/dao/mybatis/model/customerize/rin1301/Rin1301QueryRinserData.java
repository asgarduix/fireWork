package com.asi.huanan.service.dao.mybatis.model.customerize.rin1301;


import java.util.Date;

import com.asi.huanan.service.dao.mybatis.model.FriFacRincom;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRincom;


public class Rin1301QueryRinserData {
	
	private LogFriFacRincom logFriFacRincom;
	
	private FriFacRincom friFacRincom;
	
	private String rinserEname;
	
	private Date closeDate;
	

	public FriFacRincom getFriFacRincom() {
		return friFacRincom;
	}

	public void setFriFacRincom(FriFacRincom friFacRincom) {
		this.friFacRincom = friFacRincom;
	}

	public String getRinserEname() {
		return rinserEname;
	}

	public void setRinserEname(String rinserEname) {
		this.rinserEname = rinserEname;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	

	public LogFriFacRincom getLogFriFacRincom() {
		return logFriFacRincom;
	}

	public void setLogFriFacRincom(LogFriFacRincom logFriFacRincom) {
		this.logFriFacRincom = logFriFacRincom;
	}

	


	
	
}
