package test;


import java.util.Iterator;
import java. util. HashMap;
import java.util.Collections;
import java.util.ArrayList;
public class LFU implements CacheReplacementPolicy
{
    HashMap<Integer,String> Lh = new HashMap();
    ArrayList<String> Al=new ArrayList<String>();
static int key=0;
   public void add(String word) {

       int i = 0;
       int flag = 0;
       String val = "n";
       int k;
       Al.add(word);
       Lh.put(key, word);
       key++;
   }

    public String remove()
    {
        int min=Al.size();
        String stm="NULL";
        int count=0;
        int flag=0;
        ArrayList<String> Nal=new ArrayList<String>();
        Iterator<String> it=Lh.values().iterator();
        for(String x:Lh.values())
        {
            count = Collections.frequency(Lh.values(), x);

            if(count==min)
            {
                Nal.add(x);
            }
            if(count<min)
            {
                flag=1;
                min=count;
                stm= x;
            }
            if(flag==1)
            {
                Nal.clear();
                flag=0;
            }
        }
        if(Nal.size()>0)
        {
            return Nal.get(Nal.size()-1);
        }
        else {
            return stm;
        }

    }
}
