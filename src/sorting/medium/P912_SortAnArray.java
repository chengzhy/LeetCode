package sorting.medium;

import java.util.Random;

/**
 * 排序数组
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * -5 * 104 <= nums[i] <= 5 * 104
 *
 * @author chengzhy
 * @date 2022/3/8 11:16
 */
public class P912_SortAnArray {

    public int[] sortArray(int[] nums) {
        return SortingAlgorithmEnum.QUICK_SORT.sortArray(nums);
    }

    public enum SortingAlgorithmEnum {
        MERGE_SORT {
            @Override
            public int[] sortArray(int[] nums) {
                mergeSort(nums, 0, nums.length - 1);
                return nums;
            }

            /**
             * 归并排序
             *
             * @author chengzhy
             * @param nums 需要排序的数组
             * @param left 左边界
             * @param right 右边界
             * @date 2022/2/19 13:00
             */
            private void mergeSort(int[] nums, int left, int right) {
                if (left < right) {
                    int mid = (left + right) >> 1;
                    /**
                     * 分治思想：同时对左半区间和右半区间排序
                     * 排序完后再合并
                     */
                    mergeSort(nums, left, mid);
                    mergeSort(nums, mid + 1, right);
                    // 利用辅助数组合并
                    merge(nums, left, mid, right);
                }
            }

            /**
             * 合并区间
             *
             * @author chengzhy
             * @param nums 需要排序的数组
             * @param left 左边界
             * @param mid 中间点
             * @param right 右边界
             * @date 2022/2/19 13:00
             */
            private void merge(int[] nums, int left, int mid, int right) {
                int[] temp = new int[right - left + 1];
                int i = left, j = mid + 1, k = 0;
                // 将小的先添加进去
                while (i <= mid && j <= right) {
                    temp[k++] = (nums[i] < nums[j]) ? nums[i++] : nums[j++];
                }
                // 左半区剩余
                while (i <= mid) {
                    temp[k++] = nums[i++];
                }
                // 右半区剩余
                while (j <= right) {
                    temp[k++] = nums[j++];
                }
                // 更新原数组
                for (i = 0; i < temp.length; i++) {
                    nums[i + left] = temp[i];
                }
            }
        },
        QUICK_SORT {
            @Override
            public int[] sortArray(int[] nums) {
                quickSort(nums, 0, nums.length - 1);
                return nums;
            }

            /**
             * 快速排序
             *
             * @author chengzhy
             * @param nums 需要排序的数组
             * @param left 左边界
             * @param right 右边界
             * @date 2022/2/19 13:35
             */
            private void quickSort(int[] nums, int left, int right) {
                if (left < right) {
                    /**
                     * 核心思想：以一个数为基准，将比其大的数放到其右边，比其小的数放到其左边
                     * 然后再递归排以这个数为中心的左半区间和右半区间
                     */
                    int i = partition(nums, left, right);
                    quickSort(nums, left, i - 1);
                    quickSort(nums, i + 1, right);
                }
            }

            /**
             * 分隔(选取一个数作为基准，将比其大的数放到其右边，比其小的数放到其左边)
             *
             * @author chengzhy
             * @param nums 需要排序的数组
             * @param left 左边界
             * @param right 右边界
             * @date 2022/2/19 13:35
             * @return 分隔后基准数的下标值
             */
            private int partition(int[] nums, int left, int right) {
                /**
                 * 优化点：针对特殊测试用例：顺序数组或者逆序数组，一定要随机化选择切分元素（pivot），
                 * 否则在输入数组是有序数组或者是逆序数组的时候，快速排序会变得非常慢
                 */
                int randomIndex = left + new Random().nextInt(right - left + 1);
                int temp = nums[left];
                nums[left] = nums[randomIndex];
                nums[randomIndex] = temp;
                // 将区间的第一个数作为基准
                int base = nums[left];
                int i = left, j = right;
                while (i < j) {
                    // 循环找比基数小和比基数大的数
                    // 先去找第一个比基数小的数的位置
                    while (base <= nums[j] && i < j) {
                        j--;
                    }
                    // 再去找第一个比基数大的数的位置
                    while (base >= nums[i] && i < j) {
                        i++;
                    }
                    if (i < j) {
                        // 如果i在j的左边，则交换两个位置的数
                        temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                    }
                }
                // 将基数放至中心位置
                nums[left] = nums[i];
                nums[i] = base;
                // 返回基数的位置
                return i;
            }
        };

        public abstract int[] sortArray(int[] nums);
    }

}
