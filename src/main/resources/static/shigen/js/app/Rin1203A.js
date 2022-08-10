const RIN_1203 = locationHrefKeepDataFetch();   //由「RIN1203」頁面帶入的參數:
                                                                       //searchPolicy_dprtS(印單日期起)
																	   //searchPolicy_dprtE(印單日期迄)
																	   //txtarea_code(地段代號)

//預設
$(function() {
	
    //下拉選單
	ddlRiskList();
	
	//從1203帶入
	queryRin1203A();
		
		//頁籤-同險給號，同險代號欄位預設值給地段代號，為了產生同險代號使用
		$('#txtrisk_no').val( RIN_1203.txtarea_code); //同險代號
	
		//預設不能按，需等待產生同險代號之後才可以Enabled
		$("#btnAddRiskNo").attr("disabled", true);   //新增同險按鈕
		$("#btnDetelRiskNo").attr("disabled", true); //刪除同險按鈕
		$("#btnUpdOldRiskNo").attr("disabled", true);// 更改舊同險按鈕
})


/**
 * 查詢頁面資料
 * @author Sophia 2021/11/16
 */
function queryRin1203A(){
	
	//1-參數
	let param ={
	    "policy_dprtS" : RIN_1203.policy_dprtS, //印單日期起
	    "policy_dprtE" : RIN_1203.policy_dprtE, //印單日期迄
		"txtarea_code" : RIN_1203.txtarea_code, //地段代號
		"risk_flag":RIN_1203.risk_flag          //範圍
	} 

	let parJson = JSON.stringify(param);
	
	//2-執行查詢
	let res = ajaxPostByJsonParam("../../rin1203aapi/queryareapolicy", parJson, false);
	
	if("000" === res.status){
		//3-顯示
		loadData("table1203A", res.data, {type:"dataCount", value:10});
	}else{
		alert("「同險設定」查詢失敗!");
	}
		
}


//tabulator欄位設置
let columns1203A = [
	[ "checkbox", { showBtn: true , width: 30 }],
	[ "txtrisk_no", "同險代號", "display",{width: 120 }],
 	[ "txtrisk_flag", "處理狀態", "display", {
	formatter : function(cell) {
		let val = cell.getValue();
		if (val === "N") {
			cell.getElement().style.color = "red";
			cell.getElement().style.backgroundColor = "#BEBEBE";
		}
		return val;
	},
	width: 100
} ],
	[ "txtpolicy_no", "保單號碼", "display",{width: 150 }],
	[ "txtendorse_no", "批單號碼", "display",{width: 150 }],
	[ "numaddr_no", "地址序號", "display",{width: 100}],
	[ "txtprop_addr", "標的物地址", "display",{width: 300 }],
	[ "txtcinsurant", "被保險人", "display",{width: 200 }],
	[ "txtuseprop_code", "使用性質代號", "display",{width: 100 }],
	[ "txtuseprop_name", "使用性質名稱", "display",{width: 100 }],
	[ "numamt", "保額", "number",{width: 100 }],
	[ "txtarea_code", "地段代號", "display",{width: 100 }],
	[ "txtrisk_name", "同險名稱", "display",{width: 300 }],
	[ "txtacct_flag", "已分保列印", "display",{width: 100 }]
]

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1203A)

//客製tabulator本體
let tableConfigs = {
	
	//點選字讓資料可帶入輸入框
	rowClick: function (e, row) {
		let data = row.getData()
		//同險代號
		$('#txtrisk_no').val(data.txtrisk_no);
		//同險名稱(帶入標的物地址)
		$('#txtrisk_name').val(data.txtprop_addr);
	},
	//點擊標的物地址排序時，滾到已選資料的地方
	dataSorted:function(sorters, rows){		
		console.log(sorters[0])
		if(sorters[0] && sorters[0].field === 'txtprop_addr'){			
			let aaa = this;
			setTimeout(function(){			
				aaa.scrollToRow(aaa.getSelectedRows()[0]?.getData()?.id??0)
			},0)
		}
	},
}

//按鈕設置與功能
let tableRelatedBtns = []

//檢核警告設定
let alertConfig = {}

//建立table
let table1203A = createTable("table1203A", colsFormat, tableConfigs, tableRelatedBtns, alertConfig)


//查詢處理狀態列表是否有N，有N  fri_tempeara.proc_count 狀態修改為0
function changeProcCount() {

	let procCount = -1;

	let isDelete = confirm("『是否確定處理其他地段』");
	
    if(isDelete){
    	
    	// 取畫面上處理狀態
    	for ( i = 0; i < table1203A.getData().length; i++) {
    		
    		let thisData = table1203A.getData()[i].txtrisk_flag;
    		
    		if("N" === thisData){
    			procCount = 0;
    			break;
    		}
    	}
    	
    	let data = {
    			"txtarea_code" : RIN_1203.txtarea_code, //上一頁地段代號
    			"procCount"	: procCount                 //地段完成狀態
    	} 
    	let parJson = JSON.stringify(data);
    	
    	// 2-執行查詢
    	let res = ajaxPostByJsonParam("../../rin1203aapi/deleteareacode", parJson,false);
    	
    	if ("000" === res.status) {
    		//回上一頁
    		backTo1203();
    	} else {
    		alert("處理其他地段失敗!");
    	}
    }

}


//將勾選的值帶入頁籤更正地段的值
$('#table1203A').change(function() {
	
	//判斷有沒有被勾選
	if(table1203A.getSelectedRows().length > 0){
		
		$('#txtarea_code').val(table1203A.getSelectedRows()[0].getData().txtarea_code); //地段代號
		$('#txtprop_addr').val(table1203A.getSelectedRows()[0].getData().txtprop_addr); //標的地址
		$('#txtrisk_no').val(table1203A.getSelectedRows()[0].getData().txtrisk_no);     //同險代號
		$('#txtrisk_name').val(table1203A.getSelectedRows()[0].getData().txtprop_addr); //同險名稱(帶入標的物地址)
	}
})

// 更正地段按鈕
function updAreaCode() {

	var areaCode = $('#txtarea_code').val(); //地段代號輸入框值

	if (checkIsNullSpace(areaCode)|| table1203A.getSelectedRows().length <= 0) {
		alert("『請先選擇地段代號!』");
	} else {
		// 檢查是否勾選兩個以上
		if(checkIsChoseTwo()){return;}
		//檢核分保列印是否為Y
		if(chkacctFlagIsY()){return;}
		
		let isUpdate = confirm("『是否確認更正地段:"+areaCode+"』");

		if (isUpdate) {
			
			// 勾選列表的值
			let getRow = table1203A.getSelectedRows()[0].getData()
			let data = {
				"txtarea_code" :areaCode,                  // 地段代號
				"txtpolicy_no" : getRow.txtpolicy_no,      // 保單號碼
				"txtendorse_no" : getRow.txtendorse_no,    // 批單號碼
				"numaddr_no" : getRow.numaddr_no           // 地址序號
			}

			let parJson = JSON.stringify(data);
			let res = ajaxPostByJsonParam("../../rin1203aapi/updareacode",parJson, false);

			if ("000" === res.status) {

				// update完成後，將地段代號欄位的值帶入表格的地段代號
				for (i = 0; i < table1203A.getSelectedRows().length; i++) {
					table1203A.getSelectedRows()[i].update({
						txtarea_code : areaCode
					})
				}
				alert("更正地段成功!");
			} else if ("999" === res.status) {
				//更正地段失敗!
				alert(res.message);
			}else{
				//地段代號超過7碼!
				alert(res.message)
				return;
			}
		}
	}
}


//更正地址按紐
function updAddress() {
	
	var propAddr = $('#txtprop_addr').val(); //標的地址輸入框

	if (checkIsNullSpace(propAddr)|| table1203A.getSelectedRows().length <= 0) {
		alert("『請先選擇標的地址!』");
	} else {
		// 檢查是否勾選兩個以上
		if(checkIsChoseTwo()){return;}
		//檢核分保列印是否為Y
		if(chkacctFlagIsY()){return;}

		let isUpdate = confirm("『是否確認更正地址:"+propAddr+"』");

		if (isUpdate) {

			// 勾選列表的值
			let getRow = table1203A.getSelectedRows()[0].getData()

			let data = {
				"txtprop_addr" : propAddr, // 標的地址
				"txtpolicy_no" : getRow.txtpolicy_no,      // 保單號碼
				"txtendorse_no" : getRow.txtendorse_no,    // 批單號碼
				"numaddr_no" : getRow.numaddr_no           // 地址序號
			}

			let parJson = JSON.stringify(data);
			let res = ajaxPostByJsonParam("../../rin1203aapi/updateaddress",
					parJson, false);

			if ("000" === res.status) {
				// update完成後，將標的地址欄位的值帶入表格的標的地址
				for (i = 0; i < table1203A.getSelectedRows().length; i++) {
					table1203A.getSelectedRows()[i].update({
						txtprop_addr : propAddr
					})
				}
			} else {
				alert("更正地址失敗!");
			}
		}
	}
}

// 更正同險號
function btnUpdRiskNo() {
	
	let riskNo = $('#txtrisk_no').val();

	// 判斷同險代號是空值、沒有勾選
	if (checkIsNullSpace(riskNo)|| table1203A.getSelectedRows().length <= 0) {
		alert("請先選擇同險代號");
	}else{	
		// 檢查是否勾選兩個以上
		if(checkIsChoseTwo()){return;}
		// 檢核分保列印是否為Y
		if(chkacctFlagIsY()){return;}
		
		// 勾選列表的值
		var getRow = table1203A.getSelectedRows()[0].getData()
		var txtRiskNo = $('#txtrisk_no').val();
		var txtRiskName = $('#txtrisk_name').val();

		let isUpdate = confirm("『是否確認更正同險號:"+riskNo+"』");
		
		if(isUpdate){
			
			// 判斷是否以分保列印
			if (getRow.txtacct_flag !== 'Y' && getRow.txtrisk_flag === 'Y') {
				
				let data = {
						"txtrisk_no" : txtRiskNo,    // 同險代號
						"txtrisk_name" : txtRiskName,// 同險名稱
						"txtpolicy_no" : getRow.txtpolicy_no,     // 保單號碼
						"txtendorse_no" : getRow.txtendorse_no,   // 批單號碼
						"numaddr_no" : getRow.numaddr_no          // 地址序號
				}
				let parJson = JSON.stringify(data);
				let res = ajaxPostByJsonParam("../../rin1203aapi/updateriskno",parJson, false);
				
				if ("000" === res.status) {
					
					//update完成後，將同險代號欄位的值帶入表格的同險代號
					for(i = 0; i< table1203A.getSelectedRows().length;i++){			
						table1203A.getSelectedRows()[i].update({txtrisk_no : txtRiskNo,
							                                    txtrisk_name : txtRiskName})
					}
					alert("更正同險號成功");
				} else {
					alert("更正同險號失敗!");
				}
			} else {
				
				//【已分保列印】欄位的值為「Ｙ｣，則【更正同險】按鈕可顯示但不可點選。
				$("#btnUpdRiskNo").attr("disabled", true);// 更正同險按鈕禁按
         		alert("『已分保列印完成，不可更正同險號』");
				
				//檢核狀態是【已處理】='Y',才可更正同險號
				if(getRow.txtacct_flag !== 'Y'){
					alert("『狀態不是【已處理】,不可更正同險號』");
				}

			}
		}
	}
}


// 刪除同險
function btnDelRiskNo() {

	var riskNo = $('#txtrisk_no').val();   //同險代號輸入框

	// 同險代號不可為空白
	if (checkIsNullSpace(riskNo)|| table1203A.getSelectedRows().length <= 0) {
		alert("請先選擇同險代號");
	} else {

		// 檢查是否勾選兩個以上
		if(checkIsChoseTwo()){return;}
		// 檢核分保列印是否為Y
		if(chkacctFlagIsY()){return;}

		let isDelete = confirm("『是否確認刪除同險代號:"+riskNo+"』");

		if (isDelete) {

			let data = {
				"txtrisk_no" : riskNo, // 同險代號
				"txtarea_code" : RIN_1203.txtarea_code // 地段代號
			}
			let parJson = JSON.stringify(data);
			let res = ajaxPostByJsonParam("../../rin1203aapi/delateriskno",parJson, false);

			if ("000" === res.status) {
				alert("刪除同險" + riskNo + "成功");
				$('#riskList').children().remove();
				
				//下拉選單重新查詢
				ddlRiskList();
				
				$("#btnCreateRiskNo").attr("disabled", false);// 產生同險代號按鈕可按
			} else {
				alert("刪除同險失敗!");
			}
		}
	}

}


//新增同險
function btnAddRiskNo() {
	
	//檢核分保列印是否為Y
	if(chkacctFlagIsY()){return;}

	var riskNo   = $('#txtrisk_no').val();   //同險代號輸入框
	var riskName = $('#txtrisk_name').val(); //同險代號輸入框
	
	// 同險代號不可為空白
	if (checkIsNullSpace(riskNo) || table1203A.getSelectedRows().length <= 0) {
		alert("請先選擇同險代號");
	} else {

		let data = {
			"txtrisk_no" : riskNo,    // 同險代號
			"txtrisk_name" : riskName,// 同險名稱
			"txtarea_code" : RIN_1203.txtarea_code    // 地段代號
		}
		let parJson = JSON.stringify(data);
		let res = ajaxPostByJsonParam("../../rin1203aapi/insertfririskbypk",parJson, false);

		if ("000" === res.status) {
			alert("新增同險:"+riskNo+"成功");
			$("#btnAddRiskNo").attr("disabled", true); // 新增同險按鈕禁按
			$('#riskList').children().remove();
			
			//更新下拉選單
			ddlRiskList();
			
		} else {
			alert("新增同險失敗!");
		}
	}
}



//確認同險
function btnConfirmRiskNoList(){
	
	if(checkIsNullSpace($('#riskList').find(':selected').val())){
		alert("請先從下拉選單點選一筆資料");
	}else{
		//同險代號不可為空白
		if(table1203A.getSelectedRows().length <=0){
			alert("請先選擇同險代號");	
			}else{
				//檢核分保列印是否為Y
				if(chkacctFlagIsY()){return;}
				// 勾選列表的值
				var getRow = table1203A.getSelectedRows()[0].getData()
				
				if('Y'=== getRow.txtrisk_flag){
					alert("同險代號:"+getRow.txtrisk_no+"\n批單號碼:"+getRow.txtendorse_no+"\n地址序號:"+getRow.numaddr_no +"\n已經歸過同險,請使用更正同險功能");
					return;
				}
				let isConfirm = confirm("是否要確認同險？");
				if(isConfirm){
					let txtRiskNoName = $('#riskList>select').val()
					
					//勾選列表的值
					let param = {
						"riskNo":txtRiskNoName.split("/")[0],
						"riskName":txtRiskNoName.split("/")[1]
					}
					param.riskDdlList = table1203A.getSelectedData();
					
					let parJson = JSON.stringify(param);
					let res = ajaxPostByJsonParam("../../rin1203aapi/updatepolicydellist", parJson, false);
					
					if("000" === res.status){
						alert("確認同險成功!");
						
						//update完成後，將同險代號欄位的值帶入表格的同險代號
						for(i = 0; i< table1203A.getSelectedRows().length;i++){			
							table1203A.getSelectedRows()[i].update(
									{txtrisk_no:txtRiskNoName.split("/")[0],
								txtrisk_name:txtRiskNoName.split("/")[1],
								txtrisk_flag:'Y'})
						}
						queryRin1203A();
					}else{
						alert("確認同險失敗!");
					}
				}

				
			}
	}
}	


//產生同險代號
function btnCreateRiskNo() {

	var riskNo   = $('#txtrisk_no').val();   //同險代號輸入框

	// 同險代號不可為空白
	if (checkIsNullSpace(riskNo)|| table1203A.getSelectedRows().length <= 0) {
		alert("請先選擇同險代號");
	} else {
		// 檢查是否勾選兩個以上
		if(checkIsChoseTwo()){return;}
		
		if (riskNo !== (table1203A.getSelectedRows()[0].getData().txtarea_code)) {
			alert("產生同險代號時,需與地段代號相同");
		} else {
			let getRow = table1203A.getSelectedRows()[0].getData();
			let data = {
				"number_code" : RIN_1203.txtarea_code  // 地段代號
			}
			let parJson = JSON.stringify(data);
			let res = ajaxPostByJsonParam("../../rin1203aapi/getrisknoserial",parJson, false);
			// 流水號4碼
			let numberFormat = padLeft(res.data, 4);

			// 地段代號+流水號4碼
			$('#txtrisk_no').val(getRow.txtarea_code + numberFormat);

			// 產生完同險代號後，產生同險代號按鈕不能按
			$("#btnCreateRiskNo").attr("disabled", true);// 產生同險代號按鈕禁按
			$("#btnAddRiskNo").attr("disabled", false); // 新增同險按鈕可按
			$("#btnDetelRiskNo").attr("disabled", false); // 刪除同險按鈕可按
		}
	}
}

//流水號共4碼,序號不足往左補0
function padLeft(str, lenght) {
	if (str.length >= lenght) {
		return str;
	} else {
		return padLeft("0" + str, lenght);
	}
}


//更改舊同險號
function btnUpdOldRiskNo(){

	// 判斷同險代號是空值、沒有勾選
	if (checkIsNullSpace(riskNo)|| table1203A.getSelectedRows().length <= 0) {
		alert("請先選擇同險代號");

	}else{//有勾選
		
		// 檢查是否勾選兩個以上
		if(checkIsChoseTwo()){return;}
		// 檢核分保列印是否為Y
		if(chkacctFlagIsY()){return;}
		
		// 勾選列表的值
		var getRow = table1203A.getSelectedRows()[0].getData();
		var txtRiskNoName = $('#riskList>select').val(); //取下拉選單值
		var txtRiskNo=txtRiskNoName.split("/")[0]; //下拉選單_同險代號
		var txtRiskName=txtRiskNoName.split("/")[1];//下拉選單_同險名稱
		
		let isUpdate = confirm("『是否確認更改舊同險號為:"+txtRiskNo+"』");
		
		if(isUpdate){
			
			// 判斷是否以分保列印
			if (getRow.txtrisk_flag === 'Y') {
				
				$("#btnUpdOldRiskNo").attr("disabled", false);// 更改舊同險按鈕可按
				$("#btnUpdRiskNo").attr("disabled", true);// 更改舊同險按鈕禁按
				
				
				let data = {
						"txtrisk_no" : txtRiskNo,                 // 下拉選單_同險代號
						"txtrisk_name" : txtRiskName,             // 下拉選單_同險名稱
						"txtpolicy_no" : getRow.txtpolicy_no,  // 保單號碼
						"txtendorse_no" : getRow.txtendorse_no,// 批單號碼
						"txtprop_addr" : getRow.txtprop_addr   // 標的物地址
				}
				let parJson = JSON.stringify(data);
				let res = ajaxPostByJsonParam("../../rin1203aapi/updateoldriskno",parJson, false);
				
				if ("000" === res.status) {
				
					alert("更改舊同險號成功");
					
					//update完成後，將同險代號欄位的值帶入表格的同險代號		
						table1203A.getSelectedRows()[0].update({txtrisk_no:txtRiskNo,
							txtrisk_name:txtRiskName})
					
				} else {
					alert("更改舊同險號失敗!");
				}
			} else {

				alert("『狀態不是【已處理】,不可更正同險號』");

			}
		}
	}
}

// 下拉選單(取得同險)
function ddlRiskList() {

	let data = {
		"txtarea_code" : RIN_1203.txtarea_code   // 地段代號
	}
	let parJson = JSON.stringify(data);
	let resList = ajaxPostByJsonParam("../../rin1203aapi/queryddlrisklist",parJson, false);
	var ajaxdataSub = resList.data;
	let apidata = new Array();

	if ("000" === resList.status) {
		for (var i = 0; i < ajaxdataSub.length; i++) {

			// 組出來是 同險代號 同險名稱
			apidata.push({
				text : ajaxdataSub[i].txtrisk_no + " "
						+ ajaxdataSub[i].txtrisk_name,
				value : ajaxdataSub[i].txtrisk_no + "/"
						+ ajaxdataSub[i].txtrisk_name
			});
		}
		createDdl("riskList", apidata, "");
	} else {
		alert("下單選單讀取失敗!");
	}
}


//檢核分保列印是否為Y
function chkacctFlagIsY(){
	if(table1203A.getSelectedRows().length >0 && table1203A.getSelectedRows()[0].getData().txtacct_flag==='Y'){
		alert("『已分保列印完成，不可更正同險號』");
		return true;
	}
	return false;
}

//判斷是否勾選兩個以上
function checkIsChoseTwo(){
	if (table1203A.getSelectedRows().length > 1) {
		alert("請擇一選擇");
		return true;
	}
	return false;
}

//回上一頁
function backTo1203(){
	//解鎖當前的地段代號
	let param = [{"txtarea_code":	RIN_1203.txtarea_code}]	//地段代號
	

	let res = ajaxPostByJsonParam("../../rin1203api/deleteusedareain", JSON.stringify(param), false);
	if(res && res.status !== "000"){
		alert("解鎖失敗!!")
	}
	//把第一頁搜尋的值帶回到第一頁
	let data = {
			"txtarea_code" : RIN_1203.searchTxtarea_code, //地段代號
			"policy_dprtS" :RIN_1203.policy_dprtS,        //印單日期起  
			"policy_dprtE" :RIN_1203.policy_dprtE,        //印單日期迄
			"risk_flag":RIN_1203.risk_flag                //範圍		
	}

	let parJson = JSON.stringify(data)
	locationHrefKeepDataType2('Rin1203', '', parJson);
}

