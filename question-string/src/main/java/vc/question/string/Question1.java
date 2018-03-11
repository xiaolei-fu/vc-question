package vc.question.string;

/**
 * 程序员面试100题之一：<a href="http://blog.csdn.net/hackbuteer1/article/details/6686263">对称字符串的最大长度</a>
 * <p>
 * 题目：输入一个字符串，输出该字符串中对称的子字符串的最大长度。
 * <br/>
 * 比如输入字符串“google”，由于该字符串里最长的对称子字符串是“goog”，因此输出4。
 * </p>
 *
 * @author <a href="mailto:vague.fu@outlook.com">vague.fu</a>
 * @version 1.0.0
 * @date 2017-5-6 20:02:32
 * @since 1.8
 */
public class Question1 {

    public static void main(String[] args) {
        String test = "google";
        long t1 = System.nanoTime();
        System.out.println(getLongestSymmetricalLength_1(test));
        System.out.println(System.nanoTime() - t1);
        t1 = System.nanoTime();
        System.out.println(getLongestSymmetricalLength(test));
        System.out.println(System.nanoTime() - t1);
        t1 = System.nanoTime();
        System.out.println(manacher(test));
        System.out.println(System.nanoTime() - t1);
    }

    /**
     * getLongestSymmetricalLength()优化版
     * <br/>
     * 动态规划思想
     * <p>
     * 首先在字符中间插入#，消除奇偶判断,
     * 创建数组,存储每个字符回文的最大长度,
     * 在前字符的最远回文距离内，字符的最小长度是以前字符对称的前前字符的回文长度,
     * 初始化最小回文长度并持续判断，记录最远回文长度并更新最远会员下标.
     * </p>
     * 时间复杂度:O(n)
     *
     * @param str 字符串
     * @return 最长回文长度
     */
    private static int manacher(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        //前后中间添加#,字符串统一成奇数长度
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append("#").append(str.charAt(i));
        }
        sb.append("#");
        int length = sb.length();
        //回文半径数组
        int[] p = new int[length];
        //maxId:最远回文下标
        //id:前一个拥有最远回文的下标
        int maxId = 0, id = 0;
        //回文长度
        int symmetricalLength = 1;
        //当前下标
        int begin = 0;
        while (begin < length) {
            //以下标id为中心点，begin的对称点(2*id-begin)的最大回文半径 p[2*id-begin]
            //如果对称点的回文都在以id为中心的回文半径内，则最小长度就是对称点的回文半径
            //否则 begin 的最小回文距离就是以id为中心的最远回文下标maxId和当前下标begin的差 maxId-begin
            if (maxId > begin) {
                p[begin] = p[2 * id - begin] < (maxId - begin) ? p[2 * id - begin] : (maxId - begin);
            } else {
                p[begin] = 1;
            }
            //以begin为中心,p[begin]为最小回文半径,逐步增加半径判断
            while (begin >= p[begin] && (p[begin] + begin) < length && sb.charAt(p[begin] + begin) == sb
                    .charAt(begin - p[begin])) {
                p[begin]++;
            }
            //重置最远回文下标及其中心点
            if (begin + p[begin] > maxId) {
                maxId = begin + p[begin];
                id = begin;
            }
            if (p[begin] > symmetricalLength) {
                symmetricalLength = p[begin] - 1;
            }
            begin++;
        }
        return symmetricalLength;
    }

    /**
     *
     * 遍历字符,向两端扩展判断是否是回文,取最长
     * <br/>
     * 时间复杂度:O(n^2)
     *
     * @param str 字符串
     * @return 最长回文长度
     */
    private static int getLongestSymmetricalLength(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int symmetricalLength = 1;
        int begin = 0;
        int length = str.length();
        while (begin < length) {
            int left = begin, right = begin + 1;
            begin++;
            if (right >= length) {
                continue;
            }
            if (str.charAt(left) == str.charAt(right)) {
            } else if (left > 1 && str.charAt(left - 1) == str.charAt(right)) {
                left--;
            } else {
                continue;
            }
            while (left >= 0 && right < length && str.charAt(right) == str.charAt(left)) {
                left--;
                right++;
            }
            int ll = right - left - 1;
            if (ll > symmetricalLength) {
                symmetricalLength = ll;
            }
        }
        return symmetricalLength;
    }

    /**
     * 从外由内遍历所有字符串并判断是否是回文，取最长长度
     * <br/>
     * 时间复杂度:O(n^3);
     *
     * @param str 字符串
     * @return 最长回文长度
     */
    private static int getLongestSymmetricalLength_1(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int symmetricalLength = 1;
        int begin = 0;
        int length = str.length();
        while (begin < length) {
            int end = begin + 1;
            while (end <= length - 1) {
                if (isSymmetrical(str, begin, end)) {
                    int ll = end - begin + 1;
                    if (ll > symmetricalLength) {
                        symmetricalLength = ll;
                    }
                }
                end++;
            }
            begin++;
        }
        return symmetricalLength;
    }

    /**
     * 判断起始指针为pBegin，结束指针为pEnd的字符串是否对称
     */
    private static boolean isSymmetrical(String str, int begin, int end) {
        if (begin > end) {
            return false;
        }
        while (begin < end) {
            if (str.charAt(begin) != str.charAt(end)) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }
}
