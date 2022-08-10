//tabulator欄位設置
let columns1104 = [
	["checkbox", { showBtn: true }],
	["usePropId", "使用性質代碼", "input", { width: "25%" } ],
    ["special1Limit", "特一", "input", { width: "25%" } ],
    ["special2Limit", "特二", "input", { width: "25%" } ],
    ["firstLimit", "頭等", "input", { width: "25%" } ],
    ["secondLimit", "二等", "input", { width: "25%" } ],
    ["thirdLimit", "三等", "input", { width: "25%" } ],
    ["outsideLimit", "露天", "input", { width: "25%" } ],
    ["usePropName", "使用性質名稱", "input", { width: "25%" } ]
];

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1104);
              
//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = [
    {
        type: "add",
        name: "新增",
        class: "class-name",
        position: "#table1104-btn",
        getDefaultData: function () {

            return {
                isSuccess: true,
                //梯次佣金類別預設為1
//                data: { txtcomm_type: "1" }
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
        class: "class-name",
        position: "#table1104-btn",
    },
    {
        type: "del",
        name: "刪除",
        class: "class-name",
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
        class: "class-name",
        position: "#table1104-btn",
        nullSpaceCheck: true,rules:{},
        addSaveApi: function (rowData) {
            //add&copy
            // 回傳值：Object格式資料 { isSuccess: true} or { isSuccess: false, fields: ["a","b"], errMsg: "欄位驗證失敗" }
            //rowData為所選資料{...}
            //call api here
        	//檢核下限是否大於上限
//        	if(rowData.numlower_limit > rowData.numupper_limit){
//        		return{ isSuccess: false, fields: ["numlower_limit","numupper_limit"], errMsg: "損失率百分比下限應小於上限" }
//        	}
//        	let parJson = JSON.stringify(rowData);
//        	let res = ajaxPostByJsonParam("../../rin1104api/inserttreaty", parJson, false);
//        	console.log(res.status)
        	let res = ajaxPostTokenReady("../../rin1104api/insertFriUseLimit", rowData, false);
         	
        	if("000" === res.status){        		
        		table1104.getSelectedRows()[0].getData().serial = res.data;        		
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
        		//檢核下限是否大於上限
//        		if(newDataJson.numlower_limit > newDataJson.numupper_limit){
//            		return{ isSuccess: false, fields: ["numlower_limit","numupper_limit"], errMsg: "損失率百分比下限應小於上限" }
//            	}
        		//將更新需要的條件加入參數物件中
        		newDataJson.usePropId = newData[0];
//        		newDataJson.special1Limit = oldData[1];
//        		newDataJson.special2Limit = oldData[2];
//        		newDataJson.firstLimit = oldData[3];
//        		newDataJson.secondLimit = oldData[4];
//        		newDataJson.thirdLimit = oldData[5];
//        		newDataJson.outsideLimit = oldData[6];
//        		newDataJson.usePropName = oldData[7];
        		        		
//        		let parJson = JSON.stringify(newDataJson);        		
//        		let res = ajaxPostByJsonParam("../../rin1103api/updatetreaty", parJson, false);
        		let res = ajaxPostTokenReady("../../rin1104api/updateFriUseLimit", newDataJson, false);
        		        		
        		if("000" === res.status){            		
            		return { isSuccess: true }
            	}else{
            		return{ isSuccess: false, fields: [], errMsg: res.message }
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
	
	let res = ajaxPostTokenReady("../../rin1104api/queryByUsePropId", param, false);
	
	if('000' === res.status){
		
		loadData("table1104", res.data, {type:"dataCount", value:15})

	}else{
		alert("「使用性質代號查詢」失敗!!!請聯絡管理人員!!!");
	}
	
}
