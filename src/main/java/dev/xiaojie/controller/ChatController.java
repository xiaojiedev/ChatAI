package dev.xiaojie.controller;

import dev.xiaojie.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
