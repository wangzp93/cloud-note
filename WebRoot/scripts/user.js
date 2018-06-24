function login(){
	var username = $("#count").val().trim();
	var password;
	/*
	 * 获得焦点，设置提示为空并隐藏 
	 */
	$("#count").unbind("focus").focus(function(){
		$("#warning_5").children("span").text("");
		$("#warning_5").hide();
	});
	$("#password").unbind("focus").focus(function(){
		$("#warning_6").children("span").text("");
		$("#warning_6").hide();
	});
	/*
	 * 失去焦点，非空及逻辑判断
	 */
	$("#count").unbind("blur").blur(function(){
		username = $("#count").val().trim();
		if(!username){
			$("#warning_5").children("span").text("用户名为空");
			$("#warning_5").show();
		}
	});
	$("#password").unbind("blur").blur(function(){
		password = $("#password").val().trim();
		if(!password){
			$("#warning_6").children("span").text("密码为空");
			$("#warning_6").show();
		}
	});
	/*
	 * 点击登录，发送Ajax请求
	 */
	$("#login").unbind("click").click(function(){
		if(username && password){
			//发送请求前，做非空判断，并清空提示
			$("#warning_5,#warning_6").children("span").text("");
			$("#warning_5,#warning_6").hide();
			$.ajax({
				url:IP + "user/login.do",
				data:{"username":username,"password":password},
				type:"post",
				dataType:"json",
				success:function(result){
					/*
					 * 根据返回状态码判断登录信息
					 * 0 成功，1 用户不存在，2 密码错误
					 */
					if(result.status == 0){
						window.location.href = "edit.html";
						addCookie("userId", result.data.ycu_user_id, 1);
						addCookie("username", result.data.ycu_user_name, 1);
					}else if(result.status == 1){
						$("#warning_5").children("span").text(result.msg);
						$("#warning_5").show();
					}else if(result.status == 2){
						$("#warning_6").children("span").text(result.msg);
						$("#warning_6").show();
					}
				},
				error:function(){
					alert("登录失败，请联系管理员!");
				},
				async:true
			});
		}
	});
}
function regist(){
	/*
	 * 注册时，隐藏登录
	 */
	$("#count,#password").val("");
	$("#warning_5,#warning_6").children("span").text("");
	$("#warning_5,#warning_6").hide();
	$("#dl").removeClass("log log_in").addClass("log log_out");
	$("#zc").removeClass("sig sig_out").addClass("sig sig_in");
	//定义变量
	var regist_username;
	var nickname;
	var regist_password;
	var final_password;
	/*
	 * 获得焦点，设置提示为空并隐藏
	 */
	$("#regist_username").unbind("focus").focus(function(){
		$("#warning_1").children("span").text("");
		$("#warning_1").hide();
	});
	$("#nickname").unbind("focus").focus(function(){
		$("#warning_4").children("span").text("");
		$("#warning_4").hide();
	});
	$("#regist_password").unbind("focus").focus(function(){
		$("#warning_2").children("span").text("");
		$("#warning_2").hide();
	});
	$("#final_password").unbind("focus").focus(function(){
		$("#warning_3").children("span").text("");
		$("#warning_3").hide();
	});
	/*
	 * 失去焦点，非空及逻辑判断
	 */
	$("#regist_username").unbind("blur").blur(function(){
		regist_username = $("#regist_username").val().trim();
		if(!regist_username){
			$("#warning_1").children("span").text("用户名为空");
			$("#warning_1").show();
		}else if(regist_username.length < 2 || regist_username.length > 6){
			$("#warning_1").children("span").text("长度为2-6位");
			$("#warning_1").show();
		}
	});
	$("#nickname").unbind("blur").blur(function(){
		nickname = $("#nickname").val().trim();
		if(!nickname){
			$("#warning_4").children("span").text("昵称为空");
			$("#warning_4").show();
		}
	});
	$("#regist_password").unbind("blur").blur(function(){
		regist_password = $("#regist_password").val().trim();
		if(!regist_password){
			$("#warning_2").children("span").text("密码为空");
			$("#warning_2").show();
		}else if(regist_password.length < 4 || regist_password.length > 10){
			$("#warning_2").children("span").text("长度为4-10位");
			$("#warning_2").show();
		}else if(final_password && final_password != regist_password){
			$("#warning_3").children("span").text("密码不一致");
			$("#warning_3").show();
		}
	});
	$("#final_password").unbind("blur").blur(function(){
		final_password = $("#final_password").val().trim();
		if(!final_password){
			$("#warning_3").children("span").text("确认密码为空");
			$("#warning_3").show();
		}else if(final_password != regist_password){
			$("#warning_3").children("span").text("密码不一致");
			$("#warning_3").show();
		}
	});
	$("#regist_button").unbind("click").click(function(){
		if(nickname && regist_password == final_password && 
				regist_username.length >=2 && regist_username.length <=6 &&
				regist_password.length >= 4 && regist_password.length <= 10 ){
			$("#warning_1").children("span").text("");
			$("#warning_1").hide();
			$.ajax({
				url:IP + "user/regist.do",
				data:{"username":regist_username,"password":final_password,"nickname":nickname},
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.status == 0){
						/*
						 * 状态码为0，提示注册成功，并返回登录界面
						 */
						alert(result.msg);
//						addCookie("username", regist_username, 1);
						$("#count").val(regist_username);
						back();
					}else if(result.status == 1){
						/*
						 * 状态码为1，提示用户已存在
						 */
						$("#warning_1").children("span").text(result.msg);
						$("#warning_1").show();
					}
				},
				error:function(){
					alert("注册失败，请联系管理员!");
				},
				async:true
			});
		}
	});
}
function back(){
	/*
	 * 返回登录时，隐藏注册
	 */
	$("#warning_1,#warning_4,#warning_2,#warning_3").children("span").text("");
	$("#warning_1,#warning_4,#warning_2,#warning_3").hide();
	$("#regist_username,#nickname,#regist_password,#final_password").val("");
	$("#zc").removeClass("sig sig_in").addClass("sig sig_out");
	$("#dl").removeClass("log log_out").addClass("log log_in");
}