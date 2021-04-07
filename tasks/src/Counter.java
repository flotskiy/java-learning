import java.util.HashMap;
import java.util.Map;

public class Counter {
    public static void main(String[] args) {
        String[] array = new String [] {
                "cat", "mouse", "dog", "crocodile", "tiger", "deer",
                "cat", "mouse", "wolf", "cat", "dog", "dog", "mouse",
                "dog", "dog"
        };

        Map<String, Integer> words = new HashMap<>();

        for (String word : array) {
            addWord(words, word);
        }

        System.out.println(words);
    }

    public static boolean addWord(Map<String, Integer> wordsMap, String word) {
        if (wordsMap.containsKey(word)) {
            Integer value = wordsMap.get(word);
            value++;
            wordsMap.put(word, value);
            return false;
        }
        wordsMap.put(word, 1);
        return true;
    }
}
