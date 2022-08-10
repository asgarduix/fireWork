package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.huanan.service.dao.mybatis.mapper.BatchlogMapper;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchlog;
import com.asi.huanan.service.dao.mybatis.model.BatchlogExample;
import com.asi.huanan.service.dao.mybatis.model.BatchlogExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1302Table;

import org.apache.commons.lang3.StringUtils;

@Repository
public class BatchlogRepository {

	private Log log = LogFactory.getLog(BatchlogRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	public List<Batchlog> queryLogUseID(String taskid) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchlog> results = new ArrayList<>();

		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			results = mapper.queryLogUseID(taskid);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * 再保臨分到期通知列印_排程執行batchlog table
	 * 
	 * @param modelLog
	 * @param mapper
	 * @return
	 */
	public int insertLog(Batchlog modelLog, BatchlogMapper mapper) {

		int count = 0;
		count = mapper.insertLog(modelLog);
		return count;
	}

	/**
	 * 排程執行_batchlog table_start
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
//	public int updateLog_start(String taskid, Date date) throws Exception {
//		
//		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
//		int count = 0;
//		try {
//			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
//			count = mapper.updateLog_start(taskid, date);
//			sqlSession.commit();
//		}catch(Exception e){
//			sqlSession.rollback();
//			throw e;
//		}finally {
//			sqlSession.close();
//		}
//		return count;
//	}

	/**
	 * 排程執行_batchlog table_start
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_start(String taskid, Date date) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			count = mapper.insertLog_start(taskid, date);
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
	 * 排程執行_batchlog table_noData
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_noData(String taskid, Date date) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			count = mapper.insertLog_noData(taskid, date);
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
	 * 排程執行_batchlog table_error
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_error(String taskid, Date date) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			count = mapper.insertLog_error(taskid, date);
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
	 * 排程執行_batchlog table_end
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_end(String taskid, Date date) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			count = mapper.insertLog_end(taskid, date);
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
	 * 作業細項說明_模糊查詢
	 * 
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<Batchlog> queryByContent(String keyword, String taskid) throws Exception {
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchlog> results = new ArrayList<>();
		
		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			results = mapper.queryByContent("%"+keyword+"%", taskid);
			
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
	public long queryCount(Batchlog model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	public long queryCount(Batchlog model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	public int insert(final Batchlog model, BatchlogMapper mapper) {
		// return mapper.insertSelective(model);
		return 0;
	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, BatchlogMapper mapper) throws Exception {
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
	public int update(final Batchlog model, BatchlogMapper mapper) throws Exception {
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
	// public int insert(final Batchlog model, SqlSession sqlSession) {
	// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	// public int update(final Batchlog model, SqlSession sqlSession) throws
	// Exception {
	// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final Batchlog model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
			count = mapper.insertSelective(model);
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
	public int insertList(final List<Batchlog> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);

			for (Batchlog model : modelList) {
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
	// * @return List<Batchlog>
	// * @throws Exception
	// */
	// public List<Batchlog> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Batchlog> returnList = null;
	// try {
	// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
	// BatchlogExample ex = new BatchlogExample();
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
	// * @return Batchlog
	// * @throws Exception
	// */
	// public Batchlog queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Batchlog> returnList = null;
	// try
	// {
	// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
	// //BatchlogExample xp = new BatchlogExample();
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
	public List<Batchlog> queryByBatchlog(final Batchlog model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchlog> returnList = null;
		try {
//            BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
//            BatchlogExample xp = new BatchlogExample();
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
	public List<Batchlog> queryByBatchlog(final Batchlog model, SqlSession sqlSession) throws Exception {
		List<Batchlog> returnList = null;
//            BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
//            BatchlogExample xp = new BatchlogExample();
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
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	public int update(final Batchlog model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	public List<Batchlog> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchlog> result = null;
		try {
			BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	public List<Batchlog> queryAll(SqlSession sqlSession) throws Exception {
		List<Batchlog> result = null;
		BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	// BatchlogMapper mapper = session.getMapper(BatchlogMapper.class);
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
	// public List<Batchlog> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Batchlog> returnList = null;
	//
	// try {
	// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
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
	// public List<Batchlog> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<Batchlog> returnList = null;
	//
	// BatchlogMapper mapper = sqlSession.getMapper(BatchlogMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}