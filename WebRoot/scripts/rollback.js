//回收站
function loadRollbackNotes(){
	$("#pc_part_2").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_4").show();
	clearNoteBody();
	$.ajax({
		url:IP + "note/loadRollbackNote.do",
		data:{"userId":userId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				$("#rollbacknotes").empty();
				var rollbackNotes = result.data;
				for(var i=0; i<rollbackNotes.length; i++){
					var rollbackNote = rollbackNotes[i];
					var $li = $(
						'<li class="disable">' +
							'<a>' +
								'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> ' +
								rollbackNote.ycu_note_title +
								'<button type="button" class="btn btn-default btn-xs btn_position btn_delete">' +
									'<i class="fa fa-times"></i>' +
								'</button>' +
								'<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">' +
									'<i class="fa fa-reply"></i>' +
								'</button>' +
							'</a>' +
						'</li>'
					);
					$li = $li.data("noteId",rollbackNote.ycu_note_id);
					$("#rollbacknotes").append($li);
				}
			}
		},
		error:function(){
			alertError("加载回收站失败，请联系管理员!");
		},
		async:true
	});
}
//恢复笔记
function replayNote(){
	var $li = $(this).parents("li");
//	addCheckedCss($li.get(0));
//	noteId = $li.data("noteId");
	$("#opacity_bg").show();
	$("#can").load("alert/alert_replay.html", function(){
		var $books = $("#books li");
		for(var i=0; i<$books.length; i++){
			var $book = $($books[i]);
			var bookId = $book.data("bookId");
			var bookName = $book.children("a").text();
			$("#replaySelect").append(
				'<option value=' + bookId + '>- ' 
				+ bookName + ' -</option>'
			);
		}
		$("#replay_sure").unbind("click").click(function(){
			var newBookId = $("#replaySelect option:selected").val().trim();
			if(newBookId == "none"){
				alertError("请选择一个分类!");
			}else{
				$.ajax({
					url:IP + "note/moveNote.do",
					data:{"newBookId":newBookId,"noteId":noteId},
					type:"post",
					dataType:"json",
					success:function(result){
						if(result.status == 0){
							$("#can").empty();
							$("#rollbacknotes li .checked").parent().remove();
//							$("#input_note_title").val("");
//							um.setContent("");
							noteId = undefined;
							alertSuccess("恢复成功!");
						}
					},
					error:function(){
						alertError("恢复笔记失败，请联系管理员!");
					},
					async:true
				});
			}
		});
	});
}
//删除笔记
function deleteNote(){
	var $li = $(this).parents("li");
	addCheckedCss($li.get(0));
	noteId = $li.data("noteId");
	$("#opacity_bg").show();
	$("#can").load("alert/alert_delete_rollback.html", function(){
		$("#delete_rollback_sure").unbind("click").click(function(){
			$.ajax({
				url:IP + "note/deleteNotes.do",
				data:{"noteId":noteId},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						$li.remove();
						noteId = undefined;
						closeWindow();
					}else if(result.status == 1 || result.status == 2){
						alertError(result.msg);
					}
				},
				error:function(){
					alertError("删除笔记失败，请联系管理员!");
				},
				async:true
			});
		});
	});
}