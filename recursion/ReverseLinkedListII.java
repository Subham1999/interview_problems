// #### kARTHIK
class Solution {
    public ListNode findNthNode(ListNode node,int offset){
        // if left==1
        if(offset==0){
            return null;
        }
       // if right== end
        if(node.next==null)
                return null;
        // to the offset
        while(offset-->1){
            if(node.next==null)
                return null;
            node = node.next; 
        }
        return node;
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode leftPrevNode = findNthNode(head,left-1);
        ListNode rightNextNode = findNthNode(head,right+1);
        // if left starts from 1
        if(leftPrevNode==null){
          ListNode revNode = func(head, leftPrevNode,rightNextNode);
          //after reversing , making the reversed last element to point the last element from right side with original ll.
          head.next = rightNextNode;
          return revNode;
        }
        ListNode revNode = func(leftPrevNode.next,null,rightNextNode);
        leftPrevNode.next.next = rightNextNode;
        leftPrevNode.next = revNode;
        return head;
    }
    
    ListNode func(ListNode currentNode, ListNode prevNode, ListNode right) {
        if (currentNode == right) {
            return prevNode;
        }
        ListNode nextNode = currentNode.next;
        currentNode.next = prevNode;
        return func(nextNode, currentNode,right);
    }
}
// #### subham
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class AnotherSolution {
    void print(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        for (ListNode node = head; node != null; node = node.next) {
            System.out.printf("%d ", node.val);
        }
        System.out.println();
    }
    ListNode nthNode(ListNode head, int n) {
        if (n == 0)
            return null;
        ListNode node = head;
        while (n > 0) {
            node = node.next;
            n--;
        }
        return node;
    }
    
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // base case
        if (left == right)
            return head;
        
        ListNode leftNode = nthNode(head, left - 1);
        ListNode rightNode = nthNode(head, right - 1);
//         print(leftNode);
//         print(rightNode);
//         System.out.println();

        ListNode nextLeft = head;
        
        if (leftNode == null) {
            nextLeft = null;
            leftNode = head;
        } else {
            while (nextLeft.next != leftNode) {
                nextLeft = nextLeft.next;
            }
        }
        
        ListNode prev = rightNode == null ? null : rightNode.next;
        if (rightNode != null) {
            rightNode.next = null;
        }
        
        ListNode ans = reverse(head, nextLeft, leftNode, prev);
        return ans;
    }
    
    ListNode reverse(ListNode head, ListNode beforeLeft, ListNode current, ListNode previous) {
        // print(head);
        // print(beforeLeft);
        // print(current);
        // print(previous);
        // System.out.println("----------------------");
        if (current == null) {
            if (beforeLeft != null) {
                beforeLeft.next = previous;
                return head;
            } else {
                return previous;
            }
        }
        
        ListNode next = current.next;
        current.next = previous;
        previous = current;
        current = next;
        return reverse(head, beforeLeft, current, previous);
    }
}
