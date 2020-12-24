import animals.Response;
import animals.Util;

import java.util.ListResourceBundle;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class App extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"goodMorning", "Good morning!"},
                {"goodAfternoon", "Good afternoon!"},
                {"goodEvening", "Good evening!"},
                {"learnAboutAnimals", "I want to learn about animals."},
                {"likeMost", "Which animal do you like most?"},
                {"welcome", "Welcome to the animals expert system!"},
                {"menu", "What do you want to do:\n" +
                        "\n" +
                        "1. Play the guessing game\n" +
                        "2. List of all animals\n" +
                        "3. Search for an animal\n" +
                        "4. Calculate statistics\n" +
                        "5. Print the Knowledge Tree\n" +
                        "0. Exit"},
                {"thinkOfAnimal", "You think of an animal, and I guess it."},
                {"pressEnter", "Press enter when you're ready."},
                {"isit", "Is it "},
                {"animal.name", (UnaryOperator<String>) Util::generateAnimalName},
                {"giveUp", "I give up. What animal do you have in mind?"},
                {"specifyFact", "Specify a fact that distinguishes "},
                {"from", " from "},
                {"shouldBeFormat", "The sentence should be of the format: 'It can/has/is ...'"},
                {"isCorrectFor", "Is the statement correct for "},
                {"unsureResponses", new String[] {
                        "I'm not sure I caught you: was it yes or no?",
                        "Funny, I still don't understand, is it yes or no?",
                        "Oh, it's too complicated for me: just tell me yes or no.",
                        "Could you please simply say yes or no?",
                        "Oh, no, don't try to confuse me: say yes or no."
                }},
                {"won", "I won!"},
                {"factCorrect", (Function<String, Boolean>) fact -> {
                    fact = fact.toLowerCase().trim();
                    return fact.startsWith("it is") || fact.startsWith("it can") || fact.startsWith("it has");
                }},
                {"statementExamples", "The examples of a statement:\n" +
                        " - It can fly\n" +
                        " - It has horn\n" +
                        " - It is a mammal"},
                {"animalWithoutArticle", (UnaryOperator<String>) Util::generateAnimalWithoutArticle},
                {"can", "can"},
                {"has", "has"},
                {"is", "is"},
                {"the", "The "},
                {"cannot", "can't "},
                {"canIt", "Can it "},
                {"doesNotHave", "doesn't have "},
                {"doesItHave", "Does it have "},
                {"isNot", "isn't "},
                {"learnedFacts", "I learned the following facts about animals:"},
                {"distinguishAnimal", "I can distinguish these animals by asking the question:"},
                {"learnedAboutAnimal", "Nice! I've learned so much about animals!"},
                {"playAgain", "\nWould you like to play again?"},
                {"it", "It "},
                {"animalsKnown", "Here are the animals I know:"},
                {"enterAnimal", "Enter the animal:"},
                {"noFactsAboutAnimal", "No facts about the "},
                {"factsAboutAnimal", "Facts about the "},
                {"knowledgeTree", "The Knowledge Tree stats\n"},
                {"rootNode", "- root node                    "},
                {"totalNodes", "- total number of nodes        "},
                {"numAnimals", "- total number of animals      "},
                {"numStatements", "- total number of statements   "},
                {"height", "- height of the tree           "},
                {"minDepth", "- minimum depth                "},
                {"avgDepth", "- average depth                "},
                {"goodbyeResponses", new String[] {
                        "Have a nice day!",
                        "See you soon!",
                        "Bye!"
                }},
                {"isEo", false},
                {"fileName", "animals"}
        };
    }
}
