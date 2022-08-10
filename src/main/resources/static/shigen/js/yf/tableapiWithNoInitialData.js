const tableParams = [   
	["EditButton", "編輯", "button", true, null],
	["CancelButton", "取消", "button", false, null],
	["SaveButton", "儲存", "button", false, null],
	["DeleteButton", "刪除", "button", true, null],
	["oid", "oid", "input"], 
	["oidPubtPeMain", "oidPubtPeMain","input"],
  	["oidPubtPeObj", "oidPubtPeObj","input"]
];

const destination = "#example-table";
const btnMethods = {
	delete: async function(e, cell){
		//await call api to delete data in db
		console.log("call api to delete data in db");
		//return after done deleting data in db
		return "done deleting data in db";
	},
	update: async function(e, cell){
		let rowData = cell.getRow().getData();
		//await call api to save rowData to db
		console.log("call api to save data in db");
		//return after done saving data in db
		return "done saving data in db";
		
	}
};

const statusBarMethods = [
		{
			"id": "#example-table-reactivityAdd",
			"funcNm": function(){
				console.log("conditionTable reactivityAdd callback function");
			},
		}
	];
let ajaxParams = {isOid: "1000019007"};
//let ajaxUrl = "http://localhost:10122/commonController/queryT3";
let ajaxUrl = "";
const remoteTable = createRemoteTable(tableParams, destination, ajaxUrl, ajaxParams, btnMethods , false, statusBarMethods, "", []);

async function query(){
	let newAjaxUrl = "http://localhost:10122/commonController/queryT3";
	remoteTable.setData(newAjaxUrl, ajaxParams);
}
