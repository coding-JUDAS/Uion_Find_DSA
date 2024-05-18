import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnionFind implements IComponentInfo{
    // maintain a set of nodes
    private HashMap<String, Node> items;
    // keep track of the number of connected components
    private int numberOfComponents;
    private List<List<String>> cartesianProductResult;

    private class Node{
        private String id;
        private int componentSize;
        private Object cargo;
        private Node parent;
        private ArrayList<String> children;
        public Node(String id){
            cargo = id;
            this.id = id;
            parent = this;
            componentSize = 1;
            children = new ArrayList<>();
            children.add(id);
        }
        public Node(Object cargo){
            //TODO: add code to extend class
        }
        public String getId(){
            return id;
        }
        public Node parent(){
            return parent;
        }
        public void setComponentSize(int i){
            this.componentSize = i;
        }
        public void setParent(Node newParent){
            parent = newParent;
        }
        public void addChild(String child){
            if(children.contains(child)){
                return;
            }
            children.add(child);
        }

        public void removeChildren() {
            children.clear();
        }

        @Override
        public String toString(){
            StringBuilder sr = new StringBuilder();
            sr.append(this.id);
            sr.append(" ");
            if(!children.isEmpty()){
                sr.append(" = ");
                sr.append(children);
            }
            return sr.toString();
        }

        public void writeData(PrintWriter writer) {
            writer.println(this);
        }
    }
    public int getNumberOfComponents(){
        return numberOfComponents;
    }

    public UnionFind(){
        items = new HashMap<>();
        numberOfComponents = 0;
    }

    public void makeSet(String id){
        Node node = new Node(id);
        items.put(id, node);
        numberOfComponents++;

    }
    public void union(String x, String y){
        x = find(x);
        y = find(y);
        Node nodeX = items.get(x);
        Node nodeY = items.get(y);

        if(nodeX.parent.id.equals(nodeY.parent.id)){
            return;
        }
        union(nodeX, nodeY);
    }

    /**
     * Union method to join two nodes with their respective subtrees. Method employs the union-by-size subroutine.
     * @param nodeX first node to be considered.
     * @param nodeY second node to be considered.
     */
    private void union(Node nodeX, Node nodeY) {
        // compare size and perform union efficiently
        if(nodeX.componentSize < nodeY.componentSize){
            nodeX.setParent(nodeY); // set node x's parent pointer to node y.
            nodeY.addChild(nodeX.id); // register node x as child node of y using id of node x
            nodeY.componentSize += nodeX.componentSize; // adjust component size accordingly
            nodeX.setComponentSize(0); // set node x component size to zero.
        }
        else{
            nodeY.setParent(nodeX);
            nodeX.componentSize += nodeY.componentSize;
            nodeX.addChild(nodeY.id);
            nodeY.setComponentSize(0);
        }
        numberOfComponents--;
    }

    /**
     * Method is used to find the name of the set to which the given item belongs to.
     * For each find operation, a path compression operation is performed, setting the
     * parent node of all the subtree children on the path of the found node to the
     * root/parent node.
     * @param x ID of item to be searched.
     * @return the ID of the set to which the found item belongs to.
     */
    public String find(String x){
        Node nodeX = items.get(x);
        while(!(nodeX.parent.equals(nodeX))){
            nodeX = nodeX.parent();
        }
        // path compression
        compressPath(x, nodeX);
        return nodeX.id;
    }
    private void compressPath(String x, Node nodeX) {
        Node cur = items.get(x);
        Node temp;
        while (!(cur.parent.equals(cur))){
            temp = cur;
            cur = cur.parent;
            temp.parent = nodeX;
            temp.removeChildren();
            nodeX.addChild(temp.id);
        }
    }
    @Override
    public void display(){
        for(Node i : items.values()){
            if(i.componentSize >= 1){
                System.out.println(i);
            }
        }
        displayCartesianProduct();
    }
    public void writeData(PrintWriter writer) {
        int x = 0;
        writer.println("Social connections: " + numberOfComponents);
        for(Node i : items.values()){
            if(i.componentSize >= 1){
                x++;
                writer.println(i);
            }
        }
        writer.println("Nr Selections: " + cartesianProductResult.size());
        for(List<String> list : cartesianProductResult){
            writer.println(list);
        }
    }
    public List<List<String>> cartesianProduct(){
        List<List<String>> temp = new ArrayList<>();
        for(Node i : items.values()){
            if(i.componentSize >= 1){
                temp.add(i.children);
            }
        }
        return CartesianProductUtility.cartesianProduct(temp);
    }
    public void displayCartesianProduct(){
        cartesianProductResult = cartesianProduct();
        System.out.println("Nr selections: " + cartesianProductResult.size());
       /* for(List<String> list : cartesianProductResult){
            System.out.println(list);
        }*/
    }
}
