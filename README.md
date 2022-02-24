
# interview_problems

## recursion_problems:
### Combination Sum I:
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



