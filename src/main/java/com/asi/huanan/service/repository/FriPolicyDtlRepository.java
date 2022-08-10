package com.asi.huanan.service.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyDtlMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;
import com.asi.huanan.vo.R1304QueryPolicyDtlVO;
import com.asi.huanan.vo.Rin1203AVOResp;
import com.asi.huanan.vo.Rin1304FriPolicyDtlVOResp;
import com.asi.huanan.vo.Rin1304GetAddrNoVO;
import com.asi.huanan.vo.Rin1304GetUsePropVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class FriPolicyDtlRepository {

	private Log log = LogFactory.getLog(FriPolicyDtlRepository.class);

	@Autowired
	private MyBatisConnector mybatis;
	
	//=====針對使用自訂SQL=====
	/**
	 * Rin1203_同險設定，更正地段
	 * @param area_code
	 * @param policy_no
	 * @param endorse_no
	 * @param addr_no
	 * @return
	 * @throws Exception
	 */
	public int updateAreaCode(String areaCode, String policyNo, String endorseNo, Integer addrNo)throws Exception{
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            count = mapper.updateAreaCode(areaCode, policyNo, endorseNo, addrNo);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
	}

	/**
	 * Rin1203_同險設定，更正地址
	 * @param prop_addr
	 * @param policy_no
	 * @param endorse_no
	 * @param addr_no
	 * @return
	 * @throws Exception
	 */
	public int updateAddress(String propAddr, String policyNo, String endorseNo, Integer addrNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            count = mapper.updateAddress(propAddr, policyNo, endorseNo, addrNo);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
	}

	/**
	 * Rin1203_同險設定，更正同險號
	 * @param risk_no
	 * @param risk_name
	 * @param policy_no
	 * @param endorse_no
	 * @param addr_no
	 * @return
	 * @throws Exception
	 */
	public int updateRiskNo(String riskNo, String riskName, String policyNo, String endorseNo, Integer addrNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            count = mapper.updateRiskNo(riskNo, riskName, policyNo,endorseNo, addrNo);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
	}
	
	
	/**
	 * 取得同險_下拉選單
	 * @param record
	 * @param riskNo
	 * @param riskName
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyDelList(List<Rin1203AVOResp> record, String riskNo, String riskName)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
    
            count = mapper.updatePolicyDelList(record, riskNo, riskName);            	
    
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
	}
	
	
	/**
	 * Rin1203同險設定，更改舊同險號
	 * @param riskNo
	 * @param riskName
	 * @param policyNo
	 * @param endorseNo
	 * @param propAddr
	 * @return
	 * @throws Exception
	 */
	public int updateOldRiskNo(String riskNo, String riskName, String policyNo, String endorseNo,String propAddr) throws  Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            count = mapper.updateOldRiskNo(riskNo, riskName, policyNo,endorseNo, propAddr);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
	}


	/**
	 * Rin1304_臨分分入，查詢保批單明細
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public List<Rin1304VO> queryPolicyDtl(String policyNo, String endorseNo, FriPolicyDtlMapper mapper) {
		List<Rin1304VO> count=null;
		count = mapper.queryPolicyDtl(policyNo,endorseNo);
		return count;
	}
	
	/**
	 * Rin1304_臨分分入，刪除保批單明細
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public int deletePolicyDtl(String policyNo, String endorseNo,String addrNo,FriPolicyDtlMapper mapper) {
		int count=0;
		count = mapper.deletePolicyDtl(policyNo,endorseNo,addrNo);
		return count;
	}
	
	
	/**
	 * Rin1304_臨分分入，查詢保批單明細檔
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyDtlVOResp> queryPolicyDtlByPrimaryKey(String policyNo, String endorseNo, String addrNo)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304FriPolicyDtlVOResp> result = new ArrayList<>();
		try {
			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			result = mapper.queryPolicyDtlByPrimaryKey(policyNo,endorseNo,addrNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	
	/**
	 * Rin1304B_臨分分入_查詢地段序號
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNoOri
	 * @return
	 * @throws Exception
	 */
	public List<R1304QueryPolicyDtlVO> queryaddrNo(String policyNo, String endorseNo, String addrNoOri)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<R1304QueryPolicyDtlVO> result =null;
		try {
			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			result = mapper.queryaddrNo(policyNo,endorseNo,addrNoOri);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Rin1304B_臨分分入_將保單序號+1
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNoOri
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateAddrNoOriPlusOne(String policyNo, String endorseNo, String addrNoOri,FriPolicyDtlMapper mapper)throws Exception {
		int count=0;
		count = mapper.updateAddrNoOriPlusOne(policyNo,endorseNo,addrNoOri);
		return count;
	}

	/**
	 * Rin1304B_臨分分入_重新排序保單序號
	 * @param policyNo
	 * @param endorseNo
	 * @param mapper
	 * @return
	 */
	public int updateAddrNoOriAll(String policyNo, String endorseNo, FriPolicyDtlMapper mapper) {
		int count=0;
		count = mapper.updateAddrNoOriAll(policyNo,endorseNo);
		return count;
	}

	/**
	 * Rin1304B_臨分分入_加總保額
	 * @param policyNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304FriPolicyDtlVOResp> sumPolicyEndorseAmt(String policyNo, String addrNo)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304FriPolicyDtlVOResp> result =null;
		try {
			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			result = mapper.sumPolicyEndorseAmt(policyNo,addrNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	/**
	 * Rin1304_臨分分入_新增保批單明細檔 by 續保單號
	 * @param policyNo
	 * @param endorseNo
	 * @param getPolicyNo
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertFriPolicyDtlForOldPolicy(String policyNo, String endorseNo, String getPolicyNo,FriPolicyDtlMapper mapper)throws Exception {
		int count = 0;
		count = mapper.insertFriPolicyDtlForOldPolicy(policyNo,endorseNo, getPolicyNo);
	       return count;
	}

	/**
	 * Rin1304_建立新的自動產生調整批單號(ADJ)
	 * @param policyNo
	 * @param endorseNo
	 * @param policyNoADJ
	 * @param endorseNoADJ
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int createADJpolicyDtl(String policyNo, String endorseNo, String policyNoADJ, String endorseNoADJ,
			FriPolicyDtlMapper mapper)throws Exception {
		int count = 0;
		count = mapper.createADJpolicyDtl(policyNo,endorseNo, policyNoADJ,endorseNoADJ);
	       return count;
	}

	/**
	 * Rin1304_轉檔_將明細保期更改同主檔保期
	 * @param policyDbgn
	 * @param policyDend
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyDates(Date policyDbgn, Date policyDend, String policyNo, String endorseNo) throws Exception{
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            count = mapper.updatePolicyDates(policyDbgn,policyDend,policyNo,endorseNo);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
	}

	/**
	 * Rin1304_保批單明細檔_查詢使用性質名稱
	 * @param usePropId
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304GetUsePropVO> getUseProp(String usePropId)throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1304GetUsePropVO> result = new ArrayList<>();
		try {
			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			result = mapper.getUseProp(usePropId);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Rin1304_反算_更改保批當明細檔保額/保費
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param amtTyp
	 * @param premTyp
	 * @param amtEar
	 * @param premEar
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updatePolicyDtlAmtPrem(String policyNo, String endorseNo, String addrNo, BigDecimal amtTyp,
			BigDecimal premTyp, BigDecimal amtEar, BigDecimal premEar,FriPolicyDtlMapper mapper) throws Exception{
		int count = 0;
		count = mapper.updatePolicyDtlAmtPrem(policyNo,endorseNo, addrNo,amtTyp,premTyp,amtEar,premEar);
	       return count;
	}

	/**
	 * Rin1304臨分分入_反算_取得地址序號
	 * @param policyNo
	 * @param endorseNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1304GetAddrNoVO> getAddrNo(String policyNo, String endorseNo,FriPolicyDtlMapper mapper) {
		List<Rin1304GetAddrNoVO> res=null;
		res = mapper.getAddrNo(policyNo,endorseNo);
		return res;
	}

	/**
	 * Rin1304臨分分入_保批單明細檔_查詢地址序號&保單地址序號最大值
	 * @param policyNo
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyDtl> qreryPolicyAddrNo(String policyNo,String endorseNo) throws Exception{
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicyDtl> result = new ArrayList<>();
		try {
			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			result = mapper.qreryPolicyAddrNo(policyNo,endorseNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
	//=====針對使用自訂SQL結束=====
	
			/**
	 * @param model
	 * @return
	 * 
	 */
	public long queryCount(FriPolicyDtl model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		long count = 0;

		try {
			if(model == null) {
				FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
				count = mapper.countByExample(null);
			}
			
			// if (model.{GET} != null & !"".equals(model.{GET})) {
			// model.{SET}(model.{GET} + "%");
			// }

			//			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			//			count = mapper.countByExample(example);
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
	public long queryCount(FriPolicyDtl model, SqlSession sqlSession) throws Exception {
		long count = 0;

		if (model == null) {
			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			count = mapper.countByExample(null);
			return count;
		}

		// if (model.{GET} != null & !"".equals(model.{GET})) {
		// model.{SET}(model.{GET} + "%");
		// }

		//		FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
		//		count = mapper.countByExample(example);

		return count;
	}
	
	//=====以下為基本使用的=====
	
	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	public int insert(final FriPolicyDtl model, FriPolicyDtlMapper mapper) {
		//return mapper.insertSelective(model);
		return 0;
	}
	
	/**
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriPolicyDtlMapper mapper) throws Exception {
		//return mapper.deleteByPrimaryKey(primaryKey);
		return 0;
	}

	/**
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public int update(final FriPolicyDtl model, FriPolicyDtlMapper mapper) throws Exception {
		//return mapper.updateByPrimaryKey(model);
		return 0;
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 */
	//public int insert(final FriPolicyDtl model, SqlSession sqlSession) {
	//	FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
	//	return mapper.insertSelective(model);
	//}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws Exception {
	//	FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
	//	return mapper.deleteByPrimaryKey(primaryKey);
	//}
    
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final FriPolicyDtl model, SqlSession sqlSession) throws Exception {
	//	FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
	//	return mapper.updateByPrimaryKey(model);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriPolicyDtl model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            count = mapper.insertSelective(model);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
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
	public int insertList(final List<FriPolicyDtl> modelList)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int count = 0;
		
		try {
			FriPolicyDtlMapper mapper = sqlSession
					.getMapper(FriPolicyDtlMapper.class);

			for (FriPolicyDtl model : modelList) {
				//count += mapper.insertSelective(model);
			}

			sqlSession.commit();
		} catch(Exception e){
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return count;
	}

    // /**
    // *
    // * @return List<FriPolicyDtl>
    // * @throws Exception
    // */
    // public List<FriPolicyDtl> queryByJobId(final String jobId)
    // throws Exception {
    // SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    // List<FriPolicyDtl> returnList = null;
    // try {
    // FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
    // FriPolicyDtlExample ex = new FriPolicyDtlExample();
    // Criteria cr = ex.createCriteria();
    // cr.andJobIdEqualTo(jobId);
    // returnList = mapper.selectByExample(ex);
    // } finally {
    // sqlSession.close();
    // }
    // return returnList;
    // }

    // /**
    //  * 
    //  * @param key
    //  * @return FriPolicyDtl
    //  * @throws Exception
    //  */
    // public FriPolicyDtl queryByJobId(final String id) throws Exception
    // {
    //     SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
    //     List<FriPolicyDtl> returnList = null;
    //     try
    //     {
    //         FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
    //         //FriPolicyDtlExample xp = new FriPolicyDtlExample();
    //         //Criteria criteria = xp.createCriteria();
    //         // if (StringUtils.isNotBlank(jobId))
    //         // {
    //         // criteria.andJobIdEqualTo(jobId);
    //         // }
    //         //returnList = mapper.selectByExample(xp);
    //         log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
    //     }
    //     finally
    //     {
    //         sqlSession.close();
    //     }
    //     return CollectionUtils.isNotEmpty(returnList) ? returnList.get(0)
    //             : null;
    // }


	/** 
	/* @param key
	/* @return 
	/* @throws Exception
	/*/
    public List<FriPolicyDtl> queryByFriPolicyDtl(final FriPolicyDtl model)
            throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        List<FriPolicyDtl> returnList = null;
        try
        {
//            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
//            FriPolicyDtlExample xp = new FriPolicyDtlExample();
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
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return returnList;
    }

	/** 
	/* @param key
	/* @return 
	/* @throws Exception
	/*/
    public List<FriPolicyDtl> queryByFriPolicyDtl(final FriPolicyDtl model, SqlSession sqlSession)
            throws Exception
    {
        List<FriPolicyDtl> returnList = null;
//            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
//            FriPolicyDtlExample xp = new FriPolicyDtlExample();
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
    public int deleteByKey(final String primaryKey) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
		
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            // count = mapper.deleteByPrimaryKey(primaryKey);//防呆,需要刪除再打開
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
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
    public int update(final FriPolicyDtl model) throws Exception
    {
        SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
        int count = 0;
        try
        {
            FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
            count = mapper.updateByPrimaryKey(model);
            sqlSession.commit();
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return count;
    }
	
	/**
	/* @param insId
	/* @param prgId
	/* @param vhclInsId
	/* @return
	*/
	public List<FriPolicyDtl> queryAll() throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicyDtl> result = null;
		try {
			FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
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
	/* @param insId
	/* @param prgId
	/* @param vhclInsId
	/* @return
	*/
	public List<FriPolicyDtl> queryAll(SqlSession sqlSession) throws Exception {
		List<FriPolicyDtl> result = null;
		FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
		result = mapper.selectByExample(null);
		return result;
	}

	/**
	 * Rin1304_臨分分入_修改保批單明細地址序號
	 * @param record
	 * @param oldAddrNo
	 * @param mapper
	 * @return
	 */
	public int updateAddrNo(FriPolicyDtl record, String oldAddrNo ,FriPolicyDtlMapper mapper) {
		int count=0;
		count = mapper.updateAddrNo(record,oldAddrNo);
		return count;
	}


	

	///**
	// * @param insId
	// * @param prgId
	// * @param vhclInsId
	// * @return
	// */
	//public int deleteAll() throws Exception {
	//	SqlSession sqlSession = ConnectionFactory.getSession().openSession();
	//	int result = 0;
	//	try {
	//		FriPolicyDtlMapper mapper = session.getMapper(FriPolicyDtlMapper.class);
	//		result = mapper.deleteAll();
	//		session.commit();
	//	} catch (Exception e) {
	//		session.rollback();
	//		throw e;
	//	} finally {
	//		session.close();
	//	}
	//	return result;
	//}
	
	///**
	// * 注意，此method解註解，須在mapper.java中實作selectDistintXXX方法
	// *可能類似「@Select("select distinct upvirs from amdupf1") List<Amdupf1> selectDistintTypins();」
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	//public List<FriPolicyDtl> queryDistint{COL_NM}() throws Exception {
	//	SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
	//	List<FriPolicyDtl> returnList = null;
    //
	//	try {
	//		FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
	//		returnList = mapper.selectDistint{COL_NM}();
	//	} catch (Exception e) {
	//		throw e;
	//	} finally {
	//		sqlSession.close();
	//	}
    //
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
    //
	///**
	// * 注意，此method解註解，須在mapper.java中實作selectDistintXXX方法
	// *可能類似「@Select("select distinct upvirs from amdupf1") List<Amdupf1> selectDistintTypins();」
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	//public List<FriPolicyDtl> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
	//	List<FriPolicyDtl> returnList = null;
	//	
	//	FriPolicyDtlMapper mapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
	//	
	//	returnList = mapper.selectDistint{COL_NM}();
	//	
	//	log.debug(returnList == null ? "returnList is null" : "筆數:" + returnList.size());
	//	return returnList;
	//}
}