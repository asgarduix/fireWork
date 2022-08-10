/**
 * 此js為common.js分出來的，因為須對應特定系統流程使用的(此專案中特定使用的js)，且dependency common.js
 */
$(function() {
	//remove token from url bar
	//TODO 處理網址列中顯示token的問題...不行，再想想
	// try {
	// 	var urlstr = document.URL.split("?");

	// 	if (urlstr[1].indexOf("&") > -1) {

	// 	} else {
	// 		var newurlstr = urlstr.replace(urlstr[1], "");
	// 		document.URL = newurlstr;
	// 	}
	// } catch (err) {
	// 	console.log("we have error thart remove token from url bar ");
	// }


	/* ajax jquery include html */
	loadBackground();
	//	$(".ajax-aside-nav").load(concatHref("../../shigen/layout/ajax-aside-nav.html"));
	//	$(".ajax-section-main").load(concatHref("../../shigen/layout/ajax-section-main.html"));

	/* ajax jquery include html */
	$(".ajax-aside-nav").load(concatHref("../../shigen/layout/ajax-aside-nav.html"));
	$(".ajax-section-main").load(concatHref("../../shigen/layout/ajax-section-main.html"));
	$(".ajax-aside-navFIRE").load(concatHref("../../shigen/layout/ajax-aside-navFIRE.html"));
	$(".ajax-aside-navSYS").load(concatHref("../../shigen/layout/ajax-aside-navSYS.html"));

	recycleDo(0);
	recycleProgressFunc(checkLoginTimeAndAuth, 1 * 60 / 3 * 1000, false); // 每半鐘檢查登入時間，如果超過50分鐘，使用refresh token重新取得access token
	//recycleProgressFunc(checkToken, 10 * 60 * 1000, false);// 每10分鐘重新確認token
	// fetchPreviousPageJson();
	cleanPrevInInit();
});

// ...
var isuix = false; // uix模式切換
var previouskey = "_prev";
// prevJson = null;
// prevJosnArray = [];

function logout() {
	localStorage.clear();
	sessionStorage.clear();
}

/**
 *
 * @returns
 */
function cleanPrev() {
	sessionStorage.removeItem(previouskey);
}


function loadBackground() {
	var pageBgLocal = localStorage.getItem("pageBg") || "colorful";
	pageBgLocal = `../../shigen/css/type-${pageBgLocal}.css`;

	//	if(checkIsNullSpace(document.getElementById("active-stylesheet")) == true){
	//		alert("取得個人佈景資料發生錯誤");
	//		location.href = "./login";
	//		return;
	//	}

	if (checkIsNullSpace(pageBgLocal) == true) {
		document.getElementById("active-stylesheet").setAttribute('href', "colorful"); //如果有問題則預設為colorful
		return;
	}

	//	document.getElementById("active-stylesheet").setAttribute('href', concatHref(pageBgLocal));
}

/**
 *
 * @returns
 */
function cleanPrevInInit() {
	var url = location.href;
	var uArray = url.split("/");
	var funcNm = uArray[uArray.length - 1];

	var funcNm = url.substring(url.lastIndexOf("/") + 1);
	if (funcNm == "login" || funcNm == "loading" || funcNm == "index") {
		cleanPrev();
	}
}

/**
 * 取得locationHrefKeepData(...)所保留的資料
 *
 * @returns
 */
function locationHrefKeepDataFetch() {
	var sto = sessionStorage.getItem(previouskey);

	if (checkIsNullSpace(sto) == true) {
		console.log("no prev data");
		return;
	}

	var stojson = JSON.parse(sto);
	console.log("data:" + sto);
	var ks = Object.keys(stojson);

	if (ks.legnth == 0) {
		console.log("no prev data size is 0");
		return;
	}

	if (ks.length > 1) {
		console.log("no prev data size is bigger then 1");
		sessionStorage.removeItem(previouskey); // 取走即刪除資料
		return stojson[ks[0]]; // TODO 待處理多筆的
	}

	// size == 1
	sessionStorage.removeItem(previouskey); // 取走即刪除資料
	return stojson[ks[0]];
}

/*
 *
 */
function locationHref(url) {
	if (isuix == false) {
		location.href = url + "?" + "token=" + fetchToken();
	} else {
		location.href = url + ".html";
	}
};

//function windowOpen(url) {
//	if (isuix == true) {
//		url = url + ".html";
//	} else {
//		url = url + "?" + "token=" + fetchToken();
//	}
//	var childWin = window.open(url, 'newwindow', config = 'height=900,width=1000,toolbar=no');
//	return childWin;
//};

/**
 * 蒐集html內標籤資料(標籤內必須存在name屬性，無則略過該資料)
 *
 * @returns
 */
function sortOutAllFiled() {
	var res = {};

	// 處理輸入框
	var fields = $("html")
		.find(
			"input[type='email'],input[type='hidden'],input[type='number'],input[type='tel'],input[type='text'],input[type='time'],input[type='url'],input[type='date'],input[type='month'],input[type='week'],select,textarea");

	for (var i = 0; i < fields.length; i++) {
		// console.log(ins.eq(i)[0].outerHTML);
		var o = fields.eq(i);
		var nm = o.attr("name");

		if (checkIsNullSpace(nm) == true) {
			continue;
		}

		res[nm + ""] = o.val() + "";
	}

	// 處理radio button
	var fields2 = $("html").find("input[type='radio']:checked,input[type='checkbox']:checked");

	for (var i = 0; i < fields2.length; i++) {
		// console.log(ins.eq(i)[0].outerHTML);
		var o = fields2.eq(i);
		var nm = o.attr("name");

		if (checkIsNullSpace(nm) == true) {
			continue;
		}

		res[nm + ""] = o.val() + "";
	}

	return res;
}

/**
 *
 * @param funcUrl
 * @param tmpkey
 *            此function會自動產生key，大多時候放空值或null即可
 * @param tmpjson
 *            可自行帶入JSON格式資料，帶入須自訂變數或方法。null或空字串:自動蒐集畫面input等輸入框資料
 * @returns
 */
function locationHrefKeepData(funcUrl, tmpkey, tmpjson) {
	//	debugger
	if (isuix == true) {
		location.href = funcUrl + ".html";
		return;
	}

	var data = null;

	if (checkIsNullSpace(tmpjson) == true) {
		data = sortOutAllFiled();
	} else {
		data = tmpjson;
	}

	// 設定儲存的資料
	var k = tmpkey;

	if (checkIsNullSpace(k) == true) {
		var t = fetchToken();
		k = t.substring(t.length - 10, t.length);
	}

	var sto = {};
	sto[k] = data;

	sessionStorage.setItem(previouskey, JSON.stringify(sto));
	locationHref(funcUrl);
};

///**
// * 
// * @param funcUrl
// * @param tmpkey
// *            此function會自動產生key，大多時候放空值或null即可
// * @param tmpjson
// *            可自行帶入JSON格式資料，帶入須自訂變數或方法。null或空字串:自動蒐集畫面input等輸入框資料
// * @returns
// */
//function windowOpenKeepData(funcUrl, tmpkey, tmpjson) {
//	if (isuix == true) {
//		funcUrl = funcUrl + ".html";
//		window.open(funcUrl, 'newwindow', config = 'height=900,width=1000,toolbar=no');
//		return;
//	}
//
//	var data = null;
//
//	if (checkIsNullSpace(tmpjson) == true) {
//		data = sortOutAllFiled();
//	} else {
//		data = tmpjson;
//	}
//
//	// 設定儲存的資料
//	var k = tmpkey;
//
//	if (checkIsNullSpace(k) == true) {
//		var t = fetchToken();
//		k = t.substring(t.length - 10, t.length);
//	}
//
//	var sto = {};
//	sto[k] = data;
//
//	sessionStorage.setItem(previouskey, JSON.stringify(sto));
//	windowOpen(funcUrl);
//};

/**
 *
 * @param funcUrl
 * @param tmpkey
 *            此function會自動產生key，大多時候放空值或null即可
 * @param tmpjsonstr
 *            可自行帶入JSON格式的「字串」，帶入須自訂變數或方法。null或空字串:自動蒐集畫面input等輸入框資料
 * @returns
 */
function locationHrefKeepDataType2(funcUrl, tmpkey, tmpjsonstr) {
	if (isuix == true) {
		location.href = funcUrl + ".html";
		return;
	}

	var data = null;

	if (checkIsNullSpace(tmpjsonstr) == true) {
		data = sortOutAllFiled();
	} else {
		console.log(tmpjsonstr);
		data = JSON.parse(tmpjsonstr);
	}

	// 設定儲存的資料
	var k = tmpkey;

	if (checkIsNullSpace(k) == true) {
		var t = fetchToken();
		k = t.substring(t.length - 10, t.length);
	}

	var sto = {};
	sto[k] = data;

	sessionStorage.setItem(previouskey, JSON.stringify(sto));
	locationHref(funcUrl);
};

///**
// *
// * window.open
// * @param funcUrl
// * @param tmpkey
// * 				此function會自動產生key，大多時候放空值或null即可
// * @param tmpjson
// * 				可自行帶入JSON格式資料，帶入須自訂變數或方法。null或空字串:自動蒐集畫面input等輸入框資料
// * @param height
// * @param width
// * @returns
// */
//function locationHrefWindowKeepData(funcUrl, tmpkey, tmpjson, height, width) {
//	var config = 'height=' + height + ',width=' + width;
//	if (isuix == true) {
//	 	window.open(funcUrl + ".html", funcUrl, config);
//		return;
//	}
//
//	var data = null;
//
//	if (checkIsNullSpace(tmpjson) == true) {
//		data = sortOutAllFiled();
//	} else {
//		data = tmpjson;
//	}
//
//	// 設定儲存的資料
//	var k = tmpkey;
//
//	if (checkIsNullSpace(k) == true) {
//		var t = fetchToken();
//		k = t.substring(t.length - 10, t.length);
//	}
//
//	var sto = {};
//	sto[k] = data;
//
//	sessionStorage.setItem(previouskey, JSON.stringify(sto));
//	window.open(funcUrl + ".html?" + 'token=' + fetchToken(), funcUrl, config);
//};

///**
// * 開新分頁
// * @param funcUrl
// * @param tmpkey
// * 				此function會自動產生key，大多時候放空值或null即可
// * @param tmpjson
// * 				可自行帶入JSON格式資料，帶入須自訂變數或方法。null或空字串:自動蒐集畫面input等輸入框資料
// * @returns
// */
//function locationHrefWindowBlankKeepData(funcUrl, tmpkey, tmpjson) {
//	if (isuix == true) {
//		window.open(funcUrl + ".html", '_blank');
//		return;
//	}
//
//	var data = null;
//
//	if (checkIsNullSpace(tmpjson) == true) {
//		data = sortOutAllFiled();
//	} else {
//		data = tmpjson;
//	}
//
//	// 設定儲存的資料
//	var k = tmpkey;
//
//	if (checkIsNullSpace(k) == true) {
//		var t = fetchToken();
//		k = t.substring(t.length - 10, t.length);
//	}
//
//	var sto = {};
//	sto[k] = data;
//
//	sessionStorage.setItem(previouskey, JSON.stringify(sto));
//	window.open(funcUrl + ".html?" + 'token=' + fetchToken(), '_blank');
//};

/**
 *
 */

var checkToken = function() {
	var url = location.href;
	var uArray = url.split("/");

	if (uArray[uArray.length - 1] == "login") {
		return;
	}


	var res = ajaxPost(domain4Springboot(true) + "/authorization/checktoken", {
		"token": localStorage.getItem("token")
	}, null);

	if (res == null || res.status != "000") {
		location.href = "./login";
		localStorage.removeItem("token");
	} else {
		console.log("token is expired! donothing");
	}
}

/**
 * 確認登入時間，如超過將重新認證或重新登入
 */
var checkLoginTimeAndAuth = function() {
	//如果沒有login時間，則代表沒有登入過，返回即可
	if (checkIsNullSpace(localStorage.getItem("loginTime")) == true) {
		console.log("pls login");
		return;
	}

	//檢查時間
	//如超過50分鐘，使用refresh token重新取得access token，並重新設定localStorage的access token和登入時間
	var lt = localStorage.getItem("loginTime");
	// var timeUp = new Number(lt) + new Number(10 * 1000);//10s，僅供測試
	var timeUp = new Number(lt) + new Number(50 * 60 * 1000); //50m
	var nowTime = new Date().getTime();

	if (new Number(nowTime) < new Number(timeUp)) {
		//login ing，donothing
		console.log("access token in period");
		return;
	}

	//使用refresh token重新取得access token
	var res = ajaxPost(domain4Springboot(true) + "/authorization/authen/refresh", {
		"refreshToken": localStorage.getItem("refreshToken")
	}, null);

	// console.log(res);

	//使用refresh token重新取得access token
	var res = ajaxPost(domain4Springboot(true) + "/authorization/authen/refresh", {
		"refreshToken": localStorage.getItem("refreshToken")
	}, null);

	if (res != null && res.status == "000" && res.data.token != null) {
		console.log("old token expired, new token will setup to localStorage");
		// console.log("old token expired:" + localStorage.getItem("token"));
		//console.log("new token will setup to localStorage:" + res.data.token);
		localStorage.setItem("token", res.data.token);
		localStorage.setItem("loginTime", new Date().getTime());
		// console.log("new token expired:" + localStorage.getItem("token"));

		settingUi(); //重新設定相關連結
	} else {
		//refreshToken失效(預設為24小後失效)，重新登入
		console.log("refresh token is expired! pls re login");
		showLoginDialog();
	}
}

/**
 *
 * @param count
 * @returns
 */
function recycleDo(count) {
	if (count == 5) {
		console.log("setting_menu+header_bar_stop");
		return;
	}

	setTimeout(function() {
		//		console.log("+setting_menu+header_bar");
		settingUi();

		//移除無權限按鈕及欄位,程式碼於common.js
		removeBtnAndFields();

		return recycleDo(++count);
	}, 1000);
}

/**
 * 網址拼接
 * @param value url
 * @returns {string}
 */
function concatHref(value) {
	let div = document.createElement('div');
	div.innerText = value;
	return div.textContent;
}

/**
 * 設定表單和session main bar
 *
 * @returns
 */
function settingUi() {
	// 加入token至top bar連結中
	var as = $("a[class='top_item']");

	for (var i = 0; i < as.length; i++) {
		if (as.eq(i).attr("href").indexOf(".html") == -1) {
			continue;
		}

		var newHref = as.eq(i).attr("href").replace(".html", "");
		as.eq(i).attr("href", newHref + "?" + "token=" + fetchToken());
	}

	// 設定menu bar
	var token = localStorage.getItem("token") + "";

	var mas = $("#_menu_side").find("a[href]");

	for (var j = 0; j < mas.length; j++) {
		var ao = mas.eq(j);
		var h = ao.attr("href");

		// 排除多token的狀況
		if (h.indexOf("token=") > 0) {
			var posi = h.indexOf("?");
			var u = h.substring(posi + 1);
			var newh = h.substring(0, posi);
			var params = u.split("&");

			var paramStr = "";

			for (var i = 0; i < params.legnth; i++) {
				if (param[i].indexOf("token=") > 0) {
					continue;
				}

				paramStr += params[i] + "?";
			}

			paramStr = paramStr.substring(0, paramStr.lastIndexOf("?"));
			ao.attr("href", "" + newh + ("?" + "token=" + token));
			continue;
		}

		var newh = h.replace(".html", "");
		ao.attr("href", "" + newh + ("?" + "token=" + token));
		//		ao.attr("href", "#");
		//		 console.log(ao.html());
		//		console.log(ao);
	}

	// 設定index link
	//	$("#_menu_side_up").find("a").attr("href", "index?" + "token=" + localStorage.getItem("token"));
	$("#_menu_side_up").find("a").attr("href", "#"); //火再沒有index頁面

	//更改使用者名稱
	let displayUserId = localStorage.getItem("displayUserId");
	let usernameDiv = $(".top_item")[0]
	$(usernameDiv).children().text(displayUserId);
}

/**
 *
 */
var showfooContext =
	"<div class='modal fade' id='myselfDialog' data-backdrop='static' tabindex='-1' role='dialog'" +
	"aria-labelledby='staticBackdropLabel' aria-hidden='true' style='display:block;width:500px;height:300px;overflow-y:hidden;'>" +
	"<div class='modal-dialog' role='document'>" +
	"<div class='modal-content'>" +
	//title area
	"<div class='modal-header'>" +
	"<h5 class='modal-title' id='staticBackdropLabel'>" +
	"<h1><img src='../../shigen/images/general/nav-logo-red.svg' alt=''></h1>" +
	"</h5>" +
	//"<button id='_close_dialog' type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
	"<span aria-hidden='true'></span>" +
	"</button>" +
	"</div>" +
	//body area
	"<div class='modal-body content' id='foo'>" +
	"<div class='login-form' style='width:100%;'>" +
	"<div style='padding:10px;'>帳號已登入超過24小時，請重新登入</div>" +
	"<div></div>" +
	"<form action='./loading' method='post' id='action_form'>" +
	"<input name='token' id='token' style='display: none;'>" +
	"<div class='form-group'>" +
	"<input id='account' type='text' class='form-control placeholder' placeholder='帳號'>" +
	"</div>" +
	"<div class='form-group'>" +
	"<input id='pass' type='text' class='form-control' value='密碼'>" +
	"</div>" +
	"<button class='btn btn-login' type='button' onclick='doLoginDialog();'>登入</button>" +
	"<div style='padding:10px;'>註︰如無登入繼續使用，系統將無法繼續處理業務</div>" +
	"</form>" +
	"</div>" +
	"</div>" +
	"</div>" +
	"</div>";

/**
 *
 */
function showLoginDialog() {

	//設定臨時的js
	//找出頁面上最後一個js，append login.js
	var iteno = $("script").length;
	var target = $("script").eq(iteno - 1);
	target.append("<script type='text/javascript' src='../../shigen/js/app/login.js'></script>");

	//顯示dialog	
	$(document.body)[0].appendChild($(showfooContext)[0]);
	$('#myselfDialog').modal('show');

	//設定置中
	var x = window.innerWidth;
	var y = window.innerHeight;
	var positionX = x / 2 - 250;
	var positionY = y / 2 - 150;

	var styleCont = $("#myselfDialog").attr("style") + "position:fixed;position:fixed;top:" + positionY + "px;left:" + positionX + "px;";
	$("#myselfDialog").attr("style", styleCont);
}

/**
 *
 */
function doLoginDialog() {

	var res = doLogin(false); //執行login，注意!此function會自行跳錯誤視窗

	if (res == false) {
		alert("登入發生錯誤");

		//關閉所有dialog
		$('#myselfDialog').modal('hide');
		//只使用hide可能會發生後續無法繼續執行的問題，可能此機制非長駐的機制，故這邊將額外的程式碼一併刪除
		$("#myselfDialog").remove();
		$('.modal-backdrop').remove();

		//顯示dialog
		$(document.body)[0].appendChild($(showfooContext)[0]);
		$('#myselfDialog').modal('show');

		//設定置中
		var x = window.innerWidth;
		var y = window.innerHeight;
		var positionX = x / 2 - 250;
		var positionY = y / 2 - 150;

		var styleCont = $("#myselfDialog").attr("style") + "position:fixed;position:fixed;top:" + positionY + "px;left:" + positionX + "px;";
		$("#myselfDialog").attr("style", styleCont);

		return;
	}

	//刪除js
	console.log("js size - before:" + $("script").length);
	var iteno2 = $("script").length;
	$("script").eq(iteno2 - 1).remove();
	console.log("js size - after del:" + $("script").length);

	//關閉視窗
	$('#myselfDialog').modal('hide');
	//只使用hide可能會發生後續無法繼續執行的問題，可能此機制非長駐的機制，故這邊將額外的程式碼一併刪除
	$("#myselfDialog").remove();
	$('.modal-backdrop').remove();

	//在index時自動跳轉
	var caseUrl = getCaseUrl();
	var thisFunc = caseUrl.split("/")[1];

	if ("index" == thisFunc) {
		window.location = caseUrl + "?" + token + fetchToken();
	}

	settingUi(); //重新設定相關連結
}