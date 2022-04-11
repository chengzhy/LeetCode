package binary_search.easy;

/**
 * 搜索插入位置
 * <a href="https://leetcode-cn.com/problems/search-insert-position/">🔗</a>
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 **/
public class P35_SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        return search(nums, 0, nums.length-1, target);
    }

    private int search(int[] nums, int l, int r, int target) {
        int mid = l + (r - l) / 2;
        if (l <= r) {
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] > target) {
                    // 左半区找
                    return search(nums, l, mid-1, target);
                } else {
                    // 右半区找
                    return search(nums, mid+1, r, target);
                }
            }
        }
        // 返回左指针位置
        return l;
    }

}
