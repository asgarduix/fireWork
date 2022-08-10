package com.asi.huanan.service.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.huanan.service.dao.mybatis.mapper.BatchparameterMapper;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchparameter;
import com.asi.huanan.service.dao.mybatis.model.BatchparameterExample;
import com.asi.huanan.service.dao.mybatis.model.BatchparameterExample.Criteria;
import org.apache.commons.lang3.StringUtils;

@Repository
public class BatchparameterRepository {

	private Log log = LogFactory.getLog(BatchparameterRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * 再保臨分到期通知列印_排程執行_batchparameter table
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 */
	public int insertBatchFriFacParam1(Batchparameter modelParam, BatchparameterMapper mapper) {
		
		int count = 0;
		
		count = mapper.insertBatchFriFacParam1(modelParam);
		return count;
	}
	
	/**
	 * 再保臨分到期通知列印_排程執行_batchparameter table
	 * 
	 * @param modelParam
	 * @param mapper
	 * @return
	 */
	public int insertBatchFriFacParam2(Batchparameter modelParam, BatchparameterMapper mapper) {
		
		int count = 0;
		
		count = mapper.insertBatchFriFacParam2(modelParam);
		return count;
	}
	
	/**
	 * 排程使用，找DateValue起日
	 * 
	 * @param taskid
	 * @return
	 * @throws Exception
	 */
	public String queryDateValue1(String taskid) throws Exception {
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		String date1 = null;
		
		try {
			BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
			date1 = mapper.queryDateValue1(taskid);
			
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return date1;
	}
	
	/**
	 * 排程使用，找DateValue訖日
	 * 
	 * @param taskid
	 * @return
	 * @throws Exception
	 */
	public String queryDateValue2(String taskid) throws Exception {
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		String date2 = null;
		
		try {
			BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
			date2 = mapper.queryDateValue2(taskid);
			
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return date2;
	}
	

	
			/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(Batchparameter model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	public long queryCount(Batchparameter model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	public int insert(final Batchparameter model, BatchparameterMapper mapper) {
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
	public int deleteByKey(final String primaryKey, BatchparameterMapper mapper) throws Exception {
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
	public int update(final Batchparameter model, BatchparameterMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final Batchparameter model, SqlSession sqlSession) {
	//	BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	//	BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final Batchparameter model, SqlSession sqlSession) throws Exception {
	//	BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final Batchparameter model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	public int insertList(final List<Batchparameter> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			BatchparameterMapper mapper = sqlSession
					.getMapper(BatchparameterMapper.class);

			for (Batchparameter model : modelList) {
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
    // * @return List<Batchparameter>
    // * @throws Exception
    // */
    // public List<Batchparameter> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<Batchparameter> returnList = null;
    // try {
    // BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
    // BatchparameterExample ex = new BatchparameterExample();
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
    //  * @return Batchparameter
    //  * @throws Exception
    //  */
    // public Batchparameter queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<Batchparameter> returnList = null;
    //     try
    //     {
    //         BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
    //         //BatchparameterExample xp = new BatchparameterExample();
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
    public List<Batchparameter> queryByBatchparameter(final Batchparameter model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<Batchparameter> returnList = null;
        try
        {
            BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
            BatchparameterExample xp = new BatchparameterExample();
            Criteria criteria = xp.createCriteria();
            if (StringUtils.isNotBlank(model.getTaskid()))
            {
            	criteria.andTaskidEqualTo(model.getTaskid());
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
    public List<Batchparameter> queryByBatchparameter(final Batchparameter model, SqlSession sqlSession)
            throws Exception
    {
        List<Batchparameter> returnList = null;
//            BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
//            BatchparameterExample xp = new BatchparameterExample();
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
            BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
    public int update(final Batchparameter model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	public List<Batchparameter> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchparameter> result = null;
		try {
			BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	public List<Batchparameter> queryAll(SqlSession sqlSession) throws Exception {
		List<Batchparameter> result = null;
		BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	//		BatchparameterMapper mapper = session.getMapper(BatchparameterMapper.class);
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
	//public List<Batchparameter> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<Batchparameter> returnList = null;
    //
	//	try {
	//		BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
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
	//public List<Batchparameter> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<Batchparameter> returnList = null;
	//	
	//	BatchparameterMapper mapper = sqlSession.getMapper(BatchparameterMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}