//.....保批單標的物明細C.....//

/**
 *  預設 
 */
$(function() {
	//標的物下拉選單
	ddlPropertyList();
})

//執行按鈕
function dataGoC() {
	let radioValue = $("input[name='radio3']:checked").val();

	switch (radioValue) {
	
	//新增保批單標的物檔
	case '1':
		
		//檢核必填
		let msg1 = checkRequiredC();
		if(!checkIsNullSpace(msg1)){
			styleAlert(msg1);
			return;
		}
		
		let param = get1304PolicyPropDtlParam();
		let parJson = JSON.stringify(param);
		let res = ajaxPostByJsonParam("../../rin1304capi/insertfripolicypropdtl",parJson, false);
		
		if ("000"===res.status) {
			alert(res.message);
			lv2Click();
		}
		break;
		
	//修改保批標單的物檔
	case '2':
		//檢核必填
		let msg2 = checkRequiredC();
		if(!checkIsNullSpace(msg2)){
			styleAlert(msg2);
			return;
		}
		let param2 =get1304PolicyPropDtlParam();
		let parJson2 = JSON.stringify(param2);
		let res2 = ajaxPostByJsonParam("../../rin1304capi/updatefripolicypropdtl",parJson2, false);
		if ("000"===res2.status) {
			alert(res2.message)
		}
		break;
	}
}

/**
 * Rin1304臨分分入，刪除保批單標的物明細
 * @author Sophia 2021/12/07
 */
function btnDeleteC(){
	
	let isDelete = confirm("確認要刪除該保批單標的物明細?");

	if(isDelete){
		//1-參數
		let param ={
				"txtpolicy_no" : $('#txtpolicy_noC').val(),  //保單號碼
				"txtendorse_no": $('#txtendorse_noC').val(), //批單號碼 
				"numaddr_no"   : $('#numaddr_noC').val(),    //地址序號
				"numprop_no"   : $('#numprop_noC').val()     //標的物序號
		} 
		
		let parJson = JSON.stringify(param);
		
		//2-執行刪除
		let res = ajaxPostByJsonParam("../../rin1304capi/deletefripolicypropdtl", parJson, false);
		
		if("000" === res.status){
			alert("刪除保批單標的物明細成功");
			lv2Click();
		}else{
			alert("刪除保批單標的物明細失敗");
		}
	}

}



//檢核必填
function checkRequiredC(){

	let message = "";
	let isRequireStr = "為必填!<br>";
	
	
	//--------------保批單標的物明細頁(C)--------------
	
	
	if(checkIsNullSpace($('#ddlpropertyNo>select').val())){
		message = message + "標的物代號" + isRequireStr;
	}

	if(checkIsNullSpace($('#txtproperty_name').val())){
		message = message + "標地物保品名稱" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtproperty_code').val())){
		message = message + "標的物保品名稱簡碼" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numamtC').val())){
		message = message + "保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numpremC').val())){
		message = message + "保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtuseprop_codeC').val())){
		message = message + "使用性質代號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtconst_classC').val())){
		message = message + "建築等級英文代號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtlimit_no').val())){
		message = message + "限額代號" + isRequireStr;
	}
	
	return message;
}

//取得保批單標的物明細畫面欄位值
function get1304PolicyPropDtlParam(){
	var ddlPropertyNo = $('#ddlpropertyNo>select').val().split("")[0]; //取下拉選單值
	
	return{
		//-----------保批單標的物明細檔畫面欄位-------------
		"policyNo":$('#txtpolicy_noC').val(),        //保單號碼
		"endorseNo":$('#txtendorse_noC').val(),      //批單號碼
		"addrNo":$('#numaddr_noC').val(),            //保單地址序號
		"propNo":$('#numprop_noC').val(),            //標的物序號
		"floatFlag":$('#txtfloatFlag').val(),        //流動註記
		"propertyNo":ddlPropertyNo,                   //標的物代號
		"propertyName":$('#txtproperty_name').val(), //標的物保品名稱
		"propertyCode":$('#txtproperty_code').val(), //標的物保品簡碼
		"amt":$('#numamtC').val(),                   //保額
		"prem":$('#numpremC').val(),                 //保費
		"usepropCode":$('#txtuseprop_codeC').val(),   //使用性質代號
		"usepropName":$('#txtuseprop_name').val(),   //使用性質名稱
		"constClass":$('#txtconst_classC').val(),    //建築等級英文代號
		"limitNo":$('#limit_no').val(),              //限額代號
		"limit":$('#limit').val(),                   //限額
		"specTerm":$('#txtspec_term').val(),         //限額比率
		"extTerm":$('#txtext_term').val(),           //適用附加條款
	}
}

//寫入欄位
function writeFieldForRin1304C(cell){
	
	//.......保批單標的物檔........//
	$('#txtpolicy_noC').val(cell.txtpolicy_no);                   //保單號碼
	$('#txtendorse_noC').val(cell.txtendorse_no);                 //批單號碼
	
	$('#numaddr_noC').val(cell.numaddr_no);                       //地址序號
	$('#numprop_noC').val(cell.numprop_no);                       //標的物序號
	$('#txtfloatFlag').val(cell.txtfloatFlag);                    //流動註記
	$('#ddlpropertyNo').find("select").val(cell.txtproperty_no);  //標的物代號
	$('#txtproperty_name').val(cell.txtproperty_name);            //標的物保品名稱
	$('#txtproperty_code').val(cell.txtproperty_code);            //標的物保品簡碼
	$('#numamtC').val(cell.numamt);                               //保額
	$('#numpremC').val(cell.numprem);                             //保費
	$('#txtuseprop_codeC').val(cell.txtuseprop_code);              //使用性質代號
	$('#txtuseprop_name').val(cell.txtuseprop_name);              //使用性質名稱
	$('#txtconst_classC').val(cell.txtconst_class);               //建築等級英文代號
	$('#txtlimit_no').val(cell.txtlimit_no);                      //限額代號
	$('#txtspec_term').val(cell.txtspec_term);                    //適用特別條款
	$('#txtext_term').val(cell.txtext_term);                      //適用附加條款
}

//新增時欄位預設值
function insertDataRin1304C(parJson){
	
	//清空欄位
	clearDataRin1304C();
	$('#txtpolicy_noC').val(policyNo);           //保單號碼
	$('#txtendorse_noC').val(endorseNo);         //批單號碼
	$('#numaddr_noC').val(parJson.addrNo);       //地址序號
}

//清空欄位
function clearDataRin1304C(){
	$('#txtpolicy_noC').val("");                 //保單號碼
	$('#txtendorse_noC').val("");                //批單號碼
	
	$('#numaddr_noC').val("");                   //地址序號
	$('#numprop_noC').val("");                   //標的物序號
	$('#txtfloatFlag').val("");                  //流動註記
	$('#ddlpropertyNo').find("select").val("");  //標的物代號
	$('#txtproperty_name').val("");              //標的物保品名稱
	$('#txtproperty_code').val("");              //標的物保品簡碼
	$('#numamtC').val("");                       //保額
	$('#numpremC').val("");                      //保費
	$('#txtuseprop_codeC').val("");              //使用性質代號
	$('#txtuseprop_name').val("");               //使用性質名稱
	$('#txtconst_classC').val("");               //建築等級英文代號
	$('#txtlimit_no').val("");                   //限額代號
	$('#txtspec_term').val("");                  //適用特別條款
	$('#txtext_term').val("");                   //適用附加條款
}

//標的物保品名稱自動帶入
$('#ddlpropertyNo').change(function() {
	var ddlPropertyName = $('#ddlpropertyNo').find(':selected').text().split(" ")[1] //取下拉選單該值名稱
	$('#txtproperty_name').val(ddlPropertyName);
});


//查詢C頁
function query3(param){
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304capi/querypolicypropdtlresult", parJson, false);

	if("000" === res.status){
		
		//寫入資料
		writeFieldForRin1304C(res.data[0]);
		
	}else{
		alert("保批單標的物查詢錯誤");
	}
}

/*
 * 標的物下拉選單
 */
function ddlPropertyList(){
	let res = ajaxPostByJsonParam("../../rin1304capi/queryddlpropertylist", '', false);
	var ajaxdataSub =res.data;
	let apidata = new Array();
	if("000" === res.status){
		
		for(var i = 0; i < ajaxdataSub.length; i++){
			// 組出來是 標的物代號 標的物名稱
			apidata.push({
				text : ajaxdataSub[i].txtproperty_no + " "
						+ ajaxdataSub[i].txtproperty_name,
				value : ajaxdataSub[i].txtproperty_no
			});
		}
	}
		createDdl("ddlpropertyNo", apidata, "");
}
