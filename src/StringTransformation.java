import java.util.*;

public class StringTransformation {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord))
            return 0;

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String word = queue.poll();

                if (word.equals(endWord))
                    return level;

                for (String next : generateNeighbors(word, dict)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            level++;
        }

        return 0;
    }

    // Generate all valid one-letter transformations
    private List<String> generateNeighbors(String word, Set<String> dict) {

        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            char original = chars[i];

            for (char c = 'a'; c <= 'z'; c++) {

                if (c == original) continue;

                chars[i] = c;
                String newWord = new String(chars);

                if (dict.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }

            chars[i] = original;
        }

        return neighbors;
    }
}