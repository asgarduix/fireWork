package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyBrokerMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyBroker;
import com.asi.huanan.vo.Rin1102AQueryOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102ABrokerVOResp;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriTreatyBrokerRepository {

	private Log log = LogFactory.getLog(FriTreatyBrokerRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	public List<FriTreatyBroker> queryRin1206BrokerInfo(String treatyYear, String treatyNo, String brokerId, FriTreatyBrokerMapper mapper)
			throws Exception {

		List<FriTreatyBroker> results = new ArrayList<>();

		try {
			results = mapper.queryRin1206BrokerInfo(treatyYear, treatyNo, brokerId);

		} catch (Exception e) {
			throw e;
		}
		return results;
	}
	
	 /**
     * 
     * @param treatyYear
     * @param treatyNo
     * @return int
     * @throws Exception
     */
    public int deleteByYearNo(final String treatyYear, final String treatyNo, final FriTreatyBrokerMapper mapper) throws Exception
    {
        int count = 0;
		
        try
        {
        	count = mapper.deleteByYearNo(treatyYear, treatyNo);//防呆,需要刪除再打開
         
        } catch(Exception e){
			throw e;
		}
        return count;
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriTreatyBroker> modelList, final FriTreatyBrokerMapper mapper)
			throws Exception {

		int count = 0;
		
		try {

			for (FriTreatyBroker model : modelList) {
				count += mapper.insertSelective(model);
			}

		} catch(Exception e){
			throw e;
		}
		return count;
	}
	
	 /**
     * 
     * @param model
     * @param mapper
     * @return
     * @throws Exception
     */
    public List<Rin1102ABrokerVOResp> queryTreatyBrokers(final Rin1102AQueryOneTreatyVOReq model, final FriTreatyBrokerMapper mapper) throws Exception
    {
    	List<Rin1102ABrokerVOResp> results = null;
		
        try
        {            
        	results = mapper.queryTreatyBrokers(model);

        } catch(Exception e){
			throw e;
		}
       
        return results;
    }
	
	
			/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriTreatyBroker model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	public long queryCount(FriTreatyBroker model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	public int insert(final FriTreatyBroker model, FriTreatyBrokerMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriTreatyBrokerMapper mapper) throws Exception {
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
	public int update(final FriTreatyBroker model, FriTreatyBrokerMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriTreatyBroker model, SqlSession sqlSession) {
	//	FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	//	FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriTreatyBroker model, SqlSession sqlSession) throws Exception {
	//	FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreatyBroker model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	public int insertList(final List<FriTreatyBroker> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriTreatyBrokerMapper mapper = sqlSession
					.getMapper(FriTreatyBrokerMapper.class);

			for (FriTreatyBroker model : modelList) {
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
    // * @return List<FriTreatyBroker>
    // * @throws Exception
    // */
    // public List<FriTreatyBroker> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriTreatyBroker> returnList = null;
    // try {
    // FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
    // FriTreatyBrokerExample ex = new FriTreatyBrokerExample();
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
    //  * @return FriTreatyBroker
    //  * @throws Exception
    //  */
    // public FriTreatyBroker queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriTreatyBroker> returnList = null;
    //     try
    //     {
    //         FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
    //         //FriTreatyBrokerExample xp = new FriTreatyBrokerExample();
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
    public List<FriTreatyBroker> queryByFriTreatyBroker(final FriTreatyBroker model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriTreatyBroker> returnList = null;
        try
        {
//            FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
//            FriTreatyBrokerExample xp = new FriTreatyBrokerExample();
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
    public List<FriTreatyBroker> queryByFriTreatyBroker(final FriTreatyBroker model, SqlSession sqlSession)
            throws Exception
    {
        List<FriTreatyBroker> returnList = null;
//            FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
//            FriTreatyBrokerExample xp = new FriTreatyBrokerExample();
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
            FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
    public int update(final FriTreatyBroker model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	public List<FriTreatyBroker> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyBroker> result = null;
		try {
			FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	public List<FriTreatyBroker> queryAll(SqlSession sqlSession) throws Exception {
		List<FriTreatyBroker> result = null;
		FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	//		FriTreatyBrokerMapper mapper = session.getMapper(FriTreatyBrokerMapper.class);
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
	//public List<FriTreatyBroker> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriTreatyBroker> returnList = null;
    //
	//	try {
	//		FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
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
	//public List<FriTreatyBroker> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriTreatyBroker> returnList = null;
	//	
	//	FriTreatyBrokerMapper mapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}