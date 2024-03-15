import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {

    public static long gridlandMetro(int n, int m, int k, List<List<Integer>> tracks) {
        long total = (long) n * m;
        if (k == 0) {
            return total;
        }

        Map<Integer, List<List<Integer>>> trackMap = new HashMap<>();
        for (List<Integer> track : tracks) {
            int row = track.get(0);
            trackMap.computeIfAbsent(row, key -> new ArrayList<>()).add(track);
        }

        for (Map.Entry<Integer, List<List<Integer>>> entry : trackMap.entrySet()) {
            List<List<Integer>> mergedTracks = mergeTracks(entry.getValue());
            for (List<Integer> track : mergedTracks) {
                total -= (long) (track.get(2) - track.get(1) + 1);
            }
        }

        return total;
    }

    private static List<List<Integer>> mergeTracks(List<List<Integer>> tracks) {
        Collections.sort(tracks, Comparator.comparingInt(t -> t.get(1)));

        List<List<Integer>> mergedTracks = new ArrayList<>();
        mergedTracks.add(tracks.get(0));

        for (int i = 1; i < tracks.size(); i++) {
            List<Integer> last = mergedTracks.get(mergedTracks.size() - 1);
            List<Integer> track = tracks.get(i);

            if (last.get(2) >= track.get(1)) {
                last.set(2, Math.max(last.get(2), track.get(2)));
            } else {
                mergedTracks.add(track);
            }
        }

        return mergedTracks;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int m = Integer.parseInt(firstMultipleInput[1]);
        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> track = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                track.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.gridlandMetro(n, m, k, track);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
