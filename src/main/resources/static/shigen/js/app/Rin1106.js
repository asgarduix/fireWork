//預設
$(function(){
	//一進來就是查詢全部的狀態
	queryRin1106();
})

//查詢
function queryRin1106(){
	
	//搜尋時，結束目前tabulator的編輯狀態
	let param = {}
	
	let parJson = JSON.stringify(param);
	
	let res = ajaxPostByJsonParam("../../rin1106api/querymunichlist", parJson, false);
	
	if('000' === res.status){
		
		loadData("table1106", res.data, {type:"dataCount", value:15})

	}else{
		alert("「慕尼黑地區設定資料」失敗!!!請聯絡管理人員!!!");
	}

	
}


//tabulator欄位設置
let columns1106 = [
	["checkbox", { showBtn: true }],
	["txtnatural_id", "天災區域代號", "input", { width: "20%" }],
    ["txtcity_name", "縣市名稱", "input", { width: "30%" }],
    ["txtmunich_id", "慕尼黑地區代號", "input", { width: "20%" }],
    ["txtmunich_desc", "慕尼黑地區說明", "input",{ width: "30%"}]
];

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1106);

//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = [
    {
        type: "add",
        name: "新增",
        class: "class-name",
        position: "#table1106-btn",
        getDefaultData: function () {

            return {
                isSuccess: true,
                data:{}
            }

        },
    },
    {
        type: "cancel",
        name: "取消",
        class: "btn btn-oneD",
        position: "#table1106-btn",
    },
    {
        type: "edit",
        name: "修改",
        class: "class-name",
        position: "#table1106-btn",
    },
    {
        type: "del",
        name: "刪除",
        class: "class-name",
        position: "#table1106-btn",
        delApi: function (rowsDataArry) {
        	
            let parJson = JSON.stringify(rowsDataArry);
            
            let res = ajaxPostByJsonParam("../../rin1106api/deleteretain", parJson, false);
            
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
        position: "#table1106-btn",
        nullSpaceCheck: true,rules:{},
        addSaveApi: function (rowData) {
            //add&copy
            // 回傳值：Object格式資料 { isSuccess: true} or { isSuccess: false, fields: ["a","b"], errMsg: "欄位驗證失敗" }
            //rowData為所選資料{...}
            //call api here  
        	let parJson = JSON.stringify(rowData);
        	let res = ajaxPostByJsonParam("../../rin1106api/insertmunich", parJson, false);

        	//檢核資料是否已存在、不可使用  	
        	if("finish" === res.status){	
        		return{ isSuccess: false, fields: ["txtnatural_id"], errMsg: res.message }
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
        		let res = ajaxPostByJsonParam("../../rin1106api/updatemunich", parJson, false);
        		
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
    position: "#table1106-errMsg",
    displayTime: 0, //毫秒，訊息顯示時間，設置為0不清空
}

//建立table
let table1106 = createTable("table1106", colsFormat, tableConfigs, tableRelatedBtns, alertConfig2)








