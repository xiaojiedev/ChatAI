package dev.xiaojie.controller;

import dev.xiaojie.bean.api.Messages;
import dev.xiaojie.bean.api.Req;
import dev.xiaojie.bean.api.Resp;
import dev.xiaojie.bean.app.SendAndReply;
import dev.xiaojie.service.ChatService;
import dev.xiaojie.utils.IdWorker;
import dev.xiaojie.utils.RequestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@SessionAttributes("req")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/pullModel")
    public boolean pullModel() {
        return chatService.pullModel();
    }

    @GetMapping("/getModel")
    public List<String> getModel() {
        return chatService.getModel();
    }

    @PostMapping("/chat")
    public SendAndReply chat(@RequestBody SendAndReply sar, HttpSession httpSession) {
        Req sessionReq = (Req) httpSession.getAttribute("req");

        if (sessionReq == null) {
            //AI模型
            String model = sar.getModel();
            //消息列表
            List<Messages> messages = new ArrayList<>();
            messages.add(new Messages("system", sar.getSystem()));
            messages.add(sar.getMessages());
            //新会话
            sessionReq = new Req(model, messages);
            //初始化会话ID
            sar.setCid(IdWorker.newCid());
            //初始化消息ID
            sar.setMid(1);
        } else {
            //是否变更AI模型
            if (!sessionReq.getModel().equals(sar.getModel())) {
                sessionReq.setModel(sar.getModel());
            }
            //添加会话
            sessionReq.addMessages(sar.getMessages());
        }

        Resp resp = RequestAPI.chatCompletion(sessionReq);
        sar.setMessages(resp.getChoices().get(0).getMessage());
        sar.setMid(sar.getMid()+1);
        httpSession.setAttribute("req", sessionReq);

        return sar;
    }

    @PostMapping("/chatDelMsg")
    public String chatDelMsg(@RequestBody String mid, HttpSession httpSession) {
        Req req = (Req) httpSession.getAttribute("req");
        List<Messages> messages = req.getMessages();
        messages.remove(Integer.parseInt(mid));
        req.setMessages(messages);
        httpSession.setAttribute("req", req);
        return "done";
    }
}
