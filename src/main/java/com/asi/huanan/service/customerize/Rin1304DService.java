package com.asi.huanan.service.customerize;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.FriPolicyAdditionService;
import com.asi.huanan.service.FriPolicyDtlService;
import com.asi.huanan.service.FriPolicyPropService;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyDtlMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAddition;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;

@Service
public class Rin1304DService {

	private Log log = LogFactory.getLog(Rin1304DService.class);

	@Autowired
	private FriPolicyDtlService friPolicyDtlService;
	@Autowired
	private FriPolicyPropService friPolicyPropService;
	@Autowired
	private FriPolicyAdditionService friPolicyAdditionService;

	public void updatePropAndDtl(FriPolicyAddition parJson,FriPolicyAdditionMapper friPolicyAdditionMapper,FriPolicyPropMapper friPolicyPropMapper, SqlSession sqlSession) throws Exception {
		log.debug(">>> Rin1304DService.updatePropAndDtl(新增or修改後，update fri_policy_prop與fri_policy_dtl兩張表)");
		FriPolicyDtlMapper friPolicyDtlMapper = sqlSession.getMapper(FriPolicyDtlMapper.class);

		String policyNo = parJson.getPolicyNo();			//保單號碼
		String endorseNo = parJson.getEndorseNo();			//批單號碼
		String addrNo = parJson.getAddrNo().toString();		//地址序號
		String propNo = parJson.getPropNo().toString();		//標的物序號

		List<Rin1304SumAmtPremRespVO> sumAmtPremVO = friPolicyAdditionService.sumAmtPrem(policyNo, endorseNo, addrNo, propNo,
				friPolicyAdditionMapper);

		BigDecimal amt = BigDecimal.ZERO; // 保額
		BigDecimal prem = BigDecimal.ZERO; // 保費
		BigDecimal amtTyp = BigDecimal.ZERO; // 颱洪保額
		BigDecimal premTyp = BigDecimal.ZERO; // 颱洪保費
		BigDecimal amtEar = BigDecimal.ZERO; // 地震保額
		BigDecimal premEar = BigDecimal.ZERO; // 地震保費

		for (Rin1304SumAmtPremRespVO model : sumAmtPremVO) {
			BigDecimal getAmt = model.getAmt(); // 保額
			BigDecimal getPrem = model.getPrem(); // 保費
			BigDecimal getAmtTyp = model.getAmtTyp(); // 颱洪保額
			BigDecimal getPremTyp = model.getPremTyp();// 颱洪保費
			BigDecimal getAmtEar = model.getAmtEar(); // 地震保額
			BigDecimal getPremEar = model.getPremEar();// 地震保費
			if (getAmt == null) {
				getAmt = new BigDecimal(0);
			}
			if (getPrem == null) {
				getPrem = new BigDecimal(0);
			}
			if (getAmtTyp == null) {
				getAmtTyp = new BigDecimal(0);
			}
			if (getPremTyp == null) {
				getPremTyp = new BigDecimal(0);
			}
			if (getAmtEar == null) {
				getAmtEar = new BigDecimal(0);
			}
			if (getPremEar == null) {
				getPremEar = new BigDecimal(0);
			}
			amt = amt.add(getAmt);
			prem = prem.add(getPrem);
			amtTyp = amtTyp.add(getAmtTyp);
			premTyp = premTyp.add(getPremTyp);
			amtEar = amtEar.add(getAmtEar);
			premEar = premEar.add(getPremEar);
		}
		friPolicyPropService.updatePropAmtPrem(prem, amt, policyNo, endorseNo, addrNo, propNo, friPolicyPropMapper);
		friPolicyDtlService.updatePolicyDtlAmtPrem(policyNo, endorseNo, addrNo, amtTyp, premTyp, amtEar, premEar,
				friPolicyDtlMapper);

	}

}
