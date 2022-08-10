const location1 = "div1";
const location2 = "div2";
const location3 = "div3";
const defaultVal1 = "defaultVal1";
const defaultVal2 = "defaultVal2";
const defaultVal3 = "defaultVal3";

function eventFunc1(val, nextSelectLocation) {
	var nextSelect = $(`#${nextSelectLocation}`).find("select")[0];
	nextSelect.innerHTML = '<option>--Choose one</option>';
	if (val === "21080010") {
		var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData_IocpyaAsType", null, true);
		var ajaxdataSub = ajaxdata.data;
		// let apidata = new Array();

		for (var i = 0; i < ajaxdataSub.length; i++) {
			nextSelect.appendChild(new Option(ajaxdataSub[i].text, ajaxdataSub[i].code))
		}

	} else {
		nextSelect.appendChild(new Option("option1", "option1"));
		nextSelect.appendChild(new Option("option2", "option2"));
		nextSelect.appendChild(new Option("option3", "option3"));
	}

};

function eventFunc2(val, nextSelectLocation) {
	var nextSelect = $(`#${nextSelectLocation}`).find("select")[0];
	nextSelect.innerHTML = '<option>--Choose one</option>';
	// if (val === ""){
	//	decide which api to call
	//}
	if (val === "option1") {
		nextSelect.appendChild(new Option("option1", "option1"));
	}
	if (val === "option2") {
		nextSelect.appendChild(new Option("option2", "option2"));
	}
	if (val === "option3") {
		nextSelect.appendChild(new Option("option3", "option3"));
	}
}

crtDdlDemoWithEventFunc(location1, location2, eventFunc1, defaultVal1);
crtDdlDemoWithEventFunc(location2, location3, eventFunc2, defaultVal2);
crtDdlDemoWithEventFunc(location3, null, null, defaultVal3);


//createDDlDemo(location3, defaultVal3);

/**
 * 
 * @param {*} val 
 * @param {*} divObj 
 */
function eventFunc1t2(val, divObj) {
	var nextSelect = $(divObj).find("select")[0];
	nextSelect.innerHTML = '<option>--Choose one</option>';
	if (val === "21080010") {
		var ajaxdata = ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData_IocpyaAsType", null, true);
		var ajaxdataSub = ajaxdata.data;
		// let apidata = new Array();

		for (var i = 0; i < ajaxdataSub.length; i++) {
			nextSelect.appendChild(new Option(ajaxdataSub[i].text, ajaxdataSub[i].code))
		}

	} else {
		nextSelect.appendChild(new Option("option1", "option1"));
		nextSelect.appendChild(new Option("option2", "option2"));
		nextSelect.appendChild(new Option("option3", "option3"));
	}

	//須回傳值
	return nextSelect;
};

/**
 * 
 */
function eventFunc2t2(val, divObj) {
	var nextSelect = $(divObj).find("select")[0];
	nextSelect.innerHTML = '<option>--Choose one</option>';
	// if (val === ""){
	//	decide which api to call
	//}
	if (val === "option1") {
		nextSelect.appendChild(new Option("option1", "option1"));
	}
	if (val === "option2") {
		nextSelect.appendChild(new Option("option2", "option2"));
	}
	if (val === "option3") {
		nextSelect.appendChild(new Option("option3", "option3"));
	}
	//須回傳值
	return nextSelect;
}

function foo() {
	//取得api資料
	var ajaxdata =
		ajaxPostTokenReady("../../LoadDDLController/LoadDDLCommonData02_Iocpya1AsType",
			null, true);
	var ajaxdataSub = ajaxdata.data;
	let optsOfSel = new Array();

	for (var i = 0; i < ajaxdataSub.length; i++) {
		optsOfSel.push({ text: ajaxdataSub[i].text, value: ajaxdataSub[i].code });
	}

	return optsOfSel;
}

//tpye2
// var selOfDivJson = { "event": "", "defaultVal": null};
// var nextSelJson = { "selObj": "", "defaultVal":null};
crtDdlDemoWithEventFuncType2({ "div": document.getElementById("div1t2"), "optsOfSel": foo(), "event": eventFunc1t2, "defaultVal": "15000070" }, { "div": document.getElementById("div2t2"), "defaultVal": "option3" });
crtDdlDemoWithEventFuncType2({ "div": document.getElementById("div2t2"), "optsOfSel": null, "event": eventFunc2t2, "defaultVal": null }, { "div": document.getElementById("div3t2"), "defaultVal": "option3" });
crtDdlDemoWithEventFuncType2({ "div": document.getElementById("div3t2"), "optsOfSel": null, "event": null, "defaultVal": null }, { "div": null, "defaultVal": null });
// crtDdlDemoWithEventFuncType2("div2t2", "div3t2", eventFunc2, defaultVal2);
// crtDdlDemoWithEventFuncType2("div3t2", null, null, defaultVal3);




