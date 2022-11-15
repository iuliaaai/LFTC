import domain.FiniteAutomata;
import domain.Scannerr;

import java.util.Scanner;

public class Main {

    private static void printMenu(){
        System.out.println("1. Show states");
        System.out.println("2. Show alphabet");
        System.out.println("3. Show final states");
        System.out.println("4. Show transitions");
        System.out.println("5. Check if a sequence is accepted by the FA");
        System.out.println("6. Run scanner");
        System.out.println("0. Exit");
    }

    public static void main(String[] args){
        FiniteAutomata fa = new FiniteAutomata("src/resources/FA.txt");

        printMenu();
        System.out.println("Your option: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        while(option != 0) {
            switch (option) {
                case 1 -> System.out.println(fa.states.toString());
                case 2 -> System.out.println(fa.alphabet.toString());
                case 3 -> System.out.println(fa.finalStates.toString());
                case 4 -> System.out.println(fa.printTransitions());
                case 5 -> {
                    if (fa.checkIfDFA()) {
                        System.out.println("Your sequence: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String sequence = scanner2.nextLine();

                        if (fa.checkSequence(sequence))
                            System.out.println("Sequence is valid");
                        else
                            System.out.println("Invalid sequence");
                    } else {
                        System.out.println("FA is not deterministic.");
                    }
                }
                case 6 -> runScanner();
            }
            System.out.println();
            printMenu();
            System.out.println("Your option: ");
            option = scanner.nextInt();
        }
    }

    private static void runScanner(){
        Scannerr scannerP1 = new Scannerr("src/resources/p1.txt",
                "src/resources/st1.txt", "src/resources/pif1.txt");
        scannerP1.scanFile();
    }
}
