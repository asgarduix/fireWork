package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyRincomMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyRincom;
import com.asi.huanan.vo.Rin1102AQueryOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102ARincomVOResp;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriTreatyRincomRepository {

	private Log log = LogFactory.getLog(FriTreatyRincomRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
    /**
     * 
     * @param treatyYear
     * @param treatyNo
     * @return int
     * @throws Exception
     */
    public int deleteByYearNo(final String treatyYear, final String treatyNo, final FriTreatyRincomMapper mapper) throws Exception
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
	public int insertList(final List<FriTreatyRincom> modelList, final FriTreatyRincomMapper mapper)
			throws Exception {
		int count = 0;
		
		try {			
			
			for (FriTreatyRincom model : modelList) {

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
    public List<Rin1102ARincomVOResp> queryTreatyRincoms(final Rin1102AQueryOneTreatyVOReq model, final FriTreatyRincomMapper mapper) throws Exception
    {
    	List<Rin1102ARincomVOResp> results = null;
		
        try
        {            
        	results = mapper.queryTreatyRincoms(model);

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
	public long queryCount(FriTreatyRincom model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	public long queryCount(FriTreatyRincom model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	public int insert(final FriTreatyRincom model, FriTreatyRincomMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriTreatyRincomMapper mapper) throws Exception {
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
	public int update(final FriTreatyRincom model, FriTreatyRincomMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriTreatyRincom model, SqlSession sqlSession) {
	//	FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	//	FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriTreatyRincom model, SqlSession sqlSession) throws Exception {
	//	FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreatyRincom model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	public int insertList(final List<FriTreatyRincom> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriTreatyRincomMapper mapper = sqlSession
					.getMapper(FriTreatyRincomMapper.class);

			for (FriTreatyRincom model : modelList) {
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
    // * @return List<FriTreatyRincom>
    // * @throws Exception
    // */
    // public List<FriTreatyRincom> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriTreatyRincom> returnList = null;
    // try {
    // FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
    // FriTreatyRincomExample ex = new FriTreatyRincomExample();
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
    //  * @return FriTreatyRincom
    //  * @throws Exception
    //  */
    // public FriTreatyRincom queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriTreatyRincom> returnList = null;
    //     try
    //     {
    //         FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
    //         //FriTreatyRincomExample xp = new FriTreatyRincomExample();
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
    public List<FriTreatyRincom> queryByFriTreatyRincom(final FriTreatyRincom model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriTreatyRincom> returnList = null;
        try
        {
//            FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
//            FriTreatyRincomExample xp = new FriTreatyRincomExample();
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
    public List<FriTreatyRincom> queryByFriTreatyRincom(final FriTreatyRincom model, SqlSession sqlSession)
            throws Exception
    {
        List<FriTreatyRincom> returnList = null;
//            FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
//            FriTreatyRincomExample xp = new FriTreatyRincomExample();
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
            FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
    public int update(final FriTreatyRincom model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	public List<FriTreatyRincom> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyRincom> result = null;
		try {
			FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	public List<FriTreatyRincom> queryAll(SqlSession sqlSession) throws Exception {
		List<FriTreatyRincom> result = null;
		FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	//		FriTreatyRincomMapper mapper = session.getMapper(FriTreatyRincomMapper.class);
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
	//public List<FriTreatyRincom> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriTreatyRincom> returnList = null;
    //
	//	try {
	//		FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
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
	//public List<FriTreatyRincom> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriTreatyRincom> returnList = null;
	//	
	//	FriTreatyRincomMapper mapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}