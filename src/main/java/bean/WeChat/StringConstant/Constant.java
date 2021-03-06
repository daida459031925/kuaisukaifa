package bean.WeChat.StringConstant;



import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Constant{
    /**
     * 微信的appid 和密钥默认情况下是不会去改变的
     */
    /**
     * 这个是微信 HatsuneMiku 的小程序
     */
    private static final String AppID = "wx04e35afa5a4c5c17";
    private static final String AppSecret = "1a66d5df7b2d81da897e04e87deff19b";
    private static final String grant_type = "authorization_code";
    private static final String http = "https://api.weixin.qq.com/sns/jscode2session";


    public static String getHttp_authorization_code(String js_code) {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=").append(AppID)
                .append("&").append("secret=").append(AppSecret).append("&").append("js_code=").append(js_code)
                .append("&").append("grant_type=").append(grant_type);
        return sb.toString();
    }

    /**
     *
     * @param postData    需要上传的数据格式为   xxx=xxx&xxx1=xxx1
     * @param postUrl     需要访问的链接地址  https://xxxxxxxx
     * @return
     */
    private static String SMS(String postData, String postUrl) throws IOException {
            // 发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");//修改发送方式
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//发送方式
            conn.setRequestProperty("Connection", "Keep-Alive");//请求模式
            conn.setUseCaches(false);//关闭缓存
            conn.setDoOutput(true);//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            // 获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "";
            }
            // 获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
    }

    public static Map getOpenid_Session_key(String js_code) throws InterruptedException, IOException {
        Thread.sleep(500);
        String json=SMS(getHttp_authorization_code(js_code),http);
        Map mapTypes = JSON.parseObject(json);
        for (Object obj : mapTypes.keySet()){
            System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
        }
        return mapTypes;
    }

    public static String getUUID(){
        StringBuffer sb= new StringBuffer().append(new Date().getTime());
        String uuid = UUID.randomUUID().toString();
        sb.append(uuid);
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            getOpenid_Session_key("071xDyw41PdTtL1vVPy41Wadw41xDywE");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
