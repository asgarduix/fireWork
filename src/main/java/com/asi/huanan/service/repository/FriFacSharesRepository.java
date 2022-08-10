package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacSharesMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFacShares;
import com.asi.huanan.service.dao.mybatis.model.FriFacSharesExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacSharesExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1205TableFacShare;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriFacSharesRepository {

	private Log log = LogFactory.getLog(FriFacSharesRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====
	/**
	 * /* @param model /* @return /* @throws Exception /
	 */
	public List<Rin1205TableFacShare> getFacShare(final String policyNo, final String endorseNo, final String addrNo,
			FriFacSharesMapper mapper) throws Exception {
		List<Rin1205TableFacShare> returnList = null;
		try {
			returnList = mapper.getFacShare(policyNo, endorseNo, addrNo);

			log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
		} catch (Exception e) {
			throw e;
		}
		return returnList;
	}



	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriFacShares model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	public long queryCount(FriFacShares model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	public int insert(final FriFacShares model, FriFacSharesMapper mapper) throws Exception {
		return mapper.insertSelective(model);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriFacSharesMapper mapper) throws Exception {
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
	public int update(final FriFacShares model, FriFacSharesMapper mapper) throws Exception {
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
	// public int insert(final FriFacShares model, SqlSession sqlSession) {
	// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	// public int update(final FriFacShares model, SqlSession sqlSession) throws
	// Exception {
	// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriFacShares model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	public int insertList(final List<FriFacShares> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);

			for (FriFacShares model : modelList) {
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
	// * @return List<FriFacShares>
	// * @throws Exception
	// */
	// public List<FriFacShares> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacShares> returnList = null;
	// try {
	// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
	// FriFacSharesExample ex = new FriFacSharesExample();
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
	// * @return FriFacShares
	// * @throws Exception
	// */
	// public FriFacShares queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacShares> returnList = null;
	// try
	// {
	// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
	// //FriFacSharesExample xp = new FriFacSharesExample();
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
	public List<FriFacShares> queryByFriFacShares(final FriFacShares model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacShares> returnList = null;
		try {
			FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
			FriFacSharesExample xp = new FriFacSharesExample();
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

	public int delByFriFacShares(final FriFacShares model, FriFacSharesMapper mapper) throws Exception {

		FriFacSharesExample xp = new FriFacSharesExample();
		Criteria criteria = xp.createCriteria();
		if (StringUtils.isNotBlank(model.getSlipNo())) {
			criteria.andSlipNoEqualTo(model.getSlipNo());
		}
		return mapper.deleteByExample(xp);

	}

	/**
	 * /* @param key /* @return /* @throws Exception /
	 */
	public List<FriFacShares> queryByFriFacShares(final FriFacShares model, SqlSession sqlSession) throws Exception {
		List<FriFacShares> returnList = null;
//            FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
//            FriFacSharesExample xp = new FriFacSharesExample();
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
			FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	public int update(final FriFacShares model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	public List<FriFacShares> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacShares> result = null;
		try {
			FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	public List<FriFacShares> queryAll(SqlSession sqlSession) throws Exception {
		List<FriFacShares> result = null;
		FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	// FriFacSharesMapper mapper = session.getMapper(FriFacSharesMapper.class);
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
	// public List<FriFacShares> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacShares> returnList = null;
	//
	// try {
	// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
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
	// public List<FriFacShares> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<FriFacShares> returnList = null;
	//
	// FriFacSharesMapper mapper = sqlSession.getMapper(FriFacSharesMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}