package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LanguageSpecification {
    private List<String> reservedWords = new ArrayList<>();
    private List<String> operators = new ArrayList<>();
    private List<String> separators = new ArrayList<>();

    private final HashMap<String, Integer> codification = new HashMap<>();

    private final FiniteAutomata identifier = new FiniteAutomata("src/resources/identifier.txt");
    private final FiniteAutomata integerConstant = new FiniteAutomata("src/resources/integerConstant.txt");

    public LanguageSpecification() {
        readFromFile();
        createCodification();
    }

    private void readFromFile() {
        try {
            File file = new File("src/resources/token.txt");
            Scanner reader = new Scanner(file);

            int lineCount = 1;
            while (lineCount <= 14) {
                String token = reader.nextLine();
                operators.add(token);

                lineCount += 1;
            }
            while (lineCount <= 23) {
                String token = reader.nextLine();
                separators.add(token);

                lineCount += 1;
            }
            while (lineCount <= 41) {
                String token = reader.nextLine();
                reservedWords.add(token);

                lineCount += 1;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createCodification() {
        codification.put("constant", 0);
        codification.put("identifier", 1);

        int code = 2;

        for (String reservedWord : reservedWords) {
            codification.put(reservedWord, code);
            code++;
        }

        for (String operator : operators) {
            codification.put(operator, code);
            code++;
        }

        for (String separator : separators) {
            codification.put(separator, code);
            code++;
        }
    }

    public boolean isReservedWord(String token) {
        return reservedWords.contains(token);
    }

    public boolean isOperator(String token) {
        return operators.contains(token);
    }

    public boolean isPartOfOperator(char op) {
        return isOperator(String.valueOf(op));
    }

    public boolean isSeparator(String token) {
        return separators.contains(token);
    }

    public boolean isIdentifier(String token) {
//        String pattern = "^[a-zA-Z]([a-z|A-Z|0-9|_])*$";
//        return token.matches(pattern);
        return identifier.checkSequence(token);
    }

    public boolean isConstant(String token) {
        String numericPattern = "^0|[+|-][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
        String charPattern = "^\'[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]\'";
        String stringPattern = "^\"[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]+\"";
//        return token.matches(numericPattern) ||
//                token.matches(charPattern) ||
//                token.matches(stringPattern);
        return integerConstant.checkSequence(token) || token.matches(charPattern) || token.matches(stringPattern);
    }

    public Integer getCode(String token) {
        return codification.get(token);
    }
}
