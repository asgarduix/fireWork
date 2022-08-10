//.....Rin1304臨分分入主畫面.....//
$(function() {
    //預設選取保批單資料維護
	$("#rdoUpdate").attr("checked",true);
	
});

//TODO 老李之後要改VO
	var policyNo;
	var endorseNo;

	/**
	 *查詢樹狀表資料
	 */
	function queryTree(layer, parJson) {
		// 		console.log(">>>queryTree<<<");
		// 		console.log(parJson);
		//查詢樹狀結構第一層
		//	http://localhost:10127/rin1304api/queryrin1304/tree/layer/3
		let res = null;

		switch (layer + "") {
		case "0":
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain
					+ "/rin1304api/queryrin1304/tree/layer/0", parJson, false);
			break;
		case "1":
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain
					+ "/rin1304api/queryrin1304/tree/layer/1", parJson, false);
			break;
		case "2":
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain
					+ "/rin1304api/queryrin1304/tree/layer/2", parJson, false);
			break;
		case "3":
			var domain = domain4Springboot(true);
			res = ajaxPostForRequestBodyTokenReady(domain
					+ "/rin1304api/queryrin1304/tree/layer/3", parJson, false);
			break;
		}

		return res;

	}


	function bind1304Tree() {
		// 1304側選單
		var menuSection = document.querySelectorAll(".menu-section");
		var menuitem = document.querySelectorAll(".menu-item");
		var menuitemLink = document.querySelectorAll("#menu a");
		var i;

		for (i = 0; i < menuitem.length; i++) {
			menuitem[i].addEventListener("click", folderEvent2);
			// 			$(menuitem[i]).bind("click", function(event){
			// 				folderEvent2;
			// 			});
			// 			menuitem[i].addEventListener("click", function() {

			// 				if (childmenu.getElementsByClassName("nest-hidden") === true) {
			// 					childmenu.classList.toggle("");
			// 				} else {
			// 					childmenu.classList.toggle("nest-hidden");
			// 				}
			// 			});

		}

		// 讓a連結保持連結，移除其他監聽事件
		menuitemLink.forEach(function(menuLink) {
			menuLink.addEventListener("click", function(e) {
				e.stopPropagation();
			});
		});
	}

	// 	//資料夾圖示的點擊事件
	// 	var folderEvent = function() {
	// 		var childmenu = this.nextElementSibling;
	// 		if (childmenu.getElementsByClassName("nest-hidden") === true) {
	// 			childmenu.classList.toggle("");
	// 		} else {
	// 			childmenu.classList.toggle("nest-hidden");
	// 		}
	// 	}

	//資料夾圖示的點擊事件
	var memoClickElement = null;

	var folderEvent2 = function() {
		memoClickElement = this;
		let len = memoClickElement.nextElementSibling.innerText.trim().length;
		//樹狀圖左邊icon切換，eq(0)=箭頭朝右，eq(1)=箭頭朝下
		if ($(memoClickElement).find('span').eq(1).css('display') == 'inline') {
			$(memoClickElement).find('span').eq(0).css('display', 'inline');
			$(memoClickElement).find('span').eq(1).css('display', 'none');
		} else if ($(memoClickElement).find('span').eq(0).css('display') == 'inline') {
			$(memoClickElement).find('span').eq(1).css('display', 'inline');
			$(memoClickElement).find('span').eq(0).css('display', 'none');
		}

		//  		if($(this).html().indexOf("button") > 0){
		//  			alert();
		//  			return;
		//  		}

		//  		$(this).find('span').eq(1)
		//         alert("這是什麼:"+memoClickElement.outerHTML+":"+$(this).find('span').eq(1).html());
		// 先找同層的下一個結構
		var clazz = $(this).parent("div").eq(0)[0].className;
		var lv = clazz.lastIndexOf("lv");
		var lvstr = clazz.substring(lv, lv + 4);

		var m = lvstr.lastIndexOf("-");
		var fetchLvl = new Number(lvstr.substring(m + 1));

		var childmenu = this.nextElementSibling;
		// 如果是最後一層的最後一個選項則不做任何動作
		if (childmenu === null) {
			return;
		}
		// 如果是最後一層則不做任何動作
		if (!childmenu.classList.contains("menu-section")) {
			return;
		}

		// 		console.log(fetchLvl);

		parJson = {};

		//如果有內容的話即刪除，反之呼叫API並增加內容
		if (fetchLvl >= 0 && fetchLvl <= 3
				&& childmenu.getElementsByTagName("div").length == 0) {
			//呼叫API增加子資料
			//TODO 後續要能抓得到「自己在哪個位置?」並且要抓收得到相關參數

			var divClassNmVar = fetchLvl + 2;

			//TODO Function設為陣列文件傳入
			switch (fetchLvl + "") {
			case "0":
				parJson = {
					"policyNo" : policyNo,
					"endorseNo" : endorseNo,
					"addrNo" : null,
					"propNo" : null
				};

				break;
			case "1":
				parJson = {
					"policyNo" : policyNo,
					"endorseNo" : endorseNo,
					"addrNo" : null,
					"propNo" : null
				};

				break;
			case "2":
				//另外取得addr_no(這裡因為this中應該僅一組相關變數，故這邊不用多考慮位置)
				var obj = $(this).find("div[style='display:none;']");
				var addrNo = obj.find("input[name='addr_no']").eq(0).val();

				parJson = {
					"policyNo" : policyNo,
					"endorseNo" : endorseNo,
					"addrNo" : addrNo,
					"propNo" : null
				};

				break;
			case "3":
				//另外取得addr_no、prop_no
				var obj = $(this).find("div[style='display:none;']");
				var addrNo = obj.find("input[name='addr_no']").val();
				var propNo = obj.find("input[name='prop_no']").val();

				parJson = {
					"policyNo" : policyNo,
					"endorseNo" : endorseNo,
					"addrNo" : addrNo,
					"propNo" : propNo
				};

				break;
			}

			// 			alert(JSON.stringify(parJson));
			// 			var res = queryTree(fetchLvl, parJson);
			let treeRes = queryTree(fetchLvl, parJson);

			if (treeRes == false) {
				return;
			}

			// 			alert(JSON.stringify(treeRes));

			var root = treeRes.data;
			var lng = root.length;
			var context = "";
			var context2 = new Array();

			for (var i = 0; i < lng; i++) {

				// 				console.log("first!!!!")
				// 				let divTag = document.createElement('div');	
				//       	  		divTag.classList.add('menu-item');

				// 				this.next("div").appendChild(divTag);

				//準備下次點擊事件需要的參數
				var name = "";
				var otherParam = {};
				var no = null;

				switch (fetchLvl + "") {
				case "0":
					name = root[i].cinsurant;
					break;
				case "1":
					no = root[i].addrNo;
					name = root[i].propAddr;
					otherParam["addr_no"] = root[i].addrNo;
					break;
				case "2":
					no = root[i].propNo;
					name = root[i].propertyName;
					otherParam["addr_no"] = root[i].addrNo;
					otherParam["prop_no"] = root[i].propNo;
					break;
				case "3":
					no = root[i].additionSeq;
					name = root[i].additionName;
					otherParam["addr_no"] = root[i].addrNo;
					otherParam["prop_no"] = root[i].propNo;
					otherParam["addition_seq"] = root[i].additionSeq;
					otherParam["addition_no"] = root[i].additionNo;
					break;
				}

				var jsonStr = JSON.stringify(otherParam);
				// 				console.log(jsonStr);

				if (lvstr === 'lv-3') {

					let divTag = document.createElement('div');
					divTag.classList.add('menu-item');

					let aTag = document.createElement('a');
					aTag.href = "#";
					$(aTag).on(
							"click",
							function() {
								tabToggle.apply(null, [ event,
										"tab0" + (fetchLvl + 1) + "" ]);
								radioDisabled.apply(null);
							});
					// 					aTag.onclick = tabToggle(event, "tab0" + (fetchLvl + 1) + "");
					aTag.textContent = "(" + no + ")" + name;

					let divTag2 = document.createElement('div');
					divTag2.setAttribute("style", "display:none;");

					let inputTag = document.createElement('input');
					inputTag.setAttribute("type", "text");
					inputTag.setAttribute("name", "addr_no");
					inputTag.value = otherParam.addr_no;

					let inputTag2 = document.createElement('input');
					inputTag2.setAttribute("type", "text");
					inputTag2.setAttribute("name", "prop_no");
					inputTag2.value = otherParam.prop_no;

					let inputTag3 = document.createElement('input');
					inputTag3.setAttribute("type", "text");
					inputTag3.setAttribute("name", "addition_seq");
					inputTag3.value = otherParam.addition_seq;

					let inputTag4 = document.createElement('input');
					inputTag4.setAttribute("type", "text");
					inputTag4.setAttribute("name", "addition_no");
					inputTag4.value = otherParam.addition_no;

					let divTag3 = document.createElement('div');
					divTag3.classList.add('menu-section');
					divTag3.classList.add('menu-lv-' + divClassNmVar);

					divTag.appendChild(aTag);
					divTag.appendChild(divTag2);
					divTag2.appendChild(inputTag);
					divTag2.appendChild(inputTag2);
					divTag2.appendChild(inputTag3);
					divTag2.appendChild(inputTag4);

					// 					$(this).next("div").appendChild(divTag3);

					context2.push(divTag);
					context2.push(divTag3);

					context += divTag.outerHTML + divTag3.outerHTML;

					// 					context += '<div class="menu-item" >' + '<a href="#" onclick="tabToggle(event, \'tab0' + (fetchLvl + 1) + '\');">' + "(" + no + ")" + name + '</a>'
					// 					//設定隱藏的資料
					// 					+ '<div style="display:none;">' + '<input type="text" name="addr_no" value="' + otherParam.addr_no + '">' + '<input type="text" name="prop_no" value="' + otherParam.prop_no + '">' + '<input type="text" name="addition_seq" value="' + otherParam.addition_seq + '">'
					// 							+ '<input type="text" name="addition_no" value="' + otherParam.addition_no + '">' + '</div>' + '</div>' + '<div class="menu-section menu-lv-' + divClassNmVar + '"></div>';

				} else {
					let divTag = document.createElement('div');
					divTag.classList.add('menu-item');

					let spanTag = document.createElement('span');
					spanTag.classList.add('material-icons');
					spanTag.setAttribute("attr", "arrow_right");
					spanTag.style = "display:inline;";
					spanTag.textContent = "arrow_right";

					let spanTag2 = document.createElement('span');
					spanTag2.classList.add('material-icons');
					spanTag2.setAttribute("attr", "arrow_drop_down");
					spanTag2.style = "display:none;";
					spanTag2.textContent = "arrow_drop_down";

					let aTag = document.createElement('a');
					aTag.href = "#";
					$(aTag).on(
							"click",
							function() {
								tabToggle.apply(null, [ event,
										"tab0" + (fetchLvl + 1) + "" ]);
								radioDisabled.apply(null);
							});
					// 					aTag.onclick = tabToggle(event, "tab0" + (fetchLvl + 1) + "");
					aTag.textContent = "(" + no + ")" + name;

					let divTag2 = document.createElement('div');
					divTag2.setAttribute("style", "display:none;");

					let inputTag = document.createElement('input');
					inputTag.setAttribute("type", "text");
					inputTag.setAttribute("name", "addr_no");
					inputTag.value = otherParam.addr_no;

					let inputTag2 = document.createElement('input');
					inputTag2.setAttribute("type", "text");
					inputTag2.setAttribute("name", "prop_no");
					inputTag2.value = otherParam.prop_no;

					let inputTag3 = document.createElement('input');
					inputTag3.setAttribute("type", "text");
					inputTag3.setAttribute("name", "addition_seq");
					inputTag3.value = otherParam.addition_seq;

					let inputTag4 = document.createElement('input');
					inputTag4.setAttribute("type", "text");
					inputTag4.setAttribute("name", "addition_no");
					inputTag4.value = otherParam.addition_no;

					let divTag3 = document.createElement('div');
					divTag3.classList.add('menu-section');
					divTag3.classList.add('menu-lv-' + divClassNmVar);

					divTag.appendChild(spanTag);
					divTag.appendChild(spanTag2);
					divTag.appendChild(aTag);
					divTag.appendChild(divTag2);
					divTag2.appendChild(inputTag);
					divTag2.appendChild(inputTag2);
					divTag2.appendChild(inputTag3);
					divTag2.appendChild(inputTag4);

					context2.push(divTag);
					context2.push(divTag3);

					context += divTag.outerHTML + divTag3.outerHTML;

					// 					context += '<div class="menu-item" >' + ' <span class="material-icons" attr="arrow_right" style="display:inline">arrow_right</span>' + ' <span class="material-icons" attr="arrow_drop_down" style="display:none" >arrow_drop_down</span>'
					// 							+ '<a href="#" onclick="tabToggle(event, \'tab0' + (fetchLvl + 1) + '\');">' + "(" + no + ")" + name + '</a>'
					// 							//設定隱藏的資料
					// 							+ '<div style="display:none;">' + '<input type="text" name="addr_no" value="' + otherParam.addr_no + '">' + '<input type="text" name="prop_no" value="' + otherParam.prop_no + '">' + '<input type="text" name="addition_seq" value="' + otherParam.addition_seq + '">'
					// 							+ '<input type="text" name="addition_no" value="' + otherParam.addition_no + '">' + '</div>' + '</div>' + '<div class="menu-section menu-lv-' + divClassNmVar + '"></div>';
				}
			}

			// 												var x = $(context);
			// 												$(this).next("div").append(x);

			// 			console.log($(this).parents()[0].outerHTML);
			// 			console.log($(this).next("div")[0].outerHTML);
			// 			console.log($(this).next("div").parent("div")[0].outerHTML);

			//上述使用jquery物件後轉為javascript物件執行迴圈塞回this-parent中
			// 			var val = $(context).length;

			// 			for (var i = 0; i < val; i++) {
			// 				$(this).next("div")[0].appendChild($(context)[i]);
			// 			}

			// 			$(this).next("div")[0].appendChild($(context)[0]);

			var val2 = $(context2).length;

			for (var i = 0; i < val2; i++) {
				console.log($(context2[i]));
				$(this).next("div")[0].appendChild(context2[i]);
			}

			// 			$("#menu_In")[0].appendChild($(context)[0]);
		} else {
			//直接刪除子資料
			childmenu.innerHTML = "";
		}

		//第四層所提供的參數
		if (fetchLvl + "" == "4") {
			var obj = $(this).find("div[style='display:none;']");
			//另外取得addr_no、prop_no
			var addrNo = obj.find("input[name='addr_no']").val();
			var propNo = obj.find("input[name='prop_no']").val();
			var additionSeq = obj.find("input[name='addition_seq']").val();
			var additionNo = obj.find("input[name='addition_no']").val();
			parJson = {
				"policyNo" : policyNo,
				"endorseNo" : endorseNo,
				"addrNo" : addrNo,
				"propNo" : propNo,
				"additionSeq" : additionSeq,
				"additionNo" : additionNo
			};
		}

		// 		console.log(parJson);

		//執行查詢
		switch (fetchLvl + "") {
		case "1":
			buttonControl();
			break;
		case "2":
			buttonControl();
			//打開時不用再次查詢
			if (addrNo) {
				query2(parJson);
			}

			break;
		case "3":
			buttonControl();
			//打開時不用再次查詢
			if (propNo) {
				query3(parJson);
			}

			break;
		case "4":
			buttonControl();
			if (additionSeq) {
				query4(parJson);
			}
			break;

		}

		//按鈕控管
		function buttonControl() {
			switch (fetchLvl + "") {
			case "1":
				if (radioValue2 == '1') {
					readonlyForInput();
				} else {
					// 					$("#allPage").find("input, button").attr("disabled", false);
					$('button[name="lefBtnA"]').prop('disabled', false);
					$('button[name="lefBtnB"]').prop('disabled', true);
					$('#btnAddB').prop('disabled', false);
					$('button[name="lefBtnC"]').prop('disabled', true);
					$('button[name="lefBtnD"]').prop('disabled', true);
				}
				break;
			case "2":
				if (radioValue2 == '1') {
					readonlyForInput();
				} else {
					// 					$("#allPage").find("input, button,select").attr("disabled",
					// 							false);
					$('button[name="lefBtnA"]').prop('disabled', true);
					$('button[name="lefBtnB"]').prop('disabled', false);
					$('button[name="lefBtnC"]').prop('disabled', true);
					$('#btnAddC').prop('disabled', false);
					$('button[name="lefBtnD"]').prop('disabled', true);
				}
				break;
			case "3":
				if (radioValue2 == '1') {
					readonlyForInput();
				} else {
					// 					$("#allPage").find("input, button,select").attr("disabled",
					// 							false);
					$('button[name="lefBtnA"]').prop('disabled', true);
					$('button[name="lefBtnB"]').prop('disabled', true);
					$('button[name="lefBtnC"]').prop('disabled', false);
					$('button[name="lefBtnD"]').prop('disabled', true);
					$('#btnAddD').prop('disabled', false);
				}
				break;
			case "4":
				if (radioValue2 == '1') {
					readonlyForInput();
				} else {
					// 					$("#allPage").find("input, button,select").attr("disabled",
					// 							false);
					$('button[name="lefBtnA"]').prop('disabled', true);
					$('button[name="lefBtnB"]').prop('disabled', true);
					$('button[name="lefBtnC"]').prop('disabled', true);
					$('button[name="lefBtnD"]').prop('disabled', false);
				}
				break;
			}
		}

		//一律再綁定事件
		bind1304Tree();
	}

	// 頁籤內容
	var tabToggle = function foo(evt, cityName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tab-content");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className
					.replace(" active", "");

		}
		document.getElementById(cityName).style.display = "block";
		//		evt.currentTarget.className += " active";
		let classList = evt.currentTarget.classList
		classList.contains("active") ? classList.remove("active") : classList
				.add("active")

		// 		alert(JSON.stringify(parJson));
	}

	// 頁籤內容(棄用)
	function tabToggle2(evt, cityName) {
		// 		alert(cityName);
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tab-content");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
			tablinks[i].className = tablinks[i].className
					.replace(" active", "");

		}
		document.getElementById(cityName).style.display = "block";
		//		evt.currentTarget.className += " active";
		let classList = evt.currentTarget.classList
		classList.contains("active") ? classList.remove("active") : classList
				.add("active")

		// 		alert(JSON.stringify(parJson));
	}

	/**
	 * 點擊「確認」按鈕時需要處理的步驟
	 */
	function init4Tree() {
		//	let parJson = JSON.stringify(param);
		//產生樹狀資料根選單
		//先清除靜態內容
		var item = $(".item_inside").eq(0);
		item.html("");
		var inside = '<div id="menu_In"></div>';
		item.html(inside);

		// 		//使用保單號產生
		// 		var policyNo = $('#txtpolicy_no_query').val();

		//         let divId = document.getElementById('menu_In');

		let divTag = document.createElement('div');
		divTag.classList.add('menu-section');
		divTag.classList.add('menu-lv-1');

		let divTag2 = document.createElement('div');
		divTag2.classList.add('menu-item');
		divTag2.id = "lv1";

		let spanTag = document.createElement('span');
		spanTag.classList.add('material-icons');
		spanTag.setAttribute("attr", "folder");
		spanTag.textContent = "folder";

		let aTag = document.createElement('a');
		aTag.href = "#";
		$(aTag).on("click", function() {
			tabToggle.apply(null, [ event, "tab01" ]);
			radioDisabled.apply(null);
			//重新查詢
			getDataSuccess.apply(null);
		});
		// 		aTag.onclick = tabToggle(event, "tab01");
		let semicolon; //分號
		if (checkIsNullSpace(endorseNo)) {
			semicolon = "";
		} else {
			semicolon = ":"
		}
		aTag.textContent = policyNo + semicolon + endorseNo;

		let divTag3 = document.createElement('div');
		divTag3.classList.add('menu-section');
		divTag3.classList.add('menu-lv-2');

		document.querySelector('#menu_In').appendChild(divTag);
		divTag.appendChild(divTag2);
		divTag2.appendChild(spanTag);
		divTag2.appendChild(aTag);
		divTag.appendChild(divTag3);

		// 		$("#menu_In")
		// 				.html(
		// 						'<div class="menu-section menu-lv-1" >'+
		// 						'<div class="menu-item" id="lv1">'+
		// 						'<span class="material-icons" attr="folder" ">folder</span>'+
		// 						'<a href="#"  onclick="tabToggle(event, \'tab01\');">'+ policyNo  + '</a>'+
		// 						'</div>'+
		// 						'<div class="menu-section menu-lv-2"></div>'+
		// 						'</div>');

		//產生樹狀資料第0層選單
		//先產生樹狀表資料
		//檢查保單號和批單號
		// 		var policyNo = $("#txtpolicy_no_query").val();
		// 		var endorseNo = $("#txtendorse_no_query").val();

		var parJson = {
			"policyNo" : policyNo,
			"endorseNo" : endorseNo,
			"addrNo" : null,
			"propNo" : null
		}

		let treeRes = queryTree(new Number(1), parJson);
		// 		let treeRes = queryTree(0, parJson);//?

		if (treeRes == false) {
			return;
		}

		// 		console.log(JSON.stringify(treeRes));

		var root = treeRes.data;
		var lng = root.length;

		var context = "";

		var fetchLvl = 1;

		for (var i = 0; i < lng; i++) {
			var addrNoOri = root[i].addrNoOri; //保單地址序號
			var name = root[i].propAddr; //地址名稱

			var otherParam = {};
			otherParam["addr_no"] = root[i].addrNo;
			otherParam["prop_no"] = root[i].propNo;
			otherParam["addition_seq"] = root[i].additionSeq;
			otherParam["addition_no"] = root[i].additionNo;
			context += '<div class="menu-item"><span class="material-icons" attr="arrow_right">arrow_right</span><span class="material-icons" attr="arrow_drop_down" style="display:none" >arrow_drop_down</span><a href="#" onclick="tabToggle(event, \'tab0'
					+ (fetchLvl + 1)
					+ '\');">'
					+ (fetchLvl == 1 ? "(" + root[i].addrNo + ")" : "")
					+ name
					+ '</a>'
					//設定隱藏的資料
					+ '<div style="display:none;">'
					+ '<input type="text" name="addr_no" value="' + otherParam.addr_no + '">'
					+ '<input type="text" name="prop_no" value="' + otherParam.prop_no + '">'
					+ '<input type="text" name="addition_seq" value="' + otherParam.addition_seq + '">'
					+ '<input type="text" name="addition_no" value="' + otherParam.addition_no + '">'
					+ '</div>'
					+ '</div>'
					+ '<div class="menu-section menu-lv-' + "3" + '"></div>';
		}

		// 		$(".menu-lv-2").html(context);

		var lng = $(context).length;

		for (var i = 0; i < lng; i++) {
			$(".menu-lv-2")[0].appendChild($(context)[i]);
		}

		//重新綁定事件
		bind1304Tree();

		//一剛開始的時候刷新為了帶值
		$('#lv1>span').click();
		$('#lv1>span').click();
	}


	//樹狀圖更新後立刻顯示
	function immediatelyShow() {
		let length = memoClickElement.nextElementSibling.innerText.trim().length

		if (length) {
			memoClickElement.click();
		}
		memoClickElement.click();
	}


///**
// *  查詢樹狀表資料 
// */
//function queryTree(layer) {
//	
//	var parJson = {
//		"policyNo": policyNo,
//		"endorseNo": endorseNo,
//		"addrNo": null,
//		"propNo": null
//	}
//
//	//查詢樹狀結構第一層
//	//	http://localhost:10127/rin1304api/queryrin1304/tree/layer/3
//	let res = null;
//
//	switch (layer) {
//		case 0:
//			var domain = domain4Springboot(true);
//			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/0", parJson, false);
//			break;
//		case 1:
//			var domain = domain4Springboot(true);
//			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/1", parJson, false);
//			break;
//		case 2:
//			var domain = domain4Springboot(true);
//			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/2", parJson, false);
//			break;
//		case 3:
//			var domain = domain4Springboot(true);
//			res = ajaxPostForRequestBodyTokenReady(domain + "/rin1304api/queryrin1304/tree/layer/3", parJson, false);
//			break;
//	}
//	return res;
//}

	//
//		function foo(treeRes) {
//			if (treeRes == null) {
//				alert("查詢保單資料時發生錯誤");
//				return false;
//			}
	//
//			if (treeRes.data.length == 0) {
//				alert("查無保單資料");
//				return false;
//			}
	//
//			return true;
//		}


//將右邊最下面的按鈕列隱藏和將畫面設定不能編輯
function radioDisabled(){
	$('#downRowA').hide();
	$('#downRowB').hide();
	$('#downRowC').hide();
	$('#downRowD').hide();
	$("#policyPage").find("input, button,select").attr("disabled",true);
	$("#policyPageB").find("input, button,select").attr("disabled",true);
	$("#policyPageC").find("input, button,select").attr("disabled",true);
	$("#policyPageD").find("input, button,select").attr("disabled",true);
	$('#otherInfo').attr("disabled", false); //其他資訊按鈕可按
	$('#btnLeave').attr("disabled", false);  //離開按鈕可按
	$('#myModalTwo').find("input,select").attr("disabled",true);
}
