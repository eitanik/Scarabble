package test;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Collections;

public class LRU implements CacheReplacementPolicy
{
    LinkedHashSet<String> Lh = new LinkedHashSet<String>();
ArrayList<String> Al=new ArrayList<String>();
    public void add(String word)
    {
        Lh.add(word);
        Al.add(word);
    }
    public String remove()
    {
        Iterator<String> it= Al.iterator();
        Collections.reverse(Al);
        String st=it.next();

        for(String s:Lh)
        {
            if(s!=st)
            {
                Lh.remove(s);
                return s;
            }
            st=it.next();

        }
return null;


    }
}
