package me.rubl.lesson_six;

public class LessonSix {

    public int[] taskTwo(int[] array) {

        if (array == null || array.length == 0)
            throw new RuntimeException("Does not contain digit 4");

        int lastIndexOfFour = 0;
        boolean containFour = false;

        for (int i = array.length-1; i >=0; i--) {
            if (array[i] == 4) {
                containFour = true;
                lastIndexOfFour = i;
                break;
            }
        }

        if (!containFour)
            throw new RuntimeException("Does not contain digit 4");

        if (array.length == lastIndexOfFour + 1)
            return new int[]{};

        int[] result = new int[array.length - lastIndexOfFour - 1];
        System.arraycopy(array, lastIndexOfFour + 1, result, 0, result.length);

        return result;
    }

    public boolean taskThree(int[] array) {

        if (array == null || array.length == 0) return false;

        boolean containOne = false;
        boolean containFour = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                containOne = true;
                if (containFour) break;
            } else if (array[i] == 4) {
                containFour = true;
                if (containOne) break;
            }
        }

        return containOne && containFour;
    }

}
