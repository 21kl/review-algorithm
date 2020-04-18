package tree;

/**
 * 树的一个节点
 */
public class BitreeNode {
    public String val;
    public BitreeNode left;
    public BitreeNode right;
    public BitreeNode(){}
    public BitreeNode(String val){
        this.val = val;
    }
    //构造一个新节点
    public BitreeNode(BitreeNode left, BitreeNode right, String val) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
