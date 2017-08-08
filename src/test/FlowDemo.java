package test;

/**
 * 
 * 演示Java中几种常用的流程控制操作
 *
 */
public class FlowDemo {
	public static void main(String[] args) {
		int iPara1, iPara2, iEnd;
		if (args.length != 3) {
			System.out.println("USE:java flowDemo parameter1 parameter2 circle");
			System.out.println("parameter1:比较条件1，数字类型");
			System.out.println("parameter2:比较条件2，数字类型");
			System.out.println("circle:循环次数");
			System.out.println("ego:java flowDemo 1 2 5");
			return;
		} else {
			iPara1 = Integer.parseInt(args[0]);
			iPara2 = Integer.parseInt(args[1]);
			iEnd = Integer.parseInt(args[2]);
		}
		// if语句
		if (iPara2 > iPara1) {
			System.out.println("if条件满足！");
			System.out.println("第2个数比第一个数大！");
		} else {
			System.out.println("if条件不满足！");
			System.out.println("第2个数比第一个数小！");
		}
		// for循环
		for (int i = 0; i < iEnd; i++) {
			System.out.println("这是for第" + i + "次循环");
		}
		// while循环
		int i = 0;
		while (i < iEnd) {
			System.out.println("这是while第" + i + "次循环");
		}
		// do-while循环
		int j = 0;
		do {
			System.out.println("这是do-while第" + j + "次循环");
			j++;
		} while (j < iEnd);
	}
}
