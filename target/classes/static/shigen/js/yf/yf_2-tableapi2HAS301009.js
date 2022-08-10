// 前頁帶入的資料
// var keepData = locationHrefKeepDataFetch();
// keepData.fromPage = "301009";
// sessionStorage.setItem("pre_keepData", JSON.stringify(keepData));
var tabledataCon = [];
var tabledataInsType = [];
var testArr = [{ code: '1', text: '固定保額' }, { code: '2', text: '變動保額' }, { code: '3', text: '薪資倍數' }];
let btnFuncsDef = {
	edit: function(e, cell) {
		$('#select,#clean,#exit').attr('disabled', true);
		$('#ddlIinscls > select,#ddlIinskind > select').attr('disabled', true);
	},
	sel: function(e, cell) {
		alert("this event for select");
	}
}

$(function() {
	keepData = { "oid": 1000187281, "oidPubtPeMain": 1000777907, "iplan": "A", "nplan": "A", "nprtcont": "A", "ftype": "1", "faddgrp": null, "fiins": null, "fprem": null, "fstatus": "Y", "fchk": null, "fdefault": "N", "qkind": 2, "imainAm1": null, "imainAm2": null, "memo": "140809/1408036", "fshared": "1", "qshared": null };
	keepData.fromPage = "301009";
	sessionStorage.setItem("pre_keepData", JSON.stringify(keepData));

	// 將前頁數據帶入方案資料
	$('#txtIplan').val(keepData['iplan']);// 方案代號
	$('#txtNplan').val(keepData['nplan']);// 方案名稱
	$('#txtNprtCont').val(keepData['nprtcont']);// 列印名稱
	$('#ddlfshared').val(keepData['fshared']);// 保費分攤指示
	$('#txtqshared').val(keepData['qshared']);// 分攤定額/比例
	$('#txtMemo').val(keepData['memo']);// 備註

	genTable1();
	genTable2();
	
	
	
    //let data1 = fetchTabulator("#conditionTable").getData()[0];
	//let data2 = fetchTabulator("#conditionTable").getData()[5];
	//let data3 = {}
	//let data4 = null
	//let dataPos = fetchMatchingData("#conditionTable", data4);
	//console.log(data4)
	//console.log(dataPos)
	
	
});


/**
 */
function changeToColNextSel(val, thisobj) {
	var target = fetchTarget(thisobj, 8);
	target.find("select").html("");
	var sel2 = crtDdlLoadDDLIINSKind_501Type2("Y", val);
	var options = sel2.innerHTML;
	target.find("select").append(options);
}

/**
離開
 */
function leavePage() {
	var tmpjson = JSON.parse(sessionStorage.getItem('HAS301008_keepData'));
	console.log(tmpjson)
	locationHrefKeepData('HAS301008', 'HAS301008_keepData', tmpjson);
}

/**
 */
function reGenTable1() {
	var resInsType = ajaxPostTokenReady("../../	HAS301009Api/showInsTypeSetting",
		{
			"oid": keepData['oid']
		}, false);


	var data = [];

	//修改第一筆，顯示可以重新塞入資料
	resInsType.data[0].mcover = "999999";

	for (var i = 0; i < resInsType['data'].length; i++) {
		resInsType['data'][i]['id'] = i + 1;
		data.push(resInsType['data'][i]);
	}

	refreshDataToTable("#insTypeTable", data);

	//	stopEditing(cell);
}

/**
 */
function reGenTable2() {
	var table2 = cleanDataToTable("#conditionTable");

	var resCondition = ajaxPostTokenReady("../../HAS301009Api/showConditionSetting",
		{
			"oid": keepData['oid']
		}, false);
	console.log(resCondition['data']);

	var domain = domain4Springboot(true);

	for (var i = 0; i < resCondition['data'].length; i++) {
		resCondition['data'][i]['id'] = i + 1;
		resCondition['data'][i]['nomprem'] = 0;
		var data = JSON.stringify(resCondition['data'][i]);
		//		console.log(data.replace(/"/g, '&quot;'));
		//			resCondition['data'][i]['detail'] = '<a href="#" onclick="locationHrefKeepData(\'HAS301010\', \'\', \'\'); return false;">保費明細</a>';
		//		resCondition['data'][i]['detail'] = '<a href="#" onclick="locationHrefKeepData(\'HAS301010\', \'row' + i + '\', ' + data.replace(/"/g, '&quot;') + '); return false;">保費明細</a>';
		resCondition['data'][i]['detail'] = { "context": "保費明細", "funcNm": locationHrefKeepDataType2, funcParam: ["HAS301010", "row" + i, data.replaceAll("\"", "\\\"")] };
		resCondition['data'][i]['download'] = { "context": "test.txt", "funcNm": func4download, funcParam: [domain + "/frontendapi/downloadFileSample/" + "test.txt"] };
		//		tabledataCon.push(resCondition['data'][i]);
	}

	table2.addData(resCondition);
}

/**
 */
function genTable1() {
	if (checkIsNullSpace(tmp) == false) {
		tmp.clearData();
	}

	var tableId = "insTypeTable";
	$("#" + tableId + "-addToDb").off();
	$("#" + tableId + "-reactivityAdd").off();
	$("#" + tableId + "-addToDb").insertBefore("#" + tableId + "-pagination")
	$("#" + tableId + "-reactivityAdd").insertBefore("#" + tableId + "-pagination")
	$("#" + tableId + "-pagination").empty();
	$("#" + tableId + "").empty();
	//	createTable(tableParams, tabledata, "#example-table", true);

	var calType = crtSelector(testArr);

	var param = {
		"oid": keepData['oid']
	}

	// 險種設定
	var resInsType = ajaxPostTokenReady("../../	HAS301009Api/showInsTypeSetting",
		param, false);

	var data = [];

	for (var i = 0; i < resInsType['data'].length; i++) {
		resInsType['data'][i]['id'] = i + 1;
		data.push(resInsType['data'][i]);
	}
	
	for (let k = 0; k < data.length; k++) {
		data[k].uniqueid = `${k}17591741ggiwsg8f8w2h39${k}` 
	}
	
	var insTypeTableParams = [
		// ["No.", "number", "id"],
		//["checkbox"],
		["select1","選取","checkboxTitle"],
		["select2", "是否承保","checkboxTitle"],
		["select3", "是否列入黑名單","checkboxTitle"],
		//["CopyButton", "複製", "button", true, "fix"],
		//["EditButton", "編輯", "button", true, null],
		//["CancelButton", "取消", "button", false, null],
		//["SaveButton", "儲存", "button", false, null],
		//["DeleteButton", "刪除", "button", true, null],
		//["SelectButton", "選取", "button", true, null],
		["No.", "序號", "rownum"],
		//		["iinscls", "險別", "hide"],
		["iinscls", "險別", "selectCus", { "funcNm": crtDdlLoadDDLIINSCLSType2, "params": [["I", "fix"]], "eventFunc": changeToColNextSel }],
		["iinskind", "保單類別", "selectCus", { "funcNm": crtDdlLoadDDLIINSKind_501Type2, "params": [["I", "fix"], [7, "other_select"]] }],
		["iinstype", "險種", "selectCus", { "funcNm": crtDdlLoadDDLNCMNpInstypeType2, "params": [["", "fix"]] }],
		["mcover", "承保額度", "number"],
		["ftype", "保額計算方式", "selectCus", { "context": calType, "params": [["3", "fix"]] }],
		["maxamt", "最高承保限額", "number"],
		["minamt", "最低承保限額", "number"],
		["itimes", "職資倍數", "number"],
		["funit", "保額單位", "selectCus", { "funcNm": crtDdlLoadDDLFunitType2, "params": [["1", "fix"]] }],
		["qday", "限制天數", "number"],
		["fqday", "單位", "selectCus", { "funcNm": crtDdlLoadDDLFqdayType2, "params": [["", "fix"]] }]
	];

	var statusBarBtnMethods = [{
		"id": "#insTypeTable-addToDb",
		"funcNm": function() {
			//取回表格
			alert("fetched table!");
			var tabu = fetchTabulator("#insTypeTable");//取回為tablutor的table物件，後續須使用相關api操作
			var datas = tabu.getData();

			for (let i = 0; i < datas.length; i++) {
				var data = datas[i];
				console.log(data);
			}
		}
	}];
	
	
	data.forEach( dp => {
		dp.select1 = false;
		dp.select2 = false;
		dp.select3 = false;
	})
	//true to show edit and delete button, false to hide
	// createTable(insTypeParams, tabledataInsType, "#insTypeTable", true);
	
	let idColumnName = 'oid'
	
	let nonEditableParams = [4,5,6,7];
	
	let showNo = false;
	let destination = `#${tableId}`
	let insTypeTableData = data;
	tmp = createTableddlref(insTypeTableParams, insTypeTableData, destination, btnFuncsDef, showNo, statusBarBtnMethods, idColumnName, nonEditableParams );
}

/**
 */
function genTable2() {
	var param = {
		"oid": keepData['oid']
	}

	var resCondition = ajaxPostTokenReady("../../HAS301009Api/showConditionSetting",
		param, false);
	console.log(resCondition['data']);

	var domain = domain4Springboot(true);

	for (var i = 0; i < resCondition['data'].length; i++) {
		resCondition['data'][i]['id'] = i + 1;
		resCondition['data'][i]['nomprem'] = 0;
		var data = JSON.stringify(resCondition['data'][i]);
		//		console.log(data.replace(/"/g, '&quot;'));
		//			resCondition['data'][i]['detail'] = '<a href="#" onclick="locationHrefKeepData(\'HAS301010\', \'\', \'\'); return false;">保費明細</a>';
		//		resCondition['data'][i]['detail'] = '<a href="#" onclick="locationHrefKeepData(\'HAS301010\', \'row' + i + '\', ' + data.replace(/"/g, '&quot;') + '); return false;">保費明細</a>';
		resCondition['data'][i]['detail'] = { "context": "保費明細", "funcNm": locationHrefKeepDataType2, funcParam: ["HAS301010", "row" + i, data.replaceAll("\"", "\\\"")] };
		resCondition['data'][i]['download'] = { "context": "test.txt", "funcNm": func4download, funcParam: [domain + "/frontendapi/downloadFileSample/" + "test.txt"] };
		tabledataCon.push(resCondition['data'][i]);
	}

	var conditionParams = [
		//	[ "No.", "number", "id" ],
		["CopyButton", "複製", "button", true, "fix"],
		["EditButton", "編輯", "button", true, null],
		["CancelButton", "取消", "button", false, null],
		["SaveButton", "儲存", "button", false, null],
		//		["DeleteButton", "刪除", "button", true, null],
		["No.", "序號", "rownum"],
		["qyrbgn", "起始年齡(年)", "number"],
		["qdaybgn", "調整天數(天)", "number"],
		["qyrend", "截止年齡(年)", "number"],
		["qdayend", "調整天數(天)", "number"],
		["akin", "身份別", "selectCus", { "funcNm": crtDdlLoadDDLFrelIssuType2, "params": [["3", "fix"]] }],
		["fsex", "性別", "selectCus", { "funcNm": crtDdlLoadDDLFsexType2, "params": [["1", "fix"]] }],
		["focpyclsBgn", "職業等級起", "selectCus", { "funcNm": crtDdlLoadDDLFocpyClsType2, "params": [["3", "fix"]] }],
		["focpyclsEnd", "職業等級迄", "selectCus", { "funcNm": crtDdlLoadDDLFocpyClsType2, "params": [["6", "fix"]] }],
		["frenew", "新/續保", "selectCus", { "funcNm": crtDdlLoadDDLFrenewType2, "params": [["1", "fix"]] }],
		["fspecial", "特殊件註記", "selectCus", { "funcNm": crtDdlLoadDDLisdiskType2, "params": [["2", "fix"]] }],
		["mprem", "保費", "number"],
		["nomprem", "未分配保費", "input"],
		["detail", "功能", "url"],
		["download", "下載", "url"]
	];

	var defaultData = JSON.parse('{"qyrbgn":0,"qdaybgn":0,"qyrend":0,"qdayend":0,"akin":"","fsex":"2","focpyclsBgn":"","focpyclsEnd":"","frenew":"","fspecial":"","mprem":0,"nomprem":""}');

	var statusBarBtnMethods = [
		{
			"id": "#conditionTable-addToDb",
			"funcNm": function() {
				alert("addToDO");
			}
		},
		{
			"id": "#conditionTable-reactivityAdd",
			"data": defaultData
		}
	];

	createTableddlref(conditionParams, tabledataCon, "#conditionTable", {}, false, statusBarBtnMethods, '');
}

/**
 */
var tmp = null;

/**
 */
function func4download(url) {
	window.open(url + "?" + "token=" + fetchToken());
}


function getHistory(){
	let history = getEditHistory();
	let insTypeTableHistory = history['#insTypeTable']
	//let conditionTableHistory = history['#conditionTable']  //, conditionTableHistory , insTypeTableHistory
	console.log(history)	
}