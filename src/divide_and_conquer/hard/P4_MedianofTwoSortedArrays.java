package divide_and_conquer.hard;

/**
 * 寻找两个正序数组的中位数
 * <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/">🔗</a>
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author chengzhy
 * @date 2022/1/27 16:00
 */
public class P4_MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         * 时间复杂度O((m+n)/2)
         */
        /*int m = nums1.length, n = nums2.length;
        int i = 0, j = 0, cursor = 0;
        double a = 0, b = 0, half = (double) (m + n) / 2;
        while (cursor++ <= half) {
            a = b;
            if (i < m && j < n) {
                if (nums1[i] > nums2[j]) {
                    b = nums2[j++];
                } else {
                    b = nums1[i++];
                }
            } else {
                if (i < m) {
                    b = nums1[i++];
                } else {
                    b = nums2[j++];
                }
            }
        }
        return (m+n) % 2 == 0 ? (a + b) / 2 : b;*/

        /**
         * 递归
         * 时间复杂度O(log(m+n))，空间复杂度O(1)
         */
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //一个小技巧：将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

}
