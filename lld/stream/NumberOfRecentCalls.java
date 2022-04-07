
public class NumberOfRecentCalls {
    class RecentCounter {

        private ArrayDeque<Integer> timestamp = new ArrayDeque<>();
        private static final int TTW = 3000;
        private int currentTime = 0;

        public RecentCounter() {
        }

        public int ping(int t) {
            timestamp.add(t);
            currentTime = t;

            // now remove older requests
            while (!timestamp.isEmpty() && isOlder(timestamp.getFirst(), currentTime)) {
                timestamp.removeFirst();
            }

            return timestamp.size();
        }

        private boolean isOlder(int olderTime, int currentTime) {
            return olderTime < (currentTime - TTW);
        }
    }
  
    public static void main(String[] args) {
        // Test
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
