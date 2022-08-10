const RIN_1203A = locationHrefKeepDataFetch(); //由「RIN1203A」頁面帶入的參數
                                              //txtarea_code(地段代號)


$(function(){
	//若有之前的搜尋紀錄，則進來時恢復原先搜查狀態
	if(RIN_1203A){

	//寫回同險設定查詢input
	$('#dtaSpolicy_dprt').val(RIN_1203A.policy_dprtS);
	$('#dtaEpolicy_dprt').val(RIN_1203A.policy_dprtE);
	$('#txtarea_code').val(RIN_1203A.txtarea_code);
	$('input[name=txtrisk_flag]:checked').val(RIN_1203A.risk_flag);

	btnQueryRin1203();
	}
})
//明細按鈕設定
let detailBtn = [{
	name:"明細", 
	func:function(row){
		
		let riskFlag;
		
		if($("#txtrisk_flag1").prop("checked") == true){
			riskFlag = "0";//未完成地段
		}
		if($("#txtrisk_flag2").prop("checked") == true){
			riskFlag = "-1";//已完成地段
		}
		
		
		//檢核有沒有被鎖,if(有被鎖) 跳alert不可進入1203A頁面
		let areaCode = {
				"areaCode" : row.getData().txtarea_code
		}
		let parJson1 = JSON.stringify(areaCode);
		
		//2-執行查詢，將該地段上鎖
		let res = ajaxPostByJsonParam("../../rin1203api/queryareacodeislock", parJson1, false);
		
		if("finish" === res.status){
			alert(res.message)
			return;

		}else if("999" === res.status){
			alert(res.message)
			
		//else(沒鎖)
		}else{
			let data = {
					"txtarea_code" : row.getData().txtarea_code,   //地段代號
					"policy_dprtS" :$('#dtaSpolicy_dprt').val(),   //印單日期_起
					"policy_dprtE" :$('#dtaEpolicy_dprt').val(),   //印單日期_迄
					"risk_flag":riskFlag,
					"searchTxtarea_code" : $('#txtarea_code').val()//查詢的地段代號
			}
			
			let parJson = JSON.stringify(data)
			locationHrefKeepDataType2('Rin1203A', '', parJson);
		}
	
	}
}]

//tabulator欄位設置
let columns1203 = [
	[ "checkbox", { showBtn: true }],
	[ "button","",detailBtn,],
	[ "txtarea_code", "地段代號", "display",{widthGrow:10}],
	
]

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1203)

//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = []

//檢核警告設定
let alertConfig = {}

//建立table
let table1203 = createTable("table1203", colsFormat, tableConfigs, tableRelatedBtns, alertConfig)



/**
 * 「同險資料」查詢地段代號
 * @author Sophia 2021/11/11
 */
function btnQueryRin1203(){

	
	let riskFlag;
	
	if($("#txtrisk_flag1").prop("checked") == true){
		riskFlag = "0";//
	}
	
	 
	//1-參數
	let param = {
	    "policy_dprtS":$('#dtaSpolicy_dprt').val(),    //印單日期_起
	    "policy_dprtE":$('#dtaEpolicy_dprt').val(), //印單日期_迄
		"txtarea_code":$('#txtarea_code').val(),    //地段代號
		"risk_flag":riskFlag                        //範圍
	}
	let parJson = JSON.stringify(param);
	
	//2-執行查詢
	let res = ajaxPostByJsonParam("../../rin1203api/queryfritemparea", parJson, false);
	
	if("000" === res.status){
		loadData("table1203", res.data, {type:"dataCount", value:15})
	}else{
		alert("查詢失敗!");
	}

}
 
/**
 * 清除地段代號
 * @author Sophia 2021/11/11
 */
function btnDeleteRin1203(){
	
	if(table1203.getSelectedRows().length <= 0){
		alert("請勾選地段代號!");
		return;
	}else{
		
		//勾選列表的值
		let param = table1203.getSelectedData();
		
		let isDelete = confirm("『是否確定清除』");
		
		if(isDelete){
			
			let parJson = JSON.stringify(param);
			let res = ajaxPostByJsonParam("../../rin1203api/deleteusedareain", parJson, false);
			if(res){		
				alert(res.message);
				
				//清空勾選
                table1203.deselectRow();
			}
		}else {
			return;
		}
	}
	
	

}



