package com.asi.mechanism.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.mechanism.service.dao.mybatis.mapper.SysAccountRoleMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRole;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRoleExample;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRoleKey;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRoleExample.Criteria;

@Repository
public class SysAccountRoleRepository {

	private Log log = LogFactory.getLog(SysAccountRoleRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	/**
	 * 
	 */
	public List<SysAccountRole> queryByModelBetweenSize(final SysAccountRole model, String orderByColNm1,
			String ascOrDesc, int pageSize, int pageNum) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysAccountRole> returnList = null;

		try {
			// Integer[] a = CalculatorUtil.caculatorPageStartEndNum(pageSize, pageNum);

			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// returnList = mapper.selectByParamBetweenSize(model.{GET}..., "CRT_TIME",
			// "DESC", a[0], a[1]);

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
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(SysAccountRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// SysAccountRoleMapper mapper =
			// sqlSession.getMapper(SysAccountRoleMapper.class);
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
	public long queryCount(SysAccountRole model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// SysAccountRoleMapper mapper =
		// sqlSession.getMapper(SysAccountRoleMapper.class);
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
	public int insert(final SysAccountRole model, SysAccountRoleMapper mapper) {
		return mapper.insertSelective(model);
//		return 0;
	}

	/**
	 * 
	 * @param primaryKey
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final SysAccountRoleKey primaryKey, SysAccountRoleMapper mapper) throws Exception {
		return mapper.deleteByPrimaryKey(primaryKey);
//		return 0;
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final SysAccountRole model, SysAccountRoleMapper mapper) throws Exception {
		return mapper.updateByPrimaryKey(model);
//		return 0;
	}

	/**
	 * 
	 * @param pkModel
	 * @param newPkModel
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateModifyPk(final SysAccountRole pkModel, SysAccountRole newPkModel, SysAccountRoleMapper mapper)
			throws Exception {
		SysAccountRoleExample ex = new SysAccountRoleExample();
		Criteria cr = ex.createCriteria();
		cr.andUserRoleEqualTo(pkModel.getUserRole());
		cr.andAkaIdEqualTo(pkModel.getAkaId());
		return mapper.updateByExample(newPkModel, ex);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	// public int insert(final SysAccountRole model, SqlSession sqlSession) {
	// SysAccountRoleMapper mapper =
	// sqlSession.getMapper(SysAccountRoleMapper.class);
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
	// SysAccountRoleMapper mapper =
	// sqlSession.getMapper(SysAccountRoleMapper.class);
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
	// public int update(final SysAccountRole model, SqlSession sqlSession) throws
	// Exception {
	// SysAccountRoleMapper mapper =
	// sqlSession.getMapper(SysAccountRoleMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final SysAccountRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
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
	public int insertList(final List<SysAccountRole> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);

			for (SysAccountRole model : modelList) {
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
	// * @return List<SysAccountRole>
	// * @throws Exception
	// */
	// public List<SysAccountRole> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<SysAccountRole> returnList = null;
	// try {
	// SysAccountRoleMapper mapper =
	// sqlSession.getMapper(SysAccountRoleMapper.class);
	// SysAccountRoleExample ex = new SysAccountRoleExample();
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
	// * @return SysAccountRole
	// * @throws Exception
	// */
	// public SysAccountRole queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<SysAccountRole> returnList = null;
	// try
	// {
	// SysAccountRoleMapper mapper =
	// sqlSession.getMapper(SysAccountRoleMapper.class);
	// //SysAccountRoleExample xp = new SysAccountRoleExample();
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
	public List<SysAccountRole> queryBySysAccountRole(final SysAccountRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysAccountRole> returnList = null;
		try {
			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
			SysAccountRoleExample xp = new SysAccountRoleExample();
			Criteria criteria = xp.createCriteria();
			if (StringUtils.isNotBlank(model.getAkaId())) {
				criteria.andAkaIdEqualTo(model.getAkaId());
			}

			// if (StringUtils.isNotBlank(model.getJobName()))
			// {
			// criteria.andJobNameEqualTo(model.getJobName());
			// }
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
	public List<SysAccountRole> queryBySysAccountRole(final SysAccountRole model, SqlSession sqlSession)
			throws Exception {
		List<SysAccountRole> returnList = null;
		SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
		SysAccountRoleExample xp = new SysAccountRoleExample();
		Criteria criteria = xp.createCriteria();
		if (StringUtils.isNotBlank(model.getAkaId())) {
			criteria.andAkaIdEqualTo(model.getAkaId());
		}
		if (StringUtils.isNotBlank(model.getUserRole())) {
			criteria.andUserRoleEqualTo(model.getUserRole());
		}

		// if (StringUtils.isNotBlank(model.getJobName()))
		// {
		// criteria.andJobNameEqualTo(model.getJobName());
		// }
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
			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
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
	public int update(final SysAccountRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
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
	public List<SysAccountRole> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysAccountRole> result = null;
		try {
			SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
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
	public List<SysAccountRole> queryAll(SqlSession sqlSession) throws Exception {
		List<SysAccountRole> result = null;
		SysAccountRoleMapper mapper = sqlSession.getMapper(SysAccountRoleMapper.class);
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
	// SysAccountRoleMapper mapper = session.getMapper(SysAccountRoleMapper.class);
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
	// public List<SysAccountRole> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<SysAccountRole> returnList = null;
	//
	// try {
	// SysAccountRoleMapper mapper =
	/// sqlSession.getMapper(SysAccountRoleMapper.class);
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
	// public List<SysAccountRole> queryDistint{COL_NM}(SqlSession sqlSession)
	/// throws Exception {
	// List<SysAccountRole> returnList = null;
	//
	// SysAccountRoleMapper mapper =
	/// sqlSession.getMapper(SysAccountRoleMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}