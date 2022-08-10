/**
 * 
 */
$(function() {
	setTimeout(function() {
		// uix team only
		var funcs = document.URL.split("/");
		var nowfunc = funcs[funcs.length - 1];

		if ("loading.html" == nowfunc) {
			location.href = "./index.html";
		}
	}, 2000);
})