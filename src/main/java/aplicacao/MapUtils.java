package aplicacao;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapUtils {

	  private MapUtils() {}
	   
	  public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
		     Set<T> keys = new HashSet<T>();
		     for (Entry<T, E> entry : map.entrySet()) {
		         if (entry.getValue().equals(value)) {
		             keys.add(entry.getKey());
		         }
		     }
		     return keys;
		}	  
}
