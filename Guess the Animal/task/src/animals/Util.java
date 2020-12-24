package animals;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Util {

    public static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("App");

    private static final List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');

    public static String generateAnimalName(String animal) {
        animal = animal.toLowerCase().trim();

        String[] words = animal.split(" ");
        if (words.length >= 2 && (words[0].equals("a") || words[0].equals("an"))) {
            return animal;
        } else if (words.length >= 1) {
            if (vowels.contains(words[0].charAt(0))) {
                return "an " + animal;
            } else {
                return "a " + animal;
            }
        } else {
            return "";
        }
    }

    public static String generateAnimalWithoutArticle(String animalWithArticle) {
        animalWithArticle = animalWithArticle.toLowerCase().trim();

        if (animalWithArticle.startsWith("a") || animalWithArticle.startsWith("an")) {
            String[] words = animalWithArticle.split(" ");
            if (words.length < 2) {
                return animalWithArticle;
            } else {
                StringBuilder animalName = new StringBuilder();
                for (int i = 1; i < words.length; i++) {
                    animalName.append(words[i]);

                    if (i != words.length - 1) {
                        animalName.append(" ");
                    }
                }
                return animalName.toString();
            }
        } else {
            return animalWithArticle;
        }
    }

    public static String questionToSentence(String question, boolean isTrue) throws Exception {
        question = question.toLowerCase().trim();
        StringBuilder sb = new StringBuilder(Util.RESOURCE_BUNDLE.getString("it"));
        String[] words = question.split(" ");

        if ((Boolean) Util.RESOURCE_BUNDLE.getObject("isEo")) {
            if (isTrue) {
                sb.append(words[2]).append(" ");
            } else {
                sb.append("ne ").append(words[2]).append(" ");
            }

            for (int i = 3; i < words.length; i++) {
                sb.append(words[i]);

                if (i != words.length - 1) {
                    sb.append(" ");
                }
            }
        } else if (question.startsWith(Util.RESOURCE_BUNDLE.getString("isit").toLowerCase())) {
            if (isTrue) {
                sb.append(Util.RESOURCE_BUNDLE.getString("is")).append(" ");
            } else {
                sb.append(Util.RESOURCE_BUNDLE.getString("isNot")).append(" ");
            }

            for (int i = 2; i < words.length; i++) {
                sb.append(words[i]);

                if (i != words.length - 1) {
                    sb.append(" ");
                }
            }
        } else if (question.startsWith(Util.RESOURCE_BUNDLE.getString("canIt").toLowerCase())) {
            if (isTrue) {
                sb.append(Util.RESOURCE_BUNDLE.getString("can")).append(" ");
            } else {
                sb.append(Util.RESOURCE_BUNDLE.getString("cannot")).append(" ");
            }

            for (int i = 2; i < words.length; i++) {
                sb.append(words[i]);

                if (i != words.length - 1) {
                    sb.append(" ");
                }
            }
        } else if (question.startsWith(Util.RESOURCE_BUNDLE.getString("doesItHave").toLowerCase())) {
            if (isTrue) {
                sb.append(Util.RESOURCE_BUNDLE.getString("has")).append(" ");
            } else {
                sb.append(Util.RESOURCE_BUNDLE.getString("doesNotHave")).append(" ");
            }

            for (int i = 3; i < words.length; i++) {
                sb.append(words[i]);

                if (i != words.length - 1) {
                    sb.append(" ");
                }
            }
        } else {
            throw new Exception("Not a correct question");
        }

        return sb.toString().replace("?", "");
    }

}
