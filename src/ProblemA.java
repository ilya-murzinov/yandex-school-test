import java.io.*;
import java.util.*;

public class ProblemA {
    public static String inName = "input_find.txt";
    public static String outName = "output.txt";
    public static Formats inputFormat;
    public static Formats outputFormat;
    public static String number;

    public enum Formats {
        FIND("find"),
        PYTHON("python"),
        ACM1("acm1"),
        ACM2("acm2"),
        ACM3("acm3"),
        XML("xml");

        private String name;

        Formats(String name) {
            this.name = name;
        }

        public static Formats fromString(String name) {
            for (Formats format : Formats.values()) {
                if (format.name.equals(name)) {
                    return format;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        String[] paths = null;
        try {
            DataInputStream stream = new DataInputStream(System.in);
            InputStreamReader fileReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            inputFormat = Formats.fromString(bufferedReader.readLine());
            outputFormat = Formats.fromString(bufferedReader.readLine());
            number = bufferedReader.readLine();
            paths = new String[Integer.parseInt(number)];
            for (int i = 0; i < paths.length; i++) {
                paths[i] = bufferedReader.readLine();
            }

//            InputStream inputStream = new FileInputStream(inName);
//            InputStreamReader fileReader = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            inputFormat = Formats.fromString(bufferedReader.readLine());
//            outputFormat = Formats.fromString(bufferedReader.readLine());
//            number = bufferedReader.readLine();
//            paths = new String[Integer.parseInt(number)];
//            for (int i = 0; i < paths.length; i++) {
//                paths[i] = bufferedReader.readLine();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node<String, Integer> raw;

        switch (inputFormat) {
            case FIND:
                raw = parseFind(paths);
                break;
            case PYTHON:
                raw = parsePython(paths);
                break;
            default:
                raw = null;
        }

        String result;

        switch (outputFormat) {
            case FIND:
                result = toFind(raw);
                break;
            case PYTHON:
                result = toPython(raw);
                break;
            default:
                result = "Error!";
        }

        System.out.print(number + "\n" + result);
    }

    public static Node<String, Integer> parseFind(String[] paths) {
        Node<String, Integer> root
                = new Node<String, Integer>(paths[0].split("\\s")[0], Integer.parseInt(paths[0].split("\\s")[1]));
        for (int i = 1; i < paths.length; i++) {
            String path = paths[i];
            String value = path.split("\\s")[0];
            Integer key = Integer.parseInt(path.split("\\s")[1]);
            List<String> list = new ArrayList<String>(Arrays.asList(value.split("/")));
            list.remove(0);
            root.addNestedNodes(list, key);
        }
        return root;
    }

    public static Node<String, Integer> parsePython(String[] paths) {
        Node<String, Integer> root
                = new Node<String, Integer>(paths[0].split("\\s")[0], Integer.parseInt(paths[0].split("\\s")[1]));
        Node<String, Integer> previous = root;
        Node<String, Integer> parent = root;
        int numberOfSpaces = 0;
        for (int i = 1; i < paths.length; i++) {
            String path = paths[i];
            Node<String, Integer> newNode
                    = new Node<String, Integer>(path.trim().split("\\s")[0], Integer.parseInt(path.trim().split("\\s")[1]));
            int indent = path.length() - path.trim().length();
            if (indent > numberOfSpaces) {
                numberOfSpaces = indent;
                parent = previous;
                previous = newNode;
                parent.addChild(newNode);
            } else if (indent == numberOfSpaces) {
                previous = newNode;
                parent.addChild(newNode);
            } else if (indent < numberOfSpaces) {
                for (int j = 0; j <= (numberOfSpaces - indent)/4; j++) {
                    previous = previous.getParent();
                }
                numberOfSpaces = indent;
                parent = previous;
                previous = newNode;
                parent.addChild(newNode);
            }
        }
        return root;
    }

    public static String toFind(Node<String, Integer> root) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(root.getKey());
        stringBuilder.append("\n");
        for (Node<String, Integer> child : root.getChildren()) {
            Node<String, Integer> parent = child.getParent();
            List<Node<String, Integer>> list = new ArrayList<Node<String, Integer>>();
            while (parent != null) {
                list.add(parent);
                parent = parent.getParent();
            }
            Collections.reverse(list);

            for (Node<String, Integer> value : list) {
                stringBuilder.append(value.getValue());
                stringBuilder.append("/");
            }

            stringBuilder.append(toFind(child));
        }
        return stringBuilder.toString();
    }

    public static String toPython(Node<String, Integer> root) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(root.getKey());
        stringBuilder.append("\n");
        for (Node<String, Integer> child : root.getChildren()) {
            Node<String, Integer> parent = child.getParent();
            String indent = "";
            while (parent != null) {
                indent += "    ";
                parent = parent.getParent();
            }

            stringBuilder.append(indent);
            stringBuilder.append(toPython(child));
        }
        return stringBuilder.toString();
    }
}

class Node<T, U> {
    private T value;
    private U key;
    private Node<T, U> parent;
    private List<Node<T, U>> children;

    public Node(T value, U key) {
        this.value = value;
        this.key = key;
        children = new ArrayList<Node<T, U>>();
    }

    public void addChild(T value, U key) {
        Node<T, U> child = new Node<T, U>(value, key);
        children.add(child);
        child.children = new ArrayList<Node<T, U>>();
        child.parent = this;
    }

    public void addChild(Node<T, U> newNode) {
        children.add(newNode);
        newNode.children = new ArrayList<Node<T, U>>();
        newNode.parent = this;
    }

    public void addNestedNodes(List<T> values, U key) {
        if (values.size() == 1) {
            addChild(values.get(0), key);
        } else {
            Node<T, U> child = getChild(values.get(0));
            if (child == null) {
                addChild(values.get(0), key);
                values.remove(0);
                getChild(values.get(0)).addNestedNodes(values, key);
            } else {
                values.remove(0);
                child.addNestedNodes(values, key);
            }
        }
    }

    public Node<T, U> getChild(T value) {
        if (children.size() != 0) {
            for (Node<T, U> child : children) {
                if (child.value.equals(value)) {
                    return child;
                } else {
                    child.getChild(value);
                }
            }
        }
        return null;
    }

    public List<Node<T, U>> getChildren() {
        return children;
    }

    public T getValue() {
        return value;
    }

    public U getKey() {
        return key;
    }

    public Node<T, U> getParent() {
        return parent;
    }
}