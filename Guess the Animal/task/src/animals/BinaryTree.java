package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    private final Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Node {

        private String value;
        private String parentValue;
        private boolean tureToParent;
        private Node left;
        private Node right;

        public Node() {

        }

        public Node(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @JsonIgnore
        public boolean isLeaf() {
            return left == null && right == null;
        }

        public String getParentValue() {
            return parentValue;
        }

        public void setParentValue(String parentValue) {
            this.parentValue = parentValue;
        }

        public boolean isTureToParent() {
            return tureToParent;
        }

        public void setTureToParent(boolean tureToParent) {
            this.tureToParent = tureToParent;
        }

    }

    public List<String> findAllLeavesValue() {

        return findAllLeavesValue(root);

    }

    private List<String> findAllLeavesValue(Node node) {

        ArrayList<String> leaves = new ArrayList<>();

        if (node == null) {
            return new ArrayList<>();
        }
        if (node.isLeaf()) {
            leaves.add(node.value);
            return leaves;
        }
        leaves.addAll(findAllLeavesValue(node.left));
        leaves.addAll(findAllLeavesValue(node.right));

        return leaves;

    }

    public Node find(String value) {
        return find(root, value);
    }

    public Node find(Node node, String value) {
        if (node == null) {
            return null;
        }

        if (node.value.equals(value)) {
            return node;
        } else if (node.isLeaf()) {
            return null;
        } else {
            Node leftResult = find(node.left, value);
            Node rightResult = find(node.right, value);

            if (leftResult != null) {
                return leftResult;
            } else {
                return rightResult;
            }
        }
    }

    public List<String> findFacts(String animal) throws Exception {
        ArrayList<String> facts = new ArrayList<>();

        Node animalNode = find(animal);
        if (animalNode == null) {
            return facts;
        } else {
            String fact = animalNode.getParentValue();
            while (fact != null) {
                facts.add(0, Util.questionToSentence(fact, animalNode.isTureToParent()));
                animalNode = find(fact);
                fact = animalNode.getParentValue();
            }
        }

        return facts;
    }

    public int numNodes() {
        return numNodes(root);
    }

    private int numNodes(Node node) {
        if (node == null) {
            return 0;
        } else {
            return numNodes(node.right) + numNodes(node.left) + 1;
        }
    }

    public int numAnimals() {
        return numAnimals(root);
    }

    private int numAnimals(Node node) {
        if (node == null) {
            return 0;
        } else if (node.isLeaf()) {
            return 1;
        } else {
            return numAnimals(node.right) + numAnimals(node.left);
        }
    }

    public int getHeight() {
        return getHeight(root) - 1;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public int getMinHeight() {
        return getMinHeight(root);
    }

    private int getMinHeight(Node node) {
        if (node.isLeaf()) {
            return 0;
        } else {
            return Math.min(getMinHeight(node.left), getMinHeight(node.right)) + 1;
        }
    }

    public double getAverageHeight() {
        return (double) getTotalHeight(root) / numAnimals();
    }

    private int getTotalHeight(Node node) {
        if (node == null) {
            return 0;
        } else if (node.isLeaf()) {
            return getNodeHeight(node);
        } else {
            return getTotalHeight(node.left) + getTotalHeight(node.right);
        }
    }

    private int getNodeHeight(Node node) {
        if (node == null) {
            return -1;
        } else {
            int height = 0;
            String parentValue = node.parentValue;
            while (parentValue != null) {
                Node parentNode = find(parentValue);
                height++;
                parentValue = parentNode.parentValue;
            }
            return height;
        }
    }

    @Override
    public String toString() {
        return toString(root, 0, new StringBuilder());
    }

    private String toString(Node node, int depth, StringBuilder sb) {
        if (depth == 0) {
            sb.append("└ ").append(node.value).append("\n");

            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        } else if (node != null) {
            sb.append("│".repeat(Math.max(0, depth - 1)));
            if (node.tureToParent) {
                sb.append("└");
            } else {
                sb.append("├");
            }
            sb.append(" ").append(node.value).append("\n");

            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }

        return sb.toString();
    }

}
