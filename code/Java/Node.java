public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

public static void main(String[] args) {

    Node myNode = new Node(5);
    myNode.findNode(myNode, 5);

}



            
public Node findNode(Node root, int targetValue) {
    if (root == null) {
        return null; // value not present in the tree
    }
    if (root.value == targetValue) {
        return root; // found the node containing the specific value
    }
    if (targetValue < root.value) {
        return findNode(root.left, targetValue); // search left subtree
    } else {
        return findNode(root.right, targetValue); // search right subtree
    }
}

}

