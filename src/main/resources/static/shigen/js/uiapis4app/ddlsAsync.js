//=====ddl code=====
//TOOD 優化-異步啟動
//LoadDDLisdisk
/**
 * 產生ddl元件-LoadDDlisdisk
 * 
 * @param {*}
 *            elementId 產生elment的目標div物件的id
 * @param {*}
 *            defaultVal
 */

// NcmnpCommoncode table
// 4號----是/否
async function crtDdlLoadDDLisdiskAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLisdisk", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}






// TOOD pls develop other function
// funtion 2
// ...

//5號  保單型式:電子保單/一般保單
async function crtDdlLoadDDLacttypeAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLacttype", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}



// 6號 ---件數種類--- 一般件/家庭件
async function crtDdlLoadDDLjiantypAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLjiantyp", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}



// 15號---職業等級 NCMNP_COMMONCODE：FOCPYCLS
async function crtDdlLoadDDLFocpyClsAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFocpyCls", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}






// 16號---性別 NCMNP_COMMONCODE：FSEX
async function crtDdlLoadDDLFsexAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFsex", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}


// 17號---新續保件 NCMNP_COMMONCODE：FRENEW
async function crtDdlLoadDDLFrenewAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrenew", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 18號---體位別 NCMNP_COMMONCODE：FRISK
async function crtDdlLoadDDLFriskAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrisk", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}



// 19號---與要保人關係 NCMNP_COMMONCODE：FRELISSU
async function crtDdlLoadDDLFrelIssuAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrelIssu", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}


// 20號---受益人關係 NCMNP_COMMONCODE：FRELBENE
async function crtDdlLoadDDLFrelBeneAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFrelBene", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 21號---編號別 NCMNP_COMMONCODE：CUSCLS
async function crtDdlLoadDDLCusClsAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCusCls", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 22號---編號別 NCMNP_COMMONCODE：CUSCLS
async function crtDdlLoadDDLCusTypeAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCusType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 23號---共保件 NCMNP_COMMONCODE：FCOINS
async function crtDdlLoadDDLFcoinsAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFcoins", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 24號---臨分件 NCMNP_COMMONCODE：FFAC
async function crtDdlLoadDDLFfacAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFfac", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 25號---旅行地區 NCMNP_COMMONCODE：FTRVAREA
async function crtDdlLoadDDLFtrvareaAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFtrvarea", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 26號---國藉別 NCMNP_COMMONCODE：FNATION
async function crtDdlLoadDDLFnationAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFnation", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 27號 --體檢
async function crtDdlLoadDDLFmedicalAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFmedical", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 28號 --標準
async function crtDdlLoadDDLFbodyAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFbody", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 29號  ---有/無社會保險補助
async function crtDdlLoadDDLFsocalAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFsocal", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 30號---共保公司 NCMNP_COMMONCODE：FCOCOMP
async function crtDdlLoadDDLIcoCompAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIcoComp", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 31號---保額單位 NCMNP_COMMONCODE：FUNIT
async function crtDdlLoadDDLFunitAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFunit", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 32號---限數單位 NCMNP_COMMONCODE：FQUNIT
async function crtDdlLoadDDLFqdayAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFqday", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 33號---核保碼 NCMNP_COMMONCODE：FUNDERWRITING
async function crtDdlLoadDDLFunderWritingAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFunderWriting", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 34號---受益人給付方式 NCMNP_COMMONCODE：FBENETYPE
async function crtDdlLoadDDLFbeneTypeAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFbeneType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 35號---交通工具 NCMNP_COMMONCODE：ITRVCONV
async function crtDdlLoadDDLItrvConvAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLItrvConv", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 36號---給付方式 NCMNP_COMMONCODE：FPAYMETHOD
async function crtDdlLoadDDLFpayMethodAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFpayMethod", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 37號---'給付方式 NCMNP_COMMONCODE：FBENMETHOD
async function crtDdlLoadDDLFbenMethodAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLFbenMethod", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 38號----五大通路
async function crtDdlLoadDDLChannelAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLChannel", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 45號---主檔批由
async function crtDdlLoadDDLMainEdrType_GAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLMainEdrType_G", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 46號----主檔批由
async function crtDdlLoadDDLMainEdrTypeAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLMainEdrType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 47號---標的批由
async function crtDdlLoadDDLObjEdrTypeAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLObjEdrType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 48號---標的批由
async function crtDdlLoadDDLBENEEdrTypeAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLBENEEdrType", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 49號---子公司共銷
async function crtDdlLoadDDLSCCoSaleDataAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLSCCoSaleData", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 50號----身心障礙類別
async function crtDdlLoadDDLbarrkindAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLbarrkind", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 51號 ---身心障礙等級
async function crtDdlLoadDDLbarrdegrAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLbarrdegr", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}
// 52號----身心障礙 非體況未承保因數
async function crtDdlLoadDDLbarrkind_nAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLbarrkind_n", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();
	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}


// //NcmnpInscls table
//// 7號---險別代號 from table:NCMNP_INSCLS
async function crtDdlLoadDDLIINSCLSAsync(elementId, defaultVal) {
	// 取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSCLS", null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();





	for (var i = 0; i < ajaxdataSub.length; i++) {


		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);// TODO 傳物件預設值參數，待開發
}




// //NcmnpInskind table
//8號  ---保險菜單
async function crtDdlLoadDDLIINSKindAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSKind",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();
	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}




//9號 ---保險菜單2
async function crtDdlLoadDDLIINSKind_501Async(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLIINSKind_501", null,
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
//
//
//NcmnpClause table
//10號----條款
async function crtDdlLoadDDLIClauseAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLIClause",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//11號----保單類別條款
async function crtDdlLoadDDLClauseRelAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLClauseRel",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}

//NhaspNotice table
//12號----告知事項
async function crtDdlLoadDDLINoticeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLINotice",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
//
//13號---告知 NHASP_NOTICE_REL
async function crtDdlLoadDDLNoticeRelAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLNoticeRel",
		null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}

// //NcmnpInstype table
//14號---險種 NCMNP_INSTYPE
async function crtDdlLoadDDLNCMNpInstypeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLNCMNpInstype", null,
			true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code3 });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
//
// //NhaspOcpy table
// //40-1號
async function crtDdlLoadDDLOcpyDataC_IocpyaAsCodeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataC_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
// //40-2號
async function crtDdlLoadDDLOcpyDataC_Iocpya1AsCodeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataC_Iocpya1AsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
//
//
// //40-3號
async function crtDdlLoadDDLOcpyDataC_Iocpya1AsTypeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataC_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
// //41-1號
async function crtDdlLoadDDLOcpyDataP_IocpyaAsCodeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataP_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}




// //41-2號
async function crtDdlLoadDDLOcpyDataP_Iocpya1AsCodeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataP_Iocpya1AsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
// //41-3號
async function crtDdlLoadDDLOcpyDataP_Iocpya1AsTypeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataP_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
// //42-1號
async function crtDdlLoadDDLOcpyDataPVer_IocpyaAsCodeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataPVer_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
// //42-2號
async function crtDdlLoadDDLOcpyDataPVer_IocpyaAsTypeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataPVer_IocpyaAsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
// //42-3號
async function crtDdlLoadDDLOcpyDataPVer_Iocpya1AsTypeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLOcpyDataPVer_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
//
// //43-1號
async function crtDdlLoadDDLCommonData_IocpyaAsCodeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData_IocpyaAsCode",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
// //43-2號
async function crtDdlLoadDDLCommonData_IocpyaAsTypeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData_IocpyaAsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
}
//
//
//
//
// //43-3號
async function crtDdlLoadDDLCommonData02_Iocpya1AsTypeAsync(elementId, defaultVal) {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData02_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let apidata = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		apidata.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	createDdl(elementId, apidata, defaultVal);//TODO 傳物件預設值參數，待開發
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
