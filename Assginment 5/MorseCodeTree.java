import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	private TreeNode<String> root;
	
	public MorseCodeTree() {
		buildTree();
	}
	
	public void buildTree() {
		root = new TreeNode<String>("");
		
		insert(".", "e");
		insert("-", "t");
		
		insert(".-", "a");
		insert("..", "i");
		insert("--", "m");
		insert("-.", "n");
		
		insert("-..", "d");
		insert("--.", "g");
		insert("-.-", "k");
		insert("---", "o");
		insert(".-.", "r");
		insert("...", "s");
		insert("..-", "u");
		insert(".--", "w");
		
		insert("-...", "b");
		insert("-.-.", "c");
		insert("..-.", "f");
		insert("....", "h");
		insert(".---", "j");
		insert(".-..", "l");
		insert(".--.", "p");
		insert("--.-", "q");
		insert("...-", "v");
		insert("-..-", "x");
		insert("-.--", "y");
		insert("--..", "z");
	}
	
	public TreeNode<String> getRoot() {
		return root;
	}
	
	public MorseCodeTree insert(String string, String string2) {
		addNode(root,string,string2);
		return this;
	}
	
	public void setRoot(TreeNode<String> root) {
		this.root = new TreeNode<String> (root);
	}
	
	public void addNode(TreeNode<String> root, String code, String letter) {
		if(code.length() == 1) {
			if(code.charAt(0) == '.') {
				root.left = new TreeNode<String> (letter);
			}else if (code.charAt(0) == '-') {
				root.right = new TreeNode<String> (letter);
			}
		}else if(code.charAt(0) == '.') {
			addNode(root.left,code.substring(1),letter);
		}else if(code.charAt(0) == '-') {
			addNode(root.right,code.substring(1),letter);
		}
	}
	
	public MorseCodeTree delete(String data) {
		throw new UnsupportedOperationException();
	}
	
	public String fetch(String code) {
		return fetchNode(root,code);
	}
	
	public String fetchNode(TreeNode<String> root, String code) {
		if(code.length() == 1) {
			if(code.charAt(0) == '.') {
				return root.left.data;
			}else if(code.charAt(0) == '-') {
				return root.right.data;
			}
		}else if(code.charAt(0) == '.') {
			return fetchNode(root.left,code.substring(1));
		}else if(code.charAt(0) == '-') {
			return fetchNode(root.right,code.substring(1));
		}
		return null;
	}
	
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root,list);
		return list;
	}
	
	public MorseCodeTree update() {
		throw new UnsupportedOperationException();
	}
	
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list){
		if(root == null) {
			
		}if(root.left != null) {
			LNRoutputTraversal(root.left,list);
		}
		list.add(root.data);
		if (root.right != null) {
			LNRoutputTraversal(root.right,list);
		}
	}
}
