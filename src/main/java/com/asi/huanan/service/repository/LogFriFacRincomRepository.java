package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.asi.huanan.service.dao.mybatis.mapper.LogFriFacRincomMapper;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRincom;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRincomExample;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRincomExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

import org.apache.commons.lang3.StringUtils;

@Repository
public class LogFriFacRincomRepository {

	private Log log = LogFactory.getLog(LogFriFacRincomRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	
	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(LogFriFacRincom model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	public long queryCount(LogFriFacRincom model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	public int insert(final LogFriFacRincom model, LogFriFacRincomMapper mapper) {
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
	public int deleteByKey(final String primaryKey, LogFriFacRincomMapper mapper) throws Exception {
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
	public int update(final LogFriFacRincom model, LogFriFacRincomMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final LogFriFacRincom model, SqlSession sqlSession) {
	//	LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	//	LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final LogFriFacRincom model, SqlSession sqlSession) throws Exception {
	//	LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final LogFriFacRincom model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	public int insertList(final List<LogFriFacRincom> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			LogFriFacRincomMapper mapper = sqlSession
					.getMapper(LogFriFacRincomMapper.class);

			for (LogFriFacRincom model : modelList) {
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
    // * @return List<LogFriFacRincom>
    // * @throws Exception
    // */
    // public List<LogFriFacRincom> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<LogFriFacRincom> returnList = null;
    // try {
    // LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
    // LogFriFacRincomExample ex = new LogFriFacRincomExample();
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
    //  * @return LogFriFacRincom
    //  * @throws Exception
    //  */
    // public LogFriFacRincom queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<LogFriFacRincom> returnList = null;
    //     try
    //     {
    //         LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
    //         //LogFriFacRincomExample xp = new LogFriFacRincomExample();
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
    public List<LogFriFacRincom> queryByLogFriFacRincom(final LogFriFacRincom model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<LogFriFacRincom> returnList = null;
        try
        {
//            LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
//            LogFriFacRincomExample xp = new LogFriFacRincomExample();
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
    public List<LogFriFacRincom> queryByLogFriFacRincom(final LogFriFacRincom model, SqlSession sqlSession)
            throws Exception
    {
        List<LogFriFacRincom> returnList = null;
//            LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
//            LogFriFacRincomExample xp = new LogFriFacRincomExample();
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
            LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
    public int update(final LogFriFacRincom model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	public List<LogFriFacRincom> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<LogFriFacRincom> result = null;
		try {
			LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	public List<LogFriFacRincom> queryAll(SqlSession sqlSession) throws Exception {
		List<LogFriFacRincom> result = null;
		LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	//		LogFriFacRincomMapper mapper = session.getMapper(LogFriFacRincomMapper.class);
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
	//public List<LogFriFacRincom> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<LogFriFacRincom> returnList = null;
    //
	//	try {
	//		LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
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
	//public List<LogFriFacRincom> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<LogFriFacRincom> returnList = null;
	//	
	//	LogFriFacRincomMapper mapper = sqlSession.getMapper(LogFriFacRincomMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}