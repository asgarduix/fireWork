$(function(){
	// 日期初始化
	const today = new Date();
	const date = formatDateYYYYMMDD(today);
	$("#dtaStart").val(date);
})


//列印狀態可選月,季,半年等時間，第二下拉需連動變更時間點(暫無此需求)
//$('#monthPeriod').change(function() {
//	$('#periodNo').children().remove();
//	let monthPeriod = $('#monthPeriod').find('select').val();
//
//	const month = [1,2,3,4,5,6,7,8,9,10,11,12];
//	const season = [1,2,3,4];
//	const halfAYear = [1,2];
//	let period = [];
//	let objArr = new Array();
//
//	switch (monthPeriod) {
//	case '1':
//		period = month;
//		break;
//
//	case '2':
//		period = season;
//		break;
//
//	case '3':
//		period = halfAYear;
//		break;
//	default:
//		break;
//	}
//
//	for(const periodNo of period){
//		let obj = {};
//		obj.text = periodNo;
//		obj.value = periodNo;
//		objArr.push(obj);
//	}
//
//
//	createDdl('periodNo', objArr, null);
//	$('#periodNo').find('select').css('width', '20%');
//});

//根據合約年度帶出合約代號
$('#txttreaty_year').change(function() {
	let treatyYear = $('#txttreaty_year').val();

	let url = "../../rin1206api/queryfritreatybytreatyyear";
	let obj = {
			"treaty_year": treatyYear
	}
	let parJson = JSON.stringify(obj);
	let res = ajaxPostByJsonParam(url, parJson, true);

	let option = {
		"value" : "treatyNo",
		"name" : "treatyNo"
	};

	// 組下拉選
	let id = 'treatyNo';
	if(!checkIsNullSpace(res["data"])){
		$("#" + id).empty();
		for(let i = 0; i < res["data"].length; i++){
//			$("<option value=" + res["data"][i][option.value] + ">"  + res["data"][i][option.name]+"</option>").appendTo("#" + id);

			let selectOption = document.createElement('option');
			selectOption.value = res["data"][i][option.value];
			selectOption.textContent= res["data"][i][option.name];
			let select = document.getElementById(id);
			select.appendChild(selectOption);
		}
	}else{
		$("#" + id).empty();
	}
});

function printBtn() {
	let param = addParam();
	let parJson = JSON.stringify(param);
	//檢核條件
	let str = checkCondition(param);
	if(!checkIsNullSpace(str)){
		alert(str);
		return;
	}
	//列印報表 & 寫入資料
	if ('1' === param.rdoMode) {//立即執行

		ajaxRequestIsAsyncDynimicBytoken("../../rin1206api/printrin1206report", true, false, param,
				(res) => {
							if (res != null && "000" === res.status) {
								let msg = res.data['message'];
								alert("執行完畢!!!" + msg);
							}else{
								alert("列印失敗!!");
							}
				}, (error) => {
								console.log(error);
								alert("列印失敗!!");
				})


	}else if('2' === param.rdoMode){//排程執行

		ajaxRequestIsAsyncDynimicBytoken("../../rin1206api/insertscheduleforrin1206report", true, false, param,
				(res) => {
							if (res != null && "000" === res.status) {
								alert("已寫入排程！");
							}else{
								alert("排程失敗!!");
							}
				}, (error) => {
								console.log(error);
								alert("排程失敗!!");
				})
	}
}

function addParam() {
	let rdoMode = $('input[name = rdoMode0]:checked').val();				//啟動狀態
	let dtaStart = $('#dtaStart').val();									//開始日期
	let ddlHour = $('#ddlHour').val();										//時
	let ddlMin = $('#ddlMin').val();										//分
	let ddlSec = $('#ddlSec').val();										//秒
	let submitTime = dtaStart + " " + ddlHour + ":" + ddlMin + ":" + ddlSec

	let rdoTKind1 = $('input[name = radiobutton_tKind1]:checked').val();		//選擇狀態
	let txtTreatyYear = $('#txttreaty_year').val();							//合約年度
	let monthPeriod = '2';//只做季度
//	let monthPeriod = $('#monthPeriod').find('select').val();				//列印狀態-月季半年
	let periodNo = $('#periodNo').find('select').val();						//列印狀態-日期區間
	let treatyNo = $('#treatyNo').val();									//指定合約代號
	let account = localStorage.getItem("account");							//使用者帳號


	let param = {
			"rdoMode" : rdoMode,
			"submitTime" : submitTime,
			"rdoTKind1" : rdoTKind1,
			"txtTreatyYear" : txtTreatyYear,
			"monthPeriod" : monthPeriod,
			"periodNo" : periodNo,
			"treatyNo" : treatyNo,
			"account" : account
	}
	return param;
}

function checkCondition(param) {
	let str = '';
	const regex = new RegExp('^[0-9]*[0-9]$');

	if(!regex.test(param.txtTreatyYear)){
		str = '請輸入數字年份\n';
	}

	return str;
}

/**
 * 日期格式檢核
 * @returns
 */
$('#dtaStart').change(function(){
	try {
		if(!isValidDate($(this).val())){
			alert("日期格式錯誤，請輸入YYYY/MM/DD或是YYYY/M/D");
			$(this).val('');
		}
	} catch (e) {
		alert(e);
	}
});
