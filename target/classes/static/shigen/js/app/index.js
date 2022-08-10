/**
 * 
 */
$(function() {
	var url = domain4Springboot(true) + "/sysgrant/fetchSysIndex";
	var userId = { "userId": localStorage.getItem("account") };
	var result = ajaxGetTokenReady(url, userId, true);
	var token = "?token=" + localStorage.getItem("token");

	var indexlist = result.data;
	// console.log(indexlist);
	//依indexSort排列Array元素
	indexlist.sort(function(a, b) {
		return a.indexSort - b.indexSort;
	});

	var index = "";

	for (let i = 0; i < indexlist.length; i++) {
		index += '<div class="col-xl-3 col-lg-3 col-sm-6 col-6"><a class="item-feature" href="' + indexlist[i].indexFuncUrl + token
			// + '">' +
			+ '" onclick="localStorage.setItem(\'indexId\',\'' + indexlist[i].indexId + '\');return true;">' +
			'<div class="' + indexlist[i].indexIconPath + '"></div>' +
			//			'<img class="' + indexlist[i].indexIconPath + '">' +
			'<div class="title s5">' + indexlist[i].indexName + '</div></a></div>';
	};

	$(".justify-content-star").html(index);
});

function yfSwitch(s) {
	if (s == true) {
		var url = location.href;
		var temp = url.replace("http://", "");
		var uArray = temp.split("/");

		var funcNm = uArray[uArray.length - 1];

		if (funcNm.indexOf("?") > 0) {
			var posi = funcNm.indexOf("?");
			funcNm = funcNm.substring(0, posi);
		}

		if (funcNm != "index") {
			alert("?:" + funcNm);
			return;
		}

		var token = "?token=" + localStorage.getItem("token");
		$(".justify-content-star").html("");

		var indexlist = [{
			"indexFuncUrl": "yf_1-html_el",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_1-html_el"
		}
			, {
			"indexFuncUrl": "yf_1-html_el-async",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_1-html_el-async"
		}, {
			"indexFuncUrl": "yf_2-tableapi",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_2-tableapi"
		}, {
			"indexFuncUrl": "yf_2-tableapi_ddlref",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_2-tableapi_ddlref"
		}, {
			"indexFuncUrl": "yf_2-tableapi_dev1",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_2-tableapi_dev1"
		}, {
			"indexFuncUrl": "yf-3_dropDownDemo",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf-3_dropDownDemo"
		}, {
			"indexFuncUrl": "yf_2-tableapiWithDDD",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_2-tableapiWithDDD"
		}, {
			"indexFuncUrl": "yf_2-tableapiWithNoInitialData",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_2-tableapiWithNoInitialData"
		}, {
			"indexFuncUrl": "yf_2-tableapi2CMN306000",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_2-tableapi2CMN306000"
		}, {
			"indexFuncUrl": "yf-4_vue_carolyn.html",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf-4_vue_carolyn.html"
		}, {
			"indexFuncUrl": "yf_2-tableapi2HAS301009.html",
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_2-tableapi2HAS301009"
		}, {
			"indexFuncUrl": "yf_5-async_test.html",//file name in templates folder
			"indexId": "",
			"indexIconPath": "icon n0",
			"indexName": "yf_5-async_test"//function name
		}
			//sample
			//		, {
			//			"indexFuncUrl": "",//file name in templates folder
			//			"indexId": "",
			//			"indexIconPath": "icon n0",
			//			"indexName": ""//function name
			//		}
		];

		var index = "";

		for (let i = 0; i < indexlist.length; i++) {
			index += '<div class="col-xl-3 col-lg-3 col-sm-6 col-6"><a class="item-feature" href="' + indexlist[i].indexFuncUrl + token
				// + '">' +
				+ '" onclick="localStorage.setItem(\'indexId\',\'' + indexlist[i].indexId + '\');return true;">' +
				'<div class="' + indexlist[i].indexIconPath + '"></div>' +
				//			'<img class="' + indexlist[i].indexIconPath + '">' +
				'<div class="title s5">' + indexlist[i].indexName + '</div></a></div>';
		};

		$(".justify-content-star").html(index);

	}
}