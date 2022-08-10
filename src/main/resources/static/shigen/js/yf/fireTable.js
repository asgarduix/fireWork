function createTable(tableId, columns, tableConfigs, tableRelatedBtns, alertConfig) {
	// tableId(String): HTML element id. ex: <div id="table1"> tableId="table1"
	// columns(Array): tabulator formatted columns. use createTableColumns.js or design yourself
	// tableConfigs(Object): any tabulator params. replace any default config ex: { height:"500px" }
	// tableRelatedBtns(Array):
	// alertConfig(Object):

	let tableConfigsDefault = {
		height: "100%", //根據資料行高設定, 100%為資料全展開 無scroll bar, 建議根據資料筆數再用 .setHeight("450px")重設
		layout: "fitColumns",   //尚有其他格式 參考 http://tabulator.info/docs/4.9/layout
		selectable: true,   //selectable與selectableRangeMode為 shift多選的用法
		selectableRangeMode: "click",
		history: true, // 可還原上一步的修改紀錄 .undo()
		reactiveData: true,
		columnHeaderVertAlign: "center",
		columns: columns,	//表格欄位設定，由外部傳入
		pagination: false,	//設定為"local"即可開啟
		paginationSize: 200,
		paginationSizeSelector: [200],
		paginationButtonCount: 5,
		paginationAddRow: "page",   // page 為新增於該頁, table 新增於第一頁
		placeholder: false, //display message to user on empty table
		validationMode: "highlight",
		dataSorted: function (sorters, rows) {
			$(`#${tableId}-pagination .curPageNum`).val(1)
		},
		rowAdded: function (row) {
			row.select();
		},
		rowSelected: function (row) {
		},
		rowDeselected: function (row) {
		},
		rowSelectionChanged: function (data, rows) {
			//可以抓到shift的選取事件
			let table = this
			if (!table.rowSelectionChangeCheck) {
				return
			}
			if (table.tableMode !== null) {
				if (data.length === 1) {
					let selectRowIndex = data[0].id
					if (selectRowIndex === table.editableRowIndex) {
						return
					} else {
						table.rowSelectionChangeCheck = false
						table.errMsg = alertMsg.editing
						tableAlert("error")
						table.deselectRow()
						table.selectRow(table.editableRowIndex)
						table.rowSelectionChangeCheck = true
						return
					}
				} else if (data.length !== 1) {
					table.rowSelectionChangeCheck = false
					table.errMsg = alertMsg.editing
					tableAlert("error")
					table.deselectRow()
					table.selectRow(table.editableRowIndex)
					table.rowSelectionChangeCheck = true
					return
				}
			}
		},
		rowDeleted: function (row) {
		},
		rowClick: function (e, row) {
		},
		rowMouseOver: function (e, row) {
		},
		rowMouseLeave: function (e, row) {
		},
		cellEditing: function (cell) {
			let el = cell.getElement()
			elementsChangeClass(el, "remove", "table-error")
			$(el).off("focusout").off("input")
		},
		cellEdited: function (cell) {
		}
	}

	let alertMsg = {
		editing: "請完成編輯",
		getAddDefData: "請求預設值失敗",
		overSelect: "請選擇一筆資料",
		emptySelect: "請選取欲刪除的資料",
		delConfirm: "是否確定刪除?",
		delSuccess: "刪除成功",
		saveConfirm: "是否確定儲存?",
		saveAddSuccess: "新增成功",
		saveEditSuccess: "修改成功",
		// beforeNullCol: "欄位：",
		// afterNullCol: "，不可為空",
		// beforeCustomCheck: "格式錯誤：",
		// afterCustomCheck: "。",
		beforeValidation: "驗證：",
		afterValidation: "。",
	}


	function creatPagination(tableId) {
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
		//--------製作tabulator-pagination，tabulator原生的pagination由CSS隱藏--------
		if (tableConfigsDefault.pagination) {
			$(`#${tableId}`).before(`<div id="${tableId}-pagination"></div>`)
			let paginationHTML = `<div class="tabulator-footer">
	<div class="tabulator-paginator">
		<div class="tabulator-page"><button title="First Page" class="first" name="first">
				⊼
			</button></div>
		<div class="tabulator-page"><button title="Prev Page" name="prev">&lt;</button>
		</div>
		<div class="tabulator-page"><button title="Next Page" name="next">&gt;</button>
		</div>
		<div class="tabulator-page"><button title="Last Page" class="last" name="last"> ⊼
			</button></div>
	</div>
	<div>總共 <span class="dataNum">0</span> 筆</div>
	<div>每頁 <select class="dpPerPageNum"></select> 筆</div>
	<div>總共 <span class="pageNum">0</span> 頁</div>
	<div>
		<div>目前第</div>
		<div>
			<select class="curPageNum">
			</select>
		</div>
		<div>頁</div>
	</div>
</div>`

			//將標籤寫入
			$(`#${tableId}-pagination`).html(paginationHTML)

			//綁定換頁按鈕事件
			$(`#${tableId}-pagination .tabulator-page button`).each(function (index, element) {
				$(element).on("click", function (e) {
					btnChangePage(e.target.name)
				})
			})

			//製作每頁幾筆的選項
			let perPageSizeOptsStr = tableConfigsDefault.paginationSizeSelector.map((num) => {
				return `<option value="${num}">${num}</option>`
			}).join("")
			$(`#${tableId}-pagination .dpPerPageNum`).empty().html(perPageSizeOptsStr)

			//select change事件 換每頁筆數
			$(`#${tableId}-pagination .dpPerPageNum`).on("change", function (e) {
				perPageSizeChange(e.target.value)
			})

			//select change事件 換頁
			$(`#${tableId}-pagination .curPageNum`).on("change", function (e) {
				curPageChange(e.target.value)
			})

			function perPageSizeChange(val) {
				$(`#${tableId}-pagination .curPageNum`).val(1)
				table.setPageSize(val)
				let tablePageMax = table.getPageMax()
				$(`#${tableId}-pagination .pageNum`).text(tablePageMax)

				let pageOptsStr = [...new Array(tablePageMax).keys()].map((index) => {
					return `<option value="${index + 1}">${index + 1}</option>`
				}).join("")
				$(`#${tableId}-pagination .curPageNum`).empty().html(pageOptsStr)
			}

			function curPageChange(val) {
				table.setPage(val)
			}

			function btnChangePage(type) {
				let tableCurPage = table.getPage()
				let tablePageMax = table.getPageMax()
				switch (type) {
					case "first":
						table.setPage(1)
						break
					case "next":
						if (tableCurPage < tablePageMax) {
							table.nextPage()
						}
						break
					case "prev":
						if (tableCurPage > 1) {
							table.previousPage()
						}
						break
					case "last":
						table.setPage(tablePageMax)
						break
				}
				$(`#${tableId}-pagination .curPageNum`).val(table.getPage())
			}
			//分頁模式，初始化筆數、頁數等數值
			initTableFoot(tableId)
		}
	}



	//----------------tableRelatedBtns---------------------
	function createTableBtns(btnConfig) {
		let btnType = btnConfig.type
		let btn = document.createElement('button');

		switch (btnType) {
			case "add":
				$(btn).text(btnConfig.name).attr("id", `${tableId}-add`)
				elementsChangeClass(btn, "add", btnConfig.class)
				btn.addEventListener("click", () => { add(tableId, btnConfig.getDefaultData) });
				break;
			case "cancel":
				$(btn).text(btnConfig.name).attr("id", `${tableId}-cancel`)
				elementsChangeClass(btn, "add", "btn-hide")
				elementsChangeClass(btn, "add", btnConfig.class)
				btn.addEventListener("click", () => { cancel(tableId) });
				break;
			case "edit":
				$(btn).text(btnConfig.name).attr("id", `${tableId}-edit`)
				elementsChangeClass(btn, "add", "btn-hide")
				elementsChangeClass(btn, "add", btnConfig.class)
				btn.addEventListener("click", () => { edit(tableId) });
				break;
			case "copy":
				$(btn).text(btnConfig.name).attr("id", `${tableId}-copy`)
				elementsChangeClass(btn, "add", "btn-hide")
				elementsChangeClass(btn, "add", btnConfig.class)
				btn.addEventListener("click", () => { copy(tableId) });
				break;
			case "del":
				$(btn).text(btnConfig.name).attr("id", `${tableId}-del`)
				elementsChangeClass(btn, "add", "btn-hide")
				elementsChangeClass(btn, "add", btnConfig.class)
				btn.addEventListener("click", () => { del(tableId, btnConfig.delApi) });
				break;
			case "save":
				$(btn).text(btnConfig.name).attr("id", `${tableId}-save`)
				elementsChangeClass(btn, "add", "btn-hide")
				elementsChangeClass(btn, "add", btnConfig.class)
				btn.addEventListener("click", () => { save(tableId, btnConfig.nullSpaceCheck, btnConfig.addSaveApi, btnConfig.editSaveApi, btnConfig.rules) });
				btnConfig.nullSpaceCheck && setNullSpaceCheckRule()
				Object.keys(btnConfig.rules).length && setCustomCheckRule(btnConfig.rules)
				break;
			case "custom":
				$(btn).text(btnConfig.name).attr("id", `${tableId}-${btnConfig.id}`)
				elementsChangeClass(btn, "add", btnConfig.class)
				btn.addEventListener("click", () => { custom(tableId, btnConfig.fn) });
				break;
		}
		$(btnConfig.position).append(btn)
	}

	function add(tableId, getDefaultData) {
		tableAlert("empty")
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
		if (table.tableMode !== null) {
			table.errMsg = alertMsg.editing
			tableAlert("error")
			return
		}
		$(`#${tableId} .tabulator-tableHolder`).scrollTop(0)
		let apiRespond = getDefaultData()
		if (!apiRespond.isSuccess) {
			table.errMsg = alertMsg.getAddDefData
			tableAlert("error")
			return
		}
		let defaultData = apiRespond.data
		table.deselectRow()
		elementsChangeClass(`#${tableId}-edit, #${tableId}-copy, #${tableId}-del`, "add", "btn-hide")
		elementsChangeClass(`#${tableId}-cancel, #${tableId}-save`, "remove", "btn-hide")
		table.clearHistory()
		let newDataId = table.getDataCount()
		table.editableRowIndex = newDataId
		setTimeout(() => {
			table.addData([{ ...defaultData, edit: true, id: newDataId }], true);

			table.getSelectedRows().forEach(row => {
				$(row.getElement()).addClass("table-editable")

				row.getCells().reverse().forEach(cell => {
					let editable = !!cell.getColumn().getDefinition().editable; // 未找到更明確的 欄位可編輯 標誌
					if (editable) {
						let el = cell.getElement()
						elementsChangeClass(el, "add", "cell-editable")
						el.click()
						setTimeout(() => {
							elementsChangeClass(el, "remove", "table-error tabulator-validation-fail")
						}, 10)
					}
				})
				row.getElement().click()
			});
			$(`#${tableId} .tabulator-tableHolder`).scrollLeft(0)
			$(`#${tableId} .tabulator-tableHolder`).scrollTop(0)
		}, 10)
		table.tableMode = "add"
		table.rowSelectionChangeCheck = true
	}

	function cancel(tableId) {
		tableAlert("empty")
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];

		while (table.getHistoryUndoSize() > 0) {
			table.undo()
		}
		elementsChangeClass(`#${tableId}-edit, #${tableId}-copy, #${tableId}-del`, "remove", "btn-hide")
		elementsChangeClass(`#${tableId}-cancel, #${tableId}-save`, "add", "btn-hide")
		let editableDatas = table.getRows().filter(row => { return row.getData().edit })
		editableDatas.forEach(row => {
			let rowData = row.getData()
			rowData.edit = false
			$(row.getElement()).removeClass("table-editable")

			row.getCells().map(cell => {
				if (!cell.isValid()) {  // 驗證未過的欄位不會被寫進table歷史紀錄 getHistoryUndoSize()
					cell.cancelEdit()
				}
				let editable = !!cell.getColumn().getDefinition().editable;
				if (editable) {
					elementsChangeClass(cell.getElement(), "remove", "cell-editable table-error")
				}
			})
		});
		table.tableMode = null
		table.rowSelectionChangeCheck = false
	}

	function edit(tableId) {
		tableAlert("empty")
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];

		let selectRows = table.getSelectedRows()
		if (selectRows.length === 0 || selectRows.length > 1) {
			table.deselectRow()
			table.errMsg = alertMsg.overSelect
			tableAlert("error")
			return
		}
		elementsChangeClass(`#${tableId}-edit, #${tableId}-copy, #${tableId}-del`, "add", "btn-hide")
		elementsChangeClass(`#${tableId}-cancel, #${tableId}-save`, "remove", "btn-hide")
		table.clearHistory()

		selectRows.forEach(row => {
			table.editableRowIndex = row.getIndex()
			row.getData().edit = true
			$(row.getElement()).addClass("table-editable")

			table.editableRawData = table.getSelectedRows()[0].getCells().reduce((arry, cell) => {
				let editable = !!cell.getColumn().getDefinition().editable;
				if (editable) {
					elementsChangeClass(cell.getElement(), "add", "cell-editable")
					return [...arry, cell.getValue()]
				} else {
					return arry
				}
			}, [])
		});
		table.tableMode = "edit"
		table.rowSelectionChangeCheck = true
	}

	function copy(tableId) {
		tableAlert("empty")
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
		let selectRows = table.getSelectedRows()
		if (selectRows.length !== 1) {
			table.deselectRow()
			table.errMsg = alertMsg.overSelect
			tableAlert("error")
			return
		}
		$(`#${tableId} .tabulator-tableHolder`).scrollTop(0)
		let rowsDataArry = table.getSelectedData()
		selectRows[0].deselect()
		elementsChangeClass(`#${tableId}-edit, #${tableId}-copy, #${tableId}-del`, "add", "btn-hide")
		elementsChangeClass(`#${tableId}-cancel, #${tableId}-save`, "remove", "btn-hide")
		table.clearHistory()
		let newDataId = table.getDataCount()
		table.editableRowIndex = newDataId
		setTimeout(() => {
			table.addData([{ ...rowsDataArry[0], edit: true, id: newDataId }], true);

			table.getSelectedRows().forEach(row => {
				$(row.getElement()).addClass("table-editable")

				row.getCells().forEach(cell => {
					let editable = !!cell.getColumn().getDefinition().editable;
					if (editable) {
						elementsChangeClass(cell.getElement(), "add", "cell-editable")
					}
				})
			});
			$(`#${tableId} .tabulator-tableHolder`).scrollLeft(0)
			$(`#${tableId} .tabulator-tableHolder`).scrollTop(0)
		}, 10)
		table.tableMode = "add"
		table.rowSelectionChangeCheck = true
	}

	function del(tableId, delApi) {
		tableAlert("empty")
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
		let selectRows = table.getSelectedRows()
		if (selectRows.length === 0) {
			table.errMsg = alertMsg.emptySelect
			tableAlert("error")
			return
		}
		let delConfirm = confirm(alertMsg.delConfirm)

		if (delConfirm) {
			//取出選取的資料 
			let rowsDataArry = table.getSelectedData()
			let apiRespond = delApi(rowsDataArry)
			if (apiRespond.isSuccess) {
				//確認成功刪除後，將表格資料刪除
				selectRows.forEach(row => {
					row.delete()
				});
				table.errMsg = alertMsg.delSuccess
				tableAlert("success")
				table.clearHistory()
			} else {
				table.errMsg = apiRespond.errMsg
				tableAlert("error")
			}

		}

	}

	function save(tableId, nullSpaceCheck, addSaveApi, editSaveApi, rules) {
		tableAlert("empty")
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
		table.errMsg = ""
		let inputCells = table.getSelectedRows()[0].getCells().filter(cell => {
			return !!cell.getColumn().getDefinition().editable;
		})
		let inputCellsData = inputCells.map(cell => { return cell.getValue() })

		let errMsg = inputCells.reduce((arry, cell) => {
			let val = cell.getValue()
			let field = cell.getField()
			let el = cell.getElement()
			let validation = cell.isValid()
			let colTitle = cell.getColumn().getDefinition().title
			let msg = ""
			elementsChangeClass(el, "remove", "table-error")
			if (nullSpaceCheck && isNullSpace(val)) {
				msg = colTitle + "不可為空"
			} else if (!validation) {
				msg = colTitle + "格式錯誤(Tabulator)"
			} else if (rules[field]) {
				if (val === undefined || !rules[field](val)) {
					msg = colTitle + "格式錯誤(Costum)"
				}
			}

			if (msg === "") {
				return arry
			} else {
				elementsChangeClass(el, "add", "table-error")
				return [...arry, msg]
			}
		}, [])

		table.errMsg = errMsg.join(", ")
		if (table.errMsg !== "") {
			table.errMsg = `${alertMsg.beforeValidation}${table.errMsg}${alertMsg.afterValidation}`
			tableAlert("error")
			return
		}

		// 空值檢驗
		// if (nullSpaceCheck) {
		// 	let errMsg = []
		// 	inputCells.forEach(cell => {
		// 		let val = cell.getValue()
		// 		elementsChangeClass(cell.getElement(), "remove", "table-error")
		// 		if (isNullSpace(val)) {
		// 			elementsChangeClass(cell.getElement(), "add", "table-error")
		// 			let colTitle = cell.getColumn().getDefinition().title
		// 			errMsg.push(colTitle)
		// 		}
		// 	});
		// 	table.errMsg = errMsg.join(", ")
		// 	if (table.errMsg !== "") {
		// 		table.errMsg = `${alertMsg.beforeNullCol}${table.errMsg}${alertMsg.afterNullCol}`
		// 		tableAlert("error")
		// 		return
		// 	}
		// }
		// 自定義檢驗
		// let fields = Object.keys(rules)
		// if (fields.length > 0) {
		// 	let errMsg = []
		// 	inputCells.forEach(cell => {
		// 		let val = cell.getValue()
		// 		let field = cell.getField()
		// 		elementsChangeClass(cell.getElement(), "remove", "table-error")
		// 		if (rules[field]) {
		// 			if (val === undefined || !rules[field](val)) {
		// 				elementsChangeClass(cell.getElement(), "add", "table-error")
		// 				let colTitle = cell.getColumn().getDefinition().title
		// 				errMsg.push(colTitle)
		// 			}
		// 		}
		// 	});
		// 	table.errMsg = errMsg.join(", ")
		// 	if (table.errMsg !== "") {
		// 		table.errMsg = `${alertMsg.beforeCustomCheck}${table.errMsg}${alertMsg.afterCustomCheck}`
		// 		tableAlert("error")
		// 		return
		// 	}
		// }

		let dataJson = table.getSelectedData()[0]
		let saveConfirm = confirm(alertMsg.saveConfirm)
		if (!saveConfirm) {
			return
		}
		let apiRespond
		if (table.tableMode === "edit") {
			apiRespond = editSaveApi(table.editableRawData, inputCellsData, dataJson)
			table.errMsg = alertMsg.saveEditSuccess
		} else if (table.tableMode === "add") {
			apiRespond = addSaveApi(dataJson)
			table.errMsg = alertMsg.saveAddSuccess
		}

		let selectRow = table.getSelectedRows()[0]
		if (apiRespond.isSuccess) {
			tableAlert("success")
			let rowData = selectRow.getData()
			rowData.edit = false
			elementsChangeClass(selectRow.getElement(), "remove", "table-editable")
			inputCells.forEach(cell => {
				elementsChangeClass(cell.getElement(), "remove", "cell-editable")
			})

			elementsChangeClass(`#${tableId}-edit, #${tableId}-copy, #${tableId}-del`, "remove", "btn-hide")
			elementsChangeClass(`#${tableId}-cancel, #${tableId}-save`, "add", "btn-hide")
			table.tableMode = null
			table.rowSelectionChangeCheck = false
		} else {
			table.errMsg = apiRespond.errMsg
			tableAlert("error")
			apiRespond.fields.forEach(field => {
				let cell = selectRow.getCell(field)
				elementsChangeClass(cell.getElement(), "add", "table-error")
			})

		}

	}

	function custom(tableId, customFn) {
		let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
		customFn(table)
	}

	function setNullSpaceCheckRule() {
		// 若save btn有開啟nullSpaceCheck，才會導入此設定
		// 若需要使用此事件，需注意覆寫
		let cellEditing = tableConfigsDefault.cellEditing
		let cellEdited = tableConfigsDefault.cellEdited
		tableConfigsDefault = {
			...tableConfigsDefault,
			cellEditing: function (cell) {
				cellEditing(cell)
				let el = cell.getElement()

				elementsChangeClass(el, "remove", "table-error")

				$(el).on("focusout", function (e) {
					let val = e.target.value
					if (val === undefined) {
						return
					}
					if (val.length === 0) {
						elementsChangeClass(el, "add", "table-error")
					} else {
						elementsChangeClass(el, "remove", "table-error")
					}
				})
				$(el).on("input", function (e) {
					let val = e.target.value
					if (val.length === 0) {
						elementsChangeClass(el, "add", "table-error")
					} else {
						elementsChangeClass(el, "remove", "table-error")
					}
				})

			},
			cellEdited: function (cell) {
				cellEdited(cell)
				let val = cell.getValue()
				if (val.length === 0) {
					elementsChangeClass(cell.getElement(), "add", "table-error")
				} else {
					elementsChangeClass(cell.getElement(), "remove", "table-error")
				}
			}
		}
	}

	function setCustomCheckRule(rules) {
		let cellEditing = tableConfigsDefault.cellEditing
		let cellEdited = tableConfigsDefault.cellEdited
		tableConfigsDefault = {
			...tableConfigsDefault,
			cellEditing: function (cell) {
				cellEditing(cell)
				let field = cell.getField()
				if (rules[field]) {
					let el = cell.getElement()
					$(el).on("focusout", function (e) {
						let val = e.target.value
						if (val === undefined) {
							return
						}
						// 使用setTimeout確保會於空值檢核的事件結束後才執行
						setTimeout(() => {
							if (rules[field](val)) {
								elementsChangeClass(el, "remove", "table-error")
							} else {
								elementsChangeClass(el, "add", "table-error")
							}
						}, 0)

					})

					$(el).on("input", function (e) {
						let val = e.target.value
						setTimeout(() => {
							if (rules[field](val)) {
								elementsChangeClass(el, "remove", "table-error")
							} else {
								elementsChangeClass(el, "add", "table-error")
							}
						}, 0)
					})
				}
			},
			cellEdited: function (cell) {
				cellEdited(cell)
				let val = cell.getValue()
				let field = cell.getField()
				if (rules[field]) {
					setTimeout(() => {
						if (rules[field](val)) {
							elementsChangeClass(cell.getElement(), "remove", "table-error")
						} else {
							elementsChangeClass(cell.getElement(), "add", "table-error")
						}
					}, 0)
				}
			}
		}
	}

	function tableAlert(mode) {
		let alertType = alertConfig.type
		let alertDomId = alertConfig.position
		let alertShowTime = alertConfig.displayTime
		if (mode === "empty") {
			table.errMsg = ""
			$(alertDomId).text("")
			elementsChangeClass(alertDomId, "remove", "table-message-success table-message-error")
			return
		}
		let msgTime = new Date()
		table.msgTime = msgTime

		switch (alertType) {
			case "alert":
				alert(table.errMsg)
				break
			case "dom":
				if (mode === "success") {
					elementsChangeClass(alertDomId, "add", "table-message-success")
				} else {
					elementsChangeClass(alertDomId, "add", "table-message-error")
				}
				$(alertDomId).text(table.errMsg)
				break
		}
		if (alertShowTime !== 0) {
			setTimeout(() => {
				let timeGap = table.msgTime - msgTime
				if (timeGap === 0) {
					tableAlert("empty")
				}
			}, alertShowTime)
		}
	}

	//製作tabulator相關button
	tableRelatedBtns.forEach(btnConfig => { createTableBtns(btnConfig) })

	if ("cellEdited" in tableConfigs) {
		let cellEdited = tableConfigsDefault.cellEdited
		let customCellEdited = tableConfigs.cellEdited
		tableConfigsDefault = {
			...tableConfigsDefault,
			cellEdited: function (cell) {
				cellEdited(cell)
				customCellEdited(cell)
			}
		}
		delete tableConfigs.cellEdited
	}

	if ("cellEditing" in tableConfigs) {
		let cellEditing = tableConfigsDefault.cellEditing
		let customCellEditing = tableConfigs.cellEditing
		tableConfigsDefault = {
			...tableConfigsDefault,
			cellEditing: function (cell) {
				cellEditing(cell)
				customCellEditing(cell)
			}
		}
		delete tableConfigs.cellEditing
	}


	//更新外部傳入設定
	tableConfigsDefault = { ...tableConfigsDefault, ...tableConfigs }

	//製作tabulator本體
	let table = new Tabulator(`#${tableId}`, tableConfigsDefault);
	table.tableMode = null
	table.editableRowIndex = null
	table.editableRawData = null
	table.rowSelectionChangeCheck = false
	table.errMsg = ""
	table.msgTime = new Date()

	if (alertConfig.type === "dom") {
		elementsChangeClass(alertConfig.position, "add", "table-message")
	}

	// 製作agination，若沒開啟則不會製作
	creatPagination(tableId)

	return table
}


function initTableFoot(tableId) {
	let table = Tabulator.prototype.findTable(`#${tableId}`)[0];
	table.setPage(1)
	let tableDataNum = table.getDataCount()
	let tablePageSize = table.getPageSize()
	let tableCurPage = table.getPage()
	let tablePageMax = table.getPageMax()
	$(`#${tableId}-pagination .dataNum`).text(tableDataNum)
	$(`#${tableId}-pagination .dpPerPageNum`).val(tablePageSize)
	$(`#${tableId}-pagination .pageNum`).text(tablePageMax)

	// 製作頁數選項
	$(`#${tableId}-pagination .curPageNum`).val(tableCurPage)
	let pageOptsStr = [...new Array(tablePageMax).keys()].map((index) => {
		return `<option value="${index + 1}">${index + 1}</option>`
	}).join("")
	$(`#${tableId}-pagination .curPageNum`).empty().html(pageOptsStr)
}

function elementsChangeClass(elements, behavior, classNames) {
	// 使用方式
	// elements: 可接受格式 
	//      1. "#add, #edit, #del"  // CSS selector字串
	//      2. $("#add, #edit, #del")   // jQuery格式元素
	//      3. document.querySelectorAll("#add, #edit, #del")   // 原生javascript格式元素
	// behavior: "add" or "remove"
	// classNames: "btn-hide otherclass1 otherclass2" 中間以空白間隔

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

function isNullSpace(val) {
	if (!val?.length && typeof (val) !== "number") {
		return true
	} else {
		if (typeof (val) === "number" && isNaN(val)) {
			return true
		}
		return false
	}
}

function loadData(tableId, data, heightConfig) {
	let table = Tabulator.prototype.findTable(`#${tableId}`)[0];

	//載入資料
	table.setData(data)
	table.getRows().forEach((row, index) => {
		let rowData = row.getData()
		rowData.edit = false
		rowData.id = index  //table定位需使用id
	});

	elementsChangeClass(`#${tableId}-edit, #${tableId}-copy, #${tableId}-del`, "remove", "btn-hide")

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
		} else {
			table.setHeight("100%")
		}

	}

}

