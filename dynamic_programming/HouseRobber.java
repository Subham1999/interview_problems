
public class HouseRobber {
    class Solution {
        int[] cache;

        public int rob(int[] nums) {
            cache = new int[nums.length];
            Arrays.fill(cache, -1);
            return func(nums, 0);
        }

        int func(int[] arr, int idx) {
            if (idx >= arr.length)
                return 0;
            if (cache[idx] != -1)
                return cache[idx];
            return cache[idx] = Math.max(arr[idx] + func(arr, idx + 2), func(arr, idx + 1));
        }
    }

    public static void main(String[] args) {
        // Test here
    }
}
/*
 * 
 * 
 * 1, 2, 3, 1
 * ^
 * 
 * a[0...n-1]
 * 
 * f(i) = 0, i >= length of 'a'
 * max {
 * a[i] + f(i + 2),
 * f(i + 1)
 * }
 * 
 */