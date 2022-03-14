//Karthik
class Solution {
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String p: paths){
            if(p.equals("") || p.equals(".")){
                //pass
            }
            else if(p.equals("..")){
                if(!stack.isEmpty())
                    stack.pop();
            }
            else{
                stack.push(p);
            }
        }
        if(stack.isEmpty()){
            sb.insert(0,"/");
        }
        while(!stack.isEmpty()){
            sb.insert(0,stack.pop());
            sb.insert(0,"/");
        }
        return sb.toString();
    }
}
