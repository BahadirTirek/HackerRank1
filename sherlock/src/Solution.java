import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

class Result {

    public static String isValid(String s) {

        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : s.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }
        Map<Integer, Integer> frequencyCount = new HashMap<>();
        for (int frequency : charFrequency.values()) {
            frequencyCount.put(frequency, frequencyCount.getOrDefault(frequency, 0) + 1);
        }

        if (frequencyCount.size() == 1) {
            return "YES";
        }
        if (frequencyCount.size() == 2) {

            Iterator<Integer> iterator = frequencyCount.keySet().iterator();
            int frequency1 = iterator.next();
            int frequency2 = iterator.next();


            if ((frequencyCount.get(frequency1) == 1 && (frequency1 == 1 || Math.abs(frequency1 - frequency2) == 1)) || (Math.abs(frequency1 - frequency2) == 1 && frequencyCount.get(frequency2) == 1)) {
                return "YES";
            }
        } return "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
