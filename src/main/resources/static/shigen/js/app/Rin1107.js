//flag
let tableMode = null;    					// 判斷目前模式(新增或編輯或其他)
let editableRowIndex = null;     			// 正在編輯中的資料列索引值
let editableRawData = null;     			// 正在編輯中的資料列原始值
let rowSelectionChangeCheck = false;     	// 選取變動的觸發狀態

//預設
$(function(){
	//顯示所有按鈕
	elementsChangeClass("#add, #edit, #del, #copy", "remove", "btn-hide")	
})

//tabulator欄位設置
let fieldName1107 = [
	["checkbox", { showBtn: true }],
	["txtrisk_no_1", "同險編號", "display", { validator: ["minLength:11", "maxLength:20"],widthGrow:1 } ],
    ["txtrisk_name", "同險名稱", "input", { validator: ["required"], widthGrow:2 } ],
    ["txtarea_code", "地段代碼", "input", { validator: ["required"], widthGrow:1  } ]
];

//tabulator欄位格式製作
let colsFormat = createTableColumns(fieldName1107);

        
//檢核「同險編號」11~20位        
function sleep(ms) {
	return new Promise((resolve) => setTimeout(resolve, ms));
}

let requestLastTime;
async function checkAlert(value) {
	if (value === undefined) {
		return;
	}

	const delay = 1000; // 延遲 毫秒執行
    requestLastTime = (new Date()).valueOf();
    await sleep(delay);
    let nowTime = (new Date()).valueOf();
    let gap = nowTime - requestLastTime;
    if (gap < delay) {
    	return;
    } else {
    	if (value.length < 11 || value.length > 20) {
    		alert("請輸入11到20位數");
    	}
    }
}
        

        
//客製tabulator本體
let tableConfigs = {
		history: true,
		rowAdded: (row) => {
			row.select();
		},
		cellEditing: function (cell) {
			let field = cell.getField();
			if (field === "txtrisk_no_1") {
				//修改時，不可修改同險編號
				if(tableMode === "edit"){
					alert("同險編號不可修改")
				}
				$(cell.getElement()).on("input", function (e) {
					let val = e.target.value;
					checkAlert(val);
				})
			}
		},
		cellEdited: function (cell) {
			let field = cell.getField()
			//將同險編號的前七碼帶到地段代碼
			if (field === "txtrisk_no_1") {
                	
				let sevenStr = cell.getValue().slice(0, 7);

				cell.getRow().getCell("txtarea_code").setValue(sevenStr);
			}
		},
		rowSelectionChanged: function (data, rows) {

			if (!rowSelectionChangeCheck) {
				return;
			}
   
			if (tableMode !== null) {
        
				if (data.length === 1) {
                
					let selectRowIndex = data[0].id;
					if (selectRowIndex === editableRowIndex) {             
						return;
					} else {
						rowSelectionChangeCheck = false;
						alert("請完成編輯");
						table1107.deselectRow();
						table1107.selectRow(editableRowIndex);
						rowSelectionChangeCheck = true;
						return;
					}
				} else if (data.length !== 1) {
					rowSelectionChangeCheck = false;
					alert("請完成編輯");
					table1107.deselectRow();
					table1107.selectRow(editableRowIndex);
					rowSelectionChangeCheck = true;
					return;
				}
			}
		}
	}

//表格生成
let table1107 = createTable("table1107", colsFormat, tableConfigs)


// 取消鈕
function cancel() {
	while (table1107.getHistoryUndoSize() > 0) {
		table1107.undo()
	}
    elementsChangeClass("#add, #edit, #del, #copy", "remove", "btn-hide")
    elementsChangeClass("#save, #cancel", "add", "btn-hide")
    let editableDatas = table1107.getRows().filter(row => { return row.getData()._edit })
    editableDatas.forEach(row => {
    	let rowData = row.getData()
    	rowData._edit = false
    	$(row.getElement()).removeClass("table-editable")

    	row.getCells().map(cell => {
    		let editable = !!cell.getColumn().getDefinition().editable;
    		if (editable) {
    			elementsChangeClass(cell.getElement(), "remove", "cell-editable")
    		}
    	})
    });
    tableMode = null;
    rowSelectionChangeCheck = false;
    table1107.deselectRow();
}

// 新增鈕
function add() {
	if (tableMode !== null) {
		alert("請先完成編輯")
		return
	}
	table1107.deselectRow()
	elementsChangeClass("#add, #edit, #del, #copy", "add", "btn-hide")
	elementsChangeClass("#save, #cancel", "remove", "btn-hide")
	table1107.clearHistory()
	let newDataId = table1107.getDataCount()
	editableRowIndex = newDataId
	table1107.addData([{ edit: true, id: newDataId }], true);

	table1107.getSelectedRows().forEach(row => {
		let rowData = row.getData()
		$(row.getElement()).addClass("table-editable")

		row.getCells().map(cell => {
			let editable = !!cell.getColumn().getDefinition().editable;
			if (editable) {
				elementsChangeClass(cell.getElement(), "add", "cell-editable")
			}
		})
	});
	tableMode = "add"
	rowSelectionChangeCheck = true
}

// 修改鈕
function edit(cell) {

	let selectRows = table1107.getSelectedRows()
	if (selectRows.length === 0 || selectRows.length > 1) {
		table1107.deselectRow()
		alert("請選取一筆")
		return
	}
	elementsChangeClass("#add, #edit, #del, #copy", "add", "btn-hide")
	elementsChangeClass("#save, #cancel", "remove", "btn-hide")
	table1107.clearHistory()

	selectRows.forEach(row => {
		editableRowIndex = row.getIndex()
		let rowData = row.getData()
		row.getData()._edit = true
		$(row.getElement()).addClass("table-editable")
                
		editableRawData = row.getCells().map(cell => {
			let editable = !!cell.getColumn().getDefinition().editable;
			if (editable) {
				elementsChangeClass(cell.getElement(), "add", "cell-editable")
				return cell.getValue()
			}
		})
		editableRawData = editableRawData.filter(data => { return data !== undefined })
	});
	tableMode = "edit"
	rowSelectionChangeCheck = true
}

        
        
// 儲存鈕
function save() {
    
	//檢核欄位是否為空與是否處於警告狀態(檢核未過)
	let validation = table1107.getSelectedRows()[0].getCells().every((cell,index) => {
     			
		if(/[0]/g.test(index)){
			return true;
		}
		
		let flag = !checkIsNullSpace(cell.getValue().trim()) && cell.isValid();
		
		if(!flag){
			cell.getElement().click()
		}
		return flag;
	}) 
	
	if (!validation) {
		alert("所有資料欄位不可為空")
		return
	}

        	
	let selectRows = table1107.getSelectedRows()
	let dataEdited = false
	
	if (tableMode === "edit") {
		// 比較數值是否有修改，若有修改 dataEdited = true
		let editableData = selectRows[0].getCells().map(cell => {
			let editable = !!cell.getColumn().getDefinition().editable;
			if (editable) {
				elementsChangeClass(cell.getElement(), "remove", "cell-editable")
				return cell.getValue()
			}
		})
		editableData = editableData.filter(data => { return data !== undefined })

		for (let i = 0; i < editableData.length; i++) {
			if (editableData[i] !== editableRawData[i]) {
				dataEdited = true
				break
			}
		}
	} else if (tableMode === "add") {
            	
		let param = table1107.getSelectedData()[0];
		let res = ajaxPostTokenReady("../../rin1107api/insertriskbypk", param, false);
                
		if("999" === res.status){
			alert(res.message)
			return;
		}else{           		
			alert("新增成功")
		}
                
	}
            
	if (dataEdited) {

		let param = {
				"riskNo" 	:	table1107.getSelectedData()[0].txtrisk_no_1,	//同險編號
				"riskName" 	:	table1107.getSelectedData()[0].txtrisk_name,	//同險名稱
				"areaCode" 	:	table1107.getSelectedData()[0].txtarea_code		//地段代碼
		}

		let res = ajaxPostTokenReady("../../rin1107api/updaterisk", param, false);

		if("999" === res.status){
			alert("同險資料修改失敗，請聯絡管理人員")
			return;
		}else{           		
			alert("修改成功")
		}
	}

	// save success
	selectRows.forEach(row => {
		let rowData = row.getData()
		rowData._edit = false
		$(row.getElement()).removeClass("table-editable")
		row.getCells().map(cell => {
			let editable = !!cell.getColumn().getDefinition().editable;
			if (editable) {
				elementsChangeClass(cell.getElement(), "remove", "cell-editable")
			}
		})
	});

	elementsChangeClass("#add, #edit, #del, #copy", "remove", "btn-hide")
	elementsChangeClass("#save, #cancel", "add", "btn-hide")
	tableMode = null
	rowSelectionChangeCheck = false            
	table1107.deselectRow();  
}

        
// 刪除鈕
function del() {
	let selectRows = table1107.getSelectedRows()
	if (selectRows.length === 0) {
		alert("請選取至少一筆")
		return
	}
	let delConfirm = confirm('是否確認刪除？')

	if (delConfirm) {
		// 取出選取的資料
		let rowsDataArry = table1107.getSelectedData()
                
		let paramArry ='';
                
		for(let i = 0; i < rowsDataArry.length ; i++){
			if(i === (rowsDataArry.length-1)){
				paramArry += rowsDataArry[i].txtrisk_no_1;
			}else{
				paramArry += rowsDataArry[i].txtrisk_no_1;
				paramArry += ",";
			}
		}
		

		let param = {
				"noList" :JSON.stringify(paramArry)
		}
     
		let res = ajaxPostTokenReady("../../rin1107api/deleterisk", param, false);

		if("000" === res.status){
                	
			// 確認成功刪除後，將畫面上的表格資料刪除
			selectRows.forEach(row => {
				let rowData = row.delete()
			});
			alert("刪除完成")
			table1107.clearHistory()
		}else{
			alert("同險資料刪除失敗，請聯絡管理員")
		}
	}
	table1107.deselectRow();  
}

        
// 複製鈕
function copy() {
	let selectRows = table1107.getSelectedRows()
	if (selectRows.length !== 1) {
		table1107.deselectRow()
		alert("請選取一筆欲複製資料")
		return
	}
	
	let rowsDataArry = table1107.getSelectedData()
	selectRows[0].deselect()
	elementsChangeClass("#add, #edit, #del, #copy", "add", "btn-hide")
	elementsChangeClass("#save, #cancel", "remove", "btn-hide")
	table1107.clearHistory()
	let newDataId = table1107.getDataCount()
	editableRowIndex = newDataId
	table1107.addData([{ ...rowsDataArry[0], edit: true, id: newDataId }], true);

	table1107.getSelectedRows().forEach(row => {
		let rowData = row.getData()
		row.getData()._edit = true
		$(row.getElement()).addClass("table-editable")

		row.getCells().map(cell => {
			let editable = !!cell.getColumn().getDefinition().editable;
			if (editable) {
				elementsChangeClass(cell.getElement(), "add", "cell-editable")
			}
		})
	});
	
	tableMode = "add"
	rowSelectionChangeCheck = true
}

        
// 按鈕狀態變更
function elementsChangeClass(elements, behavior, classNames) {

	if (behavior === "add") {
		$(elements).each(function (index, element) {
			$(element).addClass(classNames)
		})
	} else if (behavior === "remove") {
		$(elements).each(function (index, element) {
			$(element).removeClass(classNames)
		})
	}
}





/**
 * 「同險編號」搜尋
 * 
 * @author yiting 2021/09/30
 */
function btnQueryRin1107(){

	//判別表格是否在編輯狀態
	if (tableMode !== "add" && tableMode !== "edit") {
		// 1-參數
		let param = {
				"riskNo" : $('#txtrisk_no').val().trim()   // 同險編號
		}
	
		// 2-執行查詢
		ajaxRequestIsAsyncDynimicBytoken("../../rin1107api/queryrisklist", true, false, param,     
				(res) => {
							if (res != null && "000" === res.status) {
//								table1107.setData(res.data);
								loadData("table1107", res.data, {type:"dataCount", value:7})
								table1107.getRows().forEach((row, index) => {
					                let rowData = row.getData()
					                rowData._edit = false
					                rowData.id = index  // table定位需使用id
					            });
					
					            elementsChangeClass("#edit, #del, #copy", "remove", "btn-hide")
					
					
					            let displayRowsNum = 15
					            if (res.data.length > displayRowsNum) {
					                // 資料有15筆以上時，固定表格高度
					                let headerHeight = table1107.element.children[0].offsetHeight
					                let rowHeight = table1107.getRows()[0].getElement().offsetHeight
					                let newHeight = headerHeight + rowHeight * displayRowsNum
					                table1107.setHeight(`${newHeight}px`)
					            } else {
					            	//
					            	table1107.setHeight(`100%`)
					            }
							}else{
								alert("「同顯編號查詢」失敗!!!請聯絡管理人員!!!");
							}
				}, (error) => {
								console.log(error);
								alert("「同顯編號查詢」失敗!!!請聯絡管理人員!!!");
				})
	}else{
		 rowSelectionChangeCheck = false
         alert("請完成編輯")
         table1107.deselectRow()
         table1107.selectRow(editableRowIndex)
         rowSelectionChangeCheck = true
         return
	}
}

//將資料放入表格中
function loadData(tableId, data, heightConfig) {
	let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
	let tableSorter = table.getSorters()
	table.clearSort()
	//載入資料
	return table.setData(data).then(()=>{

		elementsChangeClass(`#${tableId}-add, #${tableId}-edit, #${tableId}-copy, #${tableId}-del`, "remove", "btn-hide")
	
		let heightType = heightConfig.type
		if (heightType === "height") {
			table.setHeight(heightConfig.value)
		} else if (heightType === "dataCount") {
			let displayRowsNum = heightConfig.value
			if (data.length > displayRowsNum) {
				//根據載入資料數量調整table高度, 無資料(length===0)時建議 height="100%"
	
				//假設行高是固定的
				let headerHeight = table.element.children[0].offsetHeight
				let rowHeight = table.getRows()[0].getElement().offsetHeight
				let newHeight = headerHeight + rowHeight * displayRowsNum
				table.setHeight(`${newHeight}px`)
				table.setSort(tableSorter)
			} else {
				table.setHeight("100%")
			}
	
		}
	})
}
