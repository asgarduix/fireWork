//=========================================================
//原系統程式碼中好像可以沿用的部分
//=========================================================
//** 可用 */
//檢查日期是否符合標準
function chkDate(datestr) {
	var year, month, day;
	var pattern = /^\d{4}\/\d{1,2}\/\d{1,2}$/;
	var tmpary = new Array()

	if (!pattern.test(datestr)) return false;

	tmpary = datestr.split("/");
	year = tmpary[0];
	month = tmpary[1];
	day = tmpary[2];

	if (month < 1 || month > 12 || day > 31 || day < 1) return false;

	if (month == 2 && day > 28) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) { // 為閏年 
			if (day > 29) return false;
		}
		else {  // 非閏年 
			return false;
		}
	}
	if (day > 30 && ((month % 2) == Math.floor(month / 8))) return false;

	return true;
}


//** 可用 */
//照會日期 Get 照會期限 
function GetReplyterm(objNotedate, objReplyterm) {
	//無輸入照會日期
	if (objNotedate.value == "") {
		objNotedate.focus();
		return "";
	}

	var arr1
	var NextPly
	var tmpDate

	arr1 = objNotedate.value.split("/");
	tmpDate = (parseInt(arr1[0], 10) + 1911).toString() + "/" + arr1[1] + "/" + arr1[2];
	if (chkDate(tmpDate) == false) //照會日期格式錯誤
	{
		objNotedate.focus();
		return "";
	}

	var d1 = new Date();

	d1.setFullYear(parseInt(arr1[0], 10) + 1911);
	d1.setMonth(parseInt(arr1[1], 10) - 1);
	d1.setDate(arr1[2]);

	var d = new Date(Date.parse(d1) + (86400000 * 10));
	var sYear
	var sMonth
	var sDay

	if ((d.getFullYear() - 1911) < 100) {
		sYear = "0" + (d.getFullYear() - 1911).toString();
	}
	else {
		sYear = (d.getFullYear() - 1911).toString();
	}

	if ((d.getMonth() + 1) < 10) {
		sMonth = "0" + (d.getMonth() + 1).toString();
	}
	else {
		sMonth = (d.getMonth() + 1).toString();
	}

	if (d.getDate() < 10) {
		sDay = "0" + d.getDate().toString();
	}
	else {
		sDay = d.getDate().toString();
	}

	NextPly = sYear.toString() + "/" + sMonth.toString() + "/" + sDay.toString();

	objReplyterm.value = NextPly.toString();
	objReplyterm.focus();
}


/** 可用，但參數格式不明，應該是yyyy/MM/dd */
//保期起期Get NextYear Ply 
function GetNextYearDply(objBgn, objEnd, sKind) {
	//無輸入起期
	if (objBgn.value == "") {
		objBgn.focus();
		return "";
	}

	//前三碼為保險起期-年
	var arr1
	var NextPly
	var tmpDate

	arr1 = objBgn.value.split("/");
	tmpDate = (parseInt(arr1[0], 10) + 1911).toString() + "/" + arr1[1] + "/" + arr1[2];

	//起期日期格式錯誤
	if (chkDate(tmpDate) == false) {
		objBgn.focus();
		return "";
	}

	if (sKind.value == "308" || sKind.value == "309" || sKind.value == "410" || sKind.value == "311" || sKind.value == "221" || sKind.value == "312" || sKind.value == "GTA") {
		return "";
	}

	var d = new Date();
	var d1 = new Date();

	d1.setFullYear(parseInt(arr1[0], 10) + 1911);
	d1.setMonth(parseInt(arr1[1], 10) - 1);
	d1.setDate(arr1[2]);
	var d2 = (d - d1) / 86400000;

	if (d2 > 60) {
		alert('承保起期不可小於系統日60天，須送審!!!');
		//objBgn.value='';
		//objBgn.focus();
		//return""; 
	}

	if (parseInt(arr1[1], 10) == 2 && parseInt(arr1[2], 10) > 28) {
		if ((parseInt(arr1[0], 10) + 1) > 99) {
			NextPly = (parseInt(arr1[0], 10) + 1).toString() + "/" + arr1[1] + "/" + "28";
		}
		else {
			NextPly = "0" + (parseInt(arr1[0], 10) + 1).toString() + "/" + arr1[1] + "/" + "28";
		}
	}
	else {
		if ((parseInt(arr1[0], 10) + 1) > 99) {
			NextPly = (parseInt(arr1[0], 10) + 1).toString() + "/" + arr1[1] + "/" + arr1[2];
		}
		else {
			NextPly = "0" + (parseInt(arr1[0], 10) + 1).toString() + "/" + arr1[1] + "/" + arr1[2];
		}
	}

	objEnd.value = NextPly.toString();
	objEnd.focus();
}

/**好像可用，看起來只是比較日期，格式應該是yyyy/MM/dd */
//---比較 職業等級對照檔是否須要更換新版
function ocpyver_compare(StrDate, CheckverDate) {
	var arr = StrDate.split("/");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();

	var arr = CheckverDate.split("/");
	var chktime = new Date(arr[0], arr[1], arr[2]);
	var chktimes = chktime.getTime();

	if (starttimes >= chktimes) {
		return true;
	} else {
		return false;
	}
}

//** 可用 */
//---計算投保天數
function GetDplyDay(objBgn, objTBgn, objEnd, objTEnd, objDplyDay, sKind) {
	var arr1, arr2
	var tmpDate

	//無輸入起期
	if (objBgn.value == "") {
		objBgn.focus();
		return "";
	}

	arr1 = objBgn.value.split("/");
	tmpDate = (parseInt(arr1[0], 10) + 1911).toString() + "/" + arr1[1] + "/" + arr1[2];

	//起期日期格式錯誤
	if (chkDate(tmpDate) == false) {
		objBgn.focus();
		return "";
	}

	if (objTBgn.value == "") {
		alert('承保起期時間無輸入');
		objTBgn.focus();
		return "";
	}

	if (parseInt(objTBgn.value, 10) > 24 || parseInt(objTBgn.value, 10) < 0) {
		alert('承保起期時間只能輸入00-24');
		objTBgn.focus();
		return "";
	}

	if (objEnd.value == "") //無輸入迄期

	{
		objEnd.focus();
		return "";
	}

	arr2 = objEnd.value.split("/");
	tmpDate = (parseInt(arr2[0], 10) + 1911).toString() + "/" + arr2[1] + "/" + arr2[2];

	//迄期日期格式錯誤
	if (chkDate(tmpDate) == false) {
		objEnd.focus();
		return "";
	}

	if (objTEnd.value == "") {
		alert('承保迄期時間無輸入');
		objTEnd.focus();
		return "";
	}

	if (parseInt(objTEnd.value, 10) > 24 || parseInt(objTEnd.value, 10) < 0) {
		alert('承保迄期時間只能輸入00-24');
		objTEnd.focus();
		return "";
	}

	var d1 = new Date();
	var d2 = new Date();

	if (objTBgn.value == 24) {
		arr1[2] = eval(arr1[2]) + 1;
	}
	d1.setFullYear(parseInt(arr1[0], 10) + 1911);
	d1.setMonth(parseInt(arr1[1], 10) - 1, arr1[2]);

	if (objTBgn.value == 24) {
		d1.setHours(0);
	} else {
		d1.setHours(eval(objTBgn.value));
	}

	d1.setMinutes(0);
	d1.setSeconds(0);

	//d1.setDate(arr1[2]);

	if (objTEnd.value == 24) {
		arr2[2] = eval(arr2[2]) + 1;
	}
	d2.setFullYear(parseInt(arr2[0], 10) + 1911);
	//var m2=parseInt(arr2[1],10)-1;
	d2.setMonth(parseInt(arr2[1], 10) - 1, arr2[2]);

	if (objTEnd.value == 24) {
		d2.setHours(0);
	}
	else {
		d2.setHours(eval(objTBgn.value));
	}
	d2.setMinutes(0);
	d2.setSeconds(0);

	//d2.setDate(arr2[2]);

	if (Date.parse(d1) - Date.parse(d2) > 0) {
		alert('承保迄期不可小於承保起期');
		objEnd.focus();
		return "";
	}
	else {
		if (Date.parse(d1) - Date.parse(d2) == 0) {
			if (parseInt(objTBgn.value, 10) > parseInt(objTEnd.value, 10) || parseInt(objTBgn.value, 10) == parseInt(objTEnd.value, 10)) {
				alert('承保迄期不可等於承保起期');
				objTEnd.focus();
				return "";
			}
		}
	}

	if (parseInt(objTEnd.value, 10) > parseInt(objTBgn.value, 10)) {
		objDplyDay.value = (parseInt(Math.abs(d2 - d1) / 86400000) + 1).toString();
	}
	else {
		objDplyDay.value = eval(Math.abs(d2 - d1) / 86400000).toString();
	}
	if (sKind.value == "308" || sKind.value == "309" || sKind.value == "410" || sKind.value == "311" || sKind.value == "221" || sKind.value == "312" || sKind.value == "GTA") {
		if (parseInt(objDplyDay.value) > 180) {
			alert('保險期間不可超過180天!');
			objEnd.value = '';
			objDplyDay.value = '';
			objEnd.focus();
			return "";
		}
	}
	objDplyDay.value = Math.ceil(eval(objDplyDay.value));
	//if (objTEnd.value=="24")
	//{
	//   var dobj=parseInt(objDplyDay.value);
	//   var dobj1=dobj+1;
	//   objDplyDay.value=dobj1;
	//}
}

//** 可用，但有類似重複的 */
/*
//---計算年齡-----
function GetAge(vbeg,vend,vres)
{
			
	vbeg=document.all(vbeg)
	vend=document.all(vend)
	vres=document.all(vres)
			
	
	if(vbeg.value=='')
	{
		return false;
	}
	
	if (vend.value=='' ){
		return false;
	}

	var beg=vbeg.value.split('/');
	var end=vend.value.split('/');
	var end1=Number(end[1]);
	var beg1=Number(beg[1]);
	var end2=Number(end[2]);
	var beg2=Number(beg[2]);
				
	if(end1>beg1)
	{
		if( end1 - beg1 > 6 )
		{
			vres.value=Number(end[0])-Number(beg[0])+1;
		}
		else if( end1 - beg1 == 6 )
		{
			if( end2>beg2)
			{
				vres.value=Number(end[0])-Number(beg[0])+1;
			}
			else
			{
				vres.value=Number(end[0])-Number(beg[0]);
			}
		}
		else
		{
			vres.value=Number(end[0])-Number(beg[0]);
		}
	}
	if(end1<beg1)
	{
		vres.value=Number(end[0])-Number(beg[0])-1;
	}
	if(end1==beg1)
	{
		  if(end2>=beg2)
		  {
			   vres.value=Number(end[0])-Number(beg[0]);
		  }else{
			  vres.value=Number(end[0])-Number(beg[0])-1;
		  }
	}
}


//** 可用，但有類似重複的 */
//---計算年齡-----
function GetAge(vbeg, vend, vres) {

	vbeg = document.all(vbeg)//保險起期
	vend = document.all(vend)//生日
	vres = document.all(vres)


	if (vbeg.value == '') {
		vres.value = 0;
		return false;
	}

	if (vend.value == '') {
		vres.value = 0;
		return false;
	}

	var end = vbeg.value.split('/');
	var beg = vend.value.split('/');
	var end0 = Number(end[0]);
	var beg0 = Number(beg[0]);
	var end1 = Number(end[1]);
	var beg1 = Number(beg[1]);
	var end2 = Number(end[2]);
	var beg2 = Number(beg[2]);

	var ObjMonth = end0 * 12 + end1;
	var PolicyMonth = beg0 * 12 + beg1;

	var CurrentMonth = PolicyMonth - ObjMonth;
	var CurrentYear = parseInt(CurrentMonth / 12);
	var CurrentMonth = CurrentMonth % 12;

	if (CurrentMonth == 6)//當為6個月時要確認是否為生日相同日期 若為同一天則不加一歲 若大於生日日期 則加一歲

	{
		if (beg2 > end2) {
			CurrentMonth += 1;
		}
	}

	if (CurrentMonth > 6) {
		CurrentYear += 1;
	}

	vres.value = CurrentYear;
}

//** 可用，但有類似重複的 */
//---計算年齡-----
function GetAge2(sbeg, send) {
	//生日
	if (sbeg == '') {
		return "";
	}

	//起日
	if (send == '') {
		return "";
	}

	var end = sbeg.split('/');
	var beg = send.split('/');
	var end0 = Number(end[0]);
	var beg0 = Number(beg[0]);
	var end1 = Number(end[1]);
	var beg1 = Number(beg[1]);
	var end2 = Number(end[2]);
	var beg2 = Number(beg[2]);

	var ObjMonth = end0 * 12 + end1;
	var PolicyMonth = beg0 * 12 + beg1;

	var CurrentMonth = PolicyMonth - ObjMonth;
	var CurrentYear = parseInt(CurrentMonth / 12);
	var CurrentMonth = CurrentMonth % 12;

	//當為6個月時要確認是否為生日相同日期 若為同一天則不加一歲 若大於生日日期 則加一歲
	if (CurrentMonth == 6) {
		if (beg2 > end2) {
			CurrentMonth += 1;
		}
	}

	if (CurrentMonth > 6) {
		CurrentYear += 1;
	}

	return CurrentYear.toString();
}

//=========================================================
//作用不明
//=========================================================

//** res不明 */
//檢測被保險人是否為黑名單
function CheckRejClientR(res) {
	var i, j;
	var s;

	if (res.error != null) {
		alert(res.error);
		return;
	}
	var tbl = res.value.Tables[0];
	var cText = res.context;
	var ctl1 = cText[0];
	var r;

	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		alert('被保險人為黑名單人員');
	}
	else {
		alert('被保險人不為黑名單');
	}
}



//作用不明?
function CheckBeneCnt() {
	if (Form1.elements["ddlFbene"].value == 'Y') {
		Form1.elements["ddlBeneCnt"].value = "1";
		Form1.elements["ddlBeneCnt"].disabled = false;
		Form1.elements["ddlBeneCnt"].focus();
	}
	else {
		Form1.elements["ddlBeneCnt"].value = "0";
		Form1.elements["ddlBeneCnt"].disabled = true;
		Form1.elements["btnObjInsert"].focus();
	}
	window_onload();

}




//作用不明?
function JsToUpperText() {
	if ((window.event.keyCode >= 97) && (window.event.keyCode <= 122)) {
		window.event.keyCode = window.event.keyCode - 32;
	}
}



//** 代號不確定是不是一樣 */
//---輸入身分證號帶出性別-----
function GetFsex(viissu, vfissucls, vfsex) {
	viissu = document.all(viissu)
	vfissucls = document.all(vfissucls)
	vfsex = document.all(vfsex)

	if (vfissucls.value == '1') {
		if (viissu.value.substring(1, 2) == '1') {
			if (vfsex != null) {
				vfsex.value = '1';
			}
		}
		else {
			if (vfsex != null) {
				vfsex.value = '2';
			}
		}
	}
	else {
		if (vfsex != null) {
			vfsex.value = '3';
		}
	}
}

//** 代號不確定是不是一樣 */
//---要保人為法人帶出與被保險人關係6雇主-----
function GetAssakin() {
	if (Form1.elements["ddlAssakin"]) {
		if (Form1.elements["ddlFinsCls"].value == '2')//Form1.elements["Tabstrip2"].selectedIndex==1
		{
			Form1.elements["ddlAssakin"].value = "5";
		}
		else {
			//Form1.elements["ddlAssakin"].value = "1";
		}
		window_onload();
	}
}

//** 應不符現有系統了 */
//隱藏資料...作用不明?
function VisabletxtItrvMed() {
	if (Form1.elements["chkFtrvMed"].checked == true) {
		Form1.elements["txtItrvMed"].hidden = true;
	}
	else {
		Form1.elements["txtItrvMed"].hidden = false;
	}

}

//** 應不符現有系統了 */
// add by neo 97.03.08
function CheckFsex() //性別
{

	if (Form1.elements["ddlObjFissuCls"].value == '1') {
		if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '1') {
			Form1.elements["ddlFsex"].value = '1';
		}
		else {
			Form1.elements["ddlFsex"].value = '2';
		}
	}
}

//===========================================================
//欄位複製
//===========================================================

function CopyAddr() {
	if (Form1.elements["ucAins_txtZip"] != null)
		Form1.elements["ucAsnd_txtZip"].value = Form1.elements["ucAins_txtZip"].value;
	if (Form1.elements["ucAins_txtAddr"] != null)
		Form1.elements["ucAsnd_txtAddr"].value = Form1.elements["ucAins_txtAddr"].value;

	return false;
}
function CopyAddr2() {
	if (Form1.elements["ucAsnd_txtZip"] != null)
		Form1.elements["ucACOLL_txtZip"].value = Form1.elements["ucAsnd_txtZip"].value;
	if (Form1.elements["ucAsnd_txtAddr"] != null)
		Form1.elements["ucACOLL_txtAddr"].value = Form1.elements["ucAsnd_txtAddr"].value;

	return false;
}
function CopyAddr3() {
	if (Form1.elements["ucAreg_txtZip"] != null)
		Form1.elements["ucAissu_txtZip"].value = Form1.elements["ucAreg_txtZip"].value;
	if (Form1.elements["ucAreg_txtAddr"] != null)
		Form1.elements["ucAissu_txtAddr"].value = Form1.elements["ucAreg_txtAddr"].value;

	return false;
}


function CopyAddr4() {
	//戶籍地址  
	if (Form1.elements["ucAins_txtZip"] != null)
		Form1.elements["ucAreg_txtZip"].value = Form1.elements["ucAins_txtZip"].value;
	if (Form1.elements["ucAins_txtAddr"] != null)
		Form1.elements["ucAreg_txtAddr"].value = Form1.elements["ucAins_txtAddr"].value;

	//通訊地址
	if (Form1.elements["ucAsnd_txtZip"] != null && Form1.elements["ucAissu_txtZip"] != null) {
		Form1.elements["ucAissu_txtZip"].value = Form1.elements["ucAsnd_txtZip"].value;
	}
	if (Form1.elements["ucAsnd_txtAddr"] != null && Form1.elements["ucAissu_txtAddr"] != null) {
		Form1.elements["ucAissu_txtAddr"].value = Form1.elements["ucAsnd_txtAddr"].value;
	}

	//公司電話
	if (Form1.elements["txtNinsOtep"] != null)
		Form1.elements["txtNISSU_OTEP"].value = Form1.elements["txtNinsOtep"].value;
	if (Form1.elements["txtNinsOtel"] != null)
		Form1.elements["txtNISSU_OTEL"].value = Form1.elements["txtNinsOtel"].value;
	if (Form1.elements["txtNinsOtex"] != null)
		Form1.elements["txtNISSU_OTEX"].value = Form1.elements["txtNinsOtex"].value;

	//住家電話
	if (Form1.elements["txtNinsTep"] != null)
		Form1.elements["txtNISSU_TEP"].value = Form1.elements["txtNinsTep"].value;
	if (Form1.elements["txtNinsTel"] != null)
		Form1.elements["txtNISSU_TEL"].value = Form1.elements["txtNinsTel"].value;
	if (Form1.elements["txtNinsTex"] != null)
		Form1.elements["txtNISSU_TEX"].value = Form1.elements["txtNinsTex"].value;

	//傳真
	if (Form1.elements["txtNinsFap"] != null)
		Form1.elements["txtNISSU_FAP"].value = Form1.elements["txtNinsFap"].value;
	if (Form1.elements["txtNinsTel"] != null)
		Form1.elements["txtNISSU_FAX"].value = Form1.elements["txtNinsFax"].value;

	//手機
	if (Form1.elements["txtNinsMob"] != null)
		Form1.elements["txtNISSU_MOB"].value = Form1.elements["txtNinsMob"].value;

	Form1.elements["txtObjNissu"].focus();
	return false;
}


function CopyIssu() {

	if (Form1.elements["ddlFinsCls"] != null)
		Form1.elements["ddlObjFissuCls"].value = Form1.elements["ddlFinsCls"].value;

	if (Form1.elements["txtIins_txtCode"] != null) {
		Form1.elements["txtFisMain"].value = Form1.elements["txtIins_txtCode"].value;
		Form1.elements["txtObjIissu_txtCode"].value = Form1.elements["txtIins_txtCode"].value;
	}

	if (Form1.elements["txtIins_txtCodeCheck"] != null) {
		Form1.elements["txtObjIissu_txtCodeCheck"].value = Form1.elements["txtIins_txtCodeCheck"].value;
	}

	if (cboFinsTyp != null)
		cboObjFissuTyp.value = cboFinsTyp.value;

	if (Form1.elements["txtNins"] != null) {
		Form1.elements["txtObjNissu"].value = Form1.elements["txtNins"].value;
	}


	//戶籍地址  
	if (Form1.elements["ucAins_txtZip"] != null)
		Form1.elements["ucAreg_txtZip"].value = Form1.elements["ucAins_txtZip"].value;
	if (Form1.elements["ucAins_txtAddr"] != null)
		Form1.elements["ucAreg_txtAddr"].value = Form1.elements["ucAins_txtAddr"].value;

	//通訊地址
	if (Form1.elements["ucAsnd_txtZip"] != null && Form1.elements["ucAissu_txtZip"] != null) {
		Form1.elements["ucAissu_txtZip"].value = Form1.elements["ucAsnd_txtZip"].value;
	}
	if (Form1.elements["ucAsnd_txtAddr"] != null && Form1.elements["ucAissu_txtAddr"] != null) {
		Form1.elements["ucAissu_txtAddr"].value = Form1.elements["ucAsnd_txtAddr"].value;
	}

	//生日
	if (Form1.elements["UcRocIinsbrd_txtDate"] != null) {
		Form1.elements["UcrocDbirth_txtDate"].value = Form1.elements["UcRocIinsbrd_txtDate"].value;

		if (Form1.elements["UcRocDplyBgn_txtDate"] != null) {
			if (Form1.elements["txtAge"] != null) {
				Form1.elements["txtAge"].value = GetAge2(Form1.elements["UcrocDbirth_txtDate"].value, Form1.elements["UcRocDplyBgn_txtDate"].value);
			}
		}
	}

	//公司電話
	if (Form1.elements["txtNinsOtep"] != null)
		Form1.elements["txtNISSU_OTEP"].value = Form1.elements["txtNinsOtep"].value;
	if (Form1.elements["txtNinsOtel"] != null)
		Form1.elements["txtNISSU_OTEL"].value = Form1.elements["txtNinsOtel"].value;
	if (Form1.elements["txtNinsOtex"] != null)
		Form1.elements["txtNISSU_OTEX"].value = Form1.elements["txtNinsOtex"].value;

	//住家電話
	if (Form1.elements["txtNinsTep"] != null)
		Form1.elements["txtNISSU_TEP"].value = Form1.elements["txtNinsTep"].value;
	if (Form1.elements["txtNinsTel"] != null)
		Form1.elements["txtNISSU_TEL"].value = Form1.elements["txtNinsTel"].value;
	if (Form1.elements["txtNinsTex"] != null)
		Form1.elements["txtNISSU_TEX"].value = Form1.elements["txtNinsTex"].value;

	//傳真
	if (Form1.elements["txtNinsFap"] != null)
		Form1.elements["txtNISSU_FAP"].value = Form1.elements["txtNinsFap"].value;
	if (Form1.elements["txtNinsTel"] != null)
		Form1.elements["txtNISSU_FAX"].value = Form1.elements["txtNinsFax"].value;

	//手機
	if (Form1.elements["txtNinsMob"] != null)
		Form1.elements["txtNISSU_MOB"].value = Form1.elements["txtNinsMob"].value;

	//家庭年收入
	if (Form1.elements["txtNrecrmk2"] != null)
		Form1.elements["txtMrisk_amt"].value = Form1.elements["txtNrecrmk2"].value;


	Form1.elements["txtObjNissu"].focus();

	//ctl1:編號  ctl2:id  ctl3:國職  ctl4:性別
	if (Form1.elements["ddlObjFissuCls"].value == '1') {
		if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '1') {
			if (Form1.elements["ddlFsex"] != null) {
				Form1.elements["ddlFsex"].value = '1';
			}
		}
		else {
			if (Form1.elements["ddlFsex"] != null) {
				Form1.elements["ddlFsex"].value = '2';
			}
		}
	}
	GetJobNo('txtObjIissu_txtCode', 'ucIocpy_txtOcpy');

	//要保人 國籍(註冊地)
	if (Form1.elements["ddlcitcodM"] != null)
		Form1.elements["ddlcitcodD"].value = Form1.elements["ddlcitcodM"].value;
	//要保人 國籍代號及名稱
	if (Form1.elements["ddlcntcodM"] != null)
		Form1.elements["ddlcntcodD"].value = Form1.elements["ddlcntcodM"].value;

	//要保人 職業/行業
	if (Form1.elements["ddljobcodM"] != null)
		Form1.elements["ddljobcodD"].value = Form1.elements["ddljobcodM"].value;

	return false;
}

//複製ct11值到ct12
function CopyObjValue(ctl1, ctl2) {
	if (ctl1.value == '') {
		return true;
	}

	ctl2.value = ctl1.value;

}

//複製(ct11值/10)到ct12
function CopyChangeObjValue(ctl1, ctl2) {
	if (ctl1.value == '') {
		return true;
	}

	ctl2.value = (ctl1.value) / 10;

}












//===========================================================
//呼叫後端
//===========================================================

// 取得被保人資料 Ajax  被保險人編號id,姓名p1,生日p2,身分別p3,戶籍地址區號p4,戶籍地址p5,共同行銷註記p6,性別p7,通訊地址區號p8,通訊地址p9,職業等級p10,名稱p11,身高p12,體重p13,
// 公司電話區號p14,公司電話p15,公司電話分機p16,住家電話區號p17,住家電號p18,傳真區號p19,傳真p20,婚姻狀況p21,職業代號p22,行動電話p23
function GetISSU_PERSONID(id, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23)   //後端
{
	var objID = document.getElementById(id);
	var o1 = document.getElementById(p1);
	var o2 = document.getElementById(p2);
	var o3 = document.getElementById(p3);
	var o4 = document.getElementById(p4);
	var o5 = document.getElementById(p5);
	var o6 = document.getElementById(p6);
	var o7 = document.getElementById(p7);
	var o8 = document.getElementById(p8);
	var o9 = document.getElementById(p9);
	var o10 = document.getElementById(p10);
	var o11 = document.getElementById(p11);
	var o12 = document.getElementById(p12);
	var o13 = document.getElementById(p13);
	var o14 = document.getElementById(p14);
	var o15 = document.getElementById(p15);
	var o16 = document.getElementById(p16);
	var o17 = document.getElementById(p17);
	var o18 = document.getElementById(p18);
	var o19 = document.getElementById(p19);
	var o20 = document.getElementById(p20);
	var o21 = document.getElementById(p21);
	var o22 = document.getElementById(p22);
	var o23 = document.getElementById(p23);
	//ControlTextToUpper(id);
	if (objID.value.length == 0)
		return;
	//	if (ndesc.value.length > 0)
	//		return;

	//	if (!confirm("是否取得被保人資料？"))
	//		return;

	var sParam = new Array(objID.value);

	var sContext = new Array(objID, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21, o22, o23);
	AjaxService_InvokeCommonService('HASService.bsHAS301001GetPersonID', sParam, GetISSU_PERSONIDCallBack, sContext);
}



//後端
// 取得被保人資料 Ajax  被保險人編號id,姓名p1,生日p2,身分別p3,戶籍地址區號p4,戶籍地址p5,共同行銷註記p6,性別p7,通訊地址區號p8,通訊地址p9,職業等級p10,名稱p11,身高p12,體重p13,
// 公司電話區號p14,公司電話p15,公司電話分機p16,住家電話區號p17,住家電號p18,傳真區號p19,傳真p20,婚姻狀況p21,職業代號p22,行動電話p23
function GetISSU_PERSONID1(id, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25)  //後端
{
	var objID = document.getElementById(id);
	var o1 = document.getElementById(p1);
	var o2 = document.getElementById(p2);
	var o3 = document.getElementById(p3);
	var o4 = document.getElementById(p4);
	var o5 = document.getElementById(p5);
	var o6 = document.getElementById(p6);
	var o7 = document.getElementById(p7);
	var o8 = document.getElementById(p8);
	var o9 = document.getElementById(p9);
	var o10 = document.getElementById(p10);
	var o11 = document.getElementById(p11);
	var o12 = document.getElementById(p12);
	var o13 = document.getElementById(p13);
	var o14 = document.getElementById(p14);
	var o15 = document.getElementById(p15);
	var o16 = document.getElementById(p16);
	var o17 = document.getElementById(p17);
	var o18 = document.getElementById(p18);
	var o19 = document.getElementById(p19);
	var o20 = document.getElementById(p20);
	var o21 = document.getElementById(p21);
	var o22 = document.getElementById(p22);
	var o23 = document.getElementById(p23);
	var o24 = document.getElementById(p24);
	var o25 = document.getElementById(p25);
	//ControlTextToUpper(id);
	if (objID.value.length == 0)
		return;
	//	if (ndesc.value.length > 0)
	//		return;

	//	if (!confirm("是否取得被保人資料？"))
	//		return;

	var sParam = new Array(objID.value);

	var sContext = new Array(objID, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21, o22, o23, o24, o25);
	AjaxService_InvokeCommonService('HASService.bsHAS301001GetPersonID', sParam, GetISSU_PERSONID1CallBack, sContext);
}

//後端
// 取得要保人資料 Ajax   
function GetINS_PERSONID(id, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18) {
	var objID = document.getElementById(id);
	var o1 = document.getElementById(p1);
	var o2 = document.getElementById(p2);
	var o3 = document.getElementById(p3);
	var o4 = document.getElementById(p4);
	var o5 = document.getElementById(p5);
	var o6 = document.getElementById(p6);
	var o7 = document.getElementById(p7);
	var o8 = document.getElementById(p8);
	var o9 = document.getElementById(p9);
	var o10 = document.getElementById(p10);
	var o11 = document.getElementById(p11);
	var o12 = document.getElementById(p12);
	var o13 = document.getElementById(p13);
	var o14 = document.getElementById(p14);
	var o15 = document.getElementById(p15);
	var o16 = document.getElementById(p16);
	var o17 = document.getElementById(p17);
	var o18 = document.getElementById(p18);
	//ControlTextToUpper(id);
	if (objID.value.length == 0)
		return;

	var sParam = new Array(objID.value);
	//==要保人編號,姓名o1,生日o2,身分別o3,戶籍區號o4,戶籍地址o5,通訊地址區號o6,通訊地址o7,贈品地址區號o8,贈品地址o9
	//,電子郵件o10,公司電話o11,行動電話o12,住家電話o13,傳真o14,公司電話區號o15,傳真區號o16,住家電話區號o17,辦公室電話分機o18
	var sContext = new Array(objID, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18);
	AjaxService_InvokeCommonService('HASService.bsHAS301001GetPersonID', sParam, GetINS_PERSONIDCallBack, sContext);
}


//後端
function GetJobNo(id, p22) {
	var objID = document.getElementById(id);
	var o22 = document.getElementById(p22);
	if (objID.value.length == 0)
		return;
	var sParam = new Array(objID.value);
	var sContext = new Array(objID, o22);
	AjaxService_InvokeCommonService('HASService.bsHAS301001GetPersonID', sParam, GetJobNoCallBack, sContext);
}

//後端
// 檢查經手人並取得經手人名稱 Ajax   
function Check_Get_Officer(itype, iofficer, nofficer, iinscls, ipolicy, dplybgn, txtFeatabe, txtIofficerR1) {
	var o1 = document.getElementById(iofficer);
	var o2 = document.getElementById(nofficer);
	var o3 = document.getElementById(txtFeatabe);
	var o4 = document.getElementById(txtIofficerR1);

	if (o1.value.length == 0) {
		o2.value = "";
		return;
	}

	var sParam = new Array(itype, o1.value, iinscls, ipolicy, dplybgn, txtFeatabe);

	var sContext = new Array(o1, o2, o3, o4);

	AjaxService_InvokeCommonService('HASService.bsHASCheckOfficer', sParam, Check_Get_OfficerCallBack, sContext);
}

// 檢查保源 Ajax
//後端
function Check_Get_Ires(txtScsOfficerID, txtIres, txtSrccod, txtIrecPesn, txtIrecPesn2, txtIofficer1, JaxValue, txtNrecPesn, txtNrecPesn2, txtTemp1) {
	var o1 = txtScsOfficerID;
	var o2 = document.getElementById(txtIres);
	var o3 = document.getElementById(txtSrccod);
	var o4 = document.getElementById(txtIrecPesn);
	var o5 = document.getElementById(txtIrecPesn2);
	var o6 = document.getElementById(txtIofficer1);
	var o7 = document.getElementById(txtNrecPesn);
	var o8 = document.getElementById(txtNrecPesn2);
	var o9 = document.getElementById(txtTemp1);

	if (o2.value.length == 0) {
		//o2.value = "";   
		//o3.value = "";   
		//o4.value = "";   
		//o5.value = "";   
		//o6.value = "";   
		return;
	}


	if (JaxValue == 1) {
		return;
	}


	var sParam = new Array(o1, o2.value, txtSrccod, txtIrecPesn, txtIrecPesn2, txtIofficer1, txtNrecPesn, txtNrecPesn2, txtTemp1);

	var sContext = new Array(o1, o2, o3, o4, o5, o6, o7, o8, o9);

	AjaxService_InvokeCommonService('HASService.bsHASIresHabit', sParam, Check_Get_IRESCallBack, sContext);
}

// 檢查經手人並取得經手人名稱 Ajax //後端
function CheckIrecPesn(iofficer) {
	var o1 = document.getElementById(iofficer);

	if (o1.value.length == 0) {
		return;
	}

	var sParam = new Array(o1.value);

	var sContext = new Array(o1);

	AjaxService_InvokeCommonService('HASService.bsHASCheckIrecPesn', sParam, CheckIrecPesnCallBack, sContext);
}

//後端
function CheckRejClient(type, id_ctl, idseq_ctl) {
	if (id_ctl.value == '') {
		return true;
	}
	var sArr = new Array(type, id_ctl.value, idseq_ctl.value);
	var sContext = new Array(id_ctl);
	AjaxService_InvokeCommonService('HASService.bsHASRejClient', sArr, CheckRejClientR, sContext);
}

//===========================================================
//callback
//===========================================================

//設定被保人資料?
function GetISSU_PERSONIDCallBack(res) {
	var i, j;
	var s;

	if (res.error != null) {
		alert(res.error);
		return;
	}
	var tbl = res.value.Tables[0];
	var ctrls = res.context;
	//var ctl1 = ctrls[0];
	var r;
	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		if (r.NAME != null) {
			ctrls[1].value = r.NAME;
		}
		if (r.BIRDAT != null) {
			theDate = new Date(r.BIRDAT);
			var mon = parseInt(theDate.getMonth());
			mon += 1;

			ctrls[2].value = theDate.getFullYear() - 1911 + "/" + mon + "/" + theDate.getDate();
			if (Form1.elements["UcRocDplyBgn_txtDate"] != null) {
				if (Form1.elements["txtAge"] != null) {
					Form1.elements["txtAge"].value = GetAge2(ctrls[2].value, Form1.elements["UcRocDplyBgn_txtDate"].value);
				}
			}

		}
		if (r.TYPE != null) {
			ctrls[3].value = r.TYPE;
		}
		if (r.ZIPCOD != null) {
			ctrls[8].value = r.ZIPCOD;
		}
		if (r.HOMEADDRESS != null) {
			ctrls[9].value = r.HOMEADDRESS;
		}
		if (r.XSALE != null) {
			ctrls[6].value = r.XSALE;
		}
		if (r.SEX != null) {
			ctrls[7].value = r.SEX;
			if (Form1.elements["ddlObjFissuCls"].value == '1') {
				if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '1') {
					Form1.elements["ddlFsex"].value = '1';
				}
				else if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '2') {
					Form1.elements["ddlFsex"].value = '2';
				}
			}
		}
		if (r.OcpyCls != null) {
			ctrls[10].value = r.OcpyCls;
		}
		if (r.JOBNAME != null) {
			ctrls[11].value = r.JOBNAME;
		}
		if (r.IREG_ZIP != null) {
			ctrls[4].value = r.IREG_ZIP;
		}
		if (r.AREG_ADDR != null) {
			ctrls[5].value = r.AREG_ADDR;
		}
		if (r.HEIGHT != null) {
			ctrls[12].value = r.HEIGHT;
		}
		if (r.WEIGHT != null) {
			ctrls[13].value = r.WEIGHT;
		}
		if (r.OFFICEZONE != null) {
			ctrls[14].value = r.OFFICEZONE;
		}
		if (r.OFFICETEL != null) {
			ctrls[15].value = r.OFFICETEL;
		}
		if (r.OFFICEEXT != null) {
			ctrls[16].value = r.OFFICEEXT;
		}
		if (r.HOMEZONE != null) {
			ctrls[17].value = r.HOMEZONE;
		}
		if (r.HOMETEL != null) {
			ctrls[18].value = r.HOMETEL;
		}
		if (r.FAP != null) {
			ctrls[19].value = r.FAP;
		}
		if (r.FAX != null) {
			ctrls[20].value = r.FAX;
		}
		if (r.MARRID != null) {
			ctrls[21].value = r.MARRID;
		}
		if (r.JOBNO != null) {
			ctrls[22].value = r.JOBNO;
		}
		if (r.MOBILE != null) {
			ctrls[23].value = r.MOBILE;
		}
	}
	else {
		if (Form1.elements["ddlFsex"]) {
			if (Form1.elements["ddlObjFissuCls"].value == '1') {
				if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '1') {
					Form1.elements["ddlFsex"].value = '1';
				}
				else if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '2') {
					Form1.elements["ddlFsex"].value = '2';
				}
			}
		}
	}
}

//設定要保人資料?
function GetINS_PERSONIDCallBack(res) {
	var i, j;
	var s;

	if (res.error != null) {
		alert(res.error);
		return;
	}
	var tbl = res.value.Tables[0];
	var ctrls = res.context;
	//var ctl1 = ctrls[0];
	var r;
	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		if (r.NAME != null) {
			if (ctrls[1])
				//if(ctrls[1].value=="")
				ctrls[1].value = r.NAME;
		}
		if (r.BIRDAT != null) {
			if (ctrls[2])
				//if(ctrls[2].value=="")
				//{

				theDate = new Date(r.BIRDAT);
			var mon = parseInt(theDate.getMonth());
			mon += 1;

			ctrls[2].value = theDate.getFullYear() - 1911 + "/" + mon + "/" + theDate.getDate();

			if (Form1.elements["UcRocDplyBgn_txtDate"] != null) {
				if (Form1.elements["txtIinsAge"] != null) {
					Form1.elements["txtIinsAge"].value = GetAge2(ctrls[2].value, Form1.elements["UcRocDplyBgn_txtDate"].value);
				}
			}
			//}
		}
		if (r.TYPE != null) {
			if (ctrls[3])
				//if(ctrls[3].value=="")
				//{
				//switch(r.TYPE)
				//{
				//	case "1":
				//		ctrls[3].value = r.TYPE;
				//		break;
				//	case "2":
				//		ctrls[3].value = r.TYPE;
				//		break;
				//	case "3":
				//		ctrls[3].value = r.TYPE;
				//		break;
				//}
				ctrls[3].value = r.TYPE;
			//}
		}
		if (r.ZIPCOD != null) {
			ctrls[6].value = r.ZIPCOD;
			//if(ctrls[8].value=="")
			//	ctrls[8].value = r.ZIPCOD;
		}
		if (r.HOMEADDRESS != null) {
			ctrls[7].value = r.HOMEADDRESS;
			//if(ctrls[9].value=="")
			//	ctrls[9].value = r.HOMEADDRESS;
		}
		if (r.EMAILNAME != null) {
			ctrls[10].value = r.EMAILNAME;
		}
		if (r.OFFICETEL != null) {
			ctrls[11].value = r.OFFICETEL;
		}
		if (r.MOBILE != null) {
			ctrls[12].value = r.MOBILE;
		}
		if (r.HOMETEL != null) {
			ctrls[13].value = r.HOMETEL;
		}
		if (r.IREG_ZIP != null) {
			ctrls[4].value = r.IREG_ZIP;
		}
		if (r.AREG_ADDR != null) {
			ctrls[5].value = r.AREG_ADDR;
		}
		if (r.FAX != null) {
			ctrls[14].value = r.FAX;
		}
		if (r.OFFICEZONE != null) {
			ctrls[15].value = r.OFFICEZONE;
		}
		if (r.FAP != null) {
			ctrls[16].value = r.FAP;
		}
		if (r.HOMEZONE != null) {
			ctrls[17].value = r.HOMEZONE;
		}
		//alert(r.OFFICEEXT);																	
		if (r.OFFICEEXT != null) {
			ctrls[18].value = r.OFFICEEXT;
		}
	}
	else {
		/*alert("要保人資料未設定!");*/
	}
}



function GetISSU_PERSONID1CallBack(res) {
	var i, j;
	var s;

	if (res.error != null) {
		alert(res.error);
		return;
	}
	var tbl = res.value.Tables[0];
	var ctrls = res.context;
	//var ctl1 = ctrls[0];
	var r;
	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		if (r.NAME != null) {
			ctrls[1].value = r.NAME;
		}
		if (r.BIRDAT != null) {
			theDate = new Date(r.BIRDAT);
			var mon = parseInt(theDate.getMonth());
			mon += 1;

			ctrls[2].value = theDate.getFullYear() - 1911 + "/" + mon + "/" + theDate.getDate();
			if (ctrls[24].value != '') {
				if (ctrls[25] != null) {
					ctrls[25].value = GetAge2(ctrls[2].value, ctrls[24].value);
				}
			}

		}
		if (r.TYPE != null) {
			ctrls[3].value = r.TYPE;
		}
		if (r.ZIPCOD != null) {
			ctrls[8].value = r.ZIPCOD;
		}
		if (r.HOMEADDRESS != null) {
			ctrls[9].value = r.HOMEADDRESS;
		}
		if (r.XSALE != null) {
			ctrls[6].value = r.XSALE;
		}
		if (r.SEX != null) {
			ctrls[7].value = r.SEX;
			if (ctrls[0].value.substring(1, 2) == '1') {
				ctrls[7].value = '1';
			}
			else if (ctrls[0].value.substring(1, 2) == '2') {
				ctrls[7].value = '2';
			}
		}
		if (r.OcpyCls != null) {
			ctrls[10].value = r.OcpyCls;
		}
		if (r.JOBNAME != null) {
			ctrls[11].value = r.JOBNAME;
		}
		if (r.IREG_ZIP != null) {
			ctrls[4].value = r.IREG_ZIP;
		}
		if (r.AREG_ADDR != null) {
			ctrls[5].value = r.AREG_ADDR;
		}
		if (r.HEIGHT != null) {
			ctrls[12].value = r.HEIGHT;
		}
		if (r.WEIGHT != null) {
			ctrls[13].value = r.WEIGHT;
		}
		if (r.OFFICEZONE != null) {
			ctrls[14].value = r.OFFICEZONE;
		}
		if (r.OFFICETEL != null) {
			ctrls[15].value = r.OFFICETEL;
		}
		if (r.OFFICEEXT != null) {
			ctrls[16].value = r.OFFICEEXT;
		}
		if (r.HOMEZONE != null) {
			ctrls[17].value = r.HOMEZONE;
		}
		if (r.HOMETEL != null) {
			ctrls[18].value = r.HOMETEL;
		}
		if (r.FAP != null) {
			ctrls[19].value = r.FAP;
		}
		if (r.FAX != null) {
			ctrls[20].value = r.FAX;
		}
		if (r.MARRID != null) {
			ctrls[21].value = r.MARRID;
		}
		if (r.JOBNO != null) {
			ctrls[22].value = r.JOBNO;
		}
		if (r.MOBILE != null) {
			ctrls[23].value = r.MOBILE;
		}
	}
}


//取得職業代碼之後callback
function GetJobNoCallBack(res) {
	var i, j;
	var s;

	if (res.error != null) {
		alert(res.error);
		return;
	}
	var tbl = res.value.Tables[0];
	var ctrls = res.context;
	//var ctl1 = ctrls[0];
	var r;
	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		if (r.JOBNO != null) {
			ctrls[1].value = r.JOBNO;
		}
	}
	else {
		if (Form1.elements["ddlFsex"]) {
			if (Form1.elements["ddlObjFissuCls"].value == '1') {
				if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '1') {
					Form1.elements["ddlFsex"].value = '1';
				}
				else if (Form1.elements["txtObjIissu_txtCode"].value.substring(1, 2) == '2') {
					Form1.elements["ddlFsex"].value = '2';
				}
			}
		}
	}
}

//上面function的callback 
//檢查經手人並取得經手人名稱
function Check_Get_OfficerCallBack(res) {
	if (res.error != null) {
		alert(res.error);
		return;
	}

	var tbl = res.value.Tables[0];
	var ctrls = res.context;
	var r;

	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		if (r.SWFLAG != null) {
			if (r.SWFLAG == false) {
				ctrls[0].value = "";
				ctrls[1].value = "";
				ctrls[2].value = "";
				if (r.MSGERR != null)
					alert(r.MSGERR);
				return;
			}

			if (r.NOFFICER != null)

				ctrls[1].value = r.NOFFICER;
			//if (ctrls[2].value == '')
			//{
			//  if (r.EMDEPA!=null)
			//   {
			//      ctrls[2].value = r.EMDEPA;
			//   }
			//}   		        
			ctrls[2].value = r.EMDEPA;
			ctrls[3].value = ctrls[0].value;
		}
	}
	else {
		alert("檢查經手人並取得經手人名稱未設定!");
	}
}

//上面function的callback //設定保源習慣?
function Check_Get_IRESCallBack(res) {
	if (res.error != null) {
		alert(res.error);
		return;
	}

	var tbl = res.value.Tables[0];
	var ctrls = res.context;
	var r;

	//ctrls[1].value = "";
	//ctrls[2].value = "";
	//ctrls[3].value = "";
	//ctrls[4].value = "";
	//ctrls[5].value = "";
	//ctrls[6].value = "";
	//ctrls[7].value = "";
	//ctrls[8].value = "";

	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		//if (r.IBRANCH != null)
		//{    
		if (ctrls[1].value == '') {
			if (r.SRC_COD != null) {
				ctrls[1].value = r.SRC_COD;
			}
		}
		if (ctrls[2].value == '') {
			if (r.IBRANCH != null) {
				ctrls[2].value = r.IBRANCH;
			}
		}
		if (ctrls[3].value == '') {
			if (r.ISALES1 != null) {
				ctrls[3].value = r.ISALES1;
			}
		}
		if (ctrls[4].value == '') {
			if (r.ISALES2 != null) {
				ctrls[4].value = r.ISALES2;
			}
		}
		if (ctrls[5].value == '') {
			if (r.IOFFICE != null) {
				ctrls[5].value = r.IOFFICE;
			}
		}
		if (ctrls[8].value == '') {
			if (r.NBRANCH != null) {
				ctrls[8].value = r.NBRANCH;
			}
		}
		if (ctrls[6].value == '') {
			if (r.NSALES1 != null) {
				ctrls[6].value = r.NSALES1;
			}
		}
		if (ctrls[7].value == '') {
			if (r.NSALES2 != null) {
				ctrls[7].value = r.NSALES2;
			}
		}


		if (r.NSALES1 == 'T') {

			if (r.NSALES1 == 'T') {
				ctrls[1].value = "";
				ctrls[2].value = "";
				ctrls[3].value = "";
				ctrls[4].value = "";
				ctrls[5].value = "";
				ctrls[6].value = "";
				ctrls[7].value = "";
				ctrls[8].value = "";
			}
		}




		//}
	}
	else {
		//alert("保源習慣未設定!");		
	}
}

//檢驗業務員證號是否有誤
function CheckIrecPesnCallBack(res) {
	if (res.error != null) {
		alert(res.error);
		return;
	}

	var tbl = res.value.Tables[0];
	var ctrls = res.context;
	var r;

	if (tbl.Rows.length > 0) {
		r = tbl.Rows[0];
		if (r.SWFLAG != null) {
			if (r.SWFLAG == false) {
				if (r.MSGERR != null)
					alert(r.MSGERR);
				return;
			}
		}
	}
	else {
		alert("業務員證號檢檢有誤!");
	}
}

