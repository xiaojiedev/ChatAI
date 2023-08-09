package dev.xiaojie.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import dev.xiaojie.bean.api.ModelDetail;
import dev.xiaojie.bean.api.Req;
import dev.xiaojie.bean.api.Resp;
import dev.xiaojie.enums.URL;
import dev.xiaojie.exception.NotNetworkPortException;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class RequestAPI {
    private static final String apikey;
    private static final String proxyHost;
    private static int proxyPort;
    private static boolean useProxy = true;

    static {
        Properties properties = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("openai.properties");

        try {
            properties.load(classPathResource.getInputStream());
        } catch (IOException e) {
            System.out.println("【错误】配置文件读取错误");
        }

        apikey = properties.getProperty("apikey");
        if (apikey == null) {
            System.out.println("【错误】没有配置api key");
        }

        proxyHost = properties.getProperty("proxy-host");
        if (proxyHost == null) {
            System.out.println("【信息】没有配置HTTP代理");
            useProxy = false;
        }

        try {
            proxyPort = Integer.parseInt(properties.getProperty("proxy-port"));
            if (proxyPort < 1 || proxyPort > 65535) {
                throw new NotNetworkPortException();
            }
        } catch (NumberFormatException | NotNetworkPortException e) {
            System.out.println("【信息】HTTP代理端口格式错误，停用代理");
            useProxy = false;
        }
    }

    public static List<ModelDetail> getAIModels() {
        HttpRequest request = HttpRequest.get(URL.getModel.url);
        request.bearerAuth(apikey);
        if (useProxy) {
            request.setHttpProxy(proxyHost, proxyPort);
        }
        String body = request.execute().body();
        if (body != null) {
            String substring = body.substring(body.indexOf("["), body.lastIndexOf("]") + 1);
            return JSONUtil.toList(substring, ModelDetail.class);
        }
        return null;
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
