
public class Stopwatch {
	
	private final long start;
	
	//When an object of stopwatch is created, check the current time
	public Stopwatch() {
		start = System.currentTimeMillis();
	}
	
	//Return the time since the object was created by subtracting the current time to when the stopwatch was created
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}

}
