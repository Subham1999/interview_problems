/**
** @see : <a href="https://leetcode.com/problems/pascals-triangle/>click here</a>
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1)
            return List.of(List.of(1));
        List<List<Integer>> table = new ArrayList<>();
        for (int i = 1; i <= numRows; ++i) {
            table.add(new ArrayList<>());
        }
        
        
        for (int row = 1; row <= numRows; ++row) {
            for (int col = 1; col <= row; ++col) {
                if (col == 1 || col == row) {
                    table.get(row - 1).add(1);
                } else {
                    table.get(row - 1).add(
                        table.get(row - 1 - 1).get(col - 1 - 1) + table.get(row - 1 - 1).get(col - 1)
                    );
                }
            }
        }
        return table;       
    }
}
