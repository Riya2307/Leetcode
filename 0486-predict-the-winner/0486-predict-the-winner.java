class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        return maxDiff(nums, 0, n - 1, memo) >= 0;
    }

    private int maxDiff(int[] nums, int left, int right, int[][] memo) {
        if (left == right) {
            return nums[left];
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }

        // Pick left element vs Pick right element
        int pickLeft = nums[left] - maxDiff(nums, left + 1, right, memo);
        int pickRight = nums[right] - maxDiff(nums, left, right - 1, memo);

        return memo[left][right] = Math.max(pickLeft, pickRight);
    }
}