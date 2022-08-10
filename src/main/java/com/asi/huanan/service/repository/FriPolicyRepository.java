package com.asi.huanan.service.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicy;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyExample;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyExample.Criteria;
import com.asi.huanan.vo.Rin1304CheckAmtPremRespVO;
import com.asi.huanan.vo.Rin1304DdlCurncyListVOResp;
import com.asi.huanan.vo.Rin1304FriPolicyVORes;
import com.asi.huanan.vo.Rin1304FriPolicyVOResp;
import com.asi.huanan.vo.Rin1304GetAddrNoVO;
import com.asi.huanan.vo.Rin1304QueryAcctFlagRespVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;
import com.asi.mechanism.service.connector.MyBatisConnector;

/**
 * @author sophia_hung
 *
 */
@Repository
public class FriPolicyRepository {

	private Log log = LogFactory.getLog(FriPolicyRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====

	/**
	 * Rin1304_臨分分入，刪除保批單主檔
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public int deletePolicyByPrimaryKey(String policyNo, String endorseNo, FriPolicyMapper mapper) {
		int count = 0;
		count = mapper.deletePolicyByPrimaryKey(policyNo, endorseNo);
		return count;
	}

	/**
	 * Rin1304_臨分分入，保批單主檔查詢結果
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyVOResp> queryPolicyByPrimaryKey(String policyNo, String endorseNo) throws Exception {

		List<Rin1304FriPolicyVOResp> result = new ArrayList<>();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			result = mapper.queryPolicyByPrimaryKey(policyNo, endorseNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Rin1304_臨分分入，判斷是否有資料
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 */
	public List<Rin1304VO> queryrin1304(String policyNo, String endorseNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304VO> result = null;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			result = mapper.queryrin1304(policyNo, endorseNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Rin1304A_臨分分入_檢查是否立帳
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryAcctFlagRespVO> checkAcctfFlagIsY(String policyNo, String endorseNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304QueryAcctFlagRespVO> result = null;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			result = mapper.checkAcctfFlagIsY(policyNo, endorseNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Rin1304A_臨分分入_幣別下拉選單
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304DdlCurncyListVOResp> queryDdlCurncyList() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304DdlCurncyListVOResp> result = null;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			result = mapper.queryDdlCurncyList();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	
	/**
	 * Rin1304_查詢共保累積保額和共保累積保費
	 * 
	 * @param policyNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyVOResp> querySumAllAmtPremByPolicyNo(String policyNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304FriPolicyVOResp> result = null;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			result = mapper.querySumAllAmtPremByPolicyNo(policyNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	
	/**
	 * Rin1304_新增保批單主檔 by 續保單號
	 * @param policyNo
	 * @param endorseNo
	 * @param oldPolicy
	 * @param getPolicyNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertFriPolicyByOldPolicyNo(String policyNo, String endorseNo, String oldPolicy, String getPolicyNo,
			FriPolicyMapper mapper) throws Exception {
		int count = 0;
		count = mapper.insertFriPolicyByOldPolicyNo(policyNo, endorseNo, oldPolicy, getPolicyNo);
		return count;
	}
	
	/**
	 * Rin1304臨分分入_自動調整批單號(ADJ)
	 * @param policyNo
	 * @param endorseNo
	 * @param policyDprt
	 * @param policyNoADJ
	 * @param endorseNoADJ
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int createADJpolicy(String policyNo, String endorseNo, String policyDprt, String policyNoADJ,
			String endorseNoADJ, FriPolicyMapper mapper)  throws Exception{
		int count = 0;
		count = mapper.createADJpolicy(policyNo, endorseNo,policyDprt,policyNoADJ,endorseNoADJ);
		return count;
	}

	/**
	 * Rin1304臨分分入_反算_檢查保額/保費加總金額
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304CheckAmtPremRespVO> checkAmtPrem(String policyNo, String endorseNo)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304CheckAmtPremRespVO> result = null;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			result = mapper.checkAmtPrem(policyNo,endorseNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	// =====針對使用自訂SQL結束====

	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriPolicy model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	public long queryCount(FriPolicy model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	public int insert(final FriPolicy model, FriPolicyMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriPolicyMapper mapper) throws Exception {
//		return mapper.deleteByPrimaryKey(primaryKey);
		return 0;
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicy model, FriPolicyMapper mapper) throws Exception {
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
	// public int insert(final FriPolicy model, SqlSession sqlSession) {
	// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	// public int update(final FriPolicy model, SqlSession sqlSession) throws
	// Exception {
	// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriPolicy model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	public int insertList(final List<FriPolicy> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);

			for (FriPolicy model : modelList) {
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
	// * @return List<FriPolicy>
	// * @throws Exception
	// */
	// public List<FriPolicy> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriPolicy> returnList = null;
	// try {
	// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
	// FriPolicyExample ex = new FriPolicyExample();
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
	// * @return FriPolicy
	// * @throws Exception
	// */
	// public FriPolicy queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriPolicy> returnList = null;
	// try
	// {
	// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
	// //FriPolicyExample xp = new FriPolicyExample();
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
	public List<FriPolicy> queryByFriPolicy(final FriPolicy model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicy> returnList = null;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
			FriPolicyExample xp = new FriPolicyExample();
			Criteria criteria = xp.createCriteria();
			if (StringUtils.isNotBlank(model.getPolicyNo())) {
				criteria.andPolicyNoEqualTo(model.getPolicyNo());
			}

			if (StringUtils.isNotBlank(model.getEndorseNo())) {
				criteria.andEndorseNoEqualTo(model.getEndorseNo());
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
	public List<FriPolicy> queryByFriPolicy(final FriPolicy model, SqlSession sqlSession) throws Exception {
		List<FriPolicy> returnList = null;
//            FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
//            FriPolicyExample xp = new FriPolicyExample();
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
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	public int update(final FriPolicy model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	public List<FriPolicy> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicy> result = null;
		try {
			FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	public List<FriPolicy> queryAll(SqlSession sqlSession) throws Exception {
		List<FriPolicy> result = null;
		FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	// FriPolicyMapper mapper = session.getMapper(FriPolicyMapper.class);
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
	// public List<FriPolicy> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriPolicy> returnList = null;
	//
	// try {
	// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
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
	// public List<FriPolicy> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<FriPolicy> returnList = null;
	//
	// FriPolicyMapper mapper = sqlSession.getMapper(FriPolicyMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}