package studing.algoritms.greed_algoritms.haffemen_cod;

import java.util.*;

public class HaffmenCode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.next();
        Queue<Token> tokens = parseInputStr(inputStr);
        int size = tokens.size();
        HaffmenTree tree = new HaffmenTree(tokens);
        Map<String, String> symbolsCode = tree.getSymbolsCode(inputStr);
        String code = tree.code(inputStr);
        System.out.print(size + " " +code.length() + "\n");
        for (Map.Entry<String, String> entry : symbolsCode.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(code);

    }

    private static Queue<Token> parseInputStr(String inputStr) {
        char[] chars = inputStr.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char inputChar : chars) {
            if (map.containsKey(inputChar)) {
                Integer frequency = map.get(inputChar);
                map.put(inputChar, ++frequency);
            } else {
                map.put(inputChar, 1);
            }

        }
        Queue<Token> tokens = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            tokens.offer(new Token(entry.getKey(), entry.getValue()));
        }
        return tokens;
    }

    private static class Token implements Comparable<Token> {

        Character value;
        int frequency;

        public Token(Character value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "value=" + value +
                    ", frequency=" + frequency +
                    '}';
        }


        @Override
        /*key.compareTo(value) --> head will heave most less priority*/
        public int compareTo(Token that) {
            return this.frequency - that.frequency;
        }
    }

    private static class HaffmenTree {

        Node rootNode;
        List<Node> leaves = new LinkedList<>();
        

        public HaffmenTree(Queue<Token> tokens) {
            buildTree(tokens);

        }

        private void buildTree(Queue<Token> tokens) {
            if (tokens.size() < 2) {
                rootNode = new Node();
                rootNode.code = '0';
                rootNode.token = tokens.poll();
                leaves.add(rootNode);
                return;
            }
            Queue<Node> priorityNodes = feelQueue(tokens);
            
            while (priorityNodes.size()!= 1){
                Node leftNode = priorityNodes.poll();
                leftNode.code = '0';
                Node rightNode = priorityNodes.poll();
                rightNode.code = '1';
                if(leftNode.token.value != null){
                    leaves.add(leftNode);
                }
                if(rightNode.token.value != null){
                    leaves.add(rightNode);
                }
                Node node = new Node(new Token(null, leftNode.token.frequency + rightNode.token.frequency));
                node.leftChild = leftNode;
                node.rightChild = rightNode;
                leftNode.parent = node;
                rightNode.parent = node;
                priorityNodes.add(node);
            }
            rootNode = priorityNodes.poll();
        }

        private Queue<Node> feelQueue(Queue<Token> tokens) {
            Queue<Node> priorityQueue = new PriorityQueue<>();
            for (Token token : tokens) {
                priorityQueue.add(new Node(token));
            }
            return priorityQueue;
        }

        String code(String str) {

            StringBuilder codedStr = new StringBuilder();

            char[] chars = str.toCharArray();
            for (char character : chars) {
                codedStr.append(code(character));
            }
            return codedStr.toString();
        }

        private String code(char character) {

            for (Node leaf : leaves) {
                if (leaf.token.value == character) {
                    StringBuilder code = new StringBuilder(String.valueOf(leaf.code));
                    for (Node node = leaf.parent; node != null; node = node.parent) {
                        if (node.code != 0) {
                            code.append(String.valueOf(node.code));
                        }
                    }
                    return code.reverse().toString();
                }

            }

            throw new RuntimeException("Sheet happens");
        }

        public Map<String, String> getSymbolsCode(String str) {

            Map<String, String> charsCode = new HashMap<>();
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                charsCode.put(String.valueOf(aChar), code(aChar));
            }
            return charsCode;
        }


        private class Node implements Comparable<Node> {

            Node leftChild;
            Node rightChild;
            Node parent;
            Token token;
            char code;

            public Node() {
            }

            public Node(Node leftNode, Node rightNode, Token token, char code) {
                this.leftChild = leftNode;
                this.rightChild = rightNode;
                this.token = token;
                this.code = code;
            }

            public Node(Node leftNode, Node rightNode, Token token) {
                this.leftChild = leftNode;
                this.rightChild = rightNode;
                this.token = token;
            }

            public Node(Token token) {
                this.token = token;
            }

            @Override
            public int compareTo(Node that) {
                return this.token.frequency -  that.token.frequency;
            }
        }


    }

}


