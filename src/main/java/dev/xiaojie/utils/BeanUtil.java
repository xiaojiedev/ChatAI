package dev.xiaojie.utils;

import dev.xiaojie.bean.Choices;
import dev.xiaojie.bean.Messages;
import dev.xiaojie.bean.Resp;

import java.util.List;

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
