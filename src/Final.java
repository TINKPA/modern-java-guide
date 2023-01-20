import java.util.ArrayList;
import java.util.List;

class StringLinkedList {
    String string;
    StringLinkedList next;
    public StringLinkedList(String string) {
        this.next = null;
        this.string = null;
    }
}

public class Final extends Toolbox{
    public static void main(String[] args) {
        Final f = new Final();
        Toolbox tb = new Toolbox();
//        TreeNode root = tb.addKeyInBianyTreeLayerByLayer(new String[] {"5","3","10","1","4","8","#","#","#","#","#","7","9"});
//        TreeNode root = tb.addKeyInBianyTreeLayerByLayer(new String[] {"5","2","12","1","3","#","14"});
//        System.out.println(tb.printKeyInBianryTreeLayerByLayer(root));
//        System.out.println(f.lca(root, 2,1).key);
        System.out.println(f.decode("1121"));
//        System.out.println(f.canSit_helper(new String[] {"ALICE", "CHARLES", "ERIC", "SOPHIA"}, 1));
    }

    public TreeNode lca(TreeNode root, int p, int q) {
        int small = Math.min(p,q);
        int large = Math.max(p,q);
        while (root != null) {
            if (small < root.key && root.key < large) {
                return root;
            } else if (large < root.key) {
                root = root.left;
            } else if (root.key < small) {
                root = root.right;
            }
        }
        return null;
    }

    /**
     * Q1. Find all possible decode ways
     * 1 -> A
     * 2 -> B
     * also
     * 10 -> J
     * 1121 -> [AABA, AAU, ALA, KBA, KU]
     * @param input
     * @return
     */
    public List<String> decode(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        decode_helper(result, sb, input, 0);
        return result;
    }

    private void decode_helper(List<String> result, StringBuilder sb, String input, int index) {
        if (index == input.length()) {
            result.add(sb.toString());
            return;
        }
        int number = input.charAt(index) - '0';
        if (number >= 1 && number <= 9) {
            char cur = (char) (number - 1 + 'A');
            sb.append(cur);
            decode_helper(result, sb, input, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (index + 1 < input.length()) {
            number = (input.charAt(index+1) - '0') * 10 + input.charAt(index) - '0';
            if (0 <= number && number <= 26) {
                char cur = (char) (number - 1 + 'A');
                sb.append(cur);
                decode_helper(result, sb, input, index + 2);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    /**
     * Q2 Distance between two gieven nodes in a given binary search tree
     */
    public int distance(TreeNode root, int node1, int node2) {
        while ((node1 < root.key && node2 < root.key) || (node2 < root.key && node1 < root.key)) {
            if (root.key > node1 && root.key > node2) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return distancefromRootToNode(root, node1) + distancefromRootToNode(root, node2);
    }

    public int distancefromRootToNode(TreeNode root, int b) {
        int count = 0;
        while (root.key != b) {
            if (root.key > b) {
                root = root.left;
            } else {
                root = root.right;
            }
            count++;
        }
        return count;
    }

    /**
     *
     * @param first
     * @param second
     * @return
     */

    private boolean canSit(String first, String second) {
        return first.charAt(first.length() - 1) == (second.charAt(0));
    }

    private void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean canSit_helper(String[] array, int i) {
        if (i == array.length) {
            return canSit(array[i - 1], array[0]);
        }
        for (int j = i; j < array.length; j++) {
            if (canSit(array[i - 1], array[j])) {
                swap(array, i, j);
                if (canSit_helper(array, i + 1)) {
                    return true;
                }
                swap(array, i, j);
            }
        }
        return false;
    }


//    public boolean dinner(String[] array) {
//        StringLinkedList head = new StringLinkedList(array[0]);
//        StringLinkedList cur = head.next;
//        boolean[] visited = new boolean[array.length];
//        while (head != cur) {
//            int indexOfNext = findNext(array[0], array, visited);
//            if (indexOfNext == -1) {
//                return false;
//            } else {
//                cur = new StringLinkedList(array[indexOfNext]);
//                visited[indexOfNext] = true;
//            }
//        }
//        return true;
//    }
//
//    private boolean isAllVisted(boolean[] vistied) {
//        boolean t = true;
//        for (int i = 0; i < vistied.length; i++) {
//            t = t | vistied[i];
//        }
//        return t;
//    }
//
//    public int findNext(String string, String[] array, boolean[] visited) {
//        char target = string.charAt(string.length() - 1);
//        for (int i = 0; i < array.length; i++) {
//            if (array[i].charAt(0) == target && !visited[i]) {
//                return i;
//            }
//        }
//        return -1;
//    }

    /**
     * Q4 minBox
     * @param numberOfSwags
     * @return
     */
    public int minBox(int numberOfSwags) {
        int numberOfBox = 0;
        while (numberOfSwags > 1) {
            numberOfSwags = (int) (numberOfSwags - Math.pow((int)Math.sqrt(numberOfSwags), 2));
            numberOfBox++;
        }
        return numberOfBox + numberOfSwags;
    }

//    public void decode(String string, int i, int j ,StringBuilder sb, List<String> result) {
//        if (index == string.length()) {
//            result.add(new String(sb.toString()));
//            return;
//        }
//        decode(string, i + 1, j + 1 sb.append(helper(string.substring(i , j + 1))));
//        decode(string, i + 1, j + 2, sb.append(helper(string.substring(i , j + 1))));
//
//
//        sb.deleteCharAt(sb.length() - 1);
//    }
//
//    public String helper(int charCode) {
//        char c = (char)(charCode + '0');
//        return new String(c.Character.toString(c));
//    }


}
