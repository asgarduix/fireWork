package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacBrokerMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFacBroker;
import com.asi.huanan.service.dao.mybatis.model.FriFacBrokerExample;
import com.asi.huanan.service.dao.mybatis.model.FriFacBrokerExample.Criteria;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query3;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query4;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriFacBrokerRepository {

	private Log log = LogFactory.getLog(FriFacBrokerRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====
	/**
	 * Rin1303-Rin1303查詢經紀人結果
	 * 
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303Query3
	 * @author yiting 2021/12/08
	 */
	public List<Rin1303Query4> queryRin1303BrokerInfo(String slipNo, String brokerId, FriFacBrokerMapper mapper)
			throws Exception {

		List<Rin1303Query4> results = new ArrayList<>();

		try {
			results = mapper.queryRin1303BrokerInfo(slipNo, brokerId);

		} catch (Exception e) {
			throw e;
		}
		return results;
	}

	/**
	 * Rin1303-查詢報表「經紀人」資料
	 * 
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303Query3
	 * @author yiting 2021/12/08
	 */
	public List<Rin1303Query3> queryRin1303Print3(String slipNo, String rinComId, FriFacBrokerMapper mapper)
			throws Exception {

		List<Rin1303Query3> results = new ArrayList<>();

		try {
			results = mapper.queryRin1303Print3(slipNo, rinComId);

		} catch (Exception e) {
			throw e;
		}
		return results;
	}

	/**
	 * 
	 */
	public List<FriFacBroker> queryByModelBetweenSize(final FriFacBroker model, String orderByColNm1, String ascOrDesc,
			int pageSize, int pageNum) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacBroker> returnList = null;

		try {
			// Integer[] a = CalculatorUtil.caculatorPageStartEndNum(pageSize, pageNum);

			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);

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
	public long queryCount(FriFacBroker model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	public long queryCount(FriFacBroker model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	public int insert(final FriFacBroker model, FriFacBrokerMapper mapper) {
		return mapper.insertSelective(model);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriFacBrokerMapper mapper) throws Exception {
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
	public int update(final FriFacBroker model, FriFacBrokerMapper mapper) throws Exception {
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
	// public int insert(final FriFacBroker model, SqlSession sqlSession) {
	// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	// public int update(final FriFacBroker model, SqlSession sqlSession) throws
	// Exception {
	// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriFacBroker model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	public int insertList(final List<FriFacBroker> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);

			for (FriFacBroker model : modelList) {
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
	// * @return List<FriFacBroker>
	// * @throws Exception
	// */
	// public List<FriFacBroker> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacBroker> returnList = null;
	// try {
	// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
	// FriFacBrokerExample ex = new FriFacBrokerExample();
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
	// * @return FriFacBroker
	// * @throws Exception
	// */
	// public FriFacBroker queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacBroker> returnList = null;
	// try
	// {
	// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
	// //FriFacBrokerExample xp = new FriFacBrokerExample();
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

	public int deleteByFriFacBroker(final FriFacBroker model, FriFacBrokerMapper mapper) throws Exception {

		FriFacBrokerExample xp = new FriFacBrokerExample();
		Criteria criteria = xp.createCriteria();
		if (StringUtils.isNotBlank(model.getSlipNo())) {
			criteria.andSlipNoEqualTo(model.getSlipNo());
		}

		return mapper.deleteByExample(xp);
	}

	/**
	 * /* @param key /* @return /* @throws Exception /
	 */
	public List<FriFacBroker> queryByFriFacBroker(final FriFacBroker model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacBroker> returnList = null;
		try {
			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
			FriFacBrokerExample xp = new FriFacBrokerExample();
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
	public List<FriFacBroker> queryByFriFacBroker(final FriFacBroker model, SqlSession sqlSession) throws Exception {
		List<FriFacBroker> returnList = null;
//            FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
//            FriFacBrokerExample xp = new FriFacBrokerExample();
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
			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	public int update(final FriFacBroker model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	public List<FriFacBroker> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFacBroker> result = null;
		try {
			FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	public List<FriFacBroker> queryAll(SqlSession sqlSession) throws Exception {
		List<FriFacBroker> result = null;
		FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	// FriFacBrokerMapper mapper = session.getMapper(FriFacBrokerMapper.class);
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
	// public List<FriFacBroker> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriFacBroker> returnList = null;
	//
	// try {
	// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
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
	// public List<FriFacBroker> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<FriFacBroker> returnList = null;
	//
	// FriFacBrokerMapper mapper = sqlSession.getMapper(FriFacBrokerMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}