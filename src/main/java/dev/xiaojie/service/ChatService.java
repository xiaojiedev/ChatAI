package dev.xiaojie.service;

import dev.xiaojie.bean.ModelDetail;
import dev.xiaojie.utils.RequestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public boolean pullModel() {
        String key = "modelList";
        BoundListOperations<Object, Object> ops = redisTemplate.boundListOps(key);

        List<ModelDetail> aiModels = RequestAPI.getAIModels();

        if (aiModels != null) {
            redisTemplate.delete(key);
            for (ModelDetail model : aiModels) {
                if (model.getId().contains("gpt")) {
                    ops.rightPush(model.getId());
                }
            }
            return true;
        }
        return false;
    }

    public List<String> getModel() {
        String key = "modelList";
        BoundListOperations<Object, Object> ops = redisTemplate.boundListOps(key);
        return Objects.requireNonNull(ops.range(0, -1), "【错误】模型数据为空")
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
