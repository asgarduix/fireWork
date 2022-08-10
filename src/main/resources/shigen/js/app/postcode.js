/**
 * 郵遞區號頁面 - postcode
 * @returns
 */

let zipcodeDataParamsTerms = [
    ["SelectButton", "選取", "checkbox", true, "fix"],
    [ "zipcode", "郵遞區號", "input"],
    [ "ncity", "縣市地名", "input"],
    [ "ncounty", "市鄉區鎮地名", "input"],
    [ "nroad1", "路街名稱", "input"],
    [ "nroad2", "段弄巷號名稱", "input"]
    ];

//預設
$(function() {
	init();
});

function init(){

	let originParam = {};
	let originRes = ajaxPostTokenReady("../../HAS301001NApi/queryZipcode", originParam);
	let oriNcityDdl = originRes.data;
	createDdl("ddlNcity", oriNcityDdl, null);
	createDdl("ddlNcounty", {}, null);
	createDdl("ddlNroad1", {}, null);

	zipcodeTable = createTableddlref(zipcodeDataParamsTerms, {},"#tblZip", {
		sel: function(e, cell) {
			window.opener.setAddr(cell.getData());
			window.close();
		}
	},false, []);
}

function ddlChange(id){

	let ncity = $('#ddlNcity option:selected').val();
	let ncounty = $('#ddlNcounty option:selected').val();
	let nroad1 = $('#ddlNroad1 option:selected').val();
	let zipcodeParam = {"ncity" : ncity};

	if(id == 'ddlNcity'){
		if(ncity == null || ncity == ''){
			setDdl({}, "ddlNcounty");
			setDdl({}, "ddlNroad1");
			setZipcodeTable({});
		}else{
			let ddlRes = ajaxPostTokenReady("../../HAS301001NApi/queryZipcode", zipcodeParam);
			let ddlNcountyData = ddlRes.data.ddlMap.ncounty;
			let ddlNroad1Data = ddlRes.data.ddlMap.nroad1;
			let tableData = ddlRes.data.zipcodeList;

			setDdl(ddlNcountyData, "ddlNcounty");
			setDdl(ddlNroad1Data, "ddlNroad1");
			setZipcodeTable(tableData);
		}
	}

	if(id == 'ddlNcounty'){

		zipcodeParam.ncounty = ncounty;
		let ddlRes = ajaxPostTokenReady("../../HAS301001NApi/queryZipcode", zipcodeParam);
		let ddlNroad1Data = ddlRes.data.ddlMap.nroad1;
		let tableData = ddlRes.data.zipcodeList;
		setDdl(ddlNroad1Data, "ddlNroad1");
		setZipcodeTable(tableData);
	}

	if(id == 'ddlNroad1'){

		zipcodeParam.ncounty = ncounty;
		zipcodeParam.nroad1 = nroad1;
		let ddlRes = ajaxPostTokenReady("../../HAS301001NApi/queryZipcode", zipcodeParam);
		let tableData = ddlRes.data.zipcodeList;
		setZipcodeTable(tableData);
	}

}

function setDdl(ddlData, id){
	$('#' + id).children().remove();
	createDdl(id, ddlData, null);
}

function setZipcodeTable(tableData){
	zipcodeTable.clearData();
	zipcodeTable.setData(tableData);
}
