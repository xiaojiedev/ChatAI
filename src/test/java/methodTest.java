import cn.hutool.json.JSONUtil;
import dev.xiaojie.bean.Choices;
import dev.xiaojie.bean.Messages;
import dev.xiaojie.bean.Resp;
import dev.xiaojie.bean.Usage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class methodTest {

    @Test
    public void method1() {
        List<Choices> choices = new ArrayList<>();
        choices.add(new Choices(0, new Messages("bot", "baba"), "stop"));

        Resp resp = new Resp(
                "chat123",
                "chat.completion",
                100000000,
                choices,
                new Usage(1,2,3)
        );

        System.out.println(JSONUtil.toJsonPrettyStr(resp));
    }
}
