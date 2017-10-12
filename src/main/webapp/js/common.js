function getBastPath() {
	var url = "login/";
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = getStatusBack;
	xmlHttp.setRequestHeader("Content-Type",
		"application/x-www-form-urlencoded;");
	xmlHttp.send(xml);
};

/**************************************************定义常量begin*************************************************/
var Constants = (function() {
	/*************数字常量***********/
	var NUMBER_ZERO = 0;
	var NUMBER_ONE = 1;
	var NUMBER_TWO = 2;
	var NUMBER_THREE = 3;
	var NUMBER_FOUR = 4;
	var NUMBER_TWOHUNDRED = 200;

	/*************操作类型常量***********/
	var OPERATION_ADD = "AD";
	var OPERATION_LIST = "SH";
	var OPERATION_UPDATE = "UP";

	/*************HTML类型常量***********/
	var HTMLTYPE_DISABLED = "disabled";

	/*************权限部门类型***********/
	//权限标记URL
	var isPrivUrl = "/cubc/basic/isPriv?url=";
	//部门类型URL
	var deptTypeUrl = "/cubc/basic/deptType";

	/*************权限部门类型***********/
	var ALL="all";//全国权限
	var CURRENT="current";//当前部门权限
	var CHILD="child";//当前及下级部门权限


	/*************权限部门类型***********/
	//顶级部门
	var FIRST="first";
	//本部
	var BENBU="benbu";
	//事业部
	var DIVISION="division";
	//大区
	var BIGREGION="bigregion";
	//小区
	var SMALLREGION="smallregion";
	 //营业部/点部
	var BUSSINESSDEPT="bussinessdept";
	//未知类型部门
	var UNKNOWN="unknown";



	//定义静态返回方法
	var rseult={};
	rseult.getNUMBER_ZERO=function() {
		return NUMBER_ZERO;
	};
	rseult.getNUMBER_ONE=function() {
		return NUMBER_ONE;
	};
	rseult.getNUMBER_TWO=function() {
		return NUMBER_TWO;
	};
	rseult.getNUMBER_THREE=function() {
		return NUMBER_THREE;
	};
	rseult.getNUMBER_FOUR=function() {
		return NUMBER_FOUR;
	};
	rseult.getNUMBER_TWOHUNDRED=function() {
		return NUMBER_TWOHUNDRED;
	};
	rseult.getOPERATION_ADD=function() {
		return OPERATION_ADD;
	};
	rseult.getOPERATION_LIST=function() {
		return OPERATION_LIST;
	};
	rseult.getOPERATION_UPDATE=function() {
		return OPERATION_UPDATE;
	};
	rseult.getHTMLTYPE_DISABLED=function() {
		return HTMLTYPE_DISABLED;
	};

	rseult.getALL=function() {
		return ALL;
	};
	rseult.getCURRENT=function() {
		return CURRENT;
	};
	rseult.getCHILD=function() {
		return CHILD;
	};
	rseult.getFIRST=function() {
		return FIRST;
	};
	rseult.getBENBU=function() {
		return BENBU;
	};
	rseult.getDIVISION=function() {
		return DIVISION;
	};
	rseult.getBIGREGION=function() {
		return BIGREGION;
	};
	rseult.getSMALLREGION=function() {
		return SMALLREGION;
	};
	rseult.getBUSSINESSDEPT=function() {
		return BUSSINESSDEPT;
	};
	rseult.getUNKNOWN=function() {
		return UNKNOWN;
	};
	rseult.getISPRIVURL=function() {
		return isPrivUrl;
	};
	rseult.getDEPTTYPEURL=function() {
		return deptTypeUrl;
	};
	return rseult;
})();
/**************************************************定义常量end*************************************************/


/**************************************************序列化表单begin*************************************************/
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	return o;
};
/**************************************************序列化表单end*************************************************/

/**************************************************自动填充表单begin*************************************************/
var fillForm = function ($form, json) {
	var jsonObj = json;
	if (typeof json === 'string') {
		jsonObj = $.parseJSON(json);
	}

	for (var key in jsonObj) {  //遍历json字符串
		var objtype = jsonObjType(jsonObj[key]); // 获取值类型

		if (objtype === "array") { //如果是数组，一般都是数据库中多对多关系

			var obj1 = jsonObj[key];
			for (var arraykey in obj1) {
				//alert(arraykey + jsonObj[arraykey]);
				var arrayobj = obj1[arraykey];
				for (var smallkey in arrayobj) {
					setCkb(key, arrayobj[smallkey]);
					break;
				}
			}
		} else if (objtype === "object") { //如果是对象，啥都不错，大多数情况下，会有 xxxId 这样的字段作为外键表的id

		} else if (objtype === "string") { //如果是字符串
			var str = jsonObj[key];
			var date = new Date(str);
			if (date.getDay()) {//判断是否是时间(有问题,暂未处理)
				$("[name=" + key + "]", $form).val(date.Format("yyyy-MM-dd"));
				continue;
			}

			var tagobjs = $("[name=" + key + "]", $form);
			if ($(tagobjs[0]).attr("type") == "radio") {//如果是radio控件
				$.each(tagobjs, function (keyobj,value) {
					if ($(value).attr("val") == jsonObj[key]) {
						value.checked = true;
					}
				});
				continue;
			}

			$("[name=" + key + "]", $form).val(jsonObj[key]);

		} else { //其他的直接赋值
			$("[name=" + key + "]", $form).val(jsonObj[key]);
		}

	}
}

function isdate(intYear,intMonth,intDay){

	if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) return false;

	if(intMonth>12||intMonth<1) return false;

	if ( intDay<1||intDay>31)return false;

	if((intMonth==4||intMonth==6||intMonth==9||intMonth==11)&&(intDay>30)) return false;

	if(intMonth==2){

		if(intDay>29) return false;

		if((((intYear%100==0)&&(intYear%400!=0))||(intYear%4!=0))&&(intDay>28))return false;

	}

	return true;
}

var setCkb = function (name, value) {
	//alert(name + " " + value);
	//$("[name=" + name + "][value=" + value + "]").attr("checked", "checked");  不知为何找不到具体标签;
	$("[name=" + name + "][val=" + value + "]").attr("checked", "checked");
}

var fillckb = function (name, json) {
	var jsonObj = json;
	if (typeof json === 'string') {
		jsonObj = $.parseJSON(json);
	}
	var str = jsonObj[name];
	if (typeof str === "string") {
		var array = str.split(",");
		$.each(array, function (key, value) {
			setCkb(name, value);
		});
	}
}

var jsonObjType = function (obj) {
	if (typeof obj === "object") {
		var teststr = JSON.stringify(obj);
		if (teststr[0] == '{' && teststr[teststr.length - 1] == '}') return "class";
		if (teststr[0] == '[' && teststr[teststr.length - 1] == ']') return "array";
	}
	return typeof obj;
}
/**************************************************自动填充表单end*************************************************/



/**************************************************禁止表单控件begin*************************************************/
var prohibitForm = function(formId, isDisabled) {
	$("form[id='" + formId + "'] :text").attr(Constants.getHTMLTYPE_DISABLED(), isDisabled);
	$("form[id='" + formId + "'] textarea").attr(Constants.getHTMLTYPE_DISABLED(), isDisabled);
	$("form[id='" + formId + "'] select").attr(Constants.getHTMLTYPE_DISABLED(), isDisabled);
	$("form[id='" + formId + "'] :radio").attr(Constants.getHTMLTYPE_DISABLED(), isDisabled);
	$("form[id='" + formId + "'] :checkbox").attr(Constants.getHTMLTYPE_DISABLED(), isDisabled);
};
/**************************************************禁止表单控件end*************************************************/


/**************************************************日期格式化begin*************************************************/
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth()+1,                 //月份
		"d+" : this.getDate(),                    //日
		"h+" : this.getHours(),                   //小时
		"m+" : this.getMinutes(),                 //分
		"s+" : this.getSeconds(),                 //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
};
/**************************************************日期格式化end*************************************************/


/**************************************************自定义图标begin*************************************************/
//成功图标
var successHtml = '<span class="icon-ok icon-2x" ></span>';
//失败图标
var failHtml = '<span class="icon-remove icon-2x" ></span>';
//警告图标
var warningHtml = '<span class="icon-warning-sign icon-2x" ></span>';
/**************************************************自定义图标end*************************************************/


/**************************************************Table插件抽离公共 begin*************************************************/
initializationTable = function($table){
	var locationsearch = location.search.substring(1);//处理location带参数的请求（?xxx=xxx）
	if(locationsearch.indexOf(".js") == -1){
		locationsearch = "";
	}
	var scripts = [
			locationsearch
			|| '/cubc/libs/bootstrap-table/js/bootstrap-table-editable.js' ],

		eachSeries = function(arr, iterator, callback) {
			callback = callback || function() {
				};
			if (!arr.length) {
				return callback();
			}
			var completed = 0;
			var iterate = function() {
				iterator(arr[completed], function(err) {
					if (err) {
						callback(err);
						callback = function() {
						};
					} else {
						completed += 1;
						if (completed >= arr.length) {
							callback(null);
						} else {
							iterate();
						}
					}
				});
			};
			iterate();
		};
	eachSeries(scripts, getScript, $table);
};

function initializationTable1($table){
    var locationsearch = location.search.substring(1);//处理location带参数的请求（?xxx=xxx）
    if(locationsearch.indexOf(".js") == -1){
        locationsearch = "";
    }
    var scripts = [
            locationsearch
            || '/cubc/libs/bootstrap-table/js/bootstrap-table-editable.js' ],

        eachSeries = function(arr, iterator, callback) {
            callback = callback || function() {
                };
            if (!arr.length) {
                return callback();
            }
            var completed = 0;
            var iterate = function() {
                iterator(arr[completed], function(err) {
                    if (err) {
                        callback(err);
                        callback = function() {
                        };
                    } else {
                        completed += 1;
                        if (completed >= arr.length) {
                            callback(null);
                        } else {
                            iterate();
                        }
                    }
                });
            };
            iterate();
        };
    eachSeries(scripts, getScript, $table);
}

function getScript(url, callback) {
	var head = document.getElementsByTagName('head')[0];
	var script = document.createElement('script');
	script.src = url;
	var done = false;
	// Attach handlers for all browsers
	script.onload = script.onreadystatechange = function() {
		if (!done
			&& (!this.readyState
			|| this.readyState == 'loaded' || this.readyState == 'complete')) {
			done = true;
			if (callback)
				callback();
			script.onload = script.onreadystatechange = null;
		}
	};
	head.appendChild(script);
	return undefined;
}
/**************************************************Table插件抽离 end*************************************************/

/**************************************************公共方法 begin*************************************************/
//responseHandler 处理结果集
function responseHandler(res) {
	$.each(res.rows, function(i, row) {
		row.state = $.inArray(row.id, selections) !== -1;
	});
	return res;
}
window.operateEvents = {
	'click .like' : function(e, value, row, index) {
		alert('You click like action, row: ' + JSON.stringify(row));
	},
	'click .remove' : function(e, value, row, index) {
		$table.bootstrapTable('remove', {
			field : 'id',
			values : [ row.id ]
		});
	}
};

//金额返回
function totalPriceFormatter(data) {
	return data;
}
//获取屏幕高度乘以0.7(px)
function getHeight() {
	return $(window).height()*0.7;
}

//自定义Table高度
function getCustom(){
	return $('h1').outerHeight(true);
}

//处理Date 类型
function totalDateFormatter(date) {
	if (date!=null) {
		return new Date(date).Format("yyyy-MM-dd");
	}
	return "";
}

//处理Date 类型
function totalDateDetailFormatter(date) {
	if (date!=null) {
		return new Date(date).Format("yyyy-MM-dd hh:mm:ss");
	}
	return "";
}
//处理Date 类型
function totalDateDetailFormatters(date) {
	if(date == null || date == ""){
		return null;
	}
	return new Date(date).Format("yyyy-MM-dd");
}


/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 *
 * @param num 数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
			num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + num + '.' + cents);
}

/**
 * 格式化后金额转换成float类型
 * @param money
 * @returns {Number}
 */
function formatRmoney(money) {
	return parseFloat(money.replace(/[^\d\.-]/g, ""));
}


//校验时间(月)
function checkDate($startDate,$endDate,day){
	var nowStartDate = $startDate.val();
	var nowEndDate = $endDate.val();
	var maxDate = 1000 * 60 * 60 * 24 * day;
	var dayTime = day/30;
	$startDate.datetimepicker().on("changeDate", function(e){
		var startDate = new Date($startDate.val()).getTime();
		var entDate = new Date($endDate.val()).getTime();
		if(startDate > entDate){
			$.alert("提示","开始时间不能大于结束日期");
			$startDate.datetimepicker("update",nowStartDate);
			return;
		}
		var nowTime = entDate - startDate;
		if(nowTime >= maxDate){
			$.alert("提示","最多选择"+dayTime+"个月");
			$startDate.datetimepicker("update",nowStartDate);
		}else{
			nowStartDate = $startDate.val();
		}
	});

	$endDate.datetimepicker().on("changeDate", function(e){
		var startDate = new Date($startDate.val()).getTime();
		var entDate = new Date($endDate.val()).getTime();
		var nowTime = entDate - startDate;
		if(entDate < startDate){
			$.alert("提示","结束时间不能小于开始日期");
			$endDate.datetimepicker("update",nowEndDate);
			return;
		}
		if(nowTime >= maxDate){
			$.alert("提示","最多选择"+dayTime+"个月");
			$endDate.datetimepicker("update",nowEndDate);
		}else{
			nowEndDate = $endDate.val();
		}
	});
}

//校验时间(天)
function checkDateDay($startDate,$endDate,day){
	var nowStartDate = $startDate.val();
	var nowEndDate = $endDate.val();
	var maxDate = 1000 * 60 * 60 * 24 * day;
	var dayTime = day;
	$startDate.datetimepicker().on("changeDate", function(e){
		var startDate = new Date($startDate.val()).getTime();
		var entDate = new Date($endDate.val()).getTime();
		if(startDate > entDate){
			$.alert("提示","开始时间不能大于结束日期");
			$startDate.datetimepicker("update",nowStartDate);
			return;
		}
		var nowTime = entDate - startDate;
		if(nowTime >= maxDate){
			$.alert("提示","最多选择"+dayTime+"天");
			$startDate.datetimepicker("update",nowStartDate);
		}else{
			nowStartDate = $startDate.val();
		}
	});

	$endDate.datetimepicker().on("changeDate", function(e){
		var startDate = new Date($startDate.val()).getTime();
		var entDate = new Date($endDate.val()).getTime();
		var nowTime = entDate - startDate;
		if(entDate < startDate){
			$.alert("提示","结束时间不能小于开始日期");
			$endDate.datetimepicker("update",nowEndDate);
			return;
		}
		if(nowTime >= maxDate){
			$.alert("提示","最多选择"+dayTime+"天");
			$endDate.datetimepicker("update",nowEndDate);
		}else{
			nowEndDate = $endDate.val();
		}
	});
}


/**
 * 公共权限配置
 * authUrl : 权限URL
 * benbuID、benbuCode : 本部ID、本部CODE
 * causeDeptID、buCode ： 事业部ID、事业部CODE
 * bigRegionID、bigAreaCode ： 大区ID、小区CODE
 * smallRegionID、smallAreaCode ： 小区ID、小区CODE
 * deptNameID、deptCode ： 营业部ID、营业部CODE
 */
function jurisdiction(authUrl,benbuID,benbuCode,causeDeptID,buCode,bigRegionID,bigAreaCode,
			smallRegionID,smallAreaCode,deptNameID,deptCode){
	/***************权限加载***************/
		//获取登录部门类型
	$.get(Constants.getDEPTTYPEURL(), function (result) {
		var deptType = eval("("+result+")");
		//获取登录部门拥有权限
		$.get(Constants.getISPRIVURL()+authUrl, function (resultisPri) {
			var isPri = eval("("+resultisPri+")");

			//判断登录部门权限
			if(isPri == Constants.getALL()){//全国权限
				//TODO 全国权限则不处理
			}else {//当前部门权限
				$.get("/cubc/basic/queryParent/"+deptType, function (resultJson) {
					var json = eval("("+resultJson+")");
					var benBuItem = eval("("+json.benbu+")");
					var divisionItem = eval("("+json.division+")");
					var bigRegionItem = eval("("+json.bigregion+")");
					var smallregionItem = eval("("+json.smallregion+")");
					var bussinessdeptItem = eval("("+json.bussinessdept+")");
					if(deptType == Constants.getBENBU()){
						$("#"+causeDeptID).prop("disabled",true);
						$("#"+causeDeptID).val(benBuItem.deptName);
						$("#"+buCode).val(benBuItem.deptCode);
					}else if(deptType == Constants.getDIVISION()){
						$('#'+causeDeptID).attr('disabled',true);
						$('#'+causeDeptID).next().addClass('ac_button_disabled');
						$("#"+causeDeptID).val(divisionItem.deptName);
						$("#"+buCode).val(divisionItem.deptCode);
						$('#'+causeDeptID).blur();
						$('#'+bigRegionID).blur();
						$('#'+smallRegionID).blur();
					}else if(deptType == Constants.getBIGREGION()){
						$('#'+causeDeptID).attr('disabled',true);
						$('#'+causeDeptID).next().addClass('ac_button_disabled');
						$("#"+causeDeptID).val(divisionItem.deptName);
						$("#"+buCode).val(divisionItem.deptCode);
						$('#'+bigRegionID).attr('disabled',true);
						$('#'+bigRegionID).next().addClass('ac_button_disabled');
						$("#"+bigRegionID).val(bigRegionItem.deptName);
						$("#"+bigAreaCode).val(bigRegionItem.deptCode);
						$('#'+bigRegionID).blur();
						$('#'+smallRegionID).blur();
					}else if(deptType == Constants.getSMALLREGION()){
						$('#'+causeDeptID).attr('disabled',true);
						$('#'+causeDeptID).next().addClass('ac_button_disabled');
						$("#"+causeDeptID).val(divisionItem.deptName);
						$("#"+buCode).val(divisionItem.deptCode);
						$('#'+bigRegionID).attr('disabled',true);
						$('#'+bigRegionID).next().addClass('ac_button_disabled');
						$("#"+bigRegionID).val(bigRegionItem.deptName);
						$("#"+bigAreaCode).val(bigRegionItem.deptCode);
						$('#'+smallRegionID).attr('disabled',true);
						$('#'+smallRegionID).next().addClass('ac_button_disabled');
						$("#"+smallRegionID).val(smallregionItem.deptName);
						$("#"+smallAreaCode).val(smallregionItem.deptCode);
						$('#'+smallRegionID).blur();
					}else if(deptType == Constants.getBUSSINESSDEPT()){
						$('#'+causeDeptID).attr('disabled',true);
						$('#'+causeDeptID).next().addClass('ac_button_disabled');
						$("#"+causeDeptID).val(divisionItem.deptName);
						$("#"+buCode).val(divisionItem.deptCode);
						$('#'+bigRegionID).attr('disabled',true);
						$('#'+bigRegionID).next().addClass('ac_button_disabled');
						$("#"+bigRegionID).val(bigRegionItem.deptName);
						$("#"+bigAreaCode).val(bigRegionItem.deptCode);
						$('#'+smallRegionID).attr('disabled',true);
						$('#'+smallRegionID).next().addClass('ac_button_disabled');
						$("#"+smallRegionID).val(smallregionItem.deptName);
						$("#"+smallAreaCode).val(smallregionItem.deptCode);
						$('#'+deptNameID).attr('disabled',true);
						$('#'+deptNameID).next().addClass('ac_button_disabled');
						$("#"+deptNameID).val(bussinessdeptItem.deptName);
						$("#"+deptCode).val(bussinessdeptItem.deptCode);
					}
				});
			}
		});
	});
}

/**************************************************公共方法 end*************************************************/

//# sourceMappingURL=jquery.min.map

