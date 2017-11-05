import java.util.ArrayList;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class Test {
  public static void main(String[] args) {

// ArrayList
//
    ArrayList<String> al = new ArrayList<String>();
    al.add("apple");
    al.add("mango");
    al.add("grapes");
    al.add("orange");

    System.out.println("Contents are:" + al);

    al.remove(2);

    System.out.println("After removing contents are:" + al);
    System.out.println("size of array list:" + al.size());
    System.out.println("al[1]" + al.get(1));
    Iterator<String> itr = al.iterator();

    while (itr.hasNext()) {
      System.out.println(itr.next());
    }
    for (String obj : al) {
      System.out.println("for + " + obj);

    }

// Hashtable
//
    Hashtable<String, String> ht = new Hashtable<String, String>();
    ht.put("player 1", "sachin");
    ht.put("player 2", "sehwag");
    ht.put("player 3", "dhoni");

    Enumeration<String> values = ht.keys();
    while (values.hasMoreElements()) {
      String str = (String) values.nextElement();
      System.out.println(str + ":" + ht.get(str));
    }
    System.out.println("player1" + "=" + ht.get("player 1")); 
    String v = ht.get("player 1");
    System.out.println("player1" + "=" + v); 

// HashMap
//
    HashMap<Integer, String> hm = new HashMap<Integer, String>();
    hm.put(100, "sachin");
    hm.put(101, "sehwag");
    hm.put(102, "gambir");

    Set set = hm.entrySet();
    Iterator it = set.iterator();

    while (it.hasNext()) {
      Map.Entry m = (Map.Entry) it.next();
      System.out.println(m.getKey() + ":" + m.getValue());

    }
    if (hm.get(1001) != null) {
      String hm_v = hm.get(1001);
      System.out.println("100:" + hm_v);
    }

// HashSet
//
    HashSet<String> hs = new HashSet<String>();
    hs.add("cricket");
    hs.add("foorball");
    hs.add("baseball");
    hs.add("cricket");
    System.out.println(hs);

    System.out.println("Elements using iterator:");
    it = hs.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    for (String s : hs) {
      System.out.println("for:" + s);   
    }

    // check the existence of element
    boolean exist=hs.contains("Simply");
       
    System.out.println("Is the element 'Simply' exists: "+ exist);

// LinkedList
//
    LinkedList<String> ll = new LinkedList<String>();
    ll.add("India");
    ll.add("America");
    ll.addFirst("China");
    ll.addLast("England");

    it = ll.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    for (String s : ll) {
      System.out.println("for:" + s);   
    }

    System.out.println("first:" + ll.getFirst());
    System.out.println("last:" + ll.getLast());   
  }
    
} 