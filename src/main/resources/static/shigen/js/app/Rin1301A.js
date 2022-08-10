/**
 *  Rin1301臨分資料維護新增/修改功能
 */

let receiveParam = locationHrefKeepDataFetch();
//console.log(receiveParam);
let propNoList = [];
let insKindList = [];
let rinserList = [];
let exchangeList = [];
let chooseRinser = {};
let lsvPolicy10Data = [];
let exceptInsKindList = [];
let rinserDetailList = [];
let brokerDetailList = [];
let chooseSameRiskAndPolNoObj = {};
let riskCodeArr = [];
let rinPolEndorArr = [];
let rinDataObj = {};
initCheck();


function initCheck() {
    if (checkIsNullSpace(receiveParam) || checkIsNullSpace(receiveParam.gsProcessMode)) {
        alert("請重新點選臨分資料維護操作選項，\n將轉回臨分資料維護主頁");
        let passParam = {
            "gsProcessMode": ""
        }
        locationHrefKeepDataType2('Rin1301', '', JSON.stringify(passParam));
    } else {
        createTableSet();
        btnSetUpByGsProcessMode();
        // 同步幣別匯率兩個位置的值
        setTimeout(function(){buildCurrency_1();},500);
    }
}


function btnSetUpByGsProcessMode() {
    let gsProcessMode = receiveParam.gsProcessMode;
    //console.log("gsProcessMode:" + gsProcessMode)
    switch (gsProcessMode) {
        case "1": //新增
            // $('#cmdPrev').hide();
            // $('#cmdNext').hide();

            $('#txtSlipYear').val(new Date().getFullYear());
            $('#txtCessionYear').val(new Date().getFullYear());
            $('#facType').val('1');
            $('#fac_type2').val('1');
            pageDefaultValue();
            editAreaReadOnly('rinserEditArea1');
            editAreaReadOnly('rinserEditArea2');
            editAreaReadOnly('brokerEditArea');
            genRinserList();
            genExChangeList().then(() => {

                var ntdOptionArr = exchangeList.filter((item) => {
                    return item.optionKey == 'NTD';
                })

                if (ntdOptionArr == null || ntdOptionArr.length == 0) {
                    exchangeList.push({
                        optionValue: 1.0000,
                        optionKey: 'NTD'
                    })
                }

                $.each(exchangeList, function(i, item) {
                    let option = document.createElement('option');
                    option.value = item.optionValue;
                    option.text = item.optionKey;
                    document.getElementById("billCurrency").appendChild(option);

                    // $('#billCurrency').appendChild($('<option>', {
                    //     value: item.optionValue,
                    //     text: item.optionKey
                    // }));
                });

                let currencyOption = $("#billCurrency option").filter(function() {
                    return $(this).text() == 'NTD';
                })
                if (currencyOption != null) {
                    currencyOption.prop("selected", true);
                    takeExchangeRate();
                }

                $('#cmdInsert').show();
            })
            break;
        case "2": //臨分更正
            $('#txtSlipYear').prop('readonly', true);
            $('#txtCessionYear').prop('readonly', true);
            $('input[name=genRecordNo]').attr("disabled", true);
            $('#txtSlipNo').val(receiveParam.slipNo);
            $('#txtCessionNo').val(receiveParam.cessionNo);
            editAreaReadOnly('rinserEditArea1');
            editAreaReadOnly('rinserEditArea2');
            editAreaReadOnly('brokerEditArea');
            genRinserList();
            let exChangeListPromise = genExChangeList().then(() => {
                var ntdOptionArr = exchangeList.filter((item) => {
                    return item.optionKey == 'NTD';
                })

                if (ntdOptionArr == null || ntdOptionArr.length == 0) {
                    exchangeList.push({
                        optionValue: 1.0000,
                        optionKey: 'NTD'
                    })
                }
                $.each(exchangeList, function(i, item) {
                    let option = document.createElement('option');
                    option.value = item.optionValue;
                    option.text = item.optionKey;
                    document.getElementById("billCurrency").appendChild(option);

                    // $('#billCurrency').append($('<option>', {
                    //     value: item.optionValue,
                    //     text: item.optionKey
                    // }));
                });
            })

            let rinDataPromise = queryRinDataForEdit().then(() => {
                loadData("lsvPolicy1", rinDataObj.lsvPolicy1, { type: "height", value: "250PX" });
                loadData(
                    "lsvPolicy2", rinDataObj.lsvPolicy2, { type: "height", value: "250PX" });
                loadData("lsvPolicy3", rinDataObj.lsvPolicy3, { type: "height", value: "250PX" });
                loadData("lsvPolicy4", rinDataObj.lsvPolicy4, { type: "height", value: "250PX" });
                loadData("lsvPolicy6", rinDataObj.lsvPolicy6, { type: "dataCount", value: 10 });
                loadData("lsvPolicy7", rinDataObj.lsvPolicy7, { type: "dataCount", value: 10 });
                loadData("lsvPolicy10", rinDataObj.facSharesDetail, { type: "height", value: "250PX" });

                settleRinMainData();				
            })

            let rinserDetailPromise = queryRinserDetail().then(() => {
                loadData("rinserDetail", rinserDetailList, { type: "dataCount", value: 10 }).then(() => {})
                calSumRinInfo();
            });
            let brokerDetailPromise = queryBrokerDetail().then(() => {
                loadData("brokerDetail", brokerDetailList, { type: "dataCount", value: 10 });
            });

            Promise.all([exChangeListPromise, rinDataPromise, rinserDetailPromise, brokerDetailPromise])
                .then(() => {
                    let currencyOption = $("#billCurrency option").filter(function() {
                        //console.log($(this));
                        return $(this).text() == rinDataObj.mainData.currency;
                    });

                    if (currencyOption != null) {
                        currencyOption.prop("selected", true);
                        currencyOption.val(rinDataObj.mainData.currencyexchangerate);
                    }
                    $('#cmdUpdate').show();
                    $('#cmdDelete').show();
                });
            break;
        case "3": //臨分批單
            $('#txtCessionNo').val(receiveParam.cessionNo);
            $('#txtCessionYear').val(receiveParam.cessionNo.substring(0, 4));
            $('#txtCessionYear').prop('readonly', true);
            $('#txtCessionName').val(receiveParam.cessionName);
            $('#txtTreatyDEnd').val(receiveParam.treatyDend);
            $('#lastSlipNo').text(receiveParam.slipNo);
            $('#lastSlipNo').show();
            $('#lastCessionNo').text(receiveParam.cessionNo);
            $('#lastCessionNo').show();
            $('#facType').val(receiveParam.facType);
            if (receiveParam.facType == '1') {
                $('#fac_type2').val('1');
            }
            pageDefaultValue();
            editAreaReadOnly('rinserEditArea1');
            editAreaReadOnly('rinserEditArea2');
            editAreaReadOnly('brokerEditArea');
            genRinserList();
            queryRinserDetail().then(() => {
                rinserDetailList.forEach((item) => {
                    item.shareStatusStr = '協商中';
                });

                loadData("rinserDetail", rinserDetailList, { type: "dataCount", value: 10 });
            });
            queryBrokerDetail().then(() => {
                loadData("brokerDetail", brokerDetailList, { type: "dataCount", value: 10 });
            });
            genExChangeList().then(() => {
                var ntdOptionArr = exchangeList.filter((item) => {
                    return item.optionKey == 'NTD';
                })

                if (ntdOptionArr == null || ntdOptionArr.length == 0) {
                    exchangeList.push({
                        optionValue: 1.0000,
                        optionKey: 'NTD'
                    })
                }

                $.each(exchangeList, function(i, item) {
                    let option = document.createElement('option');
                    option.value = item.optionValue;
                    option.text = item.optionKey;
                    document.getElementById("billCurrency").appendChild(option);

                    // $('#billCurrency').append($('<option>', {
                    //     value: item.optionValue,
                    //     text: item.optionKey
                    // }));
                });

                let currencyOption = $("#billCurrency option").filter(function() {
                    return $(this).text() == 'NTD';
                })
                if (currencyOption != null) {
                    currencyOption.prop("selected", true);
                    takeExchangeRate();
                }

                $('#cmdInsert').show();
            });

            queryFacSameRiskList().then(() => {
                $('input[name="sameRistCode"]').each(function(index) {
                    $(this).val(chooseSameRiskAndPolNoObj['samerisk' + (index + 1)]);
                });

                $('#polEnrNoList :input[name="queryPolicy"]').each(function(i, pol) {
                    $(this).val(chooseSameRiskAndPolNoObj['policy' + (i + 1)]);
                });

                $('#polEnrNoList :input[name="queryEndorse"]').each(function(j, enr) {
                    $(this).val(chooseSameRiskAndPolNoObj['endorse' + (j + 1)]);
                })
            });
            break;
        case "4": //臨分績保
            $('#lastSlipNo').text(receiveParam.slipNo);
            $('#lastSlipNo').show();
            $('#lastCessionNo').text(receiveParam.cessionNo);
            $('#lastCessionNo').show();
            if (!checkIsNullSpace(receiveParam.cessionNo)) {
                $('#txtCessionYear').val(receiveParam.cessionNo.substring(0, 4));
            }
            $('#txtSlipYear').val(new Date().getFullYear());
            $('#txtCessionName').val(receiveParam.cessionName);
            $('#facType').val(receiveParam.facType);
            pageDefaultValue();
            genRinserList();
            editAreaReadOnly('rinserEditArea1');
            editAreaReadOnly('rinserEditArea2');
            editAreaReadOnly('brokerEditArea');
            queryRinserDetail().then(() => {
            	//若為臨分續保模式，則只留下再保人代號、再保人名稱、分出比率資料、每一行的id，其餘資料不留
                rinserDetailList.forEach((item) => {
                	Object.keys(item).forEach(key=>{
                		if(key !== "rinComId" && key !== "rinserEname" && key !== "shareRate" && key != "id"){                			
                			item[key]=""
                		}
                	})
                });                
                loadData("rinserDetail", rinserDetailList, { type: "dataCount", value: 10 });
            });
            queryBrokerDetail().then(() => {
            	//若為臨分續保模式，則只留下再保型態、再保人經紀人、再保人、比率(%)、每一行的id，其餘資料不留
            	brokerDetailList.forEach((item) => {
                	Object.keys(item).forEach(key=>{
                		if(key !== "facTypeStr" && key !== "brokerId" && key !== "rinComId" && key !== "shareRate" && key != "id"){                			
                			item[key]=""
                		}
                	})
                });
                loadData("brokerDetail", brokerDetailList, { type: "dataCount", value: 10 });
            });
            genExChangeList().then(() => {
                var ntdOptionArr = exchangeList.filter((item) => {
                    return item.optionKey == 'NTD';
                })

                if (ntdOptionArr == null || ntdOptionArr.length == 0) {
                    exchangeList.push({
                        optionValue: 1.0000,
                        optionKey: 'NTD'
                    })
                }

                $.each(exchangeList, function(i, item) {
                    let option = document.createElement('option');
                    option.value = item.optionValue;
                    option.text = item.optionKey;
                    document.getElementById("billCurrency").appendChild(option);

                    // $('#billCurrency').append($('<option>', {
                    //     value: item.optionValue,
                    //     text: item.optionKey
                    // }));
                });

                let currencyOption = $("#billCurrency option").filter(function() {
                    return $(this).text() == 'NTD';
                })
                if (currencyOption != null) {
                    currencyOption.prop("selected", true);
                    takeExchangeRate();
                }

                $('#cmdInsert').show();
            });

            break;
        default:
            alert("不符合臨分資料維護操作選項,請重新執行");
    }
}


function pageDefaultValue() {
    $('#polEnrNo').val('00');
    $('#excessBgn').val(0);
    $('#excessLimit').val(0);
    $('#exchangeRate').val(1);
    $('input[name=orgcomm]').val(0);
    $('input[name=orgtax]').val(0);
    $('input[name=orgprem]').val(0);
    $('input[name=genRecordNo]').val(['1']);
}



function queryFacSameRiskList() {
    let param = {
        "slipNo": receiveParam.slipNo,
    }

    if (!checkIsNullSpace(receiveParam.logSeq) && receiveParam.logSeq.startsWith('LOG-')) {
        param.logSeq = receiveParam.logSeq.replace('LOG-', '');
    }

    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryFacSameRiskList", true, false, param,
        (res) => {

            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得臨分保批單清單失敗,請聯絡系統管理員");
                }
                return;
            }

            chooseSameRiskAndPolNoObj = res.data;

        }, (error) => {
            console.log(error);
            alert("取得臨分保批單清單失敗,請聯絡系統管理員");
        })
}



function queryRinDataForEdit() {
    let param = {
        "slipNo": receiveParam.slipNo,
    }

    if (!checkIsNullSpace(receiveParam.logSeq) && receiveParam.logSeq.startsWith('LOG-')) {
        param.logSeq = receiveParam.logSeq.replace('LOG-', '');
    }
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryRinData", true, false, param,
        (res) => {

            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得臨分修改資料發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            rinDataObj = res.data;


        }, (error) => {
            console.log(error);
            alert("取得臨分修改資料發生錯誤,請聯絡系統管理員");
        })
}


function queryRinserDetail() {
    let param = {
        "slipNo": receiveParam.slipNo,
    }

    if (!checkIsNullSpace(receiveParam.logSeq) && receiveParam.logSeq.startsWith('LOG-')) {
        param.logSeq = receiveParam.logSeq.replace('LOG-', '');
    }
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryRinserDetail", true, false, param,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得再保人明細資料發生錯誤,請聯絡系統管理員");
                }
                return;

            }
            //console.log(data.data.rinserDetail);
            res.data.rinserDetail.forEach((item, i) => {
                    let shareStatusStr = "";
                    switch (item.shareStatus) {
                        case "1":
                            shareStatusStr = "協商中";
                            break;
                        case "2":
                            shareStatusStr = "完成";
                            break;
                        case "3":
                            shareStatusStr = "回覆拒絕";
                            break;
                        case "0":
                            shareStatusStr = "暫定出單";
                            break;
                    }

                    let tableData = JSON.parse(JSON.stringify(item));
                    tableData.id = i + 1;
                    tableData.shareStatusStr = shareStatusStr;
                    rinserDetailList.push(tableData);
                })
                //console.log(rinserDetailList);

        }, (error) => {
            console.log(error);
            alert("取得再保人明細資料發生錯誤,請聯絡系統管理員");
        })
}

function queryBrokerDetail() {
    let param = {
        "slipNo": receiveParam.slipNo,
    }
    if (!checkIsNullSpace(receiveParam.logSeq) && receiveParam.logSeq.startsWith('LOG-')) {
        param.logSeq = receiveParam.logSeq.replace('LOG-', '');
    }
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryBrokerDetail", true, false, param,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得經紀人明細資料發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            //console.log(data.data.brokerDetail);
            res.data.brokerDetail.forEach((item, i) => {
                    let facTypeStr = "";
                    switch (item.facType) {
                        case "1":
                            facTypeStr = "比例";
                            break;
                        case "2":
                            facTypeStr = "非比例";
                            break;
                    }

                    let tableData = JSON.parse(JSON.stringify(item));
                    tableData.id = i + 1;
                    tableData.facTypeStr = facTypeStr;
                    brokerDetailList.push(tableData);
                })
                //console.log(brokerDetailList);

        }, (error) => {
            console.log(error);
            alert("取得經紀人明細資料發生錯誤,請聯絡系統管理員");
        })
}

function genInsKindList() {
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryPropInsKindList", true, false, null,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得險種類別清單發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            insKindList = res.data;
            //console.log(insKindList);

        }, (error) => {
            console.log(error);
            alert("取得險種類別清單發生錯誤,請聯絡系統管理員");
        })
}

function genPropList() {
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryPropNoList", true, false, null,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得附加險清單發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            propNoList = res.data;
            //console.log(propNoList);

        }, (error) => {
            console.log(error);
            alert("取得附加險清單發生錯誤,請聯絡系統管理員");
        })
}

function genRinserList() {
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1102apopapi/queryallfricom", true, false, null,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得再保人清單發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            rinserList = res.data;
            //console.log(rinserList);

        }, (error) => {
            console.log(error);
            alert("取得再保人清單發生錯誤,請聯絡系統管理員");
        })
}

function genExChangeList() {
    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryExChangeList", true, false, null,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("取得匯率資料清單發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            if (checkIsNullSpace(res.data)) {
                alert("無匯率資料,請聯絡系統管理員");
                return;
            }
            exchangeList = res.data;

            //console.log(exchangeList);

        }, (error) => {
            console.log(error);
            alert("取得匯率資料清單發生錯誤,請聯絡系統管理員");
        })
}


function checkPolicyNoSeqExist() {
    let checkMessage = "";
    let param = {
        "cessionNo": receiveParam.cessionNo,
        "policyNoSeq": $('input[id=polEnrNo]').val().trim()
    }

    return ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/checkPolicyNoSeqExist", true, false, param,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("查詢保批單作業序號是否被使用失敗,請聯絡系統管理員");
                }
                return;
            }
            if (!checkIsNullSpace(res.message)) {
                checkMessage += res.message;
            }

        }, (error) => {
            console.log(error);
            alert("查詢保批單作業序號是否被使用失敗,請聯絡系統管理員");
        }).then((resolve) => {
        return checkMessage;
    });
}


function checkYear(obj) {
    let year = $(obj).val();
    let yearId = $(obj).attr("id");
    if (Number(year) - 1911 <= 0 || year > 9999 || !year.match(/^\+?[1-9][0-9]*$/)) {
        $(`#Uc${yearId}`).show();
    } else {
        $(`#Uc${yearId}`).hide();
    }
}

function checkDate(obj) {
    let date = $(obj).val();
    let dateId = $(obj).attr("id");

    if (!checkDateFormat(date)) {
        $(`#Uc${dateId}`).show();
    } else {
        $(`#Uc${dateId}`).hide();
    }
}

function checkEnrNo(obj) {
    let enrNo = $(obj).val();
    let enrNoId = $(obj).attr("id");
    if (!enrNo.match(/^\d{2}$/)) {
        $(`#Uc${enrNoId}`).show();
    } else {
        $(`#Uc${enrNoId}`).hide();
    }

}

//計算生效天數
function calEffectDays() {
    let treatyDBgnDate = $('#txtTreatyDBgn').val();
    let treatyDEndDate = $('#txtTreatyDEnd').val();
    let dBgnPassCheckFlag = false;
    let dEndPassCheckFlag = false;

    // 因日期輸入方式及檢核有變，故註解checkDateFormat檢核
    if (!checkIsNullSpace(treatyDBgnDate)) {
//        if (!checkDateFormat(treatyDBgnDate)) {
//            $('#UctxtTreatyDBgn').show();
//            dBgnPassCheckFlag = false;
//        } else {
//            $(`#UctxtTreatyDBgn`).hide();
            dBgnPassCheckFlag = true;
//        }
    }

    if (!checkIsNullSpace(treatyDEndDate)) {
//        if (!checkDateFormat(treatyDEndDate)) {
//            $('#UctxtTreatyDEnd').show();
//            dEndPassCheckFlag = false;
//        } else {
//            $(`#UctxtTreatyDEnd`).hide();
            dEndPassCheckFlag = true;
//        }
    }


    if (dBgnPassCheckFlag && dEndPassCheckFlag) {
        let effectBgnDate = new Date(treatyDBgnDate);
        let effectEndDate = new Date(treatyDEndDate);
        if (effectBgnDate > effectEndDate) {
            alert("生效起日應小於生效迄日,請重新設定");
            $('#txtDays').val('');
            return;
        }

        let days = (effectEndDate - effectBgnDate) / (1000 * 60 * 60 * 24);
        $('#txtDays').val(days);


        // if (Object.keys(chooseSameRiskAndPolNoObj).length != 0) {
        //     alert("生效起迄日已變更,請重新執行臨分保單清單功能");
        //     return;
        // }
    }
}





function showRinPolQueryForm() {
    let treatyDBgn = $('#txtTreatyDBgn').val();
    let treatyDEnd = $('#txtTreatyDEnd').val();

    if (checkIsNullSpace(treatyDBgn) || checkIsNullSpace(treatyDEnd)) {
        alert("請輸入生效起日及迄日");
        return;
    }

    if ((!checkIsNullSpace(treatyDBgn) && !isValidDate(treatyDBgn)) || (!checkIsNullSpace(treatyDEnd) && !isValidDate(treatyDBgn))) {
        alert("生效起日及迄日格式有誤,應為西元年月日");
        return;
    }
    $('#myModalTwo').modal('show');
}


function queryPolDataBySameRiskArr() {
    let treatyDBgn = $('#txtTreatyDBgn').val();
    let treatyDEnd = $('#txtTreatyDEnd').val();
    chooseSameRiskAndPolNoObj = {};
    riskCodeArr = [];
    let errorMsg = "";

    $('input[name="sameRistCode"]').each(function(index) {
        if (!checkIsNullSpace($(this).val()) && $(this).val().length != 11) {
            errorMsg += "同險代號:" + $(this).val() + "未滿11碼，請確認。\n"
        }
    });

    if (!checkIsNullSpace(errorMsg)) {
        alert(errorMsg);
    } else {
        $('input[name="sameRistCode"]').each(function(index) {
            if (!checkIsNullSpace($(this).val())) {
                riskCodeArr.push($(this).val().trim());
                chooseSameRiskAndPolNoObj['samerisk' + (index + 1)] = $(this).val().trim();
            }
        });
        //console.log(chooseSameRiskAndPolNoObj);

        //檢核參數
        if (riskCodeArr == null || riskCodeArr.length == 0) {
            alert("請輸入同險代碼");
            return;
        }
        let apiParam = {
            "riskNos": riskCodeArr,
            "treatyDBgn": treatyDBgn,
            "treatyDEnd": treatyDEnd,
        }

        //console.log(apiParam);

        ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryPolicyDetailBySameRiskCode", true, false, apiParam,
            (res) => {

                if (res == null || res.status != "000") {
                    if (!checkIsNullSpace(res.message)) {
                        alert(res.message);
                    } else {
                        alert("查詢同險代號保單明細失敗,請聯絡系統管理員");
                    }
                    return;
                }
                loadData("table1301A_modelThree", res.data, { type: "height", value: "400PX" }).then(() => {
                    $('#myModalThree').modal('show');
                    $('#myModalTwo').modal('hide');
                    $('input[class="tabulator-select"]').prop('checked', false);
                })

            }, (error) => {
                console.log(error);
                alert("查詢同險代號保單明細失敗,請聯絡系統管理員");
            })
    }
}

function queryPolDetailTablesByModelThree() {
    let table = Tabulator.prototype.findTable('#table1301A_modelThree')[0];
    let paramArr = [];
    let mkovseN = table.getSelectedData().filter((item) => {
        return item.mkovse === 'N';
    });
    //console.log(mkovseN);

    let mkovseY = table.getSelectedData().filter((item) => {
        return item.mkovse === 'Y';
    });


    //console.log(mkovseY);

    table.getSelectedData().forEach(function(data) {
        let param = {
            "policyNo": data.policyNo,
            "endorseNo": data.endorseNo,
            "addrNo": data.addrNo,
            "cessionNo": $('#txtCessionNo').val(),
            "days": $('#txtDays').val()
        }
        paramArr.push(param);
    })

    if (paramArr.length == 0 || paramArr.filter(function(data) {
            return checkIsNullSpace(data.policyNo)
        }).length > 0) {
        alert("請點選欲查詢之保批單");
        return;
    } else if ((mkovseN != null && mkovseN.length > 0) && (mkovseY != null && mkovseY.length > 0)) {
        alert("保單之境外分入註記不一致, 請重新確認");
        return;
    } else {
        genlsvPolicyTables(paramArr, 'myModalThree');
    }
}



function queryPolDetailTablesByModelTwo() {
    let paramArr = [];
    chooseSameRiskAndPolNoObj = {};
    riskCodeArr = [];

    $('#polEnrNoList :input[name="queryPolicy"]').each(function(i, pol) {
            if (!checkIsNullSpace(pol.value)) {
                let param = {
                    "policyNo": "",
                    "endorseNo": "",
                    "cessionNo": $('#txtCessionNo').val(),
                    "days": $('#txtDays').val()
                }
                param.policyNo = pol.value;
                chooseSameRiskAndPolNoObj['policy' + (i + 1)] = pol.value;
                $('#polEnrNoList :input[name="queryEndorse"]').each(function(j, enr) {
                    if (i == j) {
                        param.endorseNo = enr.value;
                        chooseSameRiskAndPolNoObj['endorse' + (j + 1)] = enr.value;
                    }
                })
                paramArr.push(param);
            }
        })
        //console.log(chooseSameRiskAndPolNoObj);
        //console.log(paramArr);
    if (paramArr == null || paramArr.length == 0) {
        alert("請輸入欲查詢之保批單");
        return;
    } else {
        ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/checkPolEndorseByMkovse", true, false, paramArr,
            (res) => {

                if (res == null || res.status != "000") {
                    if (!checkIsNullSpace(res.message)) {
                        alert(res.message);
                    } else {
                        alert("查詢境外分出註記失敗,請聯絡系統管理員");
                    }
                    return;
                }
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                    return;
                }
                genlsvPolicyTables(paramArr, 'myModalTwo');



            }, (error) => {
                console.log(error);
                alert("查詢境外分出註記失敗,請聯絡系統管理員");
            })
    }

}


function queryRinser(value) {

    loadData("rinserTable", rinserList, { type: "height", value: "400PX" }).then(() => {
        $('#myModal').modal('show');
        $('#myModalTitle').text(function() {
            return value === 'broker' ? '經紀人資料' : '再保人資料';
        });
        $('input[name="reinserType"]').val(value);
        $('input[class="tabulator-select"]').prop('checked', false);
    });




}

function genRinserName(type, value) {
    if (checkIsNullSpace(value)) {
        $(`#${type}`).text("");
        return;
    } else {
        if (checkIsNullSpace(type)) {
            return;
        } else {
            let inputRinserArr = rinserList.filter(function(item) {
                return item.lblrin_com_id == value;
            })
            if (inputRinserArr == null || inputRinserArr.length == 0) {
                $(`#${type}`).text("");
                alert("查無再保人資料");
                return;
            } else {
                let logOutDate;
                let now = new Date();
                if (!checkIsNullSpace(inputRinserArr[0].dtaUSEMRK)) {
                    logOutDate = new Date(inputRinserArr[0].dtaUSEMRK);
                }

                if (!checkIsNullSpace(logOutDate) && logOutDate <= now) {
                    alert("此再保人已註銷");
                    return;
                } else {
                    let rinserName = inputRinserArr[0].lblename;
                    $(`#${type}`).text(rinserName);
                }
            }
        }
    }
}

function calRinAmtPrem() {
    let rate = $('input[name="share_rate"]').val();
    let amt = $('input[name="amtSum"]').val().replaceAll(",", "");
    let rinPremSum = $('input[name="rinPremSum"]').val().replaceAll(",", "");;

    $('input[name="share_amt"]').val(
        floatMul(floatDiv(rate, 100, 4), amt, 0)
    );

    $('input[name="share_rinPrem"]').val(
        floatMul(floatDiv(rate, 100, 4), rinPremSum, 0)
    );

}

function genRinserInfo() {
    if (!checkIsNullSpace(chooseRinser.dtaUSEMRK)) {
        let logOutDate = new Date(chooseRinser.dtaUSEMRK);
        let now = new Date();
        if (logOutDate <= now) {
            alert("此再保人已註銷");
            return;
        }
    } else {
        let type = $('input[name="reinserType"]').val();
        //console.log(type);
        switch (type) {
            case 'rinser_1':
                $('input[name="rin_com_id1"]').val(chooseRinser.lblrin_com_id);
                break;
            case 'broker':
                $('input[name="broker_id1"]').val(chooseRinser.lblrin_com_id);
                break;
            case 'rinser_2':
                $('input[name="rin_com_id2"]').val(chooseRinser.lblrin_com_id);
                break;
        }
        $(`#${type}`).text(chooseRinser.lblename);

        $('#myModal').modal('hide');

    }


}


function calSumRinInfo() {
    let tableDataArr = Tabulator.prototype.findTable('#rinserDetail')[0].getData();
    //console.log(tableDataArr);
    let rate = 0;
    let amt = 0;
    let rinPremSum = 0;
    tableDataArr.forEach((item) => {
    	// 分出比率
        rate = floatAdd(rate, item.shareRate, 4);
        // 分出保額 加總
        amt += Number.parseInt(item.cedeAmt);
        // 再保費出帳金額 加總
        rinPremSum += Number.parseInt(item.orgprem);
    })
    $('#sumRinRate').text(rate);
    $('#sumRinAmt').text(convertCurrency(amt))
    $('#sumRinPrem').text(convertCurrency(rinPremSum))
}

function vaildlsv8Data() {
    let invaildmsg = "";

    if (checkIsNullSpace($('input[name="rin_com_id1"]').val())) {
        invaildmsg += "再保人資料為必填。\n"
    } else {

        let inputRinserArr = rinserList.filter(function(item) {
            return item.lblrin_com_id == $('input[name="rin_com_id1"]').val();
        })
        if (inputRinserArr == null || inputRinserArr.length == 0) {
            invaildmsg += "查無再保人資料。\n";
        }

        let table = Tabulator.prototype.findTable('#rinserDetail')[0];
        let tableDataArr = [];
        if ($('input[name="lsv8job"]').val() == 'U') {
            tableDataArr = table.getData().filter(function(item) {
                return item.rinComId == $('input[name="rin_com_id1"]').val() &&
                    item.id != $('input[name="lsv8DataId"]').val();
            });
        }

        if ($('input[name="lsv8job"]').val() == 'C') {
            tableDataArr = table.getData().filter(function(item) {
                return item.rinComId == $('input[name="rin_com_id1"]').val();
            });
        }

        if (tableDataArr != null && tableDataArr.length != 0) {
            invaildmsg += "此再保人資料已存在。\n"
        }
    }

    if (checkIsNullSpace($('input[name="share_rinPrem"]').val())) {
        invaildmsg += "再保費台幣為必填。\n"
    }

    if (checkIsNullSpace($('input[name="share_rate"]').val())) {
        invaildmsg += "分出比例％為必填。\n"
    }

    if (checkIsNullSpace($('input[name="share_amt"]').val())) {
        invaildmsg += "分出保額為必填。\n"
    }

    if (checkIsNullSpace($('input[name="share_comm_rate"]').val())) {
        invaildmsg += "佣金率為必填。\n"
    }

    if (checkIsNullSpace($('input[name="tax_rate"]').val())) {
        invaildmsg += "營業稅率為必填。\n"
    }

    if (checkIsNullSpace($('#txtTreatyDBgn').val())) {
        invaildmsg += "生效起日為必填。\n"
    }


    return invaildmsg;
}

function clearlsv8EditArea() {
    $('input[name="rin_com_id1"]').val("");
    $('#rinser_1').text("");
    $('input[name="share_status"]').prop("checked", false);;
    $('input[name="share_rate"]').val("");
    $('input[name="share_amt"]').val(0);
    $('input[name="share_comm_rate"]').val("");
    $('input[name="orgcomm"]').val(0);
    $('input[name="tax_rate"]').val("");
    $('input[name="orgtax"]').val(0);
    $('input[name="share_rinPrem"]').val("");
    $('input[name="orgprem"]').val(0);
    $('input[name="ref_no"]').val("");
    $('input[name="answer_date"]').val("");
    $('input[name="paid_date"]').val("");
    $('input[name="paid_date_expect"]').val("");


}


function showlsv8Btn(status) {
    let table = Tabulator.prototype.findTable('#rinserDetail')[0];
    switch (status) {
        case 'C':
            editAreaEditable('rinserEditArea1');
            editAreaEditable('rinserEditArea2');
            table.tableMode = "add";
            break;
        case 'U':
            editAreaEditable('rinserEditArea1');
            editAreaEditable('rinserEditArea2');
            table.tableMode = "edit";
            break;
    }

    $('#lsv8confirmBtn').show();
    $('#lsv8cancelBtn').show();
    $('#lsv8Save').hide();
    $('#lsv8Edit').hide();
    $('#lsv8Delete').hide();
    $('input[name="lsv8job"]').val(status);

    if (!checkIsNullSpace($('input[name="lsv8DataId"]').val())) {
        table.editableRowIndex = $('input[name="lsv8DataId"]').val();
    } else {
        table.editableRowIndex = "selectedRow";
    }
    table.rowSelectionChangeCheck = true;
    $('#rinserDetail :button').prop("disabled", true);
}


function revertlsv8Btn() {
    let table = Tabulator.prototype.findTable('#rinserDetail')[0];
    $('#lsv8confirmBtn').hide();
    $('#lsv8cancelBtn').hide();
    $('#lsv8Save').show();
    $('#lsv8Edit').show();
    $('#lsv8Delete').show();
    $('input[name="lsv8job"]').val("");
    $('input[name="lsv8DataId"]').val("");
    $('#lsv8Edit').prop('disabled', true);
    $('#lsv8Delete').prop('disabled', true);
    editAreaReadOnly('rinserEditArea1');
    editAreaReadOnly('rinserEditArea2');
    table.tableMode = null;
    table.rowSelectionChangeCheck = false;
    table.deselectRow();
    $('#rinserDetail :button').prop("disabled", false);
}

function dolsv8Job() {
    let job = $('input[name=lsv8job]').val();
    let table = Tabulator.prototype.findTable('#rinserDetail')[0];
    let selectDataObj = table.getSelectedData()[0];
    let rinserArr = [];
    let saveConfirm = true;
    saveConfirm = confirm("是否確定儲存?");
    if (!saveConfirm) {
        return;
    } else {
        let vaildMsg = vaildlsv8Data();
        if (!checkIsNullSpace(vaildMsg)) {
            alert(vaildMsg);
            return;
        } else {
            let handleConfirm = true;
            let rinser = {
                rinComId: $('input[name="rin_com_id1"]').val(),
                treatyDBgn: $('#txtTreatyDBgn').val()
            }
            rinserArr.push(rinser);
            ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/chkRinser", true, false, rinserArr,
                (res) => {
                    //console.log(res);
                    let alertMsg = "";
                    let confirmMsg = "";
                    let statusCode = "";
                    if (res == null || res.status != '000') {
                        if (!checkIsNullSpace(res.message)) {
                            alert(res.message);
                        } else {
                            alert("檢核再保人發生錯誤,請聯絡系統管理員");
                        }
                        return;
                    }
                    if (res.data != null) {
                        res.data.forEach(item => {
                            statusCode = item.statusCode
                            if (item.status != null) {
                                $.each(item.status, (k, v) => {
                                    if (k == 'noChoose' || k == 'logout') {
                                        alertMsg += "再保人代號-" + item.rinComId + ":" + v + "，不可選擇" + "\n";
                                    }

                                    if (k == 'other') {
                                        confirmMsg += "請留意,再保人代號-" + item.rinComId + ":" + v + "\n"
                                    }
                                })
                            }
                        });
                    }
                    if (!checkIsNullSpace(alertMsg)) {
                        alert(alertMsg);
                        return;
                    }

                    if (!checkIsNullSpace(confirmMsg)) {
                        handleConfirm = confirm(confirmMsg);
                    }
                    if (handleConfirm) {
                        let shareStatus = $('input[name = share_status]:checked').val();
                        let shareStatusStr = "";
                        switch (shareStatus) {
                            case "1":
                                shareStatusStr = "協商中";
                                break;
                            case "2":
                                shareStatusStr = "完成";
                                break;
                            case "3":
                                shareStatusStr = "回覆拒絕";
                                break;
                            case "0":
                                shareStatusStr = "暫定出單";
                                break;
                        }
                        if (job == 'C') {
                            let tableData = {
                                id: table.getDataCount() + 1,
                                shareStatus: shareStatus,
                                shareStatusStr: shareStatusStr,
                                rinComId: $('input[name="rin_com_id1"]').val(),
                                rinserEname: $('#rinser_1').text(),
                                shareRate: $('input[name="share_rate"]').val(),
                                cedeAmt: $('input[name="share_amt"]').val(),
                                commRate: $('input[name="share_comm_rate"]').val(),
                                taxRate: $('input[name="tax_rate"]').val(),
                                // handling_rate: $('input[name="handling_rate"]').val(),
                                // broker_rate: $('input[name="broker_rate"]').val(),
                                // discount_rate: $('input[name="discount_rate"]').val(),
                                cedePrem: $('input[name="share_rinPrem"]').val(),
                                answerDate: $('input[name="answer_date"]').val(),
                                paidDate: $('input[name="paid_date"]').val(),
                                paidDateExpect: $('input[name="paid_date_expect"]').val(),
                                printDate: "",
                                transferDate: "",
                                orgcomm: $('input[name="orgcomm"]').val(),
                                orgtax: $('input[name="orgtax"]').val(),
                                orgprem: $('input[name="orgprem"]').val(),
                                refNo: $('input[name="ref_no"]').val(),
                                statusCode: statusCode
                            }
                            table.addData([tableData], false);
                        }

                        if (job == 'U') {
                            selectDataObj.shareStatus = shareStatus;
                            selectDataObj.shareStatusStr = shareStatusStr;
                            selectDataObj.rinComId = $('input[name="rin_com_id1"]').val();
                            selectDataObj.rinserEname = $('#rinser_1').text();
                            selectDataObj.shareRate = $('input[name="share_rate"]').val();
                            selectDataObj.cedeAmt = $('input[name="share_amt"]').val();
                            selectDataObj.commRate = $('input[name="share_comm_rate"]').val();
                            selectDataObj.taxRate = $('input[name="tax_rate"]').val();
                            // selectDataObj.handling_rate = $('input[name="handling_rate"]').val(),
                            // selectDataObj.broker_rate = $('input[name="broker_rate"]').val(),
                            // selectDataObj.discount_rate = $('input[name="discount_rate"]').val(),
                            selectDataObj.cedePrem = $('input[name="share_rinPrem"]').val();
                            selectDataObj.answerDate = $('input[name="answer_date"]').val();
                            selectDataObj.paidDate = $('input[name="paid_date"]').val();
                            selectDataObj.paidDateExpect = $('input[name="paid_date_expect"]').val();
                            selectDataObj.printDate = "";
                            selectDataObj.transferDate = "";
                            selectDataObj.orgcomm = $('input[name="orgcomm"]').val();
                            selectDataObj.orgtax = $('input[name="orgtax"]').val();
                            selectDataObj.orgprem = $('input[name="orgprem"]').val();
                            selectDataObj.refNo = $('input[name="ref_no"]').val();
                            selectDataObj.statusCode = statusCode;
                            table.updateRow(selectDataObj.id);
                        }
                        calSumRinInfo();
                        revertlsv8Btn();
                        clearlsv8EditArea();
                    }
                }, (error) => {
                    console.log(error);
                    alert("檢核再保人發生錯誤,請聯絡系統管理員");
                })
        }

    }

}

function delLsv8Data() {
    let table = Tabulator.prototype.findTable('#rinserDetail')[0];
    table.tableMode = "del";
    let selectDataObj = table.getSelectedData()[0];
    let deleteConfirm = confirm("是否確定刪除?");
    if (!deleteConfirm) {
        revertlsv8Btn();
        clearlsv8EditArea();
        return;
    } else {
        table.deleteRow(selectDataObj.id);
        table.getData().forEach(function(item, index) {
            item.id = index + 1;
        });
        calSumRinInfo();
        revertlsv8Btn();
        clearlsv8EditArea();
    }
}



function vaildlsv9Data() {
    let invaildmsg = "";

    if (checkIsNullSpace($('#fac_type2').val())) {
        invaildmsg += "再保型態為必填。\n"
    }

    if (checkIsNullSpace($('input[name="broker_id1"]').val())) {
        invaildmsg += "經紀人資料為必填。\n"
    }

    if (checkIsNullSpace($('input[name="rin_com_id2"]').val())) {
        invaildmsg += "再保人資料為必填。\n"
    }

    if (!checkIsNullSpace($('input[name="broker_id1"]').val()) && !checkIsNullSpace($('input[name="rin_com_id2"]').val())) {
        let table = Tabulator.prototype.findTable('#brokerDetail')[0];
        let tableDataArr = [];
        if ($('input[name="lsv9job"]').val() == 'U') {
            tableDataArr = table.getData().filter(function(item) {
                return item.brokerId == $('input[name="broker_id1"]').val() &&
                    item.rinComId == $('input[name="rin_com_id2"]').val() &&
                    item.id != $('input[name="lsv9DataId"]').val();
            });
        }

        if ($('input[name="lsv9job"]').val() == 'C') {
            tableDataArr = table.getData().filter(function(item) {
                return item.brokerId == $('input[name="broker_id1"]').val() &&
                    item.rinComId == $('input[name="rin_com_id2"]').val();

            });
        }


//        if (tableDataArr != null && tableDataArr.length != 0) {
//            invaildmsg += "此經紀人/再保人資料已存在。\n"
//        }

        let inputBrokerArr = rinserList.filter(function(item) {
            return item.lblrin_com_id == $('input[name="broker_id1"]').val();
        })
        if (inputBrokerArr == null || inputBrokerArr.length == 0) {
            invaildmsg += "查無經紀人資料。\n";
        }

        let inputRinserArr = rinserList.filter(function(item) {
            return item.lblrin_com_id == $('input[name="rin_com_id2"]').val();
        })
        if (inputRinserArr == null || inputRinserArr.length == 0) {
            invaildmsg += "查無再保人資料。\n";
        }
    }


    if (checkIsNullSpace($('input[name="brkprem100"]').val())) {
        invaildmsg += "再保費(100%)為必填。\n"
    }

    if (checkIsNullSpace($('input[name="brkshare_rate"]').val())) {
        invaildmsg += "比率(%)為必填。\n"
    }


    if (checkIsNullSpace($('input[name="brkcomm_rate"]').val())) {
        invaildmsg += "佣金(%)為必填。\n"
    }

    if (checkIsNullSpace($('input[name="brktax_rate"]').val())) {
        invaildmsg += "營業稅(%)為必填。\n"
    }

    if (checkIsNullSpace($('input[name="brkexcess_bgn"]').val())) {
        invaildmsg += "100%起賠額為必填。\n"
    }

    if (checkIsNullSpace($('input[name="brkexcess_limit"]').val())) {
        invaildmsg += "100%責任額為必填。\n"
    }

    if (checkIsNullSpace($('#txtTreatyDBgn').val())) {
        invaildmsg += "生效起日為必填。\n"
    }

    return invaildmsg;
}

function showlsv9Btn(status) {
    let table = Tabulator.prototype.findTable('#brokerDetail')[0];
    switch (status) {
        case 'C':
            settleBrokerFacType($('#facType').val());
            editAreaEditable('brokerEditArea');
            table.tableMode = "add";
            break;
        case 'U':
            editAreaEditable('brokerEditArea');
            table.tableMode = "edit";
            break;
    }

    $('#lsv9confirmBtn').show();
    $('#lsv9cancelBtn').show();
    $('#lsv9Save').hide();
    $('#lsv9Edit').hide();
    $('#lsv9Delete').hide();
    $('input[name="lsv9job"]').val(status);
    if (!checkIsNullSpace($('input[name="lsv9DataId"]').val())) {
        table.editableRowIndex = $('input[name="lsv9DataId"]').val();
    } else {
        table.editableRowIndex = "selectedRow";
    }
    table.rowSelectionChangeCheck = true;
    $('#brokerDetail :button').prop("disabled", true);

}

function delLsv9Data() {
    let table = Tabulator.prototype.findTable('#brokerDetail')[0];
    table.tableMode = "del";
    let selectDataObj = table.getSelectedData()[0];
    let deleteConfirm = confirm("是否確定刪除?");
    if (!deleteConfirm) {
        revertlsv9Btn();
        clearlsv9EditArea();
        return;
    } else {
        table.deleteRow(selectDataObj.id);
        table.getData().forEach(function(item, index) {
            item.id = index;
        });
        revertlsv9Btn();
        clearlsv9EditArea();
    }
}


$('#lsv9cancelBtn').on('click', () => {
    revertlsv9Btn();
    clearlsv9EditArea();
})


$('#lsv8cancelBtn').on('click', () => {
    revertlsv8Btn();
    clearlsv8EditArea();
})

function dolsv9Job() {
    let job = $('input[name=lsv9job]').val();
    let table = Tabulator.prototype.findTable('#brokerDetail')[0];
    let selectDataObj = table.getSelectedData()[0];
    let rinserArr = [];
    let saveConfirm = true;
    saveConfirm = confirm("是否確定儲存?");
    if (!saveConfirm) {
        return;
    } else {
        let vaildMsg = vaildlsv9Data();
        if (!checkIsNullSpace(vaildMsg)) {
            alert(vaildMsg);
            return;
        } else {
            let handleConfirm = true;
            let rinser = {
                rinComId: $('input[name="rin_com_id2"]').val(),
                treatyDBgn: $('#txtTreatyDBgn').val()
            }
            rinserArr.push(rinser);
            let broker = {
                rinComId: $('input[name="broker_id1"]').val(),
                treatyDBgn: $('#txtTreatyDBgn').val()
            }
            rinserArr.push(broker);
            ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/chkRinser", true, false, rinserArr,
                (res) => {
                    //console.log(res);
                    let alertMsg = "";
                    let confirmMsg = "";
                    if (res == null || res.status != '000') {
                        if (!checkIsNullSpace(res.message)) {
                            alert(res.message);
                        } else {
                            alert("檢核再保人發生錯誤,請聯絡系統管理員");
                        }
                        return;
                    }
                    if (res.data != null) {
                        res.data.forEach(item => {
                            //console.log(item);
                            if (item.status != null) {
                                $.each(item.status, (k, v) => {
                                    if (k == 'noChoose' || k == 'logout') {
                                        alertMsg += "再保人代號-" + item.rinComId + ":" + v + "，不可選擇" + "\n";
                                    }

                                    if (k == 'other') {
                                        confirmMsg += "請留意,再保人代號-" + item.rinComId + ":" + v + "\n"
                                    }

                                    if (k == 'notFinalPay') {
                                        if (item.rinComId === rinser.rinComId) {
                                            alertMsg += "再保人代號-" + item.rinComId + ":" + v + "\n";
                                        }
                                    }
                                })
                            }
                        });
                    }
                    if (!checkIsNullSpace(alertMsg)) {
                        alert(alertMsg);
                        return;
                    }

                    if (!checkIsNullSpace(confirmMsg)) {
                        handleConfirm = confirm(confirmMsg);
                    }
                    if (handleConfirm) {
                        let fac_type2_value = $('#fac_type2').val();
                        let fac_type2_Str = "";
                        switch (fac_type2_value) {
                            case "1":
                                fac_type2_Str = "比例";
                                break;
                            case "2":
                                fac_type2_Str = "非比例";
                                break;
                        }

                        if (job == 'C') {
                            let tableData = {
                                id: table.getDataCount() + 1,
                                facType: fac_type2_value,
                                facTypeStr: fac_type2_Str,
                                brokerId: $('input[name="broker_id1"]').val(),
                                brokerEname: $('#broker').text(),
                                rinComId: $('input[name="rin_com_id2"]').val(),
                                rinkerEname: $('#rinser_2').text(),
                                brkprem100: $('input[name="brkprem100"]').val(),
                                shareRate: $('input[name="brkshare_rate"]').val(),
                                brkcommRate: $('input[name="brkcomm_rate"]').val(),
                                brktaxRate: $('input[name="brktax_rate"]').val(),
                                brkexcessBgn: $('input[name="brkexcess_bgn"]').val(),
                                brkexcessLimit: $('input[name="brkexcess_limit"]').val(),
                                brkripremNt: $('input[name="brkRIprem_NT"]').val(),
                                brkricommNt: $('input[name="brkRIcomm_NT"]').val(),
                                brkripremOrg: $('input[name="brkRIprem_ORG"]').val(),
                                brkricommOrg: $('input[name="brkRIcomm_ORG"]').val(),
                                brkritaxNt: $('input[name="brkRItax_NT"]').val(),
                                brkritaxOrg: $('input[name="brkRItax_ORG"]').val(),
                            }
                            table.addData([tableData], false);
                        }

                        if (job == 'U') {
                            selectDataObj.facType = fac_type2_value;
                            selectDataObj.facTypeStr = fac_type2_Str;
                            selectDataObj.brokerId = $('input[name="broker_id1"]').val();
                            selectDataObj.brokerEname = $('#broker').text();
                            selectDataObj.rinComId = $('input[name="rin_com_id2"]').val();
                            selectDataObj.rinkerEname = $('#rinser_2').text();
                            selectDataObj.brkprem100 = $('input[name="brkprem100"]').val();
                            selectDataObj.shareRate = $('input[name="brkshare_rate"]').val();
                            selectDataObj.brkcommRate = $('input[name="brkcomm_rate"]').val();
                            selectDataObj.brktaxRate = $('input[name="brktax_rate"]').val();
                            selectDataObj.brkexcessBgn = $('input[name="brkexcess_bgn"]').val();
                            selectDataObj.brkexcessLimit = $('input[name="brkexcess_limit"]').val();
                            selectDataObj.brkripremNt = $('input[name="brkRIprem_NT"]').val();
                            selectDataObj.brkricommNt = $('input[name="brkRIcomm_NT"]').val();
                            selectDataObj.brkripremOrg = $('input[name="brkRIprem_ORG"]').val();
                            selectDataObj.brkricommOrg = $('input[name="brkRIcomm_ORG"]').val();
                            selectDataObj.brkritaxNt = $('input[name="brkRItax_NT"]').val();
                            selectDataObj.brkritaxOrg = $('input[name="brkRItax_ORG"]').val();
                            table.updateRow(selectDataObj.id);
                        }

                        revertlsv9Btn();
                        clearlsv9EditArea();

                    }
                }, (error) => {
                    console.log(error);
                    alert("檢核再保人發生錯誤,請聯絡系統管理員");
                })
        }

    }
}

function revertlsv9Btn() {
    let table = Tabulator.prototype.findTable('#brokerDetail')[0];
    $('#lsv9confirmBtn').hide();
    $('#lsv9cancelBtn').hide();
    $('#lsv9Save').show();
    $('#lsv9Edit').show();
    $('#lsv9Delete').show();
    $('input[name="lsv9job"]').val("");
    $('input[name="lsv9DataId"]').val("");
    $('#fac_type2').val("");
    $('#lsv9Edit').prop('disabled', true);
    $('#lsv9Delete').prop('disabled', true);
    editAreaReadOnly('brokerEditArea');
    table.tableMode = null;
    table.rowSelectionChangeCheck = false;
    table.deselectRow();
    $('#brokerDetail :button').prop("disabled", false);

}

function clearlsv9EditArea() {
    $('#fac_type2').val("");
    $('input[name="broker_id1"]').val("");
    $('input[name="rin_com_id2"]').val("");
    $('#broker_id1').text("");
    $('#rin_com_id2').text("");
    $('#broker').text("");
    $('#rinser_2').text("");
    $('input[name="brkprem100"]').val("");
    $('input[name="brkshare_rate"]').val("");
    $('input[name="brkcomm_rate"]').val("");
    $('input[name="brktax_rate"]').val("");
    $('input[name="brkexcess_bgn"]').val("");
    $('input[name="brkexcess_limit"]').val("");
    $('input[name="brkRIprem_NT"]').val("");
    $('input[name="brkRIcomm_NT"]').val("");
    $('input[name="brkRItax_NT"]').val("");
    $('input[name="brkRIprem_ORG"]').val("");
    $('input[name="brkRIcomm_ORG"]').val("");
    $('input[name="brkRItax_ORG"]').val("");
}

function calBrokerRinData() {
    if ($('#fac_type2').val() == '1') {
        let rinPrem100 = floatDiv($('#rinPremSum').val().replaceAll(",", ""), $('#exchangeRate').val(), 4);
        let brkshareRate = checkIsNullSpace($('input[name="brkshare_rate"]').val()) ? 0 : $('input[name="brkshare_rate"]').val();
        let brkcommRate = checkIsNullSpace($('input[name="brkcomm_rate"]').val()) ? 0 : $('input[name="brkcomm_rate"]').val();
        let brktaxRate = checkIsNullSpace($('input[name="brktax_rate"]').val()) ? 0 : $('input[name="brktax_rate"]').val();
        $('input[name="brkprem100"]').val($('#rinPremSum').val().replaceAll(",", ""));
        $('input[name="brkexcess_bgn"]').val(0);
        $('input[name="brkexcess_limit"]').val(0);

        if ($('#billCurrency option:selected').text() == 'NTD') {
            let brkRIpremNT = floatMul(floatDiv(brkshareRate, 100, 4), rinPrem100, 0);
            $('input[name="brkRIprem_NT"]').val(brkRIpremNT);
            $('input[name="brkRIcomm_NT"]').val(floatMul(floatDiv(brkcommRate, 100, 4), brkRIpremNT, 0));
            $('input[name="brkRItax_NT"]').val(floatMul(floatDiv(brktaxRate, 100, 4), brkRIpremNT, 0));
            $('input[name="brkRIprem_ORG"]').val(0);
            $('input[name="brkRIcomm_ORG"]').val(0);
            $('input[name="brkRItax_ORG"]').val(0);
        } else {
            $('input[name="brkRIprem_NT"]').val(0);
            $('input[name="brkRIcomm_NT"]').val(0);
            $('input[name="brkRItax_NT"]').val(0);
            let brkRIpremORG = floatMul(floatDiv(brkshareRate, 100, 4), rinPrem100, 2);
            $('input[name="brkRIprem_ORG"]').val(brkRIpremORG);
            $('input[name="brkRIcomm_ORG"]').val(floatMul(floatDiv(brkcommRate, 100, 4), brkRIpremORG, 2));
            $('input[name="brkRItax_ORG"]').val(floatMul(floatDiv(brktaxRate, 100, 4), brkRIpremORG, 2));
        }
    }
}




function genlsvPolicyTables(value, modelId) {

    ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/checkPolEndorseByOtherCessionUse", true, false, value,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("檢核是否保批單被其他合約使用時發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            let handleComfirm = true;
            let chkMsg = "";
            //console.log(res);
            if (res.data != null && res.data.length > 0) {
                res.data.forEach((item) => {
                    chkMsg += "保單號碼:" + item.policyNo + "," +
                        "批單號碼:" + item.endorseNo + "," +
                        "已被合約號:" + item.cessionNo + "使用\n";
                })
            }
            if (!checkIsNullSpace(chkMsg)) {
                handleComfirm = confirm(chkMsg);
            }
            if (handleComfirm) {
                ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/queryPolicyDetail", true, false, value,
                    (res) => {
                        //console.log(res);

                        if (res == null || res.status != '000') {
                            if (!checkIsNullSpace(res.message)) {
                                alert(res.message);
                            } else {
                                alert("查詢臨分保單明細資料失敗,請聯絡系統管理員");
                            }
                            return;
                        }
                        let lsvPolicy1Promise = loadData("lsvPolicy1", res.data.lsvPolicy1, { type: "height", value: '250px' });
                        let lsvPolicy2Promise = loadData("lsvPolicy2", res.data.lsvPolicy2, { type: "height", value: '250px' });
                        let lsvPolicy3Promise = loadData("lsvPolicy3", res.data.lsvPolicy3, { type: "height", value: '250px' });
                        let lsvPolicy4Promise = loadData("lsvPolicy4", res.data.lsvPolicy4, { type: "height", value: '250px' });
                        let lsvPolicy6Promise = loadData("lsvPolicy6", res.data.lsvPolicy6, { type: "dataCount", value: 10 });
                        let lsvPolicy7Promise = loadData("lsvPolicy7", res.data.lsvPolicy7, { type: "dataCount", value: 10 });
                        Promise.all([lsvPolicy1Promise, lsvPolicy2Promise, lsvPolicy3Promise, lsvPolicy4Promise, lsvPolicy6Promise, lsvPolicy7Promise])
                            .then(() => {
                                //alert("資料查詢成功");
                                genPolicyInfoData(res.data);
                                $(`#${modelId}`).modal('hide');
                            });


                    }, (error) => {
                        console.log(error);
                        alert("查詢臨分保單明細資料失敗,請聯絡系統管理員");
                    })
            }

        }, (error) => {
            console.log(error);
            alert("檢核是否保批單被其他合約使用時發生錯誤,請聯絡系統管理員");
        })

}

function genPolicyInfoData(data) {
    let policyNoStr = "";
    let lsvPolicy2FirstData;
    let lsvPolicy3FirstData;
    let amtSum = 0;
    let rinPremSum = 0;
    let policyArr = [];
    rinPolEndorArr = [];

    if (data.lsvPolicy1 != null && data.lsvPolicy1.length > 0) {
        data.lsvPolicy1.forEach((item) => {
            policyArr.push(item.policyNo)
        });

        let result = policyArr.filter((item, index, arr) => {
            return arr.indexOf(item) === index;
        })

        result.forEach((item) => {
            policyNoStr += checkIsNullSpace(policyNoStr) ? item : "," + item;
        })

        lsvPolicy1First = data.lsvPolicy1[0].policyNo;
        if (data.lsvPolicy2 != null && data.lsvPolicy2.length > 0) {
            let lsvPolicy2First = data.lsvPolicy2.filter((item) => {
                return item.policyNo === lsvPolicy1First;
            });

            if (lsvPolicy2First != null && lsvPolicy2First.length >= 1) {
                lsvPolicy2FirstData = lsvPolicy2First[0];
            }

            data.lsvPolicy2.forEach((item) => {
                let facPolicyObj = {
                    "policyNo": item.policyNo,
                    "endorseNo": item.endorseNo,
                    "addrNo": item.addrNo,
                };
                rinPolEndorArr.push(facPolicyObj);
            })

        }
        if (data.lsvPolicy3 != null && data.lsvPolicy3.length > 0) {
            let lsvPolicy3First = data.lsvPolicy3.filter((item) => {
                return item.policyNo === lsvPolicy1First;
            });
            if (lsvPolicy3First != null && lsvPolicy3First.length >= 1) {
                lsvPolicy3FirstData = lsvPolicy3First[0];
            }
        }



        $('#policyInfo_policy_noList').val(policyNoStr);
        $('#policyInfo_mkovse').val(data.lsvPolicy1[0].mkovse);
        $('#policyInfo_cinsurant').val(data.lsvPolicy1[0].cinsurant);
        $('#policyInfo_prop_addr').val(checkIsNullSpace(lsvPolicy2FirstData) ? "" : lsvPolicy2FirstData.propAddr);
        $('#policyInfo_useprop_name').val(checkIsNullSpace(lsvPolicy2FirstData) ? "" : lsvPolicy2FirstData.usepropName);
        $('#policyInfo_const_class').val(checkIsNullSpace(lsvPolicy2FirstData) ? "" : lsvPolicy2FirstData.constClass);
        $('#policyInfo_addition_ename').val(checkIsNullSpace(lsvPolicy2FirstData) ? "" : lsvPolicy3FirstData.additionEname);

    }

    $('#modifyUser').val(localStorage.getItem("account"));
    $('#modifyTime').val(dateFormat(new Date()));

    if (data.lsvPolicy6 != null && data.lsvPolicy6.length > 0) {
        data.lsvPolicy6.forEach((item) => {
            amtSum += Number.parseInt(item.amt)
        });
    }


    if (data.lsvPolicy7 != null && data.lsvPolicy7.length > 0) {
        data.lsvPolicy7.forEach((item) => {
            rinPremSum += Number.parseInt(item.facPrem)
        });
    }

    $('#amtSum').val(convertCurrency(amtSum));
    $('#tsiAmtSum').text(convertCurrency(amtSum));
    $('#rinPremSum').val(convertCurrency(rinPremSum));
    $('#premSum').text(convertCurrency(rinPremSum));
    lsvPolicy10Data = $.extend(true, [], data.lsvPolicy10);
}


function genlsvPolicy10Data() {
    let lsvPolicy10DataCopy = $.extend(true, [], lsvPolicy10Data);

    let rinRate = $('#sumRinRate').text();
    lsvPolicy10DataCopy.forEach((item) => {

        if (exceptInsKindList.indexOf('1') >= 0) {
            item.amt = 0;
            item.prem = 0;
        } else {
            item.amt = floatMul(floatDiv(rinRate, 100, 4), item.amt, 0);
            item.prem = floatMul(floatDiv(rinRate, 100, 4), item.prem, 0);
        }

        if (exceptInsKindList.indexOf('2') >= 0) {
            item.amtEar = 0;
            item.premEar = 0;
        } else {
            item.amtEar = floatMul(floatDiv(rinRate, 100, 4), item.amtEar, 0);
            item.premEar = floatMul(floatDiv(rinRate, 100, 4), item.premEar, 0);
        }

        if (exceptInsKindList.indexOf('3') >= 0) {
            item.amtTyp = 0;
            item.premTyp = 0;
        } else {
            item.amtTyp = floatMul(floatDiv(rinRate, 100, 4), item.amtTyp, 0);
            item.premTyp = floatMul(floatDiv(rinRate, 100, 4), item.premTyp, 0);
        }

    });

    loadData("lsvPolicy10", lsvPolicy10DataCopy, { type: "height", value: "250PX" });
}

/**
 * 明細查詢/分保計算
 * @param showAlert
 * @returns
 */
function genlsvPolicy10Data1() {
    let errorMsg = "";
    // 檢核"臨分型態"
    if (checkIsNullSpace($('#facType').val().trim())) {
        errorMsg += "臨分型態不可為空白，請選擇一般臨分或超賠臨分\n";
    }

    // lsvPolicy7 --> 險種保額保費頁籤-險種臨分表格
    let lsvPolicy7Table = Tabulator.prototype.findTable(`#lsvPolicy7`)[0];
    if (lsvPolicy7Table.getData() == null || lsvPolicy7Table.getData().length == 0) {
        errorMsg += "險種保額保費頁籤險種臨分表格無資料\n";
    }

    // rinserDetail --> 再保人資料頁籤-再保人表格
    let rinserDetailTable = Tabulator.prototype.findTable(`#rinserDetail`)[0];
    if (rinserDetailTable.getData() == null || rinserDetailTable.getData().length == 0) {
        errorMsg += "再保人表格無資料\n";
    }

    // 生效起迄日
    let treatyDBgn = $('#txtTreatyDBgn').val();
    let treatyDEnd = $('#txtTreatyDEnd').val();

    if (checkIsNullSpace(treatyDBgn) || checkIsNullSpace(treatyDEnd)) {
        errorMsg += "請輸入生效起日及迄日\n";
    }

    if (!checkIsNullSpace(errorMsg)) {
		alert(errorMsg);
        return "wrong";
    } else {
        let param = genRinDataObj();

        ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/calRinPremAndAmt", true, false, param,
            (res) => {
                if (res == null || res.status != "000") {
                    if (!checkIsNullSpace(res.message)) {          
                    	alert(res.message);                   	
                    } else {
                    	alert("明細查詢/分保計算發生錯誤,請聯絡系統管理員");                   	
                    }
					return "wrong";
                }else{
	
                	loadData("lsvPolicy10", res.data, { type: "height", value: "250PX" })
                    	.then(() => {
							facCalcSum();
	
                			alert("明細查詢/分保計算成功");
                    	});
					return "success";
				}

            }, (error) => {
                console.log(error);
                alert("明細查詢/分保計算發生錯誤,請聯絡系統管理員");
				return "wrong";
            })
		
    }

}


function updateSumAmt() {
    let table = Tabulator.prototype.findTable(`#lsvPolicy7`)[0];
    let premCoinsSum = 0;
    table.getRows().forEach((item) => {
        premCoinsSum += Number.parseInt(item.getData().facPrem);
    });
    $('#rinPremSum').val(convertCurrency(premCoinsSum));
    $('#premSum').text(convertCurrency(premCoinsSum));
}

function updateRinserData() {
    let table = Tabulator.prototype.findTable(`#rinserDetail`)[0];
    let tableData = table.getData();
    let amtSum = $('#amtSum').val().replaceAll(",", "");;
    let rinPremSum = $('#rinPremSum').val().replaceAll(",", "");;
    if (tableData != null && tableData.length > 0) {
        tableData.forEach((item) => {
            let rate = item.shareRate;
            item.cedeAmt = floatMul(floatDiv(rate, 100, 4), amtSum, 0);
            item.cedePrem = floatMul(floatDiv(rate, 100, 4), rinPremSum, 0)
        });
        table.updateData(tableData);
    }

}

function createRinData(param) {
    ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/createRinData", true, false, param,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("新增臨分資料發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            //console.log(res.data);
            $('input[id=txtSlipNo]').val(res.data.slipNo);
            $('input[id=txtCessionNo]').val(res.data.cessionNo);
            $('#cmdInsert').hide();
            alert("新增臨分資料成功");

        }, (error) => {
            console.log(error);
            alert("新增臨分資料發生錯誤,請聯絡系統管理員");
        })
}


function insertData() {
    let saveConfirm = confirm("是否確定儲存?");
    if (saveConfirm) {
        //todo:檢核
        let chkMsg = "";
        chkMsg += saveDataVaild();
        chkMsg += brokerListVaild();

        if (!checkIsNullSpace(chkMsg)) {
            alert(chkMsg);
            return;
        } else {
			//觸發"明細查詢/分保計算"按鈕功能，執行失敗則不新增資料
			if(genlsvPolicy10Data1()==='wrong'){
				return;
			}

            let checkConfirm = true;
            if (receiveParam.gsProcessMode == '3') {
                checkPolicyNoSeqExist().then((res) => {
                    if (!checkIsNullSpace(res)) {
                        checkConfirm = confirm(res);
                    }

                    if (checkConfirm) {
                        let param = genRinDataObj();
                        createRinData(param);
                    }
                });
            } else {
                if (checkConfirm) {
                    let param = genRinDataObj();
                    createRinData(param);
                }
            }
        }
    }
}


function saveDataVaild() {
    let errorMsg = "";
    if (checkIsNullSpace($('#txtPayDate').val().trim())) {
        errorMsg += "立帳日不可為空白，請先輸入立帳日\n";
    }

    if (checkIsNullSpace($('#facType').val().trim())) {
        errorMsg += "臨分型態不可為空白，請選擇一般臨分或超賠臨分\n";
    }

    if (checkIsNullSpace($('#polEnrNo').val().trim())) {
        errorMsg += "請輸入保批單作業序號，保單為 00，批單請依序自編\n";
    }

    if (checkIsNullSpace($('#txtSlipYear').val().trim())) {
        errorMsg += "請輸入知會/更正號給號年度\n";
    } else if ($('#txtSlipYear').val().trim() > 9999 || !$('#txtSlipYear').val().trim().match(/^\+?[1-9][0-9]*$/)) {
        errorMsg += "知會/更正號給號年度須為西元年\n";
    }

    if (checkIsNullSpace($('#txtCessionYear').val().trim())) {
        errorMsg += "請輸入合約號給號年度\n";
    } else if ($('#txtCessionYear').val().trim() > 9999 || !$('#txtCessionYear').val().trim().match(/^\+?[1-9][0-9]*$/)) {
        errorMsg += "合約號給號年度須為西元年\n";
    }


    if (checkIsNullSpace($('#billCurrency').val().trim())) {
        errorMsg += "請輸入帳單幣別\n";
    }

    if (checkIsNullSpace($('#exchangeRate').val().trim()) || $('#exchangeRate').val().trim() == 0) {
        errorMsg += "請輸入幣別兌換率且不可為0\n";
    }
    
  //檢核再保人資料頁籤-RI再保費、RI佣金、RI營業稅，與所選幣別是否一致
    let currency = $('#billCurrency option:selected').text();							//分保計算及說明頁籤-帳單幣別
    let brokerTableData = Tabulator.prototype.findTable('#brokerDetail')[0].getData();	//再保人資料頁籤-brokerDetail表格的所有資料
    let brokerTableDataLen = brokerTableData.length;									//再保人資料頁籤-brokerDetail表格的資料筆數
    let hasWrongCurrency = false;														//用來判別是否有幣別不一致、需跳檢核的boolean                    
    
    for(let i = 0; i < brokerTableDataLen; i++){
    	if(currency == 'NTD'){   		
    		//RI再保費(台幣)
    		if(Number(brokerTableData[i].brkripremNt) < 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI佣金(台幣)
    		if(Number(brokerTableData[i].brkricommNt) < 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI營業稅(台幣)
    		if(Number(brokerTableData[i].brkritaxNt) < 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI再保費(外幣)
    		if(Number(brokerTableData[i].brkripremOrg) != 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI佣金(外幣)
    		if(Number(brokerTableData[i].brkricommOrg) != 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI營業稅(外幣)
    		if(Number(brokerTableData[i].brkritaxOrg) != 0 ){
    			hasWrongCurrency = true;
    		}
    	}else{
    		//RI再保費(台幣)
    		if(Number(brokerTableData[i].brkripremNt) != 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI佣金(台幣)
    		if(Number(brokerTableData[i].brkricommNt) != 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI營業稅(台幣)
    		if(Number(brokerTableData[i].brkritaxNt) != 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI再保費(外幣)
    		if(Number(brokerTableData[i].brkripremOrg) < 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI佣金(外幣)
    		if(Number(brokerTableData[i].brkricommOrg) < 0 ){
    			hasWrongCurrency = true;
    		}
    		//RI營業稅(外幣)
    		if(Number(brokerTableData[i].brkritaxOrg) < 0 ){
    			hasWrongCurrency = true;
    		}
    	}
    	//若在迭代過程中已發現檢核未通過的，則提前結束迭代並加入檢核訊息
    	if(hasWrongCurrency){
    		errorMsg += "所選幣別與資料不符\n"
    		break;
    	}
    }

    return errorMsg;
}

function editRinData(param) {
    ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/editRinData", true, false, param,
        (res) => {
            if (res == null || res.status != "000") {
                if (!checkIsNullSpace(res.message)) {
                    alert(res.message);
                } else {
                    alert("修改臨分資料發生錯誤,請聯絡系統管理員");
                }
                return;
            }
            //console.log(res.data);
            //$('input[id=txtSlipNo]').val(res.data.slipNo);
            //$('input[id=txtCessionNo]').val(res.data.cessionNo);
            $('#cmdUpdate').hide();
            alert("修改臨分資料成功");

        }, (error) => {
            console.log(error);
            alert("修改臨分資料發生錯誤,請聯絡系統管理員");
        })
}


function updateData() {
	let saveConfirm = false;
	if(receiveParam.conversionStatus == "T"){
		saveConfirm = confirm("已列印帳單並已轉入會計系統，是否要更正?");
	}else{
		saveConfirm = confirm("是否確定儲存?");
	}
//    let saveConfirm = confirm("是否確定儲存?");
    if (!saveConfirm) {
        return;
    } else {

        //todo:檢核
        let chkMsg = "";
        chkMsg += saveDataVaild();
        chkMsg += brokerListVaild();
        if (!checkIsNullSpace(chkMsg)) {
            alert(chkMsg);
            return;
        } else {
	
            let checkConfirm = true;
            let ori_policyNoSeq = rinDataObj.mainData.policyNoSeq;
            //console.log(ori_policyNoSeq);
            let new_policyNoSeq = $('input[id=polEnrNo]').val();
            //console.log(new_policyNoSeq);
            if (receiveParam.gsProcessMode == '2' && ori_policyNoSeq != new_policyNoSeq) {
                checkPolicyNoSeqExist().then((res) => {
                    if (!checkIsNullSpace(res)) {
                        checkConfirm = confirm(res);
                    }
                    if (checkConfirm) {
						//觸發"明細查詢/分保計算"按鈕功能，執行失敗則不更新資料
						if(genlsvPolicy10Data1()==='wrong'){
							return;
						}
                        let param = genRinDataObj();
                        editRinData(param);
                    }
                });
            } else {
                if (checkConfirm) {
					//觸發"明細查詢/分保計算"按鈕功能，執行失敗則不更新資料
					if(genlsvPolicy10Data1()==='wrong'){
						return;
					};	
                    let param = genRinDataObj();
                    editRinData(param);
                }
            }
        }

    }
}


function delData() {
    let delConfirm = confirm("是否確定刪除?");
    if (!delConfirm) {
        return;
    } else {
        //todo:檢核
        if (receiveParam.gsProcessMode != '2') {
            alert("操作模式錯誤,不可刪除");
            return;
        }

        if (checkIsNullSpace($('input[id=txtSlipNo]').val().trim())) {
            alert("請輸入知會/更正號");
            return;
        }

        let param = {}
        param.slipNo = $('input[id=txtSlipNo]').val().trim();
        ajaxRequestIsAsyncDynimicBytoken("../../rin1301api/delRinData", true, false, param,
            (res) => {
                if (res == null || res.status != "000") {
                    if (!checkIsNullSpace(res.message)) {
                        alert(res.message);
                    } else {
                        alert("刪除臨分資料發生錯誤,請聯絡系統管理員");
                    }
                    return;
                }
                //console.log(res.data);
                //$('input[id=txtSlipNo]').val(res.data.slipNo);
                //$('input[id=txtCessionNo]').val(res.data.cessionNo);
                $('#cmdUpdate').hide();
                $('#cmdDelete').hide();
                alert("刪除臨分資料成功");

            }, (error) => {
                console.log(error);
                alert("刪除臨分資料發生錯誤,請聯絡系統管理員");
            })
    }
}

//todo taxValid layer2
function brokerListVaild() {
	
    let errorMsg = "";
    let brokerData = Tabulator.prototype.findTable('#brokerDetail')[0].getData();
    let rinserData = Tabulator.prototype.findTable('#rinserDetail')[0].getData();

    let brokerInRinserDataArr = rinserData.filter((data) => {
        return (data.statusCode == 'notFinalPay' || data.rinComId.startsWith('B')) && data.shareStatus != '3';
    });


    if (brokerInRinserDataArr != null && brokerInRinserDataArr.length != 0) {
    	
        brokerInRinserDataArr.forEach((rinser) => {
        	
        	// 分出比率
            let rinserShareRate = rinser.shareRate;
            // 再保費台幣
            let rinserNTRinPrem = rinser.cedePrem;
            
            let rinserNTRinComm = "";
            if(checkIsNullSpace(rinser.orgcomm)){
            	rinserNTRinComm = floatMul(floatDiv(rinser.commRate, 100, 4), rinser.cedePrem, 0);
            }else{
            	// 佣金出帳金額
            	rinserNTRinComm = rinser.orgcomm;
            }
            
            let rinserNTRinTax = "";
            if(checkIsNullSpace(rinser.orgtax)){
            	rinserNTRinTax = floatMul(floatDiv(rinser.taxRate, 100, 4), rinser.cedePrem, 0);
            }else{
            	// 營業稅出帳金額
            	rinserNTRinTax = rinser.orgtax;
            }
            
            let rinserOrgRinPrem = rinser.orgprem
            let rinserOrgRinComm = rinser.orgcomm
            let rinserOrgRinTax = rinser.orgtax



            let brokerFacType1List = brokerData.filter((broker) => {
                return broker.brokerId == rinser.rinComId && broker.facType == '1'
            });

            let brokerFacType2List = brokerData.filter((broker) => {
                return broker.brokerId == rinser.rinComId && broker.facType == '2';
            })

            if ((brokerFacType1List == null || brokerFacType1List.length == 0) &&
                (brokerFacType2List == null || brokerFacType2List.length == 0)) {
                errorMsg += "再保人代號:" + rinser.rinComId + ",非為最終支付公司, 必須維護第二層再保人資料\n";
            } else {
                if (brokerFacType2List == null || brokerFacType2List.length == 0) {
                    let subShareRate = 0;
                    brokerFacType1List.forEach((type1Broker) => {
                        subShareRate = floatAdd(subShareRate, type1Broker.shareRate, 4);
                    });
                    console.log("rinserShareRate:" + rinserShareRate);
                    console.log("subShareRate:" + subShareRate);
                    if (rinserShareRate != subShareRate) {
                        errorMsg += "經紀人:" + rinser.rinComId + "各再保人分出比例加總不等於該經紀人分出比例\n"
                    }
                }
            	
                if (brokerFacType2List != null && brokerFacType2List.length > 0) {
                	
                    let subRinPremOrg = '0';
                    let subRinCommOrg = '0';
                    let subRinPremNt = '0';
                    let subRinCommNt = '0';
                    let subRinTaxNt = '0';
                    let subRinTaxOrg = '0';

                    brokerFacType2List.forEach((type2Broker) => {
                    	// 用 JAVA 計算(避免誤差)
                    	subRinPremOrg = getArithmetic(subRinPremOrg, type2Broker.brkripremOrg.toString(), "+");
                    	subRinCommOrg = getArithmetic(subRinCommOrg, type2Broker.brkricommOrg.toString(), "+");
                    	subRinTaxOrg = getArithmetic(subRinTaxOrg, type2Broker.brkritaxOrg.toString(), "+");
                    	subRinPremNt = getArithmetic(subRinPremNt, type2Broker.brkripremNt.toString(), "+");
                    	subRinCommNt = getArithmetic(subRinCommNt, type2Broker.brkricommNt.toString(), "+");
                    	subRinTaxNt = getArithmetic(subRinTaxNt, type2Broker.brkritaxNt.toString(), "+");
                    })

                    if (brokerFacType1List != null && brokerFacType1List.length > 0) {
                        brokerFacType1List.forEach((type1Broker) => {
                        	// 需改用 JAVA 計算(避免誤差)
                        	subRinPremOrg = getArithmetic(subRinPremOrg, type2Broker.brkripremOrg.toString(), "+");
                        	subRinCommOrg = getArithmetic(subRinCommOrg, type2Broker.brkricommOrg.toString(), "+");
                        	subRinTaxOrg = getArithmetic(subRinTaxOrg, type2Broker.brkritaxOrg.toString(), "+");
                        	subRinPremNt = getArithmetic(subRinPremNt, type2Broker.brkripremNt.toString(), "+");
                        	subRinCommNt = getArithmetic(subRinCommNt, type2Broker.brkricommNt.toString(), "+");
                        	subRinTaxNt = getArithmetic(subRinTaxNt, type2Broker.brkritaxNt.toString(), "+");
                        })
                    }
                                        
                    // 台幣、外幣分開加總的檢核
                    if ($('#billCurrency option:selected').text() == 'NTD') {
                    	if (rinserNTRinPrem != subRinPremNt) {
                            errorMsg += "經紀人:" + rinser.rinComId + "各再保人RI再保費(台幣)加總不等於該經紀人再保費金額\n"
                        }
                    	if (rinserNTRinComm != subRinCommNt) {
                            errorMsg += "經紀人:" + rinser.rinComId + "各再保人RI佣金(台幣)加總不等於該經紀人佣金金額\n"
                        }
                    	if (rinserNTRinTax != subRinTaxNt) {
                            errorMsg += "經紀人:" + rinser.rinComId + "各再保人RI營業稅(台幣)加總不等於該經紀人營業稅金額\n"
                        }
                    }else{
                    	// 數字四捨五入到小數點後兩位，再比較 (因為案例中第一層資料的小數點只到後兩位)
                    	if (rinserOrgRinPrem != roundToTwo(subRinPremOrg)) {
                            errorMsg += "經紀人:" + rinser.rinComId + "各再保人RI再保費(外幣)加總不等於該經紀人再保費出帳金額\n"
                        }
                    	if (rinserOrgRinComm != roundToTwo(subRinCommOrg)) {
                            errorMsg += "經紀人:" + rinser.rinComId + "各再保人RI佣金(外幣)加總不等於該經紀人佣金出帳金額\n"
                        }
                    	if (rinserOrgRinTax != roundToTwo(subRinTaxOrg)) {
                            errorMsg += "經紀人:" + rinser.rinComId + "各再保人RI營業稅(外幣)加總不等於該經紀人營業稅出帳金額\n"
                        }
                    }
                }

            }
        });
    }
    return errorMsg;

}


/**
 * 整理頁面資料物件
 * @returns
 */
function genRinDataObj() {
    let param = {}
    if (!checkIsNullSpace(receiveParam.logSeq) && receiveParam.logSeq.startsWith('LOG-')) {
        param.logSeq = receiveParam.logSeq.replace('LOG-', '');
    }
    // 知會號、更正號
    param.slipNo = $('input[id=txtSlipNo]').val().trim();
    // 合約號
    param.cessionNo = $('input[id=txtCessionNo]').val().trim();
    // 臨分更正:2, 臨分批單:3, 臨分續保:4
    param.handleMode = receiveParam.gsProcessMode;
    // 知會號、更正號 給號年度
    param.slipNoGenYear = $('input[id=txtSlipYear]').val().trim();
    // 合約號 給號年度
    param.treatyYear = $('input[id=txtCessionYear]').val().trim();
    // 知會號:1, 更正號:2
    param.slipType = $('input[name=genRecordNo]').val().trim();
    
    param.treatyDbgn = $('input[id=txtTreatyDBgn]').val().trim();
    param.treatyDend = $('input[id=txtTreatyDEnd]').val().trim();
    param.preSlipNo = receiveParam.preSlipNo;
    param.preCessionNo = receiveParam.preCessionNo;
    param.treatyDate = $('input[id=txtPayDate]').val().trim();
    param.cessionName = $('input[id=txtCessionName]').val().trim();
    param.days = $('input[id=txtDays]').val().trim();
    if (riskCodeArr != null && riskCodeArr.length > 0) {
        param.riskType = '1'
    } else {
        param.riskType = '2'
    }
    param.deductDesc = $('input[id=deductDesc]').val().trim();
    param.policyNo = $('#policyInfo_policy_noList').val().trim();
    param.insurant = $('input[id=policyInfo_cinsurant]').val().trim();
    param.address = $('input[id=policyInfo_prop_addr]').val().trim();
    param.useProp = $('input[id=policyInfo_useprop_name]').val().trim();
    param.construct = $('input[id=policyInfo_const_class]').val().trim();
    param.coverage = $('input[id=policyInfo_addition_ename]').val().trim();
    param.facType = $('#facType').val().trim();
    param.excessBgn = $('input[id=excessBgn]').val().trim();
    param.excessLimit = $('input[id=excessLimit]').val().trim();
	param.amtSum = Number($('#amtSum').val().trim().replace(/[,]+/g, ""));
	
    if (checkIsNullSpace($('#billCurrency').val())) {
        param.currency = '';
    } else {
        param.currency = $('#billCurrency').find("option:selected").text();
    }

    if (checkIsNullSpace($('input[name=exchangeRate]').val())) {
        param.currencyexchangerate = 1.0000;
    } else {
        param.currencyexchangerate = $('input[name=exchangeRate]').val().trim();
    }


    param.policyNoSeq = $('input[id=polEnrNo]').val().trim();
    param.lastupdateaccount = localStorage.getItem("account").trim();
    param.mkovse = $('input[id=policyInfo_mkovse]').val().trim();
    param.sameriskPolEndorObj = chooseSameRiskAndPolNoObj,
        param.facShareVoList = Tabulator.prototype.findTable('#lsvPolicy10')[0].getData();
    let exceptInsKindByRinserArr = [];
    let rinserData = Tabulator.prototype.findTable('#rinserDetail')[0].getData();

    if (rinserData != null && rinserData.length > 0) {
        rinserData.forEach((item) => {
            if (checkIsNullSpace(item.orgcomm)) {
                item.orgcomm = 0;
            }

            if (checkIsNullSpace(item.orgtax)) {
                item.orgtax = 0;
            }

            if (checkIsNullSpace(item.orgprem)) {
                item.orgprem = 0;
            }
        })


        if (exceptInsKindList != null && exceptInsKindList.length > 0) {
            rinserData.forEach((item) => {
                exceptInsKindList.forEach((ins) => {
                    let exceptInsKindObj = {};
                    exceptInsKindObj.rinComId = item.rinComId;
                    exceptInsKindObj.content = ins
                    exceptInsKindByRinserArr.push(exceptInsKindObj);
                })
            });
        }

    }

    param.facExcludeVoList = exceptInsKindByRinserArr;

    if ($('input[id=share_status]').val() == '0') {
        param.treatyset = 'Y'
    } else {
        param.treatyset = 'N'
    }

    param.facBrokerVoList = Tabulator.prototype.findTable('#brokerDetail')[0].getData();
    param.facRinserVoList = Tabulator.prototype.findTable('#rinserDetail')[0].getData();
    param.facSlipRiskVoList = riskCodeArr;
    param.facRateVoList = Tabulator.prototype.findTable('#lsvPolicy7')[0].getData();
    param.facNpropVoList = Tabulator.prototype.findTable('#lsvPolicy6')[0].getData();
    let facPolicyArr = [];
    Tabulator.prototype.findTable('#lsvPolicy2')[0].getData().forEach((item) => {
        let facPolicy = {};
        facPolicy.policyNo = item.policyNo;
        facPolicy.endorseNo = item.endorseNo;
        facPolicy.addrNo = item.addrNo;
        facPolicyArr.push(facPolicy);
    })
    param.facPolicyVoList = facPolicyArr;
    // 會計轉檔狀況
    param.conversionStatus = receiveParam.conversionStatus;
    //console.log(param);
    return param;

}

function settleRinMainData() {
    $("input[name=genRecordNo][value=" + rinDataObj.mainData.slipType + "]").prop('checked', true);
    $('input[id=txtSlipYear]').val(rinDataObj.mainData.slipNo.substring(0, 4));
    $('input[id=txtCessionYear]').val(rinDataObj.mainData.treatyYear);
    $('input[id=txtCessionName]').val(rinDataObj.mainData.cessionName);
    $('input[id=txtTreatyDBgn]').val(rinDataObj.mainData.treatyDbgn);
    $('input[id=txtTreatyDEnd]').val(rinDataObj.mainData.treatyDend);
    $('input[id=txtDays]').val(rinDataObj.mainData.days);
    $('input[id=txtPayDate]').val(rinDataObj.mainData.treatyDate);
    $('input[id=polEnrNo]').val(rinDataObj.mainData.policyNoSeq);

    $('input[name="sameRistCode"]').each(function(index) {
        $(this).val(rinDataObj.sameRiskPolEndorseData['samerisk' + (index + 1)]);
    });

    $('#polEnrNoList :input[name="queryPolicy"]').each(function(i, pol) {
        $(this).val(rinDataObj.sameRiskPolEndorseData['policy' + (i + 1)]);
    });

    $('#polEnrNoList :input[name="queryEndorse"]').each(function(j, enr) {
        $(this).val(rinDataObj.sameRiskPolEndorseData['endorse' + (j + 1)]);
    })

    chooseSameRiskAndPolNoObj = rinDataObj.sameRiskPolEndorseData;
    riskCodeArr = rinDataObj.slipRiskDetail;
    $('#facType').val(rinDataObj.mainData.facType);
    if (rinDataObj.mainData.facType == '1') {
        $('#fac_type2').val('1');
    }
    $('input[id=excessBgn]').val(rinDataObj.mainData.excessBgn);
    $('input[id=excessLimit]').val(rinDataObj.mainData.excessLimit);
    $('#policyInfo_policy_noList').val(rinDataObj.mainData.policyNo)
    $('input[id=policyInfo_mkovse]').val(rinDataObj.mainData.mkovse)
    $('input[id=policyInfo_cinsurant]').val(rinDataObj.mainData.insurant);
    $('input[id=policyInfo_prop_addr]').val(rinDataObj.mainData.address)
    $('input[id=policyInfo_useprop_name]').val(rinDataObj.mainData.useProp);
    $('input[id=policyInfo_const_class]').val(rinDataObj.mainData.construct);
    $('input[id=policyInfo_addition_ename]').val(rinDataObj.mainData.coverage);
    $('input[id=deductDesc]').val(rinDataObj.mainData.deductDesc);
    $('input[id=modifyUser]').val(rinDataObj.mainData.lastupdateaccount);
    $('input[id=modifyTime]').val(rinDataObj.mainData.lastupdatedatetime);
    rinDataObj.mainData.excludeSet.forEach((data) => {
        $("input[name=exceptInsKind][value=" + data + "]").prop('checked', true);
    });

    let amtSum = 0;
    let rinPremSum = 0;

    if (rinDataObj.lsvPolicy6 != null && rinDataObj.lsvPolicy6.length > 0) {
        rinDataObj.lsvPolicy6.forEach((item) => {
            amtSum += Number.parseInt(item.amt)
        });
    }

    if (rinDataObj.lsvPolicy7 != null && rinDataObj.lsvPolicy7.length > 0) {
        rinDataObj.lsvPolicy7.forEach((item) => {
            rinPremSum += item.facPrem;
        });
    }

    $('#amtSum').val(convertCurrency(amtSum));
    $('#rinPremSum').val(convertCurrency(rinPremSum));
    $('#tsiAmtSum').text(convertCurrency(amtSum));
    $('#premSum').text(convertCurrency(rinPremSum));
    $('input[name=exchangeRate]').val(rinDataObj.mainData.currencyexchangerate);
    lsvPolicy10Data = $.extend(true, [], rinDataObj.lsvPolicy10);
}






function editAreaReadOnly(id) {
    $(`#${id} :input`).prop("disabled", true);
    $(`#${id} :input`).datepicker("option", "disabled", true);
}

function editAreaEditable(id) {
    if (id == 'brokerEditArea' && $('#fac_type2').val() == '1') {
        $(`#${id} :input`).prop("disabled", true);
        $(`#${id} :input`).datepicker("option", "disabled", true);
        $('#fac_type2').prop("disabled", false);
        $('#queryRinser2').prop("disabled", false);
        $('#queryBroker').prop("disabled", false);
        $('input[name=broker_id1').prop("disabled", false);
        $('input[name=rin_com_id2').prop("disabled", false);
        $('input[name=brkshare_rate').prop("disabled", false);
        $('input[name=brkcomm_rate').prop("disabled", false);
        $('input[name=brktax_rate').prop("disabled", false);
    } else {
        $(`#${id} :input`).prop("disabled", false);
        $(`#${id} :input`).datepicker("option", "disabled", false);
    }
}


function updateBrokerDetailTable() {
    let brokerTable = Tabulator.prototype.findTable('#brokerDetail')[0];
    let brokerDetail = brokerTable.getData();
    brokerDetail.forEach((item) => {
        if (item.facType == '1') {
            let rinPrem100 = floatDiv($('#rinPremSum').val().replaceAll(",", ""), $('#exchangeRate').val(), 4);
            let brkshareRate = checkIsNullSpace(item.shareRate) ? 0 : item.shareRate;
            let brkcommRate = checkIsNullSpace(item.brkcommRate) ? 0 : item.brkcommRate;
            let brktaxRate = checkIsNullSpace(item.brktaxRate) ? 0 : item.brktaxRate;
            item.brkprem100 = $('#rinPremSum').val().replaceAll(",", "");
            if ($('#billCurrency option:selected').text() == 'NTD') {
                item.brkripremNt = floatMul(floatDiv(brkshareRate, 100, 4), rinPrem100, 0);
                item.brkricommNt = floatMul(floatDiv(brkcommRate, 100, 4), rinPrem100, 0);
                item.brkritaxNt = floatMul(floatDiv(brktaxRate, 100, 4), rinPrem100, 0);
                item.brkripremOrg = 0;
                item.brkricommOrg = 0;
                item.brkritaxOrg = 0;
            } else {
                item.brkripremNt = 0
                item.brkricommNt = 0;
                item.brkritaxNt = 0;
                item.brkripremOrg = floatMul(floatDiv(brkshareRate, 100, 4), rinPrem100, 2);
                item.brkricommOrg = floatMul(floatDiv(brkcommRate, 100, 4), rinPrem100, 2);
                item.brkritaxOrg = floatMul(floatDiv(brktaxRate, 100, 4), rinPrem100, 2);
            }
        }
    })

    brokerTable.updateData(brokerDetail);
}


function settleBrokerFacType(value) {
    if (value == '1') {
        $('#fac_type2').val('1');
    } else {
        $('#fac_type2').val('');
    }
}

function takeExchangeRate() {
    $('#exchangeRate').val($('#billCurrency').val());
}


$('input[name=exceptInsKind]').change(function() {
    exceptInsKindList = [];
    $('input[name=exceptInsKind]:checked').each(function() {
        exceptInsKindList.push($(this).val());
    });
    //console.log(exceptInsKindList);

    if (exceptInsKindList != null && exceptInsKindList.length != 0) {
        var exceptInsKind = "";
        exceptInsKindList.forEach((item) => {
            // console.log(item);
            // console.log($(`label[for=exceptInsKind${item}]`).text());
            exceptInsKind += checkIsNullSpace(exceptInsKind) ? $(`label[for=exceptInsKind${item}]`).text() : "," + $(`label[for=exceptInsKind${item}]`).text();
        });
        alert("已選擇除外險種:" + exceptInsKind)
    }
});

$('#cmdQuery').on('click', () => {
    let passParam = {
        "gsProcessMode": receiveParam.gsProcessMode,
        "queryParam": receiveParam.queryParam
    }
    locationHrefKeepDataType2('Rin1301', '', JSON.stringify(passParam));
});


$('#billCurrency').on('change', () => {
    takeExchangeRate();
    updateBrokerDetailTable();
});

/**
 * 日期格式檢核
 * @returns
 */
$('#txtTreatyDBgn, #txtTreatyDEnd, #txtPayDate, [name="answer_date"], [name="paid_date"], [name="paid_date_expect"]').change(function(){
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
 * 第二層再保人資料整理
 * @returns
 */
$('[name="brkshare_rate"], [name="brkcomm_rate"], [name="brktax_rate"], [name="brkprem100"], [name="brkexcess_bgn"], [name="brkexcess_limit"], [name="brkRIprem_NT"], [name="brkRIcomm_NT"], [name="brkRItax_NT"], [name="brkRIprem_ORG"], [name="brkRIcomm_ORG"], [name="brkRItax_ORG"]').blur(function(){
	try {
		if(!checkIsNullSpace($(this).val())){
			$(this).val(removeComma($(this).val()).trim());
		}
	} catch (e) {
		alert(e);
	}
});

/**
 * 在 JavaScript 中使用自定義函式將數字四捨五入到小數點後兩位
 * @param num
 * @returns
 */
function roundToTwo(num) {
	return +(Math.round(num + "e+2")  + "e-2");
}

// 清空第一層再保人輸入框資料(避免資料殘留，造成連動功能無效的誤會)
function clearFirstData(){
	try {
		$('#pills-3').find('input[type=text]').val('');
		$('#rinser_1').text('');
		$("input[name=share_status]").prop("checked", false);
	} catch (e) {
		alert(e);
	}
}



//分保計算與尾差處理
function facCalcSum() {

	let rinserDetail = Tabulator.prototype.findTable('#rinserDetail')[0];	//第三頁籤上方的再保人表格
	
	let dblAmtDiff = 0;
	let dblPremDiff = 0;	
	//將再保人中，處理階段不是完成的分出保額與再保費加總
	rinserDetail.getData().filter((item)=>{
		if(item.shareStatus !== "2"){
			dblAmtDiff = dblAmtDiff + item.cedeAmt;		//分出保額
			dblPremDiff = dblPremDiff + item.cedePrem;	//再保費台幣
		}
	})	
	
	let lsvPolicy10 = Tabulator.prototype.findTable('#lsvPolicy10')[0];	//取得第四頁籤分保計算表格
	let lsvPolicy10Length = lsvPolicy10.getData().length;				//分保計算表格有多少筆資料

	//資料筆數大於1筆時，做尾差計算處理
	if (lsvPolicy10Length > 1) {
		let lsvPolicy10LastRowData = lsvPolicy10.getData()[lsvPolicy10Length - 1];//最後一筆資料的值
		let bottomSumData = lsvPolicy10.getCalcResults().bottom;//最下方保額、保費的加總

		let sumRinAmt = Number($('#sumRinAmt').text().replace(/[,]+/g, ""));//合計臨分保額(移除千分位符號)
		//最後一筆的分保額(尾差處理值) = 最後一筆的分出保額(原)+合計臨分保額-分出保額加總-處理階段不是完成的分出保額加總
		let amtTail = lsvPolicy10LastRowData.amt + sumRinAmt - (Number(bottomSumData.amt)) - dblAmtDiff;
//console.log("分出保額(原)="+lsvPolicy10LastRowData.amt)
//console.log("分出保額(後)="+amtTail)

		let sumRinPrem = Number($('#sumRinPrem').text().replace(/[,]+/g, ""));//合計臨分保費(移除千分位符號)
		//最後一筆的分保費(尾差處理值) = 最後一筆的分出保費(原)+合計臨分保費-分出保費加總-颱洪分保費加總-地震分保費加總-處理階段不是完成的再保費加總
		let premTail = lsvPolicy10LastRowData.prem + sumRinPrem - bottomSumData.prem - bottomSumData.premTyp - bottomSumData.premEar - dblPremDiff;
		
		//將處理好尾差計算的值填回表格最後一筆資料中，並更新下方加總
		lsvPolicy10.getRows()[lsvPolicy10Length - 1].update({ amt: amtTail, prem: premTail }).then(()=>{
			lsvPolicy10.recalc();
		});
//		console.log("分出保費(原)="+lsvPolicy10LastRowData.prem)
//		console.log("分出保費(後)="+premTail)

	}
}

function buildCurrency_1(){
	// 帳單幣別
	$('#billCurrency_1').empty();
	let myOpts = document.getElementById('billCurrency').options;
	for (let ele of myOpts) {
		let option = document.createElement('option');
		option.value = ele.value;
	    option.text = ele.text;
	    document.getElementById("billCurrency_1").appendChild(option);
	}
	$('#billCurrency_1').val($('#billCurrency').val());
	// 兌換率
	$('#exchangeRate_1').val($('#exchangeRate').val());
//	if ($('#billCurrency option:selected').text() == 'NTD') {
//		$('#NT_group').show();
//		$('#ORG_group').hide();
//	}else{
//		$('#NT_group').hide();
//		$('#ORG_group').show();
//	}
}

$('#billCurrency, #exchangeRate').change(function(){
	$('#billCurrency_1').val($('#billCurrency').val());
	$('#exchangeRate_1').val($('#exchangeRate').val());
	// TODO 檢查台幣or外幣，欄位切換清空隱藏 (會出問題，先註解)
//	if ($('#billCurrency option:selected').text() == 'NTD') {
//		$('#NT_group').show();
//		$('#ORG_group').hide();
//		$('#ORG_group').find('input').val('');
//	}else{
//		$('#NT_group').hide();
//		$('#ORG_group').show();
//		$('#NT_group').find('input').val('');
//	}
});

/**
 * "非比例"時，也要自動帶出保費，觸發時機在user key 完
 * (比率(%) ,佣金(%), 營業稅(%), 再保費(100%), 100%起賠額 ,100%責任額)
 * @returns
 */
$('[name="brkshare_rate"], [name="brkcomm_rate"], [name="brktax_rate"], [name="brkprem100"], [name="brkexcess_bgn"], [name="brkexcess_limit"]').blur(function(){
	try {
		if($('#fac_type2').val() == '2'){
			// 比率(%)
			let brkshareRate = $('input[name="brkshare_rate"]').val();
			// 佣金(%)
	        let brkcommRate = $('input[name="brkcomm_rate"]').val();
	        // 營業稅(%)
	        let brktaxRate = $('input[name="brktax_rate"]').val();
	        // 再保費(100%)
	        let brkprem100 = $('input[name="brkprem100"]').val();
	        // 100%起賠額
	        let brkexcessBgn = $('input[name="brkexcess_bgn"]').val();
	        // 100%責任額
	        let brkexcessLimit = $('input[name="brkexcess_limit"]').val();
	        
			if(!checkIsNullSpace(brkshareRate) && !checkIsNullSpace(brkcommRate)
					&& !checkIsNullSpace(brktaxRate) && !checkIsNullSpace(brkprem100)
					&& !checkIsNullSpace(brkexcessBgn) && !checkIsNullSpace(brkexcessLimit)){
				// 公式: RI再保費(台幣) = 比率*再保費(100%)
				if ($('#billCurrency option:selected').text() == 'NTD') {
					// RI再保費(台幣)
					let brkRIpremNT = floatMul(floatDiv(brkshareRate, 100, 4), brkprem100, 0);
					$('input[name="brkRIprem_NT"]').val(brkRIpremNT);
					// RI佣金(台幣) = RI再保費(台幣)*佣金(%)
					let brkRIcommNT = floatMul(floatDiv(brkcommRate, 100, 4), brkRIpremNT, 0);
					$('input[name="brkRIcomm_NT"]').val(brkRIcommNT);
					// RI營業稅(台幣) = RI再保費(台幣)*營業稅(%)
					let brkRItaxNT = floatMul(floatDiv(brktaxRate, 100, 4), brkRIpremNT, 0);
					$('input[name="brkRItax_NT"]').val(brkRItaxNT);
				}else{
					// RI再保費(外幣)
					let brkRIpremORG = floatMul(floatDiv(brkshareRate, 100, 4), brkprem100, 0);
					$('input[name="brkRIprem_ORG"]').val(brkRIpremORG);
					// RI佣金(外幣) = RI再保費(外幣)*佣金(%)
					let brkRIcommORG = floatMul(floatDiv(brkcommRate, 100, 4), brkRIpremORG, 0);
					$('input[name="brkRIcomm_ORG"]').val(brkRIcommORG);
					// RI營業稅(外幣) = RI再保費(外幣)*營業稅(%)
					let brkRItaxORG = floatMul(floatDiv(brktaxRate, 100, 4), brkRIpremORG, 0);
					$('input[name="brkRItax_ORG"]').val(brkRItaxORG);
				}
			}
			
		}
	} catch (e) {
		alert(e);
	}
});