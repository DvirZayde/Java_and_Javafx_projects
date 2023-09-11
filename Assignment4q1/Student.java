

public class Student implements Comparable<Student> {

	private String name;
	private  long  id;
	private int grade;
	
	public Student(String name, long id, int grade) {
		this.name=name;
		this.grade=grade;
		this.id=id;
	}
	@Override
	public int compareTo(Student o) {
		return this.grade-o.grade;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", grade=" + grade + "]";
	}
	
	
}
