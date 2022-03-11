/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * @see : https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 * @author : subham-santra
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        postOrder(root, ans);
        return ans;
    }
    void postOrder(TreeNode node, List<Integer> ans) {
        if (node == null)
            return;
        
        postOrder(node.left, ans);
        postOrder(node.right, ans);
        ans.add(node.val);
    }
}

