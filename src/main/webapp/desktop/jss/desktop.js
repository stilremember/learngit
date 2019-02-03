var socket, initSocket, loopInitSocket, loopInitSocket_idto, warningTimes = 5;
var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

function getRootPath(){
    return location.protocol+"//" + location.host;
}
var serviceList={
    iconService: "json/iconService.json",
    noticeService: "json/noticeService.json",
    todoService: "json/todoService.json",
    todoService1: "json/todoService1.json",
    dataService: "json/dataService.json"
};

var systemList = new Array();

$.ajax({
    url: "desktop/json/MeauApp.json",
    type:"get",
    dataType:"json",
    async: false,
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
        url: 'desktop/json/listByLoginName.json',
        type:"get",
        data: {
            loginName: top.loginName
        },
        dataType:'json',
        success:function(userSysList) {
            // 添加必须在桌面上显示的应用
            for(var i=0; i<systemList.length; i++) {
                var sysItem = systemList[i];
                if(sysItem.isMustShowInDesktop === '1' && sysItem.isSetVisibleRange != '1' ) {//&& !sysItem.isUserBinded
                    var checkedImgHtmlContent = '';
                    if(sysItem.appLoginUrl) {
                        sysItem.cancelProxyLogin = true;
                    }
                    var liHtmlContent = '';
                    if(sysItem.position=='left'){
                        liHtmlContent += ''
                            + '<li class="desktop_icon" onclick="onclickDesk(this)" url="'+sysItem.appUrl+'"' +
                            'data-name="' + sysItem.appSysId + '" data-login-name="" position="left">'
                            + '<span class="icon">'
                            + '<img src="' + projectName+sysItem.iconAddr + '" />'
                            + checkedImgHtmlContent
                            + '</span>'
                            + '<div style="color: white" class="text">' + sysItem.appName + '</div>'
                            + '</li>';
                    }else if(sysItem.position=='right'){
                        liHtmlContent += ''
                            + '<li class="desktop_icon" onclick="onclickDesk(this)" url="'+sysItem.appUrl+'"' +
                            'data-name="' + sysItem.appSysId + '" data-login-name="" position="right">'
                            + '<span class="icon">'
                            + '<img src="' + projectName+sysItem.iconAddr + '" />'
                            + checkedImgHtmlContent
                            + '</span>'
                            + '<div style="color: white" class="text">' + sysItem.appName + '</div>'
                            + '</li>';
                    }


                    var li = $(liHtmlContent);
                    $("#deskIcon").append(li);
                    initByCount(++initCount);
                }
            }

           /* var addIcon=$("<li class='desktop_icon'><span class='icon'><img src='desktop/icon/addRemove.png'/></span><div class='text'>添加</div></li>");
            // 添加删除点击事件绑定
            addIcon.click((function () {
                return function () {
                    //addRemoveSystem();
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
function exitSystem(){
    $.ajax({
       url :''
    });
}
