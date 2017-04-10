package AVL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * Your implementation of an AVL Tree.
 *
 * @author Junyoung Jang 902860455
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
        
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null data cannot be "
                    + "an input for this method");
        }
        for (T each : data) {
            add(each);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null data cannot be "
                    + "in this AVL");
        } else {
            root = add(data, root);
        }
    }
    
    /**
     * helper method that takes in a data and a node to check and add it in 
     * this AVL tree.
     * @param data the data that will be inserted in this tree
     * @param here the current location to compare with the data.
     * @return newly selected root in case of rebalancing the tree
     */
    private AVLNode<T> add(T data, AVLNode<T> here) {
        if (here == null) {
            here = new AVLNode(data);
            here.setHeight(0);
            here.setBalanceFactor(0);
            size++;
        } else if (here.getData().compareTo(data) > 0) {
            here.setLeft(add(data, here.getLeft()));
        } else if (here.getData().compareTo(data) < 0) {
            here.setRight(add(data, here.getRight()));
        }
        here = rebalance(here);
        return here;
    }
    
    /**
     * rebalancing the AVL Tree based on resetting height and balacefactor. 
     * @param here node where this method rebalance
     * @return root of rebalanced subtree
     */
    private AVLNode<T> rebalance(AVLNode<T> here) {
        int rebalL = -1;
        int rebalR = -1;
        if (here.getLeft() != null) {
            rebalL = here.getLeft().getHeight();
        }
        if (here.getRight() != null) {
            rebalR = here.getRight().getHeight();
        }
        int newBalance = rebalL - rebalR;
        here.setBalanceFactor(newBalance);
        here.setHeight(Math.max(rebalL, rebalR) + 1);
        if (newBalance > 1) {
            if (here.getLeft().getBalanceFactor() >= 0) {
                here = rotR(here);
            } else {
                here = rotLR(here);
            }
        } else if (newBalance < -1) {
            if (here.getRight().getBalanceFactor() <= 0) {
                here = rotL(here);
            } else {
                here = rotRL(here);
            }
        }
        return here;
    }
    
    /**
     * Right rotation method
     * @param here given root of the rotation.
     * @return root of the rotated subtree
     */
    private AVLNode<T> rotR(AVLNode here) {
        AVLNode<T> roothere = here.getLeft();
        here.setLeft(roothere.getRight());
        roothere.setRight(here);
        here = rebalance(here);
        roothere = rebalance(roothere);
        return roothere;
    }
    
    /**
     * Left rotation method
     * @param here given root of the rotation.
     * @return root of the rotated subtree
     */
    private AVLNode<T> rotL(AVLNode here) {
        AVLNode<T> roothere = here.getRight();
        here.setRight(roothere.getLeft());
        roothere.setLeft(here);
        here = rebalance(here);
        roothere = rebalance(roothere);
        return roothere;
        
    }
    
    /**
     * Right-Left rotation method
     * @param here given root of the rotation.
     * @return root of the rotated subtree
     */
    private AVLNode<T> rotRL(AVLNode here) {
        here.setRight(rotR(here.getRight()));
        return rotL(here);
    }
    
    /**
     * Left-Right rotation method
     * @param here given root of the rotation.
     * @return root of the rotated subtree
     */
    private AVLNode<T> rotLR(AVLNode here) {
        here.setLeft(rotL(here.getLeft()));
        return rotR(here);
    }    
    
    @Override
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null data cannot be "
                    + "in this AVL");
        }
        return remove(data, root, null);
    }
    
    /**
     * helper remove method that go down AVL Tree and check and see if 
     * this tree contains or not. If contains remove and return the 
     * data inside.
     * @param data the target data will be removed
     * @param here the node to check if this node has value equals to
     * the data
     * @param parent the parent node of here node
     * @throws java.util.NoSuchElementException when data is not found
     * @return the removed data in the node which 
     * contained same data as input data
     */
    private T remove(T data, AVLNode<T> here, AVLNode<T> parent) {
        if (here == null) {
            throw new java.util.NoSuchElementException("The input data "
                    + "is not in this AVL");
        }
        
        if (here.getData().equals(data)) {
            T out = here.getData();
            if (here.getLeft() == null && here.getRight() == null) {
                if (parent == null) {
                    clear();
                } else {
                    if (parent.getData().compareTo(here.getData()) > 0) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                    size--;
                }
            } else if (here.getLeft() != null && here.getRight() != null) {
                T replacement = getSuc(here.getRight(), here);
                here.setData(replacement);
                size--;
            } else {
                if (parent == null) {
                    root = (here.getLeft() == null 
                            ? here.getRight() : here.getLeft());
                } else {
                    if (parent.getData().compareTo(here.getData()) > 0) {
                        parent.setLeft((here.getLeft() == null 
                                ? here.getRight() : here.getLeft()));
                    } else {
                        parent.setRight((here.getLeft() == null 
                                ? here.getRight() : here.getLeft()));
                    }
                }
                size--;
            }
            return out;
        } else if (here.getData().compareTo(data) > 0) {
            T out = remove(data, here.getLeft(), here);
            if (parent != null) {
                if (parent.getData().compareTo(here.getData()) > 0) {
                    parent.setLeft(rebalance(here));
                } else {
                    parent.setRight(rebalance(here));
                }
            } else {
                root = rebalance(here);
            }
            return out;
        } else {
            T out = remove(data, here.getRight(), here);
            if (parent != null) {
                if (parent.getData().compareTo(here.getData()) > 0) {
                    parent.setLeft(rebalance(here));
                } else {
                    parent.setRight(rebalance(here));
                }
            } else {
                root = rebalance(here);
            }
            return out;
        }
    }
    
    /**
     * Successor finding method
     * @param here the node where search for successor starts
     * @param parent the parent node of here node
     * @return found successor to replace removed node.
     */
    private T getSuc(AVLNode<T> here, AVLNode<T> parent) {
        T out;
        if (here.getLeft() == null) {
            out = here.getData();
            if (here.getData().compareTo(parent.getData()) > 0) {
                parent.setRight(here.getRight());
            } else {
                parent.setLeft(here.getRight());
            }
        } else {
            out = getSuc(here.getLeft(), here);
            if (here.getData().compareTo(parent.getData()) > 0) {
                parent.setRight(rebalance(here));
            } else {
                parent.setLeft(rebalance(here));
            }
        }
        return out;
    }
    

    @Override
    public T get(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null data cannot be "
                    + "in this AVL");
        }
        AVLNode<T> here = root;
        while (here != null) {
            if (here.getData().compareTo(data) == 0) {
                return here.getData();
            } else if (here.getData().compareTo(data) < 0) {
                here = here.getRight();
            } else {
                here = here.getLeft();
            }
        }
        throw new java.util.NoSuchElementException("The input data "
                + "is not in this AVL");
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("null data cannot be "
                    + "in this AVL");
        }
        AVLNode<T> here = root;
        while (here != null) {
            if (here.getData().compareTo(data) == 0) {
                return true;
            } else if (here.getData().compareTo(data) < 0) {
                here = here.getRight();
            } else {
                here = here.getLeft();
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> out = new ArrayList<T>();
        out = preorder(root, out);
        return out;
    }
    /**
     * Recursive helper method that will add each data in preorder traversal.
     * @param now the node currently chosen to be added to output list.
     * @param col the collection of the data to be added to in order of
     * preorder traversal.
     * @return list of data in order of preorder traversal.
     */
    private List<T> preorder(AVLNode<T> now, List<T> col) {
        if (now != null) {
            col.add(now.getData());
            col = preorder(now.getLeft(), col);
            col = preorder(now.getRight(), col);
        }
        return col;
    }

    @Override
    public List<T> postorder() {
        List<T> out = new ArrayList<T>();
        out = postorder(root, out);
        return out;
    }
    
    /**
     * Recursive helper method that will add each data in postorder traversal.
     * @param now the node currently chosen to be added to output list.
     * @param col the collection of the data to be added to in order of
     * postorder traversal.
     * @return list of data in order of postorder traversal.
     */
    private List<T> postorder(AVLNode<T> now, List<T> col) {
        if (now != null) {
            col = postorder(now.getLeft(), col);
            col = postorder(now.getRight(), col);
            col.add(now.getData());
        }
        return col;
    }

    @Override
    public List<T> inorder() {
        List<T> out = new ArrayList<T>();
        out = inorder(root, out);
        return out;
    }
    
    /**
     * Recursive helper method that will add each data in inorder traversal.
     * @param now the node currently chosen to be added to output list.
     * @param col the collection of the data to be added to in order of
     * inorder traversal.
     * @return list of data in order of inorder traversal.
     */
    private List<T> inorder(AVLNode<T> now, List<T> col) {
        if (now != null) {
            col = inorder(now.getLeft(), col);
            col.add(now.getData());
            col = inorder(now.getRight(), col);
        }
        return col;
    }

    @Override
    public List<T> levelorder() {
        Queue<AVLNode<T>> od = new LinkedList<AVLNode<T>>();
        ArrayList<T> out = new ArrayList<T>();
        if (root == null) {
            return out; 
        }
        od.add(root);
        while (!od.isEmpty()) {
            AVLNode<T> now = od.poll();
            if (now.getLeft() != null) {
                od.add(now.getLeft()); 
            }
            if (now.getRight() != null) {
                od.add(now.getRight()); 
            }
            out.add(now.getData());
        }
        return out;
    }
    
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return root == null ? -1 : root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}

