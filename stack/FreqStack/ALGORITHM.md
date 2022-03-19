# Algorithm `FrequenceyStack`
## Prepared by Subham Santra
----
- Use MAX HEAP to track max frequent element in stack.
- HHEAP node should contain 
    - value, of course we need this :)
    - count (needed to sort DESC)
    - last index (needed to sort DESC)
- Need `value --> last indexes of value` data structure

- push(val)
    - before inserting value check whether it already exists or not
    - if exists then get the node from map
    - remove it from `MAX HEAP`
    - increment the count in `node`
    - again insert it in MAX HEAP

- pop()
    - first check the head of MAX HEAP
    - as the data structure will always make a node as `head` which has max frequency and index is always `lastIndex`
    - if the node has `count == 1` then we need to 
        - remove the node from MAX HEAP
    