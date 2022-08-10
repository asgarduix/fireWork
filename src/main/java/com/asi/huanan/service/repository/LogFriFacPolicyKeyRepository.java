package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.LogFriFacPolicyMapper;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacPolicyKey;

import com.asi.mechanism.service.connector.MyBatisConnector;

import org.apache.commons.lang3.StringUtils;

@Repository
public class LogFriFacPolicyKeyRepository {

	private Log log = LogFactory.getLog(LogFriFacPolicyKeyRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(LogFriFacPolicyKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				LogFriFacPolicyMapper mapper = sqlSession.getMapper(LogFriFacPolicyMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
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
	public long queryCount(LogFriFacPolicyKey model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			LogFriFacPolicyMapper mapper = sqlSession.getMapper(LogFriFacPolicyMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
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
	public int insert(final LogFriFacPolicyKey model, LogFriFacPolicyMapper mapper) {
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
	public int deleteByKey(final String primaryKey, LogFriFacPolicyMapper mapper) throws Exception {
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
	public int update(final LogFriFacPolicyKey model, LogFriFacPolicyMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final LogFriFacPolicyKey model, SqlSession sqlSession) {
	//	LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
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
	//	LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final LogFriFacPolicyKey model, SqlSession sqlSession) throws Exception {
	//	LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final LogFriFacPolicyKey model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            LogFriFacPolicyMapper mapper = sqlSession.getMapper(LogFriFacPolicyMapper.class);
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
	public int insertList(final List<LogFriFacPolicyKey> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			LogFriFacPolicyMapper mapper = sqlSession
					.getMapper(LogFriFacPolicyMapper.class);

			for (LogFriFacPolicyKey model : modelList) {
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
    // * @return List<LogFriFacPolicyKey>
    // * @throws Exception
    // */
    // public List<LogFriFacPolicyKey> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<LogFriFacPolicyKey> returnList = null;
    // try {
    // LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
    // LogFriFacPolicyKeyExample ex = new LogFriFacPolicyKeyExample();
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
    //  * @return LogFriFacPolicyKey
    //  * @throws Exception
    //  */
    // public LogFriFacPolicyKey queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<LogFriFacPolicyKey> returnList = null;
    //     try
    //     {
    //         LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
    //         //LogFriFacPolicyKeyExample xp = new LogFriFacPolicyKeyExample();
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
    public List<LogFriFacPolicyKey> queryByLogFriFacPolicyKey(final LogFriFacPolicyKey model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<LogFriFacPolicyKey> returnList = null;
        try
        {
//            LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
//            LogFriFacPolicyKeyExample xp = new LogFriFacPolicyKeyExample();
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
    public List<LogFriFacPolicyKey> queryByLogFriFacPolicyKey(final LogFriFacPolicyKey model, SqlSession sqlSession)
            throws Exception
    {
        List<LogFriFacPolicyKey> returnList = null;
//            LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
//            LogFriFacPolicyKeyExample xp = new LogFriFacPolicyKeyExample();
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
            LogFriFacPolicyMapper mapper = sqlSession.getMapper(LogFriFacPolicyMapper.class);
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
    public int update(final LogFriFacPolicyKey model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            LogFriFacPolicyMapper mapper = sqlSession.getMapper(LogFriFacPolicyMapper.class);
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
	public List<LogFriFacPolicyKey> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<LogFriFacPolicyKey> result = null;
		try {
			LogFriFacPolicyMapper mapper = sqlSession.getMapper(LogFriFacPolicyMapper.class);
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
	public List<LogFriFacPolicyKey> queryAll(SqlSession sqlSession) throws Exception {
		List<LogFriFacPolicyKey> result = null;
		LogFriFacPolicyMapper mapper = sqlSession.getMapper(LogFriFacPolicyMapper.class);
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
	//		LogFriFacPolicyKeyMapper mapper = session.getMapper(LogFriFacPolicyKeyMapper.class);
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
	//public List<LogFriFacPolicyKey> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<LogFriFacPolicyKey> returnList = null;
    //
	//	try {
	//		LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
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
	//public List<LogFriFacPolicyKey> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<LogFriFacPolicyKey> returnList = null;
	//	
	//	LogFriFacPolicyKeyMapper mapper = sqlSession.getMapper(LogFriFacPolicyKeyMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}