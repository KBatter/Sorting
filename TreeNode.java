public class TreeNode<T> {
    T item;
    TreeNode<T> rightChild;
    TreeNode<T> leftChild;

    public TreeNode(T item) {
        this.item = item;
        leftChild = rightChild = null;
    }

    public TreeNode(T item, TreeNode<T> rightChild, TreeNode<T> leftChild) {
        this.item = item;
        this.rightChild = rightChild;
        this.leftChild = leftChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void setLeftChild(TreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public String toString() {
        return item.toString();
    }
}
