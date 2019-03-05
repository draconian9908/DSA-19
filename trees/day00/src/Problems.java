import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        return new BinarySearchTree<>();
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if (n1.leftChild == null && n2.leftChild == null && n1.rightChild == null && n2.rightChild == null) {
            return true;
        }
        if (n1.rightChild.key == n2.rightChild.key && n1.leftChild.key == n2.leftChild.key) {
            isIsomorphic(n1.rightChild,n2.rightChild);
            isIsomorphic(n1.leftChild,n2.leftChild);
        }
        else if (n1.rightChild.key == n2.leftChild.key && n1.leftChild.key == n2.rightChild.key) {
            isIsomorphic(n1.rightChild,n2.leftChild);
            isIsomorphic(n1.leftChild,n2.rightChild);
        }
        else {
            return false;
        }
        return true;
    }
}
