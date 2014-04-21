package com.kcf.test;

import com.kcf.parse.SummaryParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;

/**
 * Author: 老牛 -- TK
 * Date:   14-4-21
 */
@RunWith(JUnit4.class)
public class TestSummaryParser {
    private final static String text = "医疗技术方面，我曾总结过两句话：大部分疾病是无法医治的，大部分疾病会不治而愈。比如说感冒，现在只能对症治疗，减轻发烧，鼻塞，头痛症状。现有的药物无法杀死这些病毒，因此说无法医治，但好在感冒病毒在体内经过一定的病程（2周左右）会自动消亡，不治而愈。大部分患者没法理解这些细节，因此总会觉得这个医生好，那个医生不好；或这个药好，那个药不好，其实这些主观感觉存在大量误解。因此医生们会在微博上说越是“庸医”越能得到患者的“信任”（因为这些人迎合患者的想法），而有些好医生常常“激怒”患者。我复诊时有位远到而来的患者跪在医生面前，希望其救救他妻子。患者会认为面前的这位医生一定能救得了病人，如果结果不好，可能会迁怒于医生。医生当然会尽力而为，但很多疾病的治疗如前所述，能延长寿命几年已经是极大的成功了";

    @Test
    public void test() {
        String summary = SummaryParser.parse(text);

        System.out.println(summary);


        assert  summary != null;
    }

}
