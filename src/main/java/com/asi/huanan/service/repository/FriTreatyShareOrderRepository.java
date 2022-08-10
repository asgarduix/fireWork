package com.asi.huanan.service.repository;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyShareOrderMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShareOrder;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShareOrderExample;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShareOrderExample.Criteria;
import com.asi.huanan.vo.DeleteTreatyShareOrderVo;
import com.asi.huanan.vo.Rin1108Vo;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriTreatyShareOrderRepository {

	private Log log = LogFactory.getLog(FriTreatyShareOrderRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	/**
	 * 
	 * @param model
	 * @return int
	 * @throws Exception
	 */
	public int deleteTreadyShareOrdersByPkList(final List<DeleteTreatyShareOrderVo> model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
			count = mapper.deleteTreadyShareOrdersByPkList(model);
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
	 * @param treatyYear
	 * @param policyType
	 * @return
	 * @throws Exception
	 */
	public List<Rin1108Vo> queryTreatyShareOrderList(final String treatyYear, final String policyType)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1108Vo> returnList = null;
		try {
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);

			returnList = mapper.queryTreatyShareOrderList(treatyYear, policyType);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return returnList;
	}

	/**
	 * 
	 */
	public List<FriTreatyShareOrder> queryByModelBetweenSize(final FriTreatyShareOrder model, String orderByColNm1,
			String ascOrDesc, int pageSize, int pageNum) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyShareOrder> returnList = null;

		try {
			// Integer[] a = CalculatorUtil.caculatorPageStartEndNum(pageSize, pageNum);

			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);

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
	public long queryCount(FriTreatyShareOrder model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriTreatyShareOrderMapper mapper =
			// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	public long queryCount(FriTreatyShareOrder model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriTreatyShareOrderMapper mapper =
		// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	public int insert(final FriTreatyShareOrder model, FriTreatyShareOrderMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriTreatyShareOrderMapper mapper) throws Exception {
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
	public int update(final FriTreatyShareOrder model, FriTreatyShareOrderMapper mapper) throws Exception {
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
	// public int insert(final FriTreatyShareOrder model, SqlSession sqlSession) {
	// FriTreatyShareOrderMapper mapper =
	// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	// FriTreatyShareOrderMapper mapper =
	// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	// public int update(final FriTreatyShareOrder model, SqlSession sqlSession)
	// throws Exception {
	// FriTreatyShareOrderMapper mapper =
	// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriTreatyShareOrder model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	public int insertList(final List<FriTreatyShareOrder> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);

			for (FriTreatyShareOrder model : modelList) {
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
	// * @return List<FriTreatyShareOrder>
	// * @throws Exception
	// */
	// public List<FriTreatyShareOrder> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriTreatyShareOrder> returnList = null;
	// try {
	// FriTreatyShareOrderMapper mapper =
	// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
	// FriTreatyShareOrderExample ex = new FriTreatyShareOrderExample();
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
	// * @return FriTreatyShareOrder
	// * @throws Exception
	// */
	// public FriTreatyShareOrder queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriTreatyShareOrder> returnList = null;
	// try
	// {
	// FriTreatyShareOrderMapper mapper =
	// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
	// //FriTreatyShareOrderExample xp = new FriTreatyShareOrderExample();
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
	public List<FriTreatyShareOrder> queryByFriTreatyShareOrder(final FriTreatyShareOrder model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyShareOrder> returnList = null;
		try {
            FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
            FriTreatyShareOrderExample xp = new FriTreatyShareOrderExample();
            Criteria criteria = xp.createCriteria();
//            if (StringUtils.isNotBlank(model.get{VAR_NAME}()))
//            {
//            criteria.and{VAR_NAME}EqualTo(model.get{VAR_NAME}());
//            }
//			
             if (StringUtils.isNotBlank(model.getTreatyYear()))
             {
             criteria.andTreatyYearEqualTo(model.getTreatyYear());
             }
             if (StringUtils.isNotBlank(model.getPolicyType()))
             {
             criteria.andPolicyTypeEqualTo(model.getPolicyType());
             }
             if (model.getShareOrder() != null)
             {
             criteria.andShareOrderEqualTo(model.getShareOrder());
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
	public List<FriTreatyShareOrder> queryByFriTreatyShareOrder(final FriTreatyShareOrder model, SqlSession sqlSession)
			throws Exception {
		List<FriTreatyShareOrder> returnList = null;
//            FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
//            FriTreatyShareOrderExample xp = new FriTreatyShareOrderExample();
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
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	public int update(final FriTreatyShareOrder model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	public List<FriTreatyShareOrder> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyShareOrder> result = null;
		try {
			FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	public List<FriTreatyShareOrder> queryAll(SqlSession sqlSession) throws Exception {
		List<FriTreatyShareOrder> result = null;
		FriTreatyShareOrderMapper mapper = sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	// FriTreatyShareOrderMapper mapper =
	/// session.getMapper(FriTreatyShareOrderMapper.class);
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
	// public List<FriTreatyShareOrder> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriTreatyShareOrder> returnList = null;
	//
	// try {
	// FriTreatyShareOrderMapper mapper =
	/// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
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
	// public List<FriTreatyShareOrder> queryDistint{COL_NM}(SqlSession sqlSession)
	/// throws Exception {
	// List<FriTreatyShareOrder> returnList = null;
	//
	// FriTreatyShareOrderMapper mapper =
	/// sqlSession.getMapper(FriTreatyShareOrderMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}