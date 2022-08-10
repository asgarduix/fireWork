package com.asi.huanan.service.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.MrexpfMapper;
import com.asi.huanan.service.dao.mybatis.model.Mrexpf;
import com.asi.huanan.service.dao.mybatis.model.MrexpfExample;
import com.asi.huanan.service.dao.mybatis.model.MrexpfExample.Criteria;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.swissknife.Asiutil;

import org.apache.commons.lang3.StringUtils;

@Repository
public class MrexpfRepository {

	private Log log = LogFactory.getLog(MrexpfRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	

	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(Mrexpf model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	public long queryCount(Mrexpf model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	public int insert(final Mrexpf model, MrexpfMapper mapper) {
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
	public int deleteByKey(final String primaryKey, MrexpfMapper mapper) throws Exception {
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
	public int update(final Mrexpf model, MrexpfMapper mapper) throws Exception {
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
	// public int insert(final Mrexpf model, SqlSession sqlSession) {
	// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	// public int update(final Mrexpf model, SqlSession sqlSession) throws Exception
	// {
	// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final Mrexpf model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	public int insertList(final List<Mrexpf> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);

			for (Mrexpf model : modelList) {
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

	/**
	 *
	 * @return List<Mrexpf>
	 * @throws Exception
	 */
	public List<Mrexpf> queryByCrtdat() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Mrexpf> returnList = null;
		try {	var asiutil = new Asiutil();
		MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
//		MrexpfExample ex = new MrexpfExample();
//		Criteria cr = ex.createCriteria();
//		Date begDate = asiutil.processStringToDate("yyyy/MM/dd hh:mm:ss",
//				asiutil.processDateToString(new Date(), "yyyy/MM/dd") + " 00:00:00");
		Date now = asiutil.processStringToDate("yyyy/MM/dd hh:mm:ss",
				asiutil.processDateToString(new Date(), "yyyy/MM/dd") + " 23:59:59");
		
//		cr.andCrtdatBetween(begDate, endDate);
//			ex.setDistinct(true);
			returnList = mapper.queryByCrtdat(now);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return returnList;
	}

	// /**
	// *
	// * @param key
	// * @return Mrexpf
	// * @throws Exception
	// */
	// public Mrexpf queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Mrexpf> returnList = null;
	// try
	// {
	// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
	// //MrexpfExample xp = new MrexpfExample();
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
	public List<Mrexpf> queryByMrexpf(final Mrexpf model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Mrexpf> returnList = null;
		try {
//            MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
//            MrexpfExample xp = new MrexpfExample();
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
	public List<Mrexpf> queryByMrexpf(final Mrexpf model, SqlSession sqlSession) throws Exception {
		List<Mrexpf> returnList = null;
//            MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
//            MrexpfExample xp = new MrexpfExample();
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
			MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	public int update(final Mrexpf model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	public List<Mrexpf> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Mrexpf> result = null;
		try {
			MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	public List<Mrexpf> queryAll(SqlSession sqlSession) throws Exception {
		List<Mrexpf> result = null;
		MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}

	public List<Mrexpf> queryCurrency(String currency) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Mrexpf> returnList = null;
		try {
			var asiutil = new Asiutil();
			MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
			Date now = asiutil.processStringToDate("yyyy/MM/dd hh:mm:ss",
					asiutil.processDateToString(new Date(), "yyyy/MM/dd") + " 23:59:59");
			returnList = mapper.queryCurrency(currency, now);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return returnList;
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
	// MrexpfMapper mapper = session.getMapper(MrexpfMapper.class);
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
	// public List<Mrexpf> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<Mrexpf> returnList = null;
	//
	// try {
	// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
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
	// public List<Mrexpf> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<Mrexpf> returnList = null;
	//
	// MrexpfMapper mapper = sqlSession.getMapper(MrexpfMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}