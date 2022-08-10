package com.fireReins;

import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.asi.huanan.vo.Rin1304QueryTreeVoReq;
import com.asi.initial.LaunchFrontend;
import com.google.gson.Gson;

@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = LaunchFrontend.class)
@SpringBootTest(classes = LaunchFrontend.class, args = "--spring.profiles.active=dev")
@TestInstance(Lifecycle.PER_CLASS)
public class JunitSample2 {

	private Log log = LogFactory.getLog(JunitSample2.class);

	/**
	 * 
	 */
	@Test
	public void foo() {
		try {
			Gson gson = new Gson();
			Rin1304QueryTreeVoReq vo = new Rin1304QueryTreeVoReq();
			vo.setAddrNo("x");
			vo.setEndorseNo("x");
			vo.setPolicyNo("x");
			vo.setPropNo("x");
			String res = gson.toJson(vo);
			System.out.println(res);
			log.debug("?:" + res);
		} catch (Exception e) {
			System.out.println(e.toString());
			assertTrue(false);
		}
	}

}
