//加载笔记内容
function loadNoteBody($li){
//	addCheckedCss(this);
//	var $li = $(this);
	noteId = $li.data("noteId");
	$.ajax({
		url:IP + "note/loadNoteBody.do",
		data:{"noteId":noteId, "userId":userId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var note = result.data;
				$("#input_note_title").val(note.ycu_note_title);
				um.setContent(note.ycu_note_body);
			}
		},
		error:function(){
			alertError("加载笔记内容失败，请联系管理员!");
		},
		async:true
	});
	//修改笔记内容
	$("#save_note").unbind("click").click(function(){
		var noteTitle = $("#input_note_title").val().trim();
		var noteBody = um.getContent();
		if(!noteTitle){
			alertError("笔记标题为空!");
		}else{
			$.ajax({
				url:IP + "note/changeNoteBody.do",
				data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						$li.children("a").html(
							'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +
							noteTitle +
							'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">' +
							'<i class="fa fa-chevron-down"></i></button>'
						);
						alertSuccess(result.msg);
					}
				},
				error:function(){
					alertError("修改笔记失败，请联系管理员!");
				},
				async:true
			});
		}
	});
}
//预览笔记
function previewNote(){
	addCheckedCss(this);
	noteId = $(this).data("noteId");
	$.ajax({
		url:IP + "note/loadNoteBody.do",
		data:{"noteId":noteId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				$("#noput_note_title").text(result.data.ycu_note_title);
				$("#noput_note_title").next("p").html(result.data.ycu_note_body);
			}
		},
		error:function(){
			alertError("预览笔记失败，请联系管理员!");
		},
		async:true
	});
}