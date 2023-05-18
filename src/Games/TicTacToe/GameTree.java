package Games.TicTacToe;

import java.util.List;

public class GameTree {

    private Node[][] nodes;
    private List<Node> availableNodes;

    public GameTree(Square[][] squares) {
        for(int i = 0; i < squares.length; i++)
            for(int j = 0; j < squares.length; j++)
                nodes[i][j] = new Node(squares[i][j].getValue(), null, null);

        getAvailableNodes();
    }

    public int getNodeXPosition(Node node) {
        return node.getX();
    }

    public int getNodeYPosition(Node node) {
        return node.getY();
    }

    public Node getNode(int posX, int posY) {
        return nodes[posX][posY];
    }

    public void setNodeUsed(int posX, int posY, int value){
        nodes[posX][posY].setValue(value);
    }

    public void getAvailableNodes() {
        for (int i = 0; i < nodes.length; i++)
            for (int j = 0; j < nodes.length; j++)
                if (nodes[i][j].getValue() == 0)
                    availableNodes.add(nodes[i][j]);
    }


}

