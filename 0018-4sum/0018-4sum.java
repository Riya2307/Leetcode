import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        // 1. Sort the array
        Arrays.sort(nums);
        int n = nums.length;

        // 2. First loop for the 1st element
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for the 1st element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 3. Second loop for the 2nd element
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for the 2nd element
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Two pointers for the remaining 3rd and 4th elements
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Use long to prevent integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicate values for 3rd element
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // Skip duplicate values for 4th element
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // Increase the sum
                    } else {
                        right--; // Decrease the sum
                    }
                }
            }
        }

        return result;
    }
}