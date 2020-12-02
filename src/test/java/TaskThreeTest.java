import me.rubl.lesson_six.LessonSix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TaskThreeTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true, new int[]{1, 4}},
                {true, new int[]{1, 4, 1, 4, 4, 1, 4, 1, 1, 4}},
                {false, new int[]{1, 1, 1}},
                {false, new int[]{4, 4, 4}},
                {false, new int[]{}},
        });
    }

    private LessonSix lessonSix;

    private final boolean result;
    private final int[] input;

    public TaskThreeTest(boolean result, int[] input) {
        this.result = result;
        this.input = input;
    }

    @Before
    public void init() {
        lessonSix = new LessonSix();
    }

    @Test
    public void arrayTest() {
        Assert.assertEquals(result, lessonSix.taskThree(input));
    }
}
