package test;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 
 * 创建自己的窗体
 *
 */
public class MainFrame extends JFrame {
	public MainFrame(String sTitle, int iWidth, int iHeight) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕尺寸
		ImageIcon ii = new ImageIcon("images/middle.gif");
		setTitle(sTitle);
		setIconImage(ii.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置但关闭窗体时退出程序
		setSize(iWidth, iHeight);
		int w = getSize().width;
		int h = getSize().height;
		System.out.println("窗体宽：" + w + " 窗体高：" + h);
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		setLocation(x, y);// 将窗体移到屏幕中间
		setVisible(true);
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);// 使用最新的SWING外观
		MainFrame mf = new MainFrame("main frame demo", 400, 300);
	}
}
