function createDropDown(options, locations, ids){
	createSelectList(options[0], locations[0], ids[0]);
	createSelectList([], locations[1], ids[1]);
	createSelectList([], locations[2], ids[2]);
	var firstDropDown = document.getElementById(ids[0]);
	var secondDropDown = document.getElementById(ids[1]);
	var thirdDropDown = document.getElementById(ids[2]);
	firstDropDown.addEventListener("change", function(event){
		changeFirstDropDown(event.target.value);
	});
	secondDropDown.addEventListener("change", function(event){
		changeSecondDropDown(event.target.value);
	});
	
	var OptionsMap = {
  		"ProductA": [{"version": "1.0.0", "fileName": ["FileA1.zip", "FileA11.zip"]}, {"version": "1.0.1", "fileName": ["FileA2.zip", "FileA22.zip"]}],
  		"ProductB": [{"version": "3.5.0", "fileName": ["FileB1.zip", "FileB11.zip"]}, {"version": "4.0.1", "fileName": ["FileB2.zip", "FileB22.zip"]}],
  		"ProductC": [{"version": "1.0.0", "fileName": ["FileC1.zip", "FileC11.zip"]}, {"version": "1.0.1", "fileName": ["FileC2.zip", "FileC22.zip"]}]
	};

function changeFirstDropDown(firstValue) {
  secondDropDown.innerHTML = '<option>--Choose Product Version</option>'; // Remove previous options
  thirdDropDown.innerHTML = '<option>--Choose Filename</option>'; // Remove previous options
  //var nextOptions = OptionsMap[firstValue];
  var nextOptions = options[1][0][firstValue];
  console.log(nextOptions);
  for (var i = 0; i < nextOptions.length; i++) {
	secondDropDown.appendChild(new Option(nextOptions[i].text, nextOptions[i].value));
  }
}

function changeSecondDropDown(productVersion) {
  var firstItemID = firstDropDown.value;
  thirdDropDown.innerHTML = ''; // Remove previous options
  var moreOptions = OptionsMap[firstItemID];
  for (var i = 0; i < moreOptions.length; i++) {
    if (moreOptions[i].version == productVersion){
      var filenames = moreOptions[i].fileName;
      for (var j = 0; j < filenames.length; j++){
        thirdDropDown.appendChild(new Option(filenames[j]));
      }
      break;
    }
  }
}


//PrepopulateFirstDropDown();
}