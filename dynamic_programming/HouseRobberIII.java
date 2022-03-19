public class HouseRobberIII {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *      int val;
     *      TreeNode left;
     *      TreeNode right;
     *      TreeNode() {}
     *      TreeNode(int val) { this.val = val; }
     *      TreeNode(int val, TreeNode left, TreeNode right) {
     *          this.val = val;
     *          this.left = left;
     *          this.right = right;
     *      }
     * }
     */
    class Solution {
        public int rob(TreeNode root) {
            return rob(root, new HashMap<>());
        }

        int rob(TreeNode node, HashMap<TreeNode, Integer> map) {
            if (node == null)
                return 0;

            int ans = 0;
            if ((ans = map.getOrDefault(node, -1)) != -1)
                return ans;

            // Two choices
            // case 1 : include
            int include = 0;
            if (node.left != null) {
                int left = rob(node.left.left, map);
                int right = rob(node.left.right, map);
                include += left + right;
            }
            if (node.right != null) {
                int left = rob(node.right.left, map);
                int right = rob(node.right.right, map);
                include += left + right;
            }
            include += node.val;

            // case 2 : exclude
            int exclude = 0;
            exclude += rob(node.left, map);
            exclude += rob(node.right, map);

            // store to map
            map.put(node, Math.max(include, exclude));

            return map.get(node);
        }
    }

    public static void main(String[] args) {

    }
}

//Karthik's Solution
class Solution {
    
    public int[] rob1(TreeNode root){
        if(root==null)
            return new int[]{0,0};
        int[] left = rob1(root.left);
        int[] right = rob1(root.right);
        int withRoot = root.val + left[1]+right[1];
        int withoutRoot = Math.max(left[0],left[1])+ Math.max(right[0],right[1]);
        return new int[]{withRoot,withoutRoot};
    }
    public int rob(TreeNode root) {
        int[] res = rob1(root);
        return Math.max(res[0],res[1]);
    }
}
