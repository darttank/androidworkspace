import org.junit.Test;

public class TestPerson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testPersonEating() {
		Perosn perosn = new Perosn();
		perosn.eat("name");
	}

	@Test
	public void testPersonRunning() {
		Perosn perosn = new Perosn();
		perosn.run("name");
	}

}
