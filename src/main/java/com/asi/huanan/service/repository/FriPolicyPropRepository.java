package com.asi.huanan.service.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyDtlMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyProp;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyPropKey;
import com.asi.huanan.vo.Rin1304FriPolicyPropVOResp;
import com.asi.huanan.vo.Rin1304QueryDdlPropertyListVO;
import com.asi.huanan.vo.Rin1304QueryPolicyPropVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriPolicyPropRepository {

	private Log log = LogFactory.getLog(FriPolicyPropRepository.class);

	@Autowired
	private MyBatisConnector mybatis;

	// =====針對使用自訂SQL=====
	/**
	 * Rin1304_臨分分入，查詢標的物明細檔頁面結果
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyPropVOResp> queryPolicyPropDtlResult(String policyNo, String endorseNo, String addrNo,
			String propNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304FriPolicyPropVOResp> result = new ArrayList<>();
		try {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			result = mapper.queryPolicyPropDtlResult(policyNo, endorseNo, addrNo, propNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Rin1304C_臨分分入_標的物下拉選單
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryDdlPropertyListVO> queryDdlPropertyList() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304QueryDdlPropertyListVO> result = new ArrayList<>();
		try {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			result = mapper.queryDdlPropertyList();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Rin1304C_臨分分入_查詢標的物明細
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304VO> queryPolicyPropDtl(String policyNo, String endorseNo, FriPolicyPropMapper mapper)
			throws Exception {
		List<Rin1304VO> count = null;
		count = mapper.queryPolicyPropDtl(policyNo, endorseNo);
		return count;
	}

	/**
	 * Rin1304C_臨分分入_刪除標的物明細檔
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param propNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deletePolicyProp(String policyNo, String endorseNo, String addrNo, String propNo,
			FriPolicyPropMapper mapper) throws Exception {
		int count = 0;
		count = mapper.deletePolicyProp(policyNo, endorseNo, addrNo, propNo);
		return count;
	}
	
	/**
	 * Rin1304_臨分分入_新增保批單標的物明細檔 by 續保單號
	 * @param policyNo
	 * @param endorseNo
	 * @param getPolicyNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertFriPolicyPropForOldPolicy(String policyNo, String endorseNo, String getPolicyNo,
			FriPolicyPropMapper mapper) throws Exception {
		int count = 0;
		count = mapper.insertFriPolicyPropForOldPolicy(policyNo, endorseNo, getPolicyNo);
		return count;
	}
	
	
	/**
	 * Rin1304_建立新的自動調整批單(ADJ)
	 * @param policyNo
	 * @param endorseNo
	 * @param policyNoADJ
	 * @param endorseNoADJ
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int createADJpolicyProp(String policyNo, String endorseNo, String policyNoADJ, String endorseNoADJ,
			FriPolicyPropMapper mapper)throws Exception {
		int count = 0;
		count = mapper.createADJpolicyProp(policyNo,endorseNo, policyNoADJ,endorseNoADJ);
	       return count;
	}

	/**
	 * Rin1304_反算_取得標的物序號
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryPolicyPropVO> getPropNo(String policyNo, String endorseNo, String addrNo,FriPolicyPropMapper mapper) throws Exception{
		List<Rin1304QueryPolicyPropVO> result=null;
		result = mapper.getPropNo(policyNo,endorseNo,addrNo);
		return result;
	}

	/**
	 * Rin1304_反算_更改標的物明細檔保額/保費
	 * @param prem
	 * @param amt
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param propNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updatePropAmtPrem(BigDecimal prem, BigDecimal amt, String policyNo, String endorseNo, String addrNo,
			String propNo ,FriPolicyPropMapper mapper)throws Exception {
		int count = 0;
		count = mapper.updatePropAmtPrem(prem,amt,policyNo,endorseNo, addrNo,propNo);
	       return count;
	}

	// =====針對使用自訂SQL結束=====

	/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriPolicyProp model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if (model == null) {
				FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
				count = mapper.countByExample(null);
			}

			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	public long queryCount(FriPolicyProp model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	public int insert(final FriPolicyProp model, FriPolicyPropMapper mapper) {
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
	public int deleteByKey(final String primaryKey, FriPolicyPropMapper mapper) throws Exception {
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
	public int update(final FriPolicyProp model, FriPolicyPropMapper mapper) throws Exception {
		return mapper.updateByPrimaryKey(model);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	// public int insert(final FriPolicyProp model, SqlSession sqlSession) {
	// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	// public int update(final FriPolicyProp model, SqlSession sqlSession) throws
	// Exception {
	// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
	// return mapper.updateByPrimaryKey(model);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriPolicyProp model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	public int insertList(final List<FriPolicyProp> modelList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;

		try {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);

			for (FriPolicyProp model : modelList) {
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
	// * @return List<FriPolicyProp>
	// * @throws Exception
	// */
	// public List<FriPolicyProp> queryByJobId(final String jobId)
	// throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriPolicyProp> returnList = null;
	// try {
	// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
	// FriPolicyPropExample ex = new FriPolicyPropExample();
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
	// * @return FriPolicyProp
	// * @throws Exception
	// */
	// public FriPolicyProp queryByJobId(final String id) throws Exception
	// {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriPolicyProp> returnList = null;
	// try
	// {
	// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
	// //FriPolicyPropExample xp = new FriPolicyPropExample();
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
	public List<FriPolicyProp> queryByFriPolicyProp(final FriPolicyProp model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicyProp> returnList = null;
		try {
//            FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
//            FriPolicyPropExample xp = new FriPolicyPropExample();
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
	public List<FriPolicyProp> queryByFriPolicyProp(final FriPolicyProp model, SqlSession sqlSession) throws Exception {
		List<FriPolicyProp> returnList = null;
//            FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
//            FriPolicyPropExample xp = new FriPolicyPropExample();
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
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	public int update(final FriPolicyProp model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		try {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	public List<FriPolicyProp> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicyProp> result = null;
		try {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	public List<FriPolicyProp> queryAll(SqlSession sqlSession) throws Exception {
		List<FriPolicyProp> result = null;
		FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}

	/**
	 * Rin1304_標的物明細檔_取標的物序號最大值
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304QueryPolicyPropVO> getMaxpropNo(String policyNo, String endorseNo, String addrNo)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304QueryPolicyPropVO> result = null;
		try {
			FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			result = mapper.getMaxpropNo(policyNo,endorseNo,addrNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	
	
	/**
	 * Rin1304_臨分分入_修改標的物明細檔地址序號
	 * @param addrNo
	 * @param policyNo
	 * @param endorseNo
	 * @param oldAddrNo
	 * @param mapper
	 * @return
	 */
	public int updatePolicyPropAddrNo(String addrNo , String policyNo, String endorseNo, String oldAddrNo,FriPolicyPropMapper mapper) {
		int result=0;
		result = mapper.updatePolicyPropAddrNo(addrNo,policyNo,endorseNo,oldAddrNo);
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
	// FriPolicyPropMapper mapper = session.getMapper(FriPolicyPropMapper.class);
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
	// public List<FriPolicyProp> queryDistint{COL_NM}() throws Exception {
	// SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	// List<FriPolicyProp> returnList = null;
	//
	// try {
	// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
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
	// public List<FriPolicyProp> queryDistint{COL_NM}(SqlSession sqlSession) throws
	/// Exception {
	// List<FriPolicyProp> returnList = null;
	//
	// FriPolicyPropMapper mapper = sqlSession.getMapper(FriPolicyPropMapper.class);
	//
	// returnList = mapper.selectDistint{COL_NM}();
	//
	// log.debug(returnList == null ? "returnList is null" : "筆數:" +
	/// returnList.size());
	// return returnList;
	// }
}