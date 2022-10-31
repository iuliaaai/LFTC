import domain.Scannerr;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Program 1");
        Scannerr scannerP1 = new Scannerr("src/resources/p1.txt",
                "src/resources/st1.txt", "src/resources/pif1.txt");
        scannerP1.scanFile();

        System.out.println();
        System.out.println("Program 2");
        Scannerr scannerP2 = new Scannerr("src/resources/p2.txt",
                "src/resources/st2.txt", "src/resources/pif2.txt");
        scannerP2.scanFile();

        System.out.println();
        System.out.println("Program 3");
        Scannerr scannerP3 = new Scannerr("src/resources/p3.txt",
                "src/resources/st3.txt", "src/resources/pif3.txt");
        scannerP3.scanFile();

        System.out.println();
        System.out.println("Program 1 error");
        Scannerr scannerP1err = new Scannerr("src/resources/p1err.txt",
                "src/resources/st1err.txt", "src/resources/pif1err.txt");
        scannerP1err.scanFile();
    }
}
