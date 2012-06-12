import java.util.AbstractMap;
import java.util.HashMap;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mnikonova
 * Date: 5/28/12
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Auxiliary {
    HashMap tmpHashMap =  new HashMap <String, Integer> ();
    public Map <String,Integer> parseStringToKeyValue(String str) {
        int index = str.indexOf(' ');
        String letter = "" + str.charAt(index - 1);
        int number = Integer.parseInt("" + str.charAt(index + 1));
        tmpHashMap.put(letter, number);
        return tmpHashMap;
    }
}


