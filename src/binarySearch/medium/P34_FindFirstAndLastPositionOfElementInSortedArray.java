package binarySearch.medium;

/**
 * @description 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 *
 **/
public class P34_FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int[] res = new int[]{-1, -1};
        // 二分查找
        search(nums, 0, nums.length-1, target, res);
        /**
         * 只匹配到一个的情况
         */
        if (res[0]!=-1 && res[1]==-1) res[1] = res[0];
        if (res[1]!=-1 && res[0]==-1) res[0] = res[1];
        return res;
    }

    private void search(int[] nums, int l, int r, int target, int[] res) {
        // 其它情况
        if (l>nums.length-1 || r<0 || target<nums[l] || nums[r]<target) return;
        int mid = (l + r)/2;
        if (l <= r) {
            // 左指针匹配到
            if (nums[l] == target) {
                // 当前位置比开始位置靠左，更新开始位置
                if (res[0]==-1 || res[0]>l) res[0] = l;
                // 结束位置比当前位置靠左，更新结束位置
                if (res[1] < l) res[1] = l;
            }
            // 右指针匹配到
            if (nums[r] == target) {
                // 当前位置比结束位置靠右，更新结束位置
                if (res[1] < r) res[1] = r;
                // 开始位置比当前位置靠右，更新开始位置
                if (res[0]==-1 || res[0]>r) res[0] = r;
            }
            // 左半区查找
            search(nums, ++l, mid, target, res);
            // 右半区查找
            search(nums, mid+1, --r, target, res);
        }
    }

}
