package ru.skypro;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] array1, array2, array3;
        IntegerListImpl integerList = new IntegerListImpl();
        array1 = integerList.generateRandomArray();
        array2 = Arrays.copyOf(array1, array1.length);
        array3 = Arrays.copyOf(array2, array2.length);

        System.out.println("Сортировка вставкой");
        integerList.sortInsert(array1); // 952
        integerList.sortInsert(array2); // 643
        integerList.sortInsert(array3); // 661

        System.out.println("Сортировка рекурсией: Быстрая сортировка");
        integerList.sortReqursion(array1);

        System.out.println("integerList.contains(790) = " + integerList.contains(790));
/*
        System.out.println("Сортировка выбором");
        integerList.sortSelection(array1); // 4547
        integerList.sortSelection(array2); // 4264
        integerList.sortSelection(array3); // 2089
*/
/*
        System.out.println("Сортировка пузырьковая");
        integerList.sortBubble(array1); // 13191
        integerList.sortBubble(array2); // 12793
        integerList.sortBubble(array3); // 10619
*/

    }
}
