
public class HouseRobberII {

    class Solution {
        int[] cache1;
        int[] cache2;

        public int rob(int[] nums) {
            if (nums.length == 1)
                return nums[0];
            cache1 = new int[nums.length];
            cache2 = new int[nums.length];
            Arrays.fill(cache1, -1);
            Arrays.fill(cache2, -1);

            return Math.max(
                    func(nums, 0, nums.length - 2, cache1),
                    func(nums, 1, nums.length - 1, cache2));
        }

        int func(int[] arr, int idx, int end, int[] cache) {
            if (idx > end)
                return 0;

            if (cache[idx] != -1)
                return cache[idx];

            return cache[idx] = Math.max(
                    arr[idx] + func(arr, idx + 2, end, cache),
                    func(arr, idx + 1, end, cache));
        }
    }

    public static void main(String[] args) {
        // Test here
    }
}
