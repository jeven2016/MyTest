/**
 * requireJs 有一个需要注意的地方，require.js不保证比其他js文件提前加载完，如果在页面使用了data-main属性，且在页面调用require方法的话，
 * 会出现html已经加载但是data-main中的Js还为加载好的情况，此时执行页面的require方法会认为没有config对象从而使用默认的data-main路径去加载其他js文件
 * ，从而导致js路径错误而无法加载的问题。因此，在页面也需要调用require方法的情况下就不能使用data-main的方式去加载配置。
 */
var suffix = ".min";
requirejs.config({
    //base on directory of html page
    baseUrl: "../../libs/",

    paths: {
        crossroads: "crossroads" + suffix,
        hasher: "hasher" + suffix,
        is: "is" + suffix,
        //jquery: "jquery" + suffix,
        knockout: "knockout" + suffix,
        signals: "signals" + suffix,
        text: "text",
    },

    //Remember: only use shim config for non-AMD scripts,
    //scripts that do not already call define(). The shim
    //config will not work correctly if used on AMD scripts,
    //in particular, the exports and init config will not
    //be triggered, and the deps config will be confusing
    //for those cases.
    shim: {
        crossroads: {
            //These script dependencies should be loaded before loading
            //crossroads.js
            deps: ["signals"],

            //Once loaded, use the global 'Backbone' as the
            //module value.
            exports: "crossroads"
        }

    }
});

