package com.asi.huanan.service.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriPayAdviceMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPayAdvice;
import com.asi.huanan.service.dao.mybatis.model.FriPayAdviceExample;
import com.asi.huanan.service.dao.mybatis.model.FriPayAdviceExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

import org.apache.commons.lang3.StringUtils;

@Repository
public class FriPayAdviceRepository {

	private Log log = LogFactory.getLog(FriPayAdviceRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	//Rin1206 getLossPaidValue
	public long getLossPaidValue(String treaty_no, Date dateBgn, Date dateEnd, String brokerID,String inComId, FriPayAdviceMapper friPayAdviceMapper) throws Exception{
		long lossPaid = 0l;
		
		try {
			lossPaid = friPayAdviceMapper.getLossPaidValue(treaty_no, dateBgn, dateEnd, brokerID, inComId);

		} catch (Exception e) {
			throw e;
		}
			
		return lossPaid;
		
		
	}
	
	//Rin1206 getCashClaimValue
	public long getCashClaimValue(String treatyYear, String treatyNo, Date dateBgn, Date dateEnd, String brokerID,String inComId, FriPayAdviceMapper friPayAdviceMapper) throws Exception{
		long cashClaim = 0l;
		
		try {
			cashClaim = friPayAdviceMapper.getCashClaimValue(treatyYear, treatyNo, dateBgn, dateEnd, brokerID, inComId);

		} catch (Exception e) {
			throw e;
		}
			
		return cashClaim;
		
		
	}

	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriPayAdvice model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	public long queryCount(FriPayAdvice model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	public int insert(final FriPayAdvice model, FriPayAdviceMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriPayAdviceMapper mapper) throws Exception {
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
	public int update(final FriPayAdvice model, FriPayAdviceMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriPayAdvice model, SqlSession sqlSession) {
	//	FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	//	FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriPayAdvice model, SqlSession sqlSession) throws Exception {
	//	FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriPayAdvice model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	public int insertList(final List<FriPayAdvice> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriPayAdviceMapper mapper = sqlSession
					.getMapper(FriPayAdviceMapper.class);

			for (FriPayAdvice model : modelList) {
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
    // * @return List<FriPayAdvice>
    // * @throws Exception
    // */
    // public List<FriPayAdvice> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriPayAdvice> returnList = null;
    // try {
    // FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
    // FriPayAdviceExample ex = new FriPayAdviceExample();
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
    //  * @return FriPayAdvice
    //  * @throws Exception
    //  */
    // public FriPayAdvice queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriPayAdvice> returnList = null;
    //     try
    //     {
    //         FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
    //         //FriPayAdviceExample xp = new FriPayAdviceExample();
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
    public List<FriPayAdvice> queryByFriPayAdvice(final FriPayAdvice model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriPayAdvice> returnList = null;
        try
        {
//            FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
//            FriPayAdviceExample xp = new FriPayAdviceExample();
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
    public List<FriPayAdvice> queryByFriPayAdvice(final FriPayAdvice model, SqlSession sqlSession)
            throws Exception
    {
        List<FriPayAdvice> returnList = null;
//            FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
//            FriPayAdviceExample xp = new FriPayAdviceExample();
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
            FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
    public int update(final FriPayAdvice model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	public List<FriPayAdvice> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPayAdvice> result = null;
		try {
			FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	public List<FriPayAdvice> queryAll(SqlSession sqlSession) throws Exception {
		List<FriPayAdvice> result = null;
		FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	//		FriPayAdviceMapper mapper = session.getMapper(FriPayAdviceMapper.class);
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
	//public List<FriPayAdvice> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriPayAdvice> returnList = null;
    //
	//	try {
	//		FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
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
	//public List<FriPayAdvice> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriPayAdvice> returnList = null;
	//	
	//	FriPayAdviceMapper mapper = sqlSession.getMapper(FriPayAdviceMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}