import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 *括号匹配
 * 1.用栈实现，如果读取字符为左括号，入栈
 * 2.如果读取字符为右括号
 *   栈为空，返回false
 *   栈不为空，和栈顶比较，是否匹配，匹配出栈一次，不匹配返回false
 * 3.最后栈不为空，返回false，栈为空返回true
 */
public class Parenttheses {
    public static int number=0;

    public static int isMatch(String str){
        //定义左右括号匹配关系
        Map<Character,Character> map = new HashMap<Character,Character>();
        map.put(')','(');

        int length = str.length();//字符串长度
        LinkedList<Character> stack = new LinkedList<Character>();
        for (int i = 0;i<length;i++){
            //如果为左括号，入栈
            if(map.containsValue(str.charAt(i))){
                stack.push(str.charAt(i));
            }
            //如果为右括号，判断栈是否为空
            if(map.containsKey(str.charAt(i))){
                if(stack.isEmpty()){
                    return -1;
                }
                else if (stack.peek() == map.get(str.charAt(i))){
                    stack.pop();
                    number++;

                }
                else return -1;
            }
        }
        //寻循环遍历完成判断栈是否为空
        //return stack.isEmpty()?true:false;
        if(stack.isEmpty()){
            return number;
        }
        else return -1;

    }


    public static void main(String[] args){

        System.out.println(isMatch("(())"));
    }
}
