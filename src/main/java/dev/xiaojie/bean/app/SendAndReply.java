package dev.xiaojie.bean.app;

import dev.xiaojie.bean.api.Messages;
import lombok.Data;

@Data
public class SendAndReply {
    private long cid;
    private int mid;
    private String model;
    private String system;
    private Messages messages;
}
