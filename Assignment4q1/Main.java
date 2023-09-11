import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		SortedGroup<Student> students = new SortedGroup<Student>();
		students.add(new Student("Dvir",204581821,98));
		students.add(new Student("Mike",654852917,62));
		students.add(new Student("Bob",225684739,77));
		students.add(new Student("Robert",225685539,77));
		students.add(new Student("Yuval",204585555,89));
		students.add(new Student("Eli",206255663,54));
		students.add(new Student("Philipp",235685339,77));
		students.add(new Student("Alon",226255763,45));
		students.add(new Student("Liav",202225421,97));
		System.out.println("Count of removes:"+students.remove(new Student("Yael",225826739,77)));
		SortedGroup<Student> students2 = reduce(students, new Student("Yael",225826739,60));
		Iterator<Student> itr = students.iterator();
		Iterator<Student> itr2 = students2.iterator();
		System.out.println("Original list:");
		while (itr.hasNext())
			System.out.println(itr.next());
		System.out.println("New list after reduce:");
		while (itr2.hasNext())
			System.out.println(itr2.next());
	}
	
	public static <E extends Comparable<E>> SortedGroup<E> reduce (SortedGroup<E> sGroup, E x){
		SortedGroup<E> newGroup = new SortedGroup<E>();
		E temp;
		Iterator<E> itr = sGroup.iterator();
		while (itr.hasNext()) {
			temp = itr.next();
			if (temp.compareTo(x)>0) {
				newGroup.add(temp);
			}
		}
		return newGroup;
	}
}
