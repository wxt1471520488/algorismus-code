package com.wangxt.algorismus.code.jc;

import java.util.Arrays;

public class JC_5_FindNum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 7, 8, 9};

        int i = find(arr, 9);
        System.out.println(i);

        test();
    }

    private static int find(int[] arr, int num) {
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int M = (L + R) / 2;

            if (arr[M] == num) {
                return M;
            }

            if (arr[M] > num) {
                R = M - 1;

            } else {
                L = M + 1;
            }
        }

        return -1;
    }

    /**
     * 在一个升序排列的数组 arr 中，找到第一个比给定数 num 大的元素的索引
     **/
    private static int findLeft(int[] arr, int num) {
        int L = 0;
        int R = arr.length - 1;
        int result = -1;
        while (L <= R) {
            int M = (L + R) / 2;

            if (arr[M] <= num) {
                L = M + 1;
            } else {
                result = M;
                R = M - 1;
            }
        }

        return result;
    }

    /**
     * 任任意相邻不相等的数组，找出任意一个局部最小
     * @param arr
     * @return
     */
    private static int findMinimum(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length < 3) {
            return arr[0] > arr[1] ? 1 : 0;
        }

        int L = 0;
        int R = arr.length - 1;
        // 因为我们一直比较的是M-1和M+1，所以前提是M一定在L和R中间不能越界，所以至少要存在3个数，所以结束循环的条件就是L<R-1
        while (L < R - 1) {
            int M = (L + R) / 2;
            // 如果左>arr[M],arr[M]<右，那么M就是局部最小，直接返回
            if (arr[M - 1] > arr[M] && arr[M] < arr[M + 1]) {
                return M;
            } else {
                // 否则的话，如果左<arr[M],那么M左边就是上扬趋势，那么局部最小k肯定在M左边
                if (arr[M - 1] < arr[M]) {
                    R = M - 1;
                // 否则，左>arr[M],M左边是下降趋势，那么局部最小又不是M，那么局部最小肯定在M右边
                } else {
                    L = M + 1;
                }
            }
        }
        return arr[L] < arr[R] ? L : R;
    }

    private static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return true;
        }

        boolean isLeftMin = minIndex - 1 < 0 ? true : arr[minIndex - 1] > arr[minIndex];
        boolean isReightMin = minIndex + 1 >= arr.length ? true : arr[minIndex] < arr[minIndex + 1];
        return isLeftMin && isReightMin;
    }

    private static void test() {
        int maxLen = 10;
        int maxValue = 20;

        int[] arr = new int[(int) (Math.random() * maxLen)];
        if(arr.length == 0){
            return;
        }

        arr[0] = (int) (Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }

        System.out.println("开始");
        int minimum = findMinimum(arr);
        boolean check = check(arr, minimum);
        if (!check) {
            System.out.println("出问题了");
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(minimum);
        System.out.println("结束");
    }
}
