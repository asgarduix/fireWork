//預設
$(function(){
	$('#btnSaveDetail').hide();
})

//查詢鈕
function btnQueryRin1205(){
	
	//搜尋時，結束目前tabulator的編輯狀態
	if(table1205.tableMode){
		$("#table1205-cancel").click();
	}
	
	//檢核
	let message = "";
	let nextRow = "<br>";
	
	if(checkIsNullSpace($('#txtrisk_no').val())){
		message = message + "同險編號不可為空白!"+nextRow;
	}
	if( 11 != $('#txtrisk_no').val().trim().length){
		message = message + "同險編號需為11碼!"+nextRow;
	}
	if( 4 != $('#txttreaty_year').val().trim().length){
		message = message + "合約年度需為四位數!"+nextRow;
	}
	
	let dta = $('#dta').val();	//有效日期
	if(checkIsNullSpace(dta)){
		message = message + "有效日期不可為空白!"+nextRow;
	}
	
	if(!isValidDate(dta)){
		message = message + "輸入的日期為無效日期!"+nextRow;
	}
	
	if(!checkIsNullSpace(message)){
		styleAlert(message);
		return;
	}
	
	//進行查詢
	let param = {
			"riskNo" : $('#txtrisk_no').val(),				//同險編號
			"treatyYear" : $('#txttreaty_year').val(),		//合約年度
			"policyDate" : $('#dta').val()					//有效日期
	}
	
	ajaxRequestIsAsyncDynimicBytoken("../../rin1205api/query1205list", true, false, param,     
			(res) => {
						if (res != null && "000" === res.status) {
							loadData("table1205", res.data, {type:"dataCount", value:7})
											
							//檢核各合約分保總額是否等於各總保額(總保額-臨分保額-各個合約分出保額(含自留保額) 是否為0)
							let msg = checkMoneyCal();

							if(!checkIsNullSpace(msg)){
								alert(msg+"各合約分保總額不等於總保額");
							}
							
							//有資料，顯示分保明細調整與修改鈕;反之則不顯示
							if(res.data.length <= 0){
								$('#btnSaveDetail').hide();
								$('#table1205-edit').hide();
							}else{								
								$('#btnSaveDetail').show();
								$('#table1205-edit').show();
							}
						}else{
							alert("「分保同險資料查詢」失敗!!!請聯絡管理人員!!!");
						}
			}, (error) => {
							console.log(error);
							alert("「分保同險資料查詢」失敗!!!請聯絡管理人員!!!");
			})
}


//分保明細調整鈕
function saveDetail(){
	
	//結束目前tabulator的編輯狀態
	if(table1205.tableMode){
		alert("請完成編輯");
		return;
	}

	//檢核保額
	let msg = checkMoneyCal();
	
	if(!checkIsNullSpace(msg)){
		if(!confirm("現行資料有合約分保總額不等於總保額狀況，請確認是否仍要將資料寫入"+"\n"+msg+"確認更正資料請按[確定]，重新調整資料請按[取消]")){
			return;
		}
	}
		
	//進行【分保明細調整】update	
	let parJson = JSON.stringify(table1205.getData());
	
	let res = ajaxPostByJsonParam("../../rin1205api/updateshareamtprem", parJson, false);
	
	
	//顯示
	if('000' === res.status){
		alert("【分保明細調整】成功!!!");

	}else{
		alert("【分保明細調整】失敗!!!請聯絡管理人員!!!");
	}
}


//tabulator欄位設置
let colwidthDe = 45;
let wordTitle = 14;
let columns1205 = [
	[ "checkbox", { showBtn: true }],
	[ "txtpolicy_no", "保單號", "display", {minWidth:wordTitle*3+colwidthDe}],
	[ "txtendorse_no", "批單號", "display", {minWidth:wordTitle*3+colwidthDe}],
	[ "numaddr_no", "序號", "display", {minWidth:wordTitle*2+colwidthDe}],
	[ "dtaduty_bgn", "生效日期起", "display", {minWidth:wordTitle*5+colwidthDe}],
	[ "dtaduty_end", "生效日期迄", "display", {minWidth:wordTitle*5+colwidthDe}],
	[ "txtuseprop_name", "使用性質", "display", {minWidth:wordTitle*4+colwidthDe}],
	[ "dtapolicy_dprt", "印單日", "display", {minWidth:wordTitle*3+colwidthDe}],
	[ "numamt", "總保額", "money1",{editable:()=>{return false},minWidth:wordTitle*3+colwidthDe}],
	[ "numprem", "總保費", "money1",{editable:false,minWidth:wordTitle*3+colwidthDe}],
	[ "numprem_rate", "保單費率", "display", {minWidth:wordTitle*4+colwidthDe}],
	[ "numshare_prem_rate", "分保費率", "display", {minWidth:wordTitle*4+colwidthDe}],
	[ "numlimit", "保單限額", "money1", {minWidth:wordTitle*4+colwidthDe}],
	[ "sum_amt_1", "臨分保額", "money1", {minWidth:wordTitle*4+colwidthDe}],
	[ "numamt_ear_1", "地震自留", "money1", {minWidth:wordTitle*4+colwidthDe}],
	[ "numamt_typ_1", "颱風自留", "money1", {minWidth:wordTitle*4+colwidthDe}],
	[ "numamt_ear_2", "地震臨分", "money1", {minWidth:wordTitle*4+colwidthDe}],
	[ "numamt_typ_2", "颱風臨分", "money1", {minWidth:wordTitle*4+colwidthDe}],
	[ "txtacct_flag", "帳單註記", "display", {minWidth:wordTitle*4+colwidthDe}],
	[ "txtiendorsement2", "原批單號", "display", {minWidth:wordTitle*4+colwidthDe}]
];



//父表格控制子表格開合按鈕
let toggleBtn = {
    title: "",
    formatter: (cell, formatterParams, onRendered) => {
        let div = document.createElement('div');
        let el = cell.getRow().getElement()
        let btnName = ["＋", "–"]
        $(div).css("display", "flex").css("justify-content", "center").css("align-items", "center")
        btnName.forEach((name, index) => {
            let btn = document.createElement('button');
            $(btn).text(name)
            $(btn).attr("name", name === "＋" ? "open" : "close")
            elementsChangeClass(btn, "add", "table-btn-switch")
            btn.addEventListener("click", (e) => {
            
                $(el).find(".table-btn-switch").toggle()
                $(el).find(".table-subtable-container").toggle()
            });
            if (index === 1) {
                $(btn).css("display", "none")
            }
            div.appendChild(btn)
        })
        return div
    },
    headerHozAlign: "center",
    hozAlign: "center",
    headerSort: false,
    width: 80,
}

//tabulator欄位格式製作
let colsFormat1205 = createTableColumns(columns1205);
colsFormat1205.splice(1, 0, toggleBtn)  // 將子表格開合按鈕放進欄位中 (操作的位置(索引),刪除幾個,插入的東西)

//客製tabulator本體
let tableConfigs1205 = {}

//按鈕設置與功能
let tableRelatedBtns1205 = [

	    {
        type: "cancel",
        name: "取消",
        class: "btn btn-oneD",
        position: "#table1205-btn",
    },
    {
        type: "edit",
        name: "修改",
        class: "btn btn-oneE",
        position: "#table1205-btn",
    },
    {
        type: "save",
        name: "儲存",
        class: "btn btn-oneB",
        position: "#table1205-btn",
        nullSpaceCheck: true,
        rules:{},
        editSaveApi: function (oldData, newData, newDataJson) {
         		        	
        	 //edit
            // 回傳值：Object格式資料 { isSuccess: true} or { isSuccess: false, fields: ["a","b"], errMsg: "欄位驗證失敗" }
            //oldData:Array, newData:Array, newDataJson:Object
            //call api here

            let table = Tabulator.prototype.findTable(`#table1205`)[0];
            let subTableId = table1205.getSelectedData()[0].primaryKey.replaceAll(" ", "");

            //取得子表格資料
            let subData = table1205.getSelectedRows()[0].getData().rin1205SubVo
			//子表格資料必填欄位檢核
            for(i = 0; i < subData.length; i++){
          		//分保額
            	if(!subData[i].numshare_amt_i.trim()){            		
           		 	return { isSuccess: false, fields: [], errMsg: "欄位不可為空" }
            	}
				//分保費
            	if(!subData[i].numshare_prem_i.trim()){            		
           		 	return { isSuccess: false, fields: [], errMsg: "欄位不可為空" }
            	}
            	
            }
            // 如果api成功才click 將子表格的編輯狀態結束
            $(`#${subTableId}-save`).click()
            return { isSuccess: true }

        }
    },

]

let heightConfig2 = {
    type: "dataCount",
    value: 15,
}

let tableConfigs1205sub = {
    //子表格製作設定
    rowFormatter: function (row) {
        let container = document.createElement("div");
        let subtable = document.createElement("div");
        let btnDiv = document.createElement("div");
        let subEditBtn = document.createElement("button");
        let subSaveBtn = document.createElement("button");
        let subCancelBtn = document.createElement("button");
        // 此id用以製作子表格，請使用資料中，無法編輯且不重複的值
        let id = row.getData().primaryKey.replaceAll(" ", "")
        // 避免重複製作，需先判斷是否已製作子表格
        if (!Tabulator.prototype.findTable(`#${id}`)) {
            //初始化為隱藏，後續由開合按鈕控制
            $(container).css("display", "none")
            //子表格按鈕列隱藏，後續由js直接點擊按鈕操作
            $(btnDiv).css("display", "none")
            //設置class以利後續修改表格樣式
            elementsChangeClass(container, "add", "table-subtable-container")
            elementsChangeClass(subtable, "add", "table-subtable")
            $(subtable).attr("id", id)

            //subBtn為隱藏按鈕，後續以js點擊操作子表格CSS表現
            $(subEditBtn).attr("id", `${id}-edit`).on("click", () => {
                let table = Tabulator.prototype.findTable(`#${id}`)[0];

                table.clearHistory()
                table.subHistoryData = table.getData()
                table.getRows().forEach(row => {
                    row.update({ _edit: true })
//                    elementsChangeClass(row.getElement(), "add", "table-editable")

                    row.getCells().forEach(cell => {
                    	 let editable = cell.getColumn().getDefinition().editable;
                         if (editable && editable(cell)) {
                        	 elementsChangeClass(cell.getElement(), "add", "cell-editable")
                         }
                        
                    });
                })
            })
            //subBtn為隱藏按鈕，後續以js點擊操作子表格CSS表現
            $(subSaveBtn).attr("id", `${id}-save`).on("click", () => {
                let table = Tabulator.prototype.findTable(`#${id}`)[0];

                table.getRows().forEach(row => {
                    row.update({ _edit: false })
                    elementsChangeClass(row.getElement(), "remove", "table-editable")

                    row.getCells().forEach(cell => {
                        elementsChangeClass(cell.getElement(), "remove", "cell-editable")
                    })
                });
            })
            //subBtn為隱藏按鈕，後續以js點擊操作子表格CSS表現
            $(subCancelBtn).attr("id", `${id}-cancel`).on("click", () => {
                let table = Tabulator.prototype.findTable(`#${id}`)[0];

                while (table.getHistoryUndoSize() > 0) {
                    table.undo()
                }
                let editableDatas = table.getRows()
                editableDatas.forEach(row => {
                    $(row.getElement()).removeClass("table-editable")

                    row.getCells().forEach(cell => {
                        if (!cell.isValid()) {  // 驗證未過的欄位不會被寫進table歷史紀錄 getHistoryUndoSize()
                            cell.cancelEdit()
                        }                      
                            elementsChangeClass(cell.getElement(), "remove", "cell-editable table-error-null table-error-custom")
                        
                    })
                    row.update({ _edit: false })
                });
            })

            btnDiv.appendChild(subEditBtn)
            btnDiv.appendChild(subSaveBtn)
            btnDiv.appendChild(subCancelBtn)
            container.appendChild(subtable);
            container.appendChild(btnDiv);
            row.getElement().appendChild(container);

            // 不建議於子表格內使用Tabulator原生檢核
            let subColumns = [
                ["txttreaty_no_i", "合約代號", "display",{widthGrow: 3 }],
                ["numshare_amt_i", "分保額", "money1",{widthGrow: 3 }],
                ["numshare_prem_i", "分保費", "money1",{widthGrow: 3 }],
            ]
            let subColsFormat = createTableColumns(subColumns)
            let subNullSpaceChecked = true  //空值檢核的CSS變化開關
            let subRules = {    //自定義檢核的CSS變化設定
                date: function (val) {
                    if (val.length === 10) {
                        return true
                    }
                    return false              
                },
            }


            let subTable = new Tabulator(`#${id}`, {
                height: "100%",
                layout: "fitColumns",
                history: true, // 可還原上一步的修改紀錄 .undo()
                reactiveData: true,
                columnHeaderVertAlign: "center",
                columns: subColsFormat,	//表格欄位設定，由外部傳入
                placeholder: "無資料", //display message to user on empty table
                data: row.getData().rin1205SubVo, //資料從這放入   
                cellEditing: function (cell) {
                    let el = cell.getElement()
                    elementsChangeClass(el, "remove", "table-error-null table-error-custom tabulator-validation-fail")
                    $(el).off("focusout").off("input")

                    //空值CSS變化
                    if (subNullSpaceChecked) {
                        $(el).on("focusout", function (e) {
                            let val = e.target.value
                            if (val === undefined) {
                                return
                            }
                            if (val.length === 0) {
                                elementsChangeClass(el, "add", "table-error-null")
                            } else {
                                elementsChangeClass(el, "remove", "table-error-null")
                            }
                        })
                        $(el).on("input", function (e) {
                            let val = e.target.value
                            if (val.length === 0) {
                                elementsChangeClass(el, "add", "table-error-null")
                            } else {
                                elementsChangeClass(el, "remove", "table-error-null")
                            }
                        })
                    }

                    //customRule CSS變化
                    let field = cell.getField()
                    if (subRules[field]) {
                        let el = cell.getElement()
                        $(el).on("focusout", function (e) {
                            let val = e.target.value
                            if (val === undefined) {
                                return
                            }
                            if (subRules[field](val)) {
                                elementsChangeClass(el, "remove", "table-error-custom")
                            } else {
                                elementsChangeClass(el, "add", "table-error-custom")
                            }

                        })

                        $(el).on("input", function (e) {
                            let val = e.target.value
                            if (subRules[field](val)) {
                                elementsChangeClass(el, "remove", "table-error-custom")
                            } else {
                                elementsChangeClass(el, "add", "table-error-custom")
                            }
                        })

                    }
                },
                cellEdited: function (cell) {
                    let val = cell.getValue()
                    if (subNullSpaceChecked) {
                        if (val.length === 0) {
                            elementsChangeClass(cell.getElement(), "add", "table-error-null")
                        } else {
                            elementsChangeClass(cell.getElement(), "remove", "table-error-null")
                        }
                    }
                    let field = cell.getField()
                    if (subRules[field]) {
                        if (subRules[field](val)) {
                            elementsChangeClass(cell.getElement(), "remove", "table-error-custom")
                        } else {
                            elementsChangeClass(cell.getElement(), "add", "table-error-custom")
                        }
                    }
                }
            });
        }
    },
}

//檢核警告設定
let alertConfig1205 = {
    type: "alert",
    position: "",
    displayTime: 0, //毫秒，訊息顯示時間，設置為0不清空
}


let table1205 = createTable("table1205", colsFormat1205, tableConfigs1205sub, tableRelatedBtns1205, alertConfig1205);


//父表格點選編輯時，觸發選取的子表格編輯
$("#table1205-edit").on("click", () => { 
    let table = Tabulator.prototype.findTable(`#table1205`)[0];
    let selectRow = table.getSelectedRows()
    if (selectRow.length === 1) {
        let selectRowElement = selectRow[0].getElement()
        let subContainerStatus = $(selectRowElement).find(".table-subtable-container").css("display")

        if (subContainerStatus === "none") {
            $(selectRowElement).find('.table-btn-switch[name="open"]').click()
        }
        let id = table.getSelectedData()[0].primaryKey.replaceAll(" ", "")
        setTimeout(() => {
            $(`#${id}-edit`).click()
        }, 50);
    }
})


//父表格點選取消時，觸發選取的子表格取消
$("#table1205-cancel").on("click", () => {
    let table = Tabulator.prototype.findTable(`#table1205`)[0];
    let id = table.getSelectedData()[0].primaryKey.replaceAll(" ", "")
    $(`#${id}-cancel`).click()
})


//檢核合約分保總額是否等於總保額(總保額-臨分保額-各個合約分出保額(含自留保額) 是否為0)
function checkMoneyCal(){
	let msg = '';

	for(let i = 0; i < table1205.getData().length; i++){
	   
	    let thisData = table1205.getData()[i];//子表格的每一筆資料	    
		
		//自留保額(FAC_Retain)與各合約分保額加總
		let shareAmtSum = 0;	
		for(let j = 0; j < thisData.rin1205SubVo.length; j++){
			shareAmtSum += thisData.rin1205SubVo[j].numshare_amt_i*1
		}
	   
		//檢核合約分保總額是否等於總保額(總保額-臨分保額-各個合約分出保額(含自留保額) 是否為0)
		let moneyCal = thisData.numamt - thisData.sum_amt_1 - shareAmtSum

		if(moneyCal != 0){
			msg = msg + thisData.txtpolicy_no + "-"+ thisData.txtendorse_no+ "-"+ thisData.numaddr_no+"\n";
		}
	}

	return msg;
	
}
