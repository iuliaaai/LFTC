package domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scannerr {
    private final LanguageSpecification ls = new LanguageSpecification();
    private final int capacity = 16;
    private final SymbolTable symbolTable = new SymbolTable(capacity);
    private final ProgramInternalForm pif = new ProgramInternalForm();

    private final String programFile;
    private final String symbolTableFile;
    private final String pifFile;

    public Scannerr(String programFile, String symbolTableFile, String pifFile) {
        this.programFile = programFile;
        this.symbolTableFile = symbolTableFile;
        this.pifFile = pifFile;
    }

    public void scanFile() {
        List<Pair<String, Integer>> pairs = new ArrayList<>();
        try {
            File file = new File(programFile);
            Scanner reader = new Scanner(file);

            int lineCount = 1;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                List<String> tokens = tokenize(line);

                for (String token : tokens) {
                    pairs.add(new Pair<>(token, lineCount));
                }

                lineCount += 1;
            }

            reader.close();

//            if (buildResult(pairs)) {
//                writeToFile();
//            }
            buildResult(pairs);
            writeToFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> tokenize(String line) {
        ArrayList<String> tokens = new ArrayList<>();

        for (int i = 0; i < line.length(); ++i) {
            if (ls.isSeparator(String.valueOf(line.charAt(i))) && !(String.valueOf(line.charAt(i))).equals(" ")) {
                tokens.add(String.valueOf(line.charAt(i)));
            } else if (line.charAt(i) == '\"') {
                String constant = getStringConstant(line, i);
                tokens.add(constant);
                i += constant.length() - 1;
            } else if (line.charAt(i) == '\'') {
                String constant = getCharConstant(line, i);
                tokens.add(constant);
                i += constant.length() - 1;
            } else if (line.charAt(i) == '-') {
                String token = getMinusToken(line, i, tokens);
                tokens.add(token);
                i += token.length() - 1;
            } else if (ls.isPartOfOperator(line.charAt(i))) {
                String operator = getOperator(line, i);
                tokens.add(operator);
                i += operator.length() - 1;
            } else if (line.charAt(i) != ' ') {
                String token = getToken(line, i);
                tokens.add(token);
                i += token.length() - 1;
            }
        }
        return tokens;
    }

    public String getStringConstant(String line, int position) {
        StringBuilder constant = new StringBuilder();

        for (int i = position; i < line.length(); ++i) {
            if ((ls.isSeparator(String.valueOf(line.charAt(i))) || ls.isOperator(String.valueOf(line.charAt(i)))) && ((i == line.length() - 2 && line.charAt(i + 1) != '\"') || (i == line.length() - 1)))
                break;
            constant.append(line.charAt(i));
            if (line.charAt(i) == '\"' && i != position)
                break;
        }

        return constant.toString();
    }

    public String getCharConstant(String line, int position) {
        StringBuilder constant = new StringBuilder();

        for (int i = position; i < line.length(); ++i) {
            if ((ls.isSeparator(String.valueOf(line.charAt(i))) || ls.isOperator(String.valueOf(line.charAt(i)))) && ((i == line.length() - 2 && line.charAt(i + 1) != '\'') || (i == line.length() - 1)))
                break;
            constant.append(line.charAt(i));
            if (line.charAt(i) == '\'' && i != position)
                break;
        }

        return constant.toString();
    }

    public String getMinusToken(String line, int position, ArrayList<String> tokens) {
        //minus is preceded by an identifier, constant -> minus is an operator
        if (ls.isIdentifier(tokens.get(tokens.size() - 1)) || ls.isConstant(tokens.get(tokens.size() - 1))) {
            return "-";
        }

        //minus is preceded by operator or separator -> assign a negative number or condition with negative number -> minus belongs to a numeric constant
        StringBuilder token = new StringBuilder();
        token.append('-');

        for (int i = position + 1; i < line.length() && (Character.isDigit(line.charAt(i))); ++i) {
            token.append(line.charAt(i));
        }

        return token.toString();
    }

    public String getOperator(String line, int position) {
        StringBuilder operator = new StringBuilder();
        operator.append(line.charAt(position));
        operator.append(line.charAt(position + 1));

        if (ls.isOperator(operator.toString()))
            return operator.toString();

        return String.valueOf(line.charAt(position));
    }

    public String getToken(String line, int position) {
        StringBuilder token = new StringBuilder();

        for (int i = position; i < line.length()
                && !ls.isSeparator(String.valueOf(line.charAt(i)))
                && !ls.isPartOfOperator(line.charAt(i))
                && line.charAt(i) != ' '; ++i) {
            token.append(line.charAt(i));
        }

        return token.toString();
    }

    public boolean buildResult(List<Pair<String, Integer>> tokens) {
        List<String> invalidTokens = new ArrayList<>();
        boolean lexicalError = false;
        for (Pair<String, Integer> tokenPair : tokens) {
            String token = tokenPair.getKey();

            if (ls.isOperator(token) || ls.isReservedWord(token) || ls.isSeparator(token)) {
                pif.add(token, new Pair<>(new Pair<>(-1, -1), ls.getCode(token)));
            } else if (ls.isIdentifier(token)) {
                symbolTable.add(token);
                Pair<Integer, Integer> pos = symbolTable.getPosition(token);
                pif.add(token, new Pair<>(pos, 1));
            } else if (ls.isConstant(token)) {
                symbolTable.add(token);
                Pair<Integer, Integer> pos = symbolTable.getPosition(token);
                pif.add(token, new Pair<>(pos, 0));
            } else if (!invalidTokens.contains(token)) {
                invalidTokens.add(token);
                lexicalError = true;
                System.out.println("ERROR: invalid token " + token + " -> at line " + tokenPair.getValue());
            }
        }

        if (!lexicalError) {
            System.out.println("The program is lexically correct");
            return true;
        }
        return false;
    }

    public void writeToFile() {
        try {
            File symbolTableFile = new File(this.symbolTableFile);
            FileWriter symbolTableFileWriter = new FileWriter(symbolTableFile, false);
            BufferedWriter symbolTableWriter = new BufferedWriter(symbolTableFileWriter);
            symbolTableWriter.write(symbolTable.toString());
            symbolTableWriter.close();

            File pifFile = new File(this.pifFile);
            FileWriter pifFileWriter = new FileWriter(pifFile, false);
            BufferedWriter pifTableWriter = new BufferedWriter(pifFileWriter);
            pifTableWriter.write(pif.toString());
            pifTableWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
