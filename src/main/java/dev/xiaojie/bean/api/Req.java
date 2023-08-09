package dev.xiaojie.bean.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req {
    private String model;
    private List<Messages> messages;

    public void addMessages(Messages messages) {
        this.messages.add(messages);
    }

    public void addMessages(String role, String content) {
        this.messages.add(new Messages(role, content));
    }
}
