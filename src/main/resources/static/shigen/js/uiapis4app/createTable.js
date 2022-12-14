//create table function, takes tableParams([[fieldName, inputType [, selectParams(object) ]]...]) and tabledata
function createTable(tableParams, tabledata, destination, showBtn) {
    col = {};

    for (let i = 0; i < tableParams.length; i++) {
        var colName = tableParams[i][0];
        var colShowName = tableParams[i][1];
        col[colName] = colShowName;
    }

    console.log(col);
    //  debugger
    columns = [
        { field: "EditButton", formatter: formatterEditButton, cellClick: editButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: showBtn },
        { field: "SaveButton", formatter: formatterSaveButton, cellClick: saveButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: false },
        { field: "CancelButton", formatter: formatterCancelButton, cellClick: cancelButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: false },
        { field: "DeleteButton", formatter: formatterDeleteButton, cellClick: deleteButtonClick, headerSort: false, hozAlign: "center", resizable: false, width: 70, visible: showBtn },
        { title: "No.", formatter: "rownum", hozAlign: "center", width: 60, headerClick: handleHeaderClick },
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

    for (let i = 0; i < tableParams.length; i++) {
        if (tableParams[i][2] === "select") {
            columns.push({
                title: tableParams[i][1],
                field: tableParams[i][0],
                editor: `${tableParams[i][2]}`,
                editorParams: { values: tableParams[i][3] },
                sorter: "string",
                editable: isEditable,
                cellEditing: handleCellEditing,
                cellEditCancelled: handleEditCancelled,
                headerClick: handleHeaderClick
            })
        } else if (tableParams[i][2] === "input") {
            columns.push({
                title: tableParams[i][1],
                field: tableParams[i][0],
                editor: `${tableParams[i][2]}`,
                sorter: "string",
                editable: isEditable,
                cellEditing: handleCellEditing,
                cellEditCancelled: handleEditCancelled,
                headerClick: handleHeaderClick
            })
        } else if (tableParams[i][2] === "number") {
            columns.push({
                title: tableParams[i][1],
                field: tableParams[i][0],
                editor: `${tableParams[i][2]}`,
                sorter: "number",
                editable: isEditable,
                cellEditing: handleCellEditing,
                cellEditCancelled: handleEditCancelled,
                headerClick: handleHeaderClick
            })
        } else if (tableParams[i][2] === "url") {
            columns.push({
                title: tableParams[i][1],
                field: tableParams[i][0],
                sorter: "string",
                headerClick: handleHeaderClick,
                formatter: function(cell, formatterParams, onRendered) {
                    return cell.getValue();
                }

                // 		})} else{
                //       columns.push({
                //         title: tableParams[i][1],
                //         field: tableParams[i][0],
                //         width: 130,
                // //			editor : `${tableParams[i][2]}`,
                //         sorter: "string",
                //         editable: isEditable,
                //         headerClick: handleHeaderClick,
                //         formatter: function (cell) {
                //           sessionStorage.setItem("preCellData", JSON.stringify(cell
                //             .getData()));
                //           return tabledata[0][`${tableParams[i][0]}`];
                //         }
                //       })
            })
        } else {
            console.log("....")
        }
    }

    // make buttons appear disabled
    function disableBtns() {
        $(".cancelBtn").css("opacity", 0.2);
        $(".saveBtn").css("opacity", 0.2);

    }
    //call function when user clicks on header
    function handleHeaderClick() {
        setTimeout(function() {
            disableBtns();
            stylePaginationBtn();
        }, 0.1)
    }


    function handleCellEditing(cell) {
        //cell..getElement().style.border = "0.5px solid red";

    }

    function handleEditCancelled(cell) {
        //cell.getElement().style.border = "none";

    }

    // function handleCellEdited(cell){
    //   cell.getRow().getCells()[6].setValue("");
    //   cell.getRow().getCells()[7].setValue("");
    // }

    // function handleCellEdited1(cell){
    //   cell.getRow().getCells()[7].setValue("");
    // }


    // function insFunc1(cell){
    //     //create a options list of all values from another column in the table

    //     var rows = table.getRows();
    //     let values = {};

    //     // var row = cell.getRow();

    //     //rows.forEach(function(row){
    //         //switch(row.getData().insType)
    //         switch (cell.getRow().getData().insType) {
    //           case "A":
    //             values={};
    //              //for (let i=0; i< apiData1.length; i++){
    //              //values[apiData1[i].value] = apiData1[i].text;
    //              //}
    //             values = myMap1;
    //             //values["A"] = "A";
    //             //values["B"] = "B";
    //             //values["C"] = "C";
    //             break;
    //           case "I":
    //             values={};
    //             values["D"] = "D";
    //             values["E"] = "E";
    //             values["F"] = "F";
    //             break;
    //           case "H":
    //             values={};
    //             values["G"] = "G";
    //             values["H"] = "H";
    //             values["I"] = "I";
    //             break;
    //           default:
    //             values={};
    //             values["default"] = "default";
    //             break;
    //           }
    //    // });
    //       return { values:values }
    // }


    // function insFunc2(cell){
    //     //create a options list of all values from another column in the table

    //     //var rows = table.getRows();
    //     let values = {};

    //     // var row = cell.getRow();

    //     //rows.forEach(function(row){
    //         //switch(row.getData().insType)
    //         switch (cell.getRow().getData().insType1) {
    //           case "B00":
    //             values={};
    //              //for (let i=0; i< apiData1.length; i++){
    //              //values[apiData1[i].value] = apiData1[i].text;
    //              //}
    //             values = myMap2;
    //             //values["A"] = "A";
    //             //values["B"] = "B";
    //             //values["C"] = "C";
    //             break;
    //           case "C00":
    //             values={};
    //             values["D"] = "D";
    //             values["E"] = "E";
    //             values["F"] = "F";
    //             break;
    //           case "D00":
    //             values={};
    //             values["G"] = "G";
    //             values["H"] = "H";
    //             values["I"] = "I";
    //             break;
    //           default:
    //             values={};
    //             values["default"] = "default";
    //             break;
    //           }
    //    // });
    //       return { values:values }
    // }


    // function insEditor(cell, onRendered, success, cancel, editorParams){

    //     //cell - the cell component for the editable cell
    //     //onRendered - function to call when the editor has been rendered
    //     //success - function to call to pass the successfuly updated value to Tabulator
    //     //cancel - function to call to abort the edit and return to a normal cell
    //     //editorParams - params object passed into the editorParams column definition property

    //     var editor = crtDdlLoadDDLIINSCLSType2("Y");
    //     //create and style editor
    //     //var editor = document.createElement("select");
    //     //editor.appendChild(new Option(1, 1));
    //     //editor.appendChild(new Option(2, 2));
    //     //editor.appendChild(new Option(3, 3));
    //     //editor.setAttribute("type", "");

    //     //create and style input

    //     editor.style.height = "100%";
    //     //editor.style.border = "0.5px solid red";
    //     editor.style.width = "100%";
    //     editor.style.boxSizing = "border-box";

    //     //Set value of editor to the current value of the cell
    //     //editor.value = moment(cell.getValue(), "DD/MM/YYYY").format("YYYY-MM-DD")
    //       editor.value = cell.getValue();

    //     //set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
    //     onRendered(function(){
    //         editor.focus();
    //         editor.style.css = "100%";
    //         editor.style.border = "0.6px solid red";

    //     });

    //     //when the value has been set, trigger the cell to update
    //     function onchangeFunc(){
    //         //success(moment(editor.value, "YYYY-MM-DD").format("DD/MM/YYYY"));
    //         success(editor.value);
    //         let nextCell = cell.getRow().getCells()[6].getElement();
    //         if (cell.getValue() == "H"){
    //           //console.log(nextCell);
    //           editor1 = crtDdlLoadDDLIINSKind_501Type2("Y")
    //           //console.log(cell.getElement());
    //           // let select = document.createElement("select");
    //           //select.appendChild(new Option(1, 1));
    //           // nextCell.appendChild(select);
    //         }

    //         // select.appendChild(new Option(1, 1));
    //         // select.appendChild(new Option(2, 2));
    //         // select.appendChild(new Option(3, 3));
    //         // success(nextCell.appendChild(select));
    //         //console.log(nextSelect);
    //         //console.log(nextCell);

    //     }

    //     function successFunc(){
    //         //success(moment(editor.value, "YYYY-MM-DD").format("DD/MM/YYYY"));
    //         success(editor.value);      
    //     }

    //     editor.addEventListener("change", onchangeFunc);
    //     editor.addEventListener("blur", successFunc);

    //     //return the editor element
    //     return editor;
    // };


    // let editor1;
    // function insEditor1(cell, onRendered, success, cancel, editorParams){

    //     //cell - the cell component for the editable cell
    //     //onRendered - function to call when the editor has been rendered
    //     //success - function to call to pass the successfuly updated value to Tabulator
    //     //cancel - function to call to abort the edit and return to a normal cell
    //     //editorParams - params object passed into the editorParams column definition property
    //      editor1 = document.createElement("select");

    //     //var editor1 = crtDdlLoadDDLIINSKind_501Type2("Y");
    //     //create and style editor
    //     //var editor = document.createElement("select");
    //     //editor.appendChild(new Option(1, 1));
    //     //editor.appendChild(new Option(2, 2));
    //     //editor.appendChild(new Option(3, 3));
    //     //editor.setAttribute("type", "");

    //     //create and style input

    //     editor1.style.height = "100%";
    //     //editor.style.border = "0.5px solid red";
    //     editor1.style.width = "100%";
    //     editor1.style.boxSizing = "border-box";

    //     //Set value of editor to the current value of the cell
    //     //editor.value = moment(cell.getValue(), "DD/MM/YYYY").format("YYYY-MM-DD")
    //       editor1.value = cell.getValue();

    //     //set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
    //     onRendered(function(){
    //         editor1.focus();
    //         editor1.style.css = "100%";
    //         editor1.style.border = "0.6px solid red";
    //         if (cell.getRow().getCells()[5].getValue() == "A"){
    //           $(editor1).empty();
    //           var apiData = crtDdlLoadDDLIINSKind_501Type2Val();
    //           for (let i = 0; i < apiData.length; i++) {
    // 		        newOption = new Option(`${apiData[i].text}`, `${apiData[i].value}`);
    // 		        editor1.add(newOption, undefined);
    //         	}
    //           //editor1 = crtDdlLoadDDLNCMNpInstypeType2("Y");
    //         }

    //     });

    //     //when the value has been set, trigger the cell to update
    //     function successFunc(){
    //         //success(moment(editor.value, "YYYY-MM-DD").format("DD/MM/YYYY"));
    //         success(editor1.value);
    //         // console.log(typeof cell.getValue());
    //     }

    //     editor1.addEventListener("change", successFunc);
    //     editor1.addEventListener("blur", successFunc);

    //     //return the editor element
    //     return editor1;
    // };
    // function dateEditor(cell, onRendered, success, cancel, editorParams){
    //     //cell - the cell component for the editable cell
    //     //onRendered - function to call when the editor has been rendered
    //     //success - function to call to pass the successfuly updated value to Tabulator
    //     //cancel - function to call to abort the edit and return to a normal cell
    //     //editorParams - params object passed into the editorParams column definition property

    //     //create and style editor
    //     var editor = document.createElement("input");

    //     editor.setAttribute("type", "date");

    //     //create and style input
    //     editor.style.padding = "3px";
    //     editor.style.width = "100%";
    //     editor.style.boxSizing = "border-box";

    //     //Set value of editor to the current value of the cell
    //     //editor.value = moment(cell.getValue(), "DD/MM/YYYY").format("YYYY-MM-DD")
    //       editor.value = cell.getValue();
    //     //set focus on the select box when the editor is selected (timeout allows for editor to be added to DOM)
    //     onRendered(function(){
    //         editor.focus();
    //         editor.style.css = "100%";
    //     });

    //     //when the value has been set, trigger the cell to update
    //     function successFunc(){
    //         //success(moment(editor.value, "YYYY-MM-DD").format("DD/MM/YYYY"));
    //         success(editor.value);
    //     }

    //     editor.addEventListener("change", successFunc);
    //     editor.addEventListener("blur", successFunc);

    //     //return the editor element
    //     return editor;
    // };


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
    function formatterEditButton(cell, formatterParams) {
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
        return "<div class='deleteBtn btn badge badge-pill badge-danger'>Delete</div>";
    }

    //edit function
    function editButtonClick(e, cell) {
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
        currentRow.getCells()[5].edit();
        currentTable.hideColumn("EditButton");
        currentTable.hideColumn("DeleteButton");
        currentTable.showColumn("CancelButton");
        currentTable.showColumn("SaveButton");
        $(`${destination}-reactivityAdd`).attr("disabled", true);
        $(`${destination}-addToDb`).attr("disabled", true);
        $(`${destination}-reactivityAdd`).css("opacity", 0.2);
        $(`${destination}-addToDb`).css("opacity", 0.2);
        //stylePaginationBtn();
        //document.getElementById("reactivity-add").disabled = true;
        //document.getElementById("reactivity-add").style.opacity = 0.2;
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
    }

    //save function
    function saveButtonClick(e, cell) {
        if (!cell.getRow().isSelected()) {
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
        // if(table.getPage() !== table.getPageMax()){
        //   table.nextPage();
        //   table.previousPage();
        // }else{
        //   table.previousPage();
        //   table.nextPage();
        // }
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
        if (table.getPage() == 1) {
            table.nextPage();
            table.previousPage();
        } else {
            table.previousPage();
            table.nextPage();
        }
    }

    //call this function whenever the user stops editing
    function stopEditing(cell) {
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


    //check if cell is editable
    function isEditable(cell) {
        return cell.getRow().isSelected()
    }


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
                //     "name":"??????",
                //     "gender":"??????",
                //     "age":"??????",
                //     "insType":"??????",
                //     "insType1":"????????????",
                //     "insType2":"??????"
                // },
                "pagination": {
                    "first": "?????????",
                    "last": "????????????",
                    "prev": "?????????",
                    "next": "?????????"
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
                //      row.getCells()[5].edit();
                $("#reactivity-add").attr("disabled", true);
                $("#reactivity-add").css("opacity", 0.2);
                $("#addToDb").attr("disabled", true);
                $("#addToDb").css("opacity", 0.2);
                stylePaginationBtn();
            }, 0.1);
        },
        pageLoaded: function(pageno) {
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
    $(`${destination}-reactivityAdd`).on("click", function() {
        let dpPerPg = $(dpPerPageDiv).find("input").val();
        //table.addRow({ id: tabledata[tabledata.length-1].id + 1, name:"User",age: 23, gender:"male", color:"black"});
        let pushData = {}
        for (let i = 0; i < tableParams.length; i++) {
            if (tableParams[i][2] === "select" || tableParams[i][2] === "input") {
                pushData[tableParams[i][0]] = "";
            } else if (tableParams[i][2] === "number") {
                pushData[tableParams[i][0]] = 0;
            } else {
                pushData[tableParams[i][0]] = undefined;
            }
        }
        //tabledata = table.getData();

        pushData["id"] = tabledata[tabledata.length - 1].id + 1;
        if (table.getPage() == table.getPageMax() && tabledata.length % dpPerPg == 0) {
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
    $(`${destination}-addToDb`).on("click", function() {
        //api call to save to db
        alert("Saved to database!");
    });

    function stylePaginationBtn() {
        pagination.find("button").css({
            "margin-left": "0rem",
            "margin-right": "0rem",
            "background-color": "#dba0ac",
            "font-size": "1rem",
            "color": "#ffffff"
        })
    }

    //move pagination to the top of the table
    pagination.prependTo(`${destination}-pagination`)


    //move addRow button into pagination
    //$(`${destination}-reactivityAdd`).prependTo(pagination);
    var reactivityAdd = document.getElementById(`${destination}-reactivityAdd`);
    reactivityAdd.prepend(pagination);

    //style addRow button
    setTimeout(function() { $(`${destination}-reactivityAdd`).addClass("addRowBtn") }, 0.1)


    //move confirm button into pagination
    //$(`${destination}-addToDb`).appendTo(pagination);
    var addToDb = document.getElementById(`${destination}-addToDb`);
    pagination.appendChild(addToDb);

    //style confirm button 
    setTimeout(function() { $(`${destination}-addToDb`).addClass("addToDbBtn") }, 0.1)


    //styling pagination
    pagination.css({
        "margin-bottom": "0.5rem",
        "margin-top": "1rem",
        "display": "flex",
        "flex-direction": "row",
        // "justify-content": "space-between",
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
        dataNum.innerText = `??????  ${tabledata.length} ???`;
        //$(dataNum).appendTo(dataDiv);
        dataDiv.appendChild(dataNum);

        //$(dataDiv).insertAfter(pagination.children(".tabulator-paginator"));
        dataDiv.after(pagination.children(".tabulator-paginator"))
    }


    //create element to show dps per page
    let dpPerPageDiv;

    function createDpPerPage() {
        dpPerPageDiv = document.createElement("form");
        $(dpPerPageDiv).css({ "display": "flex", "width": "135px", "justify-content": "center", "align-items": "center", "margin-left": "10px" });
        let txt1 = document.createElement("p");
        $(txt1).css({ "display": "inline", "color": "white" });
        txt1.innerText = "??????";
        let dpPerPageNum = document.createElement("input");
        $(dpPerPageNum).css({ "display": "inline", "width": "30px", "margin-left": "5px", "margin-right": "5px" })
        $(dpPerPageNum).val(5);
        let txt2 = document.createElement("p");
        $(txt2).css({ "display": "inline", "color": "white", "margin-right": "3px" });
        txt2.innerText = "???";
        let goBtn1 = document.createElement("button");
        $(dpPerPageDiv).on("submit", function(event) {
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
        // $(goBtn1).css({"display":"inline","width":"40px" ,"color":"black", "background-color": "white", "margin-left": "6px", "border": "0.5px solid black"});
        $(goBtn1).html("go");
        $(goBtn1).addClass("goBtn");
        //$(txt1).appendTo(dpPerPageDiv);
        dpPerPageDiv.appendChild(txt1);
        //$(dpPerPageNum).insertAfter(txt1);
        dpPerPageNum.after(txt1)
            //$(txt2).insertAfter(dpPerPageNum);
        txt2.after(dpPerPageNum);
        //$(goBtn1).insertAfter(txt2);
        goBtn1.after(txt2);
        //$(dpPerPageDiv).insertAfter(dataDiv);
        dpPerPageDiv.after(dataDiv);
    }


    //create element to show number of pages
    let pageNumDiv;

    function createPageNum() {
        pageNumDiv = document.createElement("div");
        $(pageNumDiv).css({ "display": "flex", "width": "75px", "justify-content": "center", "align-items": "center" });
        let pageNum = document.createElement("p");
        $(pageNum).css({ "display": "inline", "color": "white" });
        pageNum.innerText = `??????  ${table.getPageMax()} ???`;
        //$(pageNum).appendTo(pageNumDiv);
        pageNumDiv.appendChild(pageNum);
        //$(pageNumDiv).insertAfter(dataDiv);
        //$(pageNumDiv).insertAfter(dpPerPageDiv);
        pageNumDiv.after(dpPerPageDiv);
    }



    //create element to show current page number 
    let currentPageDiv;

    function createCurrentPage() {
        currentPageDiv = document.createElement("form");
        $(currentPageDiv).css({ "display": "flex", "width": "150px", "justify-content": "center", "align-items": "center", "margin-left": "40px" });
        let pageNum1 = document.createElement("p");
        $(pageNum1).css({ "display": "inline", "color": "white" });
        pageNum1.innerText = "?????????";
        let curPageNum = document.createElement("input");
        $(curPageNum).css({ "display": "inline", "width": "30px", "margin-left": "5px", "margin-right": "5px" })
        $(curPageNum).val(table.getPage());
        let pageNum2 = document.createElement("p");
        $(pageNum2).css({ "display": "inline", "color": "white", "margin-right": "3px" });
        pageNum2.innerText = "???";
        let goBtn2 = document.createElement("button");
        $(goBtn2).attr("type", "submit");
        $(currentPageDiv).on("submit", function(event) {
            event.preventDefault();
            let pgNum = $(curPageNum).val();
            if (pgNum <= table.getPageMax() && pgNum > 0) {
                table.setPage(pgNum);
            } else {
                alert("Page number out of range!");
            }

        });
        // $(goBtn2).css({"display":"inline-block","width":"40px" ,"color":"black", "background-color": "white", "margin-left": "4px", "border": "0.5px solid black"});
        $(goBtn2).html("go");
        $(goBtn2).addClass("goBtn");
        //$(pageNum1).appendTo(currentPageDiv);
        pageNum1.appendChild(currentPageDiv);
        //$(curPageNum).insertAfter(pageNum1);
        curPageNum.after(pageNum1);
        //$(pageNum2).insertAfter(curPageNum);
        pageNum2.after(curPageNum);
        //$(goBtn2).insertAfter(pageNum2);
        goBtn2.after(pageNum2);
        //$(currentPageDiv).insertAfter(pageNumDiv);
        currentPageDiv.after(pageNumDiv);

    }


    //when page loads
    function initialLoad() {
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

    //return table;
}