// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    public void printTree( )
    {
        if(isEmpty( ))
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    public int nodeCount( ){
        if(isEmpty( ))
            return 0;
        else
            return nodeCount(root);

    }
    int count = 0;

    public boolean isFull( )
    {
        return isFull(root);
    }

    public boolean compareStructure(BinarySearchTree<AnyType> s){
        return compareStructure(root, s.root);
    }
    public boolean equals(BinarySearchTree<AnyType> s){
        return equals(root, s.root);
    }

    public BinarySearchTree<AnyType> copy(){
        BinarySearchTree<AnyType> s = new BinarySearchTree<AnyType>(); 
        s.root = copy(root);
        return s;
    }
    
    public BinarySearchTree<AnyType> mirror(){
        BinarySearchTree<AnyType> s = new BinarySearchTree<AnyType>(); 
        s.root = mirror(root);
        return s;
    }

    public boolean isMirror(BinarySearchTree<AnyType> s){
        return isMirror(root, s.root);
    }
    public BinarySearchTree<AnyType> rotateRight(){
        BinarySearchTree<AnyType> s = new BinarySearchTree<AnyType>();
        s.root = rotateRight(root);
        return s;
    }
    public BinarySearchTree<AnyType> rotateLeft(){
        BinarySearchTree<AnyType> s = new BinarySearchTree<AnyType>();
        s.root = rotateLeft(root);
        return s;
    }

    public void printLevels() { 
        int h = height(root); 
        int i; 
        for (i = 1; i <= h + 1; i++) { 
            printLevels(root, i); 
            System.out.println(); 
        } 
    } 

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }
    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }

    
        /**
     * Internal method that recursively traverses the tree and returns the count of nodes
     * @param t the node that roots the subtree.
     */
    private int nodeCount(BinaryNode<AnyType> t){
        if( t != null )
        {
            count++;
            nodeCount(t.left);
            nodeCount(t.right);
        }
        return count;
    }

            /**
     * Internal method that recursively traverses the tree and returns the count of nodes
     * @param t the node that roots the subtree.
     */
    private boolean isFull(BinaryNode<AnyType> t){
        { 
            // if empty tree 
            if(t == null) 
            return true; 
               
            // if leaf node 
            if(t.left == null && t.right == null ) 
                return true; 
               
            // if both left and right subtrees are not null 
            // the are full 
            if((t.left != null) && (t.right != null)) 
                return (isFull(t.left) && isFull(t.right)); 
               
            // if none work 
            return false; 
        } 
    }
    private boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> s){
        // if empty node
        if(t == null && s == null) 
            return true; 
        // if leaf node    
        if(t.left == null && t.right == null && s.left == null && s.right == null ) 
            return true;
        // if t or s either have children such that both exist on either side   
        if((t.left != null && s.left != null) || (t.right != null && s.right != null)){
            return compareStructure(t.left, s.left) && compareStructure(t.right, s.right);
        }
        return false;
    }

    private boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> s){
        // if empty node
        if(t == null && s == null) 
            return true; 
        // if leaf node    
        if(t.left == null && t.right == null && s.left == null && s.right == null && t.element.equals(s.element)){
            return true;
        }
        // if t or s either have children such that both exist on either side   
        if(((t.left != null && s.left != null) || (t.right != null && s.right != null)) && t.element.equals(s.element)){
            return equals(t.left, s.left) && equals(t.right, s.right);
        }
        return false;
    }
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t){
        if(t == null){
            return null;
        }
            BinaryNode<AnyType> node = new BinaryNode<AnyType>(t.element, copy(t.left), copy(t. right));
            return node;
        
    }
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t){
        if(t == null){
            return null;
        }
            BinaryNode<AnyType> node = new BinaryNode<AnyType>(t.element, mirror(t.right), mirror(t. left));
            return node;
        
    }
    private boolean isMirror(BinaryNode<AnyType> t, BinaryNode<AnyType> s){
        if(s == null && t == null){
            return true;
        }
        if(t.left == null && t.right == null && s.left == null && s.right == null && t.element.equals(s.element)){
            return true;
        }
        if(t.element.equals(s.element)){
            return isMirror(t.left, s.right) && isMirror(t.right, s.left);
        }
        return false;
    }

  

    /**
     * Rotates right around the given node.
     */
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t){
        BinaryNode<AnyType> s = t.left; 
        BinaryNode<AnyType> temp = s.right;   
        // Perform rotation 
        s.right = t; 
        t.left = temp; 
        // Return new root
        return s;  
    } 


    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t){
        BinaryNode<AnyType> s = t.right; 
        BinaryNode<AnyType> T2 = s.left; 
        // Perform rotation 
        s.left = t; 
        t.right = T2; 
  
        // Return new root 
        return s; 
    } 
/* Print nodes at a given level */
private void printLevels(BinaryNode<AnyType> root, int level) 
{ 
    if (root == null) 
        return; 
    if (level == 1) 
        System.out.println(root.element); 
    else if (level > 1) 
    { 
        printLevels(root.left, level - 1); 
        printLevels(root.right, level - 1); 
    } 
}



    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    

      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        BinarySearchTree<Integer> s = new BinarySearchTree<>( );
        t.insert(7);
        t.insert(5);
        t.insert(3);

        s.insert(5);
        s.insert(3);
        s.insert(7);

        System.out.print("nodecount: ");
        System.out.print(t.nodeCount());
        System.out.println();
        System.out.print("isFull: ");
        System.out.print(t.isFull());
        System.out.println();
        System.out.print("compareStructure: ");
        System.out.print(t.compareStructure(s));
        System.out.println();
        System.out.print("equals: ");
        System.out.print(t.equals(s));
        System.out.println();
        System.out.print("copy: ");
        System.out.print(t.equals(t.copy()));
        System.out.print(" (t has same values as its copy), ");
        System.out.print(t == t.copy());
        System.out.println(" (but is a copy as they are not equal by reference)");
        System.out.print("checking if mirror isMirror: ");
        System.out.println(t.isMirror(t.mirror()));
        System.out.println("performs a level-by-level printing of tree: ");
        t.printLevels();
        System.out.println("Now showing the right rotation:");
        t.rotateRight().printLevels();
        System.out.println("performs a level-by-level printing of tree: ");
        s.printLevels();
        System.out.println("Now showing the left rotation:");
        s.rotateLeft().printLevels();


    }
}