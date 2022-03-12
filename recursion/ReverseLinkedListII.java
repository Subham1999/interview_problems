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
