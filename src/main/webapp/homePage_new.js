$(function () {
    var modules = getModules();
    $(".header_menu .menu").on("click",function (e) {
        $(".header_menu .select").removeClass("select");
        $(this).addClass("select");
        var type = $(this).attr("type");
        createModules(modules[type]);
    })
    $(".header_menu .menu:first").click();
})

function getModules(type) {
    /*var modules= {
        "zhps":[
            {name:"实时监控",iconAddr:"jiankong1.png",color:"9,131,208",iconType:"big",colStart:1,rowStart:1},
            {name:"应急指挥调度",iconAddr:"zhihui1.png",color:"95,230,224",iconType:"small",colStart:3,rowStart:1},
            {name:"网管养护系统",iconAddr:"yanghu1.png",color:"143,130,188",iconType:"small",colStart:4,rowStart:1},
            {name:"业务审批系统",iconAddr:"shenpi1.png",color:"9,131,208",iconType:"small",colStart:3,rowStart:2},
            {name:"排水巡查系统",iconAddr:"paishuix1.png",color:"250,205,137",iconType:"small",colStart:4,rowStart:2},
            {name:"排水设施管理",iconAddr:"paishui1.png",color:"149,212,255",iconType:"high",colStart:5,rowStart:1},
            {name:"排水管线信息系统",iconAddr:"guanxian1.png",color:"149,212,255",iconType:"long",colStart:1,rowStart:3},
            {name:"水质水量模型",iconAddr:"shuizhi1.png",color:"143,130,188",iconType:"long",colStart:3,rowStart:3},
            {name:"排水模型系统",iconAddr:"mox1.png",color:"9,131,208",iconType:"small",colStart:5,rowStart:3}
        ],
        "zhhl":[
            {name:"河长制系统",iconAddr:"jiankong1.png",color:"9,131,208",iconType:"big",colStart:1,rowStart:1}
        ],
        "hmcs":[
            {name:"海绵城市指标评估展示系统",iconAddr:"jiankong1.png",color:"9,131,208",iconType:"big",colStart:1,rowStart:1},
            {name:"海绵城市指标评分录入系统",iconAddr:"yanghu1.png",color:"143,130,188",iconType:"big",colStart:3,rowStart:1},
            {name:"海绵城市指标管理系统",iconAddr:"paishui1.png",color:"149,212,255",iconType:"high",colStart:5,rowStart:1}
        ]
    }*/
    var modules = {};
    $.ajax({
        url: "home/data/MeauApp.json",
        type:"get",
        dataType:"json",
        async: false,
        success: function (data) {
            for (var i in data.rows){
                if(!modules[data.rows[i].appType]){
                    modules[data.rows[i].appType] = [];
                    modules[data.rows[i].appType].push(data.rows[i]);
                }else{
                    modules[data.rows[i].appType].push(data.rows[i]);
                }
            }
            console.log(modules);
        },
        error: function () {
        }
    });
    if(type){
        return modules[type];
    }
    return modules;
}

function createModules(modules) {
    $(".module_panel").html('');
    if(!modules){
        return;
    }
    for (var i = 0 ; i < modules.length ; i ++){
        var imgStyle = '';
        var style= '';
        //style = style + 'grid-column-start:' + modules[i].colStart + ';grid-row-start:' + modules[i].rowStart + ';';
        // style = style + '-ms-grid-column:' + modules[i].colStart + ';-ms-grid-row:' + modules[i].rowStart + ';';

        if(modules[i].iconType == 'big'){
            // style = style + 'grid-column-end:' + (modules[i].colStart+2) + ';grid-row-end:' + (modules[i].rowStart+2) + ';';
            // style = style + '-ms-grid-column-span:' + 2 + ';-ms-grid-row-span:' + 2 + ';';
            style = style + 'top:' + (220*(modules[i].rowStart - 1)) + "px;left:" + (220*(modules[i].colStart - 1)) + "px;width:420px;height:420px;";
            style = style + 'font-size:30px;padding-top:80px;background-color:rgba(' + modules[i].color + ',.8);';
            imgStyle = 'width:180px;height:206px;';
        }else if(modules[i].iconType == 'small'){
            // style = style + 'grid-column-end:' + (modules[i].colStart+1) + ';grid-row-end:' + (modules[i].rowStart+1) + ';';
            // style = style + '-ms-grid-column-span:' + 1 + ';-ms-grid-row-span:' + 1 + ';';
            style = style + 'top:' + (220*(modules[i].rowStart - 1)) + "px;left:" + (220*(modules[i].colStart - 1)) + "px;width:200px;height:200px;";
            style = style + 'font-size:20px;padding-top:30px;background-color:rgba(' + modules[i].color + ',.8);';
            imgStyle = 'width:78px;height:90px;';
        }else if(modules[i].iconType == 'high'){
            // style = style + 'grid-column-end:' + (modules[i].colStart+1) + ';grid-row-end:' + (modules[i].rowStart+2) + ';';
            // style = style + '-ms-grid-column-span:' + 1 + ';-ms-grid-row-span:' + 2 + ';';
            style = style + 'top:' + (220*(modules[i].rowStart - 1)) + "px;left:" + (220*(modules[i].colStart - 1)) + "px;width:200px;height:420px;";
            style = style + 'font-size:22px;padding-top:80px;background-color:rgba(' + modules[i].color + ',.8);';
            imgStyle = 'width:106px;height:120px;';
        }else if(modules[i].iconType == 'long'){
            // style = style + 'grid-column-end:' + (modules[i].colStart+2) + ';grid-row-end:' + (modules[i].rowStart+1) + ';';
            // style = style + '-ms-grid-column-span:' + 2 + ';-ms-grid-row-span:' + 1 + ';';
            style = style + 'top:' + (220*(modules[i].rowStart - 1)) + "px;left:" + (220*(modules[i].colStart - 1)) + "px;width:420px;height:200px;";
            style = style + 'font-size:22px;padding-top:15px;background-color:rgba(' + modules[i].color + ',.8);';
            imgStyle = 'width:106px;height:120px;';
        }

        var html = '';
        if(modules[i].appUrl){
            html = html + '<div class="module box" style=' + style + ' href=' + modules[i].appUrl + '>';
        }else{
            html = html + '<div class="module box" style=' + style + '>';
        }
        html = html + '<img src="' + modules[i].iconAddr + '" style=' + imgStyle + '>';
        html = html + '<div class="module_name" style="margin-top: 20px">' + modules[i].appName + '</div>';
        html = html + '</div>';
        $(".module_panel").append(html);
    }
    $(".module_panel .module").on("click",function () {
        var href = $(this).attr("href");
        if(href){
            window.open(href);
        }
    });
}

