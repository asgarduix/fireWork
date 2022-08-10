/**
 * 產生ddl共用元件
 * @param elementId (產生elment的目標div物件的id)
 * @param defaultVal
 *
 * 項目 :
 * 1. 「舊系統」DB取資料
 * 2. 「舊系統」無法從DB取資料
 * 3. 「宏燁」PG自訂
 *
 * 注意事項 :
 * 1. 請開發人員填上@author 與日期
 * 2. 有新增ddl，請通知IsaHuang，要更新文件
 */


/*
 * (4號) 是/否
 * @author JamesHsiao
 */
function crtDdlLoadDDLisdisk(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLisdisk", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (4號) 是/否
 * @author JamesHsiao
 */
function crtDdlLoadDDLisdiskType2(defaultVal) {

	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLisdisk", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		// alert(ajaxdataSub[i].code);//TODO 確認後，可刪
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (5號) 保單型式:電子保單/一般保單
 * @author JamesHsiao
 */
function crtDdlLoadDDLacttype(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLacttype", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (6號) 件數種類 :一般件/家庭件
 * @author JamesHsiao
 */
function crtDdlLoadDDLjiantyp(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLjiantyp", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (7號) 險別代號
 * @author JamesHsiao
 */
function crtDdlLoadDDLIINSCLS(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSCLS", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (7號) 險別代號
 * @author JamesHsiao
 */
function crtDdlLoadDDLIINSCLSType2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSCLS", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (8號) 保險菜單
 * @author JamesHsiao
 */
function crtDdlLoadDDLIINSKind(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSKind",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();
	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (9號) 保險菜單2
 * @author JamesHsiao
 */
function crtDdlLoadDDLIINSKind_501(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSKind_501", null,
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (9號) 保險菜單2
 * @author JamesHsiao
 */
function crtDdlLoadDDLIINSKind_501Type2(defaultVal, iinscls) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSKind_501CondIINSCLS", { "iinscls": iinscls },
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (10號) 條款
 * @author JamesHsiao
 */
function crtDdlLoadDDLIClause(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIClause",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (11號) 保單類別條款
 * @author JamesHsiao
 */
function crtDdlLoadDDLClauseRel(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLClauseRel",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (12號) 告知事項
 * @author JamesHsiao
 */
function crtDdlLoadDDLINotice(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLINotice",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (13號) 告知
 * @author JamesHsiao
 */
function crtDdlLoadDDLNoticeRel(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLNoticeRel",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (14號) 險種
 * @author JamesHsiao
 */
function crtDdlLoadDDLNCMNpInstype(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLNCMNpInstype", null,
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code3+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code3 });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (14號) 險種
 * @author JamesHsiao
 */
function crtDdlLoadDDLNCMNpInstypeType2(defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLNCMNpInstype", null,
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code3+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code3 });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (15號) 職業等級
 * @author JamesHsiao
 */
function crtDdlLoadDDLFocpyCls(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFocpyCls", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
//		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (15號) 職業等級
 * @author JamesHsiao
 */
function crtDdlLoadDDLFocpyClsType2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFocpyCls", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
//		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (16號) 性別
 * @author JamesHsiao
 */
function crtDdlLoadDDLFsex(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFsex", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (16號) 性別
 * @author JamesHsiao
 */
function crtDdlLoadDDLFsexType2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFsex", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (17號) 新續保件
 * @author JamesHsiao
 */
function crtDdlLoadDDLFrenew(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrenew", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (17號) 新續保件
 * @author JamesHsiao
 */
function crtDdlLoadDDLFrenewType2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrenew", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (18號) 體位別
 * @author JamesHsiao
 */
function crtDdlLoadDDLFrisk(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrisk", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (19號) 與要保人關係
 * @author JamesHsiao
 */
function crtDdlLoadDDLFrelIssu(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrelIssu", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (19號) 與要保人關係
 * @author JamesHsiao
 */
function crtDdlLoadDDLFrelIssuType2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrelIssu", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}


	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (20號) 受益人關係
 * @author JamesHsiao
 */
function crtDdlLoadDDLFrelBene(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrelBene", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (21號) 編號別
 * @author JamesHsiao
 */
function crtDdlLoadDDLCusCls(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCusCls", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (22號) 編號別
 * @author JamesHsiao
 */
function crtDdlLoadDDLCusType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCusType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (23號) 共保件
 * @author JamesHsiao
 */
function crtDdlLoadDDLFcoins(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFcoins", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (24號) 臨分件
 */
function crtDdlLoadDDLFfac(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFfac", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (25號) 旅行地區
 */
function crtDdlLoadDDLFtrvarea(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFtrvarea", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (26號) 國藉別
 * @author JamesHsiao
 */
function crtDdlLoadDDLFnation(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFnation", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (27號) 體檢
 * @author JamesHsiao
 */
function crtDdlLoadDDLFmedical(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFmedical", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (28號) 標準
 * @author JamesHsiao
 */
function crtDdlLoadDDLFbody(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFbody", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (29號) 有/無社會保險補助
 * @author JamesHsiao
 */
function crtDdlLoadDDLFsocal(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFsocal", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (30號) 共保公司
 * @author JamesHsiao
 */
function crtDdlLoadDDLIcoComp(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIcoComp", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (30號) 共保公司
 * @author JamesHsiao
 */
function crtDdlLoadDDLIcoComp2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIcoComp", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}
	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (31號) 保額單位
 * @author JamesHsiao
 */
function crtDdlLoadDDLFunit(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFunit", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (31號) 保額單位
 * @author JamesHsiao
 */
function crtDdlLoadDDLFunitType2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFunit", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (32號) 限數單位
 * @author JamesHsiao
 */
function crtDdlLoadDDLFqday(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFqday", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (32號) 限數單位
 * @author JamesHsiao
 */
function crtDdlLoadDDLFqdayType2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFqday", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * (33號) 核保碼
 * @author JamesHsiao
 */
function crtDdlLoadDDLFunderWriting(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFunderWriting", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	// 第一個放空白
	apidata.push({ text: "", value: "" });
	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (34號) 受益人給付方式
 * @author JamesHsiao
 */
function crtDdlLoadDDLFbeneType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFbeneType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (35號) 交通工具
 * @author JamesHsiao
 */
function crtDdlLoadDDLItrvConv(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLItrvConv", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (36號) 給付方式
 * @author JamesHsiao
 */
function crtDdlLoadDDLFpayMethod(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFpayMethod", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (37號) 給付方式
 * @author JamesHsiao
 */
function crtDdlLoadDDLFbenMethod(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFbenMethod", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (38號) 五大通路
 * @author JamesHsiao
 */
function crtDdlLoadDDLChannel(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLChannel", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (40-1號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataC_IocpyaAsCode(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataC_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (40-2號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataC_Iocpya1AsCode(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataC_Iocpya1AsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (40-3號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataC_Iocpya1AsType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataC_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (41-1號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataP_IocpyaAsCode(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataP_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (41-2號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataP_Iocpya1AsCode(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataP_Iocpya1AsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (41-3號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataP_Iocpya1AsType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataP_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (42-1號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataPVer_IocpyaAsCode(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataPVer_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (42-2號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataPVer_IocpyaAsType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataPVer_IocpyaAsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (42-3號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLOcpyDataPVer_Iocpya1AsType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataPVer_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (43-1號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLCommonData_IocpyaAsCode(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (43-2號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLCommonData_IocpyaAsType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData_IocpyaAsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (43-3號) 無法得知
 * @author JamesHsiao
 */
function crtDdlLoadDDLCommonData02_Iocpya1AsType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData02_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * (45號) 主檔批由
 * @author JamesHsiao
 */
function crtDdlLoadDDLMainEdrType_G(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLMainEdrType_G", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (46號) 主檔批由
 * @author JamesHsiao
 */
function crtDdlLoadDDLMainEdrType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLMainEdrType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (47號) 標的批由
 * @author JamesHsiao
 */
function crtDdlLoadDDLObjEdrType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLObjEdrType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (48號) 標的批由
 * @author JamesHsiao
 */
function crtDdlLoadDDLBENEEdrType(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLBENEEdrType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (49號) 子公司共銷
 * @author JamesHsiao
 */
function crtDdlLoadDDLSCCoSaleData(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLSCCoSaleData", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (50號) 身心障礙類別
 * @author JamesHsiao
 */
function crtDdlLoadDDLbarrkind(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLbarrkind", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (51號) 身心障礙等級
 * @author JamesHsiao
 */
function crtDdlLoadDDLbarrdegr(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLbarrdegr", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * (52號) 身心障礙 非體況未承保因數
 * @author JamesHsiao
 */
function crtDdlLoadDDLbarrkind_n(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLbarrkind_n", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();
	// 第一個放空白
	apidata.push({ text: "", value: "" });
	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*
 * 身心障礙 體況未承保因數
 * @author Steven Tsai
 */
function crtDdlLoadDDLbarrkind_body(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLbarrkind_body", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();
	// 第一個放空白
	apidata.push({ text: "", value: "" });
	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}

/*************************************************/
/********** 以下是針對無法從資料庫 抓取的下拉式選單  **********/

/*
 * (54號) 保單類別
 * @author JamesHsiao
 */
function ddlRptType(id){
	var elementId = "#"+id
	var optionParam =
		'<option value="1">1:一般保單</option>'+
		'<option value="2">2:最新保單(日結後)</option>'+
		'<option value="3">3:單底</option>'+
		'<option value="4">4:最新保單(日結前)</option>'+
		'<option value="5">5:核對(核保人員使用)</option>'
		$(elementId).append(optionParam);
}

/*
 * (55號) 保單類別 延伸
 * @author JamesHsiao
 */
function ddliinskind(id){
	var elementId = "#"+id
	var optionParam =
		'<option selected="selected" value="GH0">GH0&nbsp;日額甲型</option>'+
		'<option value="GM0">GM0&nbsp;個人組合</option>'+
		'<option value="GD0">GD0&nbsp;重大疾病</option>'+
		'<option value="GC0">GC0&nbsp;初次罹癌</option>'+
		'<option value="GI0">GI0&nbsp;癌症住院</option>'+
		'<option value="MH0">MH0&nbsp;團體健康</option>'+
		'<option value="MM0">MM0&nbsp;團體組合</option>'+
		'<option value="MH1">MH1&nbsp;團體健康(甲型)</option>'+
		'<option value="MM1">MM1&nbsp;團體組合(甲型)</option>'+
		'<option value="DH0">DH0&nbsp;重大傷病</option>'+
		'<option value="MR0">MR0&nbsp;團體住院醫療(實支實付型)</option>'+
		'<option value="GC1">GC1&nbsp;團體癌症</option>'+
		'<option value="GC2">GC2&nbsp;團體癌症身故</option>'+
		'<option value="MH2">MH2&nbsp;住院日額醫療</option>'+
		'<option value="GC3">GC3&nbsp;初次罹癌(台灣中油)</option>'+
		'<option value="GC4">GC4&nbsp;團體癌症(台灣中油)</option>'+
		'<option value="MH3">MH3&nbsp;團體住院醫療日額(台灣中油)</option>'+
		'<option value="MR1">MR1&nbsp;團體住院醫療限額(台灣中油)</option>'+
		'<option value="MR2">MR2&nbsp;團體醫療(實支實付型)</option>'

		$(elementId).append(optionParam);
}

/*
 * (56號) 列印類別
 * @author JamesHsiao
 */
function ddlFormType(id){
	var elementId = "#"+id
	var optionParam =
		'<option selected="selected" value="1">個人件保單</option>'+
		'<option value="2">團體保單</option>'+
		'<option value="4">承保範圍</option>'+
		'<option value="7">收據-新</option>'+
		'<option value="8">名冊</option>'+
		'<option value="10">共保特約條款</option>'
		$(elementId).append(optionParam);
}

/*
 * (57號) 收據版本
 * @author JamesHsiao
 */
function ddlPrintType(id){
	var elementId = "#"+id
	var optionParam =
		'<option Value="1" Selected="True">新版</option>'+
		'<option Value="2">舊版(加註)</option>'
		$(elementId).append(optionParam);
}

/*
 * (58號) 身故受益人
 * @author JamesHsiao
 */
function rdo_1(id){
	var elementId = "#"+id
	var optionParam =
		'<option Value="Y" Selected="True">顯示</option>'+
		'<option Value="N">不顯示</option>'
		$(elementId).append(optionParam);
}

/*
 * (59號) 保費顯示
 * @author JamesHsiao
 */
function rdo_2(id){
	var elementId = "#"+id
	var optionParam =
		'<option Value="Y" Selected="True">顯示</option>'+
		'<option Value="N">不顯示</option>'
			$(elementId).append(optionParam);
}

/*
 * (60號) 依部門別跳頁
 * @author JamesHsiao
 */
function rdo_3(id){
	var elementId = "#"+id
	var optionParam =
		'<option Value="Y" Selected="True">跳頁</option>'+
		'<option Value="N">不跳頁</option>'
			$(elementId).append(optionParam);
}

/*
 * (61號) 輸出類別
 * @author JamesHsiao
 */
function rdo_5(id){
	var elementId = "#"+id
	var optionParam =
		'<option Value="pdf" Selected="True">PDF</option>'+
		'<option Value="xls">EXCEL</option>'
			$(elementId).append(optionParam);
}

/*
 * (62號) 是否隱藏被保險人ID
 * @author JamesHsiao
 */
function rdo_6(id){
	var elementId = "#"+id
	var optionParam =
		'<option Value="Y" Selected="True">隱藏</option>'+
		'<option Value="N">不隱藏</option>'
			$(elementId).append(optionParam);
}

/*
 * ( 63號) 是否隱藏被保險人出生年月日
 * @author JamesHsiao
 */
function rdo_7(id){
	var elementId = "#"+id
	var optionParam =
		'<option Value="Y" Selected="True">隱藏</option>'+
		'<option Value="N">不隱藏</option>'
			$(elementId).append(optionParam);
}

/*
 * (64號) 保額計算方式
 * @author JamesHsiao
 */
function insuredCalculation (id){
	var elementId = "#"+id
	var optionParam =
		'<option selected="selected" value="1">1:固定保額</option>'+
		'<option selected="selected" value="2">2:變動保額</option>'+
		'<option selected="selected" value="3">3:薪資倍數</option>'
			$(elementId).append(optionParam);
}

/*
 * (64號) 保額計算方式
 * @author JamesHsiao
 */
function insuredCalculationType2 (){
	var arr = [{code:'1', text:'1:固定保額'}, {code:'2', text:'2:變動保額'}, {code:'3', text:'3:薪資倍數'}];
	var select = document.createElement("SELECT");
	for (var i = 0; i < arr.length; i++) {
	  var op = document.createElement("option");
	  op.setAttribute("value", arr[i].code);
	  var text = document.createTextNode(arr[i].text);
	  op.appendChild(text);
	  select.appendChild(op);
	}
	return select;
}

/*
 * (65號) 續保類型
 * @author JamesHsiao
 */
function ddlAUTOTYPE(id){
	var elementId = "#"+id
	var optionParam =
		'<option selected="selected" value="N">1. 一般續保</option>'+
		'<option value="Y">2. 自動續保</option>'+
		'<option value="">3. 全部</option>'
			$(elementId).append(optionParam);
}

/*
 * (66號) 續保狀態
 * @author JamesHsiao
 */
function ddlQINXTPLY(id){
	var elementId = "#"+id
	var optionParam =
		'<option value="">全部</option>'+
		'<option value="Y">已續保</option>'+
		'<option value="N">未續保</option>'+
		'<option value="Y1">已續保已出單</option>'+
		'<option value="Y2">已續保未出單</option>'
			$(elementId).append(optionParam);
}

/*
 * (67號) 列印類別2
 * @author JamesHsiao
 */
function ddlFormType2(id){
	var elementId = "#"+id
	var optionParam =
		' <option selected="selected" value="7">7: 收據(新)</option>'+
		' <option value="9">9: 核保照會單</option>'+
		' <option value="11">11: 除外同意書</option>'

			$(elementId).append(optionParam);
}

/*
 * (68號) 報表類型2 //value值還不知道 後續再補入
 * @author JamesHsiao
 */
function ddlPrintType2(id){
	var elementId = "#"+id
	var optionParam =
		'<option selected="selected" value="1">核保照會單</option>'+
		'<option value="2">除外同意書</option>'+
		'<option value="3">扣款失敗照會單</option>'
			$(elementId).append(optionParam);
}

/*
 * 處理狀況
 * @author StevenTsai
 */
function ddlAttendance (){
	var arr = [{code:'0', text:'0:未處理'}, {code:'Y', text:'Y:已處理'}, {code:'N', text:'N:無須處理'}];
	var select = document.createElement("SELECT");
	for (var i = 0; i < arr.length; i++) {
	  var op = document.createElement("option");
	  op.setAttribute("value", arr[i].code);
	  var text = document.createTextNode(arr[i].text);
	  op.appendChild(text);
	  select.appendChild(op);
	}
	return select;
}

/*
 * 給付方式 (1:均分, 2:順位, 3:比例)
 * 移除 2:順位
 * @author StevenTsai
 */
function ddlFbenmethod (){
	var arr = [{code:'1', text:'1:均分'}, {code:'3', text:'3:比例'}];
	var select = document.createElement("SELECT");
	for (var i = 0; i < arr.length; i++) {
	  var op = document.createElement("option");
	  op.setAttribute("value", arr[i].code);
	  var text = document.createTextNode(arr[i].text);
	  op.appendChild(text);
	  select.appendChild(op);
	}
	return select;
}

/********** 以上是針對無法從資料庫 抓取的下拉式選單  **********/
/*************************************************/


/*************************************************/
/******************* 以下是PG自訂 *******************/

/*
 * 參數個數
 */
function ddlParamNum(id){
	var elementId = "#"+id
	var optionParam =
		'<option value="1">1</option>'+
		'<option value="2">2</option>'
		$(elementId).append(optionParam);
}

/*
 * 洽攬人種類
 * @author IsaHuang
 */
function crtDdlLoadDDLSubsetclass(defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "1-真保代", value: "1" });
	apidata.push({ text: "2-專屬保代", value: "2" });
	apidata.push({ text: "3-手續費", value: "3" });
	apidata.push({ text: "5-業務員", value: "5" });

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * 佣金一/佣金二
 * @author IsaHuang
 */
function crtDdlLoadDDLSource(defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "A-佣一", value: "A" });
	apidata.push({ text: "C-佣二", value: "C" });

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/* 董監事名冊_符合恐怖主義
 * @author MikeWang
 */
function crtDdlLoadDDLMkterr(defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "Y", value: "Y" });
	apidata.push({ text: "N", value: "N" });

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * 董監事名冊_符合政治人物
 * @author MikeWang
 */
function crtDdlLoadDDLMkpoli(defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "Y", value: "Y" });
	apidata.push({ text: "N", value: "N" });

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * 董監事名冊_符合函轉名單
 * @author MikeWang
 */
function crtDdlLoadDDLMkgrup(defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "Y", value: "Y" });
	apidata.push({ text: "N", value: "N" });

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * 與被保人關係
 * @author IsaHuang
 */
function crtDdlLoadDDLReleation(elementId, defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "1 本人", value: "1" });
	apidata.push({ text: "2 配偶", value: "2" });
	apidata.push({ text: "3 子女", value: "3" });
	apidata.push({ text: "4 父母", value: "4" });
	apidata.push({ text: "5 兄弟姊妹", value: "5" });
	apidata.push({ text: "6 其他", value: "6" });

	createDdl(elementId, apidata, defaultVal);
}

/*
 * 信用卡類別
 * @author IsaHuang
 */
function crtDdlLoadDDLNrmk(elementId, defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "", value: "" });
	apidata.push({ text: "1 VISA", value: "1" });
	apidata.push({ text: "2 MASTER", value: "2" });
	apidata.push({ text: "3 JCB", value: "3" });
	apidata.push({ text: "4 U-CARD", value: "4" });

	createDdl(elementId, apidata, defaultVal);
}

/*
 * 持卡人
 * @author IsaHuang
 */
function crtDdlLoadDDLNrmk3(elementId, defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "", value: "" });
	apidata.push({ text: "1 要保人", value: "1" });
	apidata.push({ text: "2 被保人", value: "2" });
	apidata.push({ text: "3 其他", value: "3" });

	createDdl(elementId, apidata, defaultVal);
}

/*
 * 自訂收據
 * @author IsaHuang
 */
function crtDdlLoadDDLFcollect(elementId, defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "N:一般", value: "N" });
	apidata.push({ text: "Y:自訂收據", value: "Y" });

	createDdl(elementId, apidata, defaultVal);
}

//條款編號
function ClauseNumber(){
	var arr = [{code:'ID5', text:'ID5 雇主意外責任保險附加員工及眷屬團體傷'}, {code:'911A', text:'911A 傷害保險恐怖主義行為保險限額給付加條館'}, {code:'KD1', text:'KD1 懲罰性賠償金除外條款'}];
	var select = document.createElement("SELECT");
	for (var i = 0; i < arr.length; i++) {
	  var op = document.createElement("option");
	  op.setAttribute("value", arr[i].code);
	  var text = document.createTextNode(arr[i].text);
	  op.appendChild(text);
	  select.appendChild(op);
	}
	return select;
}

//批改共保註記
function CoInsuranceNote(){
	var arr = [{code:'1', text:'1:非共保'}, {code:'2', text:'2:共保'}];
	var select = document.createElement("SELECT");
	for (var i = 0; i < arr.length; i++) {
	  var op = document.createElement("option");
	  op.setAttribute("value", arr[i].code);
	  var text = document.createTextNode(arr[i].text);
	  op.appendChild(text);
	  select.appendChild(op);
	}
	return select;
}

//承保共保註記
function UnCoInsuranceNote(npubtPeMainFcoins){
	var arr = [{code:'1', text:'1:非共保'}, {code:'2', text:'2:共保主出單'}, {code:'3', text:'3:非共保主出單'}];
	var select = document.createElement("SELECT");
	select.setAttribute("id", "bdfbfd");
	for (var i = 0; i < arr.length; i++) {
	  var op = document.createElement("option");
		  op.setAttribute("value", arr[i].code);
	  var text = document.createTextNode(arr[i].text);
	  op.appendChild(text);
	  select.appendChild(op);
	}
	// 外共  只要 共保主出單
	if(npubtPeMainFcoins =="3"){
		select.removeChild(select.childNodes[0]);
		select.removeChild(select.childNodes[1]);
	}
	// 非共保 只要  非共保
	if(npubtPeMainFcoins =="4"){
		select.removeChild(select.childNodes[1]);
		select.removeChild(select.childNodes[1]);
	}
	return select;
}

/*
 * 疾病代碼
 * @author StevenTsia
 */
function crtDdlLoadDDLKDisease(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLKDisease",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
//		apidata.push({ text: ajaxdataSub[i].kdisease+": "+ajaxdataSub[i].ndiseaseC, value: ajaxdataSub[i].kdisease });
		apidata.push({ text: ajaxdataSub[i].kdisease, value: ajaxdataSub[i].kdisease });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * 國籍代碼
 * @author StevenTsia
 */
function crtDdlLoadDDLCountry2(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCountry",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].cntcod.trim() + ": " + ajaxdataSub[i].cntcodname.trim(), value: ajaxdataSub[i].cntcod.trim() });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * 國籍代號及名稱
 * @author MikeWang 2021/06/21
 */
function crtDdlLoadDDLCountry(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCountry",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].cntcod.trim() + ": " + ajaxdataSub[i].cntcodname.trim(), value: ajaxdataSub[i].cntcod.trim() });
	}

	createDdl(elementId, apidata, defaultVal);
}

/*
 * 分公司代號
 * @author MikeWang 2021/06/23
 */
function crtDdlLoadDDLBranch(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLBranch",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].deptwn.trim() + ": " + ajaxdataSub[i].deptna.trim(), value: ajaxdataSub[i].deptwn.trim() });
	}

	createDdl(elementId, apidata, defaultVal);
}

//險別代號
function crtDdlLoadDDLIINSKind_501Type2Val() {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSKind_501", null,
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	return apidata;
}

/**
 * 設定保單類別下拉選單
 * @param elementId
 * @param param {"iinscls" : iinscls}
 * @author CarlosWu 2021/05/11
 */
function setIinskindDdl(elementId, param){

	let inskindRes = ajaxPostTokenReady("../../LoadDDLController/queryIinskindDdl", param, true);
	$('#' + elementId).children().remove();
	createDdl(elementId, inskindRes.data, null);

}

/**
 * 設定險種下拉選單
 * @param elementId
 * @param param {"iinscls" : iinscls, "iinskind" : iinskind}
 * @author CarlosWu 2021/05/11
 */
function setIinstypeDdl(elementId, param){

	let instypeRes = ajaxPostTokenReady("../../LoadDDLController/queryInstypeDdl", param, true);
	$('#' + elementId).children().remove();
	createDdl(elementId, instypeRes.data, null);

}

/*
 * 地區代碼
 * @author yiting
 */
function crtDdlLoadDDLArea(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLArea",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].deptno.trim() + ": " + ajaxdataSub[i].deptni.trim(), value: ajaxdataSub[i].deptno.trim() });
	}
	createDdl(elementId, apidata, defaultVal);
}

/*
 * 照會代碼
 * @author StevenTsai
 */
function crtDdlLoadDDLHasmNoteCode(defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLHasmNoteCode",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].notecode.trim(), value: ajaxdataSub[i].notecode.trim() });
	}

	var sel = crtDdlType2(apidata);
	sel.value = defaultVal;
	return sel;
}

/*
 * 核保照會-取得被保險人ID
 * @author StevenTsai
 */
function crtDdlGetCName(iobjseqArr, mainOid) {
	var param = {
			"iobjseqArr":iobjseqArr,
			"mainOid":mainOid
	};
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLGetCName",param , true);
	// select IISSU, OID_PUBT_PE_OBJ, OID_PUBT_PE_MAIN, IOBJSEQ, IOBJSUBSEQ 
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	// 預設空白選項
	apidata.push({ text: '', value: '' })
	for (var i = 0; i < ajaxdataSub.length; i++) {
//		apidata.push({ text: ajaxdataSub[i].iissu.trim(), value: ajaxdataSub[i].iobjseq });
		apidata.push({ text: ajaxdataSub[i].iissu.trim(), value: ajaxdataSub[i].iissu.trim() });
	}
	var sel = crtDdlType2(apidata);
//	sel.value = defaultVal;
	return sel;
}

/*
 * 核保照會-取得被保險人序號
 * @author StevenTsai
 */
function crtDdlLoadDDLIobjseqIobjsubseq(mainOid) {
	var param = {
			"mainOid":mainOid
	};
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIobjseqIobjsubseq",param , false);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].iobjseq+"-"+ajaxdataSub[i].iobjsubseq, value: ajaxdataSub[i].iobjseq+"-"+ajaxdataSub[i].iobjsubseq });
	}
	var sel = crtDdlType2(apidata);
	return sel;
}

/*
 * 核保照會-取得被保險人序號2
 * @author StevenTsai
 */
function crtDdlLoadDDLIobjseqIobjsubseq2(elementId, mainOid) {
	var param = {
			"mainOid":mainOid
	};
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIobjseqIobjsubseq",param , false);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();
	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].iissu, value: ajaxdataSub[i].iobjseq+"-"+ajaxdataSub[i].iobjsubseq });
	}
	createDdl(elementId, apidata, null);
}


/*
 * 保費計算方式
 * @author yiting
 */
function crtDdlLoadDDLNrmk1(elementId, defaultVal) {

	let apidata = new Array();

	apidata.push({ text: "0:依短期費率", value: "0" });
	apidata.push({ text: "1:按日數計算", value: "1" });
	apidata.push({ text: "2:按月數計算", value: "2" });
	apidata.push({ text: "3:按日計算/30天", value: "3" });
	apidata.push({ text: "5:案年計算", value: "5" });
	apidata.push({ text: "6:不計費 ", value: "6" });

	createDdl(elementId, apidata, defaultVal);
}


/******************* 以上是PG自訂 *******************/
/*************************************************/


//不知是什麼 ?

function crtDdlLoadDDLIINSCLSType2Val(){
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSCLS", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}
	return apidata;
}

function crtDdlLoadDDLIINSKind_501Type2ValDep(defaultVal, iinscls) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSKind_501CondIINSCLS", { "iinscls": iinscls },
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	return apidata;
}

function crtDdlLoadDDLNCMNpInstypeType2Val(){
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLNCMNpInstype", null,
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code3+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code3 });
	}

	return apidata;
}

// ddl core function
const createDdl = (locationId, choices, defaultVal) => {
	let selectBox = document.createElement("select");
	let newOption;

	for (let i = 0; i < choices.length; i++) {
		// newOption = new Option(`${choices[i]}`, `${choices[i]}`);
		newOption = new Option(`${choices[i].text}`, `${choices[i].value}`);
		selectBox.add(newOption, undefined);
	}

	let element = document.getElementById(locationId);

	element.appendChild(selectBox);

	// 予許空白資料
	try {
		if (defaultVal != undefined && defaultVal != "undefined" && defaultVal != null) {
			selectBox.value = defaultVal;
			// document.getElementById("div1").getElementsByTagName("select")[0].value
			// = "N";
		}
	} catch (err) {
		console.log("el-id:" + locationId + " set default value have serious error");
	}
};

// func for createDropDown
function crtDdlDemo(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData02_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);

}

// func for createDropDown with EventFunc
function crtDdlDemoWithEventFunc(elementId, nextElementId, eventFunc, defaultVal) {
	// 取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData02_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].code+": "+ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);

	if (nextElementId != null && eventFunc != null) {
		var createdSelect = document.getElementById(elementId).getElementsByTagName("select")[0];
		createdSelect.addEventListener("change", function (event) {
			eventFunc(event.target.value, nextElementId);
		})
	}

}

//func for createDropDown with EventFunc
function crtDdlDemoWithEventFuncType2(selOfDivJson, nextSelJson) {
	if (selOfDivJson.optsOfSel == null) {
		let selObj = document.createElement("select");
		let element = selOfDivJson.div;
		element.appendChild(selObj);
		return;
	}

	var selObj = crtDdlType2(selOfDivJson.optsOfSel);
	bindEvent4SelObj(selObj, selOfDivJson.event, nextSelJson);

	if (selOfDivJson.defaultVal != undefined && selOfDivJson.defaultVal != "undefined" && selOfDivJson.defaultVal != null) {
		selObj.value = selOfDivJson.defaultVal;
	}

	let element = selOfDivJson.div;
	element.appendChild(selObj);
}

const bindEvent4SelObj = (bindSelObj, eventFunc, nextSelJson) => {
	if (nextSelJson.div != null && eventFunc != null) {
		bindSelObj.addEventListener("change", function (event) {
			var obj = eventFunc(event.target.value, nextSelJson.div);
			obj.value = nextSelJson.defaultVal;
		})
	}
}

const bindEvent4SelObjType2 = (bindSelObj, eventFunc) => {
	if (eventFunc != null) {
		bindSelObj.addEventListener("change", function (event) {
			var obj = eventFunc(event.target.value);
		})
	}
}

// ddl core function type2
const crtDdlType2 = (choices) => {
	let selectBox = document.createElement("select");
	let newOption;

	if (choices == null || choices.length == 0) {
		return selectBox;
	}

	for (let i = 0; i < choices.length; i++) {
		// newOption = new Option(`${choices[i]}`, `${choices[i]}`);
		newOption = new Option(`${choices[i].text}`, `${choices[i].value}`);
		selectBox.add(newOption, undefined);
	}

	return selectBox;
};

// 自訂下拉選
function crtSelector(arr) {
	var select = document.createElement("SELECT");
	for (var i = 0; i < arr.length; i++) {
	  var op = document.createElement("option");
	  op.setAttribute("value", arr[i].code);
	  var text = document.createTextNode(arr[i].text);
	  op.appendChild(text);
	  select.appendChild(op);
	}
	return select;
}

// //////////////////////////////////////////////////////////////////////////////////////////////////////
// =====pop window code=====
// options => [{text: "", value: }, {text: "", value: }, ....]
// w => width of popup
// h => height of popup
function openSelectWindow(id, options, w, h) {
	var left = (window.innerWidth / 2) - (w / 2);
	var top = (window.innerHeight / 2.5) - (h / 2.5);
	newWindow = window.open("", null, `height=${h},width=${w},top=${top}, left=${left}, status=yes,toolbar=no,menubar=no,location=no`);
	newWindow.document.write(`<select onchange='window.opener.setValue("${id}",this.value);'>`);
	for (let i = 0; i < options.length; i++) {
		newWindow.document.write("<option value='" + options[i].value + "'>");
		newWindow.document.write(options[i].text);
		newWindow.document.write("</option>");
	}
	newWindow.document.write("</select>");
}

function openInputWindow(id, w, h) {
	var left = (window.innerWidth / 2) - (w / 2);
	var top = (window.innerHeight / 2.5) - (h / 2.5);
	newWindow = window.open("", null, `height=${h},width=${w},top=${top}, left=${left}, status=yes,toolbar=no,menubar=no,location=no`);
	newWindow.document.write(`<input onchange='window.opener.setValue("${id}", this.value);'/>`);
}

function setValue(id, value) {
	document.getElementById(id).value = value;
}

// 即此段程式碼: window.opener.document.getElementById("oriPageId").value = "value";
// oriPageId 為原頁的欄位id
// value為欲帶回的值
////////////////////////////////////////////////////////////////////////////////////////////////////////
