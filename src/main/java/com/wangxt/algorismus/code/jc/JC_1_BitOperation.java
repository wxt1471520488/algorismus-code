package com.wangxt.algorismus.code.jc;

import java.util.Stack;

/**
 * 位运算
 * https://www.yuque.com/wangxiaotao-yq0j6/uyk8po/gqy14w/edit
 */
public class JC_1_BitOperation {

    public static void main(String[] args) {
        int num = 8;
        print(num);
        print2(num);
        System.out.println("-----------");

        // -2的31次方 ~ 2的31次方-1
        num = Integer.MAX_VALUE;
        print(num);
        num = Integer.MIN_VALUE;
        print(num);

        play();
    }

    /**
     * 1用32位表示是 0000000000000000000000000000001
     * 用 1 左移 i 位 & 上 num
     * 如果 & 完结果 == 0 说明num该位为0
     * 如果 & 完结果 > 0  说明num该位为1
     * <p>
     * 假如数字num是8，8的二级制是 00000000000000000000000000001000
     * 那么我们从第31位开始，用每一位的数和 1 进行 & 操作，如果结果是 1，说明该位置就是 1，否则就是 0
     * 所以，比较第31位时，我们需要将1左移31位，从而得到1000000000000000000000000000000，去 & 上 num，由于1左移31位只有第31位是1，
     * 所以和num &完之后如果结果是1，说明第31位一定是1，否则就是0，每一位都 & 完之后，那么就得到了num的二进制表示
     */
    public static void print2(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    /**
     * 因为二进制是从右往左进位，所以上边遍历时需要从31位开始往低位遍历，可以使用栈实现从低位向高位顺向遍历，
     * 仍然得到正确的的二进制表达，当然该方法只是拓展，时间和空间复杂度都高于直接遍历
     */
    public static void print(int num) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i <= 31; i++) {
            int temp = num & (1 << i);
            String s = temp == 0 ? "0" : "1";
            stack.push(s);
        }

        while (!stack.isEmpty()) {
            String pop = stack.pop();
            System.out.print(pop);
        }
        System.out.println();
    }

    public static void play() {
        int a = 9;
        int b = 3;

        print(a);
        print(b);

        // |  或   有1则为1
        System.out.println("|");
        print(a | b);

        // ^ 亦或  相同为0不同为1，又为无进位相加
        System.out.println("^");
        print(a ^ b);

        // & 与  相同为1
        System.out.println("&");
        print(a & b);

        // >> 带符号右移
        print(-a >> 2);

        // >>> 不带符号右移
        print(-a >>> 2);

        // 相反数
        print(-a);
        print(~a + 1);
    }
}
