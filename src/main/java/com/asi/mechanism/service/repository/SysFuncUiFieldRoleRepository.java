package com.asi.mechanism.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.mechanism.service.dao.mybatis.mapper.SysFuncUiFieldRoleMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiFieldRole;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiFieldRoleExample;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiFieldRoleExample.Criteria;

@Repository
public class SysFuncUiFieldRoleRepository {

	private Log log = LogFactory.getLog(SysFuncUiFieldRoleRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	/**
	 * 
	 */
	public List<SysFuncUiFieldRole> queryByModelBetweenSize(final SysFuncUiFieldRole model, String orderByColNm1,
			String ascOrDesc, int pageSize, int pageNum) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysFuncUiFieldRole> returnList = null;

		try {
			// Integer[] a = CalculatorUtil.caculatorPageStartEndNum(pageSize, pageNum);

			SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);

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
	public long queryCount(SysFuncUiFieldRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// SysFuncUiFieldRoleMapper mapper =
			// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	public long queryCount(SysFuncUiFieldRole model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// SysFuncUiFieldRoleMapper mapper =
		// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	public int insert(final SysFuncUiFieldRole model, SysFuncUiFieldRoleMapper mapper) {
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
	public int deleteByKey(final String primaryKey, SysFuncUiFieldRoleMapper mapper) throws Exception {
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
	public int update(final SysFuncUiFieldRole model, SysFuncUiFieldRoleMapper mapper) throws Exception {
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
	public int updateModifyPk(final SysFuncUiFieldRole pkModel, SysFuncUiFieldRole newPkModel,
			SysFuncUiFieldRoleMapper mapper) throws Exception {
		SysFuncUiFieldRoleExample ex = new SysFuncUiFieldRoleExample();
		Criteria cr = ex.createCriteria();
		cr.andUserRoleEqualTo(pkModel.getUserRole());
		cr.andMenuIdEqualTo(pkModel.getMenuId());
		return mapper.updateByExample(newPkModel, ex);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	// public int insert(final SysFuncUiFieldRole model, SqlSession sqlSession) {
	// SysFuncUiFieldRoleMapper mapper =
	// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	// SysFuncUiFieldRoleMapper mapper =
	// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	// public int update(final SysFuncUiFieldRole model, SqlSession sqlSession)
	// throws Exception {
	// SysFuncUiFieldRoleMapper mapper =
	// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final SysFuncUiFieldRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	public int insertList(final List<SysFuncUiFieldRole> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);

			for (SysFuncUiFieldRole model : modelList) {
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
	// * @return List<SysFuncUiFieldRole>
	// * @throws Exception
	// */
	// public List<SysFuncUiFieldRole> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<SysFuncUiFieldRole> returnList = null;
	// try {
	// SysFuncUiFieldRoleMapper mapper =
	// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
	// SysFuncUiFieldRoleExample ex = new SysFuncUiFieldRoleExample();
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
	// * @return SysFuncUiFieldRole
	// * @throws Exception
	// */
	// public SysFuncUiFieldRole queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<SysFuncUiFieldRole> returnList = null;
	// try
	// {
	// SysFuncUiFieldRoleMapper mapper =
	// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
	// //SysFuncUiFieldRoleExample xp = new SysFuncUiFieldRoleExample();
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
	public List<SysFuncUiFieldRole> queryBySysFuncUiFieldRole(final SysFuncUiFieldRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysFuncUiFieldRole> returnList = null;
		try {
//            SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
//            SysFuncUiFieldRoleExample xp = new SysFuncUiFieldRoleExample();
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
	public List<SysFuncUiFieldRole> queryBySysFuncUiFieldRole(final SysFuncUiFieldRole model, SqlSession sqlSession)
			throws Exception {
		List<SysFuncUiFieldRole> returnList = null;
		SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
		SysFuncUiFieldRoleExample xp = new SysFuncUiFieldRoleExample();
		Criteria criteria = xp.createCriteria();
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
			SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	public int update(final SysFuncUiFieldRole model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	public List<SysFuncUiFieldRole> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<SysFuncUiFieldRole> result = null;
		try {
			SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	public List<SysFuncUiFieldRole> queryAll(SqlSession sqlSession) throws Exception {
		List<SysFuncUiFieldRole> result = null;
		SysFuncUiFieldRoleMapper mapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	// SysFuncUiFieldRoleMapper mapper =
	/// session.getMapper(SysFuncUiFieldRoleMapper.class);
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
	// public List<SysFuncUiFieldRole> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<SysFuncUiFieldRole> returnList = null;
	//
	// try {
	// SysFuncUiFieldRoleMapper mapper =
	/// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
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
	// public List<SysFuncUiFieldRole> queryDistint{COL_NM}(SqlSession sqlSession)
	/// throws Exception {
	// List<SysFuncUiFieldRole> returnList = null;
	//
	// SysFuncUiFieldRoleMapper mapper =
	/// sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}