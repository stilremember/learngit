define(['jquery','bootstrap','layer',
    'tool/js/meau.js',
    'tool/js/head.js'],function($,bootstrap,layer,meau,head){
    function init(){
        debugger;
        head.init();
        meau.init();

    }
    return {
        init:init
    }
});