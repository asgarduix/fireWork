const RINCOMID_1101 = locationHrefKeepDataFetch();   //由「RIN1101」頁面帶入的參數:rinComId(再保人代號)、searchHistory(1101搜尋紀錄)


/*
 * 預設
 */
$(function(){
	
	//查詢頁面資料
	queryRin1101A();
	//查詢評等資料
	queryFriComCredit();
	
	
})



/**
 * 查詢頁面資料
 * @author yiting 2021/09/28
 */
function queryRin1101A(){
	
	//1-參數
	let param ={
		"rinComId" : RINCOMID_1101.rinComId	 //再保人代號		
	} 
	
	//2-執行查詢
	ajaxRequestIsAsyncDynimicBytoken("../../rin1101aapi/queryonereiner", true, false, param,     
			(res) => {
						if (res != null && "000" === res.status) {
							writeFieldForRin1101A(res.data[0]);
						}else{
							alert("「再保人代號」單筆查詢失敗!!!請聯絡管理人員!!!");
						}
			}, (error) => {
							console.log(error);
							alert("「再保人代號」單筆查詢失敗!!!請聯絡管理人員!!!");
			})
		
}


//寫入欄位
function writeFieldForRin1101A(cell){
	
	$('#txtOCODE').val(cell.ocode);						//保發代號
	$('#txtrin_com_id').val(cell.rinComId);				//再保人代號
	$('#txtename').val(cell.ename);						//再保人英文名稱
	$('#txtcname').val(cell.cname);						//再保人中文名稱
	$('#txtacct_area').val(cell.acctArea);				//國別
	//業務往來型態-1
	if(cell.favtyp){		
		switch(cell.favtyp.trim()){
		case 'F':
			$('#txtfavtyp').val("F:臨分");
			break;
		case 'T':
			$('#txtfavtyp').val("T:合約");
			break;
		case 'B':
			$('#txtfavtyp').val("B:合約及臨分");
			break;
		default:
			$('#txtfavtyp').val("");
		}
	}
	
	//業務往來型態-2
	if(cell.inout){		
		switch(cell.inout.trim()){
		case 'A':
			$('#txtinout').val("A:分入");
			break;
		case 'C':
			$('#txtinout').val("C:分出");
			break;
		case 'B':
			$('#txtinout').val("B:中性業務(中止使用)");
			break;
		case 'T':
			$('#txtinout').val("T:分出及分入");
			break;
		default:
			$('#txtinout').val("");
		}
	}
	

	//業務往來部門
	if(cell.carmrk && "Y" == cell.carmrk.trim()){		
		$('#ChkCheckbox_carmrk').prop("checked", true);	//車險
	}
	if(cell.marmrk && "Y" == cell.marmrk.trim()){		
		$('#ChkCheckbox_marmrk').prop("checked", true);	//水險
	}
	if(cell.firmrk && "Y" == cell.firmrk.trim()){		
		$('#ChkCheckbox_firmrk').prop("checked", true);	//火險
	}
	if(cell.accmrk && "Y" == cell.accmrk.trim()){		
		$('#ChkCheckbox_accmrk').prop("checked", true);	//意外險
	}
	if(cell.ahmrk && "Y" == cell.ahmrk.trim()){		
		$('#ChkCheckbox_ahmrk').prop("checked", true);	//健康險
	}
	
	$('#txtremark1').val(cell.remark1);					//備註(再保)
	
	//適格
	if("Y" == cell.shige){
		$('#txtshige').val("Y 適格");					
	}else if("N" == cell.shige){
		$('#txtshige').val("N 不適格");					
	}
	//觀察名單
	if("Y" == cell.watchto){
		$('#txtwatchto').val("Y 觀察名單");				
	}else if("N" == cell.watchto){
		$('#txtwatchto').val("N 非觀察名單");		
	}
	//另案簽報
	if("Y" == cell.signnok){
		$('#txtsignnok').val("Y 另案簽報");				
	}else if("N" == cell.signnok){
		$('#txtsignnok').val("N 非另案簽報");		
	}

	$('#dtablocklst').val(cell.blocklst);				//封鎖起始日

	
}



/**
 * 查詢評等資料
 * @author yiting 2021/09/28
 */
function queryFriComCredit(){
	
	//1-參數
	let param ={
		"rinComId" : RINCOMID_1101.rinComId	 //再保人代號		
	} 
	
	let parJson = JSON.stringify(param);
	
	//2-執行查詢
	let res = ajaxPostByJsonParam("../../rin1101aapi/querycredit", parJson, false);
	
	if("000" === res.status){
		//3-顯示
		writeFieldForTable(res.data)
	}else{
		alert("「評等資料」查詢失敗!!!請聯絡管理人員!!!");
	}
}


/**
 * 產生評等資料表格
 * @author yiting 2021/09/28
 */
function writeFieldForTable(cell){
	
//	var creditTable = "<tr class='cssDataGridHeader'>" +
//			"<th>信評機構</th><th>評等日期</th><th>信評等級</th></tr>"
//	
//	for(let i = 0; i < cell.length; i++){
//		
//		creditTable +="<tr><td>"+cell[i].creditOrgan+"</td>"+
//						"<td>"+cell[i].txtCreditDate+"</td>"+
//						"<td>"+cell[i].creditLevel+"</td></tr>"
//		
//	}
//	
//	$('#creditTable').html(creditTable);
	
//========================================================================

	//產生表頭
	let headTr = document.createElement('tr');	
	let headTh1 = document.createElement('th');
	headTh1.textContent="信評機構";
	let headTh2 = document.createElement('th');
	headTh2.textContent="評等日期";
	let headTh3 = document.createElement('th');
	headTh3.textContent="信評等級";
	
	document.querySelector('#creditTable').appendChild(headTr);
	headTr.appendChild(headTh1);
	headTr.appendChild(headTh2);
	headTr.appendChild(headTh3);
	
	headTr.style.height = '25px'
	headTr.style.lineHeight = '25px'
	headTr.style.borderBottom = '1.2px solid  #E4D7C1'
	
	//產生表格內容
	for(let i = 0; i < cell.length; i++){
		let contentTr = document.createElement('tr');
		let contentTd1 = document.createElement('td');
		contentTd1.textContent = cell[i].creditOrgan;
		let contentTd2 = document.createElement('td');
		contentTd2.textContent = cell[i].txtCreditDate;
		let contentTd3 = document.createElement('td');
		contentTd3.textContent = cell[i].creditLevel;
		
		document.querySelector('#creditTable').appendChild(contentTr);
		contentTr.appendChild(contentTd1);
		contentTr.appendChild(contentTd2);
		contentTr.appendChild(contentTd3);
		
		contentTr.style.borderBottom = '1.2px solid  #E4D7C1'
	}
	
}


//回上一頁
function backTo1101(){
	
	let data = {
			"searchHistory" : RINCOMID_1101.searchHistory		//再保人代號搜尋條件
	}
	let parJson = JSON.stringify(data)
	locationHrefKeepDataType2('Rin1101', '', parJson);
}

