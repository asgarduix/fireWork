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

function fetchTabulator(tableId) {
	var tmp = $(tableId)[0];

	if (checkIsNullSpace(tmp) == true) {
		console.log("error! id of your input that cant fetch Obj");
		return null;
	}

	var tabu = Tabulator.prototype.findTable(tableId)[0];
	var res = tabu.getColumns()[0].getTable();
	return res;
}

function cleanDataToTable(tableId) {
	var tabu = Tabulator.prototype.findTable(tableId)[0];
	tabu.clearData();
	return tabu;
}

function refreshDataToTable(tableId, dataArray) {
	var tabu = cleanDataToTable(tableId);
	tabu.addData(dataArray);

	tabu.deselectRow();
	tabu.getRows()[0].reformat();
	//	disableBtns();

	$("" + tableId + "-reactivityAdd").attr("disabled", false);

	//status bar操作
	$(`${tableId}-reactivityAdd`).attr("disabled", false);
	$(`${tableId}-reactivityAdd`).css("opacity", 1);
	$(`${tableId}-addToDb`).attr("disabled", false);
	$(`${tableId}-addToDb`).css("opacity", 1);
}

//create table function, takes colDefines([[fieldName, inputType [, selectParams(object) ]]...]) and tabledata
function createTableddlref(colDefines, tabledata, destination, btnmethods, showNo, statusBarBtnMethods, nonEditableParams) {
	var col = {};
	
	for (let i = 0; i < colDefines.length; i++) {
		var colName = colDefines[i][0];
		var colShowName = colDefines[i][1];
		col[colName] = colShowName;
	}

	//先根據輸入的參數設定按鈕
	//目前僅支援copy、Edit、Cancel、save、delete、、、
	columns = new Array();
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
		} else if (param[2] === "uneditableInput") {
			defineObjTmp.push("");

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				sorter: "string",
				headerClick: handleHeaderClick

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
		} else if (param[2] === "cusInput") {
			defineObjTmp.push(param[3]);
			//param[3]為一個object包含 
			//fieldNm => 欲更動的欄位的名稱
			//params => 要怎麼更改cell的值放到想更改的欄位

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				resizable: true,
				editable: isEditable,
				editor: function foo(cell, onRendered, success, cancel, editorParams) {
					//cell - the cell component for the editable cell
					//onRendered - function to call when the editor has been rendered
					//success - function to call to pass the successfuly updated value to Tabulator
					//cancel - function to call to abort the edit and return to a normal cell
					//editorParams - params object passed into the editorParams column definition property

					//create and style editor
					var editor = document.createElement("input");

					editor.setAttribute("type", "number");

					//create and style input
					editor.style.padding = "3px";
					editor.style.width = "100%";
					editor.style.boxSizing = "border-box";

					//Set value of editor to the current value of the cell
					editor.value = cell.getValue();

					//set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
					onRendered(function() {
						editor.focus();
						editor.style.css = "100%";
					});

					//when the value has been set, trigger the cell to update
					function changeFunc(e) {
						let cellNm;
						for (let j = 0; j < colDefines.length; j++) {
							if (colDefines[j][0] == param[3].fieldNm) {
								cellNm = j
							}
						}
						let cellValue = e.target.value;
						cell.getRow().getCells()[cellNm].setValue(cellValue * param[3].params);
					}
					function successFunc() {
						success(editor.value);
					}

					editor.addEventListener("change", changeFunc);
					editor.addEventListener("blur", successFunc);

					//return the editor element
					return editor;
				}
			});
		} else if (param[2] === "cusInput2") {
			defineObjTmp.push(param[3]);
			//param[3]為一個array包含 
			//param[3][0] => 已分配保費input的id
			//param[3][1] => 未分配保費input的id

			columns.push({
				title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
				field: param[0],
				resizable: true,
				editable: isEditable,
				editor: function foo(cell, onRendered, success, cancel, editorParams) {
					//cell - the cell component for the editable cell
					//onRendered - function to call when the editor has been rendered
					//success - function to call to pass the successfuly updated value to Tabulator
					//cancel - function to call to abort the edit and return to a normal cell
					//editorParams - params object passed into the editorParams column definition property

					//create and style editor
					var editor = document.createElement("input");

					editor.setAttribute("type", "number");

					//create and style input
					editor.style.padding = "3px";
					editor.style.width = "100%";
					editor.style.boxSizing = "border-box";

					//Set value of editor to the current value of the cell
					editor.value = cell.getValue();

					//set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
					onRendered(function() {
						editor.focus();
						editor.style.css = "100%";
					});

					//when the value has been set, trigger the cell to update
					function changeFunc(e) {
						let cellValue = e.target.value;
			            let undistributedVal = $(param[3][1]).val();
						if (cellValue <= undistributedVal){
							$(param[3][1]).val(undistributedVal - cellValue);
							let distributedVal = $(param[3][0]).val(); 
							$(param[3][0]).val(Number(distributedVal) + Number(cellValue));
						}else{
							alert("error!")
						}
					}
					function successFunc() {
						success(editor.value);
					}

					editor.addEventListener("change", changeFunc);
					editor.addEventListener("blur", successFunc);

					//return the editor element
					return editor;
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
	
	
	
	if(nonEditableParams){
		for (let i= 0; i < nonEditableParams.length; i++){
			columns[nonEditableParams[i]].editable = false;		
		}
		
	}
	
	
	// console.log(columns);
	// console.log(defineObjTmp);

	var printIcon = function(cell, formatterParams) { //plain text value
		return "<div>hello</div>";
		//		return "<i class='fa fa-print'></i>";
	};

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
			console.log(err);
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

	//call function when user clicks on header
	function handleHeaderClick() {
		setTimeout(function() {
			disableBtns();
			stylePagination();
		}, 0.1)
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

	//create select button
	function formatterSelectButton(cell, formatterParams) {
		return "<div name='selectBtn' class='btn badge badge-pill badge-secondary'>選取</div>";
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
				if (nonEditableParams && nonEditableParams.includes(i)){
					cells[i].getElement().querySelectorAll("select")[0].disabled = true;	
				}else{
					cells[i].getElement().querySelectorAll("select")[0].disabled = false;	
				}
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
		$(`${destination}-reactivityAdd`).attr("disabled", true);
		$(`${destination}-addToDb`).attr("disabled", true);
		$(`${destination}-reactivityAdd`).css("opacity", 0.2);
		$(`${destination}-addToDb`).css("opacity", 0.2);
		//stylePagination();
		//document.getElementById("reactivity-add").disabled = true;
		//document.getElementById("reactivity-add").style.opacity = 0.2;

		try {
			btnmethods.edit(e, cell);
		} catch (err) {
			console.log("callback function(edit) failed");
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

		//此會發生status bar消失的問題，故註解，待確認沒問題後，可刪
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
			//可能新增需要的
			console.log(cells);
			proc4NewCloneFlow(cell);
		}

		// currentTable.hideColumn("CancelButton");
		// currentTable.hideColumn("SaveButton");
		// currentTable.showColumn("EditButton");
		// currentTable.showColumn("DeleteButton");
		$(dataDiv).remove();
		$(pageNumDiv).remove();
		createDataNum();
		createPageNum();

		try {
			btnmethods.cancel(e, cell);
		} catch (err) {
			console.log("callback function(cancel) failed");
			console.log(err);
		}

		//$(".tabulator-footer").show();
		//btnmethods.cancel(e, cell);

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
		let tableData = table.getData();
		let index;
		for (let i = 0; i < table.getData().length; i++) {
			if (tableData[i].id === id) {
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
				tabledata.splice(index, 1);
			}
		} else {
			switchVisibility(cell);
			currentTable.deselectRow();
		}

		stylePaginationBtn();
		disableBtns();
	}

	//copy function
	function copyButtonClick(e, cell) {
		// if (!cell.getRow().isSelected()){
		//     return
		// }
		setTimeout(()=> {
		  $(`${destination}-reactivityAdd`).attr("disabled", true);
		  $(`${destination}-addToDb`).attr("disabled", true);
		  $(`${destination}-reactivityAdd`).css("opacity", 0.2);
		  $(`${destination}-addToDb`).css("opacity", 0.2);
		}, 400)

		const curRowIndex = cell.getRow().getPosition(true);
		const curRowData = cell.getData();
		const dpPerPg = $(dpPerPageDiv).find("input").val();
		console.log(curRowIndex);
		console.log(table.getPage() === table.getPageMax());
		console.log((curRowIndex+1)% dpPerPg === 0 );
		//TODO: fix copy function
		if ((curRowIndex + 1) % dpPerPg === 0) {
			if (table.getPage() === table.getPageMax()) {
				//table.addRow(Object.assign({}, curRowData), false, curRowIndex)
				tabledata.push(Object.assign({}, curRowData));
				table.previousPage();
				table.nextPage();
				table.nextPage();
				//table.setPage("last");
				console.log(100)
			} else {
				table.nextPage();
				tabledata.splice(curRowIndex + 1, 0, Object.assign({}, curRowData));
				//table.addRow(Object.assign({}, curRowData), false, curRowIndex)
				console.log(200)
			}
		} else {
			if (curRowIndex === tabledata.length - 1) {
				//table.addRow(Object.assign({}, curRowData), false, curRowIndex)
				console.log(300)
				tabledata.push(Object.assign({}, curRowData));
			} else {
				tabledata.splice(curRowIndex + 1, 0, Object.assign({}, curRowData));
				//console.log(curRowIndex);
				//table.addRow(Object.assign({}, curRowData), false, curRowIndex )
				console.log(400)
			}
		}
		
		var copyCell = cell.getTable().getRows()[curRowIndex + 1].getCells()[0];
		copyCell.setValue("copy-cancel");
		// var test = copyCell.getElement().getElementsByTagName("input")[0];
		// console.log(test);
		// // console.log(cell.getRow().getPosition()); //2
		// console.log(copyCell.getRow().getPosition()); //3

		// tabledata.splice(curRowIndex + 1, 0, curRowData);
		stylePaginationBtn();
		disableBtns();
		$(dataDiv).remove();
		$(pageNumDiv).remove();
		createDataNum();
		createPageNum();

		switchVisibility(cell);
		// table.hideColumn("EditButton");
		// table.hideColumn("DeleteButton");
		// table.hideColumn("CopyButton");
		// table.showColumn("CancelButton");
		// table.showColumn("SaveButton");

		//status bar操作
		//$(`${destination}-reactivityAdd`).attr("disabled", true);
		//$(`${destination}-addToDb`).attr("disabled", true);
		//$(`${destination}-reactivityAdd`).css("opacity", 0.2);
		//$(`${destination}-addToDb`).css("opacity", 0.2);

		if (table.getPage() == 1) {
			table.nextPage();
			table.previousPage();
		} else {
			table.previousPage();
			table.nextPage();
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
		createDataNum();
		createPageNum();

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
		for (let i = 0; i < tabledata.length; i++) {
			if (tabledata[i].id === id) {
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
				tabledata.splice(index, 1);
			}
		}

		stylePaginationBtn();
		disableBtns();
		$(dataDiv).remove();
		$(pageNumDiv).remove();
		createDataNum();
		createPageNum();

		// if (table.getPage() == 1) {
		//   table.nextPage();
		//   table.previousPage();
		// } else {
		//   table.previousPage();
		//   table.nextPage();
		// }
		////
	}

	//call this function whenever the user stops editing
	function stopEditing(cell) {
		currentRow = cell.getRow();
		currentTable = cell.getTable();
		currentTable.deselectRow();
		currentRow.reformat();
		disableBtns();

		//status bar操作
		$(`${destination}-reactivityAdd`).attr("disabled", false);
		$(`${destination}-reactivityAdd`).css("opacity", 1);
		$(`${destination}-addToDb`).attr("disabled", false);
		$(`${destination}-addToDb`).css("opacity", 1);
		//document.getElementById("reactivity-add").disabled = false;
		//document.getElementById("reactivity-add").style.opacity = 1;
	}

	//check if cell is editable
	function isEditable(cell) {
		return cell.getRow().isSelected()
	}
	//cell.getValue() === null || cell.getValue() === ""

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
				"columns": col,
				// {
				//     "name":"名字",
				//     "gender":"性別",
				//     "age":"年齡",
				//     "insType":"險別",
				//     "insType1":"保險菜單",
				//     "insType2":"險種"
				// },
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
		rowAdded: function(row) {//addRow()、addData()會觸發的事件
			setTimeout(function() {
				currentRow = row;
				currentTable = row.getTable();
				currentTable.deselectRow()
				currentRow.select()
				currentRow.reformat()

				let cell = row.getCells()[0];

				if (checkIsNullSpace(cell) == true) {
					return;
				}

				stopEditing(cell);

				if (currentTable.getData().length == 0) {
					//此切換按鈕改使用共用切換按鈕
					//如果沒資料，則代表示新增列資料，使之切換為可以編輯
					switchVisibility(cell);
				}

				//status bar操作
//				$(`${destination}-reactivityAdd`).attr("disabled", true);
//				$(`${destination}-addToDb`).attr("disabled", true);
//				$(`${destination}-reactivityAdd`).css("opacity", 0.2);
//				$(`${destination}-addToDb`).css("opacity", 0.2);
				stylePaginationBtn();

				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				//沒選取其他row才發動編輯
				change2EditStatus(cell);

				//				currentRow = cell.getRow();
				//				currentTable = cell.getTable();
				//				selectedRows = currentTable.getSelectedRows();
				//
				//				if (selectedRows.length == 0) {
				//					for (i = 0; i < selectedRows.length; i++) {
				//						selectedRows[i].deselect();
				//						selectedRows[i].reformat();
				//					}
				//					
				//					currentTable.deselectRow();
				//					currentRow.select();
				//					currentRow.reformat();
				//
				//					var scanerRes = scanObjPosiInRow(cell, "select");
				//					var selPosis = scanerRes.select;
				//					var otherPosis = scanerRes.other;
				//
				//					//找到相關select欄位
				//					for (var a = selPosis.length - 1; a >= 0; a--) {
				//						var i = selPosis[a];
				//						cells[i].getElement().querySelectorAll("select")[0].disabled = false;
				//					}
				//
				//					//略過相關select欄位
				//					for (var a = otherPosis.length - 1; a >= 0; a--) {
				//						var i = otherPosis[a];
				//						cells[i].setValue(cells[i].getValue());
				//					}
				//				}

				//				currentTable.hideColumn("EditButton");
				//				currentTable.hideColumn("DeleteButton");
				//				currentTable.showColumn("CancelButton");
				//				currentTable.showColumn("SaveButton");

				try {
					console.log("insert callback function here");
				} catch (e) {
					console.log(e)
				}

				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// currentRow = row;
				//currentTable = row.getTable();
				//     selectedRows = currentTable.getSelectedRows();
				//     if (selectedRows.length == 0) {
				//       for (i = 0; i < selectedRows.length; i++) {
				//         selectedRows[i].deselect()
				//         selectedRows[i].reformat()
				//       }
				//       currentTable.deselectRow()
				//       currentRow.select()
				//       currentRow.reformat()
				//       cells = currentRow.getCells();
				//       for (i = 0; i < cells.length; i++) {
				//         cells[i].setValue(cells[i].getValue());
				//       }
				//     }
				//     row.getCells()[5].edit();

				//status bar操作
				//     $(`${destination}-reactivityAdd`).attr("disabled", true);
				//     $(`${destination}-reactivityAdd`).css("opacity", 0.2);
				//     $(`${destination}-addToDb`).attr("disabled", true);
				//     $(`${destination}-addToDb`).css("opacity", 0.2);

				//     stylePaginationBtn();

			}, 0.02);
		},
		
		pageLoaded: function(pageno) {
			//pageno - the number of the loaded page
			disableBtns();
			stylePaginationBtn();
			$(currentPageDiv).remove();
			createCurrentPage();
		}
		//cell發生點擊「一律」觸發事件的api
		//		, rowClick: function(e, row) {
		//			alert("Row " + row.getIndex() + " Clicked!!!!")
		//		}
		// ,
		// cellClick: function(e, cell){
		//   if (cell.getValue() === null || cell.getValue() === ""){
		//     currentRow = cell.getRow()
		//     currentTable = cell.getTable()
		//     selectedRows = currentTable.getSelectedRows()
		//     if (selectedRows.length == 0) {
		//       for (i = 0; i < selectedRows.length; i++) {
		//         selectedRows[i].deselect()
		//         selectedRows[i].reformat()
		//       }
		//       currentTable.deselectRow()
		//       currentRow.select()
		//       currentRow.reformat()

		//       cells = currentRow.getCells()
		//       for (i = 0; i < cells.length; i++) {
		//         cells[i].setValue(cells[i].getValue());
		//       }
		//     }
		//   }
		// }

	});

	/**
	 */
	function change2EditStatus(cell) {
		var currentRow = cell.getRow();
		var currentTable = cell.getTable();
		var selectedRows = currentTable.getSelectedRows();

		if (selectedRows.length != 0) {
			return false;
		}

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

		return true;
	}


	//select pagination
	let pagination = $(destination).children(".tabulator-footer");

	//status bar中的按鈕操作
	//add row to bottom of table on button click
	$(`${destination}-reactivityAdd`).on("click", function() {
		setTimeout(() =>{
			$(`${destination}-reactivityAdd`).attr("disabled", true);
	    	$(`${destination}-reactivityAdd`).css("opacity", 0.2);
	    	$(`${destination}-addToDb`).attr("disabled", true);
	    	$(`${destination}-addToDb`).css("opacity", 0.2);
		},400)
		
		let dpPerPg = $(dpPerPageDiv).find("input").val();
		//table.addRow({ id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male", color:"black"});
		let pushData = {}
		for (let i = 0; i < colDefines.length; i++) {
			if (colDefines[i][2] === "select" || colDefines[i][2] === "input" || colDefines[i][2] === "selectCus") {
				pushData[colDefines[i][0]] = "";
			} else if (colDefines[i][2] === "number") {
				pushData[colDefines[i][0]] = 0;
			} else {
				pushData[colDefines[i][0]] = undefined;
			}

		}

		//		pushData["id"] = tabledata[tabledata.length - 1].id + 1;
		if (table.getPage() == table.getPageMax() && table.getData().length % dpPerPg == 0) {
			//tabledata.push({ insType: "", insType1: "", insType2: "", id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male"});
			//			tabledata.push(pushData);
			table.addRow(pushData);
			table.setPage(table.getPageMax());//last不明原因無法作用，改用getset方法
			//			table.setPage("last");
			//table.addRow(pushData);//因為有無資料狀態且後續須新增資料的情況，故這邊改用表格物件操作
			//			table.previousPage();
			//			table.nextPage();
		} else {
			table.setPage(table.getPageMax());//last不明原因無法作用，改用getset方法
			//			table.setPage("last");
			table.addRow(pushData);//因為有無資料狀態且後續須新增資料的情況，故這邊改用表格物件操作
			//						tabledata.push(pushData);
			//tabledata.push({ insType: "", insType1: "", insType2: "", id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male"});
		}

		//此切換按鈕改使用共用切換按鈕
		var rows = table.getRows();

		//有資料才幫表格執行切換按鈕的動作
		if (rows.length > 0) {
			var cell = rows[0].getCells()[0];
			switchVisibility(cell);
		}
		// table.hideColumn("EditButton");
		// table.hideColumn("DeleteButton");
		// table.showColumn("CancelButton");
		// table.showColumn("SaveButton");

		//status bar操作
		//$(`${destination}-reactivityAdd`).attr("disabled", true);
		//$(`${destination}-addToDb`).attr("disabled", true);
		//$(`${destination}-reactivityAdd`).css("opacity", 0.2);
		//$(`${destination}-addToDb`).css("opacity", 0.2);
	});

	//save data to DB
	//	$(`${destination}-addToDb`).on("click", function() {
	//		//api call to save to db
	//		console.log(table.getData());
	//		console.log("Saved to database!");
	//	});

	//需要在產生前先綁釘靜態物件
	//bind event to button of status bar 
	for (let i = 0; i < statusBarBtnMethods.length; i++) {
		var id = statusBarBtnMethods[i].id;
		var func = statusBarBtnMethods[i].funcNm;

		$(id).on("click", function() {
			func.apply($(id)[0]);//callback時強制轉回javascript物件傳回
		});
	}

	function stylePaginationBtn() {
		pagination.find("button").css({
			"margin-left": "0rem",
			"margin-right": "0rem",
			"background-color": "#dba0ac",
			"font-size": "1rem",
			"color": "#ffffff",

		})
	}

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

	
	//create element to show number of data points
	let dataDiv;
	function createDataNum() {
		dataDiv = document.createElement("div");
		$(dataDiv).css({ "display": "flex", "width": "75px", "justify-content": "center", "align-items": "center" });
		let dataNum = document.createElement("p");
		$(dataNum).css({ "display": "inline", "color": "white" });
		//dataNum.innerText = `總共  ${tabledata.length} 筆`;
		dataNum.innerText = `總共  ${table.getData().length} 筆`;
		$(dataNum).appendTo(dataDiv);
		$(dataDiv).insertAfter(pagination.children(".tabulator-paginator"));
	}
	
	//create element to show dps per page
	let dpPerPageDiv;
	function createDpPerPage() {
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
			if (dpPerPg <= table.getData().length && dpPerPg > 0) {
				table.setPageSize(dpPerPg);
				$(pageNumDiv).remove();
				createPageNum()
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

	function createPageNum() {
		pageNumDiv = document.createElement("div");
		$(pageNumDiv).css({ "display": "flex", "width": "75px", "justify-content": "center", "align-items": "center" });
		let pageNum = document.createElement("p");
		$(pageNum).css({ "display": "inline", "color": "white" });
		pageNum.innerText = `總共  ${table.getPageMax()} 頁`;
		$(pageNum).appendTo(pageNumDiv);
		//$(pageNumDiv).insertAfter(dataDiv);
		$(pageNumDiv).insertAfter(dpPerPageDiv);
	}

	//create element to show current page number 
	let currentPageDiv;
	function createCurrentPage() {
		currentPageDiv = document.createElement("form");
		$(currentPageDiv).css({ "display": "flex", "width": "150px", "justify-content": "center", "align-items": "center", "margin-left": "40px" });
		let pageNum1 = document.createElement("p");
		$(pageNum1).css({ "display": "inline", "color": "white" });
		pageNum1.innerText = "目前第";
		let curPageNum = document.createElement("input");
		$(curPageNum).css({ "display": "inline", "width": "30px", "margin-left": "5px", "margin-right": "5px" })
		$(curPageNum).val(table.getPage());
		let pageNum2 = document.createElement("p");
		$(pageNum2).css({ "display": "inline", "color": "white", "margin-right": "3px" });
		pageNum2.innerText = "頁";
		let goBtn2 = document.createElement("button");
		$(currentPageDiv).on("submit", function(event) {
			event.preventDefault();
			let pgNum = $(curPageNum).val();
			if (pgNum <= table.getPageMax() && pgNum > 0) {
				table.setPage(pgNum);
			} else {
				alert("Page number out of range!");
			}
		});
		$(goBtn2).html("go");
		$(goBtn2).addClass("goBtn");
		$(pageNum1).appendTo(currentPageDiv);
		$(curPageNum).insertAfter(pageNum1);
		$(pageNum2).insertAfter(curPageNum);
		$(goBtn2).insertAfter(pageNum2);
		$(currentPageDiv).insertAfter(pageNumDiv);
	}

	//when page loads
	function initialLoad() {
		disableBtns();
		//styling pagination buttons
		stylePaginationBtn();
		createDataNum();
		createDpPerPage();
		createPageNum();
		createCurrentPage();
		table.setLocale("ch-ch");
	}

	initialLoad();
	return table;

}