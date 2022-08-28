package com.wangxt.algorismus.code.jc;

import java.util.Arrays;

public class SimpleSort {

    public static void main(String[] args) {
        int maxCount = 8;
        int maxValue = 100;
        int[] arr = randomArr(maxCount, maxValue);

        print(arr);
        sort(arr);
        print(arr);
    }

    private static int[] randomArr(int maxCount, int maxValue) {
        int len = (int) (Math.random() * maxCount);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int v = (int) (Math.random() * maxValue);
            arr[i] = v;
        }

        return arr;
    }

    /**
     * 选择排序：
     * 先从 0 ~ length-1 找到最小的数和 0 位置的数交换，这时 0~0 有序
     * 再从 1 ~ length-1 找到最小的数和 1 位置的数交换，这时 0~1 有序
     * 再从 2 ~ length-1 找到最小的数和 2 位置的数交换，这时 0~2 有序
     * 再从 n ~ length-1 找到最小的数和 n 位置的数交换，这时 0~n 有序
     */
    public static void selectSort(int[] arr) {
        // 先定边界
        // 0 - n-1
        // 1 - n-1
        // n-1 - n-1
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            // 假设i位置是最小值，从 i~n 中遍历，如果有比i小的，则最小索引换成j,遍历完minIndex则为 i~n 中最小值索引
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 交换 i 和 minIndex
            swap(minIndex, i, arr);
        }
    }

    /**
     * 冒泡排序：相邻两个数两两比较，大就交换
     * 0~n   范围，0 位置的数开始，如果 0 位置的数比 1 位置的数大则交换，然后1 位置的数比2 位置的数大则交换，一直到n，结束后n位置的数肯定最大
     * 0~n-1 范围，0 位置的数开始，如果 0 位置的数比 1 位置的数大则交换，然后1 位置的数比2 位置的数大则交换，一直到n-1，结束后n-1 位置的数肯定最大
     */
    public static void bubbleSort(int[] arr) {
        // 0 - n
        // 0 - n-1
        // 0 - n-2
        for (int n = arr.length; n > 0; n--) {
            // 两两比较，谁打谁交换
            for (int sortIndex = 0; sortIndex < n - 1; sortIndex++) {
                if (arr[sortIndex] > arr[sortIndex + 1]) {
                    swap(sortIndex, sortIndex + 1, arr);
                }
            }
        }
    }

    /**
     * 插入排序：从0 位置开始，往左排序，看当前数左边是否还有数，并且左侧的数比当前数大，则交换，一直比到左侧没有数或者左侧的数比当前数大
     * 从 0 位置开始，看0 位置左边是否还有数，有的话比较，大就交换，一直到左边没数或者0 位置的数不比左位置的小，结束，0~0有序
     * 从 1 位置开始，看1 位置左边是否还有数，有的话比较，大就交换，一直到左边没数或者1 位置的数不比左位置的小，结束，0~1有序
     */
    public static void insertSort(int[] arr) {
        // 0 - n
        // 1 - n
        // 2 - n
        for (int n = 0; n < arr.length; n++) {
            // 往左看，小就交换，直到左边没有数或者左边的数比自己小停止
            int sortIndex = n;
            while (sortIndex > 0 && arr[sortIndex] < arr[sortIndex - 1]) {
                swap(sortIndex, sortIndex - 1, arr);
                sortIndex--;
            }
        }
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int x, int y, int[] arr) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    private static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for(int j = i;j > 0 && arr[j] < arr[j - 1];j--){
                swap(j,j-1,arr);
            }
        }


    }
}
