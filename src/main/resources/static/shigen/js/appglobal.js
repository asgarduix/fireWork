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
			// TODO 使用function 帶入的方式
			// alert();
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
			// TODO 使用function 帶入的方式
			// alert();
			return false;
		}
	}
	sessionStorage.setItem("tempFiles", files);

}

//轉為base64
function getBase64(file) {
	var reader = new FileReader(file);
	reader.onload = function(e) {
		console.log(e.target.result);
		$('#base64String').val(e.target.result);
		$('#base64FileName').val(file.name);
	};
	reader.onerror = function(error) {
		console.log('Error: ', error);
	};
	reader.readAsDataURL(file);
}

//// 刪除列表
//function delFileData(num) {
//	files.splice(num, 1);
//}

function preview(num) {
	console.log('第' + num + '筆資料')
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

function createExcel2(caseName, subCaseName, jsonObj) {
	var objKey = Object.keys(jsonObj)

	for (i = 0; i < objKey.length; i++) {
		//'%'符號為特殊字元，須以十六進位制值方式傳遞
		objKey[i] = jsonObj[objKey[i]].replace(/%/g, '%25');
	}

	$.LoadingOverlay("show", {
		custom: "<span style=\"font-size: 25px;\">處理中，請稍候.....</span>"
	});

	ajaxGetTokenReady("../../" + caseName + "/" + subCaseName, jsonObj, false);
	$.LoadingOverlay("hide");
}

/**
 * 以POST方式產生excel檔案
 * @param caseName
 * @param subCaseName
 * @param jsonObj
 * @param fileName
 * @returns
 */
function createExcelByPOST(caseName, subCaseName, jsonObj, fileName) {
	var token = fetchToken();
	var xhr = new XMLHttpRequest();
	xhr.responseType = "arraybuffer";
	xhr.open("POST", "../../" + caseName + "/" + subCaseName, true);
	xhr.onload = function() {
		const blob = new Blob([this.response], { type: "application/vnd.ms-excel" });
		if (blob.size < 1) {
			alert('匯出失敗，匯出的內容為空！');
			return;
		}
		if (window.navigator.msSaveOrOpenBlob) {
			navigator.msSaveOrOpenBlob(blob, fileName + '.xls');
		} else {
			const aLink = document.createElement('a');
			aLink.style.display = 'none';
			aLink.href = window.URL.createObjectURL(blob);
			aLink.download = fileName;
			document.body.appendChild(aLink);
			aLink.click();
			document.body.removeChild(aLink);
		}
	}
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.setRequestHeader("Authorization", "bearer" + " " + token);
	xhr.send(JSON.stringify(jsonObj));
}

/**
 * 左側補0
 * @param str
 * @param lenght
 * @returns
 */
function paddingLeft(str, len) {
	if (str.length >= len)
		return str;
	else
		return paddingLeft("0" + str, len);
}

function fetchPlaninsData(oidHastPePlan, oidHastPeKind, isQuery) {
	let param = {
		"oidPubtPeMain": oidPubtPeMain,
		"oidHastPekind": oidHastPeKind,
		"isQuery": isQuery
	}
	let resInsType = ajaxPostTokenReady("../../HAS301009Api/showInsTypeSetting", param, false);
	return resInsType['data'];
}