package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.Rin1206BrokerdetailMapper;
import com.asi.huanan.service.dao.mybatis.model.Rin1206Brokerdetail;
import com.asi.huanan.service.dao.mybatis.model.Rin1206BrokerdetailExample;
import com.asi.huanan.service.dao.mybatis.model.Rin1206BrokerdetailExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class Rin1206BrokerdetailRepository {

	private Log log = LogFactory.getLog(Rin1206BrokerdetailRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	public int insertModel(final Rin1206Brokerdetail model, Rin1206BrokerdetailMapper mapper) throws Exception {
        int count = 0;
		
        try {
            count = mapper.insertSelective(model);
        } catch(Exception e){
			
			throw e;
		}
        
        return count;
    }
	
	//BrokerId & rptId 查詢資料
	public List<Rin1206Brokerdetail> queryByRptIdBrokerId(final Rin1206Brokerdetail model)
            throws Exception {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<Rin1206Brokerdetail> returnList = null;
        try {
            Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
            Rin1206BrokerdetailExample xp = new Rin1206BrokerdetailExample();
            Criteria criteria = xp.createCriteria();
            if (StringUtils.isNotBlank(model.getRptid().toString())) {
            	criteria.andRptidEqualTo(model.getRptid());
            }
            if (StringUtils.isNotBlank(model.getBrokerId())) {
            	criteria.andBrokerIdEqualTo(model.getBrokerId());
            }
			
            returnList = mapper.selectByExample(xp);
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
	public long queryCount(Rin1206Brokerdetail model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	public long queryCount(Rin1206Brokerdetail model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	public int insert(final Rin1206Brokerdetail model, Rin1206BrokerdetailMapper mapper) {
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
	public int deleteByKey(final String primaryKey, Rin1206BrokerdetailMapper mapper) throws Exception {
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
	public int update(final Rin1206Brokerdetail model, Rin1206BrokerdetailMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final Rin1206Brokerdetail model, SqlSession sqlSession) {
	//	Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	//	Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final Rin1206Brokerdetail model, SqlSession sqlSession) throws Exception {
	//	Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final Rin1206Brokerdetail model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	public int insertList(final List<Rin1206Brokerdetail> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			Rin1206BrokerdetailMapper mapper = sqlSession
					.getMapper(Rin1206BrokerdetailMapper.class);

			for (Rin1206Brokerdetail model : modelList) {
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
    // * @return List<Rin1206Brokerdetail>
    // * @throws Exception
    // */
    // public List<Rin1206Brokerdetail> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<Rin1206Brokerdetail> returnList = null;
    // try {
    // Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
    // Rin1206BrokerdetailExample ex = new Rin1206BrokerdetailExample();
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
    //  * @return Rin1206Brokerdetail
    //  * @throws Exception
    //  */
    // public Rin1206Brokerdetail queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<Rin1206Brokerdetail> returnList = null;
    //     try
    //     {
    //         Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
    //         //Rin1206BrokerdetailExample xp = new Rin1206BrokerdetailExample();
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
    public List<Rin1206Brokerdetail> queryByRin1206Brokerdetail(final Rin1206Brokerdetail model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<Rin1206Brokerdetail> returnList = null;
        try
        {
//            Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
//            Rin1206BrokerdetailExample xp = new Rin1206BrokerdetailExample();
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
    public List<Rin1206Brokerdetail> queryByRin1206Brokerdetail(final Rin1206Brokerdetail model, SqlSession sqlSession)
            throws Exception
    {
        List<Rin1206Brokerdetail> returnList = null;
//            Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
//            Rin1206BrokerdetailExample xp = new Rin1206BrokerdetailExample();
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
            Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
    public int update(final Rin1206Brokerdetail model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	public List<Rin1206Brokerdetail> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1206Brokerdetail> result = null;
		try {
			Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	public List<Rin1206Brokerdetail> queryAll(SqlSession sqlSession) throws Exception {
		List<Rin1206Brokerdetail> result = null;
		Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	//		Rin1206BrokerdetailMapper mapper = session.getMapper(Rin1206BrokerdetailMapper.class);
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
	//public List<Rin1206Brokerdetail> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<Rin1206Brokerdetail> returnList = null;
    //
	//	try {
	//		Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
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
	//public List<Rin1206Brokerdetail> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<Rin1206Brokerdetail> returnList = null;
	//	
	//	Rin1206BrokerdetailMapper mapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}