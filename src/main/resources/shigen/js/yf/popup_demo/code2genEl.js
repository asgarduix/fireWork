let options = [
   { text: 'First', value: 'First'}, 
   { text: 'Second', value: 'Second'},
   { text: 'Third', value: 'Third'}
];

document.getElementById('openWindow').addEventListener("click", function(){

   openSelectWindow( "oriPageId",options, 200, 200);
   //openInputWindow("oriPageId" ,200, 200);
});
