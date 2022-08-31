import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void printLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {

            TreeNode tempNode = queue.poll();

            if (tempNode == null) {
                System.out.print("null, ");
            } else {
                System.out.print(tempNode.val + ", ");
                queue.add(tempNode.left);
                queue.add(tempNode.right);
            }

        }
    }

}

class GetNode {
    Map<Integer, List<TreeNode>> map = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (map.containsKey(n))
            return map.get(n);
        List<TreeNode> list = new ArrayList<>();
        if (n == 1) {
            list.add(new TreeNode(0));
            map.put(1, list);
            return list;
        }

        for (int i = 1; i < n; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - i - 1);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode t = new TreeNode(0, l, r);
                    list.add(t);
                }
            }
        }
        map.put(n, list);
        return list;
    }
}

public class BinaryTreeNode {
    public static void main(String[] args) {
        GetNode gn = new GetNode();
        List<TreeNode> nodes = gn.allPossibleFBT(3);

        for (TreeNode node : nodes) {
            TreeNode.printLevelOrder(node);
            System.out.println("");
        }
    }
}