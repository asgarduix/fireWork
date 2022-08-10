const Batch002 = locationHrefKeepDataFetch();

// tabulator欄位設置
let columnsBatch002A = [
    ["checkbox", {
        showBtn: true
    }],
    ["seqString", "序號", "input"],
    ["logdescription", "說明", "input"]
];

// tabulator欄位格式製作
let colsFormat = createTableColumns(columnsBatch002A);

// 客製tabulator本體
let tableConfigs = {}

// 按鈕設置與功能
let tableRelatedBtns = []

// 檢核警告設定
let alertConfig2 = {
    type: "dom",
    position: "#tableBatch002A-errMsg",
    displayTime: 0, // 毫秒，訊息顯示時間，設置為0不清空
}

// 建立table
let tableBatch002A = createTable("tableBatch002A", colsFormat, tableConfigs,
    tableRelatedBtns, alertConfig2);

//預設
$(function() {
    queryBatch002A();
});

//Batch002A-作業細項說明 查詢
function queryBatch002A() {

    let param = {
        "taskid": Batch002.taskid
    }

    let parJson = JSON.stringify(param);

    let res = ajaxPostByJsonParam("../../batch002aapi/querylog", parJson, false);

    if ("000" === res.status) {
        loadData("tableBatch002A", res.data, {
            type: "dataCount",
            value: 7
        })

    } else {
        alert("「同險設定」查詢失敗!!!請聯絡管理人員!!!");
    }
}

//離開
function backTo002() {
    locationHref("batch002");
}

//開始收尋
function search() {

    let param = {
        "keyword": $('#queryBar').val(),
        "taskid": Batch002.taskid
    }

    let parJson = JSON.stringify(param);

    let res = ajaxPostByJsonParam("../../batch002aapi/querybycontent", parJson,
        false);

    if ("000" === res.status) {
        loadData("tableBatch002A", res.data, {
            type: "dataCount",
            value: 7
        })

    } else {
        alert("「同險設定」查詢失敗!!!請聯絡管理人員!!!");
    }
}

// 複製全部內容
function copyAllLog() {

    let logdescription = '';
    for (var i = 0; i < tableBatch002A.getData().length; i++) {
        logdescription = logdescription + ' ' +
            tableBatch002A.getData()[i].logdescription;
    }
    //let input = `<input type = text id = "temp" value = "${logdescription}">`;
    //$("body").append(input);
    $("#temp").val(logdescription);
    $("#temp").select();
    document.execCommand("Copy");
    $("#temp").val("");
}

// 複製選定內容
function copyLog() {

    if (tableBatch002A.getSelectedRows().length !== 1) {
        alert("請選擇一筆資料");
        return;
    }

    for (var i = 0; i < tableBatch002A.getSelectedRows().length; i++) {
        logdescription = tableBatch002A.getSelectedRows()[i].getData().logdescription;
        //let input = `<input type = text id = "temp" value = "${logdescription}">`;
        //$("body").append(input);
        $("#temp").val(logdescription);
        $("#temp").select();
        document.execCommand("Copy");
        $("#temp").val("");
    }
}