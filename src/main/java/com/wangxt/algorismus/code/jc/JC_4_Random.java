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
     * Math.max(Math.random,Math.random),取两次random的最大值要小于x，那么两次random要同时小于x取最大值才小于x，所以两次random最大值得到x的概率就是x的平方
     * Math。min(Math.random,Math.random),取两次random的最小值，因为是最小值，所以有一次random得到的值小于x，那么两次的最小值就小于x，相反，如果两次都不得x的概率
     * 就是1-x的平方，那么能得到x的概率就是1-(1-x)的平方
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
    }

    //------------------------------------
    // 01不等概率
    private static int m01(){
        return Math.random() < 0.6 ? 1 : 0;
    }

    // 01等概率
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
