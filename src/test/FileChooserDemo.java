package test;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * 演示打开文件对话框和保存文件对话框，使用文件过滤
 *
 */
public class FileChooserDemo extends JPanel implements ActionListener {
	static private final String newline = "\n";
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;

	public FileChooserDemo() {
		super(new BorderLayout());

		log = new JTextArea(15, 40);
		log.setMargin(new Insets(10, 10, 10, 10));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		// 创建一个文件过滤，使用当前目录
		fc = new JFileChooser(".");
		// 过滤条件在MyFilter类中定义
		fc.addChoosableFileFilter(new MyFilter());

		openButton = new JButton("打开文件", createImageIcon("images/Open16.gif"));
		openButton.addActionListener(this);

		saveButton = new JButton("保存文件", createImageIcon("images/Save16.gif"));
		saveButton.addActionListener(this);

		// 构建一个面板，添加“打开文件”和“保存文件”
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);

		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 当点击打开文件按钮时
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(FileChooserDemo.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// 在这里添加一些对文件的处理
				log.append("打开文件:" + file.getName() + newline);
			} else {
				log.append("打开文件被用户取消！" + newline);
			}
		} else if (e.getSource() == saveButton) {
			// 点击保存文件按钮
			int returnVal = fc.showSaveDialog(FileChooserDemo.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// 在这里添加一些对文件的处理
				log.append("保存文件: " + file.getName() + newline);
			} else {
				log.append("保存文件被用户取消！" + newline);
			}
		}

	}

	protected static ImageIcon createImageIcon(String path) {
		URL imgURL = FileChooserDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		// 创建窗体
		JFrame frame = new JFrame("FileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建一个面板
		JComponent newContentPane = new FileChooserDemo();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		// 显示窗体
		frame.pack();
		frame.setVisible(true);
	}

}
