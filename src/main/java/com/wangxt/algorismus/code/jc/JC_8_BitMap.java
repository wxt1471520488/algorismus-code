package com.wangxt.algorismus.code.jc;

/*
位图
 */
public class JC_8_BitMap {
    private long[] bits;

    // max 最大存放个数
    public JC_8_BitMap(int max) {
        // >> 6 --> /64
        bits = new long[(max + 64) >> 6];
    }

    public void add(long num) {
        // 先判断在那个index, / 64, /64 和 << 6 效果一样
        long arrIndex = num / bits.length;
        // 在判断在这个数的那一位上  % 64 ,% 64 和& 63 效果一样
        long index = num % 64;
        // 在将该位置上的数置为1，将1左移index位，或上该位置的数
        bits[(int) arrIndex] = bits[(int) arrIndex] | (1L << index);
    }

    public void del(long num) {
        // 先判断在那个index, / 64, /64 和 << 6 效果一样
        long arrIndex = num / bits.length;
        // 在判断在这个数的那一位上  % 64 ,% 64 和& 63 效果一样
        long index = num % 64;
        // 将该位置的数删除，将1左移index位，取反，在与上该位置的数
        bits[(int) arrIndex] = bits[(int) arrIndex] & ~(1L << index);
    }

    public boolean contains(long num) {
        // 先判断在那个index, / 64, /64 和 << 6 效果一样
        long arrIndex = num / bits.length;
        // 在判断在这个数的那一位上  % 64 ,% 64 和& 63 效果一样
        long index = num % 64;
        // 判断该位置是否存了数，将1左移index位，然后与上该位置的数，看是否为1
        return (bits[(int) arrIndex] & (1L << index)) == 1;
    }

    public static void main(String[] args) {
        System.out.println(64 >> 6);
    }
}

