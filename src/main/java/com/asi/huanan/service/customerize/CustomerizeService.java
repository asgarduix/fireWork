package com.asi.huanan.service.customerize;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.model.customerize.FricomJoinRicmpf1;
import com.asi.huanan.service.repository.cutomerize.CustomerizeRepository;

@Service
public class CustomerizeService {

	private Log log = LogFactory.getLog(CustomerizeService.class);

	@Autowired
	private CustomerizeRepository repository;

	// =====針對使用自訂SQL=====
	
	
	/**
     * 
     * @param rinComId
     * @return
     * @throws Exception
     */
    public List<FricomJoinRicmpf1> queryOneReiner(final String rinComId)
            throws Exception
    {
        return repository.queryOneReiner(rinComId);
    }
	 
}
