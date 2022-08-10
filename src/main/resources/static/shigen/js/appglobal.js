/**
 * 儲存送審意見
 * @param data
 * @param checkfrom
 * @returns
 */
	
	
function saveCheckInfo(data, checkfrom) {
	var applno = $('#applnoAf').val();
	if (checkIsNullSpace(applno)) {
		applno = $('#applnoAx').val();
	}
	if (checkIsNullSpace(applno)) {
		styleAlert("尚未產生申請單號");
		return;
	}
	var opinionF = $('#textarea03').val();
	var opinionX = $('#textarea07').val();
	var checkTextParam = {
		"applno": applno,
		"opinionF": opinionF,
		"opinionX": opinionX,
		"action": data.innerText,
		"checkfrom": checkfrom
	};
	var checkText = ajaxPostTokenReady("../../insEndorseBuildCreateInsideApi/checkInfo", checkTextParam, false);
	styleAlert(checkText['data']);

	checkInfoHistory(applno, checkfrom);
}

/**
 * 顯示歷史訊息
 * @param applno
 * @param checkfrom
 * @returns
 */
function checkInfoHistory(applno, checkfrom) {
	var checkInfoParam = {
		"applno": applno
	}
	var checkInfoHistory = ajaxPostTokenReady("../../insEndorseBuildCreateInsideApi/checkInfoHistory", checkInfoParam, false);
	console.log(checkInfoHistory);
	$('#textarea04').val(checkInfoHistory['data']['checkInfoHistoryF']);
	$('#textarea08').val(checkInfoHistory['data']['checkInfoHistoryX']);
}

/*
 * 車種列表
 */
function prepareCarType() {
	var param = {};
	res = ajaxPostTokenReady("../../commonController/queryDropDownDataList", param, false);
	var str = '';
	var carTypeList = res['data'];
	for (var i = 0; i < carTypeList.length; i++) {
		var value = carTypeList[i].mappingValue;
		if ("string" == typeof value) {
			str += '<option value="' + carTypeList[i].mappingValue.trim() + '">' + carTypeList[i].displayName + '</option>';
		} else {
			str += '<option value="' + carTypeList[i].mappingValue + '">' + carTypeList[i].displayName + '</option>';
		}
	}
	$('#input183').html(str);
	$('#input65').html(str);
}

// 取得批單申請的批文生效日期
function prepareOldEnvDate(applno) {
	var envDateMap = ajaxPostTokenReady("../../insEndorseBuildCreateInsideApi/preparedenvDate", { "applno": applno }, false);
	var envdateF = formatDateYYYYMMDD(envDateMap['data']['envdatF']);
	var envdateX = formatDateYYYYMMDD(envDateMap['data']['envdatX']);
	prepareChangelog(applno, 'appl', envdateF, envdateX);
}

/*
 * 檔案上傳暫存資料
 */
function uploadFileTemp() {
	var file = $('#file01')[0].files[0];
	if (files.length == 0) {
		if (!checkIsNullSpace(file)) {
			files.push(file);
			base64Array.push($('#base64String').val());
			fileNameArray.push($('#base64FileName').val());
		} else {
			styleAlert("請選擇檔案");
			return false;
		}
	} else {
		if (!checkIsNullSpace(file)) {
			for (var i = 0; i < files.length; i++) {
				if (file.name == files[i].name && file.size == files[i].size) {
					styleAlert('待上傳列表中已有相同檔案');
					return false;
				}
			}
			files.push(file);
			base64Array.push($('#base64String').val());
			fileNameArray.push($('#base64FileName').val());
		} else {
			styleAlert("請選擇檔案");
			return false;
		}
	}
	sessionStorage.setItem("tempFiles", files);
	showFileList(files);

}

// 顯示檔案列表
function showFileList(files) {
	console.log(files);
	var str = '';
	for (var i = 0; i < files.length; i++) {
		var names = files[i].name.split('.');
		str += '<tr><td>';
		str += '<button class="button" onclick="delFileData(' + i + ')">刪除</button>';
		str += '<button class="button">檢視</button>';
		str += '</td>';
		str += '<td>' + names[0] + '</td>';
		str += '<td>' + names[1] + '</td>';
		str += '</tr>';
	}
	$('#fileDataAf').html(str);
	$('#fileDataAx').html(str);
	$('#dataInfoAf').show();
	$('#dataInfoAx').show();
}

//轉為base64
function getBase64(file) {
	var reader = new FileReader(file);
	reader.onload = function (e) {
		console.log(e.target.result);
		$('#base64String').val(e.target.result);
		$('#base64FileName').val(file.name);
	};
	reader.onerror = function (error) {
		console.log('Error: ', error);
	};
	reader.readAsDataURL(file);
}

// 刪除列表
function delFileData(num) {
	files.splice(num, 1);
	showFileList(files);
}

function preview(num) {
	console.log('第' + num + '筆資料')
}


function preview(applno, name, type) {
	var url = "&applno=" + applno
		+ "&name=" + name
		+ "&type=" + type
	window.open("../../insEndorseBuildCreateInsideApi/preview?useUnicode=true&amp;characterEncoding=UTF-8" + url);
}

/**
 * 資料上傳(建檔內頁)
 * @returns
 */
function uploadFile(applno, files) {
	// 取得既有申請單號
	//	if (checkIsNullSpace(applno)) {
	//		styleAlert("請先取得申請單號");
	//		return false;
	//	}
	var res = null;
	var param = {
		"applno": applno,
		"base64Array": JSON.stringify(base64Array),
		"fileNameArray": JSON.stringify(fileNameArray)
	}
	res = ajaxPostTokenReady("../../commonController/fileUplaod", param, false);
	var map = res['data'];
	var str = '';
	var fileNames = Object.keys(map);
	var results = Object.values(map);
	for (var i = 0; i < fileNames.length; i++) {
		str += fileNames[i] + ' : ' + results[i] + '</br>';
	}
	if (!checkIsNullSpace(map)) {
		styleAlert(str);
	}
}

/**
TODO ???
 */
function isEndmk(applno) {
	var param = {
		"applno": applno
	}
	res = ajaxPostTokenReady("../../commonController/queryEndmkStatusByApplno", param, false);
	return res['data'];
}

/**
 * 產生excel檔案
 * @param operationNo
 * @param paramJson
 * @returns
 */
function createExcel(caseName, subCaseName, jsonObj) {
	var objKey = Object.keys(jsonObj)
	var url = ''
	for (i = 0; i < objKey.length; i++) {
		url = url + '&' + objKey[i] + '=' + jsonObj[objKey[i]]
	}
	//'%'符號為特殊字元，須以十六進位制值方式傳遞
	var newString = url.replace(/%/g, '%25')
	$.LoadingOverlay("show", {
		custom : "<span style=\"font-size: 25px;\">處理中，請稍候.....</span>"
	});
	window.location.href = "../../" + caseName + "/" + subCaseName + "?useUnicode=true&amp;characterEncoding=UTF-8" + newString + "&" + "token=" + fetchToken();
	$.LoadingOverlay("hide");
}

function createExcel2(caseName, subCaseName, jsonObj) {
	 var objKey = Object.keys(jsonObj)
	 
	 for (i = 0; i < objKey.length; i++) {
	  //'%'符號為特殊字元，須以十六進位制值方式傳遞
	  objKey[i] = jsonObj[objKey[i]].replace(/%/g, '%25');
	 }
	 
	 $.LoadingOverlay("show", {
	  custom : "<span style=\"font-size: 25px;\">處理中，請稍候.....</span>"
	 });
	 
	 ajaxGetTokenReady("../../" + caseName + "/" + subCaseName, jsonObj, false);
	 $.LoadingOverlay("hide");
}

/**
 * 左側補0
 * @param str
 * @param lenght
 * @returns
 */
function paddingLeft(str,len){
	if(str.length >= len)
	return str;
	else
	return paddingLeft("0" +str,len);
}

function fetchPlaninsData(oidHastPePlan, oidHastPeKind, isQuery) {
	let param = {
			"oidPubtPeMain" : oidPubtPeMain,
			"oidHastPekind" : oidHastPeKind,
			"isQuery" : isQuery
	}
	let resInsType = ajaxPostTokenReady("../../HAS301009Api/showInsTypeSetting",param, false);
	return resInsType['data'];
}














