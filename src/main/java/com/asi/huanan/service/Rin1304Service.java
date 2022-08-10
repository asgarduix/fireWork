package com.asi.huanan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.repository.cutomerize.Rin1304Repository;
import com.asi.huanan.vo.Rin1304QueryTreeVoReq;

@Service
public class Rin1304Service {

	@Autowired
	private Rin1304Repository repository;

	/**
	 * @return
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T> T qeuryTree(Rin1304QueryTreeVoReq vo, int layer) throws Exception {
		return (T) this.repository.queryTree(vo, layer);
	}

}
