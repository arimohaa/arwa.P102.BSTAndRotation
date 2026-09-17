/*
 * Author: Arwa Mohamud
 * Email: mohamud4@wisc.edu
 * Course: CS400, Fall 2026
 * Assignment: P102.BSTRotation
 */

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

        // null check for parent and child
        if (child == null || parent == null) {
            throw new IllegalArgumentException("Can't have null nodes");
            // do nothing get out of this method 
        }

        // checks if child is even the is the child of parent here 
        if (parent.downRight() != child && parent.downLeft() != child) {
            return; // the right and left children must equal the child 
        }

        // right child, left child, parent check 

        BinaryNode<T> grandParent = parent.up(); 

        if (child.isRightChild()) {
            // LEFT ROTATE
            BinaryNode<T> movedSubtree = child.downLeft(); // subtree that gets moved from original spot in rotation

            // turn the moveable subtree to the right child of the parent
            parent.setRight(movedSubtree);
            if (movedSubtree != null) {
                movedSubtree.setUp(parent); 
            }

            // parent becomes left child of the child 
            child.setLeft(parent); 
            parent.setUp(child); // swap 

            // updating grandparent 
            child.setUp(grandParent); 
            if (grandParent == null) {
                this.root = child; 
            } else if (grandParent.downLeft() == parent) {
                // if parent was left child of grandparent
                grandParent.setLeft(child); 
            } else {
                // if parent was right child of grandparent 
                grandParent.setRight(child); 
            }

        } else {
            
            // RIGHT ROTATE
            BinaryNode<T> movedSubtree = child.downRight(); // subtree that gets moved from original spot in rotation

            // turn the moveable subtree to the left child of the parent
            parent.setLeft(movedSubtree);
            if (movedSubtree != null) {
                movedSubtree.setUp(parent); 
            }

            // parent becomes right  child of the child 
            child.setRight(parent); 
            parent.setUp(child); // swap 

            // update grandparent
            child.setUp(grandParent); 
            if (grandParent == null) {
                this.root = child; 
            } else if (grandParent.downLeft() == parent) {
                // if parent was left child of grandparent
                grandParent.setLeft(child); 
            } else {
                // if parent was right child of grandparent 
                grandParent.setRight(child); 
            }
        }
    }


    // --- TESTER METHODS ---

    /**
     * Tester method for a basic right rotation. 
     * @return true if all tests pass!
     */
    public boolean test1() {
        // Build: 30, 20, 10 
        BinaryNode<Integer> num30 = new BinaryNode<>(30);
        BinaryNode<Integer> num20 = new BinaryNode<>(20);
        BinaryNode<Integer> num10 = new BinaryNode<>(10);

        num30.setLeft(num20); 
        num20.setLeft(num10); 
        num10.setUp(num20); 
        num20.setUp(num30);

        this.root = num30; 

        // right rotate 20 and 30
        rotate(num20, num30); 

        // shape tests 
        if (this.root != num20) {
            return false; 
        } 
        if (num20.up() != null) {
            return false; 
        }
        if (num20.downRight() != num30) {
            return false; 
        }
        if (num20.downLeft() != num10) {
            return false; 
        }
        if (num10.up() != num20) {
            return false; 
        }
        if (num30.up() != num20) {
            return false; 
        }

        return true; 
    }

    /**
     * Tester method for a basic left rotation. 
     * @return true if all tests pass!
     */
    public boolean test2() {
        //build: 30, 40, 50 
        BinaryNode<Integer> num30 = new BinaryNode<>(30); 
        BinaryNode<Integer> num40 = new BinaryNode<>(40); 
        BinaryNode<Integer> num50 = new BinaryNode<>(50); 

        num30.setRight(num40); 
        num40.setRight(num50); 
        num50.setUp(num40); 
        num40.setUp(num30);

        this.root = num30; 

        // left rotate 40 and 30
        rotate(num40, num30); 

        // shape tests 
        if (this.root != num40) {
            return false; 
        } 
        if (num40.up() != null) {
            return false; 
        }
        if (num40.downRight() != num50) {
            return false; 
        }
        if (num40.downLeft() != num30) {
            return false; 
        }
        if (num30.up() != num40) {
            return false; 
        }
        if (num50.up() != num40) {
            return false; 
        }

        return true; 
        
    }

    /**
     * Tester method for more rotations that include non root node rotations. 
     * Rotations on parent-child pairs for nodes that have 0, 1, 2, 3 shared children. 
     * @return true if all tests pass!
     */
    public boolean test3() {

        //building the wonky tree 1
        BinaryNode<Integer> num50 = new BinaryNode<>(50); 
        BinaryNode<Integer> num40 = new BinaryNode<>(40); 
        BinaryNode<Integer> num30 = new BinaryNode<>(30); 
        BinaryNode<Integer> num35 = new BinaryNode<>(35); 
        BinaryNode<Integer> num45 = new BinaryNode<>(45); 

        num50.setLeft(num30); 
        num30.setRight(num40); 
        num40.setLeft(num35);
        num40.setRight(num45); 
        num35.setUp(num40); 
        num45.setUp(num40); 
        num40.setUp(num30); 
        num30.setUp(num50); 

        this.root = num50; 

        rotate(num40, num30); 

        if (num50.downLeft() != num40) {
            return false; 
        }
        if (num40.up() != num50) {
            return false; 
        }
        if (num40.downLeft() != num30){
            return false; 
        }
        if (num40.downRight() != num45) {
            return false; 
        }
        if (num30.up() != num40) {
            return false; 
        }
        if (num30.downRight() != num35) {
            return false;
        }
        if (num35.up() != num30) {
            return false; 
        }
        if (num45.up() != num40) {
            return false; 
        }
        if (this.root != num50) {
            return false; 
        }

        // wonky tree 2: 
        BinaryNode<Integer> num5 = new BinaryNode<>(5);
        BinaryNode<Integer> num7 = new BinaryNode<>(7);
        BinaryNode<Integer> num6 = new BinaryNode<>(6);
        BinaryNode<Integer> num4 = new BinaryNode<>(4);

        num5.setRight(num7); 
        num7.setLeft(num6); 
        num6.setLeft(num4); 
        num4.setUp(num6); 
        num6.setUp(num7); 
        num7.setUp(num5); 

        this.root = num5; 

        rotate(num6, num7); 

        if (num5.downRight() != num6) {
            return false; 
        }
        if (num6.up() != num5) {
            return false; 
        }
        if (num6.downRight() != num7) {
            return false; 
        }
        if (num6.downLeft() != num4) {
            return false; 
        }
        if (num7.up() != num6) {
            return false;
        }
        if (num4.up() != num6) {
            return false; 
        }
        if (this.root != num5) {
            return false; 
        }

        //wonky tree 3: with 3 shared children right rotation
        BinaryNode<Integer> num800 = new BinaryNode<>(800); 
        BinaryNode<Integer> num600 = new BinaryNode<>(600); 
        BinaryNode<Integer> num400 = new BinaryNode<>(400); 
        BinaryNode<Integer> num300 = new BinaryNode<>(300); 
        BinaryNode<Integer> num500 = new BinaryNode<>(500); 
        BinaryNode<Integer> num700 = new BinaryNode<>(700); 
        BinaryNode<Integer> num900 = new BinaryNode<>(900); 

        num800.setRight(num900); 
        num800.setLeft(num600); 
        num600.setRight(num700);
        num600.setLeft(num400); 
        num400.setRight(num500); 
        num400.setLeft(num300); 
        num300.setUp(num400); 
        num500.setUp(num400); 
        num400.setUp(num600); 
        num700.setUp(num600); 
        num600.setUp(num800); 
        num900.setUp(num800);

        this.root = num800; 

        rotate(num600, num800);

        if (num600.downRight() != num800) {
            return false; 
        }
        if (num600.downLeft() != num400) {
            return false; 
        }
        if (num800.downRight() != num900) {
            return false; 
        }
        if (num800.downLeft() != num700) {
            return false; 
        }
        if (num400.downRight() != num500) {
            return false; 
        }
        if (num400.downLeft() != num300) {
            return false; 
        }
        if (this.root != num600) {
            return false; 
        }

        // 0 shared children test: 
        BinaryNode<Integer> parent = new BinaryNode<>(10); 
        BinaryNode<Integer> child = new BinaryNode<>(5); 

        parent.setLeft(child); 
        child.setUp(parent); 

        this.root = parent; 

        rotate(child, parent); 

        if (this.root != child) {
            return false; 
        }
        if (child.up() != null) {
            return false; 
        }
        if (child.downLeft() != null) {
            return false; 
        }
        if (child.downRight() != parent) {
            return false; 
        }
        if (parent.up() != child) {
            return false; 
        }
        if (parent.downLeft() != null) {
            return false;
        }
        if (parent.downRight() != null) {
            return false; 
        }
        
        return true; 


    }

} 