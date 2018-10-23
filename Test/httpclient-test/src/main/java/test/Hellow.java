package test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-12-24.
 */
public class Hellow {
    static String homeUrl = "http://mmse8.net/forum.php";
    private static String loginUrl = "http://mmse8.net/member.php?mod=logging&action=login&loginsubmit=yes&infloat=yes&lssubmit=yes";
    static String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36";
    static String username = "nanjingren2008";
    static String password = "562405";

    public static void main(String[] args) {
        fetch();
    }

    static void fetch() {
        try {
            login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void login(String userName, String pwd) throws Exception {

        //第一次请求
        Connection con = Jsoup.connect(homeUrl);//获取连接
        con.header("User-Agent", userAgent);//配置模拟浏览器
        Connection.Response rs = con.execute();//获取响应

        Document d1 = Jsoup.parse(rs.body());//转换为Dom树
        List<Element> et = d1.select("#lsform");//获取form表单，可以通过查看页面源码代码得知

        //获取，cooking和表单属性，下面map存放post时的数据
        Map<String, String> datas = new HashMap<>();
        for (Element e : et.get(0).getAllElements()) {
            if (e.attr("name").equals("username")) {
                e.attr("value", userName);//设置用户名
            }

            if (e.attr("name").equals("password")) {
                e.attr("value", pwd); //设置用户密码
            }

            if (e.attr("name").length() > 0) {//排除空值表单属性
                datas.put(e.attr("name"), e.attr("value"));
            }
        }


        /**
         * 第二次请求，post表单数据，以及cookie信息
         *
         * **/
        Connection con2 = Jsoup.connect(loginUrl);
        con2.header("User-Agent", userAgent);
        //设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).method(Connection.Method.POST).data(datas).cookies(rs.cookies()).execute();
        //打印，登陆成功后的信息
        //System.out.println(login.body());

        //登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
        Map<String, String> map = login.cookies();
        for (String s : map.keySet()) {
            System.out.println(s + "      " + map.get(s));
        }


        Connection con3 =Jsoup.connect("http://mmse8.net/thread-8247551-1-1.html").cookies(map);
        System.out.println(con3.get().outerHtml());


    }


}
