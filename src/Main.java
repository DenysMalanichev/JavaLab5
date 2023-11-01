import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // C17 = 3 (В усіх питальних реченнях заданого тексту знайти та надрукувати без
    //повторень слова заданої довжини.)
    public static void main(String[] args) {
        Text text = new Text("This is test line. Wait,    is it long  long enough  long? I guess yes!");
        int num = 4;

        System.out.println("Use predefined data?");
        char ans = getAns();

        if (ans == 'y') {
            System.out.println("Entered text: \n" + text);
            System.out.println("Words length to find: " + num);
            System.out.println("Result:");
            System.out.println(text.getDistinctWords(num));
        } else {
            System.out.println("Enter your text, then number of character. This program will retrieve words with given length from question sentences in text.");
            System.out.println("Text: ");
            text = new Text(getText());

            System.out.println("Number: ");
            num = getNum();

            System.out.println(text.getDistinctWords(num));
        }
    }

    private static char getAns() {
        char ans = 0;
        boolean validInput;
        Scanner sc = new Scanner(System.in);
        do {
            validInput = true;
            try {
                System.out.print("Enter 'y' or 'n': ");
                ans = sc.nextLine().charAt(0);
                if (ans != 'y' && ans != 'n') {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    validInput = false;
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Answer must not be empty");
                validInput = false;
            }
        } while (!validInput);

        return ans;
    }

    private static String getText() {
        String ans;
        boolean validInput;
        Scanner sc = new Scanner(System.in);
        do {
            validInput = true;
            ans = sc.nextLine();
            if (ans == null || ans.isEmpty()) {
                System.out.println("Invalid input. Please enter some text.");
                validInput = false;
            }
        } while (!validInput);

        return ans;
    }

    private static int getNum() {
        int ans = 0;
        boolean validInput;
        Scanner sc = new Scanner(System.in);
        do {
            validInput = true;
            try {
                ans = sc.nextInt();
                if (ans < 1) {
                    System.out.println("Too short. Please enter number that is equal or greater than 1.");
                    validInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter number that is equal or greater than 1.");
                validInput = false;
                sc.nextLine();
            }
        } while (!validInput);

        return ans;
    }
}