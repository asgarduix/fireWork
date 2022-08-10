$(function(){
	// 合約年度：預設帶系統日年度
	$('#txt_treaty_year').val(new Date().getFullYear());
	// 帶出小時下拉選00~23
	setTimeData(0, 23, 'ddl_HH');
	// 帶出分鐘下拉選00~59
	setTimeData(0, 59, 'ddl_mm');
	// 帶出秒鐘下拉選00~59
	setTimeData(0, 59, 'ddl_ss');
	// 同險代號帶預設值99999999999 (11碼)
	$('#txt_risk_no').val('99999999999');
	// 預設選取"立即執行"
	$('input[name="radio"]')[0].checked = true;
	
});

//tabulator欄位設置
let columns1204 = [
	[ "areaCode", "地段代號", "display"],
	[ "policyNo", "保單號碼", "display"],
	[ "endorseNo", "批單號碼", "display"]
]

//tabulator欄位格式製作
let colsFormat = createTableColumns(columns1204);

//客製tabulator本體
let tableConfigs = {};

//按鈕設置與功能
let tableRelatedBtns = [];

//檢核警告設定
let alertConfig = {};

//建立table
let table1204 = createTable("table1204", colsFormat, tableConfigs, tableRelatedBtns, alertConfig);

/**
 * 產生時間數字區間下拉選
 * @param beg 起始
 * @param end 結束
 * @param id 欄位id 
 * @returns
 */
function setTimeData(beg, end, id){
	$('#'+id).empty();
	for(var i = beg; i <= end; i++){
		var str = String(i);
		if(i<10){
			str = "0" + str;
		}
		
		let selectOption = document.createElement('option');
		selectOption.value = str; 
		selectOption.textContent= str ;
		let select = document.getElementById(id);
		select.appendChild(selectOption);
	}
}

/**
 * 執行自動分保
 * @returns
 */
$('#autoReinsurance').click(function(){
	try {
		// 1:立即執行, 2:排程執行
		let bootStatus = $("input[name='radio']:checked").val();
		if(checkIsNullSpace(bootStatus)){
			alert("請選擇執行方式");
			return;
		}
		
		let alertMsg = "";
		if(bootStatus == "2" && checkIsNullSpace($('#submitTime').val())){
			alertMsg += "未輸入排程開始日期!" +"\r\n";
		}
		// 檢核合約年度
		if(checkIsNullSpace($('#txt_treaty_year').val())){
			alertMsg += "未輸入合約年度!" +"\r\n";
		}
		// 檢核本次分保日期區間
		if(checkIsNullSpace($('#UcRocDbgn').val()) || checkIsNullSpace($('#UcRocDend').val())){
			alertMsg += "未輸入分保日期區間!" +"\r\n";
		}
		// 檢核同險代號須為11碼
		if($('#txt_risk_no').val().length != 11){
			alertMsg += "同險代號須為11碼!" +"\r\n";
		}
		if(!checkIsNullSpace(alertMsg)){
			alert(alertMsg);
			return;
		}
		
		// 是否執行分保計算
		let isDoReins = false;
		
		// 1. 執行自動分保按鈕檢核邏輯
		// 是否執行分保計算，顯示提示訊息『部分保單尚未做同險設定，是否執行分保計算』
		// 2. 期間內所有保單已完成同險設定
		// 是否執行分保計算，顯示提示訊息『期間內所有保單已完成同險設定，是否執行分保計算？』
		let checkParam = {
			"ucRocDbgn":$('#UcRocDbgn').val(), // 分保起日
			"ucRocDend":$('#UcRocDend').val(), // 分保迄日
			"riskNo":$('#txt_risk_no').val() // 同險代號	
		}
		
		// 查詢同險未處理保單
		let queryRes = ajaxPostByJsonParam("../../rin1204api/queryUnProcPolicy", JSON.stringify(checkParam), false);
		if(queryRes["data"].length != 0){
			// 在下方顯示表格
			loadData("table1204", queryRes.data, {type:"dataCount", value:7});
			setTimeout(function(){isDoReins = confirm("部分保單尚未做同險設定，是否執行分保計算?");doAutoReinsurance(isDoReins, checkParam, bootStatus);},500);
		}else{
			table1204.clearData();
			setTimeout(function(){isDoReins = confirm("期間內所有保單已完成同險設定，是否執行分保計算?");doAutoReinsurance(isDoReins, checkParam, bootStatus);},500);
		}
		
		function doAutoReinsurance(pIsDoReins, pCheckParam, pBootStatus){
			if(pIsDoReins){
				// 檢核區間是否已關帳，並顯示警告訊息，『合約分保區間已關帳，不可執行。
				let isClose = ajaxPostByJsonParam("../../rin1204api/checkIsClose", JSON.stringify(pCheckParam), false);
				if(isClose.data){
					alert("合約分保區間已關帳，不可執行");
					return;
				}
				
				let submitTime = $('#submitTime').val() + " " + $('#ddl_HH').val() 
								+ ":" + $('#ddl_mm').val() + ":" + $('#ddl_ss').val()
				
				let param = {
						"bootStatus":pBootStatus,
						"submitTime":submitTime,
						"account":localStorage.getItem("account"),
						"treatyYear":$('#txt_treaty_year').val(), // 合約年度
						"ucRocDbgn":$('#UcRocDbgn').val(), // 分保起日
						"ucRocDend":$('#UcRocDend').val(), // 分保迄日
						"riskNo":$('#txt_risk_no').val() // 同險代號
				}
				// 執行自動分保排程
				let res = ajaxPostByJsonParam("../../rin1204api/doAutoReinsurance", JSON.stringify(param), false);
				if(res.status==='000'){
					if(bootStatus == '1'){
						alert("執行自動分保成功");
						if(!checkIsNullSpace(res.data)){
							alert(res.data);
						}
					}
					if(bootStatus == '2'){
						alert("已排程執行自動分保");	
					}
				}
				if(res.status==='999'){
					if(bootStatus == '1'){
						alert("執行自動分保失敗，請查看Log");					
					}
					if(bootStatus == '2'){
						alert("已排程執行自動分保失敗");	
					}
				}
			}
		}
		
	} catch (e) {
		alert(e);
	}
});

/**
 * 匯出資料
 * @returns
 */
$('#exportData').click(function(){
	try {
		let jsonObj = {
			"ucRocDbgn":$('#UcRocDbgn').val(), // 分保起日
			"ucRocDend":$('#UcRocDend').val(), // 分保迄日
			"riskNo":$('#txt_risk_no').val() // 同險代號	
		}
		createExcelByPOST("rin1204api", "exportData", jsonObj, "同險未處理保單");
	} catch (e) {
		alert(e);
	}
});

/**
 * 合約年度檢核
 * @returns
 */
$('#txt_treaty_year').change(function(){
	try {
		// 只能為數字
		let regex = /^[0-9\s]*$/;
		if (!regex.test($('#txt_treaty_year').val()) || $('#txt_treaty_year').val().length !=4){
			alert("合約年度格式錯誤");
			// 帶回系統日年度
			$('#txt_treaty_year').val(new Date().getFullYear());
		}
	} catch (e) {
		alert(e);
	}
});

/**
 * 全形轉半形
 * @returns
 */
$('input').keyup(function(){
	try {
		$(this).val(ascType2($(this).val()));
	} catch (e) {
		alert(e);
	}
});

/**
 * 日期格式檢核
 * @returns
 */
$('#submitTime, #UcRocDbgn, #UcRocDend').change(function(){
	try {
		if(!isValidDate($(this).val())){
			alert("日期格式錯誤，請輸入YYYY/MM/DD或是YYYY/M/D");
			$(this).val('');
		}
	} catch (e) {
		alert(e);
	}
});

/**
 * 合約年度與區間年度需相同
 * @returns
 */
$('#txt_treaty_year, #UcRocDbgn, #UcRocDend').change(function(){
	try {
		let isMsg = false;
		// 合約年度
		let txt_treaty_year = $('#txt_treaty_year').val();
		// 本次分保日期區間(起)
		let UcRocDbgn = $('#UcRocDbgn').val();
		// 本次分保日期區間(迄)
		let UcRocDend = $('#UcRocDend').val();
		let arr1 = UcRocDbgn.split("/");
		if(arr1.length == 3){
			if(txt_treaty_year != arr1[0]){
				isMsg = true;
				$('#UcRocDbgn').val('');
			}
		}
		let arr2 = UcRocDend.split("/");
		if(arr2.length == 3){
			if(txt_treaty_year != arr2[0]){
				isMsg = true;
				$('#UcRocDend').val('');
			}
		}
		if(isMsg){
			alert("合約年度與區間年度需相同");
		}
	} catch (e) {
		alert(e);
	}
});
