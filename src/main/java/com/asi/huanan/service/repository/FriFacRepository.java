package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacBrokerMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.FriFacExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacExample.Criteria;
import com.asi.huanan.vo.Rin1303QueryCesNoVOResp;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriFacRepository {

	private Log log = LogFactory.getLog(FriFacRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * @param cessionNO
	 * return
	 */
	public  void updateRin1303AcctFlag(final String slipNo, FriFacMapper mapper)
			throws Exception {

		try {
			mapper.updateRin1303AcctFlag(slipNo);
		} catch(Exception e){
			throw e;
		}
	}
	/**
	 * @param cessionNO
	 * return
	 */
	public  List<Rin1303QueryCesNoVOResp> queryCessionNoList(final String cession)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1303QueryCesNoVOResp> returnList = null;

		try {

			FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);

			returnList = mapper.queryCessionNoList(cession);

			log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
		} catch(Exception e){
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return returnList;
	}
	

	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriFac model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
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
	public long queryCount(FriFac model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
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
	public int insert(final FriFac model, FriFacMapper mapper)throws Exception {
		return mapper.insertSelective(model);
	}
	
	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriFacMapper mapper) throws Exception {
		return mapper.deleteByPrimaryKey(primaryKey);
		//return 0;
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final FriFac model, FriFacMapper mapper) throws Exception {
		return mapper.updateByPrimaryKey(model);
		
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriFac model, SqlSession sqlSession) {
	//	FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
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
	//	FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriFac model, SqlSession sqlSession) throws Exception {
	//	FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriFac model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
           // count = mapper.insertSelective(model);
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
	public int insertList(final List<FriFac> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriFacMapper mapper = sqlSession
					.getMapper(FriFacMapper.class);

			for (FriFac model : modelList) {
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
    // * @return List<FriFac>
    // * @throws Exception
    // */
    // public List<FriFac> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriFac> returnList = null;
    // try {
    // FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
    // FriFacExample ex = new FriFacExample();
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
    //  * @return FriFac
    //  * @throws Exception
    //  */
    // public FriFac queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriFac> returnList = null;
    //     try
    //     {
    //         FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
    //         //FriFacExample xp = new FriFacExample();
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
    public List<FriFac> queryByFriFac(final FriFac model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriFac> returnList = null;
        try
        {
            FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
            FriFacExample xp = new FriFacExample();
            Criteria criteria = xp.createCriteria();
            
            if(StringUtils.isNotBlank(model.getSlipNo())) {
            	criteria.andSlipNoEqualTo(model.getSlipNo());
            }
            
            if(StringUtils.isNotBlank(model.getCessionNo())) {
            	criteria.andCessionNoEqualTo(model.getCessionNo());
            }
            
            if(StringUtils.isNotBlank(model.getPolicyNoSeq())) {
            	criteria.andPolicyNoSeqEqualTo(model.getPolicyNoSeq());
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
    public List<FriFac> queryByFriFac(final FriFac model, SqlSession sqlSession)
            throws Exception
    {
        List<FriFac> returnList = null;
//            FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
//            FriFacExample xp = new FriFacExample();
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
            FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
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
    public int update(final FriFac model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
            //count = mapper.updateByPrimaryKey(model);
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
	public List<FriFac> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFac> result = null;
		try {
			FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
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
	public List<FriFac> queryAll(SqlSession sqlSession) throws Exception {
		List<FriFac> result = null;
		FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
		result = mapper.selectByExample(null);
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
	//		FriFacMapper mapper = session.getMapper(FriFacMapper.class);
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
	//public List<FriFac> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriFac> returnList = null;
    //
	//	try {
	//		FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
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
	//public List<FriFac> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriFac> returnList = null;
	//	
	//	FriFacMapper mapper = sqlSession.getMapper(FriFacMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}