package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriAccountingMapper;
import com.asi.huanan.service.dao.mybatis.model.FriAccounting;
import com.asi.huanan.service.dao.mybatis.model.FriAccountingExample;
import com.asi.huanan.service.dao.mybatis.model.FriAccountingExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriAccountingRepository {

	private Log log = LogFactory.getLog(FriAccountingRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====
	
	public int deleteChangeStatusRin1303(String slipNo, FriAccountingMapper mapper) {
		return mapper.deleteChangeStatusRin1303(slipNo);
	}
	
	/**
     * Rin1206 帳單號碼查詢fri_accounting資料
     * @param billNo
     * @return
     */	
	public List<FriAccounting> queryByBillNo(String billNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriAccounting> results = new ArrayList<>();
		
		try {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
			FriAccountingExample xp = new FriAccountingExample();
			xp.setOrderByClause("rin_com_id");
			Criteria criteria = xp.createCriteria();
			
			criteria.andBillNoEqualTo(billNo);
			
			
			results = mapper.selectByExample(xp);
			
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	/**
	 * @param model
	 * @return
	 * 
	 */
	public List<FriAccounting> queryForRin1303No1(String slipNo, FriAccountingMapper mapper) throws Exception {
		
		List<FriAccounting> results = new ArrayList<>();
		
		try {
			results = mapper.queryForRin1303No1(slipNo);
		} catch (Exception e) {
			
			throw e;
		} 
		return results;
	}

	/**
	 * @param model
	 * @return
	 * 
	 */
	public List<FriAccounting> queryForRin1303No2(String slipNo, String brokerId, FriAccountingMapper mapper) throws Exception {
		
		List<FriAccounting> results = new ArrayList<>();
		
		try {
			results = mapper.queryForRin1303No2(slipNo, brokerId);
		} catch (Exception e) {
			
			throw e;
		} 
		return results;
	}

	/**
	 * @param model
	 * @return
	 * 
	 */
	public List<FriAccounting> queryForRin1303No3(String slipNo, String brokerId, Short amendSeq, FriAccountingMapper mapper) throws Exception {
		
		List<FriAccounting> results = new ArrayList<>();
		
		try {
		      FriAccountingExample xp = new FriAccountingExample();
		      Criteria criteria = xp.createCriteria();
		      
		      criteria.andBillNoEqualTo(slipNo);
		      criteria.andAmendSeqEqualTo(amendSeq);
		      criteria.andBrokerIdEqualTo(brokerId);
		      
		      results = mapper.selectByExample(xp);

		} catch (Exception e) {
			
			throw e;
		} 
		return results;
	}
	
	public List<FriAccounting> queryForRin1303No4(String slipNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriAccounting> results = new ArrayList<>();
		try {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
			results = mapper.queryForRin1303No4(slipNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public void deleteForRin1303(String slipNo, Short amendSeq, String brokerId, FriAccountingMapper mapper) throws Exception {
		
		
		
		try {
			mapper.deleteForRin1303(slipNo, amendSeq, brokerId);
		} catch (Exception e) {
			
			throw e;
		} 
//		return results;
	}
	
	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriAccounting model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
			// count = mapper.countByExample(example);
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
	public long queryCount(FriAccounting model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
		// count = mapper.countByExample(example);

		return count;
	}

	// =====以下為基本使用的=====

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	public int insert(final FriAccounting model, FriAccountingMapper mapper) {
		 return mapper.insertSelective(model);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriAccountingMapper mapper) throws Exception {
		// return mapper.deleteByPrimaryKey(primaryKey);
		return 0;
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final FriAccounting model, FriAccountingMapper mapper) throws Exception {
		// return mapper.updateByPrimaryKey(model);
		return 0;
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	// public int insert(final FriAccounting model, SqlSession sqlSession) {
	// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
	// return mapper.insertSelective(model);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws
	// Exception {
	// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
	// return mapper.deleteByPrimaryKey(primaryKey);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int update(final FriAccounting model, SqlSession sqlSession) throws
	// Exception {
	// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriAccounting model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
			// count = mapper.insertSelective(model);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
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
	public int insertList(final List<FriAccounting> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);

			for (FriAccounting model : modelList) {
				// count += mapper.insertSelective(model);
			}

			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

	// /**
	// *
	// * @return List<FriAccounting>
	// * @throws Exception
	// */
	// public List<FriAccounting> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriAccounting> returnList = null;
	// try {
	// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
	// FriAccountingExample ex = new FriAccountingExample();
	// Criteria cr = ex.createCriteria();
	// cr.andJobIdEqualTo(jobId);
	// returnList = mapper.selectByExample(ex);
	// } finally {
	// sqlSession.close();
	// }
	// return returnList;
	// }

	// /**
	// *
	// * @param key
	// * @return FriAccounting
	// * @throws Exception
	// */
	// public FriAccounting queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriAccounting> returnList = null;
	// try
	// {
	// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
	// //FriAccountingExample xp = new FriAccountingExample();
	// //Criteria criteria = xp.createCriteria();
	// // if (StringUtils.isNotBlank(jobId))
	// // {
	// // criteria.andJobIdEqualTo(jobId);
	// // }
	// //returnList = mapper.selectByExample(xp);
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	// returnList.size());
	// }
	// finally
	// {
	// sqlSession.close();
	// }
	// return CollectionUtils.isNotEmpty(returnList) ? returnList.get(0)
	// : null;
	// }

	/**
	 * /* @param key /* @return /* @throws Exception /
	 */
	public List<FriAccounting> queryByFriAccounting(final FriAccounting model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriAccounting> returnList = null;
		try {
//            FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
//            FriAccountingExample xp = new FriAccountingExample();
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
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return returnList;
	}

	/**
	 * /* @param key /* @return /* @throws Exception /
	 */
	public List<FriAccounting> queryByFriAccounting(final FriAccounting model, SqlSession sqlSession) throws Exception {
		List<FriAccounting> returnList = null;
//            FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
//            FriAccountingExample xp = new FriAccountingExample();
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
	public int deleteByKey(final String primaryKey) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
			// count = mapper.deleteByPrimaryKey(primaryKey);//防呆,需要刪除再打開
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
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
	public int update(final FriAccounting model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
			// count = mapper.updateByPrimaryKey(model);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

	/**
	 * /* @param insId /* @param prgId /* @param vhclInsId /* @return
	 */
	public List<FriAccounting> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriAccounting> result = null;
		try {
			FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
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
	 * /* @param insId /* @param prgId /* @param vhclInsId /* @return
	 */
	public List<FriAccounting> queryAll(SqlSession sqlSession) throws Exception {
		List<FriAccounting> result = null;
		FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}

	/// **
	// * @param insId
	// * @param prgId
	// * @param vhclInsId
	// * @return
	// */
	// public int deleteAll() throws Exception {
	// SqlSession sqlSession = ConnectionFactory.getSession().openSession();
	// int result = 0;
	// try {
	// FriAccountingMapper mapper = session.getMapper(FriAccountingMapper.class);
	// result = mapper.deleteAll();
	// session.commit();
	// } catch (Exception e) {
	// session.rollback();
	// throw e;
	// } finally {
	// session.close();
	// }
	// return result;
	// }

	/// **
	// * 注意，此method解註解，須在mapper.java中實作selectDistintXXX方法
	// *可能類似「@Select("select distinct upvirs from amdupf1") List<Amdupf1>
	/// selectDistintTypins();」
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	// public List<FriAccounting> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriAccounting> returnList = null;
	//
	// try {
	// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
	// returnList = mapper.selectDistint{COL_NM}();
	// } catch (Exception e) {
	// throw e;
	// } finally {
	// sqlSession.close();
	// }
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
	//
	/// **
	// * 注意，此method解註解，須在mapper.java中實作selectDistintXXX方法
	// *可能類似「@Select("select distinct upvirs from amdupf1") List<Amdupf1>
	/// selectDistintTypins();」
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	// public List<FriAccounting> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<FriAccounting> returnList = null;
	//
	// FriAccountingMapper mapper = sqlSession.getMapper(FriAccountingMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}