//tabulator欄位設置
let columns1104 = [
	["checkbox", { showBtn: true }],
	["usePropId", "使用性質代碼", "input"],
    ["special1Limit", "特一限額代號", "input" ],
    ["special2Limit", "特二限額代號", "input" ],
    ["firstLimit", "頭等限額代號", "input"],
    ["secondLimit", "二等限額代號", "input"],
    ["thirdLimit", "三等限額代號", "input"],
    ["outsideLimit", "露天限額代號", "input"],
];

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1104);
              
//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = [
    {
        type: "add",
        name: "",
        class: "add-btn-custom",
        position: "#table1104addBtn",
        getDefaultData: function () {

            return {
                isSuccess: true,
                data: {}
            }

        },
    },
    {
        type: "cancel",
        name: "取消",
        class: "btn btn-oneD",
        position: "#table1104-btn",
    },
    {
        type: "edit",
        name: "修改",
        class: "btn btn-oneE",
        position: "#table1104-btn",
    },
    {
        type: "del",
        name: "刪除",
        class: "btn btn-oneG",
        position: "#table1104-btn",
        delApi: function (rowsDataArry) {        	       	
        	 
            let parJson = JSON.stringify(rowsDataArry);
            
            let res = ajaxPostByJsonParam("../../rin1104api/deleteFriUseLimit", parJson, false);
            
            if("000" === res.status){            	
            	return { isSuccess: true }
            }else{
            	
            	return {
            		isSuccess: false,
            		errMsg: "刪除失敗，請聯絡管理人員"
            	}
            }
        }
    },
    {
        type: "save",
        name: "儲存",
        class: "btn btn-oneD",
        position: "#table1104-btn",
        nullSpaceCheck: true,rules:{},
        addSaveApi: function (rowData) {
            //add&copy
            // 回傳值：Object格式資料 { isSuccess: true} or { isSuccess: false, fields: ["a","b"], errMsg: "欄位驗證失敗" }
            //rowData為所選資料{...}
            //call api here
        	
        	let parJson = JSON.stringify(rowData);
        	let res = ajaxPostByJsonParam("../../rin1104api/insertFriUseLimit", parJson, false);
         	
        	if("finish" === res.status){	
        		return{ isSuccess: false, fields: ["usePropId"], errMsg: res.message }
        	}else if("000" === res.status){        		
        		return { isSuccess: true }
        	}else{
        		return{ isSuccess: false, fields: [], errMsg: res.message }
        	}

        },
        editSaveApi: function (oldData, newData, newDataJson) {

        	//比對新舊資料，若一樣則不做任何事情   	
        	if(isSameArray(oldData, newData)){
        		
        		return { isSuccess: true }
        		
        	//不同才執行更新	
        	}else{
        		        		
        		let parJson = JSON.stringify(newDataJson);        		
        		let res = ajaxPostByJsonParam("../../rin1104api/updateFriUseLimit", parJson, false);
        		        		
        		if("000" === res.status){            		
            		return { isSuccess: true }
            	}else{
            		return{ isSuccess: false, fields: [], errMsg: "資料修改失敗！" }
            	}        		
        	}
        }
    },
]

//檢核警告設定
let alertConfig2 = {
    type: "dom",
    position: "#table1104-errMsg",
    displayTime: 0, //毫秒，訊息顯示時間，設置為0不清空
}

//建立table
let table1104 = createTable("table1104", colsFormat, tableConfigs, tableRelatedBtns, alertConfig2)

//查詢
function btnQueryRin1104(){
	
	//搜尋時，結束目前tabulator的編輯狀態
	if(table1104.tableMode){
		$("#table1104-cancel").click();
	}
	
	let param = {
			"usePropId":$('#txtuse_prop_id').val().trim()
	}
	
	let parJson = JSON.stringify(param);
	
//	let res = ajaxPostTokenReady("../../rin1104api/queryByUsePropId", param, false);
	
	let res = ajaxPostByJsonParam("../../rin1104api/queryByUsePropId", parJson, false);
	
	if('000' === res.status){
		
		loadData("table1104", res.data, {type:"dataCount", value:6})

	}else{
		alert("「使用性質代號查詢」失敗!!!請聯絡管理人員!!!");
	}
	
}
