import animals.Util;

import java.util.ListResourceBundle;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class App_eo extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"goodMorning", "Bonan matenon!"},
                {"goodAfternoon", "Bonan posttagmezon!"},
                {"goodEvening", "Bonan vesperon!"},
                {"learnAboutAnimals", "Mi volas lerni pri bestoj."},
                {"likeMost", "Kiun beston vi plej ŝatas?"},
                {"welcome", "Bonvenon al la sperta sistemo de la besto!"},
                {"menu", "Kion vi volas fari:\n" +
                        "\n" +
                        "1. Ludi la divenludon\n" +
                        "2. Listo de ĉiuj bestoj\n" +
                        "3. Serĉi beston\n" +
                        "4. Kalkuli statistikojn\n" +
                        "5. Presu la Sciarbon\n" +
                        "0. Eliri"},
                {"thinkOfAnimal", "Vi pensu pri besto, kaj mi divenos ĝin."},
                {"pressEnter", "Premu enen kiam vi pretas."},
                {"isit", "Ĉu ĝi estas "},
                {"animal.name", (UnaryOperator<String>) name -> name},
                {"giveUp", "Mi rezignas. Kiun beston vi havas en la kapo?"},
                {"specifyFact", "Indiku fakton, kiu distingas "},
                {"from", " de "},
                {"shouldBeFormat", "La frazo estu laŭ la formato: 'Ĝi ...'"},
                {"isCorrectFor", "Ĉu la aserto ĝustas por la "},
                {"unsureResponses", new String[] {
                        "Mi ne certas, ke mi kaptis vin: ĉu jes aŭ ne?",
                        "Amuza, mi ankoraŭ ne komprenas, ĉu jes aŭ ne?",
                        "Ho, ĝi estas tro komplika por mi: nur diru al mi jes aŭ ne.",
                        "Ĉu vi bonvolus simple diri jes aŭ ne?",
                        "Ho, ne, ne provu konfuzi min: diru jes aŭ ne."
                }},
                {"won", "Mi gajnis!"},
                {"factCorrect", (Function<String, Boolean>) fact -> {
                    fact = fact.toLowerCase().trim();
                    return fact.startsWith("ĝi");
                }},
                {"statementExamples", "La ekzemploj de aserto:\n" +
                        "- Ĝi povas flugi\n" +
                        "- Ĝi havas kornon\n" +
                        "- Ĝi estas mamulo"},
                {"animalWithoutArticle", (UnaryOperator<String>) animal -> animal},
                {"can", "povas"},
                {"has", "havas"},
                {"is", "estas"},
                {"the", "La "},
                {"cannot", "ne povas "},
                {"canIt", "Ĉu ĝi povas "},
                {"doesNotHave", "ne havas "},
                {"doesItHave", "Ĉu ĝi havas "},
                {"isNot", "ne estas "},
                {"learnedFacts", "Mi lernis la jenajn faktojn pri bestoj:"},
                {"distinguishAnimal", "Mi povas distingi ĉi tiujn bestojn per la demando:"},
                {"learnedAboutAnimal", "Bela! Mi lernis tiom multe pri bestoj!"},
                {"playAgain", "\nĈu vi ŝatus ludi denove?"},
                {"it", "Ĝi "},
                {"animalsKnown", "Jen la bestoj, kiujn mi konas:"},
                {"enterAnimal", "Enigu la nomon de besto:"},
                {"noFactsAboutAnimal", "Neniuj faktoj pri la "},
                {"factsAboutAnimal", "Faktoj pri la "},
                {"knowledgeTree", "La statistiko de la Scio-Arbo\n"},
                {"rootNode", "- radika nodo             "},
                {"totalNodes", "- nombro de nodoj         "},
                {"numAnimals", "- nombro de bestoj        "},
                {"numStatements", "- nombro de deklaroj      "},
                {"height", "- alteco de la arbo       "},
                {"minDepth", "- minimuma profundo       "},
                {"avgDepth", "- averaĝa profundo        "},
                {"notCorrectOption", "Ne ĝusta opcio!"},
                {"goodbyeResponses", new String[] {
                        "Ĝis baldaŭ!",
                        "Ĝis revido!"
                }},
                {"isEo", true},
                {"fileName", "animals_eo"}
        };
    }
}
