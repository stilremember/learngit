require.config({
    baseUrl:"desktop",
    package:[
        {name:"svg",location:"../plugin/svg",main:"snap.svg.js"},
        {name:"index",location:"../plugin/request/index"},
        {name:"tool",location:"../tool/"},
    ],
    paths:{
        "jquery":'../plugin/jquery/jquery.min',
        'bootstrap': '../plugin/boostrap/bootstrap.min',
        'bootstrapTable': '../plugin/bootstrap-table/bootstrap-table',
        'bootstrapTableCN': '../plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min',
        'bootstrapValidator' : '../plugin/bootstrapvalidator/bootstrapValidator',
        'bootstrapValidatorCN' : '../plugin/bootstrapvalidator/language/zh_CN',
        'bootstrapDatetimepicker': '../plugin/datetimepicker/js/bootstrap-datetimepicker',
        'bootstrapDatetimepickerCN': '../plugin/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN',
        'bootstrapSelect':'../plugin/bootstrap-select/bootstrap-select',
        //bootstrap上传插件
        'fileInput': '../plugin/bootstrap-fileinput/fileinput.min',
        'fileInputLocaleZh': '../plugin/bootstrap-fileinput/fileinput_locale_zh',
        //提示框
        'layer': '../plugin/layer-v3.0/layer',
        //vue
        'vue':'../plugin/vue/vue.min',
        //图表
        'highcharts': '../plugin/highcharts/highcharts',
        'highchartsMore': '../plugin/highcharts/highcharts-more',//多图形支持
        'echarts': '../plugin/echarts/echarts',
        'svg': '../plugin/svg/snap.svg',
        'index':'../js/index',
    },
    //引用公共模块到插件中
    shime:{
//bootstrap
        'bootstrap': ['jquery'],
        'bootstrapTable': ['jquery'],
        'bootstrapTableCN': ['jquery', 'bootstrapTable'],
        'bootstrapValidator': ['jquery'],
        'bootstrapValidatorCN': ['jquery', 'bootstrapValidator'],
        'bootstrapDatetimepicker': ['jquery'],
        'bootstrapDatetimepickerCN': ['jquery', 'bootstrapDatetimepicker'],
        'bootstrapSelect': ['jquery'],
        //bootstrap上传插件
        'fileInput':['jquery'],
        'fileInputLocaleZh':['jquery','fileInput'],
        //提示框
        'layer': ['jquery'],
        'highcharts': ['jquery'],
        'highchartsMore': ['jquery','highcharts'],
        'echarts': ['jquery'],
        'vue':['jquery']
    }

});
require(['jquery','index'],function($,index){
   index.init();
});