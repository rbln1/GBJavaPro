package me.rubl.lesson_three;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LessonThree {

    private static final int PAGE_SIZE = 1800;

    public static void main(String[] args) {

        taskOne("file50.txt", false);

        taskTwo();

        long t = System.currentTimeMillis();
        taskThree("file_over10mb.txt");
        System.out.println("Execute time: " + (System.currentTimeMillis() - t));
    }

    private static void taskOne(String fileWithExt, boolean parseToChar) {

        try(InputStreamReader isr = new InputStreamReader(new FileInputStream(fileWithExt), "UTF-8")) {

            int x;

            while ((x = isr.read()) != -1) {
                if (parseToChar) {
                    System.out.print((char) x);
                } else {
                    System.out.print(x);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try(FileInputStream inputStream = new FileInputStream(fileWithExt)) {
//            byte[] result = new byte[64];
//            int x;
//            while((x = inputStream.read(result)) > 0) {
//                if (parseToChar) {
//                    System.out.print(new String(result, 0, x, "UTF-8"));
//
//                } else {
//                    for (byte b : result) {
//                        System.out.print(b);
//                    }
//                }
//
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private static void taskTwo() {

        try {
            ArrayList<InputStream> streamList = new ArrayList<>();
            streamList.add(new FileInputStream("file100_1.txt"));
            streamList.add(new FileInputStream("file100_2.txt"));
            streamList.add(new FileInputStream("file100_3.txt"));
            streamList.add(new FileInputStream("file100_4.txt"));
            streamList.add(new FileInputStream("file100_5.txt"));

            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(streamList));

            FileOutputStream out = new FileOutputStream("file100_final.txt", false);

            int x;
            while((x = in.read()) != -1) {
                out.write(x);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void taskThree(String filenameWithExt) {

        long filesize = getFileSizeInBytes(new File(filenameWithExt));

        if (filesize == 0) {
            System.out.println("Файл пуст или не существует.");
            return;
        }

        long pageCount = 0;

        if (filesize <= PAGE_SIZE) pageCount = 1;
        else if (filesize % PAGE_SIZE == 0) pageCount = filesize / PAGE_SIZE;
        else pageCount = (filesize / PAGE_SIZE) + 1;

        long currentPage = 0;

        if (pageCount > 1) {
            Scanner scanner = new Scanner(System.in);

            while (currentPage <= 0 || currentPage > pageCount) {
                System.out.printf("Введите номер страницы (1-%d): ", pageCount);

                try {
                    currentPage = Integer.parseInt(scanner.next());
                } catch (NumberFormatException ignored) {}
            }
        }

        long t = System.currentTimeMillis();

        try (RandomAccessFile raf = new RandomAccessFile(filenameWithExt, "r")){

            byte[] pageBytes = new byte[PAGE_SIZE];

            raf.seek(currentPage == 0 ? 0 : (currentPage - 1) * PAGE_SIZE);
            raf.read(pageBytes);

            System.out.println(new String(pageBytes));

            System.out.println("\nRead time: " + (System.currentTimeMillis() - t));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long getFileSizeInBytes(File file) {
        return file.length();
    }
}
