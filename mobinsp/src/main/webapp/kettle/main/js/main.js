define(['jquery','vue','bootstrap','layer'],function($,vue,bootstrap,layer){
    var main={};
    function init(){
        main.meau=new vue({
            el:'',
            data:[]
        });
    }


    return {
        init:init
    }
});