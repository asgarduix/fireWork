//tabulator欄位設置
let columns1103 = [
	["checkbox", { showBtn: true }],
	["txttreaty_year", "合約年度", "input", { width: "25%" }],
    ["txttreaty_no", "合約編號", "input", { width: "25%" }],
    ["txtcomm_type", "梯次佣金類別", "select1", {"1.再保佣金":"1","2.盈餘佣金":"2"},{ width: "12.5%" }],
    ["numlower_limit", "損失率下限百分比", "number", { width: "12.5%" }],
    ["numupper_limit", "損失率上限百分比", "number", { width: "12.5%" }],
    ["numcomm_rate", "佣金百分比", "number",{ width: "10%" }]
];

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1103);

//客製tabulator本體
let tableConfigs = {}

//按鈕設置與功能
let tableRelatedBtns = [
    {
        type: "add",
        name: "新增",
        class: "class-name",
        position: "#table1103-btn",
        getDefaultData: function () {

            return {
                isSuccess: true,
                //梯次佣金類別預設為1
                data: { txtcomm_type: "1" }
            }

        },
    },
    {
        type: "cancel",
        name: "取消",
        class: "btn btn-oneD",
        position: "#table1103-btn",
    },
    {
        type: "edit",
        name: "修改",
        class: "class-name",
        position: "#table1103-btn",
    },
    {
        type: "copy",
        name: "複製",
        class: "class-name",
        position: "#table1103-btn",
    },
    {
        type: "del",
        name: "刪除",
        class: "class-name",
        position: "#table1103-btn",
        delApi: function (rowsDataArry) {
        	
            let parJson = JSON.stringify(rowsDataArry);
            
            let res = ajaxPostByJsonParam("../../rin1103api/deletetreaty", parJson, false);
            
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
        position: "#table1103-btn",
        nullSpaceCheck: true,rules:{},
        addSaveApi: function (rowData) {
            //add&copy
            // 回傳值：Object格式資料 { isSuccess: true} or { isSuccess: false, fields: ["a","b"], errMsg: "欄位驗證失敗" }
            //rowData為所選資料{...}
            //call api here
        	//檢核下限是否大於上限
        	if(rowData.numlower_limit > rowData.numupper_limit){
        		return{ isSuccess: false, fields: ["numlower_limit","numupper_limit"], errMsg: "損失率百分比下限應小於上限" }
        	}
        	let parJson = JSON.stringify(rowData);
        	let res = ajaxPostByJsonParam("../../rin1103api/inserttreaty", parJson, false);

        	//檢核上下限是否重疊、不可使用    	
        	if("finish" === res.status){	
        		return{ isSuccess: false, fields: ["numlower_limit","numupper_limit"], errMsg: res.message }
        	}else if("000" === res.status){
        		//將DB自動生成的序號傳回前端、放入table1103中
        		table1103.getSelectedRows()[0].getData().serial = res.data;        		
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
        		if(newDataJson.numlower_limit > newDataJson.numupper_limit){
            		return{ isSuccess: false, fields: ["numlower_limit","numupper_limit"], errMsg: "損失率百分比下限應小於上限" }
            	}
        		//將更新需要的條件加入參數物件中
        		newDataJson.conditionYear = oldData[0];
        		newDataJson.conditionNo = oldData[1];
        		newDataJson.conditionType = oldData[2];
        		
        		let parJson = JSON.stringify(newDataJson);        		
        		let res = ajaxPostByJsonParam("../../rin1103api/updatetreaty", parJson, false);
        		
        		//檢核上下限是否重疊、不可使用     
        		if("finish" === res.status){          		
            		return{ isSuccess: false, fields: ["numlower_limit","numupper_limit"], errMsg: res.message }
            	}else if("000" === res.status){            		
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
    position: "#table1103-errMsg",
    displayTime: 0, //毫秒，訊息顯示時間，設置為0不清空
}

//建立table
let table1103 = createTable("table1103", colsFormat, tableConfigs, tableRelatedBtns, alertConfig2)





//查詢
function btnQueryRin1103(){
	
	//搜尋時，結束目前tabulator的編輯狀態
	if(table1103.tableMode){
		$("#table1103-cancel").click();
	}
	
	let param = {
			"treatyYear" : $('#txttready_year1').val().trim(),   	//合約年度
			"treatyNo"	 : $('#txttready_no1').val().trim(),		//合約編號
	}
	
	let parJson = JSON.stringify(param);
	
	let res = ajaxPostByJsonParam("../../rin1103api/querytreatylist", parJson, false);
	
	if('000' === res.status){
		
		loadData("table1103", res.data, {type:"dataCount", value:15})

	}else{
		alert("「梯次佣金查詢」失敗!!!請聯絡管理人員!!!");
	}

	
}


