import java.util.PriorityQueue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Node {
    int val;
    int count;
    int lastIndex;

    public Node(int _v, int _c, int _i) {
        val = _v;
        count = _c;
        lastIndex = _i;
    }

    @Override
    public String toString() {
        return String.format("<value = %d, count = %d, index = %d>", val, count, lastIndex);
    }
}

public class FreqStack {

    PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> {
        if (node1.count == node2.count) {
            return node2.lastIndex - node1.lastIndex; // descending
        }
        return node2.count - node1.count; // descending
    });

    HashMap<Integer, Node> map = new HashMap<>();
    HashMap<Integer, Stack<Integer>> indexMap = new HashMap<>();

    int index = 0;

    public FreqStack() {
    }

    public boolean isPresent(int val) {
        return map.containsKey(val);
    }

    public Node remove(int val) {
        Node node = map.get(val);
        pq.remove(node);
        return node;
    }

    private void addNewNode(int val) {
        Node node = new Node(val, 1, index);
        map.put(val, node);
        pq.add(node);
    }

    private void addNewNode(Node node) {
        map.put(node.val, node);
        pq.add(node);
    }

    public void push(int val) {
        // System.out.printf("before 'push' (%d) =in map(%s) =in indexMap(%s)\n", val, map.get(val), indexMap.get(val));
        if (isPresent(val)) {
            Node oldNode = remove(val);
            oldNode.count = oldNode.count + 1;
            oldNode.lastIndex = index;
            addNewNode(oldNode);
        } else {
            addNewNode(val);
            if (!indexMap.containsKey(val)) {
                indexMap.put(val, new Stack<>());
            }
        }
        indexMap.get(val).push(index);
        ++index;
        // System.out.printf("after 'push' (%d) =in map(%s) =in indexMap(%s)\n", val, map.get(val), indexMap.get(val));
    }

    private String check() {
        List<Node> nodes = new ArrayList<>();
        while (!pq.isEmpty()) {
            nodes.add(pq.poll());
        }
        for (Node node : nodes) {
            pq.add(node);
        }
        return nodes.toString();
    }

    public int pop() {
        Node node = pq.peek();
        // System.out.printf("before 'pop' (%d) =in map(%s) =in indexMap(%s)\n", node.val, map.get(node.val), indexMap.get(node.val));
        indexMap.get(node.val).pop();
        if (node.count == 1) {
            // if count = 1 then remove completely
            // remove(node.val);
            pq.remove(node);
            // indexMap.get(node.val).pop();
            map.get(node.val).count = 0;
        } else {
            Integer lastIndex = indexMap.get(node.val).peek();
            remove(node.val);
            node.count -= 1;
            node.lastIndex = lastIndex;
            addNewNode(node);
        }
        // System.out.printf("after 'pop' (%d) =in map(%s) =in indexMap(%s)\n", node.val, map.get(node.val), indexMap.get(node.val));
        return node.val;
    }

    public static void main(String[] args) {
        // FreqStack freqStack = new FreqStack();
        // Write Test cases
    }
}
