const RINCOMID_1101A = locationHrefKeepDataFetch(); //由「RIN1101A」頁面帶入的參數:searchHistory(1101搜尋紀錄)

//預設
$(function(){
	
	//若有之前的搜尋紀錄，則進來時恢復原先搜查狀態
	if(RINCOMID_1101A){
		//1-參數
		let param = {
				"rinComId":RINCOMID_1101A.searchHistory   //再保人代號	
		}
		
		//2-執行查詢
		ajaxRequestIsAsyncDynimicBytoken("../../rin1101api/queryreiners", true, false, param,     
				(res) => {
							if (res != null && "000" === res.status) {
								loadData("table1101", res.data, {type:"dataCount", value:7})
							}else{
								alert("「再保人代號查詢」失敗!!!請聯絡管理人員!!!");
							}
				}, (error) => {
								console.log(error);
								alert("「再保人代號查詢」失敗!!!請聯絡管理人員!!!");
				})
		
		//3-寫回再保人查詢input
		$('#txtrin_com_id').val(RINCOMID_1101A.searchHistory);
	}
})
	
	


//明細按鈕設定
let detailBtn = [{
	name:"明細", 
	class:"btn btn-oneA",
	func:function(row){
		let data = {
				"rinComId" : row.getData().rinComId,					//再保人代號
				"searchHistory" : $('#txtrin_com_id').val().trim()		//再保人代號搜尋條件
		}
		let parJson = JSON.stringify(data)
		locationHrefKeepDataType2('Rin1101A', '', parJson);
	}
}]

//tabulator欄位設置
let columns1101 = [
	[ "button","",detailBtn],
	[ "rinComId", "再保人代號", "display"],
	[ "cname", "再保人中文名稱", "display"],
	[ "ename", "再保人英文名稱", "display"],
	[ "usemrk", "註銷日", "display"]
	
]

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1101)

//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = []

//檢核警告設定
let alertConfig = {}

//建立table
let table1101 = createTable("table1101", colsFormat, tableConfigs, tableRelatedBtns, alertConfig)



/**
 * 「再保人代號」搜尋
 * @author yiting 2021/09/27
 */
function btnQueryRin1101(){

	 
	//1-參數
	let param = {
		"rinComId":$('#txtrin_com_id').val().trim()   //再保人代號	
	}

	//2-執行查詢
	ajaxRequestIsAsyncDynimicBytoken("../../rin1101api/queryreiners", true, false, param,     
			(res) => {
						if (res != null && "000" === res.status) {
							loadData("table1101", res.data, {type:"dataCount", value:7})
						}else{
							alert("「再保人代號查詢」失敗!!!請聯絡管理人員!!!");
						}
			}, (error) => {
							console.log(error);
							alert("「再保人代號查詢」失敗!!!請聯絡管理人員!!!");
			})

}


/**
 * 再保人代號查詢-autocomplete功能
 * @author yiting 2021/09/29
 */
$("#txtrin_com_id").autocomplete({
    minLength: 0,
    source: function (request, response) {  
    	//1-參數
    	let param = {
    		"rinComId":$('#txtrin_com_id').val().trim()   //再保人代號	
    	}
    	
    	//2-執行查詢
    	ajaxRequestIsAsyncDynimicBytoken("../../rin1101api/autotenrcid", true, false, param,     
    			(res) => {
    						if (res != null && "000" === res.status) {
    							//將查詢結果顯示在列表中
    				    		response($.map(res.data, function (item) {
    				    			return {
    				    				
    				    				label: item.rinComId, 	//列表所顯示的文字
    				    				value: item.rinComId 	//列表選項的值
    				    				
    				    			};
    				    		}));
    						}else{
    							alert("「再保人代號查詢」失敗!!!請聯絡管理人員!!!");
    						}
    			}, (error) => {
    							console.log(error);
    							alert("「再保人代號查詢」失敗!!!請聯絡管理人員!!!");
    			}, false)

    },
//	focus參數事件介紹 : 在下拉選單匹配時如果滑鼠有焦點到會觸發事件
//	下方是指當使用者只是焦點到某個選項就將名子帶#name輸入框中
    focus: function (event, ui) {
    	$("#txtrin_com_id").attr('autocomplete', 'on');
        $("#txtrin_com_id").val(ui.item.value);
        return false;
    },
	//select參數事件介紹 : 使用者選擇下拉式某項目後觸發事件
	//當使用者選擇某項目後自動將所有的值帶進輸入框中
    select: function (event, ui) {
    	$("#txtrin_com_id").attr('autocomplete', 'on');
        $("#txtrin_com_id").val(ui.item.value);
        return false;
    }
});
