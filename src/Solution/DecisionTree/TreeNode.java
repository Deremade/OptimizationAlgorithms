package Solution.DecisionTree;

public interface TreeNode {
	
	boolean conditionMet();
	
	default boolean isLeaf() {
		return getLeft() == null;
	}
	
	default boolean isRoot() {
		return getParent() == null;
	}
	
	TreeNode getParent();
	
	/**
	 * @return the TreeNode if the condition is false
	 */
	TreeNode getLeft();
	
	/**
	 * @return the TreeNode if the condition is true
	 */
	TreeNode getRight();
	
	void setParent(TreeNode node);
	
	default void setLeft(TreeNode node) {
		node.setParent(this);
		this.putLeft(this);
	}
	
	default void setRight(TreeNode node) {
		node.setParent(this);
		this.putRight(this);
	}
	
	void putLeft(TreeNode node);
	
	void putRight(TreeNode node);
	
	/**
	 * @return the Node the condition implies
	 */
	default TreeNode testCondition() {
		return getChild(conditionMet());
	}
	
	/**
	 * @param b
	 * @return the result of the condition being b
	 */
	default TreeNode getChild(boolean b) {
		if(b)
			return getRight();
		else
			return getLeft();
	}
}
