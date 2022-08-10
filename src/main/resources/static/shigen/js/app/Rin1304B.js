//.....保批單明細檔B.....//
var oldAddrNo;
var radioValue3 = "";

//執行按鈕
function dataGoB() {

	//取得選取的Radio值
	let radioValue = $("input[name='radio3']:checked").val();
	switch (radioValue) {

		//新增保批單明細檔
		case '1':

			//給預設值
			setZeroB();
			let param = get1304PolicyDtlParam();
			let parJson = JSON.stringify(param);


			// 檢核必填
			let msg1 = checkRequiredB();
			if (!checkIsNullSpace(msg1)) {
				styleAlert(msg1);
				return;
			}
			//其他檢核
			if (chk1304B()) {
				return;
			}

			let res = ajaxPostByJsonParam("../../rin1304bapi/insertfripolicydtl", parJson, false);
			if ("000" === res.status) {
				alert("新增保批單明細成功");
				$('#downRowB').hide();
				//樹狀圖立刻顯示
				var thisPreLv2 = $(memoClickElement).parent("div").eq(0)[0].className;
				var childenDiv2 = $(memoClickElement).parent("div").children('div').eq(0)[0].className;
				if (thisPreLv2 === 'menu-section menu-lv-2' && childenDiv2 === 'menu-item') {
					memoClickElement.parentElement.previousSibling.click();
					memoClickElement.click();
				} else {
					immediatelyShow();
				}
			} else {
				alert("地址序號:" + $('#numaddr_noB').val() + "重複!" + "\n新增保單批單失敗");
			}
			break;

		//修改保批單明細檔
		case '2':
			let addrNoValue = $('#numaddr_noB').val();
			let param2 = get1304PolicyDtlParam();
			let parJson2 = JSON.stringify(param2);
			// 檢核必填
			let msg2 = checkRequiredB();
			if (!checkIsNullSpace(msg2)) {
				styleAlert(msg2);
				return;
			}
			//其他檢核
			if (chk1304B()) {
				return;
			}
			//本身序號就可以修改
			if (oldAddrNo === addrNoValue) {
				let res2 = ajaxPostByJsonParam("../../rin1304bapi/updatefripolicydtl", parJson2, false);
				if ("000" === res2.status) {
					alert("修改保批單明細成功");
					$('#downRowB').hide();
					//樹狀圖立刻顯示
					$('#lv1>span').click();
					$('#lv1>span').click();
				} else {
					alert("修改保批單明細失敗");
				}
				//如果地址序號不同	
			} else if (oldAddrNo !== addrNoValue) {
				//查詢該地址序號是否存在
				let param3 = get1304PolicyDtlParam();
				let parJson3 = JSON.stringify(param3);
				let res3 = ajaxPostByJsonParam("../../rin1304bapi/querypolicydtlbyprimarykey", parJson3, false);
				if (res3.data.length >= 1) {
					alert("地址序號已存在!");
					//舊地址序號
					$('#numaddr_noB').val(oldAddrNo);

					//沒有重複地址序號即可修改地址序號(保批單明細檔、標的物明細檔、附加險明細檔皆會一起更改地址序號)
				} else {
					let isUpdate = confirm("確定要修改該地址序號?")
					if (isUpdate) {
						let param4 = {
							"policyDtlList": get1304PolicyDtlParam(),//保批單明細頁整頁資料
							"oldAddrNo": oldAddrNo                   //舊地址序號
						}
						let parJson4 = JSON.stringify(param4);
						let res4 = ajaxPostByJsonParam("../../rin1304bapi/updateaddrno", parJson4, false);
						if ("000" === res4.status) {
							alert("修改保批單明細、標的明細、附加險明細之地址序號成功");
							$('#downRowB').hide();
							//樹狀圖立刻顯示
							$('#lv1>span').click();
							$('#lv1>span').click();
						} else {
							alert("修改保批單明細、標的明細、附加險明細之地址序號失敗");
						}
					}
				}
			}
			break;
	}
}

//查詢地址序號
function qreryPolicyAddrNo() {
	let param = {
		"policyNo": $('#txtpolicy_no').val(),  // 保單號碼
		"endorseNo": $('#txtendorse_no').val()  // 批單號碼
	}

	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304bapi/qrerypolicyaddrno", parJson, false);


	if (res.data[0] === null) {
		$('#numaddr_no_ori').val("1");
		$('#numaddr_noB').val("1");
	} else {
		let addrNoOri;
		let addrNo;
		addrNoOri = ((res.data[0].addrNoOri * 1) + (1 * 1));
		addrNo = ((res.data[0].addrNo * 1) + (1 * 1));
		$('#numaddr_no_ori').val(addrNoOri);  //保單地址序號
		$('#numaddr_noB').val(addrNo);        //地址序號
	}
}

//======按鈕控制開始=====//

//按左下新增時，顯示右下的新增Radio
$('#btnAddB').click(function (){
	$("#policyPageB").find("input, button,select").attr("disabled",false);
	$("#updateRadioB").removeAttr("checked");
	$('#downRowB').show();
	$('#insertRadioSpanB').show();
	$("#insertRadioB").prop("checked",true); //設定打勾
	$('#updateRadioSpanB').hide();
});

//按左下修改時，顯示右下的修改Radio
$('#btnUpdB').click(function (){
	$("#policyPageB").find("input, button,select").attr("disabled",false);
	$("#insertRadioB").removeAttr("checked");
	$('#downRowB').show();
	$('#insertRadioSpanB').hide();
	$("#updateRadioB").prop("checked",true); //設定打勾
	$('#updateRadioSpanB').show();

});

//======按鈕控制結束=====//

/**
 * 更新地址序號
 */
function updateAddrNoOri() {

	let param = {
		"policyNo": $('#txtpolicy_noB').val(),  // 保單號碼
		"endorseNo": $('#txtendorse_noB').val(), // 批單號碼
		"addrNoOri": $('#numaddr_no_ori').val()  // 保單地址序號
	}

	let parJson = JSON.stringify(param);
	let addrNoOriAllRes = ajaxPostByJsonParam("../../rin1304bapi/updateaddrnoori", parJson, false);
	if ("000" === addrNoOriAllRes.status) {
	} else {
		alert("更新保單地址序號失敗");
	}

}

/*
 * 查詢保單地址序號是否有重複
 * /
function queryAddrNoOriIsDuplicate(){

//	 let addrNoOri=$('#numaddr_no_ori').val();
//	   let param2 ={
//				"policyNo" : $('#txtpolicy_noB').val(),  // 保單號碼
//				"endorseNo": $('#txtendorse_noB').val(), // 批單號碼
//				"addrNoOri"   : addrNoOri              // 保單地址序號
//		       } 
//			
//			let parJson2 = JSON.stringify(param2);
//			let addrNoOriRes=ajaxPostByJsonParam("../../rin1304bapi/queryaddrno",parJson2, false);
//			
//			//如果保單地址序號有重複，執行保單地址序號+1和將保單地址序號重新排號碼和排序
//			if(addrNoOriRes.data.length >= 1){}
}

/**
 * Rin1304臨分分入，刪除保批單明細
 * @author Sophia 2021/12/07
 */
function btnDeleteB() {

	let isDelete = confirm("確認要刪除該保批單明細?");
	if (isDelete) {

		//1-參數
		let param = {
			"policyNo": $('#txtpolicy_noB').val(),  //保單號碼
			"endorseNo": $('#txtendorse_noB').val(), //批單號碼 
			"addrNo": $('#numaddr_noB').val()      //地址序號
		}

		let parJson = JSON.stringify(param);

		//2-執行刪除
		let res = ajaxPostByJsonParam("../../rin1304bapi/deletefripolicydtl", parJson, false);

		if ("000" === res.status) {
			alert("刪除保批單明細成功");

			//更新保單地址序號
			updateAddrNoOri();
			//樹狀圖立刻顯示
			$('#lv1>span').click();
			$('#lv1>span').click();

		} else {
			alert("刪除保批單明細失敗");
		}
	}
}



//檢核必填
function checkRequiredB(){

	let message = "";
	let isRequireStr = "為必填!<br>";
	
	
	//--------------保批單明細檔頁(B)--------------
	if(checkIsNullSpace($('#numaddr_noB').val())){
		message = message + "地址序號" + isRequireStr;
	}
	if(checkIsNullSpace($('#numaddr_no_ori').val())){
		message = message + "保單地址序號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtrisk_flag').val())){
		message = message + "是否完成同險" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtpolicy_dbgn').val())){
		message = message + "起保日" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtpolicy_dend').val())){
		message = message + "迄止日" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtprop_addr').val())){
		message = message + "標的物地址" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtzip_code').val())){
		message = message + "郵遞區號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtarea_code').val())){
		message = message + "地段代碼" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtuseprop_codeB').val())){
		message = message + "使用性質代號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtuseprop_nameB').val())){
		message = message + "使用性質名稱" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numFlor_no').val())){
		message = message + "樓層數" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtconst_class').val())){
		message = message + "建築等級英文代號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#limit_no').val())){
		message = message + "限額代號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#limit').val())){
		message = message + "限額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtlimit_rate').val())){
		message = message + "限額比率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numlimit_ori').val())){
		message = message + "保單限額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numamt').val())){
		message = message + "異動總保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numprem').val())){
		message = message + "異動總保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numamt_flt').val())){
		message = message + "流動貨物保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numprem_flt').val())){
		message = message + "流動貨物保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numamt_fix').val())){
		message = message + "非流動貨物保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numprem_fix').val())){
		message = message + "非流動貨物保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numamt_typ').val())){
		message = message + "颱洪本單總保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numprem_typ').val())){
		message = message + "颱洪本單年保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numamt_ear').val())){
		message = message + "地震本單總保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numprem_ear').val())){
		message = message + "地震本單年保費" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#num_amt_hn').val())){
		message = message + "華南保額總保額" + isRequireStr;
	}

	return message;
	
}


//取得保批單明細檔畫面欄位值
function get1304PolicyDtlParam(){
	return{
		//-----------保批單明細檔畫面欄位-------------
		"policyNo":$('#txtpolicy_noB').val(),                   //保單號碼
		"endorseNo":$('#txtendorse_noB').val(),                 //批單號碼
		"addrNoOri":$('#numaddr_no_ori').val(),                 //保單地址序號
		"addrNo":$('#numaddr_noB').val(),                       //地址序號
		"riskNo":$('#txtrisk_no').val(),                        //同險編號
		"riskFlag":$('#txtrisk_flag').val(),                    //是否完成同險(Y/N)
		"riskName":$('#txtrisk_name').val(),                    //同險名稱
		"policyDbgn":transDateStrToDateObj($('#txtpolicy_dbgnB').val()),   //起保日
		"policyDend":transDateStrToDateObj($('#txtpolicy_dendB').val()),   //訖止日
		"propAddr":$('#txtprop_addr').val(),                    //標的物地址
		"zipCode":$('#txtzip_code').val(),                      //郵遞區號
		"areaCode":$('#txtarea_code').val(),                    //地段代碼
		"usepropCode":$('#txtuseprop_codeB').val(),             //使用性質代號
		"usepropName":$('#txtuseprop_nameB').val(),             //使用性質名稱
		"florNo":$('#numFlor_no').val(),                        //樓層數
		"constClass":$('#txtconst_class').val(),                //建築等級英文代號
		"limitNo":$('#limit_no').val(),                         //限額代號
		"limit":removeComma($('#limit').val()),                 //限額
		"limitRate":$('#txtlimit_rate').val(),                  //限額比率
		"limitOri":removeComma($('#numlimit_ori').val()),       //保單限額
		"amt":removeComma($('#numamt').val()),                  //異動總保額
		"prem":removeComma($('#numprem').val()),                //異動總保費
		"amtFlt":removeComma($('#numamt_flt').val()),           //流動貨物保額
		"premFlt":removeComma($('#numprem_flt').val()),         //流動貨物保費
		"amtFix":removeComma($('#numamt_fix').val()),           //非流動貨物保額
		"premFix":removeComma($('#numprem_fix').val()),         //非流動貨物保費
		"amtTyp":removeComma($('#numamt_typ').val()),           //颱洪本單總保額
		"premTyp":removeComma($('#numprem_typ').val()),         //颱洪本單總保費
		"amtEar":removeComma($('#numamt_ear').val()),           //地震本單總保額
		"premEar":removeComma($('#numprem_ear').val()),         //地震本單總保費
		"mercno":$('#txtmercno').val()                          //29險種代碼
	}
}

//寫入欄位
function writeFieldForRin1304B(cell){
	
	var policyDbgnB = new Date(cell.txtpolicy_dbgn).format('yyyy/MM/dd');
	
	var policyDendB= new Date(cell.txtpolicy_dend).format('yyyy/MM/dd');

	
	//.......保批單明細檔........//
	$('#txtpolicy_noB').val(policyNo);        //保單號碼
	$('#txtendorse_noB').val(endorseNo);      //批單號碼
	
	$('#numaddr_no_ori').val(cell.numaddr_no_ori);     //保單地址序號
	$('#numaddr_noB').val(cell.numaddr_no);            //地址序號          
	oldAddrNo=cell.numaddr_no;                         //舊地址序號
	$('#txtrisk_no').val(cell.txtrisk_no);             //同險編號
	$('#txtrisk_flag').val(cell.txtrisk_flag);         //是否完成同險(Y/N)
	$('#txtrisk_name').val(cell.txtrisk_name);         //同險名稱
	$('#txtpolicy_dbgnB').val(policyDbgnB);            //起保日
	$('#txtpolicy_dendB').val(policyDendB);            //訖止日
	$('#txtprop_addr').val(cell.txtprop_addr);         //標的物地址
	$('#txtzip_code').val(cell.txtzip_code);           //郵遞區號
	$('#txtarea_code').val(cell.txtarea_code);         //地段代碼
	$('#txtuseprop_codeB').val(cell.txtuseprop_code);  //使用性質代號
	$('#txtuseprop_nameB').val(cell.txtuseprop_name);  //使用性質名稱
	$('#numFlor_no').val(cell.numFlor_no);             //樓層數
	$('#txtconst_class').val(cell.txtconst_class);     //建築等級英文代號
	$('#limit_no').val(cell.limit_no);                 //限額代號
	$('#limit').val(toThousands(cell.limit));          //限額
	$('#txtlimit_rate').val(cell.txtlimit_rate);       //限額比率
	$('#numlimit_ori').val(toThousands(cell.numlimit_ori));         //保單限額
	$('#numamt').val(toThousands(cell.numamt));                     //異動總保額
	$('#numprem').val(toThousands(cell.numprem));                   //異動總保費 
	$('#numamt_flt').val(toThousands(cell.numamt_flt));             //流動貨物保額
	$('#numprem_flt').val(toThousands(cell.numprem_flt));           //流動貨物保費
	$('#numamt_fix').val(toThousands(cell.numamt_fix));             //非流動貨物保額
	$('#numprem_fix').val(toThousands(cell.numprem_fix));           //非流動貨物保費
	$('#numamt_typ').val(toThousands(cell.numamt_typ));             //颱洪本單總保額
	$('#numprem_typ').val(toThousands(cell.numprem_typ));           //颱洪本單年保費
	$('#numamt_ear').val(toThousands(cell.numamt_ear));             //地震本單總保費
	$('#numprem_ear').val(toThousands(cell.numprem_ear));           //地震本單年保費
	$('#txtmercno').val(cell.txtmercno);                            //29險種代碼
}

//寫入該明細累計保額
function writeSumAmt (cell){
	$('#sumamt').text(toThousands(cell.sumamt));                    //該明細累計保額
}

//預設為0
function setZeroB(){
	if(checkIsNullSpace($('#numamt').val())){          //異動總保額
		$('#numamt').val(0);
	}
	if(checkIsNullSpace($('#numprem').val())){         //異動總保費
		$('#numprem').val(0);
	}
	if(checkIsNullSpace($('#numamt_flt').val())){      //流動貨物保額
		$('#numamt_flt').val(0);
	}
	if(checkIsNullSpace($('#numprem_flt').val())){     //流動貨物保費
		$('#numprem_flt').val(0);
	}
	if(checkIsNullSpace($('#numamt_fix').val())){      //非流動貨物保額
		$('#numamt_fix').val(0);
	}
	if(checkIsNullSpace($('#numprem_fix').val())){     //非流動貨物保費
		$('#numprem_fix').val(0);
	}    
	if(checkIsNullSpace($('#numamt_typ').val())){      //颱洪本單總保額
		$('#numamt_typ').val(0);
	}
    if(checkIsNullSpace($('#numprem_typ').val())){     //颱洪本單總保費
		$('#numprem_typ').val(0);
	} 
    if(checkIsNullSpace($('#numamt_ear').val())){      //地震本單總保額
		$('#numamt_ear').val(0);
	}
    if(checkIsNullSpace($('#numprem_ear').val())){     //地震本單年保費
		$('#numprem_ear').val(0);
	}
    if(checkIsNullSpace($('#txtmercno').val())){       //30險種代碼
		$('#txtmercno').val("03");
	}
    if(checkIsNullSpace($('#sumamt').text())){          //該明細累積保額
		$('#sumamt').text("0");
	}
    
}

//其他檢核
function chk1304B(){
	let msg = '';
	let isRequireStr = "<br>";
	if(!chkDateFormat($('#txtpolicy_dbgnB').val())){
		msg = msg + '起保日'+isRequireStr;
	}
	if(!chkDateFormat($('#txtpolicy_dendB').val())){
		msg = msg + '迄止日'+isRequireStr;
	}
	if(!checkIsNullSpace(msg)){
		styleAlert(msg+"格式需為 YYYY/MM/DD 或是 YYYY/M/D ");
		return true;
	}
	return false;
}


//新增時欄位預設值
function insertDataRin1304B() {

	clearDataRin1304B();
	$('#txtpolicy_noB').val(policyNo);                         //保單號碼
	$('#txtendorse_noB').val(endorseNo);                       //批單號碼

	$('#txtrisk_flag').val("N");                               //是否完成同險(Y/N)
	$('#txtpolicy_dbgnB').val($('#txtpolicy_dbgn').val());     //起保日
	$('#txtpolicy_dendB').val($('#txtpolicy_dend').val());     //訖止日
	$('#txtlimit_rate').val("1");                              //限額比率
	$('#txtmercno').val("03")                                  //30險種代碼           
	qreryPolicyAddrNo();

}

//清空資料
function clearDataRin1304B(){
	$('#txtpolicy_noB').val("");           //保單號碼
	$('#txtendorse_noB').val("");          //批單號碼
	$('#numaddr_no_ori').val("");          //保單地址序號
	$('#numaddr_noB').val("");              //地址序號
	$('#txtlimit_rate').val("");           //限額比率 
	$('#txtrisk_flag').val("");            //是否完成同險(Y/N)
	$('#txtrisk_no').val("");              //同險編號
	$('#txtpolicy_dbgnB').val("");         //起保日
	$('#txtpolicy_dendB').val("");         //訖止日
	$('#txtrisk_name').val("");            //同險名稱
	$('#txtprop_addr').val("");            //標的物地址
	$('#txtzip_code').val("");             //郵遞區號
	$('#txtarea_code').val("");            //地段代碼
	$('#txtuseprop_codeB').val("");        //使用性質代號
	$('#txtuseprop_nameB').val("");        //使用性質名稱
	$('#numFlor_no').val("");              //樓層數
	$('#txtconst_class').val("");          //建築等級英文代號
	$('#limit_no').val("");                //限額代號
	$('#limit').val("");                   //限額
	$('#numlimit_ori').val("");            //保單限額
	$('#numamt').val("");                  //異動總保額
	$('#numprem').val("");                 //異動總保費 
	$('#numamt_flt').val("");              //流動貨物保額
	$('#numprem_flt').val("");             //流動貨物保費
	$('#numamt_fix').val("");              //非流動貨物保額
	$('#numprem_fix').val("");             //非流動貨物保費
	$('#numamt_typ').val("");              //颱洪本單總保額
	$('#numprem_typ').val("");             //颱洪本單年保費
	$('#numamt_ear').val("");              //地震本單總保費
	$('#numprem_ear').val("");             //地震本單年保費
	$('#sumamt').text("");                  //該明細累計保額
	$('#txtmercno').val("");               //29險種代碼
}

//轉換日期格式「"yyyy/MM/dd"」
Date.prototype.format = function(format) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(format)) {
	   format=format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(format)){
    	   format = format.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return format; 
}

//查詢保批單明細B
function query2(param) {
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304bapi/querypolicydtlbyprimarykey", parJson, false);
	if ("000" === res.status) {
		writeFieldForRin1304B(res.data[0]);
	} else {
		alert("保批單明細檔查詢錯誤");
	}
	//該明細累積保額	
	sumPolicyEndorseAmt();
}

//查詢該明細累積保額
function sumPolicyEndorseAmt() {
	let param2 = {
		"policyNo": $('#txtpolicy_noB').val(),  // 保單號碼
		"addrNo": $('#numaddr_noB').val()      // 地址序號
	}
	let parJson2 = JSON.stringify(param2);
	let res2 = ajaxPostByJsonParam("../../rin1304bapi/sumpolicyendorseamt", parJson2, false);
	if ("000" === res2.status) {
		//3-顯示
		writeSumAmt(res2.data[0]);
	} else {
		alert("查詢該明細累積保額錯誤");
	}
}

//輸入使用性質代號則帶出使用性質名稱
$('#txtuseprop_codeB').blur(function() {
	// 使用性質代號
	let usePropCodeB = $('#txtuseprop_codeB').val();
	let param = {
		"usePropId": usePropCodeB
	}
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304bapi/getuseprop", parJson, false);
	if ("000" === res.status) {
		$('#txtuseprop_nameB').val(res.data[0].usePropName);
	} else if ("100" === res.status) {
		alert("查詢使用性質名稱錯誤。\n查無使用性質代號:" + usePropCodeB);
		$('#txtuseprop_nameB').val("");
	}

});

//輸入完樓層數和建築等級英文代號會自動帶出 限額代號、限額、限額比率、保單限額
$('#txtconst_class').blur(function() {
	
	let constClass=$('#txtconst_class').val(); // 建築等級英文代號
	let propCode=$('#txtuseprop_codeB').val(); // 使用性質代號
	let policyYear=$('#txtpolicy_dprt').val(); // 保單列印日
	let param ={
			"policyYear" : policyYear , // 合約年度
			"constClass" : constClass , 
			"propCode" : propCode 
	       } 
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304bapi/querylimit", parJson, false);

	if("000"===res.status && res.data.length>0){
		$('#limit').val(res.data[0].limitAmount);
		$('#limit_no').val(res.data[0].limitId);
		$('#numlimit_ori').val(res.data[0].limitAmount);
	}else {
		alert("限額代號、限額、限額比率、保單限額\n查無資料");
	}

});

//用保單「保單號碼」、「批單號碼」、「保單地址序號」帶入該保單、地址序號的資料
$('#numaddr_noB').blur(function() {

	radioValue3 = $("input[name='radio3']:checked").val();
	if (radioValue3 === '1') {
		let policyNo = $('#txtpolicy_noB').val();
		let addrNo = $('#numaddr_noB').val();
		let param = {
			"policyNo": policyNo,
			"endorseNo": '',
			"addrNo": addrNo
		}
		let parJson = JSON.stringify(param);
		let res = ajaxPostByJsonParam("../../rin1304bapi/querypolicydtlbyprimarykey", parJson, false);

		if ("000" === res.status && res.data.length > 0) {
			//寫入資料
			writeFieldForRin1304B(res.data[0]);
			//查詢該明細累積保額	
			sumPolicyEndorseAmt();
		} else {
			alert("保單號碼:" + policyNo + "\n" +
				"地址序號:" + addrNo + "\n" +
				"查無資料");
			//清空欄位
			clearDataRin1304B();
			//給預設值
			insertDataRin1304B();
		}
	}
});

