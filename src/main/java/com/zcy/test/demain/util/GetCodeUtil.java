package com.zcy.test.demain.util;

public class GetCodeUtil {


    public static void main(String[] args) {
        String code = encode(132456);
        int nid = decode(code);
        System.out.println(  132456 + " -> " + code + " -> " + nid);
    }

    //验证码长度
    private static final int LEN = 6;
    //验证码字符列表
    private static final char STUFFS[] = {
            'A','B','C','D','E','F','G','H',
            'I','J','K','L','M','N','P','Q',
            'R','S','T','U','V','W','X','Y',
            '1','2','3','4','5','6','7','8'};

    private static final int PERMUTATION;
    private static final int MAX_COMBINATION;

    static {
        PERMUTATION = permutation(LEN);
        MAX_COMBINATION = combination(STUFFS.length,LEN);
    }

    private static int combination(int n,int m) {
        int com = 1;
        for(int i = n - m + 1; i <= n ; ++i) {
            com *= i;
        }
        for(int i = 2; i <= m ; ++i ) {
            com /= i;
        }
        return com;
    }

    private static int permutation(int n) {
        int per = 1;
        for(int i = 2; i <= n ; ++i) {
            per *= i;
        }
        return per;
    }

    public static int decode(String code) {
        if(code.length() != LEN) {
            throw new RuntimeException("invalid code");
        }
        char[] chars = new char[LEN];
        for(int i = 0; i < LEN;++i) {
            chars[i] = code.charAt(i);
        }
        int com = combination(chars);
        int per = permutation(chars);
        return com * PERMUTATION + per;
    }

    public static String encode(int val) {
        int com = val / PERMUTATION;
        if(com >= MAX_COMBINATION) {
            throw new RuntimeException("id can't be greater than 652458239");
        }
        int per = val % PERMUTATION;
        char[] chars = combination(com);
        chars = permutation(chars,per);
        return new String(chars);
    }

    private static char[] combination(int com){
        char[] chars = new char[LEN];
        int start = 0;
        int index = 0;
        while (index < LEN) {
            for(int s = start; s < STUFFS.length; ++s ) {
                int c = combination(STUFFS.length - s - 1, LEN - index - 1);
                if(com >= c) {
                    com -= c;
                    continue;
                }
                chars[index++] = STUFFS[s];
                start = s + 1;
                break;
            }
        }
        return chars;
    }

    private static char[] sort(char[] src) {
        char[] sort = new char[src.length];
        int index = 0;
        for(int i = 0; i < STUFFS.length;++i) {
            if(find(src, STUFFS[i]) != -1) {
                sort[index++] = STUFFS[i];
            }
        }
        return sort;
    }

    private static int combination(char[] chars) {
        int[] offset = new int[LEN];
        char[] sort = sort(chars);
        for(int i = 0; i < sort.length;++i) {
            offset[i] = find(STUFFS, sort[i]);
            if(offset[i] == -1) {
                throw new RuntimeException("invalid code");
            }
        }
        int com = 0;
        for(int i = 0;i < offset.length;++i) {
            if(i == 0) {
                if(offset[0] == 0) {
                    continue;
                }
                for(int n = 0; n < offset[0];++n) {
                    com += combination(STUFFS.length -  n - 1, LEN - 1);
                }
                continue;
            }

            if(offset[i] - offset[i - 1] <= 1) {
                continue;
            }

            for(int n = offset[i-1] + 1; n < offset[i];++n) {
                com += combination(STUFFS.length -  n - 1, LEN - i - 1);
            }
        }

        return com;
    }

    private static char[] permutation(char[] chars,int per){
        char[] tmpchars = new char[chars.length];
        System.arraycopy(chars, 0, tmpchars, 0, chars.length);
        int[] offset = new int[chars.length];
        int step = chars.length;
        for(int i = chars.length -1;i >= 0;--i) {
            offset[i] = per % step;
            per /= step;
            step --;
        }

        for(int i = 0; i < chars.length;++i) {
            if(offset[i] == 0)
                continue;
            char tmp = tmpchars[i];
            tmpchars[i] = tmpchars[i - offset[i]];
            tmpchars[i - offset[i]] = tmp;
        }

        return tmpchars;
    }

    private static int find(char[] chars,char ch) {
        for(int i = 0;i < chars.length;++i) {
            if(chars[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private static int permutation(char[] chars){
        char[] sort = sort(chars);
        int[] offset = new int[chars.length];
        for(int i =chars.length -1;i >=0;--i ) {
            int f = find(chars, sort[i]);
            offset[i] = i - f;
            char tmp = chars[i];
            chars[i] = chars[i - offset[i]];
            chars[i - offset[i]] = tmp;
        }
        int per = 0;
        int step = 1;
        for(int i = 0; i < offset.length;++i ) {
            per = per * step + offset[i];
            step++;
        }
        return per;
    }

}
