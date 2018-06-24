function loadShareBody(){
	addCheckedCss(this);
	var $li = $(this);
	shareId = $li.data("shareId");
	clearShareBody();
	$.ajax({
		url:IP + "share/loadShareBody.do",
		data:{"shareId":shareId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var share = result.data;
				$("#share_title span").text(share.ycu_share_title);
				$("#share_title").next("div").html(share.ycu_share_body);
			}
		},
		error:function(){
			alertError("加载笔记内容失败，请联系管理员!");
		},
		async:true
	});
}