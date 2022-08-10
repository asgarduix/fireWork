package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreaty;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyExample;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyKey;
import com.asi.huanan.vo.Rin1102AQueryOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102QueryTreatysVOReq;
import com.asi.huanan.vo.Rin1102VOResp;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriTreatyRepository {

	private Log log = LogFactory.getLog(FriTreatyRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	 public List<FriTreaty> queryTreatyNoByTreatyYear(final String treatyYear) throws Exception	{
		 SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		 
		 List<FriTreaty> results = null;
		 
		 try{
			 FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
			 FriTreatyExample xp = new FriTreatyExample();
			 Criteria criteria = xp.createCriteria();
			 
			 if(StringUtils.isNotBlank(treatyYear)) {
				 criteria.andTreatyYearEqualTo(treatyYear);
			 }

			 results = mapper.selectByExample(xp);
			 
		 } catch(Exception e){
				sqlSession.rollback();
				throw e;
		 }
		 finally{
			 sqlSession.close();
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
    public int deleteByYearNo(final String treatyYear, final String treatyNo, final FriTreatyMapper mapper) throws Exception
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
	/* @param key
	/* @return 
	/* @throws Exception
	/*/
    public List<FriTreaty> queryByFriTreaty(final FriTreaty model, final FriTreatyMapper mapper)
            throws Exception
    {
        List<FriTreaty> returnList = null;
        FriTreatyExample xp = new FriTreatyExample();
        Criteria criteria = xp.createCriteria();

        if(StringUtils.isNotBlank((model.getTreatyYear()))) {
            criteria.andTreatyYearEqualTo(model.getTreatyYear());
        }
        if(StringUtils.isNotBlank(model.getTreatyNo())) {
            criteria.andTreatyNoEqualTo(model.getTreatyNo());
        }

        returnList = mapper.selectByExample(xp);
        log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
        
        return returnList;
    }
	  /**
     * 
     * @param model
     * @param mapper
     * @return
     * @throws Exception
     */
    public List<FriTreaty> queryOneTreaty(final Rin1102AQueryOneTreatyVOReq model, final FriTreatyMapper mapper) throws Exception
    {
    	List<FriTreaty> results = null;
		
        try
        {
        	FriTreatyExample xp = new FriTreatyExample();
        	Criteria criteria = xp.createCriteria();
        	
        	if(StringUtils.isNotBlank(model.getTreatyYear())) {
        		criteria.andTreatyYearEqualTo(model.getTreatyYear());
        	}
        	
        	if(StringUtils.isNotBlank(model.getTreatyNo())) {
        		criteria.andTreatyNoEqualTo(model.getTreatyNo());
        	}
            
        	results = mapper.selectByExample(xp);

        } catch(Exception e){
			throw e;
		}
       
        return results;
    }
	
	  /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public List<Rin1102VOResp> queryTreatys(final Rin1102QueryTreatysVOReq model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<Rin1102VOResp> results = new ArrayList<>();
		
        try
        {
            FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
            results = mapper.queryTreatys(model);

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
	public long queryCount(FriTreaty model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	public long queryCount(FriTreaty model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	public int insert(final FriTreaty model, FriTreatyMapper mapper) {
		return mapper.insertSelective(model);
	}
	
	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriTreatyMapper mapper) throws Exception {
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
	public int update(final FriTreaty model, FriTreatyMapper mapper) throws Exception {
		return mapper.updateByPrimaryKey(model);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriTreaty model, SqlSession sqlSession) {
	//	FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	//	FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriTreaty model, SqlSession sqlSession) throws Exception {
	//	FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreaty model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	public int insertList(final List<FriTreaty> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriTreatyMapper mapper = sqlSession
					.getMapper(FriTreatyMapper.class);

			for (FriTreaty model : modelList) {
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
    // * @return List<FriTreaty>
    // * @throws Exception
    // */
    // public List<FriTreaty> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriTreaty> returnList = null;
    // try {
    // FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
    // FriTreatyExample ex = new FriTreatyExample();
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
    //  * @return FriTreaty
    //  * @throws Exception
    //  */
    // public FriTreaty queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriTreaty> returnList = null;
    //     try
    //     {
    //         FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
    //         //FriTreatyExample xp = new FriTreatyExample();
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
    public List<FriTreaty> queryByFriTreaty(final FriTreaty model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriTreaty> returnList = null;
        try
        {
//            FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
//            FriTreatyExample xp = new FriTreatyExample();
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
    public List<FriTreaty> queryByFriTreaty(final FriTreaty model, SqlSession sqlSession)
            throws Exception
    {
        List<FriTreaty> returnList = null;
            FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
            FriTreatyExample xp = new FriTreatyExample();
            Criteria criteria = xp.createCriteria();

            if(StringUtils.isNotBlank((model.getTreatyYear()))) {
            	criteria.andTreatyYearEqualTo(model.getTreatyYear());
            }
            if(StringUtils.isNotBlank(model.getTreatyNo())) {
            	criteria.andTreatyNoEqualTo(model.getTreatyNo());
            }
			
            // if (StringUtils.isNotBlank(model.getJobName()))
            // {
            // criteria.andJobNameEqualTo(model.getJobName());
            // }
             returnList = mapper.selectByExample(xp);
            log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
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
            FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
    public int update(final FriTreaty model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	public List<FriTreaty> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreaty> result = null;
		try {
			FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	public List<FriTreaty> queryAll(SqlSession sqlSession) throws Exception {
		List<FriTreaty> result = null;
		FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	//		FriTreatyMapper mapper = session.getMapper(FriTreatyMapper.class);
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
	//public List<FriTreaty> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriTreaty> returnList = null;
    //
	//	try {
	//		FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
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
	//public List<FriTreaty> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriTreaty> returnList = null;
	//	
	//	FriTreatyMapper mapper = sqlSession.getMapper(FriTreatyMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}