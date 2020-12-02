import me.rubl.lesson_six.LessonSix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskTwoTest {

    private LessonSix lessonSix;

    @Before
    public void init() {
        lessonSix = new LessonSix();
    }

    @Test
    public void testOne() {
        Assert.assertArrayEquals(new int[]{1, 7}, lessonSix.taskTwo(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test(expected = RuntimeException.class)
    public void testTwo() {
        Assert.assertArrayEquals(new int[]{}, lessonSix.taskTwo(new int[]{}));
    }

    @Test(expected = RuntimeException.class)
    public void testThree() {
        Assert.assertArrayEquals(new int[]{}, lessonSix.taskTwo(new int[]{2, 3, 7, 6, 9, 1}));
    }

    @Test
    public void testFour() {
        Assert.assertArrayEquals(new int[]{}, lessonSix.taskTwo(new int[]{1, 2, 4, 4, 4}));
    }
}
