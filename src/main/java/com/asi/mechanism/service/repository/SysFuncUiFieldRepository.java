package com.asi.mechanism.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.mechanism.service.dao.mybatis.mapper.SysFuncUiFieldMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiField;

@Repository
public class SysFuncUiFieldRepository {

	private Log log = LogFactory.getLog(SysFuncUiFieldRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * 
	 */
	public List<SysFuncUiField> queryByModelBetweenSize(final SysFuncUiField model, String orderByColNm1, String ascOrDesc, int pageSize, int pageNum)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysFuncUiField> returnList = null;

		try {
			//Integer[] a = CalculatorUtil.caculatorPageStartEndNum(pageSize, pageNum);

			SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);

			//if (model.{GET} != null & !"".equals(model.{GET})) {
			//	model.{SET}(model.{GET} + "%");
			//}

			//returnList = mapper.selectByParamBetweenSize(model.{GET}..., "CRT_TIME", "DESC", a[0], a[1]);

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
	public long queryCount(SysFuncUiField model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	public long queryCount(SysFuncUiField model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	public int insert(final SysFuncUiField model, SysFuncUiFieldMapper mapper) {
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
	public int deleteByKey(final String primaryKey, SysFuncUiFieldMapper mapper) throws Exception {
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
	public int update(final SysFuncUiField model, SysFuncUiFieldMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final SysFuncUiField model, SqlSession sqlSession) {
	//	SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	//	SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final SysFuncUiField model, SqlSession sqlSession) throws Exception {
	//	SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final SysFuncUiField model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	public int insertList(final List<SysFuncUiField> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			SysFuncUiFieldMapper mapper = sqlSession
					.getMapper(SysFuncUiFieldMapper.class);

			for (SysFuncUiField model : modelList) {
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
    // * @return List<SysFuncUiField>
    // * @throws Exception
    // */
    // public List<SysFuncUiField> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<SysFuncUiField> returnList = null;
    // try {
    // SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
    // SysFuncUiFieldExample ex = new SysFuncUiFieldExample();
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
    //  * @return SysFuncUiField
    //  * @throws Exception
    //  */
    // public SysFuncUiField queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<SysFuncUiField> returnList = null;
    //     try
    //     {
    //         SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
    //         //SysFuncUiFieldExample xp = new SysFuncUiFieldExample();
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
    public List<SysFuncUiField> queryBySysFuncUiField(final SysFuncUiField model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<SysFuncUiField> returnList = null;
        try
        {
//            SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
//            SysFuncUiFieldExample xp = new SysFuncUiFieldExample();
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
    public List<SysFuncUiField> queryBySysFuncUiField(final SysFuncUiField model, SqlSession sqlSession)
            throws Exception
    {
        List<SysFuncUiField> returnList = null;
//            SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
//            SysFuncUiFieldExample xp = new SysFuncUiFieldExample();
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
            SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
    public int update(final SysFuncUiField model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	public List<SysFuncUiField> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysFuncUiField> result = null;
		try {
			SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	public List<SysFuncUiField> queryAll(SqlSession sqlSession) throws Exception {
		List<SysFuncUiField> result = null;
		SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	//		SysFuncUiFieldMapper mapper = session.getMapper(SysFuncUiFieldMapper.class);
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
	//public List<SysFuncUiField> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<SysFuncUiField> returnList = null;
    //
	//	try {
	//		SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
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
	//public List<SysFuncUiField> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<SysFuncUiField> returnList = null;
	//	
	//	SysFuncUiFieldMapper mapper = sqlSession.getMapper(SysFuncUiFieldMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}