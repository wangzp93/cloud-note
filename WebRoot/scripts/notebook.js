/*
 * 加载分类列表
 */
function loadbooks(){
	 /*
	  * 加载前先清空列表
	  * @param {Object} result
	  */
	$("#books").empty();
	/*
	 * 判断Cookie是否存在
	 * @param {Object} result
	 */
	if(userId == null){
		window.location.href = "log_in.html";
	}else{
		$.ajax({
			url:IP + "book/loadBooks.do",
			data:{"userId":getCookie("userId")},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					var data = result.data;
					/*
					 * 循环生成列表
					 */
					for(var i=0; i<data.length; i++){
						var $li = $(
							'<li class="online">' +
								'<a>' +
									'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+ 
									data[i].ycu_notebook_name +
									'<button type="button" class="btn btn-default btn-xs btn_position btn_delete">' +
										'<i class="fa fa-times"></i>' +
									'</button>' +
								'</a>' +
							'</li>');
						/*
						 * 为每个笔记本绑定bookId
						 */
						$li.data("bookId", data[i].ycu_notebook_id);
						$("#books").append($li);
					}
				}
			},
			error:function(){
				alertError("加载失败，请联系管理员!");
			},
			async:true
		});
	}
}
/*
 * 分类重命名
 */
function renameBook(){
	var $li = $(this);
	bookId = $li.data("bookId");
	var lastBookName = $li.children("a").text().trim();
	/*
	 * 加载页面
	 */
	$("#opacity_bg").show();
	$("#can").load("alert/alert_rename.html", function(){
		$("#input_notebook_rename").val(lastBookName);
	});
	$("#can").off("click", "#renameBook_sure").on("click", "#renameBook_sure", function(){
		var newBookName = $("#input_notebook_rename").val();
		if(!newBookName){
			alertErroe("名称为空!");
		}else if(newBookName == lastBookName){
			alertError("与原名称相同!");
		}else{
			$.ajax({
				url:IP + "book/renameNoteBook.do",
				data:{"bookId":bookId, "newBookName":newBookName},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						$("#can").empty();
						/*$li.children("a").html(
							'<i class="fa fa-book" title="online" rel="tooltip-bottom">' +
							'</i>'+ newBookName
						);*/
						$li.parent("ul").prepend($(
							'<li class="online">' +
								'<a  class="checked">' +
									'<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+ 
									newBookName +
									'<button type="button" class="btn btn-default btn-xs btn_position btn_delete">' +
										'<i class="fa fa-times"></i>' +
									'</button>' +
								'</a>' +
							'</li>'
						));
						$li.remove();
						alertSuccess("修改成功!");
					}
				},
				error:function(){
					alertError("重命名失败，请联系管理员!");
				},
				async:true
			});
		}
	});
}
/*
 * 添加新分类
 */
function addBook(){
	/*
	 * 加载添加页面
	 */
	$("#opacity_bg").show();
	$("#can").load("alert/alert_notebook.html");
	$("#can").off("click", "#addbook_sure").on("click", "#addbook_sure", function(){
		var input_notebook = $("#input_notebook").val().trim();
		if(!input_notebook){
			alertError("名称为空!");
		}else{
			$.ajax({
				url:IP + "book/addNoteBook.do",
				data:{"userId":userId, "bookName":input_notebook},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						$("#can").empty();
						alertSuccess("添加成功!");
						$("#books li a").removeClass();
						var $li = $('<li class="online">' +
							'<a class="checked">' +
							'<i class="fa fa-book" title="online" rel="tooltip-bottom">' +
							'</i>'+ input_notebook +
							'<button type="button" class="btn btn-default btn-xs btn_position btn_delete">' +
							'<i class="fa fa-times"></i>' +
							'</button>' + 
							'</a></li>');
						/*
						 * 为笔记本绑定bookId
						 */
						$li.data("bookId", result.data);
						$("#books").prepend($li);
						bookId = result.data;
						$("#notes").empty();
						clearNoteBody();
					}else if(result.status == 1 || result.status == 2){
						alertError(result.msg);
					}
				},
				error:function(){
					alertError("创建失败，请联系管理员!");
				}
			});
		}
	});
}
//删除分类
function deleteBook(){
	var $li = $($(this).parents("li"));
	$("#opacity_bg").show();
	$("#can").load("alert/alert_delete_notebook.html", function(){
		$("#delete_book_sure").unbind("click").click(function(){
			$.ajax({
				url:IP + "book/deleteBook.do",
				data:{"bookId":bookId},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						$li.remove();
						$("#notes").empty();
						$("#input_note_title").val("");
						um.setContent("");
						noteId = undefined;
						bookId = undefined;
						closeWindow();
//						alertSuccess(result.msg);
					}else if(result.status == 1 || result.status == 2 || result.status == 3){
						alertError(result.msg);
					}
				},
				error:function(){
					alertError("删除类别失败，请联系管理员!");
				},
				async:true
			});
		});
	});
}