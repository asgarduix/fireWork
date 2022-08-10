

function exportPDF(){
	
	var radioValue = $("input[name='radio']:checked").val();
	
	var time_in = $('#txt_treaty_year').val().trim();
	
	var a_time_in = time_in.split("/");

	if (a_time_in[0] <= 2050 && a_time_in[0] > 1900 
			&& a_time_in[1] <= 12 && a_time_in[1] > 0 && a_time_in != "") {
		
		if (radioValue == '1') { //立即執行
			
			let param = {
					"treaty_dend":$('#txt_treaty_year').val().trim(),
					"account":localStorage.getItem("account")
			}
			
			let parJson = JSON.stringify(param);
				　		
			let res = ajaxPostByJsonParam("../../rin1302api/exportPDF", parJson, false);
			
			if('000' === res.status){
				
				if(!checkIsNullSpace(res.data.fileBase64Encode)){
					openFile(res.data.fileName +".xls", res.data.fileBase64Encode)
				}else{
					alert("查無資料！");
				}
								
			}else{
				alert("列印失敗!!!請聯絡管理人員!!!");
			}
			
		} else if (radioValue == '2') { //排程執行
		
			var dtaStart = $('#dtaStart').val();
		
			var ddlHour = $('#ddlHour').val();
		
			var ddlMin = $('#ddlMin').val();
		
			var ddlddlMin = $('#ddlddlMin').val();
			
			var submittime = dtaStart+" "+ddlHour+":"+ddlMin+":"+ddlddlMin;
			
			var submittimeDate = new Date(submittime);
			
			var nowtime = new Date();
			
			if (submittimeDate <= nowtime || dtaStart == ''){
				alert("輸入的時間要是未來時間！！！");
				return;
			} else{
				let param = {
						"dtaStart":dtaStart,
						"ddlHour":ddlHour,
						"ddlMin":ddlMin,
						"ddlddlMin":ddlddlMin,
						"treaty_dend":time_in,
						"account":localStorage.getItem("account")
				}
			
				let parJson = JSON.stringify(param);
			
				let res = ajaxPostByJsonParam("../../rin1302api/insertschedulepdf", parJson, false);
				
				alert("成功執行！");
			}
		}
		
	} else{
		alert("日期為西元年/月，日期範圍輸入錯誤！ 請重新輸入！");
	}

}