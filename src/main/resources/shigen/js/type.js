/////////////////////////////////////////////////////////////
/************** 基本建置 **************/
$(document).ready(function() {

	/* 全局輸入框：預設文字 jquery.placeholder - 表單內顯示的文字 - 支援：IE6-IE9 */
	$('[placeholder]').focus(function() {
		var input = $(this);
		if (input.val() == input.attr('placeholder')) {
			input.val('');
			input.removeClass('placeholder');
		}
	}).blur(function() {
		var input = $(this);
		if (input.val() == '' || input.val() == input.attr('placeholder')) {
			input.addClass('placeholder');
			input.val(input.attr('placeholder'));
		}
	}).blur().parents('form').submit(function() {
		$(this).find('[placeholder]').each(function() {
			var input = $(this);
			if (input.val() == input.attr('placeholder')) {
				input.val('');
			}
		});
	});

	/* 全局輸入框：select 預設框架 */
	$("select").on('change', function() {
		if ($(this).val() !== "0") {
			$(this).addClass("color_change");
		} else {
			$(this).removeClass("color_change");
		}
	});


	/* 右側標題區塊內容：關閉*/
	$(".content .item_title .bt-close").click(function(){
		$(this).parent().next().slideUp(400);
		$(this).hide();
		$(this).parent().children(".bt-open").show();
	});

	/* 右側標題區塊內容：開啟*/
	$(".content .item_title .bt-open").click(function(){
		$(this).parent().next().slideDown(400);
		$(this).hide();
		$(this).parent().children(".bt-close").show();
	});


});
