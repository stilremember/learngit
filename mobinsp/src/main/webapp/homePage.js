var socket, initSocket, loopInitSocket, loopInitSocket_idto, warningTimes = 5;
top.agsupportUrl=top.agsupportUrl || "http://127.0.0.1/agsupport";
var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

function getRootPath(){
    return location.protocol+"//" + location.host;
}

var serviceList={
    /*iconService: top.agsupportUrl + "/appauthor/listByLoginName",
    noticeService: top.agsupportUrl + "/platformNotice/list",
    todoService: top.agsupportUrl + "/resources/listApplyRecords",
    todoService1: top.agsupportUrl + "/swuser/findMyAuditRecords",
    dataService: top.agsupportUrl + "/datacatalog/findDataRecords"*/
    iconService: "json/iconService.json",
    noticeService: "json/noticeService.json",
    todoService: "json/todoService.json",
    todoService1: "json/todoService1.json",
    dataService: "json/dataService.json"
};

var systemList = new Array();

$.ajax({
	//url: top.agsupportUrl + "/thirdappsys/listAuthoredApps",
	/*url: "json/listAuthoredApps.json",*/
	url: "json/MeauApp.json",
	type:"get",
	dataType:"json",
	async: false,
	//data: {start: 1, rows: 1000, page: 1, loginName: top.loginName },
	success: function (data) {
		var rows = data.rows;
		for(var i=0; i<rows.length; i++) {
			var row = rows[i];
			if(row.authentication === '1' && row.useSso === '0') {
				row.isNeedLogin = '1';
			} else {
				row.isNeedLogin = '0';
			}

			if(row.status === '1' && row.appUrl) {
				// 首页url需特别处理，线上环境有71、78两套, 但用的是同一套库
				row.appUrl = row.appUrl.replace(/(10\.194\.170\.71|10\.194\.170\.78|127\.0\.0\.1)/, location.host);
				systemList.push(row);
			}
		}
	},
	error: function () {
	}
});
//弹出子系统
var onclickDesk = function(event){
    var url = $(event).attr('url');
    var name = $(event).text();
    var wi = window.open(url,name);
    setTimeout(function(){
        wi.document.title=name;
    }, 1000) ;
}
var initCount=0;
function initDeskIcon() {
    $.ajax({
        //url: serviceList.iconService + '?ti=' + new Date().getTime(),
        url: 'json/listByLoginName.json',
		type:"get",
		data: {
			loginName: top.loginName
		},
		dataType:'json',
        success:function(userSysList) {
            if(false){
                for(var key in userSysList) {

                    var system = getSystem(userSysList[key].appId);

                    if (!system) {
                        continue;
                    } else {
                        system.isUserBinded = true;
                    }

                    var checkedImgHtmlContent = '';
                    if(system.useSso === '0' && system.authentication === '1') {
                        system.cancelProxyLogin = (userSysList[key].cancelProxyLogin == 1);
                        if(system.cancelProxyLogin) {
                           // checkedImgHtmlContent = '<span title="点击记住密码" style="left: 2px; bottom: 2px; position: absolute" onclick="toggleCheckedImg(this);"><img src="json/images/unchecked.png" style="height:16px; width:16px;"  /></span>';
                        } else {
                            //checkedImgHtmlContent = '<span title="取消记住密码" style="left: 2px; bottom: 2px; position: absolute" onclick="toggleCheckedImg(this);"><img src="json/images/checked.png" style="height:16px; width:16px;"  /></span>';
                        }
                    }
                    debugger;
                    var liHtmlContent = ''
                        + '<li class="desktop_icon" data-name="' + userSysList[key].appId + '" data-login-name="' + userSysList[key].loginName + '">'
                        + '<span class="icon">'
                        + '<img src="' + projectName+system.iconAddr + '" />'
                        + checkedImgHtmlContent
                        + '</span>'
                        + '<div style="color:white" class="text">' + system.appName + '</div>'
                        + '</li>';
                    var li = $(liHtmlContent);

                    li.click(
                        (function (userSys, sys) {
                            return function () {
                                //login(sys.appSysId, userSys.appLoginName, userSys.appPassword, false);
                            }
                        })(userSysList[key], system)
                    );

                    //$("#deskIcon").append(li);

                    //initByCount(++initCount);
                }
			}
			// 添加必须在桌面上显示的应用
			for(var i=0; i<systemList.length; i++) {
            	var sysItem = systemList[i];
				if(sysItem.isMustShowInDesktop === '1' && sysItem.isSetVisibleRange != '1' ) {//&& !sysItem.isUserBinded
					var checkedImgHtmlContent = '';
					if(sysItem.appLoginUrl) {
						sysItem.cancelProxyLogin = true;
						checkedImgHtmlContent = '<span title="点击记住密码" style="left: 2px; bottom: 2px; position: absolute" onclick="openSaveUserDialog(this);"><img src="json/images/unchecked.png" style="height:16px; width:16px;"  /></span>';
					}
					var liHtmlContent = ''
						+ '<li class="desktop_icon" onclick="onclickDesk(this)" url="'+sysItem.appUrl+'"' +
						'data-name="' + sysItem.appSysId + '" data-login-name="">'
						+ '<span class="icon">'
							+ '<img src="' + projectName+sysItem.iconAddr + '" />'
							+ checkedImgHtmlContent
						+ '</span>'
						+ '<div style="color: white" class="text">' + sysItem.appName + '</div>'
						+ '</li>';

                    var li = $(liHtmlContent);
                    $("#deskIcon").append(li);
                    initByCount(++initCount);
                }
			}

			/*var addIcon=$("<li class='desktop_icon'><span class='icon'><img src='json/icon/addRemove.png'/></span><div class='text'>添加</div></li>");
			// 添加删除点击事件绑定
			addIcon.click((function () {
				return function () {
					addRemoveSystem();
				}
			})());
			$("#deskIcon").append(addIcon);*/
			initByCount(++initCount);
        },
        error:function() {
            console.error("获取系统列表失败！！");
        }
    });
}

function initNotice() {
	if(window.myroll) {
		clearInterval(window.myroll);
		try {
			delete window.myroll;
		} catch(error) {
			window.myroll = undefined;
		}
	}
    $.ajax({
    	//url:serviceList.noticeService + '?ti=' + new Date().getTime(),
    	url:'json/noticeService.json',
		type: "get",
		//data: {isManagePage: false, loginName: top.loginName, rows: 12},
		dataType:'json',
		async: false,
    	success:function(data) {
			var list = data.rows;
			var liContents = '';
			for(var i=0; i<list.length; i++) {
				var title = list[i].title;
				if(list[i].emergencyLvl == 2) {// 紧急度高
					title = '<font style="color:#F60;">（重要）&nbsp;' + title + "</font>";
				}

				if(list[i].isTop != null && list[i].isTop == '1') {
					liContents += '<li onclick="showNoticeDetail(\'' + list[i].id + '\')" ><a class="noticeName" href="#" title="' + list[i].title + '"><font style="color:#F60;">[置顶]</font>&nbsp;' + title + '</a><span>' + list[i].noticeTime.split(/\s/)[0] + '</span></li>';
				} else {
					liContents += '<li onclick="showNoticeDetail(\'' + list[i].id + '\')" ><a class="noticeName" href="#" title="' + list[i].title + '">' + title + '</a><span>' + list[i].noticeTime.split(/\s/)[0] + '</span></li>';
				}
        	}

			$("#platformNotice").html(liContents);

			// 长度要超出ul的范围, 进行滚动效果处理
			if(list.length > 6) {
				window.textRoll = function () {
					$("#platformNotice li:first").animate({
						marginTop: "-36px"
					}, 2000, function () {
						$(this).css("marginTop", "0").appendTo('#platformNotice');
					});
				};
				window.myroll = setInterval(window.textRoll, 2000);
				$("#platformNotice li").hover(function () {
					clearInterval(window.myroll);
					try {
						delete window.myroll;
					} catch(error) {
						window.myroll = undefined;
					}
				}).mouseout(function () {
					if(!window.myroll) {
						window.myroll = setInterval(textRoll, 2000);
					}
				});
			}
		},
		error:function(){
			layer.msg('获取公告失败', { time: 1000 });
		}
	});
}

function audit(instanceId, title, applyingLoginName) {
    window.parent.openAuditPanalInHomePage(title, instanceId, applyingLoginName, {
        // 成功的回调方法
        initData: function() {
            $("#schedule").children("#" + instanceId).remove();
            //如果最后一条记录被删除,则隐藏div
            if($("#schedule li").length==0){
            	$("#dbsx").hide();
            }
        }
    });
}

function showNoticeDetail(id){
	if(layer.ie == 8) {// IE8下特别处理
		window.open("/awater/view/noticeDetail/noticeDetail.html?id=" + id);
	} else {
		layer.open({
			type: 2,
			content: ["/awater/view/noticeDetail/noticeDetail.html?id="+id],
			shadeClose: true,
			title: false,
			area: ['99%', '99%']
		});
	}
}

//发布公告
function release() {
	window.parent.releaseNoticePanelInHomePage('通知公告发布', {
		// 成功的回调方法
		initData: function() {
			// 在IE下，initNotice方法调用后莫名其妙增加随机几个空行li元素
			// initNotice();
			location.reload();
	}});
}
function moreData(){
	layer.open({
		type: 2,
		content: ["/awater/view/noticeDetail/morePlatformNotice.html?ti=" + new Date().getTime(), 'no'],
		title: "通知公告",
		area: ['95%', '98%'],
		btn: [],
		end: function () {
		 	location.reload();
		}
	});
}

function manage(){
	layer.open({
		type: 2,
		content: ["/awater/view/noticeDetail/platformNotice.html?ti=" + new Date().getTime(), 'no'],
		title: "通知公告管理",
		area: ['95%', '98%'],
		btn: [],
		end: function () {
			location.reload();
		}
	});
} 
 
function initByCount(count){
    if(count>=1){
        initPage();
    }
}

function addRemoveSystem() {
    layer.open({
        type: 2,
        content: 'sysCenter.html?ti=' + new Date().getTime(),
        title: '业务系统中心',
		offset: '2px',
        area: ['1100px', '580px'],
        btn: [],
        end: function () {
            location.reload();
        }
    });
}

function getSystem(appId){
	for(var index in systemList) {
		if (systemList[index].appSysId === appId) {
			return systemList[index];
		}
	}
}

function toggleCheckedImg(item) {
	if(window.event){//IE下阻止冒泡
		event.cancelBubble  = true;
	} else{
		event.stopPropagation();
	}

	var sys = getSystem($(item).closest("li").attr("data-name"));
	var cancelProxyLogin = $(item).attr('title') === '取消记住密码';
	$.ajax({
		url: top.agsupportUrl + '/appauthor/save',
		type: 'post',
		dataType:'json',
		data: {
			loginName: loginName,
			appId: sys.appSysId,
			cancelProxyLogin: (cancelProxyLogin ? 1 : 0)
		},
		success: function (data) {
			if(cancelProxyLogin) {
				$(item).attr('title', '点击记住密码');
				$(item).find("img").attr('src', 'json/images/unchecked.png');
				sys.cancelProxyLogin = true;
			} else {
				$(item).attr('title', '取消记住密码');
				$(item).find("img").attr('src', 'json/images/checked.png');
				sys.cancelProxyLogin = false;
			}
		}
	});

	return false;
}

function openSaveUserDialog(item) {
	if(window.event){//IE下阻止冒泡
		event.cancelBubble  = true;
	} else{
		event.stopPropagation();
	}
	if($(item).attr('title') === '取消记住密码') {
		$(item).attr('title', '点击记住密码');
		$(item).find("img").attr('src', 'images/unchecked.png');
		return;
	} else {
		$(item).attr('title', '取消记住密码');
		$(item).find("img").attr('src', 'images/checked.png');
	}


	var sys = getSystem($(item).closest("li").attr("data-name"));
	var divHtmlContent = ''
		+ '<div id="password-edit-container">'
		+ '<input class="txt03" type="hidden" name="appId" value="' + sys.appSysId + '" disabled />'
		+ '<div class="form-item">'
		+ '<span class="form-label">系统名称:</span>'
		+ '<div class="input-block">'
		+ '<input class="txt03" type="text" name="showName" value="' + sys.appName + '" disabled />'
		+ '</div>'
		+ '</div>'
		+ '<div class="form-item">'
		+ '<span class="form-label">系统用户名:</span>'
		+ '<div class="input-block">'
		+ '<input class="txt03" type="text" name="sysLoginName" />'
		+ '&nbsp;&nbsp;<span class="invalid-msg"></span>'
		+ '</div>'
		+ '</div>'
		+ '<div class="form-item">'
		+ '<span class="form-label">系统密码:</span>'
		+ '<div class="input-block">'
		+ '<input class="txt03" type="password" name="sysLoginPass" />'
		+ '&nbsp;&nbsp;<span class="invalid-msg"></span>'
		+ '</div>'
		+ '</div>'
		+ '<div id="conn-msg" style="text-align: center; color: #FF7F27;">'
		+ '</div>'
		+ '</div>';

	layer.open({
		content: divHtmlContent,
		title: '保存业务系统用户信息',
		area: ['650px', '400px'],
		btn: ['确定', '测试连接', '取消'],
		resize: false,
		btnAlign: 'c',
		yes: function (index) {
			var appId = $('input[name="appId"]').val();// 第三方系统编码
			var sysLoginName = $('input[name="sysLoginName"]').val();// 第三方登录用户
			var sysLoginPass = $('input[name="sysLoginPass"]').val();// 第三方登录密码

			if (!$.trim(sysLoginName)) {
				$('.invalid-msg').text('');
				$('input[name="sysLoginName"]').parent('div').find('span').text('用户名不能为空');
				return false;
			}

			if (!$.trim(sysLoginPass)) {
				$('.invalid-msg').text('');
				$('input[name="sysLoginPass"]').parent('div').find('span').text('密码不能为空');
				return false;
			}

			// 保存第三方业务系统用户和密码信息
			$.ajax({
				xhrFields: {withCredentials: true},
				url: '/agsupport/appauthor/save',
				type: 'POST',
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				data: {
					loginName: loginName,
					appId: appId,
					authorizeStatus: 'ok',
					appLoginName: sysLoginName,
					appPassword: sysLoginPass
				},
				dataType: 'json',
				success: function (data) {
					if (data.success) {
						layer.msg('添加成功', {time: 2000});
						location.reload();
					} else {
						layer.msg('添加失败', {time: 2000});
					}
					// 关闭弹出确认框
					layer.close(index);
				},
				error: function () {
					layer.msg('添加失败', {time: 2000});
				}
			});
		},
		success: function (layer) {
			$(".layui-layer-btn-c a:eq(1)").removeClass('layui-layer-btn1');
			$(".layui-layer-btn-c a:eq(1)").addClass('layui-layer-btn3');
			//layer[0].childNodes[3].childNodes[1].attributes[0].value='layui-layer-btn3';
			$(".layui-layer-content").css("min-height", "280px");
		},
		btn2: function (index) {
			var appId = $('input[name="appId"]').val();// 第三方系统编码
			var sysLoginName = $('input[name="sysLoginName"]').val();// 第三方登录用户
			var sysLoginPass = $('input[name="sysLoginPass"]').val();// 第三方登录密码

			if (!$.trim(sysLoginName)) {
				$('.invalid-msg').text('');
				$('input[name="sysLoginName"]').parent('div').find('span').text('用户名不能为空');
				return false;
			}

			if (!$.trim(sysLoginPass)) {
				$('.invalid-msg').text('');
				$('input[name="sysLoginPass"]').parent('div').find('span').text('密码不能为空');
				return false;
			}

			var connMsgs = ['正在连接系统中.', '正在连接系统中..', '正在连接系统中...', '正在连接系统中....'];
			var connMsgsIndex = 0;

			function showConnMsg() {
				$("#conn-msg").css("color", "#FF7F27").text(connMsgs[connMsgsIndex++ % 4]);
			}

			var connMsgsShowId;

			$.ajax({
				xhrFields: {withCredentials: true},
				url: sys.appLoginUrl,
				data: "loginName=" + sysLoginName + "&password=" + sysLoginPass,
				dataType: 'json',
				beforeSend: function () {
					connMsgsShowId = setInterval(showConnMsg, 500);
				},
				complete: function () {
					if (connMsgsShowId) {
						clearInterval(connMsgsShowId);
						connMsgsShowId = null;
					}
				},
				success: function (data) {
					if (data.success) {
						$("#conn-msg").css("color", "green").text("连接成功！");
					} else {
						$("#conn-msg").css("color", "#f55").text("连接失败，请核对输入的用户名和密码信息！");
					}
				},
				error: function () {
					if (connMsgsShowId) {
						clearInterval(connMsgsShowId);
						connMsgsShowId = null;
					}
					$("#conn-msg").css("color", "#f55").text("连接失败，请核对输入的用户名和密码信息！");
				}
			});

			return false;
		}
	});

	return false;
}