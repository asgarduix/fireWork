//create table function, takes tableParams([[fieldName, inputType [, selectParams(object) ]]...]) and tabledata
function createTableWithCopy(tableParams, tabledata, destination, showBtn){

	columns = [
    {field:"EditButton", formatter:formatterEditButton, cellClick:editButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70, visible: showBtn},
    {field:"SaveButton", formatter:formatterSaveButton, cellClick:saveButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70, visible: false},
    {field:"CancelButton", formatter:formatterCancelButton, cellClick:cancelButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70, visible: false},
    {field:"DeleteButton", formatter:formatterDeleteButton, cellClick:deleteButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70, visible: showBtn},
    {field:"CopyButton", formatter:formatterCopyButton, cellClick:copyButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70, visible: showBtn},
    {title: "No." ,formatter:"rownum", hozAlign:"center",width:60, headerClick: handleHeaderClick },
    //{title:"Insurance Type", field:"insType", editor: insEditor, editable: isEditable, headerClick:handleHeaderClick},
    //{title:"Insurance Type1", field:"insType1", editor: insEditor1, editable: isEditable, headerClick:handleHeaderClick},
    //{title: "customSelect", field:"numbers", editor: selectEditor, width: 60},
    //, cellClick: saveValue
    
    // {title:"Name", field:"name", editor:"input",sorter:"string", width:200, editable: isEditable, headerClick:disableBtnsTimeout },
    // {title:"Progress", field:"progress", editor: "number",sorter:"number", formatter:"progress", editable: isEditable, headerClick:disableBtnsTimeout},
    // {title:"Gender", field:"gender", editor:"select", editorParams:{"male":"male","female":"female"}, sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout},
    // {title:"Rating", field:"rating", editor:"star",formatter:"star", hozAlign:"center", editable: isEditable, headerClick:disableBtnsTimeout},
    // {title:"Favourite Color", field:"col", editor:"input", sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout}
  ]
 
  for (let i=0; i < tableParams.length; i++){
    if (tableParams[i][2] === "select"){
      columns.push({
		  title: tableParams[i][1],
		  field: tableParams[i][0],
      editor: `${tableParams[i][2]}`,
      editorParams: {values:tableParams[i][3]},
		  sorter: "string",
      editable: isEditable,
      cellEditing: handleCellEditing,
      cellEditCancelled: handleEditCancelled,
		  headerClick: handleHeaderClick
	  })} else if (tableParams[i][2] === "input"){
      columns.push({
		  title: tableParams[i][1],
		  field: tableParams[i][0],
		  editor: `${tableParams[i][2]}`,
		  sorter: "string",
      editable: isEditable,
      cellEditing: handleCellEditing,
      cellEditCancelled: handleEditCancelled,
		  headerClick: handleHeaderClick
    })} else if (tableParams[i][2] === "uneditableInput"){
      columns.push({
		  title: tableParams[i][1],
		  field: tableParams[i][0],
		  sorter: "string",
      cellEditing: handleCellEditing,
      cellEditCancelled: handleEditCancelled,
		  headerClick: handleHeaderClick
    })} else if (tableParams[i][2] === "number"){
      columns.push({
		  title: tableParams[i][1],
		  field: tableParams[i][0],
		  editor: `${tableParams[i][2]}`,
		  sorter: "number",
      editable: isEditable,
      cellEditing: handleCellEditing,
      cellEditCancelled: handleEditCancelled,
		  headerClick: handleHeaderClick
    })} else if (tableParams[i][2] === "url") {
		  columns.push({
			title : tableParams[i][1],
      field : tableParams[i][0],
			sorter : "string",
      headerClick : handleHeaderClick,
      formatter: function(cell, formatterParams, onRendered ){
        return cell.getValue();
      }
			
    })}else if (tableParams[i][2] === "cusInput") {//僅供測試//syntax為tabulator關鍵字，不能使用
			

			columns.push({
				title : tableParams[i][1],
        field : tableParams[i][0],
        resizable: true,
        editable: isEditable,
				// width: 70,
				editor: function foo(cell, onRendered, success, cancel, editorParams){
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
					onRendered(function(){
						editor.focus();
						editor.style.css = "100%";
					});

					//when the value has been set, trigger the cell to update
					function changeFunc(e){
            let cellNm ;
            for (let j = 0; j < tableParams.length; j++){
              if (tableParams[j][0] == tableParams[i][3].fieldNm){
                cellNm = j
              }
            }
            let cellValue = e.target.value;
            cell.getRow().getCells()[cellNm + 6].setValue(cellValue * tableParams[i][3].params);
            
					}
          function successFunc(){
            success(editor.value);
          }

					editor.addEventListener("change", changeFunc);
					editor.addEventListener("blur", successFunc);

					//return the editor element
					return editor;
					}
			});
		}else if (tableParams[i][2] === "cusInput2") {//僅供測試//syntax為tabulator關鍵字，不能使用
			

			columns.push({
				title : tableParams[i][1],
        field : tableParams[i][0],
        resizable: true,
        editable: isEditable,
				// width: 70,
				editor: function foo(cell, onRendered, success, cancel, editorParams){
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
					onRendered(function(){
						editor.focus();
						editor.style.css = "100%";
					});

					//when the value has been set, trigger the cell to update
					function changeFunc(e){
			            let cellValue = e.target.value;
			            let undistributedVal = $(tableParams[i][3][1]).val();
						
						if (cellValue <= undistributedVal){
							$(tableParams[i][3][1]).val(undistributedVal - cellValue);
							let distributedVal = $(tableParams[i][3][0]).val();
							$(tableParams[i][3][0]).val(Number(distributedVal) + Number(cellValue));
						}else{
							alert("error!")
						}
			            
					}
					
			        function successFunc(){
			        	success(editor.value);
			        }

					editor.addEventListener("change", changeFunc);
					editor.addEventListener("blur", successFunc);

					//return the editor element
					return editor;
					}
			});
		} else{
      console.log("....")
    }
  }

// make buttons appear disabled
function disableBtns(){
  $(".cancelBtn").css("opacity", 0.2);
  $(".saveBtn").css("opacity", 0.2);
 
}
//call function when user clicks on header
function handleHeaderClick(){
  setTimeout(function(){
    disableBtns();
    stylePaginationBtn();
  },0.1)  
}


function handleCellEditing(cell){
  //cell..getElement().style.border = "0.5px solid red";
  
}

function handleEditCancelled(cell){
  //cell.getElement().style.border = "none";
  
}

// function handleCellEdited(cell){
//   cell.getRow().getCells()[6].setValue("");
//   cell.getRow().getCells()[7].setValue("");
// }

// function handleCellEdited1(cell){
//   cell.getRow().getCells()[7].setValue("");
// }



//define data
// var tabledata = [
//     { id:1, name:"Oli Bob", progress:12, gender:"male", rating:1, col:"red", age:24 },
//     { id:2, name:"Mary May", progress:1, gender:"female", rating:2, col:"blue", age:24 },
//     { id:3, name:"Christine Lobowski", progress:42, gender:"female", rating:0, col:"green", age:24 },
//     { id:4, name:"Brendon Philips", progress:100, gender:"male", rating:1, col:"orange", age:24 },
//     { id:5, name:"Margret Marmajuke", progress:16, gender:"female", rating:5, col:"yellow", age:24},
// ];

// const editCheck = function(cell){
//     let position = cell.getRow().getPosition();
//     let data = cell.getValue();
//     return (position === tabledata.length -1 ) && (data === undefined || data === "") ;
// }



//create edit button
function formatterEditButton(cell, formatterParams){
    return "<div class='btn badge badge-pill badge-secondary'>Edit</div>";
}
//create cancel button
function formatterCancelButton(cell, formatterParams){
   return "<div id='cancelBtn' name='cancelBtn' class='cancelBtn btn badge badge-pill badge-warning'>Cancel</div>";
}
//create save button
function formatterSaveButton(cell, formatterParams){
   return "<div name='saveBtn' class='saveBtn btn badge badge-pill badge-success'>Save</div>";
}
//create delete button
function formatterDeleteButton(cell, formatterParams){
   return "<div class='deleteBtn btn badge badge-pill badge-danger'>Delete</div>";
}
//create copy button
function formatterCopyButton(cell, formatterParams){
   return "<div class='copyBtn btn badge badge-pill badge-info'>Copy</div>";
}

//edit function
function editButtonClick(e, cell){
  currentRow = cell.getRow()
  currentTable = cell.getTable()
  selectedRows = currentTable.getSelectedRows()
    if (selectedRows.length == 0) {
      for (i = 0; i < selectedRows.length; i++) {
        selectedRows[i].deselect()
        selectedRows[i].reformat()
      }
      currentTable.deselectRow()
      currentRow.select()
      currentRow.reformat()

      cells = currentRow.getCells()
      for (i = 0; i < cells.length; i++) {
        cells[i].setValue(cells[i].getValue());
      }
    }
    currentRow.getCells()[6].edit();
    currentTable.hideColumn("EditButton");
    currentTable.hideColumn("DeleteButton");
    currentTable.showColumn("CancelButton");
    currentTable.showColumn("SaveButton");
    currentTable.hideColumn("CopyButton");
    $(`${destination}-reactivityAdd`).attr("disabled", true);
    $(`${destination}-addToDb`).attr("disabled", true);
    $(`${destination}-reactivityAdd`).css("opacity", 0.2);
    $(`${destination}-addToDb`).css("opacity", 0.2);
    //$(".copyBtn").css("opacity", 0.2);

    //stylePaginationBtn();
    //document.getElementById("reactivity-add").disabled = true;
    //document.getElementById("reactivity-add").style.opacity = 0.2;
  }
  
//cancel function
function cancelButtonClick(e, cell){
  if (!cell.getRow().isSelected()){
    return
  }
  currentRow = cell.getRow()
  currentTable = cell.getTable()
  if (cell.getRow().isSelected()){
    //Cancel
    cells = currentRow.getCells()
    for (i = 0; i < cells.length; i++) {
      cells[i].restoreOldValue();
    }
    stopEditing(cell);
  }
  currentTable.hideColumn("CancelButton");
  currentTable.hideColumn("SaveButton");
  currentTable.showColumn("EditButton");
  currentTable.showColumn("DeleteButton");
  currentTable.showColumn("CopyButton");
  $(dataDiv).remove();
  $(pageNumDiv).remove();
  createDataNum();
  createPageNum();
}

//save function
function saveButtonClick(e, cell){
  if (!cell.getRow().isSelected()){
    return
  }
  stopEditing(cell);
  $(dataDiv).remove();
  $(pageNumDiv).remove();
  createDataNum();
  createPageNum();
  currentTable.hideColumn("CancelButton");
  currentTable.hideColumn("SaveButton");
  currentTable.showColumn("EditButton");
  currentTable.showColumn("DeleteButton");
  currentTable.showColumn("CopyButton");
  // if(table.getPage() !== table.getPageMax()){
  //   table.nextPage();
  //   table.previousPage();
  // }else{
  //   table.previousPage();
  //   table.nextPage();
  // }
}

//delete function
function deleteButtonClick(e, cell){
  // if (!cell.getRow().isSelected()){
  //   return
  // }
  let rowIndex = cell.getRow().getPosition();

  let id = cell.getData().id;
  let index;
  for (let i=0; i< tabledata.length; i++){
    if (tabledata[i].id === id){
      index = i;
    }
  }
  //Can use prompt to make them confirm the name
  if(window.confirm("Delete Data No."+ (rowIndex+1))){
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
  if (table.getPage() == 1){
    table.nextPage();
    table.previousPage();
  }else{
    table.previousPage();
    table.nextPage();
  }
}


//copy function
function copyButtonClick(e, cell){
  // if (!cell.getRow().isSelected()){
  //     return
  // }
  let curRowIndex = cell.getRow().getPosition();
  let curRowData = cell.getData();
  let dpPerPg = $(dpPerPageDiv).find("input").val();
  if ((curRowIndex + 1) % dpPerPg == 0){
    if (table.getPage() == table.getPageMax()){
      tabledata.push(Object.assign({}, curRowData));
      table.previousPage();
      table.setPage("last");
    }else{
      table.nextPage();
      tabledata.splice(curRowIndex + 1, 0, Object.assign({}, curRowData));
    }
  } else {
    if (curRowIndex == tabledata.length - 1){
      tabledata.push(Object.assign({}, curRowData));
    }else{
      tabledata.splice(curRowIndex + 1, 0, Object.assign({}, curRowData));
    }
  }
  
  // tabledata.splice(curRowIndex + 1, 0, curRowData);
  stylePaginationBtn();
  disableBtns();
  $(dataDiv).remove();
  $(pageNumDiv).remove();
  createDataNum();
  createPageNum();
  table.hideColumn("EditButton");
  table.hideColumn("DeleteButton");
  table.hideColumn("CopyButton");
  table.showColumn("CancelButton");
  table.showColumn("SaveButton");
  $(`${destination}-reactivityAdd`).attr("disabled", true);
  $(`${destination}-addToDb`).attr("disabled", true);
  $(`${destination}-reactivityAdd`).css("opacity", 0.2);
  $(`${destination}-addToDb`).css("opacity", 0.2);

  if (table.getPage() == 1){
    table.nextPage();
    table.previousPage();
  }else{
    table.previousPage();
    table.nextPage();
  }
}


//call this function whenever the user stops editing
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
  $(".copyBtn").css("opacity", 1);
}


//check if cell is editable
function isEditable(cell){
  return cell.getRow().isSelected()  
}


//Build Tabulator
var table = new Tabulator(`${destination}`, {
  data:tabledata,
  height:"100%",
  layout:"fitColumns",
  layoutColumnsOnNewData:true,
  pagination: "local",
  paginationSize: 5,
  paginationAddRow:"local",
  langs:{
        "ch-ch":{ //Mandarin definition
            "columns":{
                "name":"名字",
                "gender":"性別",
                "age":"年齡",
                "insType":"險別",
                "insType1":"保險菜單",
                "insType2":"險種"
            },
            "pagination":{
                "first":"第一頁",
                "last":"最後一頁",
                "prev":"上一頁",
                "next":"下一頁"
            },
        }
  },
  selectable: false,
  reactiveData:true,
  autoResize:true,
  columns: columns,
  rowAdded: function(row){
    setTimeout(function(){
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
        for (i = 0; i < cells.length; i++) {
          cells[i].setValue(cells[i].getValue());
        }
      }
      row.getCells()[6].edit();
      $("#reactivity-add").attr("disabled", true);
      $("#reactivity-add").css("opacity", 0.2);
      $("#addToDb").attr("disabled", true);
      $("#addToDb").css("opacity", 0.2);
      stylePaginationBtn();
    },0.1);
  },
  pageLoaded:function(pageno){
    //pageno - the number of the loaded page
    disableBtns();
    stylePaginationBtn();
    $(currentPageDiv).remove();
    createCurrentPage();
    }
});


//select pagination
let pagination = $(destination).children(".tabulator-footer");


//add row to bottom of table on button click
$(`${destination}-reactivityAdd`).on("click", function(){
  let dpPerPg = $(dpPerPageDiv).find("input").val();
  //table.addRow({ id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male", color:"black"});
  let pushData = {}
  for (let i = 0; i < tableParams.length; i++){
    if (tableParams[i][2] === "select" || tableParams[i][2] === "input"){
      pushData[tableParams[i][0]] = "";
    }else if (tableParams[i][2] === "number"){
      pushData[tableParams[i][0]] = 0;
    }else {
      pushData[tableParams[i][0]] = undefined;
    }
  }
  pushData["id"] = tabledata[tabledata.length-1].id + 1;
  if (table.getPage() == table.getPageMax() && tabledata.length % dpPerPg == 0){
    //tabledata.push({ insType: "", insType1: "", insType2: "", id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male"});
    tabledata.push(pushData);
    table.previousPage();
    table.setPage("last");
  } else {
    table.setPage("last");
    tabledata.push(pushData);
    //tabledata.push({ insType: "", insType1: "", insType2: "", id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male"});
  }
  table.hideColumn("EditButton");
  table.hideColumn("DeleteButton");
  table.showColumn("CancelButton");
  table.showColumn("SaveButton");
  $(`${destination}-reactivityAdd`).attr("disabled", true);
  $(`${destination}-addToDb`).attr("disabled", true);
  $(`${destination}-reactivityAdd`).css("opacity", 0.2);
  $(`${destination}-addToDb`).css("opacity", 0.2);
  
});

//save data to DB
$(`${destination}-addToDb`).on("click", function(){
    //api call to save to db
    alert("Saved to database!");
});

function stylePaginationBtn(){
  pagination.find("button").css({
  "margin-left": "0rem",
  "margin-right": "0rem",
  "background-color": "#dba0ac",
  "font-size":"1rem", 
  "color":"#ffffff" 
  })
}

//move pagination to the top of the table
pagination.prependTo(`${destination}-pagination`) 


//move addRow button into pagination
$(`${destination}-reactivityAdd`).prependTo(pagination);
//style addRow button
setTimeout(function(){$(`${destination}-reactivityAdd`).addClass("addRowBtn")},0.1)


//move confirm button into pagination
$(`${destination}-addToDb`).appendTo(pagination);
//style confirm button 
setTimeout(function(){$(`${destination}-addToDb`).addClass("addToDbBtn")},0.1)


//styling pagination
pagination.css({
  "margin-bottom": "0.5rem",
  "margin-top":"1rem",
  "display": "flex",
  "flex-direction": "row",
  // "justify-content": "space-between",
  "align-items": "center",
  "background-color":"#dba0ac"
});

pagination.children(".tabulator-paginator").css({
  "margin-left": "2rem",
  "margin-right": "4rem"
})



//create element to show number of data points
let dataDiv;
function createDataNum(){
  dataDiv = document.createElement("div");
  $(dataDiv).css({"display":"flex", "width":"75px", "justify-content": "center", "align-items": "center" });
  let dataNum = document.createElement("p");
  $(dataNum).css({"display":"inline", "color":"white"});
  dataNum.innerText = `總共  ${tabledata.length} 筆`;
  $(dataNum).appendTo(dataDiv);
  $(dataDiv).insertAfter(pagination.children(".tabulator-paginator"));
}


//create element to show dps per page
let dpPerPageDiv;
function createDpPerPage(){
  dpPerPageDiv = document.createElement("form");
  $(dpPerPageDiv).css({"display":"flex", "width":"135px", "justify-content": "center", "align-items": "center", "margin-left":"10px"});
  let txt1 = document.createElement("p");
  $(txt1).css({"display":"inline", "color":"white"});
  txt1.innerText = "每頁";
  let dpPerPageNum = document.createElement("input");
  $(dpPerPageNum).css({"display":"inline", "width": "30px", "margin-left":"5px", "margin-right":"5px"})
  $(dpPerPageNum).val(5);
  let txt2 = document.createElement("p");
  $(txt2).css({"display":"inline", "color":"white", "margin-right": "3px"});
  txt2.innerText = "筆";
  let goBtn1 = document.createElement("button");
  $(dpPerPageDiv).on("submit", function(event){
    event.preventDefault();
    let dpPerPg = $(dpPerPageNum).val();
    if (dpPerPg <= tabledata.length && dpPerPg > 0){
      table.setPageSize(dpPerPg);
      $(pageNumDiv).remove();
      createPageNum()
    } else {
      alert("Number out of range!");
    }
     
  });
  // $(goBtn1).css({"display":"inline","width":"40px" ,"color":"black", "background-color": "white", "margin-left": "6px", "border": "0.5px solid black"});
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
function createPageNum(){
  pageNumDiv = document.createElement("div");
  $(pageNumDiv).css({"display":"flex", "width":"75px", "justify-content": "center", "align-items": "center" });
  let pageNum = document.createElement("p");
  $(pageNum).css({"display":"inline", "color":"white"});
  pageNum.innerText = `總共  ${table.getPageMax()} 頁`;
  $(pageNum).appendTo(pageNumDiv);
  //$(pageNumDiv).insertAfter(dataDiv);
  $(pageNumDiv).insertAfter(dpPerPageDiv);
  
}



//create element to show current page number 
let currentPageDiv;
function createCurrentPage(){
  currentPageDiv = document.createElement("form");
  $(currentPageDiv).css({"display":"flex", "width":"150px", "justify-content": "center", "align-items": "center", "margin-left": "40px"});
  let pageNum1 = document.createElement("p");
  $(pageNum1).css({"display":"inline", "color":"white"});
  pageNum1.innerText = "目前第";
  let curPageNum = document.createElement("input");
  $(curPageNum).css({"display":"inline", "width": "30px", "margin-left":"5px", "margin-right":"5px"})
  $(curPageNum).val(table.getPage());
  let pageNum2 = document.createElement("p");
  $(pageNum2).css({"display":"inline", "color":"white", "margin-right":"3px"});
  pageNum2.innerText = "頁";
  let goBtn2 = document.createElement("button");
  $(goBtn2).attr("type", "submit");
  $(currentPageDiv).on("submit", function(event){
    event.preventDefault();
    let pgNum = $(curPageNum).val();
    if (pgNum <= table.getPageMax() && pgNum > 0){
      table.setPage(pgNum);
    } else {
      alert("Page number out of range!");
    }
     
  });
  // $(goBtn2).css({"display":"inline-block","width":"40px" ,"color":"black", "background-color": "white", "margin-left": "4px", "border": "0.5px solid black"});
  $(goBtn2).html("go");
  $(goBtn2).addClass("goBtn");
  $(pageNum1).appendTo(currentPageDiv);
  $(curPageNum).insertAfter(pageNum1);
  $(pageNum2).insertAfter(curPageNum);
  $(goBtn2).insertAfter(pageNum2);
  $(currentPageDiv).insertAfter(pageNumDiv);
  
}


//when page loads
function initialLoad(){
  disableBtns();
  stylePaginationBtn();
  createDataNum();
  createDpPerPage();
  createPageNum();
  createCurrentPage();
  table.setLocale("ch-ch");
}

initialLoad();
return table;
// //add to tabledata
// document.getElementById("confirm").addEventListener("click", function(){
//     document.getElementById("confirm").style.display = "none";
//     document.getElementById("cancel").style.display = "none";
//     document.getElementById("reactivity-add").disabled= false;
//     document.getElementById("reactivity-delete").disabled = false;
// })


// document.getElementById("cancel").addEventListener("click", function(){
//     tabledata.pop();
//     document.getElementById("confirm").style.display = "none";
//     document.getElementById("cancel").style.display = "none";
//     document.getElementById("reactivity-add").disabled= false;
//     document.getElementById("reactivity-delete").disabled = false;
// })

// cellClick: function(e,cell){
    //         let position = cell.getRow().getPosition();
    //         let data = cell.getValue();
    //         // console.log(`position: ${position}`);
    //         // console.log(`tabledata.length: ${tabledata.length}`);
    //         console.log(position === tabledata.length -1)
    //         console.log(data === undefined);
    //         console.log(data);
    //         console.log("---------------")
    //     }
}




