package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.asi.huanan.service.dao.mybatis.mapper.LogFriFacSameriskMapper;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSamerisk;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSameriskExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSameriskExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

import org.apache.commons.lang3.StringUtils;

@Repository
public class LogFriFacSameriskRepository {

	private Log log = LogFactory.getLog(LogFriFacSameriskRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * 
	 */
	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(LogFriFacSamerisk model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	public long queryCount(LogFriFacSamerisk model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	public int insert(final LogFriFacSamerisk model, LogFriFacSameriskMapper mapper) {
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
	public int deleteByKey(final String primaryKey, LogFriFacSameriskMapper mapper) throws Exception {
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
	public int update(final LogFriFacSamerisk model, LogFriFacSameriskMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final LogFriFacSamerisk model, SqlSession sqlSession) {
	//	LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	//	LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final LogFriFacSamerisk model, SqlSession sqlSession) throws Exception {
	//	LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final LogFriFacSamerisk model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	public int insertList(final List<LogFriFacSamerisk> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			LogFriFacSameriskMapper mapper = sqlSession
					.getMapper(LogFriFacSameriskMapper.class);

			for (LogFriFacSamerisk model : modelList) {
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
    // * @return List<LogFriFacSamerisk>
    // * @throws Exception
    // */
    // public List<LogFriFacSamerisk> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<LogFriFacSamerisk> returnList = null;
    // try {
    // LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
    // LogFriFacSameriskExample ex = new LogFriFacSameriskExample();
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
    //  * @return LogFriFacSamerisk
    //  * @throws Exception
    //  */
    // public LogFriFacSamerisk queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<LogFriFacSamerisk> returnList = null;
    //     try
    //     {
    //         LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
    //         //LogFriFacSameriskExample xp = new LogFriFacSameriskExample();
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
    public List<LogFriFacSamerisk> queryByLogFriFacSamerisk(final LogFriFacSamerisk model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<LogFriFacSamerisk> returnList = null;
        try
        {
            LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
            LogFriFacSameriskExample xp = new LogFriFacSameriskExample();
            Criteria criteria = xp.createCriteria();
            if (StringUtils.isNotBlank(model.getSlipNo()))
            {
            criteria.andSlipNoEqualTo(model.getSlipNo());
            }
            
            if (StringUtils.isNotBlank(model.getLogSeq()))
            {
            criteria.andLogSeqEqualTo(model.getLogSeq());
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
    public List<LogFriFacSamerisk> queryByLogFriFacSamerisk(final LogFriFacSamerisk model, SqlSession sqlSession)
            throws Exception
    {
        List<LogFriFacSamerisk> returnList = null;
//            LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
//            LogFriFacSameriskExample xp = new LogFriFacSameriskExample();
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
            LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
    public int update(final LogFriFacSamerisk model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	public List<LogFriFacSamerisk> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<LogFriFacSamerisk> result = null;
		try {
			LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	public List<LogFriFacSamerisk> queryAll(SqlSession sqlSession) throws Exception {
		List<LogFriFacSamerisk> result = null;
		LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	//		LogFriFacSameriskMapper mapper = session.getMapper(LogFriFacSameriskMapper.class);
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
	//public List<LogFriFacSamerisk> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<LogFriFacSamerisk> returnList = null;
    //
	//	try {
	//		LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
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
	//public List<LogFriFacSamerisk> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<LogFriFacSamerisk> returnList = null;
	//	
	//	LogFriFacSameriskMapper mapper = sqlSession.getMapper(LogFriFacSameriskMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}