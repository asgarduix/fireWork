function openPdf(caseName, subCaseName, myJSON){
	var objKey = Object.keys(myJSON)
	var url = ''
	
	for(i = 0; i < objKey.length; i++){
		url = url + '&' + objKey[i] + '=' + myJSON[objKey[i]]
	}
	
	//'%'符號為特殊字元，須以十六進位制值方式傳遞
	var newString = url.replace(/%/g, '%25')
	
	window.open("../../" + caseName + "/" + subCaseName + "?useUnicode=true&amp;characterEncoding=UTF-8" + newString + "&" + "token=" + fetchToken());


}

function openPdf2(caseName, subCaseName, myJSON){
//	var objKey = Object.keys(myJSON)
//	var url = ''
//	
//	for(i = 0; i < objKey.length; i++){
//		url = url + '&' + objKey[i] + '=' + myJSON[objKey[i]]
//	}
//	
//	//'%'符號為特殊字元，須以十六進位制值方式傳遞
//	var newString = url.replace(/%/g, '%25')
//	
//	window.open("../../" + caseName + "/" + subCaseName + "?useUnicode=true&amp;characterEncoding=UTF-8" + newString + "&" + "token=" + fetchToken());
	$.LoadingOverlay("show", {
		custom : "<span style=\"font-size: 25px;\">處理中，請稍候.....</span>"
	});
//	window.location.href = "../../" + caseName + "/" + subCaseName + "?useUnicode=true&amp;characterEncoding=UTF-8" + newString + "&" + "token=" + fetchToken();
	ajaxGetTokenReady("../../" + caseName + "/" + subCaseName, myJSON, false);
	$.LoadingOverlay("hide");

}