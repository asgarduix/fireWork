package com.asi.huanan.service.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.BatchqueueMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.huanan.service.dao.mybatis.model.BatchqueueExample;
import com.asi.huanan.service.dao.mybatis.model.BatchqueueExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class BatchqueueRepository {

	private Log log = LogFactory.getLog(BatchqueueRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	/**
	 * 再保臨分到期通知列印_立即執行
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertBatchFriFacQueue(final Batchqueue model) throws Exception  {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			count = mapper.insertBatchFriFacQueue0(model);

			sqlSession.commit();
		}catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

		return count;
	}

	/**
	 * 再保臨分到期通知列印_排程執行
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */

	public int insertBatchFriFacQueue(final Batchqueue model, BatchqueueMapper mapper) {

		int count;

		count = mapper.insertBatchFriFacQueue(model);
		return count;
	}

	/**
	 * 產生taskid_立即執行
	 *
	 * @param mapper
	 * @return
	 */
	public String genTaskID() throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		String resTaskID;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			resTaskID = mapper.genTaskID();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return resTaskID;
	}

	/**
	 * 產生taskid_排程執行
	 *
	 * @param mapper
	 * @return
	 */
	public String genTaskID(BatchqueueMapper mapper) {
		String resTaskID;
		resTaskID = mapper.genTaskID();
		return resTaskID;
	}

	/**
	 * 排程查batchqueue的ProcessStatus
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Batchqueue> queryProcessStatus() throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchqueue> returnList = null;

		try {

			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			xp.setOrderByClause("submittime asc");
			Criteria criteria = xp.createCriteria();
			criteria.andProcessstatusEqualTo((byte) 0);
			criteria.andBatchidEqualTo("Rin1302P");
			returnList = mapper.selectByExample(xp);
			log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

		return returnList;
	}

	// selectSubmitTime
	public Date selectSubmitTime(String taskid, Date date) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		Date submitTime = null;
		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			submitTime = mapper.selectSubmitTime(taskid);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

		return submitTime;
	}

	//立即執行，修改batchqueue processstatus狀態_endtime
	public int updateProcessStatus0(String taskid, Date endtime) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			Criteria criteria = xp.createCriteria();
			criteria.andTaskidEqualTo(taskid);
			Batchqueue model = new Batchqueue();
			model.setEndtime(endtime);
			count = mapper.updateByExampleSelective(model, xp);
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
	 * 修改batchqueue processstatus狀態_starttime
	 *
	 * @param taskid
	 * @param processstatus
	 * @param starttime
	 * @return
	 * @throws Exception
	 */
	public int updateProcessStatus(String taskid, byte processstatus, Date starttime) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			Criteria criteria = xp.createCriteria();
			criteria.andTaskidEqualTo(taskid);
			Batchqueue model = new Batchqueue();
			model.setProcessstatus(processstatus);
			model.setStarttime(starttime);
			count = mapper.updateByExampleSelective(model, xp);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

	// 修改batchqueue processstatus狀態_endtime，執行完成
	public int updateProcessStatus2(String taskid, byte processstatus, Date endtime, String batchreprotaccesspath)
			throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			Criteria criteria = xp.createCriteria();
			criteria.andTaskidEqualTo(taskid);
			Batchqueue model = new Batchqueue();
			model.setProcessstatus(processstatus);
			model.setEndtime(endtime);
			if(StringUtils.isNotBlank(batchreprotaccesspath)) {
				model.setBatchreprotaccesspath(batchreprotaccesspath);				
			}

			count = mapper.updateByExampleSelective(model, xp);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

	// 修改batchqueue processstatus狀態_endtime，執行失敗
	public int updateProcessStatus4(String taskid, byte processstatus, Date endtime) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			Criteria criteria = xp.createCriteria();
			criteria.andTaskidEqualTo(taskid);
			Batchqueue model = new Batchqueue();
			model.setProcessstatus(processstatus);
			model.setEndtime(endtime);

			count = mapper.updateByExampleSelective(model, xp);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

	// 批次作業監視器_強制中止
	public int updateBatchqueueByTerminate(String taskid, Date date, byte processstatus) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			count = mapper.updateBatchqueueByTerminate(taskid, date, processstatus);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

	// 批次作業監視器_開啟報表
	public List<Batchqueue> queryBatchReprotAccessPath(String taskid) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchqueue> returnList = null;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			returnList = mapper.queryBatchReprotAccessPath(taskid);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

		return returnList;
	}

	// starttime/endtime
	public int updateProcessStatus(String taskid, byte processstatus,Date starttime, Date endtime, String batchreprotaccesspath)
			throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			Criteria criteria = xp.createCriteria();
			criteria.andTaskidEqualTo(taskid);
			Batchqueue model = new Batchqueue();
			model.setProcessstatus(processstatus);
			model.setStarttime(starttime);
			model.setEndtime(endtime);
			model.setBatchreprotaccesspath(batchreprotaccesspath);

			count = mapper.updateByExampleSelective(model, xp);
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
	 * @param model
	 * @return
	 *
	 */
	public long queryCount(Batchqueue model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	public long queryCount(Batchqueue model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	public int insert(final Batchqueue model, BatchqueueMapper mapper) {
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
	public int deleteByKey(final String primaryKey, BatchqueueMapper mapper) throws Exception {
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
	public int update(final Batchqueue model, BatchqueueMapper mapper) throws Exception {
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
	// public int insert(final Batchqueue model, SqlSession sqlSession) {
	// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	// public int update(final Batchqueue model, SqlSession sqlSession) throws
	// Exception {
	// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final Batchqueue model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	public int insertList(final List<Batchqueue> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);

			for (Batchqueue model : modelList) {
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
	// * @return List<Batchqueue>
	// * @throws Exception
	// */
	// public List<Batchqueue> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Batchqueue> returnList = null;
	// try {
	// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
	// BatchqueueExample ex = new BatchqueueExample();
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
	// * @return Batchqueue
	// * @throws Exception
	// */
	// public Batchqueue queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Batchqueue> returnList = null;
	// try
	// {
	// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
	// //BatchqueueExample xp = new BatchqueueExample();
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
	public List<Batchqueue> queryByBatchqueue(final Batchqueue model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchqueue> returnList = null;
		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			xp.setOrderByClause("submittime asc");
			Criteria criteria = xp.createCriteria();
			if (StringUtils.isNotBlank(model.getBatchid())) {
				criteria.andBatchidEqualTo(model.getBatchid());
			}
			if (model.getProcessstatus() != null) {
				criteria.andProcessstatusEqualTo(model.getProcessstatus());
			}
			if (StringUtils.isNotBlank(model.getTaskid())) {
				criteria.andTaskidEqualTo(model.getTaskid());
			}
			returnList = mapper.selectByExample(xp);
			log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
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
	public List<Batchqueue> queryByBatchqueue(final Batchqueue model, SqlSession sqlSession) throws Exception {
		List<Batchqueue> returnList = null;
//            BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
//            BatchqueueExample xp = new BatchqueueExample();
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
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	public int update(final Batchqueue model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	public List<Batchqueue> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Batchqueue> result = null;
		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	public List<Batchqueue> queryAll(SqlSession sqlSession) throws Exception {
		List<Batchqueue> result = null;
		BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}

	public int updatePathByTaskId(String taskId, String filePath) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchqueueExample xp = new BatchqueueExample();
			Criteria criteria = xp.createCriteria();
			criteria.andTaskidEqualTo(taskId);
			Batchqueue model = new Batchqueue();
			model.setBatchreprotaccesspath(filePath);
			count = mapper.updateByExampleSelective(model, xp);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
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
	// BatchqueueMapper mapper = session.getMapper(BatchqueueMapper.class);
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
	// public List<Batchqueue> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Batchqueue> returnList = null;
	//
	// try {
	// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
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
	// public List<Batchqueue> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<Batchqueue> returnList = null;
	//
	// BatchqueueMapper mapper = sqlSession.getMapper(BatchqueueMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}