package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriComCreditMapper;
import com.asi.huanan.service.dao.mybatis.model.FriComCredit;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriComCreditRepository {

	private Log log = LogFactory.getLog(FriComCreditRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	public List<FriComCredit> queryCredit(String rinComId) throws Exception {

		List<FriComCredit> results = new ArrayList<>();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		try
        {
			FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
			results = mapper.queryCredit(rinComId);
			
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return results;
	}
	
	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriComCredit model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	public long queryCount(FriComCredit model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	public int insert(final FriComCredit model, FriComCreditMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriComCreditMapper mapper) throws Exception {
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
	public int update(final FriComCredit model, FriComCreditMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriComCredit model, SqlSession sqlSession) {
	//	FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	//	FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriComCredit model, SqlSession sqlSession) throws Exception {
	//	FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriComCredit model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	public int insertList(final List<FriComCredit> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriComCreditMapper mapper = sqlSession
					.getMapper(FriComCreditMapper.class);

			for (FriComCredit model : modelList) {
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
    // * @return List<FriComCredit>
    // * @throws Exception
    // */
    // public List<FriComCredit> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriComCredit> returnList = null;
    // try {
    // FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
    // FriComCreditExample ex = new FriComCreditExample();
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
    //  * @return FriComCredit
    //  * @throws Exception
    //  */
    // public FriComCredit queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriComCredit> returnList = null;
    //     try
    //     {
    //         FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
    //         //FriComCreditExample xp = new FriComCreditExample();
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
    public List<FriComCredit> queryByFriComCredit(final FriComCredit model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriComCredit> returnList = null;
        try
        {
//            FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
//            FriComCreditExample xp = new FriComCreditExample();
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
    public List<FriComCredit> queryByFriComCredit(final FriComCredit model, SqlSession sqlSession)
            throws Exception
    {
        List<FriComCredit> returnList = null;
//            FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
//            FriComCreditExample xp = new FriComCreditExample();
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
            FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
    public int update(final FriComCredit model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	public List<FriComCredit> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriComCredit> result = null;
		try {
			FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	public List<FriComCredit> queryAll(SqlSession sqlSession) throws Exception {
		List<FriComCredit> result = null;
		FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	//		FriComCreditMapper mapper = session.getMapper(FriComCreditMapper.class);
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
	//public List<FriComCredit> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriComCredit> returnList = null;
    //
	//	try {
	//		FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
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
	//public List<FriComCredit> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriComCredit> returnList = null;
	//	
	//	FriComCreditMapper mapper = sqlSession.getMapper(FriComCreditMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}