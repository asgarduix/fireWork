//create table function, takes tableParams([[fieldName, inputType [, selectParams(object) ]]...]) and tabledata
//function createTable(tableParams, tabledata, destination){
//	columns = [
//    {field:"EditButton", formatter:formatterEditButton, cellClick:editButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
//    {field:"CancelButton", formatter:formatterCancelButton, cellClick:cancelButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
//    {field:"SaveButton", formatter:formatterSaveButton, cellClick:saveButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
//    {field:"DeleteButton", formatter:formatterDeleteButton, cellClick:deleteButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
//    {title: "No." ,formatter:"rownum", hozAlign:"center",width:60, headerClick: handleHeaderClick },
//    // {title:"Name", field:"name", editor:"input",sorter:"string", width:200, editable: isEditable, headerClick:disableBtnsTimeout },
//    // {title:"Progress", field:"progress", editor: "number",sorter:"number", formatter:"progress", editable: isEditable, headerClick:disableBtnsTimeout},
//    // {title:"Gender", field:"gender", editor:"select", editorParams:{"male":"male","female":"female"}, sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout},
//    // {title:"Rating", field:"rating", editor:"star",formatter:"star", hozAlign:"center", editable: isEditable, headerClick:disableBtnsTimeout},
//    // {title:"Favourite Color", field:"col", editor:"input", sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout}
//  ]
//  for (let i=0; i < tableParams.length; i++){
//    if (tableParams[i][1] === "select"){
//      columns.push({
//		  title: tableParams[i][0].charAt(0).toUpperCase()+ tableParams[i][0].slice(1),
//		  field: tableParams[i][0],
//      editor: `${tableParams[i][1]}`,
//      editorParams: tableParams[i][2],
//		  sorter: "string",
//		  editable: isEditable,
//		  headerClick: handleHeaderClick
//	  })} else if (tableParams[i][1] === "input"){
//      columns.push({
//		  title: tableParams[i][0].charAt(0).toUpperCase()+ tableParams[i][0].slice(1),
//		  field: tableParams[i][0],
//		  editor: `${tableParams[i][1]}`,
//		  sorter: "string",
//		  editable: isEditable,
//		  headerClick: handleHeaderClick
//    })} else if (tableParams[i][1] === "number"){
//      columns.push({
//		  title: tableParams[i][0].charAt(0).toUpperCase()+ tableParams[i][0].slice(1),
//		  field: tableParams[i][0],
//		  editor: `${tableParams[i][1]}`,
//		  sorter: "number",
//		  editable: isEditable,
//		  headerClick: handleHeaderClick
//    })} else{
//      console.log("....")
//    }
//  }
//}
// make buttons appear disabled
function disableBtns(){
  $(".cancelBtn").css("opacity", 0.2);
  $(".saveBtn").css("opacity", 0.2);
}

function preparedColumns(tableParams, tabledata) {
	var a = new Array();//因應javascript的scope問題，會亂抓變數，故這邊將syntax用的變數限制住
	columns = [
		{title:"編輯",
			field:"edit", 
			width:70,align:"center", 
			formatter:function(cell, formatterParams, onRendered){
				if (editting) {
	        		currentRow = cell.getRow();
	  	          currentColumn = cell.getColumn();
	  	          currentColumn.hide();
				}else {
					return '<button type="button" class="btn-editt">編輯</button>';
				}
				},
			cellClick:function(e, cell){
	          currentRow = cell.getRow();
	          currentTable = cell.getTable();
	          selectedRows = currentTable.getSelectedRows();
	          tableFunObj.showUpdateCancelHideEditDelete(cell);
	          
	          if(selectedRows.length == 0){
	              for (i = 4; i < selectedRows.length; i++) {
	                  selectedRows[i].deselect();
	                  selectedRows[i].reformat();
	              }
	              currentTable.deselectRow();
	              currentRow.select();
	              currentRow.reformat();
	              
	              cells = currentRow.getCells();
	              for (i = 4; i < cells.length; i++) {
	                  cells[i].setValue(cells[i].getValue());
	              }
	              // 更改下拉選單
	              let iinscls = cell.getData().iinscls;
	              ajaxFunObj.loadDDLIINSKind_501Type2("table",iinscls);
	          }
	          pageFunObj.disableAllFun();
	      }
	  },
	  {title:"刪除",field:"delete",width:70,align:"center",
	      formatter:function(cell, formatterParams, onRendered){
	    	  if (editting) {
	        		currentRow = cell.getRow();
	  	          currentColumn = cell.getColumn();
	  	          currentColumn.hide();
				}else {
					return '<button type="button" class="btn-del">刪除</button>'
				}
	    	  },
	      cellClick:function(e,cell){
	        if(checkIsNullSpace(cell.getData().dkeyin)){
	            // 新增列
	            cell.getRow().delete();
	        }else{
	            let iinskind = cell.getData().iinskind;
	            let iinscls = cell.getData().iinscls;
	            let iclause = cell.getData().iclause;
	            let res = ajaxFunObj.delData(iinskind,iinscls,iclause);
	            if('1' === res.message){
	                cell.getRow().delete();
	            }
	        }
	      }
	  },
	  {title:"更新",field:"update",width:70,align:"center",
	      formatter:function(cell, formatterParams, onRendered){
	    	  if (!editting) {
	        		currentRow = cell.getRow();
	  	          currentColumn = cell.getColumn();
	  	          currentColumn.hide();
				}else {
					return '<button type="button" class="btn-update">更新</button>'
				}
	    	  },
	      cellClick:function(e, cell){
	        if (!cell.getRow().isSelected()){
	          return;
	        }
	      if(checkIsNullSpace(cell.getData().iinskind) || 
			 checkIsNullSpace(cell.getData().iclause) || 
			 checkIsNullSpace(cell.getData().iinscls)){
	    	     alert("請正確填寫資料");
	    	 return;
	      }
	      let res;
	      // 建檔日期 undefined 為新增
	      if(undefined == cell.getData().dkeyin){
	          let ncmnpClauseRelMap = {
	              "obj" : {
	                  "iinskind" : cell.getData().iinskind,
	                  "qserial" : cell.getData().qserial,
	                  "iclause" : cell.getData().iclause,
	                  "ikeyin" : cell.getData().ikeyin,
	                  "dkeyin" : new Date(),
	                  "iinscls" : cell.getData().iinscls
	              }
	           }
	          res = ajaxFunObj.addData(ncmnpClauseRelMap);
	          cell.getData().dkeyin =  ncmnpClauseRelMap.obj.dkeyin;
	      }else{
		    currentRow = cell.getRow()
		    cells = currentRow.getCells();
		    let oldIinskind = cells[5].getOldValue();
		    let oldIinscls = cells[4].getOldValue();
		    let oldIclause = cells[6].getOldValue();
		    let ncmnpClauseRelDtoMap = {
		          "obj" : {
		              "iinskind" : cell.getData().iinskind,
		              "qserial" : cell.getData().qserial,
		              "iclause" : cell.getData().iclause,
		              "ikeyin" : cell.getData().ikeyin,
		              "dkeyin" : new Date(),
		              "iinscls" : cell.getData().iinscls,
		              "oldIinskind" : oldIinskind,
		              "oldIinscls" : oldIinscls,
		              "oldIclause" : oldIclause
		          }
		    }
		    res = ajaxFunObj.updateData(ncmnpClauseRelDtoMap);
	      }
//	        tableFunObj.showEditDeleteHideUpdateCancel(cell);
	        pageFunObj.enableAllfun();
	        tableFunObj.stopEditing(cell);
	      }
	  },
	    {title:"取消",field:"cancel",width:70,align:"center",
//		  visible: false,
	        formatter:function(cell, formatterParams, onRendered){
	        	if (!editting) {
	        		currentRow = cell.getRow();
	  	          currentColumn = cell.getColumn();
	  	          currentColumn.hide();
				}else {
					return '<button type="button" class="btn-del">刪除</button>'
				}
        	},
//	        onClick:function(e, cell, val, data){},
	        cellClick:function(e,cell){
	              if (!cell.getRow().isSelected()){
	                    return
	              }
	              if(checkIsNullSpace(cell.getData().dkeyin)){
	                  // 新增列
	                  cell.getRow().delete();
	              }
	              currentRow = cell.getRow();
	              if (cell.getRow().isSelected()){
	                cells = currentRow.getCells()
	                  for (i = 4; i < cells.length; i++) {
	                      // console.log(cells[i].getOldValue());
	                      cells[i].restoreOldValue();
	                  }
	              }           
//	              tableFunObj.showEditDeleteHideUpdateCancel(cell);
	              pageFunObj.enableAllfun();
	              tableFunObj.stopEditing(cell);
	        }
	    }]
	for (let i = 0; i < tableParams.length; i++) {
		// console.log(JSON.stringify(tabledata[i]));
		// var data = JSON.stringify(tabledata[i]);
		// console.log(tabledata[i]);
		if (tableParams[i][1] === "select") {
			a.push("");//此容器變數為限制script問題使用，在這邊須給一個供後續方便計算位置
			columns.push({
				title : tableParams[i][0].charAt(0).toUpperCase()
						+ tableParams[i][0].slice(1),
				field : tableParams[i][2],
				width : 130,
				editor : `${tableParams[i][1]}`,
				editorParams : tableParams[i][2],
				sorter : "string",
				editable : isEditable,
				headerClick : handleHeaderClick
			})
		} else if (tableParams[i][1] === "input") {
			a.push("");
			columns.push({
				title : tableParams[i][0].charAt(0).toUpperCase()
						+ tableParams[i][0].slice(1),
				field : tableParams[i][2],
				width : 130,
				editor : `${tableParams[i][1]}`,
				sorter : "string",
				editable : isEditable,
				headerClick : handleHeaderClick
			})
		} else if (tableParams[i][1] === "number") {
			a.push("");
			columns.push({
				title : tableParams[i][0].charAt(0).toUpperCase()
						+ tableParams[i][0].slice(1),
				field : tableParams[i][2],
				width : 130,
				editor : `${tableParams[i][1]}`,
				editorParams : {
					min : 0
				},
				sorter : "number",
				editable : isEditable,
				headerClick : handleHeaderClick
			})
		} else if (tableParams[i][1] === "url") {
			a.push("");
			columns.push({
				title : tableParams[i][0].charAt(0).toUpperCase()
						+ tableParams[i][0].slice(1),
				field : tableParams[i][2],
				width : 130,
				editor : `${tableParams[i][1]}`,
				sorter : "string",
				editable : isEditable,
				headerClick : handleHeaderClick,
				formatter : function(cell) {
					sessionStorage.setItem("preCellData", JSON.stringify(cell
							.getData()));
					return tabledata[0][`${tableParams[i][2]}`];
				}
			})
		} else if (tableParams[i][1] === "button") {
			a.push("");
			columns.push({
				field : tableParams[i][2],
				cellClick:function(e, cell){
//					var data = cell.getData();
//					var val = cell.getValue();
//					var row = cell.getRow();
//					var cellElement = cell.getElement();
//					var tableId = $(cellElement).parents('.tabulator')[0].id;
//					sessionStorage.setItem('tableId', '#' + tableId);
					
					// delRow(data.id);
					},
				formatter : function(cell) {
					var data = cell.getData();
					var tableId = $(this)[0].table.element.id + '';
					switch (tableParams[i][3]) {
					case "edit":
						if (rowIsSelected) {
							return '<button type="button" class="btn-update" onclick="updateRow(' + data.id + ', \'#' + tableId + '\'); return false;">更新</button>'
							+ '<button type="button" class="btn-del">取消</button>'
						}else {
							return '<button type="button" class="btn-editt" onclick="edittRow(' + data.id + ', \'#' + tableId + '\'); return false;">編輯</button>'
						}
						break;
					case "pinks":
						return '<button type="button" class="btn-pinks" onclick="pinksRow(' + data.id + ', \'#' + tableId + '\');return false;">複製</button>';
						break;
					default:
						if (rowIsSelected) {
							return '<button type="button" class="btn-update" onclick="updateRow(' + data.id + ', \'#' + tableId + '\');return false;">更新</button>'
							+ '<button type="button" class="btn-del" onclick="delRow(' + data.id + ', \'#' + tableId + '\');return false;">取消</button>';
						}else {
							return '<button type="button" class="btn-editt" onclick="edittRow(' + data.id + ', \'#' + tableId + '\'); return false;">編輯</button>'
							+ '<button type="button" class="btn-del" onclick="delRow(' + data.id + ', \'#' + tableId + '\'); return false;">刪除</button>'
						}
						break;
					}
				},
				headerSort : false,
				hozAlign : "center",
				resizable : false,
				width : 100
			})
		} else if (tableParams[i][1] === "formatter_syntax") {//syntax為tabulator關鍵字，不能使用
		      a.push(tableParams[i][3]);

		      columns.push({
		        title: tableParams[i][0].charAt(0).toUpperCase() + tableParams[i][0].slice(1),
		        field: tableParams[i][0],
//		        editor : 'select',
		        resizable: true,
		        // width: 70,
		        formatter: function foo(cell, formatterParams, onRendered) {
		          //在進入此步前，須將字串準備好
		          onRendered(function () {
		            var defineObj = a[i];
		            renderedFunc4FormatSyntax(cell, defineObj);
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
		    } else {
			console.log("....")
		}
	}
	return columns;
}
// call function when user clicks on header
function handleHeaderClick(){
  setTimeout(function(){
    disableBtns();
    stylePagination();
  },0.1)  
}

// create edit button
function formatterEditButton(cell, formatterParams){
    return "<div class='btn badge badge-pill badge-secondary'>Edit</div>";
}

// call this function whenever the user stops editing
function stopEditing(cell){
  currentRow = cell.getRow()
  currentTable = cell.getTable()
  currentTable.deselectRow()
  currentRow.reformat()
  disableBtns();
  $(`${destination}-reactivityAdd`).attr("disabled", false);
  $(`${destination}-reactivityAdd`).css("opacity", 1);
  $(`${destination}-addToDb`).attr("disabled", false);
  $(`${destination}-addToDb`).css("opacity", 1);
}

// create cancel button
//function formatterCancelButton(cell, formatterParams){
//   return "<div id='cancelBtn' name='cancelBtn' class='cancelBtn btn badge badge-pill badge-warning'>Cancel</div>";
//}
//// create save button
//function formatterSaveButton(cell, formatterParams){
//   return "<div name='saveBtn' class='saveBtn btn badge badge-pill badge-success'>Save</div>";
//}
//// create delete button
//function formatterDeleteButton(cell, formatterParams){
//   return "<div class='btn badge badge-pill badge-danger'>Delete</div>";
//}

// edit function
//function editButtonClick(e, cell){
//  currentRow = cell.getRow()
//  currentTable = cell.getTable()
//  selectedRows = currentTable.getSelectedRows()
//    if (selectedRows.length == 0) {
//      for (i = 0; i < selectedRows.length; i++) {
//        selectedRows[i].deselect()
//        selectedRows[i].reformat()
//      }
//      currentTable.deselectRow()
//      currentRow.select()
//      currentRow.reformat()
//
//      cells = currentRow.getCells()
//      for (i = 0; i < cells.length; i++) {
//        cells[i].setValue(cells[i].getValue());
//      }
//    }
//    currentRow.getCells()[5].edit();
//    $("#reactivity-add").attr("disabled", true);
//    $("#addToDb").attr("disabled", true);
//    $("#reactivity-add").css("opacity", 0.2);
//    $("#addToDb").css("opacity", 0.2);
//    // stylePagination();
//    // document.getElementById("reactivity-add").disabled = true;
//    // document.getElementById("reactivity-add").style.opacity = 0.2;
//  }
  
// cancel function
//function cancelButtonClick(e, cell){
//  if (!cell.getRow().isSelected()){
//    return
//  }
//  currentRow = cell.getRow()
//  currentTable = cell.getTable()
//  if (cell.getRow().isSelected()){
//    // Cancel
//    cells = currentRow.getCells()
//    for (i = 0; i < cells.length; i++) {
//      cells[i].restoreOldValue();
//    }
//    stopEditing(cell);
//  }
//}
// check if cell is editable
function isEditable(cell) {
	if (cell.getField() == 'ikind' || cell.getField() == 'nomprem' || cell.getField() == 'id') {
		return false;
	}
// // 其他輸入框
// if (!edittFlag) {
// return false;
// }
	return cell.getRow().isSelected()

}
// cell.getValue() === null || cell.getValue() === ""

// save function
//function saveButtonClick(e, cell){
//  if (!cell.getRow().isSelected()){
//    return
//  }
//  stopEditing(cell);
//   $(dataSpan).remove();
//  createDataInput();
//  if(table.getPage() !== table.getPageMax()){
//    table.nextPage();
//    table.previousPage();
//  }else{
//    table.previousPage();
//    table.nextPage();
//  }
//}
  
function stylePagination(tableId) {
	$(tableId + " .tabulator-footer").find("button").css({
		"margin-left" : "0.35rem",
		"margin-right" : "0.35rem",
		"background-color" : "#dba0ac",
		"font-size" : "1rem",
		"color" : "#ffffff"
	})
}

function stylePaginationBtn(){
  $(".tabulator-footer").find("button").css({
  "margin-left": "0rem",
  "margin-right": "0rem",
  "background-color": "#dba0ac",
  "font-size":"1rem", 
  "color":"#ffffff" 
  })
}

// delete function
//function deleteButtonClick(e, cell){
//  // if (!cell.getRow().isSelected()){
//  // return
//  // }
//  let rowIndex = cell.getRow().getPosition();
//
//  let id = cell.getData().id;
//  let index;
//  for (let i=0; i< tabledata.length; i++){
//    if (tabledata[i].id === id){
//      index = i;
//    }
//  }
//  // Can use prompt to make them confirm the name
//  if(window.confirm("Delete Data No."+ (rowIndex+1))){
//    stopEditing(cell);
//    cell.getRow().delete();
//    if (index > -1) {
//      tabledata.splice(index, 1);
//    }
//  }
//  stylePagination();
//  disableBtns();
//  $(dataSpan).remove();
//  createDataInput();
//}

// create input element to show number of data points
let dataSpan;
function createDataInput(tab, tableId) {
	dataSpan = document.createElement("span");
// let dataText = document.createElement("p");
// dataText.innerText = "資料筆數:";
	let dataInput = document.createElement("input");
	dataInput.setAttribute("disabled", true);
	$(dataInput).prependTo(dataSpan);
// $(dataText).prependTo(dataSpan);
	$(dataSpan).prepend('資料筆數:');
	$(dataInput).val(tab.getDataCount());
	$(dataSpan).prependTo(tableId + " .tabulator-footer");
}

function removeDataSpan(tab, tableId) {
	var spans = $(tableId).find('span');
	$(spans[0]).remove();
}

// call this function whenever the user stops editing
function stopEditing(cell){
  currentRow = cell.getRow()
  currentTable = cell.getTable()
  currentTable.deselectRow()
  currentRow.reformat()
  disableBtns();
  $(`${destination}-reactivityAdd`).attr("disabled", false);
  $(`${destination}-reactivityAdd`).css("opacity", 1);
  $(`${destination}-addToDb`).attr("disabled", false);
  $(`${destination}-addToDb`).css("opacity", 1);
  // document.getElementById("reactivity-add").disabled = false;
  // document.getElementById("reactivity-add").style.opacity = 1;
}

function checkIfLastPage() {
	if (table.getPage() !== table.getPageMax()) {
		$("#reactivity-add").attr("disabled", true);
		$("#reactivity-add").css("opacity", 0.2);
	} else {
		$("#reactivity-add").attr("disabled", false);
		$("#reactivity-add").css("opacity", 1);
	}
}

// check if cell is editable
function isEditable(cell){
  return cell.getRow().isSelected()  
}
// make buttons appear disabled
function disableBtns() {
	$(".cancelBtn").css("opacity", 0.2);
	$(".saveBtn").css("opacity", 0.2);
}
// cell.getValue() === null || cell.getValue() === ""
// call function when user clicks on header
function handleHeaderClick() {
	setTimeout(function() {
		disableBtns();
		stylePagination();
	}, 0.1)
}


// Build Tabulator
// var table = new Tabulator(`${destination}`, {
// data:tabledata,
// height:"100%",
// layout:"fitColumns",
// layoutColumnsOnNewData:true,
// pagination: "local",
// paginationSize: 10,
// paginationAddRow:"local",
// selectable: false,
// reactiveData:true,
// columns: columns,
// rowAdded: function(row){
// setTimeout(function(){
// currentRow = row;
// currentTable = row.getTable();
// selectedRows = currentTable.getSelectedRows();
// if (selectedRows.length == 0) {
// for (i = 0; i < selectedRows.length; i++) {
// selectedRows[i].deselect()
// selectedRows[i].reformat()
// }
// currentTable.deselectRow()
// currentRow.select()
// currentRow.reformat()
// cells = currentRow.getCells();
// for (i = 0; i < cells.length; i++) {
// cells[i].setValue(cells[i].getValue());
// }
// }
// row.getCells()[5].edit();
// $("#reactivity-add").attr("disabled", true);
// $("#reactivity-add").css("opacity", 0.2);
// $("#addToDb").attr("disabled", true);
// $("#addToDb").css("opacity", 0.2);
// stylePagination();
// },0.1);
// },
// pageLoaded:function(pageno){
// //pageno - the number of the loaded page
// disableBtns();
// stylePagination();
// // checkIfLastPage();
// }
// });




// save data to DB
// document.getElementById("addToDb").addEventListener("click", function(){
// //api call to save to db
// alert("Saved to database!");
// });

function stylePagination(){
  $(".tabulator-footer").find("button").css({
  "margin-left": "0.35rem",
  "margin-right": "0.35rem",
  "background-color": "#dba0ac",
  "font-size":"1rem", 
  "color":"#ffffff" 
  })
}
// Build Tabulator
function createTable(tableParams, tabledata, destination) {
	var table = new Tabulator(`${destination}`, {
		data : tabledata,
		height : "100%",
		layout : "fitColumns",
		layoutColumnsOnNewData : true,
		index:"id", // set the index field to the "id" field
		pagination : "local",
		paginationSize : 10,
		paginationAddRow : "local",
		selectable : false,
		reactiveData : true,
		locale : true,
		langs : {
			"zh-tw" : {
				"pagination" : {
					"page_size" : "每頁筆數",
					"first" : "最前頁",
					"first_title" : "最前頁2",
					"last" : "最尾頁",
					"last_title" : "最尾頁2",
					"prev" : "前頁",
					"prev_title" : "前頁2",
					"next" : "下頁",
					"next_title" : "下頁2",
				},
			}
		},
		columns : preparedColumns(tableParams, tabledata),
		rowAdded: function (row) {
		      setTimeout(function () {
		        currentRow = row;
		        currentTable = row.getTable();
		        selectedRows = currentTable.getSelectedRows();
		        if (selectedRows.length == 0) {
		          for (i = 0; i < selectedRows.length; i++) {
		            selectedRows[i].deselect()
		            selectedRows[i].reformat()
		          }
		          currentTable.deselectRow()
		          currentRow.select()
		          currentRow.reformat()
		          cells = currentRow.getCells();
//		          for (i = 0; i < cells.length; i++) {
//		            cells[i].setValue(cells[i].getValue());
//		          }
		        }
//		        row.getCells()[5].edit();
		        $("#reactivity-add").attr("disabled", true);
		        $("#reactivity-add").css("opacity", 0.2);
		        $("#addToDb").attr("disabled", true);
		        $("#addToDb").css("opacity", 0.2);
		        stylePagination();


		        //document.getElementById("reactivity-add").disabled = true;
		        //document.getElementById("reactivity-add").style.opacity = 0.2;
		      }, 0.1);
		    },
		pageLoaded : function(pageno) {
			// pageno - the number of the loaded page
			disableBtns();
			stylePagination();
			// checkIfLastPage();
		}
	});
	return table;
}

function createFooterInfo(tab, tableId) {
	
// move pagination to the top of the table
	$(tableId + " .tabulator-footer").prependTo(".table-div");
	
// styling pagination
	$(tableId + " .tabulator-footer").css({
		"margin-bottom" : "0.5rem",
		"margin-top" : "1rem",
		"display" : "flex",
		"justify-content" : "center",
		"background-color" : "#dba0ac"
	});
	stylePagination(tableId);
	
// when page loads
	disableBtns();
	createDataInput(tab, tableId);
// checkIfLastPage();
}

function checkIfLastPage(){
  if(table.getPage() !== table.getPageMax()){
    $("#reactivity-add").attr("disabled", true);
    $("#reactivity-add").css("opacity", 0.2);
  }else{
    $("#reactivity-add").attr("disabled", false);
    $("#reactivity-add").css("opacity", 1);
  }
}

// //when page loads
// disableBtns();
// createDataInput();
// //checkIfLastPage();
// }

/**
 * 對應自製下拉選單的function
 * @param {*} cell 
 * @param {*} defineObj 
 */
function renderedFunc4FormatSyntax(cell, defineObj) {
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

      var obj = null;

      //總之先將設定的預設值設定進select
      //整理輸入的參數
      var p = new Array();

      for (var i = 0; i < params.length; i++) {
        var param = params[i];
        var type = param[1];//fix:固定值、other_select:列中其他欄位中select
        var val = param[0];

        if ("fix" == type) {
          p.push(val);
        }

        if ("other_select" == type) {
          var selObj = cell.getRow().getCells()[val].getElement().querySelectorAll("select")[0];
          p.push(selObj.value);
        }
      }

      var obj = func.apply(this, p);
      obj.disabled = true;
      cell.getElement().appendChild(obj);

      if (checkIsNullSpace(cellVal) == true) {
        // console.log("1");
        //設定第一個值為預設值
        var defVal = cell.getElement().querySelectorAll("select")[0].querySelectorAll("option")[0].value;
        cell.getElement().querySelectorAll("select")[0].value = defVal;
        return;
      }

      //使用查詢資料作為預設值
      // var x = cell.getElement().querySelectorAll("select")[0].querySelectorAll("option")[0].value;
      // console.log("2" + " def val:" + cellVal + " x:" + x);
      cell.getElement().querySelectorAll("select")[0].value = cellVal;
    } catch (err) {
      console.log("we have a truble to gen select of col");
      console.log(err);
      cell.getElement().appendChild(document.createElement("select"));
    }
  }
}

let tableFunObj = {
	      showEditDeleteHideUpdateCancel : function(cell){
	    	  currentRow = cell.getRow();
	          currentColumn = cell.getColumn();
	          currentColumn.hide();
	    	  currentRow.showColumn("edit");
	    	  currentRow.showColumn("delete");
	    	  currentRow.hideColumn("update");
	    	  currentRow.hideColumn("cancel");
//	          currentTable = cell.getTable();
//	          currentTable.showColumn("edit");
//	          currentTable.showColumn("delete");
//	          currentTable.hideColumn("update");
//	          currentTable.hideColumn("cancel");
	      },
	      showUpdateCancelHideEditDelete : function(cell){
	    	  currentRow = cell.getRow();
	          currentColumn = cell.getColumn();
	          currentColumn.hide();
	          currentColumn.getNextColumn().hide();
	          currentColumn.getNextColumn().show();
	          currentColumn.getNextColumn().show();
//	    	  currentRow.hideColumn("edit");
//	    	  currentRow.hideColumn("delete");
//	    	  currentRow.showColumn("update");
//	    	  currentRow.showColumn("cancel");
//	          currentTable = cell.getTable();
//	          currentTable.hideColumn("edit");
//	          currentTable.hideColumn("delete");
//	          currentTable.showColumn("update");
//	          currentTable.showColumn("cancel");
	      },
	      stopEditing : function(cell){
	          currentRow = cell.getRow();
	          currentTable = cell.getTable();
	          currentTable.deselectRow();
	          currentRow.reformat();
	      }
	}

let pageFunObj = {
	    chengeAllCount : function(){
	        let rowCount = table.getRows().length;
	        $('#allCount').text(rowCount);
	        let pageCount = Math.ceil(rowCount / $('#pageCount').val());
	        $('#allPages').text(pageCount);
	    },
	    setPageCount : function(){
	        let newPageCount = $('#pageCount').val();
	        table.setPageSize(newPageCount);
	        $('#currentPage').val("1");
	    },
	    goCurrentPage : function(){
	        let newCurrentPage = $('#currentPage').val();
	        table.setPage(newCurrentPage);
	    },
	    disableAllFun : function(){
	        $('#select,#clean,#exit,#exportData,#insert,#setPageCount,#pageCount,#currentPage,#goCurrentPage').attr('disabled', true);
	        $('#ddlIinscls > select,#ddlIinskind > select').attr('disabled', true);
	        $(".tabulator-footer").hide();
	    },
	    enableAllfun : function(){
	        $('#select,#clean,#exit,#exportData,#insert,#setPageCount,#pageCount,#currentPage,#goCurrentPage').attr('disabled', false);
	        $('#ddlIinscls > select,#ddlIinskind > select').attr('disabled', false);
	        $('.btn-update, .btn-cancel').hide();
	        $('.btn-edit, .btn-del').show();
	        $(".tabulator-footer").show();
	    }
	}