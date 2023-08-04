package dev.xiaojie.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import dev.xiaojie.bean.ModelDetail;
import dev.xiaojie.bean.Req;
import dev.xiaojie.bean.Resp;
import dev.xiaojie.enums.URL;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;
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

    public static List<ModelDetail> getAIModels() {
        HttpRequest request = HttpRequest.get(URL.getModel.url);
        request.bearerAuth(apikey);
        if (useProxy) {
            request.setHttpProxy(proxyHost, proxyPort);
        }
        String body = request.execute().body();
        String substring = body.substring(body.indexOf("["), body.lastIndexOf("]") + 1);
        return JSONUtil.toList(substring, ModelDetail.class);
    }

    public static Resp chatCompletion(Req req) {
        HttpRequest request = HttpRequest.post(URL.chatCompletion.url);
        request.bearerAuth(apikey);
        request.body(JSONUtil.toJsonStr(req), "application/json");
        if (useProxy) {
            request.setHttpProxy(proxyHost, proxyPort);
        }
        String body = request.execute().body();
        return JSONUtil.toBean(body, Resp.class);
    }
}
