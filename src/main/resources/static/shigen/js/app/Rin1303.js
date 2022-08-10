var cessionNo;//合約號
var slipNo;   //更正號	
/*
 * 預設
 */
$(function() {
	$('#billType1').prop("checked", true);		//帳單選擇「正式帳單」
	$('#transferStatus1').prop("checked", true);//轉檔狀況選擇「是，正常臨分處理狀況」
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
			value : 5
		})

		table1303_2.clearData();	//清空再保人表格
		cessionNo = "";				//清空flag合約號值
		slipNo = "";				//清空flag更正號值

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
				value : 5
			})
			//查詢成功後更新flag
			cessionNo = row.getData().txtcession_no;	//合約號
			slipNo = row.getData().txtslip_no;			//更正號

		} else {
			alert("「臨分再保人」查詢失敗!!!請聯絡管理人員!!!");
		}
	}
} ]

// tabulator欄位設置
let columns1303_1 = [ 
	[ "button", "",detailBtn, {widthGrow: 1 }],
	[ "txtcession_no", "合約號", "display",{widthGrow: 2 } ],
	[ "txtslip_no", "更正號", "display",{widthGrow: 2 }],
	[ "txtpolicy_no", "保單號", "display",{widthGrow: 2 } ],
	[ "txtcession_name", "合約名稱", "display",{widthGrow: 5 } ]
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

	let rinComIdArray = [];		//再保人代號
	let acctFlagArray = [];		//已列印否
	let turnFlagArray = [];		//轉檔狀況
	let timesArray = [];		//索引用陣列

	for (let i = 0; i < tableSelected.length; i++) {
		rinComIdArray.push(tableSelected[i].getData().txtrin_com_id);
		acctFlagArray.push(tableSelected[i].getData().txtacct_flag.substring(0,1));
		turnFlagArray.push(tableSelected[i].getData().txtTurn_flag);
		timesArray.push(i)
	}

	//檢核帳單分類與列印狀態(是否已列印)
	if(checkPrint(acctFlagArray)){
		return;
	}	
	
	var successMsg = "";		//列印成功
	var failedMsg = "";			//列印失敗
	var noDataMsg = ""; 		//無資料可印
	var msgBox ="";				//列印結果訊息
	let promiseArray = [];		//promise收集
	
	for (let j of timesArray){		
		let param = {
				"acctFlag" : acctFlagArray[j], 									//已列印否
				"turnFlag" : turnFlagArray[j], 									//轉檔狀況
				"billType" : $("input[name='billType']:checked").val(), 		//帳單類別
				"cessionNo" : cessionNo, 										//合約號
				"slipNo" : slipNo, 												//更正號
				"rinComId" : rinComIdArray[j], 									//再保人代號
				"account" : localStorage.getItem("account")						//使用者帳號
		}		
			
		let pro = ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/printrin1303report", true, false, param,     
				(res) => {
					if("000" === res.status){	
						openFile(res.data['fileName'] +'.pdf', res.data['fileBase64Encode']);	
						successMsg = successMsg + rinComIdArray[j]+"、"
					}else if( "finish" === res.status){
						noDataMsg += rinComIdArray[j]+"、"
					}else{
						failedMsg += rinComIdArray[j]+"、"
					}
				}, (error) => {
					console.log(error);
					failedMsg += rinComIdArray[j]+"、"
				})
					
		promiseArray.push(pro)
	}//for (let j of timesArray) END

	//等收到所有報表response後，顯示列印結果狀態並重整「臨分再保人」欄位
	let proAll = Promise.allSettled(promiseArray);
	proAll.then(()=>{
//user說希望把列印成功的提示訊息拿掉
//		if(!checkIsNullSpace(successMsg)){
//			msgBox += successMsg.substring(0, successMsg.length - 1)+"列印成功!</br>";
//		}
		//列印結果訊息顯示(查無資料或失敗的)
		if(!checkIsNullSpace(noDataMsg)){
			msgBox += noDataMsg.substring(0, noDataMsg.length - 1)+"查無資料可列印!</br>";
		}
		if(!checkIsNullSpace(failedMsg)){
			msgBox += failedMsg.substring(0, failedMsg.length - 1)+"列印失敗!</br>";
		}
		if(!checkIsNullSpace(msgBox)){
			styleAlert(msgBox);
		}
		
		//重整「臨分再保人」欄位
		let param = {
				"slipNo" : slipNo		// 更正號
		}
		ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/queryrincombyslipno", true, false, param,     
				(res) => {
					if (res != null && "000" === res.status) {
						loadData("table1303_2", res.data, {
							type : "dataCount",
							value : 5
						})							
					}else{
						alert("「臨分再保人」重整失敗!!!請聯絡管理人員!!!");
					}
				}, (error) => {
					console.log(error);
					alert("「臨分再保人」重整失敗!!!請聯絡管理人員!!!");
				})								
	})//proAll.then() END
}

/**
 * 「本再保人轉檔狀況」鈕檢核
 * 
 * @author yiting 2021/12/09
 */
function checkTransferBtn() {
	let tableSelectLength = table1303_2.getSelectedRows().length;//再保人table選擇筆數

	if (tableSelectLength !== 1) {
		alert("請選擇一筆再保人資料");
		return;
	}
	
	//檢核若為[未列印]，則不可設定再保人轉檔狀況
	if('N' == table1303_2.getSelectedRows()[0].getData().txtacct_flag.substring(0,1)){
		alert("未列印帳單，不可轉檔; 請列印帳單之後，才可轉檔")
		return;
	}
	$('#myModal').modal('show')//顯示「設定本再保人轉檔狀況」的彈出視窗
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


	let tableSelect = table1303_2.getSelectedRows()[0].getData();			//再保人table選擇的資料
	let transferStatus = $("input[name='transferStatus']:checked").val();	//轉檔狀況

	let param = {
		"billNoExternal" : tableSelect.txtbill_no_external,		// 外部帳單號碼
		"transferStatus" : transferStatus,						// 轉檔狀況
		"slipNo" : slipNo, 										// 更正號
		"rinComId" : tableSelect.txtrin_com_id					// 再保人代號
	}

	ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/updatestatusbynoid", true, false, param,     
			(res) => {
						
			}, (error) => {
							console.log(error);
							alert("設定「再保人轉檔狀況」失敗!!!請聯絡管理人員!!!");
			}).then((res)=>{
				
				if (res != null && "000" === res.status) {
					let param = {
							"slipNo" : slipNo		// 更正號
						}
					//「再保人轉檔狀況」設定成功，就重整「臨分再保人」表格狀態
					ajaxRequestIsAsyncDynimicBytoken("../../rin1303api/queryrincombyslipno", true, false, param,     
							(res) => {
								if (res != null && "000" === res.status) {
											loadData("table1303_2", res.data, {
												type : "dataCount",
												value : 5
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


/**
 * 檢核帳單分類與是否已列印
 * 
 * 若要列印的帳單中有[已列印]，則帳單分類不可選擇[正式帳單]
 * 若要列印的帳單中有[未列印]，則帳單分類不可選擇[重印帳單]
 * @author yiting 2022/02/21
 */
function checkPrint(data){
	
	//列印類型
	let billType = $("input[name='billType']:checked").val();
	
	if(billType == "1" && data.includes('Y')){//選擇[正式帳單]，但所選擇要列印的帳單中又有[已列印]		
		alert("選定列印資料已列印，不可指定列印[正式帳單]，請另指定[重印帳單]")
		return true;
	}else if(billType == "3" && data.includes('N')){//選擇[重印帳單]，但所選擇要列印的帳單中又有[未列印]
		alert("已選定尚未列印帳單之再保人，不可指定[重印帳單]功能")
		return true;
	}else{
		return false;
	}
	
}