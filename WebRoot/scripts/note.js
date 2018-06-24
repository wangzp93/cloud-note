function loadnotes(){
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_2").show();
	$("#pc_part_5").hide();
	$("#pc_part_3").show();
//	$("#pc_part_5").hide();
//	$("#pc_part_3").show();
	/*
	 * 为当选中击笔记本添加CSS
	 */
	clearNoteBody();
	addCheckedCss(this);
	bookId = $(this).data("bookId");
	$.ajax({
		url:IP + "note/loadNotes.do",
		data:{"bookId":bookId, "userId":userId, "noteType":"delete_N"},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				$("#notes").empty();
				var data = result.data;
				for(var i=0; i<data.length; i++){
					var $li = $(
						'<li class="online">' +
							'<a>' +
								'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +
								data[i].ycu_note_title +
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
					$li.data("noteId", data[i].ycu_note_id);
					$("#notes").append($li);
				}
			}
		},
		error:function(){
			alertError("加载笔记列表失败，请联系管理员!");
		},
		async:true
	});
}
//添加笔记
function addNote(){
	/*
	 * 加载添加笔记页面
	 */
	$("#opacity_bg").show();
	$("#can").load("alert/alert_note.html");
	$("#can").off("click", "#addnote_sure").on("click", "#addnote_sure", function(){
		var input_note = $("#input_note").val().trim();
		if(!input_note){
			alertError("笔记名为空!");
		}else{
			$.ajax({
				url:IP + "note/addNote.do",
				data:{"userId":userId, "bookId":bookId, "noteTitle":input_note},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						$("#can").empty();
						alertSuccess(result.msg);
						$("#notes li a").removeClass();
						var $li = $(
							'<li class="online">' +
							'<a class="checked">' +
								'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +
								input_note +
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
						'</li>'
						);
						/*
						 * 为笔记绑定noteId
						 */
						$li.data("noteId", result.data);
						$("#notes").prepend($li);
						noteId = result.data;
//						$("#input_note_title").val(input_note);
//						um.setContent("");
						loadNoteBody($li);
					}else if(result.status == 1 || result.status == 2){
						alertError(result.msg);
					}
				},
				error:function(){
					alertError("添加笔记失败，请联系管理员!");
				},
				async:true
			});
		}
	});
}
//展示下拉菜单
function showDownMenu(){
	var $note_menu = $(this).parent().next(".note_menu");
//	console.log($(this).parents("ul").find(".note_menu"));
//	$("#notes .note_menu").not($note_menu).slideUp(500);
//	$(this).parents("ul").children("li .note_menu").not($note_menu).slideUp(500);
	$(this).parents("ul").find(".note_menu").not($note_menu).slideUp(500);
	if($note_menu.is(":hidden")){
		$note_menu.slideDown(500);
	}else{
		$note_menu.slideUp(500);
	}
}
//移动笔记
function moveNote(){
	$("#opacity_bg").show();
	$("#can").load("alert/alert_move.html", function(){
//		var $li = $(this).parent("a").parent("li");
//		var $books = $($("#books li").not($("#books li .checked").parent()));
		var $books = $("#books li");
		for(var i=0; i<$books.length; i++){
			var $book = $($books[i]);
			var bookId = $book.data("bookId");
			var bookName = $book.children("a").text();
			$("#moveSelect").append(
				'<option value=' + bookId + '>- ' 
				+ bookName + ' -</option>'
			);
		}
		$("#move_sure").unbind("click").click(function(){
			var newBookId = $("#moveSelect option:selected").val().trim();
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
							$("#opacity_bg").hide();
							$("#notes li .checked").parent().remove();
							$("#input_note_title").val("");
							um.setContent("");
							noteId = undefined;
//							alertSuccess(result.msg);
						}
					},
					error:function(){
						alertError("移动笔记失败，请联系管理员!");
					},
					async:true
				});
			}
		});
	});
}
//分享笔记
function shareNote(){
	$.ajax({
		url:IP + "note/shareNote.do",
		data:{"noteId":noteId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				alertSuccess(result.msg);
			}else if(result.status == 1 || result.status == 2 || result.status == 3){
				alertError(result.msg);
			}
		},
		error:function(){
			alertError("分享笔记失败，请联系管理员!");
		},
		async:true
	});
}
//移除笔记
function removeNote(){
	var $li = $($(this).parents("li"));
//	console.log($li);
	$("#opacity_bg").show();
	$("#can").load("alert/alert_delete_note.html", function(){
		$("#delete_note_sure").unbind("click").click(function(){
			$.ajax({
				url:IP + "note/removeNote.do",
				data:{"noteId":noteId},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
//						$("#notes li .checked").parent().remove();
						$li.remove();
						$("#input_note_title").val("");
						um.setContent("");
						noteId = undefined;
						closeWindow();
//						alertSuccess(result.msg);
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
