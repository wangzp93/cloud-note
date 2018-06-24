function reload(){
	$("#load_list").empty();						
	$("#page").val("1");
	$("#search_share").val("");
	clearShareBody();
	var page = parseInt($("#page").val().trim());
//	console.log(page);
	load(page);
}
function load(page, shareTitle){
	$("#load_msg").empty();
//	console.log("page="+page+"  title="+shareTitle);
	$.ajax({
		url:IP + "share/loadShares.do",
		data:{"shareTitle":shareTitle, "page":page},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				$("#page").val(page + 1);
				var shares = result.data;
				for(var i=0; i<shares.length; i++){
					var $li;
//					console.log(shares[i].ycu_user_id != userId);
					if(shares[i].ycu_user_id != userId){
						$li = $(
							'<li class="online">' +
								'<a >' +
									'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> ' +
									shares[i].ycu_share_title +
	//								'<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up">' +
	//									'<i class="fa fa-thumbs-o-up"></i>' +
	//								'</button>' +
	//								'<button type="button" class="btn btn-default btn-xs btn_position_2 btn_down">' +
	//									'<i class="fa fa-thumbs-o-down"></i>' +
	//								'</button>' +
									'<button type="button" class="btn btn-default btn-xs btn_position btn_like">' +
										'<i class="fa fa-star-o"></i>' +
									'</button>' +
								'</a>' +
							'</li>'
						);
					}else{
						$li = $(
							'<li class="online">' +
								'<a >' +
									'<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> ' +
									shares[i].ycu_share_title +
//									'<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up">' +
//										'<i class="fa fa-thumbs-o-up"></i>' +
//									'</button>' +
//									'<button type="button" class="btn btn-default btn-xs btn_position_2 btn_down">' +
//										'<i class="fa fa-thumbs-o-down"></i>' +
//									'</button>' +
									'<button type="button" class="btn btn-default btn-xs btn_position_2 btn_delete">' +
										'<i class="fa fa-times"></i>' +
									'</button>' +
									'<button type="button" class="btn btn-default btn-xs btn_position btn_like">' +
										'<i class="fa fa-star-o"></i>' +
									'</button>' +
								'</a>' +
							'</li>'
						);
					}
					$li.data("shareId", shares[i].ycu_share_id);
					$li.data("userId", shares[i].ycu_user_id);
					$("#load_list").append($li);
				}
			}else if(result.status == 1){
				$("#load_msg").text(result.msg);
			}
		},
		error:function(){
			alertError("加载分享失败，请联系管理员!");
		},
		async:true
	});
}

function addCollect(){
	shareId = $(this).parents("li").data("shareId");
//	console.log(shareId);
	$(".opacity_bg").show();
	$("#modalBasic_14").show();
	$(".sure").unbind("click").click(function(){
		$.ajax({
			url:IP + "share/addCollect.do",
			data:{"shareId":shareId, "userId":userId},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					$("#modalBasic_14").hide();
					alertSuccess(result.msg);
				}else{
					$("#modalBasic_14").hide();
					alertError(result.msg);
				}
			},
			error:function(){
				$("#modalBasic_14").hide();
				alertError("添加收藏失败，请联系管理员!");
			},
			asycn:true
		});
	});
}
function delShare(){
	var $li = $(this).parents("li");
	shareId = $li.data("shareId");
	if(userId == $li.data("userId")){
		$.ajax({
			url:IP + "share/delShare.do",
			data:{"shareId":shareId},
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					$("#share_title span").empty();
					$("#share_title").next("div").empty();
					$li.remove();
					shareId = undefined;
					alertSuccess(result.msg);
				}else{
					alertError(result.msg);
				}
			},
			error:function(){
				alertError("删除分享失败，请联系管理员!");
			},
			async:true
		});
	}else{
		alertError("您不是分享者，没有权限删除此内容!");
	}
}