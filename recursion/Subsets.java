import java.util.*;
public class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public void generate(List<Integer> cur,int k,int n,int[] nums){
            res.add(new ArrayList<>(cur));
            for(int i=k;i<n;i++){
                cur.add(nums[i]);
                generate(cur,i+1,n,nums);
                cur.remove(new Integer(nums[i]));
            }
    }
    public List<List<Integer>> subsets(int[] nums) {
        generate(new ArrayList<>(),0,nums.length,nums);
        return res;
    }
    public static void main(String[] args){
      int[] nums = new int[]{1,2,3};
      new Solution().subsets(nums);
    }
}
