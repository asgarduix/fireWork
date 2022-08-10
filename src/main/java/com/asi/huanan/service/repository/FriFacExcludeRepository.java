package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacExcludeMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFacExcludeExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacExcludeExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.FriFacExcludeKey;
import com.asi.mechanism.service.connector.MyBatisConnector;

import org.apache.commons.lang3.StringUtils;

@Repository
public class FriFacExcludeRepository {

	private Log log = LogFactory.getLog(FriFacExcludeRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====



	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriFacExcludeKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	public long queryCount(FriFacExcludeKey model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	public int insert(final FriFacExcludeKey model, FriFacExcludeMapper mapper) throws Exception {
		return mapper.insertSelective(model);

	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriFacExcludeMapper mapper) throws Exception {
		// return mapper.deleteByPrimaryKey(primaryKey);
		return 0;
	}

	public int delByFriFacExcludeKey(final FriFacExcludeKey model, FriFacExcludeMapper mapper) throws Exception {

		FriFacExcludeExample xp = new FriFacExcludeExample();
		Criteria criteria = xp.createCriteria();
		if (StringUtils.isNotBlank(model.getSlipNo())) {
			criteria.andSlipNoEqualTo(model.getSlipNo());
		}

		return mapper.deleteByExample(xp);
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final FriFacExcludeKey model, FriFacExcludeMapper mapper) throws Exception {
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
	// public int insert(final FriFacExcludeKey model, SqlSession sqlSession) {
	// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	// public int update(final FriFacExcludeKey model, SqlSession sqlSession) throws
	// Exception {
	// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriFacExcludeKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	public int insertList(final List<FriFacExcludeKey> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);

			for (FriFacExcludeKey model : modelList) {
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
	// * @return List<FriFacExcludeKey>
	// * @throws Exception
	// */
	// public List<FriFacExcludeKey> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacExcludeKey> returnList = null;
	// try {
	// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
	// FriFacExcludeExample ex = new FriFacExcludeExample();
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
	// * @return FriFacExcludeKey
	// * @throws Exception
	// */
	// public FriFacExcludeKey queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacExcludeKey> returnList = null;
	// try
	// {
	// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
	// //FriFacExcludeExample xp = new FriFacExcludeExample();
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
	public List<FriFacExcludeKey> queryByFriFacExcludeKey(final FriFacExcludeKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacExcludeKey> returnList = null;
		try {
			FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
			FriFacExcludeExample xp = new FriFacExcludeExample();
			Criteria criteria = xp.createCriteria();
			if (StringUtils.isNotBlank(model.getSlipNo())) {
				criteria.andSlipNoEqualTo(model.getSlipNo());
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
	public List<FriFacExcludeKey> queryByFriFacExcludeKey(final FriFacExcludeKey model, SqlSession sqlSession)
			throws Exception {
		List<FriFacExcludeKey> returnList = null;
//            FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
//            FriFacExcludeExample xp = new FriFacExcludeExample();
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
			FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	public int update(final FriFacExcludeKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	public List<FriFacExcludeKey> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacExcludeKey> result = null;
		try {
			FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	public List<FriFacExcludeKey> queryAll(SqlSession sqlSession) throws Exception {
		List<FriFacExcludeKey> result = null;
		FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	// FriFacExcludeMapper mapper = session.getMapper(FriFacExcludeMapper.class);
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
	// public List<FriFacExcludeKey> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacExcludeKey> returnList = null;
	//
	// try {
	// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
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
	// public List<FriFacExcludeKey> queryDistint{COL_NM}(SqlSession sqlSession)
	/// throws Exception {
	// List<FriFacExcludeKey> returnList = null;
	//
	// FriFacExcludeMapper mapper = sqlSession.getMapper(FriFacExcludeMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}