//.....保批單主檔A.....//
//條件篩選的Radio
var radioValue2 = "";
// 新增/修改的Radio
var radioValue3 = "";
// 續保單號
var oldPolicyNo;
let chooseRinser = {};
let rinserList = [];
/**
 * 預設
 */
$(function() {
	// 查詢幣別下拉選單
	ddlCurrencyList();
	// 標的物下拉選單
	ddlPropertyList();
	// 附加險下拉選單
	ddlAdditionList();

});

/**
 * 確認按鈕
 */
function doModifyA() {

	var filterRadio = $(":radio:checked").length;
    if(filterRadio<=0){
        alert("條件篩選請擇一輸入");
        return;
    }
	// 檢查保單號和批單號是否為空白
	if (checkIsNullSpace($('#txtpolicy_no_query').val().trim()) === true && checkIsNullSpace($('#txtendorse_no_query').val().trim()) === true) {
		alert("保單號碼或批單號碼至少擇一輸入");
		
	}else{
		
		radioValue2 = $("input[name='radio2']:checked").val();
		if(radioValue2 != '1'){
			// 檢核是否已立帳
			checkAcctfFlagIsY();
		}
	
		// 1-參數
		let param1 ={
				"policyNo":$('#txtpolicy_no_query').val().trim(),       // 保單號碼
				"endorseNo":$('#txtendorse_no_query').val().trim()      // 批單號碼
		} 

	    let parJson1 = JSON.stringify(param1);
		
		// 2-判斷是否有資料
		let res1 = ajaxPostByJsonParam("../../rin1304api/queryrin1304", parJson1, false);
		

		// 沒資料跳新增頁
		if(res1.data<=0){
			
			if(radioValue2 === '1'){
				alert("指定保批單資料不存在");
			}else if(radioValue2 === '2'){

				$('#myModal').modal('show');
				getOldPolicy();

				doModifyInsertData();
			}
			
		// 有資料到allPage
		}else{
			
			policyNo=$('#txtpolicy_no_query').val().trim();
			endorseNo=$('#txtendorse_no_query').val().trim();
			
			// 若只輸入批單號碼，如查有資料則將保單號碼帶入查詢輸入框
			let param2 ={
					"policyNo":$('#txtpolicy_no_query').val().trim(),       // 保單號碼
					"endorseNo":$('#txtendorse_no_query').val().trim()      // 批單號碼
			} 
			let parJson2 = JSON.stringify(param2);
			let res2 = ajaxPostByJsonParam("../../rin1304aapi/querypolicy", parJson2, false);	
			
			$('#txtpolicy_no_query').val(res2.data.txtpolicy_no);
			$('#txtendorse_no_query').val(res2.data.txtendorse_no);
			
		// 所有畫面show!
		$('#allPage').show();
		
		policyNo=$('#txtpolicy_no_query').val().trim();
		endorseNo=$('#txtendorse_no_query').val().trim(); 
		
		radioValue2 = $("input[name='radio2']:checked").val();
		// 當不是選帳單入帳維護時，入帳日期、轉檔狀態、銷帳日期則不顯示
	 	if (radioValue2 != '3'){
			$('#firstRow').hide();
			$('#secondRow').hide();
		}else if(radioValue2 == '3'){
			$('#firstRow').show();
			$('#secondRow').show();
		}
		init4Tree();
		// 查詢保批單主頁A
		query1();
	}
  }
}

// 帶出續保單號
function getOldPolicy(){
$('#txtold_policy').blur(function() {
	
    var oldPolicy = $(this).val().trim();
    var len = oldPolicy.length;
   
    if(len >= 1 && len < 13){
      alert("續保單號必須為13碼");
    }else if(len == 13){
        // 1-參數
        let param ={
                "policyNo":$('#txtold_policy').val().trim(),  // 保單號碼
                "endorseNo": ""                               // 批單號碼
        } 

        let parJson = JSON.stringify(param);
        // 2-判斷是否有資料
        let res = ajaxPostByJsonParam("../../rin1304api/queryrin1304", parJson, false);
        if(res.data>=1){
        	let isCopy = confirm("複製此保單內容？");
        	if(isCopy){
        		$("#leftboxDownBtn").find("button").attr("disabled", true);
//        		// 此保批單號for續保單號樹狀圖需要的參數
        	   	policyNo=$('#txtold_policy').val().trim();
        		endorseNo="";
        		init4Tree();
        		// 查詢保批單主頁A
        		let parJson2 = JSON.stringify(param);
        		let res2 = ajaxPostByJsonParam("../../rin1304aapi/querypolicy", parJson2, false);
//        		$("#leftboxDownBtn").find("button").attr("disabled", true);
        		if ("000" === res2.status) {
        			oldPolicyNo = policyNo;
        			// 寫入資料
        			writeFieldForRin1304A(res2.data);
        			$('#txtpolicy_no').val($('#txtpolicy_no_query').val());
            		$('#txtendorse_no').val($('#txtendorse_no_query').val());
            		$('#txtold_policy').val(res2.data.txtpolicy_no);
        			// 給預設值
        			setZeroA();
        		}
        	}
        }else {
            alert("保單號碼有誤，查無該保單資料！");
            // 續保單號
            $('#txtold_policy').val(oldPolicyNo);
        }
    }

});
}


// 新增續保單號
function insertOldPolicy(){
	let param ={
            "policyNo":$('#txtpolicy_no').val().trim(),    // 保單號碼
            "endorseNo":$('#txtendorse_no').val().trim(),  // 批單號碼
            "oldPolicy":$('#txtold_policy').val().trim()   // 續保單號
    }
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304aapi/insertfripolicybyoldpolicyno", parJson, false);
    if("000"===res.status){
    	alert("續保單號新增成功");
    }else{
    	alert("續保單號新增失敗");
    }
}


// 查詢保批單主頁A
function query1(){	
	
	let param = {
			"policyNo": $('#txtpolicy_no_query').val().trim(), // 保單號碼查詢
			"endorseNo": $('#txtendorse_no_query').val().trim()  // 批單號碼查詢
		};
		let parJson = JSON.stringify(param);
		let res = ajaxPostByJsonParam("../../rin1304aapi/querypolicy", parJson, false);
		
		$('#txtpolicy_no_query').val(res.data.txtpolicy_no);
		$('#txtendorse_no_query').val(res.data.txtendorse_no);
		
		if ("000" === res.status) {
			// 寫入資料
			writeFieldForRin1304A(res.data);
			
			// 給預設值
			setZeroA();
			
		} else {
			alert("保批單主檔查詢錯誤");
		}
	
}


/**
 * 查無資料，是否新增的畫面上確認按鈕
 */
function doModifyInsertData(){
	$('#myModal').modal('hide');
	$('#allPage').show();
	$('#txtpolicy_no').val($('#txtpolicy_no_query').val().trim());
	$('#txtendorse_no').val($('#txtendorse_no_query').val().trim());
}


/*
 * 是否新增頁面_取消按鈕
 */
function closeInsertPage(){
	// 新增頁和所有頁面關閉
	$('#myModal').modal('hide');
	$('#allPage').hide();
}


/**
 * 其他資訊確認按鈕
 */
// TODO 之後要換成檢核
function closeOtherInfo(){
    $('#myModalTwo').modal('hide');
}


// //當華南保額欄位變動時，自動計 保額/共保比率
// $('#num_amt_hn').blur(function() {
// let amtHn = $('#num_amt_hn').val();
// let coinsRate = $('#txtcoins_rate').val(); //共保比率
// let numAmtHn = (amtHn * 1) / (coinsRate / 100);
// $('#numall_amt').val(parseInt(numAmtHn));
// });

// 當共保保額欄位變動時，自動計 保額*共保比率
$('#numall_amt').blur(function() {
	let amt = $('#numall_amt').val();
	let coinsRate = $('#txtcoins_rate').val(); // 共保比率
	let numAmtHn = (amt * 1) * (coinsRate / 100);
	$('#num_amt_hn').val(parseInt(numAmtHn));
});

// //當華南保費欄位變動時，自動計算 保費/共保比率
// $('#num_prem_hn').blur(function() {
// let premHn = $('#num_prem_hn').val();
// let coinsRate = $('#txtcoins_rate').val(); //共保比率
// let numallPrem = (premHn * 1) / (coinsRate / 100);
// $('#numall_prem').val(parseInt(numallPrem));
// });

// 當共保保費欄位變動時，自動計 保費*共保比率
$('#numall_prem').blur(function() {
	let prem = $('#numall_prem').val();
	let coinsRate = $('#txtcoins_rate').val(); // 共保比率
	let numPremHn = (prem * 1) * (coinsRate / 100);
	$('#num_prem_hn').val(parseInt(numPremHn));
});

// 兌換率根據下拉選單選的值顯示
$('#ddlCurncyList').change(function() {
	var currencyExchangeRate = $('#ddlCurncyList').find(':selected').val(); // 取下拉選單值
 $('#txtCurrencyExchangeRate').val(currencyExchangeRate);
  });


/**
 * 執行按鈕
 */
function dataGoA() {
	
    radioValue3 = $("input[name='radio3']:checked").val();

	switch (radioValue3) {
	    // 新增保批單主檔
		case '1':
			// 1-參數
			let param1 ={
					"policyNo":$('#txtpolicy_no_query').val().trim(),       // 保單號碼
					"endorseNo":$('#txtendorse_no_query').val().trim()      // 批單號碼
			} 

		    let parJson1 = JSON.stringify(param1);
			
			// 2-判斷是否有資料
			let res1 = ajaxPostByJsonParam("../../rin1304api/queryrin1304", parJson1, false);
			if(res1.data<=0){
				insertOldPolicy();
				break;
			}else{
				// 給預設值
				setZeroA();
				
				// 1-參數
				let param = get1304PolicyParam();
				let parJson = JSON.stringify(param);
			
				// 檢核必填
				let msg1 = checkRequiredA();
				if (!checkIsNullSpace(msg1)) {
					styleAlert(msg1);
					return;
				}
				// 2-新增
				let res = ajaxPostByJsonParam("../../rin1304aapi/insertfripolicy", parJson, false);

				if ("000" === res.status) {
					alert("新增保單主檔成功");
					doModifyA();
				} else {
					alert("新增保單主檔失敗");
				}
				break;
			}
			
			

			
		// 修改保批單主檔
		case '2':
			// 1-參數
			let param2 = get1304PolicyParam();
			let parJson2 = JSON.stringify(param2);
			
			// 檢核必填
			let msg2 = checkRequiredA();
			if (!checkIsNullSpace(msg2)) {
				styleAlert(msg2);
				return;
			}
            // 2-修改
			let res2 = ajaxPostByJsonParam("../../rin1304aapi/updatefripolicy", parJson2, false);

			if ("000" === res2.status) {
				alert("修改保單主檔成功");
			} else {
				alert("修改保單主檔失敗");
			}
			break;
	}
}

///**
// * 經紀人按鈕
// */
//function btnRinComId() {
//
//	let url = "1102A_pop.html?token=" + localStorage.token + "&type=input";
//	window.open(url, 'newwindow', config = 'height=900,width=1000,toolbar=no');
//
//}


/**
 * 經紀人和經手人代號欄位顯示
 */
function dataFrom1102Apop(cell) {
	$('#txtRin_com_id').val(cell.rinComId)
	$('#txtcname').val(cell.name)
}


/**
 * Rin1304臨分分入，刪除保批單主檔
 * 
 * @author Sophia 2021/12/07
 */
function btnDeleteA() {
	// 1-參數
	let param = {
		"txtpolicy_no": $('#txtpolicy_no').val().trim(), // 保單號碼
		"txtendorse_no": $('#txtendorse_no').val().trim()  // 批單號碼
	}

	let parJson = JSON.stringify(param);
	
	// 2-執行查詢
	let queryall = ajaxPostByJsonParam("../../rin1304aapi/queryfripolicyall", parJson, false);

	// 如果保批單底下有明細
	if (queryall === true) {
		let isDelete = confirm("本保批單下仍有相關明細項目, 確定刪除本保批單資料 ?");
		if (isDelete) {
			// 2-執行刪除
		let res= ajaxPostByJsonParam("../../rin1304aapi/deletefripolicy", parJson, false);
		 alert(res.message);
		 
		 // 刪除後回到查詢頁
		 locationHrefKeepDataType2('Rin1304', '', '');
		}
	} else {// 如果保批單底下沒有明細
		let isDelete = confirm("確定刪除本保批單資料 ?");
		if (isDelete) {
			// 2-執行刪除
			let res2 = ajaxPostByJsonParam("../../rin1304aapi/deletefripolicy", parJson, false);
			alert(res2.message);
			
			 // 刪除後回到查詢頁
			locationHrefKeepDataType2('Rin1304', '', '');
		}
	}
}



/**
 * 檢何必填欄位
 */
function checkRequiredA() {

	let message = "";
	let isRequireStr = "為必填!<br>";

	// --------------保批單主檔頁(A)--------------
	if (checkIsNullSpace($('#txtpolicy_dbgn').val())) {
		message = message + "保單始期" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtpolicy_dend').val())) {
		message = message + "保單終期" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtpolicy_dprt').val())) {
		message = message + "保單列印日" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtpolicy_type').val())) {
		message = message + "保單類別" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtcoins_flag').val())) {
		message = message + "共保註記" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtcoins_rate').val())) {
		message = message + "共保比率" + isRequireStr;
	}

	if (checkIsNullSpace($('#numall_amt').val())) {
		message = message + "總保額" + isRequireStr;
	}

	if (checkIsNullSpace($('#numamt_1_eq').val())) {
		message = message + "共保保額地震險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numamt_2_ty').val())) {
		message = message + "共保保額颱洪險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numamt_3_bi').val())) {
		message = message + "共保保額營業中斷險" + isRequireStr;
	}

	if (checkIsNullSpace($('#num_amt_hn').val())) {
		message = message + "華南保額總保額" + isRequireStr;
	}

	if (checkIsNullSpace($('#numamt_1_eq_hn').val())) {
		message = message + "華南保額地震險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numamt_2_ty_hn').val())) {
		message = message + "華南保額颱洪險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numamt_3_bi_hn').val())) {
		message = message + "華南保額營業中斷險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numall_prem').val())) {
		message = message + "共保保費總保費" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_1_reins').val())) {
		message = message + "共保保費火險及其他附加險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_2_eq').val())) {
		message = message + "共保保費地震險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_3_ty').val())) {
		message = message + "共保保費颱洪險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_4_bi').val())) {
		message = message + "共保保費營業中斷險" + isRequireStr;
	}

	if (checkIsNullSpace($('#num_prem_hn').val())) {
		message = message + "華南保費總保費" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_1_reins_hn').val())) {
		message = message + "華南保費火險及其他附加險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_2_eq_hn').val())) {
		message = message + "華南保費地震險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_3_ty_hn').val())) {
		message = message + "華南保費颱洪險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numprem_4_bi_hn').val())) {
		message = message + "華南保費營業中斷險" + isRequireStr;
	}

	if (checkIsNullSpace($('#numcom_amt').val())) {
		message = message + "異動保額" + isRequireStr;
	}

	if (checkIsNullSpace($('#numcom_prem').val())) {
		message = message + "異動保費" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtcalc_flag').val())) {
		message = message + "是否分保(Y/N)" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtchange_flag').val())) {
		message = message + "調整註記(Y/N/X)" + isRequireStr;
	}

	if (checkIsNullSpace($('#txtFac_flag').val())) {
		message = message + "臨分註記(Y/N)" + isRequireStr;
	}
	return message;
}


/**
 * 取得保批單主檔畫面欄位值
 */
function get1304PolicyParam() {
	return {
		// -----------保批單主檔畫面欄位-------------
		"policyNo": $('#txtpolicy_no').val(),       // 保單號碼
		"endorseNo": $('#txtendorse_no').val(),     // 批單號碼
		"fireType": $('#txtfire_type').val(),       // 險種
		"policyType": $('#txtpolicy_type').val(),   // 保單類別
		"cinsurant": $('#txtcinsurant').val(),      // 被保險人
		"einsurant": $('#txteinsurant').val(),      // 被保險人英文名
		"policyYear": $('#txtpolicy_year').val(),   // 年期
		"irateType": $('#txtirate_type').val(),     // 費率性質代號
		"ifloat": $('#txtifloat').val(),            // 流動代號
		"policyDbgn": $('#txtpolicy_dbgn').val(),   // 保單始期
		"policyDend": $('#txtpolicy_dend').val(),   // 保單終期
		"coinsFlag": $('#txtcoins_flag').val(),     // 共保代號
		"allAmt": $('#numall_amt').val(),           // 累計總保額
		"allPrem": $('#numall_prem').val(),         // 累計總保費
		"amt": $('#num_amt_hn').val(),              // 華南保額總保額
		"prem": $('#num_prem_hn').val(),            // 華南保額總保費
		"comAmt": $('#numcom_amt').val(),           // 異動保額
		"comPrem": $('#numcom_prem').val(),         // 異動保費
		"comm": $('#numcomm').val(),                // 批加減佣金
		"commRate": $('#numcomm_rate').val(),       // 佣金率
		"prepayRate": $('#numprepay_rate').val(),   // 預收比率
		"natureFlag": $('#txtnature_flag').val(),   // 批加減異動天然災害旗標
		"endReason": $('#txtend_reason').val(),     // 批改事由
		"calcFlag": $('#txtcalc_flag').val(),       // 是否分保(Y/N)
		"refNo": $('#txtref_no').val(),             // 關連單號
		"policyDprt": $('#txtpolicy_dprt').val(),   // 保單列印日
		"changeFlag": $('#txtchange_flag').val(),   // 調整註記(Y/N/X)
		"policyMode": $('#txtpolicy_mode').val(),   // 保單來源類別
		"coinsRate": $('#txtcoins_rate').val(),     // 共保比率
		"oldPolicy": $('#txtold_policy').val(),     // 續保單號
		"riPolicyno": $('#txtRi_policyno').val(),   // 分入保單號
		"rinComId": $('#txtRin_com_id').val(),      // 分入公司
		"txtofficer1": $('#txttxtOfficer1').val(),  // 經手人代號
		"txtofficer2": $('#txttxtOfficer2').val(),  // 經手人名稱
		"facFlag": $('#txtFac_flag').val(),         // 臨分註記(Y/N)
		"brokerId": $('#txtbroker_id').val(),       // 經紀人代號
		


		// --------------其他資訊頁面-------------------//
		"accenterdate": $('#dtaAccEnterDate').val(),                // 入帳日
		"acctransferstate": $('#txtAccTransferState').val(),        // 轉檔狀態
		"receiveDate": $('#txtReceive_date').val(),                 // 承接日期
		"currency": $('#ddlCurncyList').find(":selected").text(),   // 幣別
		"currencyexchangerate": $('#txtCurrencyExchangeRate').val(),// 兌換率
		"office": $('#txtOffice').val(),                            // 辦事處
		"countryid": $('#txtCountryID').val(),                      // 國名別代號
		"countryname": $('#txtCountryName').val(),                  // 國名名稱
		"orgPrem": $('#numorg_prem').val(),                         // 再保費(原幣)
		"orgComm": $('#numorg_comm').val(),                         // 佣金(原幣)
		"tempFlag": $('#temp_flag').val(),                          // 暫存保單
		"mkovse": $('#txtMkovse').val()                             // 境外分入註記
	}
}


/**
 * 寫入欄位
 */
function writeFieldForRin1304A(cell) {

	// 千分位
// var totalAmt = cell.numall_amt.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,
// ",");
// var totalPrem = cell.numall_prem.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g,
// ",");
// var totalAmt = cell.numall_amt;
// var totalPrem = cell.numall_prem;
	// 日期格式轉換「yyyy-MM-dd」」
	var policyDbgnA = new Date(cell.txtpolicy_dbgn).format('yyyy-MM-dd');
	var policyDendA = new Date(cell.txtpolicy_dend).format('yyyy-MM-dd');
	var policyDprtA = new Date(cell.txtpolicy_dprt).format('yyyy-MM-dd');
	
	// .......保批單主檔........//
	$('#txtpolicy_no').val(cell.txtpolicy_no);             // 保單號碼
	$('#txtendorse_no').val(cell.txtendorse_no);           // 批單號碼

	$('#numAddr_No').val(cell.numAddr_No);                 // 地址
	$('#txtprop_addr').val(cell.txtprop_addr);             // 標的物地址
	$('#numprop_no').val(cell.numprop_no);                 // 標的物序號
	$('#txtProperty_Name').val(cell.txtProperty_Name);     // 標的物名稱
	$('#numaddition_seq').val(cell.numaddition_seq);       // 附加險
	$('#txtaddition_name').val(cell.txtaddition_name);     // 附加險名稱
	$('#txtold_policy').val(cell.txtold_policy);           // 續保單號
	$('#txtnature_flag').val(cell.txtnature_flag);         // 批加減異動天然災害旗標
	$('#txtcinsurant').val(cell.txtcinsurant);             // 被保險人
	$('#txteinsurant').val(cell.txteinsurant);             // 被保險人英文名
	$('#txtifloat').val(cell.txtifloat);                   // 流動註記
	$('#txtpolicy_dbgn').val(policyDbgnA);                 // 保單始期
	$('#txtpolicy_dend').val(policyDendA);                 // 保單終期
	$('#txtpolicy_dprt').val(policyDprtA);                 // 保單列印日
	$('#txtpolicy_type').val(cell.txtpolicy_type);         // 保單類別
	$('#txtpolicy_year').val(cell.txtpolicy_year);         // 年期
	$('#txtfire_type').val(cell.txtfire_type);             // 險種
	$('#txtirate_type').val(cell.txtirate_type);           // 費率性質代號
	$('#txtcoins_flag').val(cell.txtcoins_flag);           // 共保註記
	$('#txtcoins_rate').val(cell.txtcoins_rate);           // 共保比率
	$('#totalAmt').val(cell.totalAmt);                     // 共保累計保額
	$('#totalPrem').val(cell.totalPrem);                   // 共保累計保額

	$('#numall_amt').val(cell.totalAmt);                 // 共保保額總保額
	$('#numamt_1_eq').val(cell.numamt_1_eq);               // 共保保額地震險
	$('#numamt_2_ty').val(cell.numamt_2_ty);               // 共保保額颱風洪水險
	$('#numamt_3_bi').val(cell.numamt_3_bi);               // 共保保額營業中斷險
	$('#num_amt_hn').val(cell.num_amt_hn);                 // 華南保額總保額
	$('#numamt_1_eq_hn').val(cell.numamt_1_eq_hn);         // 華南保額地震險
	$('#numamt_2_ty_hn').val(cell.numamt_2_ty_hn);         // 華南保額颱風洪水險
	$('#numamt_3_bi_hn').val(cell.numamt_3_bi_hn);         // 華南保額營業中斷險
	$('#numall_prem').val(cell.totalPrem);               // 共保保費總保費
	$('#numprem_1_reins').val(cell.numprem_1_reins);       // 共保保費火險及其他附加險
	$('#numprem_2_eq').val(cell.numprem_2_eq);             // 共保保費地震險
	$('#numprem_3_ty').val(cell.numprem_3_ty);             // 共保保費颱風洪水險
	$('#numprem_4_bi').val(cell.numprem_4_bi);             // 共保保費營業中斷險
	$('#num_prem_hn').val(cell.num_prem_hn);               // 華南保費總保費
	$('#numprem_1_reins_hn').val(cell.numprem_1_reins_hn); // 華南保費火險及其他附加險
	$('#numprem_2_eq_hn').val(cell.numprem_2_eq_hn);       // 華南保費地震險
	$('#numprem_3_ty_hn').val(cell.numprem_3_ty_hn);       // 華南保費颱風洪水險
	$('#numprem_4_bi_hn').val(cell.numprem_4_bi_hn);       // 華南保費營業中斷險
	$('#numcom_amt').val(cell.numcom_amt);                 // 異動保額
	$('#numcom_prem').val(cell.numcom_prem);               // 異動保費

	$('#numcomm').val(cell.numcomm);                       // 批加減佣金
	$('#numcomm_rate').val(cell.numcomm_rate);             // 佣金率
	$('#numprepay_rate').val(cell.numprepay_rate);         // 預收比率
	$('#txtend_reason').val(cell.txtend_reason);           // 批改事由
	$('#txtcalc_flag').val(cell.txtcalc_flag);             // 是否分保(Y/N)
	$('#txtref_no').val(cell.txtref_no);                   // 關連單號
	$('#txtRi_policyno').val(cell.txtRi_policyno);         // 分入保單號
	$('#txtchange_flag').val(cell.txtchange_flag);         // 調整註記(Y/N/X)
	$('#txtFac_flag').val(cell.txtFac_flag);               // 臨分註記(Y/N)
	$('#txtpolicy_mode').val(cell.txtpolicy_mode);         // 保單來源類別
	$('#txtRin_com_id').val(cell.txtRin_com_id);           // 經紀人代號
	$('#txtcname').val(cell.txtcname);                     // 經紀人名稱
	$('#txttxtOfficer1').val(cell.txttxtOfficer1);         // 經手人代號
	$('#txttxtOfficer2').val(cell.txttxtOfficer2);         // 經手人名稱

	// --------------其他資訊頁面-------------------//
	$('#dtaAccEnterDate').val(cell.dtaAccEnterDate);                       // 入帳日
	$('#txtAccTransferState').val(cell.txtAccTransferState);               // 轉檔狀態
	$('#txtReceive_date').val(cell.txtReceive_date);                       // 承接日期
	$('#ddlCurncyList').find("select").val(cell.txtCurrencyExchangeRate);  // 幣別
	$('#txtCurrencyExchangeRate').val(cell.txtCurrencyExchangeRate);       // 兌換率
	$('#txtOffice').val(cell.txtOffice);                                   // 辦事處
	$('#txtCountryID').val(cell.txtCountryID);                             // 國名別代號
	$('#txtCountryName').val(cell.txtCountryName);                         // 國名名稱
	$('#numorg_prem').val(cell.numorg_prem);                               // 再保費(原幣)
	$('#numorg_comm').val(cell.numorg_comm);                               // 佣金(原幣)
	$('#temp_flag').val(cell.temp_flag);                                   // 暫存保單
	$('#txtMkovse').val(cell.txtMkovse);                                   // 境外分入註記


}

/**
 * 檢核是否已立帳
 */
function checkAcctfFlagIsY(){
	let param = {
			"policyNo": $('#txtpolicy_no_query').val().trim(), // 保單號碼
			"endorseNo": $('#txtendorse_no_query').val().trim()  // 批單號碼
		}
		let parJson = JSON.stringify(param);
		// 2-執行查詢
		let resAll = ajaxPostByJsonParam("../../rin1304aapi/checkacctflagisy", parJson, false);
	if("000"===resAll.status && resAll.data.length>0){
		for(var i =0; i<=resAll.data.length;i++) {
			if("Y"===resAll.data[i].acctFlag){
				
				alert("指定保批單已列印正式帳單, 不可修改, 請選用 [保批單查詢] 作業");
				
				// 變查詢狀態
				$('#rdoOnlyQuery').prop('checked', true);
// radioValue2 = $("input[name='radio2']:checked").val();
// readonlyForInput();
				return ;
			}
		}
	}	
}

// 讓按鈕、input變disabled
function readonlyForInput(){
	new Promise((resolve)=>{
		$("#allPage").find("input, button, select").attr("disabled", true);
		resolve()
	}).then(_=>{
		$("#queryPage").find("input, button ,select").attr("disabled", false);
		$('#doModifyA').removeAttr("disabled", false);
		$('#otherInfo').removeAttr("disabled", false);
		$('#otherInfoPage').find("input, button ,select").attr("disabled", true);
	})
}

/**
 * 其他資訊頁面
 */
function otherInfo(){
	$('#myModalTwo').modal('show');
}

/**
 * 其他資訊頁_幣別選單
 */
function ddlCurrencyList(){
	
	let ddlCurncyRes = ajaxPostByJsonParam("../../rin1304aapi/queryddlcurncylist", '', false);
	var ajaxdataSub =ddlCurncyRes.data;
	let apidata = new Array();
	if("000" === ddlCurncyRes.status){
		
		for(var i = 0; i < ajaxdataSub.length; i++){
			// 幣別下拉選單
			apidata.push({
				text : ajaxdataSub[i].curncy ,
				value : ajaxdataSub[i].exRate
			});
		}
	}
		createDdl("ddlCurncyList", apidata, "");
}



// //新增時欄位預設值
// function insertDataRin1304A(){
//
// $('#totalAmt').val(0); //共保累計保額
// $('#totalPrem').val(0); //共保累計保額
//
// $('#numamt_1_eq').val(0); //共保保額地震險
// $('#numamt_2_ty').val(0); //共保保額颱風洪水險
// $('#numamt_3_bi').val(0); //共保保額營業中斷險
//
// $('#numamt_1_eq_hn').val(0); //華南保額地震險
// $('#numamt_2_ty_hn').val(0); //華南保額颱風洪水險
// $('#numamt_3_bi_hn').val(0); //華南保額營業中斷險
//
// $('#numprem_1_reins').val(0); //共保保費火險及其他附加險
// $('#numprem_2_eq').val(0); //共保保費地震險
// $('#numprem_3_ty').val(0); //共保保費颱風洪水險
// $('#numprem_4_bi').val(0); //共保保費營業中斷險
//
// $('#numprem_1_reins_hn').val(0); //華南保費火險及其他附加險
// $('#numprem_2_eq_hn').val(0); //華南保費地震險
// $('#numprem_3_ty_hn').val(0); //華南保費颱風洪水險
// $('#numprem_4_bi_hn').val(0); //華南保費營業中斷險
// $('#numcom_amt').val(0); //異動保額
// $('#numcom_prem').val(0); //異動保費
// }


/**
 * 給預設值
 */
function setZeroA (){
	if(checkIsNullSpace($('#totalAmt').val())){           // 共保累計保額
		$('#totalAmt').val(0);
	}
	if(checkIsNullSpace($('#totalPrem').val())){          // 共保累計保費
		$('#totalPrem').val(0);
	}
	if(checkIsNullSpace($('#numamt_1_eq').val())){        // 共保保額地震險
		$('#numamt_1_eq').val(0);
	}
	if(checkIsNullSpace($('#numamt_2_ty').val())){        // 共保保額颱風洪水險
		$('#numamt_2_ty').val(0);
	}
	if(checkIsNullSpace($('#numamt_3_bi').val())){        // 共保保額營業中斷險
		$('#numamt_3_bi').val(0);
	}
	if(checkIsNullSpace($('#numamt_1_eq_hn').val())){     // 華南保額地震險
		$('#numamt_1_eq_hn').val(0);
	}     
	if(checkIsNullSpace($('#numamt_2_ty_hn').val())){     // 華南保額颱風洪水險
		$('#numamt_2_ty_hn').val(0);
	} 
    if(checkIsNullSpace($('#numamt_3_bi_hn').val())){     // 華南保額營業中斷險
		$('#numamt_3_bi_hn').val(0);
	} 
    if(checkIsNullSpace($('#numprem_1_reins').val())){    // 共保保費火險及其他附加險
		$('#numprem_1_reins').val(0);
	}
    if(checkIsNullSpace($('#numprem_2_eq').val())){       // 共保保費地震險
		$('#numprem_2_eq').val(0);
	}
    if(checkIsNullSpace($('#numprem_3_ty').val())){       // 共保保費颱風洪水險
		$('#numprem_3_ty').val(0);
	}
    if(checkIsNullSpace($('#numprem_4_bi').val())){       // 共保保費營業中斷險
		$('#numprem_4_bi').val(0);
	}
    if(checkIsNullSpace($('#numprem_1_reins_hn').val())){ // 華南保費火險及其他附加險
		$('#numprem_1_reins_hn').val(0);
	}
    if(checkIsNullSpace($('#numprem_2_eq_hn').val())){    // 華南保費地震險
		$('#numprem_2_eq_hn').val(0);
	}
    if(checkIsNullSpace($('#numprem_3_ty_hn').val())){    // 華南保費颱風洪水險
		$('#numprem_3_ty_hn').val(0);
	}
    if(checkIsNullSpace($('#numprem_4_bi_hn').val())){    // 華南保費營業中斷險
		$('#numprem_4_bi_hn').val(0);
	}
    if(checkIsNullSpace($('#numcom_amt').val())){         // 異動保額
		$('#numcom_amt').val(0);
	}
    if(checkIsNullSpace($('#numcom_prem').val())){        // 異動保費
		$('#numcom_prem').val(0);
	}
}


//rinserTable table start
//選取按鈕設定
let chooseBtn1 = [{
    name: "選取",
    func: function(row) {
        let data = {
            "lblrin_com_id": row.getData().lblrin_com_id,
            "lblcname": row.getData().lblcname,
        }
        chooseRinser = data;
    }
}]
let rinserTableColumns = [
    ["button", "", chooseBtn1],
    ["lblrin_com_id", "再保人代號", "display"],
    ["lblename", "再保人英文名稱", "display"],
    ["lblcname", "再保人中文名稱", "display"],
    ["lblsname", "再保人中文簡稱", "display"],
    ["lblremark", "備註", "display"],
    ["dtaUSEMRK", "註銷日", "display"]
]

//tabulator欄位格式製作
let rinserTableColumnsFormat = createTableColumns(rinserTableColumns)

//客製tabulator本體
let rinserTableConfigs = {
    layout: "fitDataStretch",
    placeholder: "無資料"
};

//按鈕設置與功能
let rinserTableRelatedBtns = []

//檢核警告設定
let rinserTableAlertConfig = {}

//建立table
let rinserTable = createTable("rinserTable", rinserTableColumnsFormat, rinserTableConfigs, rinserTableRelatedBtns, rinserTableAlertConfig);

//rinserTable table end

function queryRinser() {
    let table = Tabulator.prototype.findTable('#rinserTable')[0];
    let tableSorter = table.getSorters();
    table.clearSort();
    table.setHeight('400px');
    table.setSort(tableSorter);
    genRinserList().then(() => {
        table.setData(rinserList).then(() => {
            $('#rimComPage').modal('show');
//            $('#myModalTitle').text(function(value) {
//                value == 'broker' ? '經紀人資料' : '再保人資料';
//            });
//            $('input[name="reinserType"]').val(value);

            elementsChangeClass(`#rinserTable-add, #rinserTable-edit, #rinserTable-copy, #rinserTable-del`, "remove", "btn-hide")
            $('input[class="tabulator-select"]').prop('checked', false);
        })
    })
}

function genRinserList() {
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1102apopapi/queryallfricom", true, false, null,
        (res) => {
            if (res.status != "000") {
                console.log(res.message);
                alert("取得再保人清單發生錯誤,請聯絡系統管理員");
            } else {
                rinserList = res.data;
            }
        }, (error) => {
            console.log(error);
            alert("取得再保人清單發生錯誤,請聯絡系統管理員");
        })
}

function genRinserInfo() {
    if (!checkIsNullSpace(chooseRinser.dtaUSEMRK)) {
        let logOutDate = new Date(chooseRinser.dtaUSEMRK);
        let now = new Date();
        if (logOutDate <= now) {
            alert("此再保人已註銷");
            return;
        }
    } else {
        let type = $('input[name="reinserType"]').val();
                $('input[name="txtRin_com_id"]').val(chooseRinser.lblrin_com_id);
                $('#txtcname').val(chooseRinser.lblcname);
        }
        $('#rimComPage').modal('hide');
    }


function genRinserName(value) {
    if (!checkIsNullSpace(value)) {
        let inputRinserArr = rinserList.filter(function(item) {
        	return item.lblrin_com_id == value;
        })

        if (inputRinserArr == null || inputRinserArr.length == 0) {
            alert("查無再保人資料");
            return;
        } else if (inputRinserArr != null || inputRinserArr.length > 0) {

            if (!checkIsNullSpace(inputRinserArr[0].dtaUSEMRK)) {
                let logOutDate = new Date(inputRinserArr[0].dtaUSEMRK);
                let now = new Date();
                if (logOutDate <= now) {
                    alert("此再保人已註銷");
                    return;
                }
            }
        } else {
            $('#txtcname').val(inputRinserArr[0].lblcname);
        }
    }
}
// 回上一頁
function backTo1304() {

	locationHrefKeepDataType2('Rin1304', '', '');
}