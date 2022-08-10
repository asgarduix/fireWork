/*
/*function checkNemuricEmptyEnabled(elementId,labelName,len,scale){

	if(!isNumber(elementId,labelName+' 必須是數字')) return false;
	var fieldobject = getObject(elementId);
	var temp = fieldobject.value;
	if(temp.indexOf(".") ==-1){
		if(temp.length > len){
			return showError(fieldobject ,labelName + " 整位數只可輸入" + len + "位數");
		}
	}else{
		var arr=temp.split(".");
		if(arr[0].length > len){
			return showError(fieldobject ,labelName + " 整位數只可輸入" + len+ "位數");
		}
		if(arr[1].length > scale){
			return showError(fieldobject ,labelName + " 小數位數最多只可輸入" + scale+ "位數");
		}
	}
	return "";
}

function checkNemuric(elementId,labelName,len,scale){
	if(!isEmpty(elementId,"請輸入"+labelName)) return false;
	if(!isNumber(elementId,labelName+' 必須是數字')) return false;
	var fieldobject = getObject(elementId);
	var temp = fieldobject.value;
	if(temp.indexOf(".") ==-1){
		if(temp.length > len){
			return showError(fieldobject ,labelName + " 整位數只可輸入" + len + "位數");
		}
	}else{
		var arr=temp.split(".");
		if(arr[0].length > len){
			return showError(fieldobject ,labelName + " 整位數只可輸入" + len+ "位數");
		}
		if(arr[1].length > scale){
			return showError(fieldobject ,labelName + " 小數位數最多只可輸入" + scale+ "位數");
		}
	}
		return "";
}*/
var jsform = null;

function setformname(formname) {
	jsform = formname;
}
/**
 * 下拉式清單 label 顯示方式 label
 * 
 * @param id
 *            元素id
 * @param obj
 *            資料物件
 * @param defaultValue
 *            預設值
 */
function getSelectOption(id, obj, defaultValue) {
	$("#" + id).children().remove();
	if (null != obj && undefined != obj) {
		for (var i = 0, j = obj.length; i < j; i++) {
			if (obj[i].value == new String(defaultValue)) {
				$("#" + id)[0].options.add(new Option(obj[i].label, obj[i].value, false, true));
			} else {
				$("#" + id)[0].options.add(new Option(obj[i].label, obj[i].value));
			}
		}
	}
}
/**
 * 下拉式清單 label 顯示方式 valus_label
 * 
 * @param id
 *            元素id
 * @param obj
 *            資料物件
 * @param defaultValue
 *            預設值
 */
function getSelectOption1(id, obj, defaultValue) {
	$("#" + id).children().remove();
	var label;
	for (var i = 0, j = obj.length; i < j; i++) {
		if (obj[i].value == "") {
			label = obj[i].label;
		} else {
			label = obj[i].value + "-" + obj[i].label;
		}
		if (obj[i].value == new String(defaultValue)) {
			$("#" + id)[0].options.add(new Option(label, obj[i].value, false, true));
		} else {
			$("#" + id)[0].options.add(new Option(label, obj[i].value));
		}
	}
}

function getCheckBox(id, obj, defaultValue) {
	var tempCheckBox = "";
	var tempArray = defaultValue.split(",");
	tempCheckBox = tempCheckBox + "&nbsp;&nbsp;<input type=\"checkbox\" name=\"checkAll\" id=\"checkAll\"/>全選";

	for (var i = 0, j = obj.length; i < j; i++) {
		if (i == 5) {
			tempCheckBox = tempCheckBox + "<br/>";
		}
		if (obj[i].value == "") {
		} else {
			if (obj[i].value in tempArray) {
				tempCheckBox = tempCheckBox + "&nbsp;&nbsp;<input type=\"checkbox\" name=\"" + id + "\" id=\"" + id + "\"  value=\""
					+ obj[i].value + "\" checked=\"checked\" />" + obj[i].value + "-" + obj[i].label;
			} else {
				tempCheckBox = tempCheckBox + "&nbsp;&nbsp;<input type=\"checkbox\" name=\"" + id + "\" id=\"" + id + "\"  value=\""
					+ obj[i].value + "\" />" + obj[i].value + "-" + obj[i].label;
			}
		}
	}
	return tempCheckBox;
}

/**
 * 表格型態的checkBox
 * 
 * @param id
 *            元素id
 * @param verId
 *            版本id
 * @param obj
 *            資料物件(資料表示:label-value|ver)
 * @param defaultValue
 *            預設值
 */
function getTableBodyCheckBox(id, verId, obj, defaultValue) {
	var tempCheckBox = "";
	var tempArray = defaultValue;
	var head = "<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" class=\"datatable_s\">";
	var endHead = "</table>";
	var objValue = "";
	var objVer = "";
	var objTemp = [];
	for (var i = 0, j = obj.length; i < j; i++) {
		if (i % 3 == 0) {
			tempCheckBox = tempCheckBox + ((parseInt(i / 3)) % 2 == 1 ? "<tr>" : "<tr bgcolor=\"#F8F8F8\">");
			tempCheckBox = tempCheckBox + "<td width=\"10%\"></td><td width=\"30%\">";
		} else {
			tempCheckBox = tempCheckBox + "<td width=\"30%\">";
		}
		if (obj[i] == "") {
		} else {
			objTemp = obj[i].split("|");
			objValue = objTemp[0].split("-")[0];
			objVer = objTemp[1];

			if (tempArray[i] != "") {
				tempCheckBox = tempCheckBox + "<input type=\"checkbox\" name=\"" + id + "\" id=\"" + id + "\"  value=\"" + objValue + "-"
					+ objVer + "\" checked=\"checked\" />" + objTemp[0];
			} else {
				tempCheckBox = tempCheckBox + "<input type=\"checkbox\" name=\"" + id + "\" id=\"" + id + "\"  value=\"" + objValue + "-"
					+ objVer + "\" />" + objTemp[0];
			}
		}
		if (i % 3 == 2) {
			tempCheckBox = tempCheckBox + "</td></tr>";
		} else {
			tempCheckBox = tempCheckBox + "</td>";

			// 補齊table
			if (i == (j - 1)) {
				for (var k = 0, z = j % 3; k <= z; k++) {
					tempCheckBox = tempCheckBox + "<td width=\"30%\"></td>";
				}
				tempCheckBox = tempCheckBox + "</tr>";
			}
		}

	}
	return head + tempCheckBox + endHead;
}
/**
 * 顯示日期格式為YYYY/MM/DD
 * 
 * @param data
 */
function dateDisplay(data) {
	var trans = convertDateFormat(data, "/");
	document.write(trans);
}
function shortDate(data) {
	if (data == undefined)
		return "";
	var trans = convertDateFormat(data, "/");
	return trans;
}
/**
 * 轉換日期格式
 * 
 * @param separated
 *            為/或-
 */
function convertDateFormat(date, st) {
	var trans = date;
	if (date.length == 8) {
		trans = date.substring(0, 4) + st + date.substring(4, 6) + st + date.substring(6, 8);
	} else if (date.length == 7) {
		trans = date.substring(0, 3) + st + date.substring(3, 5) + st + date.substring(5, 7);
	} else if (date.length > 8) {
		trans = date.substring(0, 4) + st + date.substring(5, 7) + st + date.substring(8, 10);
	}
	return trans;
}

/**
 * 判斷過帳期間
 * 
 * @param fieldName
 *            欄位名稱
 * @param dateFormat
 *            日期格式
 * @param lenmsg
 * @param msg
 * @returns
 */
/*
 * function isPeriod(fieldName, dateFormat, lenmsg, msg){ var fieldobject =
 * getObject(fieldName); var date = fieldobject.value; // 必須為7碼 if (date.length !=
 * 7) { return showError(fieldobject,lenmsg);//過帳期間格式錯誤 } // 第3碼必須為0
 * if(parseInt(date.substring(4, 5),10) != 0){ return
 * showError(fieldobject,msg);//過帳期間格式錯誤 } return ""; }
 */
/**
 * jquery ajax common fucntion
 * 
 * @param url
 *            url網址
 * @param param
 *            post 傳入至action參數
 * @param callback
 *            success function name
 * @param errorCallback
 *            error function name
 */
function ajaxRequest(url, param, callback, errorCallback) {
	$.ajax({
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		type: 'post',
		url: url,
		data: param,
		async: false,
		dataType: "json",
		success: function (data) {
			callback(data);
		},
		error: function (data) {
			errorCallback(data);
		}
	});
}

/**
 * jquery ajax common fucntion
 * 
 * @param url
 *            url網址
 * @param param
 *            post 傳入至action參數
 * @param callback
 *            success function name
 * @param errorCallback
 *            error function name
 */
function ajaxRequestIsAsyncDynimic(url, isAsync, param, callback, errorCallback) {
	$.ajax({
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		type: 'post',
		url: url,
		data: param,
		async: isAsync,
		dataType: "json",
		success: function (data) {
			callback(data);
		},
		error: function (data) {
			errorCallback(data);
		}
	});
}

function removeComma(val) {
	if (val != undefined && val != "" && val != null) {
		val = new String(val);
		var temp = val.replace(/,/g, '');
		return temp;
	}
	return val;
}

function addCommas(val) {
	var s = new String(val);
	if (s != null && s.length > 0) {
		s = removeComma(s);
		var sValue = s;
		var r = "";
		var arr = s.split(".");
		if (arr != null && arr.length > 0) {
			sValue = arr[0];
			if (arr.length > 1) {
				r = removeZero(arr[1]);
				if (r.length > 0) {
					r = "." + r;
				}
			}
			var sRegExp = new RegExp('(-?[0-9]+)([0-9]{3})');
			while (sRegExp.test(sValue)) {
				sValue = sValue.replace(sRegExp, '$1,$2');
			}
			return sValue + r;
		}
	}
	return s;
}
/**
 * 小數點後移除零
 * 
 * @param val
 * @returns {String}
 */
function removeZero(val) {
	if (val == "")
		return "";
	if (val == null)
		return "";
	if (val == undefined)
		return "";
	var temp = new String(val);
	var single = temp.substring(temp.length - 1);
	if (parseInt(single) == 0) {
		temp = temp.substring(0, temp.length - 1);
		return removeZero(temp);
	}
	return temp;
}

/**
 * 送出之前class為field_N移除逗號&class為field_N
 */
function beforeSubmit() {

	$(":input").each(function () {
		var text = "";
		// console.log($(this).attr("numeric"));
		if ($(this).attr("type") == "text" && $(this).attr("numeric") != undefined) {

			text = $(this).val();
			$(this).val(removeComma(text));
		}

		if ($(this).attr("class") == "hasDatepicker") {
			text = $(this).val();
			$(this).val(text.replace(/\//g, ""));
		}
	});
}
/**
 * 將頁面的textbox 指定為readonly ,checkbox、radio、select 指定為 disabled
 */
function allReadOnly() {
	$(":input").each(function () {
		if ($(this).attr("type") == "text") {
			$(this).attr("readonly", "readonly");
		}
		if ($(this).attr("type") == "checkbox") {
			$(this).attr("disabled", "disabled");
		}
		if ($(this).attr("type") == "radio") {
			$(this).attr("disabled", "disabled");
		}
	});

	$("select").each(function () {
		$(this).attr("disabled", "disabled");
	});
}
function cleanAllValue() {
	$(":input").each(function () {
		if ($(this).attr("type") == "text") {
			$(this).val("");
		}
		if ($(this).attr("type") == "checkbox") {
			$(this).removeAttr("checked");
		}
		if ($(this).attr("type") == "radio") {
			$(this).removeAttr("checked");
		}
	});

	$("select").each(function () {
		$(this).get(0).selectedIndex = 0;
	});
}
function parseDateColumn(data) {
	return formatDate(new Date(data), "yyyy/MM/dd");
}
/**
 * 
 * @param msg
 *            錯誤訊息
 * @returns {String}
 */
/**function setErrorMessage(msg) {
	var error = "<div class=\"info\">";
	error = error + "<img style=\"vertical-align: middle\" src=\"" + contextPath + "/images/common/information.gif\">";
	error = error + "<span class=\"actionMessage\">" + msg + "</span>";
	error = error + "<br>";
	error = error + "</div>";
	return error;
}
function messageErrorBox(msg, title) {
	new $.msgbox({
		type : 'alert',
		title : title,
		content : msg,
	}).show();
	$("#jMsgbox_content").css("height", "100px");
}
function messageBox(msg, title) {
	new $.msgbox({
		type : 'alert',
		title : title,
		content : msg
	}).show();
	$("#jMsgbox_content").css("height", "60px");
}
**/


/**
 * html id 命名規則為name_列數
 * 
 * @param id
 *            html id
 * @param cloneRow
 *            是否複製上一列資料 true:是 false:否
 * @returns
 */
function addTableRow(id, cloneRow) {
	var tableBody = $('#' + id).find("tbody");
	var trLast = tableBody.find("tr:last");
	var trNew = trLast.clone();
	if (!cloneRow) {
		trNew.find('input').val("");
	}
	trLast.after(trNew);
	if (cloneRow) {
		trNew.find('input').each(function () {
			var type = $(this).attr("type");
			// if(type!="button"){
			var id = $(this).attr("id");
			var currId = id.split("_")[0];
			var currIndex = id.split("_")[1];
			var topIndex = parseInt(currIndex) + 1;
			$(this).attr("id", currId + "_" + topIndex);
			$("#" + currId + "_" + (topIndex)).val($("#" + currId + "_" + (topIndex - 1)).val());
			// }
		});
		trNew.find('select').each(function () {
			var id = $(this).attr("id");
			var currId = id.split("_")[0];
			var currIndex = id.split("_")[1];
			var topIndex = parseInt(currIndex) + 1;
			$(this).attr("id", currId + "_" + topIndex);
			$("#" + currId + "_" + (topIndex)).val($("#" + currId + "_" + (topIndex - 1)).val());
		});
	}
}
function addTableRowByArray(id, cloneRow) {
	var tableBody = $('#' + id).find("tbody");
	var trLast = tableBody.find("tr:last");
	var trNew = trLast.clone();
	if (!cloneRow) {
		trNew.find('input').val("");
	}
	trLast.after(trNew);
	if (cloneRow) {
		trNew.find('input,select').each(function () {
			var type = $(this).attr("type");
			if (type != "button") {
				var id = $(this).attr("id");
				var name = $(this).attr("name");
				var lindexOf = name.indexOf("[");
				var rindexOf = name.indexOf("]");
				var currId = id.split("_")[0];
				var currIndex = id.split("_")[1];
				var topIndex = parseInt(currIndex) + 1;
				var tempName = name.substring(0, lindexOf + 1) + topIndex + name.substring(rindexOf, name.length);
				$(this).attr("name", tempName);
				$(this).attr("id", currId + "_" + topIndex);
				$("#" + currId + "_" + (topIndex)).val($("#" + currId + "_" + (topIndex - 1)).val());
			}
		});

	}
}

function removeLastRow(id) {
	var rowCount = $("#" + id + " tr").length;
	if (rowCount > 2) {
		$("#" + id + " tbody tr:last").remove();
	}
}

/**
 * 輸入英文
 * 
 * @param tempStr
 */
function enterEnglish(obj) {
	var tempStr = $(obj).val();
	$(obj).val(tempStr.replace(/[^A-Z|a-z]/g, ''));
}

/**
 * 輸入數字英文
 * 
 * @param tempStr
 */
function enterDigitalEnglish(obj) {
	var tempStr = $(obj).val();
	tempStr = tempStr.toUpperCase();
	$(obj).val(tempStr.replace(/[^A-Z0-9]/g, ''));
}
function enterArabEng(obj) {
	var tempStr = $(obj).val();
	$(obj).val(tempStr.replace(/[^A-Za-z0-9]/g, ''));
}

/**
 * 去除非數字、非斜線的字元
 * 
 * @param obj
 */
function enterDigitalSlash(obj) {
	var tempStr = $(obj).val();
	$(obj).val(tempStr.replace(/[^0-9//]/g, ''));
}
/**
 * 輸入數字
 * 
 * @param tempStr
 */
function enterNum(obj) {
	var tempStr = $(obj).val();
	tempStr = tempStr.toUpperCase();
	$(obj).val(tempStr.replace(/[^0-9]/g, ''));
}
/**
 * 輸入數字，不含小數點
 * 
 * @param tempStr
 * @returns
 */
function enterDigital(obj) {
	var tempStr = $(obj).val();
	$(obj).val(tempStr.replace(/\D/g, ''));
}
/**
 * 輸入數字，含小數點
 * 
 * @param tempStr
 * @returns
 */
function enterDigitalPoint(obj) {
	var tempStr = $(obj).val();
	$(obj).val(tempStr.replace(/[^0-9.0-9]/g, ''));
}
/**
 * 輸入數字英文並轉大寫
 * 
 * @param tempStr
 */
function toDigitalEnglishUpper(obj) {
	var val = obj.value.toUpperCase();
	obj.value = val.replace(/[^A-Z0-9]/g, '');
}
/**
 * 鎖右鍵
 */
// start
function clickIE() {
	if (document.all) {
		return false;
	}
}
function clickNS(e) {
	if (document.layers || (document.getElementById && !document.all)) {
		if (e.which == 2 || e.which == 3) {
			return false;
		}
	}
}
if (document.layers) {
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown = clickNS;
} else {
	document.onmouseup = clickNS;
	document.oncontextmenu = clickIE;
}
document.oncontextmenu = new Function("return false");

/**
 * 
 */
function checkNaturalNum(val) {
	if (checkRegular(val, "(^[0-9]{1,}$)")) {
		return true;
	} else {
		return false;
	}
}

/**
 * 
 */
function checkNaturalNumOrNumPoint0(val) {
	if (checkRegular(val, "(^[0-9]{1,}[.][0]{1,}$)")) {
		return true;
	} else {
		return false;
	}
}

/**
 * 檢查是否為小數點數
 */
function checkFloatNum(val) {
	if (checkRegular(val, "(^[0-9]{1,}[.][0-9]{1,}$)")) {
		return true;
	} else {
		return false;
	}
}

/**
 * 檢查是否為小數點數或自然數
 */
function checkFloatNumOrNaturalNum(val) {
	if (checkRegular(val, "(^[0-9]{1,}[.][0-9]{1,}$|^[0-9]{1,}$)")) {
		return true;
	} else {
		return false;
	}
}

/**
 * 小數點數檢查並刪除,如果有任何非小數點數,則會刪除最後一個字
 * 
 * @param val
 * @returns
 */
function delNotFloat(inputObj) {
	var val = inputObj.value;

	// 檢查違規狀態
	if (checkRegular(val, "[^0-9.]")) {
		inputObj.value = val.substring(0, val.length - 1);// 移除違規字
		return;
	}

	if (checkRegular(val, "[.]{2,}")) {
		inputObj.value = val.substring(0, val.length - 1);// 移除違規小數點
		return;
	}

	// 檢查可能的輸入狀態,非定義狀態則會移除該字
	// 自動更改為「1.0」時機難抓,故可以與許「1.」這種狀態
	if (checkRegular(val, "[0-9]") || checkRegular(val, "[0-9].[0-9]" || checkRegular(val, "[0-9]."))) {
		inputObj.value = val;
	} else {
		inputObj.value = val.substring(0, val.length - 1);
	}
}

/**
 * (僅針對兆豐專案使用)(僅針對共用視窗模組使用)清除共用視窗input旁的span做清除
 * 
 * @Param idArray
 *            input有使用共用視窗的id陣列
 */
function cleanSpan4SubWindowGroup(idArray) {
	for (var i = 0; i < idArray.length; i++) {
		$("#" + idArray[i]).closest("td").find("span:last").eq(0).html("");
	}
}

/**
 * (僅針對兆豐專案使用)錯誤訊息處理,例:輸入「{0}foobar{1}」和「{"hello","world"}」,回傳為hellofoobarworld
 */
function replaceStrOnly4ChungKuo(origiStr, array) {
	var temp = origiStr;

	for (var i = 0; i < array.length; i++) {
		temp = temp.replace("{" + i + "}", array[i]);
	}

	return temp;
}

/**
 * 檢查是否大於0
 * 
 * @param val
 * @returns {Boolean}
 */
function isBiggerZero(val) {
	var b = false;

	if (!checkIsNullSpace(val)) {
		var n = new Number(val);

		if (n > 0) {
			b == true;
		}
	}

	return b;
}

/**
 * 發動input(with id)陣列的onkeyup(with jQuery)
 * 
 * @param idArray
 */
function triggerOnkeyupInInputByIdArray(idArray) {
	for (var i = 0; i < idArray.length; i++) {
		triggerOnkeyupInInput(idArray[i]);
	}
}

/**
 * 發動input的onkeyup(with jQuery)
 * 
 * @param id
 */
function triggerOnkeyupInInput(id) {
	if (!checkIsNullSpace($("#" + id + "").val())) {
		$("#" + id + "").trigger("onkeyup");
	}
}

/**
 * (僅針對兆豐專案)針對共用視窗的input,如果該input是手動輸入,則會發動Ajax查詢資料_Type2
 * 
 * @param thisObj
 * @param actionUrl
 * @param requestJsonData
 * @param errMsg
 * @returns {Boolean}
 */
function queryData4WINWithAjaxType2(thisObj, jsonObj, requestJsonData, requestJsonData4PopupWindow) {
	var actionUrl = jsonObj.ACTION_URL;
	var errMsg = jsonObj.ERROR_MSG;
	var key = jsonObj.DATA_KEY_TO_SPAN;
	var winCode = jsonObj.WIN_CODE;
	var winParam = requestJsonData4PopupWindow;

	var v = thisObj.value;
	var resData = null;

	if (checkIsNullSpace(v)) {
		jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "");
		jQuery(thisObj).closest("td").find("span:last").eq(0).html("");
		jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
		return resData;
	}

	// 成功的話則依照thisObj的往右的span塞html內容
	var msg = "";

	ajaxFuncAsyncFalse(actionUrl, requestJsonData, function (res) {
		// 查詢為多筆
		if (!checkIsNullSpace(res["SYS_OPEN_POPWINDOW"])) {
			// !!!!!遇缺再補!!!!!
			if (winCode = "IMWIN00009") {
				var param = "";

				if (!checkIsNullSpace(winParam.EXG_CDE)) {
					param = "?" + "ExgBasDatInputParams=" + winParam.EXG_CDE;
				}

				openPopupWindowMax(contextPath + "/WIN/imwin00009View.action" + param, "winform1", "yes", "no");
			}
		}

		// 查詢為單筆
		// console.log(res);//IE8不能用
		if (!checkIsNullSpace(res[key])) {
			msg = res[key];
			jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "");
			jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("true");
		} else {
			msg = errMsg;
			jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "required");
			jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
		}

		resData = res;
	}, function () {
		msg = "查詢資料發生錯誤";
		jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "required");
		jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
	}, function () {
		msg = "查詢資料發生錯誤";
		jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "required");
		jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
	});

	jQuery(thisObj).closest("td").find("span:last").eq(0).html(msg);
	return resData;
}

/**
 * (僅針對兆豐專案)針對共用視窗的input,如果該input是手動輸入,則會發動Ajax查詢資料
 * 注意!如果thisObj為空字串或null,會回傳null
 * 
 * @param thisObj
 * @param actionUrl
 * @param requestJsonData
 * @param errMsg
 * @returns {Boolean}
 */
function queryData4WINWithAjax(thisObj, actionUrl, requestJsonData, errMsg, key) {
	var v = thisObj.value;
	var resData = null;

	if (checkIsNullSpace(v)) {
		jQuery(thisObj).closest("td").find("span").eq(0).attr("class", "");
		jQuery(thisObj).closest("td").find("span:last").eq(0).html("");
		jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
		return resData;
	}

	// 成功的話則依照thisObj的往右的span塞html內容
	var msg = "";

	ajaxFuncAsyncFalse(actionUrl, requestJsonData, function (res) {
		// 查詢為多筆
		if (!checkIsNullSpace(res["SYS_OPEN_POPWINDOW"])) {
			// openPopupWindowMax(contextPath + "/WIN/imwin00009View.action",
			// "winform1", "yes", "no");
		}

		// 查詢為單筆
		// console.log(res);//IE8不能用
		if (!checkIsNullSpace(res[key])) {
			msg = res[key];
			jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "");
			jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("true");
		} else {
			msg = errMsg;
			jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "required");
			jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
		}

		resData = res;
	}, function () {
		msg = "查詢資料發生錯誤";
		jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "required");
		jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
	}, function () {
		msg = "查詢資料發生錯誤";
		jQuery(thisObj).closest("td").find("span:last").eq(0).attr("class", "required");
		jQuery(thisObj).closest("td").find("input[type=hidden]").eq(0).val("false");
	});

	jQuery(thisObj).closest("td").find("span:last").eq(0).html(msg);
	return resData;
}

/**
 * AjaxFunction(異步)
 * 
 * @param url
 * @param requestJsonData
 *            (注意!送出後會改成一字串,該字串由requestStr變數接起,再由工具轉換)
 * @param successFunc
 * @param errorFunc
 * @param failFunc
 */
function ajaxFuncAsyncFalse(url, requestJsonData, successFunc, errorFunc, failFunc) {
	var s = processJSONObjToStr(requestJsonData);

	$.ajax({
		method: "POST",
		url: url,
		async: false,
		data: {
			"requestStr": s
		},
		success: function (res) {
			successFunc(res);
		},
		error: function () {
			errorFunc();
		}
	}).fail(function () {
		failFunc();
	});
}

/**
 * 
 * @param str1
 * @param str2
 * @returns
 */
function mix2Str(str1, str2, linkSymbol) {
	var s = "";

	if (!checkIsNullSpace(str1)) {
		s += str1;

		if (!checkIsNullSpace(str2)) {
			s += linkSymbol + str2;
		}
	} else {
		if (!checkIsNullSpace(str2)) {
			s += str2;
		}
	}

	return s;
}

/**
 * 確認是否為"yyyy/MM/dd"格式的日期
 */
function checkDateFormat(dateStr) {
	return checkRegular(dateStr,
		"^[0-9]{4}/(((0[13578]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02/(0[1-9]|[1-2][0-9]))|((0[469]|11)/(0[1-9]|[1-2][0-9]|30)))$");
}

/**
 * 清除頁面所有的input、將select設定為第一個option
 */
function setInputTextAndSelClean4Form(formIdNm) {
	$("#" + formIdNm + " :input[type=text]").val("");
	settingSelFisrtVal($("#" + formIdNm + " select")[0]);
}

/**
 * 根據輸入的form的idNm,將相關操作關閉(attr-disabled:true)
 * 
 * @returns {Boolean}
 */
function setAllOperateDisabled4Form(formIdNm) {
	$("#" + formIdNm + " :input[type=text]").attr("disabled", true);
	$("#" + formIdNm + " :input[type=button]").attr("disabled", true);
	$("#" + formIdNm + " :input[type=image]").attr("disabled", true);
	$("#" + formIdNm + " :input[type=radio]").attr("disabled", true);
	$("#" + formIdNm + " select").attr("disabled", true);
	$("#" + formIdNm + " img").attr("onclick", "");
}

/**
 * 將jQuery的DatePicker隱藏
 */
function setjQeuryDatePickerHide(idArray) {
	for (var i = 0; i < idArray.length; i++) {
		$("#" + idArray[i] + "").closest("td").find("img").attr("style", "display:none");
	}
}

/**
 * 
 * @returns
 */
function transFloatVal(inputObj) {
	var inputVal = inputObj.value;

	if (checkIsNullSpace(inputVal)) {
		return;
	}

	inputObj.value = removNotDotAndNum(inputVal);
}

/**
 * 移除非小數點數的部份,注意!如果為自然數會被保留
 * 
 * @param s
 * @returns
 */
function removNotDotAndNum(str) {
	var s = str;

	// 移除跟小數點數無關的內容
	for (var i = str.length - 1; i >= 0; i--) {
		if (checkRegular(str.charAt(i), "[^0-9.]")) {
			var afterStr = s.substring(i + 1);
			var beforeStr = s.substring(0, i);
			s = beforeStr + afterStr;
		}
	}

	// 擷取符合小數點數的部份
	var s2 = "";

	for (var i = 0; i < s.length; i++) {
		var s3 = s2 + s.charAt(i);

		if (checkRegular(s3, "(^[0-9]{1,}.[0-9]{1,}$|^[0-9]{1,}$|^[0-9]{1,}.$)")) {
			s2 = s3;
		} else {
			break
		}
	}

	// 「1.」形式的資料需要保留待之後可以輸入
	return s2;
}

/**
 * 更改input Object的值,如果不為純數字,則會被移除內容
 * 
 * @param inputObj
 */
function transIntVal(inputObj) {
	var inputVal = inputObj.value;

	if (checkIsNullSpace(inputVal)) {
		return;
	}

	if (checkRegular(inputVal, "[^0-9]")) {
		inputObj.value = removeNotNumStr(inputVal);
	}

	if (checkIsNullSpace(inputObj.value)) {
		return;
	}

	inputObj.value = parseInt(inputVal);
}

/**
 * 更改input Object的值,如果不為純數字或純英文,則會被移除內容
 * 
 * @param inputObj
 */
function transAlphetIntVal(inputObj) {
	var inputVal = inputObj.value;

	if (checkIsNullSpace(inputVal)) {
		return;
	}

	if (checkRegular(inputVal, "[^0-9A-Za-z]")) {
		inputObj.value = removeNotAplphetOrNumStr(inputVal);
	}
}

/**
 * 將日期隔格式為「yyyyMMdd」轉成「yyyy/MM/dd」
 * 
 * @param str
 */
function transDateFormat01(str) {
	var year = str.substring(0, 4);
	var month = str.substring(4, 6);
	var day = str.substring(6, 8);
	return year + "/" + month + "/" + day + "";
}

/**
 * 將日期隔格式為「yyyy/MM/dd」轉成「yyyyMMdd」
 * 
 * @param dateStr
 * @returns {String}
 */
function transDateFormat02(str) {
	return dateObjToFormat(transDateStrToDateObj(str));
}

/**
 * (僅針對兆豐專案) 依照定義的json格式和內容,產生相對應的button碼(注意!每一個物件會有td在前後,例:＜ｔｄ＞＜ａ＞...＜／ａ＞＜／ｔｄ＞)
 * json格式:{DATA:[{TITLE:...,FUNC:...,CLASSNM:...,SRC:...},{..... ]}
 * (注意!如果FUNC在點擊input type=img後有reload畫面問題,請將FUNC加入'return
 * false;',例:"alert('hello');return false;")
 */
function genTableButtonHtml(jsonObj) {
	var a = jsonObj.DATA;
	var h = "";
	h = h + "<tr>";
	var tdCount = a.length;
	var par = (100 / tdCount).toFixed();
	for (var i = 0; i < a.length; i++) {
		var subA = a[i];

		var title = subA.TITLE;
		var func = subA.FUNC;
		var id = subA.ID;
		var src = subA.SRC;

		h = h + "<td width='" + par + "%'>";
		h = h + "<input name='" + "abc" + parseInt(Math.random() * 100) + "' value='" + "xyz" + parseInt(Math.random() * 100) + "' id='"
			+ id + "' type='image' src='" + src + "' title='" + title + "' onclick=\"" + func + "\">";
		h = h + "</td>";
	}
	h = h + "<tr>";
	var html = "";
	html += "<table class=\"datatable\" border='0' cellpadding='0' cellspacing='0'>";
	html += h;
	html += "</table>";
	return html;
}

/**
 * 刪除輸入符號的在輸入字串最後一個位置後所有內容,例:delContentByLastSymbol("hello,world",",");=>hello
 */
function delContentByLastSymbol(val, symbol) {
	var posiNum = val.lastIndexOf(symbol);
	var res = val.substring(0, posiNum);
	return res;
}

/**
 * 小數點數字處理,加上千分位符號
 * 
 * @param obj
 */
function addCommasInFloat(obj) {
	// delNotFloat(obj);//不能使用此function,因有千分號,之後看能不能統一function

	// 移除內容由陣列後面移除
	var str = obj.value;

	for (var i = str.length - 1; i >= 0; i--) {
		if (checkRegular(str.charAt(i), "[^0-9,.]")) {
			var afterStr = str.substring(i + 1);
			var beforeStr = str.substring(0, i);
			obj.value = beforeStr + afterStr;
		}
	}

	var a = obj.value.split(".");
	var intStr = a[0];

	if (!checkIsNullSpace(a[1])) {
		// n.n
		var decimalStr = a[1];
		obj.value = addCommas(intStr) + "." + decimalStr;
	} else {
		var l = obj.value.length;
		var lastStr = obj.value.substring(l - 1, l);

		if ("." == lastStr) {
			// n.
			obj.value = addCommas(intStr) + ".";
		} else {
			// n
			obj.value = addCommas(intStr);
		}
	}
}

/**
 * 處理輸入的值,移除非數字字串,並加上千分位符號
 */
function processNumVal(obj) {
	if (checkIsNullSpace(obj.value)) {
		return;
	}

	var val = removeComma(obj.value);

	if (!checkAllNum(val)) {
		obj.value = removeNotNumStr(val);
	}

	obj.value = addCommas(obj.value);
}

/**
 * 移除非數字內容,33111aaa222=>333111222
 */
function removeNotNumStrType2(str) {
	var s = str;
	var c = "";

	for (var i = s.length - 1; i >= 0; i--) {
		if (!checkRegular(s[i], "[^0-9]")) {
			c = s[i] + c;
		}
	}

	return c;
}

/**
 * 移除非數字內容後的所有內容,3111aaa222=>3111
 */
function removeNotNumStr(str) {
	var s = str;

	// 移除內容由陣列後面移除
	for (var i = str.length - 1; i >= 0; i--) {
		if (checkRegular(str.charAt(i), "[^0-9]")) {
			// s = s.substring(0, i - 1);
			// 更改實作方式
			var afterStr = s.substring(i + 1);
			var beforeStr = s.substring(0, i);
			s = beforeStr + afterStr;
		}
	}

	return s;
}

/**
 * 移除非字母或數字內容後的所有內容,3111aaa222=>3111
 */
function removeNotAplphetOrNumStr(str) {
	var s = str;

	// 移除內容由陣列後面移除
	for (var i = str.length - 1; i >= 0; i--) {
		if (checkRegular(str.charAt(i), "[^0-9A-Za-z]")) {
			// s = s.substring(0, i - 1);
			// 更改實作方式
			var afterStr = s.substring(i + 1);
			var beforeStr = s.substring(0, i);
			s = beforeStr + afterStr;
		}
	}

	return s;
}

/**
 * 依據輸入的符號,拆解StrList,轉為arrayArray
 */
function takeApartStrListBySymbol(strList, symbol) {
	var dataArray = new Array();

	for (var i = 0; i < strList.length; i++) {
		var t = strList[i].split(symbol);
		var subDataArray = new Array();
		subDataArray.push(t[0]);
		subDataArray.push(t[1]);
		dataArray.push(subDataArray);
	}

	return dataArray;
}

/**
 * 必填欄位檢查,並執行errMsgFunc
 * 
 */
function checkInputAndReturnMsg(array) {
	var errorMsg = "";

	for (var i = 0; i < array.length; i++) {
		var a = array[i];
		errorMsg += isEmpty(a[0], a[1]);// isEmpty應該struts的tiles就匯進來了
	}

	return errorMsg;
}

/**
 * 必填欄位檢查,並執行errMsgFunc
 * 
 */
function checkInput(array) {
	var errorMsg = "";

	for (var i = 0; i < array.length; i++) {
		var a = array[i];
		errorMsg += isEmpty(a[0], a[1]);// isEmpty應該struts的tiles就匯進來了
	}

	var haveErr = false;

	if (errorMsg.length > 0) {
		haveErr = true;
	}

	return haveErr;
}

/**
 * 顯示錯誤訊息Dialog,且可自訂button(記得補說明到文件)
 * 
 * @param msg
 * @param title
 */
/*function messageErrorBoxType2(msg, title, buttonJsonObj) {
	// new Dialog Object
	// 此機制有無法操作多個dialog的問題
	var DialogObjectTemp = new $.msgbox({
		type : 'alert',
		title : title,
		content : msg
	});

	DialogObjectTemp.show();
	$("#jMsgbox_content").css("height", "100px");

	// 清空既有button
	DialogObjectTemp.mainWrap.find("input[type=button]")[0].outerHTML = "";

	// 建立新的Button
	var a = buttonJsonObj.BUTTON;
	var html = "";

	for (var i = 0; i < a.length; i++) {
		var buttonValTemp = a[i].buttonVal;
		html += "<input type='button' name='' value='" + buttonValTemp + "'>" + "<div style='display:inline;'> </div>";
	}

//	html = "<div style='text-align:center;'>" + html + "</div>";
	html = "<center style='padding: 8px;'>" + html + "</center>";
	DialogObjectTemp.mainWrap.append(html);

	// 重新註冊click event
	var buttonArray = DialogObjectTemp.mainWrap.find("input[type=button]");

	for (var i = 0; i < buttonArray.length; i++) {
		DialogObjectTemp.mainWrap.find("input[type=button]").eq(i).click(buttonJsonObj.BUTTON[i].func);
	}

	return DialogObjectTemp;
}
*/
/**
 * 將JSON物件轉為JSON字串(一般轉換方法,針對AJAX使用)
 */
function processJSONObjToStr(jsonObj) {
	var jsonStr = JSON.stringify(jsonObj);
	var resStr = jsonStr;

	// var tempStr = jsonStr;
	// var resStr = "";
	//	
	// for(var i = tempStr.length - 1; i >= 0; i--){
	// if(checkRegular(tempStr[i], "\"")){
	// resStr = "\\\"" + resStr;
	// }else{
	// resStr = tempStr[i] + resStr;
	// }
	// }

	return resStr;
}

/**
 * (不使用了,OGNL似乎可以把雙引號包住不發生錯誤)將JSON物件轉為JSON字串(針對form submit做處理)
 */
function processJSONObjToStr4FormSubmit(jsonObj) {
	var jsonStr = JSON.stringify(jsonObj);

	var tempStr = jsonStr;
	var resStr = "";

	for (var i = tempStr.length - 1; i >= 0; i--) {
		if (checkRegular(tempStr[i], "\"")) {
			resStr = "\\\"" + resStr;
		} else {
			resStr = tempStr[i] + resStr;
		}
	}

	return resStr;
}

/**
 * 自訂jquery.msgbox.js的按鈕(注意!物件出現才會被執行) param-jsonObj: var jsonObj = {BUTTON:{[{
 * "buttonVal" : "{YOUR_BUTTON_NAME}", "func" : function hello() {
 * {YOUR_BUTTON_FUNCTION} },{ "buttonVal" : "{YOUR_BUTTON_NAME}", "func" :
 * function hello() { {YOUR_BUTTON_FUNCTION} }, ... ]}
 * 
 * @param jsonObj
 *            承上述描述,可能null:不執行更改
 */
/*function settingErrMsgButton(jsonObj) {
	if (jsonObj != null && window.errorMsg && !checkIsNullSpace(errorMsg) && errorMsg.length > 0) {
		if ($("#	_content").find("div").html().length > 0) {
			// 清空原來的button
			$("#jMsgbox_content").parent().find("input[type=button]")[0].outerHTML = "";

			// 建立新的Button
			var a = jsonObj.BUTTON;
			var html = "";

			for (var i = 0; i < a.length; i++) {
				var buttonValTemp = a[i].buttonVal;
				html += "<input type='button' name='' value='" + buttonValTemp + "'>";

				if (i != a.length - 1) {
					html += "<div style='display:inline;'>&nbsp;&nbsp;&nbsp;</div>";
				}
			}

			// html = "<div style='text-align:center;'>" + html +
			// "</div>";//排版有問題,改用以下方式
			html = "<center style=\"padding: 8px;\">" + html + "</center>";
			$("#jMsgbox_content").parent()[0].innerHTML = $("#jMsgbox_content").parent()[0].innerHTML + html;

			// 重新註冊click event
			var buttonArray = $("#jMsgbox_content").parent().find("input[type=button]");

			for (var i = 0; i < buttonArray.length; i++) {
				$("#jMsgbox_content").parent().find("input[type=button]").eq(i).click(jsonObj.BUTTON[i].func);
			}
		}
	}
}
*/
/**
 * 關閉broswer子視窗(須由子視窗自己呼叫)
 */
function closeSubWindow() {
	window.close();
}

/**
 * 將母視窗執行reload(注意!此function會清掉母視窗form的attribute-action)
 */
function reloadParentPage(targetUrl) {
	window.opener.document.forms[2].setAttribute("action", targetUrl);
	window.opener.document.location.reload();
}

/**
 * 將母視窗執行reload(注意!此function會清掉母視窗form的attribute-action)
 */
function reQueryParentPage(targetUrl) {
	window.opener.document.forms[2].setAttribute("action", targetUrl);
	submitParentPage();
}

/**
 * reload page of parent 重新將母視窗的form再送一次
 */
function submitParentPage() {
	var formLeng = window.opener.document.forms.length;

	if (!checkIsNullSpace(formLeng) && formLeng > 0) {
		window.opener.document.forms[formLeng - 1].submit();
	} else {
		alert("System Have Error!");
	}
}

/**
 * 僅針對兆豐專案使用
 * 
 * @param jspUrl
 * @param callback
 */
function openSubWindow() {
	// contextPath + "/jsps/relay.jsp"為僅針對兆豐專案使用
	// openSubWindowWithParam(contextPath + "/jsps/relay.jsp", null);
	openSubWindowWithParamType2(contextPath + "/RELAY/relay.action", null);
}

/**
 * 開啟broswer子視窗(會自動計算螢幕解析度,並放大至螢幕全畫面)
 * 
 * @param jspUrl
 * @param callback
 *            function(subWindow){...},針對
 */
function openSubWindowWithParamType2(jspUrl, callback) {
	var bNm = getBroswerName();

	var dialogW = screen.width;
	var dialogH = screen.height;
	var subWindow = null;

	var s = "," + "width" + "=" + dialogW + "," + "height" + "=" + dialogH;
	var nm = "sn" + new Number(parseInt(Math.random() * 100)) + "";// 可能還有雷,看要怎麼取亂數
	subWindow = window.open(jspUrl, nm, "toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no" + s);

	if (callback != null) {
		callback(subWindow);
	}
}

/**
 * 開啟broswer子視窗
 * 
 * @param jspUrl
 * @param callback
 *            function(subWindow){...},針對
 */
function openSubWindowWithParam(jspUrl, callback) {
	var bNm = getBroswerName();

	var dialogW = "1016px";
	var dialogH = "766px";
	var subWindow = null;

	var s = "," + "width" + "=" + dialogW + "," + "height" + "=" + dialogH;
	var nm = "sn" + new Number(parseInt(Math.random() * 100)) + "";// 可能還有雷,記得改用時間設定此值
	subWindow = window.open(jspUrl, nm, "toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no" + s);

	if (callback != null) {
		callback(subWindow);
	}
}

/**
 * 檢查inputObj的value是否為空資料,如果是則會inputObj旁邊加上警告提示(含預設ClassName:_ERR_MSG,HTML:
 * 請輸入必填資料)
 * 
 * @param inputObjArray
 */
function checkMustInput(inputObjArray, isSetToOriginalVal) {
	// 清除class=_ERR_MSG(jQuery用法,因IE8不支援getElementsByClass,故這裡沒使用javascript)
	$("._ERR_MSG").each(function (index) {
		$(this).html("");
	});

	return checkMustInputGeneral(inputObjArray, "_ERR_MSG", "#FF0000", " 請輸入必填資料", isSetToOriginalVal);
}

/**
 * 檢查inputObj的value是否為空資料,如果是則會inputObj旁邊加上警告提示
 * 
 * @param inputObjArray
 * @returns {Boolean}
 */
function checkMustInputGeneral(inputObjArray, msgClassNm, colorVal, msg, isSetToOriginalVal) {
	var fontObj = document.createElement("font");
	fontObj.className = msgClassNm;
	fontObj.innerHTML = msg;
	fontObj.color = colorVal;

	var bArray = new Array();

	for (var i = 0; i < inputObjArray.length; i++) {
		var inputObj = inputObjArray[i];

		// 最小需求,可以切出(未進行)
		if (checkIsNullSpace(inputObj.value)) {
			var val = inputObj.value;
			inputObj.removeAttribute("value");
			inputObj.outerHTML = inputObj.outerHTML + fontObj.outerHTML;

			if (isSetToOriginalVal == true) {
				inputObj.value = val;
			}

			if (isSetToOriginalVal == false) {
				inputObj.value = "";
			}

			bArray.push(false);
		} else {
			bArray.push(true);
		}
	}

	var b = false;

	if (checkBArrayContainInputBoolean(bArray, false)) {
		b = false;
	} else {
		b = true;
	}

	return b;
}

/**
 * 
 */
function getBroswerName() {
	var broswerNm = "";

	if (navigator.userAgent.match("Firefox")) {
		broswerNm = "Firefox";
	}

	if (navigator.userAgent.match("MSIE")) {
		broswerNm = "MSIE";
	}

	if (navigator.userAgent.match("Opera")) {
		broswerNm = "Opera";
	}

	if (navigator.userAgent.match("Safari")) {// chrome也適用
		broswerNm = "Safari";
	}

	if (navigator.userAgent.match("Chrome")) {
		broswerNm = "Chrome";
	}

	return broswerNm;
}

/**
 * 身分證檢核需要變數
 */
var ALP_STR = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
var NUM_STR = "0123456789";
var SEX_STR = "12";
var MAX_COUNT = 999;

/**
 * 
 * @param sPID
 * @returns {Boolean}
 */
function checkNationalId(sPID) {
	// var sMsg = "正確";
	var b = false;

	if (checkIsNullSpace(sPID)) {
		console.log("Your Id is null or space");
		return b;
	}

	if (sPID.length != 10) {
		console.log("Your Id length is not 10");
		return b;
	}

	sPID = trim(sPID.toUpperCase());

	if (!chkPID_CHAR(sPID)) {
		return b;
	}

	var iChkNum = getPID_SUM(sPID);

	if (iChkNum % 10 != 0) {
		// 進入此即為錯誤的身分證號
		var iLastNum = sPID.substr(9, 1) * 1;

		var bArray = new Array();

		for (var i = 0; i < 10; i++) {
			var xRightAlpNum = iChkNum - iLastNum + i;

			if ((xRightAlpNum % 10) == 0) {
				// console.log("String of Last must is : " + i);
				// sMsg = "最後一個數應為：" + i;
				// b = false;
				bArray.push(false);
				break;
			} else {
				// bArray.push(true);
			}
		}

		if (checkBArrayContainInputBoolean(bArray, false)) {
			b = false;
		} else {
			// b = true;
		}
	} else {
		b = true;
	}

	// alert(sMsg);
	return b;
}

/**
 * 根據輸入的身分證號,確認性別(0:男,1:女)
 * 
 * @param id
 * @returns
 */
function checkGender(idVal) {
	var genderCode = idVal.substring(1, 2);

	if (genderCode == "1") {
		return 0;
	}

	if (genderCode == "2") {
		return 1;
	}
}

/**
 * 檢核法人統一編號
 * 
 * @returns
 */
function checkArtificialPersonId(id) {
	// 檢查是否為八碼
	if (id.length != 8) {
		return false;
	}

	// 檢查是否為純數字八碼
	if (!checkRegular(id, "[0-9]{8}")) {
		return false;
	}

	var sum = 0;
	var cx = [1, 2, 1, 2, 1, 2, 4, 1];// 每一碼的倍數
	var cs = id.split("");// 將統一編號拆解

	for (var i = 0; i < id.length; i++) {
		var beSum = new Number(id[i] * cx[i]);

		if (beSum <= 9) {
			sum += new Number(beSum);
		} else {
			var s = beSum + "";// 強制轉字串
			var n1 = s.substring(0, 1) * 1;
			var n2 = s.substring(1, 2) * 1;
			sum += new Number(n1 + n2);
		}
	}

	var isCurr = false;

	if (sum % 10 == 0) {
		isCurr = true;
	} else if (cs[6] == 7 && (sum + 1) % 10 == 0) {
		isCurr = true;
	}

	return isCurr;
}

/**
 * 
 * @param inputObj
 * @param radioGenderNm
 * @returns
 */
function settingRadioGender(inputObj, radioGenderNm) {
	var id = inputObj.value;// 取得input的val(身份證字號)

	if (!idCheck(id)) {
		return;
	}

	settingGender(id, radioGenderNm, "01", "02");// 01、02依照各專案需求更改,如M、F之類的
}

/**
 * 根據身分證號,設定男女的radio
 * 
 * @param id
 * @param inputNm
 * @param mValInRadio
 *            在radio裡面定義男的值
 * @param mValInRadio
 *            在radio裡面定義女的值
 * @returns
 */
function settingGender(id, inputNm, mValInRadio, fValInRadio) {
	var genderObjArray = $("input[name='" + inputNm + "']");

	if (genderObjArray.size() != 2) {
		return;
	}

	var genderCode = checkGender(id);

	if (genderCode == 0) {
		$("input[name='" + inputNm + "'][value='" + mValInRadio + "']").prop("checked", true);
	}

	if (genderCode == 1) {
		$("input[name='" + inputNm + "'][value='" + fValInRadio + "']").prop("checked", true);
	}
}

/**
 * 
 * @returns
 */
function createDaySel(monthNum) {
	var array = new Array();

	for (var i = 1; i <= 31; i++) {
		var v = null;

		if (i < 10) {
			v = "0" + i + "";
		} else {
			v = i + "";
		}

		var subA = [v + "", v + ""];
		array.push(subA);
	}

	return createSelect(array);
}

/**
 * 
 * @returns
 */
function createMonthSel() {
	var array = new Array();

	for (var i = 1; i <= 12; i++) {
		var v = null;

		if (i < 10) {
			v = "0" + i + "";
		} else {
			v = i + "";
		}

		var subA = [v + "", v + ""];
		array.push(subA);
	}

	var obj = createSelect(array);

	// 不知道為什麼事件不會被觸發
	// obj.onchange = function(){
	// alert();
	// };
	//	
	// console.log(obj.onchange);

	return createSelect(array);
}

/**
 * 
 * @returns
 */
function createYearSel(isRoc) {
	// 產生原始發照日期的頁面
	var dObj = new Date();
	var yyy = new Number(1900 + dObj.getYear());
	var mm = new Number(dObj.getMonth() + 1) + "";
	var dd = new Number(dObj.getDate()) + "";

	var array = new Array();

	for (var i = yyy; i >= yyy - 100; i--) {
		if (isRoc) {
			array.push([new Number(i - 1911) + "", i + ""]);
		} else {
			array.push([i + "", i + ""]);
		}
	}

	return createSelect(array);
}

/**
 * 
 * @returns
 */
function appendYearSelHtml(id, isRoc) {
	$("#" + id + "").append(createYearSel(isRoc));
}

/**
 * 
 * @returns
 */
function appendMonthSelHtml(id) {
	$("#" + id + "").append(createMonthSel());
}

/**
 * 
 * @returns
 */
function appendDaySelHtml(id) {
	$("#" + id + "").append(createDaySel());
}

/**
 * 
 * @returns
 */
function genDaysWithYearMonth(selectObj, year, month) {
	cleanSelectOption(selectObj);

	var n = daysInMonth(year, month);
	var a = new Array();

	for (var i = 1; i <= n; i++) {
		var s = "";

		if (i < 10) {
			s = "0" + i + "";
		} else {
			s = i + "";
		}

		a.push([s, s]);
	}

	appendSelectNewOptionType2(selectObj, a);
}

function daysInMonth(year, month) {
	return new Date(year, month, 0).getDate();
}

/**
 * 
 * @param array
 * @returns
 */
function createSelect(array) {
	var selectObj = document.createElement("select");

	for (var i = 0, j = array.length; i < j; i++) {
		selectObj.options.add(new Option(array[i][0], array[i][1]));
	}

	return selectObj;
}

/**
 * =>common.js
 * 
 * @param sPID
 * @returns {Boolean}
 */
function chkPID_CHAR(sPID) {
	var sMsg = "";
	// sPID = trim(sPID.toUpperCase());
	var iPIDLen = String(sPID).length;

	var sChk = ALP_STR + NUM_STR;

	for (var i = 0; i < iPIDLen; i++) {
		if (sChk.indexOf(sPID.substr(i, 1)) < 0) {
			sMsg = "這個身分證字號含有不正確的字元！";
			break;
		}
	}

	if (sMsg.length == 0) {
		if (ALP_STR.indexOf(sPID.substr(0, 1)) < 0) {
			sMsg = "身分證字號第 1 碼應為英文字母(A~Z)。";
		} else if ((sPID.substr(1, 1) != "1") && (sPID.substr(1, 1) != "2")) {
			sMsg = "身分證字號第 2 碼應為數字(1~2)。";
		} else {
			for (var i = 2; i < iPIDLen; i++) {
				if (NUM_STR.indexOf(sPID.substr(i, 1)) < 0) {
					sMsg = "第 " + (i + 1) + " 碼應為數字(0~9)。";
					break;
				}
			}
		}
	}

	if (sMsg.length != 0) {
		// alert(sMsg);
		return false;
	} else {
		return true;
	}
}

/**
 * 
 * @param sPID
 * @returns {Number}
 */
function getPID_SUM(sPID) {
	var iChkNum = 0;

	// 第 1 碼
	iChkNum = ALP_STR.indexOf(sPID.substr(0, 1)) + 10;
	iChkNum = Math.floor(iChkNum / 10) + (iChkNum % 10 * 9);

	// 第 2 - 9 碼
	for (var i = 1; i < sPID.length - 1; i++) {
		iChkNum += sPID.substr(i, 1) * (9 - i);
	}

	// 第 10 碼
	iChkNum += sPID.substr(9, 1) * 1;

	return iChkNum;
}

/**
 * 判斷輸入的str array包含str
 * 
 * @param array
 * @param str
 */
function checkArrayContainStr(array, str) {
	var b = false;

	for (var i = 0; i < array.length; i++) {
		if (array[i] == str) {
			b = true;
			break;
		}
	}

	return b;
}

/**
 * 檢查array中是否有重複資料(僅判斷有無重複,並不會整理出重覆哪些資料、哪個位置重複)
 * 
 * @param str
 * @param array
 */
function checkDuplicateContent(array) {
	var tempArray = new Array();

	for (var i = 0; i < array.length; i++) {
		var valInArray = array[i];
		var duplicateCount = 0;

		for (var j = 0; j < array.length; j++) {
			if (valInArray == array[j]) {
				duplicateCount++;
			}
		}

		var subTempArray = new Array(valInArray, duplicateCount);
		tempArray.push(subTempArray);
	}

	var bArray = new Array();

	for (var i = 0; i < tempArray.length; i++) {
		var c = new Number(tempArray[i][1]);

		// 輸入的array僅能一筆一種資料
		if (c == 1) {
			bArray.push(true);
		}

		if (c > 1) {
			bArray.push(false);
		}
	}

	return checkBArrayContainInputBoolean(bArray, false);
}

/**
 * 檢查電話/手機號碼,是否為數字和指定的長度
 * 
 * telOrPhone: length:
 */
function checkTelOrPhone(telOrPhone, length) {
	var b = false;

	if (checkRegular(telOrPhone, "[0-9]{" + length + "}")) {
		b = true;
	}

	return b;
}

/**
 * 限定輸入的電話號碼格式：xxxx-xxxxxx
 */
function enterCellPhoneAnMidline(obj) {
	if (null != obj && undefined != obj) {
		var tempStr = $(obj).val();
		$(obj).val(tempStr.replace(/[^\d-]/g, ''));
		tempStr = $(obj).val();
		if (tempStr.length > 4) {

			var strOfNo5 = tempStr.substring(4, 5);
			if ("-" != strOfNo5) {
				$(obj).val(tempStr.substring(0, 4));
			} else {
				var strOfNo6After = tempStr.substring(5);
				strOfNo6After = strOfNo6After.replace(/[^\d]/g, '');
				if (strOfNo6After.length > 6) {
					strOfNo6After = strOfNo6After.substring(0, 6);
				}
				$(obj).val(tempStr.substring(0, 5) + strOfNo6After);
			}

		}
	}
}

/**
 * 檢核輸入的電話號碼格式：xxxx-xxxxxx
 */
function checkCellPhoneAnMidline(obj) {
	if (null != obj && undefined != obj) {
		var tempStr = $(obj).val();
		if (RegExp(/^[0-9]{4}-[0-9]{6}$/).test(tempStr)) {
			return true;
		}
	}
	return false;
}

/**
 * 輸入數字英文並轉大寫
 * 
 * @param tempStr
 */
function toDigitalEnglishUpper(obj) {
	var val = obj.value.toUpperCase();
	obj.value = val.replace(/[^-_A-Z0-9]/g, '');
}

/**
 * 
 */
function fillToAStrByBStr(aStr, bStr, length) {
	var resS = "";

	if (aStr.length < length) {
		var l = length - aStr.length;

		var s = "";

		for (var i = 1; i <= l; i++) {
			s += bStr;
		}

		resS = s + aStr;
	} else {
		resS = aStr;
	}

	return resS;
}

/**
 * 設定select的第一個選項
 */
function settingSelFisrtVal(selObj) {
	var firstOptionObj = selObj.getElementsByTagName("option")[0];
	var v = firstOptionObj.value;
	selObj.value = v;
}

/**
 * 找出現在select所選擇的option中所顯示的html內容
 * 
 * @param selObj
 * @returns {String}
 */
function findSelHtml(selObj) {
	return findSelHtmlByVal(selObj.value, selObj);
}

/**
 * 
 * @param h
 * @param selObj
 * @returns {String}
 */
function findSelValByHtml(h, selObj) {
	var optionArray = selObj.getElementsByTagName("option");
	var val = "";

	for (var i = 0; i < optionArray.length; i++) {
		if (optionArray[i].innerHTML == h) {
			val = optionArray[i].value;
			break;
		}
	}

	return val;
}

/**
 * 
 * @param objArray
 * @returns {Number}
 */
function subAdjustStepBanner(objArray) {
	var v = 0;

	for (var i = 0; i < objArray.length; i++) {
		v += objArray[i].offsetWidth;
	}

	return v;
}

/**
 * 
 * @param selectObj
 * @param defOpText
 * @param defOptVal
 */
function cleanSelectOptionAndSetDefVal(selectObj, defOpText, defOptVal) {
	cleanSelectOption(selectObj);

	var a = new Array();
	var sa = new Array(defOptVal, defOpText);
	a.push(sa);

	appendSelectNewOptionType2(selectObj, a);
}

/**
 * 
 * @param selectObj
 */
function cleanSelectOption(selectObj) {
	// alert(selectObj.innerHTML);

	var optionArray = selectObj.getElementsByTagName("option");
	// alert("size: " + optionArray.length);
	// var optionArray = selectObj.childNodes();

	for (var i = optionArray.length - 1; i > -1; i--) {
		// alert("cleanSelectOption : " + i + " , " + optionArray[i].innerHTML);
		selectObj.removeChild(optionArray[i]);
	}
}

/**
 * 
 * @param dataArray
 * @returns {Array}
 */
function trans1DimensionTo2Dimension(dataArray) {
	var a = new Array();

	for (var i = 0; i < dataArray.length; i++) {
		var sa = new Array(dataArray[i], dataArray[i]);
		a.push(sa);
	}

	return a;
}

/**
 * 
 * @param selectObj
 * @param dataArray
 */
function appendSelectNewOptionType2(selectObj, dataArray) {
	for (var i = 0; i < dataArray.length; i++) {
		if (
			// dataArray[i][0] != "" & //因為可能不需要value,故不需要檢查
			dataArray[i][1] != "") {
			var opt = document.createElement("OPTION");

			opt.value = dataArray[i][0];
			opt.text = dataArray[i][1];

			selectObj.options.add(opt);
		}
	}
}

/**
 * 
 * @param selectObj
 * @param dataArray
 */
function appendSelectNewOption(selectObj, dataArray) {
	for (var i = 0; i < dataArray.length; i++) {
		if (dataArray[i] != "") {
			var opt = document.createElement("OPTION");
			opt.text = dataArray[i];
			opt.value = dataArray[i];
			selectObj.options.add(opt);
		}
	}
}

/**
 * 
 * @param val
 * @param selectObj
 * @returns {String}
 */
function findSelHtmlByVal(val, selectObj) {
	var optioinArray = selectObj.getElementsByTagName("option");

	var str = "";

	for (var i = 0; i < optioinArray.length; i++) {
		if (optioinArray[i].value == val) {
			str = optioinArray[i].innerHTML;
			break;
		}
	}

	return str;
}

/**
 * 檢查是否為全形字
 */
function isHolomorphic(val) {
	var text = val;
	var big5Table = "%uFF01%u201D%uFF03%uFF04%uFF05%uFF06%u2019%uFF08%uFF09%uFF0A%uFF0B%uFF0C%uFF0D%uFF0E%uFF0F%uFF10%uFF11%uFF12%uFF13%uFF14%uFF15%uFF16%uFF17%uFF18%uFF19%uFF1A%uFF1B%uFF1C%uFF1D%uFF1E%uFF1F%uFF20%uFF21%uFF22%uFF23%uFF24%uFF25%uFF26%uFF27%uFF28%uFF29%uFF2A%uFF2B%uFF2C%uFF2D%uFF2E%uFF2F%uFF30%uFF31%uFF32%uFF33%uFF34%uFF35%uFF36%uFF37%uFF38%uFF39%uFF3A%uFF3B%uFF3C%uFF3D%uFF3E%uFF3F%u2018%uFF41%uFF42%uFF43%uFF44%uFF45%uFF46%uFF47%uFF48%uFF49%uFF4A%uFF4B%uFF4C%uFF4D%uFF4E%uFF4F%uFF50%uFF51%uFF52%uFF53%uFF54%uFF55%uFF56%uFF57%uFF58%uFF59%uFF5A%uFF5B%uFF5C%uFF5D%uFF5E";

	var b = false;

	for (var i = 0; i < text.length; i++) {
		var val = escape(text.charAt(i));
		var j = big5Table.indexOf(val);

		if ((j > -1) && (val.length == 6)) {
			b = true;
			break;
		}
	}

	return b;
}

/**
 * 全形轉半形(將輸入的值轉換結果)
 */
function ascType2(val) {
	var text = val;
	var asciiTable = "!\"#$%&\’()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
	var big5Table = "%uFF01%u201D%uFF03%uFF04%uFF05%uFF06%u2019%uFF08%uFF09%uFF0A%uFF0B%uFF0C%uFF0D%uFF0E%uFF0F%uFF10%uFF11%uFF12%uFF13%uFF14%uFF15%uFF16%uFF17%uFF18%uFF19%uFF1A%uFF1B%uFF1C%uFF1D%uFF1E%uFF1F%uFF20%uFF21%uFF22%uFF23%uFF24%uFF25%uFF26%uFF27%uFF28%uFF29%uFF2A%uFF2B%uFF2C%uFF2D%uFF2E%uFF2F%uFF30%uFF31%uFF32%uFF33%uFF34%uFF35%uFF36%uFF37%uFF38%uFF39%uFF3A%uFF3B%uFF3C%uFF3D%uFF3E%uFF3F%u2018%uFF41%uFF42%uFF43%uFF44%uFF45%uFF46%uFF47%uFF48%uFF49%uFF4A%uFF4B%uFF4C%uFF4D%uFF4E%uFF4F%uFF50%uFF51%uFF52%uFF53%uFF54%uFF55%uFF56%uFF57%uFF58%uFF59%uFF5A%uFF5B%uFF5C%uFF5D%uFF5E";
	var result = "";

	for (var i = 0; i < text.length; i++) {
		var val = escape(text.charAt(i));
		var j = big5Table.indexOf(val);
		result += (((j > -1) && (val.length == 6)) ? asciiTable.charAt(j / 6) : text.charAt(i));
	}

	return result;
}

/**
 * 全形轉半形(轉換輸入的物件結果)
 */
function asc(obj) {
	var text = obj.value;
	var asciiTable = "!\"#$%&\’()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
	var big5Table = "%uFF01%u201D%uFF03%uFF04%uFF05%uFF06%u2019%uFF08%uFF09%uFF0A%uFF0B%uFF0C%uFF0D%uFF0E%uFF0F%uFF10%uFF11%uFF12%uFF13%uFF14%uFF15%uFF16%uFF17%uFF18%uFF19%uFF1A%uFF1B%uFF1C%uFF1D%uFF1E%uFF1F%uFF20%uFF21%uFF22%uFF23%uFF24%uFF25%uFF26%uFF27%uFF28%uFF29%uFF2A%uFF2B%uFF2C%uFF2D%uFF2E%uFF2F%uFF30%uFF31%uFF32%uFF33%uFF34%uFF35%uFF36%uFF37%uFF38%uFF39%uFF3A%uFF3B%uFF3C%uFF3D%uFF3E%uFF3F%u2018%uFF41%uFF42%uFF43%uFF44%uFF45%uFF46%uFF47%uFF48%uFF49%uFF4A%uFF4B%uFF4C%uFF4D%uFF4E%uFF4F%uFF50%uFF51%uFF52%uFF53%uFF54%uFF55%uFF56%uFF57%uFF58%uFF59%uFF5A%uFF5B%uFF5C%uFF5D%uFF5E";
	var result = "";

	for (var i = 0; i < text.length; i++) {
		var val = escape(text.charAt(i));
		var j = big5Table.indexOf(val);
		result += (((j > -1) && (val.length == 6)) ? asciiTable.charAt(j / 6) : text.charAt(i));
	}

	obj.value = result;
}

/**
 * 
 * @returns {String}
 */
function getCaseUrl() {
	var url = location.href;
	var temp = url.replace("http://", "");
	var uArray = temp.split("/");
	return uArray[0] + "/" + uArray[1];
}

/**
 * yyyy/MM/dd檢查
 */
function isNormalDate(inputDate) {
	var b = false;

	// 此步驟會剃除不必要的雜訊
	var d = inputDate.split("/");
	var yy = new Number(d[0]);
	var tMm = new Number(d[1]);
	var tDd = new Number(d[2]);

	var mm = null;
	var dd = null;

	// 判斷是否小於10,並轉字串
	yy = yy + "";

	if (tMm < 10) {
		mm = "0" + tMm + "";
	} else {
		mm = tMm + "";
	}

	if (tDd < 10) {
		dd = "0" + tDd + "";
	} else {
		dd = tDd + "";
	}

	var bArray = new Array();

	if (checkRegular(yy, "(19|20)?[0-9]{2}")) {
		bArray.push(true);
	} else {
		bArray.push(false);
	}

	if (checkRegular(mm, "([0][1-9]|1[012])")) {
		bArray.push(true);
	} else {
		bArray.push(false);
	}

	if (checkRegular(dd, "([0][1-9]|[12][0-9]|3[01])")) {
		bArray.push(true);
	} else {
		bArray.push(false);
	}

	if (!checkBArrayContainInputBoolean(bArray, false)) {
		b = true;
	}

	// 用解析Date解析方式不work,用不同瀏覽器各會有不同自動加日期的情況
	// try {
	// var t = Date.parse(inputDate);
	//
	// if (!isNaN(t)) {
	// b = true;
	// } else {
	// console.log("produce date have error");
	// }

	// var d = new Date(inputDate + " 23:59:59");
	//
	// if (d != "Invalid Date") {
	// b = true;
	// }

	// alert(d);
	// } catch (err) {
	// b = false;
	// console.log("produce date have Exception");
	// }

	return b;
}

/**
 * yyyy/MM/dd=>new Date
 * 
 * @param timeStr
 * @returns {Date}
 */
function transDateStrToDateObj(timeStr) {
	var temp = timeStr.split("/");
	var d = new Date(temp[0], new Number(temp[1]) - 1, temp[2]);
	return d;
}

/**
 * 確認A時間OOB時間(輸入的時間格式:yyyy/MM/dd、OO:等於、大於或小於) aTime bTime judge:EQUAL、LESS、MORE
 */
function checkATimeLessBTime(aTime, bTime, judgeType) {
	var aDateObj = transDateStrToDateObj(aTime);
	var bDateObj = transDateStrToDateObj(bTime);

	var b = false;

	if (judgeType == "EQUAL") {
		if (aDateObj.getTime() == bDateObj.getTime()) {
			b = true;
		}
	}

	if (judgeType == "LESS") {
		if (aDateObj.getTime() < bDateObj.getTime()) {
			b = true;
		}
	}

	if (judgeType == "MORE") {
		if (aDateObj.getTime() > bDateObj.getTime()) {
			b = true;
		}
	}

	return b;
}

/**
 * (注意!有新的function可以用,建議不使用此function)
 * 
 * @param inputDate
 * @returns {Boolean}
 */
function isLessThanToday(inputDate) {
	var d = new Date();
	var temp = inputDate.split("/");
	var inputD = new Date(temp[0], new Number(temp[1]) - 1, temp[2]);

	var b = false;

	if (inputD.getTime() <= d.getTime()) {
		b = true;
	}

	return b;
}

/**
 * 
 * @returns {String},回傳格式為yyyy/MM/dd
 */
function getTodayStr() {
	var d = new Date();
	var yy = d.getYear();
	var mm = d.getMonth() + 1;
	var dd = d.getDate();

	if (yy < 2000) {
		yy += 1900;
	}

	if (mm < 10) {
		mm = "0" + mm;
	}

	if (dd < 10) {
		dd = "0" + dd;
	}

	return yy + "/" + mm + "/" + dd;
}

/**
 * 
 */
function dateObjToFormat(dateObj) {
	var yy = dateObj.getYear();
	var mm = dateObj.getMonth() + 1;
	var dd = dateObj.getDate();

	if (yy < 2000) {
		yy += 1900;
	}

	if (mm < 10) {
		mm = "0" + mm;
	}

	if (dd < 10) {
		dd = "0" + dd;
	}

	return yy + mm + dd + "";
}

/**
 * 
 * @param val
 * @returns {String}
 */
function transROCStrToADStr(val) {
	var d = val.split("/");
	var y = new Number(d[0]) + 1911;
	var dateStr = y + "/" + d[1] + "/" + d[2];
	return dateStr;
}

/**
 * yyyy/MM/dd->rocYYY/MM/dd
 */
function transADStrToROCStr(val) {
	var d = val.split("/");
	var y = new Number(d[0]) - 1911;
	var dateStr = y + "/" + d[1] + "/" + d[2];
	return dateStr;
}

/**
 * 
 * @param d1
 * @param d2
 * @returns {Number}
 */
function calYearsOld(d1, d2) {
	return (Date.parse(d2) - Date.parse(d1)) / (24 * 60 * 60 * 1000 * 365);
}

/**
 * 因為IE6 7 8沒有document.getElementsByClassName函式(僅針對南山專案)
 * 
 * @param obj
 * @param className
 * @returns {Array}
 */
function getElementsByClassName(obj, className) {
	var found = [];

	var elements = null;

	if (obj == null) {
		elements = document.getElementsByTagName("*");
	} else {
		elements = obj.getElementsByTagName("*");
	}

	for (var i = 0; i < elements.length; i++) {
		var names = elements[i].className.split(' ');

		for (var j = 0; j < names.length; j++) {
			if (names[j] == className)
				found.push(elements[i]);
		}
	}

	return found;
}

/**
 * 
 * @param nmStr
 * @returns {Boolean}
 */
function checkNmSyntax(nmStr) {
	return !checkRegular(nmStr, "[a-zA-Z0-9!@#$%\^&*()_+{}:\"\<\>\?~]");
}

/**
 * 檢核E-Mail格式(注意!此僅會回傳true、false)
 * 
 * @param emailStr
 * @returns {Boolean}
 * @deprecated
 */
function checkEmailSyntax(emailStr) {
	return checkRegular(emailStr, "[a-zA-Z0-9._%-]+@[a-zA-Z0-9._%-]+\.[a-zA-Z]{2,4}");
}

// /**
// * 已重複，不要再用囉
// * @param str
// * @returns {Boolean}
// * @deprecated
// */
// function checkAllNumFloat(str) {
// var bArray = new Array();
//
// for (var i = 0; i < str.length; i++) {
// var s = str.substring(i, i + 1);
//
// bArray.push(checkRegular(s, "[0-9]{1}"));
// }
//
// var b = false;
//
// // 如果陣列中沒有出現檢和錯誤,即代表均為數字
// if (!checkBArrayContainInputBoolean(bArray, false)) {
// b = true;
// }
//
// return b;
// }

/**
 * 已重複，不要再用囉
 * 
 * @param str
 * @returns {Boolean}
 * @deprecated
 */
function checkAllNum(str) {
	var bArray = new Array();

	for (var i = 0; i < str.length; i++) {
		var s = str.substring(i, i + 1);

		bArray.push(checkRegular(s, "[0-9]{1}"));
	}

	var b = false;

	// 如果陣列中沒有出現檢和錯誤,即代表均為數字
	if (!checkBArrayContainInputBoolean(bArray, false)) {
		b = true;
	}

	return b;
}

/**
 * 
 * @param id
 * @returns {Boolean}
 */
function checkNationalId_old(id) {
	var headStr = id.substring(0, 1);
	var tailStr = id.substring(1, id.length);

	if (checkRegular(tailStr, "[a-zA-Z]")) {
		return false;
	}

	var bArray = new Array();

	// 檢查字頭
	bArray.push(checkRegular(headStr, "[a-zA-Z]{1}"));

	// 檢查字尾
	var tB = false;

	if (checkRegular(tailStr, "[0-9]{9}") & tailStr.length == 9) {
		tB = true
	}

	bArray.push(tB);

	// 綜合檢查
	var b = false;

	if (bArray[0] == true & bArray[1] == true) {
		b = true;
	}

	// if (checkBArrayContainInputBoolean(bArray, false)) {
	// b = true;
	// }

	return b;
}

/**
 * 
 * @param str
 * @param regularExpSyntax
 * @returns {Boolean}
 */
function checkRegular(str, regularExpSyntax) {
	var resExpAftCompil = new RegExp(regularExpSyntax);

	var b = false;

	var strTemp = str + "";// 預防自動轉型問題

	if (strTemp.match(resExpAftCompil)) {
		b = true;
	}

	return b;
}

/**
 * 
 * @param bArray
 * @param defineB
 * @returns {Boolean}
 */
function checkBArrayContainInputBoolean(bArray, defineB) {
	var b = false;

	for (var i = 0; i < bArray.length; i++) {
		if (bArray[i] == defineB) {
			b = true;
		}
	}

	return b;
}

/**
 * 
 * @param bArray
 * @returns {Boolean}
 */
function checkBArrayHaveTrue(bArray) {
	var b = false;

	for (var i = 0; i < bArray.length; i++) {
		if (bArray[i] == true) {
			b = true;
		}
	}

	return b;
}

/**
 * 檢查輸入的值,如果為"不存在"或空值,則執行func
 */
function checkInputAndExeFunc(valArray, func) {
	var errorMsg = "";
	var b = false;// 有無錯誤

	for (var i = 0; i < array.length; i++) {
		var a = array[i];

		if (checkIsNullSpace(a[0])) {
			errorMsg += a[1] + "<br>";
			b = true;
		}
	}

	if (b) {
		func();
	}

	return b;
}

/**
 * 
 * @param val
 * @returns {Boolean}
 */
function checkIsNullSpace(val) {
	if (val == null || val == undefined || val == "undefined" || val == "") {
		return true;
	}

	return false;
}

/**
 * 
 * @param _URL
 * @param _windowName
 * @param w_width
 * @param w_height
 * @param _scrollable
 * @param _resizable
 * @returns
 */
function openPopupWindow(_URL, _windowName, w_width, w_height, _scrollable, _resizable) {
	var maxW = screen.width;
	var maxH = screen.height;
	var _top = Math.floor((maxH - 600) / 2);
	var _left = Math.floor((maxW - 800) / 2);

	var win = window.open(_URL, _windowName, "toolbar=no, menubar=no, scrollbars=" + _scrollable + ", resizable=" + _resizable
		+ ",location=no, status=no,width=" + w_width + ",height=" + w_height);
	win.moveTo(_left, _top);
	win.focus();
}

/**
 * 
 * @param _URL
 * @param _windowName
 * @param w_width
 * @param w_height
 * @param _scrollable
 * @param _resizable
 * @returns
 */
function openPopupWindowWithMenu(_URL, _windowName, w_width, w_height, _scrollable, _resizable) {
	var maxW = screen.width;
	var maxH = screen.height;
	var _top = Math.floor((maxH - 600) / 2);
	var _left = Math.floor((maxW - 800) / 2);

	var win = window.open(_URL, _windowName, "toolbar=no, menubar=yes, scrollbars=" + _scrollable + ", resizable=" + _resizable
		+ ",location=no, status=no,width=" + w_width + ",height=" + w_height);
	win.moveTo(_left, _top);
	win.focus();
}

/**
 * 
 * @param _URL
 * @param _windowName
 * @param _scrollable
 * @param _resizable
 * @returns
 */
function openPopupWindowMax(_URL, _windowName, _scrollable, _resizable) {
	var winMax = window.open(_URL, _windowName, "toolbar=no, menubar=no, scrollbars=" + _scrollable + ", resizable=" + _resizable
		+ ",location=no, status=no");
	winMax.moveTo(0, 0);
	winMax.resizeTo(screen.availWidth, screen.availHeight);
	winMax.focus();
}
/**
 * 阿拉伯數字轉月份
 * 
 * @param arab
 *            阿拉伯數字
 */
function transChineseMonth(arab) {
	var tempArab = parseInt(arab);
	var month = "";
	if (tempArab == 1)
		month = "一";
	else if (tempArab == 2)
		month = "二";
	else if (tempArab == 3)
		month = "三";
	else if (tempArab == 4)
		month = "四";
	else if (tempArab == 5)
		month = "五";
	else if (tempArab == 6)
		month = "六";
	else if (tempArab == 7)
		month = "七";
	else if (tempArab == 8)
		month = "八";
	else if (tempArab == 9)
		month = "九";
	else if (tempArab == 10)
		month = "十";
	else if (tempArab == 11)
		month = "十一";
	else if (tempArab == 12)
		month = "十二";
	return month;
}

/**
 * 檢核是否為統一編號，為正確格式時回傳""，反之回傳錯誤訊息 (不跳出alert，僅回傳true/false)
 */
function isCompanyIDToErrorMsg(idName, msg) {
	var isRight = true;
	var val = $("#" + idName).val();

	removeCss(idName);

	var li_weight = new Array("1", "2", "1", "2", "1", "2", "4", "1");
	var ls_no, li_sum, li_sum1, i;

	// 公司統編長度確認
	if ((val.length != 8) || isNaN(val)) {
		isRight = false;
	}

	// 公司統編檢核計算
	if (isRight) {
		li_sum = 0;

		for (i = 0; i <= 7; i++) {
			ls_no = val.charAt(i);
			li_sum1 = parseInt(ls_no, 10) * li_weight[i];
			li_sum += parseInt(li_sum1 / 10, 10) + (li_sum1 % 10);
		}

		i = li_sum % 10;
		ls_no = val.charAt(6);

		if ((i != 0) && (ls_no == '7')) {
			li_sum -= 9;
		}

		i = li_sum % 10;

		if (i != 0) {
			isRight = false;
		}
	}

	// 檢核錯誤時的動作
	if (!isRight) {
		addCss(idName);
		return appendBreakLine(msg);
	}

	return "";
}


/**
 * 是否有值(全型空白為有值)
 * 
 * @param value
 *            標籤id
 * @return boolean
 */
function isExistValue(value) {
	if (value == undefined || value == null || $.trim(value).length == 0) {
		return false;
	}
	return true;
}

/**
 * jquery ajax common fucntion
 * 
 * @param url
 *                URL網址
 * @param param
 *                傳入至action參數
 * @param callback
 *                成功的方法
 * @param errorCallback
 *                失敗的方法
 */
function ajaxRequestBlockUI(url, param, callback, errorCallback) {
	$.ajax({
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		type: 'post',
		url: url,
		data: param,
		async: true,
		cache: false,
		dataType: "json",
		/** 執行function前先鎖定畫面 */
		beforeSend: function () {
			$.blockUI({
				message: $("#domMessage").html($("#domMessage").html()),
				css: {
					border: '1px solid',
					width: '200px',
					top: '40%',
					left: '40%'
				}
			});
		},
		/** 執行結束後解鎖畫面 */
		complete: function () {
			$.unblockUI();
		},
		success: function (data) {
			callback(data);
		},
		error: function (data) {
			errorCallback(data);
		},
	});
}

/**
 * 檢查email格式
 * 
 * @param fieldName
 * @param msg
 * @returns
 */
function isEmailAddress(fieldName, msg) {
	var fieldobject = getObject(fieldName);
	var val = fieldobject.value;
	data = val.match(/^\S+@\S+\.\S+$/);
	if (!data || !val)
		return showErrorCommon(fieldobject, msg);
	if (val.indexOf(';') != -1)
		return showErrorCommon(fieldobject, msg);
	return true;
}

/**
 * 檢查手機格式
 * 
 * @param fieldName
 * @param msg
 * @returns
 */
function isMobileFormat(fieldName, msg) {
	var fieldobject = getObject(fieldName);
	var val = fieldobject.value;
	data = val.match(/^[09]{2}[0-9]{2}-[0-9]{6}$/);
	if (!data || !val)
		return showErrorCommon(fieldobject, msg);
	if (val.indexOf(';') != -1)
		return showErrorCommon(fieldobject, msg);
	return true;
}

function isTelFormat(fieldName, msg) {
	var fieldobject = getObject(fieldName);
	var val = fieldobject.value;
	// data = val.match(/^[0-9]{2}-[0-9]{7,8}#[0-9]{3}$/);
	data = val.match(/^[0-9]{2}-[0-9]{7,8}(?:(?:#)(\d+))?$/);

	if (!data || !val)
		return showErrorCommon(fieldobject, msg);
	if (val.indexOf(';') != -1)
		return showErrorCommon(fieldobject, msg);
	return true;
}

function showErrorCommon(val, msg) {
	alert(msg);
	if (val.type != null && val.type != 'hidden') {
		val.focus();
	}
	return false;
}

var jsform = null;

function setformname(formname) {
	jsform = formname;
}

function getObject(fieldName) {
	if (jsform == null) {
		jsform = "all";
	}
	var o_name = 'document.' + jsform + '["' + fieldName + '"]';
	var e_index = fieldName.indexOf("[");
	if (e_index != -1) {
		o_name = 'document.' + jsform + '["' + fieldName.substring(0, e_index) + '"]' + fieldName.substring((e_index), fieldName.length);
	}
	var o_field = eval(o_name);

	if (o_field == null) {
		var e_index = o_name.indexOf("[0]");
		if (e_index != -1 && o_field == null) {
			o_field = eval(o_name.substring(0, e_index));
		}
	}
	if (o_field == null) {
		alert("在取[" + o_name + "]值時是未定義的，請檢查有此物件");
	}
	if (o_field.value == null) {
		alert("在取 " + o_name + " 欄位時發生欄位定義有兩個以上，" + "\n原因一　:可能 " + fieldName + " 欄位在兩個form以上出現，"
			+ "\n處理方式:請在with(document.<%=formName%>) {" + "\n　　 　　加入一行setformname('<%=formName%>');" + "\n原因二　:在form裡面此 " + fieldName
			+ " 欄位是屬於陣列型態，" + "\n處理方式:請把 " + fieldName + " 欄位定義陣列，" + "\n　　例如: " + fieldName + "[0] ");
	}
	return o_field;
}

/**
 * 是否有值(全型空白為有值)
 * 
 * @param value
 *            標籤id
 * @return boolean
 */
function isExistValue(value) {
	if (value == undefined || value == null || $.trim(value).length == 0) {
		return false;
	}
	return true;
}

/**
 * 將時間隔格式為「HHMMSS」轉成「HH:MM:SS」
 * 
 * @param str
 */
function transTimeFormat(str) {
	var hour = str.substring(0, 2);
	var minute = str.substring(2, 4);
	var second = str.substring(4, 6);
	return hour + ":" + minute + ":" + second + "";
}

/**
 * 檢查時間格式(HH:MM)
 */
function isTimeHHMM(str) {
	if (str == undefined || str != undefined == null || str == "") {
		return true;
	}
	var a = str.match(/^(\d{1,2})(:)(\d{1,2})$/);
	if (a == null) {
		return false;
	}
	if (a[1] > 24 || a[3] > 60) {
		return false
	}
	return true;
}

/**
 * 身份證檢核(旺旺使用的function)
 * 
 * @param id
 * @returns
 */
function checkPermit(id) {
	if (id.length != 10) {
		return false;
	}

	if (isNaN(id.substr(2, 8)) || (id.substr(0, 1) < "A" || id.substr(0, 1) > "Z") || (id.substr(1, 1) < "A" || id.substr(1, 1) > "Z")) {
		return false;
	}

	var head = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
	id = (head.indexOf(id.substr(0, 1)) + 10) + '' + ((head.indexOf(id.substr(1, 1)) + 10) % 10) + '' + id.substr(2, 8);
	s = parseInt(id.substr(0, 1)) + parseInt(id.substr(1, 1)) * 9 + parseInt(id.substr(2, 1)) * 8 + parseInt(id.substr(3, 1)) * 7
		+ parseInt(id.substr(4, 1)) * 6 + parseInt(id.substr(5, 1)) * 5 + parseInt(id.substr(6, 1)) * 4 + parseInt(id.substr(7, 1)) * 3
		+ parseInt(id.substr(8, 1)) * 2 + parseInt(id.substr(9, 1)) + parseInt(id.substr(10, 1));

	if ((s % 10) != 0) {
		return false;
	}

	return true;
}

/**
 * 取得inputDate後overMonth個月的日期
 * 
 * @param inputDate
 *            要計算的日期，如果沒有値，則為目前日期
 * @param overMonth
 *            要超過幾個月
 * @return Date
 */
function overDateByMonth(inputDate, overMonth) {
	if (!isExistValue(inputDate)) {
		inputDate = new Date();
	}
	inputDate.setMonth(inputDate.getMonth() + Number(overMonth));
	return inputDate;
}

/**
 * 在一html物件中含<select name='nm1'/><select name='nm2'/>...<input type='h1'/><input
 * type='h2'/>...(下拉選單和隱藏輸入框的物件數量相同) 將值依序設定回hidden
 * 
 * @param obj
 * @returns
 */
function settingHiddenBySelVal(obj) {
	var selList = obj.find("select");
	var hiddenList = obj.find("input[type='hidden']");

	// selectArray應該和hiddenArray數量一致
	if (selList.length != hiddenList.length) {
		console.log('selList value not equal hiddenList value');
		return;
	}

	for (var i = 0; i < selList.size(); i++) {
		if (!checkIsNullSpace(selList.eq(i).val())) {
			hiddenList.eq(i).val(selList.eq(i).val());
		}
	}
}

/**
 * 身分證檢核
 * 
 * @param id
 * @returns
 */
function idCheck(id) {
	tab = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
	A1 = new Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3);
	A2 = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5);
	Mx = new Array(9, 8, 7, 6, 5, 4, 3, 2, 1, 1);

	if (id.length != 10)
		return false;
	i = tab.indexOf(id.charAt(0));
	if (i == -1)
		return false;
	sum = A1[i] + A2[i] * 9;

	for (i = 1; i < 10; i++) {
		v = parseInt(id.charAt(i));
		if (isNaN(v))
			return false;
		sum = sum + v * Mx[i];
	}
	if (sum % 10 != 0)
		return false;
	return true;
}

/**
 * 在一html物件中含<select name='nm1'/><select name='nm2'/>...<input type='h1'/><input
 * type='h2'/>...(下拉選單和隱藏輸入框的物件數量相同) 將值依序設定回select
 * 
 * @param liStr
 * @returns
 */
function settingSelByHiddenVal(liObj) {
	var selList = liObj.find("select");
	var hiddenList = liObj.find("input[type='hidden']");

	// selectArray應該和hiddenArray數量一致
	if (selList.length != hiddenList.length) {
		console.log('selList value not equal hiddenList value');
		return;
	}

	for (var i = 0; i < selList.length; i++) {
		if (!checkIsNullSpace(hiddenList.eq(i).val())) {
			selList.eq(i).val(hiddenList.eq(i).val());
		}
	}
}

/**
 * jQuery物件,轉為Javascript物件
 * 
 * @param jQueryObjArray
 * @returns
 */
function transToJavascriptObj(jQueryObjArray) {
	var a = new Array();

	jQueryObjArray.each(function () {
		a.push($(this)[0]);
	});
}

/**
 * 拆解option中html的符號,使之內容變為新的屬性(注意!此function就地更改內容,故option的selected狀態不會被動到)
 * 
 * @param selObj
 * @param seperatSymbol
 *            html內容含的符號
 * @param htmlArrayNum
 *            html拆解之後的改為屬性使用的陣列位置
 * @param newAttrNm
 *            新的屬性名稱
 * @returns
 */
function settingSelOptionAddAttrByHtml(selObj, seperatSymbol, htmlArrayNum, newAttrNm) {
	var optionArray = selObj.getElementsByTagName("option");

	for (var i = 0; i < optionArray.length; i++) {
		var h = optionArray[i].innerHTML;
		var a = h.split(",");
		var newAttrCont = a[htmlArrayNum];

		// 設定新的屬性
		optionArray[i].setAttribute(newAttrNm, newAttrCont);

		// 新的html
		var newHtml = "";

		for (var j = 0; j < a.length; j++) {
			if (j != htmlArrayNum) {
				newHtml += a[j] + seperatSymbol;
			}
		}

		newHtml = delContentByLastSymbol(newHtml, seperatSymbol);
		optionArray[i].innerHTML = newHtml;
	}
}

/**
 * 
 * @returns
 */
function calTimeGap() {
	var d = new Date();
	console.log(d);

	var d2 = new Date(procYear(d), (d.getMonth() - (5 * 12)), d.getDate());
}

/**
 * new Date()產生的年,如果小於1999則需另外加回,才會得到正確的數字
 * 
 * @returns
 */
function procYear(d) {
	var yy = d.getYear();

	if (yy < 2000) {
		yy += 1900;
	}

	return yy;
}

/**
 * 確認輸入的物件,檢查值是否為空
 * 
 * @param beCheckInputObjArray
 * @returns
 */
function findSpaceInputObjType1(beCheckInputObjArray) {
	var jQueryObjArray = new Array();

	for (var i = 0; i < beCheckInputObjArray.size(); i++) {
		var obj = beCheckInputObjArray.eq(i);

		// 如果為單一input,則才需要往下檢查
		if (obj.size() != 1) {
			continue;
		}

		if (checkIsNullSpace(obj.eq(0).val())) {
			jQueryObjArray.push(beCheckInputObjArray.eq(i));
		}
	}

	return jQuery(jQueryObjArray);
}

// =====針對旺旺B2B使用的function=====

/**
 * 蒐集[{"KEY" : "VAL", ...},{...}...]形式的資料中符合輸入參數Key的內容
 * 
 * @param dataArray
 * @param key
 * @returns
 */
function sortOutJsonArrayByKey(dataArray, key) {
	var sortOutArray = new Array();

	for (var i = 0; i < dataArray.length; i++) {
		sortOutArray.push(dataArray[i][key]);
	}

	return sortOutArray;
}

/**
 * 檢查輸入的陣列,是否有值也在另外一個陣列中
 * 
 * @returns
 */
function checkValIsContainValArray(array1, array2) {
	var v = null;

	for (var i = 0; i < array1.length; i++) {
		for (var j = 0; j < array2.length; j++) {
			if (array1[i] + "" == array2[j] + "") {// 預防強制轉型
				v = array2[j];
				break;
			}
		}
	}

	return v;
}

/**
 * 
 * @returns
 */
function setVal2Json4MappingInsType(insType, masterInsType, jsonArray) {
	for (var i = 0; i < jsonArray.length; i++) {
		if (jsonArray[i].INS_TYPE == insType) {
			for (var j = 0; j < jsonArray.length; j++) {
				if (!checkRegular(jsonArray[j].INS_TYPE, "N_") && masterInsType == jsonArray[j].INS_TYPE) {
					jsonArray[i].MASTER_CODE = jsonArray[j].INS_TYPE;
					break;
				}
			}

			break;
		}
	}
}

/**
 * WantWant-B2B check content of input type='text' is null or space
 * 
 * @returns
 */
function findSpaceInputObj(beCheckInputObjArray, nextSelector) {
	// 蒐集錯誤的「jQuery物件」
	// var markSpanArray = $("span[class='formtit']:contains('*')");

	var jQueryObjArray = new Array();

	for (var i = 0; i < beCheckInputObjArray.size(); i++) {
		// select一定會有資料,故不需要在這邊檢查
		// var inputObj = beCheckInputObjArray.eq(i).next("input[type='text']");
		var obj = beCheckInputObjArray.eq(i).next(nextSelector);

		// 如果為單一input,則才需要往下檢查
		if (obj.size() != 1) {
			continue;
		}

		if (checkIsNullSpace(obj.eq(0).val())) {
			jQueryObjArray.push(beCheckInputObjArray.eq(i));
		}
	}

	return jQuery(jQueryObjArray);
}

/**
 * 
 * @returns
 */
function validateInputText(errObjArray) {
	var errMsg = "";

	for (var i = 0; i < errObjArray.length; i++) {
		var temp = errObjArray[i];
		var os = temp.html();
		var markStr = temp.find("span").eq(0)[0].outerHTML;
		var spanCont = os.replace(markStr, "").replace("：", "").trim();
		errMsg += "請輸入" + spanCont + "\n";
	}

	return errMsg;
}

/**
 * 
 * @returns
 */
function validateInputRemoveAndAppendCss(errObjArray) {
	// 一律清除css
	for (var i = 0; i < errObjArray.length; i++) {
		var temp = errObjArray[i];
		$(temp).removeClass("field_error");
	}
}

/**
 * 
 * @param insId
 * @param jsonStr
 * @returns
 */
function checkHaveSelInsInJson(insId, insSelJson) {
	var isHave = false;

	for (var i = 0; i < insSelJson.length; i++) {
		if (insSelJson[i].INS_TYPE == insId) {
			isHave = true;
			break;
		}
	}

	return isHave;
}

// =====針對旺旺B2B的險種選擇產生器=====

/**
 * 
 * @param vhclInsPdtData
 * @returns
 */
function parseJsonForVhclInsPdtData(vhclInsPdtData) {
	vhclInsPdtDataJson = JSON.parse(vhclInsPdtData);// 設為全域變數,待後續可以使用
	var data4Show = vhclInsPdtDataJson.DB_DATA;
	var lng = data4Show.length;

	// 將r0、r1、r2、r3、r4不為null的部份打包起來
	var tempJson = {
		"r0": [],
		"r1": [],
		"r2": [],
		"r3": [],
		"r4": []
	};

	for (var i = 0; i < lng; i++) {
		for (var j = 0; j < 5; j++) {
			if (data4Show[i]["r" + j] != null) {
				tempJson["r" + j].push(data4Show[i]);
			}
		}
	}

	// 整理好資料後,重新排序
	// 依照r0~r4取出ordeerBy資料,待後續sort()
	var orderByJson = {
		"r0": [],
		"r1": [],
		"r2": [],
		"r3": [],
		"r4": []
	};

	for (var i = 0; i < lng; i++) {
		for (var j = 0; j < 5; j++) {
			if (data4Show[i].orderBy != null) {
				orderByJson["r" + j].push(data4Show[i].orderBy);
			}
		}
	}

	var layer0Order = orderByJson.r0.sort();
	var layer1Order = orderByJson.r1.sort();
	var layer2Order = orderByJson.r2.sort();

	sortByOrderArray(tempJson.r0, "orderBy", layer0Order);
	sortByOrderArray(tempJson.r1, "orderBy", layer1Order);
	sortByOrderArray(tempJson.r2, "orderBy", layer2Order);

	return tempJson;
}

/**
 * 產生險種選擇的畫面
 * 
 * @param id
 * @param tempJson
 * @returns
 */
function genAllInsHtmlObj(id, tempJson) {
	// 先產生框
	$("#" + id + "").append("<table class='datatable_line_green2' id='_insSel'></table>");
	$("#" + "_insSel" + "").append("<tr><th width='100%'>投保險種選擇</th></tr>");
	$("#" + "_insSel" + "").append("<tr><td></td></tr>");

	var tdObj_L1 = $("#_insSel td");// 第一層selector syntax
	var selStr = "#_insSel td ol";// 第二層selector syntax

	// 第一層
	for (var i = 0; i < tempJson.r0.length; i++) {
		var cont = "";

		if (tempJson["r" + "0"][i].r0 != null) {
			if (tempJson["r" + "0"][i].showVhclInsId == "0") {
				cont = tempJson.r0[i].insuredText;
			} else {
				cont += tempJson.r0[i].vhclInsId + "." + tempJson.r0[i].insuredText;
				cont += genSelHtml(tempJson.r0[i].selJson);
			}
		}

		// 如果狀況為2,則不顯示代號(因為此種作法並不夠簡單且需要講解和定義,故不使用了)
		// if (tempJson["r" + "0"][i].r0 == "r_typ2") {
		// cont = tempJson.r0[i].insuredText;
		// } else {
		// cont += tempJson.r0[i].vhclInsId + "." + tempJson.r0[i].insuredText;
		// cont += genSelHtml(tempJson.r0[i].selJson);
		// }

		var htmlTagHtml = genRadioOrCheckboxHtml(null, tempJson.r0[i], r);

		// tdObj_L1.append("<ol class='p1'><li>" + htmlTagHtml + cont + "<input
		// type='hidden' value='" + tempJson.r0[i].vhclInsId + "'>"
		// + "<button class='button_green colortxt_white'>險種介紹</button>" +
		// "</li></ol>");
		tdObj_L1.append("<ol class='p1'><li>" + htmlTagHtml + cont + "<input type='hidden' value='" + tempJson.r0[i].vhclInsId + "'>"
			+ "</li></ol>");
	}

	// 第二層以後
	for (var r = 1; r <= 4; r++) {
		var olObjList = $(selStr);

		for (var i = 0; i < olObjList.length; i++) {
			for (var j = 0; j < tempJson["r" + r].length; j++) {
				if (olObjList.eq(i).find("input[type='hidden']").val() == tempJson["r" + r][j]["r" + r]) {
					var cont = "";

					// 畫面顯示需要「.」,例:01.HelloWorld
					if (tempJson["r" + r][j].showVhclInsId == "0") {
						cont = tempJson["r" + r][j].insuredText;
					} else {
						cont += tempJson["r" + r][j].vhclInsId + "." + tempJson["r" + r][j].insuredText;
					}

					// var cont = tempJson["r" + r][j].vhclInsId + "." +
					// tempJson["r" + r][j].insuredText;

					var val = tempJson["r" + r][j].vhclInsId;
					var rootVal = tempJson["r" + r][j]["r" + r];

					// 處理畫面上點選的方式
					var htmlTagHtml = genRadioOrCheckboxHtml(null, tempJson["r" + r][j], r);

					// 處理每個險種後面需要的內容(select、input之類的tag)
					// 處裡畫面上需要的下拉式選單
					var selectHtml = genSelHtml(tempJson["r" + r][j].selJson);

					// 將結果嵌入到對應的位置
					// olObjList.eq(i).append(
					// "<ol class='p" + new Number(r + 1) + "'><li>" +
					// htmlTagHtml + cont + "<input type='hidden' value='" + val
					// + "'>" + "<input type='hidden' name='root' value='" +
					// rootVal + "'>" + "&nbsp;" + selectHtml + "&nbsp;"
					// + "<button class='button_green
					// colortxt_white'>險種介紹</button>" + "</li></ol>");
					olObjList.eq(i).append(
						"<ol class='p" + new Number(r + 1) + "'><li>" + htmlTagHtml + cont + "<input type='hidden' value='" + val
						+ "'>" + "<input type='hidden' name='root' value='" + rootVal + "'>" + "&nbsp;" + selectHtml + "&nbsp;"
						+ "</li></ol>");
				}
			}
		}

		selStr += " " + "ol";
	}

	// 設定disable至tag
	// checkbox的部份
	var tempOl = $("#_insSel td").children("ol").children("li");

	for (var i = 0; i < tempOl.length; i++) {
		if (tempOl.eq(i).find("input[type='checkbox']").size() != 0) {
			tempOl.eq(i).parent("ol").children("ol").find("input[type='checkbox']").attr("disabled", true);
		}
	}

	// radio的部份
	$("#_insSel td").children("ol").children("ol").children("ol").find("input[type='radio']").attr("disabled", true);
}

/**
 * 產生險種選擇的畫面的子功能
 * 
 * @param selJson
 * @returns
 */
function genSelHtml(selArray) {
	var selectHtml = "";

	if (selArray != null) {
		var selectHtmlOptions = "";
		var keys = Object.keys(selArray);
		keys.sort();

		for (var k = 0; k < keys.length; k++) {
			var selectHtmlOptions = "";
			var optionA = selArray[keys[k]];

			// 找到「最長」的值
			var lng = 0;

			for (var v = 0; v < optionA.length; v++) {
				if (optionA[v].VALUE.length > lng) {
					lng = optionA[v].VALUE.length;
				}
			}

			// 排序處理
			var valArray = new Array();

			for (var l = 1; l <= lng; l++) {
				var digiArray = new Array();

				for (var v = 0; v < optionA.length; v++) {
					if (optionA[v].VALUE.length == l) {
						digiArray.push(optionA[v].VALUE);
					}
				}

				// concat()不知道為什麼無效,這邊使用迴圈裝載重新
				digiArray.sort()

				for (var v2 = 0; v2 < digiArray.length; v2++) {
					valArray.push(digiArray[v2]);
				}
			}

			// 這邊僅能分別在一位數和其他位數的資料,不使用了
			// var digi1Array = new Array();
			// var digi2Array = new Array();
			//
			// for (var v = 0; v < optionA.length; v++) {
			// if (optionA[v].VALUE.length == 1) {// 「字位數」為1的,例:「1」或「G」
			// digi1Array.push(optionA[v].VALUE);
			// } else {
			// digi2Array.push(optionA[v].VALUE);
			// }
			// }
			//
			// digi1Array.sort();
			// digi2Array.sort();
			// var valArray = digi1Array.concat(digi2Array);

			sortByOrderArray(optionA, "VALUE", valArray);

			// 產生select程式碼
			for (var o = 0; o < optionA.length; o++) {
				selectHtmlOptions = selectHtmlOptions + "<option value='" + optionA[o].VALUE + "'>" + optionA[o].DESC + "</option>";
			}

			selectHtml = selectHtml + "&nbsp;" + "<select name='" + keys[k] + "'>" + selectHtmlOptions + "</select>";
		}
	}

	return selectHtml;
}

/**
 * 產生險種選擇的畫面的子功能
 * 
 * @param rootVal
 * @param jsonObj
 * @param lvl
 * @returns
 */
function genRadioOrCheckboxHtml(rootVal, jsonObj, lvl) {
	var s = "";

	if (rootVal != null) {
		s = rootVal.vhclInsId;
	}

	var htmlTag = jsonObj.htmlTag;
	var htmlTagHtml = null;

	switch (htmlTag) {
		case null:
			htmlTagHtml = "";
			break;
		case "CHECKBOX":
			htmlTagHtml = "<input type='checkbox' value='" + jsonObj.vhclInsId + "' onclick='triggerIns4Sel(this);'/>";
			break;
		case "RADIO":
			htmlTagHtml = "<input type='radio' name='" + "lvl_" + lvl + "_upperIs" + jsonObj["r" + lvl] + "' value='" + jsonObj.vhclInsId
				+ "' onclick='triggerIns4Radio(this, \"" + lvl + "\");'/>";
			break;
	}

	return htmlTagHtml;
}

/**
 * 
 * @param obj
 * @param lvl
 * @returns
 */
function triggerIns4Radio(obj, lvl) {
	var jqueryObj = jQuery(obj);
	var parentOl = jqueryObj.parent("li").parent("ol").parent("ol");
	var olArray = parentOl.children("ol");

	for (var i = 0; i < olArray.length; i++) {
		var liObj = olArray.eq(i).children("li");

		if (liObj.find("input[type='radio']").prop("checked") == true) {
			liObj.parent("ol").children("ol").find("input[type='radio']").attr("disabled", false);
		} else {
			liObj.parent("ol").children("ol").find("input[type='radio']").removeAttr("checked");
			liObj.parent("ol").children("ol").find("input[type='radio']").attr("disabled", true);
		}
	}
}

/**
 * 
 * @param obj
 * @returns
 */
function triggerIns4Sel(obj) {
	var jqueryObj = jQuery(obj);
	var parentOl = jqueryObj.parent("li").parent("ol");
	var olArray = parentOl.children("ol");

	if (jqueryObj.prop("checked") == true) {
		// 將checkbox為根的子input(checkbox、radio)設為可輸入
		for (var i = 0; i < olArray.length; i++) {
			olArray.eq(i).find("input[type='checkbox']").attr("disabled", false);
			olArray.eq(i).find("input[type='radio']").attr("disabled", false);
		}
	} else {
		// 將checkbox為根的子input(checkbox、radio)設為不可輸入和將勾選的部份取消
		for (var i = 0; i < olArray.length; i++) {
			olArray.eq(i).find("input[type='checkbox']").removeAttr("checked");
			olArray.eq(i).find("input[type='checkbox']").attr("disabled", true);
			olArray.eq(i).find("input[type='radio']").removeAttr("checked");
			olArray.eq(i).find("input[type='radio']").attr("disabled", true);
		}
	}
}

/**
 * (注意!將廢棄不使用)將所選擇的險種解析為儲存的資料
 * 
 * @param id
 * @returns
 */
function parseCheckedToJson(id) {
	var tempJsonStr = null;
	var inputChecked = $("#" + id + " li input[type='checkbox']:checked, #" + id + " li input[type='radio']:checked");

	if (inputChecked.size() != 0) {
		var tempJson = {
			"DATA": []
		};

		for (var i = 0; i < inputChecked.length; i++) {
			var sub = {};
			var liObj = inputChecked.eq(i).parent("li");

			// INS處理
			sub.INS_TYPE = liObj.find("input").eq(0).val();

			// 後面的下拉選單處理
			var selArray = liObj.find("select");

			if (selArray.length != 0) {
				for (var s = 0; s < selArray.length; s++) {
					sub[selArray[s].name] = selArray[s].value;
				}
			}

			// 根選項處理
			sub.MASTER_CODE = liObj.find("> input").last().val();

			tempJson.DATA.push(sub);
		}

		tempJsonStr = processJSONObjToStr(tempJson);
	}

	// TODO:處理例外狀況(1、5、21、31、32、49(尚待確認?)底下子選單如果有選起來,則)
	// 這邊如果有相關選項的話,則將不要的險種移除

	return tempJsonStr;
}

/**
 * 將所選擇的險種解析為儲存的資料
 * 
 * @param id
 * @returns
 */
function parseCheckedToJsonObj(id) {
	var tempJsonObj = null;
	var inputChecked = $("#" + id + " li input[type='checkbox']:checked, #" + id + " li input[type='radio']:checked");

	if (inputChecked.size() != 0) {
		var tempJson = {
			"DATA": []
		};

		for (var i = 0; i < inputChecked.length; i++) {
			var sub = {};
			var liObj = inputChecked.eq(i).parent("li");

			// INS處理
			// 如果遇到「N_」則跳過不收集
			if (checkRegular(liObj.find("input").eq(0).val(), "N_")) {
				continue;
			}

			sub.INS_TYPE = liObj.find("input").eq(0).val();

			// 根選項處理
			if (!checkRegular(liObj.find("input[name='root']").eq(0).val(), "N_")) {
				sub.MASTER_CODE = liObj.find("input[name='root']").eq(0).val();
			}

			// 取的下拉選單值
			var selArray = liObj.find("select");

			if (selArray.length != 0) {
				for (var s = 0; s < selArray.length; s++) {
					sub[selArray[s].name] = selArray[s].value;
				}
			}

			// 如果有根選項,則取根選項的下拉選單值,作為自己的值(如果有重複種類的資料,則取根選項的)
			var rootSelArray = $("input[value='" + sub.MASTER_CODE + "']").eq(0).parent("li").find("select");

			if (rootSelArray.length != 0) {
				for (var ss = 0; ss < rootSelArray.length; ss++) {
					if (checkRegular(rootSelArray[ss].value, "N_")) {
						continue;
					}

					sub[rootSelArray[ss].name] = rootSelArray[ss].value;
				}
			}

			// 僅針對36A的測試
			// if(sub.INS_TYPE == "36A"){
			// alert(sub.INS_TYPE);
			// sub.MASTER_CODE = "31";
			// var rootSelArray2 = $("input[value='" + "65" +
			// "']").eq(0).parent("li").find("select");
			//				
			// if (rootSelArray2.length != 0) {
			// for (var ss = 0; ss < rootSelArray2.length; ss++) {
			// sub[rootSelArray2[ss].name] = rootSelArray2[ss].value;
			// }
			// }
			// }

			// 未進行
			// 如果有根選項的根選項,則取根選項的根選項的下拉選單值,作為自己的值(如果有重複種類的資料,則取根選項的)
			// var rootSelArray = $("input[value='" + sub.MASTER_CODE +
			// "']").eq(0).parent("li").find("select");
			//			
			// if (rootSelArray.length != 0) {
			// for (var ss = 0; ss < rootSelArray.length; ss++) {
			// sub[rootSelArray[ss].name] = rootSelArray[ss].value;
			// }
			// }

			tempJson.DATA.push(sub);
		}

		tempJsonObj = tempJson;
	}

	return tempJsonObj;
}

/**
 * 將所選的值設定回險種選擇
 * 
 * @returns
 */
function settingSelectedInsValInTable(id, jsonData) {
	var a = $("#" + id + " li input[type='checkbox'], #" + id + " li input[type='radio']");
	var stableKey = "INS_TYPE";// 一定有的KEY
	var data = jsonData.DATA;

	for (var i = 0; i < a.length; i++) {
		for (var j = 0; j < data.length; j++) {
			if (a.eq(i).val() == data[j][stableKey]) {
				// 處理選擇的部份
				a.eq(i).parent("li").find("input").eq(0).prop("checked", true);

				// 處理下拉式選單
				var keys = Object.keys(data[j]);

				for (var k = 0; k < keys.length; k++) {
					if (keys[k] != stableKey) {
						var selectVal = data[j][keys[k]];
						a.eq(i).parent("li").find("select[name='" + keys[k] + "']").eq(0).val(data[j][keys[k]]);
					}
				}

				break;// 照理說一次只會對應到一次,故直接跳出此條件的迴圈找下一筆
			}
		}
	}
}

/**
 * 
 * @param val
 * @returns
 */
function parseCalInsDataToTable(jsonStr1, jsonStr2, calResOtherId, calResInsRangeId, amtInsId, amtInsFreeId) {
	var json = JSON.parse(jsonStr1);
	var json2 = JSON.parse(jsonStr2);

	// 設定查詢資料
	var res = "";

	if (!checkIsNullSpace(json.FORCE_INS)) {
		res += checkIsNullSpace(json.FORCE_INS.NEW_GRADE) ? "" : "<li>係數等級(強制)：<span class='colortxt_blue'>" + json.FORCE_INS.NEW_GRADE;
		+"</span></li>";
		res += checkIsNullSpace(json.FORCE_INS.PREMIUM_E_DATE) ? "" : "<li>查詢有效日期：<span class='colortxt_blue'>"
			+ convertDateFormat(json.FORCE_INS.PREMIUM_E_DATE, "/") + "</span></li>";
		res += checkIsNullSpace(json.FORCE_INS.QUERY_SERIAL_NO) ? "" : "<li>查詢序號(強制)：<span class='colortxt_blue'>"
			+ json.FORCE_INS.QUERY_SERIAL_NO + "</span></li>";
	}

	if (!checkIsNullSpace(json.ANY_INS)) {
		res += "<li>查詢序號(任意)：<span class='colortxt_blue'>" + json.ANY_INS.QUERY_SERIAL_NO + "</span></li>";
	}

	if (!checkIsNullSpace(res)) {
		$("#" + calResOtherId + "").append("<tr><th width='100%''>查詢資料</th></tr>");
		$("#" + calResOtherId + "").append("<tr><td><ul>" + res + "</ul></td></tr>");
	}

	// 設定保障範圍
	$("#" + calResInsRangeId + "").append("<tr><th colspan='5'>保障範圍</th></tr>");
	$("#" + calResInsRangeId + "")
		.append(
			"<tr><th class='gray c'>保險種類</th><th class='gray c'>保險金額</th><th class='gray c'>自負額</th><th class='gray c'>保險費</th><th class='gray c'>優惠後保險費</th></tr>");

	var insRange = json.INS_RANGE;

	if (!checkIsNullSpace(insRange)) {
		for (var i = 0; i < insRange.length; i++) {
			var obj = insRange[i];
			var tds = "";

			var iInsTypeNm = findInsTypeNm(obj.iInsType, json2);

			tds += "<td>" + obj.iInsType + "." + iInsTypeNm + "</td>";

			if (!checkIsNullSpace(obj.insured_text)) {
				var insuredShow = null;

				if (checkNaturalNum(obj.insured_text)) {
					// 純數字則加千分號
					insuredShow = addCommas(obj.insured_text);
				} else {
					// 非純數字
					insuredShow = obj.insured_text;
				}

				tds += "<td class='r'>" + insuredShow + "</td>";
			} else {
				tds += "<td class='r'>" + "" + "</td>";
			}

			if (!checkIsNullSpace(obj.deductible)) {
				tds += "<td class='r'>" + addCommas(obj.deductible) + "</td>";
			} else {
				tds += "<td class='r'>" + "" + "</td>";
			}

			if (!checkIsNullSpace(obj.premiumsFree)) {
				tds += "<td class='r'>" + addCommas(obj.premiumsFree) + "</td>";
			} else {
				tds += "<td class='r'>" + "" + "</td>";
			}

			if (!checkIsNullSpace(obj.premiums)) {
				tds += "<td class='r'>" + addCommas(obj.premiums) + "</td>";
			} else {
				tds += "<td class='r'>" + "" + "</td>";
			}

			$("#" + calResInsRangeId + "").append("<tr>" + tds + "</tr>");
		}
	}

	// 設定總保費
	$("#" + amtInsId + "").html("總保費：NT$" + addCommas(json.AMT_INS));
	$("#" + amtInsFreeId + "").html("優惠後總保費：NT$" + addCommas(json.AMT_INS_FREE));
}

/**
 * 
 * @param iInsType
 * @returns
 */
function findInsTypeNm(iInsType, json2) {
	var res = "";

	var data = json2.DB_DATA;
	var lng = data.length;

	for (var i = 0; i < lng; i++) {
		if (data[i].vhclInsId + "" == iInsType + "") {
			res = data[i].insuredText;
			break;
		}
	}

	return res;
}

/**
 * 依照輸入的排列方式將資料重新排序
 * 
 * @param rowDataArray
 *            被排序的列資料
 * @param orderByCol
 *            使用列資料中哪個欄做排序
 * @param orderColArray
 *            排序順序
 * @returns
 */
function sortByOrderArray(rowDataArray, orderByCol, orderColArray) {
	var p = 0;

	for (var m = 0; m < orderColArray.length; m++) {
		for (var i = p; i < rowDataArray.length; i++) {
			if (orderColArray[m] == rowDataArray[i][orderByCol]) {
				var tempData = rowDataArray[i];
				rowDataArray[i] = rowDataArray[p];
				rowDataArray[p] = tempData;

				p++;
				break;
			}
		}
	}
}

// fetch data of ajax...使用http header+token access ajax post apis
function ajaxPost(url, paramJson, token) {
	var res = null;
	// console.log(url);
	// console.log(paramJson);

	//TOOD if token == null

	$.ajax({
		url: url,
		method: "POST",
		async: false,// 為async function，故使用ajax不需要async
		contentType: "application/x-www-form-urlencoded",
		dataType: "json",
		data: paramJson,
		beforeSend: function (xhr) {
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.setRequestHeader("Authorization", "bearer" + " " + token);
		},
		statusCode: {
			200: function (data) {
				// console.log(data);
				res = data;
			},
			401: function (data) {
				console.log("auth fail");
			},
			default: function (data) {
				console.log("service errors");
			}
		}
	});

	return res;
}

// fetch data of ajax...使用http header+token access ajax post apis
function ajaxGet(url, paramJson, token) {
	var ks = Object.keys(paramJson);
	var sb = "";

	sb += "token=" + token + "&";

	for (var i = 0; i < ks.length; i++) {
		sb += ks[i] + "=" + paramJson[ks[i]] + "&";
		console.log(sb);
	}

	sb = sb.substring(0, sb.lastIndexOf("&"));

	var res = null;
	console.log(url);
	console.log(sb);

	// console.log(paramJson);
	$.ajax({
		url: url + "?" + sb,
		method: "GET",
		async: false,// 為async function，故使用ajax不需要async
		// contentType: "application/x-www-form-urlencoded",
		// beforeSend: function (xhr) {
		// xhr.setRequestHeader("Content-Type",
		// "application/x-www-form-urlencoded");
		// xhr.setRequestHeader("Authorization", "bearer" + " " + token);
		// },
		statusCode: {
			200: function (data) {
				// console.log(data);
				res = data;
			},
			401: function (data) {
				console.log("auth fail");
			},
			default: function (data) {
				console.log("service errors");
			}
		}
	});

	return res;
}

/**
 * 
 * @returns
 */
function fetchToken() {
	var sessionToken = sessionStorage.getItem("token")

	if (checkIsNullSpace(sessionToken) == false) {
		return sessionToken;
	}

	var localToken = localStorage.getItem("token");

	if (checkIsNullSpace(localToken) == false) {
		return localToken;
	}

	return null;
}

/**
 * 
 * @param url
 * @param paramJson
 * @returns
 */
function ajaxPostTokenReady(url, param) {
	var token = fetchToken();

	if (checkIsNullSpace(token) == true) {
		console.log("token is null, we will send token null to function");
	}

	return ajaxPost(url, param, token);
}

/**
 * 
 * @param url
 * @param paramJson
 * @returns
 */
function ajaxGetTokenReady(url, param) {
	var token = fetchToken();

	if (checkIsNullSpace(token) == true) {
		console.log("token is null, we will send token null to function");
	}

	return ajaxGet(url, param, token);
}

/**
 * 
 * @returns
 */
function appendFormToBody(actionContext, idNm) {
	f = document.createElement("form");
	f.style = "display:none;";
	f.action = actionContext;
	f.method = "post";
	f.id = idNm;// "_direct_to_func";
	$(f).appendTo("body");
}

/**
 * 
 * @param idNm
 * @returns
 */
function submitFormByFormId(idNm) {
	$("#" + idNm + "").submit();
}

/**
 * 
 * @param count
 * @returns
 */
function recycleProgressFunc(progressFunc, time) {
	progressFunc();

	setTimeout(function () {
		return recycleProgressFunc(progressFunc, time);
	}, time);
}

/**
 * 
 * @returns
 */
function domain4Springboot(isprotocol) {
	var domains = document.URL.split("/");
	return (isprotocol == true ? domains[0] + "//" : "") + domains[2];
}

/**
 * 
 * @returns
 */
function genTabulator(id, columnsDefine) {
	// 由columnsDefine取得欄位名稱資料
	var headerDefine = {};

	for (var i = 0; i < columnsDefine.length; i++) {
		if (checkIsNullSpace(columnsDefine[i].title)) {
			continue;
		}

		if (checkIsNullSpace(columnsDefine[i].field)) {
			continue;
		}

		headerDefine[columnsDefine[i].field] = columnsDefine[i].title;
	}

	// console.log(headerDefine);

	var pw = [10, 20, 30, 50, 100];

	return new Tabulator("#" + id, {
		tooltipGenerationMode: "hover",
		// tooltipsHeader : true, // enable header tooltips(for all columns)
		height: window.height, // set height of table (in CSS or here),
		// this
		// enables the Virtual DOM and improves render
		// speed dramatically (can be any valid css
		// height value)
		// data : tabledata, // assign data to table
		layout: "fitColumns", // fit columns to width of table
		// (optional)
		pagination: "local",
		paginationSize: pw[0],
		paginationSizeSelector: pw,
		initialHeaderFilter: [{// set the initial value of the
			// header
			// filter to "red"
			field: "col",
			value: "red"
		}],
		tooltipsHeader: true,
		// tooltips : true,
		tooltips: function (cell) {
			if (checkIsNullSpace(cell.getValue()) == true) {
				return cell.getColumn().getField();
			}

			return cell.getValue();
			// return cell.getColumn().getField() + " - " + cell.getValue();
		},
		// initialFilter : [ {// Define Table Columns
		// field : "col",
		// type : "=",
		// value : "red"
		// } ],
		locale: true,
		langs: {
			"zh-tw": {
				"columns":
					headerDefine,
				"pagination": {
					"page_size": "每頁筆數",
					"first": "最前頁",
					"first_title": "最前頁2",
					"last": "最尾頁",
					"last_title": "最尾頁2",
					"prev": "前頁",
					"prev_title": "前頁2",
					"next": "下頁",
					"next_title": "下頁2",
				},
			}
		},
		columns: columnsDefine
		// [
		// {
		// title : "checkbox",
		// formatter : "rowSelection",
		// titleFormatter : "rowSelection",
		// width : 80
		// // formatter : function(cell, formatterParams) {
		// // return "<input name='cb' type='checkbox'>";
		// // }
		// },
		// {
		// title : "Name_test",// 底線無法呈現在畫面欄位
		// field : "uuid",
		// width : 300,
		// visible : true,
		// formatter : function(cell, formatterParams) {
		// var value = cell.getValue();
		// if (value != undefined && value.indexOf("o") > 0) {
		// return "<span style='color:red; font-weight:bold;'>" + value + "</span>";
		// } else {
		// // <!--<button id="hiddenButton" class='button'
		// // type='button' data-toggle='modal'
		// // data-target='#myModal' -->
		// // <!-- onclick="" style="display:
		// // none;">showDialog</button> -->
		//
		// return "<span>" + "<button class='button' type='button'
		// onclick='openDialog(\""
		// + cell.getValue()
		// + "\")' data-toggle='modal' data-target='#myModal'>showDialog</button>"
		// // + "<a href='#'
		// // data-toggle='modal'
		// // data-target='#staticBackdrop'><i
		// // class='fas
		// // fa-pencil-alt'></i></a>"
		// + "&nbsp"
		// // + "<span>"
		// + "<button id='alert_uuid' class='button' type='button'
		// onclick='alert(\""
		// + cell.getValue()
		// + "\");return false;' style='width:150px;'>show cell filed val</button>"
		// + "</span>";
		// }
		// }
		// }, {
		// title : "test2",
		// field : "uuid",
		// formatter : printIcon,
		// width : 150,
		// align : "center",
		// callClick : function(e, cell) {
		// alert("Printing row data for: " + cell.getRow().getData().uuid);
		// },
		// visible : false
		// }, {
		// title : "uuid",
		// field : "uuid",
		// formatter : function(cell, formatterParams) {
		// return "<p>" + cell.getValue() + "</p><p>" + cell.getValue() + "</p>";
		// },
		// // headerTooltip : true,// tip for single column
		// callClick : function(e, cell) {
		// alert("Printing row data for: " + cell.getRow().getData().uuid);
		// }
		// }, {
		// title : "holiday",
		// field : "holiday"
		// }, {
		// title : "memo",
		// field : "memo"
		// }, {
		// title : "status",
		// field : "status"
		// } ],
	});
}

/*
 * 
 */
function locationHref(url) {
	var isuix = true;

	if (isuix == false) {
		location.href = url + "?" + "token=" + fetchToken();
	} else {
		location.href = url + ".html";
	}
};

/**
 * 
 * @param val
 * @returns {Boolean}
 */
function checkIsNullSpace(val) {
	if (val == null || val == undefined || val == "undefined" || val == "") {
		return true;
	}

	return false;
}

// function settingUiJumpToFunc(url) {
// 	alert("url:" + url);
//}

function doAsyncFunc(asyncFunc, asyncSuccFunc) {
	const asyncdata = async () => {
		let temp = await asyncFunc;
		return temp;
	}

	// 將結果依序將資料放入畫面
	asyncdata().then(data => {
		asyncSuccFunc(data);
	}).catch(response => {
		console.log(response)
	});
}

/* input 輸入時有x按鈕 清除內容 */
$(document).ready(function() {
    $('input[type=text]').click(function() {
        $(this).addClass('clearable')
    })
});
function tog(v){return v?'addClass':'removeClass';} 
$(document).on('input', '.clearable', function(){
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function( e ){
    $(this)[tog(this.offsetWidth-18 < e.clientX-this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function( ev ){
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
});
