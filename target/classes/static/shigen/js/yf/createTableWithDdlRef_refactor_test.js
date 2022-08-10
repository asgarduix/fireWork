


/**
*TODO 表格中按鈕定義可以動態使用tabu.setColumnLayout(tabu.getColumnDefinitions())實做，或許可以全部改造
 */

var btnmethods = {};
var colDefines = [];
var _globalDest = null;
var _tabledata = [];
var _pagination = null;
var _table = null;

//create table function, takes colDefines([[fieldName, inputType [, selectParams(object) ]]...]) and tabledata
function createTableddlref(colDefineArray, tabledata, destination, btnmethodArray, showNo, statusBarBtnMethods) {
	col = {};
	btnmethods = btnmethodArray;
	_globalDest = destination;
	_tabledata = tabledata;

	for (let i = 0; i < colDefineArray.length; i++) {
		var colName = colDefineArray[i][0];
		var colShowName = colDefineArray[i][1];
		col[colName] = colShowName;
	}

	//先根據輸入的參數設定按鈕
	//目前僅支援copy、Edit、Cancel、save、delete、、、
	columns = defineCols(showNo, colDefineArray);

	//Build Tabulator
	var table = new Tabulator(`${destination}`, {
		data: tabledata,
		height: "100%",
		layout: "fitColumns",
		layoutColumnsOnNewData: true,
		pagination: "local",
		paginationSize: 5,
		paginationAddRow: "local",
		langs: {
			"ch-ch": { //Mandarin definition
				"columns": col,//根據輸入的欄位定義，產生欄位基本資料
				"pagination": {
					"first": "第一頁",
					"last": "最後一頁",
					"prev": "上一頁",
					"next": "下一頁"
				},
			}
		},
		selectable: false,
		reactiveData: true,
		columns: columns,
		rowAdded: function(row) {
			setTimeout(function() {
				currentRow = row;
				currentTable = row.getTable();
				currentTable.deselectRow()
				currentRow.select()
				currentRow.reformat()
				let cell = row.getCells()[0];
				stopEditing(cell);

				currentRow = cell.getRow();
				currentTable = cell.getTable();
				selectedRows = currentTable.getSelectedRows();

				//沒選取其他row才發動編輯
				if (selectedRows.length == 0) {
					for (i = 0; i < selectedRows.length; i++) {
						selectedRows[i].deselect();
						selectedRows[i].reformat();
					}
					currentTable.deselectRow();
					currentRow.select();
					currentRow.reformat();

					var scanerRes = scanObjPosiInRow(cell, "select");
					var selPosis = scanerRes.select;
					var otherPosis = scanerRes.other;

					//找到相關select欄位
					for (var a = selPosis.length - 1; a >= 0; a--) {
						var i = selPosis[a];
						cells[i].getElement().querySelectorAll("select")[0].disabled = false;
					}

					//略過相關select欄位
					for (var a = otherPosis.length - 1; a >= 0; a--) {
						var i = otherPosis[a];
						cells[i].setValue(cells[i].getValue());
					}

					// var cells = currentRow.getCells();
					//TODO 這邊還得要改進
					// for (i = 8; i < cells.length; i++) {
					// }
				}
				currentTable.hideColumn("EditButton");
				currentTable.hideColumn("DeleteButton");
				currentTable.showColumn("CancelButton");
				currentTable.showColumn("SaveButton");

				//status bar操作
				$(`${destination}-reactivityAdd`).attr("disabled", true);
				$(`${destination}-addToDb`).attr("disabled", true);
				$(`${destination}-reactivityAdd`).css("opacity", 0.2);
				$(`${destination}-addToDb`).css("opacity", 0.2);
				stylePaginationBtn();
				try {
					console.log("insert callback function here");
				} catch (e) {
					console.log(e)
				}
			}, 0.1);
		},
		pageLoaded: function(pageno) {
			//pageno - the number of the loaded page
			disableBtns();
			stylePaginationBtn();
			$(currentPageDiv).remove();
			createCurrentPage(table);
		}
	});

	_table = table;//設定為global待後續可使用
	createCurrentPage(table);

	//select pagination
	let pagination = $(destination).children(".tabulator-footer");
	_pagination = pagination;

	//add row to bottom of table on button click
	$(`${destination}-reactivityAdd`).on("click", function() {
		let dpPerPg = $(dpPerPageDiv).find("input").val();
		//table.addRow({ id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male", color:"black"});
		let pushData = {}
		for (let i = 0; i < colDefineArray.length; i++) {
			if (colDefineArray[i][2] === "select" || colDefineArray[i][2] === "input" || colDefineArray[i][2] === "selectCus") {
				pushData[colDefineArray[i][0]] = "";
			} else if (colDefineArray[i][2] === "number") {
				pushData[colDefineArray[i][0]] = 0;
			} else {
				pushData[colDefineArray[i][0]] = undefined;
			}

		}
		//		pushData["id"] = tabledata[tabledata.length - 1].id + 1;
		if (table.getPage() == table.getPageMax() && _tabledata.length % dpPerPg == 0) {
			//tabledata.push({ insType: "", insType1: "", insType2: "", id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male"});
			_tabledata.push(pushData);
			table.previousPage();
			table.nextPage();
			table.setPage("last");
		} else {
			table.setPage("last");
			_tabledata.push(pushData);
			//tabledata.push({ insType: "", insType1: "", insType2: "", id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male"});
		}
		table.hideColumn("EditButton");
		table.hideColumn("DeleteButton");
		table.showColumn("CancelButton");
		table.showColumn("SaveButton");

		//status bar操作
		$(`${destination}-reactivityAdd`).attr("disabled", true);
		$(`${destination}-addToDb`).attr("disabled", true);
		$(`${destination}-reactivityAdd`).css("opacity", 0.2);
		$(`${destination}-addToDb`).css("opacity", 0.2);

		// table.hideColumn("EditButton");
		// table.hideColumn("DeleteButton");
		// table.showColumn("CancelButton");
		// table.showColumn("SaveButton");
		// $(".tabulator-footer").hide();
	});

	//註冊除了新增資料的的按鈕事件
	//	statusBarBtnMethods
	for (var i = 0; i < statusBarBtnMethods.length; i++) {
		var id = statusBarBtnMethods[i].id;
		var func = statusBarBtnMethods[i].funcNm;
		$(id).on("click", func);
	}

	//save data to DB
	//	$(`${destination}-addToDb`).on("click", function() {
	//		//api call to save to db
	//		console.log(table.getData());
	//		console.log("Saved to database!");
	//	});
	//=====status bar中的按鈕操作_end=====

	//move pagination to the top of the table
	pagination.prependTo(`${destination}-pagination`)

	//status bar移動
	//move addRow button into pagination
	$(`${destination}-reactivityAdd`).prependTo(pagination);
	//style addRow button
	setTimeout(function() { $(`${destination}-reactivityAdd`).addClass("addRowBtn") }, 0.1)


	//move confirm button into pagination
	$(`${destination}-addToDb`).appendTo(pagination);
	//style confirm button
	setTimeout(function() { $(`${destination}-addToDb`).addClass("addToDbBtn") }, 0.1)

	//styling pagination
	pagination.css({
		"margin-bottom": "0.5rem",
		"margin-top": "1rem",
		"display": "flex",
		"flex-direction": "row",
		"align-items": "center",
		"background-color": "#dba0ac"
	});

	pagination.children(".tabulator-paginator").css({
		"margin-left": "2rem",
		"margin-right": "4rem"
	})

	initialLoad(table);
	return table;
}

/**
 */
function stylePaginationBtn() {
	_pagination.find("button").css({
		"margin-left": "0rem",
		"margin-right": "0rem",
		"background-color": "#dba0ac",
		"font-size": "1rem",
		"color": "#ffffff",

	})
}

//when page loads
function initialLoad(thisTable) {
	disableBtns();
	//styling pagination buttons
	stylePaginationBtn();
	createDataNum(thisTable);
	createDpPerPage(thisTable);
	createPageNum(thisTable);
	createCurrentPage(thisTable);
	thisTable.setLocale("ch-ch");
}

/**
*/
function renderedFunc4Url(cell, defineObj) {
	var aTag = document.createElement("a");

	try {
		var json = cell.getValue();

		var func = json.funcNm;
		var context = json.context;
		var val = json.funcParam;
		//設定參數
		var paramstr = "";

		//在tabulator的onrender中，迴圈使用須將function分出
		for (var i = 0; i < val.length; i++) {
			var sub = "\"" + val[i] + "\"" + ",";
			paramstr += sub;
		}

		var posi = paramstr.lastIndexOf(",");
		paramstr = paramstr.substring(0, posi);
		paramstr = "(" + paramstr + ")";

		var aTagContext = "<a href='#' onclick='" + func.name + paramstr + ";" + ";return false;'>" + context + "</a>";
		aTag = $(aTagContext)[0]

		cell.getElement().appendChild(aTag);
	} catch (err) {
		cell.getElement().appendChild(aTag);
	}
}

/**
 * 對應自製下拉選單的function
 * @param {*} cell 
 * @param {*} defineObj 
 */
function renderedFunc4Sel(cell, defineObj) {
	var cellVal = cell.getValue();

	//丟入參數已放入select物件狀況
	if (checkIsNullSpace(defineObj.context) == false) {
		var contextClone = $(defineObj.context).clone()[0];

		//要先cell內部元件後續再設定資料
		contextClone.disabled = true;
		cell.getElement().appendChild(contextClone);

		//總之先將設定的預設值設定進select
		if (defineObj.params != null && defineObj.params[0][1] == "fix") {
			var defVal = defineObj.params[0][0];//預設值理論上僅一個
			cell.getElement().querySelectorAll("select")[0].value = defVal;
			// return;
		}

		if (checkIsNullSpace(cellVal) == true) {
			// cell.getElement().appendChild(document.createElement("select"));
			// cell.getElement().appendChild = xxx.context;
			//設定第一個值為預設值
			var defVal = cell.getElement().querySelectorAll("select")[0].querySelectorAll("option")[0].value;
			cell.getElement().querySelectorAll("select")[0].value = defVal;
			return;
		}

		//使用查詢資料作為預設值
		// var x2 = cell.getData();
		// cell.getElement().innerHTML = xxx.context;
		cell.getElement().appendChild(contextClone);
		var x2 = cell.getElement().querySelectorAll("select")[0];
		x2.value = cellVal;
	}

	//TOOD一律改用無select狀況，待在這裡處理下拉選單物件快取問題
	//無select物件狀況，呼叫api
	if (checkIsNullSpace(defineObj.context) == true) {
		// console.log(cell.getRow().getCells().length);
		// var xxx = cell.getRow().getCells();
		// for (var i = 5; i < xxx.length; i++) {
		//   cell.getElement().querySelectorAll("select")[0].value = cellVal;
		//   cells[i].setValue("x", true);
		// }

		try {
			//呼叫function產生夏拉物件
			var func = defineObj.funcNm;
			var params = defineObj.params;
			var ef = defineObj.eventFunc;

			var obj = null;

			//總之先將設定的預設值設定進select
			//整理輸入的參數
			var p = new Array();

			if (checkIsNullSpace(params) == false) {
				for (var i = 0; i < params.length; i++) {
					var param = params[i];
					var type = param[1];//fix:固定值、other_select:列中其他欄位中select
					var val = param[0];

					if ("fix" == type) {
						p.push(val);
					}

					if ("other_select" == type) {
						var selObj = null;
						selObj = cell.getRow().getCells()[val].getElement().querySelectorAll("select")[0];
						p.push(selObj.value);

						// console.log(val);
						// console.log(cell.getRow().getCells()[val].getElement());
						// console.log(selObj);
					}
				}
			}

			var obj = null;

			if (p.length == 0) {//無參數
				obj = func.apply(this);
			} else {
				obj = func.apply(this, p);
			}

			//綁釘事件
			if (checkIsNullSpace(ef) == false) {
				$(obj).attr("onchange", ef.name + "(event.target.value, this);" + "return false;");
			}

			//將select物件加至cell中
			obj.disabled = true;
			cell.getElement().appendChild(obj);

			if (checkIsNullSpace(cellVal) == true) {
				//設定第一個值為預設值
				var defVal = cell.getElement().querySelectorAll("select")[0].querySelectorAll("option")[0].value;
				cell.getElement().querySelectorAll("select")[0].value = defVal;
				// console.log("1" + " " + cell.getElement().querySelectorAll("select")[0].value);
				return;
			}

			//使用查詢資料作為預設值
			// var x = cell.getElement().querySelectorAll("select")[0].querySelectorAll("option")[0].value;
			// console.log("2" + " def val:" + cellVal + " x:" + x);
			cell.getElement().querySelectorAll("select")[0].value = cellVal;
		} catch (err) {
			console.log("we have a trouble to gen select of col");
			console.log(err);
			cell.getElement().appendChild(document.createElement("select"));
		}
	}
}

/**
 */
var printIcon = function(cell, formatterParams) { //plain text value
	return "<div>hello</div>";
	//		return "<i class='fa fa-print'></i>";
};

/**
 */
function defineCols(showNo, colDefines) {
	var columns = new Array();
	var defineObjTmp = new Array();//因應javascript的scope問題，會亂抓變數，故這邊將syntax用的變數限制住

	for (var i = 0; i < colDefines.length; i++) {
		var param = colDefines[i];

		if (param[0] === "EditButton") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				formatter: formatterEditButton,
				cellClick: editButtonClick,
				headerSort: false,
				resizable: false,
				visible: param[3]
			});
		} else if (param[0] === "CancelButton") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				formatter: formatterCancelButton,
				cellClick: cancelButtonClick,
				headerSort: false,
				resizable: false,
				visible: param[3]
			});
		} else if (param[0] === "SaveButton") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				field: "SaveButton",
				formatter: formatterSaveButton,
				cellClick: saveButtonClick,
				headerSort: false,
				resizable: false,
				visible: param[3]
			});
		} else if (param[0] === "DeleteButton") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				formatter: formatterDeleteButton,
				cellClick: deleteButtonClick,
				headerSort: false,
				resizable: false,
				visible: param[3]
			});
		} else if (param[0] === "CopyButton") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				formatter: formatterCopyButton,
				cellClick: copyButtonClick,
				headerSort: false,
				resizable: false,
				visible: param[3]
			});
		} else if (param[0] === "SelectButton") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				formatter: formatterSelectButton,
				cellClick: selectButtonClick,
				headerSort: false,
				resizable: false,
				visible: param[3]
			});
		}
	}

	if (showNo == true) {
		defineObjTmp.push("");
		columns.push({ title: "No.", formatter: "rownum", hozAlign: "center", width: 60, headerClick: handleHeaderClick });
	}

	for (let i = 0; i < colDefines.length; i++) {
		var param = colDefines[i];

		if (param[2] === "rownum") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				editable: false,
				formatter: "rownum",
				headerClick: handleHeaderClick
			});
		} else if (param[2] === "select") {
			defineObjTmp.push("");//此容器變數為限制script問題使用，在這邊須給一個供後續方便計算位置

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				editor: `${param[2]}`,
				editorParams: param[3],
				sorter: "string",
				editable: isEditable,
				headerClick: handleHeaderClick
			});
		} else if (param[2] === "input") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				editor: `${param[2]}`,
				sorter: "string",
				editable: isEditable,
				headerClick: handleHeaderClick
			});
		} else if (param[2] === "number") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				editor: `${param[2]}`,
				sorter: "number",
				editable: isEditable,
				headerClick: handleHeaderClick
			});
		} else if (param[2] === "selectCus") {//syntax為tabulator關鍵字，不能使用
			defineObjTmp.push(param[3]);

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				resizable: true,
				// width: 70,
				formatter: function foo(cell, formatterParams, onRendered) {
					//在進入此步前，須將字串準備好
					onRendered(function() {
						var defineObj = defineObjTmp[i];

						if (checkIsNullSpace(defineObj) == true) {
							console.log("something is wrong!defineObj is undefined" + "iterator:" + i + " size:" + colDefines.length);
							cell.getElement().appendChild(document.createElement("select"));
							return;
						}

						renderedFunc4Sel(cell, defineObj);
					});

					//最後將選單值設定至cell
					// var x = cell.getElement().querySelectorAll("select")[0].value;
					// cell.setValue("X", false);

					//formatter參考程式
					//注意!不能直接使用傳入json，傳入參數會變很奇怪，可能套件內部有自動轉換
					// return param[2];//承上，在進入此步前，須將字串準備好
					// return xxx;
				}
			});
		} else if (param[2] === "url") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				resizable: true,
				//      editor : `${tableParams[i][2]}`,
				//				editable: isEditable,
				//				headerClick: function() {
				//					alert("?");
				//				},
				//				formatter: "link",
				//				formatterParams: {
				//					label: function(cell) {
				//						//						return "<a onclick=\"alert('?');\">hello</a>";
				//						if (checkIsNullSpace(param[3].context) == false) {
				//							return param[3].context;
				//						} else {
				//							return "link";
				//						}
				//					}
				//					, url: function(cell) {
				//						var val = cell.getValue();
				//						console.log(val[0]);
				//						return "#";
				//						//						return locationHrefKeepData(val[0],val[1],val[2]);
				//					}
				//				},
				formatter: function foo(cell, formatterParams, onRendered) {

					onRendered(function() {
						//這裡不需要檢核參數，參數已移出
						var defineObj = defineObjTmp[i];

						renderedFunc4Url(cell, defineObj);
					});
				}
			})
		} else if (param[2] === "display") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				editor: `${param[2]}`,
				sorter: "stirng",
				editable: false,
			});
		} else if (param[2] === "cusSyntax") {//僅供測試//syntax為tabulator關鍵字，不能使用
			defineObjTmp.push(param[3]);

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				resizable: true,
				// width: 70,
				formatter: function foo(cell, formatterParams, onRendered) {
					//在進入此步前，須將字串準備好
					onRendered(function() {
						try {
							var str = cell.getValue();
							var aTag = $(str)[0];
							cell.getElement().appendChild(aTag);
						} catch (err) {
							console.log("we have a trouble to gen select of col");
							console.log(err);
							cell.getElement().appendChild(document.createElement("select"));
						}
					});

					//最後將選單值設定至cell
					// var x = cell.getElement().querySelectorAll("select")[0].value;
					// cell.setValue("X", false);

					//formatter參考程式
					//注意!不能直接使用傳入json，傳入參數會變很奇怪，可能套件內部有自動轉換
					// return param[2];//承上，在進入此步前，須將字串準備好
					// return xxx;
				}
			});
		} else if (param[2] === "icon") {//僅供測試
			defineObjTmp.push("");

			columns.push({
				formatter: printIcon,
				hozAlign: "center",
				cellClick: function(e, cell) {
					alert("Printing row data for: " + cell.getRow().getData().name)
				}
			});
		}
		else {
			console.log("....");
		}
	}

	return columns;
}

//create edit button
function formatterEditButton(cell, formatterParams, onRendered) {
	onRendered(function() {
		cells = cell.getRow().getCells();

		for (var i = 0; i < cells.length; i++) {
			// cell.
			var sels = cells[i].getElement().querySelectorAll("select");

			for (var j = 0; j < sels.length; j++) {
				sels[j][0].style = "display:none;";//後面的0轉javascript物件
			}
		}

		// console.log(cells.getElement());//需要rendered才可以正確抓到element，推測是物件尚未產生在畫面
	});
	// console.log(cell.getRow().getCells()[5].getElement().querySelectorAll("select")[0].outerHTML);
	return "<div class='btn badge badge-pill badge-secondary'>Edit</div>";
}

//create cancel button
function formatterCancelButton(cell, formatterParams) {
	return "<div id='cancelBtn' name='cancelBtn' type='button' class='cancelBtn btn badge badge-pill badge-warning'>Cancel</div>";
}

//create save button
function formatterSaveButton(cell, formatterParams) {
	return "<div name='saveBtn' class='saveBtn btn badge badge-pill badge-success'>Save</div>";
}

//create delete button
function formatterDeleteButton(cell, formatterParams) {
	return "<div class='btn badge badge-pill badge-danger'>Delete</div>";
}

//create copy button
function formatterCopyButton(cell, formatterParams) {
	return "<div name='copyBtn' class='btn badge badge-pill badge-secondary'>複製</div>";
}

//create copy button
function formatterSelectButton(cell, formatterParams) {
	return "<div name='copyBtn' class='btn badge badge-pill badge-secondary'>選取</div>";
}

test = null;

//copy function
function copyButtonClick(e, cell) {
	alert("!");
	test = cell;

	var localTable = cell.getTable();
	var localTableData = cell.getTable().getData();

	let curRowIndex = cell.getRow().getPosition();
	let curRowData = cell.getRow().getData();
	let dpPerPg = $(dpPerPageDiv).find("input").val();

	if ((curRowIndex + 1) % dpPerPg == 0) {
		if (localTable.getPage() == localTable.getPageMax()) {
			localTableData.push(Object.assign({}, curRowData));
			localTable.previousPage();
			localTable.setPage("last");
		} else {
			localTable.nextPage();
			localTableData.splice(curRowIndex + 1, 0, Object.assign({}, curRowData));
		}
	} else {
		if (curRowIndex == localTableData.length - 1) {
			localTableData.push(Object.assign({}, curRowData));
		} else {
			localTableData.splice(curRowIndex + 1, 0, Object.assign({}, curRowData));
		}
	}

	//	cell.getTable().clearData();
	cell.getTable().setData(localTableData);

	var copyCell = cell.getTable().getRows()[curRowIndex + 1].getCells()[0];
	copyCell.setValue("copy-cancel");
	// var test = copyCell.getElement().getElementsByTagName("input")[0];
	// console.log(test);
	// // console.log(cell.getRow().getPosition()); //2
	// console.log(copyCell.getRow().getPosition()); //3

	// _tabledata.splice(curRowIndex + 1, 0, curRowData);
	//	stylePaginationBtn();
	//	disableBtns();
	$(dataDiv).remove();
	$(pageNumDiv).remove();
	createDataNum(cell.getTable());
	createPageNum(cell.getTable());

	switchVisibility(cell);
	// table.hideColumn("EditButton");
	// table.hideColumn("DeleteButton");
	// table.hideColumn("CopyButton");
	// table.showColumn("CancelButton");
	// table.showColumn("SaveButton");

	//status bar操作
	$(`${_globalDest}-reactivityAdd`).attr("disabled", true);
	$(`${_globalDest}-addToDb`).attr("disabled", true);
	$(`${_globalDest}-reactivityAdd`).css("opacity", 0.2);
	$(`${_globalDest}-addToDb`).css("opacity", 0.2);

	if (localTable.getPage() == 1) {
		localTable.nextPage();
		localTable.previousPage();
	} else {
		localTable.previousPage();
		localTable.nextPage();
	}
}

function selectButtonClick(e, cell) {
	try {
		btnmethods.sel(e, cell);
	} catch (err) {
		console.log("we callback function(select) fail");
		console.log(err);
	}
}

//edit function
function editButtonClick(e, cell) {
	currentRow = cell.getRow();
	currentTable = cell.getTable();
	selectedRows = currentTable.getSelectedRows();
	cell.getRow().getCells()[0].setValue('edit-cancel');

	//沒選取其他row才發動編輯
	if (selectedRows.length == 0) {
		for (i = 0; i < selectedRows.length; i++) {
			selectedRows[i].deselect();
			selectedRows[i].reformat();
		}
		currentTable.deselectRow();
		currentRow.select();
		currentRow.reformat();

		var scanerRes = scanObjPosiInRow(cell, "select");
		var selPosis = scanerRes.select;
		var otherPosis = scanerRes.other;

		//找到相關select欄位
		var cells = currentRow.getCells();
		// console.log(cells.length);

		for (var a = selPosis.length - 1; a >= 0; a--) {
			var i = selPosis[a];
			cells[i].getElement().querySelectorAll("select")[0].disabled = false;
		}

		//略過相關select欄位
		for (var a = otherPosis.length - 1; a >= 0; a--) {
			var i = otherPosis[a];
			cells[i].setValue(cells[i].getValue());
		}

	}
	switchVisibility(cell);

	// currentTable.hideColumn("EditButton");
	// currentTable.hideColumn("DeleteButton");
	// currentTable.showColumn("CancelButton");
	// currentTable.showColumn("SaveButton");

	//status bar操作
	$(`${_globalDest}-reactivityAdd`).attr("disabled", true);
	$(`${_globalDest}-addToDb`).attr("disabled", true);
	$(`${_globalDest}-reactivityAdd`).css("opacity", 0.2);
	$(`${_globalDest}-addToDb`).css("opacity", 0.2);
	//stylePagination();
	//document.getElementById("reactivity-add").disabled = true;
	//document.getElementById("reactivity-add").style.opacity = 0.2;

	try {
		btnmethods.edit(e, cell);
	} catch (err) {
		console.log("we callback function(edit) fail");
		console.log(err);
	}

	//$(".tabulator-footer").hide();
	//btnmethods.edit(e, cell);

}

//cancel function
function cancelButtonClick(e, cell) {
	if (!cell.getRow().isSelected()) {
		return
	}
	currentRow = cell.getRow();
	currentTable = cell.getTable();
	// console.log(cell.getRow().getCells()[0].getValue());
	// console.log(cell.getRow().getPosition()); //3
	// var curRowIndex = cell.getRow().getPosition();
	// var prevRow = currentRow.getPrevRow();
	// var prevCells = prevRow.getCells();
	// console.log(prevRow);
	// console.log(prevCells);

	//後續刪除的時候列消失cell也會消失，須處理
	//取得自己的index，再找到可以處理的row
	//tabulator使用迭代數為1開始，故減1
	var currentIndex = currentRow.getIndex() - 1;
	var lastIndex = currentTable.getRows().length - 1;

	var opeCell = null;

	if (currentIndex == 1) {
		//取最後一個
		opeCell = currentTable.getRows()[lastIndex].getCells()[0];
	} else if (currentIndex == lastIndex) {
		//取第一個
		opeCell = currentTable.getRows()[0].getCells()[0];
	} else {
		//取第一個
		opeCell = currentTable.getRows()[0].getCells()[0];
	}
	//		$(".tabulator-footer").hide();
	//依照點擊狀況個別處理
	if (cell.getRow().getCells()[0].getValue() == 'edit-cancel') {
		// console.log(cell.getRow().getCells()[0].getValue());
		if (cell.getRow().isSelected()) {
			//Cancel
			cells = currentRow.getCells();
			for (i = 0; i < cells.length; i++) {
				cells[i].restoreOldValue();
			}
			stopEditing(cell);
		}

		switchVisibility(opeCell);
	} else if (cell.getRow().getCells()[0].getValue() == 'copy-cancel') {
		console.log(cells);
		proc4NewCloneFlow(cell);
	} else {
		console.log(cells);
		proc4NewCloneFlow(cell);
	}

	// currentTable.hideColumn("CancelButton");
	// currentTable.hideColumn("SaveButton");
	// currentTable.showColumn("EditButton");
	// currentTable.showColumn("DeleteButton");
	$(dataDiv).remove();
	$(pageNumDiv).remove();
	createDataNum(cell.getTable());
	createPageNum(cell.getTable());

	try {
		btnmethods.cancel(e, cell);
	} catch (err) {
		console.log("we callback function(edit) fail");
		console.log(err);
	}

	//$(".tabulator-footer").show();
	//btnmethods.cancel(e, cell);

}

createTableWithDdlRef_modifiedPosi = [];//TODO 待發佈此變數

/**
 * save function
 * @param {*} e 
 * @param {*} cell 
 */
function saveButtonClick(e, cell) {
	if (!cell.getRow().isSelected()) {
		return;
	}

	//	cell.getTable();

	//取得資料位置
	var index = cell.getRow().getIndex();
	var cells = cell.getRow().getCells();

	//掃描select位置
	var selPosis = scanObjPosiInRow(cell, "select").select;

	//準備舊資料，待後續判斷是否有修改資料
	// var colsOld = cell.getRow().getCells();
	var tmpOld = {};

	for (var a = selPosis.length - 1; a >= 0; a--) {
		var i = selPosis[a];
		tmpOld[cells[i].getField()] = cells[i].getValue();
	}

	//設定修改結果至cell，並蒐集修改前後的資料
	var modifyRes = new Array();

	//注意迴圈是由後到前迭代執行，
	//可能是重新set的關係，尚未經過onrender，可能實體物件尚未出現，會出現找不到物件的狀況
	for (var a = selPosis.length - 1; a >= 0; a--) {
		var i = selPosis[a];

		var colNm = cells[i].getField();
		var o = cells[i].getValue();
		var val = cells[i].getElement().querySelectorAll("select")[0].value;

		cells[i].setValue(val, true);

		var m = cells[i].getValue();
		modifyRes.push({ "nm": colNm, "posi": i, "orgin_cell_data": o, "new_cell_data": m });
	}

	//準備新資料，待後續判斷是否有修改資料
	var colsNew = cell.getRow().getCells();
	var tmpNew = {};

	for (var a = selPosis.length - 1; a >= 0; a--) {
		var i = selPosis[a];
		tmpNew[colsNew[i].getField()] = colsNew[i].getValue();
	}

	var isModified = false;

	if (JSON.stringify(tmpOld) != JSON.stringify(tmpNew)) {
		// console.log(tmpOld + " " + tmpNew);
		isModified = true;
		createTableWithDdlRef_modifiedPosi.push(index);
	}

	try {
		btnmethods.update(e, cell);
	} catch (err) {
		console.log("we callback function(edit) fail");
		console.log(err);
	}
	console.log("editor_msg-row_index:" + index + ", judge_modified:" + isModified + ", data:" + JSON.stringify(modifyRes));

	//關閉可編輯設定
	stopEditing(cell);
	$(dataDiv).remove();
	$(pageNumDiv).remove();
	createDataNum(cell.getTable());
	createPageNum(cell.getTable());

	// var sels = cell.getElement().querySelectorAll("select");

	// if (checkIsNullSpace(sels) == false && sels.length > 0) {
	//   sels[0].disabled = true;
	//   console.log();
	// }


	// if (table.getPage() !== table.getPageMax()) {
	//   table.nextPage();
	//   table.previousPage();
	// } else {
	//   table.previousPage();
	//   table.nextPage();
	// }
	var cells = cell.getRow().getCells();
	switchVisibility(cell);
	// currentTable.hideColumn("CancelButton");
	// currentTable.hideColumn("SaveButton");
	// currentTable.showColumn("EditButton");
	// currentTable.showColumn("DeleteButton");
	$(".tabulator-footer").show();
}

//delete function
function deleteButtonClick(e, cell) {
	// if (!cell.getRow().isSelected()){
	//   return
	// }
	let rowIndex = cell.getRow().getPosition();

	let id = cell.getData().id;
	let index;
	for (let i = 0; i < _tabledata.length; i++) {
		if (_tabledata[i].id === id) {
			index = i;
		}
	}

	//Can use prompt to make them confirm the name
	if (window.confirm("Delete Data No." + (rowIndex + 1))) {

		try {
			btnmethods.del(e, cell);
		} catch (err) {
			console.log("we callback function(edit) fail");
			console.log(err);
		}
		// switchVisibility(cell);
		stopEditing(cell);
		cell.getRow().delete();
		if (index > -1) {
			_tabledata.splice(index, 1);
		}
	}

	stylePaginationBtn();
	disableBtns();
	$(dataDiv).remove();
	$(pageNumDiv).remove();
	createDataNum(cell.getTable());
	createPageNum(cell.getTable());

	// if (table.getPage() == 1) {
	//   table.nextPage();
	//   table.previousPage();
	// } else {
	//   table.previousPage();
	//   table.nextPage();
	// }
	////
}

//call function when user clicks on header
function handleHeaderClick() {
	setTimeout(function() {
		disableBtns();
		stylePagination();
	}, 0.1)
}

//check if cell is editable
function isEditable(cell) {
	return cell.getRow().isSelected()
}

//掃描cell中指定物件的欄位置
/**
 * 
 * @param {*} cell 
 * @param {*} jquertSelector 
 */
function scanObjPosiInRow(cell, jquertSelector) {
	var cells = cell.getRow().getCells();
	var selPosis = new Array();
	var inputPosis = new Array();

	for (var i = 0; i < cells.length; i++) {
		// var x = cells[i].getElement().querySelectorAll(objName);
		var x = cells[i].getElement();

		var selectorList = $(x).find(jquertSelector);

		//轉回javascript物件
		if (selectorList.length <= 0) {
			inputPosis.push(i);
			continue;
		}

		//轉回javascript物件
		var containerList = new Array();

		for (var j = 0; j < selectorList.length; j++) {
			containerList.push(selectorList.eq(j)[0]);
		}

		if (containerList.length > 0) {
			selPosis.push(i);
		}
	}

	var res = {};

	res[jquertSelector] = selPosis;
	res["other"] = inputPosis;

	return res;
}

/**
 * 
 * @param {*} cell
 */
function switchVisibility(cell) {
	//找到釘選button的位置
	//這邊不使用掃描機制，因使用formatter太過複雜
	var fixedPosi = new Array();

	for (var i = 0; i < colDefines.length; i++) {
		if (colDefines[i][2] == "button" && colDefines[i][4] == "fix") {
			fixedPosi.push(i);
		}
	}

	//找到button位置
	var cells = cell.getRow().getCells();
	var inputPosi = scanObjPosiInRow(cell, ".btn")[".btn"];

	for (var i = 0; i < inputPosi.length; i++) {
		var p = inputPosi[i];

		//如果欄位被設為fixed即為釘選，預設如果切換一次，即內部按鈕執行一次變化「可按」或「不可按」
		var boo = false;

		for (var x = 0; x < fixedPosi.length; x++) {
			var fixedPosiJudge = fixedPosi[x];

			if (fixedPosiJudge == p) {
				boo = true;
			}
		}

		if (boo == true) {
			var switch2Cell = cells[p];
			continue;
		}

		//執行切換
		var switch2Cell = cells[p];
		// console.log(switch2Cell);
		var s = switch2Cell.getColumn().getVisibility();

		if (s == true) {
			//顯示的話即關閉
			switch2Cell.getColumn().hide();
		} else {
			//不顯示的話即開啟
			switch2Cell.getColumn().show();
		}

	}


	// currentTable.hideColumn("CancelButton");
	// // currentTable.hideColumn("SaveButton");
	// // currentTable.showColumn("EditButton");
	// // currentTable.showColumn("DeleteButton");

	// for (var i = 0; i < cells.length; i++) {
	//   console.log(cells[i]);
	//   if (cells[i].getElement().querySelectorAll(".btn").length !== 0) {
	//     if (cells[i].getColumn().getVisibility()) {
	//       cells[i].getColumn().hide();
	//     } else {
	//       cells[i].getColumn().show();
	//     }
	//   }
	// }
}

/**
 *針對cacnel中使用的共用程式
*/
function proc4NewCloneFlow(cell) {
	//因程序有些為不同，且底層需用event控制deleteButtonClick函式
	//故不適合直接呼叫，這邊將實作貼過來個別處理
	// delete the entire row
	// deleteButtonClick(e, cell);

	let rowIndex = cell.getRow().getPosition();

	let id = cell.getData().id;
	let index;
	for (let i = 0; i < _tabledata.length; i++) {
		if (_tabledata[i].id === id) {
			index = i;
		}
	}

	//Can use prompt to make them confirm the name
	if (window.confirm("Delete Data No." + (rowIndex + 1))) {

		try {
			btnmethods.del(e, cell);
		} catch (err) {
			console.log("we callback function(edit) fail");
			console.log(err);
		}
		switchVisibility(cell);
		stopEditing(cell);
		cell.getRow().delete();
		if (index > -1) {
			_tabledata.splice(index, 1);
		}
	} else {
		switchVisibility(cell);
		currentTable.deselectRow();
	}

	stylePaginationBtn();
	disableBtns();
}

//call this function whenever the user stops editing
//TODO 此function有耦合destination，待改進
function stopEditing(cell) {
	currentRow = cell.getRow();
	currentTable = cell.getTable();
	currentTable.deselectRow();
	currentRow.reformat();
	disableBtns();

	//status bar操作
	$(`${_globalDest}-reactivityAdd`).attr("disabled", false);
	$(`${_globalDest}-reactivityAdd`).css("opacity", 1);
	$(`${_globalDest}-addToDb`).attr("disabled", false);
	$(`${_globalDest}-addToDb`).css("opacity", 1);
	//document.getElementById("reactivity-add").disabled = false;
	//document.getElementById("reactivity-add").style.opacity = 1;
}

// make buttons appear disabled
function disableBtns() {
	$(".cancelBtn").css("opacity", 0.2);
	$(".saveBtn").css("opacity", 0.2);
	//var cancelBtns = document.getElementsByName('cancelBtn');
	// for (let i=0; i< cancelBtns.length; i++){
	//   cancelBtns[i].style.opacity = 0.2;
	// }
	// var saveBtns = document.getElementsByName('saveBtn');
	// for (let i=0; i< saveBtns.length; i++){
	//   saveBtns[i].style.opacity = 0.2;
	// }

}

/**
*輸入表格內物件，根據輸入的目標數，找到特定的欄位
 */
function fetchTarget(obj, targetPosi) {
	var row = $(obj).parent("div").parent("div");
	var divs = row.children("div");
	return divs.eq(targetPosi);

	// alert("this sel1 event:" + val + "," + $(thisobj)[0].outerHTML);
	// console.log($(thisobj).parent("div").parent("div").parent("div").html());
	//重新查詢api，放入第二個select
	// alert(divs.eq(6).find("select").eq(0)[0].outerHTML);
	// for (var i = 0; i < divs.length; i++) {
	//  console.log(i);
	//  console.log(divs.eq(i));
	// }
}

//create element to show number of data points
let dataDiv;
function createDataNum(thisTable) {
	dataDiv = document.createElement("div");
	$(dataDiv).css({ "display": "flex", "width": "75px", "justify-content": "center", "align-items": "center" });
	let dataNum = document.createElement("p");
	$(dataNum).css({ "display": "inline", "color": "white" });
	dataNum.innerText = `總共  ${thisTable.getData().length} 筆`;
	$(dataNum).appendTo(dataDiv);
	$(dataDiv).insertAfter(_pagination.children(".tabulator-paginator"));
}

//create element to show dps per page
let dpPerPageDiv;
function createDpPerPage(thisTable) {
	dpPerPageDiv = document.createElement("form");
	$(dpPerPageDiv).css({ "display": "flex", "width": "135px", "justify-content": "center", "align-items": "center", "margin-left": "10px" });
	let txt1 = document.createElement("p");
	$(txt1).css({ "display": "inline", "color": "white" });
	txt1.innerText = "每頁";
	let dpPerPageNum = document.createElement("input");
	$(dpPerPageNum).css({ "display": "inline", "width": "30px", "margin-left": "5px", "margin-right": "5px" })
	$(dpPerPageNum).val(5);
	let txt2 = document.createElement("p");
	$(txt2).css({ "display": "inline", "color": "white", "margin-right": "3px" });
	txt2.innerText = "筆";
	let goBtn1 = document.createElement("button");
	$(dpPerPageDiv).on("submit", function(event) {
		event.preventDefault();
		let dpPerPg = $(dpPerPageNum).val();
		if (dpPerPg <= thisTable.length && dpPerPg > 0) {
			thisTable.setPageSize(dpPerPg);
			$(pageNumDiv).remove();
			createPageNum(thisTable)
		} else {
			alert("Number out of range!");
		}
	});
	$(goBtn1).html("go");
	$(goBtn1).addClass("goBtn");
	$(txt1).appendTo(dpPerPageDiv);
	$(dpPerPageNum).insertAfter(txt1);
	$(txt2).insertAfter(dpPerPageNum);
	$(goBtn1).insertAfter(txt2);
	$(dpPerPageDiv).insertAfter(dataDiv);

}

//create element to show number of pages
let pageNumDiv;

function createPageNum(thisTable) {
	pageNumDiv = document.createElement("div");
	$(pageNumDiv).css({ "display": "flex", "width": "75px", "justify-content": "center", "align-items": "center" });
	let pageNum = document.createElement("p");
	$(pageNum).css({ "display": "inline", "color": "white" });
	pageNum.innerText = `總共  ${thisTable.getPageMax()} 頁`;
	$(pageNum).appendTo(pageNumDiv);
	//$(pageNumDiv).insertAfter(dataDiv);
	$(pageNumDiv).insertAfter(dpPerPageDiv);
}

//create element to show current page number 
let currentPageDiv;
function createCurrentPage(thisTable) {
	currentPageDiv = document.createElement("form");
	$(currentPageDiv).css({ "display": "flex", "width": "150px", "justify-content": "center", "align-items": "center", "margin-left": "40px" });
	let pageNum1 = document.createElement("p");
	$(pageNum1).css({ "display": "inline", "color": "white" });
	pageNum1.innerText = "目前第";
	let curPageNum = document.createElement("input");
	$(curPageNum).css({ "display": "inline", "width": "30px", "margin-left": "5px", "margin-right": "5px" })
	let pageNum2 = document.createElement("p");
	$(pageNum2).css({ "display": "inline", "color": "white", "margin-right": "3px" });
	pageNum2.innerText = "頁";
	let goBtn2 = document.createElement("button");

	if (checkIsNullSpace(thisTable) != null) {
		$(curPageNum).val(thisTable.getPage());
		$(currentPageDiv).on("submit", function(event) {
			event.preventDefault();
			let pgNum = $(curPageNum).val();
			if (pgNum <= thisTable.getPageMax() && pgNum > 0) {
				thisTable.setPage(pgNum);
			} else {
				alert("Page number out of range!");
			}
		});
	}

	$(goBtn2).html("go");
	$(goBtn2).addClass("goBtn");
	$(pageNum1).appendTo(currentPageDiv);
	$(curPageNum).insertAfter(pageNum1);
	$(pageNum2).insertAfter(curPageNum);
	$(goBtn2).insertAfter(pageNum2);
	$(currentPageDiv).insertAfter(pageNumDiv);
}

	//已被scanObjPosiInRow取代，可刪
	// /**
	//  * 掃描列中含下拉選單物件的欄位置
	//  * @param {} cell 
	//  */
	// function scanSelPosiInRow(cell) {
	//   var cells = cell.getRow().getCells();
	//   var selPosis = new Array();
	//   var inputPosis = new Array();

	//   for (var i = 0; i < cells.length; i++) {
	//     var x = cells[i].getElement().querySelectorAll("select");

	//     if (checkIsNullSpace(x) == false && x.length > 0) {
	//       selPosis.push(i);
	//     } else {
	//       inputPosis.push(i);
	//     }
	//   }

	//   var res = {
	//     "select": selPosis,
	//     "other": inputPosis
	//   };

	//   return res;
	// }