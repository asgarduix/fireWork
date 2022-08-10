//const tableParams = [[fieldName, inputType [, selectParams(object) ]]...]
const tabledata = [
    {   id:1, name:"Oli Bob", multiple:0, total: undefined,  gender:"male", urlEvent:"<a href='https://www.google.com/' target='_blank' >Google</a>"},
    {   id:2, name:"Mary May",multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:3, name:"Christine Lobowski",multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:4, name:"Brendon Philips", multiple:0, total: undefined,  gender:"male", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:5, name:"Margret Marmajuke", multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:6, name:"Oli Bob", multiple:0, total: undefined,  gender:"male", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:7, name:"Mary May",multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:8, name:"Christine Lobowski",multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:9, name:"Brendon Philips", multiple:0, total: undefined,  gender:"male", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:10, name:"Margret Marmajuke", multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:11, name:"Oli Bob", multiple:0, total: undefined,  gender:"male", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:12, name:"Mary May",multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:13, name:"Christine Lobowski",multiple:0, total: undefined,  gender:"female", urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"},
    {   id:14, name:"Brendon Philips", multiple:0,total: undefined,  gender:"male" , urlEvent:"<a href='#' onclick='locationHrefKeepData('HAS301010', 'row0', {&quot;oid&quot;:1000337294,&quot;oidPubtPeMain&quot;:1000777907,&quot;oidHastPePlan&quot;:1000187281,&quot;ikind&quot;:&quot;1&quot;,&quot;qyrbgn&quot;:15,&quot;qdaybgn&quot;:0,&quot;qyrend&quot;:65,&quot;qdayend&quot;:365,&quot;fsex&quot;:&quot;3&quot;,&quot;focpyclsBgn&quot;:&quot;1&quot;,&quot;focpyclsEnd&quot;:&quot;3&quot;,&quot;frenew&quot;:&quot;1&quot;,&quot;mprem&quot;:2100,&quot;akin&quot;:9,&quot;fspecial&quot;:&quot;N&quot;,&quot;id&quot;:1,&quot;nomprem&quot;:0}); return false;'>保費明細</a>"}

];

const tabledata1 = [
    {   id:1, amount:"", name:"Oli Bob", age:24, gender:"male"},
    {   id:2, amount:"", name:"Mary May",age:25, gender:"female"},
    {   id:3, amount:"", name:"Christine Lobowski",age:26, gender:"female"},
    {   id:4, amount:"", name:"Brendon Philips", age:27, gender:"male"},
    {   id:5, amount:"", name:"Margret Marmajuke", age:28, gender:"female"},
    {   id:6, amount:"", name:"Oli Bob", age:24, gender:"male"},
    {   id:7, amount:"", name:"Mary May",age:25, gender:"female"},
    {   id:8, amount:"", name:"Christine Lobowski",age:26, gender:"female"},
    {   id:9, amount:"", name:"Brendon Philips", age:27, gender:"male"},
    {   id:10, amount:"",  name:"Margret Marmajuke", age:28, gender:"female"},
    {   id:11, amount:"", name:"Oli Bob", age:24, gender:"male"},
    {   id:12, amount:"", name:"Mary May",age:25, gender:"female"},
    {   id:13, amount:"", name:"Christine Lobowski",age:26, gender:"female"},
    {   id:14, amount:"", name:"Brendon Philips", age:27, gender:"male" },
    {   id:15, amount:"", name:"Margret Marmajuke", age:28, gender:"female"},
    {   id:16, amount:"", name:"Oli Bob", age:24, gender:"male"},
    {   id:17, amount:"", name:"Mary May",age:25, gender:"female"}

];



function handleCellEdited(cell){
  cell.getRow().getCells()[6].setValue("");
  cell.getRow().getCells()[7].setValue("");
}
function handleCellEdited1(cell){
  cell.getRow().getCells()[7].setValue("");
}


//first column you defined should be cell6 (getCells()[5])
const tableParams = [   
  //["insType", "Ins", "select", crtDdlLoadDDLIINSCLSType2Val, handleCellEdited, false],
  //["insType1", "Ins1", "select", crtDdlLoadDDLIINSKind_501Type2ValDep, handleCellEdited1, true],
  //["insType2", "Ins2", "select", crtDdlLoadDDLNCMNpInstypeType2Val, undefined, false],
	["name", "Name", "input"], 
	["asd", "TEXTAREA", "textarea"],
  ["multiple", "Multiple","cusInput", { "fieldNm": "total", "params": 10000 }],
  ["total", "Total","uneditableInput"],
  ["gender", "Sex","select", {"male":"male", "female":"female"}],
  ["urlEvent", "URL", "url"]
];
const tableParams1 = [   
  //["insType", "Ins", "select", crtDdlLoadDDLIINSCLSType2Val, handleCellEdited, false],
  //["insType1", "Ins1", "select", crtDdlLoadDDLIINSKind_501Type2ValDep, handleCellEdited1, true],
  //["insType2", "Ins2", "select", crtDdlLoadDDLNCMNpInstypeType2Val, undefined, false],
    ["amount", "Amount", "cusInput2",["#input1", "#input2"]],
	["name", "Name", "input"], 
	["age", "Age","number"],
	["gender", "Sex","select", {"male":"male", "female":"female"}]
];


//true to show edit and delete button, false to hide
createTableWithCopy(tableParams, tabledata, "#example-table", true);
createTableWithCopy(tableParams1, tabledata1, "#example-table-1", true);




