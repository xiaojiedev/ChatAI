package dev.xiaojie.bean.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choices {
    private int index;
    private Messages message;
    private String finish_reason;
}
