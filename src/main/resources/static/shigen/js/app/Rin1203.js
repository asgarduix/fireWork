const RIN_1203A = locationHrefKeepDataFetch(); //由「RIN1203A」頁面帶入的參數
                                              //txtarea_code(地段代號)


$(function(){
	
	defaultDate();
	//若有之前的搜尋紀錄，則進來時恢復原先搜查狀態
	if(RIN_1203A){

	//寫回同險設定查詢input
	$('#dtaSpolicy_dprt').val(RIN_1203A.policy_dprtS);
	$('#dtaEpolicy_dprt').val(RIN_1203A.policy_dprtE);
	$('#txtarea_code').val(RIN_1203A.txtarea_code);
	if(RIN_1203A.risk_flag == 0){		
		$('#txtrisk_flag1').prop("checked", true);
	}
	if(RIN_1203A.risk_flag == -1){		
		$('#txtrisk_flag2').prop("checked", true);
	}

	btnQueryRin1203();
	}
})
//明細按鈕設定
let detailBtn = [{
	name:"明細", 
	func:function(row){
		$(window).unbind('beforeunload');
		let riskFlag;
		
		if($("#txtrisk_flag1").prop("checked") === true){
			riskFlag = "0";//未完成地段
		}
		if($("#txtrisk_flag2").prop("checked") === true){
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
	[ "button","",detailBtn,{ widthGrow:1}],
	[ "txtarea_code", "地段代號", "display",{ widthGrow:9 }]
	
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
	
	if($("#txtrisk_flag1").prop("checked") === true){
		riskFlag = "0";//
	}
	
	 
	//1-參數
	let param = {
	    "policy_dprtS":$('#dtaSpolicy_dprt').val(), //印單日期_起
	    "policy_dprtE":$('#dtaEpolicy_dprt').val(), //印單日期_迄
		"txtarea_code":$('#txtarea_code').val(),    //地段代號
		"risk_flag":riskFlag                        //範圍
	}
	
//	ajaxRequestIsAsyncDynimicBytoken("../../rin1203api/queryfritemparea",true,false,param,
//			(res)=>{
//				if(null!=res && "000" === res.status){
//					loadData("table1203", res.data, {type:"dataCount", value:15})
//				}else{
//					alert("查詢失敗!");
//				}
//			},
//			(error)=>{
//				alert("loading錯誤")
//			})
	
	let parJson = JSON.stringify(param);
	
	//2-執行查詢
	let res = ajaxPostByJsonParam("../../rin1203api/queryfritemparea", parJson, false);
	
	if("000" === res.status){
		loadData("table1203", res.data, {type:"dataCount", value:6})
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


//印單日期起迄日為使用者設定n年前和系統當日
function defaultDate(){
	let res = ajaxPostByJsonParam("../../rin1203api/queryusersetyear", null,false);
	var today = new Date();
	const todayInput=today.format("yyyy/MM/dd");
    var NYearAgo = new Date();
    NYearAgo.setFullYear(NYearAgo.getFullYear()-(res.data));
    const NYearAgoInput=NYearAgo.format("yyyy/MM/dd");
    $('#dtaSpolicy_dprt').val(NYearAgoInput)
    $('#dtaEpolicy_dprt').val(todayInput)
}


//轉換日期格式「"yyyy/MM/dd"」
Date.prototype.format = function(format) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(format)) {
	   format=format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(format)){
    	   format = format.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return format; 
}

/**
 * 日期格式檢核
 * @returns
 */
$('#dtaSpolicy_dprt, #dtaEpolicy_dprt').change(function(){
	try {
		if(!isValidDate($(this).val())){
			alert("日期格式錯誤，請輸入YYYY/MM/DD或是YYYY/M/D");
			// 欄位值清空
			$(this).val("");
		}
	} catch (e) {
		alert(e);
	}
});