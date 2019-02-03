define(['jquery','bootstrap','layer'],function($,bootstrap,layer){
    function init(){
        meau();
    }
    function stuffHtml(data){
        if(data && typeof data == 'object'){
            for(var i in data){
                var item  = data[i];
                var html=" <li class=\"menu-item\">\n" +
                    " <a href=\"#\" class=\"menu-link\" tabindex=\"-1\">item.name</a>\n" +
                    " </li>";
                if(typeof item == 'object'){
                    if(i == 'classIcon'){
                        var icon = "<span class=\""+item.classIcon+"\"></span>\n" +
                            "<span class=\"menu-text\">Dashboard</span>";
                        $(html).find("a").html(icon);
                    }
                    $(html).prepend("<ul class=\"menu\">").append(stuffHtml(item))
                        .append("</ul>");
                }else{

                }
            }
        }
    }
    function meau(){
        $.ajax({
            url:'/awater/kettle/request/index/menu.json',
            type:'get',
            dataType:'text',
            success:function(data){
                var g = eval('('+data+')');
                if(g && typeof g == 'object'){
                    for(var i in g){
                        var item = g[i];
                        var html="<li class=\"menu-item has-child\">\n" +
                            "                    <a href=\"#\" class=\"menu-link\">\n" +
                            "                        <span class=\"menu-icon oi oi-list-rich\"></span>\n" +
                            "                        <span class=\"menu-text\">item.name</span>\n" +
                            "                    </a></li>"
                        debugger;
                    }

                }
            },error:function(e){
                console.log(e);
            }
        });

       /* $.get('tool/meau.html',function(g){
            $("#desktop-meau").append(g);
            $("#desktop-meau .menu-item.has-child").on('click',function(){
               $(this).toggleClass('has-open');
            });
        });*/
    }
    return {
        init:init
    }
});