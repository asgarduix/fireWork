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

//create table function, takes colDefines([[fieldName, inputType [, selectParams(object) ]]...]) and tabledata

function createTableddlref(colDefines, tabledata, destination, showBtn, methods) {
  col = {};


  for (let i = 0; i < colDefines.length; i++) {
    var colName = colDefines[i][0];
    var colShowName = colDefines[i][1];
    col[colName] = colShowName;
  }
  debugger
  columns = [
    { field: "EditButton", formatter: formatterEditButton, cellClick: editButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: showBtn },
    { field: "SaveButton", formatter: formatterSaveButton, cellClick: saveButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: false },
    { field: "CancelButton", formatter: formatterCancelButton, cellClick: cancelButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: false },
    { field: "DeleteButton", formatter: formatterDeleteButton, cellClick: deleteButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: showBtn },
    { title: "No.", formatter: "rownum", hozAlign: "center", width: 60, headerClick: handleHeaderClick },
    // {title:"Name", field:"name", editor:"input",sorter:"string", width:200, editable: isEditable, headerClick:disableBtnsTimeout },
    // {title:"Progress", field:"progress", editor: "number",sorter:"number", formatter:"progress", editable: isEditable, headerClick:disableBtnsTimeout},
    // {title:"Gender", field:"gender", editor:"select", editorParams:{"male":"male","female":"female"}, sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout},
    // {title:"Rating", field:"rating", editor:"star",formatter:"star", hozAlign:"center", editable: isEditable, headerClick:disableBtnsTimeout},
    // {title:"Favourite Color", field:"col", editor:"input", sorter:"string", editable: isEditable, headerClick:disableBtnsTimeout}
  ];

  var defineObjTmp = new Array();//因應javascript的scope問題，會亂抓變數，故這邊將syntax用的變數限制住

  for (let i = 0; i < colDefines.length; i++) {
    var param = colDefines[i];

    if (param[2] === "select") {
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
    } else if (param[2] === "display") {
      defineObjTmp.push("");

      columns.push({
        title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
        field: param[0],
        sorter: "string",
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
          onRendered(function () {
            var defineObj = defineObjTmp[i];
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
    } else if (param[2] === "url") {
      defineObjTmp.push("");
      columns.push({
        title: param[0].charAt(0).toUpperCase() + param[0].slice(1),
        field: param[0],
        width: 130,
        //      editor : `${tableParams[i][2]}`,
        sorter: "string",
        editable: isEditable,
        headerClick: handleHeaderClick,
        formatter: "link",
        formatterParams: {
          label: function (cell) {
            return "保單明細"
          }
        }
        //          formatter: function (cell) {
        //            return tabledata[0][`${param[0]}`];
        //          }
      })
    } else {
      console.log("....");
    }
  }

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
    setTimeout(function () {
      disableBtns();
      stylePagination();
    }, 0.1)
  }

  //create edit button
  function formatterEditButton(cell, formatterParams, onRendered) {
    onRendered(function () {
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
    return "<div id='cancelBtn' name='cancelBtn' class='cancelBtn btn badge badge-pill badge-warning'>Cancel</div>";
  }

  //create save button
  function formatterSaveButton(cell, formatterParams) {
    return "<div name='saveBtn' class='saveBtn btn badge badge-pill badge-success'>Save</div>";
  }

  //create delete button
  function formatterDeleteButton(cell, formatterParams) {
    return "<div class='btn badge badge-pill badge-danger'>Delete</div>";
  }

  //edit function
  function editButtonClick(e, cell) {
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

      var scanerRes = scanSelPosiInRow(cell);
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
    $(`${destination}-reactivityAdd`).attr("disabled", true);
    $(`${destination}-addToDb`).attr("disabled", true);
    $(`${destination}-reactivityAdd`).css("opacity", 0.2);
    $(`${destination}-addToDb`).css("opacity", 0.2);
    //stylePagination();
    //document.getElementById("reactivity-add").disabled = true;
    //document.getElementById("reactivity-add").style.opacity = 0.2;

    //methods.edit(e, cell);

    //$(".tabulator-footer").hide();
    //methods.edit(e, cell);

  }

  //cancel function
  function cancelButtonClick(e, cell) {
    if (!cell.getRow().isSelected()) {
      return
    }
    currentRow = cell.getRow()
    currentTable = cell.getTable()
    if (cell.getRow().isSelected()) {
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
    $(dataDiv).remove();
    $(pageNumDiv).remove();
    createDataNum();
    createPageNum();

    //methods.cancel(e, cell);

    //$(".tabulator-footer").show();
    //methods.cancel(e, cell);

  }

  /**
   * 掃描列中含下拉選單物件的欄位置
   * @param {} cell 
   */
  function scanSelPosiInRow(cell) {
    var cells = cell.getRow().getCells();
    var selPosis = new Array();
    var inputPosis = new Array();

    for (var i = 0; i < cells.length; i++) {
      var x = cells[i].getElement().querySelectorAll("select");

      if (checkIsNullSpace(x) == false && x.length > 0) {
        selPosis.push(i);
      } else {
        inputPosis.push(i);
      }
    }

    var res = {
      "select": selPosis,
      "other": inputPosis
    };

    return res;
  }

  createTableWithDdlRef_modifiedPosi = [];//TODO 待發佈此變數

  //save function
  function saveButtonClick(e, cell) {
    if (!cell.getRow().isSelected()) {
      return;
    }

    //取得資料位置
    var index = cell.getRow().getIndex();
    var cells = cell.getRow().getCells();

    //掃描select位置
    var selPosis = scanSelPosiInRow(cell).select;

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

      var o = cells[i].getValue();
      var val = cells[i].getElement().querySelectorAll("select")[0].value;

      cells[i].setValue(val, true);

      var m = cells[i].getValue();
      modifyRes.push({ "orgin_cell_data": o, "new_cell_data": m });
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
    //methods.update(e,cell);
    console.log("editor_msg-row_index:" + index + ", judge_modified:" + isModified + ", data:" + JSON.stringify(modifyRes));

    //關閉可編輯設定
    stopEditing(cell);
    $(dataDiv).remove();
    $(pageNumDiv).remove();
    createDataNum();
    createPageNum();

    currentTable.hideColumn("CancelButton");
    currentTable.hideColumn("SaveButton");
    currentTable.showColumn("EditButton");
    currentTable.showColumn("DeleteButton");
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

      //if(methods.del(e,cell)){
      stopEditing(cell);
      cell.getRow().delete();
      if (index > -1) {
        tabledata.splice(index, 1);
      }
      //}else{

      //}
    }
    stylePaginationBtn();
    disableBtns();
    $(dataDiv).remove();
    $(pageNumDiv).remove();
    createDataNum();
    createPageNum();
    if (table.getPage() == 1) {
      table.nextPage();
      table.previousPage();
    } else {
      table.previousPage();
      table.nextPage();
    }
    ////
  }

  //call this function whenever the user stops editing
  function stopEditing(cell) {
    currentRow = cell.getRow();
    currentTable = cell.getTable();
    currentTable.deselectRow();
    currentRow.reformat();
    disableBtns();
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
    rowAdded: function (row) {
      setTimeout(function () {
        currentRow = row;
        currentTable = row.getTable();
        currentTable.deselectRow()
        currentRow.select()
        currentRow.reformat()
        let cell = row.getCells()[0];
        stopEditing(cell);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

          var scanerRes = scanSelPosiInRow(cell);
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
        //     $(`${destination}-reactivityAdd`).attr("disabled", true);
        //     $(`${destination}-reactivityAdd`).css("opacity", 0.2);
        //     $(`${destination}-addToDb`).attr("disabled", true);
        //     $(`${destination}-addToDb`).css("opacity", 0.2);
        //     stylePaginationBtn();

      }, 0.1);
    },
    pageLoaded: function (pageno) {
      //pageno - the number of the loaded page
      disableBtns();
      stylePaginationBtn();
      $(currentPageDiv).remove();
      createCurrentPage();
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
    //   }
    // }

  });


  //select pagination
  let pagination = $(destination).children(".tabulator-footer");


  //add row to bottom of table on button click
  $(`${destination}-reactivityAdd`).on("click", function () {
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
    pushData["id"] = tabledata[tabledata.length - 1].id + 1;
    if (table.getPage() == table.getPageMax() && tabledata.length % dpPerPg == 0) {
      //tabledata.push({ insType: "", insType1: "", insType2: "", id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male"});
      tabledata.push(pushData);
      table.previousPage();
      table.nextPage();
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


    // table.hideColumn("EditButton");
    // table.hideColumn("DeleteButton");
    // table.showColumn("CancelButton");
    // table.showColumn("SaveButton");
    // $(".tabulator-footer").hide();
  });




  //save data to DB
  $(`${destination}-addToDb`).on("click", function () {
    //api call to save to db
    console.log(table.getData());
    console.log("Saved to database!");
  });

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


  //move addRow button into pagination
  $(`${destination}-reactivityAdd`).prependTo(pagination);
  //style addRow button
  setTimeout(function () { $(`${destination}-reactivityAdd`).addClass("addRowBtn") }, 0.1)


  //move confirm button into pagination
  $(`${destination}-addToDb`).appendTo(pagination);
  //style confirm button
  setTimeout(function () { $(`${destination}-addToDb`).addClass("addToDbBtn") }, 0.1)

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
    dataNum.innerText = `總共  ${tabledata.length} 筆`;
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
    $(dpPerPageDiv).on("submit", function (event) {
      event.preventDefault();
      let dpPerPg = $(dpPerPageNum).val();
      if (dpPerPg <= tabledata.length && dpPerPg > 0) {
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
    $(currentPageDiv).on("submit", function (event) {
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