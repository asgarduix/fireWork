/**
 * 
 */
$(function () {
	// Generate print icon
	var printIcon = function (cell, formatterParams) { // plain text value
		return "<i class='fa fa-print'></i>";
		// return "<button class='button' type='button'
		// onclick='javascript:location.href=\'#\'''>查詢</button>";
		// return "<span>hello</span>";
	};

	$("#time").html(new Date());

	// define some sample data
	var tabledata = [];// 這裡使用Ajax替換即可?

	for (var i = 1; i <= 100; i++) {
		// for (var i = 1; i <= 1000000; i++) {
		var data = {
			id: i,
			name: "Oli Bob",
			age: "12",
			col: "red",
			dob: ""
		};

		tabledata[i - 1] = data;
	}

	// var tabledata = [ {
	// id : 1,
	// name : "Oli Bob",
	// age : "12",
	// col : "red",
	// dob : ""
	// }, {
	// id : 2,
	// name : "Mary May",
	// age : "1",
	// col : "blue",
	// dob : "14/05/1982"
	// }, {
	// id : 3,
	// name : "Christine Lobowski",
	// age : "42",
	// col : "green",
	// dob : "22/05/1982"
	// }, {
	// id : 4,
	// name : "Brendon Philips",
	// age : "125",
	// col : "orange",
	// dob : "01/08/1980"
	// }, {
	// id : 5,
	// name : "Margret Marmajuke",
	// age : "16",
	// col : "yellow",
	// dob : "31/01/1999"
	// }, ];

	var tabledata = [
		{ uuid: "A123456789", holiday: "章只怡", memo: "11111", status: "ABCD-1234", crtDate: "20201231", crtUserid: "艷陽天", entryUserid: "好空氣", entryDate: "" },
		{ uuid: "A123456789", holiday: "梁朝為", memo: "22222", status: "ABCD-1234", crtDate: "20201231", crtUserid: "艷陽天", entryUserid: "好空氣", entryDate: "" },
		{ uuid: "A123456789", holiday: "金城五", memo: "33333", status: "ABCD-1234", crtDate: "20201231", crtUserid: "艷陽天", entryUserid: "好空氣", entryDate: "" },
		{ uuid: "A123456789", holiday: "劉家鄰", memo: "44444", status: "ABCD-1234", crtDate: "20201231", crtUserid: "艷陽天", entryUserid: "好空氣", entryDate: "" },
		{ uuid: "A123456789", holiday: "陳金三", memo: "55555", status: "ABCD-1234", crtDate: "20201231", crtUserid: "艷陽天", entryUserid: "好空氣", entryDate: "" },
		{ uuid: "A123456789", holiday: "吳思凱", memo: "66666", status: "ABCD-1234", crtDate: "20201231", crtUserid: "艷陽天", entryUserid: "好空氣", entryDate: "" }
	];

	// alert(new Date());

	// TODO 或許可以自動產出?
	// TODO 表格高度使用畫面自動產生
	// create Tabulator on DOM element with id "example-table"
	var pw = [10, 20, 30, 50, 100];

	var table = new Tabulator("#example-table", {
		locale:true,
		height: window.height, // set height of table (in CSS or here), this
		// enables the Virtual DOM and improves render
		// speed dramatically (can be any valid css
		// height value)
		data: tabledata, // assign data to table
		layout: "fitColumns", // fit columns to width of table (optional)
		pagination: "local",
		paginationSize: pw[2],
		paginationSizeSelector: pw,
		//spaginationSizeSelector:true, //enable page size select element and generate list options
		initialHeaderFilter: [{// set the initial value of the header
			// filter to "red"
			field: "col",
			value: "red"
		}],
		// initialFilter : [ {//Define Table Columns
		// field : "col",
		// type : "=",
		// value : "red"
		// } ],
		langs: {
			"zh-tw": {
				"columns": {
					"checkbox": "checkbox",
					"Name_test": "Name_test",
					"test2": "test2",
					"uuid": "uuid",
					"保單號碼/強制證號": "保單號碼/強制證號",
					"被保險人名稱": "被保險人名稱",
					"被保險人ID": "被保險人ID",
					"車號": "車號",
					"保險期間": "保險期間",
					"經手人": "經手人",
					"保源": "保源",
					"洽攬人": "洽攬人",
					"收費註": "收費註"
				},
				"pagination": {
					"page_size":"每頁筆數",
					"first": "最前頁",
					"first_title": "最前頁2",
					"last": "最尾頁",
					"last_title": "最尾頁2",
					"prev": "前頁",
					"prev_title": "前頁2",
					"next": "下頁",
					"next_title": "下頁2",
				},
			}
		},
		columns: [
			{
				title: "checkbox",
				formatter: "rowSelection",
				titleFormatter: "rowSelection",
				width: 80
				// formatter : function(cell, formatterParams) {
				// return "<input name='cb' type='checkbox'>";
				// }
			},
			{
				title: "Name_test",
				field: "uuid",
				width: 150,
				visible: false,
				formatter: function (cell, formatterParams) {
					var value = cell.getValue();
					if (value.indexOf("o") > 0) {
						return "<span style='color:red; font-weight:bold;'>" + value + "</span>";
					} else {
						return "<span style='color:red'><button class='button' type='button' onclick='alert(\""
							+ cell.getValue() + "\");return false;'>hello</button></span>";
					}
				}
			}, {
				title: "test2",
				field: "uuid",
				formatter: printIcon,
				width: 100,
				align: "center",
				visible: false,
				callClick: function (e, cell) {
					alert("Printing row data for: " + cell.getRow().getData().uuid);
				}
			}, {
				title: "uuid",
				field: "uuid",
				visible: false,
				callClick: function (e, cell) {
					alert("Printing row data for: " + cell.getRow().getData().uuid);
				}
			}, {
				title: "保單號碼/強制證號",
				field: "status"
			}, {
				title: "被保險人名稱",
				field: "holiday"
			}, {
				title: "被保險人ID",
				field: "status"
			}, {
				title: "車號",
				field: "memo"
			}, {
				title: "保險期間",
				field: "status"
			}, {
				title: "經手人",
				field: "status"
			}, {
				title: "保源",
				field: "status"
			}, {
				title: "洽攬人",
				field: "holiday"
			}, {
				title: "收費註",
				field: "status"
			}],
		// columns : [ {
		// title : "Id",
		// field : "id",
		// width : 150
		// }, {
		// title : "Name",
		// field : "name",
		// width : 150
		// }, {
		// title : "Age",
		// field : "age",
		// align : "left",
		// formatter : "progress"
		// }, {
		// title : "Favourite Color",
		// field : "col"
		// }, {
		// title : "Date Of Birth",
		// field : "dob",
		// sorter : "date",
		// align : "center"
		// }, ],
		// columns : [ {
		// title : "保單號碼/強制證號",
		// field : "f1"
		// }, {
		// title : "被保險人名稱",
		// field : "f2"
		// }, {
		// title : "被保險人ID",
		// field : "f3"
		// }, {
		// title : "車號",
		// field : "f4"
		// }, {
		// title : "保險期間",
		// field : "f5"
		// }, {
		// title : "經手人",
		// field : "f6"
		// }, {
		// title : "保源",
		// field : "f7"
		// }, {
		// title : "洽攬人",
		// field : "f8"
		// }, {
		// title : "收費註",
		// field : "f9"
		// } ],
		// 點擊列資料事件
		// rowClick : function(e, row) { //trigger an alert message when the row is
		// clicked
		// // alert("Row " + row.getData().id + " Clicked!!!!");
		// console.log("Row " + row.getData().id + " Clicked!!!!");
		// }
	});

	//table.setLocale("zh-tw");

	// 限制...這邊由API取得的資料，需要剔除表格上沒有呈現的資料，可能在某些地方不好用，要找找有沒有hiden column的能力
	//table.setData("/frontendapi/fetchAppHoliday");

	// Tabulator.prototype.extendModule("filter", "filters", {
	// "===" : function(headerValue, rowValue, rowData, filterParams) {
	// //headerValue - the value of the header filter element
	// //rowValue - the value of the column in this row
	// //rowData - the data for the row being filtered
	// //filterParams - params object passed to the headerFilterFuncParams
	// property

	// return rowVal === headerValue ? true : false;
	// }
	// });

	// alert(new Date());

	// =====filter test=====
	// Custom filter example
	function customFilter(data) {
		return data.car && data.rating < 3;
	}

	// Trigger setFilter function with correct parameters
	function updateFilter() {
		// var filter = $("#filter-field").val() == "function" ? customFilter :
		// $("#filter-field").val();

		// if ($("#filter-field").val() == "function") {
		// $("#filter-type").prop("disabled", true);
		// $("#filter-value").prop("disabled", true);
		// } else {
		// $("#filter-type").prop("disabled", false);
		// $("#filter-value").prop("disabled", false);
		// }

		var filter = $("#filter-field").val() == "" ? null : $("#filter-field").val();

		if (filter == null) {
			return;
		}

		table.setFilter(filter, $("#filter-type").val(), $("#filter-value").val());
	}

	// Update filters on value change
	$("#filter-field, #filter-type").change(updateFilter);
	$("#filter-value").keyup(updateFilter);

	// Clear filters on "Clear Filters" button click
	$("#filter-clear").click(function () {
		$("#filter-field").val("");
		$("#filter-type").val("=");
		$("#filter-value").val("");

		table.clearFilter();
	});
});