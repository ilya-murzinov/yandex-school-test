import java.io.*;
import java.util.*;

public class ProblemA {
    public static String inName = "input_acm1.txt";
    public static Formats inputFormat;
    public static Formats outputFormat;
    public static Integer number;

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
//        Node<String> node = new Node<String>("1", 0);
//        node.addChild("2", 1);
//        node.get(1).addChild("3", 5);
//        node.get(5).addChild("4", 7);
        String[] paths = null;
        try {
            InputStream stream = new FileInputStream(inName);
            //DataInputStream stream = new DataInputStream(System.in);
            InputStreamReader fileReader = new InputStreamReader(stream);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            inputFormat = Formats.fromString(bufferedReader.readLine());
            outputFormat = Formats.fromString(bufferedReader.readLine());
            if (inputFormat != Formats.XML) {
                number = Integer.parseInt(bufferedReader.readLine());
                if (inputFormat == Formats.ACM1 || inputFormat == Formats.ACM2
                        || inputFormat == Formats.ACM3) {
                    paths = new String[2 * number];
                } else {
                    paths = new String[number];
                }

                for (int i = 0; i < paths.length; i++) {
                    paths[i] = bufferedReader.readLine();
                }
            } else {

            }

            if (inputFormat.equals(outputFormat)) {
                System.out.println(number);
                for (String path : paths) {
                    System.out.println(path);
                }
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Node<String> raw;

        switch (inputFormat) {
            case FIND:
                raw = parseFind(paths);
                break;
            case PYTHON:
                raw = parsePython(paths);
                break;
            case ACM1:
                raw = parseACM1(paths);
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

    public static Node<String> parseFind(String[] paths) {
        Node<String> root
                = new Node<String>(paths[0].split("\\s")[0], 0);
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

    public static Node<String> parsePython(String[] paths) {
        Node<String> root
                = new Node<String>(paths[0].split("\\s")[0], 0);
        Node<String> previous = root;
        Node<String> parent = root;
        int numberOfSpaces = 0;
        for (int i = 1; i < paths.length; i++) {
            String path = paths[i];
            Node<String> newNode
                    = new Node<String>(path.trim().split("\\s")[0],
                    Integer.parseInt(path.trim().split("\\s")[1]));
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

    public static Node<String> parseACM1(String[] paths) {
        Map<String, String> data = new HashMap<String, String>();
        for (int i = 0; i < number; i++) {
            data.put(paths[i].split("\\s")[1],
                    paths[i].split("\\s")[0]);
        }
        Node<String> root
                = new Node<String>(data.get("0"), 0);
        for (int i = number; i < 2 * number; i++) {
            if (paths[i].equals("0")) {
                if (root.get(Integer.parseInt(paths[i - number].split("\\s")[1])) == null) {
                    root.addChild(paths[i - number].split("\\s")[0],
                            Integer.parseInt(paths[i - number].split("\\s")[1]));
                }
            } else {
                int numberOfChildren = Integer.parseInt(paths[i].split("\\s")[0]);
                for (int j = 1; j <= numberOfChildren; j++) {
                    int parentKey = Integer.parseInt(paths[i - number].split("\\s")[1]);
                    Node<String> parent = root.get(parentKey);
                    String value = data.get(paths[i].split("\\s")[j]);
                    int key = Integer.parseInt(paths[i].split("\\s")[j]);
                    parent.addChild(value, key);
                }
            }
        }
        return root;
    }

    public static String toFind(Node<String> root) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(root.getKey());
        stringBuilder.append("\n");
        for (Node<String> child : root.getChildren()) {
            Node<String> parent = child.getParent();
            List<Node<String>> list = new ArrayList<Node<String>>();
            while (parent != null) {
                list.add(parent);
                parent = parent.getParent();
            }
            Collections.reverse(list);

            for (Node<String> value : list) {
                stringBuilder.append(value.getValue());
                stringBuilder.append("/");
            }

            stringBuilder.append(toFind(child));
        }
        return stringBuilder.toString();
    }

    public static String toPython(Node<String> root) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(root.getKey());
        stringBuilder.append("\n");
        for (Node<String> child : root.getChildren()) {
            Node<String> parent = child.getParent();
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

class Node<T> {
    private T value;
    private Integer key;
    private Node<T> parent;
    private List<Node<T>> children;

    public Node(T value, Integer key) {
        this.value = value;
        this.key = key;
        children = new ArrayList<Node<T>>();
    }

    public void addChild(T value, Integer key) {
        Node<T> child = new Node<T>(value, key);
        children.add(child);
        child.children = new ArrayList<Node<T>>();
        child.parent = this;
    }

    public void addChild(Node<T> newNode) {
        children.add(newNode);
        newNode.children = new ArrayList<Node<T>>();
        newNode.parent = this;
    }

    public void addNestedNodes(List<T> values, Integer key) {
        if (values.size() == 1) {
            addChild(values.get(0), key);
        } else {
            Node<T> child = getChild(values.get(0));
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

    public Node<T> getChild(T value) {
        if (children.size() != 0) {
            for (Node<T> child : children) {
                if (child.value.equals(value)) {
                    return child;
                } else {
                    Node<T> node = child.get(value);
                    if (value != null) {
                        return node;
                    }
                }
            }
        }
        return null;
    }

    public Node<T> getChild(Integer key) {
        if (children.size() != 0) {
            for (Node<T> child : children) {
                if (child.key.equals(key)) {
                    return child;
                } else {
                    child.getChild(key);
                }
            }
        }
        return null;
    }

    public Node<T> get(T value) {
        if (this.value.equals(value)) {
            return this;
        }
        if (children.size() != 0) {
            for (Node<T> child : children) {
                if (child.value.equals(value)) {
                    return child;
                } else {
                    child.get(value);
                }
            }
        }
        return null;
    }

    public Node<T> get(Integer key) {
        if (this.key.equals(key)) {
            return this;
        }
        if (children.size() != 0) {
            for (Node<T> child : children) {
                if (child.key.equals(key)) {
                    return child;
                } else {
                    Node<T> value = child.get(key);
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
        return null;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public T getValue() {
        return value;
    }

    public Integer getKey() {
        return key;
    }

    public Node<T> getParent() {
        return parent;
    }
}