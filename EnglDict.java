import java.util.Scanner;
/**
 * Created by jasonhertz on 3/16/17.
 */

public class EnglDict {

    public static void main(String[] args) {
        Codex codex = new Codex();

        int counter =0;
        while (counter < 1000) {
            System.out.println("search ...");
            Scanner sc = new Scanner(System.in);
            String searchTerm = sc.nextLine();

            System.out.println("term found: " + codex.searchTerm(searchTerm));

            if(codex.searchTerm(searchTerm) != null) codex.displayWord(searchTerm);

            counter++;
        }

    }

}

