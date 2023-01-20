import java.util.LinkedList;
import java.util.*;

public class Toolbox {
    public static void main(String[] args) {
        Toolbox tb = new Toolbox();
        TreeNode root;
        root = tb.addKeyInBianyTreeLayerByLayer(new Integer[] {1,2,7,3,null,null,null,4,null,5,null,6});
        System.out.println(tb.printKeyInBianryTreeLayerByLayer(root));

        root = tb.addKeyInBianyTreeLayerByLayer(new Integer[] {-13,0,-1,2,14,-12,3,10,null,-6,1,6,-4,11,5,16});
        System.out.println(tb.printKeyInBianryTreeLayerByLayer(root));

        root = tb.addKeyInBianyTreeLayerByLayer(new Integer[] {7,3,16,2,5,11,18,1,null,4,6,null,12,null,20});
        System.out.println(tb.printKeyInBianryTreeLayerByLayer(root));

        root = tb.addKeyInBianyTreeLayerByLayer(new Integer[] {1,2,null,3,4,null,null,5});
        System.out.println(tb.printKeyInBianryTreeLayerByLayer(root));

    }
    /**
     *
     * @param array
     * @param begin
     * @param end
     * @return
     *
     * @author  Daniu Tang
     */
    public String mySubArray(int[] array, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        StringBuilder ss = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (begin <= i && i <= end) {
                sb.append("* ");
            } else {
                sb.append("  ");
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param dict
     * @return
     */
    public Set<String> toSet(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String s : dict) {
            set.add(s);
        }
        return set;
    }

    /**
     *
     * @param stack
     * @return
     */
    public String stackToString(Deque<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        for (Integer number : stack) {
            sb.append(String.valueOf(number) + " ");
        }
        return sb.toString();
    }

    /**
     *
     * @param realRoot
     * @param root
     */
    public void inOrder(TreeNode realRoot, TreeNode root) {
        if (root != null) {
            inOrder(realRoot, root.left);
//            System.out.println(root.key + " ");
            inOrder(realRoot, root.right);
        }
    }

    /**
     *
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.key + " ");
            inOrder(root.right);
        }
    }

    /**
     *
     * @param list
     * @return
     */
    public String listOfTreeNodeToString(List<TreeNode> list) {
        StringBuilder sb = new StringBuilder();
        for (TreeNode root : list) {
            sb.append(String.valueOf(root.key));
        }
        return sb.toString();
    }

    /**
     *
     * @param array
     * @return
     */
    public TreeNode addKeyInBianyTreeLayerByLayer(Integer[] array) {
        TreeNode root = new TreeNode();
        return addKeyInBianryTreeLayerByLayerHelper(array, root, 0);
    }

    /**
     *
     * @param array
     * @param root
     * @param i
     * @return
     */
    private TreeNode addKeyInBianryTreeLayerByLayerHelper(Integer[] array, TreeNode root, int i) {
        if (i < array.length) {
            if (array[i] == null) {
                return root;
            }
            TreeNode temp = new TreeNode(array[i]);
            root = temp;
            root.left = addKeyInBianryTreeLayerByLayerHelper(array, root.left, 2*i + 1);
            root.right = addKeyInBianryTreeLayerByLayerHelper(array, root.right, 2*i + 2);
        }
        return root;
    }

    /**
     *
     * @param root
     * @return
     */
    public String printKeyInBianryTreeLayerByLayer(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int[] leadingSpaces = new int[] {1, 3 ,6, 12, 24, 48, 48 * 2};
        int[] middleSpaces = new int[] {1, 4, 10, 22, 47, 80};
        int h = findHeight(root);
        while (!queue.isEmpty() && h >= 0) {
            int size = queue.size();
            boolean isLeading = true;
            sb.append(h + "  ");
            for (int i = 0; i < size; i++) {
                if (queue.peek() != null && queue.peek().key == -9999) {
                    if (isLeading) {
                        sb.append(makeSpace(leadingSpaces[h]));
                        isLeading = false;
                    } else {
                        sb.append(makeSpace(middleSpaces[h]));
                    }
                    sb.append("  ");
                    queue.poll();
                    queue.offer(new TreeNode(-9999));
                    queue.offer(new TreeNode(-9999));
                } else if (queue.peek() == null) {
                    rootToString(queue.poll());
                    if (h >= 0) {
                        if (isLeading) {
                            sb.append(makeSpace(leadingSpaces[h]));
                            isLeading = false;
                        } else {
                            sb.append(makeSpace(middleSpaces[h]));
                        }
                        sb.append("# ");
                        queue.offer(new TreeNode(-9999));
                        queue.offer(new TreeNode(-9999));
                    }
                } else {
                    if (isLeading) {
                        sb.append(makeSpace(leadingSpaces[h]));
                        isLeading = false;
                    } else {
                        sb.append(makeSpace(middleSpaces[h]));
                    }

                    if (0 < queue.peek().key && queue.peek().key < 10) {
                        sb.append(rootToString(queue.peek()) + " ");
                    } else {
                        sb.append(rootToString(queue.peek()));
                    }
                    queue.offer(queue.peek().left);
                    queue.offer(queue.poll().right);
                }
            }
            h--;
            sb.append("\n");
        }
        return sb.toString();
    }

    private String makeSpace(int space) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < space; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }


    private String rootToString(TreeNode root) {
        if (root == null) {
            return new String("#");
        } else {
            return new String(String.valueOf(root.key));
        }
    }

    public int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(findHeight(root.left),findHeight(root.right)) + 1;
    }


    public String intToBinaryString(int a) {
        String binaryString = Integer.toBinaryString(a);
        String withLeadingZeros = String.format("%8s", binaryString).replace(' ', '0');
        return withLeadingZeros;
    }

    public String printBinary(long value) {
        StringBuilder builder = new StringBuilder();
        builder.append(value);
        builder.append(" : ");
        int spaceIndex = 0;
        for (int shift = 31; shift >= 0; shift--) {
            if (spaceIndex == 4) {
                builder.append(" ");
                spaceIndex = 0;
            }
            builder.append((value >>> shift) & 1);
            spaceIndex++;
        }
        return builder.toString();
    }

    public void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public String chessboardToString(List<Integer> chessboard, int size) {
        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= size; row++) {
            int quessn_col_location = -1;
            if (row <= chessboard.size()) {
                quessn_col_location = chessboard.get(row-1) + 1;
            }
            for (int col = 1; col <= size; col++) {
                if (col == quessn_col_location) {
                    sb.append("X  ");
                } else {
                    sb.append("o  ");
                }
            }
            sb.append("\n");

        }
        return sb.toString();
    }

    public String listOfListToString(List<List<Integer>> result) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> l : result) {
            sb.append(l.toString());
        }
        return sb.toString();
    }

    public String chessboardToString(List<Integer> chessboard, int size, int trow, int column) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            int quessn_col_location = -1;
            if (row < chessboard.size()) {
                quessn_col_location = chessboard.get(row);
            }
            for (int col = 0; col < size; col++) {
                if (col == quessn_col_location) {
                    sb.append("X  ");
                } else {
                    if (col == column && trow == row) {
                        sb.append("R  ");
                    } else {
                        sb.append("o  ");
                    }
                }
            }
            sb.append("\n");

        }
        return sb.toString();
    }

}
