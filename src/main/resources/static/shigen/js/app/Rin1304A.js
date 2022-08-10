//.....保批單主檔A.....//
//條件篩選的Radio
var radioValue2 = "";
// 續保單號
var oldPolicyNo;
let chooseRinser = {};
let rinserList = [];
let exchangeList = [];
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
function btnQueryData() {
	//先點回第一層
	if (document.getElementById("lv1")) {
		let getLv1 = document.getElementById("lv1").getElementsByTagName('a');
		getLv1[0].click();
	}

	$('#allPage').hide();

	var filterRadio = $(":radio:checked").length;
	if (filterRadio <= 0) { alert("條件篩選請擇一輸入"); return; }

	// 檢查保單號和批單號是否為空白
	if (checkPolicyEndorseIsNull()) { return; }

	//條件篩選選項
	radioValue2 = $("input[name='radio2']:checked").val();
	policyNo = $('#txtpolicy_no_query').val().trim();
	endorseNo = $('#txtendorse_no_query').val().trim();

	switch (radioValue2) {
		//保批單查詢
		case '1':
			$('#enterAccountRadioA').hide(); // 入帳Radio
			if (getDataSuccess().length <= 0) {
				alert("指定保批單資料不存在");
				$('#allPage').hide();
			} else {
				// 如有資料則跑樹狀圖和右邊頁面
				showPageAndTree();
			}
			break;

		//保批單資料維護
		case '2':
			$('#allPage').hide();
			$('#enterAccountRadioA').hide(); // 入帳Radio
			// 檢核是否已立帳
			checkAcctfFlagIsY();
			if (getDataSuccess().length <= 0) {
				$('#myModal').modal('show'); // 查無資料時顯示是否要新增的視窗
				// 取得續保單號資料
				getOldPolicy();

			} else {
				// 如有資料則跑樹狀圖和右邊頁面
				showPageAndTree();
			}
			break;

		//帳單入帳維護
		case '3':
			if (getDataSuccess().length > 0) {
				// 檢核是否已立帳
				if (checkAcctfFlagIsY() === true) {

					// 如有資料則跑樹狀圖和右邊頁面
					showPageAndTree();
					$("#btnUpdA").click(function() {
						if (radioValue2 === '3') {
							$('#enterAccountRadioA').show(); //入帳按鈕出現
							$('#insertRadioA').hide();       //新增按鈕隱藏
						}
					});
				}
			} else {
				alert("指定保批單資料不存在");
			}
			break;

		//自動產生調整批單(ADJ)	
		case '4':
			if (getDataSuccess().length > 0) {
				// 檢核是否已立帳
				if (checkAcctfFlagIsY() === true) {
					// 如有資料則跑樹狀圖和右邊頁面
					showPageAndTree();
					$('#txtADJEndorseNo').attr("disabled", false); //新增整批單號
					$('#createADJBtn').attr("disabled", false);    //自動產生批單號按紐
				}
			} else {
				alert("指定保批單資料不存在");
			}
			break;
	}

	//條件篩選Radio
	radioValue2 = $("input[name='radio2']:checked").val();
	// 當不是選帳單入帳維護時，入帳日期、轉檔狀態、銷帳日期則不顯示&其他資訊頁面可修改&畫面切換
	switch (radioValue2) {
		//保批單查詢
		case '1':
			$('#CUDBtn').show();           	 //左下新增刪按鈕顯示
			$('#createADJ').hide();        	 //自動調整批單號框隱藏
			$('#transferStateDiv').hide();   //轉檔Radio隱藏
			readonlyForInput();              //讓按鈕、input變disabled
			break;
		//保批單資料維護
		case '2':
			$('#createADJ').hide();       	 //自動調整批單號框隱藏
			$('#CUDBtn').show();          	 //左下新增刪按鈕顯示
			$('#transferStateDiv').hide();   //轉檔Radio隱藏
			readonlyForInput();              //讓按鈕、input變disabled
			break;
		//帳單入帳維護
		case '3':
			$('#CUDBtn').show();           	 //左下新增刪按鈕顯示
			$('#createADJ').hide();        	 //自動調整批單號框隱藏
			$('#transferStateDiv').hide();   //轉檔Radio隱藏
			break;
		//自動產生調整批單(ADJ)
		case '4':
			$('#CUDBtn').hide();             //左下新增刪按鈕隱藏
			$('#transferStateDiv').hide();   //轉檔Radio隱藏
			$('#enterAccountRadioA').hide(); //入帳按鈕關閉
			$('#createADJ').show();          //自動調整批單號框顯示
			$('#txtADJPolicyNo').val($('#txtpolicy_no_query').val().trim());
			break;
		default:

	}

	//重跑一次樹狀圖
	init4Tree();
	//查詢保批單主頁A
	query1();

}


//點擊入帳鈕，下列是否轉帳顯示
$("#enterAccountRadioA").click(function() {
	$('#downRowA').show();
	$('#transferStateDiv').show();
});

//按左下修改時，顯示右下的修改Radio
$('#btnUpdA').click(function() {
	$("#policyPage").find("input, button,select").attr("disabled", false);
	$("#myModalTwo").find("input, button,select").attr("disabled", false);
	$('#downRowA').show();
	$('#insertRadioSpanA').hide();
	$('#updateRadioSpanA').show();
	$("#updateRadioA").prop("checked", true); //設定打勾
});

//判斷兩個日期相差天數
function dateDiff(sDate1, sDate2) {
	var startDate = new Date(sDate1);
	var endDate = new Date(sDate2);

	//把相差的毫秒數轉為天數
	var iDays = parseInt(Math.abs(endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24);
	return iDays;//返回相差的天數
}

//========反算相關開始=======//

function checkAmtPrem() {
	let param = {
		"policyNo": $('#txtpolicy_no').val().trim(),   // 保單號碼
		"endorseNo": $('#txtendorse_no').val().trim() // 批單號碼
	}

	let parJson = JSON.stringify(param);

	let res = ajaxPostByJsonParam("../../rin1304aapi/checkamtprem", parJson, false);
	let amt = res.data.amt;
	let prem = res.data.prem;
	let amtDtl = res.data.amtDtl;
	let premDtl = res.data.premDtl;


	if (amt != amtDtl || prem != premDtl) {
		let isLeave = confirm(
			"明細保額合計:$" + toThousands(String(amtDtl)) + "\n" +
			"保單主檔保額:$" + toThousands(String(amt)) + "\n" +
			"明細保費合計:$" + toThousands(String(premDtl)) + "\n" +
			"保單主檔保費:$" + toThousands(String(prem)) + "\n" +
			"明細保額(或保費)與主檔保額(或保費)不符!" +
			"是否要離開?");
		if (isLeave) {
			backTo1304();
			$('#allPage').hide();
		}
	} else {
		alert("保批單保額/保費反算成功!")
		backTo1304();
		$('#allPage').hide();
	}
}

function sumAmtPrem() {
	let policyNo = $('#txtpolicy_no').val();
	let endorseNo = $('#txtendorse_no').val();
	let param = {
		"policyNo": policyNo,   // 保單號碼
		"endorseNo": endorseNo // 批單號碼
	}
	let parJson = JSON.stringify(param);

	ajaxPostByJsonParam("../../rin1304aapi/sumamtprem", parJson, false);
}

//離開按鈕，做反算檢核
function btnLeave() {
	sumAmtPrem();
	checkAmtPrem();
}
//========反算相關結束=======//

// ========續保相關開始=======//

// 查詢續保單號&新增續保資料
function getOldPolicy() {
	$('#txtold_policy').blur(function() {

		var oldPolicy = $(this).val().trim();
		if (oldPolicy !== '0' && !checkIsNullSpace(oldPolicy)) {
			// 1-參數
			let param = {
				"policyNo": $('#txtold_policy').val().trim(),  // 保單號碼
				"endorseNo": ""                               // 批單號碼
			}

			let parJson = JSON.stringify(param);
			// 2-判斷是否有資料
			let res = ajaxPostByJsonParam("../../rin1304api/queryrin1304", parJson, false);
			let resLength = res.data.length;
			if (resLength >= 1) {
				let isCopy = confirm("複製此保單內容？");
				if (isCopy) {

					let param2 = {
						"policyNo": $('#txtpolicy_no').val().trim(),    // 保單號碼
						"endorseNo": $('#txtendorse_no').val().trim(), // 批單號碼
						"oldPolicy": $('#txtold_policy').val().trim()   // 續保單號
					}
					let parJson2 = JSON.stringify(param2);
					let res2 = ajaxPostByJsonParam("../../rin1304aapi/insertpolicyforoldpolicy", parJson2, false);

					if ("000" === res2.status) {
						alert("新增續保資料成功");
						btnQueryData();
						$("#txtold_policy").attr("disabled", true);
					} else {
						alert("新增續保資料失敗");
						$('#txtold_policy').val("");
					}
				} else {
					$('#txtold_policy').val("");
				}
			} else if (resLength == 0 || resLength == -1) {
				alert("保單號碼有誤，查無該保單資料！");
				// 續保單號
				$('#txtold_policy').val("");
			}
		}
	});
}


// 新增續保單號
function insertOldPolicy() {
	let param = {
		"policyNo": $('#txtpolicy_no').val().trim(),    // 保單號碼
		"endorseNo": $('#txtendorse_no').val().trim(),  // 批單號碼
		"oldPolicy": $('#txtold_policy').val().trim()   // 續保單號
	}
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304aapi/insertfripolicybyoldpolicyno", parJson, false);
	if ("000" === res.status) {
		alert("續保單號新增成功");
	} else {
		alert("續保單號新增失敗");
	}
}

// ========續保相關結束=======//

// 查詢保批單主頁A
function query1() {

	let param = {
		"policyNo": $('#txtpolicy_no_query').val().trim(), // 保單號碼查詢
		"endorseNo": $('#txtendorse_no_query').val().trim()  // 批單號碼查詢
	};
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304aapi/querypolicy", parJson, false);

	if (res.data) {
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
}


/**
 * 查無資料，是否新增的畫面上確認按鈕
 */
function doModifyInsertData() {
	$('#insertRadioSpanA').show();
	$('#myModal').modal('hide');
	init4Tree();
	$('#allPage').show();
	$("#policyPage").find("input, button, select").attr("disabled", false);
	$("#myModalTwo").find("input, button, select").attr("disabled", false);
	$('#insertRadioSpanA').show();
	$("#insertRadioA").attr("checked", true); //設定打勾
	$('#updateRadioSpanA').hide();
	$("#policyLeftBtn").find("button").attr("disabled", true);

	//給預設值
	defaultDataRin1304A();

	//樹狀圖第一層先隱藏
	$('#menu_In').hide();

	//下列按鈕顯示
	$('#downRowA').show();
	
	//查詢保單資料並帶到畫面
	let param = {
		"policyNo": $('#txtpolicy_no_query').val().trim(), 	// 保單號碼
		"endorseNo": ''  									// 批單號碼
	};
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304aapi/querypolicy", parJson, false);

	if (res.data) {
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
}

/*
 * 是否新增頁面_取消按鈕
 */
function closeInsertPage() {
	// 新增頁和所有頁面關閉
	$('#myModal').modal('hide');
	$('#allPage').hide();
}


/**
 * 其他資訊確認按鈕
 */
// TODO 之後要換成檢核
function closeOtherInfo() {
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
	let amt = removeComma($('#numall_amt').val());
	let coinsRate = Number($('#txtcoins_rate').val()); // 共保比率
	let numAmtHn = (amt * 1) * (coinsRate / 100);
	$('#num_amt_hn').val(parseInt(Math.round(numAmtHn)));
	$('#numcom_amt').val(parseInt(Math.round(numAmtHn)));
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
	let prem = removeComma($('#numall_prem').val());
	let coinsRate = Number($('#txtcoins_rate').val()); // 共保比率
	let numPremHn = (prem * 1) * (coinsRate / 100);
	$('#num_prem_hn').val(parseInt(Math.round(numPremHn)));
	$('#numcom_prem').val(parseInt(Math.round(numPremHn)));
});

// 當批加減佣金欄位變動時，自動計 華南保費*佣金率
$('#numcomm_rate').blur(function() {
	// 華南保費
	let premHn = removeComma($('#num_prem_hn').val());
	let commRate = Number($('#numcomm_rate').val()); // 共保比率
	let numPremHn = (premHn * 1) * (commRate / 100);
	$('#numcomm').val(parseInt(Math.round(numPremHn)));
});

// 兌換率根據下拉選單選的值顯示
$('#ddlCurncyList').change(function() {
	// 取下拉選單值
	let currency = $('#ddlCurncyList').find(':selected').val();
	// 以幣別查詢匯率
	let param = {"txtcurrency":currency};
	let paramJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304aapi/queryCurrency", paramJson, false);
	$('#txtCurrencyExchangeRate').val(res.data[0].exrate);
 });


//修改華南總保額，連動修改異動保額
$('#num_amt_hn').change(function(){
	let val = $('#num_amt_hn').val();
	$('#numcom_amt').val(val);
})

//修改華南總保費，連棟修改 異動保費
$('#num_prem_hn').change(function(){
	let val = $('#num_prem_hn').val();
	$('#numcom_prem').val(val);
})


/**
 * 執行按鈕
 */
function dataGoA() {
	// 新增、修改Radio
	let radioValue3 = $("input[name='radio3']:checked").val();
	// 轉檔:是、否、暫緩Radio
	let radioValue4 = $("input[name='radio4']:checked").val();

	switch (radioValue3) {
		// 新增保批單主檔
		case '1':
			// 1-參數
			let param1 = {
				"policyNo": $('#txtpolicy_no_query').val().trim(),       // 保單號碼
				"endorseNo": $('#txtendorse_no_query').val().trim()      // 批單號碼
			}

			let parJson1 = JSON.stringify(param1);

			// 2-判斷是否有資料
			let res1 = ajaxPostByJsonParam("../../rin1304api/queryrin1304", parJson1, false);
			if (res1.data <= 0) {
				// 給預設值
				setZeroA();
				// 檢核必填
				let msg1 = checkRequiredA();
				if (!checkIsNullSpace(msg1)) {
					styleAlert(msg1);
					return;
				}
				//其他檢核
				if (chk1304A()) {
					return;
				}
				// 1-參數
				let param = get1304PolicyParam();
				let parJson = JSON.stringify(param);

				// 2-新增
				let res = ajaxPostByJsonParam("../../rin1304aapi/insertfripolicy", parJson, false);

				if ("000" === res.status) {
					alert("新增保單主檔成功");
					btnQueryData();
					$('#downRowA').hide();
				} else {
					alert("新增保單主檔失敗");
				}
				break;
			}

		// 修改保批單主檔
		case '2':
			//修改主檔
			updatePolicy();
			break;
		//入帳	
		case '3':

			// 判斷轉檔
			switch (radioValue4) {
				// 是
				case 'Y':
					let policyMode = $('#txtpolicy_mode').val();
					if (policyMode === '3' || policyMode === '4' || policyMode === '5') {
						let isUpdate = confirm("是否將明細保期更改同主檔保期?");
						if (isUpdate) {
							let param = {
								"policyDbgn": transDateStrToDateObj($('#txtpolicy_dbgn').val()),
								"policyDend": transDateStrToDateObj($('#txtpolicy_dend').val()),
								"policyNo": $('#txtpolicy_no').val().trim(),       // 保單號碼
								"endorseNo": $('#txtendorse_no').val().trim()      // 批單號碼
							}
							let parJson = JSON.stringify(param);
							// 2-將明細保期更改同主檔保期
							let res = ajaxPostByJsonParam("../../rin1304aapi/updatepolicydates", parJson, false);
							if ("000" === res.status) {
								alert("資料修改完成");
							} else {
								alert("資料修改失敗");
							}
						}
					}
					break;
				// 否
				case 'N':
					break;
				// 暫緩
				case 'H':
					break;
			}
			//修改主檔
			updatePolicy();
			break;
	}
}

//修改主檔
function updatePolicy() {
	// 檢核必填
	let msg3 = checkRequiredA();
	if (!checkIsNullSpace(msg3)) {
		styleAlert(msg3);
		return;
	}
	//其他檢核
	if (chk1304A()) {
		return;
	}

	// 1-參數
	let param3 = get1304PolicyParam();
	let parJson3 = JSON.stringify(param3);
	//2-修改
	console.log(parJson3);
	let res3 = ajaxPostByJsonParam("../../rin1304aapi/updatefripolicy", parJson3, false);

	if ("000" === res3.status) {
		alert("修改保單主檔成功");
		btnQueryData();
		$('#updateRadioSpanA').hide();
	} else {
		alert("修改保單主檔失敗");
	}
}

///**
// * 經紀人和經手人代號欄位顯示
// */
//function dataFrom1102Apop(cell) {
//	$('#txtRin_com_id').val(cell.rinComId)
//	$('#txtcname').val(cell.name)
//}


/**
 * Rin1304臨分分入，刪除保批單主檔
 * 
 * @author Sophia 2021/12/07
 */
function btnDeleteA() {
	// 1-參數
	let param = {
		"policyNo": $('#txtpolicy_no').val().trim(), // 保單號碼
		"endorseNo": $('#txtendorse_no').val().trim()  // 批單號碼
	}
	let parJson = JSON.stringify(param);

	// 2-執行查詢
	let queryall = ajaxPostByJsonParam("../../rin1304aapi/queryfripolicyall", parJson, false);

	// 如果保批單底下有明細
	if (queryall.data === true) {
		let isDelete = confirm("本保批單下仍有相關明細項目, 確定刪除本保批單資料 ?");
		if (isDelete) {
			// 2-執行刪除
			let res = ajaxPostByJsonParam("../../rin1304aapi/deletefripolicy", parJson, false);
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

// =======檢核相關開始========//

// 檢查保單號和批單號是否為空白
function checkPolicyEndorseIsNull(){
	if (checkIsNullSpace($('#txtpolicy_no_query').val().trim()) === true && checkIsNullSpace($('#txtendorse_no_query').val().trim()) === true) {
		alert("保單號碼或批單號碼至少擇一輸入");
		return true;
	}
	return false;
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


// =======檢核相關結束========//

/**
 * 取得保批單主檔畫面欄位值
 */
function get1304PolicyParam() {
    
	return {
		// -----------保批單主檔畫面欄位-------------
		"policyNo": $('#txtpolicy_no').val(),                              // 保單號碼
		"endorseNo": $('#txtendorse_no').val(),                            // 批單號碼
		"fireType": $('#txtfire_type').val(),                              // 險種
		"policyType": $('#txtpolicy_type').val(),                          // 保單類別
		"cinsurant": $('#txtcinsurant').val(),                             // 被保險人
		"einsurant": $('#txteinsurant').val(),                             // 被保險人英文名
		"policyYear": $('#txtpolicy_year').val(),                          // 年期
		"irateType": $('#txtirate_type').val(),                            // 費率性質代號
		"ifloat": $('#txtifloat').val(),                                   // 流動代號
		"policyDbgn": transDateStrToDateObj($('#txtpolicy_dbgn').val()),   // 保單始期
		"policyDend": transDateStrToDateObj($('#txtpolicy_dend').val()),   // 保單終期
		"coinsFlag": $('#txtcoins_flag').val(),                            // 共保代號
		"allAmt": removeComma($('#numall_amt').val()),                     // 累計總保額
		"allPrem": removeComma($('#numall_prem').val()),                   // 累計總保費
		"amt": removeComma($('#num_amt_hn').val()),                        // 華南保額總保額
		"prem": removeComma($('#num_prem_hn').val()),                      // 華南保額總保費
		"comAmt": removeComma($('#numcom_amt').val()),                     // 異動保額
		"comPrem": removeComma($('#numcom_prem').val()),                   // 異動保費
		"comm": removeComma($('#numcomm').val()),                                       // 批加減佣金
		"commRate": $('#numcomm_rate').val(),                              // 佣金率
		"prepayRate": $('#numprepay_rate').val(),                          // 預收比率
		"natureFlag": $('#txtnature_flag').val(),                          // 批加減異動天然災害旗標
		"endReason": $('#txtend_reason').val(),                            // 批改事由
		"calcFlag": $('#txtcalc_flag').val(),                              // 是否分保(Y/N)
		"refNo": $('#txtref_no').val(),                                    // 關連單號
		"policyDprt":transDateStrToDateObj($('#txtpolicy_dprt').val()),    // 保單列印日
		"policyDprtOri":transDateStrToDateObj($('#txtpolicy_dprt').val()),    // 保單列印日
		"changeFlag": $('#txtchange_flag').val(),                          // 調整註記(Y/N/X)
		"policyMode": $('#txtpolicy_mode').val(),                          // 保單來源類別
		"coinsRate": $('#txtcoins_rate').val(),                            // 共保比率
		"oldPolicy": $('#txtold_policy').val(),                            // 續保單號
		"riPolicyno": $('#txtRi_policyno').val(),                          // 分入保單號
		"rinComId": $('#txtRin_com_id').val(),                             // 分入公司
		"txtofficer1": $('#txttxtOfficer1').val(),                         // 經手人代號
		"txtofficer2": $('#txttxtOfficer2').val(),                         // 經手人名稱
		"facFlag": $('#txtFac_flag').val(),                                // 臨分註記(Y/N)
		"brokerId": $('#txtbroker_id').val(),                              // 經紀人代號
		


		// --------------其他資訊頁面-------------------//
		"accenterdate": transDateStrToDateObj($('#dtaAccEnterDate').val()), // 入帳日
		"acctransferstate": $("input[name='radio4']:checked").val(),        // 轉檔狀態
		"receiveDate": $('#txtReceive_date').val(),                         // 承接日期
		"currency": $('#ddlCurncyList').find(":selected").text(),           // 幣別
		"currencyexchangerate": $('#txtCurrencyExchangeRate').val(),        // 兌換率
		"office": $('#txtOffice').val(),                                    // 辦事處
		"countryid": $('#txtCountryID').val(),                              // 國名別代號
		"countryname": $('#txtCountryName').val(),                          // 國名名稱
		"orgPrem": $('#numorg_prem').val(),                                 // 再保費(原幣)
		"orgComm": $('#numorg_comm').val(),                                 // 佣金(原幣)
		"tempFlag": $('#temp_flag').val(),                                  // 暫存保單
		"mkovse": $('#txtMkovse').val()                                     // 境外分入註記
	}
}


/**
 * 寫入欄位
 */
function writeFieldForRin1304A(cell) {

	// 日期格式轉換「yyyy/MM/dd」」
	var policyDbgnA = new Date(cell.txtpolicy_dbgn).format('yyyy/MM/dd');
	var policyDendA = new Date(cell.txtpolicy_dend).format('yyyy/MM/dd');
	var policyDprtA = new Date(cell.txtpolicy_dprt).format('yyyy/MM/dd');
	var accTransferState = cell.txtAccTransferState; //轉檔狀態
	var diffDays = dateDiff(policyDbgnA,policyDendA);//相差天數

	var accTransferStateInput; 
	switch (accTransferState){
	case 'Y' :
		accTransferStateInput = "是";    
		break;
	case 'N' :
		accTransferStateInput = "否";  
		break;
	case 'H' :
		accTransferStateInput = "暫緩";  
		break;
	}
	
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
	$('#diffDays').text(diffDays);                         // 天數
	$('#txtpolicy_dprt').val(policyDprtA);                 // 保單列印日
	$('#txtpolicy_type').val(cell.txtpolicy_type);         // 保單類別
	$('#txtpolicy_year').val(cell.txtpolicy_year);         // 年期
	$('#txtfire_type').val(cell.txtfire_type);             // 險種
	$('#txtirate_type').val(cell.txtirate_type);           // 費率性質代號
	$('#txtcoins_flag').val(cell.txtcoins_flag);           // 共保註記
	$('#txtcoins_rate').val(cell.txtcoins_rate);           // 共保比率
	
	//千分位顯示
	$('#totalAmt').text(toThousands(cell.totalAmt));                     // 共保累計保額
	$('#totalPrem').text(toThousands(cell.totalPrem));                   // 共保累計保額

	$('#numall_amt').val(toThousands(cell.numall_amt));                 // 共保保額總保額
	$('#numamt_1_eq').val(toThousands(cell.numamt_1_eq));               // 共保保額地震險
	$('#numamt_2_ty').val(toThousands(cell.numamt_2_ty));               // 共保保額颱風洪水險
	$('#numamt_3_bi').val(toThousands(cell.numamt_3_bi));               // 共保保額營業中斷險
	$('#num_amt_hn').val(toThousands(cell.num_amt_hn));                 // 華南保額總保額
	$('#numamt_1_eq_hn').val(toThousands(cell.numamt_1_eq_hn));         // 華南保額地震險
	$('#numamt_2_ty_hn').val(toThousands(cell.numamt_2_ty_hn));         // 華南保額颱風洪水險
	$('#numamt_3_bi_hn').val(toThousands(cell.numamt_3_bi_hn));         // 華南保額營業中斷險
	$('#numall_prem').val(toThousands(cell.numall_prem));               // 共保保費總保費
	$('#numprem_1_reins').val(toThousands(cell.numprem_1_reins));       // 共保保費火險及其他附加險
	$('#numprem_2_eq').val(toThousands(cell.numprem_2_eq));             // 共保保費地震險
	$('#numprem_3_ty').val(toThousands(cell.numprem_3_ty));             // 共保保費颱風洪水險
	$('#numprem_4_bi').val(toThousands(cell.numprem_4_bi));             // 共保保費營業中斷險
	$('#num_prem_hn').val(toThousands(cell.num_prem_hn));               // 華南保費總保費
	$('#numprem_1_reins_hn').val(toThousands(cell.numprem_1_reins_hn)); // 華南保費火險及其他附加險
	$('#numprem_2_eq_hn').val(toThousands(cell.numprem_2_eq_hn));       // 華南保費地震險
	$('#numprem_3_ty_hn').val(toThousands(cell.numprem_3_ty_hn));       // 華南保費颱風洪水險
	$('#numprem_4_bi_hn').val(toThousands(cell.numprem_4_bi_hn));       // 華南保費營業中斷險
	$('#numcom_amt').val(toThousands(cell.numcom_amt));                 // 異動保額
	$('#numcom_prem').val(toThousands(cell.numcom_prem));               // 異動保費

	$('#numcomm').val(toThousands(cell.numcomm));                       // 批加減佣金
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
	$('#txtcname').val(cell.txtcname);                     // 經紀人名稱(英文)
	$('#txttxtOfficer1').val(cell.txttxtOfficer1);         // 經手人代號
	$('#txttxtOfficer2').val(cell.txttxtOfficer2);         // 經手人名稱

	// --------------其他資訊頁面-------------------//
	$('#dtaAccEnterDate').val(policyDprtA);                                // 入帳日
	$('#txtAccTransferState').val(accTransferStateInput);                  // 轉檔狀態
	$('#txtReceive_date').val(cell.txtReceive_date);                       // 承接日期
	$('#ddlCurncyList').find("select").val(checkIsNullSpace(cell.txtcurrency)?"NTD":cell.txtcurrency);              // 幣別
	$('#txtCurrencyExchangeRate').val(checkIsNullSpace(cell.txtCurrencyExchangeRate)?"1":cell.txtCurrencyExchangeRate);       // 兌換率
	$('#txtOffice').val(cell.txtOffice);                                   // 辦事處
	$('#txtCountryID').val(cell.txtCountryID);                             // 國名別代號
	$('#txtCountryName').val(cell.txtCountryName);                         // 國名名稱
	$('#numorg_prem').val(Math.round(cell.numorg_prem));                   // 再保費(原幣)
	$('#numorg_comm').val(Math.round(cell.numorg_comm));                   // 佣金(原幣)
	$('#temp_flag').val(cell.temp_flag);                                   // 暫存保單
	$('#txtMkovse').val(cell.txtMkovse);                                   // 境外分入註記

}

//千分位方法
function toThousands(num) {
	num = (num || 0).toString();
	let result = '';
	// 負號額外處理
	let isMinus = false;
	if(!checkIsNullSpace(num)){
		if(num[0] === '-'){
			isMinus = true;
			num = num.substring(1);
		}
	}
    while (num.length > 3) {
        result = ',' + num.slice(-3) + result;
        num = num.slice(0, num.length - 3);
    }
    if (num) { result = num + result; }
    if(isMinus){
    	result = '-' + result;
    }
    return result;
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
				return;
			}else if ("N"===resAll.data[i].acctFlag || checkIsNullSpace (resAll.data[i].acctFlag)){
				return true;
			}
		}
	}
	else if("000"===resAll.status && resAll.data.length<=0){
		return true;
		}

}

// 讓按鈕、input變disabled
function readonlyForInput(){
	new Promise((resolve)=>{
		$("#allPage").find("input, button, select").attr("disabled", true);
		resolve()
	}).then(_=>{
		$("#queryPage").find("input, button ,select").attr("disabled", false);
		$('#btnQueryData').removeAttr("disabled", false);
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
				value : ajaxdataSub[i].curncy
			});
		}
	}
		createDdl("ddlCurncyList", apidata, "");
}

// 自動產生整批單號按鈕
function createADJPolicyBtn(){
	
	// 新保單號ADJ
	let ADJPolicyNo = $('#txtADJPolicyNo').val().trim();
	// 新批單號ADJ
	let endorseNoADJ = $('#txtADJEndorseNo').val().trim();
	
	if(checkIsNullSpace(ADJPolicyNo)||checkIsNullSpace(endorseNoADJ)){
		alert("請輸入新批單號!")
	}else{

		let param ={
                "policyNo":ADJPolicyNo,  // 保單號碼ADJ
                "endorseNo":endorseNoADJ  // 批單號碼ADJ
        } 
		let parJson = JSON.stringify(param);
		let res = ajaxPostByJsonParam("../../rin1304api/queryrin1304", parJson, false);
		if(res.data>=1){
			alert("新增整批單號:"+endorseNoADJ+"已存在");
		}else{

			let isCreate = confirm("確認產生新調整批單 (ADJ)?");
			if(isCreate){
				let param2 ={
						"policyNo":$('#txtpolicy_no').val().trim(),     // 保單號碼
		                "endorseNo":$('#txtendorse_no').val().trim(),   // 批單號碼
		                "policyNoADJ":ADJPolicyNo,                      // 保單號碼ADJ
		                "endorseNoADJ":endorseNoADJ                     // 批單號碼ADJ
		        } 
				let parJson2 = JSON.stringify(param2);
			        
			        // 2-新增新的保批單號
			        let res2 = ajaxPostByJsonParam("../../rin1304aapi/createADJpolicy", parJson2, false);
			        if("000"===res2.status){
			        	alert("產生新調整批單 "+ADJPolicyNo +"-"+endorseNoADJ 
			        		  +"完成，請由〔保批單查詢〕或〔保批單資料維護〕功能選取本批單進行相關作業");
			        }else{
			        	alert("調整批單號失敗");
			        }
			}
		}
	}
}

// 判斷有無資料
function getDataSuccess() {
	// 有資料到allPage
	policyNo = $('#txtpolicy_no_query').val().trim();
	endorseNo = $('#txtendorse_no_query').val().trim();

	// 若只輸入批單號碼，如查有資料則將保單號碼帶入查詢輸入框
	let param2 = {
		"policyNo": policyNo,       // 保單號碼
		"endorseNo": endorseNo      // 批單號碼
	}
	let parJson2 = JSON.stringify(param2);
	let res2 = ajaxPostByJsonParam("../../rin1304api/queryrin1304", parJson2, false);

	if (res2.data.length > 0) {
		$('#txtpolicy_no_query').val(res2.data[0].policyNo);
		$('#txtendorse_no_query').val(res2.data[0].endorseNo);
	}
//	// 所有畫面show!
//	$('#allPage').show();
//
//	init4Tree();

	return res2.data;
}

// 跑畫面和樹狀圖
function showPageAndTree (){
	$('#allPage').show();
	init4Tree();
}

//新增時欄位預設值
function defaultDataRin1304A() {
	$("#policyPage2 input").val("");                                 //清空輸入框

	$('#txtpolicy_no').val($('#txtpolicy_no_query').val().trim());   //保單號碼
	$('#txtendorse_no').val($('#txtendorse_no_query').val().trim()); //批單號碼
	$('#txtifloat').val("N");                                        //流動註記
	$('#numprepay_rate').val("100");                                 //預收比率
	$('#txtcalc_flag').val("Y");                                     //是否分保
	$('#diffDays').text("");                                         //保險起訖總天數
	$('#totalAmt').text("0");                                        //共保累計保額
	$('#totalPrem').text("0");                                       //共保累計保費

	//其他頁面
	$('#ddlCurncyList').find("select").val('NTD');                   //幣別
	$('#txtCurrencyExchangeRate').val("1");                          //兌換率
	$('#txtMkovse').val("N");                                        //境外分入註記

	//保單明細新增鈕禁按
	$('#btnAddB').attr("disabled", true);
}


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
    
    if(checkIsNullSpace($('#txtcalc_flag').val())){        // 是否分保
		$('#txtcalc_flag').val("Y");
	}
}


// rinserTable table start
// 選取按鈕設定
let chooseBtn1 = [{
    name: "選取",
    func: function(row) {
        let data = {
            "lblrin_com_id": row.getData().lblrin_com_id,
            "lblename": row.getData().lblename,
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

// tabulator欄位格式製作
let rinserTableColumnsFormat = createTableColumns(rinserTableColumns)

// 客製tabulator本體
let rinserTableConfigs = {
    layout: "fitDataStretch",
    placeholder: "無資料"
};

// 按鈕設置與功能
let rinserTableRelatedBtns = []

// 檢核警告設定
let rinserTableAlertConfig = {}

// 建立table
let rinserTable = createTable("rinserTable", rinserTableColumnsFormat, rinserTableConfigs, rinserTableRelatedBtns, rinserTableAlertConfig);

// rinserTable table end

function queryRinser() {
    let table = Tabulator.prototype.findTable('#rinserTable')[0];
    let tableSorter = table.getSorters();
    table.clearSort();
    table.setHeight('400px');
    table.setSort(tableSorter);
    genRinserList().then(() => {
        table.setData(rinserList).then(() => {
            $('#rimComPage').modal('show');

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
                $('#txtcname').val(chooseRinser.lblename);
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
            $('#txtcname').val(inputRinserArr[0].lblename);
        }
    }
}


// 取消輸入鈕，讓畫面變為唯讀
function backTo1304() {
	radioDisabled();
}

//其他檢核
function chk1304A(){
	let msg = '';
	let isRequireStr = "<br>";
	if(!chkDateFormat($('#txtpolicy_dbgn').val())){
		msg = msg + '保單起日'+isRequireStr;
	}
	if(!chkDateFormat($('#txtpolicy_dend').val())){
		msg = msg + '保單迄日'+isRequireStr;
	}
	if(!chkDateFormat($('#txtpolicy_dprt').val())){
		msg = msg + '保單列印日'+isRequireStr;
	}
	if(!checkIsNullSpace(msg)){
		styleAlert(msg+"格式需為 YYYY/MM/DD 或是 YYYY/M/D ");
		return true;
	}
	return false;
}
//檢核日期格式需為YYYY/MM/DD 或YYYY/M/D
function chkDateFormat(strInputDate) {
	if (strInputDate == "") return false;
	var d = new Date(strInputDate);
	if (isNaN(d)) return false;
	var arr = strInputDate.split("/");
	return ((parseInt(arr[0], 10) == d.getFullYear()) && (parseInt(arr[1], 10) == (d.getMonth() + 1)) && (parseInt(arr[2], 10) == d.getDate()));
}