const RIN_1102 = locationHrefKeepDataFetch();   //由「RIN1102」頁面帶入的參數:treatyYear(合約年度)、
																	//treatyNo(合約代號)、
																	//searchYear(搜尋年度)、
																	//searchNo(搜尋代號)、
																	//mode(模式)

var pills4Data = [];			//再保人頁籤table值宣告(放入talbe用)
var pills5Data = [];			//經紀人頁籤table值宣告(放入talbe用)
var table1102A_1 = null;		//再保人頁籤table宣告
var table1102A_2 = null;		//經紀人頁籤table宣告
var friTreatyRincomList;		//再保人table值(存db用)
var	friTreatyBrokerList;		//經紀人table值(存db用)
let reinserList = [];			//查詢再保人/經紀人pop頁面用

/*
 * 預設
 */
$(function(){
	
	//從1102進入
	if(RIN_1102){
		//明細鈕進入
		if("update" === RIN_1102.mode){			
			
			queryRin1102A();//查詢頁面資料
		//新增鈕進入	
		}else if("insert" === RIN_1102.mode){
			$('#update1102').hide();
			$('#delete1102').hide();
			
			init();//初始值
		}
	}

	
})


/**
 * 查詢頁面資料
 * @author yiting 2021/11/02
 */
function queryRin1102A(){
	
	//1-參數
	let param ={
		"treatyYear" : RIN_1102.treatyYear,	 	
		"treatyNo":	RIN_1102.treatyNo
	} 
	
	let parJson = JSON.stringify(param);
	
	//2-執行查詢
	let res = ajaxPostByJsonParam("../../rin1102aapi/queryonetreaty", parJson, false);
	
	if("000" === res.status){
		//3-顯示
		writeFieldForRin1102A(res.data);
		pills4Data = res.data.friTreatyRincomList;
		pills5Data = res.data.friTreatyBrokerList;
		
	}else{
		alert("「再保合約資料」查詢失敗!!!請聯絡管理人員!!!");
	}
		
}


//為處理畫面與tabulator產生的順序問題，以第一次點擊頁籤事件來產生table
$('a[data-toggle="pill"]').on('shown.bs.tab', function (e) {
	let useTab = e.target;
	
	if(useTab.id ==='pills-4-tab'){
		Tabulator.prototype.findTable("#table1102A_1")||pills4();
	}else if(useTab.id ==='pills-5-tab'){
		Tabulator.prototype.findTable("#table1102A_2")||pills5();
	}
	

})

function pills4(){
	table1102A_1 = createTable("table1102A_1", colsFormat1102A_1, tableConfigs1102A_1, tableRelatedBtns1102A_1, alertConfig1102A_1);
	loadData("table1102A_1", pills4Data,{type:"dataCount", value:10})
	
}

function pills5(){
	table1102A_2 = createTable("table1102A_2", colsFormat1102A_2, tableConfigs1102A_2, tableRelatedBtns1102A_2, alertConfig1102A_2);
	loadData("table1102A_2", pills5Data,{type:"dataCount", value:10})
	
}


//點擊新增鈕
function insert1102(){
	
	//判別為新增模式or複製模式，及是否觸發產生兩張表(取值)
	if("insert" === RIN_1102.mode){
		
		friTreatyRincomList = [];
		friTreatyBrokerList = [];
		
	}
	
	if("update" === RIN_1102.mode){
		
		friTreatyRincomList = pills4Data;
		friTreatyBrokerList = pills5Data;
		
	}
	
	if(table1102A_1){
		friTreatyRincomList = table1102A_1.getData();
		
		if(table1102A_1.tableMode){
			alert("請先儲存再保人分攤比例表格!")
			return;
		}
	}
	if(table1102A_2){
		friTreatyBrokerList = table1102A_2.getData();
	}

	//檢核必填
	let msg = checkRequired();
	if(!checkIsNullSpace(msg)){
		styleAlert(msg);
		return;
	}

	//檢核其他
	let otherMsg = checkRin1102A();
	if(!checkIsNullSpace(otherMsg)){
		styleAlert(otherMsg);
		return;
	}
	
	
	let param = {
			"friTreaty":get1102Param(),
			"friTreatyRincomList":friTreatyRincomList,
			"friTreatyBrokerList":friTreatyBrokerList
	};
	
	let parJson = JSON.stringify(param);
	
	let res = ajaxPostByJsonParam("../../rin1102aapi/insertonetreaty", parJson, false);
	
	if(res){		
		alert(res.message)
	}
}


//修改鈕
function update1102(){
	
	//判別是否產生兩張表(取值)
	friTreatyRincomList = pills4Data;
	friTreatyBrokerList = pills5Data;
	
	if(table1102A_1){
		friTreatyRincomList = table1102A_1.getData();
	}
	if(table1102A_2){
		friTreatyBrokerList = table1102A_2.getData();
	}
	
	//檢核必填
	let msg = checkRequired();
	if(!checkIsNullSpace(msg)){
		styleAlert(msg);
		return;
	}

	//檢核其他
	let otherMsg = checkRin1102A();
	if(!checkIsNullSpace(otherMsg)){
		styleAlert(otherMsg);
		return;
	}
	
	let param = {
			"friTreaty":get1102Param(),
			"friTreatyRincomList":friTreatyRincomList,
			"friTreatyBrokerList":friTreatyBrokerList
	};
	
	let parJson = JSON.stringify(param);
	
	let res = ajaxPostByJsonParam("../../rin1102aapi/updateonetreaty", parJson, false);
	
	if(res){		
		alert(res.message)
	}
}


//刪除鈕
function delete1102(){
	
	let isDelete = confirm("是否確定刪除?")
	if(isDelete){
		
		let param = {
				"treatyYear":$('#txttreaty_year').val(),
				"treatyNo":$('#txttreaty_no').val()
		};
	
		let parJson = JSON.stringify(param);
	
		let res = ajaxPostByJsonParam("../../rin1102aapi/deleteonetreaty", parJson, false);
	
		if(res){		
			alert(res.message)
		}
		
		cleanRin1102A(); //清空畫面欄位
		init();			 //給欄位初始值
		
	}else{
		return;
	}
}



//回上一頁鈕
function backTo1102(){
	
	if(RIN_1102){
		
		let data = {
				"searchYear" : RIN_1102.searchYear,		//搜尋年度
				"searchNo" : RIN_1102.searchNo			//搜尋代號
		}
		let parJson = JSON.stringify(data)
		locationHrefKeepDataType2('Rin1102', '', parJson);
	}else{
		locationHrefKeepDataType2('Rin1102', '', '');
	}
}




//--------------再保人分攤比例表格--------------
//查詢再保人按鈕設定
let idBtn = [{
	name:"查詢", 
	func:function(row){
		if(!row.getData()._edit){
			return;
		}
//		
//		let url = "1102A_pop.html?token=" + localStorage.token +"&table=table1102A_1&column=txtrin_com_id1&type=tabulator";
//		window.open(url, 'newwindow', config = 'height=900,width=1000,toolbar=no');
		queryReinser('reinser1');
	}
}]
//tabulator欄位設置
let columns1102A_1 = [
	["checkbox", { showBtn: true }],
	["button","查詢再保人代號",idBtn],
	["txtrin_com_id1", "再保人代號", "inputPlaceholder",{placeholder:"必填"} ,{ validator:"required"}],
    ["numrin_com_seq", "序號", "select1",{"1:基本成份":"1","2:增分成份":"2","3:經紀人":"3"}, {validator:"required" }],
    ["txtrin_com_sname1", "再保人名稱", "input"],
    ["numrin_com_share1", "分攤比例", "inputPlaceholder",{placeholder:"必填"}, {validator:"required"}],
    ["numrin_com_tax", "代扣營業稅", "inputPlaceholder",{placeholder:"必填"}, { validator:"required" }]
];

//tabulator欄位格式製作
let colsFormat1102A_1 = createTableColumns(columns1102A_1);

//客製tabulator本體
let tableConfigs1102A_1 = {}

//按鈕設置與功能
let tableRelatedBtns1102A_1 = [
    {
        type: "add",
        name: "",
        class: "add-btn-custom",
        position: "#table1102A_1addBtn",
        getDefaultData: function () {

            return {
                isSuccess: true
            }

        },
    },
    {
        type: "cancel",
        name: "取消",
        class: "btn btn-oneF",
        position: "#table1102A_1-btn",
    },
    {
        type: "edit",
        name: "修改",
        class: "btn btn-oneE",
        position: "#table1102A_1-btn",
    },
    {
        type: "del",
        name: "刪除",
        class: "btn btn-oneG",
        position: "#table1102A_1-btn",
        delApi: function (rowsDataArry) {
          	
            return { isSuccess: true }

        }
    },
    {
        type: "save",
        name: "儲存",
        class: "btn btn-oneD",
        position: "#table1102A_1-btn",
        rules:{},
        addSaveApi: function (rowData) {
        	
        	let param ={
        			"rinComId":rowData.txtrin_com_id1,			//再保人代號
        			"treatyBgn":$("#dtatreaty_dbgn").val(),		//合約期間開始日
        			"type":"reinser"
        	} 
        	
        	let parJson = JSON.stringify(param);
        	//再保人檢核
        	let res = ajaxPostByJsonParam("../../rin1102aapi/chkcomidusable", parJson, false);
            if("finish" === res.status){
            	return{ isSuccess: false, fields: ["txtrin_com_id1"], errMsg: res.message }
            }
        	
        	return { isSuccess: true }
        },
        editSaveApi: function (oldData, newData, newDataJson) {
         		
        	let param ={
        			"rinComId":newDataJson.txtrin_com_id1,		//再保人代號
        			"treatyBgn":$("#dtatreaty_dbgn").val(),		//合約期間開始日
        			"type":"reinser"
        	} 
        	
        	let parJson = JSON.stringify(param);
        	//再保人檢核
        	let res = ajaxPostByJsonParam("../../rin1102aapi/chkcomidusable", parJson, false);
            if("finish" === res.status){
            	return{ isSuccess: false, fields: ["txtrin_com_id1"], errMsg: res.message }
            }
            
            return { isSuccess: true }
        }
    },

]

//檢核警告設定
let alertConfig1102A_1 = {
    type: "alert",
    position: "",
    displayTime: 0, //毫秒，訊息顯示時間，設置為0不清空
}

//--------------經紀人分攤比例表格--------------
//查詢再保人按鈕設定
let brokeridBtn = [{
	name:"查詢", 
	func:function(row){
		
		if(!row.getData()._edit){
			return;
		}
		
		queryReinser('broker');
//		let url = "1102A_pop.html?token=" + localStorage.token +"&table=table1102A_2&column=txtBroker_id";
//		window.open(url, 'newwindow', config = 'height=900,width=1000,toolbar=no');
	}
}]

let rincomidBtn = [{
	name:"查詢", 
	func:function(row){
		if(!row.getData()._edit){
			return;
		}
		queryReinser('reinser2');
//		let url = "1102A_pop.html?token=" + localStorage.token +"&table=table1102A_2&column=txtrin_com_id2";
//		window.open(url, 'newwindow', config = 'height=900,width=1000,toolbar=no');
		
	}
}]
//tabulator欄位設置
let columns1102A_2 = [
	["checkbox", { showBtn: true }],
	["button","查詢經紀人代號",brokeridBtn],
	["txtBroker_id", "經紀人代號", "inputPlaceholder",{placeholder:"必填"}, {validator:"required"}],
    ["txtBroker_sname", "經紀人名稱", "input"],
    ["button","查詢再保人代號",rincomidBtn],
    ["txtrin_com_id2", "再保人代號", "inputPlaceholder",{placeholder:"必填"},{ validator:"required"}],
    ["txtrin_com_sname2", "再保人名稱", "input"],
	["numrin_com_share2", "再保人分攤比率", "inputPlaceholder",{placeholder:"必填"}, { validator:"required"}]
];

//tabulator欄位格式製作
let colsFormat1102A_2 = createTableColumns(columns1102A_2);

//客製tabulator本體
let tableConfigs1102A_2 = {}

//按鈕設置與功能
let tableRelatedBtns1102A_2 = [
    {
        type: "add",
        name: "",
        class: "add-btn-custom",
        position: "#table1102A_2addBtn",
        getDefaultData: function () {

            return {
                isSuccess: true
            }

        },
    },
    {
        type: "cancel",
        name: "取消",
        class: "btn btn-oneF",
        position: "#table1102A_2-btn",
    },
    {
        type: "edit",
        name: "修改",
        class: "btn btn-oneE",
        position: "#table1102A_2-btn",
    },
    {
        type: "del",
        name: "刪除",
        class: "btn btn-oneG",
        position: "#table1102A_2-btn",
        delApi: function (rowsDataArry) {
          	
            return { isSuccess: true }

        }
    },
    {
        type: "save",
        name: "儲存",
        class: "btn btn-oneD",
        position: "#table1102A_2-btn",
        rules:{},
        addSaveApi: function (rowData) {
              		
        	let param ={
        			"rinComId":rowData.txtBroker_id,			//經紀人代號
        			"treatyBgn":$("#dtatreaty_dbgn").val(),		//合約期間開始日
        			"type":"broker"
        	} 
        	
        	let parJson = JSON.stringify(param);
        	//經紀人檢核
        	let res = ajaxPostByJsonParam("../../rin1102aapi/chkcomidusable", parJson, false);
            if("finish" === res.status){
            	return{ isSuccess: false, fields: ["txtBroker_id"], errMsg: res.message }
            }
        	return { isSuccess: true }
        },
        editSaveApi: function (oldData, newData, newDataJson) {
         		
        	let param ={
        			"rinComId":newDataJson.txtBroker_id,		//經紀人代號
        			"treatyBgn":$("#dtatreaty_dbgn").val(),		//合約期間開始日
        			"type":"broker"
        	} 
        	
        	let parJson = JSON.stringify(param);
        	//經紀人檢核
        	let res = ajaxPostByJsonParam("../../rin1102aapi/chkcomidusable", parJson, false);
            if("finish" === res.status){
            	return{ isSuccess: false, fields: ["txtBroker_id"], errMsg: res.message }
            }
            return { isSuccess: true }
        }
    },

]

//檢核警告設定
let alertConfig1102A_2 = {
    type: "alert",
    position: "",
    displayTime: 0, //毫秒，訊息顯示時間，設置為0不清空
}


//寫入欄位
function writeFieldForRin1102A(cell){
		
	//--------------共用區塊--------------
	$('#txttreaty_year').val(cell.friTreaty.treatyYear);					//合約年度
	$('#txttreaty_no').val(cell.friTreaty.treatyNo);						//合約代號
	$('#txttreaty_name').val(cell.friTreaty.treatyName);					//合約名稱
	$('#txttreaty_sname').val(cell.friTreaty.treatySname);					//合約簡稱
	
	$('#dtatreaty_dbgn').val(formatDateYYYYMMDD(cell.friTreaty.treatyDbgn));//合約期間(起)
	$('#dtatreaty_dend').val(formatDateYYYYMMDD(cell.friTreaty.treatyDend));//合約期間(迄)
	$('#txttreaty_mode').val(cell.friTreaty.treatyMode);					//合約型態
	//分入方式
	if("1" == cell.friTreaty.shareType){		
		$('#txtshare_type1').prop("checked", true);							//分入百分比
	}else if("2" == cell.friTreaty.shareType){
		$('#txtshare_type2').prop("checked", true);							//分入線數
	}
	
	$('#numshare_rate').val(cell.friTreaty.shareRate);						//分入比率
	$('#txtclose_type').val(cell.friTreaty.closeType);						//終止方式
	$('#numlose_keep_year').val(cell.friTreaty.loseKeepYear);				//虧損移轉年數
	$('#txtacct_type').val(cell.friTreaty.acctType);						//帳單製作期
	$('#txtnpremcal_type').val(cell.friTreaty.npremcalType);				//未滿期保費計算	
	
	//--------------利率及稅率--------------
	$('#numnpreminst_rate').val(cell.friTreaty.npreminstRate);				//未滿期保費利息利率
	$('#numnpremtax_rate').val(cell.friTreaty.npremtaxRate);				//未滿期保費利息稅率
	$('#numnripaycal_rate').val(cell.friTreaty.nripaycalRate);				//未決賠款計算比例
	$('#numbusinesstax_rate').val(cell.friTreaty.businesstaxRate);			//代扣營業稅率
	$('#numstamptax_rate').val(cell.friTreaty.stamptaxRate);				//印花稅率
	$('#numhandling_rate').val(cell.friTreaty.handlingRate);				//管理率
	$('#numagent_rate').val(cell.friTreaty.agentRate);						//代理率
	$('#numriprem_rate').val(cell.friTreaty.ripremRate);					//再保費提存率
	$('#numripreminst_rate').val(cell.friTreaty.ripreminstRate);			//再保費提存利息利率
	$('#numripremtax_rate').val(cell.friTreaty.ripremtaxRate);				//再保費提存代扣稅率
	$('#numripay_rate').val(cell.friTreaty.ripayRate);						//賠款提存率
	$('#numripayinst_rate').val(cell.friTreaty.ripayinstRate);				//賠款提存利息利率
	$('#numripaytax_rate').val(cell.friTreaty.ripaytaxRate);				//賠款提存代扣税率
	
	$('#numExceddEST_P_I').val(cell.friTreaty.exceedestPI);					//預估保費收入(EST.P.I)
	$('#numExceedReinstateme').val(cell.friTreaty.exceedreinstateme);		//復效次數(Reinstateme)
	$('#numExceedDuty').val(cell.friTreaty.exceedduty);						//責任額
	$('#numExceedR_O_L').val(cell.friTreaty.exceedrOL);						//責任費率%(R. O. L)
	$('#numExceedRipayBegin').val(cell.friTreaty.exceedripaybegin);			//起賠
	$('#numclass29_03rate').val(cell.friTreaty.class2903rate);				//29險種03分配比例
	$('#numExceedRate').val(cell.friTreaty.exceedrate);						//費率%
	$('#numclass29_25rate').val(cell.friTreaty.class2925rate);				//29險種25分配比例
	$('#numExceedEST_EP').val(cell.friTreaty.exceedestEp);					//預估再保費(Est. EP)
	$('#numclass29_28rate').val(cell.friTreaty.class2928rate);				//29險種28分配比例
	$('#numExceedEST_EP_MD').val(cell.friTreaty.exceedestEpMd);				//扣款後預估再保費(M & D)
	
	//--------------限額及除外業務--------------	
	//限額
	$('#txtlimit_base').val(cell.friTreaty.limitBase);						//基礎
	$('#numlimit_general').val(cell.friTreaty.limitGeneral);				//一般
	$('#numlimit_total').val(cell.friTreaty.limitTotal);					//共保
	$('#numcoins_rate').val(cell.friTreaty.coinsRate);						//共保比率
	$('#numretain_times').val(cell.friTreaty.retainTimes);					//自留額倍數
	$('#txtref_treaty_no').val(cell.friTreaty.refTreatyNo);					//比較基礎合約代號
	$('#numaccident_notice').val(cell.friTreaty.accidentNotice);			//出險通知
	$('#numcash_call').val(cell.friTreaty.cashCall);						//現金攤賠
	$('#Numlimit_tia').val(cell.friTreaty.limitTia);						//TIA限額
	
	$('#numfirrulcom_rate').val(cell.friTreaty.firrulcomRate);				//純火暫訂佣
	$('#numfirprjcom_rate').val(cell.friTreaty.firprjcomRate);				//純火專案
	$('#numfirnrucom_rate').val(cell.friTreaty.firnrucomRate);				//純火非規章
	$('#numearrulcom_rate').val(cell.friTreaty.earrulcomRate);				//地震暫訂佣
	$('#numearprjcom_rate').val(cell.friTreaty.earprjcomRate);				//地震專案
	$('#numearnrucom_rate').val(cell.friTreaty.earnrucomRate);				//地震非規章
	$('#numtyprulcom_rate').val(cell.friTreaty.typrulcomRate);				//颱洪暫訂佣
	$('#numtypprjcom_rate').val(cell.friTreaty.typprjcomRate);				//颱洪專案
	$('#numtypnrucom_rate').val(cell.friTreaty.typnrucomRate);				//颱洪非規章
	$('#numaddrulcom_rate').val(cell.friTreaty.addrulcomRate);				//附加暫訂佣
	$('#numaddprjcom_rate').val(cell.friTreaty.addprjcomRate);				//附加專案
	$('#numaddnrucom_rate').val(cell.friTreaty.addnrucomRate);				//附加非規章	
	
	//--------------再保人分攤比例--------------
	$('#txtshare_flag').val(cell.friTreaty.shareFlag);						//本公司是否參加分保
	$('#nummyself_share').val(cell.friTreaty.myselfShare);					//Share%
	$('#numother_share').val(cell.friTreaty.otherShare);					//其他再保公司Share%
	
	
}


//取得欄位值
function get1102Param(){
	
	//合約期間(起)
	let treatyDbgn = transDateStrToDateObj($('#dtatreaty_dbgn').val());
	//合約期間(迄)
	let treatyDend = transDateStrToDateObj($('#dtatreaty_dend').val());
	
	//分入方式
	let shareType;
	
	if($("#txtshare_type1").prop("checked") == true){
		shareType = "1";//分入百分比
	}
	if($("#txtshare_type2").prop("checked") == true){
		shareType = "2";//分入線數
	}
	
	
	return {
			
			//--------------共用區塊--------------		
			"treatyYear" : $('#txttreaty_year').val(),				//合約年度
			"treatyNo" : $('#txttreaty_no').val(),					//合約代號
			"treatyName" : $('#txttreaty_name').val(),				//合約名稱
			"treatySname" : $('#txttreaty_sname').val(),			//合約簡稱
			"treatyDbgn" : treatyDbgn,								//合約期間(起)
			"treatyDend" : treatyDend,								//合約期間(迄)
			"treatyMode" : $('#txttreaty_mode').val(),				//合約型態
			"shareType" : shareType,								//分入方式
			"shareRate" : $('#numshare_rate').val(),				//分入比率
			"closeType" : $('#txtclose_type').val(),				//終止方式
			"loseKeepYear" : $('#numlose_keep_year').val(),			//虧損移轉年數
			"acctType" : $('#txtacct_type').val(),					//帳單製作期
			"npremcalType" : $('#txtnpremcal_type').val(),			//未滿期保費計算
			
			//--------------利率及稅率--------------
			"npreminstRate" : $('#numnpreminst_rate').val(),		//未滿期保費利息利率
			"npremtaxRate" : $('#numnpremtax_rate').val(),			//未滿期保費利息稅率
			"nripaycalRate" : $('#numnripaycal_rate').val(),		//未決賠款計算比例
			"businesstaxRate" : $('#numbusinesstax_rate').val(),	//代扣營業稅率
			"stamptaxRate" : $('#numstamptax_rate').val(),			//印花稅率
			"handlingRate" : $('#numhandling_rate').val(),			//管理率
			"agentRate" : $('#numagent_rate').val(),				//代理率
			"ripremRate" : $('#numriprem_rate').val(),				//再保費提存率
			"ripreminstRate" : $('#numripreminst_rate').val(),		//再保費提存利息利率
			"ripremtaxRate" : $('#numripremtax_rate').val(),		//再保費提存代扣稅率
			"ripayRate" : $('#numripay_rate').val(),				//賠款提存率
			"ripayinstRate" : $('#numripayinst_rate').val(),		//賠款提存利息利率
			"ripaytaxRate" : $('#numripaytax_rate').val(),			//賠款提存代扣税率
			
			"exceedestPI" : $('#numExceddEST_P_I').val(),			//預估保費收入(EST.P.I)
			"exceedreinstateme" : $('#numExceedReinstateme').val(),	//復效次數(Reinstateme)
			"exceedduty" : $('#numExceedDuty').val(),				//責任額
			"exceedrOL" : $('#numExceedR_O_L').val(),				//責任費率%(R. O. L)
			"exceedripaybegin" : $('#numExceedRipayBegin').val(),	//起賠
			"class2903rate" : $('#numclass29_03rate').val(),		//29險種03分配比例
			"exceedrate" : $('#numExceedRate').val(),				//費率%
			"class2925rate" : $('#numclass29_25rate').val(),		//29險種25分配比例
			"exceedestEp" : $('#numExceedEST_EP').val(),			//預估再保費(Est. EP)
			"class2928rate" : $('#numclass29_28rate').val(),		//29險種28分配比例
			"exceedestEpMd" : $('#numExceedEST_EP_MD').val(),		//扣款後預估再保費(M & D)
			
			//--------------限額及除外業務--------------	
			//限額
			"limitBase" : $('#txtlimit_base').val(),				//基礎
			"limitGeneral" : $('#numlimit_general').val(),			//一般
			"limitTotal" : $('#numlimit_total').val(),				//共保
			"coinsRate" : $('#numcoins_rate').val(),				//共保比率
			"retainTimes" : $('#numretain_times').val(),			//自留額倍數
			"refTreatyNo" : $('#txtref_treaty_no').val(),			//比較基礎合約代號
			"accidentNotice" : $('#numaccident_notice').val(),		//出險通知
			"cashCall" : $('#numcash_call').val(),					//現金攤賠
			"limitTia" : $('#Numlimit_tia').val(),					//TIA限額
			//佣金率
			"firrulcomRate" : $('#numfirrulcom_rate').val(),		//純火暫訂佣
			"firprjcomRate" : $('#numfirprjcom_rate').val(),		//純火專案
			"firnrucomRate" : $('#numfirnrucom_rate').val(),		//純火非規章
			"earrulcomRate" : $('#numearrulcom_rate').val(),		//地震暫訂佣
			"earprjcomRate" : $('#numearprjcom_rate').val(),		//地震專案
			"earnrucomRate" : $('#numearnrucom_rate').val(),		//地震非規章
			"typrulcomRate" : $('#numtyprulcom_rate').val(),		//颱洪暫訂佣
			"typprjcomRate" : $('#numtypprjcom_rate').val(),		//颱洪專案
			"typnrucomRate" : $('#numtypnrucom_rate').val(),		//颱洪非規章
			"addrulcomRate" : $('#numaddrulcom_rate').val(),		//附加暫訂佣
			"addprjcomRate" : $('#numaddprjcom_rate').val(),		//附加專案
			"addnrucomRate" : $('#numaddnrucom_rate').val(),		//附加非規章
			
			//--------------再保人分攤比例--------------
			"shareFlag" : $('#txtshare_flag').val(),				//本公司是否參加分保
			"myselfShare" : $('#nummyself_share').val(),			//Share%
			"otherShare" : $('#numother_share').val(),				//其他再保公司Share%		
			
	}

}


//清空欄位
function cleanRin1102A(){
	
	//--------------共用區塊--------------
	$('#txttreaty_year').val("");							//合約年度
	$('#txttreaty_no').val("");								//合約代號
	$('#txttreaty_name').val("");							//合約名稱
	$('#txttreaty_sname').val("");							//合約簡稱
	
	$('#dtatreaty_dbgn').val("");							//合約期間(起)
	$('#dtatreaty_dend').val("");							//合約期間(迄)
	$('#txttreaty_mode').val("");							//合約型態
	//分入方式	
	$('#txtshare_type1').prop("checked", false);			//分入百分比
	$('#txtshare_type2').prop("checked", false);			//分入線數
	
	
	$('#numshare_rate').val("");							//分入比率
	$('#txtclose_type').val("");							//終止方式
	$('#numlose_keep_year').val("");						//虧損移轉年數
	$('#txtacct_type').val("");								//帳單製作期
	$('#txtnpremcal_type').val("");							//未滿期保費計算
	
	
	//--------------利率及稅率--------------
	$('#numnpreminst_rate').val("");						//未滿期保費利息利率
	$('#numnpremtax_rate').val("");							//未滿期保費利息稅率
	$('#numnripaycal_rate').val("");						//未決賠款計算比例
	$('#numbusinesstax_rate').val("");						//代扣營業稅率
	$('#numstamptax_rate').val("");							//印花稅率
	$('#numhandling_rate').val("");							//管理率
	$('#numagent_rate').val("");							//代理率
	$('#numriprem_rate').val("");							//再保費提存率
	$('#numripreminst_rate').val("");						//再保費提存利息利率
	$('#numripremtax_rate').val("");						//再保費提存代扣稅率
	$('#numripay_rate').val("");							//賠款提存率
	$('#numripayinst_rate').val("");						//賠款提存利息利率
	$('#numripaytax_rate').val("");							//賠款提存代扣税率
	
	$('#numExceddEST_P_I').val("");							//預估保費收入(EST.P.I)
	$('#numExceedReinstateme').val("");						//復效次數(Reinstateme)
	$('#numExceedDuty').val("");							//責任額
	$('#numExceedR_O_L').val("");							//責任費率%(R. O. L)
	$('#numExceedRipayBegin').val("");						//起賠
	$('#numclass29_03rate').val("");						//29險種03分配比例
	$('#numExceedRate').val("");							//費率%
	$('#numclass29_25rate').val("");						//29險種25分配比例
	$('#numExceedEST_EP').val("");							//預估再保費(Est. EP)
	$('#numclass29_28rate').val("");						//29險種28分配比例
	$('#numExceedEST_EP_MD').val("");						//扣款後預估再保費(M & D)

	
	//--------------限額及除外業務--------------	
	//限額
	$('#txtlimit_base').val("");							//基礎
	$('#numlimit_general').val("");							//一般
	$('#numlimit_total').val("");							//共保
	$('#numcoins_rate').val("");							//共保比率
	$('#numretain_times').val("");							//自留額倍數
	$('#txtref_treaty_no').val("");							//比較基礎合約代號
	$('#numaccident_notice').val("");						//出險通知
	$('#numcash_call').val("");								//現金攤賠
	$('#Numlimit_tia').val("");								//TIA限額
	//佣金率
	$('#numfirrulcom_rate').val("");						//純火暫訂佣
	$('#numfirprjcom_rate').val("");						//純火專案
	$('#numfirnrucom_rate').val("");						//純火非規章
	$('#numearrulcom_rate').val("");						//地震暫訂佣
	$('#numearprjcom_rate').val("");						//地震專案
	$('#numearnrucom_rate').val("");						//地震非規章
	$('#numtyprulcom_rate').val("");						//颱洪暫訂佣
	$('#numtypprjcom_rate').val("");						//颱洪專案
	$('#numtypnrucom_rate').val("");						//颱洪非規章
	$('#numaddrulcom_rate').val("");						//附加暫訂佣
	$('#numaddprjcom_rate').val("");						//附加專案
	$('#numaddnrucom_rate').val("");						//附加非規章
	
	
	//--------------再保人分攤比例--------------
	$('#txtshare_flag').val("");							//本公司是否參加分保
	$('#nummyself_share').val("");							//Share%
	$('#numother_share').val("");							//其他再保公司Share%

	try{	
		table1102A_1.clearData();
	}catch(e){
		pills4Data = [];
	}

	//--------------經紀人分攤比例--------------
	try{
		table1102A_2.clearData(); 
	}catch(e){
		pills5Data = [];
	}
}


//Rin1102A欄位檢核(必填以外)
function checkRin1102A(){
	
	let message = "";
	let nextRow = "<br>";

	//--------------檢核合約期間起迄日--------------
	let treatyBgn = $('#dtatreaty_dbgn').val();
	let treatyEnd = $('#dtatreaty_dend').val();
		
	if(treatyBgn > treatyEnd){
		message = message + "合約期間_起不應大於合約期間_迄" + nextRow;
	}

	//--------------檢核分入方式與其值--------------
	let shareType = $("input[name='txtshare_type']:checked").val();
	let shareRate = $("#numshare_rate").val();
	
	if("1" === shareType && (shareRate < 0 || shareRate > 100)){
		message = message + "分入百分比的區間範圍為0~100" + nextRow;			
	}else if("2" === shareType && !(/^[1-9]\d*$/.test(shareRate))){
		message = message + "分入線數需為正整數" + nextRow;		
	}
	
	//--------------檢核Share%與其他再保公司Share%--------------
	
	let hundred = $("#nummyself_share").val()*1+$("#numother_share").val()*1

	if(100 !== hundred){
		message = message + "Share% 與 其他再保公司Share% 加總需為 100" + nextRow;		
	}
	
	//--------------檢核其他再保公司Share%與分攤比例加總是否相等------
	let sum = friTreatyRincomList.reduce((sum_, data) =>{
		return sum_ += data.numrin_com_share1*1;
	}, 0);
		
	if($('#numother_share').val()!= sum){
		message = message + "[再保人分攤比例]加總值不等於[其他再保公司Share%]" + nextRow;		
	}
	
	//--------------最終支付公司檢核---------------------------

	let rinComIdList = friTreatyRincomList.map(data=>{return data.txtrin_com_id1})
	let brokerIdList = friTreatyBrokerList.map(data=>{return data.txtBroker_id})
	
	let param ={
		"rinComIdList":rinComIdList,
		"brokerIdList":brokerIdList	
	}

	let parJson = JSON.stringify(param);
	
	let res = ajaxPostByJsonParam("../../rin1102aapi/chkenode", parJson, false);

	if(res && !checkIsNullSpace(res.message)){		
		message = message + res.message + nextRow;
	}


	//--------------檢核經紀人與再保人分攤比例是否相等---------------
	if(friTreatyBrokerList.length > 0){
		
		let resultCom = friTreatyRincomList.reduce((obj, data)=>{
			let keyCom = data.txtrin_com_id1;
			let valueCom = data.numrin_com_share1*1;
			if(keyCom in obj){
				return{...obj,[keyCom]:obj[keyCom]+valueCom}
			}else{
				return{...obj,[keyCom]:valueCom}
			}
		},{})
		
		let resultBroker = friTreatyBrokerList.reduce((obj, data)=>{
			let keyBroker = data.txtBroker_id;
			let valueBroker = data.numrin_com_share2*1;
			if(keyBroker in obj){
				return{...obj,[keyBroker]:obj[keyBroker]+valueBroker}
			}else{
				return{...obj,[keyBroker]:valueBroker}
			}
		},{})
		
		let brokerKeys = Object.keys(resultBroker);
		
		
		
		let strangePeople = brokerKeys.reduce((msg,key)=>{
			let aValue = resultCom[key];
			let bValue = resultBroker[key];
			
			if(aValue !== bValue){return [...msg, key]}
			else{return msg}
		},[]);
		
		
		if(strangePeople.length > 0){
			message = message + `第二層再保人分攤比例加總不等於經紀人比例(${strangePeople.join(",")})` + nextRow;
		}
	}	

	return message;
}


//檢核必填欄位
function checkRequired(){

	let message = "";
	let isRequireStr = "為必填!<br>";

	//--------------共用區塊--------------
	if(checkIsNullSpace($('#txttreaty_year').val())){
		message = message + "合約年度" + isRequireStr;
	}

	if(checkIsNullSpace($('#txttreaty_no').val())){
		message = message + "合約代號" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#dtatreaty_dbgn').val())){
		message = message + "合約期間(起)" + isRequireStr;
	}

	if(checkIsNullSpace($('#dtatreaty_dend').val())){
		message = message + "合約期間(迄)" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txttreaty_mode').val())){
		message = message + "合約型態" + isRequireStr;
	}
	
	let share_type = $("input[name='txtshare_type']:checked").val(); 
	if(!share_type){
		message = message + "分入方式" + isRequireStr;
	}

	if(checkIsNullSpace($('#numshare_rate').val())){
		message = message + "分入值" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtclose_type').val())){
		message = message + "終止方式" + isRequireStr;
	}

	if(checkIsNullSpace($('#numlose_keep_year').val())){
		message = message + "虧損移轉年數" + isRequireStr;
	}

	if(checkIsNullSpace($('#txtacct_type').val())){
		message = message + "帳單製作期" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#txtnpremcal_type').val())){
		message = message + "未滿期保費計算" + isRequireStr;
	}

	
	//--------------利率及稅率--------------
	if(checkIsNullSpace($('#numnpreminst_rate').val())){
		message = message + "未滿期保費利息利率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numnpremtax_rate').val())){
		message = message + "未滿期保費利息稅率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numnripaycal_rate').val())){
		message = message + "未決賠款計算比例" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numbusinesstax_rate').val())){
		message = message + "代扣營業稅率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numstamptax_rate').val())){
		message = message + "印花稅率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numhandling_rate').val())){
		message = message + "管理率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numagent_rate').val())){
		message = message + "代理率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numriprem_rate').val())){
		message = message + "再保費提存率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numripreminst_rate').val())){
		message = message + "再保費提存利息利率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numripremtax_rate').val())){
		message = message + "再保費提存代扣稅率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numripay_rate').val())){
		message = message + "賠款提存率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numripayinst_rate').val())){
		message = message + "賠款提存利息利率" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numripaytax_rate').val())){
		message = message + "賠款提存代扣税率" + isRequireStr;
	}
	
//	if(checkIsNullSpace($('#numExceddEST_P_I').val())){
//		message = message + "預估保費收入(EST.P.I)" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numExceedReinstateme').val())){
//		message = message + "復效次數(Reinstateme)" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numExceedDuty').val())){
//		message = message + "責任額" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numExceedR_O_L').val())){
//		message = message + "責任費率%(R. O. L)" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numExceedRipayBegin').val())){
//		message = message + "起賠" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numclass29_03rate').val())){
//		message = message + "29險種03分配比例" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numExceedRate').val())){
//		message = message + "費率%" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numclass29_25rate').val())){
//		message = message + "29險種25分配比例" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numExceedEST_EP').val())){
//		message = message + "預估再保費(Est. EP)" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numclass29_28rate').val())){
//		message = message + "29險種28分配比例" + isRequireStr;
//	}
//	
//	if(checkIsNullSpace($('#numExceedEST_EP_MD').val())){
//		message = message + "扣款後預估再保費(M & D)" + isRequireStr;
//	}
	
	//--------------限額及除外業務--------------	
	//限額	
	if(checkIsNullSpace($('#txtlimit_base').val())){
		message = message + "基礎" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numlimit_general').val())){
		message = message + "一般" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numlimit_total').val())){
		message = message + "共保" + isRequireStr;
	}
	
//	if(checkIsNullSpace($('#numcoins_rate').val())){
//		message = message + "共保比率" + isRequireStr;
//	}
	
	if(checkIsNullSpace($('#numretain_times').val())){
		message = message + "自留額倍數" + isRequireStr;
	}
	
//	if(checkIsNullSpace($('#txtref_treaty_no').val())){
//		message = message + "比較基礎合約代號" + isRequireStr;
//	}
	
	if(checkIsNullSpace($('#numaccident_notice').val())){
		message = message + "出險通知" + isRequireStr;
	}

	if(checkIsNullSpace($('#numcash_call').val())){
		message = message + "現金攤賠" + isRequireStr;
	}
	
//	if(checkIsNullSpace($('#Numlimit_tia').val())){
//		message = message + "TIA限額" + isRequireStr;
//	}
	//佣金率
	if(checkIsNullSpace($('#numfirrulcom_rate').val())){
		message = message + "純火暫定佣" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numfirprjcom_rate').val())){
		message = message + "純火專案" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numfirnrucom_rate').val())){
		message = message + "純火非規章" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numearrulcom_rate').val())){
		message = message + "地震暫定佣" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numearprjcom_rate').val())){
		message = message + "地震專案" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numearnrucom_rate').val())){
		message = message + "地震非規章" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numtyprulcom_rate').val())){
		message = message + "颱洪暫定佣" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numtypprjcom_rate').val())){
		message = message + "颱洪專案" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numtypnrucom_rate').val())){
		message = message + "颱洪非規章" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numaddrulcom_rate').val())){
		message = message + "附加暫定佣" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numaddprjcom_rate').val())){
		message = message + "附加專案" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numaddnrucom_rate').val())){
		message = message + "附加非規章" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numtypprjcom_rate').val())){
		message = message + "颱洪專案" + isRequireStr;
	}
	
	//--------------再保人分攤比例--------------
	if(checkIsNullSpace($('#txtshare_flag').val())){
		message = message + "本公司是否參加分保" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#nummyself_share').val())){
		message = message + "Share%" + isRequireStr;
	}
	
	if(checkIsNullSpace($('#numother_share').val())){
		message = message + "其他再保公司Share%" + isRequireStr;
	}
	return message;
}



//欄位預設值
function init(){
	
	$('#txttreaty_mode').val("");					//合約型態
	$('#txtshare_type1').prop("checked", true);		//分入方式(分入百分比)
	$('#txtclose_type').val("");					//終止方式
	$('#txtacct_type').val("");						//帳單製作期
	$('#txtnpremcal_type').val("");					//未滿期保費計算
	$('#txtlimit_base').val("");					//基礎
	$('#txtshare_flag').val("");					//本公司是否參加分保
}


//====================================================================================
//reinserTable table start
//選取按鈕設定
let chooseBtn1 = [{
    name: "選取",
    func: function(row) {
        let data = {
            "lblrin_com_id": row.getData().lblrin_com_id,
            "lblename": row.getData().lblename,
        }
        chooseReinser = data;
  
    }
}]
let reinserTableColumns = [
    ["button", "", chooseBtn1],
    ["lblrin_com_id", "再保人代號", "display"],
    ["lblename", "再保人英文名稱", "display"],
    ["lblcname", "再保人中文名稱", "display"],
    ["lblsname", "再保人中文簡稱", "display"],
    ["lblremark", "備註", "display"],
    ["dtaUSEMRK", "註銷日", "display"]
]

//tabulator欄位格式製作
let reinserTableColumnsFormat = createTableColumns(reinserTableColumns)

//客製tabulator本體
let reinserTableConfigs = {
    layout: "fitDataStretch",
    placeholder: "無資料"
};

//按鈕設置與功能
let reinserTableRelatedBtns = []

//檢核警告設定
let reinserTableAlertConfig = {}

//建立table
let reinserTable = createTable("reinserTable", reinserTableColumnsFormat, reinserTableConfigs, reinserTableRelatedBtns, reinserTableAlertConfig);

//====================================================================================
function queryReinser(value) {
    let table = Tabulator.prototype.findTable('#reinserTable')[0];
    let tableSorter = table.getSorters();
    table.clearSort();
    table.setHeight('400px');
    table.setSort(tableSorter);
    getReinserList().then(() => {
        table.setData(reinserList).then(() => {
            $('#myModal').modal('show');
            if(value == 'broker'){
            	$('#myModalTitle').text('經紀人資料');
            }else{
            	$('#myModalTitle').text('再保人資料');
            }
            $('input[name="reinserType"]').val(value);
            $('input[class="tabulator-select"]').prop('checked', false);
        })
    })

}

function getReinserList() {
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1102apopapi/queryallfricom", true, false, null,
        (res) => {
            if (res.status != "000") {
                console.log(res.message);
                alert("取得再保人清單發生錯誤,請聯絡系統管理員");
            } else {
                reinserList = res.data;
            }
        }, (error) => {
            console.log(error);
            alert("取得再保人清單發生錯誤,請聯絡系統管理員");
        })
}

function getReinserInfo() {
    if (!checkIsNullSpace(chooseReinser.dtaUSEMRK)) {
        let logOutDate = new Date(chooseReinser.dtaUSEMRK);
        let now = new Date();
        if (logOutDate <= now) {
            alert("此再保人已註銷");
            return;
        }
    } else {
        let type = $('input[name="reinserType"]').val();
       
        switch (type) {
            case 'reinser1':
            	table1102A_1.getSelectedRows()[0].update({txtrin_com_id1:chooseReinser.lblrin_com_id})
                table1102A_1.getSelectedRows()[0].getCells()[2].getElement().click();
            	break;
            case 'broker':
            	table1102A_2.getSelectedRows()[0].update({txtBroker_id:chooseReinser.lblrin_com_id})
            	table1102A_2.getSelectedRows()[0].getCells()[2].getElement().click();
                break;
            case 'reinser2':
            	table1102A_2.getSelectedRows()[0].update({txtrin_com_id2:chooseReinser.lblrin_com_id})
            	table1102A_2.getSelectedRows()[0].getCells()[5].getElement().click();
                break;
        }

        $('#myModal').modal('hide');

    }


}
//假資料(要刪掉!!)
function fake(){
	
	
	//--------------共用區塊--------------
	$('#txttreaty_year').val('1111');				//合約年度
	$('#txttreaty_no').val('1111');					//合約代號
	$('#txttreaty_name').val('1');					//合約名稱
	$('#txttreaty_sname').val('2');					//合約簡稱
	
	$('#dtatreaty_dbgn').val('2021/12/12');			//合約期間(起)
	$('#dtatreaty_dend').val('2021/12/12');			//合約期間(迄)
	$('#txttreaty_mode').val('3');					//合約型態
	$('#numshare_rate').val('4');					//分入比率
	$('#txtclose_type').val('2');					//終止方式
	$('#numlose_keep_year').val('6');				//虧損移轉年數
	$('#txtacct_type').val('4');					//帳單製作期
	$('#txtnpremcal_type').val('2');				//未滿期保費計算
	
	
	//--------------利率及稅率--------------
	$('#numnpreminst_rate').val('9');				//未滿期保費利息利率
	$('#numnpremtax_rate').val('10');				//未滿期保費利息稅率
	$('#numnripaycal_rate').val('11');				//未決賠款計算比例
	$('#numbusinesstax_rate').val('12');			//代扣營業稅率
	$('#numstamptax_rate').val('13');				//印花稅率
	$('#numhandling_rate').val('14');				//管理率
	$('#numagent_rate').val('15');					//代理率
	$('#numriprem_rate').val('16');					//再保費提存率
	$('#numripreminst_rate').val('17');				//再保費提存利息利率
	$('#numripremtax_rate').val('18');				//再保費提存代扣稅率
	$('#numripay_rate').val('19');					//賠款提存率
	$('#numripayinst_rate').val('20');				//賠款提存利息利率
	$('#numripaytax_rate').val('21');				//賠款提存代扣税率
	
	$('#numExceddEST_P_I').val('22');				//預估保費收入(EST.P.I)
	$('#numExceedReinstateme').val('23');			//復效次數(Reinstateme)
	$('#numExceedDuty').val('24');					//責任額
	$('#numExceedR_O_L').val('25');					//責任費率%(R. O. L)
	$('#numExceedRipayBegin').val('26');			//起賠
	$('#numclass29_03rate').val('27');				//29險種03分配比例
	$('#numExceedRate').val('28');					//費率%
	$('#numclass29_25rate').val('29');				//29險種25分配比例
	$('#numExceedEST_EP').val('30');				//預估再保費(Est. EP)
	$('#numclass29_28rate').val('31');				//29險種28分配比例
	$('#numExceedEST_EP_MD').val('32');				//扣款後預估再保費(M & D)

	
	//--------------限額及除外業務--------------	
	//限額
	$('#txtlimit_base').val('2');					//基礎
	$('#numlimit_general').val('34');				//一般
	$('#numlimit_total').val('35');					//共保
	$('#numcoins_rate').val('36');					//共保比率
	$('#numretain_times').val('37');				//自留額倍數
	$('#txtref_treaty_no').val('38');				//比較基礎合約代號
	$('#numaccident_notice').val('39');				//出險通知
	$('#numcash_call').val('40');					//現金攤賠
	$('#Numlimit_tia').val('41');					//TIA限額
	
	$('#numfirrulcom_rate').val('42');				//純火暫訂佣
	$('#numfirprjcom_rate').val('43');				//純火專案
	$('#numfirnrucom_rate').val('44');				//純火非規章
	$('#numearrulcom_rate').val('45');				//地震暫訂佣
	$('#numearprjcom_rate').val('46');				//地震專案
	$('#numearnrucom_rate').val('47');				//地震非規章
	$('#numtyprulcom_rate').val('48');				//颱洪暫訂佣
	$('#numtypprjcom_rate').val('49');				//颱洪專案
	$('#numtypnrucom_rate').val('50');				//颱洪非規章
	$('#numaddrulcom_rate').val('51');				//附加暫訂佣
	$('#numaddprjcom_rate').val('52');				//附加專案
	$('#numaddnrucom_rate').val('53');				//附加非規章
	
	
	//--------------再保人分攤比例--------------
	$('#txtshare_flag').val('2');					//本公司是否參加分保
	$('#nummyself_share').val('55');				//Share%
	$('#numother_share').val('56');					//其他再保公司Share%
	
	
	
	//--------------經紀人分攤比例--------------
	
}




