package math.medium;

/**
 * 最优除法
 * <a href="https://leetcode.cn/problems/optimal-division/">🔗</a>
 *
 * 给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
 *
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。
 *
 * 示例：
 *
 * 输入: [1000,100,10,2]
 * 输出: "1000/(100/10/2)"
 * 解释:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * 但是，以下加粗的括号 "1000/((100/10)/2)" 是冗余的，
 * 因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。
 *
 * 其他用例:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 * 说明:
 *
 * 输入数组的长度在 [1, 10] 之间。
 * 数组中每个元素的大小都在 [2, 1000] 之间。
 * 每个测试用例只有一个最优除法解。
 *
 * @author chengzhy
 * @date 2022/2/27 14:38
 */
public class P553_OptimalDivision {

    public String optimalDivision(int[] nums) {
        /**
         * 数学题：结果为a/b，要是结果最大则尽可能将数集中在分子即可
         * 那么a/b = num1/(num2/num3/.../numn) = num1*num3*num4...*numn/num2这样就是最大的结果
         */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
            if (i < nums.length - 1) {
                sb.append("/");
            }
        }
        if (nums.length > 2) {
            sb.insert(sb.indexOf("/") + 1, '(');
            sb.append(')');
        }
        return sb.toString();
    }

}
