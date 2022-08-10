//.....Rin1304臨分分入主畫面.....//

/**
 *  查詢樹狀表資料 
 */
function queryTree(layer) {
	
	var parJson = {
		"policyNo": policyNo,
		"endorseNo": endorseNo,
		"addrNo": null,
		"propNo": null
	}

	//查詢樹狀結構第一層
	//	http://localhost:10127/rin1304api/queryrin1304/tree/layer/3
	let res = null;

	switch (layer) {
		case 0:
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/0", parJson, false);
			break;
		case 1:
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/1", parJson, false);
			break;
		case 2:
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/2", parJson, false);
			break;
		case 3:
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/3", parJson, false);
			break;
	}
	return res;
}


function foo(treeRes) {
	if (treeRes == null) {
		alert("查詢保單資料時發生錯誤");
		return false;
	}

	if (treeRes.data.length == 0) {
		alert("查無保單資料");
		return false;
	}

	return true;
}

