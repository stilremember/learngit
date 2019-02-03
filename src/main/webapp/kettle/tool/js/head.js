define(['jquery'],function($){
    function init(){
        $.get('tool/head.html',function(g){
            $("#desktop-head").append(g);
        });
    }
   return {
    init:init
   }
});