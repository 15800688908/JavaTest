package test;

/**
 * 
 * 演示标识符对类的访问控制
 *
 */
public class ClassDemo {
	// 公有方法
	public void mechod1() {
		System.out.println("这是一个公有的方法！任何类都可以访问。");
	}

	// 授保护的方法
	protected void mechod2() {
		System.out.println("这是一个受到保护的方法！只有子类可以访问。");
	}

	// 私有的方法
	private void mechod3() {
		System.out.println("这是一个私有的方法！只有类本身才可以访问。");
	}

	public static void main(String[] args) {
		ClassDemo d = new ClassDemo();
		d.mechod1();
		d.mechod2();
		d.mechod3();
	}
}
