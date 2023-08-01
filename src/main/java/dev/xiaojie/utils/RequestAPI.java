package dev.xiaojie.utils;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.http.HttpRequest;
import dev.xiaojie.bean.Req;
import dev.xiaojie.enums.URL;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class RequestAPI {
    private static String apikey = "";
    private static String proxyHost = "";
    private static int proxyPort = 0;
    private static boolean useProxy = true;

    static {
        Properties properties = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("openai.properties");

        try {
            properties.load(classPathResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        apikey = properties.getProperty("apikey");
        proxyHost = properties.getProperty("proxy-host");
        String port = properties.getProperty("proxy-port");
        if (!"".equals(port))
            proxyPort = Integer.parseInt(port);

        if ("".equals(proxyHost) || proxyPort == 0)
            useProxy = false;
    }

    public static String getAIModels() {
        HttpRequest request = new HttpRequest(UrlBuilder.ofHttp(URL.getModel.url));
        request.bearerAuth(apikey);
        if (useProxy) {
            request.setHttpProxy(proxyHost, proxyPort);
        }
        String body;
        try {
            body = request.execute().body();
        } catch (Exception e) {
            return "网络连接错误";
        }
        return body;
    }

    public static String chatCompletion(Req req) {

        return null;
    }

    public static void main(String[] args) {
        System.out.println(getAIModels());
    }
}
