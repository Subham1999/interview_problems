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