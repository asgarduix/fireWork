package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.ComAutonumMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTempareaMapper;
import com.asi.huanan.service.dao.mybatis.model.ComAutonum;
import com.asi.huanan.service.dao.mybatis.model.ComAutonumExample;
import com.asi.huanan.service.dao.mybatis.model.ComAutonumExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.ComAutonumKey;
import com.asi.huanan.vo.QueryByNumbeCodeVo;
import com.asi.huanan.vo.Rin1203Vo;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class ComAutonumRepository {

	private Log log = LogFactory.getLog(ComAutonumRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	//=====針對使用自訂SQL=====

	/**
	 * rin1206取BillNoMaxSerial
	 * @param num_year
	 * @param number_code
	 * @param comAutonumMapper
	 * @return
	 * @throws Exception
	 */
	public int getBillNoMaxSerial(String num_year, String number_code,ComAutonumMapper comAutonumMapper) throws Exception {
		int serial = 0;
		try {
			serial = comAutonumMapper.getBillNoMaxSerial(num_year, number_code);
			log.debug(serial);
		} catch (Exception e) {
			throw e;
		}
		return serial;
	}

	/**
	 * Rin1206_若是無資料，新增一筆序號為0的資料
	 * @param num_year
	 * @param number_code
	 * @param comAutonumMapper
	 * @return
	 * @throws Exception
	 */
	public int insertNewAutonum(String num_year, String number_code) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			ComAutonumMapper comAutonumMapper = sqlSession.getMapper(ComAutonumMapper.class);
			count = comAutonumMapper.insertNewAutonum(num_year, number_code);
			sqlSession.commit();
		} catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
		finally {
			sqlSession.close();
		}
		return count;
	}

	/**
	 * rin1206更新updateBillNoSerial
	 * @param serial
	 * @param num_year
	 * @param number_code
	 * @param comAutonumMapper
	 * @return
	 * @throws Exception
	 */
	public int updateBillNoSerial(int serial,String num_year, String number_code)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			ComAutonumMapper comAutonumMapper = sqlSession.getMapper(ComAutonumMapper.class);
			count = comAutonumMapper.updateBillNoSerial(serial,num_year, number_code);
			sqlSession.commit();
		} catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
		finally {
			sqlSession.close();
		}
		return count;
	}

	/**
	 * Rin1203_同險設定，查詢流水號
	 * @param numberCode
	 * @return
	 * @throws Exception
	 */
	public int getRiskNoSerial(final String numberCode,ComAutonumMapper mapper) throws Exception {
		
		int returnList = 0;



			returnList = mapper.getRiskNoSerial(numberCode);


		return returnList;
	}

	/**
	 * Rin1203_同險設定,新增同險後，序號+1
	 * @param numberCode
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int plusComAutonumSerial(String numberCode,ComAutonumMapper mapper) throws Exception{
		int count = 0;
		count = mapper.plusComAutonumSerial(numberCode);
	       return count;
	}

	/**
	 * Rin1203_同險設定,刪除同險號，序號-1
	 * @param numberCode
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int minusComAutonumSerial(String numberCode,ComAutonumMapper mapper) throws Exception{
		int count = 0;
		count = mapper.minusComAutonumSerial(numberCode);
	       return count;
	}

	/**
	 * Rin1203_同險設定,刪除流水號資料
	 * @param numberCode
	 * @param mapper
	 * @return
	 */
	public int deleteOneComAutonumList(String numberCode, ComAutonumMapper mapper) {
		int count = 0;
		count = mapper.deleteOneComAutonumList(numberCode);
	       return count;
	}

	/**
	 * Rin1203_同險設定,新增一筆流水號資料
	 * @param numberCode
	 * @param mapper
	 * @return
	 */
	public int insertOneListByNumberCode(String numberCode, ComAutonumMapper mapper) {
		int count = 0;
		count = mapper.insertOneListByNumberCode(numberCode);
	       return count;
	}



	/**
	 * @param model
	 * @return
	 *
	 */
	public long queryCount(ComAutonum model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
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
	public long queryCount(ComAutonum model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
		//		count = mapper.countByExample(example);

		return count;
	}
	//=====針對使用自訂SQL結束=====


	//=====以下為基本使用的=====

	/**
	 *
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	public int insert(final ComAutonum model, ComAutonumMapper mapper)throws Exception  {

		return mapper.insertSelective(model);

	}

	/**
	 *
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, ComAutonumMapper mapper) throws Exception {
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
	public int update(final ComAutonum model, ComAutonumMapper mapper) throws Exception {
		return mapper.updateByPrimaryKey(model);

	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final ComAutonum model, SqlSession sqlSession) {
	//	ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
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
	//	ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final ComAutonum model, SqlSession sqlSession) throws Exception {
	//	ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     *
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final ComAutonum model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;

        try
        {
            ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
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
	public int insertList(final List<ComAutonum> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			ComAutonumMapper mapper = sqlSession
					.getMapper(ComAutonumMapper.class);

			for (ComAutonum model : modelList) {
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
    // * @return List<ComAutonum>
    // * @throws Exception
    // */
    // public List<ComAutonum> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<ComAutonum> returnList = null;
    // try {
    // ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
    // ComAutonumExample ex = new ComAutonumExample();
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
    //  * @return ComAutonum
    //  * @throws Exception
    //  */
    // public ComAutonum queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<ComAutonum> returnList = null;
    //     try
    //     {
    //         ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
    //         //ComAutonumExample xp = new ComAutonumExample();
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
    public List<ComAutonum> queryByComAutonum(final ComAutonum model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<ComAutonum> returnList = null;
        try
        {
//            ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
//            ComAutonumExample xp = new ComAutonumExample();
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
    public List<ComAutonum> queryByComAutonum(final ComAutonum model, SqlSession sqlSession)
            throws Exception
    {
        List<ComAutonum> returnList = null;
//            ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
//            ComAutonumExample xp = new ComAutonumExample();
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
            ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
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
    public int update(final ComAutonum model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
            count = mapper.updateByPrimaryKey(model);
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
	public List<ComAutonum> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<ComAutonum> result = null;
		try {
			ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
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
	public List<ComAutonum> queryAll(SqlSession sqlSession) throws Exception {
		List<ComAutonum> result = null;
		ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}

	public Integer selectMaxSerial(ComAutonumMapper mapper,ComAutonum model) throws Exception {
		try {
			ComAutonumExample xp = new ComAutonumExample();
		      Criteria criteria = xp.createCriteria();
		      criteria.andCompanyCodeEqualTo("");
		      if(StringUtils.isNotBlank(model.getFunctionId())) {
		    	  criteria.andFunctionIdEqualTo(model.getFunctionId());
		      }

		      if(StringUtils.isNotBlank(model.getNumberCode())) {
		    	  criteria.andNumberCodeEqualTo(model.getNumberCode());
		      }

		      if(StringUtils.isNotBlank(model.getOperateType())) {
		    	  criteria.andOperateTypeEqualTo(model.getOperateType());
		      }

		      if(StringUtils.isNotBlank(model.getNumYear())) {
		    	  criteria.andNumYearEqualTo(model.getNumYear());
		      }

				return mapper.selectMaxSerial(xp);
		}catch(Exception e) {
			throw e;
		}
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
	//		ComAutonumMapper mapper = session.getMapper(ComAutonumMapper.class);
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
	//public List<ComAutonum> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<ComAutonum> returnList = null;
    //
	//	try {
	//		ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
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
	//public List<ComAutonum> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<ComAutonum> returnList = null;
	//
	//	ComAutonumMapper mapper = sqlSession.getMapper(ComAutonumMapper.class);
	//
	//	returnList = mapper.selectDistint{COL_NM}();
	//
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}