import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SortedGroup<E extends Comparable<E>> {

	private ArrayList<E> list;
	
	public SortedGroup() {
		list = new ArrayList<E>();
	}
	
	public void add(E obj) {
		int index = Collections.binarySearch(list, obj);
		if (index <0)
			index = -(index + 1);
		list.add(index, obj);
	}
	public int remove(E obj) {
		int count=0;
		for (int i=0; i<list.size();i++) {
			if (list.get(i).compareTo(obj)==0){
				list.remove(i);
				i--;
				count++;
			}
		}
		return count;
	}
	public Iterator<E> iterator() {	
		return list.iterator();
	}
}
