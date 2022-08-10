package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacPolicyMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFacPolicyExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacPolicyKey;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriFacPolicyKeyRepository {

	private Log log = LogFactory.getLog(FriFacPolicyKeyRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====


	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriFacPolicyKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	public long queryCount(FriFacPolicyKey model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	public int insert(final FriFacPolicyKey model, FriFacPolicyMapper mapper) throws Exception {
		return mapper.insertSelective(model);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int deleteByKey(final String primaryKey, FriFacPolicyMapper mapper)
	// throws Exception {
	// return mapper.deleteByPrimaryKey(primaryKey);
	// }

	public int deleteByFriFacPolicyKey(final FriFacPolicyKey model, FriFacPolicyMapper mapper) throws Exception {

		FriFacPolicyExample xp = new FriFacPolicyExample();
		FriFacPolicyExample.Criteria criteria = xp.createCriteria();
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
	public int update(final FriFacPolicyKey model, FriFacPolicyMapper mapper) throws Exception {
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
	// public int insert(final FriFacPolicyKey model, SqlSession sqlSession) {
	// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	// public int update(final FriFacPolicyKey model, SqlSession sqlSession) throws
	// Exception {
	// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriFacPolicyKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	public int insertList(final List<FriFacPolicyKey> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);

			for (FriFacPolicyKey model : modelList) {
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
	// * @return List<FriFacPolicyKey>
	// * @throws Exception
	// */
	// public List<FriFacPolicyKey> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacPolicyKey> returnList = null;
	// try {
	// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
	// FriFacPolicyKeyExample ex = new FriFacPolicyKeyExample();
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
	// * @return FriFacPolicyKey
	// * @throws Exception
	// */
	// public FriFacPolicyKey queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacPolicyKey> returnList = null;
	// try
	// {
	// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
	// //FriFacPolicyKeyExample xp = new FriFacPolicyKeyExample();
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
	public List<FriFacPolicyKey> queryByFriFacPolicyKey(final FriFacPolicyKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacPolicyKey> returnList = null;
		try {
//            FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
//            FriFacPolicyKeyExample xp = new FriFacPolicyKeyExample();
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
	public List<FriFacPolicyKey> queryByFriFacPolicyKey(final FriFacPolicyKey model, SqlSession sqlSession)
			throws Exception {
		List<FriFacPolicyKey> returnList = null;
//            FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
//            FriFacPolicyKeyExample xp = new FriFacPolicyKeyExample();
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
			FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	public int update(final FriFacPolicyKey model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	public List<FriFacPolicyKey> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacPolicyKey> result = null;
		try {
			FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	public List<FriFacPolicyKey> queryAll(SqlSession sqlSession) throws Exception {
		List<FriFacPolicyKey> result = null;
		FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	// FriFacPolicyMapper mapper = session.getMapper(FriFacPolicyMapper.class);
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
	// public List<FriFacPolicyKey> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacPolicyKey> returnList = null;
	//
	// try {
	// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
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
	// public List<FriFacPolicyKey> queryDistint{COL_NM}(SqlSession sqlSession)
	/// throws Exception {
	// List<FriFacPolicyKey> returnList = null;
	//
	// FriFacPolicyMapper mapper = sqlSession.getMapper(FriFacPolicyMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}