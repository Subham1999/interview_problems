## Algorithmic Hints

step 1.
  -- How to group and reverse
```
a->b->c->d->e->f->g->h->i->NULL
k=3

0->a->b->c->d->e->f->g->h->i->NULL

0->(a<-b<-c),      d->e->f->g->h->i->NULL

0->(c->b->a)->d->e->f->g->h->i->NULL

0->c->b->a->(d<-e<-f),       g->h->i->NULL

0->c->b->a->(f->e->d)->g->h->i->NULL

0->c->b->a->f->e->d->(g<-h<-i)

0->c->b->a->f->e->d->(i->h->g->NULL)
```

step 2.
  -- How to reverse
```
f(a->b->c->d, n)
f(b->c->d, a->n)
f(c->d, b->a->n)
f(d, c->b->a->n)
```


step 3.
 -- CODE
```
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
 
 /**
  * @see 'https://leetcode.com/problems/reverse-nodes-in-k-group'
  * @author : subham-santra
  */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1)
            return head;
        
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        return reverseK(dummyHead, head, k);
    }
    
    ListNode reverseK(ListNode prev, ListNode node, int k) {
        // base case when current node == 'null'
        if (node == null)
            return null;
        
        ListNode n = node;
        
        ListNode lastNode = n;
        
        for (int i = 0; i < k; ++i) {
            lastNode = n;
            n = n.next;
            if (n == null) {
                if (i < k - 1)
                    return node;
            }
        }
        
        ListNode nextHead = n;
        lastNode.next = null;
        
        prev.next = reverse(node, null);
        node.next = nextHead;
        
        reverseK(node, nextHead, k);
        
        return prev.next;
    }
    
    ListNode reverse(ListNode currentHead, ListNode reversedHead) {
        if (currentHead.next == null) {
            currentHead.next = reversedHead;
            return currentHead;
        }
        
        ListNode next = currentHead.next;
        currentHead.next = reversedHead;
        return reverse(next, currentHead);
    }
}
```
