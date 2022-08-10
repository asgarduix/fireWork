package com.asi.mechanism.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class QueryPolicyShouldPayNoPayIndQuery {
	public List<HashMap<String, String>> getResultList(String policy_no) {
		// TODO Auto-generated method stub
		List<HashMap<String, String>> list = new ArrayList<>();

//		PreparedStatement preparedStatement = null;
//		String sql = " SELECT COUNT(*) as pshp_count " + " FROM pshp " + " WHERE policy_no = ?"
//				+ " AND pshp_sts_code IN ( '0' , '1' ) ";
//		preparedStatement = DBConnection.createPreparedStatement(sql);
//
//		DBConnection.setPreparedStatementString(preparedStatement, 1, policy_no);
//		list = DBConnection.getResultList(DBConnection.executeQuery(preparedStatement));
//		DBConnection.closeStatement(preparedStatement);
		return list;
	}

	// 取得是否有 未兌現支票(注意，此cc182i procedure由靜芬副理提供，未來維護等事宜也需要由他們主導)
	public List<HashMap<String, String>> getResultListOfUnCashingCheck(String policy_no) {
		List<HashMap<String, String>> list = new ArrayList<>();
//		
//		PreparedStatement preparedStatement = null;
//		String sql = " execute procedure cc182i('NoConditio','" + policy_no + "');";// 此cc182i可使用其中一參數來查找，在此不使用第一個，隨便傳一個文字串
//
//		preparedStatement = DBConnection.createPreparedStatement(sql);
//		list = DBConnection.getResultList(DBConnection.executeQuery(preparedStatement));
//		DBConnection.closeStatement(preparedStatement);
		return list;
	}
}
