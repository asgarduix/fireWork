package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.asi.huanan.service.dao.mybatis.mapper.UsedAreaMapper;

import com.asi.huanan.service.dao.mybatis.model.UsedArea;
import com.asi.huanan.service.dao.mybatis.model.UsedAreaExample;
import com.asi.huanan.service.dao.mybatis.model.UsedAreaExample.Criteria;
import com.asi.huanan.vo.DeleteUsedArea;
import com.asi.huanan.vo.UpdateFriPolicyDel;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class UsedAreaRepository {

	private Log log = LogFactory.getLog(UsedAreaRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	/**
	 * Rin1203_同險設定，查詢是否地段已上鎖
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<UsedArea> queryAreaCodeIsLock(final UsedArea model,final UsedAreaMapper mapper) throws Exception {
		
		List<UsedArea> result = null;
		
			UsedAreaExample xp =new UsedAreaExample();
			Criteria criteria = xp.createCriteria();
			
			if(StringUtils.isNotBlank((model.getAreaCode()))) {
	            criteria.andAreaCodeEqualTo(model.getAreaCode());
	            System.out.println(model.getAreaCode());
	        }
			
		result = mapper.selectByExample(xp);
		log.debug(result == null ? "result is null" : "筆數:" + result.size());
		return result;
	}
	
	
    /**
     * Rin1203_同險設定，清除地段代號
     * @param model
     * @return
     * @throws Exception
     */
    public int deleteUsedAreaIn(List<DeleteUsedArea> model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
            count = mapper.deleteUsedAreaIn(model);//防呆,需要刪除再打開
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
    
  //=====針對使用自訂SQL結束=====
	
	
			/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(UsedArea model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	public long queryCount(UsedArea model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	public int insert(final UsedArea model, UsedAreaMapper mapper) {
		return mapper.insertSelective(model);
	}
	
	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, UsedAreaMapper mapper) throws Exception {
		return mapper.deleteByPrimaryKey(primaryKey);
		
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final UsedArea model, UsedAreaMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final UsedArea model, SqlSession sqlSession) {
	//	UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	//	UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final UsedArea model, SqlSession sqlSession) throws Exception {
	//	UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final UsedArea model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	public int insertList(final List<UsedArea> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			UsedAreaMapper mapper = sqlSession
					.getMapper(UsedAreaMapper.class);

			for (UsedArea model : modelList) {
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
    // * @return List<UsedArea>
    // * @throws Exception
    // */
    // public List<UsedArea> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<UsedArea> returnList = null;
    // try {
    // UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
    // UsedAreaExample ex = new UsedAreaExample();
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
    //  * @return UsedArea
    //  * @throws Exception
    //  */
    // public UsedArea queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<UsedArea> returnList = null;
    //     try
    //     {
    //         UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
    //         //UsedAreaExample xp = new UsedAreaExample();
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
    public List<UsedArea> queryByUsedArea(final UsedArea model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<UsedArea> returnList = null;
        try
        {
//            UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
//            UsedAreaExample xp = new UsedAreaExample();
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
    public List<UsedArea> queryByUsedArea(final UsedArea model, final UsedAreaMapper mapper)
            throws Exception
    {
        List<UsedArea> returnList = null;
//            UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
//            UsedAreaExample xp = new UsedAreaExample();
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
            UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
            count = mapper.deleteByPrimaryKey(primaryKey);//防呆,需要刪除再打開
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
    public int update(final UsedArea model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	public List<UsedArea> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<UsedArea> result = null;
		try {
			UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	public List<UsedArea> queryAll(SqlSession sqlSession) throws Exception {
		List<UsedArea> result = null;
		UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	//		UsedAreaMapper mapper = session.getMapper(UsedAreaMapper.class);
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
	//public List<UsedArea> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<UsedArea> returnList = null;
    //
	//	try {
	//		UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
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
	//public List<UsedArea> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<UsedArea> returnList = null;
	//	
	//	UsedAreaMapper mapper = sqlSession.getMapper(UsedAreaMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}