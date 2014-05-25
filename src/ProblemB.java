import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemB {
    public static String inName = "input.txt";
    public static int numberOfWords;
    public static String[] words;

    public static void main(String[] args) {
        try {
//            DataInputStream stream = new DataInputStream(System.in);
//            InputStreamReader fileReader = new InputStreamReader(stream);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);

            InputStream inputStream = new FileInputStream(inName);
            InputStreamReader fileReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            numberOfWords = Integer.parseInt(bufferedReader.readLine());
            words = new String[numberOfWords];
            for (int i = 0; i < numberOfWords; i++) {
                words[i] = bufferedReader.readLine();
            }
            System.out.println(longestSubstring(new ArrayList<String>(Arrays.asList(words))));
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
            }}

        return sb.toString();
    }
}
