function createTableSet() {
    //#table1301A_modelThree start
    let table1301A_modelThreeColumns = [
        ["checkbox", { showBtn: true }],
        ["riskNo", "同險代號", "display"],
        ["policyNo", "保單號碼", "display"],
        ["endorseNo", "批單號碼", "display"],
        ["addrNo", "地址序號", "display"],
        ["amt", "保險總金額", "money1"],
        ["mkovse", "境外分入註記", "display"],
    ];

    //tabulator欄位格式製作
    let table1301A_modelThreeColumnsFormat = createTableColumns(table1301A_modelThreeColumns);

    //客製tabulator本體
    let table1301A_modelThreeConfigs = {
        layout: "fitDataStretch",
        placeholder: "無資料",
    };

    //按鈕設置與功能
    let table1301A_modelThreeRelatedBtns = [];

    //檢核警告設定
    let table1301A_modelThreeAlertConfig = {};

    createTable("table1301A_modelThree", table1301A_modelThreeColumnsFormat, table1301A_modelThreeConfigs, table1301A_modelThreeRelatedBtns, table1301A_modelThreeAlertConfig);
    //#table1301A_modelThree end

    //#lsvPolicy1 table start
    let lsvPolicy1Columns = [
        ["policyNo", "保單號碼", "display"],
        ["endorseNo", "批單號碼", "display"],
        ["cinsurant", "被保險人", "display"],
    ];

    //tabulator欄位格式製作
    let lsvPolicy1ColumnsFormat = createTableColumns(lsvPolicy1Columns);

    //客製tabulator本體
    let lsvPolicy1Configs = {
        layout: "fitDataStretch",
        placeholder: "無資料",
    };

    //按鈕設置與功能
    let lsvPolicy1RelatedBtns = [];

    //檢核警告設定
    let lsvPolicy1AlertConfig = {};

    createTable("lsvPolicy1", lsvPolicy1ColumnsFormat, lsvPolicy1Configs, lsvPolicy1RelatedBtns, lsvPolicy1AlertConfig);
    //#lsvPolicy1 table end

    //#lsvPolicy2 table start
    let lsvPolicy2Columns = [
        ["policyNo", "保單號碼", "display"],
        ["endorseNo", "批單號碼", "display"],
        ["addrNo", "地址序號", "display"],
        ["usepropName", "使用性質名稱", "display"],
        ["constClass", "建築等級", "display"],
        ["propAddr", "地址", "display"],
    ];

    //tabulator欄位格式製作
    let lsvPolicy2ColumnsFormat = createTableColumns(lsvPolicy2Columns);

    //客製tabulator本體
    let lsvPolicy2Configs = {
        layout: "fitDataStretch",
        placeholder: "無資料",
    };

    //按鈕設置與功能
    let lsvPolicy2RelatedBtns = [];

    //檢核警告設定
    let lsvPolicy2AlertConfig = {};

    createTable("lsvPolicy2", lsvPolicy2ColumnsFormat, lsvPolicy2Configs, lsvPolicy2RelatedBtns, lsvPolicy2AlertConfig);
    //#lsvPolicy2 table end




    //#lsvPolicy3 table start
    let lsvPolicy3Columns = [
        ["policyNo", "保單號碼", "display"],
        ["endorseNo", "批單號碼", "display"],
        ["addrNo", "地址序號", "display"],
        ["additionSeq", "附加險序號", "display"],
        ["additionNo", "附加險代號", "display"],
        ["additionEname", "附加險英文名稱", "display"],
    ];

    //tabulator欄位格式製作
    let lsvPolicy3ColumnsFormat = createTableColumns(lsvPolicy3Columns);

    //客製tabulator本體
    let lsvPolicy3Configs = {
        layout: "fitDataStretch",
        placeholder: "無資料",
    };

    //按鈕設置與功能
    let lsvPolicy3RelatedBtns = [];

    //檢核警告設定
    let lsvPolicy3AlertConfig = {};

    createTable("lsvPolicy3", lsvPolicy3ColumnsFormat, lsvPolicy3Configs, lsvPolicy3RelatedBtns, lsvPolicy3AlertConfig);
    //#lsvPolicy3 table end



    //#lsvPolicy4 table start
    let lsvPolicy4Columns = [
        ["policyNo", "保單號碼", "display"],
        ["endorseNo", "批單號碼", "display"],
        ["addrNo", "地址序號", "display"],
        ["propertyNo", "標的物代號", "display"],
        ["propertyName", "標的物中文名稱", "display"],
        ["propertyEname", "標的物英文簡碼", "display"],
        ["amt", "華南保額", "money1"],
        ["coinsAmt", "共保保額", "money1"],

    ];

    //tabulator欄位格式製作
    let lsvPolicy4ColumnsFormat = createTableColumns(lsvPolicy4Columns);

    //客製tabulator本體
    let lsvPolicy4Configs = {
        placeholder: "無資料",
    };

    //按鈕設置與功能
    let lsvPolicy4RelatedBtns = [];

    //檢核警告設定
    let lsvPolicy4AlertConfig = {};

    createTable("lsvPolicy4", lsvPolicy4ColumnsFormat, lsvPolicy4Configs, lsvPolicy4RelatedBtns, lsvPolicy4AlertConfig);
    //#lsvPolicy4 table end

    //#lsvPolicy6 table start
    genPropList().then(() => {
        let propNoObj = {}
        propNoList.forEach((item) => {
            propNoObj[item.optionKey] = item.optionKey;
        });
        //console.log(propNoObj);

        let chooseBtn = [{
            name: "選取",
            func: function(row) {
                row.toggleSelect();
                //console.log(chooseRinser);
            }
        }]
        let lsvPolicy6Columns = [
            //["checkbox", { showBtn: true }],
            //["button", "", chooseBtn],
            ["propertyNo", "代號", "select1", propNoObj],
            ["propertyName", "英文簡碼", "display"],
            ["amt", "總保額", "money1"],
        ];
        //tabulator欄位格式製作
        let lsvPolicy6ColumnsFormat = createTableColumns(lsvPolicy6Columns);

        //客製tabulator本體
        let lsvPolicy6Configs = {
            placeholder: "無資料",
            cellEdited: function(cell) {
                //console.log(cell.getField());
                if (cell.getField() == 'propertyNo') {
                    let propArr = propNoList.filter(function(item) {
                        return cell.getValue() == item.optionKey;
                    });
                    //console.log(propArr);
                    cell.getRow().getCell('propertyName').setValue(propArr[0].optionValue);
                }
            }

        };

        //按鈕設置與功能
        let lsvPolicy6RelatedBtns = [{
                type: "add",
                name: "新增",
                class: "btn btn-oneD",
                position: "#lsvPolicy6Btn",
                getDefaultData: function() {
                    return {
                        isSuccess: true,
                        data: {}
                    }
                },
            },
            {
                type: "edit",
                name: "修改",
                class: "btn btn-oneC",
                position: "#lsvPolicy6Btn",
            },
            {
                type: "del",
                name: "刪除",
                class: "btn btn-oneA",
                position: "#lsvPolicy6Btn",
                delApi: function(rowsDataArry) {
                    return {
                        isSuccess: true,
                        errMsg: "刪除失敗，請聯絡系統管理員"
                    }
                }
            },
            {
                type: "cancel",
                name: "取消",
                class: "btn btn-oneF",
                position: "#lsvPolicy6Btn",
            },
            {
                type: "save",
                name: "確定",
                class: "btn btn-oneD",
                position: "#lsvPolicy6Btn",
                nullSpaceCheck: false,
                rules: {},
                addSaveApi: function(rowData) {
                    return { isSuccess: true }
                },
                editSaveApi: function(oldData, newData, newDataJson) {
                    return { isSuccess: true }
                }
            },
        ];

        //檢核警告設定
        let lsvPolicy6AlertConfig = {};
        createTable("lsvPolicy6", lsvPolicy6ColumnsFormat, lsvPolicy6Configs, lsvPolicy6RelatedBtns, lsvPolicy6AlertConfig);
    });

    //#lsvPolicy6 table end

    //#lsvPolicy7 table start
    genInsKindList().then(() => {
        let insKindObj = {}
        insKindList.forEach((item) => {
            insKindObj[item.optionKey] = item.optionValue;
        });
        //console.log(insKindObj);

        let chooseBtn = [{
            name: "選取",
            func: function(row) {
                row.toggleSelect();
                //console.log(chooseRinser);
            }
        }]

        let lsvPolicy7Columns = [
            //["button", "", chooseBtn],
            //["checkbox", { showBtn: true }],
            ["content", "險種", "select1", insKindObj],
            ["premRate", "費率", "display", {
                formatter: "money",
                formatterParams: {
                    precision: 4,
                }
            }],
            ["facPremRate", "臨分費率", "input", {
                formatter: "money",
                formatterParams: {
                    precision: 4,
                }
            }],
            ["amt", "共保保額", "display", {
                formatter: "money",
                formatterParams: {
                    precision: 0,
                }
            }],
            ["prem", "共保保費", "display", {
                formatter: "money",
                formatterParams: {
                    precision: 0,
                }
            }],
            ["facPrem", "臨分保費", "money1"],
            ["commRate", "佣金率", "input", {
                formatter: "money",
                formatterParams: {
                    precision: 4,
                }
            }],
            ["limitRate", "限额比例", "input", {
                formatter: "money",
                formatterParams: {
                    precision: 4,
                }
            }],
            ["limit", "限额", "money1"],
        ];

        //tabulator欄位格式製作
        let lsvPolicy7ColumnsFormat = createTableColumns(lsvPolicy7Columns);

        //客製tabulator本體
        let lsvPolicy7Configs = {
            placeholder: "無資料",
            cellEdited: function(cell) {
                //console.log(cell.getField());
                if (cell.getField() == 'facPremRate') {
                    let fac_prem_rate = cell.getValue();
                    let amt_coins = cell.getRow().getCell('amt').getValue();
                    cell.getRow().getCell('facPrem').setValue(floatMul(floatDiv(fac_prem_rate, 1000, 8), amt_coins, 0));
                }
            }
        };

        //按鈕設置與功能
        let lsvPolicy7RelatedBtns = [{
                type: "add",
                name: "新增",
                class: "btn btn-oneD",
                position: "#lsvPolicy7Btn",
                getDefaultData: function() {
                    return {
                        isSuccess: true,
                        data: {}
                    }
                },
            },
            {
                type: "edit",
                name: "修改",
                class: "btn btn-oneC",
                position: "#lsvPolicy7Btn",
            },
            {
                type: "del",
                name: "刪除",
                class: "btn btn-oneA",
                position: "#lsvPolicy7Btn",
                delApi: function(rowsDataArry) {
                    updateSumAmt();
                    updateRinserData();
                    return {
                        isSuccess: true,
                        errMsg: "刪除失敗，請聯絡系統管理員"
                    }
                }
            },
            {
                type: "cancel",
                name: "取消",
                class: "btn btn-oneF",
                position: "#lsvPolicy7Btn",
            },
            {
                type: "save",
                name: "確定",
                class: "btn btn-oneD",
                position: "#lsvPolicy7Btn",
                nullSpaceCheck: false,
                rules: {},
                addSaveApi: function(rowData) {
                    updateSumAmt();
                    updateRinserData();
                    updateBrokerDetailTable();
                    return { isSuccess: true }
                },
                editSaveApi: function(oldData, newData, newDataJson) {
                    updateSumAmt();
                    updateRinserData();
                    updateBrokerDetailTable();
                    // 清空第一層再保人輸入框資料(避免資料殘留，造成連動功能無效的誤會)
                    clearFirstData();
                    // 觸發"明細查詢/分保計算"按鈕功能 TODO 因為改成儲存時都會呼叫此功能，故這裡註解掉，避免呼叫過於頻繁
//                    genlsvPolicy10Data1('notAlert');
                    return { isSuccess: true }
                }
            },
        ];

        //檢核警告設定
        let lsvPolicy7AlertConfig = {};
        createTable("lsvPolicy7", lsvPolicy7ColumnsFormat, lsvPolicy7Configs, lsvPolicy7RelatedBtns, lsvPolicy7AlertConfig);
    });

    //lsvPolicy7 end

    //rinserTable table start
    //選取按鈕設定
    let chooseBtn1 = [{
        name: "選取",
        func: function(row) {
            let data = {
                "lblrin_com_id": row.getData().lblrin_com_id,
                "lblename": row.getData().lblename,
            }
            chooseRinser = data;
            //console.log(chooseRinser);
        }
    }]
    let rinserTableColumns = [
        //["button", "", chooseBtn1],
        ["lblrin_com_id", "再保人代號", "display"],
        ["lblename", "再保人英文名稱", "display", { sorter: "string" }],
        ["lblcname", "再保人中文名稱", "display", { sorter: "string" }],
        ["lblsname", "再保人中文簡稱", "display", { sorter: "string" }],
        ["lblremark", "備註", "display"],
        ["dtaUSEMRK", "註銷日", "display"]
    ]

    //tabulator欄位格式製作
    let rinserTableColumnsFormat = createTableColumns(rinserTableColumns)

    //客製tabulator本體
    let rinserTableConfigs = {
        layout: "fitColumns",
        placeholder: "無資料",
        rowSelected: function(row) {
            let data = {
                "lblrin_com_id": row.getData().lblrin_com_id,
                "lblename": row.getData().lblename,
            }
            chooseRinser = data;
            //console.log(chooseRinser);
        }
    };

    //按鈕設置與功能
    let rinserTableRelatedBtns = []

    //檢核警告設定
    let rinserTableAlertConfig = {}

    //建立table
    let rinserTable = createTable("rinserTable", rinserTableColumnsFormat, rinserTableConfigs, rinserTableRelatedBtns, rinserTableAlertConfig);

    //rinserTable table end



    //#rinserDetail table start
    let chooseBtn2 = [{
        name: "選取",
        func: function(row) {
            row.toggleSelect();
            let rowData = row.getData();
            $('input[name="lsv8DataId"]').val(rowData.id);
            $("input[name='share_status'][value=" + rowData.shareStatus + "]").prop('checked', true);
            $('input[name="rin_com_id1"]').val(rowData.rinComId);
            $('#rinser_1').text(rowData.rinserEname);
            $('input[name="share_rate"]').val(rowData.shareRate);
            $('input[name="share_amt"]').val(rowData.cedeAmt);
            $('input[name="share_comm_rate"]').val(rowData.commRate);
            $('input[name="tax_rate"]').val(rowData.taxRate);
            // $('input[name="handling_rate"]').val(selectDataObj.handling_rate);
            // $('input[name="broker_rate"]').val(selectDataObj.broker_rate);
            // $('input[name="discount_rate"]').val(selectDataObj.discount_rate);
            $('input[name="share_rinPrem"]').val(rowData.cedePrem);
            $('input[name="answer_date"]').val(rowData.answerDate);
            $('input[name="paid_date"]').val(rowData.paidDate);
            $('input[name="paid_date_expect"]').val(rowData.paidDateExpect);
            $('input[name="orgcomm"]').val(rowData.orgcomm);
            $('input[name="orgtax"]').val(rowData.orgtax);
            $('input[name="orgprem"]').val(rowData.orgprem);
            $('input[name="ref_no"]').val(rowData.refNo);
            $('#lsv8Edit').prop('disabled', false);
            $('#lsv8Delete').prop('disabled', false);

        }
    }]
    let rinserDetailColumns = [
        //["button", "", chooseBtn2],
        ["shareStatusStr", "處理階段", "display"],
        ["rinComId", "再保人代號", "display"],
        ["rinserEname", "再保人名稱", "display"],
        ["shareRate", "分出比率", "display", {
            formatter: "money",
            formatterParams: {
                precision: 4,
            }
        }],
        ["cedeAmt", "分出保額", "money1"],
        ["commRate", "佣金率", "display", {
            formatter: "money",
            formatterParams: {
                precision: 4,
            }
        }],
        ["orgcomm", "佣金出帳金額", "money"],
        ["taxRate", "營業稅率", "display", {
            formatter: "money",
            formatterParams: {
                precision: 4,
            }
        }],
        ["orgtax", "營業稅出帳金額", "money"],
        // ["handling_rate", "處理率", "display",
        //     {
        //         formatter: "money",
        //         formatterParams: {
        //             precision: 2,
        //         }
        //     }
        // ],
        // ["broker_rate", "代理率", "display", {
        //     formatter: "money",
        //     formatterParams: {
        //         precision: 2,
        //     }
        // }],
        // ["discount_rate", "折扣率", "display",
        //     {
        //         formatter: "money",
        //         formatterParams: {
        //             precision: 2,
        //         }
        //     }
        // ],
        ["cedePrem", "再保費台幣", "money1"],
        ["orgprem", "再保費出帳金額", "money"],
        ["refNo", "Ref. No.", "display"],
        ["answerDate", "回覆日期", "datetime"],
        ["paidDate", "付款日期", "display"],
        ["paidDateExpect", "希望付款日", "display"],
        ["printDate", "印單日", "display"],
        ["transferDate", "轉檔日", "display"],


    ];

    //tabulator欄位格式製作
    let rinserDetailColumnsFormat = createTableColumns(rinserDetailColumns);

    //客製tabulator本體
    let rinserDetailConfigs = {
        layout: "fitDataStretch",
        placeholder: "無資料",
        rowSelected: function(row) {
            let rowData = row.getData();
            $('#lsv8Edit').prop('disabled', false);
            $('#lsv8Delete').prop('disabled', false);
            //若為臨分續保模式，則只留下再保人代號與再保人名稱、分出比率資料，其餘資料不留
//            if(receiveParam.gsProcessMode === '4'){
//            	$('input[name="rin_com_id1"]').val(rowData.rinComId);
//                $('#rinser_1').text(rowData.rinserEname);
//				$('input[name="share_rate"]').val(rowData.shareRate);
//            }else{            	
			if(rowData.id){		
            	$('input[name="lsv8DataId"]').val(rowData.id);
			}
			if(rowData.shareStatus){		
            	$("input[name='share_status'][value=" + rowData.shareStatus + "]").prop('checked', true);
			}
			if(rowData.rinComId){		
            	$('input[name="rin_com_id1"]').val(rowData.rinComId);
			}
			if(rowData.rinserEname){		
            	$('#rinser_1').text(rowData.rinserEname);
			}
			if(rowData.shareRate){		
            	$('input[name="share_rate"]').val(rowData.shareRate);
			}
    		if(rowData.cedeAmt){		
            	$('input[name="share_amt"]').val(rowData.cedeAmt);
			}       	
      		if(rowData.commRate){		
            	$('input[name="share_comm_rate"]').val(rowData.commRate);
			}	             	
        	if(rowData.taxRate){		
            	$('input[name="tax_rate"]').val(rowData.taxRate);
			}                  	  
            	// $('input[name="handling_rate"]').val(selectDataObj.handling_rate);
            	// $('input[name="broker_rate"]').val(selectDataObj.broker_rate);
            	// $('input[name="discount_rate"]').val(selectDataObj.discount_rate);
        	if(rowData.cedePrem){		
           		$('input[name="share_rinPrem"]').val(rowData.cedePrem);
			}  
        	if(rowData.answerDate){		
       			$('input[name="answer_date"]').val(rowData.answerDate);
			}         	
          	if(rowData.paidDate){		
     			$('input[name="paid_date"]').val(rowData.paidDate);
			}                 
            if(rowData.paidDateExpect){		
     			$('input[name="paid_date_expect"]').val(rowData.paidDateExpect);
			}                	
            if(rowData.orgcomm){		
    			$('input[name="orgcomm"]').val(rowData.orgcomm);
			}            	
            if(rowData.orgtax){		
    			$('input[name="orgtax"]').val(rowData.orgtax);
			}                   	
            if(rowData.orgprem){		
   				$('input[name="orgprem"]').val(rowData.orgprem);
			}             	
            if(rowData.refNo){		
  				$('input[name="ref_no"]').val(rowData.refNo);
			}             	
            
//            }

        }
    };

    //按鈕設置與功能
    let rinserDetailRelatedBtns = [];

    //檢核警告設定
    let rinserDetailAlertConfig = {};

    createTable("rinserDetail", rinserDetailColumnsFormat, rinserDetailConfigs, rinserDetailRelatedBtns, rinserDetailAlertConfig);
    //#rinserDetail table end

    //#brokerDetail table start
    let chooseBtn3 = [{
        name: "選取",
        func: function(row) {
            row.toggleSelect();
            let rowData = row.getData();
            $('#fac_type2').val(rowData.facType);
            $('input[name="lsv9DataId"]').val(rowData.id);
            $('input[name="broker_id1"]').val(rowData.brokerId);
            $('#broker').text(rowData.brokerEname);
            $('input[name="rin_com_id2"]').val(rowData.rinComId);
            $('#rinser_2').text(rowData.rinkerEname);
            $('input[name="brkprem100"]').val(rowData.brkprem100);
            $('input[name="brkshare_rate"]').val(rowData.shareRate);
            $('input[name="brkcomm_rate"]').val(rowData.brkcommRate);
            $('input[name="brktax_rate"]').val(rowData.brktaxRate);
            $('input[name="brkexcess_bgn"]').val(rowData.brkexcessBgn);
            $('input[name="brkexcess_limit"]').val(rowData.brkexcessLimit);
            $('input[name="brkRIprem_NT"]').val(rowData.brkripremNt);
            $('input[name="brkRIcomm_NT"]').val(rowData.brkricommNt);
            $('input[name="brkRIprem_ORG"]').val(rowData.brkripremOrg);
            $('input[name="brkRIcomm_ORG"]').val(rowData.brkricommOrg);
            $('input[name="brkRItax_NT"]').val(rowData.brkritaxNt);
            $('input[name="brkRItax_ORG"]').val(rowData.brkritaxOrg);
            $('#lsv9Edit').prop('disabled', false);
            $('#lsv9Delete').prop('disabled', false);
        }
    }]

    let brokerDetailColumns = [
        //["button", "", chooseBtn3],
        ["facTypeStr", "再保型態", "display"],
        ["brokerId", "經紀人", "display"],
        ["rinComId", "再保人", "display"],
        ["shareRate", "比率(%)", "display", {
            formatter: "money",
            formatterParams: {
                precision: 4,
            }
        }],
        ["brkcommRate", "佣金(%)", "display", {
            formatter: "money",
            formatterParams: {
                precision: 4,
            }
        }],
        ["brktaxRate", "營業稅(%)", "display", {
            formatter: "money",
            formatterParams: {
                precision: 4,
            }
        }],
        ["brkprem100", "再保費(100%)", "money1"],
        ["brkexcessBgn", "100%起賠額", "money1"],
        ["brkexcessLimit", "100%責任額", "money1"],
        ["brkripremNt", "RI再保費(台幣)", "money1"],
        ["brkricommNt", "RI佣金(台幣)", "money1"],
        ["brkritaxNt", "RI營業稅(台幣)", "money1"],
        ["brkripremOrg", "RI再保費(外幣)", "money"],
        ["brkricommOrg", "RI佣金(外幣)", "money"],
        ["brkritaxOrg", "RI營業稅(外幣)", "money"]
    ];

    //tabulator欄位格式製作
    let brokerDetailColumnsFormat = createTableColumns(brokerDetailColumns);

    //客製tabulator本體
    let brokerDetailConfigs = {
        layout: "fitDataStretch",
        placeholder: "無資料",
        rowSelected: function(row) {
            //row.toggleSelect();
            let rowData = row.getData();
            $('#fac_type2').val(rowData.facType);
            $('input[name="lsv9DataId"]').val(rowData.id);
            $('input[name="broker_id1"]').val(rowData.brokerId);
            $('#broker').text(rowData.brokerEname);
            $('input[name="rin_com_id2"]').val(rowData.rinComId);
            $('#rinser_2').text(rowData.rinkerEname);
            $('input[name="brkprem100"]').val(rowData.brkprem100);
            $('input[name="brkshare_rate"]').val(rowData.shareRate);
            $('input[name="brkcomm_rate"]').val(rowData.brkcommRate);
            $('input[name="brktax_rate"]').val(rowData.brktaxRate);
            $('input[name="brkexcess_bgn"]').val(rowData.brkexcessBgn);
            $('input[name="brkexcess_limit"]').val(rowData.brkexcessLimit);
            $('input[name="brkRIprem_NT"]').val(rowData.brkripremNt);
            $('input[name="brkRIcomm_NT"]').val(rowData.brkricommNt);
            $('input[name="brkRIprem_ORG"]').val(rowData.brkripremOrg);
            $('input[name="brkRIcomm_ORG"]').val(rowData.brkricommOrg);
            $('input[name="brkRItax_NT"]').val(rowData.brkritaxNt);
            $('input[name="brkRItax_ORG"]').val(rowData.brkritaxOrg);
            $('#lsv9Edit').prop('disabled', false);
            $('#lsv9Delete').prop('disabled', false);
        }
    };

    //按鈕設置與功能
    let brokerDetailRelatedBtns = [];

    //檢核警告設定
    let brokerDetailAlertConfig = {};

    createTable("brokerDetail", brokerDetailColumnsFormat, brokerDetailConfigs, brokerDetailRelatedBtns, brokerDetailAlertConfig);
    //#brokerDetail table end

    //#lsvPolicy10 table start
    let chooseBtn4 = [{
        name: "選取",
        func: function(row) {
            row.toggleSelect();
            $('#lsv10Edit').prop('disabled', false);
            $('#lsv10Delete').prop('disabled', false);
        }
    }]

    let lsvPolicy10Columns = [
        //["button", "", chooseBtn4],
        ["policyNo", "保單號碼", "display"],
        ["endorseNo", "批單號碼", "display"],
        ["addrNo", "序號", "display"],
        ["riskNo", "同險號碼", "display"],
        ["dutyDbgn", "生效起日", "display"],
        ["dutyDend", "生效迄日", "display"],
        ["amt", "分出保額", "money1",
            {
                bottomCalc: "sum",
                bottomCalcFormatter: "money",
                bottomCalcFormatterParams: {
                    precision: 0,
                }
            }
        ],
        ["prem", "分出保費", "money1", {
            bottomCalc: "sum",
            bottomCalcFormatter: "money",
            bottomCalcFormatterParams: {
                precision: 0,
            }
        }],
        ["amtTyp", "颱洪分保额", "money1", {
            bottomCalc: "sum",
            bottomCalcFormatter: "money",
            bottomCalcFormatterParams: {
                precision: 0,
            }
        }],
        ["premTyp", "颱洪分保費", "money1", {
            bottomCalc: "sum",
            bottomCalcFormatter: "money",
            bottomCalcFormatterParams: {
                precision: 0,
            }
        }],
        ["amtEar", "地震分保额", "money1", {
            bottomCalc: "sum",
            bottomCalcFormatter: "money",
            bottomCalcFormatterParams: {
                precision: 0,
            }
        }],
        ["premEar", "地震分保費", "money1", {
            bottomCalc: "sum",
            bottomCalcFormatter: "money",
            bottomCalcFormatterParams: {
                precision: 0,
            }
        }],
    ];

    //tabulator欄位格式製作
    let lsvPolicy10ColumnsFormat = createTableColumns(lsvPolicy10Columns);

    //客製tabulator本體
    let lsvPolicy10Configs = {
        placeholder: "無資料",
        layout: "fitDataStretch",
        rowSelected: function(row) {
            $('#lsv10Edit').prop('disabled', false);
            $('#lsv10Delete').prop('disabled', false);
        }
    };

    //按鈕設置與功能
    let lsvPolicy10RelatedBtns = [];

    //檢核警告設定
    let lsvPolicy10AlertConfig = {};

    createTable("lsvPolicy10", lsvPolicy10ColumnsFormat, lsvPolicy10Configs, lsvPolicy10RelatedBtns, lsvPolicy10AlertConfig);
    //#lsvPolicy10 table end

}


function customFilter(data) {
    var match = false;
    var filterValue = $("#filter-value").val();
    for (var key in data) {
        var dataValue = data[key];

        if (!checkIsNullSpace(dataValue) && dataValue.indexOf(filterValue) !== -1) {
            match = true;
            break;
        }
    }
    return match;
}


$("#filter-value").keyup(() => {
    let table = Tabulator.prototype.findTable('#rinserTable')[0];
    table.setFilter(customFilter);
});
$('#filter-clear').click(() => {
    $("#filter-value").val("");
    let table = Tabulator.prototype.findTable('#rinserTable')[0];
    table.clearFilter();
})