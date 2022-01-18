package Solution.DecisionTree;

public abstract class DecisionNode implements TreeNode {
	TreeNode parent;
	TreeNode right;
	TreeNode left;

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public TreeNode getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public TreeNode getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public void setParent(TreeNode node) {
		// TODO Auto-generated method stub
		parent = node;
	}

	@Override
	public void putLeft(TreeNode node) {
		// TODO Auto-generated method stub
		left = node;
	}

	@Override
	public void putRight(TreeNode node) {
		// TODO Auto-generated method stub
		right = node;
	}
	
	public String toString() {
		return describe()+"[ "+getLeft().toString()+" , "+getRight().toString()+" ]";
	}
	
	public abstract String describe();

}
