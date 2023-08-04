import dev.xiaojie.bean.ModelDetail;
import dev.xiaojie.utils.RequestAPI;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class methodTest {
    @Test
    public void method1() {
        List<ModelDetail> aiModels = RequestAPI.getAIModels();
        List<String> list = new ArrayList<>();
        for (ModelDetail model : aiModels) {
            if (model.getId().contains("gpt")) {
                list.add(model.getId());
            }
        }
        System.out.println(list);
    }
}
