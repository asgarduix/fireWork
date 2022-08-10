package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.Rin120602Mapper;
import com.asi.huanan.service.dao.mybatis.mapper.Rin120603Mapper;
import com.asi.huanan.service.dao.mybatis.model.Rin120602;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class Rin120602Repository {

	private Log log = LogFactory.getLog(Rin120602Repository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	//truncateTable;
	public void truncateTable() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		
		try {
			Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);  
			mapper.truncateTable();
			sqlSession.commit();
		} catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
		finally
		{
			sqlSession.close();
		}
	}
	
	//update Bill_no
	public int updateBillNo(Long rptid, Rin120602Mapper mapper) throws Exception {
		int count = 0;
		try {
			mapper.updateBillNo(rptid);
			
		} catch (Exception e) {
			throw e;
		}
		
		return count;
	}
	
	//
	public List<Rin120602> queryTop1(Long rptid, Rin120602Mapper rin120602Mapper){
		List<Rin120602> returnList = null;
		
		try {
			returnList = rin120602Mapper.queryTop1(rptid);
		} catch (Exception e) {
			throw e;
		}
		
		return returnList;
	}
	
	
	//rin1206 insert多筆
	public int insertList(final List<Rin120602> modelList, Rin120602Mapper rin120602Mapper)
			throws Exception {		
		int count = 0;
		
		try {
			
			for (Rin120602 model : modelList) {
				count += rin120602Mapper.insertSelective(model);
			}
			
		} catch(Exception e){
			
			throw e;
		}
		return count;
	}
	
	
			/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(Rin120602 model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	public long queryCount(Rin120602 model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	public int insert(final Rin120602 model, Rin120602Mapper mapper) {
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
	public int deleteByKey(final String primaryKey, Rin120602Mapper mapper) throws Exception {
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
	public int update(final Rin120602 model, Rin120602Mapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final Rin120602 model, SqlSession sqlSession) {
	//	Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	//	Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final Rin120602 model, SqlSession sqlSession) throws Exception {
	//	Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final Rin120602 model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	public int insertList(final List<Rin120602> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			Rin120602Mapper mapper = sqlSession
					.getMapper(Rin120602Mapper.class);

			for (Rin120602 model : modelList) {
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
    // * @return List<Rin120602>
    // * @throws Exception
    // */
    // public List<Rin120602> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<Rin120602> returnList = null;
    // try {
    // Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
    // Rin120602Example ex = new Rin120602Example();
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
    //  * @return Rin120602
    //  * @throws Exception
    //  */
    // public Rin120602 queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<Rin120602> returnList = null;
    //     try
    //     {
    //         Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
    //         //Rin120602Example xp = new Rin120602Example();
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
    public List<Rin120602> queryByRin120602(final Rin120602 model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<Rin120602> returnList = null;
        try
        {
//            Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
//            Rin120602Example xp = new Rin120602Example();
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
    public List<Rin120602> queryByRin120602(final Rin120602 model, SqlSession sqlSession)
            throws Exception
    {
        List<Rin120602> returnList = null;
//            Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
//            Rin120602Example xp = new Rin120602Example();
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
            Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
    public int update(final Rin120602 model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	public List<Rin120602> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin120602> result = null;
		try {
			Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	public List<Rin120602> queryAll(SqlSession sqlSession) throws Exception {
		List<Rin120602> result = null;
		Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	//		Rin120602Mapper mapper = session.getMapper(Rin120602Mapper.class);
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
	//public List<Rin120602> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<Rin120602> returnList = null;
    //
	//	try {
	//		Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
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
	//public List<Rin120602> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<Rin120602> returnList = null;
	//	
	//	Rin120602Mapper mapper = sqlSession.getMapper(Rin120602Mapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}