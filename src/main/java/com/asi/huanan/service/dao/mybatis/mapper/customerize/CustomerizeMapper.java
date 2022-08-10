package com.asi.huanan.service.dao.mybatis.mapper.customerize;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.asi.huanan.service.dao.mybatis.model.customerize.FricomJoinRicmpf1;

public interface CustomerizeMapper {
	
	/**
	 * Rin1101A-「再保人代號」單筆搜尋
	 * @param rinComId
	 * @return list FricomJoinRicmpf1
	 * @author yiting 	2021/09/29
	 */
	@Select("<script>"+
			"select a.rin_com_id as rinComId, a.ename, a.cname, a.acct_Area as acctArea, a.favtyp, " + 
			"a.[inout], a.remark1, a.shige, a.watchto, a.signnok, " + 
			"CONVERT (varchar(12), a.blocklst ,111) as blocklst, " + 
			"b.ocode, b.carmrk, b.marmrk, b.firmrk, b.accmrk, b.ahmrk " + 
			"from fri_com a " + 
			"left join ricmpf1 b on a.rin_com_id = b.compn1 " + 
			"where a.rin_Com_Id = #{rinComId,jdbcType=VARCHAR}"+
			"</script>")
	List<FricomJoinRicmpf1> queryOneReiner(@Param("rinComId") String rinComId);

	
}