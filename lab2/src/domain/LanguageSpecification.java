package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LanguageSpecification {
    private final List<String> reservedWords = Arrays.asList("Int", "Bool", "String", "Char", "func", "if", "elif", "else",
            "let", "var", "ret", "True", "False", "read", "print", "loop", "GO", "STOP");
    private final List<String> operators = Arrays.asList("+", "-", "*", "/", "%", "=", "==", "!=", "<", ">", "<=", ">=", "!", "?:");
    private final List<String> separators = Arrays.asList("(", ")", ";", "{", "}","[", "]", "#", "@");

    private final HashMap<String, Integer> codification = new HashMap<>();

    public LanguageSpecification() {
        createCodification();
    }

    private void createCodification() {
        int code = 0;

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
        String pattern = "^[a-zA-Z]([a-z|A-Z|0-9])*$";
        return token.matches(pattern);
    }

    public boolean isConstant(String token) {
        String numericPattern = "^0|-?[1-9]([0-9])*$";
        String charPattern = "^\'[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]\'";
        String stringPattern = "^\"[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]+\"";
        return token.matches(numericPattern) || token.matches(charPattern) || token.matches(stringPattern);
    }
}
