//收藏列表
function loadCollects(){
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();
	$("#pc_part_2").hide();
	$("#pc_part_7").show();
	$("#pc_part_3").hide();
	$("#pc_part_5").show();
	$("#collects").empty();
	clearCollectBody();
	$.ajax({
		url:IP + "collect/loadCollects.do",
		data:{"userId":userId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var collects = result.data;
				for(var i=0; i<collects.length; i++){
					var $li = $(
						'<li class="idle">' +
							'<a >' +
								'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' +
								collects[i].ycu_collect_title +
								'<button type="button" class="btn btn-default btn-xs btn_position btn_delete">' +
									'<i class="fa fa-times"></i>' +
								'</button>' +
							'</a>' +
						'</li>'	
					);
					$li.data("collectId", collects[i].ycu_collect_id);
					$("#collects").append($li);
				}
			}
		},
		error:function(){
			alertError("加载收藏失败，请联系管理员!");
		},
		async:true
	});
}
//加载收藏内容
function loadCollectBody(){
	addCheckedCss(this);
	noteId = $(this).data("collectId");
	clearCollectBody();
	$.ajax({
		url:IP + "collect/loadCollectBody.do",
		data:{"collectId":noteId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var collect = result.data;
				$("#noput_note_title").text(collect.ycu_collect_title);
				$("#noput_note_title").next("p").html(collect.ycu_collect_body);
			}else if(result.status == 1){
				alertError(result.msg);
			}
		},
		error:function(){
			alertError("加载收藏内容失败，请联系管理员!");
		},
		async:true
	});
}
//删除收藏
function deleteCollect(){
	var $li = $(this).parents("li");
	noteId = $li.data("collectId");
	$("#opacity_bg").show();
	$("#can").load("alert/alert_delete_like.html", function(){
		$("#delete_like_sure").unbind("click").click(function(){
			$.ajax({
				url:IP + "collect/deleteCollect.do",
				data:{"collectId":noteId},
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
					alertError("删除收藏失败，请联系管理员!");
				},
				async:true
			});
		});
	});
}