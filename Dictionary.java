package test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary
{
    CacheManager cm1;
    CacheManager cm2;
    BloomFilter bf;
    IOSearcher io;
    ArrayList<String> filenames=new ArrayList<>();

    CacheReplacementPolicy c;
    public Dictionary(String...filenames)
    {

        String s;
        LRU lr = new LRU();
        LFU lf = new LFU();
        bf = new BloomFilter(256, "SHA1", "MD5");
        this.cm1 = new CacheManager(400, lr);
        this.cm2 = new CacheManager(100, lf);
        this.io = new IOSearcher();
        for(int i=0;i<filenames.length;i++)
        {
            this.filenames.add(filenames[i].toString());
        }

for(int j=0;j<filenames.length;j++) {
    try {
        File f = new File(filenames[j]);
        String[] words = null;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String st;
        while ((st = br.readLine()) != null) {
            words = st.split(" ");
            for (String word : words) {
                bf.add(word);
            }
        }
    } catch (Exception e) {

    }

}

    }

    public boolean query(String s) {
        boolean b1, b2, b3;

        b1 = cm1.query(s);
        b2 = cm2.query(s);
        b3 = bf.contains(s);

        if (b1) {
            return true;
        } else {


            if (b2) {
                return false;
            } else {

            }
            if (b3) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean challenge(String s)
    {
        for(int i=0;i<filenames.size();i++) {
            try {
                boolean b = io.search(s, filenames.get(i));

                if (b) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {

            }
        }
        return false;
    }


}

