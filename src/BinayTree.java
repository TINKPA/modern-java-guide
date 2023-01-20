import java.util.*;
import java.util.LinkedList;

public class BinayTree extends Toolbox{

    public static void main(String[] args) {
        Hashtable<Integer, Integer> dict = new Hashtable<>();
        dict.put(0,1);
        dict.put(1,3);
        System.out.println(search(dict, 3));
    }

    public static int search(Hashtable<Integer, Integer> dict, int target) {
        int stepSize = 2;
        int index = 0;
        int left = 0;
        int right = 0;
        while (dict.get(right) != null) {
            if (target <= dict.get(right)) {
                return binarySearch(dict, right, left, target);
            }
            right = left * stepSize + 1;
        }
        return -1;
    }

    private static int binarySearch(Hashtable<Integer, Integer> dict, int right, int left, int target) {

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == dict.get(mid)) {
                return mid;
            } else if (target < dict.get(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

class Solution1 extends Toolbox{
    public boolean exist(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        List<TreeNode> path = new ArrayList<TreeNode>();
        return helper(root, path, target);
    }

    private boolean helper(TreeNode root, List<TreeNode> path, int sum) {
        path.add(root);
        int tmp = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            tmp += path.get(i).key;
            if (tmp == sum) {
                return true;
            }
        }
        if (root.left != null && helper(root.left, path, sum)) {
            return true;
        }
        if (root.right != null && helper(root.right, path, sum)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public int[] closestKValues(TreeNode root, double target, int k) {
        Queue<Integer> sw = new LinkedList<>();
        helper(root, target, k, sw, true);
        int[] result = new int[sw.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = sw.poll();
        }
        return result;
    }

    private void helper(TreeNode root, double target, int k, Queue<Integer> slidingWindow, boolean doChangeSlidingWindow) {
        if (root == null) {
            return;
        }
        helper(root.left, target, k, slidingWindow, doChangeSlidingWindow);
        if (slidingWindow.size() < k) {
            slidingWindow.offer(root.key);
        } else {
            if (doChangeSlidingWindow) {
                if ((Math.abs(root.key - target)) < Math.abs(slidingWindow.peek()- target)) {
                    slidingWindow.offer(root.key);
                    slidingWindow.poll();
                } else {
                    doChangeSlidingWindow = true;
                }
            }
        }
        helper(root.right, target, k, slidingWindow, doChangeSlidingWindow);
    }

    public TreeNode rotateTree(TreeNode root) {
        if (!isRoot(root.left) && !isRoot(root.right)) {
            return root;
        }
        if (isRoot(root.left)) {
            root.left = rotateTree(root.left);
            root.left = rotateTree_helper(root.left);
        }
        if (isRoot(root.right)) {
            root.right = rotateTree(root.right);
            root.right = rotateTree_helper(root.right);
        }

        return rotateTree_helper(root);
    }

    public TreeNode rotateTree_helper(TreeNode root) {
        TreeNode newRoot = new TreeNode(-1);
        if (root.left != null) {
            newRoot = root.left;
            TreeNode newRoot_right = root;

            TreeNode newRoot_left = root.right;

            TreeNode theMostRight = mostRight(newRoot);
            theMostRight.right = newRoot_right;
            theMostRight.left = newRoot_left;

        } else { // root.left == null && root.right != null
            newRoot = root.right;
            newRoot.right = mostRight(root);
        }
        root.left = null;
        root.right = null;

        return newRoot;
    }

    private TreeNode mostLeft(TreeNode root) {
        if (root.left == null) {
            return root;
        }
        return mostLeft(root.left);
    }

    private TreeNode mostRight(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        return mostRight(root.right);
    }

    private boolean isRoot(TreeNode aTreeNode) {
        if (aTreeNode != null && aTreeNode.right != null && aTreeNode.left != null) {
            return true;
        }
        return false;
    }


    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        res = postOrder(root,res);
        return res;
    }
    private List<Integer> postOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = null;
        stack.offerFirst(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            // go deeper
            if (pre == null || pre.right == cur || pre.left == cur) {
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else { // guarantee cur.left == null && cur.right == null
                    res.add(stack.pollFirst().key);
                }
            } else if (pre == cur.left) { // back from left
                res.add(cur.key);
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                }
            } else if (pre == cur.right) { // back from right
                stack.pollFirst(); // poll root out form stack
            }
            pre = cur;
        }
        return res;
    }
}

class Solution {
    public TreeNode insert (TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }else {
            if (root.key < key)
                root.right = insert(root.right, key);
            else {
                root.left = insert(root.left, key);
            }
        }
        return root;
    }

    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        res = postOrder_i_helper(root, res);
        return res;
    }

    private List<Integer> postOrder_i_helper(TreeNode root ,List<Integer> res) {
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null;
        stack.offerFirst(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.peekFirst();
            if (prev == null || current == prev.left || current == prev.right) {
                if (current.left != null) {
                    stack.offerFirst(current.left);
                } else if (current.right != null) {
                    stack.offerFirst(current.right);
                } else {
                    res.add(current.key);
                    stack.pollFirst();
                }
            } else if (prev == current.left) {
                if (current.right != null) {
                    stack.offerFirst(current.right);
                } else {
                    res.add(current.key);
                    stack.pollFirst();
                }
            } else {
                res.add(current.key);
                stack.pollFirst();
                }
            prev = current;
        }
        return res;
    }


    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder_helper(root, res);
        return res;
    }

    private void preOrder_helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.key);
        preOrder_helper(root.left, res);
        preOrder_helper(root.right, res);
    }

    // ----------------------------------------------------------------------
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder_helper(root, res);
        return res;
    }

    private void inOrder_helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder_helper(root.left, res);
        res.add(root.key);
        inOrder_helper(root.right, res);
    }

    // ----------------------------------------------------------------------
    public List<Integer> postOrder1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder_helper(root, res);
        return res;
    }

    private void postOrder_helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder_helper(root.left, res);
        postOrder_helper(root.right, res);
        res.add(root.key);
    }
    // ----------------------------------------------------------------------
    public int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(findHeight(root.right), findHeight(root.left)) + 1;
    }

    // ----------------------------------------------------------------------
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

}

class TreeNode {
    int key;
    TreeNode left;
    TreeNode right;
    public TreeNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }
    public TreeNode(){

    }

}