import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProblemB {
    public static String inName = "input.txt";
    public static int numberOfWords;
    public static String[] words;

    public static void main(String[] args) {
        try {
            //DataInputStream stream = new DataInputStream(System.in);
            InputStream stream = new FileInputStream(inName);
            InputStreamReader fileReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            numberOfWords = Integer.parseInt(bufferedReader.readLine());
            words = new String[numberOfWords];
            for (int i = 0; i < numberOfWords; i++) {
                words[i] = bufferedReader.readLine();
            }
            System.out.println(longestSubstring(words));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String longestSubstring(List<String> strings) {
        if (strings.size() == 1) {
            return strings.get(0);
        }
        String substring = longestSubstring(strings.get(0), strings.get(1));
        strings.remove(0);
        strings.set(0, substring);
        return longestSubstring(strings);
    }

    public static String longestSubstring(String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] dimensions = new int[strings.length];
        int length = 0;
        for (int i = 0; i < strings.length; i++) {
            dimensions[i] = strings[i].length();
            if (dimensions[i] > length) {
                length = dimensions[i];
            }
        }

        String[] normalizedStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length(); j++) {
                normalizedStrings[i] += strings[i].charAt(j);
            }
            for (int j = strings[i].length(); j < length; j++) {
                normalizedStrings[i] += "0";
            }
        }

        Matrix matrix = new Matrix(dimensions);

        for (int[] aMatrix : matrix) {
            boolean equals = false;
            for (int i = 0; i < aMatrix.length - 1; i++) {
                if (i % aMatrix.length != 0){
                    equals = equals &&
                            (normalizedStrings[i].charAt(aMatrix[i]) ==
                                    normalizedStrings[i+1].charAt(aMatrix[i+1]));
                    System.out.println(equals);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String longestSubstring(String str1, String str2) {

        StringBuilder sb = new StringBuilder();

        int[][] num = new int[str1.length()][str2.length()];
        int maxlen = 0;
        int lastSubsBegin = 0;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    if ((i == 0) || (j == 0))
                        num[i][j] = 1;
                    else
                        num[i][j] = 1 + num[i - 1][j - 1];

                    if (num[i][j] > maxlen) {
                        maxlen = num[i][j];
                        int thisSubsBegin = i - num[i][j] + 1;
                        if (lastSubsBegin == thisSubsBegin) {
                            sb.append(str1.charAt(i));
                        } else {
                            lastSubsBegin = thisSubsBegin;
                            sb = new StringBuilder();
                            sb.append(str1.substring(lastSubsBegin, i + 1));
                        }
                    }
                }
            }
        }

        return sb.toString();
    }
}

class Matrix implements Iterable<int[]> {
    private List<Integer> list;
    private int length;
    private Iterator<int[]> iterator;

    public Matrix(int[] dimensions) {
        int length = 0;
        for (int dimension : dimensions) {
            if (dimension > length) {
                length = dimension;
            }
        }
        this.length = dimensions.length * length;
        list = new ArrayList<Integer>();
        for (int i = 0; i < this.length; i++) {
            list.add(0);
        }
        iterator = new Index(dimensions.length, length);
    }

    public int get(int[] indexes) {
        int resultingIndex = 0;

        for (int i = 0; i < indexes.length; i++) {
            resultingIndex += i + indexes[i];
        }

        return list.get(resultingIndex);
    }

    public int put(int[] indexes, int value) {
        int resultingIndex = 0;

        for (int i = 0; i < indexes.length; i++) {
            resultingIndex += i + indexes[i];
        }

        return list.set(resultingIndex, value);
    }

    @Override
    public Iterator<int[]> iterator() {
        return iterator;
    }
}

class Index implements Iterator<int[]> {
    private int[] current;
    private int length;

    public Index(int n, int length) {
        this.length = length;
        current = new int[n];
        for (int i = 0; i < current.length; i++) {
            current[i] = 0;
        }
    }

    @Override
    public boolean hasNext() {
        return current[current.length - 1] < length;
    }

    @Override
    public int[] next() {
        int[] result = current;
        for (int i = 0; i < current.length; i++) {
            if (result[i] < length) {
                result[i]++;
                current = result;
                return result;
            }
        }
        return result;
    }
}