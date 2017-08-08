package test;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * 选择不同的选择框显示不同的图片
 *
 */
public class CheckBoxDemo extends JPanel implements ItemListener {

	JCheckBox chinButton;
	JCheckBox glassesButton;
	JCheckBox hairButton;
	JCheckBox teethButton;

	StringBuffer choices;
	JLabel pictureLabel;

	public CheckBoxDemo() {
		super(new BorderLayout());
		// 创建检查盒
		chinButton = new JCheckBox("下巴(c)");
		chinButton.setMnemonic(KeyEvent.VK_C);
		chinButton.setSelected(true);

		glassesButton = new JCheckBox("眼镜(g)");
		glassesButton.setMnemonic(KeyEvent.VK_G);
		glassesButton.setSelected(true);

		hairButton = new JCheckBox("头发(h)");
		hairButton.setMnemonic(KeyEvent.VK_H);
		hairButton.setSelected(true);

		teethButton = new JCheckBox("牙齿(t)");
		teethButton.setMnemonic(KeyEvent.VK_T);
		teethButton.setSelected(true);
		// 给检查盒添加监听
		chinButton.addItemListener(this);
		glassesButton.addItemListener(this);
		hairButton.addItemListener(this);
		teethButton.addItemListener(this);

		choices = new StringBuffer("cght");
		// 放置一个带图片的标签
		pictureLabel = new JLabel();
		pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
		updatePicture();

		// 将检查盒放置到面版中
		JPanel checkPanel = new JPanel(new GridLayout(0, 1));
		checkPanel.add(chinButton);
		checkPanel.add(glassesButton);
		checkPanel.add(hairButton);
		checkPanel.add(teethButton);

		add(checkPanel, BorderLayout.LINE_START);
		add(pictureLabel, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	private void updatePicture() {
		// 将得到的图片绘制成图标
		ImageIcon icon = createImageIcon("images/geek/geek-" + choices.toString() + ".gif");
		pictureLabel.setIcon(icon);
		pictureLabel.setToolTipText(choices.toString());
		if (icon == null) {
			pictureLabel.setText("没有发现图片");
		} else {
			pictureLabel.setText(null);
		}
	}

	private ImageIcon createImageIcon(String path) {
		URL imgURL = CheckBoxDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int index = 0;
		char c = '-';
		Object source = e.getItemSelectable();
		if (source == chinButton) {
			index = 0;
			c = 'c';
		} else if (source == glassesButton) {
			index = 1;
			c = 'g';
		} else if (source == hairButton) {
			index = 2;
			c = 'h';
		} else if (source == teethButton) {
			index = 3;
			c = 't';
		}
		// 取消选择事件
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			c = '-';
		}

		// 改变文件名字
		choices.setCharAt(index, c);
		updatePicture();
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建一个窗体，
		JFrame frame = new JFrame("CheckBoxDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建一个面板
		JComponent newContentPane = new CheckBoxDemo();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		// 显示窗体
		frame.pack();
		frame.setVisible(true);
	}
}
