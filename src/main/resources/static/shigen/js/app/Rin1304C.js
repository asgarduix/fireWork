//.....保批單標的物明細C.....//

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
			$('#downRowC').hide();
			//樹狀圖立刻顯示
			var thisPreLv3=$(memoClickElement).parent("div").eq(0)[0].className;
			var childenDiv3=$(memoClickElement).parent("div").children('div').eq(0)[0].className;
			if(thisPreLv3 === 'menu-section menu-lv-3'&& childenDiv3 === 'menu-item'){
				memoClickElement.parentElement.previousSibling.click();
				memoClickElement.click();
			}else{
				immediatelyShow();
			}
		}else{
			alert("標的物序號:"+$('#numprop_noC').val()+"重複!" + "\n新增標的物單失敗");
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
			$('#downRowC').hide();
			//樹狀圖立刻顯示
			memoClickElement.parentElement.previousSibling.click();
			memoClickElement.click();
		}else{
			alert("修改保批標單的物失敗!");
		}
		break;
	}
}

//======按鈕控制開始=====//

//按左下修新增時，顯示右下的新增Radio
$('#btnAddC').click(function() {
	$("#policyPageC").find("input, button,select").attr("disabled", false);
	$("#numamtC").attr("disabled", true);		//保額
	$("#numpremC").attr("disabled", true);		//保費
	$("#updateRadioC").removeAttr("checked");   //修改Radio取消勾選
	$('#downRowC').show();                      //RadioRow 顯示	
	$('#insertRadioSpanC').show();              //新增Radio顯示
	$("#insertRadioC").prop("checked", true);   //新增設定打勾
	$('#updateRadioSpanC').hide();              //修改Radio隱藏
});

//按左下修改時，顯示右下的修改Radio
$('#btnUpdC').click(function() {
	$("#policyPageC").find("input, button,select").attr("disabled", false);
	$("#numamtC").attr("disabled", true);		//保額
	$("#numpremC").attr("disabled", true);		//保費
	$("#insertRadioC").removeAttr("checked");   //新增adio取消勾選
	$('#downRowC').show();                      //RadioRow 顯示
	$('#insertRadioSpanC').hide();              //新增Radio隱藏
	$("#updateRadioC").prop("checked", true);   //修改設定打勾
	$('#updateRadioSpanC').show();              //修改Radio顯示
});

//======按鈕控制結束=====//

/**
 * Rin1304臨分分入，刪除保批單標的物明細
 * @author Sophia 2021/12/07
 */
function btnDeleteC(){
	
	let isDelete = confirm("確認要刪除該保批單標的物明細?");

	if(isDelete){
		//1-參數
		let param ={
				"policyNo" : $('#txtpolicy_noC').val(),  //保單號碼
				"endorseNo": $('#txtendorse_noC').val(), //批單號碼 
				"addrNo"   : $('#numaddr_noC').val(),    //地址序號
				"propNo"   : $('#numprop_noC').val()     //標的物序號
		} 
		
		let parJson = JSON.stringify(param);
		
		//2-執行刪除
		let res = ajaxPostByJsonParam("../../rin1304capi/deletefripolicypropdtl", parJson, false);
		
		if("000" === res.status){
			alert("刪除保批單標的物明細成功");
			//樹狀圖即時更新
			memoClickElement.parentElement.previousSibling.click();
			memoClickElement.click();
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
	
	if(checkIsNullSpace($('#txtuseprop_nameC').val())){
		message = message + "使用性質名稱" + isRequireStr;
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
		"propertyNo":ddlPropertyNo,                  //標的物代號
		"propertyName":$('#txtproperty_name').val(), //標的物保品名稱
		"propertyCode":$('#txtproperty_code').val(), //標的物保品簡碼
		"amt":removeComma($('#numamtC').val()),      //保額
		"prem":removeComma($('#numpremC').val()),    //保費
		"usepropCode":$('#txtuseprop_codeC').val(),  //使用性質代號
		"usepropName":$('#txtuseprop_nameC').val(),  //使用性質名稱
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
	$('#txtpolicy_noC').val(policyNo);                   //保單號碼
	$('#txtendorse_noC').val(endorseNo);                 //批單號碼
	
	$('#numaddr_noC').val(cell.numaddr_no);                       //地址序號
	$('#numprop_noC').val(cell.numprop_no);                       //標的物序號
	$('#txtfloatFlag').val(cell.txtfloatFlag);                    //流動註記
	$('#ddlpropertyNo').find("select").val(cell.txtproperty_no);  //標的物代號
	$('#txtproperty_name').val(cell.txtproperty_name);            //標的物保品名稱
	$('#txtproperty_code').val(cell.txtproperty_code);            //標的物保品簡碼
	$('#numamtC').val(toThousands(cell.numamt));                    //保額
	$('#numpremC').val(toThousands(cell.numprem));                  //保費
	$('#txtuseprop_codeC').val(cell.txtuseprop_code);             //使用性質代號
	$('#txtuseprop_nameC').val(cell.txtuseprop_name);             //使用性質名稱
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
	$('#txtfloatFlag').val("N");                 //流動註記
	$('#numamtC').val("0");                      //保額
	$('#numpremC').val("0");                     //保費
	$('#txtuseprop_codeC').val($('#txtuseprop_codeB').val()); //使用性質代號	
	$('#txtuseprop_nameC').val($('#txtuseprop_nameB').val()); //使用性質名稱	
	$('#txtconst_classC').val($('#txtconst_class').val());    //建築等級英文代號
	$('#txtlimit_no').val($('#limit_no').val());              //限額代號
	getMaxPropNo();                              //標的物序號
	
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
	$('#txtuseprop_nameC').val("");               //使用性質名稱
	$('#txtconst_classC').val("");               //建築等級英文代號
	$('#txtlimit_no').val("");                   //限額代號
	$('#txtspec_term').val("");                  //適用特別條款
	$('#txtext_term').val("");                   //適用附加條款
}

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


//==========標的物下拉選單相關==========//
/*
 * 標的物下拉選單
 */
function ddlPropertyList() {
	let res = ajaxPostByJsonParam("../../rin1304capi/queryddlpropertylist", '', false);
	var ajaxdataSub = res.data;
	let apidata = new Array();
	if ("000" === res.status) {

		for (var i = 0; i < ajaxdataSub.length; i++) {

			var name;
			var propertyNo = ajaxdataSub[i].propertyNo;
			name = propertyNo === '0' ? name = "BF/FF....." : name = ajaxdataSub[i].propertyName;
			// 組出來是 標的物代號 標的物名稱
			apidata.push({
				text: ajaxdataSub[i].propertyNo + ","
					+ name + ";"
					+ ajaxdataSub[i].ename,
				value: ajaxdataSub[i].propertyNo
			});
		}

	}
	createDdl("ddlpropertyNo", apidata, "");
}

//標的物保品名稱自動帶入
$('#ddlpropertyNo').change(function() {
	var ddlpropertySplit = $('#ddlpropertyNo').find(':selected').text().split(/,|;/);
	$('#txtproperty_name').val(ddlpropertySplit[1]);//取下拉選單該值名稱
	$('#txtproperty_code').val(ddlpropertySplit[2]);//取下拉選單該值簡稱
});

//==========標的物下拉選單相關END==========//

//取標的物序號最大值
function getMaxPropNo() {
	let param = {
		"policyNo": $('#txtpolicy_noB').val(),   //保單號碼
		"endorseNo": $('#txtendorse_noB').val(), //批單號碼 
		"addrNo": $('#numaddr_noB').val()        //地址序號
	}

	let parJson = JSON.stringify(param);
	//2-查詢
	let res = ajaxPostByJsonParam("../../rin1304capi/getmaxpropno", parJson, false);
	if ("000" === res.status) {
		$('#numprop_noC').val(res.data);
	}
}