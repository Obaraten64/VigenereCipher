public class VigenereCipher {
    private static String alphabet = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";

    public static String code(String str, String key, String language) {
        str = VigenereCipher.formatText(str);
        key = VigenereCipher.formatText(key);
        int keyLength = key.length();
        StringBuilder sb = new StringBuilder();

        if (language.equals("English")) {
            for (int i = 0; i < str.length(); i++) {
                int symbNumber = (str.charAt(i)) + (key.charAt(i % keyLength) - 'a');
                if (symbNumber > 'z') {
                    symbNumber = symbNumber - 'z' - 1 + 'a';
                }
                sb.append((char) symbNumber);
            }
        } else if (language.equals("Ukraine")) {
            for (int i = 0; i < str.length(); i++) {
                int symbNumber = (alphabet.indexOf(str.charAt(i))
                        + alphabet.indexOf(key.charAt(i % keyLength))) % alphabet.length();
                sb.append(alphabet.charAt(symbNumber));
            }
        } else { return "ERROR: The language is not selected"; }

        return sb.toString();
    }

    public static String decode(String str, String key, String language) {
        str = VigenereCipher.formatText(str);
        key = VigenereCipher.formatText(key);
        int keyLength = key.length();
        StringBuilder sb = new StringBuilder();

        if (language.equals("English")) {
            for (int i = 0; i < str.length(); i++) {
                int symbNumber = (str.charAt(i)) - (key.charAt(i % keyLength) - 'a');
                if (symbNumber < 'a') {
                    symbNumber = symbNumber - 'a' + 1 + 'z';
                }
                sb.append((char) symbNumber);
            }
        } else if (language.equals("Ukraine")) {
            for (int i = 0; i < str.length(); i++) {
                System.out.println(str.length());
                int symbNumber = (alphabet.indexOf(str.charAt(i)) + 33
                        - alphabet.indexOf(key.charAt(i % keyLength))) % alphabet.length();
                sb.append(alphabet.charAt(symbNumber));
            }
        } else { return "ERROR: The language is not selected"; }

        return sb.toString();
    }

    public static String formatText(String str) {
        return str.toLowerCase().replaceAll("[^a-zа-яіїєґ]", "");
    }
}
