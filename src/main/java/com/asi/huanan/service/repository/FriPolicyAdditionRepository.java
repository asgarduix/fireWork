package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAddition;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAdditionExample;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAdditionExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAdditionKey;
import com.asi.huanan.vo.Rin1304FriPolicyAdditionVOResp;
import com.asi.huanan.vo.Rin1304QueryDdlAdditionListVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriPolicyAdditionRepository {

	private Log log = LogFactory.getLog(FriPolicyAdditionRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	
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
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304FriPolicyAdditionVOResp> result = new ArrayList<>();
		try {
			FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			result = mapper.queryPolicyAdditionResult(policyNo,endorseNo,addrNo,propNo,additionSeq,additionNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	
	/**
	 * Rin1304D_臨分分入，附加險代號下拉選單
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryDdlAdditionListVO> queryDdlAdditionList() throws Exception{
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304QueryDdlAdditionListVO> result = new ArrayList<>();
		try {
			FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			result = mapper.queryDdlAdditionList();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	
	/**
	 * Rin1304_臨分分入_更新保批單附加險明細檔
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyAddition(FriPolicyAddition parJson, FriPolicyAdditionMapper mapper)throws Exception {
        return mapper.updatePolicyAddition(parJson);
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
	public int insertFriPolicyAdditionForOldPolicy(String policyNo, String endorseNo, String getPolicyNo,FriPolicyAdditionMapper mapper) {
		int count = 0;
		count = mapper.insertFriPolicyAdditionForOldPolicy(policyNo,endorseNo, getPolicyNo);
	       return count;
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
		int count = 0;
		count = mapper.createADJpolicyAddition(policyNo,endorseNo, policyNoADJ,endorseNoADJ);
	       return count;
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
	public List<Rin1304SumAmtPremRespVO> sumAmtPrem(String policyNo, String endorseNo, String addrNo, String propNo,FriPolicyAdditionMapper mapper)  throws Exception{
		List<Rin1304SumAmtPremRespVO> result=null;
		result = mapper.sumAmtPrem(policyNo,endorseNo,addrNo,propNo);
		return result;
	}
	//=====針對使用自訂SQL結束=====

	
			/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriPolicyAddition model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			//			count = mapper.countByExample(example);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

		return count;
	}
	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriPolicyAddition model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
		//		count = mapper.countByExample(example);

		return count;
	}
	
	//=====以下為基本使用的=====
	
	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	public int insert(final FriPolicyAddition model, FriPolicyAdditionMapper mapper) {
		return mapper.insertSelective(model);
	}
	
	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriPolicyAdditionMapper mapper) throws Exception {
		//return mapper.deleteByPrimaryKey(primaryKey);
		return 0;
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicyAddition model, FriPolicyAdditionMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriPolicyAddition model, SqlSession sqlSession) {
	//	FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
	//	return mapper.insertSelective(model);
	//}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws Exception {
	//	FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriPolicyAddition model, SqlSession sqlSession) throws Exception {
	//	FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriPolicyAddition model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
            count = mapper.insertSelective(model);
            
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriPolicyAddition> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriPolicyAdditionMapper mapper = sqlSession
					.getMapper(FriPolicyAdditionMapper.class);

			for (FriPolicyAddition model : modelList) {
				//count += mapper.insertSelective(model);
			}

			sqlSession.commit();
		} catch(Exception e){
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

    // /**
    // *
    // * @return List<FriPolicyAddition>
    // * @throws Exception
    // */
    // public List<FriPolicyAddition> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriPolicyAddition> returnList = null;
    // try {
    // FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
    // FriPolicyAdditionExample ex = new FriPolicyAdditionExample();
    // Criteria cr = ex.createCriteria();
    // cr.andJobIdEqualTo(jobId);
    // returnList = mapper.selectByExample(ex);
    // } finally {
    // sqlSession.close();
    // }
    // return returnList;
    // }

    // /**
    //  * 
    //  * @param key
    //  * @return FriPolicyAddition
    //  * @throws Exception
    //  */
    // public FriPolicyAddition queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriPolicyAddition> returnList = null;
    //     try
    //     {
    //         FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
    //         //FriPolicyAdditionExample xp = new FriPolicyAdditionExample();
    //         //Criteria criteria = xp.createCriteria();
    //         // if (StringUtils.isNotBlank(jobId))
    //         // {
    //         // criteria.andJobIdEqualTo(jobId);
    //         // }
    //         //returnList = mapper.selectByExample(xp);
    //         log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
    //     }
    //     finally
    //     {
    //         sqlSession.close();
    //     }
    //     return CollectionUtils.isNotEmpty(returnList) ? returnList.get(0)
    //             : null;
    // }


	/** 
	/* @param key
	/* @return 
	/* @throws Exception
	/*/
    public List<FriPolicyAddition> queryByFriPolicyAddition(final FriPolicyAddition model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriPolicyAddition> returnList = null;
        try
        {
            FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
            FriPolicyAdditionExample xp = new FriPolicyAdditionExample();
            Criteria criteria = xp.createCriteria();
            // 保、批單號為 key 且批單號有可能是空
			criteria.andPolicyNoEqualTo(model.getPolicyNo());
			criteria.andEndorseNoEqualTo(model.getEndorseNo());
			if (model.getAddrNo() != null) {
				criteria.andAddrNoEqualTo(model.getAddrNo());
			}
			if (model.getPropNo() != null) {
				criteria.andPropNoEqualTo(model.getPropNo());
			}
			if (StringUtils.isNotBlank(model.getAdditionNo())) {
				criteria.andAdditionNoEqualTo(model.getAdditionNo());
			}
            returnList = mapper.selectByExample(xp);
            log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return returnList;
    }

	/** 
	/* @param key
	/* @return 
	/* @throws Exception
	/*/
    public List<FriPolicyAddition> queryByFriPolicyAddition(final FriPolicyAddition model, SqlSession sqlSession)
            throws Exception
    {
        List<FriPolicyAddition> returnList = null;
//            FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
//            FriPolicyAdditionExample xp = new FriPolicyAdditionExample();
//            Criteria criteria = xp.createCriteria();
//            if (StringUtils.isNotBlank(model.get{VAR_NAME}()))
//            {
//            criteria.and{VAR_NAME}EqualTo(model.get{VAR_NAME}());
//            }
//			
//            // if (StringUtils.isNotBlank(model.getJobName()))
//            // {
//            // criteria.andJobNameEqualTo(model.getJobName());
//            // }
//            // returnList = mapper.selectByExample(xp);
//            log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
        return returnList;
    }

    /**
     * 
     * @param key
     * @return int
     * @throws Exception
     */
    public int deleteByKey(final String primaryKey) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
            // count = mapper.deleteByPrimaryKey(primaryKey);//防呆,需要刪除再打開
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
    }

    /**
     * 
     * @param model
     * @return int
     * @throws Exception
     */
    public int update(final FriPolicyAddition model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
            count = mapper.updateByPrimaryKey(model);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
    }
	
	/**
	/* @param insId
	/* @param prgId
	/* @param vhclInsId
	/* @return
	*/
	public List<FriPolicyAddition> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicyAddition> result = null;
		try {
			FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			result = mapper.selectByExample(null);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	/**
	/* @param insId
	/* @param prgId
	/* @param vhclInsId
	/* @return
	*/
	public List<FriPolicyAddition> queryAll(SqlSession sqlSession) throws Exception {
		List<FriPolicyAddition> result = null;
		FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}


	/**
	 * Rin1304_附加險明細檔_取附加險序號最大值
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param propNo
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyAdditionKey> getMaxAdditionSeq(String policyNo, String endorseNo, String addrNo,
			String propNo)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicyAdditionKey> result = null;
		try {
			FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			result = mapper.getMaxAdditionSeq(policyNo,endorseNo,addrNo,propNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
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
		int result = 0;
		result = mapper.updatePolicyAdditionAddrNo(addrNo,policyNo,endorseNo,oldAddrNo);
		return result;
	}

	
	///**
	// * @param insId
	// * @param prgId
	// * @param vhclInsId
	// * @return
	// */
	//public int deleteAll() throws Exception {
	//	SqlSession sqlSession = ConnectionFactory.getSession().openSession();
	//	int result = 0;
	//	try {
	//		FriPolicyAdditionMapper mapper = session.getMapper(FriPolicyAdditionMapper.class);
	//		result = mapper.deleteAll();
	//		session.commit();
	//	} catch (Exception e) {
	//		session.rollback();
	//		throw e;
	//	} finally {
	//		session.close();
	//	}
	//	return result;
	//}
	
	///**
	// * 注意，此method解註解，須在mapper.java中實作selectDistintXXX方法
	// *可能類似「@Select("select distinct upvirs from amdupf1") List<Amdupf1> selectDistintTypins();」
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	//public List<FriPolicyAddition> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriPolicyAddition> returnList = null;
    //
	//	try {
	//		FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
	//		returnList = mapper.selectDistint{COL_NM}();
	//	} catch (Exception e) {
	//		throw e;
	//	} finally {
	//		sqlSession.close();
	//	}
    //
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
    //
	///**
	// * 注意，此method解註解，須在mapper.java中實作selectDistintXXX方法
	// *可能類似「@Select("select distinct upvirs from amdupf1") List<Amdupf1> selectDistintTypins();」
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	//public List<FriPolicyAddition> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriPolicyAddition> returnList = null;
	//	
	//	FriPolicyAdditionMapper mapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}