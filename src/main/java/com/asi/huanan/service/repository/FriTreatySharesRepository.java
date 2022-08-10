package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatySharesMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShares;
import com.asi.huanan.vo.Rin1205VORespSub;
import com.asi.huanan.vo.Rin1205UpdateShareAmtPremVOReq;
import com.asi.mechanism.service.connector.MyBatisConnector;


@Repository
public class FriTreatySharesRepository {

	private Log log = LogFactory.getLog(FriTreatySharesRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/** 
	/* @param model
	/* @return 
	/* @throws Exception
	/*/
    public int updateShareAmtPrem(final Rin1205UpdateShareAmtPremVOReq model, FriTreatySharesMapper mapper)
            throws Exception
    {
        int result = 0;
        try
        {      
        	result = mapper.updateShareAmtPrem(model);

        } catch(Exception e){
			throw e;
		}
        return result;
    }
	
	/** 
	/* @param model
	/* @return 
	/* @throws Exception
	/*/
    public List<FriTreatyShares> getRetainResult(final FriTreatyShares model, FriTreatySharesMapper mapper)
            throws Exception
    {
        List<FriTreatyShares> returnList = null;
        try
        {      
             returnList = mapper.getRetainResult(model);
             
            log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
        } catch(Exception e){
			throw e;
		}
        return returnList;
    }
    
	/** 
	/* @param model
	/* @return 
	/* @throws Exception
	/*/
    public List<Rin1205VORespSub> getShareResult(final FriTreatyShares model, FriTreatySharesMapper mapper)
            throws Exception
    {
        List<Rin1205VORespSub> returnList = null;
        try
        {      
             returnList = mapper.getShareResult(model);
             
            log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
        } catch(Exception e){
			throw e;
		}
        return returnList;
    }
	
	
			/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriTreatyShares model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	public long queryCount(FriTreatyShares model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	public int insert(final FriTreatyShares model, FriTreatySharesMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriTreatySharesMapper mapper) throws Exception {
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
	public int update(final FriTreatyShares model, FriTreatySharesMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriTreatyShares model, SqlSession sqlSession) {
	//	FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	//	FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriTreatyShares model, SqlSession sqlSession) throws Exception {
	//	FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreatyShares model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	public int insertList(final List<FriTreatyShares> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriTreatySharesMapper mapper = sqlSession
					.getMapper(FriTreatySharesMapper.class);

			for (FriTreatyShares model : modelList) {
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
    // * @return List<FriTreatyShares>
    // * @throws Exception
    // */
    // public List<FriTreatyShares> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriTreatyShares> returnList = null;
    // try {
    // FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
    // FriTreatySharesExample ex = new FriTreatySharesExample();
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
    //  * @return FriTreatyShares
    //  * @throws Exception
    //  */
    // public FriTreatyShares queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriTreatyShares> returnList = null;
    //     try
    //     {
    //         FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
    //         //FriTreatySharesExample xp = new FriTreatySharesExample();
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
    public List<FriTreatyShares> queryByFriTreatyShares(final FriTreatyShares model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriTreatyShares> returnList = null;
        try
        {
//            FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
//            FriTreatySharesExample xp = new FriTreatySharesExample();
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
    public List<FriTreatyShares> queryByFriTreatyShares(final FriTreatyShares model, SqlSession sqlSession)
            throws Exception
    {
        List<FriTreatyShares> returnList = null;
//            FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
//            FriTreatySharesExample xp = new FriTreatySharesExample();
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
            FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
    public int update(final FriTreatyShares model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	public List<FriTreatyShares> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyShares> result = null;
		try {
			FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	public List<FriTreatyShares> queryAll(SqlSession sqlSession) throws Exception {
		List<FriTreatyShares> result = null;
		FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	//		FriTreatySharesMapper mapper = session.getMapper(FriTreatySharesMapper.class);
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
	//public List<FriTreatyShares> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriTreatyShares> returnList = null;
    //
	//	try {
	//		FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
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
	//public List<FriTreatyShares> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriTreatyShares> returnList = null;
	//	
	//	FriTreatySharesMapper mapper = sqlSession.getMapper(FriTreatySharesMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}