$(function() {
});

/**
 * 產生下拉選單
 * 
 * @param id
 * @param type
 * @param paramArr
 * @returns
 */
function createddl(id, type, paramArr) {
	var res = [];
	var param = {
			"type" : type,
			"paramArr" : paramArr
		}
	res = ajaxPostTokenReady("../../createddlController/createddl", param, true);
	// 組下拉選
	if(!checkIsNullSpace(res["data"])){
		$("#" + id).empty();
		for(i = 0; i < res["data"].length; i++){
			$("<option value=" + res["data"][i]["value"] + ">"  + res["data"][i]["name"]+"</option>").appendTo("#" + id);
		}
	}
	return res;
}









