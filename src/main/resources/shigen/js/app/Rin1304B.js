//.....保批單明細檔B.....//


//執行按鈕
function dataGoB() {
	
	//取得選取的Radio值
	let radioValue = $("input[name='radio3']:checked").val();
	
	switch (radioValue) {
	
	//新增保批單明細檔
	case '1':
		
		//給預設值
		setZeroB ();
		let param = get1304PolicyDtlParam();
		let parJson = JSON.stringify(param);
		
		// 檢核必填
		let msg1 = checkRequiredB();
		if(!checkIsNullSpace(msg1)){
			styleAlert(msg1);
			return;
		}
		
		let res = ajaxPostByJsonParam("../../rin1304bapi/insertfripolicydtl",parJson, false);
		if("000" === res.status){
			alert("新增保批單明細成功");
			
			//更新地址序號
			updateAddrNoOri();
			
			//資料夾重新點擊
			lv1Click();
		}else{
			alert("地址序號:"+$('#numaddr_no').val()+"重複!" + "\n新增保單批單失敗");
		}
		break;

	//修改保批單明細檔
	case '2':
		let param2 =get1304PolicyDtlParam();
		let parJson2 = JSON.stringify(param2);
		// 檢核必填
		let msg2 = checkRequiredB();
		if(!checkIsNullSpace(msg2)){
			styleAlert(msg2);
			return;
		}
		let res2 = ajaxPostByJsonParam("../../rin1304bapi/updatefripolicydtl",parJson2, false);
		if("000" === res2.status){
			alert("修改保批單明細成功");
			//更新保單地址序號
			updateAddrNoOri();
		}else{
			alert("修改保批單明細失敗");
		}
		break;
	}
}



/**
 * 更新地址序號
 */
function updateAddrNoOri(){

				let param ={
						"policyNo" : $('#txtpolicy_noB').val(),  // 保單號碼
						"endorseNo": $('#txtendorse_noB').val(), // 批單號碼
						"addrNoOri": $('#numaddr_no_ori').val()  // 保單地址序號
				       } 
					
					let parJson = JSON.stringify(param);
					let addrNoOriAllRes=ajaxPostByJsonParam("../../rin1304bapi/updateaddrnoori",parJson, false);
					if("000" === addrNoOriAllRes.status){
						alert(addrNoOriAllRes.message);
					}else{
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
function btnDeleteB(){
	
	let isDelete = confirm("確認要刪除該保批單明細?");
	if(isDelete){
		
		//1-參數
		let param ={
				"txtpolicy_no" : $('#txtpolicy_noB').val(),  //保單號碼
				"txtendorse_no": $('#txtendorse_noB').val(), //批單號碼 
				"numaddr_no"   : $('#numaddr_no').val()      //地址序號
		} 
		
		let parJson = JSON.stringify(param);
		
		//2-執行刪除
		let res = ajaxPostByJsonParam("../../rin1304bapi/deletefripolicydtl", parJson, false);
		
		if("000" === res.status){
			alert("刪除保批單明細成功");
			
			//更新保單地址序號
			updateAddrNoOri();
			
			//重新點擊資料夾
			lv1Click();
		}else{
			alert("刪除保批單明細失敗");
		}
	}

}



//檢核必填
function checkRequiredB(){

	let message = "";
	let isRequireStr = "為必填!<br>";
	
	
	//--------------保批單明細檔頁(B)--------------
	if(checkIsNullSpace($('#numaddr_no').val())){
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
	
	if(checkIsNullSpace($('#txtuseprop_name').val())){
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
	
	if(checkIsNullSpace($('#sumamt').val())){
		message = message + "該明細累積保額" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtmercno').val())){
		message = message + "29險種代碼" + isRequireStr;
	}
	return message;
	
}


//取得保批單明細檔畫面欄位值
function get1304PolicyDtlParam(){
	return{
		//-----------保批單明細檔畫面欄位-------------
		"policyNo":$('#txtpolicy_noB').val(),       //保單號碼
		"endorseNo":$('#txtendorse_noB').val(),     //批單號碼
		"addrNoOri":$('#numaddr_no_ori').val(),    //保單地址序號
		"addrNo":$('#numaddr_no').val(),           //地址序號
		"riskNo":$('#txtrisk_no').val(),           //同險編號
		"riskFlag":$('#txtrisk_flag').val(),       //是否完成同險(Y/N)
		"riskName":$('#txtrisk_name').val(),       //同險名稱
		"policyDbgn":$('#txtpolicy_dbgn').val(),   //起保日
		"policyDend":$('#txtpolicy_dend').val(),   //訖止日
		"propAddr":$('#txtprop_addr').val(),       //標的物地址
		"zipCode":$('#txtzip_code').val(),         //郵遞區號
		"areaCode":$('#txtarea_code').val(),       //地段代碼
		"usepropCode":$('#txtuseprop_codeB').val(), //使用性質代號
		"usepropName":$('#txtuseprop_name').val(), //使用性質名稱
		"florNo":$('#numFlor_no').val(),           //樓層數
		"constClass":$('#txtconst_class').val(),   //建築等級英文代號
		"limitNo":$('#limit_no').val(),            //限額代號
		"limit":$('#limit').val(),                 //限額
		"limitRate":$('#txtlimit_rate').val(),     //限額比率
		"limitOri":$('#numlimit_ori').val(),       //保單限額
		"amt":$('#numamt').val(),                  //異動總保額
		"prem":$('#numprem').val(),                //異動總保費
		"amtFlt":$('#numamt_flt').val(),           //流動貨物保額
		"premFlt":$('#numprem_flt').val(),         //流動貨物保費
		"amtFix":$('#numamt_fix').val(),           //非流動貨物保額
		"premFix":$('#numprem_fix').val(),         //非流動貨物保費
		"amtTyp":$('#numamt_typ').val(),           //颱洪本單總保額
		"premTyp":$('#numprem_typ').val(),         //颱洪本單總保費
		"amtEar":$('#numamt_ear').val(),           //地震本單總保額
		"premEar":$('#numprem_ear').val(),         //地震本單總保費
		"mercno":$('#txtmercno').val()             //29險種代碼
	}
}

//寫入欄位
function writeFieldForRin1304B(cell){
	
	var policyDbgnB = new Date(cell.txtpolicy_dbgn).format('yyyy-MM-dd');
	
	var policyDendB= new Date(cell.txtpolicy_dend).format('yyyy-MM-dd');

	
	//.......保批單明細檔........//
	$('#txtpolicy_noB').val(cell.txtpolicy_no);        //保單號碼
	$('#txtendorse_noB').val(cell.txtendorse_no);      //批單號碼
	
	$('#numaddr_no_ori').val(cell.numaddr_no_ori);     //保單地址序號
	$('#numaddr_no').val(cell.numaddr_no);             //地址序號
	$('#txtrisk_no').val(cell.txtrisk_no);             //同險編號
	$('#txtrisk_flag').val(cell.txtrisk_flag);         //是否完成同險(Y/N)
	$('#txtrisk_name').val(cell.txtrisk_name);         //同險名稱
	$('#txtpolicy_dbgnB').val(policyDbgnB);            //起保日
	$('#txtpolicy_dendB').val(policyDendB);            //訖止日
	$('#txtprop_addr').val(cell.txtprop_addr);         //標的物地址
	$('#txtzip_code').val(cell.txtzip_code);           //郵遞區號
	$('#txtarea_code').val(cell.txtarea_code);         //地段代碼
	$('#txtuseprop_codeB').val(cell.txtuseprop_code);  //使用性質代號
	$('#txtuseprop_name').val(cell.txtuseprop_name);   //使用性質名稱
	$('#numFlor_no').val(cell.numFlor_no);             //樓層數
	$('#txtconst_class').val(cell.txtconst_class);     //建築等級英文代號
	$('#limit_no').val(cell.limit_no);                 //限額代號
	$('#limit').val(cell.limit);                       //限額
	$('#txtlimit_rate').val(cell.txtlimit_rate);       //限額比率
	$('#numlimit_ori').val(cell.numlimit_ori);         //保單限額
	$('#numamt').val(cell.numamt);                     //異動總保額
	$('#numprem').val(cell.numprem);                   //異動總保費 
	$('#numamt_flt').val(cell.numamt_flt);             //流動貨物保額
	$('#numprem_flt').val(cell.numprem_flt);           //流動貨物保費
	$('#numamt_fix').val(cell.numamt_fix);             //非流動貨物保額
	$('#numprem_fix').val(cell.numprem_fix);           //非流動貨物保費
	$('#numamt_typ').val(cell.numamt_typ);             //颱洪本單總保額
	$('#numprem_typ').val(cell.numprem_typ);           //颱洪本單年保費
	$('#numamt_ear').val(cell.numamt_ear);             //地震本單總保費
	$('#numprem_ear').val(cell.numprem_ear);           //地震本單年保費
	$('#txtmercno').val(cell.txtmercno);               //29險種代碼
	
}

//寫入該明細累計保額
function writeSumAmt (cell){
	$('#sumamt').val(cell.sumamt);                     //該明細累計保額
}

//預設為0
function setZeroB (){
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
    
}

//新增時欄位預設值
function insertDataRin1304B(){
	
	clearDataRin1304B();
	$('#txtpolicy_noB').val(policyNo);     //保單號碼
	$('#txtendorse_noB').val(endorseNo);   //批單號碼
}

//清空資料
function clearDataRin1304B(){
	$('#txtpolicy_noB').val("");           //保單號碼
	$('#txtendorse_noB').val("");          //批單號碼
	
	$('#numaddr_no_ori').val("");          //保單地址序號
	$('#numaddr_no').val("");              //地址序號
	$('#txtrisk_no').val("");              //同險編號
	$('#txtrisk_flag').val("");            //是否完成同險(Y/N)
	$('#txtrisk_name').val("");            //同險名稱
	$('#txtpolicy_dbgnB').val("");         //起保日
	$('#txtpolicy_dendB').val("");         //訖止日
	$('#txtprop_addr').val("");            //標的物地址
	$('#txtzip_code').val("");             //郵遞區號
	$('#txtarea_code').val("");            //地段代碼
	$('#txtuseprop_codeB').val("");        //使用性質代號
	$('#txtuseprop_name').val("");         //使用性質名稱
	$('#numFlor_no').val("");              //樓層數
	$('#txtconst_class').val("");          //建築等級英文代號
	$('#limit_no').val("");                //限額代號
	$('#limit').val("");                   //限額
	$('#txtlimit_rate').val("");           //限額比率
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
	$('#sumamt').val("");                  //該明細累計保額
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
function query2(param){
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304bapi/querypolicydtlbyprimarykey", parJson, false);
	
	if("000" === res.status){
		//3-顯示
		writeFieldForRin1304B(res.data[0]);
	}else{
		alert("保批單明細檔查詢錯誤");
	}
	
	let param2 ={
			"policyNo" : $('#txtpolicy_noB').val(),  // 保單號碼
			"addrNo"   : $('#numaddr_no').val()      // 保單地址序號
	       } 
	let parJson2 = JSON.stringify(param2);
	let res2 = ajaxPostByJsonParam("../../rin1304bapi/sumpolicyendorseamt", parJson2, false);
	if("000" === res2.status){
		//3-顯示
		writeSumAmt(res2.data[0]);
	}else{
		alert("查詢該明細累積保額錯誤");
	}
}