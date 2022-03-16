//Karthik's Solution

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public void generate(int[] candidates,int n,int target,int k,List<Integer> cur){
        if(target<0){
            return;
        }
        if(target==0){
            res.add(new ArrayList<>(cur));
        }
        else{
            for(int i=k;i<n;i++){
                cur.add(candidates[i]);
                generate(candidates,n,target-candidates[i],i,cur);
                cur.remove(new Integer(candidates[i]));
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        generate(candidates, candidates.length,target,0,new ArrayList<>());
        return res;
    }
}
