package Games.TicTacToe;

public class Node {
    private int value;
    private Node[] children;
    private Node[] parents;
    private int x;
    private int y;

    public Node(int value, Node[] parents, int x, int y) {
        this.value = value;
        this.parents = parents;
    }

    public Node(int value, Node[] parents, Node[] children) {
        this.value = value;
        this.children = children;
        this.parents = parents;
        this.x = x;
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public Node[] getChildren() {
        return children;
    }

    public Node[] getParents() {
        return parents;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public void setParent(Node[] parents) {
        this.parents = parents;
    }

    public void addChild(Node child) {
        Node[] temp = new Node[children.length + 1];
        for(int i = 0; i < children.length; i++) {
            temp[i] = children[i];
        }
        temp[children.length] = child;
        children = temp;
    }

    public void removeChild(Node child) {
        Node[] temp = new Node[children.length - 1];
        int j = 0;
        for(int i = 0; i < children.length; i++) {
            if(children[i] != child) {
                temp[j] = children[i];
                j++;
            }
        }
        children = temp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
