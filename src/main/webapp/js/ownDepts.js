$(function(){
	$("[class='modal fade bs-example-modal-lg']").modal({backdrop: 'static', keyboard: false});
	$("[class='modal fade bs-example-modal-lg']").modal('hide');
//	$('#oaPassword').addEventListener("input propertychange",confirmOAPassword,false); 
//	$('#newpassword').addEventListener("input propertychange",valPassword,false); 
//	$('#confirmpassword').addEventListener("input propertychange",confirmPassword,false);
//	
	$('#oaPassword').bind('input propertychange', function() { 
		confirmOAPassword();
		});
		$('#newpassword').bind('input propertychange', function() { 
			valPassword();
		});
		$('#confirmpassword').bind('input propertychange', function() { 
			confirmPassword();
		});
	
});

//1.按钮有默认关闭窗口功能 但是需要按钮再监听其他功能:去掉关闭窗口属性,然后在js调用显示和关闭
//入参实体勿使用内部类
//使用checkbox列
function changeDept(){
	$("#msgModal_change").modal('show');
//	根据工号查询所关联的部门
	
//	获取工号
	var no=$("#userEmpCode");
	var code=$("#userEmpCode").text();
	var orgName=$("#orgname").val();
	var queryData={
				userCode:code,
				orgName:orgName
	};
	var columns=[{field: 'checked',checkbox: true},{field: 'orgCode',title: '部门编码'
	    		},{field: 'orgName',title: '部门名称'}];
	var tableParams = {tableId: "queryOwnDepts", columns: columns, url: "auth/dept/queryRelateDept", 
			queryFormId: "formSeach",queryData:queryData, orderBy: "orgName", isStringQueryData: true};
	loadBootstrapTable(tableParams);
}
function showUpdate(){
	if($("#theEmpCode").val()==""||$("#theEmpCode").val()==null||$("#theEmpCode").val()==undefined){
		$("#theEmpCode").val($("#userEmpCode").text());		
	}
	$("#msgModalupdatePassword").modal('show');		
}


function confirmChange(){
	var choose = $("#queryOwnDepts").bootstrapTable('getSelections')[0];
	if(choose == undefined){//未选择
		$("#msgModal_change").modal('hide');
		return;
	}else{
		var data={
				orgCode:choose.orgCode
		};
		$("#msgModal_change").modal('hide');
		$.ajax({
			type:"POST",
			contentType: 'application/json;charset=utf-8', //设置请求头信息 
			url:"auth/dept/change",
			async:false,
			data:JSON.stringify(data),
			success:function(){
				loadLoginUser();
//				top.location.href="index#/home";
//				top.location.reload();
				 $('.tabs-inner span').each(function(i, n) {
			            var t = $(n).text();
			            if(t!="主页"){
			            $('#tabsui').tabs('close', t);
			        }
			      });
			}
		});
		$("#queryOwnDepts").bootstrapTable("destroy");
	}
	
}


function showResult(res){
	$("#operationResult_result").text(res);//成功
	$('#msgModal').modal('show');
}
//校验oa密码是否正确 正确 提示正确 错误提示错误
//校验新密码长度是否足够 不足提示不足
//校验确认密码是否和新密码一致
//修改密码
function confirmOAPassword(){
	
//	获取oa密码 
	var oap=$("#oaPassword").val();//"aaaaaa1!"
	if(oap==null||oap.length<6||oap.length>20){
		$('#oamsg').html("密码错误");
		return ;
	}
	var empCode=$("#userEmpCode").text();
	if($("#theEmpCode").val()==""||$("#theEmpCode").val()==null||$("#theEmpCode").val()==undefined){
		empCode=$("#userEmpCode").text();		
	}else{
		empCode=$("#theEmpCode").val();
	}
	var data={
			password:oap,
			empcode:empCode
	};
	$.ajax({
		type:"POST",
		contentType: 'application/json;charset=utf-8',
		data:JSON.stringify(data),
		url:"auth/password/confirm",
		async:false,
		success:function(data){
			var result=jQuery.parseJSON(data);//获取操作结果status
			if(result.success==true){		
//				查询成功 将新密码的disable去掉
				$('#oamsg').html("密码正确");
//				$('#oaPassword').attr("disabled",true); //将oa框disable
				$('#newpassword').attr("disabled",false);//将新密码输入框设置为有效
			}else{
				$('#oamsg').html("密码错误");
			}
		}
	});
}
//校验新密码
function valPassword(){
//	获取新密码
	var np=$('#newpassword').val();
//	进行正则判断
	if(np.length<6||np.length>20){
		$('#newpmsg').html("新密码长度为6到20之间");
	}else{		
		$('#newpmsg').html("新密码符合规则");
		$('#confirmpassword').attr("disabled",false);//将确认密码输入框设置为有效
	}
}
//确认新密码
function confirmPassword(){
//	获取新密码
	var cp=$('#confirmpassword').val();
	var np=$('#newpassword').val();
	if(np==cp){
		$('#confirmpmsg').html("新密码和确认密码一致");
	}else{
		$('#confirmpmsg').html("新密码和确认密码不一致");
	}
}
function resetmsg(){
	$("#oaPassword").val("");
	$("#confirmpassword").val("");
	$("#newpassword").val("");
	$('#confirmpmsg').html("");
	$('#newpmsg').html("");
	$('#oamsg').html("");
//	$("#theEmpCode").val("");
	$('#oaPassword').attr("disabled",false); //将oa框设置为有效
	$('#newpassword').attr("disabled",true);//将新密码输入框设置为disable
	$('#confirmpassword').attr("disabled",true);//将确认密码输入框设置为disable
}
//修改密码
function updatePassword(){
//	获取老密码
//	获取新密码
	var oap=$("#oaPassword").val();
	var cp=$("#confirmpassword").val();
	var np=$("#newpassword").val();
	var empCode="";
	if($("#theEmpCode").val()==""||$("#theEmpCode").val()==null||$("#theEmpCode").val()==undefined){//这个有值就用这个
		$("#theEmpCode").val($("#userEmpCode").text());	
		empCode=$("#theEmpCode").val();
	}else{//没值就用这个
		empCode=$("#theEmpCode").val();		
	}
	if(oap==null||oap==undefined||oap==""){
		$("#msgModal_updatePassword").modal('hide');
		showResult('OA密码为空');
		resetmsg();
		return;
	}
	if(np==null||np==undefined||np==""||np.length<6||np.length>20){
		$("#msgModal_updatePassword").modal('hide');
		showResult('新密码不符合规则');
		resetmsg();
		return;
	}
	if(cp==null||cp==undefined||cp==""||np!=cp){
		$("#msgModal_updatePassword").modal('hide');
		showResult('确认密码与新密码不一致');
		resetmsg();
		return;
	}
	var data={
			oldpassword:oap,
			password:cp,
			empcode:empCode
	};
	$.ajax({
		type:"POST",
		contentType: 'application/json;charset=utf-8',
		data:JSON.stringify(data),
		url:"auth/password/update",
		async:false,
		success:function(data){
			var result=jQuery.parseJSON(data);//获取操作结果status
			$("#msgModalupdatePassword").modal('hide');
			if(result.success==true){		
//				修改成功 
				showResult('修改成功');				
			}else{
//				修改失败				
				showResult('修改失败');
			}
			resetmsg();
		}
	});
}