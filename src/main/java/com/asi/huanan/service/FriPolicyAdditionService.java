package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAddition;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAdditionKey;
import com.asi.huanan.service.repository.FriPolicyAdditionRepository;
import com.asi.huanan.vo.Rin1304FriPolicyAdditionVOResp;
import com.asi.huanan.vo.Rin1304QueryDdlAdditionListVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;

@Service
public class FriPolicyAdditionService {

	private Log log = LogFactory.getLog(FriPolicyAdditionService.class);

	@Autowired
	private FriPolicyAdditionRepository repository;
	
	//=====針對使用自訂SQL=====


	/**
	 * Rin1304_臨分分入，刪除保批單附加險檔
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 */
	public int deletePolicyAddition(String policyNo, String endorseNo,String addrNo, String propNo,String additionSeq,FriPolicyAdditionMapper mapper) {
		int count=0;
		count = mapper.deletePolicyAddition(policyNo,endorseNo,addrNo,propNo,additionSeq);
		return count;
	}
	

	/**
	 * Rin1304_臨分分入，查詢保批單附加險檔
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public List<Rin1304VO> queryPolicyAddition(String policyNo, String endorseNo, FriPolicyAdditionMapper mapper) {
		List<Rin1304VO> count=null;
		count = mapper.queryPolicyAddition(policyNo,endorseNo);
		return count;
	}

	
	/**
	 * Rin1304_臨分分入，查詢附加險結果頁面
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param propNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyAdditionVOResp> queryPolicyAdditionResult(String policyNo, String endorseNo, String addrNo, String propNo,String additionSeq,String additionNo)throws Exception {
		return repository.queryPolicyAdditionResult(policyNo, endorseNo,addrNo,propNo,additionSeq,additionNo);
	}


	/**
	 * Rin1304_臨分分入，附加險頁面，附加險代號下拉選單
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryDdlAdditionListVO> queryDdlAdditionList()throws Exception  {
		return repository.queryDdlAdditionList();
	}
	
	/**
	 * Rin1304_臨分分入_新增附加險明細檔 by 續保單號
	 * @param policyNo
	 * @param endorseNo
	 * @param getPolicyNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertFriPolicyAdditionForOldPolicy(String policyNo, String endorseNo, String getPolicyNo,FriPolicyAdditionMapper mapper)throws Exception {
		return repository.insertFriPolicyAdditionForOldPolicy(policyNo,endorseNo,getPolicyNo,mapper);
	}
	
	
	/**
	 * Rin1304_臨分分入_更新保批單附加險明細檔
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyAddition(FriPolicyAddition model, FriPolicyAdditionMapper mapper)throws Exception {
		return repository.updatePolicyAddition(model, mapper);
	}


	/**
	 * Rin1304_臨分分入_建立產生新調整批單號(ADJ)
	 * @param policyNo
	 * @param endorseNo
	 * @param policyNoADJ
	 * @param endorseNoADJ
	 * @param mapper
	 * @return
	 */
	public int createADJpolicyAddition(String policyNo, String endorseNo, String policyNoADJ, String endorseNoADJ,
			FriPolicyAdditionMapper mapper) {
		return repository.createADJpolicyAddition(policyNo, endorseNo,policyNoADJ,endorseNoADJ,mapper);
	}

	/**
	 * Rin1304_計算附加險明顯保額保費總額
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param propNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304SumAmtPremRespVO> sumAmtPrem(String policyNo, String endorseNo, String addrNo, String propNo,FriPolicyAdditionMapper mapper)throws Exception {
		return repository.sumAmtPrem(policyNo, endorseNo,addrNo,propNo,mapper);
	}
	//=====針對使用自訂SQL結束=====
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriPolicyAddition model, FriPolicyAdditionMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriPolicyAdditionMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicyAddition model, FriPolicyAdditionMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriPolicyAddition model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriPolicyAddition model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriPolicyAddition model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriPolicyAddition> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriPolicyAddition＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriPolicyAddition> modelList)
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
    // public FriPolicyAddition queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriPolicyAddition
     * @return
     * @throws Exception
     */
    public List<FriPolicyAddition> queryByFriPolicyAddition(final FriPolicyAddition model)
            throws Exception
    {
        return repository.queryByFriPolicyAddition(model);
    }

    /**
     * 
     * @param FriPolicyAddition
     * @return
     * @throws Exception
     */
    public List<FriPolicyAddition> queryByFriPolicyAddition(final FriPolicyAddition model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriPolicyAddition(model, sqlSession);
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
    public int update(final FriPolicyAddition model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriPolicyAddition> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriPolicyAddition> queryAll(SqlSession sqlSession) throws Exception{
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
    
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriPolicyAddition> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriPolicyAddition> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriPolicyAddition model) throws Exception {
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
	public long queryCount(FriPolicyAddition model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}


	/**
	 * Rin1304_標的物明細檔_取附加險序號最大值
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param propNo
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyAdditionKey> getMaxAdditionSeq(String policyNo, String endorseNo, String addrNo,
			String propNo) throws Exception{
		return repository.getMaxAdditionSeq(policyNo, endorseNo,addrNo,propNo);
	}


	/**
	 * Rin1304_臨分分入_修改附加險明細檔地址序號
	 * @param addrNo
	 * @param policyNo
	 * @param endorseNo
	 * @param oldAddrNo
	 * @param mapper
	 * @return
	 */
	public int updatePolicyAdditionAddrNo(String addrNo, String policyNo, String endorseNo, String oldAddrNo,FriPolicyAdditionMapper mapper) {
		return repository.updatePolicyAdditionAddrNo(addrNo, policyNo,endorseNo,oldAddrNo,mapper);
	}


	


}