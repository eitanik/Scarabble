package test;
import java.util.HashSet;

public class CacheManager
{
HashSet<String> Hs=new HashSet<String>();
    CacheReplacementPolicy crp;
    int size;
public CacheManager(int size,CacheReplacementPolicy crp)
{
    this.size=size;
    this.crp=crp;
}

public boolean query(String word)
{
   if(Hs.contains(word))
   {
       return true;
   }
    return false;
}

public void add(String word)
{
    String s="null";
   Hs.add(word);
   crp.add(word);
   if(Hs.size()>size) {
       s = crp.remove();
       Hs.remove(s);
       }
   }



}
