
// function selectedCheck(cell){
//     return cell.getRow().isSelected();
// }

// let columns = [
// 	  {title:"編輯",field:"edit", width:70,align:"center", 
// 	      // editable:
// 	      // function(cell) {
// 	      // return true; },
// 	      formatter:function(cell, formatterParams, onRendered){return "<a href='#'>編輯</a>";},
// 	      cellClick:function(e, cell){
// 	          currentRow = cell.getRow();
// 	          currentTable = cell.getTable();
// 	          selectedRows = currentTable.getSelectedRows();
// 	          tableFunObj.showUpdateCancelHideEditDelete(cell);

// 	          if(selectedRows.length == 0){
// 	              for (i = 4; i < selectedRows.length; i++) {
// 	                  selectedRows[i].deselect();
// 	                  selectedRows[i].reformat();
// 	              }
// 	              currentTable.deselectRow();
// 	              currentRow.select();
// 	              currentRow.reformat();

// 	              cells = currentRow.getCells();
// 	              for (i = 4; i < cells.length; i++) {
// 	                  cells[i].setValue(cells[i].getValue());
// 	              }

// 	              // 更改下拉選單
// 	              let iinscls = cell.getData().iinscls;
// 	              ajaxFunObj.loadDDLIINSKind_501Type2("table",iinscls);
// 	          }
// 	          pageFunObj.disableAllFun();
// 	      }
// 	  },
// 	  {title:"刪除",field:"delete",width:70,align:"center",
// 	      formatter:function(cell, formatterParams, onRendered){return "<a href='#'>刪除</a>";},
// 	      cellClick:function(e,cell){
// 	        if(checkIsNullSpace(cell.getData().dkeyin)){
// 	            // 新增列
// 	            cell.getRow().delete();
// 	        }else{
// 	            let iinskind = cell.getData().iinskind;
// 	            let iinscls = cell.getData().iinscls;
// 	            let iclause = cell.getData().iclause;
// 	            let res = ajaxFunObj.delData(iinskind,iinscls,iclause);
// 	            if('1' === res.message){
// 	                cell.getRow().delete();
// 	            }
// 	        }
// 	      }
// 	  },
// 	  {title:"更新",field:"update",width:70,align:"center",visible: false,
// 	      // editable:
// 	      // function(cell)
// 	      // { return
// 	      // true; },
// 	      formatter:function(cell, formatterParams, onRendered){return "更新";},
// 	      cellClick:function(e, cell){
// 	        if (!cell.getRow().isSelected()){
// 	          return;
// 	        }
// 	      if(checkIsNullSpace(cell.getData().iinskind) || 
// 			 checkIsNullSpace(cell.getData().iclause) || 
// 			 checkIsNullSpace(cell.getData().iinscls)){
// 	    	     alert("請正確填寫資料");
// 	    	 return;
// 	      }
// 	      let res;
// 	      // 建檔日期 undefined 為新增
// 	      if(undefined == cell.getData().dkeyin){
// 	          let ncmnpClauseRelMap = {
// 	              "obj" : {
// 	                  "iinskind" : cell.getData().iinskind,
// 	                  "qserial" : cell.getData().qserial,
// 	                  "iclause" : cell.getData().iclause,
// 	                  "ikeyin" : cell.getData().ikeyin,
// 	                  "dkeyin" : new Date(),
// 	                  "iinscls" : cell.getData().iinscls
// 	              }
// 	           }
// 	          res = ajaxFunObj.addData(ncmnpClauseRelMap);
// 	          cell.getData().dkeyin =  ncmnpClauseRelMap.obj.dkeyin;
// 	      }else{
// 		    currentRow = cell.getRow()
// 		    cells = currentRow.getCells();
// 		    let oldIinskind = cells[5].getOldValue();
// 		    let oldIinscls = cells[4].getOldValue();
// 		    let oldIclause = cells[6].getOldValue();
// 		    let ncmnpClauseRelDtoMap = {
// 		          "obj" : {
// 		              "iinskind" : cell.getData().iinskind,
// 		              "qserial" : cell.getData().qserial,
// 		              "iclause" : cell.getData().iclause,
// 		              "ikeyin" : cell.getData().ikeyin,
// 		              "dkeyin" : new Date(),
// 		              "iinscls" : cell.getData().iinscls,
// 		              "oldIinskind" : oldIinskind,
// 		              "oldIinscls" : oldIinscls,
// 		              "oldIclause" : oldIclause
// 		          }
// 		    }
// 		    res = ajaxFunObj.updateData(ncmnpClauseRelDtoMap);
// 	      }
// 	        tableFunObj.showEditDeleteHideUpdateCancel(cell);
// 	        pageFunObj.enableAllfun();
// 	        tableFunObj.stopEditing(cell);
// 	      }
// 	  },
// 	    {title:"取消",field:"cancel",width:70,align:"center",visible: false,
// 	        formatter:function(cell, formatterParams, onRendered){return "取消";},
// 	        onClick:function(e, cell, val, data){},
// 	        cellClick:function(e,cell){
// 	              if (!cell.getRow().isSelected()){
// 	                    return
// 	              }
// 	              if(checkIsNullSpace(cell.getData().dkeyin)){
// 	                  // 新增列
// 	                  cell.getRow().delete();
// 	              }
// 	              currentRow = cell.getRow();
// 	              if (cell.getRow().isSelected()){
// 	                cells = currentRow.getCells()
// 	                  for (i = 4; i < cells.length; i++) {
// 	                      // console.log(cells[i].getOldValue());
// 	                      cells[i].restoreOldValue();
// 	                  }
// 	              }           
// 	              tableFunObj.showEditDeleteHideUpdateCancel(cell);
// 	              pageFunObj.enableAllfun();
// 	              tableFunObj.stopEditing(cell);
// 	        }
// 	    },
// 	    {
// 	        title:"險別代號",
// 	        field:"iinscls",
// 	        width:100,
// 	        editor:"select",
// 	        editable: selectedCheck,
// 	        editorParams:function(cell){
// 	            // let rows = table.getRows();
// 	            let options = iinsclsTableObj;
// 	            // rows.forEach(function(row){
// 	            // var data = row.getData();
// 	            // options[data.iinscls] = data.iinscls;
// 	            // });
// 	            return options;
// 	        },cellEdited:function(cell){
// 	            ajaxFunObj.loadDDLIINSKind_501Type2("table",cell.getData().iinscls);
// 	        },
// 	        formatter:function(cell, formatterParams, onRendered) {
// 	            return iinsclsTableObj[cell.getData().iinscls];
// 	        }
// 	    },
// 	    {
// 	        title:"保單類別",
// 	        field:"iinskind",
// 	        width:300,
// 	        editor:"select",
// 	        editable: selectedCheck,
// 	        editorParams:function(cell){
// 	            let rows = table.getRows();
// 	            let options = iinskindTableObj;
// 	            return options;
// 	        },
// 	        formatter:function(cell, formatterParams, onRendered) {
// 	          if(undefined === cell.getData().iinscls){
// 	            return null;
// 	          }else {
// 	            ajaxFunObj.loadDDLIINSKind_501Type2("table",cell.getData().iinscls);
// 	            return iinskindTableObj[cell.getData().iinskind];
// 	          }
// 	        }
// 	    },
// 	    {
// 	        title:"條款代號/條款名稱",
// 	        field:"iclause",
// 	        editor:"select",
// 	        editable: selectedCheck,
// 	        editorParams:function(cell){
// 	            let rows = table.getRows();
// 	            let options = iinskindTableObj;
// 	            // rows.forEach(function(row){
// 	            // let data = row.getData();
// 	            // options[data.iclause] = data.iclause;
// 	            // });
// 	            return options;
// 	        }
// 	    },
// 	    {
// 	        title:"條款順序",
// 	        field:"qserial",
// 	        editor:"number", 
// 	        editable:selectedCheck,
// 	        width:100
// 	    },
// 	    {
// 	        title:"建檔人員",
// 	        field:"ikeyin",
// 	        editor:"textarea", 
// 	        editable:selectedCheck,
// 	        width:100
// 	    },
// 	    {
// 	        title:"建檔日期",
// 	        field:"dkeyin",
// 	        align:"center",
// 	        width:140,
// 	        formatter:function(cell, formatterParams, onRendered){
// 	            if(checkIsNullSpace(cell.getData().dkeyin)){
// 	                return null;
// 	            }else{
// 	                return formatDateYYYMMDD(new Date(cell.getData().dkeyin));
// 	            }
// 	        }
// 	    }
// 	];
$(function () {
	const columns = [
		["sel1", "selectCus", { "funcNm": crtDdlLoadDDLIINSCLSType2, "params": null, "eventFunc": changeToSel2 }],
		["sel2", "selectCus", {
			"funcNm": crtDdlLoadDDLIINSKind_501Type2, "params": [["A", "fix"], [5, "other_select"]], "eventFunc": null
		}],
		["sel3", "selectCus", {
			//如果為純數字，則為使用數字中的位置找欄位資料
			"funcNm": crtDdlLoadDDLNCMNpInstypeType2, "params": [["", "fix"]], "eventFunc": null
		}],
		["sequence", "input"],
		["oid", "input"],
		["iinskind", "input"],
		["qserial", "input"],
		["iclause", "input"],
		["ikeyin", "input"],
		["dkeyin", "input"],
		["iinscls", "input"],
		["oldIinskind", "input"],
		["oldIinscls", "input"],
		["oldIclause", "input"]
	];

	var data = ajaxPostTokenReady("../../CMN306000Api/queryAll", null, false).data;
	//console.log(JSON.stringify(data));
	createTable(columns, data, "#mainData", true);
	
});

function changeToSel2(val, thisobj) {
  var target = fetchTarget(thisobj, 6);
  target.find("select").html("");
  var sel2 = crtDdlLoadDDLIINSKind_501Type2("Y", val);
  var options = sel2.innerHTML;
  target.find("select").append(options);
}