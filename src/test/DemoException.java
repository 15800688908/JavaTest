package test;

/**
 * 
 * 通过继承Exception类实现自己的异常类，并用try-catch来捕获
 *
 */
class MyException extends Exception {
	private int i;

	public MyException() {

	}

	public MyException(String msg) {
		super(msg);
	}

	public MyException(String msg, int x) {
		super(msg);
		i = x;
	}

	public int val() {
		return i;
	}
}

public class DemoException {
	public static void a() throws MyException {
		System.out.println("Throwing MyException from a()");
		throw new MyException();
	}

	public static void b() throws MyException {
		System.out.println("Throwing MyException from b()");
		throw new MyException("Originated in b()");
	}

	public static void c() throws MyException {
		System.out.println("Throwing MyException from c()");
		throw new MyException("Originated in c()", 47);
	}

	public static void main(String[] args) {
		try {
			a();
		} catch (MyException e) {
			e.getMessage();
		}
		try {
			b();
		} catch (MyException e) {
			e.toString();
		}
		try {
			c();
		} catch (MyException e) {
			e.printStackTrace();
			System.out.println("error code: " + e.val());
		}
	}
}