//編輯按鈕設置
let receiveParam = locationHrefKeepDataFetch();
let queryParam = {};
let detailBtn = [{
    name: "編輯",
    func: function(row) {
        //1-參數
        let param = {
            "cessionNo": row.getData().cessionNo,
            "cessionName": row.getData().cessionName,
            "slipNo": row.getData().slipNo,
            "gsProcessMode": $('input[name = radio_checkbox_kind1]:checked').val(),
            "preSlipNo": row.getData().preSlipNo,
            "preCessionNo": row.getData().preCessionNo,
            "treatyDend": row.getData().treatyDend,
            "facType": row.getData().facType,
            "queryParam": queryParam
        }

        if (param.gsProcessMode == '3') {
            param.preSlipNo = row.getData().slipNo;
            param.preCessionNo = row.getData().cessionNo;
        }

        if ($('input[name = radio_checkbox_kind2]:checked').val() != '4') {
            param.logSeq = row.getData().logSeq;
        }

        //2-轉頁
        if (checkIsNullSpace(param.gsProcessMode)) {
            alert("請點選操作選項");
            return;
        } else {
            $(window).unbind('beforeunload');
            //locationHrefWindowBlankKeepData('Rin1301A', '', param);
            locationHrefKeepDataType2('Rin1301A', '', JSON.stringify(param));
        }
    }
}];


//tabulator欄位設置
let columns1301_1 = [
    ["button", "功能", detailBtn, { widthShrink: 1 }],
    ["policyNo", "保單號碼", "display"],
    ["endorseNo", "批單號碼", "display"],
    ["cessionNo", "合約號", "display"],
    ["cessionName", "合約名稱", "display", { widthGrow: 2 }],
    ["slipNo", "知會/更正號", "display"],
    ["logSeq", "修改註記", "display", { widthShrink: 1 }],
    ["printType", "帳單列印", "display", { widthShrink: 1 }],
    ["conversionStatus", "會計轉檔狀況", "display", { widthShrink: 2 }]

];

//tabulator欄位格式製作
let colsFormat1301_1 = createTableColumns(columns1301_1);

//客製tabulator本體
let tableConfigs1301_1 = {
    placeholder: "無資料",
};


//按鈕設置與功能
let tableRelatedBtns1301_1 = [];

//檢核警告設定
let alertConfig1301_1 = {};

let table1301_1 = createTable("table1301_1", colsFormat1301_1, tableConfigs1301_1, tableRelatedBtns1301_1, alertConfig1301_1);


//tabulator欄位設置
let columns1301_2 = [
    ["button", "功能", detailBtn],
    ["policyNo", "保單號碼", "display"],
    ["endorseNo", "批單號碼", "display"],
    ["cessionNo", "合約號", "display"],
    ["cessionName", "合約名稱", "display", { widthGrow: 2 }],
    ["slipNo", "知會/更正號", "display"],
    ["tmp", "處理狀態", "display"],
    ["acctFlag", "列印狀態", "display"]
];

//tabulator欄位格式製作
let colsFormat1301_2 = createTableColumns(columns1301_2);

//客製tabulator本體
let tableConfigs1301_2 = {};

//按鈕設置與功能
let tableRelatedBtns1301_2 = [];

//檢核警告設定
let alertConfig1301_2 = {};

/**
 * 臨分資料維護主頁查詢
 * 
 */
function btnRin1301QueryMainData() {
    //1-參數
    let radioKind2 = $('input[name = radio_checkbox_kind2]:checked').val();
    let param = genParam(radioKind2);
    if (Object.keys(param).length != 0) {
        //2-執行查詢
        //若點選查詢未列印及暫緩或不轉之臨分
        if ("4" === radioKind2) {
            ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryFacPrintAccount", true, false, param,
                (res) => {
                    if (res != null && "000" === res.status) {
                        if (!checkIsNullSpace(res.message)) {
                            alert(res.message);
                            return;
                        }
                        //建立table
                        let table = Tabulator.prototype.findTable('#table1301_1')[0];
                        table.setColumns(colsFormat1301_2);
                        loadData("table1301_1", res.data, { type: "dataCount", value: 5 });
                        queryParam = JSON.parse(JSON.stringify(param));
                        queryParam.radioKind2 = radioKind2;
                    } else {
                        console.log(res.message);
                        alert(" 臨分資料維護主頁查詢失敗，請聯絡系統管理員！");
                    }
                }, (error) => {
                    console.log(error);
                    alert("臨分資料維護主頁查詢失敗，請聯絡系統管理員！");
                })


        }
        //若點選保單號碼或知會/更正號或合約號
        else if ("1" === radioKind2 || "2" === radioKind2 || "3" === radioKind2) {
            ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryFacPolicy", true, false, param,
                (res) => {
                    if (res != null && "000" === res.status) {
                        if (!checkIsNullSpace(res.message)) {
                            alert(res.message);
                            return;
                        }
                        //建立table
                        let table = Tabulator.prototype.findTable('#table1301_1')[0];
                        table.setColumns(colsFormat1301_1);
                        loadData("table1301_1", res.data, { type: "dataCount", value: 10 });
                        queryParam = JSON.parse(JSON.stringify(param));
                        queryParam.radioKind2 = radioKind2;
                    } else {
                        console.log(res.message);
                        alert("臨分資料維護主頁查詢失敗，請聯絡系統管理員！");
                    }
                }, (error) => {
                    console.log(error);
                    alert("臨分資料維護主頁查詢失敗，請聯絡系統管理員！");
                })
        }
    }

}


function genParam(value) {
    let policyNo = ""; //保單號碼
    let slipNo = ""; //知會/更正號
    let cessionNo = ""; //合約號
    let policyDprtBgn = ""; //查詢未列印及暫緩或不轉之臨分起日
    let policyDprtEnd = ""; //查詢未列印及暫緩或不轉之臨分訖日
    let errorMsg = "";
    let param = {};
    switch (value) {
        case "1":
            policyNo = $('#txtPolicyNo').val();
            if (checkIsNullSpace(policyNo)) {
                errorMsg += "請輸入保單號碼";
            }
            break;
        case "2":
            slipNo = $('#txtSlipNo').val();
            if (checkIsNullSpace(slipNo)) {
                errorMsg += "請輸入知會/更正號";
            }
            break;

        case "3":
            cessionNo = $('#txtCessionNo').val();
            if (checkIsNullSpace(cessionNo)) {
                errorMsg += "請輸入合約號";
            }
            break;
        case "4":
            policyDprtBgn = $('#txtTreatyDBgn').val();
            policyDprtEnd = $('#txtTreatyDEnd').val();
            if (!checkIsNullSpace(policyDprtBgn) && !checkDateFormat(policyDprtBgn)) {
                errorMsg += "起日格式不符合西元年/月/日";
            }

            if (!checkIsNullSpace(policyDprtEnd) && !checkDateFormat(policyDprtEnd)) {
                errorMsg += "迄日格式不符合西元年/月/日";
            }

            if (!checkIsNullSpace(policyDprtBgn) && !checkIsNullSpace(policyDprtEnd)) {
                let begDate = new Date(policyDprtBgn);
                let endDate = new Date(policyDprtEnd);
                if (begDate > endDate) {
                    errorMsg += "起日應小於等於迄日";
                }
            }
            break;
        default:
            errorMsg += "請選擇查詢條件";
            break;
    }

    if (!checkIsNullSpace(errorMsg)) {
        alert(errorMsg);
    } else {
        param = {
            "policyNo": policyNo,
            "slipNo": slipNo,
            "cessionNo": cessionNo,
            "policyDprtBgn": policyDprtBgn,
            "policyDprtEnd": policyDprtEnd
        }
    }

    return param;


}

//新增臨分資料轉頁
function linkNewRin1301DataPage() {
    $(window).unbind('beforeunload');
    let passParam = {
        "gsProcessMode": "1"
    }
    locationHrefKeepDataType2('Rin1301A', '', JSON.stringify(passParam));
}

function checkDataFormat(dateId) {

    let inputDate = $(`#${dateId}`).val();
    //console.log(inputDate);
    if (!checkIsNullSpace(inputDate) && !checkDateFormat(inputDate)) {
        $(`#Uc${dateId}`).show();
    } else if (checkIsNullSpace(inputDate) || (!checkIsNullSpace(inputDate) && checkDateFormat(inputDate))) {
        $(`#Uc${dateId}`).hide();
    }
}




$(function() {
    $('input[name=radio_checkbox_kind1][value=2]').prop('checked', true);

    $('#txtPolicyNo').focus(function() {
        $('input[name=radio_checkbox_kind2][value=1]').prop('checked', true);
    });

    $('input[name=radio_checkbox_kind2][value=1]').click(function() {
        $('#txtPolicyNo').focus();
    });

    $('#txtSlipNo').focus(function() {
        $('input[name=radio_checkbox_kind2][value=2]').prop('checked', true);
    });

    $('input[name=radio_checkbox_kind2][value=2]').click(function() {
        $('#txtSlipNo').focus();
    });

    $('#txtCessionNo').focus(function() {
        $('input[name=radio_checkbox_kind2][value=3]').prop('checked', true);
    });

    $('input[name=radio_checkbox_kind2][value=3]').click(function() {
        $('#txtCessionNo').focus();
    });

    $('#txtTreatyDBgn').focus(function() {
        $('input[name=radio_checkbox_kind2][value=4]').prop('checked', true);
    });

    $('input[name=radio_checkbox_kind2][value=4]').click(function() {
        $('#txtTreatyDBgn').focus();
    });

    $('#txtTreatyDEnd').focus(function() {
        $('input[name=radio_checkbox_kind2][value=4]').prop('checked', true);
    });

    if (!checkIsNullSpace(receiveParam) && !checkIsNullSpace(receiveParam.queryParam)) {
        $('#txtPolicyNo').val(receiveParam.queryParam.policyNo);
        $('#txtSlipNo').val(receiveParam.queryParam.slipNo);
        $('#txtCessionNo').val(receiveParam.queryParam.cessionNo);
        $('#txtTreatyDBgn').val(receiveParam.queryParam.policyDprtBgn);
        $('#txtTreatyDEnd').val(receiveParam.queryParam.policyDprtEnd);
        $('input[name=radio_checkbox_kind2][value=' + receiveParam.queryParam.radioKind2 + ']').prop('checked', true);
        $('input[name=radio_checkbox_kind1][value=' + receiveParam.gsProcessMode + ']').prop('checked', true);

        btnRin1301QueryMainData();
    }

});