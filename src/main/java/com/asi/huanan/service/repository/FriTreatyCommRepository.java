package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyCommMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyComm;
import com.asi.huanan.vo.Rin1103DeleteTreatyVOReq;
import com.asi.huanan.vo.Rin1103InsertTreatyCommVOReq;
import com.asi.huanan.vo.Rin1103VOResp;
import com.asi.huanan.vo.Rin1103UpdateTreatyCommVOReq;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriTreatyCommRepository {

	private Log log = LogFactory.getLog(FriTreatyCommRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/**
     * 
     * @param model
     * @return int
     * @throws Exception
     */
    public short insertTreadyReturnSerial(final Rin1103InsertTreatyCommVOReq model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int res = 0;
        short serial = 0;
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
            res = mapper.insertTreadyReturnSerial(model);
            serial = model.getSerial();
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return serial;
    }
	
    /**
     * 
     * @param model
     * @return int
     * @throws Exception
     */
    public int deleteTreadysByPkList(final List<Rin1103DeleteTreatyVOReq> model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
            count = mapper.deleteTreadysByPkList(model);
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
    public int updateByOldPk(final Rin1103UpdateTreatyCommVOReq model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
            count = mapper.updateByOldPk(model);
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
	/* @param treatyYear
	 * @param treatyNo
	 * @return
	 * @throws Exception
	 */
    public List<Rin1103VOResp> queryTreatyList(final String treatyYear, final String treatyNo)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<Rin1103VOResp> returnList = null;
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
       
            returnList = mapper.queryTreatyList(treatyYear, treatyNo);
           
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
	/* @param treatyYear
	 * @param treatyNo
	 * @param commType
	 * @return
	 * @throws Exception
	 */
	/* @return 
	/* @throws Exception
	/*/
    public List<FriTreatyComm> checkInsertUpLow(final String treatyYear, final String treatyNo, final String commType)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriTreatyComm> returnList = null;
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
       
             returnList = mapper.checkInsertUpLow(treatyYear, treatyNo, commType);
           
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
	/* @param treatyYear
	 * @param treatyNo
	 * @param commType
	 * @param serial
	 * @return
	 * @throws Exception
	 */
	/* @return 
	/* @throws Exception
	/*/
    public List<FriTreatyComm> checkUpdateUpLow(final String treatyYear, final String treatyNo, final String commType, final short serial)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriTreatyComm> returnList = null;
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
       
             returnList = mapper.checkUpdateUpLow(treatyYear, treatyNo, commType, serial);
           
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
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriTreatyComm model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	public long queryCount(FriTreatyComm model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	public int insert(final FriTreatyComm model, FriTreatyCommMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriTreatyCommMapper mapper) throws Exception {
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
	public int update(final FriTreatyComm model, FriTreatyCommMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriTreatyComm model, SqlSession sqlSession) {
	//	FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	//	FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriTreatyComm model, SqlSession sqlSession) throws Exception {
	//	FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreatyComm model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	public int insertList(final List<FriTreatyComm> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriTreatyCommMapper mapper = sqlSession
					.getMapper(FriTreatyCommMapper.class);

			for (FriTreatyComm model : modelList) {
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
    // * @return List<FriTreatyComm>
    // * @throws Exception
    // */
    // public List<FriTreatyComm> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriTreatyComm> returnList = null;
    // try {
    // FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
    // FriTreatyCommExample ex = new FriTreatyCommExample();
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
    //  * @return FriTreatyComm
    //  * @throws Exception
    //  */
    // public FriTreatyComm queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriTreatyComm> returnList = null;
    //     try
    //     {
    //         FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
    //         //FriTreatyCommExample xp = new FriTreatyCommExample();
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
    public List<FriTreatyComm> queryByFriTreatyComm(final FriTreatyComm model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriTreatyComm> returnList = null;
        try
        {
//            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
//            FriTreatyCommExample xp = new FriTreatyCommExample();
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
    public List<FriTreatyComm> queryByFriTreatyComm(final FriTreatyComm model, SqlSession sqlSession)
            throws Exception
    {
        List<FriTreatyComm> returnList = null;
//            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
//            FriTreatyCommExample xp = new FriTreatyCommExample();
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
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
    public int update(final FriTreatyComm model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	public List<FriTreatyComm> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyComm> result = null;
		try {
			FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	public List<FriTreatyComm> queryAll(SqlSession sqlSession) throws Exception {
		List<FriTreatyComm> result = null;
		FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	//		FriTreatyCommMapper mapper = session.getMapper(FriTreatyCommMapper.class);
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
	//public List<FriTreatyComm> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriTreatyComm> returnList = null;
    //
	//	try {
	//		FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
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
	//public List<FriTreatyComm> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriTreatyComm> returnList = null;
	//	
	//	FriTreatyCommMapper mapper = sqlSession.getMapper(FriTreatyCommMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}