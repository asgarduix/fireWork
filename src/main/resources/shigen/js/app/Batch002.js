//tabulator欄位設置
let columnsBatch002 = [ [ "checkbox", {
	showBtn : true
} ], [ "batchid", "Batch元件", "input", {
	width : "13%"
} ], [ "batchdescription", "程式名稱", "input", {
	width : "27%"
} ], [ "startDateString", "開始時間", "input", {
	width : "20%"
} ], [ "endDateString", "結束時間", "input", {
	width : "20%"
} ], [ "processstatusString", "執行狀態", "input", {
	width : "20%"
} ] ];

// tabulator欄位格式製作
let colsFormat = createTableColumns(columnsBatch002);

// 客製tabulator本體
let tableConfigs = {}

// 按鈕設置與功能
let tableRelatedBtns = []

// 檢核警告設定
let alertConfig2 = {
	type : "dom",
	position : "#tableBatch002-errMsg",
	displayTime : 0, // 毫秒，訊息顯示時間，設置為0不清空
}

// 建立table
let tableBatch002 = createTable("tableBatch002", colsFormat, tableConfigs,
		tableRelatedBtns, alertConfig2);

//預設
$(function() {
	btnQueryBatch002();
});		

//Batch002-批次作業監視器 查詢
function btnQueryBatch002() {

	let param = {
		"account" : localStorage.getItem("account")
	}

	let parJson = JSON.stringify(param);

	let res = ajaxPostByJsonParam("../../batch002api/queryuseaccount", parJson,
			false);

	if ('000' === res.status) {

		loadData("tableBatch002", res.data, {
			type : "dataCount",
			value : 15
		})

	} else {
		alert("批次作業監視器_資料搜尋 失敗!!!請聯絡管理人員!!!");
	}
}

//檢視作業紀錄(Log)
function checkLog() {
	
	if(tableBatch002.getSelectedRows().length !== 1){
		alert("請選擇一筆資料");
		return;
	}

	let data = {
		"taskid" : tableBatch002.getSelectedRows()[0].getData().taskid
	}
	let parJson = JSON.stringify(data)

	locationHrefKeepDataType2('batch002A', '', parJson);
}

//強制終止
function stop() {
	
	if(tableBatch002.getSelectedRows().length !== 1){
		alert("請選擇一筆資料");
		return;
	}

	let processstatus = tableBatch002.getSelectedRows()[0].getData().processstatus;

	if (processstatus == '0') {

		let taskid = tableBatch002.getSelectedRows()[0].getData().taskid

		let param = {
			"taskid" : taskid
		}

		let parJson = JSON.stringify(param);

		ajaxPostByJsonParam("../../batch002api/updatebatchqueuebyterminate",
				parJson, false);

		btnQueryBatch002();

	} else

	{
		alert("[尚未執行]才可強制終止！")
	}
}

//開啟報表
function openExcelPdf() {
	
	if(tableBatch002.getSelectedRows().length !== 1){
		alert("請選擇一筆資料");
		return;
	}else{
		
		let data = {
				"taskid" : tableBatch002.getSelectedRows()[0].getData().taskid
			}
			let parJson = JSON.stringify(data)

			let res = ajaxPostByJsonParam(
					"../../batch002api/querybatchreprotaccesspath", parJson, false);

			if ('000' === res.status) {

				if (!checkIsNullSpace(res.data.fileBase64Encode)) {
					openFile(res.data.fileName, res.data.fileBase64Encode);
				}

			} else {
				alert("批次作業監視器_開啟報表 失敗!!!請聯絡管理人員!!!");
			}
	}

}

//更新間隔頻率-設定
function reload() {

	alert("更新間隔頻率成功！");
	var timeReload = $('#timeReload').val();
	setInterval("btnQueryBatch002()", timeReload * 1000);
}

//報表選項
$("#reportType").change(
		function() {

			let param = {
				"account" : localStorage.getItem("account"),
				"reportType" : $("#reportType").val()
			}

			let parJson = JSON.stringify(param);

			// 根據不同報表選項，與初始畫面差異在條件多batchqueue的table的batchid欄位條件
			let res = ajaxPostByJsonParam(
					"../../batch002api/queryuseaccountandbatchid", parJson,
					false);

			if ('000' === res.status) {

				loadData("tableBatch002", res.data, {
					type : "dataCount",
					value : 15
				})

			} else {
				alert("批次作業監視器_資料搜尋 失敗!!!請聯絡管理人員!!!");
			}

		});
