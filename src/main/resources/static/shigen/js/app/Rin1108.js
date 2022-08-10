//tabulator欄位設置
let columns1108 = [
	["checkbox", { showBtn: true }],
	["txttreaty_year", "合約年度", "input", { width: "25%" }],
    ["txtpolicy_type", "保單種類", "input", { width: "25%" }],
    ["numshare_order", "分保順序", "input", { width: "25%" }],
    ["txttreaty_no", "合約代號", "input",{ width: "25%"}]
];

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1108);

//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = [
    {
        type: "add",
        name: "新增",
        class: "class-name",
        position: "#table1108-btn",
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
        position: "#table1108-btn",
    },
    {
        type: "edit",
        name: "修改",
        class: "class-name",
        position: "#table1108-btn",
    },
    {
        type: "copy",
        name: "複製",
        class: "class-name",
        position: "#table1108-btn",
    },
    {
        type: "del",
        name: "刪除",
        class: "class-name",
        position: "#table1108-btn",
        delApi: function (rowsDataArry) {
        	
            let parJson = JSON.stringify(rowsDataArry);
            
            let res = ajaxPostByJsonParam("../../rin1108api/deletetreatyshareorder", parJson, false);
            
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
        position: "#table1108-btn",
        nullSpaceCheck: true,rules:{},
        addSaveApi: function (rowData) {
            //add&copy
            // 回傳值：Object格式資料 { isSuccess: true} or { isSuccess: false, fields: ["a","b"], errMsg: "欄位驗證失敗" }
            //rowData為所選資料{...}
            //call api here      
        	let parJson = JSON.stringify(rowData);
        	let res = ajaxPostByJsonParam("../../rin1108api/inserttreatyshareorder", parJson, false);

        	//檢核資料是否已存在、不可使用  	
        	if("finish" === res.status){	
        		return{ isSuccess: false, fields: ["txttreaty_year","txtpolicy_type","numshare_order"], errMsg: res.message }
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
        		let res = ajaxPostByJsonParam("../../rin1108api/updatetreatyshareorder", parJson, false);
        		
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
    position: "#table1108-errMsg",
    displayTime: 0, //毫秒，訊息顯示時間，設置為0不清空
}

//建立table
let table1108 = createTable("table1108", colsFormat, tableConfigs, tableRelatedBtns, alertConfig2)





//查詢
function btnQueryRin1108(){
	
	//搜尋時，結束目前tabulator的編輯狀態
	if(table1108.tableMode){
		$("#table1108-cancel").click();
	}
	
	let param = {
			"treatyYear" : $('#txttreaty_year1').val().trim(),   	//合約年度
			"policyType" : $('#txtpolicy_type1').val().trim(),		//保單種類
	}
	
	let parJson = JSON.stringify(param);
	
	let res = ajaxPostByJsonParam("../../rin1108api/querytreatyshareorderlist", parJson, false);
	
	if('000' === res.status){
		
		loadData("table1108", res.data, {type:"dataCount", value:15})

	}else{
		alert("「限額資料查詢」失敗!!!請聯絡管理人員!!!");
	}

	
}


