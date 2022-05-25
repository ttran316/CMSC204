
public class TreeNode<T> {
	public T data;
	public TreeNode<T> left, right;
	
	public TreeNode(T dataNode) {
		data = dataNode;
		left = right = null;
	}
	
	public TreeNode(TreeNode<T> node) {
		data = node.data;
		left = node.left;
		right = node.right;
	}
	
	public T getData() {
		return data;
	}
}
