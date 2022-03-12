
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        return func(head, null);
    }

    /*
     * f(1->2->3, n)
     *  |->f(2->3, 1->n)
     *      |->f(3->n, 2->1->n)
     *          |->f(n, 3->2->1->n)
     * 
     */
    ListNode func(ListNode currentNode, ListNode prevNode) {

        if (currentNode == null) {
            return prevNode;
        }

        ListNode nextNode = currentNode.next;
        currentNode.next = prevNode;
        return func(nextNode, currentNode);
    }
}

public class ReverseLinkedList {

}
