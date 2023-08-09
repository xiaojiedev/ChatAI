package dev.xiaojie.bean.api;

import lombok.Data;

@Data
public class Permission {
    private String id;
    private String object;
    private long created;
    private boolean allow_create_engine;
    private boolean allow_sampling;
    private boolean allow_logprobs;
    private boolean allow_search_indices;
    private boolean allow_view;
    private boolean allow_fine_tuning;
    private String organization;
    private String group;
    private boolean is_blocking;
}
