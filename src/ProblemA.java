import java.io.*;
import java.util.*;

public class ProblemA {
    public static String inName = "input_find_acm3.txt";
    public static Formats inputFormat;
    public static Formats outputFormat;
    public static Integer number;

    public ProblemA(String fileName) {
        inName = fileName;
    }

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
        System.out.print(transform());
    }

    public static String transform() {
        String[] paths = null;
        try {
            //InputStream stream = new FileInputStream(inName);
            DataInputStream stream = new DataInputStream(System.in);
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
                List<String> lines = new ArrayList<String>();
                String line = bufferedReader.readLine();
                while (line != null) {
                    lines.add(line);
                    line = bufferedReader.readLine();
                }
                paths = new String[lines.size()];
                lines.toArray(paths);
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
            case ACM2:
                raw = parseACM2(paths);
                break;
            case ACM3:
                raw = parseACM3(paths);
                break;
            case XML:
                raw = parseXML(paths);
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
            case ACM1:
                result = toACM1(raw);
                break;
            case ACM2:
                result = toACM2(raw);
                break;
            case ACM3:
                result = toACM3(raw);
                break;
            case XML:
                result = toXML(raw);
                break;
            default:
                result = "Error!";
        }

        if (outputFormat != Formats.XML) {
            return number + "\n" + result;
        } else {
            return result;
        }
    }

    public static Node<String> parseFind(String[] paths) {
        Node<String> root
                = new Node<String>(paths[0].split("\\s")[0], 0);
        for (int i = 1; i < paths.length; i++) {
            String[] dirs = paths[i].split("\\s")[0].split("/");
            if (dirs.length == 2) {
                root.addChild(dirs[1],
                        Integer.parseInt(paths[i].split("\\s")[1]));
            } else {
                root.get(dirs[dirs.length - 2]).addChild(dirs[dirs.length - 1],
                        Integer.parseInt(paths[i].split("\\s")[1]));
            }
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

    public static Node<String> parseACM2(String[] paths) {
        Map<String, String> data = new HashMap<String, String>();
        for (int i = 0; i < number; i++) {
            data.put(paths[i].split("\\s")[1],
                    paths[i].split("\\s")[0]);
        }
        Node<String> root
                = new Node<String>(data.get("0"), 0);
        for (int i = number; i < 2 * number; i++) {
            if (paths[i].equals("-1")) {
                continue;
            }

            if (paths[i].equals("0")) {
                root.addChild(paths[i - number].split("\\s")[0],
                        Integer.parseInt(paths[i - number].split("\\s")[1]));
            } else {
                root.get(Integer.parseInt(paths[i]))
                        .addChild(paths[i - number].split("\\s")[0],
                                Integer.parseInt(paths[i - number].split("\\s")[1]));
            }
        }
        return root;
    }

    public static Node<String> parseACM3(String[] paths) {
        Map<String, String> data = new HashMap<String, String>();
        for (int i = 0; i < number; i++) {
            data.put(paths[i].split("\\s")[1],
                    paths[i].split("\\s")[0]);
        }
        Node<String> root
                = new Node<String>(data.get("0"), 0);
        for (int i = number; i < 2 * number - 1; i++) {
            root.get(Integer.parseInt(paths[i].split("\\s")[0]))
                    .addChild(data.get(paths[i].split("\\s")[1]),
                            Integer.parseInt(paths[i].split("\\s")[1]));
        }
        return root;
    }

    public static Node<String> parseXML(String[] paths) {
        Node<String> root =
                new Node<String>(paths[0].split("\'")[1],
                        Integer.parseInt(paths[0].split("\'")[3]));
        int numberOfSpaces = 0;
        Node<String> previous = root;
        Node<String> parent = root;
        for (int i = 1; i < paths.length; i++) {
            if (paths[i].contains("</dir>")) {
                continue;
            }
            int indent = paths[i].length() - paths[i].trim().length();
            Node<String> newNode = new Node<String>(paths[i].split("\'")[1],
                    Integer.parseInt(paths[i].split("\'")[3]));
            if (indent > numberOfSpaces) {
                numberOfSpaces = indent;
                parent = previous;
                previous = newNode;
                parent.addChild(newNode);
            } else if (indent == numberOfSpaces) {
                previous = newNode;
                parent.addChild(newNode);
            } else if (indent < numberOfSpaces) {
                for (int j = 0; j <= (numberOfSpaces - indent)/2; j++) {
                    previous = previous.getParent();
                }
                numberOfSpaces = indent;
                parent = previous;
                previous = newNode;
                parent.addChild(newNode);
            }
        }
        number = root.getAllChildren().size() + 1;
        return root;
    }

    public static String toFind(Node<String> root) {
        List<Node<String>> nodes = new ArrayList<Node<String>>();
        nodes.add(root);
        for (Node<String> child : root.getAllChildren()) {
            nodes.add(child);
        }
        Collections.sort(nodes);

        StringBuilder stringBuilder = new StringBuilder();

        for (Node<String> node : nodes) {
            List<String> subDirs = new ArrayList<String>();
            Node<String> parent = node.getParent();
            while (parent != null) {
                subDirs.add(parent.getValue());
                parent = parent.getParent();
            }
            Collections.reverse(subDirs);
            for (String dir : subDirs) {
                stringBuilder.append(dir);
                stringBuilder.append("/");
            }
            stringBuilder.append(node.getValue());
            stringBuilder.append(" ");
            stringBuilder.append(node.getKey());
            stringBuilder.append("\n");
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

    public static String toACM1(Node<String> root) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(root.getKey());
        stringBuilder.append("\n");
        List<Node<String>> children = root.getAllChildren();
        Collections.sort(children);
        for (Node<String> node : children) {
            stringBuilder.append(node.getValue());
            stringBuilder.append(" ");
            stringBuilder.append(node.getKey());
            stringBuilder.append("\n");
        }
        stringBuilder.append(root.getChildren().size());
        for (Node<String> node : root.getChildren()) {
            stringBuilder.append(" ");
            stringBuilder.append(node.getKey());
        }
        stringBuilder.append("\n");
        for (Node<String> child : children) {
            stringBuilder.append(child.getChildren().size());
            for (Node<String> node : child.getChildren()) {
                stringBuilder.append(" ");
                stringBuilder.append(node.getKey());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String toACM2(Node<String> root) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(root.getKey());
        stringBuilder.append("\n");
        List<Node<String>> children = root.getAllChildren();
        Collections.sort(children);
        for (Node<String> node : children) {
            stringBuilder.append(node.getValue());
            stringBuilder.append(" ");
            stringBuilder.append(node.getKey());
            stringBuilder.append("\n");
        }
        stringBuilder.append("-1");
        stringBuilder.append("\n");
        for (Node<String> child : children) {
            stringBuilder.append(child.getParent().getKey());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String toACM3(Node<String> root) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue());
        stringBuilder.append(" ");
        stringBuilder.append(root.getKey());
        stringBuilder.append("\n");
        List<Node<String>> children = root.getAllChildren();
        Collections.sort(children);
        for (Node<String> node : children) {
            stringBuilder.append(node.getValue());
            stringBuilder.append(" ");
            stringBuilder.append(node.getKey());
            stringBuilder.append("\n");
        }
        List<Edge> edges = new ArrayList<Edge>();
        for (Node<String> child : children) {
            edges.add(new Edge(child.getParent().getKey(), child.getKey()));
        }
        Collections.sort(edges);
        for (Edge edge : edges) {
            stringBuilder.append(edge.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String toXML(Node<String> root) {
        StringBuilder stringBuilder = new StringBuilder();
        Node<String> parent = root.getParent();
        String indent = "";
        while (parent != null) {
            indent += "  ";
            parent = parent.getParent();
        }
        stringBuilder.append(indent);
        if (root.getChildren().size() == 0) {
            stringBuilder.append("<file name='");
            stringBuilder.append(root.getValue());
            stringBuilder.append("' id='");
            stringBuilder.append(root.getKey());
            stringBuilder.append("'/>\n");
        } else {
            stringBuilder.append("<dir name='");
            stringBuilder.append(root.getValue());
            stringBuilder.append("' id='");
            stringBuilder.append(root.getKey());
            stringBuilder.append("'>\n");
            for (Node<String> child : root.getChildren()) {
                stringBuilder.append(toXML(child));
            }
            stringBuilder.append(indent);
            stringBuilder.append("</dir>\n");
        }
        return stringBuilder.toString();
    }
}

class Node<T> implements Comparable {
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
        addChild(child);
    }

    public void addChild(Node<T> newNode) {
        children.add(newNode);
        Collections.sort(children);
        newNode.children = new ArrayList<Node<T>>();
        newNode.parent = this;
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
                    Node<T> val = child.get(value);
                    if (val != null) {
                        return val;
                    }
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

    public List<Node<T>> getAllChildren() {
        List<Node<T>> list = new ArrayList<Node<T>>();
        for (Node<T> child : children) {
            list.add(child);
            list.addAll(child.getAllChildren());
        }
        return list;
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

    @Override
    public int compareTo(Object o) {
        Node<T> node = null;
        try {
            node = (Node<T>) o;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        if (node == null) {
            return 0;
        } else {
            return key.compareTo(node.getKey());
        }
    }
}

class Edge implements Comparable {
    private Integer firstVertex;
    private Integer secondVertex;

    public Edge(Integer firstVertex, Integer secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    @Override
    public int compareTo(Object o) {
        Edge secondEdge = null;
        try {
            secondEdge = (Edge) o;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        if (secondEdge == null) {
            return 0;
        } else {
            if (!firstVertex.equals(secondEdge.firstVertex)) {
                return firstVertex.compareTo(secondEdge.firstVertex);
            } else {
                return secondVertex.compareTo(secondEdge.secondVertex);
            }
        }
    }

    @Override
    public String toString() {
        return firstVertex + " " + secondVertex;
    }
}