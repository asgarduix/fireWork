//const tableParams = [[fieldName, inputType [, selectParams(object) ]]...]

	 // This is the data for #example-table
	const tabledata = [ 
	    { select1: false, select2: false, select3: false, text: "textarea value", name:"Oli Bob", age:25, gender:"male"},
	    { select1: false, select2: false, select3: false, text: null,name:"Oli Bob", age:24, gender:"male"},
	    { select1: false, select2: false, select3: false, text: undefined,name:"Mary May",age:25, gender:"female"},
	    { select1: false, select2: false, select3: false, text: "",name:"Oli Bob", age:24, gender:"male"},
	    { select1: false, select2: false, select3: false, text: "",name:"Brendon Philips",age:26, gender:"male"}
	];
	
	const tabledata1 = [  // This is the data for #example-table
	    { select1: false, name:"Oli Bob", age:25, gender:"male"},
	    { select1: false,name:"Oli Bob", age:24, gender:"male"},
	    { select1: false,name:"Mary May",age:25, gender:"female"},
	    { select1: false,name:"Oli Bob", age:24, gender:"male"},
	    { select1: false,name:"Brendon Philips",age:26, gender:"male"},
		{ select1: false, name:"Oli Bob", age:25, gender:"male"},
	    { select1: false,name:"Oli Bob", age:24, gender:"male"},
	    { select1: false,name:"Mary May",age:25, gender:"female"},
	    { select1: false,name:"Oli Bob", age:24, gender:"male"},
	    { select1: false,name:"Brendon Philips",age:26, gender:"male"}
	];

	
	
const tabledata2 = [
    { id:1, name:"Oli Bob", age:24, gender:"male"},
    { id:2, name:"Mary May",age:25, gender:"female"},
    { id:3, name:"Christine Lobowski",age:26, gender:"female"},
    { id:4, name:"Brendon Philips", age:27, gender:"male"},
    { id:5, name:"Margret Marmajuke", age:28, gender:"female"},
    { id:6, name:"Oli Bob", age:24, gender:"male"},
    { id:7, name:"Mary May",age:25, gender:"female"},
    { id:8, name:"Christine Lobowski",age:26, gender:"female"},
    { id:9, name:"Brendon Philips", age:27, gender:"male"},
    { id:10, name:"Margret Marmajuke", age:28, gender:"female"},
    { id:11, name:"Oli Bob", age:24, gender:"male"},
    { id:12, name:"Mary May",age:25, gender:"female"},
    { id:13, name:"Christine Lobowski",age:26, gender:"female"},
    { id:14, name:"Brendon Philips", age:27, gender:"male" },
    { id:15, name:"Margret Marmajuke", age:28, gender:"female"},
    { id:16, name:"Oli Bob", age:24, gender:"male"},
    { id:17, name:"Mary May",age:25, gender:"female"},
    { id:18, name:"Mary May",age:25, gender:"female"}

];

function handleCellEdited(cell){
  cell.getRow().getCells()[6].setValue();
  cell.getRow().getCells()[7].setValue();
}
function handleCellEdited1(cell){
  cell.getRow().getCells()[7].setValue();
}

//first column you defined should be cell6 (getCells()[5])
const tableParams = [   
	["select1", "選取","checkboxTitle"],
	["select2", "是否承保","checkboxTitle"],
	["select3", "是否列入黑名單","checkboxTitle"],
	["text", "核保決定說明", "textarea"],
	["name", "Name", "input"], 
	["age", "Age","number"],
	["gender", "Sex","select", {"male":"male", "female":"female"}]
];

const tableParams1 = [   
  ["insType", "Ins", "select", crtDdlLoadDDLIINSCLSType2Val, handleCellEdited, false],
  ["insType1", "Ins1", "select", crtDdlLoadDDLIINSKind_501Type2ValDep, handleCellEdited1, true],
  ["insType2", "Ins2", "select", crtDdlLoadDDLNCMNpInstypeType2Val, undefined, false],
  ["name", "Name", "input"], 
  ["age", "Age","number"],
  ["gender", "Sex","select", {"male":"male", "female":"female"}]
];

//true to show edit and delete button, false to hide
//createTableWithDDD(tableParams, tabledata, "#example-table", true);
//createTableWithDDD(tableParams1, tabledata1, "#example-table-1", true);

let exampleTable = createTableddlref(tableParams, tabledata, "#example-table", {}, true , [])
let exampleTable2 = createTableddlref(tableParams, tabledata1, "#example-table2", {}, true , [])
 //let data1 = fetchTabulator("#example-table").getData()[0];
 //let data2 = {name:"Oli Bobby", age: 24};
 //let dataPos = fetchMatchingData("#example-table", data2);
 //console.log(data1)
 //console.log(dataPos)
	let data = {name:"Oli Bob", age: 24};
	let dataPos = fetchMatchingData("#example-table", data);
	console.log(`dataPos: ${dataPos}`)

