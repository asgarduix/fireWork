package com.asi.huanan.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyDtlMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyProp;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyPropKey;
import com.asi.huanan.service.repository.FriPolicyPropRepository;
import com.asi.huanan.vo.Rin1304FriPolicyPropVOResp;
import com.asi.huanan.vo.Rin1304QueryDdlPropertyListVO;
import com.asi.huanan.vo.Rin1304QueryPolicyPropVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;

@Service
public class FriPolicyPropService {

	private Log log = LogFactory.getLog(FriPolicyPropService.class);

	@Autowired
	private FriPolicyPropRepository repository;

	// =====針對使用自訂SQL=====

	/**
	 * Rin1304_臨分分入，刪除保批單明細檔
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public int deletePolicyProp(String policyNo, String endorseNo, String addrNo, String propNo,
			FriPolicyPropMapper mapper) throws Exception {
		return repository.deletePolicyProp(policyNo, endorseNo, addrNo, propNo, mapper);
	}

	/**
	 * Rin1304_臨分分入，查詢標的物明細檔
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public List<Rin1304VO> queryPolicyPropDtl(String policyNo, String endorseNo, FriPolicyPropMapper mapper)
			throws Exception {
		return repository.queryPolicyPropDtl(policyNo, endorseNo, mapper);
	}

	/**
	 * Rin1304_臨分分入，查詢標的物明細檔頁面結果
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	public List<Rin1304FriPolicyPropVOResp> queryPolicyPropDtlResult(String policyNo, String endorseNo, String addrNo,
			String propNo) throws Exception {
		return repository.queryPolicyPropDtlResult(policyNo, endorseNo, addrNo, propNo);

	}

	/**
	 * Rin1304C_臨分分入_標的物下拉選單
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryDdlPropertyListVO> queryDdlPropertyList() throws Exception {
		return repository.queryDdlPropertyList();
	}
	
	/**
	 * Rin1304_臨分分入_新增標的物明細檔 by 續保單號
	 * @param policyNo
	 * @param endorseNo
	 * @param getPolicyNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertFriPolicyPropForOldPolicy(String policyNo, String endorseNo, String getPolicyNo,
			FriPolicyPropMapper mapper) throws Exception {
		return repository.insertFriPolicyPropForOldPolicy(policyNo, endorseNo, getPolicyNo, mapper);
	}
	
	
	/**
	 * Rin1304_建立新的自動調整批單(ADJ)
	 * @param policyNo
	 * @param endorseNo
	 * @param policyNoADJ
	 * @param endorseNoADJ
	 * @param friPolicyPropMapper
	 * @return
	 * @throws Exception
	 */
	public int createADJpolicyProp(String policyNo, String endorseNo, String policyNoADJ, String endorseNoADJ,
			FriPolicyPropMapper friPolicyPropMapper) throws Exception{
		return repository.createADJpolicyProp(policyNo, endorseNo,policyNoADJ,endorseNoADJ,friPolicyPropMapper);
		
	}

	/**
	 * Rin1304_反算_取得標的物序號
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryPolicyPropVO> getPropNo(String policyNo, String endorseNo, String addrNo,FriPolicyPropMapper mapper) throws Exception {
		return repository.getPropNo(policyNo, endorseNo,addrNo,mapper);
	}

	/**
	 * Rin1304_反算_更改標的物明細檔保額/保費
	 * @param prem
	 * @param amt
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param propNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updatePropAmtPrem(BigDecimal prem, BigDecimal amt, String policyNo, String endorseNo, String addrNo,
			String propNo,FriPolicyPropMapper mapper) throws Exception{
		return repository.updatePropAmtPrem(prem,amt,policyNo, endorseNo,addrNo,propNo,mapper);
		
	}
	// =====針對使用自訂SQL結束=====

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriPolicyProp model, FriPolicyPropMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriPolicyPropMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicyProp model, FriPolicyPropMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int insert(final FriPolicyProp model, SqlSession sqlSession) throws
	// Exception {
	// return repository.insert(model, sqlSession);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws
	// Exception {
	// return repository.deleteByKey(primaryKey, sqlSession);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int update(final FriPolicyProp model, SqlSession sqlSession) throws
	// Exception {
	// return repository.update(model, sqlSession);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriPolicyProp model) throws Exception {
		return repository.insert(model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriPolicyProp> modelList) throws Exception {
		return repository.insertList(modelList);
	}

	// /**
	// *
	// * @param modelList
	// * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriPolicyProp＞)
	// * @throws Exception
	// */
	// public Map<String, Object> insertList(List<FriPolicyProp> modelList)
	// throws Exception
	// {
	// return repository.insertList(modelList);
	// }

	// /**
	// *
	// * @param nmArray
	// * @param paraNmArray
	// * @param moduleNm
	// * @return
	// * @throws Exception
	// */
	// public Map<String, String> queryAllReturnMap(String[] paraNmArray,
	// String moduleNm) throws Exception
	// {
	// return repository.queryAllReturnMap(paraNmArray, moduleNm);
	// }

	// /**
	// *
	// * @param jobId
	// * @return
	// * @throws Exception
	// */
	// public FriPolicyProp queryByJobId(final String jobId) throws Exception
	// {
	// return repository.queryByJobId(jobId);
	// }

	/**
	 * 
	 * @param FriPolicyProp
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyProp> queryByFriPolicyProp(final FriPolicyProp model) throws Exception {
		return repository.queryByFriPolicyProp(model);
	}

	/**
	 * 
	 * @param FriPolicyProp
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyProp> queryByFriPolicyProp(final FriPolicyProp model, SqlSession sqlSession) throws Exception {
		return repository.queryByFriPolicyProp(model, sqlSession);
	}

	/**
	 * 
	 * @param primaryKey
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey) throws Exception {
		return repository.deleteByKey(primaryKey);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicyProp model) throws Exception {
		return repository.update(model);
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyProp> queryAll() throws Exception {
		return repository.queryAll();
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyProp> queryAll(SqlSession sqlSession) throws Exception {
		return repository.queryAll(sqlSession);
	}

	// /**
	// *
	// * @return
	// * @throws Exception
	// */
	// public int deleteAll() throws Exception{
	// return repository.deleteAll();
	// }

//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriPolicyProp> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriPolicyProp> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriPolicyProp model) throws Exception {
		return repository.queryCount(model);
	}

	/**
	 * 請注意須在使用前須完成交易
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriPolicyProp model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}

	/**
	 * Rin1304_標的物明細檔_取標的物序號最大值
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryPolicyPropVO> getMaxpropNo(String policyNo, String endorseNo, String addrNo) throws Exception{
		return repository.getMaxpropNo(policyNo, endorseNo,addrNo);
	}

	/**
	 * Rin1304_臨分分入_修改標的物明細檔地址序號
	 * @param addrNo
	 * @param policyNo
	 * @param endorseNo
	 * @param oldAddrNo
	 * @param mapper
	 * @return
	 */
	public int updatePolicyPropAddrNo(String addrNo, String policyNo, String endorseNo, String oldAddrNo,FriPolicyPropMapper mapper) {
		return repository.updatePolicyPropAddrNo(addrNo, policyNo,endorseNo,oldAddrNo,mapper);
	}

	

	

}