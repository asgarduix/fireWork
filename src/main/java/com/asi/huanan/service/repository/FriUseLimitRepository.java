package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriUseLimitMapper;
import com.asi.huanan.service.dao.mybatis.model.FriUseLimit;
import com.asi.huanan.service.dao.mybatis.model.FriUseLimitExample;
import com.asi.huanan.service.dao.mybatis.model.FriUseLimitExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriUseLimitRepository {

	private Log log = LogFactory.getLog(FriUseLimitRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	// 「使用性質代號」搜尋
	public List<FriUseLimit> queryByUsePropId(final String usePropId) throws Exception{
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriUseLimit> results = new ArrayList<>();
        try
        {
        	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
        	results = mapper.queryByUsePropId(usePropId);
        	
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
	
	// 新增自留限額資料
	public int insertByPk(final FriUseLimit model) throws Exception {
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        
        try
        {
        	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
            count = mapper.insertByPk(model);
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
	
	// 修改自留限額資料
	public int updateFriUseLimit(final FriUseLimit model) throws Exception{
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
        	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
            count = mapper.updateByPrimaryKeySelective(model);
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
	
	// 刪除自留限額資料
	public int deleteFriUseLimit(final List<FriUseLimit> model) throws Exception{
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
        	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
            	count = mapper.deleteFriUseLimit(model);
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
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriUseLimit model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	public long queryCount(FriUseLimit model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	public int insert(final FriUseLimit model, FriUseLimitMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriUseLimitMapper mapper) throws Exception {
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
	public int update(final FriUseLimit model, FriUseLimitMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriUseLimit model, SqlSession sqlSession) {
	//	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	//	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriUseLimit model, SqlSession sqlSession) throws Exception {
	//	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriUseLimit model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	public int insertList(final List<FriUseLimit> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriUseLimitMapper mapper = sqlSession
					.getMapper(FriUseLimitMapper.class);

			for (FriUseLimit model : modelList) {
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
    // * @return List<FriUseLimit>
    // * @throws Exception
    // */
    // public List<FriUseLimit> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriUseLimit> returnList = null;
    // try {
    // FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
    // FriUseLimitExample ex = new FriUseLimitExample();
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
    //  * @return FriUseLimit
    //  * @throws Exception
    //  */
    // public FriUseLimit queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriUseLimit> returnList = null;
    //     try
    //     {
    //         FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
    //         //FriUseLimitExample xp = new FriUseLimitExample();
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
    public List<FriUseLimit> queryByFriUseLimit(final FriUseLimit model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriUseLimit> returnList = null;
        try
        {
            FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
            FriUseLimitExample xp = new FriUseLimitExample();
            Criteria criteria = xp.createCriteria();
            if (StringUtils.isNotBlank(model.getUsePropId()))
            {
            criteria.andUsePropIdEqualTo(model.getUsePropId());
            }
			
//            // if (StringUtils.isNotBlank(model.getJobName()))
//            // {
//            // criteria.andJobNameEqualTo(model.getJobName());
//            // }
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
    public List<FriUseLimit> queryByFriUseLimit(final FriUseLimit model, SqlSession sqlSession)
            throws Exception
    {
        List<FriUseLimit> returnList = null;
//            FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
//            FriUseLimitExample xp = new FriUseLimitExample();
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
            FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
    public int update(final FriUseLimit model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	public List<FriUseLimit> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriUseLimit> result = null;
		try {
			FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	public List<FriUseLimit> queryAll(SqlSession sqlSession) throws Exception {
		List<FriUseLimit> result = null;
		FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	//		FriUseLimitMapper mapper = session.getMapper(FriUseLimitMapper.class);
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
	//public List<FriUseLimit> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriUseLimit> returnList = null;
    //
	//	try {
	//		FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
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
	//public List<FriUseLimit> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriUseLimit> returnList = null;
	//	
	//	FriUseLimitMapper mapper = sqlSession.getMapper(FriUseLimitMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}
