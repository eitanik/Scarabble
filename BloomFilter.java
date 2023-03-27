package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;

public class BloomFilter
{
    int bytes;
    ArrayList<String> algs=new ArrayList<>();
    BitSet b;

    public BloomFilter(int bytes,String...algs)
    {
       this.bytes=bytes;
      for(int i=0;i<algs.length;i++)
      {
          this.algs.add(algs[i]);
      }
       b=new BitSet(bytes);
    }

    public void add(String s) {
        byte[] bts;
        int x;
        int m;

        for (int i = 0; i < algs.size(); i++) {
            try {
                MessageDigest md1 = MessageDigest.getInstance(algs.get(i));
                bts = md1.digest(s.getBytes());
                BigInteger b1 = new BigInteger(bts);
                x = Math.abs(b1.intValue());
                m = x % bytes;
                b.set(m);
            } catch (Exception e) {
                System.out.println("Exception thrown : " + e);
            }
        }
    }
    public Boolean contains(String s)
    {
        byte[] bts;
        int x;
        int m;

        for (int i = 0; i < algs.size(); i++) {
        try {
            MessageDigest md1 = MessageDigest.getInstance(algs.get(i));
            bts= md1.digest(s.getBytes());
            BigInteger b1 = new BigInteger(bts);
            x = Math.abs(b1.intValue());
            m = x%bytes;
            if(!b.get(m))
            {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Exception thrown : " + e);
        }

        }

        return true;
    }

    public String toString() {

        String sb="";
        for (int i = 0; i < b.length(); i++)
        {
            if(b.get(i))
            {
                sb+="1";
            }
            else {
                sb+="0";
            }

        }
        return sb;
    }
}
