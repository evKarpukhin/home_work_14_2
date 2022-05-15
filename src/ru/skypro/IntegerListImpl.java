package ru.skypro;

import ru.skypro.exception.BadIndexException;
import ru.skypro.exception.BadValueException;
import ru.skypro.exception.NotFountValueException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    final static float increaseArray = 1.5f;
    int size = 10_000;
    int[] intArray;


    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }


    public void sortReqursion(int[] arr) {
        long start = System.currentTimeMillis();
        quickSort(arr, 1, arr.length-1);
        System.out.println(System.currentTimeMillis() - start);
    }

    //  Сортировка вставкой
    private void intSortInsert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    //  Сортировка выбором
    private void intSortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    // Сортировка пузырьковая
    public static void intSortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public void sortInsert(int[] arr) {
        long start = System.currentTimeMillis();
        intSortInsert(arr);
        System.out.println(System.currentTimeMillis() - start);
    }

    public void sortSelection(int[] arr) {
        long start = System.currentTimeMillis();
        intSortSelection(arr);
        System.out.println(System.currentTimeMillis() - start);
    }

    public void sortBubble(int[] arr) {
        long start = System.currentTimeMillis();
        intSortBubble(arr);
        System.out.println(System.currentTimeMillis() - start);
    }


    public int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000);
        }
        return intArray = arr;
    }

    // Binary Search
    private boolean containsBinarySearch(int[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        if (size == intArray.length) {
            int[] newIntArray = new int[(int) (intArray.length * increaseArray)];
            for (int i = 0; i < intArray.length; i++) {
                newIntArray[i] = intArray[i];
            }
            intArray = newIntArray;
        }
    }


    @Override
    public int add(int item) {
        if (size >= intArray.length) {
            //throw new BadValueException("Bad value array." + item);
            grow();
        }
        return intArray[size++] = item;
    }

    @Override
    public int add(int index, int item) {
        if (index < 0 || index > size) {
            throw new BadIndexException("Bad index array " + index);
        }

        grow();
        for (int i = size - 1; i > index; i--) {
            intArray[i] = intArray[i - 1];
        }
        intArray[index] = item;
        size++;
        return item;
    }

    @Override
    public int set(int index, int item) {
        if (index > size || index > intArray.length) {
            throw new BadIndexException("Bad index array " + index);
        }
        return intArray[index] = item;

    }

    @Override
    public int removeByValue(int item) {
        int ind = 0;
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (intArray[i] == item) {
                ind = i;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NotFountValueException("Value not found.");
        }
        if (ind == size - 1) {
            intArray[ind] = 0;
        } else {
            for (int i1 = ind; i1 < size - 1; i1++) {
                intArray[i1] = intArray[i1 + 1];
            }
        }
        size--;
        return item;

    }

    @Override
    public int removeByIndex(int index) {
        if (index > size || index > intArray.length) {
            throw new BadIndexException("Bad index array " + index);
        }
        int str = intArray[index];
        if (index == size - 1) {
            intArray[index] = 0;
        } else {
            for (int i1 = index; i1 < size - 1; i1++) {
                intArray[i1] = intArray[i1 + 1];
            }
        }
        size--;
        return str;
    }

    @Override
    public boolean contains(int item) {
        return containsBinarySearch(intArray, item);
    }

    @Override
    public int indexOf(int item) {
        for (int i = 0; i < size; i++) {
            if (intArray[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int item) {
        for (int i = size; i == 0; i--) {
            if (intArray[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int get(int index) {
        if (index > size) {
            throw new BadIndexException("Bad index array.");
        }
        return intArray[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == this) {
            return true;
        }

        if (!(otherList instanceof IntegerList)) {
            return false;
        }

        int[] intOtherList = ((IntegerListImpl) otherList).intArray;
        intSortInsert(intArray);
        intSortInsert(intOtherList);
        return Arrays.equals(intArray, intOtherList);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(intArray, 0);
        size = 0;
    }

    @Override
    public int[] toArray() {
        return Arrays.copyOf(intArray, size);
    }
}
