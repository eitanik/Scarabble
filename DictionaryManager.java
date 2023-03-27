package test;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryManager
{

    Map<String, Dictionary> m=new HashMap();

    public static DictionaryManager get()
    {
        DictionaryManager m=new DictionaryManager();
        return m;
    }

    public boolean query(String...args)
    {
        int flag=0;
        for(int i=0;i<args.length-1;i++)
        {
            String s=args[args.length-1];
            if(!m.containsKey(args[i]))
            {
                Dictionary d=new Dictionary(args);
                m.put(args[i],d);
            }

            File f=new File(args[i]);
            Scanner sc= null;
            try {
                sc = new Scanner(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String st;
                while(sc.hasNext())
                {
                    st=sc.next();
                    if(st.compareTo(s)==0)
                    {
                        flag=1;
                    }
                }

        }
        if(flag==0)
        {
            return false;
        }
        return true;
    }

    public boolean challenge(String...args)
    {
        int flag=0;
        for(int i=0;i<args.length-1;i++)
        {
            String s=args[args.length-1];
            if(!m.containsKey(args[i]))
            {
                Dictionary d=new Dictionary(args);
                m.put(args[i],d);
            }

            File f=new File(args[i]);
            Scanner sc= null;
            try {
                sc = new Scanner(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String st;
            while(sc.hasNext())
            {
                st=sc.next();
                if(st.compareTo(s)==0)
                {
                    flag=1;
                }
            }

        }
        if(flag==0)
        {
            return false;
        }
        return true;
    }

    public int getSize()
    {
        return m.size();
    }


}
