package BST;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Junyoung Jang 902860455
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data "
                   + "cannot be turned into BST."); 
        }
        for (T each : data) {
            if (each == null) {
                throw new IllegalArgumentException("Null data "
                        + "cannot be an input to BST.");
            }
            add(each);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data "
                    + "cannot be an input to BST.");
        }
        if (size == 0) {
            root = new BSTNode(data);
            size++;
        } else {
            add(root, data);
        }
    }
    /**
     * Recursive helper method for add a new data
     * @param now the node in this BST to be compared with data
     * in this recursion.
     * @param data the data that will be add to this BST.
     */
    private void add(BSTNode<T> now, T data) {
        if (data.compareTo(now.getData()) > 0) {
            if (now.getRight() == null) {
                now.setRight(new BSTNode(data));
                size++;
            } else {
                add(now.getRight(), data);
            }
        } else if (data.compareTo(now.getData()) < 0) {
            if (now.getLeft() == null) {
                now.setLeft(new BSTNode(data));
                size++;
            } else {
                add(now.getLeft(), data);
            }
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data "
                    + "cannot be in BST.");
        }
        return remove(root, null, data, 0);
    }
    /**
     * Recursive helper method for removing an data from this BST.
     * @throws java.util.NoSuchElementException when the input data is not found
     * in this BST. 
     * @param now the node that is chosen now for comparing in this recursion.
     * @param parent the parent of currently chosen node.
     * @param data the data to be removed
     * @param po integer input that indicates
     * either node chosen now is left or right of its parent node
     * @return the data of a node in this BST that is equals to i data.
     */
    private T remove(BSTNode<T> now, BSTNode<T> parent, T data, int po) {
        if (now == null) {
            throw new java.util.NoSuchElementException("the "
                    + "input data is not in this BST");
        }
        boolean isroot = false;
        if (now.equals(root)) {
            isroot = true;
        }
        if (now.getData().equals(data)) {
            T out = now.getData();
            if (now.getRight() == null && now.getLeft() == null) {
                if (isroot) {
                    clear();
                    size = 0;
                    return out;
                }
                if (po == 1) {
                    parent.setRight(null);
                } else {
                    parent.setLeft(null);
                }
                size--;
                return out;
            }
            if (now.getRight() == null) {
                if (isroot) {
                    root = now.getLeft();
                    size--;
                    return out;
                }
                if (po == 1) {
                    parent.setRight(now.getLeft());
                } else {
                    parent.setLeft(now.getLeft());
                }
                size--;
                return out;
            }
            if (now.getLeft() == null) {
                if (isroot) {
                    root = now.getLeft();
                    size--;
                    return out;
                }
                if (po == 1) {
                    parent.setRight(now.getRight());
                } else {
                    parent.setLeft(now.getRight());
                }
                size--;
                return out;
            }
            now.setData(successor(now.getRight(), now, 1));
            return out;
        }
        return now.getData().compareTo(data) > 0 
                ? remove(now.getLeft(), now, data, -1) 
                : remove(now.getRight(), now, data, 1);
    }
    
    /**
     * Recursive helper method to find and to remove the successor 
     * to replace the node that will be removed from this BST.
     * @param now the node that is currently chosen in this BST.
     * @param parent the parent of the chosen now node
     * @param fromparent indicates whether now node is left of its parent 
     * or not. 1 for right, -1 for left. 
     * @return the data that will be replacing the data of node that will be
     * removed.
     */
    private T successor(BSTNode<T> now, BSTNode<T> parent, int fromparent) {
        if (now.getLeft() == null) {
            T out = now.getData();
            return fromparent == 1 ? remove(now, parent, out, 1) 
                    : remove(now, parent, out, -1);
        }
        return successor(now.getLeft(), now, -1);
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data "
                    + "cannot be in BST.");
        }
        return get(root, data);
    }
    
    /**
     * Recursive helper method to return data of a node in this BST when 
     * the data is equal to given input data.
     * @throws java.util.NoSuchElementException when the input data is not found
     * in this BST.
     * @param now the node that is currently chosen to check if it contains
     * same data to the given input.
     * @param data the data that this function will look for.
     * @return the data that has been stored in the node which contains a data
     * that is equals to given input data.
     */
    private T get(BSTNode<T> now, T data) {
        if (now == null) {
            throw new java.util.NoSuchElementException("the "
                    + "input data is not in this BST");
        }
        if (now.getData().compareTo(data) == 0) {
            return now.getData();
        } else if ((now.getData().compareTo(data) > 0)) {
            return get(now.getLeft(), data);
        } else {
            return get(now.getRight(), data);
        }
    }
    
    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Null data "
                    + "cannot be in this BST.");
        }
        return contains(root, data);
    }
    
    /**
     * recursive helper method that iterates nodes to check and see if
     * this BST contains given data value.
     * @param now the node that is currently chosen to compare 
     * with given input data. 
     * @param data the data that this method will look for.
     * @return boolean value on whether this BST contains given input data.
     */
    private boolean contains(BSTNode<T> now, T data) {
        if (now.getData().equals(data)) {
            return true;
        } else {
            if (data.compareTo(now.getData()) > 0) {
                if (now.getRight() == null) {
                    return false;
                }
                return contains(now.getRight(), data);
            } else {
                if (now.getLeft() == null) {
                    return false;
                }
                return contains(now.getLeft(), data);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        return preorder(root, new ArrayList<T>());
    }
    
    /**
     * Recursive helper method that will add each data in preorder traversal.
     * @param now the node currently chosen to be added to output list.
     * @param col the collection of the data to be added to in order of
     * preorder traversal.
     * @return list of data in order of preorder traversal.
     */
    private List<T> preorder(BSTNode<T> now, List<T> col) {
        if (now != null) {
            col.add(now.getData());
            col = preorder(now.getLeft(), col);
            col = preorder(now.getRight(), col);
        }
        return col;
    }

    @Override
    public List<T> postorder() {
        return postorder(root, new ArrayList<T>());
    }

    /**
     * Recursive helper method that will add each data in postorder traversal.
     * @param now the node currently chosen to be added to output list.
     * @param col the collection of the data to be added to in order of
     * postorder traversal.
     * @return list of data in order of postorder traversal.
     */
    private List<T> postorder(BSTNode<T> now, List<T> col) {
        if (now != null) {
            col = postorder(now.getLeft(), col);
            col = postorder(now.getRight(), col);
            col.add(now.getData());
        }
        return col;
    }

    @Override
    public List<T> inorder() {
        return inorder(root, new ArrayList<T>());
    }
    
    /**
     * Recursive helper method that will add each data in inorder traversal.
     * @param now the node currently chosen to be added to output list.
     * @param col the collection of the data to be added to in order of
     * inorder traversal.
     * @return list of data in order of inorder traversal.
     */
    private List<T> inorder(BSTNode<T> now, List<T> col) {
        if (now != null) {
            col = inorder(now.getLeft(), col);
            col.add(now.getData());
            col = inorder(now.getRight(), col);
        }
        return col;
    }

    @Override
    public List<T> levelorder() {
        Queue<BSTNode<T>> od = new LinkedList<>();
        ArrayList<T> out = new ArrayList<>();
        if (root == null) {
            return out; 
        }
        od.add(root);
        while (!od.isEmpty()) {
            BSTNode<T> now = od.poll();
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
        return height(root);
    }
    
    /**
     * Recursive helper method to find height of this BST.
     * @param now the node that is currently chosen in this BST.
     * @return the bigger height between two children + 1 to return
     * the height of current node. 
     */
    private int height(BSTNode<T> now) {
        if (now == null) {
            return -1;
        }
        int rh = height(now.getRight());
        int lh = height(now.getLeft());
        return rh > lh ? rh + 1 : lh + 1;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}