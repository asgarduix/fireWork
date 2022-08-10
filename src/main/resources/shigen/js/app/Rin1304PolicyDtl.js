//.....保批單明細檔.....//
const RIN_1304A = locationHrefKeepDataFetch(); 

$(function (){
	$('#myModal').modal('hide');
})

function doModify(thisobj){
//	if(RIN_1304A){
//		$('input[name=radio2]:checked').val(RIN_1304A.filterPolicy);   //篩選條件
//		$('#txtpolicy_no_query').val( RIN_1304A.txtpolicy_no_query);   //保單號碼
//		$('#txtendorse_no_query').val( RIN_1304A.txtendorse_no_query); //批單號碼
//	}
	
	let param ={
		    "txtpolicy_no" : $('#txtpolicy_no_query').val(), //保單號碼查詢
		    "txtendorse_no": $('#txtendorse_no_query').val()  //批單號碼查詢
		} 
	let parJson = JSON.stringify(param);
	let res = ajaxPostByJsonParam("../../rin1304bapi/querypolicydtlbyprimarykey", parJson, false);
	
	if("000" === res.status){
		//3-顯示
		writeFieldForRin1304B(res.data[0]);
	
	}else{
		alert("保批單明細檔查詢錯誤");
	}
}


//執行按鈕
function dataGo() {
	let radioValue = $("input[name='radio3']:checked").val();
	console.log("radioValue:"+radioValue)
	switch (radioValue) {
	
	//新增保批單明細檔
	case '1':
		console.log("有跑到這1")
		let param = get1304PolicyDtlParam();

		let parJson = JSON.stringify(param);

		let res = ajaxPostByJsonParam("../../rin1304bapi/insertfripolicydtl",parJson, false);

		if (res) {
			alert(res.message)
		}
		break;
		
	//修改保批單明細檔
	case '2':
		console.log("有跑到這2");
		let param2 =get1304PolicyDtlParam();

		let parJson2 = JSON.stringify(param2);

		let res2 = ajaxPostByJsonParam("../../rin1304bapi/updatefripolicydtl",parJson2, false);

		if (res2) {
			alert(res2.message)
		}
		break;
	}
}


/**
 * Rin1304臨分分入，刪除保批單明細
 * @author Sophia 2021/12/07
 */
function btnDeletePolicyDtl(){
	
	let isDelete = confirm("確認要刪除該保批單明細?");
	if(isDelete){
		
		//1-參數
		let param ={
				"txtpolicy_no" : $('#policyNo').val(),  //保單號碼
				"txtendorse_no": $('#endorseNo').val(), //批單號碼 
				"numaddr_no"   : $('#addrNo').val()     //地址序號
		} 
		
		let parJson = JSON.stringify(param);
		
		//2-執行刪除
		let res = ajaxPostByJsonParam("../../rin1304bapi/deletefripolicydtl", parJson, false);
		
		if("000" === res.status){
			alert("刪除保批單明細成功");
			
		}else{
			alert("刪除保批單明細失敗");
		}
	}

}


//取得保批單明細檔畫面欄位值
function get1304PolicyDtlParam(){
	return{
		//-----------保批單明細檔畫面欄位-------------
		"policyNo":$('#txtpolicy_no').val(),       //保單號碼
		"endorseNo":$('#txtendorse_no').val(),     //批單號碼
		"addrNo":$('#numaddr_no').val(),           //保單地址序號
		"riskNo":$('#txtrisk_no').val(),           //同險編號
		"riskFlag":$('#txtrisk_flag').val(),       //是否完成同險(Y/N)
		"riskName":$('#txtrisk_name').val(),       //同險名稱
		"policyDbgn":$('#txtpolicy_dbgn').val(),   //起保日
		"policyDend":$('#txtpolicy_dend').val(),   //訖止日
		"propAddr":$('#txtprop_addr').val(),       //標的物地址
		"zipCode":$('#txtzip_code').val(),         //郵遞區號
		"areaCode":$('#txtarea_code').val(),       //地段代碼
		"usepropCode":$('#txtuseprop_code').val(), //使用性質代號
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
		"":$("#dtlAllAmt").val(),                  //該明細累計保額
		"mercno":$('#txtmercno').val()             //29險種代碼
	}
}

//寫入欄位
function writeFieldForRin1304B(cell){
	
	//.......保批單明細檔........//
	$('#txtpolicy_no').val(cell.txtpolicy_no);      //保單號碼
	$('#txtendorse_no').val(cell.txtendorse_no);    //批單號碼
	
	$('#numaddr_no').val(cell.numaddr_no);          //保單地址序號
	$('#txtrisk_no').val(cell.txtrisk_no);          //同險編號
	$('#txtrisk_flag').val(cell.txtrisk_flag);      //是否完成同險(Y/N)
	$('#txtrisk_name').val(cell.txtrisk_name);      //同險名稱
	$('#txtpolicy_dbgn').val(cell.txtpolicy_dbgn);  //起保日
	$('#txtpolicy_dend').val(cell.txtpolicy_dend);  //訖止日
	$('#txtprop_addr').val(cell.txtprop_addr);      //標的物地址
	$('#txtzip_code').val(cell.txtzip_code);        //郵遞區號
	$('#txtarea_code').val(cell.txtarea_code);      //地段代碼
	$('#txtuseprop_code').val(cell.txtuseprop_code);//使用性質代號
	$('#txtuseprop_name').val(cell.txtuseprop_name);//使用性質名稱
	$('#numFlor_no').val(cell.numFlor_no);          //樓層數
	$('#txtconst_class').val(cell.txtconst_class);  //建築等級英文代號
	$('#limit_no').val(cell.limit_no);              //限額代號
	$('#limit').val(cell.limit);                    //限額
	$('#txtlimit_rate').val(cell.txtlimit_rate);    //限額比率
	$('#numlimit_ori').val(cell.numlimit_ori);      //保單限額
	$('#numamt').val(cell.numamt);                  //異動總保額
	$('#numprem').val(cell.numprem);                //異動總保費 
	$('#numamt_flt').val(cell.numamt_flt);          //流動貨物保額
	$('#numprem_flt').val(cell.numprem_flt);        //流動貨物保費
	$('#numamt_fix').val(cell.numamt_fix);          //非流動貨物保額
	$('#numprem_fix').val(cell.numprem_fix);        //非流動貨物保費
	$('#numamt_typ').val(cell.numamt_typ);          //颱洪本單總保額
	$('#numprem_typ').val(cell.numprem_typ);        //颱洪本單年保費
	$('#numamt_ear').val(cell.numamt_ear);          //地震本單總保費
	$('#numprem_ear').val(cell.numprem_ear);          //地震本單年保費
	$('#sumamt').val(cell.sumamt);                  //該明細累計保額
	$('#txtmercno').val(cell.txtmercno);            //29險種代碼
	
}



//回上一頁
function backTo1304(){
	

	locationHrefKeepDataType2('Rin1304', '', "");
}