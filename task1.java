for(char c : result) {
        System.out.print(c + " ");
    }
}

public static char[] removeDuplicates(char[] charArray) {
    Set<Character> set = new LinkedHashSet<>();

    for(char c : charArray) {
        set.add(c);
    }

    // Convert set back to char array
    char[] result = new char[set.size()];
    int i = 0;
    for(Character c : set) {
        result[i++] = c.charValue();
    }

    return result;
}
