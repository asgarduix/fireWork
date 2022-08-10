/**
 * 
 */
$(function() {
	setTimeout(function() {

//		if (true) {
//			return;
//		}

		var funcs = document.URL.split("/");
		var nowfunc = funcs[funcs.length - 1];
		if ("loading" == nowfunc) {
			var t = localStorage.getItem("token");
			var r = localStorage.getItem("refreshToken");
			var a = localStorage.getItem("account");
			logout();// clean cache
			localStorage.setItem("token", t);
			localStorage.setItem("refreshToken", r);
			localStorage.setItem("account", a);
			localStorage.setItem("loginTime", new Date().getTime());

			try {
//				var url1 = domain4Springboot(true) + "/settingApi/fetchSetting";
//				var userId1 = { "userId": localStorage.getItem("account") };
//				var result1 = ajaxGetTokenReady(url1, userId1, true);
//				var pageBg = result1.data["pageBg"];
//				localStorage.setItem("pageBg", pageBg);

//				let url2 = domain4Springboot(true) + "/sysgrant/fetchSysFuuction";
//				let userId2 = {
//					"userId": localStorage.getItem("account")
//				};
//				let res2 = ajaxGetTokenReady(url2, userId2, true);
//				sessionStorage.setItem("permissionSetting", JSON.stringify(res2.data));

				let url3 = domain4Springboot(true) + "/sysgrant/fetchAccountInfo";
				let userId3 = {
					"userId": localStorage.getItem("account")
				};
				let res3 = ajaxGetTokenReady(url3, userId3, true);
				let displayUserId = res3.data.userId
				localStorage.setItem("displayUserId", displayUserId);
				
				// 使用者員編&姓名
//				var userInfo = fetchUserInfo(localStorage.getItem("account"));
//				console.log("5500userInfo", userInfo);
				//				sessionStorage.setItem("emplno", userInfo[0].emplno);
				//				sessionStorage.setItem("emname", userInfo[0].emname.trim());
				// TODO 因 uat 環境尚未設定帳號密碼，這裡會出錯，故先寫死
				sessionStorage.setItem("emplno", "123456");
				sessionStorage.setItem("emname", "測試記得改");

				// 將承作地區存入 localStorage
//				debugger;
//				setDoareaToLocalStorage();

				// jump to index
				location.href = "./index" + "?" + "token=" + localStorage.getItem("token");
			} catch (e) {
				console.log(e);
				alert("取得基本資料發生錯誤");
				location.href = "./login";
			}
		}

	}, 1300);
});


function jump() {
	//		alert();
	var funcs = document.URL.split("/");
	var nowfunc = funcs[funcs.length - 1];
	if ("loading" == nowfunc) {
		var t = localStorage.getItem("token");
		var r = localStorage.getItem("refreshToken");
		var a = localStorage.getItem("account");
		logout();// clean cache
		localStorage.setItem("token", t);
		localStorage.setItem("refreshToken", r);
		localStorage.setItem("account", a);
		localStorage.setItem("loginTime", new Date().getTime());

		try {
			//multi thread mechanism to load ddl data, then process main flow for login
//						setTimeout(function() {
//							preLoadDdlDataBlock1();
//						}, 0);
//						
//						setTimeout(function() {
//							preLoadDdlDataBlock2();
//						}, 0);

			setTimeout(function() {
//								var url1 = domain4Springboot(true) + "/settingApi/fetchSetting";
//								var userId1 = { "userId": localStorage.getItem("account") };
//								var result1 = ajaxGetTokenReady(url1, userId1, true);
//								var pageBg = result1.data["pageBg"];
//								localStorage.setItem("pageBg", pageBg);
//				
//								let url2 = domain4Springboot(true) + "/sysgrant/fetchSysFuuction";
//								let userId2 = { "userId": localStorage.getItem("account") };
//								let res2 = ajaxGetTokenReady(url2, userId2, true);
//								sessionStorage.setItem("permissionSetting", JSON.stringify(res2.data));
//				
//								let url3 = domain4Springboot(true) + "/sysgrant/fetchAccountInfo";
//								let userId3 = { "userId": localStorage.getItem("account") };
//								let res3 = ajaxGetTokenReady(url3, userId3, true);
//								let displayUserId = res3.data.userId
//								localStorage.setItem("displayUserId", displayUserId);
//			
//								// 使用者員編&姓名
//								var userInfo = fetchUserInfo(localStorage.getItem("account"));
//								console.log("5500userInfo", userInfo);
//								//				sessionStorage.setItem("emplno", userInfo[0].emplno);
//								//				sessionStorage.setItem("emname", userInfo[0].emname.trim());
//								// TODO 因 uat 環境尚未設定帳號密碼，這裡會出錯，故先寫死
//								sessionStorage.setItem("emplno", "123456");
//								sessionStorage.setItem("emname", "測試記得改");
//				
//								// 將承作地區存入 localStorage
//								debugger;
//								setDoareaToLocalStorage();

				//			alert();
				// jump to index
				//				location.href = "./index" + "?" + "token=" + localStorage.getItem("token");

				//火再系統首頁為Rin1101
				location.href = "./index" + "?" + "token=" + localStorage.getItem("token");
			}, 3000);
		} catch (e) {
			console.log(e);
			alert("取得基本資料發生錯誤");
			location.href = "./login";
		}
	}
}

async function preLoaDddlData() {
	crtDdlLoadDDLisdiskAsync("div4", "Y");
	crtDdlLoadDDLacttypeAsync("div5", "Y");
	crtDdlLoadDDLjiantypAsync("div6", "Y");
	crtDdlLoadDDLFocpyClsAsync("div15", "Y");
	crtDdlLoadDDLFsexAsync("div16", "Y");
	crtDdlLoadDDLFrenewAsync("div17", "Y");
	crtDdlLoadDDLFriskAsync("div18", "Y");
	crtDdlLoadDDLFrelIssuAsync("div19", "Y");
	crtDdlLoadDDLFrelBeneAsync("div20", "Y");
	crtDdlLoadDDLCusClsAsync("div21", "Y");
	crtDdlLoadDDLCusTypeAsync("div22", "Y");
	crtDdlLoadDDLFcoinsAsync("div23", "Y");
	crtDdlLoadDDLFfacAsync("div24", "Y");
	crtDdlLoadDDLFtrvareaAsync("div25", "Y");
	crtDdlLoadDDLFnationAsync("div26", "Y");
	crtDdlLoadDDLFmedicalAsync("div27", "Y");
	crtDdlLoadDDLFbodyAsync("div28", "Y");
	crtDdlLoadDDLFsocalAsync("div29", "Y");
	crtDdlLoadDDLIcoCompAsync("div30", "Y");
	crtDdlLoadDDLFunitAsync("div31", "Y");
	crtDdlLoadDDLFqdayAsync("div32", "Y");
	crtDdlLoadDDLFunderWritingAsync("div33", "Y");
	crtDdlLoadDDLFbeneTypeAsync("div34", "Y");
	crtDdlLoadDDLItrvConvAsync("div35", "Y");
	crtDdlLoadDDLFpayMethodAsync("div36", "Y");
	crtDdlLoadDDLFbenMethodAsync("div37", "Y");
	crtDdlLoadDDLChannelAsync("div38", "Y");
	crtDdlLoadDDLMainEdrType_GAsync("div45", "Y");
	crtDdlLoadDDLMainEdrTypeAsync("div46", "Y");
	crtDdlLoadDDLObjEdrTypeAsync("div47", "Y");
	crtDdlLoadDDLBENEEdrTypeAsync("div48", "Y");
	crtDdlLoadDDLSCCoSaleDataAsync("div49", "Y");
	crtDdlLoadDDLbarrkindAsync("div50", "Y");
	crtDdlLoadDDLbarrdegrAsync("div51", "Y");
	crtDdlLoadDDLbarrkind_nAsync("div52", "Y");
	crtDdlLoadDDLIINSCLSAsync("div7", "Y");
	crtDdlLoadDDLIINSKindAsync("div8", "Y");
	crtDdlLoadDDLIINSKind_501Async("div9", "Y");
	crtDdlLoadDDLIClauseAsync("div10", "Y");
	crtDdlLoadDDLClauseRelAsync("div11", "Y");
	crtDdlLoadDDLINoticeAsync("div12", "Y");
	crtDdlLoadDDLNoticeRelAsync("div13", "Y");
	crtDdlLoadDDLNCMNpInstypeAsync("div14", "Y");
	crtDdlLoadDDLOcpyDataC_IocpyaAsCodeAsync("div40-1", "Y");
	crtDdlLoadDDLOcpyDataC_Iocpya1AsCodeAsync("div40-2", "Y");
	crtDdlLoadDDLOcpyDataC_Iocpya1AsTypeAsync("div40-3", "Y");
	crtDdlLoadDDLOcpyDataP_IocpyaAsCodeAsync("div41-1", "Y");
	crtDdlLoadDDLOcpyDataP_Iocpya1AsCodeAsync("div41-2", "Y");
	crtDdlLoadDDLOcpyDataP_Iocpya1AsTypeAsync("div41-3", "Y");
	crtDdlLoadDDLOcpyDataPVer_IocpyaAsCodeAsync("div42-1", "Y");
	crtDdlLoadDDLOcpyDataPVer_IocpyaAsTypeAsync("div42-2", "Y");
	crtDdlLoadDDLOcpyDataPVer_Iocpya1AsTypeAsync("div42-3", "Y");
	crtDdlLoadDDLCommonData_IocpyaAsCodeAsync("div43-1", "Y");
	crtDdlLoadDDLCommonData_IocpyaAsTypeAsync("div43-2", "Y");
	crtDdlLoadDDLCommonData02_Iocpya1AsTypeAsync("div43-3", "Y");
	//	alert();
}


async function preLoadDdlDataBlock1() {
	crtDdlLoadDDLFbenMethodAsync("div37", "Y");
	crtDdlLoadDDLChannelAsync("div38", "Y");
	crtDdlLoadDDLMainEdrType_GAsync("div45", "Y");
	crtDdlLoadDDLMainEdrTypeAsync("div46", "Y");
	crtDdlLoadDDLObjEdrTypeAsync("div47", "Y");
	crtDdlLoadDDLBENEEdrTypeAsync("div48", "Y");
	crtDdlLoadDDLSCCoSaleDataAsync("div49", "Y");
	crtDdlLoadDDLbarrkindAsync("div50", "Y");
	crtDdlLoadDDLbarrdegrAsync("div51", "Y");
	crtDdlLoadDDLbarrkind_nAsync("div52", "Y");
	crtDdlLoadDDLIINSCLSAsync("div7", "Y");
	crtDdlLoadDDLIINSKindAsync("div8", "Y");
	crtDdlLoadDDLIINSKind_501Async("div9", "Y");
	crtDdlLoadDDLIClauseAsync("div10", "Y");
	crtDdlLoadDDLClauseRelAsync("div11", "Y");
	crtDdlLoadDDLINoticeAsync("div12", "Y");
	crtDdlLoadDDLNoticeRelAsync("div13", "Y");
	crtDdlLoadDDLNCMNpInstypeAsync("div14", "Y");
	crtDdlLoadDDLOcpyDataC_IocpyaAsCodeAsync("div40-1", "Y");
	crtDdlLoadDDLOcpyDataC_Iocpya1AsCodeAsync("div40-2", "Y");
	crtDdlLoadDDLOcpyDataC_Iocpya1AsTypeAsync("div40-3", "Y");
	crtDdlLoadDDLOcpyDataP_IocpyaAsCodeAsync("div41-1", "Y");
	crtDdlLoadDDLOcpyDataP_Iocpya1AsCodeAsync("div41-2", "Y");
	crtDdlLoadDDLOcpyDataP_Iocpya1AsTypeAsync("div41-3", "Y");
	crtDdlLoadDDLOcpyDataPVer_IocpyaAsCodeAsync("div42-1", "Y");
	crtDdlLoadDDLOcpyDataPVer_IocpyaAsTypeAsync("div42-2", "Y");
	crtDdlLoadDDLOcpyDataPVer_Iocpya1AsTypeAsync("div42-3", "Y");
	crtDdlLoadDDLCommonData_IocpyaAsCodeAsync("div43-1", "Y");
	crtDdlLoadDDLCommonData_IocpyaAsTypeAsync("div43-2", "Y");
	crtDdlLoadDDLCommonData02_Iocpya1AsTypeAsync("div43-3", "Y");
	//	alert();
}

async function preLoadDdlDataBlock2() {
	crtDdlLoadDDLisdiskAsync("div4", "Y");
	crtDdlLoadDDLacttypeAsync("div5", "Y");
	crtDdlLoadDDLjiantypAsync("div6", "Y");
	crtDdlLoadDDLFocpyClsAsync("div15", "Y");
	crtDdlLoadDDLFsexAsync("div16", "Y");
	crtDdlLoadDDLFrenewAsync("div17", "Y");
	crtDdlLoadDDLFriskAsync("div18", "Y");
	crtDdlLoadDDLFrelIssuAsync("div19", "Y");
	crtDdlLoadDDLFrelBeneAsync("div20", "Y");
	crtDdlLoadDDLCusClsAsync("div21", "Y");
	crtDdlLoadDDLCusTypeAsync("div22", "Y");
	crtDdlLoadDDLFcoinsAsync("div23", "Y");
	crtDdlLoadDDLFfacAsync("div24", "Y");
	crtDdlLoadDDLFtrvareaAsync("div25", "Y");
	crtDdlLoadDDLFnationAsync("div26", "Y");
	crtDdlLoadDDLFmedicalAsync("div27", "Y");
	crtDdlLoadDDLFbodyAsync("div28", "Y");
	crtDdlLoadDDLFsocalAsync("div29", "Y");
	crtDdlLoadDDLIcoCompAsync("div30", "Y");
	crtDdlLoadDDLFunitAsync("div31", "Y");
	crtDdlLoadDDLFqdayAsync("div32", "Y");
	crtDdlLoadDDLFunderWritingAsync("div33", "Y");
	crtDdlLoadDDLFbeneTypeAsync("div34", "Y");
	crtDdlLoadDDLItrvConvAsync("div35", "Y");
	crtDdlLoadDDLFpayMethodAsync("div36", "Y");
}
