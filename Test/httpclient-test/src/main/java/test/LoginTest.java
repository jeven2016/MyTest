package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 16-12-25.
 */
public class LoginTest {
    public static void main(String[] args) {
        login();
    }

    static void login() {
        String cookies = "__insp_wid=202549551; __insp_nv=true; __insp_targlpu=http%3A%2F%2Fmmse8.net%2F; __insp_targlpt=%E6%80%A7%E5%90%A7%7C%E6%98%A5%E6%9A%96%E8%8A%B1%E5%BC%80%EF%BC%8C%E6%80%A7%E5%90%A7%E6%9C%89%E4%BD%A0%7CSex8%7C%E6%80%A7%E5%90%A7%E6%9C%80%E6%96%B0%E5%9C%B0%E5%9D%80; __insp_norec_sess=true; nlbi_625395=rWESPI7mCwzXYmE8JJkW2AAAAACdYoYqfrmGM8ylsW1M/dwC; cus_cookie=36; incap_ses_415_625395=wxMiNpV7Q3r9V7B/62DCBRdrX1gAAAAAn4ApOH2F23qtIWlE8WsTBA==; visid_incap_625395=/rR02CiHS5SZWn+Uqz0RXGRbX1gAAAAAQUIPAAAAAABdsjn4upzdNav8qzZE2KEv; incap_ses_124_625395=B4aVcBGGPGkYjNm+x4q4AaRpX1gAAAAABZU6El8/K6Ces6p3HyjU/Q==; A8tI_2132_saltkey=BOhx0pKL; A8tI_2132_lastvisit=1482644795; A8tI_2132_auth=0b17lZSRbIEHNzbZSNp2r75dJ7dUknoiqT%2FCgiLfM8JZj5RCOKX7Q5tTQ73MDYZNmBvswBTsrhxBaZ3wci1bMcxHQUPn; A8tI_2132_staticlogin=1; A8tI_2132_lip=8.8.8.8%2C1482648407; A8tI_2132_1qaz21=svb%2BJJ8BXUsKkOqF4g5Y5N8SyH7OFbuv; A8tI_2132_1qaz341=6O%2FRKa7zbUakeCojYh1BiA%3D%3D; A8tI_2132_visitedfid=11; _gat=1; _gat_b=1; A8tI_2132_ulastactivity=1482648765%7C0; A8tI_2132_sendmail=1; __insp_slim=1482648966608; A8tI_2132_st_p=2447617%7C1482648977%7Ced26c92baf7bcb2fc7ddeced9e8c8bb5; A8tI_2132_viewid=tid_8246070; A8tI_2132_checkpm=1; A8tI_2132_lastcheckfeed=2447617%7C1482648979; A8tI_2132_checkfollow=1; A8tI_2132_noticeTitle=1; A8tI_2132_self_uid=2447617; A8tI_2132_self_fid=11; A8tI_2132_self_tid=8246070; A8tI_2132_self_unique_code=2dd08a06-5521-0340-c86c-2cb333614a8f; A8tI_2132_adv_gid=21; _ga=GA1.2.1209851403.1482644359; A8tI_2132_smile=1D1; A8tI_2132_lastact=1482648983%09UserSessionDetector.php%09";
        Map map = new HashMap();
        Arrays.stream(cookies.split(";")).forEach(val -> {
            String[] attrs = val.split("=");
            map.put(attrs[0], attrs[1]);
        });

        try {
            Document doc = Jsoup.connect("http://mmse8.net/thread-8247551-1-1.html").cookies(map).get();
            System.out.println(doc.outerHtml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
