public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student = new Student();
		student.print();
	}

}

class Person {
	public void print() {
		System.out.println("person");
	}
}

class Student extends Person {
	@Override
	public void print() {
		System.out.println("student");
	}
}