public class BSTRotation<T extends Comparable<T>> extends BinarySearchTree_Placeholder<T> {
    
    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a right
     * child of the provided parent, this method will perform a left rotation.
     *
     * @param child is the node being rotated from child to parent position 
     * @param parent is the node being rotated from parent to child position
     */
    protected void rotate(BinaryNode<T> child, BinaryNode<T> parent) {
        // TODO: Implement this method.

        // null check for parent and child
        if (child == null || parent == null) {
            throw new IllegalArgumentException("Can't have null nodes");
            return; // do nothing get out of this method 
        }

        // checks if child is even the is the child of parent here 
        if (parent.downRight() != child || parent.downLeft() != child) {
            return; // the right and left children must equal the child 
        }

        // right child, left child, parent check 

        if (child.isRightChild()) {
            // child is right child 
            // left rotate 
            child.setLeft(parent); 
            parent.setUp(child);


        } else {
            // child is left child 
            // right rotate 
            child.setRight(parent); 
            parent.setUp(child); 
            
        }
    }



} 