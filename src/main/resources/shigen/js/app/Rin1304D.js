//.....保批單附加險明細檔D.....//
/**
 *  預設 
 */
$(function() {
	//附加險下拉選單
	ddlAdditionList();

})

//當保額欄位變動時，自動計        保額/共保比率
$('#numamtD').blur(function() {
	let amt = $('#numamtD').val();
	var coinsRate=$('#txtcoins_rate').val();
	let numhundredamt = (amt*1)/(coinsRate/100);
 $('#numcoinamt').val(numhundredamt);
  });

//當保費欄位變動時，自動計算     保費/共保比率
$('#numpremD').blur(function() {
	let prem = $('#numpremD').val();
	var coinsRate=$('#txtcoins_rate').val();
	let numhundredprem = (prem*1)/(coinsRate/100); 
 $('#numcoinprem').val(numhundredprem);
  });

//附加險名稱根據下拉選單選的值顯示
$('#additionNoList').change(function() {
	var txtAdditionName = $('#additionNoList').find(':selected').text().split(" ")[1]; //取下拉選單文字
 $('#txtaddition_name').val(txtAdditionName);
  });


//執行按鈕
function dataGoD() {
	let radioValue = $("input[name='radio3']:checked").val();

	switch (radioValue) {
	//新增保批單附加險明細檔
	case '1':
		
		//給預設值
		setZeroD();
		
		//檢核必填
		let msg1 = checkRequiredD();
		if(!checkIsNullSpace(msg1)){
			styleAlert(msg1);
			return;
		}
		let param = get1304PolicyAdditionDtlParam();
		let parJson = JSON.stringify(param);
		let res = ajaxPostByJsonParam("../../rin1304dapi/insertfripolicyadditiondtl",parJson, false);

		if (res) {
			alert(res.message)
			lv2Click();
		}
		break;
		
	//修改保批單附加險明細檔
	case '2':
		
		//檢核必填
		let msg2 = checkRequiredD();
		if(!checkIsNullSpace(msg2)){
			styleAlert(msg2);
			return;
		}
		let param2 =get1304PolicyAdditionDtlParam();
		let parJson2 = JSON.stringify(param2);
		let res2 = ajaxPostByJsonParam("../../rin1304dapi/updatefripolicyadditiondtl",parJson2, false);

		if (res2) {
			alert(res2.message)
		}
		break;
	}
}

/**
 * Rin1304臨分分入，刪除保批單附加險明細檔
 * @author Sophia 2021/12/07
 */
function btnDeleteD(){
	
	let isDelete = confirm("確認要刪除保批單附加險檔?");

	if(isDelete){
		//1-參數
		let param ={
				"txtpolicy_no" : $('#txtpolicy_noD').val(),     //保單號碼
				"txtendorse_no": $('#txtendorse_noD').val(),    //批單號碼 
				"numaddr_no"   : $('#txtaddr_noD').val(),       //地址序號
				"numprop_no"   : $('#txtprop_noD').val(),       //標的物序號
				"numaddition_seq" : $('#txtaddition_seq').val()//附加險序號
		} 
		
		let parJson = JSON.stringify(param);
		
		//2-執行刪除
		let res = ajaxPostByJsonParam("../../rin1304dapi/deletefripolicyaddition", parJson, false);
		
		if("000" === res.status){
			alert("刪除保批單附加險檔成功");
			lv2Click();
		}else{
			alert("刪除保批單附加險檔失敗");
		}
	}
}


//檢核必填
function checkRequiredD(){

	let message = "";
	let isRequireStr = "為必填!<br>";
	
	
	//--------------保批單附加險明細頁(D)--------------
	if(checkIsNullSpace($('#additionNoList>select').val())){
		message = message + "附加險代號	" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numamt').val())){
		message = message + "保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numprem').val())){
		message = message + "保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numprercv_rate').val())){
		message = message + "預收比例" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numrate').val())){
		message = message + "費率%" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numcoinamt').val())){
		message = message + "100%保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numcoinprem').val())){
		message = message + "100%保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numlimit_rate').val())){
		message = message + "限額比例" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numlimit').val())){
		message = message + "單次賠款限額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numlimit_year').val())){
		message = message + "全年累計賠款限額	" + isRequireStr;
	}
	return message;
}


//取得保批單附加險明細檔畫面欄位值
function get1304PolicyAdditionDtlParam(){
	var txtAdditionNo = $('#additionNoList>select').val().split("/")[0]; //取下拉選單值

	return{
		//-----------保批單附加險明細檔畫面欄位-------------
		"policyNo":$('#txtpolicy_noD').val(),        //保單號碼
		"endorseNo":$('#txtendorse_noD').val(),      //批單號碼
		"addrNo":$('#txtaddr_noD').val(),            //保單地址序號
		"propNo":$('#txtprop_noD').val(),            //標的物序號
		"additionSeq":$('#txtaddition_seq').val(),   //附加險序號
		"additionNo":txtAdditionNo,                  //附加險代號
		"additionName":$('#txtaddition_name').val(), //附加險名稱
		"amt":$('#numamtD').val(),                   //保額
		"prem":$('#numpremD').val(),                 //保費
		"prercvRate":$('#numprercv_rate').val(),     //預收比例
		"rate":$('#numrate').val(),                  //費率％
		"coinamt":$('#numcoinamt').val(),            //100%保額
		"coinprem":$('#numcoinprem').val(),          //100%保費
		"limitRate":$('#numlimit_rate').val(),       //限額比例
		"limit":$('#numlimit').val(),                //單次理賠限額
		"limitYear":$('#numlimit_year').val(),       //全年累計賠款限額
		"mercno":$('#txtmercno').val(),              //29 險種代碼
		"deductRem":$('#numdeduct_rem').val()        //自付額說明
	}
}


//寫入欄位
function writeFieldForRin1304D(cell){

	//.......保批單附加險明細檔........//
	$('#txtpolicy_noD').val(policyNo);                           //保單號碼
	$('#txtendorse_noD').val(endorseNo);                         //批單號碼
	
	$('#txtaddr_noD').val(cell.txtaddr_no);                      //地址序號
	$('#txtprop_noD').val(cell.txtprop_no);                      //標的物序號
	$('#txtaddition_seq').val(cell.txtaddition_seq);             //附加險序號
	$('#additionNoList').find("select").val(cell.txtaddition_no);//附加險代號
	$('#txtaddition_name').val(cell.txtaddition_name);           //附加險名稱
	$('#numamtD').val(cell.numamt);                              //保額
	$('#numpremD').val(cell.numprem);                            //保費
	$('#numprercv_rate').val(cell.numprercv_rate);               //預收比例
	$('#numrate').val(cell.numrate);                             //費率％
	$('#numcoinamt').val(parseInt(cell.numcoinamt));             //100%保額
	$('#numcoinprem').val(parseInt(cell.numcoinprem));           //100%保費
	$('#numlimit_rate').val(cell.numlimit_rate);                 //限額比例
	$('#numlimit').val(cell.numlimit);                           //單次賠款限額
	$('#numlimit_year').val(cell.numlimit_year);                 //全年累計賠款限額
	$('#txtmercno').val(cell.txtmercno);                         //29 險種代碼
	$('#numdeduct_rem').val(cell.numdeduct_rem);                 //自付額說明
}

//清空欄位
function cleanDataRin1304D(){
	$('#txtaddr_no').val("");                   //地址序號
	$('#txtprop_no').val("");                   //標的物序號
	$('#txtaddition_seq').val("");              //附加險序號
	$('#additionNoList').find("select").val("");//附加險代號
	$('#txtaddition_name').val("");             //附加險名稱
	$('#numamtD').val("");                      //保額
	$('#numpremD').val("");                     //保費
	$('#numprercv_rate').val("");               //預收比例
	$('#numrate').val("");                      //費率％
	$('#numcoinamt').val("");                   //100%保額
	$('#numcoinprem').val("");                  //100%保費
	$('#numlimit_rate').val("");                //限額比例
	$('#numlimit').val("");                     //單次賠款限額
	$('#numlimit_year').val("");                //全年累計賠款限額
	$('#txtmercno').val("");                    //29 險種代碼
	$('#numdeduct_rem').val("");                //自付額說明
}

//新增時欄位預設值
function insertDataRin1304D(parJson){
	cleanDataRin1304D();
	$('#txtpolicy_noD').val(policyNo);           //保單號碼
	$('#txtendorse_noD').val(endorseNo);         //批單號碼
	$('#txtaddr_noD').val(parJson.addrNo);       //地址序號
	$('#txtprop_noD').val(parJson.propNo);       //標的物序號
}


//查詢保批單附加險明細
function query4(param){
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304dapi/querypolicyadditionresult", parJson, false);
	if("000" === res.status){
		//3-寫入資料
		writeFieldForRin1304D(res.data[0]);
	
	}else{
		alert("保批單附加險明細查詢失敗")
	}
}

/*
 * 附加險下拉選單
 */
function ddlAdditionList(){
	let ddlres = ajaxPostByJsonParam("../../rin1304dapi/queryddladditionlist", '', false);
	var ajaxdataSub =ddlres.data;
	let apidata = new Array();
	if("000" === ddlres.status){
		
		
		for(var i = 0; i < ajaxdataSub.length; i++){
			// 組出來是 附加險代號 附加險名稱
			apidata.push({
				text : ajaxdataSub[i].txtaddition_no + " "
						+ ajaxdataSub[i].txtaddition_name,
				value : ajaxdataSub[i].txtaddition_no
			});
		}
		createDdl("additionNoList", apidata, "");
	}
}


/**
 * 給預設值
 */
function setZeroD(){

	if(checkIsNullSpace($('#numamtD').val())){           //保額
		$('#numamtD').val(0);
	}
	if(checkIsNullSpace($('#numpremD').val())){          //保費
		$('#numpremD').val(0);
	}
	if(checkIsNullSpace($('#numprercv_rate').val())){    //預收比率
		$('#numprercv_rate').val(0);
	}
	if(checkIsNullSpace($('#numrate').val())){           //費率%
		$('#numrate').val(0);
	}
	if(checkIsNullSpace($('#numcoinamt').val())){        //100%保額	
		$('#numcoinamt').val(0);
	}
	if(checkIsNullSpace($('#numcoinprem').val())){       //100%保費
		$('#numcoinprem').val(0);
	}
	if(checkIsNullSpace($('#numlimit_rate').val())){     //限額比例
		$('#numlimit_rate').val(0);
	}     
	if(checkIsNullSpace($('#numlimit').val())){          //單次賠款限額
		$('#numlimit').val(0);
	} 
    if(checkIsNullSpace($('#numlimit_year').val())){     //全年累計賠款限額
		$('#numlimit_year').val(0);
	} 
    
}
