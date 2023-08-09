package dev.xiaojie.utils;

import dev.xiaojie.bean.api.Messages;
import dev.xiaojie.bean.api.Resp;

public class BeanUtil {
    public Messages respGetMsg(Resp resp) {
        return resp.getChoices().get(0).getMessage();
    }
    public String respGetMsgStr(Resp resp) {
        String role = resp.getChoices().get(0).getMessage().getRole();
        String content = resp.getChoices().get(0).getMessage().getContent();
        return role + ": " + content;
    }

}
