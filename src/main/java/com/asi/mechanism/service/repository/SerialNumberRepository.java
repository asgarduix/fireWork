package com.asi.mechanism.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.mechanism.service.dao.mybatis.mapper.SerialNumberMapper;
import com.asi.mechanism.service.dao.mybatis.model.SerialNumber;
import com.asi.mechanism.service.dao.mybatis.model.SerialNumberExample;
import com.asi.mechanism.service.dao.mybatis.model.SerialNumberExample.Criteria;

@Repository
public class SerialNumberRepository {

	private Log log = LogFactory.getLog(SerialNumberRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * 
	 */
	public List<SerialNumber> queryByModelBetweenSize(final SerialNumber model, String orderByColNm1, String ascOrDesc, int pageSize, int pageNum)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SerialNumber> returnList = null;

		try {
			//Integer[] a = CalculatorUtil.caculatorPageStartEndNum(pageSize, pageNum);

			SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);

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
	public long queryCount(SerialNumber model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
	public long queryCount(SerialNumber model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
	public int insert(final SerialNumber model, SerialNumberMapper mapper) {
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
	public int deleteByKey(final String primaryKey, SerialNumberMapper mapper) throws Exception {
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
	public int update(final SerialNumber model, SerialNumberMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final SerialNumber model, SqlSession sqlSession) {
	//	SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
	//	SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final SerialNumber model, SqlSession sqlSession) throws Exception {
	//	SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final SerialNumber model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
	public int insertList(final List<SerialNumber> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			SerialNumberMapper mapper = sqlSession
					.getMapper(SerialNumberMapper.class);

			for (SerialNumber model : modelList) {
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
    // * @return List<SerialNumber>
    // * @throws Exception
    // */
    // public List<SerialNumber> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<SerialNumber> returnList = null;
    // try {
    // SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
    // SerialNumberExample ex = new SerialNumberExample();
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
    //  * @return SerialNumber
    //  * @throws Exception
    //  */
    // public SerialNumber queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<SerialNumber> returnList = null;
    //     try
    //     {
    //         SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
    //         //SerialNumberExample xp = new SerialNumberExample();
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
    public List<SerialNumber> queryBySerialNumber(final SerialNumber model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<SerialNumber> returnList = null;
        try
        {
            SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
            SerialNumberExample xp = new SerialNumberExample();
            Criteria criteria = xp.createCriteria();
            if (model.getCreateDate() != null){
            	criteria.andCreateDateEqualTo(model.getCreateDate());
            }
            if (model.getDateStr() != null && !"".equals(model.getDateStr())) {
				criteria.andDateStrEqualTo(model.getDateStr());
			}
            if (model.getSerialLen() != null) {
				criteria.andSerialLenEqualTo(model.getSerialLen());
			}
            if (model.getPrepName() != null && !"".equals(model.getPrepName())) {
				criteria.andPrepNameEqualTo(model.getPrepName());
			}
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
    public List<SerialNumber> queryBySerialNumber(final SerialNumber model, SqlSession sqlSession)
            throws Exception
    {
        List<SerialNumber> returnList = null;
//            SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
//            SerialNumberExample xp = new SerialNumberExample();
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
            SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
    public int update(final SerialNumber model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
            SerialNumberExample xp = new SerialNumberExample();
			Criteria criteria = xp.createCriteria();
			if (CommonRespository.checkNotNull(model.getCreateDate()))
			{
				criteria.andCreateDateEqualTo(model.getCreateDate());
			}
			
			if (CommonRespository.checkNotNull(model.getDateStr()))
			{
				criteria.andDateStrEqualTo(model.getDateStr());
			}
			
			if (CommonRespository.checkNotNull(model.getPrepName()))
			{
				criteria.andPrepNameEqualTo(model.getPrepName());
			}
			
			if (CommonRespository.checkNotNull(model.getSerialLen()))
			{
				criteria.andSerialLenEqualTo(model.getSerialLen());
			}
			
//			if (commonRespository.checkNotNull(model.getRdnNo()))
//			{
//				criteria.andRdnNoEqualTo(model.getRdnNo());
//			}
            count = mapper.updateByExampleSelective(model, xp);
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
	public List<SerialNumber> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SerialNumber> result = null;
		try {
			SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
	public List<SerialNumber> queryAll(SqlSession sqlSession) throws Exception {
		List<SerialNumber> result = null;
		SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
	//		SerialNumberMapper mapper = session.getMapper(SerialNumberMapper.class);
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
	//public List<SerialNumber> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<SerialNumber> returnList = null;
    //
	//	try {
	//		SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
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
	//public List<SerialNumber> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<SerialNumber> returnList = null;
	//	
	//	SerialNumberMapper mapper = sqlSession.getMapper(SerialNumberMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}