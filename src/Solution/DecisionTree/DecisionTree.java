package Solution.DecisionTree;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public interface DecisionTree extends Collection<TreeNode> {

	TreeNode getRoot();

	public default HashMap<TreeNode, Boolean> pathTo(TreeNode node) {
		//If the node is not in the list, the path does not exist
		if(!this.contains(node)) return null;
		HashMap<TreeNode, Boolean> path = new HashMap<TreeNode, Boolean>();
		//While not at the root
		while(node != getRoot()) {
			//Place the node and how to get to it from it's parent
			path.put(node, node.getParent().getRight() == node);
			node = node.getParent();
		}
		return path;
	}
	
	/**
	 * @param node
	 * @return a string representing how to get to that node
	 */
	public default String binPath(TreeNode node) { 
		String binPath = "";
		for(Entry<TreeNode, Boolean> entry : pathTo(node).entrySet()) {
		    Boolean value = entry.getValue();
		    //if you get there by the condition being true, place a 1, otherwise a 0
		    if(value) binPath += "1";
		    else binPath += "0";
		}
		return binPath;
	}
	
	/**
	 * @param binCode
	 * List of which nodes to take to get to a node
	 * @return the node
	 */
	public default TreeNode getAtBinCode(List<Boolean> binCode) {
		TreeNode curr = getRoot();
		//Follow the path to the Node
		for(Boolean condition : binCode)
			curr = curr.getChild(condition);
		return curr;
	}
	
	/**
	 * @param binCode
	 * String representing how to get to the node
	 * @return the node
	 */
	public default TreeNode getAtBinCode(String binCode) {
		List<Boolean> code = new LinkedList<Boolean>();
		//Populate the path
		for(int i = binCode.length(); i > 0; i--)
			code.add(binCode.charAt(i) == '1');
		return getAtBinCode(code);
	}
	
	/**
	 * @return The resulting leaf node
	 */
	public default TreeNode runFromRoot() {
		TreeNode curr = getRoot();
		while(!curr.isLeaf())
			curr = curr.testCondition();
		return curr;
	}
}
