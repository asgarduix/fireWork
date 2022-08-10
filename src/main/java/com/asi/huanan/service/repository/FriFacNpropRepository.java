package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacNpropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFacNprop;
import com.asi.huanan.service.dao.mybatis.model.FriFacNpropExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacNpropExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;

import org.apache.commons.lang3.StringUtils;

@Repository
public class FriFacNpropRepository {

	private Log log = LogFactory.getLog(FriFacNpropRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====



	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriFacNprop model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	public long queryCount(FriFacNprop model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	public int insert(final FriFacNprop model, FriFacNpropMapper mapper) throws Exception {
		return mapper.insertSelective(model);

	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriFacNpropMapper mapper) throws Exception {
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
	public int update(final FriFacNprop model, FriFacNpropMapper mapper) throws Exception {
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
	// public int insert(final FriFacNprop model, SqlSession sqlSession) {
	// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	// public int update(final FriFacNprop model, SqlSession sqlSession) throws
	// Exception {
	// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriFacNprop model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	public int insertList(final List<FriFacNprop> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);

			for (FriFacNprop model : modelList) {
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

	public int deleteByFriFacNprop(final FriFacNprop model, FriFacNpropMapper mapper) throws Exception {

		FriFacNpropExample xp = new FriFacNpropExample();
		Criteria criteria = xp.createCriteria();
		if (StringUtils.isNotBlank(model.getSlipNo())) {
			criteria.andSlipNoEqualTo(model.getSlipNo());
		}

		return mapper.deleteByExample(xp);

	}

	// /**
	// *
	// * @return List<FriFacNprop>
	// * @throws Exception
	// */
	// public List<FriFacNprop> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacNprop> returnList = null;
	// try {
	// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
	// FriFacNpropExample ex = new FriFacNpropExample();
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
	// * @return FriFacNprop
	// * @throws Exception
	// */
	// public FriFacNprop queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacNprop> returnList = null;
	// try
	// {
	// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
	// //FriFacNpropExample xp = new FriFacNpropExample();
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
	public List<FriFacNprop> queryByFriFacNprop(final FriFacNprop model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacNprop> returnList = null;
		try {
			FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
			FriFacNpropExample xp = new FriFacNpropExample();
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
	public List<FriFacNprop> queryByFriFacNprop(final FriFacNprop model, SqlSession sqlSession) throws Exception {
		List<FriFacNprop> returnList = null;
//            FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
//            FriFacNpropExample xp = new FriFacNpropExample();
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
			FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	public int update(final FriFacNprop model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	public List<FriFacNprop> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacNprop> result = null;
		try {
			FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	public List<FriFacNprop> queryAll(SqlSession sqlSession) throws Exception {
		List<FriFacNprop> result = null;
		FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	// FriFacNpropMapper mapper = session.getMapper(FriFacNpropMapper.class);
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
	// public List<FriFacNprop> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacNprop> returnList = null;
	//
	// try {
	// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
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
	// public List<FriFacNprop> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<FriFacNprop> returnList = null;
	//
	// FriFacNpropMapper mapper = sqlSession.getMapper(FriFacNpropMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}