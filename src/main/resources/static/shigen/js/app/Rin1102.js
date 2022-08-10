const RIN_1102A = locationHrefKeepDataFetch(); //由「RIN1102A」頁面帶入的參數:searchYear(搜尋年度)、
																		//searchNo(搜尋代號)

//預設
$(function(){
	//若有之前的搜尋紀錄，則進來時恢復原先搜查狀態
	if(RIN_1102A){
		//1-參數
		let param = {
				"treatyYear":RIN_1102A.searchYear,	//合約年度
				"treatyNo":RIN_1102A.searchNo		//合約代號
		}
		
		let parJson = JSON.stringify(param);
		
		//2-執行查詢
		let res = ajaxPostByJsonParam("../../rin1102api/querytreatys", parJson, false);
		
		if("000" === res.status){
			loadData("table1102", res.data, {type:"dataCount", value:7})
		}else{
			alert("「再保合約資料」查詢失敗!!!請聯絡管理人員!!!");
		}
		
		//3-寫回再保人查詢input
		$('#txttreaty_year1').val(RIN_1102A.searchYear);
		$('#txttreaty_no1').val(RIN_1102A.searchNo);
	}
})

//新增鈕
function btnInsertRin1102(){
	let data = {
			"searchYear" : $('#txttreaty_year1').val().trim(),	//搜尋條件(合約年度)
			"searchNo" : $('#txttreaty_no1').val().trim(),		//搜尋條件(合約代號)
			"mode":"insert"										//進入Rin1102A的模式為新增
	}
	
	let parJson = JSON.stringify(data)
	locationHrefKeepDataType2('Rin1102A', '', parJson);
	
}	


//明細按鈕設定
let detailBtn = [{
	name:"明細", 
	func:function(row){
		let data = {
				"treatyYear" : row.getData().txttreaty_year,		//合約年度
				"treatyNo" : row.getData().txttreaty_no,			//合約代號
				"searchYear" : $('#txttreaty_year1').val().trim(),	//搜尋條件(合約年度)
				"searchNo" : $('#txttreaty_no1').val().trim(),		//搜尋條件(合約代號)
				"mode":"update"										//進入Rin1102A的模式為修改
		}
		let parJson = JSON.stringify(data)
		locationHrefKeepDataType2('Rin1102A', '', parJson);

	}
}]

//tabulator欄位設置
let columns1102 = [
	[ "button","",detailBtn],
	[ "txttreaty_year", "合約年度", "display"],
	[ "txttreaty_no", "合約代號", "display"],
	[ "txttreaty_name", "合約名稱", "display"]
	
]

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1102)

//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = []

//檢核警告設定
let alertConfig = {}

//建立table
let table1102 = createTable("table1102", colsFormat, tableConfigs, tableRelatedBtns, alertConfig)



/**
 * 「合約資料」查詢
 * @author yiting 2021/11/02
 */
function btnQueryRin1102(){

	 
	//1-參數
	let param = {
		"treatyYear":$('#txttreaty_year1').val().trim(),	//合約年度
		"treatyNo":$('#txttreaty_no1').val().trim()   		//合約代號	
	}
	
	let parJson = JSON.stringify(param);
	
	//2-執行查詢
	let res = ajaxPostByJsonParam("../../rin1102api/querytreatys", parJson, false);
	
	if("000" === res.status){
		loadData("table1102", res.data, {type:"dataCount", value:7})
	}else{
		alert("「再保合約資料」查詢失敗!!!請聯絡管理人員!!!");
	}

}

