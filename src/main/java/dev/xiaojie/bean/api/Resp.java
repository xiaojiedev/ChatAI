package dev.xiaojie.bean.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resp {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choices> choices;
    private Usage usage;
}
