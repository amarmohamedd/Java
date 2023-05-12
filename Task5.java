import java.util.*;

class Node {
    String value;
    Node left, right;

    Node(String item) {
        value = item;
        left = right = null;
    }
}

class ExpressionTree {
    Map<String, Integer> variables = new HashMap<>();

    Node constructTree(String postfix) {
        Stack<Node> stack = new Stack<>();
        Node t, t1, t2;

        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if (!isOperator(ch)) {
                t = new Node(Character.toString(ch));
                stack.push(t);
            } else {
                t = new Node(Character.toString(ch));
                t1 = stack.pop();
                t2 = stack.pop();

                t.right = t1;
                t.left = t2;

                stack.push(t);
            }
        }

        t = stack.peek();
        stack.pop();

        return t;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    int evaluate(Node node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null) {
            if (Character.isDigit(node.value.charAt(0))) {
                return Integer.parseInt(node.value);
            } else {
                return variables.getOrDefault(node.value, 0);
            }
        }

        int leftVal = evaluate(node.left);
        int rightVal = evaluate(node.right);
        char operator = node.value.charAt(0);

        switch (operator) {
            case '+':
                return leftVal + rightVal;
            case '-':
                return leftVal - rightVal;
            case '*':
                return leftVal * rightVal;
            case '/':
                if (rightVal == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return leftVal / rightVal;
        }

        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        ExpressionTree et = new ExpressionTree();
        String postfix = "ab+ef*g*-";
        et.variables.put("a", 5);
        et.variables.put("b", 3);
        et.variables.put("e", 7);
        et.variables.put("f", 2);
        et.variables.put("g", 3);
        Node root = et.constructTree(postfix);
        System.out.println(et.evaluate(root));
    }
}
