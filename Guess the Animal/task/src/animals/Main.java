package animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.UnaryOperator;

@SuppressWarnings("unchecked")
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static BinaryTree animalTree;

    public static void main(String[] args) throws Exception {

        String fileName = Util.RESOURCE_BUNDLE.getString("fileName") + ".";
        ObjectMapper objectMapper = new JsonMapper();

        if (args.length == 2 && args[0].equals("-type")) {
            if (args[1].equals("xml")) {
                fileName += "xml";
                objectMapper = new XmlMapper();
            } else if (args[1].equals("yaml")) {
                fileName += "yaml";
                objectMapper = new YAMLMapper();
            } else {
                fileName += "json";
            }
        } else {
            fileName += "json";
        }

        System.out.println(Response.getCurrentTime() + "\n");

        File file = new File(fileName);
        if (file.exists()) {
            animalTree = new BinaryTree(objectMapper.readValue(file, BinaryTree.Node.class));
        } else {
            System.out.println(Util.RESOURCE_BUNDLE.getString("learnAboutAnimals"));
            System.out.println(Util.RESOURCE_BUNDLE.getString("likeMost"));
            animalTree = new BinaryTree(new BinaryTree.Node(scanner.nextLine().toLowerCase().trim()));
        }

        System.out.println("\n" + Util.RESOURCE_BUNDLE.getString("welcome") + "\n");

        mainLoop:while (true) {
            System.out.println(Util.RESOURCE_BUNDLE.getString("menu"));

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    guessGame();
                    break;
                case 2:
                    System.out.println(Util.RESOURCE_BUNDLE.getString("animalsKnown"));
                    List<String> allAnimals = animalTree.findAllLeavesValue();
                    Collections.sort(allAnimals);
                    for (String animal : allAnimals) {
                        System.out.println("- " + animal);
                    }
                    break;
                case 3:
                    System.out.println(Util.RESOURCE_BUNDLE.getString("enterAnimal"));
                    String animal = scanner.nextLine().toLowerCase().trim();
                    List<String> facts = animalTree.findFacts(animal);
                    if (facts.size() == 0) {
                        System.out.println(Util.RESOURCE_BUNDLE.getString("noFactsAboutAnimal") +
                                animal + ".");
                    } else {
                        System.out.println(Util.RESOURCE_BUNDLE.getString("factsAboutAnimal") +
                                animal + ":");
                        for (String fact : facts) {
                            System.out.println("- " + fact + ".");
                        }
                    }
                    break;
                case 4:
                    System.out.println(Util.RESOURCE_BUNDLE.getString("knowledgeTree"));
                    System.out.println(Util.RESOURCE_BUNDLE.getString("rootNode") +
                            Util.questionToSentence(animalTree.getRoot().getValue(), true));
                    System.out.println(Util.RESOURCE_BUNDLE.getString("totalNodes") +
                            animalTree.numNodes());
                    System.out.println(Util.RESOURCE_BUNDLE.getString("numAnimals") +
                            animalTree.numAnimals());
                    System.out.println(Util.RESOURCE_BUNDLE.getString("numStatements") +
                            (animalTree.numNodes() - animalTree.numAnimals()));
                    System.out.println(Util.RESOURCE_BUNDLE.getString("height") +
                            animalTree.getHeight());
                    System.out.println(Util.RESOURCE_BUNDLE.getString("minDepth") +
                            animalTree.getMinHeight());
                    System.out.println(Util.RESOURCE_BUNDLE.getString("avgDepth") +
                            String.format("%.1f", animalTree.getAverageHeight()));
                    break;
                case 5:
                    System.out.println(animalTree);
                    break;
                case 0:
                    break mainLoop;
                default:
                    System.out.println(Util.RESOURCE_BUNDLE.getString("notCorrectOption"));
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, animalTree.getRoot());
        System.out.println("\n" + Response.getGoodbyeResponse());
    }

    private static void guessGame() {
        answerLoop:while (true) {
            System.out.println(Util.RESOURCE_BUNDLE.getString("thinkOfAnimal"));
            System.out.println(Util.RESOURCE_BUNDLE.getString("pressEnter"));
            scanner.nextLine();

            guessAnimal(animalTree.getRoot());

            System.out.println(Util.RESOURCE_BUNDLE.getString("playAgain"));
            switch (Answer.findAnswerType(scanner.nextLine())) {
                case UNCLEAR:
                    System.out.println(Response.getUnsureResponse());
                    break;
                case POSITIVE:
                    continue answerLoop;
                case NEGATIVE:
                    break answerLoop;
            }
        }
    }

    private static void guessAnimal(BinaryTree.Node node) {

        if (!node.getValue().contains("?")) {
            System.out.println(Util.RESOURCE_BUNDLE.getString("isit") +
                    ((UnaryOperator<String>) Util.RESOURCE_BUNDLE.getObject("animal.name"))
                            .apply(node.getValue()) + "?");
        } else {
            System.out.println(node.getValue());
        }

        answerLoop:while (true) {
            AnswerType answer = Answer.findAnswerType(scanner.nextLine());

            switch (answer) {
                case UNCLEAR:
                    System.out.println(Response.getUnsureResponse());
                    break;
                case POSITIVE:
                    node = node.getRight();
                    if (node == null) {
                        System.out.println(Util.RESOURCE_BUNDLE.getString("won"));
                        return;
                    }
                    break answerLoop;
                case NEGATIVE:
                    if (node.getLeft() == null) {
                        learnAnimal(node);
                        return;
                    }
                    node = node.getLeft();
                    break answerLoop;
            }
        }

        guessAnimal(node);

    }

    private static void learnAnimal(BinaryTree.Node node) {

        String firstAnimal = ((UnaryOperator<String>) Util.RESOURCE_BUNDLE.getObject("animal.name"))
                .apply(node.getValue());
        System.out.println(Util.RESOURCE_BUNDLE.getString("giveUp"));
        String secondAnimal = ((UnaryOperator<String>) Util.RESOURCE_BUNDLE.getObject("animal.name"))
                .apply(scanner.nextLine());

        while (true) {
            System.out.println(Util.RESOURCE_BUNDLE.getString("specifyFact")
                    + firstAnimal + Util.RESOURCE_BUNDLE.getString("from") + secondAnimal + ".\n" +
                    Util.RESOURCE_BUNDLE.getString("shouldBeFormat") + ".\n");

            String fact = scanner.nextLine();
            if (((Function<String, Boolean>) Util.RESOURCE_BUNDLE
                    .getObject("factCorrect")).apply(fact)) {
                System.out.println(Util.RESOURCE_BUNDLE.getString("isCorrectFor") + secondAnimal + "?");

                while (true) {
                    AnswerType answer = Answer.findAnswerType(scanner.nextLine());

                    if (answer == AnswerType.UNCLEAR) {
                        System.out.println(Response.getUnsureResponse());
                    } else {
                        System.out.println(Util.RESOURCE_BUNDLE.getString("learnedFacts"));
                        List<String> learnedFact;
                        if (answer == AnswerType.POSITIVE) {
                            learnedFact = Response.generateLearnedFact(fact, secondAnimal, firstAnimal);
                            System.out.println("- " + learnedFact.get(1)
                                    .replaceAll("[?!.]", "") + ".");
                            System.out.println("- " + learnedFact.get(0)
                                    .replaceAll("[?!.]", "") + ".");

                            node.setLeft(new BinaryTree.Node(Util.generateAnimalWithoutArticle(firstAnimal)));
                            node.setRight(new BinaryTree.Node(Util.generateAnimalWithoutArticle(secondAnimal)));
                        } else {
                            learnedFact = Response.generateLearnedFact(fact, firstAnimal, secondAnimal);
                            System.out.println("- " + learnedFact.get(0)
                                    .replaceAll("[?!.]", "") + ".");
                            System.out.println("- " + learnedFact.get(1)
                                    .replaceAll("[?!.]", "") + ".");

                            node.setRight(new BinaryTree.Node(Util.generateAnimalWithoutArticle(firstAnimal)));
                            node.setLeft(new BinaryTree.Node(Util.generateAnimalWithoutArticle(secondAnimal)));
                        }

                        String question = learnedFact.get(2)
                                .replaceAll("[?!.]", "") + "?";
                        System.out.println(Util.RESOURCE_BUNDLE.getString("distinguishAnimal"));
                        System.out.println("- " + question);
                        node.setValue(question);
                        node.getLeft().setParentValue(question);
                        node.getLeft().setTureToParent(false);
                        node.getRight().setParentValue(question);
                        node.getRight().setTureToParent(true);

                        System.out.println(Util.RESOURCE_BUNDLE.getString("learnedAboutAnimal"));

                        break;
                    }
                }

                break;
            } else {
                System.out.println(Util.RESOURCE_BUNDLE.getString("statementExamples"));
            }
        }
    }

}
