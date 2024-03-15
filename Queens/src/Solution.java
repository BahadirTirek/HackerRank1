import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.*;

class Result {

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {

        int[] dirCounts = new int[8];

        int up = n - r_q;
        int down = r_q - 1;
        int left = c_q - 1;
        int right = n - c_q;
        int upLeft = Math.min(up, left);
        int upRight = Math.min(up, right);
        int downLeft = Math.min(down, left);
        int downRight = Math.min(down, right);

        for (List<Integer> obstacle : obstacles) {
            int r = obstacle.get(0);
            int c = obstacle.get(1);

            if (r == r_q) {
                if (c < c_q) {
                    left = Math.min(left, c_q - c - 1);
                } else {
                    right = Math.min(right, c - c_q - 1);
                }
            } else if (c == c_q) {
                if (r < r_q) {
                    down = Math.min(down, r_q - r - 1);
                } else {
                    up = Math.min(up, r - r_q - 1);
                }
            } else if (Math.abs(r - r_q) == Math.abs(c - c_q)) {
                if (r > r_q) {
                    if (c < c_q) {
                        downLeft = Math.min(downLeft, r - r_q - 1);
                    } else {
                        downRight = Math.min(downRight, r - r_q - 1);
                    }
                } else {
                    if (c < c_q) {
                        upLeft = Math.min(upLeft, r_q - r - 1);
                    } else {
                        upRight = Math.min(upRight, r_q - r - 1);
                    }
                }
            }
        }

        dirCounts[0] = up;
        dirCounts[1] = down;
        dirCounts[2] = left;
        dirCounts[3] = right;
        dirCounts[4] = upLeft;
        dirCounts[5] = upRight;
        dirCounts[6] = downLeft;
        dirCounts[7] = downRight;

        int total = 0;
        for (int count : dirCounts) {
            total += count;
        }

        return total;
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
