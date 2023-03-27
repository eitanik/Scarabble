package test;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BookScrabbleHandler implements ClientHandler{
    Scanner in;
    PrintWriter out;
    DictionaryManager dm = new DictionaryManager();

    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        in = new Scanner(new InputStreamReader(inFromClient));
        out = new PrintWriter(outToClient);
        boolean flag = false;
        String line = in.nextLine();
        int length = line.split(",").length;
        String word = line.split(",")[0];
        String[] f = new String[length - 1];
        for (int i = 0; i < length - 1; i++) {
            f[i] = line.split(",")[i + 1];
        }

        if (word.equals("Q")) {
            flag = dm.query(f);
        } else if (word.equals("C")) {
            flag = dm.challenge(f);
        }

        if (flag)
        {
            out.println("true");
        }

        else {
            out.println("false");
        }
            out.flush();
        this.close();
    }

    public void close()
    {
        try
        {
            in.close();
            out.close();
        }catch (Exception e){}
    }

}





























/*
package test;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler
{
DictionaryManager m=new DictionaryManager();
    Scanner in;
    PrintWriter out;
    public void handleClient(InputStream inFromclient, OutputStream outToClient)
    {
        boolean check;
        try {
            in = new Scanner(new InputStreamReader(inFromclient));
            out = new PrintWriter(outToClient, true);

            String s;


           in.useDelimiter(",");
           s= in.next();
            String st="";

            String str = in.nextLine();
            int length = str.split(",").length;

            String word = str.split(",")[0];
            String[] f = new String[length - 1];
            for(int i = 1, j = 0, k = 0; i < length; i++, j++)
            {
                f[j] = str.split(",")[i];
            }

            if (s.compareTo("S")==0) {

                  check= m.challenge(f);
                  if(check==true)
                  {
                      System.out.println("true"+"\n");
                  }
                  else
                  {
                      System.out.println("false"+"\n");
                  }
                }


            if (s.compareTo("Q")==0) {

                   check= m.query(f);

                if(check==true)
                {
                   out.println("true");
                }
                else
                {
                    out.println("false");
                }
            }
        }
        catch (Exception e) {
        }

    }
   public void close()
    {
        in.close();
        out.close();

    }







}
*/