var billType; //帳單類別
var cessionNo;//合約號
var slipNo;   //更正號	

/*
 * 預設
 */
$(function() {
	$('#billType1').prop("checked", true);
	$('#transferStatus1').prop("checked", true);
})


/**
 * 「合約號」搜尋
 * 
 * @author yiting 2021/11/29
 */
function btnQueryRin1303() {

	// 1-參數
	let param = {
		"cessionNo" :  $('#txtcession_no1').val().trim()		// 合約號
	}

	let parJson = JSON.stringify(param);

	// 2-執行查詢
	let res = ajaxPostByJsonParam("../../rin1303api/querycessionnolist",
			parJson, false);

	if ("000" === res.status) {
		loadData("table1303_1", res.data, {
			type : "dataCount",
			value : 10
		})
		billType = $("input[name='billType']:checked").val();

		table1303_2.clearData();
		cessionNo = "";
		slipNo = "";

	} else {
		alert("「合約號」查詢失敗!!!請聯絡管理人員!!!");
	}

}
// --------------------合約列表table-----------------------------------------------------------------
// 選擇按鈕設定
let detailBtn = [ {
	name : "選擇",
	func : function(row) {
		// 1-參數
		let param = {
			"slipNo" : row.getData().txtslip_no		// 更正號
		}

		let parJson = JSON.stringify(param);

		// 2-執行查詢
		let res = ajaxPostByJsonParam("../../rin1303api/queryrincombyslipno",
				parJson, false);

		if ("000" === res.status) {
			loadData("table1303_2", res.data, {
				type : "dataCount",
				value : 10
			})

			cessionNo = row.getData().txtcession_no;
			slipNo = row.getData().txtslip_no;

		} else {
			alert("「臨分再保人」查詢失敗!!!請聯絡管理人員!!!");
		}
	}
} ]

// tabulator欄位設置
let colwidthDe = 45;
let wordTitle = 14;
let columns1303_1 = [ [ "button", "", detailBtn,{minWidth:wordTitle}],
[ "txtcession_no", "合約號", "display" ],
[ "txtslip_no", "更正號", "display"],
[ "txtpolicy_no", "保單號", "display" ],
[ "txtcession_name", "合約名稱", "display" ]

]

// tabulator欄位格式製作
let colsFormat1303_1 = createTableColumns(columns1303_1)

// 客製tabulator本體
let tableConfigs1303_1 = {}

// 按鈕設置與功能
let tableRelatedBtns1303_1 = []

// 檢核警告設定
let alertConfig1303_1 = {}

// 建立table
let table1303_1 = createTable("table1303_1", colsFormat1303_1,
		tableConfigs1303_1, tableRelatedBtns1303_1, alertConfig1303_1)

// --------------------再保人列表table-----------------------------------------------------------------

// tabulator欄位設置
let columns1303_2 = [ [ "checkbox", {showBtn : true} ],
[ "txtrin_com_id", "再保人代號", "display"],
[ "txtacct_flag", "已列印否", "display" ],
[ "txtTurn_flag", "轉檔狀況", "display" ],
[ "txtbill_no", "帳單號碼", "display"],
[ "txtbill_no_external", "外部帳單號碼", "display" ],
[ "txtename", "再保人名稱", "display" ] ]

// tabulator欄位格式製作
let colsFormat1303_2 = createTableColumns(columns1303_2)

// 客製tabulator本體
let tableConfigs1303_2 = {}

// 按鈕設置與功能
let tableRelatedBtns1303_2 = []

// 檢核警告設定
let alertConfig1303_2 = {}

// 建立table
let table1303_2 = createTable("table1303_2", colsFormat1303_2,
		tableConfigs1303_2, tableRelatedBtns1303_2, alertConfig1303_2)

// -------------------------------------------------------------------------------------
/**
 * 列印
 * 
 * @author yiting 2021/12/08
 */
function printRin1303() {
	let tableSelected = table1303_2.getSelectedRows();
	if (tableSelected.length === 0) {
		alert("請選擇一筆以上的再保人資料");
		return;
	}

	let rinComIdArray = [];
	let acctFlagArray = [];
	let turnFlagArray = [];

	for (let i = 0; i < tableSelected.length; i++) {
		rinComIdArray.push(tableSelected[i].getData().txtrin_com_id);
		acctFlagArray.push(tableSelected[i].getData().txtacct_flag);
		turnFlagArray.push(tableSelected[i].getData().txtTurn_flag);
	}

	let param = {
		"acctFlagArray" : acctFlagArray, 				// 已列印否
		"turnFlagArray" : turnFlagArray, 				// 轉檔狀況
		"billType" : billType, 							// 帳單類別
		"cessionNo" : cessionNo, 						// 合約號
		"slipNo" : slipNo, 								// 更正號
		"rinComIdArray" : rinComIdArray, 				// 再保人代號陣列
		"account" : localStorage.getItem("account")		// 使用者帳號
	}

	
	ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/printrin1303report", true, false, param,     
			(res) => {
						
			}, (error) => {
							console.log(error);
							alert("列印失敗,請聯絡系統管理員");
			}).then((res)=>{
				if (res != null && "000" === res.status) {
					let param = {
							"slipNo" : slipNo		// 更正號
						}
					ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/queryrincombyslipno", true, false, param,     
							(res) => {
								if (res != null && "000" === res.status) {
											loadData("table1303_2", res.data, {
												type : "dataCount",
												value : 10
											})							
									}else{
										alert("「臨分再保人」重整失敗!!!請聯絡管理人員!!!");
									}
							}, (error) => {
											console.log(error);
											alert("「臨分再保人」重整失敗!!!請聯絡管理人員!!!");
							})
					
					alert(res.message);
					
				}else{
					alert("列印失敗,請聯絡系統管理員");
				}
			})
	
}

/**
 * 「本再保人轉檔狀況」鈕檢核
 * 
 * @author yiting 2021/12/09
 */
function checkTransferBtn() {
	let tableSelectLength = table1303_2.getSelectedRows().length;

	if (tableSelectLength !== 1) {
		alert("請選擇一筆再保人資料");
		return;
	}
	$('#myModal').modal('show')
}

/**
 * 設定本再保人轉檔狀況
 * 
 * @author yiting 2021/12/09
 */
function doModify(thisobj) {

	var h = $(thisobj).closest("div[id='myModal']").find("#foo").eq(0).html();

	var ins = $(thisobj).closest("div[id='myModal']").find("#foo").eq(0).find(
			"input");


	let tableSelect = table1303_2.getSelectedRows()[0].getData();
	let transferStatus = $("input[name='transferStatus']:checked").val();

	let param = {
		"billNoExternal" : tableSelect.txtbill_no_external,// 外部帳單號碼
		"transferStatus" : transferStatus, // 轉檔狀況
		"slipNo" : slipNo, // 更正號
		"rinComId" : tableSelect.txtrin_com_id
	// 再保人代號
	}
//
//	let parJson = JSON.stringify(param);
//	let res = ajaxPostByJsonParam("../../rin1303api/updatestatusbynoid",
//			parJson, false);
//
//	if ("000" === res.status) {
//		$('#myModal').modal('hide')
//	} else {
//		alert("設定「再保人轉檔狀況」失敗!!!請聯絡管理人員!!!");
//	}
	ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/updatestatusbynoid", true, false, param,     
			(res) => {
						
			}, (error) => {
							console.log(error);
							alert("設定「再保人轉檔狀況」失敗!!!請聯絡管理人員!!!");
			}).then((res)=>{
				//設定成功，重整「臨分再保人」表格狀態
				if (res != null && "000" === res.status) {
					let param = {
							"slipNo" : slipNo		// 更正號
						}
					ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/queryrincombyslipno", true, false, param,     
							(res) => {
								if (res != null && "000" === res.status) {
											loadData("table1303_2", res.data, {
												type : "dataCount",
												value : 10
											})							
									}else{
										alert("「臨分再保人」重整失敗!!!請聯絡管理人員!!!");
									}
							}, (error) => {
											console.log(error);
											alert("「臨分再保人」重整失敗!!!請聯絡管理人員!!!");
							})
					
					alert(res.message);
				}else{
					alert("設定「再保人轉檔狀況」失敗!!!請聯絡管理人員!!!");
				}
			})
	$('#myModal').modal('hide')	
			
	// 恢復預設
	$('#transferStatus1').prop("checked", true);

}
