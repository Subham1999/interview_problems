import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Subham Santra
 * @param <K> key
 * @param <V> value
 */
public class LeastRecentlyUsedCache<K, V> {

    class Node<K, V> {
        private K key;
        private V value;
        private Node prev;
        private Node next;

        public Node(K key, V value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("{\"key\":\"%s\", \"value\":\"%s\"}", getKey(), getValue());
        }
    }

    final int SIZE;
    private Map<K, Node<K, V>> nodeMap = new ConcurrentHashMap<>();
    private Node<K, V> headNode;
    private Node<K, V> tailNode;

    public LeastRecentlyUsedCache(int SIZE) {
        this.SIZE = SIZE;
    }

    public LeastRecentlyUsedCache() {
        this(5);
    }


    void put(K key, V val) {
        if (alreadyExist(key)) {
            updateNode(key, val);
        } else {
            final Node<K, V> newNode = new Node<>(key, val);
            nodeMap.put(key, newNode);
            addNode(newNode);
        }
    }

    V get(K key) {
        if (alreadyExist(key)) {
            return nodeMap.get(key).getValue();
        }
        return null;
    }

    V get(K key, V defaultVal) {
        final V val = get(key);
        if (val == null) {
            return defaultVal;
        }
        return val;
    }

    private void updateNode(K key, V val) {
        final Node<K, V> kvNode = nodeMap.get(key);
        deleteNode(kvNode);
        kvNode.setValue(val);
        kvNode.setNext(null);
        kvNode.setPrev(null);
        addNode(kvNode);
    }

    private void addNode(Node<K, V> kvNode) {
        if (tailNode == null) {
            if (headNode != null) {
                throw new RuntimeException("headNode is not null but tailNode is null");
            }
            headNode = tailNode = kvNode;
        } else {
            kvNode.setPrev(tailNode);
            tailNode.setNext(kvNode);
            tailNode = tailNode.getNext();
        }
        balance();
    }

    private void deleteNode(Node<K, V> kvNode) {
        if (kvNode == headNode && kvNode == tailNode) {
            headNode = tailNode = null;
        } else if (kvNode == headNode) {
            headNode = headNode.getNext();
            headNode.setPrev(null);
        } else if (kvNode == tailNode) {
            tailNode = tailNode.getPrev();
            tailNode.setNext(null);
        } else {
            final Node prevNode = kvNode.getPrev();
            final Node nextNode = kvNode.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
    }

    private boolean alreadyExist(K key) {
        return nodeMap.containsKey(key);
    }

    private void balance() {
        while (size() > SIZE) {
            evict();
        }
    }

    private void evict() {
        if (headNode == null)
            throw new RuntimeException("Invoked evict() when head=null");
        removeNodeFromMap(headNode);
        headNode = headNode.getNext();
        headNode.setPrev(null);
    }

    private void removeNodeFromMap(Node<K, V> node) {
        nodeMap.remove(node.key);
    }

    private int size() {
        return nodeMap.size();
    }

    void print() {
        Node<K, V> node = headNode;
        while (node != null) {
            System.out.print(node);
            node = node.getNext();
        }
        System.out.println();
    }
}
