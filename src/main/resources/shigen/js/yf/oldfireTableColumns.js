
function createTableColumns(colDefines) {


	function columnFormat(colDefine) {
		let column
		var param = colDefine;
		if (param[0] === "checkbox") {
			//用於有換頁的table，全選按鈕僅選取當頁
			column = {
				formatter: "rowSelection",
				titleFormatter: titleCheckboxFormatter,
				titleFormatterParams: param[1],	// default = { selectOnlyPage: false, showBtn: false }
				headerHozAlign: "center",
				hozAlign: "center",
				headerSort: false,
				resizable: false,
				cellClick: function (e, cell) {
					e.stopPropagation();
					cell.getRow().toggleSelect();
				},
				cellMouseEnter(e, cell) {
					cell.getElement().classList.add("tabulator-defaultCursor")
				},
				width: 60
			};
		} else if (param[0] === "group") {
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				headerHozAlign: "center",
				columns: param[2].map(arry => { return columnFormat(arry) })
			};
		} else if (param[0] === "rownum") {
			// 不隨資料排序而變動，僅表示表格資料索引
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				formatter: "rownum",
				headerSort: false,
				headerHozAlign: "center",
				hozAlign: "center",
				width: 60,
				headerClick: handleHeaderClick
			};
		} else if (param[0] === "button") {
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				formatter: buttonFormatt,
				formatterParams: param[2],	//formatter中需要的參數由此傳入
				headerHozAlign: "center",
				hozAlign: "center",
				headerSort: false,
				// resizable: false,
				// width: 60,	//未定 也許由外部輸入而非定植
				headerClick: handleHeaderClick
			};
		} else if (param[2] === "select") {
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				headerHozAlign: "center",
				hozAlign: "center",
				headerSort: false,
//				editable: editCheck,
				editable:true,
				editor:"select",
				editorParams:{
					values: ["1.再保佣金", "2.盈餘佣金", "blue", "orange"]
				}
			}
		}  else if (param[2] === "input") {
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				headerHozAlign: "center",
				editor: "input",
				// sorter: "string",
				editable: editCheck,
				headerClick: handleHeaderClick,
			};
		} else if (param[2] === "inputCenter") {
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				editor: "input",
				// sorter: "string",
				editable: editCheck,
				headerClick: handleHeaderClick,
				headerHozAlign: "center",
				hozAlign: "center",
			};
		} else if (param[2] === "number") {
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				editor: "input",
				sorter: "number",
				editable: editCheck,
				headerClick: handleHeaderClick,
				headerHozAlign: "center",
				hozAlign: "right",
			};
		} else if (param[2] === "money") {
			//全部幣值以 0,0.00 格式顯示
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				editor: `input`,
				sorter: "number",
				formatter: customMoneyFormatter, //"money",
				editable: editCheck,
				headerHozAlign: "center",
				hozAlign: "right"
			};
		} else if (param[2] === "money1") {
			//全部幣值以 0,0 格式顯示 (純台幣使用)
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				editor: `input`,
				sorter: "number",
				formatter: customMoneyFormatter1, //"money",
				editable: editCheck,
				headerHozAlign: "center",
				hozAlign: "right"
			};
		} else if (param[2] === "money2") {
			//用以需判定幣別者
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				editor: `input`,
				sorter: "number",
				formatter: customMoneyFormatter2,
				formatterParams: param[3],	//formatter中需要的參數由此傳入
				editable: editCheck,
				headerHozAlign: "center",
				hozAlign: "right"
			};
		} else if (param[2] === "money3") {
			//純台幣使用
			//數字不為0者為紅色
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				editor: `input`,
				sorter: "number",
				formatter: customMoneyFormatter3, //"money",
				editable: editCheck,
				headerHozAlign: "center",
				hozAlign: "right"
			};
		} else if (param[2] === "custom") {
			//純台幣使用
			//數字不為0者為紅色
			column = {
				title: param[1].charAt(0).toUpperCase() + param[1].slice(1),
				field: param[0],
				editor: `input`,
				editable: editCheck,
				headerHozAlign: "center",
				hozAlign: "left"
			};
			column = {...column, ...param[3]}
		}
		return column
	}

	function handleHeaderClick(e, column) {

	}

	function editCheck(cell) {
		// console.log(cell.getTable().getSelectedRows())
		// return cell.getRow().isSelected()
		// console.log(!!cell.getRow().getData().edit)
		return !!cell.getRow().getData().edit
	}


	//button
	function buttonFormatt(cell, formatterParams, onRendered) {
		let div = document.createElement('div');
		let row = cell.getRow()
		formatterParams.forEach(btnConfig => {
			let btn = document.createElement('button');
			btn.innerText = btnConfig.name
			btn.addEventListener("click", () => { btnConfig.func(row) });
			div.appendChild(btn)
		});
		return div
	}

	//money formatter 其他外幣使用
	function customMoneyFormatter(cell, formatterParams, onRendered) {
		if (cell.getValue() < 0) {
			cell.getElement().style.color = "#FF0000";
		}
		let formatted = cell.getValue();
		if (cell.getValue()) {
			formatted = numeral(cell.getValue()).format("0,0.00")
		}
		return formatted;
	}

	//money formatter 台幣用
	function customMoneyFormatter1(cell, formatterParams, onRendered) {
		if (cell.getValue() < 0) {
			cell.getElement().style.color = "#FF0000";
		}
		let formatted = cell.getValue();
		if (cell.getValue()) {
			formatted = numeral(cell.getValue()).format("0,0")
		}
		return formatted;
	}

	//money formatter 需辨別幣別者
	function customMoneyFormatter2(cell, formatterParams, onRendered) {
		let currKey = formatterParams.currency;
		let currency = cell.getRow().getData()[currKey]
		if (cell.getValue() < 0) {
			cell.getElement().style.color = "#FF0000";
		}
		let formatted = cell.getValue();
		if (cell.getValue()) {
			if (/(TWD)|(台幣)/.test(currency)) {
				formatted = numeral(cell.getValue()).format("0,0")
			} else {
				formatted = numeral(cell.getValue()).format("0,0.00")
			}
		}
		return formatted;
	}

	//money formatter 台幣用 不為0者改紅色
	function customMoneyFormatter3(cell, formatterParams, onRendered) {
		if (cell.getValue() !== 0) {
			cell.getElement().style.color = "#FF0000";
		}
		let formatted = cell.getValue();
		if (cell.getValue()) {
			formatted = numeral(cell.getValue()).format("0,0")
		}
		return formatted;
	}

	function titleCheckboxFormatter(cell, formatterParams, onRendered) {
		//用於有換頁的table，全選按鈕僅選取當頁
		let table = this.table
		let div = document.createElement('div');
		if (formatterParams.showBtn) {
			let checkbox = document.createElement('input');
			checkbox.setAttribute("class", "tabulator-select")
			checkbox.setAttribute("type", "checkbox")
			checkbox.addEventListener('change', function (e) {
				let checked = e.target.checked
				if (formatterParams.selectOnlyPage) {
					let curPage = table.getPage();
					let dpPerPg = table.getPageSize();
					let startIndex = (curPage - 1) * dpPerPg;
					let endIndex = startIndex + dpPerPg;
					let rows = table.getRows().slice(startIndex, endIndex);
					rows.forEach((row) => {
						checked ? row.select() : row.deselect()
					})
				} else {
					
					checked ? table.selectRow() : table.deselectRow()
					// let rows = table.getRows()
					// rows.forEach((row) => {
					// 	checked ? row.select() : row.deselect()
					// })
				}
			});
			div.appendChild(checkbox)
		}
		return div
	}

	let columns = colDefines.map((colDefine) => { return columnFormat(colDefine) })

	return columns;

}