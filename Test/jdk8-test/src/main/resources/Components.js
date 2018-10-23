/**系统默认的接口定义及对应的实现定义*/
var Definitions = {
    "compiler": {
        "interfaceClass": "com.alibaba.dubbo.common.compiler.Compiler",
        "default": "jdk",
        "list": {
            "adaptive": "com.alibaba.dubbo.common.compiler.support.AdaptiveCompiler",
            "jdk": "com.alibaba.dubbo.common.compiler.support.JdkCompiler",
            "javassist": "com.alibaba.dubbo.common.compiler.support.JavassistCompiler"
        }
    },
    "protocol": {}
};