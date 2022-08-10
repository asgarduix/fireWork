package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicy;
import com.asi.huanan.service.repository.FriPolicyRepository;
import com.asi.huanan.vo.Rin1304CheckAmtPremRespVO;
import com.asi.huanan.vo.Rin1304DdlCurncyListVOResp;
import com.asi.huanan.vo.Rin1304FriPolicyVOResp;
import com.asi.huanan.vo.Rin1304GetAddrNoVO;
import com.asi.huanan.vo.Rin1304QueryAcctFlagRespVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;


@Service
public class FriPolicyService {

	private Log log = LogFactory.getLog(FriPolicyService.class);

	@Autowired
	private FriPolicyRepository repository;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * Rin1304_臨分分入，刪除保批檔主檔
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public int  deletePolicyByPrimaryKey(String policyNo,String endorseNo ,FriPolicyMapper mapper ) {
		return repository.deletePolicyByPrimaryKey(policyNo, endorseNo,mapper);
	}
	
	
	/**
	 * Rin1304臨分分入，保批單主檔查詢顯示
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1304FriPolicyVOResp> queryPolicyByPrimaryKey(String policyNo, String endorseNo) throws Exception {
		return repository.queryPolicyByPrimaryKey(policyNo,endorseNo);
		
	}


	/**
	 * Rin1304臨分分入，判斷是否有資料
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 */
	public List<Rin1304VO> queryrin1304(String policyNo, String endorseNo) throws Exception{
		return repository.queryrin1304(policyNo,endorseNo);
		
	}
	
	/**Rin1304A_臨分分入_檢查是否立帳
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryAcctFlagRespVO> checkAcctfFlagIsY(String policyNo, String endorseNo)throws Exception {
		return repository.checkAcctfFlagIsY(policyNo, endorseNo);
	}


	/**
	 * Rin1304A_臨分分入_幣別下拉選單
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304DdlCurncyListVOResp> queryDdlCurncyList() throws Exception{
		return repository.queryDdlCurncyList();
	}
	
	/**
	 * Rin1304_臨分分入_查詢共保累計保額/保費
	 * @param policyNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyVOResp> querySumAllAmtPremByPolicyNo(String policyNo) throws Exception {
		return repository.querySumAllAmtPremByPolicyNo(policyNo);
	}
	
	/**
	 * Rin1304_新增保批單主檔 by 續保單號
	 * @param policyNo
	 * @param endorseNo
	 * @param oldPolicy
	 * @param getPolicyNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertFriPolicyByOldPolicyNo(String policyNo,String endorseNo,String oldPolicy,String getPolicyNo,FriPolicyMapper mapper)throws Exception {
		return repository.insertFriPolicyByOldPolicyNo(policyNo,endorseNo,oldPolicy,getPolicyNo,mapper);
	}
	
	/**
	 * Rin1304_臨分分入_自動調整批單號(ADJ)
	 * @param policyNo
	 * @param endorseNo
	 * @param policyDprt
	 * @param policyNoADJ
	 * @param endorseNoADJ
	 * @param friPolicyMapper
	 * @return
	 * @throws Exception
	 */
	public int createADJpolicy(String policyNo, String endorseNo, String policyDprt, String policyNoADJ,
			String endorseNoADJ, FriPolicyMapper friPolicyMapper)throws Exception {
		return repository.createADJpolicy(policyNo, endorseNo,policyDprt,policyNoADJ,endorseNoADJ,friPolicyMapper);
		
	}


	/**
	 * Rin1304臨分分入_反算_檢查保額/保費加總金額
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304CheckAmtPremRespVO> checkAmtPrem(String policyNo, String endorseNo)throws Exception {
		return repository.checkAmtPrem(policyNo, endorseNo);
	}
	//==========自訂SQL結束===========//
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriPolicy model, FriPolicyMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriPolicyMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicy model, FriPolicyMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriPolicy model, SqlSession sqlSession) throws Exception {
	//	return repository.insert(model, sqlSession);
	//}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws Exception {
	//	return repository.deleteByKey(primaryKey, sqlSession);
	//}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriPolicy model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriPolicy model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriPolicy> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriPolicy＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriPolicy> modelList)
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
    //  * 
    //  * @param jobId
    //  * @return
    //  * @throws Exception
    //  */
    // public FriPolicy queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriPolicy
     * @return
     * @throws Exception
     */
    public List<FriPolicy> queryByFriPolicy(final FriPolicy model)
            throws Exception
    {
        return repository.queryByFriPolicy(model);
    }

    /**
     * 
     * @param FriPolicy
     * @return
     * @throws Exception
     */
    public List<FriPolicy> queryByFriPolicy(final FriPolicy model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriPolicy(model, sqlSession);
    }
    
    

    /**
     * 
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public int deleteByKey(final String primaryKey) throws Exception
    {
		return repository.deleteByKey(primaryKey);
    }

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int update(final FriPolicy model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriPolicy> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriPolicy> queryAll(SqlSession sqlSession) throws Exception{
    	return repository.queryAll(sqlSession);
    }
    
	// /**
    // * 
    // * @return
    // * @throws Exception
    // */
    //public int deleteAll() throws Exception{
    //	return repository.deleteAll();
    //}
    
    /**
     * 
     * @param sqlSession
     * @return
     * @throws Exception
     */
//    public List<FriPolicy> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }

    /**
     * 
     * @param sqlSession
     * @return
     * @throws Exception
     */
//    public List<FriPolicy> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriPolicy model) throws Exception {
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
	public long queryCount(FriPolicy model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}


	

}