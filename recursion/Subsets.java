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

//***** USING BITMASKING
class AnotherSolution {
    public List<List<Integer>> subsets(int[] nums) {
        int LIMIT = (1 << nums.length);
        List<List<Integer>> ans = new ArrayList<>();
        
        for (int mask = 0; mask < LIMIT; ++mask) {
            int tmp = mask;
            List<Integer> ll = new ArrayList<>();
            int x = 0;
            while (tmp > 0) {
                if ((tmp & 1) == 1)
                    ll.add(nums[x]);
                tmp >>= 1;
                ++x;
            }
            ans.add(ll);
        }
        
        return ans;
    }
}
