

# interview_problems

## recursion_problems:
### Combination Sum I:

    class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        recursiveHelper(
                candidates,
                0,
                0,
                target,
                new ArrayList<>(),
                list
        );

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
            recursiveHelper(
                    candidates,
                    currentIndex,
                    currentSumAfterIncluding,
                    target,
                    currentList,
                    list
            );
        }

        // Remove current item from currentList
        currentList.remove(positionInList);

        // Without using current item
        recursiveHelper(
                candidates,
                currentIndex + 1,
                currentSum,
                target,
                currentList,
                list
        );
    }
    }

### Combination Sum II:
### Print all palindromic partitions:

    static boolean pal(String str) {
        int len = str.length();
        len--;
        for (int i=0; i<len; i++) {
            if (str.charAt(i) != str.charAt(len))
                return false;
            len--;
        }
        return true;
    }
    static ArrayList<ArrayList<String>> help(String s, int f, int t, 
                        ArrayList<ArrayList<String>> ans, ArrayList<String> list) {
        if (f > t) {
            return ans;
        }
        String currS = "";

        for (int i = f; i <= t; ++i) {
            currS = currS + s.charAt(i);
            if (pal(currS)) {
                ArrayList<String> tmpList = new ArrayList<>(list);
                tmpList.add(currS);
                if (i != t){
                    help(s, i + 1, t, ans, tmpList);
                } else {
                    ans.add(tmpList);
                }
            }
        }
        return ans;
    }
    static ArrayList<ArrayList<String>> allPalindromicPerms(String S) {
        // code here
        int n = S.length();
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        return help(S, 0, S.length() - 1, ans, new ArrayList<>());
    }



