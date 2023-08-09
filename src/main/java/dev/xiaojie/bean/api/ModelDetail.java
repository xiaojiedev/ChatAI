package dev.xiaojie.bean.api;

import lombok.Data;

import java.util.List;

@Data
public class ModelDetail {
    private String id;
    private String object;
    private long created;
    private String owned_by;
    private List<Permission> permission;
    private String root;
    private String parent;
}
