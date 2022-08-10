$(function () {
  //進畫面時就先查相關資料，底層會自動進行cache
  crtDdlLoadDDLIINSKind_501Type2("", "H");
  crtDdlLoadDDLIINSKind_501Type2("", "I");
  // crtDdlLoadDDLIINSKind_501Type2("", "L");//TODO L沒資料...待問
  crtDdlLoadDDLIINSKind_501Type2("", "A");

  //$("#posi1")[0].appendChild(sel1);
  // $("#posi1").append(sel1.outerHTML);
  //$("#posi2").append(sel2.outerHTML);
  //$("#posi3").append(sel3.outerHTML);

  //測試資料產生
  const tabledata = new Array();

  for (var i = 0; i < 100; i++) {
    var m = parseInt((Math.random() * 10) + "");
    // console.log((m % 3));

    if ((m % 2) == 0) {
      var m2 = parseInt((Math.random() * 10) + "");
      var sel1Val = (m2 == 0 ? "I" : "A");
      tabledata.push({ id: i, name: "Oli Bob" + " " + m, age: 24, gender: "male", color: "red", sel1: sel1Val, sel2: "221", sel3: "3081001" });
    }

    if ((m % 2) == 1) {
      var m2 = parseInt((Math.random() * 10) + "");
      var sel1Val = (m2 == 0 ? "H" : "A");
      tabledata.push({ id: i, name: "Oli Bob" + " " + m, age: 24, gender: "male", color: "red", sel1: sel1Val, sel2: "221", sel3: "2212022" });
    }

    // if ((m % 3) == 2) {
    // 	tabledata.push({ id: i, name: "Oli Bob" + " " + m, age: 24, gender: "male", color: "red", sel1: "A" });
    // }
  }

  //榜釘select事件
  // sel1.addEventListener("change", function (event) {
  // 	alert(event.target.value);
  // 	var obj = eventFunc(event.target.value);
  // })

  // sel1.addEventListener("change", function (event) {
  // 	alert(event.target.value);
  // })

  // console.log(sel1.outerHTML);

  const tableParams = [
    ["sel1", "sel1","selectCus", { "funcNm": crtDdlLoadDDLIINSCLSType2, "params": null, "eventFunc": changeToSel2 }],
    // ["sel1", "selectCus", { "context": sel1, "params": [["A", "fix"]] }],
    // ["sel1", "selectCus", { "context": crtDdlLoadDDLIINSCLSType2, "params": ["Y"] }],
    ["sel2", "", "selectCus", {
      // "context": sel2, "params": ["Y"]
      //如果為純數字，則為使用數字中的位置找欄位資料
      "funcNm": crtDdlLoadDDLIINSKind_501Type2, "params": [["A", "fix"], [5, "other_select"]], "eventFunc": null
    }],
    ["sel3", "sel3", "selectCus", {
      //  "context": sel3
      //如果為純數字，則為使用數字中的位置找欄位資料
      "funcNm": crtDdlLoadDDLNCMNpInstypeType2, "params": [["", "fix"]], "eventFunc": null
    }],
    ["name", "name", "input"],
    ["age", "age", "number"],
    ["gender", "gender", "select", { "male": "male", "female": "female" }],
    ["color", "color", "select", { "red": "red", "blue": "blue", "green": "green", "orange": "orange", "yellow": "yellow", "white": "white", "black": "black" }]
  ];

  createTableddlref(tableParams, tabledata, "#example-table");

  // addToDb
  $("#addToDb").click(function () {
    var rows = _tabu.getRows();

    var resList = new Array();

    for (var i = 0; i < rows.length; i++) {
      var cols = rows[i].getCells();
      var res = {};

      for (var j = 5; j < cols.length; j++) {
        res[cols[j].getField()] = cols[j].getValue();
      }

      resList.push(res);
    }

    console.log(resList);
    alert(resList);
  });
});

// $(sel1).attr("onchange", "changeToSel2(event.target.value, this);return false;");
// $(sel2).attr("onchange", "changeToSel3(event.target.value, this);return false;");

function changeToSel2(val, thisobj) {
  var target = fetchTarget(thisobj, 6);
  target.find("select").html("");
  var sel2 = crtDdlLoadDDLIINSKind_501Type2("Y", val);
  var options = sel2.innerHTML;
  target.find("select").append(options);
}

function changeToSel3(val, thisobj) {
  var target = fetchTarget(thisobj, 8);
  target.find("select").html("");
  var sel3 = crtDdlLoadDDLNCMNpInstypeType2("Y");
  var options = sel3.innerHTML;
  target.find("select").append(options);
}