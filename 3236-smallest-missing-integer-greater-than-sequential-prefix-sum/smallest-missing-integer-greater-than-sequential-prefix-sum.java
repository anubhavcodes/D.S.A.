class Solution {
    public int missingInteger(int[] nums) {

        // Step 1: Find sum of longest sequential prefix
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Step 2: Check numbers starting from sum
        boolean[] present = new boolean[101];

        for (int num : nums) {
            present[num] = true;
        }

        // Step 3: Find first missing number
        while (sum < present.length && present[sum]) {
            sum++;
        }

        return sum;
    }
}