package vc.question.arithmetic;

/**
 * 程序员面试100题之二：<a href="http://blog.csdn.net/hackbuteer1/article/details/6686747">跳台阶问题（变态跳台阶）</a>
 *
 * @author <a href="mailto:vague.fu@outlook.com">vague.fu</a>
 * @date 2017-5-6 20:55:28
 * @since 1.8
 */
public class Question1 {
    /**
     * 题目1：一个台阶总共有n级，如果一次可以跳1级，也可以跳2级。求总共有多少总跳法，并分析算法的时间复杂度。
     *
     * @param n 台阶阶数
     * @return 跳法数
     */
    private static int title1(int n) {
        return recursive1(n);
    }

    /**
     * 分析：这道题最近经常出现，包括MicroStrategy等比较重视算法的公司都曾先后选用过个这道题作为面试题或者笔试题。
     * <p>
     * 首先我们考虑最简单的情况。
     * 如果只有1级台阶，那显然只有一种跳法。
     * 如果有2级台阶，那就有两种跳的方法了：一种是分两次跳，每次跳1级；另外一种就是一次跳2级。
     * <p>
     * 现在我们再来讨论一般情况。
     * 我们把n级台阶时的跳法看成是n的函数，记为f(n)。
     * 当n>2时，第一次跳的时候就有两种不同的选择：
     * 一是第一次只跳1级，此时跳法数目等于后面剩下的n-1级台阶的跳法数目，即为f(n-1)；
     * 另外一种选择是第一次跳2级，此时跳法数目等于后面剩下的n-2级台阶的跳法数目，即为f(n-2)。
     * 因此n级台阶时的不同跳法的总数f(n)=f(n-1)+(f-2)。
     * <p>
     * 我们把上面的分析用一个公式总结如下：
     * <p>
     * <pre>
     *      /   1               n=1
     * f(n)=    2               n=2
     *      \   f(n-1)+(f-2) n>2
     * </pre>
     * 分析到这里，相信很多人都能看出这就是我们熟悉的Fibonacci序列。
     *
     * @param n 台阶阶数
     * @return 跳法数
     */
    private static int recursive1(int n) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return recursive1(n - 1) + recursive1(n - 2);
        }
    }

    /**
     * 题目2：一个台阶总共有n级，如果一次可以跳1级，也可以跳2级......它也可以跳上n级。此时该青蛙跳上一个n级的台阶总共有多少种跳法？
     *
     * @param n 台阶阶数
     * @return 跳法数
     */
    private static int title2(int n) {
        return recursive2(n);
    }

    /**
     * 分析：用Fib(n)表示青蛙跳上n阶台阶的跳法数，青蛙一次性跳上n阶台阶的跳法数1(n阶跳)，设定Fib(0) = 1；
     * <br/>
     * <ul>
     * <li>当n = 1 时, 只有一种跳法，即1阶跳：Fib(1) = 1;</li>
     * <li>当n = 2 时， 有两种跳的方式，一阶跳和二阶跳：Fib(2) = Fib(1) + Fib(0) = 2;</li>
     * <li>当n = 3 时，有三种跳的方式，第一次跳出一阶后，后面还有Fib(3-1)中跳法；</li>
     * <li>...</li>
     * <li>第一次跳出二阶后，后面还有Fib(3-2)中跳法；</li>
     * <li>第一次跳出三阶后，后面还有Fib(3-3)中跳法</li>
     * <li>Fib(3) = Fib(2) + Fib(1)+Fib(0)=4;</li>
     * <li>...</li>
     * <li>当n = n 时，共有n种跳的方式，</li>
     * <li>第一次跳出一阶后，后面还有Fib(n-1)中跳法；</li>
     * <li>第一次跳出二阶后，后面还有Fib(n-2)中跳法</li>
     * <li>...</li>
     * </ul>
     * <p>
     * 第一次跳出n阶后，后面还有Fib(n-n)中跳法.
     * <br/>
     * Fib(n) = Fib(n-1)+Fib(n-2)+Fib(n-3)+..........+Fib(n-n)=Fib(0)+Fib(1)+Fib(2)+.......+Fib(n-1)
     * <br/>
     * 又因为Fib(n-1)=Fib(0)+Fib(1)+Fib(2)+.......+Fib(n-2)
     * <br/>
     * 两式相减得：Fib(n)-Fib(n-1)=Fib(n-1) =====》 Fib(n) = 2*Fib(n-1)    n >= 2
     * </p>
     * 递归等式如下：
     * <pre>
     *      /   1             n=0
     * f(n)=    1             n=1
     *      \   2*f(n-1)      n>2
     * </pre>
     *
     * @param n 台阶阶数
     * @return 跳法数
     */
    private static int recursive2(int n) {
        switch (n) {
            case 1:
                return 1;
            default:
                return 2 * recursive1(n - 1);
        }
    }
}
