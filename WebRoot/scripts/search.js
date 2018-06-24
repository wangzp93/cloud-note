function searchNotes(){
	var noteTitle = $("#search_note").val().trim();
	var page = parseInt($("#page").val().trim());
//	console.log(111);
	$.ajax({
		url:IP + "note/search.do",
		data:{"userId":userId,"noteTitle":noteTitle,"page":page},
		type:"post",
		dataType:"json",
		success:function(result){
//			console.log(222);
			if(result.status == 0){
//				console.log(333);
				$("#page").val(page + 1);
				var notes = result.data;
				for(var i=0; i<notes.length; i++){
					var $li = $(
						'<li class="online">' +
							'<a>' +
								'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +
								notes[i].ycu_note_title +
								'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">' +
								'<i class="fa fa-chevron-down"></i></button>' +
							'</a>' +
							'<div class="note_menu" tabindex="-1">' +
								'<dl>' +
									'<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>' +
									'<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>' +
									'<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>' +
								'</dl>' +
							'</div>' +
						'</li>');
					$li.data("noteId", notes[i].ycu_note_id);
					$("#search_list").append($li);
				}
			}else if(result.status == 1){
//				console.log(11);
				$("#search_msg").text(result.msg);
			}
		},
		error:function(){
			alertError("搜索失败，请联系管理员!");
		},
		async:true
	});
}