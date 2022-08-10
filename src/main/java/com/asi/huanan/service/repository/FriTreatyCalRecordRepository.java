package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyCalRecordMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyCalRecord;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyCalRecordExample;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyCalRecordExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriTreatyCalRecordRepository {

	private Log log = LogFactory.getLog(FriTreatyCalRecordRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriTreatyCalRecord model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriTreatyCalRecordMapper mapper =
			// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	public long queryCount(FriTreatyCalRecord model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriTreatyCalRecordMapper mapper =
		// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	public int insert(final FriTreatyCalRecord model, FriTreatyCalRecordMapper mapper) {
		return mapper.insertSelective(model);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriTreatyCalRecordMapper mapper) throws Exception {
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
	public int update(final FriTreatyCalRecord model, FriTreatyCalRecordMapper mapper) throws Exception {
		return mapper.updateByPrimaryKey(model);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	// public int insert(final FriTreatyCalRecord model, SqlSession sqlSession) {
	// FriTreatyCalRecordMapper mapper =
	// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	// FriTreatyCalRecordMapper mapper =
	// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	// public int update(final FriTreatyCalRecord model, SqlSession sqlSession)
	// throws Exception {
	// FriTreatyCalRecordMapper mapper =
	// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriTreatyCalRecord model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	public int insertList(final List<FriTreatyCalRecord> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);

			for (FriTreatyCalRecord model : modelList) {
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
	// * @return List<FriTreatyCalRecord>
	// * @throws Exception
	// */
	// public List<FriTreatyCalRecord> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriTreatyCalRecord> returnList = null;
	// try {
	// FriTreatyCalRecordMapper mapper =
	// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
	// FriTreatyCalRecordExample ex = new FriTreatyCalRecordExample();
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
	// * @return FriTreatyCalRecord
	// * @throws Exception
	// */
	// public FriTreatyCalRecord queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriTreatyCalRecord> returnList = null;
	// try
	// {
	// FriTreatyCalRecordMapper mapper =
	// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
	// //FriTreatyCalRecordExample xp = new FriTreatyCalRecordExample();
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
	public List<FriTreatyCalRecord> queryByFriTreatyCalRecord(final FriTreatyCalRecord model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyCalRecord> returnList = null;
		try {
			FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
			FriTreatyCalRecordExample xp = new FriTreatyCalRecordExample();
			Criteria criteria = xp.createCriteria();
			criteria.andProgramIdEqualTo(model.getProgramId());
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
	public List<FriTreatyCalRecord> queryByFriTreatyCalRecord(final FriTreatyCalRecord model, SqlSession sqlSession) {
		List<FriTreatyCalRecord> returnList = null;
		FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
		FriTreatyCalRecordExample xp = new FriTreatyCalRecordExample();
		Criteria criteria = xp.createCriteria();
		criteria.andProgramIdEqualTo(model.getProgramId());
		returnList = mapper.selectByExample(xp);
		log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
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
			FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	public int update(final FriTreatyCalRecord model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
			count = mapper.updateByPrimaryKey(model);
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
	public List<FriTreatyCalRecord> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyCalRecord> result = null;
		try {
			FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	public List<FriTreatyCalRecord> queryAll(SqlSession sqlSession) throws Exception {
		List<FriTreatyCalRecord> result = null;
		FriTreatyCalRecordMapper mapper = sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	// FriTreatyCalRecordMapper mapper =
	/// session.getMapper(FriTreatyCalRecordMapper.class);
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
	// public List<FriTreatyCalRecord> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriTreatyCalRecord> returnList = null;
	//
	// try {
	// FriTreatyCalRecordMapper mapper =
	/// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
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
	// public List<FriTreatyCalRecord> queryDistint{COL_NM}(SqlSession sqlSession)
	/// throws Exception {
	// List<FriTreatyCalRecord> returnList = null;
	//
	// FriTreatyCalRecordMapper mapper =
	/// sqlSession.getMapper(FriTreatyCalRecordMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}