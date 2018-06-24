//关闭alert
function closeAlertWindow(){
	$("#can_alert").empty();
	if($("#can").is(":empty")){
		$("#opacity_bg").hide();
	}
}
//关闭操作窗方法
function closeWindow(){
	$("#opacity_bg").hide();
	$("#can").empty();
}
//alert方法Error
function alertError(msg){
	$("#opacity_bg").show();
	$("#can_alert").load("alert/alert_error.html", function(){
		$("#error_info").html(msg);
	});
}
//alert方法Success
function alertSuccess(msg){
	$("#opacity_bg").show();
	$("#can_alert").load("alert/alert_success.html", function(){
		$("#success_info").html(msg);
	});
}
//点击添加CSS
function addCheckedCss(li){
	var $li = $(li);
	$li.parent().children("li").children("a").removeClass();
	$li.children("a").addClass("checked");
}
function clearNoteBody(){
//	$("#noput_note_title").empty();
//	$("#noput_note_title").next("p").empty();
	$("#input_note_title").val("");
	um.setContent("");
}
function clearShareBody(){
	$("#share_title span").empty();
	$("#share_title").next("div").empty();
}
function clearCollectBody(){
	$("#noput_note_title").empty();
	$("#noput_note_title").next("p").empty();
}
//关闭收藏
function closeCollect(){
	$(".opacity_bg").hide();
	$("#modalBasic_14").hide();
}
var IP = "http://" + "localhost:" + "8080/" + "note/";