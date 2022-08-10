//.....保批單附加險明細檔D.....//


//當保額欄位變動時，自動計算     保額/共保比率
$('#numamtD').blur(function() {
	let amt = removeComma($('#numamtD').val());
	var coinsRate = removeComma($('#txtcoins_rate').val());
	let numhundredamt = Math.round((amt*1)/(coinsRate/100)); //四捨五入
	$('#numcoinamt').val(parseInt(Math.round(numhundredamt)));
	calNumrate(); // 計算費率
});

//當保費欄位變動時，自動計算     保費/共保比率
$('#numpremD').blur(function() {
	let prem = removeComma($('#numpremD').val());
	var coinsRate = removeComma($('#txtcoins_rate').val());
	let numhundredprem = Math.round((prem*1)/(coinsRate/100)); //四捨五入
	$('#numcoinprem').val(parseInt(Math.round(numhundredprem)));
	calNumrate(); // 計算費率
});

//附加險名稱根據下拉選單選的值顯示
$('#additionNoList').change(function() {
	var txtAdditionNo = $('#additionNoList').find(':selected').text().split(" ")[0]; //取下拉選單序號
	var txtAdditionName = $('#additionNoList').find(':selected').text().split(" ")[1]; //取下拉選單文字
	$('#txtaddition_name').val(txtAdditionName);//附加險名稱
	$('#txtmercnoD').val(txtAdditionNo);        //30險種代碼
});


// 100%保費, 100%保額 change 事件
$('#numcoinamt, #numcoinprem').blur(function(){
	calNumrate();
})

//費率% = 100%保費/100%保額 (四捨五入至小數點第四位)
function calNumrate(){
	try {
		let coinamt = removeComma($('#numcoinamt').val());			//100%保額
		let coinprem = removeComma($('#numcoinprem').val());		//100%保費
		let numrate = getArithmetic(coinprem, coinamt, "/", "7");
		// 顯示為千分比
		numrate = getArithmetic(numrate, "1000", "*");
		$('#numrate').val(numrate);
	} catch (e) {
		console.log(e.stack);
		$('#numrate').val("0");
	}
}

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

		if("000" !== res.status){
			alert(res.message);
		}else if (res){
			alert(res.message);
			$('#downRowD').hide();
			//樹狀圖立刻顯示
			var thisPreLv4=$(memoClickElement).parent("div").eq(0)[0].className;
			var childenDiv4=$(memoClickElement).parent("div").children('div').eq(0)[0].className;
			if(thisPreLv4 === 'menu-section menu-lv-4' && childenDiv4 === 'menu-item'){
				memoClickElement.parentElement.previousSibling.click();
				memoClickElement.click();
			}else{
				immediatelyShow();
			}
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

		if("000" !== res2.status){
			alert(res2.message);
		}else if(res2){
			alert(res2.message);
			$('#downRowD').hide();
			    //樹狀圖立刻顯示
				memoClickElement.parentElement.previousSibling.click();
				memoClickElement.click();
		}
		break;
	}
}

//======按鈕控制開始=====//

//按左下修新增時，顯示右下的新增Radio
$('#btnAddD').click(function() {
	$("#policyPageD").find("input, button,select").attr("disabled", false);
	$("#updateRadioD").removeAttr("checked");  //修改Radio取消勾選
	$('#downRowD').show();                     //RadioRow 顯示	
	$('#insertRadioSpanD').show();             //新增Radio顯示
	$("#insertRadioD").prop("checked", true);  //新增設定打勾
	$('#updateRadioSpanD').hide();             //修改Radio隱藏
});

//按左下修改時，顯示右下的修改Radio
$('#btnUpdD').click(function() {
	$("#policyPageD").find("input, button,select").attr("disabled", false);
	$("#insertRadioD").removeAttr("checked");  //新增adio取消勾選
	$('#downRowD').show();                     //RadioRow 顯示
	$('#insertRadioSpanD').hide();             //新增Radio隱藏
	$("#updateRadioD").prop("checked", true);  //修改設定打勾
	$('#updateRadioSpanD').show();             //修改Radio顯示
});


//======按鈕控制結束=====//

/**
 * Rin1304臨分分入，刪除保批單附加險明細檔
 * @author Sophia 2021/12/07
 */
function btnDeleteD(){
	
	let isDelete = confirm("確認要刪除保批單附加險檔?");

	if(isDelete){
		//1-參數
		let param ={
				"policyNo" : $('#txtpolicy_noD').val(),     //保單號碼
				"endorseNo": $('#txtendorse_noD').val(),    //批單號碼 
				"addrNo"   : $('#txtaddr_noD').val(),       //地址序號
				"propNo"   : $('#txtprop_noD').val(),       //標的物序號
				"additionSeq" : $('#txtaddition_seq').val() //附加險序號
		} 
		
		let parJson = JSON.stringify(param);
		
		//2-執行刪除
		let res = ajaxPostByJsonParam("../../rin1304dapi/deletefripolicyaddition", parJson, false);
		
		if("000" === res.status){
			alert("刪除保批單附加險檔成功");
			////樹狀圖即時更新
			memoClickElement.parentElement.previousSibling.click();
			memoClickElement.click();

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
		"policyNo":$('#txtpolicy_noD').val(),            //保單號碼
		"endorseNo":$('#txtendorse_noD').val(),          //批單號碼
		"addrNo":$('#txtaddr_noD').val(),                //保單地址序號
		"propNo":$('#txtprop_noD').val(),                //標的物序號
		"additionSeq":$('#txtaddition_seq').val(),       //附加險序號
		"additionNo":txtAdditionNo,                      //附加險代號
		"additionName":$('#txtaddition_name').val(),     //附加險名稱
		"amt":removeComma($('#numamtD').val()),          //保額
		"prem":removeComma($('#numpremD').val()),        //保費
		"prercvRate":$('#numprercv_rate').val(),         //預收比例
		"rate":$('#numrate').val(),                      //費率％
		"coinamt":removeComma($('#numcoinamt').val()),   //100%保額
		"coinprem":removeComma($('#numcoinprem').val()), //100%保費
		"limitRate":$('#numlimit_rate').val(),           //限額比例
		"limit":$('#numlimit').val(),                    //單次理賠限額
		"limitYear":$('#numlimit_year').val(),           //全年累計賠款限額
		"mercno":$('#txtmercnoD').val(),                 //30險種代碼
		"deductRem":$('#numdeduct_rem').val()            //自付額說明
	}
}


//寫入欄位
function writeFieldForRin1304D(cell){
	
	//顯示千分位
    let amt = toThousands(cell.numamt);   //保額
    let prem = toThousands(cell.numprem); //保費
    let coinAmt = toThousands((parseInt(cell.numcoinamt)));  //100%保額
    let coinPrem = toThousands((parseInt(cell.numcoinprem)));//100%保費
	//.......保批單附加險明細檔........//
	$('#txtpolicy_noD').val(policyNo);                           //保單號碼
	$('#txtendorse_noD').val(endorseNo);                         //批單號碼
	
	$('#txtaddr_noD').val(cell.txtaddr_no);                      //地址序號
	$('#txtprop_noD').val(cell.txtprop_no);                      //標的物序號
	$('#txtaddition_seq').val(cell.txtaddition_seq);             //附加險序號
	$('#additionNoList').find("select").val(cell.txtaddition_no.trim());//附加險代號
	$('#txtaddition_name').val(cell.txtaddition_name);           //附加險名稱
	$('#numamtD').val(amt);                                      //保額
	$('#numpremD').val(prem);                                    //保費
	$('#numprercv_rate').val(cell.numprercv_rate);               //預收比例
	$('#numcoinamt').val(coinAmt);                               //100%保額
	$('#numcoinprem').val(coinPrem);                             //100%保費
	$('#numlimit_rate').val(cell.numlimit_rate);                 //限額比例
	$('#numlimit').val(cell.numlimit);                           //單次賠款限額
	$('#numlimit_year').val(cell.numlimit_year);                 //全年累計賠款限額
	$('#txtmercnoD').val(cell.txtmercno);                        //30 險種代碼
	$('#numdeduct_rem').val(cell.numdeduct_rem);                 //自付額說明
	
	calNumrate(); // 計算費率
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
	$('#txtmercnoD').val("");                   //30 險種代碼
	$('#numdeduct_rem').val("");                //自付額說明
}

//新增時欄位預設值
function insertDataRin1304D(parJson){
	cleanDataRin1304D();
	$('#txtpolicy_noD').val(policyNo);           //保單號碼
	$('#txtendorse_noD').val(endorseNo);         //批單號碼
	$('#txtaddr_noD').val(parJson.addrNo);       //地址序號
	$('#txtprop_noD').val(parJson.propNo);       //標的物序號
	$('#numprercv_rate').val("100");             //預收比例
	$('#numlimit_rate').val("100");              //限額比例
	getMaxAdditionSeq();                         //附加險序號
	
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
function ddlAdditionList() {
	let ddlres = ajaxPostByJsonParam("../../rin1304dapi/queryddladditionlist", '', false);
	var ajaxdataSub = ddlres.data;
	let apidata = new Array();
	if ("000" === ddlres.status) {

		for (var i = 0; i < ajaxdataSub.length; i++) {
			// 組出來是 附加險代號 附加險名稱
			apidata.push({
				text: ajaxdataSub[i].txtaddition_no.trim() + " "
					+ ajaxdataSub[i].txtaddition_name,
				value: ajaxdataSub[i].txtaddition_no.trim()
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
	
	if(checkIsNullSpace($('#numrate').val())){           //費率%
		$('#numrate').val(0);
	}
	if(checkIsNullSpace($('#numcoinamt').val())){        //100%保額	
		$('#numcoinamt').val(0);
	}
	if(checkIsNullSpace($('#numcoinprem').val())){       //100%保費
		$('#numcoinprem').val(0);
	}    
	if(checkIsNullSpace($('#numlimit').val())){          //單次賠款限額
		$('#numlimit').val(0);
	} 
    if(checkIsNullSpace($('#numlimit_year').val())){     //全年累計賠款限額
		$('#numlimit_year').val(0);
	} 

}

//取附加險序號最大值
function getMaxAdditionSeq() {
	let param = {
		"policyNo": $('#txtpolicy_noC').val(),  //保單號碼
		"endorseNo": $('#txtendorse_noC').val(), //批單號碼 
		"addrNo": $('#numaddr_noC').val(),    //地址序號
		"propNo": $('#numprop_noC').val()     //標的物序號
	}

	let parJson = JSON.stringify(param);
	//2-查詢
	let res = ajaxPostByJsonParam("../../rin1304dapi/getmaxadditionseq", parJson, false);
	if ("000" === res.status) {
		$('#txtaddition_seq').val(res.data);
	}
}