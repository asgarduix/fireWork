package com.asi.huanan.service.customerize;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.ComAutonumService;
import com.asi.huanan.service.FriComService;
import com.asi.huanan.service.FriFacBrokerService;
import com.asi.huanan.service.FriFacExcludeService;
import com.asi.huanan.service.FriFacNpropService;
import com.asi.huanan.service.FriFacPolicyKeyService;
import com.asi.huanan.service.FriFacRateService;
import com.asi.huanan.service.FriFacRincomService;
import com.asi.huanan.service.FriFacSameriskService;
import com.asi.huanan.service.FriFacService;
import com.asi.huanan.service.FriFacSharesService;
import com.asi.huanan.service.FriFacSlipriskService;
import com.asi.huanan.service.LogFriFacExcludeKeyService;
import com.asi.huanan.service.LogFriFacNpropService;
import com.asi.huanan.service.LogFriFacRateService;
import com.asi.huanan.service.LogFriFacSameriskService;
import com.asi.huanan.service.LogFriFacService;
import com.asi.huanan.service.LogFriFacSharesService;
import com.asi.huanan.service.LogFriFacSlipriskKeyService;
import com.asi.huanan.service.dao.mybatis.mapper.ComAutonumMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacBrokerMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacExcludeMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacNpropMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacPolicyMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacRateMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacRincomMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacSameriskMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacSharesMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacSlipriskMapper;
import com.asi.huanan.service.dao.mybatis.model.ComAutonum;
import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.FriFacBroker;
import com.asi.huanan.service.dao.mybatis.model.FriFacExcludeKey;
import com.asi.huanan.service.dao.mybatis.model.FriFacNprop;
import com.asi.huanan.service.dao.mybatis.model.FriFacPolicyKey;
import com.asi.huanan.service.dao.mybatis.model.FriFacRate;
import com.asi.huanan.service.dao.mybatis.model.FriFacRincom;
import com.asi.huanan.service.dao.mybatis.model.FriFacSamerisk;
import com.asi.huanan.service.dao.mybatis.model.FriFacShares;
import com.asi.huanan.service.dao.mybatis.model.FriFacSlipriskKey;
import com.asi.huanan.service.dao.mybatis.model.LogFriFac;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacExcludeKey;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacNprop;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacRate;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSamerisk;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacShares;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacSlipriskKey;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryMainData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryPolicyData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryRinserData;
import com.asi.huanan.service.repository.cutomerize.Rin1301Repository;
import com.asi.huanan.vo.rin1301.Rin1301SameRiskPolEndorseVo;
import com.asi.huanan.vo.rin1301.req.Rin1301AQueryParamVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacBrokerVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacExcludeVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacNpropVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacPolicyVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacRateVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacRinserVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacShareVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301HandleDataVOReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryMainDataVOReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryPolicyDetailVOReq;
import com.asi.huanan.vo.rin1301.res.Rin1301QueryPolicyDetailSubVOResp;
import com.asi.huanan.vo.rin1301.res.Rin1301QueryPolicyDetailVOResp;
import com.asi.huanan.vo.rin1301.res.Rin1301QueryRinserDetailSubVoResp;
import com.asi.huanan.vo.rin1301.res.Rin1301RinMainDataVoResp;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Service
public class Rin1301Service {

	private Log log = LogFactory.getLog(Rin1301Service.class);

	@Autowired
	private Rin1301Repository repository;
	@Autowired
	private MyBatisConnector mybatis;
	@Autowired
	private ComAutonumService comAutonumService;
	@Autowired
	private FriFacSameriskService friFacSameriskService;
	@Autowired
	private FriFacSharesService friFacSharesService;
	@Autowired
	private FriFacExcludeService friFacExcludeService;
	@Autowired
	private FriFacBrokerService friFacBrokerService;
	@Autowired
	private FriFacSlipriskService friFacSlipriskService;
	@Autowired
	private FriFacRincomService friFacRincomService;
	@Autowired
	private FriFacRateService friFacRateService;
	@Autowired
	private FriFacNpropService friFacNpropService;
	@Autowired
	private FriFacPolicyKeyService friFacPolicyService;
	@Autowired
	private FriFacService friFacService;
	@Autowired
	private LogFriFacSameriskService logFriFacSameriskService;
	@Autowired
	private LogFriFacService logFriFacService;
	@Autowired
	private LogFriFacExcludeKeyService logFriFacExcludeService;
	@Autowired
	private LogFriFacNpropService logFriFacNpropService;
	@Autowired
	private LogFriFacRateService logFriFacRateService;
	@Autowired
	private LogFriFacSharesService logFriFacSharesService;
	@Autowired
	private LogFriFacSlipriskKeyService logFriFacSlipriskKeyService;
	@Autowired
	private FriComService friComService;

	// ===== 針對使用自訂SQL =====
	/**
	 * 臨分資料維護主頁查詢<br>
	 * 查詢條件為保單號、合約編號、知會/更正號擇一
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryMainData> queryFacPolicy(Rin1301QueryMainDataVOReq model) throws Exception {
		return repository.queryFacPolicy(model);
	}

	/**
	 * 臨分資料維護主頁查詢<br>
	 * 查詢條件為未列印及暫緩或不轉之臨分期間(時間區間)
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryMainData> queryFacPrintAccount(Rin1301QueryMainDataVOReq model) throws Exception {
		return repository.queryFacPrintAccount(model);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetailBySameRiskCode(Rin1301AQueryParamVoReq model)
			throws Exception {
		return repository.queryPolicyDetailBySameRiskCode(model);
	}

	public List<Rin1301QueryPolicyData> checkPolEndorseByOtherCessionUse(List<Rin1301QueryPolicyDetailVOReq> voList)
			throws Exception {
		return repository.checkPolEndorseByOtherCessionUse(voList);
	}

	/**
	 * 臨分資料維護內頁之「保單基本資料」明細1查詢<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail1(List<Rin1301QueryPolicyDetailVOReq> voList)
			throws Exception {
		return repository.queryPolicyDetail1(voList);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail1ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		return repository.queryPolicyDetail1ByEdit(vo);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail1WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo)
			throws Exception {
		return repository.queryPolicyDetail1WithLogSeqByEdit(vo);
	}

	/**
	 * 臨分資料維護內頁之「保單基本資料」明細2查詢<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail2(List<Rin1301QueryPolicyDetailVOReq> voList)
			throws Exception {
		return repository.queryPolicyDetail2(voList);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail2ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		return repository.queryPolicyDetail2ByEdit(vo);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail2WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo)
			throws Exception {
		return repository.queryPolicyDetail2WithLogSeqByEdit(vo);
	}

	/**
	 * 臨分資料維護內頁之「保單基本資料」明細3查詢<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail3(List<Rin1301QueryPolicyDetailVOReq> voList)
			throws Exception {
		return repository.queryPolicyDetail3(voList);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail3ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		return repository.queryPolicyDetail3ByEdit(vo);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail3WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo)
			throws Exception {
		return repository.queryPolicyDetail3WithLogSeqByEdit(vo);
	}

	/**
	 * 臨分資料維護內頁之「保單基本資料」明細4查詢<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail4(List<Rin1301QueryPolicyDetailVOReq> voList)
			throws Exception {
		return repository.queryPolicyDetail4(voList);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail4ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		return repository.queryPolicyDetail4ByEdit(vo);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail4WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo)
			throws Exception {
		return repository.queryPolicyDetail4WithLogSeqByEdit(vo);
	}

	/**
	 * 臨分資料維護內頁之「保單基本資料」明細5查詢<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail5(List<Rin1301QueryPolicyDetailVOReq> voList)
			throws Exception {
		return repository.queryPolicyDetail5(voList);
	}

	/**
	 * 臨分資料維護內頁之「分保計算及說明」計算前資料<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail10(List<Rin1301QueryPolicyDetailVOReq> voList)
			throws Exception {
		return repository.queryPolicyDetail10(voList);
	}

	/**
	 * 臨分資料維護內頁之「分保計算及說明」計算後資料<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail10ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		return repository.queryPolicyDetail10ByEdit(vo);
	}

	public List<Rin1301QueryPolicyData> queryPolicyDetail10WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo)
			throws Exception {
		return repository.queryPolicyDetail10WithLogSeqByEdit(vo);
	}

	/**
	 * 臨分資料維護內頁之「再保人資料」再保人明細查詢<br>
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryRinserData> queryRinserDetail(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		return repository.queryRinserDetail(vo);
	}

	public List<Rin1301QueryRinserData> queryRinserDetailForLogSeq(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		return repository.queryRinserDetailForLogSeq(vo);
	}

	// ==================== 商業邏輯處理區 ====================

	public JsonBean queryPolicyDetail(List<Rin1301QueryPolicyDetailVOReq> params) throws Exception {
		var jsonBean = new JsonBean();
		var resVo = new Rin1301QueryPolicyDetailVOResp();
		// lsvPolicy1
		List<Rin1301QueryPolicyData> lsvPolicy1Results = this.queryPolicyDetail1(params);

		if (lsvPolicy1Results == null || lsvPolicy1Results.isEmpty()) {
			jsonBean.setMessage("查無保批單資料");
			jsonBean.setStatus(SysEnum.status001.code);
			return jsonBean;
		}

		List<String> msgList = lsvPolicy1Results.stream()
				.filter(result -> StringUtils.equals(result.getEndReason(), "R")).map(item -> {
					var errorMsg = "";
					errorMsg += "全退保單" + item.getPolicyNo() + "(批改事由='R') 不可與其他批改事由保單共同作業\n";
					return errorMsg;
				}).collect(Collectors.toList());

		if (msgList != null && !msgList.isEmpty()) {
			msgList.forEach(msg -> {
				var errorMsg = "";
				errorMsg += msg;
				jsonBean.setMessage(errorMsg);
				jsonBean.setStatus(SysEnum.status001.code);
			});
			return jsonBean;
		}

		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail1 = lsvPolicy1Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy1(policyDetail1);

		// lsvPolicy2
		List<Rin1301QueryPolicyData> lsvPolicy2Results = this.queryPolicyDetail2(params);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail2 = lsvPolicy2Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy2(policyDetail2);

		// lsvPolicy3
		List<Rin1301QueryPolicyData> lsvPolicy3Results = this.queryPolicyDetail3(params);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail3 = lsvPolicy3Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy3(policyDetail3);

		// lsvPolicy4
		List<Rin1301QueryPolicyData> lsvPolicy4Results = this.queryPolicyDetail4(params);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail4 = lsvPolicy4Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy4(policyDetail4);

		// lsvPolicy6
		List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy6 = new ArrayList<>();
		lsvPolicy4Results.stream().collect(Collectors.groupingBy(Rin1301QueryPolicyData::getPropertyNo))
				.forEach((k, v) -> {
					var vo = new Rin1301QueryPolicyDetailSubVOResp();
					vo.setPropertyNo(k);
					vo.setPropertyName(v.get(0).getPropertyEname());
					Optional<BigDecimal> sumAmtCoins = v.stream().map(Rin1301QueryPolicyData::getCoinsAmt)
							.reduce((total, item) -> total.add(item));
					vo.setAmt(sumAmtCoins.orElse(BigDecimal.ZERO));
					lsvPolicy6.add(vo);
				});

		resVo.setLsvPolicy6(lsvPolicy6);

		// lsvPolicy7
		List<Rin1301QueryPolicyData> lsvPolicy7Results = this.queryPolicyDetail5(params);
		List<Rin1301QueryPolicyDetailSubVOResp> lsvPolicy7 = new ArrayList<>();
		lsvPolicy7Results.stream().collect(Collectors.groupingBy(Rin1301QueryPolicyData::getContent))
				.forEach((k, v) -> {
					var vo = new Rin1301QueryPolicyDetailSubVOResp();
					vo.setContent(k);
					// 共保保額
					Optional<BigDecimal> sumAmtCoins = v.stream()
							.filter(item -> StringUtils.equals(item.getIncludeAmt(), "Y"))
							.map(Rin1301QueryPolicyData::getCoinsAmt).reduce((total, item) -> total.add(item));
					vo.setAmt(sumAmtCoins.orElse(BigDecimal.ZERO));
					// 共保保費
					Optional<BigDecimal> sumPremCoins = v.stream()
							.filter(item -> StringUtils.equals(item.getIncludePrem(), "Y"))
							.map(Rin1301QueryPolicyData::getCoinsPrem).reduce((total, item) -> total.add(item));
					vo.setPrem(sumPremCoins.orElse(BigDecimal.ZERO));

					// 佣金
					Optional<BigDecimal> sumComm = v.stream()
							.filter(item -> StringUtils.equals(item.getIncludePrem(), "Y"))
							.map(Rin1301QueryPolicyData::getComm).reduce((total, item) -> total.add(item));

					// 佣金率
					if (sumPremCoins.isPresent() && sumPremCoins.get().compareTo(BigDecimal.ZERO) != 0) {
						vo.setCommRate(
								sumComm.orElse(BigDecimal.ZERO).divide(sumPremCoins.get(), 2, RoundingMode.HALF_UP));

					} else {
						vo.setCommRate(BigDecimal.ZERO);
					}

					// 費率
					if (sumAmtCoins.orElse(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0) {
						vo.setPremRate(BigDecimal.ZERO);
					} else {
						vo.setPremRate(sumPremCoins.orElse(BigDecimal.ZERO)
								.divide(sumAmtCoins.orElse(BigDecimal.ZERO), 8, RoundingMode.HALF_UP)
								.multiply(new BigDecimal("1000")));
					}

					// 臨分保費
//					Optional<BigDecimal> sumFacPremCoins = v.stream()
//							.filter(item -> StringUtils.equals(item.getIncludePrem(), "Y")).map(item -> {
//								BigDecimal facdays = BigDecimal.ONE;
//								BigDecimal coinsPrem = item.getCoinsPrem() == null ? BigDecimal.ZERO
//										: item.getCoinsPrem();
//								if (item.getPolicyDend() != null && item.getPolicyDbgn() != null) {
//									if (item.getPolicyDend().getTime() - item.getPolicyDbgn().getTime() != 0) {
//										var datediff = (item.getPolicyDend().getTime() - item.getPolicyDbgn().getTime())
//												/ (24 * 60 * 60 * 1000);
//										facdays = new BigDecimal(params.get(0).getDays())
//												.divide(new BigDecimal(datediff), 4, RoundingMode.HALF_UP);
//									}
//								}
//								return coinsPrem.multiply(facdays);
//							}).reduce((total, item) -> total.add(item));

					vo.setFacPrem(sumPremCoins.orElse(BigDecimal.ZERO));

					// 臨分費率
//					if (sumAmtCoins.orElse(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0) {
//						vo.setFacPremRate(BigDecimal.ZERO);
//					} else {
//						vo.setFacPremRate(sumFacPremCoins.orElse(BigDecimal.ZERO)
//								.divide(sumAmtCoins.orElse(BigDecimal.ZERO), 8, RoundingMode.HALF_UP)
//								.multiply(new BigDecimal("1000")));
//					}
					if (sumAmtCoins.orElse(BigDecimal.ZERO).compareTo(BigDecimal.ZERO) == 0) {
						vo.setFacPremRate(BigDecimal.ZERO);
					} else {
						vo.setFacPremRate(sumPremCoins.orElse(BigDecimal.ZERO)
								.divide(sumAmtCoins.orElse(BigDecimal.ZERO), 8, RoundingMode.HALF_UP)
								.multiply(new BigDecimal("1000")));
					}

					lsvPolicy7.add(vo);
				});

		resVo.setLsvPolicy7(lsvPolicy7);

		// lsvPolicy10
		List<Rin1301QueryPolicyData> lsvPolicy10Results = this.queryPolicyDetail10(params);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail10 = lsvPolicy10Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy10(policyDetail10);

		jsonBean.setData(resVo);
		jsonBean.setStatus(SysEnum.statusSuccess.code);
		return jsonBean;

	}

	public JsonBean createRinData(Rin1301HandleDataVOReq param) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		var jsonBean = new JsonBean();
		try {

			String slipNo = "";
			String cessionNo = "";
			// 取號
			ComAutonumMapper comAutonumMapper = sqlSession.getMapper(ComAutonumMapper.class);

			var slipModel = new ComAutonum();
			slipModel.setCompanyCode("");
			slipModel.setFunctionId("1301");
			slipModel.setNumberCode("FAC");
			slipModel.setNumYear(param.getSlipNoGenYear());
			slipModel.setNumMonth("");
			slipModel.setNumDay("");
			slipModel.setOperateType("Slip");
			var cessionModel = new ComAutonum();
			cessionModel.setCompanyCode("");
			cessionModel.setFunctionId("1301");
			cessionModel.setNumberCode("F");
			cessionModel.setNumYear(param.getTreatyYear());
			cessionModel.setNumMonth("");
			cessionModel.setNumDay("");
			cessionModel.setOperateType("Cesn");

			if (StringUtils.equals(param.getHandleMode(), "1") || StringUtils.equals(param.getHandleMode(), "4")) {
				slipNo += this.genNumber(comAutonumMapper, slipModel);
				cessionNo += this.genNumber(comAutonumMapper, cessionModel);
				param.setSlipNo(slipNo);
				param.setCessionNo(cessionNo);
			} else if (StringUtils.equals(param.getHandleMode(), "3")) {
				slipNo += this.genNumber(comAutonumMapper, slipModel);
				cessionNo = param.getCessionNo();
				param.setSlipNo(slipNo);
			} else {
				jsonBean.setMessage("操作選項不符合");
				jsonBean.setStatus(SysEnum.status001.code);
				return jsonBean;
			}

			// fri_fac_samerisk
			var friFacSameriskVo = param.getSameriskPolEndorObj();
			var friFacSameriskModel = new FriFacSamerisk();
			BeanUtils.copyProperties(friFacSameriskVo, friFacSameriskModel);
			friFacSameriskModel.setSlipNo(slipNo);
			FriFacSameriskMapper friFacSameriskMapper = sqlSession.getMapper(FriFacSameriskMapper.class);
			friFacSameriskService.insert(friFacSameriskModel, friFacSameriskMapper);

			// fri_fac_sliprisk
			if (param.getFacSlipRiskVoList() != null && !param.getFacSlipRiskVoList().isEmpty()) {
				FriFacSlipriskMapper friFacSlipriskMapper = sqlSession.getMapper(FriFacSlipriskMapper.class);
				friFacSlipriskService.insertByQueryPolicy(param, friFacSlipriskMapper);
			}

			// fri_fac
			FriFacMapper friFacMapper = sqlSession.getMapper(FriFacMapper.class);
			var friFacModel = new FriFac();
			BeanUtils.copyProperties(param, friFacModel);
			// 原系統程式碼
			// appendPutData "acct_flag", "N"
			// appendPutData "acct_dprt", ""
			// appendPutData "treaty_dprt", ""

			friFacModel.setLastupdatedatetime(new Date());
			friFacModel.setLogSeq("00");
			friFacModel.setAcctFlag("N");
			friFacModel.setLimit(0L);
			friFacModel.setLimitRate(BigDecimal.ZERO);
			friFacService.insert(friFacModel, friFacMapper);

			// fri_fac_policy
			List<Rin1301FacPolicyVoReq> facPolicyVoList = param.getFacPolicyVoList();
			FriFacPolicyMapper friFacPolicyMapper = sqlSession.getMapper(FriFacPolicyMapper.class);
			for (Rin1301FacPolicyVoReq vo : facPolicyVoList) {
				var friFacPolicyModel = new FriFacPolicyKey();
				BeanUtils.copyProperties(vo, friFacPolicyModel);
				friFacPolicyModel.setSlipNo(slipNo);
				friFacPolicyService.insert(friFacPolicyModel, friFacPolicyMapper);
			}

			// fri_fac_nprop
			List<Rin1301FacNpropVoReq> facNpropVoList = param.getFacNpropVoList();
			FriFacNpropMapper friFacNpropMapper = sqlSession.getMapper(FriFacNpropMapper.class);
			for (Rin1301FacNpropVoReq vo : facNpropVoList) {
				var friFacNpropModel = new FriFacNprop();
				BeanUtils.copyProperties(vo, friFacNpropModel);
				friFacNpropModel.setSlipNo(slipNo);
				friFacNpropService.insert(friFacNpropModel, friFacNpropMapper);
			}

			// fri_fac_rate
			List<Rin1301FacRateVoReq> facRateVoList = param.getFacRateVoList();
			FriFacRateMapper friFacRateMapper = sqlSession.getMapper(FriFacRateMapper.class);
			for (Rin1301FacRateVoReq vo : facRateVoList) {
				var friFacRateModel = new FriFacRate();
				BeanUtils.copyProperties(vo, friFacRateModel);
				friFacRateModel.setSlipNo(slipNo);
				friFacRateService.insert(friFacRateModel, friFacRateMapper);
			}

			// fri_fac_rincom
			List<Rin1301FacRinserVoReq> facRinserVoList = param.getFacRinserVoList();
			FriFacRincomMapper friFacRincomMapper = sqlSession.getMapper(FriFacRincomMapper.class);
			for (Rin1301FacRinserVoReq vo : facRinserVoList) {
				if(vo.getOrgcomm()==null) {
					vo.setOrgcomm(BigDecimal.ZERO);
				}
				if(vo.getOrgprem()==null) {
					vo.setOrgprem(BigDecimal.ZERO);
				}
				
				if(vo.getOrgtax()==null) {
					vo.setOrgtax(BigDecimal.ZERO);
				}
				var friFacRincomModel = new FriFacRincom();
				BeanUtils.copyProperties(vo, friFacRincomModel);
				friFacRincomModel.setHandlingRate(BigDecimal.ZERO);
				friFacRincomModel.setBrokerRate(BigDecimal.ZERO);
				friFacRincomModel.setDiscountRate(BigDecimal.ZERO);
				friFacRincomModel.setAcctFlag("N");
				friFacRincomModel.setSlipNo(slipNo);
				friFacRincomService.insert(friFacRincomModel, friFacRincomMapper);
			}

			// fri_fac_broker
			List<Rin1301FacBrokerVoReq> facBrokerVoList = param.getFacBrokerVoList();
			FriFacBrokerMapper friFacBrokerMapper = sqlSession.getMapper(FriFacBrokerMapper.class);
			for (Rin1301FacBrokerVoReq vo : facBrokerVoList) {
				var friFacBrokerModel = new FriFacBroker();
				BeanUtils.copyProperties(vo, friFacBrokerModel);
				friFacBrokerModel.setSlipNo(slipNo);
				friFacBrokerService.insert(friFacBrokerModel, friFacBrokerMapper);
			}

			// fri_fac_exclude
			List<Rin1301FacExcludeVoReq> facExcludeVoList = param.getFacExcludeVoList();
			FriFacExcludeMapper friFacExcludeMapper = sqlSession.getMapper(FriFacExcludeMapper.class);
			for (Rin1301FacExcludeVoReq vo : facExcludeVoList) {
				var friFacExcludeModel = new FriFacExcludeKey();
				BeanUtils.copyProperties(vo, friFacExcludeModel);
				friFacExcludeModel.setSlipNo(slipNo);
				friFacExcludeService.insert(friFacExcludeModel, friFacExcludeMapper);
			}

			// fri_fac_shares
			List<Rin1301FacShareVoReq> facShareVoList = param.getFacShareVoList();
			FriFacSharesMapper friFacSharesMapper = sqlSession.getMapper(FriFacSharesMapper.class);
			for (Rin1301FacShareVoReq vo : facShareVoList) {
				var friFacSharesModel = new FriFacShares();
				BeanUtils.copyProperties(vo, friFacSharesModel);
				friFacSharesModel.setSlipNo(slipNo);
				friFacSharesService.insert(friFacSharesModel, friFacSharesMapper);
			}

			sqlSession.commit();
			Map<String, String> result = new HashMap<>();
			result.put("slipNo", slipNo);
			result.put("cessionNo", cessionNo);
			jsonBean.setData(result);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			return jsonBean;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

	}

	public String genNumber(ComAutonumMapper mapper, ComAutonum model) throws Exception {

		Integer serial = 0;
		String autoNo = "";

		serial = comAutonumService.selectMaxSerial(mapper, model);
		if (serial == null || serial.compareTo(0) == 0) {
			model.setSerial(1);
			comAutonumService.insert(model, mapper);
		} else {
			model.setSerial(serial + 1);
			comAutonumService.update(model, mapper);
		}
		serial = comAutonumService.selectMaxSerial(mapper, model);

		autoNo += model.getNumYear();

		switch (model.getOperateType()) {

		case "Slip":
			autoNo += model.getNumberCode();
			if (serial < 10) {
				autoNo += "000" + serial;
			} else if (serial < 100) {
				autoNo += "00" + serial;
			} else if (serial < 1000) {
				autoNo += "0" + serial;
			} else {
				autoNo += serial;
			}
			break;
		case "Cesn":
			autoNo += model.getNumberCode() + "F";
			if (serial < 10) {
				autoNo += "00" + serial;
			} else if (serial < 100) {
				autoNo += "0" + serial;
			} else {
				autoNo += serial;
			}
			break;
		}
		return autoNo;

	}

	public JsonBean queryRinData(Rin1301QueryPolicyDetailVOReq param) throws Exception {
		var jsonBean = new JsonBean();
		var resVo = new Rin1301QueryPolicyDetailVOResp();

		// fri_fac
		var friFacModel = new FriFac();
		var firFacVo = new Rin1301RinMainDataVoResp();
		friFacModel.setSlipNo(param.getSlipNo());
		List<FriFac> friFacResult = friFacService.queryByFriFac(friFacModel);
		if (friFacResult != null && !friFacResult.isEmpty()) {
			BeanUtils.copyProperties(friFacResult.get(0), firFacVo);
		}
		resVo.setMainData(firFacVo);

		// fri_fac_samerisk
		var friFacSameriskModel = new FriFacSamerisk();
		var friFacSameriskVo = new Rin1301SameRiskPolEndorseVo();
		friFacSameriskModel.setSlipNo(param.getSlipNo());
		List<FriFacSamerisk> friFacSameriskResult = friFacSameriskService.queryByFriFacSamerisk(friFacSameriskModel);
		if (friFacSameriskResult != null && !friFacSameriskResult.isEmpty()) {
			BeanUtils.copyProperties(friFacSameriskResult.get(0), friFacSameriskVo);
		}

		resVo.setSameRiskPolEndorseData(friFacSameriskVo);

		// fri_fac_exclude
		var friFacExcludeModel = new FriFacExcludeKey();
		friFacExcludeModel.setSlipNo(param.getSlipNo());
		List<FriFacExcludeKey> friFacExcludeResults = friFacExcludeService.queryByFriFacExcludeKey(friFacExcludeModel);
		Set<String> friFacExcludeSet = friFacExcludeResults.stream()
				.map(result -> StringUtils.split(result.getContent(), ".")[0]).collect(Collectors.toSet());
		resVo.getMainData().setExcludeSet(friFacExcludeSet);

		// lsvPolicy1
		List<Rin1301QueryPolicyData> lsvPolicy1Results = this.queryPolicyDetail1ByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail1 = lsvPolicy1Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy1(policyDetail1);

		// lsvPolicy2
		List<Rin1301QueryPolicyData> lsvPolicy2Results = this.queryPolicyDetail2ByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail2 = lsvPolicy2Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy2(policyDetail2);

		// lsvPolicy3
		List<Rin1301QueryPolicyData> lsvPolicy3Results = this.queryPolicyDetail3ByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail3 = lsvPolicy3Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy3(policyDetail3);

		// lsvPolicy4

		List<Rin1301QueryPolicyData> lsvPolicy4Results = this.queryPolicyDetail4ByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail4 = lsvPolicy4Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy4(policyDetail4);

		// lsvPolicy6
		var friFacNpropModel = new FriFacNprop();
		friFacNpropModel.setSlipNo(param.getSlipNo());
		List<FriFacNprop> lsvPolicy6Results = friFacNpropService.queryByFriFacNprop(friFacNpropModel);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail6 = lsvPolicy6Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			targetBean.setAmt(result.getAmt() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmt()));
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy6(policyDetail6);

		// lsvPolicy7
		var friFacRateModel = new FriFacRate();
		friFacRateModel.setSlipNo(param.getSlipNo());
		List<FriFacRate> lsvPolicy7Results = friFacRateService.queryByFriFacRate(friFacRateModel);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail7 = lsvPolicy7Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			if (StringUtils.isNotBlank(targetBean.getContent())) {
				targetBean.setContent(StringUtils.split(targetBean.getContent(), ".")[0]);
			}
			targetBean.setAmt(result.getAmt() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmt()));
			targetBean.setPrem(result.getPrem() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPrem()));
			targetBean.setFacPrem(
					result.getFacPrem() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getFacPrem()));
			targetBean.setLimit(result.getLimit() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getLimit()));
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy7(policyDetail7);

		// lsvPolicy10
		List<Rin1301QueryPolicyData> lsvPolicy10Results = this.queryPolicyDetail10ByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail10 = lsvPolicy10Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy10(policyDetail10);

		// facSharesDetail
		var friFacSharesModel = new FriFacShares();
		friFacSharesModel.setSlipNo(param.getSlipNo());
		List<FriFacShares> facSharesResults = friFacSharesService.queryByFriFacShares(friFacSharesModel);
		List<Rin1301QueryPolicyDetailSubVOResp> facSharesVoResults = facSharesResults.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);

			targetBean.setAddrNo(String.valueOf(result.getAddrNo()));
			targetBean.setAmt(result.getAmt() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmt()));
			targetBean.setPrem(result.getPrem() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPrem()));
			targetBean.setAmtTyp(result.getAmtTyp() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmtTyp()));
			targetBean.setPremTyp(
					result.getPremTyp() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPremTyp()));
			targetBean.setAmtEar(result.getAmtEar() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmtEar()));
			targetBean.setPremEar(
					result.getPremEar() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPremEar()));
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setFacSharesDetail(facSharesVoResults);

		// slipRiskDetail
		var friFacSlipRiskModel = new FriFacSlipriskKey();
		friFacSlipRiskModel.setSlipNo(param.getSlipNo());
		List<FriFacSlipriskKey> friFacSlipriskResults = friFacSlipriskService
				.queryByFriFacSlipriskKey(friFacSlipRiskModel);
		List<String> friFacSlipriskVoResults = friFacSlipriskResults.stream().map(FriFacSlipriskKey::getRiskNo)
				.collect(Collectors.toList());

		resVo.setSlipRiskDetail(friFacSlipriskVoResults);
		jsonBean.setData(resVo);
		jsonBean.setStatus(SysEnum.statusSuccess.code);
		return jsonBean;

	}

	public JsonBean queryRinDataByLogSeq(Rin1301QueryPolicyDetailVOReq param) throws Exception {
		var jsonBean = new JsonBean();
		var resVo = new Rin1301QueryPolicyDetailVOResp();

		// log_fri_fac
		var logFriFacModel = new LogFriFac();
		var firFacVo = new Rin1301RinMainDataVoResp();
		logFriFacModel.setSlipNo(param.getSlipNo());
		logFriFacModel.setLogSeq(param.getLogSeq());

		List<LogFriFac> friFacResult = logFriFacService.queryByLogFriFac(logFriFacModel);
		if (friFacResult != null && !friFacResult.isEmpty()) {
			BeanUtils.copyProperties(friFacResult.get(0), firFacVo);
		}
		resVo.setMainData(firFacVo);

		// fri_fac_samerisk
		var logFriFacSameriskModel = new LogFriFacSamerisk();
		var friFacSameriskVo = new Rin1301SameRiskPolEndorseVo();
		logFriFacSameriskModel.setSlipNo(param.getSlipNo());
		logFriFacSameriskModel.setLogSeq(param.getLogSeq());

		List<LogFriFacSamerisk> logfriFacSameriskResult = logFriFacSameriskService
				.queryByLogFriFacSamerisk(logFriFacSameriskModel);
		if (logfriFacSameriskResult != null && !logfriFacSameriskResult.isEmpty()) {
			BeanUtils.copyProperties(logfriFacSameriskResult.get(0), friFacSameriskVo);
		}
		resVo.setSameRiskPolEndorseData(friFacSameriskVo);

		// fri_fac_exclude
		var logFriFacExcludeModel = new LogFriFacExcludeKey();
		logFriFacExcludeModel.setSlipNo(param.getSlipNo());
		logFriFacExcludeModel.setLogSeq(param.getLogSeq());
		List<LogFriFacExcludeKey> logFriFacExcludeResults = logFriFacExcludeService
				.queryByLogFriFacExcludeKey(logFriFacExcludeModel);
		Set<String> friFacExcludeSet = logFriFacExcludeResults.stream()
				.map(result -> StringUtils.split(result.getContent(), ".")[0]).collect(Collectors.toSet());
		resVo.getMainData().setExcludeSet(friFacExcludeSet);

		// lsvPolicy1
		List<Rin1301QueryPolicyData> lsvPolicy1Results = this.queryPolicyDetail1WithLogSeqByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail1 = lsvPolicy1Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy1(policyDetail1);

		// lsvPolicy2
		List<Rin1301QueryPolicyData> lsvPolicy2Results = this.queryPolicyDetail2WithLogSeqByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail2 = lsvPolicy2Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy2(policyDetail2);

		// lsvPolicy3
		List<Rin1301QueryPolicyData> lsvPolicy3Results = this.queryPolicyDetail3WithLogSeqByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail3 = lsvPolicy3Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy3(policyDetail3);

		// lsvPolicy4
		List<Rin1301QueryPolicyData> lsvPolicy4Results = this.queryPolicyDetail4WithLogSeqByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail4 = lsvPolicy4Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy4(policyDetail4);

		// lsvPolicy6
		var logFriFacNpropModel = new LogFriFacNprop();
		logFriFacNpropModel.setSlipNo(param.getSlipNo());
		logFriFacNpropModel.setLogSeq(param.getLogSeq());
		List<LogFriFacNprop> lsvPolicy6Results = logFriFacNpropService.queryByLogFriFacNprop(logFriFacNpropModel);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail6 = lsvPolicy6Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			targetBean.setAmt(result.getAmt() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmt()));
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy6(policyDetail6);

		// lsvPolicy7
		var logfriFacRateModel = new LogFriFacRate();
		logfriFacRateModel.setSlipNo(param.getSlipNo());
		logfriFacRateModel.setLogSeq(param.getLogSeq());
		List<LogFriFacRate> lsvPolicy7Results = logFriFacRateService.queryByLogFriFacRate(logfriFacRateModel);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail7 = lsvPolicy7Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			if (StringUtils.isNotBlank(targetBean.getContent())) {
				targetBean.setContent(StringUtils.split(targetBean.getContent(), ".")[0]);
			}
			targetBean.setAmt(result.getAmt() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmt()));
			targetBean.setPrem(result.getPrem() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPrem()));
			targetBean.setFacPrem(
					result.getFacPrem() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getFacPrem()));
			targetBean.setLimit(result.getLimit() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getLimit()));
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy7(policyDetail7);

		// lsvPolicy10
		List<Rin1301QueryPolicyData> lsvPolicy10Results = this.queryPolicyDetail10WithLogSeqByEdit(param);
		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail10 = lsvPolicy10Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setLsvPolicy10(policyDetail10);

		// facSharesDetail
		var logFriFacSharesModel = new LogFriFacShares();
		logFriFacSharesModel.setSlipNo(param.getSlipNo());
		logFriFacSharesModel.setLogSeq(param.getLogSeq());
		List<LogFriFacShares> facSharesResults = logFriFacSharesService.queryByLogFriFacShares(logFriFacSharesModel);
		List<Rin1301QueryPolicyDetailSubVOResp> facSharesVoResults = facSharesResults.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);

			targetBean.setAddrNo(String.valueOf(result.getAddrNo()));
			targetBean.setAmt(result.getAmt() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmt()));
			targetBean.setPrem(result.getPrem() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPrem()));
			targetBean.setAmtTyp(result.getAmtTyp() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmtTyp()));
			targetBean.setPremTyp(
					result.getPremTyp() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPremTyp()));
			targetBean.setAmtEar(result.getAmtEar() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getAmtEar()));
			targetBean.setPremEar(
					result.getPremEar() == null ? BigDecimal.ZERO : BigDecimal.valueOf(result.getPremEar()));
			return targetBean;
		}).collect(Collectors.toList());
		resVo.setFacSharesDetail(facSharesVoResults);

		// slipRiskDetail
		var logFriFacSlipRiskModel = new LogFriFacSlipriskKey();
		logFriFacSlipRiskModel.setSlipNo(param.getSlipNo());
		logFriFacSlipRiskModel.setLogSeq(param.getLogSeq());
		List<LogFriFacSlipriskKey> friFacSlipriskResults = logFriFacSlipriskKeyService
				.queryByLogFriFacSlipriskKey(logFriFacSlipRiskModel);
		List<String> friFacSlipriskVoResults = friFacSlipriskResults.stream().map(LogFriFacSlipriskKey::getRiskNo)
				.collect(Collectors.toList());

		resVo.setSlipRiskDetail(friFacSlipriskVoResults);

		jsonBean.setData(resVo);
		jsonBean.setStatus(SysEnum.statusSuccess.code);
		return jsonBean;

	}

	public JsonBean editRinData(Rin1301HandleDataVOReq param) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		var jsonBean = new JsonBean();
		try {

			String slipNo = "";
			// 取號
			ComAutonumMapper comAutonumMapper = sqlSession.getMapper(ComAutonumMapper.class);

			var slipModel = new ComAutonum();
			slipModel.setCompanyCode("");
			slipModel.setFunctionId("1301");
			slipModel.setNumberCode("FAC");
			slipModel.setNumYear(param.getSlipNoGenYear());
			slipModel.setNumMonth("");
			slipModel.setNumDay("");
			slipModel.setOperateType("Slip");
			slipNo += this.genNumber(comAutonumMapper, slipModel);
			param.setSlipNo(slipNo);
			// fri_fac
			// 原系統程式碼
			// appendPutData "acct_flag", "N"
			// appendPutData "acct_dprt", ""
			// appendPutData "treaty_dprt", ""
			FriFacMapper friFacMapper = sqlSession.getMapper(FriFacMapper.class);
			var friFacModel = new FriFac();
			BeanUtils.copyProperties(param, friFacModel);
			// #todo editRinData log_sql logic
			friFacModel.setLastupdatedatetime(new Date());
			friFacModel.setAcctFlag("N");
			
			// TODO 需要產生新 slip_no, cession_no 待確認
			int count = 0;
			if("02".equals(param.getLogSeq())) {
				count = friFacService.insert(friFacModel, friFacMapper);
			}else {
				count = friFacService.update(friFacModel, friFacMapper);				
			}
			if (count == 0) {
				jsonBean.setMessage("此臨分資料不存在");
				jsonBean.setStatus(SysEnum.status001.code);
				return jsonBean;
			}

			// fri_fac_samerisk
			var friFacSameriskVo = param.getSameriskPolEndorObj();
			var friFacSameriskModel = new FriFacSamerisk();
			FriFacSameriskMapper friFacSameriskMapper = sqlSession.getMapper(FriFacSameriskMapper.class);
			if(!"02".equals(param.getLogSeq())) {
				friFacSameriskService.deleteByKey(param.getSlipNo(), friFacSameriskMapper);
			}
			BeanUtils.copyProperties(friFacSameriskVo, friFacSameriskModel);
			friFacSameriskModel.setSlipNo(param.getSlipNo());
			friFacSameriskService.insert(friFacSameriskModel, friFacSameriskMapper);
			

			// fri_fac_sliprisk
			var facSlipRiskModelForDel = new FriFacSlipriskKey();
			facSlipRiskModelForDel.setSlipNo(param.getSlipNo());
			FriFacSlipriskMapper friFacSlipriskMapper = sqlSession.getMapper(FriFacSlipriskMapper.class);
			if(!"02".equals(param.getLogSeq())) {
				friFacSlipriskService.deleteByFriFacSlipriskKey(facSlipRiskModelForDel, friFacSlipriskMapper);
			}

			if (param.getFacSlipRiskVoList() != null && !param.getFacSlipRiskVoList().isEmpty()) {
				friFacSlipriskService.insertByQueryPolicy(param, friFacSlipriskMapper);
			}

			// fri_fac_policy
			List<Rin1301FacPolicyVoReq> facPolicyVoList = param.getFacPolicyVoList();
			FriFacPolicyMapper friFacPolicyMapper = sqlSession.getMapper(FriFacPolicyMapper.class);
			var friFacPolicyModelForDel = new FriFacPolicyKey();
			friFacPolicyModelForDel.setSlipNo(param.getSlipNo());
			if(!"02".equals(param.getLogSeq())) {
				friFacPolicyService.deleteByFriFacPolicyKey(friFacPolicyModelForDel, friFacPolicyMapper);
			}
			for (Rin1301FacPolicyVoReq vo : facPolicyVoList) {
				var friFacPolicyModel = new FriFacPolicyKey();
				BeanUtils.copyProperties(vo, friFacPolicyModel);
				friFacPolicyModel.setSlipNo(param.getSlipNo());
				friFacPolicyService.insert(friFacPolicyModel, friFacPolicyMapper);
			}

			// fri_fac_nprop
			List<Rin1301FacNpropVoReq> facNpropVoList = param.getFacNpropVoList();
			FriFacNpropMapper friFacNpropMapper = sqlSession.getMapper(FriFacNpropMapper.class);
			var friFacNpropModelForDel = new FriFacNprop();
			friFacNpropModelForDel.setSlipNo(param.getSlipNo());
			if(!"02".equals(param.getLogSeq())) {
				friFacNpropService.deleteByFriFacNprop(friFacNpropModelForDel, friFacNpropMapper);
			}

			for (Rin1301FacNpropVoReq vo : facNpropVoList) {
				var friFacNpropModel = new FriFacNprop();
				BeanUtils.copyProperties(vo, friFacNpropModel);
				friFacNpropModel.setSlipNo(param.getSlipNo());
				friFacNpropService.insert(friFacNpropModel, friFacNpropMapper);
			}

			// fri_fac_rate
			List<Rin1301FacRateVoReq> facRateVoList = param.getFacRateVoList();
			FriFacRateMapper friFacRateMapper = sqlSession.getMapper(FriFacRateMapper.class);
			var friFacRateModelForDel = new FriFacRate();
			friFacRateModelForDel.setSlipNo(param.getSlipNo());
			if(!"02".equals(param.getLogSeq())) {
				friFacRateService.deleteByFriFacRate(friFacRateModelForDel, friFacRateMapper);
			}

			for (Rin1301FacRateVoReq vo : facRateVoList) {
				var friFacRateModel = new FriFacRate();
				BeanUtils.copyProperties(vo, friFacRateModel);
				friFacRateModel.setSlipNo(param.getSlipNo());
				friFacRateService.insert(friFacRateModel, friFacRateMapper);
			}

			// fri_fac_rincom
			List<Rin1301FacRinserVoReq> facRinserVoList = param.getFacRinserVoList();
			FriFacRincomMapper friFacRincomMapper = sqlSession.getMapper(FriFacRincomMapper.class);
			var friFacRincomModelForDel = new FriFacRincom();
			friFacRincomModelForDel.setSlipNo(param.getSlipNo());
			if(!"02".equals(param.getLogSeq())) {
				friFacRincomService.deleteByFriFacRincom(friFacRincomModelForDel, friFacRincomMapper);
			}
			for (Rin1301FacRinserVoReq vo : facRinserVoList) {
				if(vo.getOrgcomm()==null) {
					vo.setOrgcomm(BigDecimal.ZERO);
				}
				if(vo.getOrgprem()==null) {
					vo.setOrgprem(BigDecimal.ZERO);
				}
				
				if(vo.getOrgtax()==null) {
					vo.setOrgtax(BigDecimal.ZERO);
				}
				
				var friFacRincomModel = new FriFacRincom();
				BeanUtils.copyProperties(vo, friFacRincomModel);
				friFacRincomModel.setHandlingRate(BigDecimal.ZERO);
				friFacRincomModel.setBrokerRate(BigDecimal.ZERO);
				friFacRincomModel.setDiscountRate(BigDecimal.ZERO);
				friFacRincomModel.setAcctFlag("N");
				friFacRincomModel.setTransferStatus("N");
				friFacRincomModel.setSlipNo(param.getSlipNo());
				friFacRincomService.insert(friFacRincomModel, friFacRincomMapper);
			}

			// fri_fac_broker
			List<Rin1301FacBrokerVoReq> facBrokerVoList = param.getFacBrokerVoList();
			FriFacBrokerMapper friFacBrokerMapper = sqlSession.getMapper(FriFacBrokerMapper.class);
			var friFacBrokerModelForDel = new FriFacBroker();
			friFacBrokerModelForDel.setSlipNo(param.getSlipNo());
			if(!"02".equals(param.getLogSeq())) {
				friFacBrokerService.deleteByFriFacBroker(friFacBrokerModelForDel, friFacBrokerMapper);
			}
			for (Rin1301FacBrokerVoReq vo : facBrokerVoList) {
				var friFacBrokerModel = new FriFacBroker();
				BeanUtils.copyProperties(vo, friFacBrokerModel);
				friFacBrokerModel.setSlipNo(param.getSlipNo());
				friFacBrokerService.insert(friFacBrokerModel, friFacBrokerMapper);
			}

			// fri_fac_exclude
			List<Rin1301FacExcludeVoReq> facExcludeVoList = param.getFacExcludeVoList();
			FriFacExcludeMapper friFacExcludeMapper = sqlSession.getMapper(FriFacExcludeMapper.class);
			var friFacExcludeModelForDel = new FriFacExcludeKey();
			friFacExcludeModelForDel.setSlipNo(param.getSlipNo());
			if(!"02".equals(param.getLogSeq())) {
				friFacExcludeService.delByFriFacExcludeKey(friFacExcludeModelForDel, friFacExcludeMapper);
			}

			for (Rin1301FacExcludeVoReq vo : facExcludeVoList) {
				var friFacExcludeModel = new FriFacExcludeKey();
				BeanUtils.copyProperties(vo, friFacExcludeModel);
				friFacExcludeModel.setSlipNo(param.getSlipNo());
				friFacExcludeService.insert(friFacExcludeModel, friFacExcludeMapper);
			}

			// fri_fac_shares
			List<Rin1301FacShareVoReq> facShareVoList = param.getFacShareVoList();
			FriFacSharesMapper friFacSharesMapper = sqlSession.getMapper(FriFacSharesMapper.class);
			var friFacSharesModelForDel = new FriFacShares();
			friFacSharesModelForDel.setSlipNo(param.getSlipNo());
			if(!"02".equals(param.getLogSeq())) {
				friFacSharesService.delByFriFacShares(friFacSharesModelForDel, friFacSharesMapper);
			}
			for (Rin1301FacShareVoReq vo : facShareVoList) {
				var friFacSharesModel = new FriFacShares();
				BeanUtils.copyProperties(vo, friFacSharesModel);
				friFacSharesModel.setSlipNo(param.getSlipNo());
				friFacSharesService.insert(friFacSharesModel, friFacSharesMapper);
			}

			sqlSession.commit();
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			return jsonBean;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

	}

	public JsonBean delRinData(Rin1301QueryPolicyDetailVOReq param) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		var jsonBean = new JsonBean();
		try {

			// fri_fac
			FriFacMapper friFacMapper = sqlSession.getMapper(FriFacMapper.class);
			int count = friFacService.deleteByKey(param.getSlipNo(), friFacMapper);
			if (count == 0) {
				jsonBean.setMessage("此臨分資料不存在");
				jsonBean.setStatus(SysEnum.status001.code);
				return jsonBean;
			}

			// fri_fac_samerisk
			FriFacSameriskMapper friFacSameriskMapper = sqlSession.getMapper(FriFacSameriskMapper.class);
			friFacSameriskService.deleteByKey(param.getSlipNo(), friFacSameriskMapper);

			// fri_fac_sliprisk
			var facSlipRiskModelForDel = new FriFacSlipriskKey();
			facSlipRiskModelForDel.setSlipNo(param.getSlipNo());
			FriFacSlipriskMapper friFacSlipriskMapper = sqlSession.getMapper(FriFacSlipriskMapper.class);
			friFacSlipriskService.deleteByFriFacSlipriskKey(facSlipRiskModelForDel, friFacSlipriskMapper);

			// fri_fac_policy
			FriFacPolicyMapper friFacPolicyMapper = sqlSession.getMapper(FriFacPolicyMapper.class);
			var friFacPolicyModelForDel = new FriFacPolicyKey();
			friFacPolicyModelForDel.setSlipNo(param.getSlipNo());
			friFacPolicyService.deleteByFriFacPolicyKey(friFacPolicyModelForDel, friFacPolicyMapper);

			// fri_fac_nprop
			FriFacNpropMapper friFacNpropMapper = sqlSession.getMapper(FriFacNpropMapper.class);
			var friFacNpropModelForDel = new FriFacNprop();
			friFacNpropModelForDel.setSlipNo(param.getSlipNo());
			friFacNpropService.deleteByFriFacNprop(friFacNpropModelForDel, friFacNpropMapper);

			// fri_fac_rate
			FriFacRateMapper friFacRateMapper = sqlSession.getMapper(FriFacRateMapper.class);
			var friFacRateModelForDel = new FriFacRate();
			friFacRateModelForDel.setSlipNo(param.getSlipNo());
			friFacRateService.deleteByFriFacRate(friFacRateModelForDel, friFacRateMapper);

			// fri_fac_rincom
			FriFacRincomMapper friFacRincomMapper = sqlSession.getMapper(FriFacRincomMapper.class);
			var friFacRincomModelForDel = new FriFacRincom();
			friFacRincomModelForDel.setSlipNo(param.getSlipNo());
			friFacRincomService.deleteByFriFacRincom(friFacRincomModelForDel, friFacRincomMapper);

			// fri_fac_broker
			FriFacBrokerMapper friFacBrokerMapper = sqlSession.getMapper(FriFacBrokerMapper.class);
			var friFacBrokerModelForDel = new FriFacBroker();
			friFacBrokerModelForDel.setSlipNo(param.getSlipNo());
			friFacBrokerService.deleteByFriFacBroker(friFacBrokerModelForDel, friFacBrokerMapper);

			// fri_fac_exclude
			FriFacExcludeMapper friFacExcludeMapper = sqlSession.getMapper(FriFacExcludeMapper.class);
			var friFacExcludeModelForDel = new FriFacExcludeKey();
			friFacExcludeModelForDel.setSlipNo(param.getSlipNo());
			friFacExcludeService.delByFriFacExcludeKey(friFacExcludeModelForDel, friFacExcludeMapper);

			// fri_fac_shares
			FriFacSharesMapper friFacSharesMapper = sqlSession.getMapper(FriFacSharesMapper.class);
			var friFacSharesModelForDel = new FriFacShares();
			friFacSharesModelForDel.setSlipNo(param.getSlipNo());
			friFacSharesService.delByFriFacShares(friFacSharesModelForDel, friFacSharesMapper);

			sqlSession.commit();
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			return jsonBean;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

	}

	public JsonBean queryFacSameRiskList(Rin1301QueryPolicyDetailVOReq param) throws Exception {
		var jsonBean = new JsonBean();
		var friFacSameriskVo = new Rin1301SameRiskPolEndorseVo();
		if (StringUtils.isBlank(param.getLogSeq())) {
			var friFacSameriskModel = new FriFacSamerisk();
			friFacSameriskModel.setSlipNo(param.getSlipNo());
			List<FriFacSamerisk> friFacSameriskResult = friFacSameriskService
					.queryByFriFacSamerisk(friFacSameriskModel);
			if (friFacSameriskResult != null && !friFacSameriskResult.isEmpty()) {
				BeanUtils.copyProperties(friFacSameriskResult.get(0), friFacSameriskVo);
			}

		} else {
			var logFriFacSameriskModel = new LogFriFacSamerisk();
			logFriFacSameriskModel.setSlipNo(param.getSlipNo());
			logFriFacSameriskModel.setLogSeq(param.getLogSeq());
			List<LogFriFacSamerisk> logFriFacSameriskResult = logFriFacSameriskService
					.queryByLogFriFacSamerisk(logFriFacSameriskModel);
			if (logFriFacSameriskResult != null && !logFriFacSameriskResult.isEmpty()) {
				BeanUtils.copyProperties(logFriFacSameriskResult.get(0), friFacSameriskVo);
			}
		}

		jsonBean.setData(friFacSameriskVo);
		jsonBean.setStatus(SysEnum.statusSuccess.code);
		return jsonBean;
	}

	public JsonBean chkRinser(List<Rin1301FacRinserVoReq> params) throws Exception {
		var jsonBean = new JsonBean();
		List<Rin1301QueryRinserDetailSubVoResp> resultList = new ArrayList<>();

		for (Rin1301FacRinserVoReq param : params) {
			var vo = new Rin1301QueryRinserDetailSubVoResp();
			Map<String, String> result = new HashMap<>();

			String usableCheckResult = friComService.chkRinQua2(param);
			String statusCheckResult = friComService.chkEnode(param.getRinComId());
			vo.setRinComId(param.getRinComId());

			if (!StringUtils.equals("適格", usableCheckResult)) {
				if (StringUtils.equals("另案簽報名單", usableCheckResult) || StringUtils.equals("觀察名單", usableCheckResult)) {
					result.put("other", usableCheckResult);
				} else {
					result.put("noChoose", usableCheckResult);
				}
				vo.setStatus(result);
			}

			if (StringUtils.equals("C", statusCheckResult)) {
				result.put("logout", "已註銷");
				vo.setStatus(result);
			}

			if (StringUtils.equals("N", statusCheckResult)) {
				result.put("notFinalPay", "非為最終支付公司, 必須維護第二層再保人資料");
				vo.setStatus(result);
			}
			resultList.add(vo);
		}

		jsonBean.setData(resultList);
		jsonBean.setStatus(SysEnum.statusSuccess.code);
		return jsonBean;
	}

	public JsonBean calRinPremAndAmt(Rin1301HandleDataVOReq param) throws Exception {
		// 由前端傳入資料(包括表格)，整理及運算後回傳
		var jsonBean = new JsonBean();
		var facPrem = BigDecimal.ZERO;
		var facEarPrem = BigDecimal.ZERO;
		var facTypPrem = BigDecimal.ZERO;
		var sCFacPrem = BigDecimal.ZERO;
		var sCFacEarPrem = BigDecimal.ZERO;
		var sCFacTypPrem = BigDecimal.ZERO;
		var isReverse = false;
		var isExceedMode = false;
		var iSign = BigDecimal.ONE;
		var dblDetailRate = BigDecimal.ZERO;
		var dblExceedBegin = 0L;
		var dblExceedEnd = 0L;
		var dblExceedAmt = 0L;
		var mdTotAmout = 0L;
		var shareRate = BigDecimal.ZERO;

		var queryPolicyList = param.getFacPolicyVoList().stream().map(item -> {
			Rin1301QueryPolicyDetailVOReq targetBean = new Rin1301QueryPolicyDetailVOReq();
			BeanUtils.copyProperties(item, targetBean);
			return targetBean;
		}).collect(Collectors.toList());

		var excludeRiskList = param.getFacExcludeVoList().stream().map(item -> item.getContent())
				.collect(Collectors.toList());

		shareRate = param.getFacRinserVoList().stream().filter(item -> StringUtils.equals(item.getShareStatus(), "2"))
				.map(Rin1301FacRinserVoReq::getShareRate).reduce((total, item) -> total.add(item))
				.orElse(BigDecimal.ZERO).multiply(new BigDecimal("0.01"));

		for (var item : param.getFacRateVoList()) {
			if (excludeRiskList == null || !excludeRiskList.contains(item.getContent())) {

				var itemFacPrem = item.getFacPrem() == null ? BigDecimal.ZERO : new BigDecimal(item.getFacPrem());
				var itemPrem = item.getPrem() == null ? BigDecimal.ZERO : new BigDecimal(item.getPrem());

				switch (item.getContent()) {
				case "1":
					facPrem = facPrem.add(itemFacPrem);
					sCFacPrem = sCFacPrem.add(itemPrem);
					break;
				case "2":
					facEarPrem = facEarPrem.add(itemFacPrem);
					sCFacEarPrem = sCFacEarPrem.add(itemPrem);
					break;
				case "3":
					facTypPrem = facTypPrem.add(itemFacPrem);
					sCFacTypPrem = sCFacTypPrem.add(itemPrem);
					break;
				case "4":
					facPrem = facPrem.add(itemFacPrem);
					sCFacPrem = sCFacPrem.add(itemPrem);
					break;
				}
			}

		}

		dblExceedAmt = param.getFacNpropVoList().stream().map(Rin1301FacNpropVoReq::getAmt)
				.reduce((total, item) -> total += item).orElse(0L);

		if (StringUtils.equals(param.getFacType(), "2")) {
			if (!StringUtils.equals(param.getCessionNo(), param.getPreCessionNo())) {
				isExceedMode = true;
			}
		}

		List<Rin1301QueryPolicyData> lsvPolicy10Results = this.queryPolicyDetail10(queryPolicyList);
		List<Rin1301QueryPolicyDetailSubVOResp> resultList = lsvPolicy10Results.stream().map(result -> {
			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
			BeanUtils.copyProperties(result, targetBean);
			return targetBean;
		}).collect(Collectors.toList());

		for (var result : resultList) {
			result.setDutyDbgn(param.getTreatyDbgn());
			result.setDutyDend(param.getTreatyDend());
			if (StringUtils.equals(result.getEndReason(), "R")) {
				isExceedMode = true;
				isReverse = true;
				iSign = iSign.negate();
			}

			if (!isExceedMode) {
				result.setAmt(result.getAmt().multiply(shareRate).setScale(0, RoundingMode.HALF_UP));
				if (sCFacPrem.compareTo(BigDecimal.ZERO) == 0) {
					result.setPrem(BigDecimal.ZERO);
				} else {
					result.setPrem(result.getPrem().multiply(shareRate).multiply(iSign)
							.multiply(facPrem.divide(sCFacPrem, 4, RoundingMode.HALF_UP))
							.setScale(0, RoundingMode.HALF_UP));
				}

				result.setAmtTyp(
						result.getAmtTyp().multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));

				if (sCFacTypPrem.compareTo(BigDecimal.ZERO) == 0) {
					result.setPremTyp(BigDecimal.ZERO);
				} else {
					result.setPremTyp(result.getPremTyp().multiply(shareRate).multiply(iSign)
							.multiply(facTypPrem.divide(sCFacTypPrem, 4, RoundingMode.HALF_UP))
							.setScale(0, RoundingMode.HALF_UP));
				}

				result.setAmtEar(
						result.getAmtEar().multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));

				if (sCFacEarPrem.compareTo(BigDecimal.ZERO) == 0) {
					result.setPremEar(BigDecimal.ZERO);
				} else {
					result.setPremEar(result.getPremEar().multiply(shareRate).multiply(iSign)
							.multiply(facEarPrem.divide(sCFacEarPrem, 4, RoundingMode.HALF_UP))
							.setScale(0, RoundingMode.HALF_UP));
				}

			} else {
				dblExceedBegin = isReverse && param.getExcessBgn() < 0 ? -dblExceedBegin : dblExceedBegin;
				dblExceedEnd = isReverse && param.getExcessLimit() < 0 ? -dblExceedEnd : dblExceedEnd;
				dblExceedAmt = isReverse && dblExceedAmt < 0 ? -dblExceedAmt : dblExceedAmt;
				mdTotAmout = dblExceedEnd == 0 ? dblExceedAmt - dblExceedBegin : dblExceedEnd - dblExceedBegin;
				dblDetailRate = dblExceedAmt != 0 ? new BigDecimal(mdTotAmout).divide(new BigDecimal(dblExceedAmt))
						: BigDecimal.ZERO;

				var amt = isReverse && result.getAmt().multiply(dblDetailRate).compareTo(BigDecimal.ZERO) < 0
						? result.getAmt().multiply(dblDetailRate).negate()
						: result.getAmt().multiply(dblDetailRate);
				result.setAmt(amt.multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));

				if (sCFacPrem.compareTo(BigDecimal.ZERO) == 0) {
					result.setPrem(BigDecimal.ZERO);
				} else {
					result.setPrem(result.getPrem().multiply(shareRate).multiply(iSign).multiply(dblDetailRate)
							.multiply(facPrem.divide(sCFacPrem, 4, RoundingMode.HALF_UP))
							.setScale(0, RoundingMode.HALF_UP));
				}

				var amtTyp = isReverse && result.getAmtTyp().multiply(dblDetailRate).compareTo(BigDecimal.ZERO) < 0
						? result.getAmtTyp().multiply(dblDetailRate).negate()
						: result.getAmtTyp().multiply(dblDetailRate);
				result.setAmtTyp(amtTyp.multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));

				if (sCFacTypPrem.compareTo(BigDecimal.ZERO) == 0) {
					result.setPremTyp(BigDecimal.ZERO);
				} else {
					result.setPremTyp(result.getPremTyp().multiply(shareRate).multiply(iSign).multiply(dblDetailRate)
							.multiply(facTypPrem.divide(sCFacTypPrem, 4, RoundingMode.HALF_UP))
							.setScale(0, RoundingMode.HALF_UP));
				}

				var amtEar = isReverse && result.getAmtEar().multiply(dblDetailRate).compareTo(BigDecimal.ZERO) < 0
						? result.getAmtEar().multiply(dblDetailRate).negate()
						: result.getAmtEar().multiply(dblDetailRate);
				result.setAmtEar(amtEar.multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));

				if (sCFacEarPrem.compareTo(BigDecimal.ZERO) == 0) {
					result.setPremEar(BigDecimal.ZERO);
				} else {
					result.setPremEar(result.getPremEar().multiply(shareRate).multiply(iSign).multiply(dblDetailRate)
							.multiply(facEarPrem.divide(sCFacEarPrem, 4, RoundingMode.HALF_UP))
							.setScale(0, RoundingMode.HALF_UP));
				}

			}
		}

		jsonBean.setData(resultList);
		jsonBean.setStatus(SysEnum.statusSuccess.code);
		return jsonBean;
	}
	
	
	
//	public JsonBean calRinPremAndAmt(Rin1301HandleDataVOReq param) throws Exception {
//
//		var jsonBean = new JsonBean();
//
//		// CalcFacShare
//		int i = 0;
//		String isExclude ="";						//險種(第二頁籤下方表格)
//		BigDecimal shareRate = BigDecimal.ZERO;		//再保人分出比率
//		BigDecimal facAmt = BigDecimal.ZERO;
//		BigDecimal facTypAmt = BigDecimal.ZERO;
//		BigDecimal facEarAmt = BigDecimal.ZERO;
//		BigDecimal facPrem = BigDecimal.ZERO;
//		BigDecimal facTypPrem = BigDecimal.ZERO;
//		BigDecimal facEarPrem = BigDecimal.ZERO;
//		BigDecimal sCFacPrem = BigDecimal.ZERO;
//		BigDecimal sCFacTypPrem = BigDecimal.ZERO;
//		BigDecimal sCFacEarPrem = BigDecimal.ZERO;
//
//		BigDecimal dblTmp = BigDecimal.ZERO;
//		BigDecimal mdTotAmout = BigDecimal.ZERO;
//		
//		
//		BigDecimal dblDetailRate = BigDecimal.ZERO;		//保額比例
//		BigDecimal dblExceedBegin = BigDecimal.ZERO;	//超額起賠金額
//		BigDecimal dblExceedEnd = BigDecimal.ZERO;		//超額賠款上限
//		BigDecimal dblExceedAmt = BigDecimal.ZERO;		//保額合計
//		
//
//		BigDecimal hundred = new BigDecimal("100.00");
//		BigDecimal zero = BigDecimal.ZERO;
//
//		// 將處理階段為完成的再保人分出比率加總(第三頁籤上方表格)
//		shareRate = param.getFacRinserVoList().stream().filter(item -> StringUtils.equals(item.getShareStatus(), "2"))
//				.map(Rin1301FacRinserVoReq::getShareRate).reduce((total, item) -> total.add(item))
//				.orElse(BigDecimal.ZERO);
//		// 再保人分出比率
//		shareRate = shareRate.divide(hundred);
//		
//		
//		// 勾選了哪些除外險種(第三個頁籤的最下方)
//		List<String> excludeRiskList = param.getFacExcludeVoList().stream().map(item -> item.getContent())
//				.collect(Collectors.toList());
//
//		// 第二頁籤最下方表格資料
//		for (Rin1301FacRateVoReq item : param.getFacRateVoList()) {
//
//			// 若險種有等於第三頁籤選的除外險種
//			if (CollectionUtils.isEmpty(excludeRiskList) || !excludeRiskList.contains(item.getContent())) {
//				// 取共保保額(null則給0)
//				BigDecimal itemFacAmt = item.getAmt() == null ? BigDecimal.ZERO : new BigDecimal(item.getAmt());
//				// 取臨分保費(null則給0)
//				BigDecimal itemFacPrem = item.getFacPrem() == null ? BigDecimal.ZERO : new BigDecimal(item.getFacPrem());
//				// 取共保保費(null則給0)
//				BigDecimal itemSCFacPrem = item.getPrem() == null ? BigDecimal.ZERO : new BigDecimal(item.getPrem());
//				
//				// 依照險種分別做共保保額、臨分保費、共保保費的加總
//				isExclude = item.getContent();
//				switch (isExclude) {
//				//商業火險、營業中斷險
//				case "1":
//				case "4":
//					facAmt = facAmt.add(itemFacAmt);
//					facPrem = facPrem.add(itemFacPrem);
//					sCFacPrem = sCFacPrem.add(itemSCFacPrem);
//					break;
//				//地震險
//				case "2":
//					facEarAmt = facEarAmt.add(itemFacAmt);
//					facEarPrem = facEarPrem.add(itemFacPrem);
//					sCFacEarPrem = sCFacEarPrem.add(itemSCFacPrem);
//					break;
//				//颱洪險	
//				case "3":
//					facTypAmt = facTypAmt.add(itemFacAmt);
//					facTypPrem = facTypPrem.add(itemFacPrem);
//					sCFacTypPrem = sCFacTypPrem.add(itemSCFacPrem);
//					break;
//				default:
//					break;
//		
//				}
//			}
//
//		}
//		
//		
//		//原先改成用這個SQL(先留著)=========
////		var queryPolicyList = param.getFacPolicyVoList().stream().map(item -> {
////			Rin1301QueryPolicyDetailVOReq targetBean = new Rin1301QueryPolicyDetailVOReq();
////			BeanUtils.copyProperties(item, targetBean);
////			return targetBean;
////		}).collect(Collectors.toList());
////		
////		
////		List<Rin1301QueryPolicyData> lsvPolicy10Results = this.queryPolicyDetail10(queryPolicyList);
//		//==========================
//		//(舊程式Rin1301_GetData.QryFacPolicyDtl)
//		Rin1301QueryPolicyDetailVOReq model = new Rin1301QueryPolicyDetailVOReq();
//		model.setSlipNo(param.getSlipNo());
//		List<Rin1301QueryPolicyData> lsvPolicy10Results = this.queryPolicyDetail10ByEdit(model);
//		List<Rin1301QueryPolicyDetailSubVOResp> policyDetail10 = lsvPolicy10Results.stream().map(result -> {
//			Rin1301QueryPolicyDetailSubVOResp targetBean = new Rin1301QueryPolicyDetailSubVOResp();
//			BeanUtils.copyProperties(result, targetBean);
//			return targetBean;
//		}).collect(Collectors.toList());
//		
//		List<Rin1301QueryPolicyDetailSubVOResp> resultList = new ArrayList<>();
//		Rin1301QueryPolicyDetailSubVOResp result = null;
//		for(Rin1301QueryPolicyDetailSubVOResp facShare : policyDetail10) {
//			result = new Rin1301QueryPolicyDetailSubVOResp();
//			result.setPolicyNo(facShare.getPolicyNo());			//保單號
//			result.setEndorseNo(facShare.getEndorseNo());		//批單號
//			result.setAddrNo(facShare.getAddrNo().toString());	//地址序號
//			result.setRiskNo(facShare.getRiskNo());				//同險號
//			result.setDutyDbgn(param.getTreatyDbgn());			//生效起日
//			result.setDutyDend(facShare.getDutyDend());			//生效迄日
//					
//			boolean bIsExceedMode = false;		//是否以超賠模式處理
//			boolean bIsReverse = false;			//是否是'全退'
//			BigDecimal iSign = BigDecimal.ONE;
//			
//			//若臨分類型為'2.超賠'且合約號!=前合約號，則為'非臨分批單'
//			if(StringUtils.equals(param.getFacType(), "2") && !StringUtils.equals(param.getCessionNo(), param.getPreCessionNo())) {
//				bIsExceedMode = true;
//				
//			//反之，則為'臨分批單'
//			}else {
//				bIsExceedMode = false;
//				//批改事由若為"R"，表示'全退'
//				if(StringUtils.equals(facShare.getEndReason(), "R")) {
//					bIsExceedMode = true;
//					bIsReverse = true;
//					iSign = iSign.negate();	//iSign給-1
//				}				
//			}	
//			
//			if(bIsExceedMode == false) {//一般
//				dblDetailRate = BigDecimal.ONE;		//保額比例				
//			}else {//超賠
//				dblExceedBegin = new BigDecimal(param.getExcessBgn());		//超額起賠金額
//				dblExceedEnd = new BigDecimal(param.getExcessLimit());		//超額賠款上限
//				dblExceedAmt = new BigDecimal(param.getAmtSum());			//保額合計
//				
//				if(bIsReverse == true) {//全退
//					//超額起賠金額小於0，變成正
//					if(dblExceedBegin.compareTo(zero) == -1) {
//						dblExceedBegin = dblExceedBegin.negate();
//					}
//					//超額賠款上限小於0，變成正
//					if(dblExceedEnd.compareTo(zero) == -1) {
//						dblExceedEnd = dblExceedEnd.negate();
//					}
//					//保額合計小於0，變成正
//					if(dblExceedAmt.compareTo(zero) == -1) {
//						dblExceedAmt = dblExceedAmt.negate();
//					}
//				}
//				
//				//超額賠款上限是否為0，
//				if(dblExceedEnd.compareTo(zero) == 0) {
//					//保額合計-超額起賠金額
//					mdTotAmout = dblExceedAmt.subtract(dblExceedBegin);
//				}else {
//					//超額賠款上限-超額起賠金額
//					mdTotAmout = dblExceedEnd.subtract(dblExceedBegin);
//				}
//
//				//保額合計是否為0
//				if(dblExceedAmt.compareTo(zero) != 0) {
//					//保額比例=mdTotAmout除以保額合計
//					dblDetailRate = mdTotAmout.divide(dblExceedAmt, 4, RoundingMode.HALF_UP);
//				}else{
//					//保額比例=0
//					dblDetailRate = BigDecimal.ZERO;
//				}
//			}
//			
//			//-------------------------------------------------------------
//			//Fac Amt(分保額與分保費)
//			//-------------------------------------------------------------
//			//---Amt(分保額)---
//			if(bIsExceedMode == false) {//一般
//				//保額
//				result.setAmt(facShare.getAmt().multiply(shareRate).setScale(0, RoundingMode.HALF_UP));			
//			}else {//超賠
//				dblTmp = facShare.getAmt().multiply(dblDetailRate);
//				if(bIsReverse == true && dblTmp.compareTo(zero) == -1) {
//					dblTmp = dblTmp.negate();
//				}
//				result.setAmt(dblTmp.multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));								
//			}
//			
//			//---Prem(分保費)---
//			if(sCFacPrem.compareTo(zero) == 0) {
//				result.setPrem(zero);
//			}else {
//				if(bIsExceedMode == false) {//一般
//					result.setPrem(facShare.getPrem().multiply(facPrem.divide(sCFacPrem, 4, RoundingMode.HALF_UP)).multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}else {
//					result.setPrem(facShare.getPrem().multiply(facPrem.divide(sCFacPrem, 4, RoundingMode.HALF_UP)).multiply(dblDetailRate).multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}
//			}
//			
//			
//			//-------------------------------------------------------------
//			//Fac Typ(颱洪分保額與分保費)
//			//-------------------------------------------------------------
//			//---Amt(分保額)---
//			if(StringUtils.equals(isExclude, "3")) {
//				result.setAmtTyp(zero);
//			}else {
//				if(bIsExceedMode == false) {//一般
//					result.setAmtTyp(facShare.getAmtTyp().multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}else {
//					dblTmp = facShare.getAmtTyp().multiply(dblDetailRate);
//					if(bIsReverse == true && dblTmp.compareTo(zero) == -1) {
//						dblTmp = dblTmp.negate();
//					}					
//					result.setAmtTyp(dblTmp.multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}
//			}
//			
//			//---Prem(分保費)---
//			if(sCFacTypPrem.compareTo(zero) == 0) {
//				result.setPremTyp(zero);
//			}else {
//				if(bIsExceedMode == false) {
//					result.setPremTyp(facShare.getPremTyp().multiply(facTypPrem.divide(sCFacTypPrem, 4, RoundingMode.HALF_UP)).multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}else {
//					result.setPremTyp(facShare.getPremTyp().multiply(facTypPrem.divide(sCFacTypPrem, 4, RoundingMode.HALF_UP)).multiply(dblDetailRate).multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}
//			}
//			
//			
//			//-------------------------------------------------------------
//			//Fac Ear(地震分保額與分保費)
//			//-------------------------------------------------------------
//			//---Amt(分保額)---
//			if(StringUtils.equals(isExclude, "2")) {
//				result.setAmtEar(zero);
//			}else {
//				if(bIsExceedMode == false) {
//					result.setAmtEar(facShare.getAmtEar().multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}else {
//					dblTmp = facShare.getAmtEar().multiply(dblDetailRate);
//					if(bIsReverse == true && dblTmp.compareTo(zero) == -1) {
//						dblTmp = dblTmp.negate();
//					}
//					result.setAmtEar(dblTmp.multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}
//			}
//			
//			//---Prem(分保費)---
//			if(sCFacEarPrem.compareTo(zero) == 0) {
//				result.setPremEar(zero);
//			}else {
//				if(bIsExceedMode == false) {
//					result.setPremEar(facShare.getPremEar().multiply(facEarPrem.divide(sCFacEarPrem, 4, RoundingMode.HALF_UP)).multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}else {
//					result.setPremEar(facShare.getPremEar().multiply(facEarPrem.divide(sCFacEarPrem, 4, RoundingMode.HALF_UP)).multiply(dblDetailRate).multiply(shareRate).multiply(iSign).setScale(0, RoundingMode.HALF_UP));
//				}
//			}
//			
//			result.setCoinsRate(facShare.getCoinsRate());		//共保比率
//			resultList.add(result);
//		}
//
//		jsonBean.setData(resultList);
//		jsonBean.setStatus(SysEnum.statusSuccess.code);
//		return jsonBean;
//	}

}
