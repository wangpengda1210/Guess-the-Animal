package animals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Response {

    private static final String[] unsureResponses = Util.RESOURCE_BUNDLE.getStringArray("unsureResponses");

    private static final String[] goodbyeResponses = Util.RESOURCE_BUNDLE.getStringArray("goodbyeResponses");

    public static String getCurrentTime() {
        LocalTime time = LocalTime.now();

        if (time.isAfter(LocalTime.of(5, 0)) && time.isBefore(LocalTime.of(12, 0))) {
            return Util.RESOURCE_BUNDLE.getString("goodMorning");
        } else if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(18, 0))) {
            return Util.RESOURCE_BUNDLE.getString("goodAfternoon");
        } else {
            return Util.RESOURCE_BUNDLE.getString("goodEvening");
        }
    }

    public static String getUnsureResponse() {
        Random random = new Random();
        return unsureResponses[random.nextInt(unsureResponses.length)];
    }

    public static String getGoodbyeResponse() {
        Random random = new Random();
        return goodbyeResponses[random.nextInt(goodbyeResponses.length)];
    }

    @SuppressWarnings("unchecked")
    public static List<String> generateLearnedFact(String fact, String correctAnimal, String incorrectAnimal) {
        UnaryOperator<String> withoutArticle =
                (UnaryOperator<String>) Util.RESOURCE_BUNDLE.getObject("animalWithoutArticle");

        correctAnimal = correctAnimal.toLowerCase().trim();
        correctAnimal = withoutArticle.apply(correctAnimal);
        incorrectAnimal = incorrectAnimal.toLowerCase().trim();
        incorrectAnimal = withoutArticle.apply(incorrectAnimal);
        fact = fact.toLowerCase().trim();

        ArrayList<String> facts = new ArrayList<>();

        String[] words = fact.split(" ");
        StringBuilder factBuilder = new StringBuilder();

        for (int i = 2; i < words.length; i++) {
            factBuilder.append(words[i]);
            if (i != words.length - 1) {
                factBuilder.append(" ");
            }
        }

        String can = Util.RESOURCE_BUNDLE.getString("can");
        String has = Util.RESOURCE_BUNDLE.getString("has");
        String is = Util.RESOURCE_BUNDLE.getString("is");

        if (words[1].equals(can)) {
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    correctAnimal + " " + can + " " + factBuilder);
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    incorrectAnimal + " " + Util.RESOURCE_BUNDLE.getString("cannot") + factBuilder);
            facts.add(Util.RESOURCE_BUNDLE.getString("canIt") + factBuilder);
        } else if (words[1].equals(has)) {
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    correctAnimal + " " + has + " " + factBuilder);
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    incorrectAnimal + " " + Util.RESOURCE_BUNDLE.getString("doesNotHave") + factBuilder);
            facts.add(Util.RESOURCE_BUNDLE.getString("doesItHave") + factBuilder);
        } else if (words[1].equals(is)) {
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    correctAnimal + " " + is + " " + factBuilder);
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    incorrectAnimal + " " + Util.RESOURCE_BUNDLE.getString("isNot") + factBuilder);
            facts.add(Util.RESOURCE_BUNDLE.getString("isit") + factBuilder);
        } else if ((Boolean) Util.RESOURCE_BUNDLE.getObject("isEo")) {
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    correctAnimal + " " + words[1] + " " + factBuilder);
            facts.add(Util.RESOURCE_BUNDLE.getString("the") +
                    incorrectAnimal + " ne " + words[1] + " " + factBuilder);
            facts.add("Ĉu ĝi " + words[1] + " " + factBuilder);
        }

        return facts;
    }

}
