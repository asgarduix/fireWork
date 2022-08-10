function disableBtns(){
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

function disableBtnsTimeout(){
  setTimeout(disableBtns,1);
}


//define data
var tabledata = [
    { id:1, name:"Oli Bob", progress:12, gender:"male", rating:1, col:"red" },
    { id:2, name:"Mary May", progress:1, gender:"female", rating:2, col:"blue" },
    { id:3, name:"Christine Lobowski", progress:42, gender:"female", rating:0, col:"green" },
    { id:4, name:"Brendon Philips", progress:100, gender:"male", rating:1, col:"orange" },
    { id:5, name:"Margret Marmajuke", progress:16, gender:"female", rating:5, col:"yellow"},
];

// const editCheck = function(cell){
//     let position = cell.getRow().getPosition();
//     let data = cell.getValue();
//     return (position === tabledata.length -1 ) && (data === undefined || data === "") ;
// }


function formatterEditButton(cell, formatterParams){
    return "<div class='btn badge badge-pill badge-secondary'>Edit</div>";
}

function formatterCancelButton(cell, formatterParams){
   return "<div id='cancelBtn' name='cancelBtn' class='cancelBtn btn badge badge-pill badge-warning'>Cancel</div>";
}

function formatterSaveButton(cell, formatterParams){
   return "<div name='saveBtn' class='saveBtn btn badge badge-pill badge-success'>Save</div>";
}

function formatterDeleteButton(cell, formatterParams){
   return "<div class='btn badge badge-pill badge-danger'>Delete</div>";
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
      
      // currentTable.hideColumn("EditButton");
      // currentTable.showColumn("CancelButton");
      // currentTable.showColumn("DeleteButton");
      // currentTable.showColumn("SaveButton");
    }
    $("#reactivity-add").attr("disabled", true);
    $("#reactivity-add").css("opacity", 0.2);
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
}

//save function
function saveButtonClick(e, cell){
  if (!cell.getRow().isSelected()){
    return
  }
  stopEditing(cell);
  
}

//delete function
function deleteButtonClick(e, cell){
  // if (!cell.getRow().isSelected()){
  //   return
  // }
  

  let id = cell.getData().id;
  let index;
  for (let i=0; i< tabledata.length; i++){
    if (tabledata[i].id === id){
      index = i;
    }
  }
  //Can use prompt to make them confirm the name
  if(window.confirm("Delete Data No."+ (index+1))){
    stopEditing(cell);
    cell.getRow().delete();
    if (index > -1) {
      tabledata.splice(index, 1);
    }
  }
}

function stopEditing(cell){
  currentRow = cell.getRow()
  currentTable = cell.getTable()
  currentTable.deselectRow()
  currentRow.reformat()
  disableBtns();
  $("#reactivity-add").attr("disabled", false);
  $("#reactivity-add").css("opacity", 1);
  //document.getElementById("reactivity-add").disabled = false;
  //document.getElementById("reactivity-add").style.opacity = 1;
}

function isEditable(cell){
  return cell.getRow().isSelected()  
}
//cell.getValue() === null || cell.getValue() === ""


//Build Tabulator
var table = new Tabulator("#example-table", {
  data:tabledata,
  height:"100%",
  layout:"fitColumns",
  layoutColumnsOnNewData:true,
  selectable: false,
  reactiveData:true,
  columns:[
    {field:"EditButton", formatter:formatterEditButton, cellClick:editButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
    {field:"CancelButton", formatter:formatterCancelButton, cellClick:cancelButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
    {field:"SaveButton", formatter:formatterSaveButton, cellClick:saveButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
    {field:"DeleteButton", formatter:formatterDeleteButton, cellClick:deleteButtonClick, headerSort:false, hozAlign:"center", resizable:false,width: 70},
    {title: "No." ,formatter:"rownum", hozAlign:"center",width:60, headerClick:disableBtnsTimeout },
    {title:"Name", field:"name", editor:"input",sorter:"string", width:200, editable: isEditable, headerClick:disableBtnsTimeout },
    {title:"Progress", field:"progress", editor: "number",sorter:"number", formatter:"progress", editable: isEditable, headerClick:disableBtnsTimeout},
    {title:"Gender", field:"gender", editor:"select", editorParams:{"male":"male","female":"female"}, sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout},
    {title:"Rating", field:"rating", editor:"star",formatter:"star", hozAlign:"center", editable: isEditable, headerClick:disableBtnsTimeout},
    {title:"Favourite Color", field:"col", editor:"input", sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout}
  ],
  rowAdded: function(row){
    currentRow = row;
    currentTable = row.getTable();
    console.log(row);
    console.log(currentTable.getRowFromPosition(4))
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
    $("#reactivity-add").attr("disabled", true);
    $("#reactivity-add").css("opacity", 0.2);
    
    //document.getElementById("reactivity-add").disabled = true;
    //document.getElementById("reactivity-add").style.opacity = 0.2;
  } 
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
  //     $("#reactivity-add").attr("disabled", true);
  //     $("#reactivity-add").css("opacity", 0.2);
      
  //   //document.getElementById("reactivity-add").disabled = true;
  //   //document.getElementById("reactivity-add").style.opacity = 0.2;
  //   }
  // }

});



//add row to bottom of table on button click
document.getElementById("reactivity-add").addEventListener("click", function(){
  //table.addRow();
  tabledata.push({ id: tabledata[tabledata.length-1].id + 1, name:"User", progress: null, gender:"male", rating: null, col:"blue"});
    
});

//save data to DB
document.getElementById("addToDb").addEventListener("click", function(){
    //api call to save to db
    alert("Saved to database!");
});



disableBtns();












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