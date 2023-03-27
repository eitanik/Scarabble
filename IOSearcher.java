package test;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class IOSearcher {
    static boolean search(String s, String...filenames) throws IOException {

        int i = 0;

        while (filenames.length != i)
            try {
                File f = new File(filenames[i]);
                i++;
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String w = sc.next();
                    if (w.compareTo(s) == 0) {
                        return true;
                    }

                }
            } catch (Exception e) {
                System.out.println("Filed to locate the file" + filenames[i]);
            }

            return false;
        }

}

