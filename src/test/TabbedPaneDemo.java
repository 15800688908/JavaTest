package test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * 
 * 这里是一个选项卡演示，点击不同的卡片，显示不同的内容
 *
 */
public class TabbedPaneDemo extends JPanel {
	public TabbedPaneDemo() {
		super(new GridLayout(1, 1));
		ImageIcon icon = createImageIcon("images/middle.gif");
		JTabbedPane tabbedPane = new JTabbedPane();

		Component panel1 = makeTextPanel("#第一个卡片#");
		tabbedPane.addTab("One", icon, panel1, "第一个卡片提示信息！");
		tabbedPane.setSelectedIndex(0);

		Component panel2 = makeTextPanel("##第二个卡片##");
		tabbedPane.addTab("Two", icon, panel2, "第二个卡片提示信息！");

		Component panel3 = makeTextPanel("###第三个卡片###");
		tabbedPane.addTab("Three", icon, panel3, "第三个卡片提示信息！");

		Component panel4 = makeTextPanel("####第四个卡片####");
		tabbedPane.addTab("Four", icon, panel4, "第四个卡片提示信息！");

		// 将选项卡添加到panl中
		add(tabbedPane);
	}

	private Component makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static void main(String[] args) {
		// 使用Swing窗体描述
		JFrame.setDefaultLookAndFeelDecorated(true);

		// 创建窗体
		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TabbedPaneDemo(), BorderLayout.CENTER);

		// 显示窗体
		frame.setSize(400, 200);
		frame.setVisible(true);
	}
}
