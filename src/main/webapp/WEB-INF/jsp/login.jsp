<%@ page language="java"  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
	<!-- <script src="./js/jquery.js" type="text/javascript"></script> -->
	<link href="./css/login.css" rel="stylesheet">
	<link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css" rel="stylesheet">
  	<script type="text/javascript" src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js"></script>
  	<script type="text/javascript" src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script>
  	<script type="text/javascript" src="./js/angular.min.js"></script>
</head>

<body>
    <div class="login">
        <div class="login_title">
            <div class="sysName">客户结算中心</div>
        </div>
        <div class="login_inputPanel">
            <form id='loginForm' action='#' method='post' onsubmit="return subCheck();">
                <input type="hidden" name="doLogin" value="true">
                <div class="user">
                    <input type="text" id='username' name='username' placeholder="用户名" />
                </div>
                <div class="user_icon"></div>
                <div class="key">
                    <input type="password" id='password' name='password' placeholder="密码" />
                </div>
                <div class="key_icon"></div>
                <div class="tip">
                    <span class="error" id='error'></span>
                </div>
                <div class="loginBtn">
                    <a href="javascript:" style="text-decoration:none;" onclick='login()'>登录</a>
                </div>
            </form>
        </div>
    </div>
    
    <div id="msgModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog  modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">提示</h5>
                </div>
                <div class="modal-body">
                    <h4 id="operationResult_result">操作成功</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
                </div>
            </div>
        </div>
    </div>
    <div class="body">Copyright © 2017 晓风. All rights reserved.沪ICP备10005645<span class="login"></span>
    </div>
</body>

<body>

<!-- <form class="sui-form form-horizontal" action="../dologin.do" method="post" id="info">
  <div class="control-group">
    <label for="inputEmail" class="control-label">用户名：</label>
    <div class="controls">
      <input type="text" name="username" id="username"/>
    </div>
  </div>
  <div class="control-group">
    <label for="inputPassword" class="control-label">密码：</label>
    <div class="controls">
      <input type="password" name="password" id="password"/>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label"></label>
    <div class="controls">
      <label data-toggle="checkbox" class="checkbox-pretty inline">
        <input type="checkbox" name="remember-me"><span>记住我</span>
      </label>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label"></label>
    <div class="controls">
      <button type="submit" class="sui-btn btn-primary" id="sb">登录</button>
      <button id="registerBtn" type="button" name="register" class="sui-btn">注册</button>
    </div>
  </div>
</form> -->
<!-- <form action="../dologin.do" method="post">
<table>
	<tr>
		<td><label>用户名</label></td>
		<td><input type="text" name="username"
			style="width: 120;" /></td>
	</tr>
	<tr>
		<td><label>密&nbsp;码</label></td>
		<td><input type="password" name="password"
			style="width: 120;" /></td>
	</tr>
	<tr>
		<td><input type="submit" name="login" value="登录" /></td>
		<td><input id="registerBtn" type="button" name="register" value="注册"/></td>
	</tr>
</table>
</form> -->
<script type="text/javascript">
 /* $(function(){
    $("#registerBtn").click(function(event){
        window.location = "../jsp/register.jsp";
    });
    $('#sb').click(function(){
    	if(null == $('#username').val() || '' == $('#username').val()
    			 || null == $('#password').val() || '' == $('#password').val()){
    		alert("用户名或密码不能为空");
    		return false;
    	}
        var user = $('#info').serialize();
        $.post('../dologin.do',user,function(data){
            if(hasSucc(data)){
                window.location = "../index.jsp";
            }else{
                $('#errMsg').html(data.error);
            }
        });
    	
    });
  }); */
 if (window != top) {
	    top.location.href = location.href;
	}
	//取得cookie
	function getCookie(name) {
	    var nameEQ = name + "=";
	    var ca = document.cookie.split(';');    //把cookie分割成组
	    for (var i = 0; i < ca.length; i++) {
	        var c = ca[i];                      //取得字符串
	        while (c.charAt(0) == ' ') {          //判断一下字符串有没有前导空格
	            c = c.substring(1, c.length);      //有的话，从第二位开始取
	        }
	        if (c.indexOf(nameEQ) == 0) {       //如果含有我们要的name
	            return unescape(c.substring(nameEQ.length, c.length));    //解码并截取我们要值
	        }
	    }
	    return false;
	}

	//设置cookie
	function setCookie(name, value, days) {
	    days = days || 0;   //days有值就直接赋值，没有为0，这个根php不一样。
	    var expires = "";
	    if (days != 0) {      //设置cookie生存时间
	        var date = new Date();
	        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
	        expires = "; expires=" + date.toGMTString();
	    }
	    document.cookie = name + "=" + escape(value) + expires + "; path=/";   //转码并赋值
	}

	function subCheck() {
	    var username = $("#username").val();
	    var pwd = $("#password").val();
	    if (username == "" || username == null || username == undefined) {
	        $(".error").html("用户名不能为空");
	        return false;
	    } else if (pwd == "" || pwd == null || pwd == undefined) {
	        $(".error").html("密码不能为空");
	        return false;
	    }
	    return true;
	};

	function login() {
	    $(".error").html("");
	    var urlLogin = "./login";
	    var validateValue = subCheck();
	    if (!validateValue) {
	        return false;
	    }
	    var userName = $("#username").val();
	    var passWord = $("#password").val();
	    //var sha = hex_sha1(passWord) ; 
	    var queryParams = {
	        "username": userName,
	        "passWord": passWord
	    };
	    var jsonData = {
	        "searchCondition": JSON.stringify(queryParams)
	    };
	    $.ajax({
	        type: "POST",
	        url: urlLogin,
	        dataType: "json",
	        contentType: "application/json",
	        data: JSON.stringify(jsonData),
	        success: function(oData) {
	            // var  data= jQuery.parseJSON(oData);
	            console.log(oData);
	            if (oData != null && oData.errorCode == 0) {
	                window.location.href = "index";
	            } else if (oData != null && oData.errorCode == -2) {
	                $(".error").html(oData.msg);
	            } else {
	                $(".error").html(oData.msg);
	            }
	        }
	    });
	}

	/* function enterLogin(){
	  if (event.keyCode == 13){
	    login();
	  }
	}
	function enterToPass(){
	  if (event.keyCode == 13){
	    $("#password").focus();
	  }
	} */
	var password = document.getElementById("password");
	password.onkeydown = keydownpassword;

	function keydownpassword(e) {
	    e = e ? e : window.event;
	    var keyCode = e.which ? e.which : e.keyCode;
	    if (keyCode == 13) {
	        login();
	    }
	}
	var loginName = document.getElementById("username");
	loginName.onkeydown = keydownloginName;

	function keydownloginName(e) {
	    e = e ? e : window.event;
	    var keyCode = e.which ? e.which : e.keyCode;
	    if (keyCode == 13) {
	        $("#password").focus();
	    }
	}
</script>
</body>
</html>