import java.util.ArrayList;
import java.util.List;

/**
 * @author subham-santra
 */
public class CombinationSum {
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> list = new ArrayList<>();
            recursiveHelper(candidates, 0, 0, target, new ArrayList<>(), list);
            return list;
        }

        void recursiveHelper(final int[] candidates,
                final int currentIndex,
                final int currentSum,
                final int target,
                final List<Integer> currentList,
                final List<List<Integer>> list) {

            if (currentIndex >= candidates.length) {
                // NOTHING todo
                return;
            }

            if (currentSum == target) {
                list.add(new ArrayList<>(currentList));
                return;
            }

            // Add current item to currentList
            currentList.add(candidates[currentIndex]);
            int positionInList = currentList.size() - 1;

            // Using current item
            // when no overflow
            final int currentSumAfterIncluding = currentSum + candidates[currentIndex];
            if (currentSumAfterIncluding <= target) {
                recursiveHelper(candidates, currentIndex, currentSumAfterIncluding, target, currentList, list);
            }

            // Remove current item from currentList
            currentList.remove(positionInList);

            // Without using current item
            recursiveHelper(candidates, currentIndex + 1, currentSum, target, currentList, list);
        }
    }
}


//Karthik
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
    //- f([2,3,6,7],7)
     //   -f()
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        generate(candidates, candidates.length,target,0,new ArrayList<>());
        return res;
    }
}
