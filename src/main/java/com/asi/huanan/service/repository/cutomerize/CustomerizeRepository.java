package com.asi.huanan.service.repository.cutomerize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;
import com.asi.huanan.service.dao.mybatis.model.FriTreaty;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShares;
import com.asi.huanan.service.dao.mybatis.model.customerize.BatchqueueJoinBatchlist;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1101FricomJoinRicmpf1;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1205TableMain;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1302Table;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query2;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303QueryMain;
import com.asi.huanan.vo.Rin1203AVOResp;
import com.asi.huanan.vo.Rin1204VOReq1;
import com.asi.huanan.vo.Rin1204VOReq2;
import com.asi.huanan.vo.Rin1204VOReq3;
import com.asi.huanan.vo.Rin1204VOResp;
import com.asi.huanan.vo.Rin1204VOResp1;
import com.asi.huanan.vo.Rin1204VOResp2;
import com.asi.huanan.vo.Rin1204VOResp3;
import com.asi.huanan.vo.Rin1204VOResp4;
import com.asi.huanan.vo.Rin1204VOResp5;
import com.asi.huanan.vo.Rin1204VOResp6;
import com.asi.huanan.vo.Rin1204VOResp7;
import com.asi.huanan.vo.Rin1204VOResp8;
import com.asi.huanan.vo.Rin1204VOResp9;
import com.asi.huanan.vo.Rin1205QueryVOReq;
import com.asi.huanan.vo.Rin1206PrintModelVO;
import com.asi.huanan.vo.Rin1206QueryConditionVO;
import com.asi.huanan.vo.Rin1206QueryDataVO;
import com.asi.huanan.vo.Rin1303QueryRinComBySlipNoVOResp;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class CustomerizeRepository {

	@Autowired
	private MyBatisConnector mybatis;

	/**
	 * Rin1101A-?????????????????????????????????
	 * 
	 * @param rinComId
	 * @return list FricomJoinRicmpf1
	 * @author yiting 2021/09/29
	 */
	public List<Rin1101FricomJoinRicmpf1> queryOneReiner(String rinComId) throws Exception {

		List<Rin1101FricomJoinRicmpf1> results = new ArrayList<>();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryOneReiner(rinComId);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????????????????_????????????
	 * 
	 * @param treaty_dend_Bgn
	 * @param treaty_dend_End
	 * @return
	 * @throws Exception
	 */
	public List<Rin1302Table> getRin1302MainData(Date treaty_dend_Bgn, Date treaty_dend_End) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1302Table> results = new ArrayList<Rin1302Table>();

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.getRin1302MainData(treaty_dend_Bgn, treaty_dend_End);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	// ?????????????????????_????????????_????????????
	public List<BatchqueueJoinBatchlist> queryUseAccount(String account) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<BatchqueueJoinBatchlist> results = new ArrayList<>();

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryUseAccount(account);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	// ?????????????????????_????????????_????????????
	public List<BatchqueueJoinBatchlist> queryUseAccountAndBatchid(String account, String batchid) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<BatchqueueJoinBatchlist> results = new ArrayList<>();

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryUseAccountAndBatchid(account, batchid);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	// ?????????????????????_????????????_????????????(pdf)
	public List<BatchqueueJoinBatchlist> queryUseAccountAndBatchid2(String account, String batchid) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<BatchqueueJoinBatchlist> results = new ArrayList<>();

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryUseAccountAndBatchid2(account, batchid);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * Rin1203-?????????????????????????????????????????????????????????????????????
	 * 
	 * @param policyDprtS
	 * @param policyDprtE
	 * @return
	 * @throws Exception
	 */
	public int insertToFriTemparea(String policyDprtS, String policyDprtE) throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int results = 0;

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.insertToFriTemparea(policyDprtS, policyDprtE);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * Rin1203-???????????????????????????????????????
	 * 
	 * @return
	 * @throws Exception
	 */
	public int deleteAllFriTemparea() throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int results;

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.deleteAllFriTemparea();
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * Rin1205-??????????????????????????????
	 * 
	 * @param model
	 * 
	 * @return list Rin1205TableMain
	 * @author yiting 2021/11/12
	 */
	public List<Rin1205TableMain> queryRin1205MainData(Rin1205QueryVOReq model, CustomerizeMapper mapper)
			throws Exception {

		List<Rin1205TableMain> results = new ArrayList<>();
		results = mapper.queryRin1205MainData(model);

		return results;
	}

	/**
	 * Rin1203-????????????????????????
	 * 
	 * @param policyDprtS
	 * @param policyDprtE
	 * @param areaCode
	 * @return
	 * @throws Exception
	 */
	public List<Rin1203AVOResp> queryAreaPolicy(String policyDprtS, String policyDprtE, String areaCode)
			throws Exception {

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1203AVOResp> results = new ArrayList<>();

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryAreaPolicy(policyDprtS, policyDprtE, areaCode);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????=99999999999 ???, ?????????????????????????????????????????????
	 * 
	 * @param customerizeMapper
	 * @param ucRocDbgn
	 * @param treatyYear
	 * @return
	 */
	public int deleteOldReinsData999(CustomerizeMapper customerizeMapper, String ucRocDbgn, String treatyYear) {
		int result = 0;
		result = customerizeMapper.deleteOldReinsData999(ucRocDbgn, treatyYear);
		return result;
	}

	/**
	 * ??????????????????, ????????????????????????????????????????????????
	 * 
	 * @param ucRocDbgn
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<FriPolicyDtl> queryReinsBeDeleteData(String ucRocDbgn, String riskNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriPolicyDtl> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryReinsBeDeleteData(ucRocDbgn, riskNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????????????????, ???????????????????????????????????????????????????
	 * 
	 * @param customerizeMapper
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 */
	public int deleteOldReinsDataSpe(CustomerizeMapper customerizeMapper, String policyNo, String endorseNo,
			Short addrNo) {
		int result = 0;
		result = customerizeMapper.deleteOldReinsDataSpe(policyNo, endorseNo, addrNo);
		return result;
	}

	/**
	 * ????????????????????????
	 * 
	 * @param treatyYear
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp1> queryTreatyShareOrder(String treatyYear) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp1> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryTreatyShareOrder(treatyYear);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp2> queryShareDetailList(String ucRocDbgn, String ucRocDend, String riskNo)
			throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp2> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryShareDetailList(ucRocDbgn, ucRocDend, riskNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * Rin1303-?????????????????????????????????
	 * 
	 * @param slip_no
	 * @return list Rin1303QueryRinComBySlipNoVOResp
	 * @author yiting 2021/11/29
	 */
	public List<Rin1303QueryRinComBySlipNoVOResp> queryRinComBySlipNo(String slipNo) throws Exception {

		List<Rin1303QueryRinComBySlipNoVOResp> results = new ArrayList<>();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryRinComBySlipNo(slipNo);

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????????????????(????????????!="99999999999")
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp> queryUnProcPolicy1(String ucRocDbgn, String ucRocDend, String riskNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryUnProcPolicy1(ucRocDbgn, ucRocDend, riskNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????????????????(????????????="99999999999")
	 * 
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp> queryUnProcPolicy2(String ucRocDbgn, String ucRocDend) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryUnProcPolicy2(ucRocDbgn, ucRocDend);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ?????????????????????????????????????????????
	 * 
	 * @param riskNo
	 * @param policyDend
	 * @param policyDbgn
	 * @param policyDprt
	 * @param policyNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp3> queryOldPolicyList(String riskNo, String policyDend, String policyDbgn,
			String policyDprt, String policyNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp3> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryOldPolicyList(riskNo, policyDend, policyDbgn, policyDprt, policyNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????(????????????)?????????????????????
	 * 
	 * @param policyNo
	 * @param policyDEND
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp4> queryMoNextPolicy(String policyNo, String policyDEND) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp4> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoNextPolicy(policyNo, policyDEND);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????????????????????????????????????????
	 * 
	 * @param treatyYear
	 * @param riskNo
	 * @param policyDEND
	 * @param policyDBGN
	 * @param policyPRT
	 * @param oldPolicyList
	 * @param oldPolicy
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp5> queryMoShareAmt(String treatyYear, String riskNo, String policyDEND, String policyDBGN,
			String policyPRT, List<String> oldPolicyList, String oldPolicy) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp5> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoShareAmt(treatyYear, riskNo, policyDEND, policyDBGN, policyPRT, oldPolicyList,
					oldPolicy);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????????????? Sum amt, prem of FAC share
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp6> queryFACShare(String policyNo, String endorseNo, Short addrNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp6> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryFACShare(policyNo, endorseNo, addrNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????????????????(FAC_Retain) Sum amt, prem of FAC_Retail share
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<FriFac> queryFACRetain(String policyNo, String endorseNo, Short addrNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriFac> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryFACRetain(policyNo, endorseNo, addrNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????"??????????????????"????????????
	 * 
	 * @param exSlip
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp7> queryExAmt(String exSlip) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp7> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryExAmt(exSlip);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????"??????????????????"???????????? (?????????) % ??????
	 * 
	 * @param exSlip
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp8> queryExShare(String exSlip) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp8> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryExShare(exSlip);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ?????????????????????
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @param treatyYear
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	public List<FriTreatyShares> queryExShare2(String policyNo, String endorseNo, Short addrNo, String treatyYear,
			String riskNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyShares> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryExShare2(policyNo, endorseNo, addrNo, treatyYear, riskNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * update ???????????????
	 * 
	 * @param customerizeMapper
	 * @param rin1204VOReq1
	 * @return
	 */
	public int updateFriTreatyShares(CustomerizeMapper customerizeMapper, Rin1204VOReq1 rin1204VOReq1) {
		int result = 0;
		result = customerizeMapper.updateFriTreatyShares(rin1204VOReq1);
		return result;
	}

	/**
	 * insert ???????????????
	 * 
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		int result = 0;
		result = customerizeMapper.insertFriTreatyShares(rin1204voReq2);
		return result;
	}

	/**
	 * Rin1206??????????????????????????????
	 * 
	 * @param printModelVo
	 * @param customerizeMapper
	 * @return
	 * @throws Exception
	 */
	public List<Rin1206PrintModelVO> getContractDetail(Rin1206PrintModelVO printModelVo,
			CustomerizeMapper customerizeMapper) throws Exception {

		List<Rin1206PrintModelVO> results = new ArrayList<>();

		try {
			results = customerizeMapper.getContractDetail(printModelVo);
		} catch (Exception e) {
			throw e;
		}

		return results;
	}

	//???????????????????????????
	public List<Rin1206PrintModelVO> CreateRincom(Rin1206PrintModelVO printModelVo,
			CustomerizeMapper customerizeMapper) throws Exception{
		List<Rin1206PrintModelVO> results = new ArrayList<>();

		try {
			results = customerizeMapper.CreateRincom(printModelVo);
		} catch (Exception e) {
			throw e;
		}

		return results;
	}
	
	public List<Rin1206PrintModelVO> CreateRincom2(Rin1206PrintModelVO printModelVo,
	CustomerizeMapper customerizeMapper) throws Exception{
		List<Rin1206PrintModelVO> results = new ArrayList<>();

		try {
			results = customerizeMapper.CreateRincom(printModelVo);
		} catch (Exception e) {
			throw e;
		}

		return results;
	}
	//Rin1206?????????????????????????????????-??????
	public List<Rin1206QueryDataVO> QueryData1(Rin1206QueryConditionVO queryDataVO,
			CustomerizeMapper customerizeMapper) throws Exception{
		List<Rin1206QueryDataVO> results = new ArrayList<>();
		
		try {
			results = customerizeMapper.QueryData1(queryDataVO);
		} catch (Exception e) {
			throw e;
		}
		
		return results;
	}
	
	//Rin1206?????????????????????????????????-??????
	public List<Rin1206QueryDataVO> QueryData2(Rin1206QueryConditionVO queryDataVO,
			CustomerizeMapper customerizeMapper) throws Exception{
		List<Rin1206QueryDataVO> results = new ArrayList<>();
		
		try {
			results = customerizeMapper.QueryData1(queryDataVO);
		} catch (Exception e) {
			throw e;
		}
		
		return results;
	}
	
	//get treatyMode
	public int getTreatyMode(String treatyYear, String treatyNo, CustomerizeMapper customerizeMapper) throws Exception{
		int treatyMode = 0;
		
		try {
			treatyMode = customerizeMapper.getTreatyMode(treatyYear, treatyNo);
		} catch (Exception e) {
			throw e;
		}
		
		return treatyMode;
	}
	/**
	 * Query PolDtl
	 * 
	 * @param policyNo
	 * @param addrNoOri
	 * @return
	 * @throws Exception
	 */
	public List<Rin1204VOResp> queryMoTemp(String policyNo, Short addrNoOri) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoTemp(policyNo, addrNoOri);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * Query Policy Shares
	 * 
	 * @param policyNo
	 * @param endorseNo
	 * @param addrNo
	 * @return
	 * @throws Exception
	 */
	public List<FriTreatyShares> queryMoCancel(String policyNo, String endorseNo, String addrNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyShares> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoCancel(policyNo, endorseNo, addrNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * Rin1303-????????????????????????????????????
	 * 
	 * @param cessionNo
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303QueryMain
	 * @author yiting 2021/12/06
	 */
	public List<Rin1303QueryMain> queryRin1303PrintMain(String cessionNo, String slipNo, String rinComId,
			CustomerizeMapper mapper) throws Exception {

		List<Rin1303QueryMain> results = new ArrayList<>();
		results = mapper.queryRin1303PrintMain(cessionNo, slipNo, rinComId);

		return results;
	}

	/**
	 * Rin1303-??????????????????????????????????????????
	 * 
	 * @param slipNo
	 * @param rinComId
	 * @return list Rin1303Query2
	 * @author yiting 2021/12/06
	 */
	public List<Rin1303Query2> queryRin1303Print2(String slipNo, String rinComId, CustomerizeMapper mapper)
			throws Exception {

		List<Rin1303Query2> results = new ArrayList<>();
		results = mapper.queryRin1303Print2(slipNo, rinComId);

		return results;
	}

	/**
	 * insert ???????????????2
	 * 
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares2(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		int result = 0;
		result = customerizeMapper.insertFriTreatyShares2(rin1204voReq2);
		return result;
	}

	/**
	 * Get Treaty Share Order
	 * @param treatyYear
	 * @param policyType
	 * @return
	 * @throws Exception 
	 */
	public List<FriTreaty> querymMoRetain(String treatyYear, String policyType) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreaty> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.querymMoRetain(treatyYear, policyType);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????????????????
	 * @param riskNo
	 * @param treatyYear
	 * @param policyDEND
	 * @param policyDBGN
	 * @param policyPRT
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp9> queryMinLimit(String riskNo, String treatyYear, String policyDEND, String policyDBGN,
			String policyPRT) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp9> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMinLimit(riskNo, treatyYear, policyDEND, policyDBGN, policyPRT);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????????????????????????????
	 * @param treatyYear
	 * @param riskNo
	 * @param policyDEND
	 * @param policyDBGN
	 * @param policyPRT
	 * @param oldPolicyList
	 * @param oldPolicy
	 * @param nextPolicy
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt2(String treatyYear, String riskNo, String policyDEND, String policyDBGN,
			String policyPRT, List<String> oldPolicyList, String oldPolicy, String nextPolicy) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp5> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoShareAmt2(treatyYear, riskNo, policyDEND, policyDBGN, policyPRT, oldPolicyList,
					oldPolicy, nextPolicy);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ?????????????????????
	 * @param msDpolicyNo
	 * @param msDendorseNo
	 * @param msDaddrNo
	 * @param treatyYear
	 * @param msOtreatyNo
	 * @return
	 * @throws Exception 
	 */
	public List<FriTreatyShares> billedCheck(String msDpolicyNo, String msDendorseNo, Short msDaddrNo,
			String treatyYear, String msOtreatyNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<FriTreatyShares> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.billedCheck(msDpolicyNo, msDendorseNo, msDaddrNo, treatyYear, msOtreatyNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * Insert Treaty Share --[Retain]
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares3(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		int result = 0;
		result = customerizeMapper.insertFriTreatyShares3(rin1204voReq2);
		return result;
	}

	/**
	 * ????????????????????? (???????????????)
	 * @param rin1204voReq3
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt3(Rin1204VOReq3 rin1204voReq3) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp5> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoShareAmt3(rin1204voReq3);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * 07-11.?????????????????????
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares4(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		int result = 0;
		result = customerizeMapper.insertFriTreatyShares4(rin1204voReq2);
		return result;
	}

	/**
	 * [5.5.3.1.3]?????????2??????(SEC_Retain)
	 * @param customerizeMapper
	 * @param rin1204voReq2
	 * @return
	 */
	public int insertFriTreatyShares5(CustomerizeMapper customerizeMapper, Rin1204VOReq2 rin1204voReq2) {
		int result = 0;
		result = customerizeMapper.insertFriTreatyShares5(rin1204voReq2);
		return result;
	}

	/**
	 * ????????????????????????1
	 * @param treatyYear
	 * @param policyType
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp1> queryMoTreatyOrder(String treatyYear, String policyType) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp1> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoTreatyOrder(treatyYear, policyType);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ??????????????????????????????
	 * @param rin1204voReq3
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt4(Rin1204VOReq3 rin1204voReq3) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp5> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoShareAmt4(rin1204voReq3);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????????????????
	 * @param rin1204voReq3
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp5> queryMoShareAmt5(Rin1204VOReq3 rin1204voReq3) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp5> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryMoShareAmt5(rin1204voReq3);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ????????????QS?????????
	 * @param treatyYear
	 * @param policyType
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp8> querymMoRetain2(String treatyYear, String policyType) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp8> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.querymMoRetain2(treatyYear, policyType);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}

	/**
	 * ???????????????????????????
	 * @param ucRocDbgn
	 * @param ucRocDend
	 * @param riskNo
	 * @return
	 * @throws Exception 
	 */
	public List<Rin1204VOResp> checkIsClose(String ucRocDbgn, String ucRocDend, String riskNo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1204VOResp> results = new ArrayList<>();
		try {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.checkIsClose(ucRocDbgn, ucRocDend, riskNo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
}
