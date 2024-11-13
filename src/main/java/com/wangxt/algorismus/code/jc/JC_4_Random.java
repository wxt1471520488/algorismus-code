package com.wangxt.algorismus.code.jc;

import java.util.HashMap;
import java.util.Map;

public class JC_4_Random {

    // 该函数可以获取到1-5的随机
    private static int random1_5() {
        return (int)(Math.random() * 5) + 1;
    }

    // 1.先通过1-5随机函数，搞出一个01 等概率发生器
    private static int random01() {
        while (true) {
            double v = random1_5();
            if (v < 3) {
                return 1;
            }
            if (v > 3) {
                return 0;
            }
        }
    }

    // 2.在想办法得到[0,6)的等概率发生器,然后+1就是[1,7)的等概率发生器
    private static int random0_6() {
        // 3.表示6需要3个二级制位，那么[0,7)的等概率发生器就是
        int res = random01() << 2 + random01() << 1 + random01();
        // 4.然后我们遇到7就重做，这样就得到[0,6)
        while (true) {
            int i = (random01() << 2) + (random01() << 1) + random01();
            if (i != 7) {
                res = i;
                break;
            }
        }
        return res;
    }

    private static int random1_7() {
        return random0_6() + 1;
    }

    public static void main(String[] args) {
        int times = 10000000;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < times; i++) {
            int i1 = (int) (Math.random() * 7);
            if (map.containsKey(i1)) {
                map.put(i1, map.get(i1) + 1);
            } else {
                map.put(i1, 1);
            }
            i1 = random0_6();
            if (map1.containsKey(i1)) {
                map1.put(i1, map1.get(i1) + 1);
            } else {
                map1.put(i1, 1);
            }
        }

        System.out.println(map.toString());
        System.out.println(map1.toString());

       // x2Power2();
        System.out.println("===================");
    }

    /**
     * 因为Math.random得出的是[0,1)的随机数，
     * 生成的数小于x发生的概率就是x，生成的数大于x的概率就是1-x
     * Math.max(Math.random,Math.random) < x, 2次random的最大值要小于x，那么2次的结果都要小于x，一次结果小于x的概率是x，2次都小于就是x²
     * Math.min(Math.random,Math.random) < x, 2次random的最小值要小于x，那么只要有一次的结果小于x就行，也就是说两次都小于和任意一次小于都可以，那就是x² + 2x(1 - x) = 2x - x²
     * 如果反过来推算，先求2次都大于x的概率就是(1 - x) * (1 - x)，再求两次都小于和任意一次小于，就是1 - (1 - x) * (1 - x) = 1 - (1 - x)²
     */
    private static void x2Power2() {
        int count = 0;
        int testTimes = 10000;
        double x = 0.17;
        for (int i = 0; i < testTimes; i++) {
            if (Math.max(Math.random(), Math.random()) < x) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));
        System.out.println("==================");

        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.min(Math.random(), Math.random()) < x) {
                count++;
            }
        }

        System.out.println((double) count / (double) testTimes);
        System.out.println(1 - Math.pow((1 - x), 2));
        System.out.println(x * (1 - x));
    }

    //------------------------------------
    // 01不等概率
    private static int m01(){
        return Math.random() < 0.6 ? 1 : 0;
    }

    // 01等概率
    // m01得到 1的概率是 0.6，得到0的概率是1 - 0.6 = 0.4
    // 那么两次结果相乘，几种情况的概率：
    // 1 1 概率 0.6 * 0.6 = 0.36
    // 1 0 概率 0.6 * 0.4 = 0.24
    // 0 1 概率 0.4 * 0.6 = 0.24
    // 0 0 概率 0.4 * 0.4 = 0.16
    // 可以看到两次结果不一样的概率是一样的，所以当2次结果一样时就重做，不一样时返回，那么返回0和1的概率都是 0.24 / 0.24 * 0.24 = 0.5
    private static int m01_d(){
        while (true){
            // 两次获得x和y
            int x = m01();
            int y = m01();
            // 如果两次结果不一样就返回，什么时候
            if(x != y){
                return y;
            }
        }
    }
}
