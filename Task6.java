import java.util.*;

public class SpellChecker {
    private Set<String> lexicon;

    public SpellChecker(Set<String> lexicon) {
        this.lexicon = lexicon;
    }

    public List<String> check(String word) {
        if (lexicon.contains(word)) {
            return Collections.singletonList(word);
        }

        List<String> suggestions = new ArrayList<>();

        // Generate possible words and check if they are in the lexicon
        for (String s : generatePossibleWords(word)) {
            if (lexicon.contains(s)) {
                suggestions.add(s);
            }
        }

        return suggestions;
    }

    private List<String> generatePossibleWords(String word) {
        List<String> possibleWords = new ArrayList<>();

        // Swap adjacent characters
        for (int i = 0; i < word.length() - 1; i++) {
            char[] chars = word.toCharArray();
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            possibleWords.add(new String(chars));
        }

        // Insert a character
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String s = word.substring(0, i) + c + word.substring(i);
                possibleWords.add(s);
            }
        }

        // Delete a character
        for (int i = 0; i < word.length(); i++) {
            String s = word.substring(0, i) + word.substring(i + 1);
            possibleWords.add(s);
        }

        // Replace a character
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String s = word.substring(0, i) + c + word.substring(i + 1);
                possibleWords.add(s);
            }
        }

        return possibleWords;
    }
}

public class Main {
    public static void main(String[] args) {
        Set<String> lexicon = new HashSet<>(Arrays.asList("hello", "world", "java", "python", "spell", "check"));
        SpellChecker spellChecker = new SpellChecker(lexicon);

        System.out.println(spellChecker.check("hwllo"));
        System.out.println(spellChecker.check("javaa"));
        System.out.println(spellChecker.check("spel"));
    }
}
