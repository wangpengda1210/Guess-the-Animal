import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();

        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            String[] operation = scanner.nextLine().split(" ");
            switch (operation[0]) {
                case "+":
                    binaryTree.add(Integer.parseInt(operation[1]), Integer.parseInt(operation[2]));
                    break;
                case "-":
                    binaryTree.delete(Integer.parseInt(operation[1]));
                    break;
                case "!":
                    binaryTree.changeValue(Integer.parseInt(operation[1]), Integer.parseInt(operation[2]));
                    break;
            }
        }

        binaryTree.inOrderTraversal();
    }

}

class BinaryTree {

    private Node root = null;

    public Node getRoot() {
        return root;
    }

    public void add(int key, int value) {
        root = add(root, null, key, value);
    }

    private Node add(Node node, Node parent, int key, int value) {
        if (node == null) {
            node = new Node(key, value, parent);
        } else {
            if (key < node.key) {
                node.left = add(node.left, node, key, value);
            } else {
                node.right = add(node.right, node, key, value);
            }
        }
        return node;
    }

    private void replace(Node a, Node b) {
        if (a.parent == null) {
            root = b;
        } else if (a == a.parent.left) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }

        if (b != null) {
            b.parent = a.parent;
        }
    }

    public void delete(int key) {
        delete(root, key);
    }

    private void delete(Node node, int key) {
        if (node == null) {
            return;
        }
        if (key < node.key) {
            delete(node.left, key);
        } else if (key > node.key) {
            delete(node.right, key);
        } else if (node.left == null && node.right == null) {
            replace(node, null);
        } else if (node.left == null) {
            replace(node, node.right);
        } else if (node.right == null) {
            replace(node, node.left);
        } else {
            Node toReplace = node.right;
            while (toReplace.left != null) {
                toReplace = toReplace.left;
            }
            node.key = toReplace.key;
            node.value = toReplace.value;
            replace(toReplace, toReplace.right);
        }
    }

    public void changeValue(int key, int value) {
        changeValue(root, key, value);
    }

    private void changeValue(Node node, int key, int value) {
        if (node == null) {
            return;
        }
        if (key == node.key) {
            node.value = value;
        } else if (key < node.key) {
            changeValue(node.left, key, value);
        } else {
            changeValue(node.right, key, value);
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.print(node.value + " ");
        inOrderTraversal(node.right);
    }


    static class Node {

        private int key;
        private int value;
        private Node parent;
        private Node left;
        private Node right;

        public Node(int key, int value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
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

    }

}