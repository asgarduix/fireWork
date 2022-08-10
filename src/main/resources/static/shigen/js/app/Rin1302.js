$(function(){
	// 開始日期：預設帶系統日
	$('#dtaStart').val(formatDateYYYYMMDD(new Date()));
});

/** 臨份合約到期日期正規表示 yyyy/MM*/
var scheduledDatePatern = '^[0-9]{4}/(0[1-9]|1[1-2])$';

/** 列印*/
function exportExcel() {
	
	if (!validateExpiredDate($('#txt_treaty_year').val().trim())) {
		alert("日期為西元年/月，日期範圍輸入錯誤！ 請重新輸入！");
		return;
	}
	
	let radioValue = $("input[name='radio']:checked").val();

	if (radioValue == '1') { //立即執行
		immediateExport();
	} else if (radioValue == '2') { //排程執行
		scheduledExport();
	}

}

/** 立即執行列印 */
function immediateExport() {
	let param = {
		"treaty_dend": $('#txt_treaty_year').val().trim(),
		"account": localStorage.getItem("account")
	}

	let res = ajaxPostByJsonParam("../../rin1302api/exportexcel", JSON.stringify(param));

	if ('000' === res.status) {
		if (!checkIsNullSpace(res.data.fileBase64Encode)) {
			openFile(res.data.fileName + ".xls", res.data.fileBase64Encode)
			alert("列印成功")
		} else {
			alert("查無資料！");
		}

	} else {
		alert("列印失敗!!!請聯絡管理人員!!!");
	}
}

/** 排程執行列印 */
function scheduledExport() {
	var dtaStart = $('#dtaStart').val();

	var ddlHour = $('#ddlHour').val();

	var ddlMin = $('#ddlMin').val();

	var ddlddlMin = $('#ddlddlMin').val();

	var submittime = dtaStart + " " + ddlHour + ":" + ddlMin + ":" + ddlddlMin;

	var submittimeDate = new Date(submittime);

	var nowtime = new Date();

	if (submittimeDate <= nowtime || dtaStart == '') {
		alert("輸入的時間要是未來時間！！！");
		return;
	}
	
	let param = {
		"dtaStart": dtaStart,
		"ddlHour": ddlHour,
		"ddlMin": ddlMin,
		"ddlddlMin": ddlddlMin,
		"treaty_dend": $('#txt_treaty_year').val().trim(),
		"account": localStorage.getItem("account")
	}

	let res = ajaxPostByJsonParam("../../rin1302api/insertscheduleexcel", JSON.stringify(param), false);

	if (res && "000" === res.status) {
		alert("已寫入排程！");
	} else {
		alert("排程失敗!!");
	}

}

/** 檢查臨份合約到期日期格式*/
function validateExpiredDate(dateStr) {
	return dateStr.match(scheduledDatePatern);
}

/**
 * 日期格式檢核
 * @returns
 */
$('#dtaStart').change(function(){
	try {
		if(!isValidDate($(this).val())){
			alert("日期格式錯誤，請輸入YYYY/MM/DD或是YYYY/M/D");
			$(this).val('');
		}
	} catch (e) {
		alert(e);
	}
});