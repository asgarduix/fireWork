package com.asi.mechanism;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asi.machanism.launcher.util.ScanUtil;
import com.asi.mechanism.service.connector.DBConnectionSqlite;
import com.asi.swissknife.Asiutil;

/**
 * 
 * @author leo_lee
 *
 */
@Component
public class Initiator implements InitializingBean {
	/**
	 * XXX 能將處理邏輯拆出嗎?
	 */

	private Logger logger = LogManager.getLogger(Initiator.class);

	private Asiutil util = new Asiutil();

//	@Autowired
//	private DBConnectionSqlite dbConnSqlite;

	/**
	* 
	*/
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("#initiator will execute to prepare system...start");

		// 設定供由api反查class的資料
		ScanUtil scan = new ScanUtil();
		scan.settingFilter();
		Map<String, String> map = scan.scanApisDescription();
		Tmp.apiconstract = map;

		// Iterator<Entry<String, String>> ite = map.entrySet().iterator();
		// while (ite.hasNext()) {
		// Entry<String, String> obj = ite.next();
		// System.out.println(obj.getKey() + ":" + obj.getValue());
		// }

		// print params of application.yml
		// List<String> nmList =
		// Arrays.asList(SpringProperty.class.getDeclaredFields()).stream().map(s ->
		// s.getName())
		// .collect(Collectors.toList());
		// nmList.stream().forEach(nm -> {
		// try {
		// System.out.println(nm + ":" +
		// SpringProperty.class.getDeclaredField(nm).get(nm));
		// } catch (IllegalArgumentException e) {
		//
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		//
		// e.printStackTrace();
		// } catch (NoSuchFieldException e) {
		//
		// e.printStackTrace();
		// } catch (SecurityException e) {
		//
		// e.printStackTrace();
		// }
		// });

		logger.debug("#initiator will execute to prepare system...end");
	}

}
