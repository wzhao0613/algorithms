import java.util.LinkedList;

/*
 * Given  the root and two nodes in a binary tree,  find the lowest common ancestor(LCA) of the two nodes
 * for example:
 *         4
 *        / \
 *       3   7
 *      /   / \ 
 *     2   5   6
 * LCA(3,5) = 4
 * LCA(5,6) = 7
 * LCA(6,7) = 7
 */
public class LowestCommonAncestor {

    public class TreeNode<V>{
        private V value;
        public TreeNode<V> left;
        public TreeNode<V> right;

        public TreeNode(V val) {
            this.value = val;
            this.left = null;
            this.right = null;
        }
        
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    
    public class ListNode <V>{
    	private V value;
    	public ListNode<V> next;
    	public ListNode(V val) {
    		this.value = val;
    		this.next = null;
    	}
    	public V getValue() {
    		return this.value;
    	}
    	public void setValue(V value) {
    		this.value = value;
    	}
    }
    
    public TreeNode<Integer> createTree() {
        TreeNode<Integer> root = new TreeNode<Integer>(4);
        root.left = new TreeNode<Integer>(3);
        root.left.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(7);
        root.right.left = new TreeNode<Integer>(5);
        root.right.right = new TreeNode<Integer>(6);
        
        return root;
    }
    
    public Boolean FindPath(TreeNode<Integer> root, TreeNode<Integer> p, LinkedList<TreeNode<Integer>> pathList){
    	if (null == root || null == p) {
    		return false;
    	}
    	
    	pathList.addLast(root);
    	if(root == p) {
    		return true;
    	}
    	Boolean bFound = false;
    	if (!(bFound = FindPath(root.left, p,  pathList))) {
    		if (!(bFound = FindPath(root.right, p,  pathList))) {
    			pathList.removeLast();
    		}   		
    	}

    	return bFound;
    }
    

	public TreeNode<Integer> findLowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode <Integer> q){
		//idea is to travel the tree from root to p and from root to q, then create two list root->p and root->q
		//then find the last cross point
		LinkedList <TreeNode<Integer>> path1 = new LinkedList<TreeNode<Integer>>();
		LinkedList <TreeNode<Integer>> path2 = new LinkedList<TreeNode<Integer>>();
		if(! FindPath(root, p, path1))
			return null;
		
		if(! FindPath(root, q, path2))
			return null;
		
		TreeNode<Integer> prev = null;
		int len = path1.size() > path2.size()?path2.size():path1.size();
		
		for(int index=0; index < len; index++ ) {
			if(path1.get(index) == path2.get(index)) {
				prev = path1.get(index);
			}else {
				//the last common node is their lowest ancestor
				break;
			}
		}
		
		return prev;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LowestCommonAncestor instance = new LowestCommonAncestor();
		TreeNode<Integer> root = instance.createTree();
		//test case 1
		TreeNode<Integer> res = instance.findLowestCommonAncestor(root, root.left, root.right.left);
		System.out.println("lowest commn ancestor is:" + res.getValue());
	}
}
