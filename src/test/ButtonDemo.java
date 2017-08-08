package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * 演示按钮操作
 *
 */
public class ButtonDemo extends JPanel implements ActionListener {

	protected JButton b1, b2, b3;

	public ButtonDemo() {
		ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
		ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
		ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

		b1 = new JButton("失效中间按钮（D）", leftButtonIcon);
		b1.setVerticalTextPosition(AbstractButton.CENTER);// 水平中间对齐
		b1.setHorizontalTextPosition(AbstractButton.LEADING);// 相当于LEFT
		b1.setMnemonic(KeyEvent.VK_D);// 将b1绑定alt+D键
		b1.setActionCommand("disable");

		b2 = new JButton("M中间按钮", middleButtonIcon);
		b2.setVerticalTextPosition(AbstractButton.BOTTOM);
		b2.setHorizontalTextPosition(AbstractButton.CENTER);
		b2.setMnemonic(KeyEvent.VK_M);// 将b2邦定alt+M键

		b3 = new JButton("E激活中间按钮", rightButtonIcon);
		b3.setMnemonic(KeyEvent.VK_E);// 将b3邦定alt+E键
		b3.setActionCommand("enable");
		b3.setEnabled(false);

		// 给1和3添加事件监听
		b1.addActionListener(this);
		b3.addActionListener(this);
		// 设置按钮提示文本
		b1.setToolTipText("点击这个按钮，将使中间的按钮失效！");
		b2.setToolTipText("点击这个按钮，没有任何的事件发生！");
		b3.setToolTipText("点击这个按钮，将使中间的按钮有效");
		// 将按钮添加到JPanel中
		add(b1);
		add(b2);
		add(b3);
	}

	private ImageIcon createImageIcon(String path) {
		URL imgURL = ButtonDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("disable".equals(e.getActionCommand())) {
			b2.setEnabled(false);
			b1.setEnabled(false);
			b3.setEnabled(true);
		} else {
			b2.setEnabled(true);
			b1.setEnabled(true);
			b3.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("ButtonDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ButtonDemo newContentPane = new ButtonDemo();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		// 显示窗体
		frame.pack();
		frame.setVisible(true);
	}
}
