<%@ page language="java"  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
</head>
	<link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css" rel="stylesheet">
	
	
  	<script type="text/javascript" src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js"></script>
  	<script type="text/javascript" src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script>
  	<script type="text/javascript" src="/js/angular.min.js"></script>
  	
  	
<body >
	<div class="sui-navbar navbar-fixed-top">
  <div class="navbar-inner"><a href="#" class="sui-brand">SUI</a>
    <ul class="sui-nav">
      <li class="active"><a href="#">首页</a></li>
      <li><a href="#">订单列表</a></li>
      <li class="sui-dropdown"><a href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle">其他 <i class="caret"></i></a>
        <ul role="menu" class="sui-dropdown-menu">
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">关于</a></li>
          <li role="presentation"><a role="menuitem" tabindex="-1" href="#">版权申明</a></li>
        </ul>
      </li>
      <li >
      	<a id="name">欢迎您：</a>
      	<input type="hidden" id="username"/>
      </li>
      <li>
      	<a  href="/loginout">退出</a>
      </li>
    </ul>
  </div>
</div>
</body>
<script type="text/javascript" src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
var username;
$(function(){
	var user="<%=session.getAttribute("name")%>"; 
	username = "<%=session.getAttribute("userName")%>"; 
	$("#name").html("欢迎您："+user);
	$("#username").val(username);
});
function loginout(){
	if(username == null){
		window.location.href="/login";
	}
	$.ajax({
        type: "POST",
        url: "/loginout",
        dataType: "json",
        contentType: "application/json",
        data: username,
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
</script>
</html>