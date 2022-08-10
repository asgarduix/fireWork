package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriRetainLimitMapper;
import com.asi.huanan.service.dao.mybatis.model.FriRetainLimit;
import com.asi.huanan.service.dao.mybatis.model.FriRetainLimitExample;
import com.asi.huanan.service.dao.mybatis.model.FriRetainLimitExample.Criteria;
import com.asi.huanan.vo.Rin1105DeleteRetainVOReq;
import com.asi.huanan.vo.Rin1105VOResp;
import com.asi.huanan.vo.Rin1304QueryLimitVO;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriRetainLimitRepository {

	private Log log = LogFactory.getLog(FriRetainLimitRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	  /**
     * 
     * @param model
     * @return int
     * @throws Exception
     */
    public int deleteRetainsByPkList(final List<Rin1105DeleteRetainVOReq> model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
            count = mapper.deleteRetainsByPkList(model);
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
	/* @param treatyYear
	 * @param limitId
	 * @return
	 * @throws Exception
	 */
    public List<Rin1105VOResp> queryRetainList(final String treatyYear, final String limitId)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<Rin1105VOResp> returnList = null;
        try
        {
            FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
       
             returnList = mapper.queryRetainList(treatyYear, limitId);
           
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
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriRetainLimit model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
	public long queryCount(FriRetainLimit model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
	public int insert(final FriRetainLimit model, FriRetainLimitMapper mapper) {
		//return mapper.insertSelective(model);
		return 0;
	}
	
	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriRetainLimitMapper mapper) throws Exception {
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
	public int update(final FriRetainLimit model, FriRetainLimitMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriRetainLimit model, SqlSession sqlSession) {
	//	FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
	//	FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriRetainLimit model, SqlSession sqlSession) throws Exception {
	//	FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriRetainLimit model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
	public int insertList(final List<FriRetainLimit> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriRetainLimitMapper mapper = sqlSession
					.getMapper(FriRetainLimitMapper.class);

			for (FriRetainLimit model : modelList) {
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
    // * @return List<FriRetainLimit>
    // * @throws Exception
    // */
    // public List<FriRetainLimit> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriRetainLimit> returnList = null;
    // try {
    // FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
    // FriRetainLimitExample ex = new FriRetainLimitExample();
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
    //  * @return FriRetainLimit
    //  * @throws Exception
    //  */
    // public FriRetainLimit queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriRetainLimit> returnList = null;
    //     try
    //     {
    //         FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
    //         //FriRetainLimitExample xp = new FriRetainLimitExample();
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
    public List<FriRetainLimit> queryByFriRetainLimit(final FriRetainLimit model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriRetainLimit> returnList = null;
        try
        {
            FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
            FriRetainLimitExample xp = new FriRetainLimitExample();
            Criteria criteria = xp.createCriteria();
//            if (StringUtils.isNotBlank(model.get{VAR_NAME}()))
//            {
//            criteria.and{VAR_NAME}EqualTo(model.get{VAR_NAME}());
//            }

			if(StringUtils.isNotBlank(model.getTreatyYear())) {
				criteria.andTreatyYearEqualTo(model.getTreatyYear());
			}
			
			if(StringUtils.isNotBlank(model.getLimitId())) {
				criteria.andLimitIdEqualTo(model.getLimitId());
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
    public List<FriRetainLimit> queryByFriRetainLimit(final FriRetainLimit model, SqlSession sqlSession)
            throws Exception
    {
        List<FriRetainLimit> returnList = null;
//            FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
//            FriRetainLimitExample xp = new FriRetainLimitExample();
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
            FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
    public int update(final FriRetainLimit model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
	public List<FriRetainLimit> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriRetainLimit> result = null;
		try {
			FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
	public List<FriRetainLimit> queryAll(SqlSession sqlSession) throws Exception {
		List<FriRetainLimit> result = null;
		FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}

	/**
	 * Rin1304_保批單明細畫面_查詢保單限額、限額代號、限額
	 * @param policyYear
	 * @param propCode
	 * @param constClass
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryLimitVO> queryLimit(String policyYear, String propCode, String constClass) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304QueryLimitVO> result = null;
		try {
			FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
			
			result = mapper.queryLimit(policyYear,propCode,constClass);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
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
	//		FriRetainLimitMapper mapper = session.getMapper(FriRetainLimitMapper.class);
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
	//public List<FriRetainLimit> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriRetainLimit> returnList = null;
    //
	//	try {
	//		FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
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
	//public List<FriRetainLimit> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriRetainLimit> returnList = null;
	//	
	//	FriRetainLimitMapper mapper = sqlSession.getMapper(FriRetainLimitMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}