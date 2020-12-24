package animals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Answer {

    private static final String[] positiveAnswers = {"y", "yes", "yeah", "yep", "sure", "right",
            "affirmative", "correct", "indeed", "you bet", "exactly", "you said it", "jes"};

    private static final String[] negativeAnswers = {"n", "no", "no way", "nah", "nope", "negative",
            "I don't think so", "yeah no", "ne"};

    public static AnswerType findAnswerType(String answer) {
        answer = answer.trim();

        for (String positiveAnswer : positiveAnswers) {
            Pattern pattern = Pattern.compile(positiveAnswer + "([!.])?", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(answer);
            if (matcher.matches()) {
                return AnswerType.POSITIVE;
            }
        }

        for (String negativeAnswer : negativeAnswers) {
            Pattern pattern = Pattern.compile(negativeAnswer + "([!.])?", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(answer);
            if (matcher.matches()) {
                return AnswerType.NEGATIVE;
            }
        }

        return AnswerType.UNCLEAR;
    }

}
