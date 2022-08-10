package com.asi.huanan.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyDtlMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;
import com.asi.huanan.service.repository.FriPolicyDtlRepository;
import com.asi.huanan.vo.R1304QueryPolicyDtlVO;
import com.asi.huanan.vo.Rin1203AVOResp;
import com.asi.huanan.vo.Rin1304FriPolicyDtlVOResp;
import com.asi.huanan.vo.Rin1304GetAddrNoVO;
import com.asi.huanan.vo.Rin1304GetUsePropVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;


@Service
public class FriPolicyDtlService {

	private Log log = LogFactory.getLog(FriPolicyDtlService.class);

	@Autowired
	private FriPolicyDtlRepository repository;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * Rin1203_更改地段代號
	 * @param area_code
	 * @param policy_no
	 * @param endorse_no
	 * @param addr_no
	 * @return
	 * @throws Exception
	 */
	public int updateAreaCode(String areaCode,String policyNo,String endorseNo ,Integer addrNo)throws Exception{
		return repository.updateAreaCode(areaCode ,policyNo,endorseNo,addrNo);
	}

	/**
	 * Rin1203_更改地段地址
	 * @param prop_addr
	 * @param policy_no
	 * @param endorse_no
	 * @param addr_no
	 * @return
	 * @throws Exception
	 */
	public int updateAddress(String propAddr, String policyNo, String endorseNo, Integer addrNo) throws Exception {
		return repository.updateAddress(propAddr, policyNo, endorseNo, addrNo);
	}

	/**
	 * Rin1203_更正同險號
	 * @param risk_no
	 * @param risk_name
	 * @param policy_no
	 * @param endorse_no
	 * @param addr_no
	 * @return
	 * @throws Exception
	 */
	public int updateRiskNo(String riskNo, String riskName, String policyNo, String endorseNo,Integer addrNo)throws Exception {
		return repository.updateRiskNo(riskNo, riskName, policyNo, endorseNo,addrNo);
		
	}

	/**
	 * Rin1203_確認同險
	 * @param record
	 * @param riskNo
	 * @param riskName
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyDelList(List<Rin1203AVOResp> record, String riskNo, String riskName)throws Exception {
		return repository.updatePolicyDelList(record, riskNo, riskName);
		
	}
	
	/**
	 * Rin1203_更改舊同險號
	 * @param txtRiskNo
	 * @param txtRiskName
	 * @param txtPolicyNo
	 * @param txtEndorseNo
	 * @param txtPropAddr
	 * @return
	 * @throws Exception 
	 */
	public int updateOldRiskNo(String riskNo, String riskName, String policyNo, String endorseNo,String propAddr) throws Exception {
		return repository.updateOldRiskNo(riskNo, riskName, policyNo, endorseNo,propAddr);
		
	}
	
//	/**
//	 * Rin1304_臨分分入，確認查詢Fri_policy_dtl(第1層)
//	 * @param policyNo
//	 * @param endorseNo
//	 * @param mapper
//	 */
//	public List<Rin1304VOResp> getPolicyTreeData1(String policyNo,String endorseNo ,FriPolicyDtlMapper mapper ) {
//		return repository.getPolicyTreeData1(policyNo, endorseNo,mapper);
//		
//	}
	
	
	/**
	 * Rin1304_臨分分入，刪除保批單明細檔
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public int deletePolicyDtl(String policyNo, String endorseNo,String addrNo,  FriPolicyDtlMapper mapper) {
		return repository.deletePolicyDtl(policyNo, endorseNo,addrNo,mapper);
		
	}
	
	

	/**
	 * Rin1304_臨分分入，查詢保批單明細檔
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public List<Rin1304VO> queryPolicyDtl(String policyNo, String endorseNo, FriPolicyDtlMapper mapper) {
		return repository.queryPolicyDtl(policyNo, endorseNo,mapper);
	}
	
	/**
	 * Rin1304_臨分分入，保批單明細檔查詢結果
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 */
	public List<Rin1304FriPolicyDtlVOResp> queryPolicyDtlByPrimaryKey(String policyNo, String endorseNo, String addrNo)throws Exception {
		return repository.queryPolicyDtlByPrimaryKey(policyNo, endorseNo,addrNo);
		
	}

	/**
	 * Rin1304_臨分分入，查詢是否有保單地址序號
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	public List<R1304QueryPolicyDtlVO> queryaddrNo(String policyNo, String endorseNo, String addrNoOri)throws Exception {
		return repository.queryaddrNo(policyNo, endorseNo,addrNoOri);
	}

	/**
	 * Rin1304_臨分分入_保單地址序號+1
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNoOri
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateAddrNoOriPlusOne(String policyNo, String endorseNo, String addrNoOri,FriPolicyDtlMapper mapper) throws Exception{
		return repository.updateAddrNoOriPlusOne(policyNo, endorseNo,addrNoOri,mapper);
	}

	
	/**
	 * Rin1304_臨分分入_將保單地址序號全部更新
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateAddrNoOriAll(String policyNo, String endorseNo,FriPolicyDtlMapper mapper) throws Exception{
		return repository.updateAddrNoOriAll(policyNo, endorseNo,mapper);
	}

	/**
	 * Rin1304_臨分分入_加總保額
	 * @param policyNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyDtlVOResp> sumPolicyEndorseAmt(String policyNo, String addrNo) throws Exception {
		return repository.sumPolicyEndorseAmt(policyNo, addrNo);
	}

	/**
	 * Rin1304_臨分分入_新增保批單明細檔 by 續保單號
	 * @param policyNo
	 * @param endorseNo
	 * @param getPolicyNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertFriPolicyDtlForOldPolicy(String policyNo, String endorseNo, String getPolicyNo,FriPolicyDtlMapper mapper)throws Exception {
		return repository.insertFriPolicyDtlForOldPolicy(policyNo,endorseNo,getPolicyNo,mapper);
	}
	
	
	/**
	 * Rin1304_建立新的自動產生調整批單號(ADJ)
	 * @param policyNo
	 * @param endorseNo
	 * @param policyNoADJ
	 * @param endorseNoADJ
	 * @param friPolicyDtlMapper
	 * @return
	 * @throws Exception
	 */
	public int createADJpolicyDtl(String policyNo, String endorseNo, String policyNoADJ, String endorseNoADJ,
			FriPolicyDtlMapper friPolicyDtlMapper) throws Exception{
		return repository.createADJpolicyDtl(policyNo, endorseNo,policyNoADJ,endorseNoADJ,friPolicyDtlMapper);
		
	}

	/**
	 * Rin1304_轉檔_將明細保期更改同主檔保期
	 * @param policyDbgn
	 * @param policyDend
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyDates(Date policyDbgn, Date policyDend, String policyNo, String endorseNo)throws Exception {
		return repository.updatePolicyDates(policyDbgn,policyDend,policyNo,endorseNo);
	}

	/**
	 * Rin1304_保批單明細檔_查詢使用性質名稱
	 * @param usePropId
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304GetUsePropVO> getUseProp(String usePropId)throws Exception {
		return repository.getUseProp(usePropId);
	}

	/**
	 * Rin1304_反算_更改保批當明細檔保額/保費
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param amtTyp
	 * @param premTyp
	 * @param amtEar
	 * @param premEar
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyDtlAmtPrem(String policyNo, String endorseNo, String addrNo, BigDecimal amtTyp,
			BigDecimal premTyp, BigDecimal amtEar, BigDecimal premEar,FriPolicyDtlMapper mapper)throws Exception {
		return repository.updatePolicyDtlAmtPrem(policyNo,endorseNo,addrNo,amtTyp,premTyp,amtEar,premEar,mapper);
		
	}

	/**
	 * Rin1304臨分分入_反算_取得地址序號
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304GetAddrNoVO> getAddrNo(String policyNo, String endorseNo ,FriPolicyDtlMapper mapper) throws Exception{
		return repository.getAddrNo(policyNo, endorseNo,mapper);
	}

	/**
	 * Rin1304臨分分入_保批單明細檔_查詢地址序號&保單地址序號最大值
	 * @param policyNo
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyDtl> qreryPolicyAddrNo(String policyNo,String endorseNo)throws Exception {
		return repository.qreryPolicyAddrNo(policyNo,endorseNo);
	}
	//=====針對使用自訂SQL結束=====
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriPolicyDtl model, FriPolicyDtlMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriPolicyDtlMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicyDtl model, FriPolicyDtlMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriPolicyDtl model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriPolicyDtl model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriPolicyDtl model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriPolicyDtl> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriPolicyDtl＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriPolicyDtl> modelList)
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
    // public FriPolicyDtl queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriPolicyDtl
     * @return
     * @throws Exception
     */
    public List<FriPolicyDtl> queryByFriPolicyDtl(final FriPolicyDtl model)
            throws Exception
    {
        return repository.queryByFriPolicyDtl(model);
    }

    /**
     * 
     * @param FriPolicyDtl
     * @return
     * @throws Exception
     */
    public List<FriPolicyDtl> queryByFriPolicyDtl(final FriPolicyDtl model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriPolicyDtl(model, sqlSession);
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
    public int update(final FriPolicyDtl model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriPolicyDtl> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriPolicyDtl> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<FriPolicyDtl> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }

    /**
     * 
     * @param sqlSession
     * @return
     * @throws Exception
     */
//    public List<FriPolicyDtl> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriPolicyDtl model) throws Exception {
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
	public long queryCount(FriPolicyDtl model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}

	/**
	 * Rin1304_臨分分入_修改保批單明細地址序號
	 * @param record
	 * @param oldAddrNo
	 * @param mapper
	 * @return
	 */
	public int updateAddrNo(FriPolicyDtl record, String oldAddrNo,FriPolicyDtlMapper mapper) {
		return repository.updateAddrNo(record, oldAddrNo,mapper);
		
	}
	

}