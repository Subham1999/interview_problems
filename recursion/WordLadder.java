import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// ## Subham Santra
// #####********** UnSOLVED
public class WordLadder {

    public static void main(String[] args) {
        final WordLadder wordLadder = new WordLadder();

//        // Test case 1
//        System.out.println(
//                wordLadder.new Solution()
//                        .ladderLength("hit", "cog", new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog")))
//        );
//
//        // Test case 2
//        System.out.println(
//                wordLadder.new Solution()
//                        .ladderLength("hit", "cog", new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log")))
//        );
//
//        // Edge case 1
//        System.out.println(
//                wordLadder.new Solution()
//                        .ladderLength("a", "c", new ArrayList<>(List.of("a", "b", "c")))
//        );
//
//        // Edge case 2
//        System.out.println(
//                wordLadder.new Solution()
//                        .ladderLength("a", "c", new ArrayList<>(List.of("a", "x")))
//        );

        System.out.println(
                wordLadder.new Solution()
                        .ladderLength("hot", "dog", new ArrayList<>(List.of("hot", "dog", "cog", "pot", "dot")))
        );
    }

    class Solution {

        int minDistance = Integer.MAX_VALUE;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Map<String, Set<String>> map = new HashMap<>();
            map.put(beginWord, new HashSet<>());
            for (String word : wordList) {
                map.put(word, new HashSet<>());
            }
            wordList.add(beginWord);
            for (int i = 0; i < wordList.size(); ++i) {
                for (int j = i + 1; j < wordList.size(); ++j) {
                    final String w1 = wordList.get(i);
                    final String w2 = wordList.get(j);
                    if (wordDiffOne(w1, w2)) {
                        map.get(w1).add(w2);
                        map.get(w2).add(w1);
                    }
                }
            }
            System.out.println(map);
            dfs(beginWord, endWord, 0, map, new HashSet<>());
            return getAns();
        }

        int getAns() {
            return minDistance == Integer.MAX_VALUE ? 0 : minDistance;
        }

        void updateAns(int newAnswer) {
            minDistance = Math.min(newAnswer, minDistance);
        }

        void dfs(String currentWord, String targetWord, int currentPathLength, Map<String, Set<String>> map, Set<String> visited) {
            if (currentWord.equals(targetWord)) {
                updateAns(currentPathLength + 1);
                return;
            }

            if (visited.contains(currentWord))
                return;

            visited.add(currentWord);
            for (String nextWord : map.get(currentWord)) {
                dfs(nextWord, targetWord, currentPathLength + 1, map, visited);
            }
        }

        private boolean wordDiffOne(String w1, String w2) {
            int[] chars = new int[26];
            for (int i = 0; i < w1.length(); ++i) {
                chars[w1.charAt(i) - 'a']++;
            }
            for (int i = 0; i < w2.length(); i++) {
                chars[w2.charAt(i) - 'a']--;
            }
            boolean plusOne = false;
            boolean minusOne = false;
            for (int i = 0; i < 26; ++i) {
                if (chars[i] > 1)
                    return false;
                if (chars[i] < -1)
                    return false;

                if (chars[i] == 1) {
                    if (plusOne)
                        return false;
                    plusOne = true;
                } else if (chars[i] == -1) {
                    if (minusOne)
                        return false;
                    minusOne = true;
                }
            }
            return plusOne && minusOne;
        }
    }
}
