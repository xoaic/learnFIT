package Example2;

/**
 * Class ArrayBinaryTree implements Binary Tree data structure using an array. 
 * Binary Tree's nodes are indexed using the numbering scheme introduced in Lecture 09.
 * Node has a string label that is stored in the l array.
 * Please refers to Lecture 09 and week 09 tutorial instruction for more detail on Binary Tree's ADT.
 * @author (nxthang) 
 * @version (1.0)
 */
import java.util.Arrays;
public class ArrayBinaryTree
{
    private static final int maxSize=100; //Maximum possible number of nodes in the tree
    private String[] l;
    
    /**
     * Constructor for objects of class ArrayBinaryTree
     * Create an empty Binary Tree
     */
    public ArrayBinaryTree()
    {
        l = new String[maxSize];
        Arrays.fill(l,null);
    }

    public void addRoot(String label)
    {
        if (l[0]==null)
        {
            l[0]=label;     
        }
    }
    
    public int getLeftChild(int node)
    {
        return 2*node+1;
    }
    
    public int getRightChild(int node)
    {
        return 2*node+2;
    }
    
    public int getParent(int node)
    {
        return (int)Math.floor((node-1)/2);
    }
    
    public String getNodeLabel(int node)
    {
        return l[node];
    }
    
    public void setNodeLabel(String label, int node)
    {
        l[node]=label;
    }
    
    public void addLeftChild(String label, int node)
    {
        setNodeLabel(label,getLeftChild(node));
    }

    public void addRightChild(String label, int node)
    {
    	
        setNodeLabel(label,getRightChild(node));
    }
    
    public void preOrderTravel(int node)
    {
        if (l[node] != null)
        {
            System.out.print(l[node] + " ");
            preOrderTravel(getLeftChild(node));
            preOrderTravel(getRightChild(node));
        }
    }
    
    public boolean isEmpty()
    {
        return (l[0]==null);
    }
}