package test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

/**
 * 
 * 演示各种类型的对话框使用
 *
 */
public class DialogDemo extends JPanel {
	JLabel label;
	ImageIcon icon = createImageIcon("images/middle.gif");
	JFrame frame;
	String simpleDialogDesc = "简单的信息提示对话窗";
	String iconDesc = "带有图标的对话窗";
	String moreDialogDesc = "复杂信息对话窗";
	CustomDialog customDialog;

	public DialogDemo(JFrame frame) {
		super(new BorderLayout());
		this.frame = frame;
		customDialog = new CustomDialog(frame, "tom", this);
		customDialog.pack();

		// 创建成员
		JPanel frequentPanel = createSimpleDialogBox();
		JPanel featurePanel = createFeatureDialogBox();
		JPanel iconPanel = createIconDialogBox();
		label = new JLabel("点击\"显示\" 按钮" + " 显示一个选择的对话框", JLabel.CENTER);
		// 放置对象
		Border padding = BorderFactory.createEmptyBorder(20, 20, 5, 20);
		frequentPanel.setBorder(padding);
		featurePanel.setBorder(padding);
		iconPanel.setBorder(padding);
		// 创建选项卡
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("简单对话窗", null, frequentPanel, simpleDialogDesc);
		tabbedPane.addTab("复杂对话窗", null, featurePanel, moreDialogDesc);
		tabbedPane.addTab("图标对话窗", null, iconPanel, iconDesc);

		add(tabbedPane, BorderLayout.CENTER);
		add(label, BorderLayout.PAGE_END);
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	private JPanel createFeatureDialogBox() {
		final int numButtons = 5;
		JRadioButton[] radioButtons = new JRadioButton[numButtons];
		final ButtonGroup group = new ButtonGroup();

		JButton showItButton = null;
		// 定义操作命令
		final String pickOneCommand = "pickone";
		final String textEnteredCommand = "textfield";
		final String nonAutoCommand = "nonautooption";
		final String customOptionCommand = "customoption";
		final String nonModalCommand = "nonmodal";
		// 定义radio数组
		radioButtons[0] = new JRadioButton("选择一个");
		radioButtons[0].setActionCommand(pickOneCommand);

		radioButtons[1] = new JRadioButton("输入信息");
		radioButtons[1].setActionCommand(textEnteredCommand);

		radioButtons[2] = new JRadioButton("关闭按钮无效");
		radioButtons[2].setActionCommand(nonAutoCommand);

		radioButtons[3] = new JRadioButton("输入校验" + "(用户输入信息)");
		radioButtons[3].setActionCommand(customOptionCommand);

		radioButtons[4] = new JRadioButton("没有模式");
		radioButtons[4].setActionCommand(nonModalCommand);
		// 合成一个组群
		for (int i = 0; i < numButtons; i++) {
			group.add(radioButtons[i]);
		}
		// 设置第一个为默认选择
		radioButtons[0].setSelected(true);

		showItButton = new JButton("显示");
		showItButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = group.getSelection().getActionCommand();

				// 选择一个
				if (command == pickOneCommand) {
					Object[] possibilities = { "辣椒", "西红柿", "洋葱" };
					// 设置对话框
					String s = (String) JOptionPane.showInputDialog(frame, // 所属窗体
							"请选择项目：\n" + "\"鸡蛋炒\"", // 输出信息
							"客户选择", JOptionPane.PLAIN_MESSAGE, // 对话框模式
							icon, // 显示图标
							possibilities, // 选项内容
							"辣椒"); // 默认选项

					// 如果有选择
					if ((s != null) && (s.length() > 0)) {
						setLabel("鸡蛋炒" + s + "!");
						return;
					}

					// 如果客户没有选择
					setLabel("快点！");

					// 文本输入
				} else if (command == textEnteredCommand) {
					String s = (String) JOptionPane.showInputDialog(frame, "选择一个配料\n" + "\"鸡蛋炒\"", "客户输入",
							JOptionPane.PLAIN_MESSAGE, icon, null, "辣椒");

					// 如果用户有输入
					if ((s != null) && (s.length() > 0)) {
						setLabel("你要的是鸡蛋炒" + s + "！");
						return;
					}

					// 如果返回的是空或者是null。
					setLabel("快些选择！");

					// 关闭按钮无效
				} else if (command == nonAutoCommand) {
					// 构造一个对话框面板
					final JOptionPane optionPane = new JOptionPane("关闭这个对话框\n" + "请点击下面的按钮\n" + "明白吗？",
							JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);

					JDialog.setDefaultLookAndFeelDecorated(false);
					// 构造一个对话框
					final JDialog dialog = new JDialog(frame, "点击一个按钮", true);
					// 将对话框面板添加到对话框中
					dialog.setContentPane(optionPane);
					// 设置对话框关闭时的操作模式
					dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					dialog.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) { // 当点击关闭按钮
							setLabel("阻碍用户视图关闭窗体！");
						}
					});

					JDialog.setDefaultLookAndFeelDecorated(true);

					optionPane.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent e) {
							String prop = e.getPropertyName();

							if (dialog.isVisible() && (e.getSource() == optionPane)
									&& (JOptionPane.VALUE_PROPERTY.equals(prop))) {
								// 如果你要阻止关闭按钮，可以在这里进行处理。

								dialog.setVisible(false);
							}
						}
					});
					dialog.pack();
					dialog.setLocationRelativeTo(frame);
					dialog.setVisible(true);

					int value = ((Integer) optionPane.getValue()).intValue();
					if (value == JOptionPane.YES_OPTION) {
						setLabel("好的");
					} else if (value == JOptionPane.NO_OPTION) {
						setLabel("试图点击关闭按钮来关闭一个不能关闭的对话框！" + "你不能！");
					} else {
						setLabel("窗体可以使用ESC键关闭。");
					}

					// 自己定义版面
				} else if (command == customOptionCommand) {
					customDialog.setLocationRelativeTo(frame);
					customDialog.setVisible(true);

					String s = customDialog.getValidatedText();
					if (s != null) {
						// The text is valid.
						setLabel("欢迎你！" + "你已经进入了\"" + s + "\"。");
					}

					// 没有模式
				} else if (command == nonModalCommand) {
					// 创建一个对话框
					final JDialog dialog = new JDialog(frame, "一个没有模式的对话框");
					// 使用html语言来显示信息
					JLabel label = new JLabel(
							"<html><p align=center>" + "这是一个没有模式的对话框<br>" + "你可以使用更多的格式<br>" + "甚至可以使用主窗体！");
					label.setHorizontalAlignment(JLabel.CENTER);
					Font font = label.getFont();

					label.setFont(label.getFont().deriveFont(font.PLAIN, 14.0f));

					JButton closeButton = new JButton("关闭");
					closeButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dialog.setVisible(false);
							dialog.dispose();
						}
					});
					JPanel closePanel = new JPanel();
					closePanel.setLayout(new BoxLayout(closePanel, BoxLayout.LINE_AXIS));
					closePanel.add(Box.createHorizontalGlue());
					closePanel.add(closeButton);
					closePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));

					JPanel contentPane = new JPanel(new BorderLayout());
					contentPane.add(label, BorderLayout.CENTER);
					contentPane.add(closePanel, BorderLayout.PAGE_END);
					contentPane.setOpaque(true);
					dialog.setContentPane(contentPane);

					// 显示窗体
					dialog.setSize(new Dimension(300, 150));
					dialog.setLocationRelativeTo(frame);
					dialog.setVisible(true);
				}
			}
		});

		return createPane(moreDialogDesc + ":", radioButtons, showItButton);
	}

	private JPanel createIconDialogBox() {

		JButton showItButton = null;

		final int numButtons = 6;
		JRadioButton[] radioButtons = new JRadioButton[numButtons];
		final ButtonGroup group = new ButtonGroup();

		final String plainCommand = "plain";
		final String infoCommand = "info";
		final String questionCommand = "question";
		final String errorCommand = "error";
		final String warningCommand = "warning";
		final String customCommand = "custom";

		radioButtons[0] = new JRadioButton("普通（没有图标）");
		radioButtons[0].setActionCommand(plainCommand);

		radioButtons[1] = new JRadioButton("信息图标");
		radioButtons[1].setActionCommand(infoCommand);

		radioButtons[2] = new JRadioButton("问题图标");
		radioButtons[2].setActionCommand(questionCommand);

		radioButtons[3] = new JRadioButton("错误图标");
		radioButtons[3].setActionCommand(errorCommand);

		radioButtons[4] = new JRadioButton("警告图标");
		radioButtons[4].setActionCommand(warningCommand);

		radioButtons[5] = new JRadioButton("自定义图标");
		radioButtons[5].setActionCommand(customCommand);

		for (int i = 0; i < numButtons; i++) {
			group.add(radioButtons[i]);
		}
		radioButtons[0].setSelected(true);

		showItButton = new JButton("显示");
		showItButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = group.getSelection().getActionCommand();

				// 没有图标
				if (command == plainCommand) {
					JOptionPane.showMessageDialog(frame, "水煮鱼里不要放酸菜！", "无图标", JOptionPane.PLAIN_MESSAGE);
					// 信息图标
				} else if (command == infoCommand) {
					JOptionPane.showMessageDialog(frame, "水煮鱼里不要放酸菜！", "信息图标", JOptionPane.INFORMATION_MESSAGE);

					// 问题图标
				} else if (command == questionCommand) {
					JOptionPane.showMessageDialog(frame, "请你吃饭前洗手，好吗？", "问题", JOptionPane.QUESTION_MESSAGE);
					// 错误图标
				} else if (command == errorCommand) {
					JOptionPane.showMessageDialog(frame, "对不起，你的信用卡没有资金了！", "错误信息", JOptionPane.ERROR_MESSAGE);
					// 警告图标
				} else if (command == warningCommand) {
					JOptionPane.showMessageDialog(frame, "警告！你严重透支信用卡，请尽快补齐金额！", "警告信息", JOptionPane.WARNING_MESSAGE);
					// 自定义图标
				} else if (command == customCommand) {
					JOptionPane.showMessageDialog(frame, "哈哈。我想用什么图标都可以！", "自定义对话窗", JOptionPane.INFORMATION_MESSAGE,
							icon);
				}
			}
		});

		return create2ColPane(iconDesc + ":", radioButtons, showItButton);
	}

	private JPanel create2ColPane(String description, JRadioButton[] radioButtons, JButton showButton) {
		JLabel label = new JLabel(description);
		int numPerColumn = radioButtons.length / 2;

		JPanel grid = new JPanel(new GridLayout(0, 2));
		for (int i = 0; i < numPerColumn; i++) {
			grid.add(radioButtons[i]);
			grid.add(radioButtons[i + numPerColumn]);
		}

		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		box.add(label);
		grid.setAlignmentX(0.0f);
		box.add(grid);

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.PAGE_START);
		pane.add(showButton, BorderLayout.PAGE_END);

		return pane;
	}

	private JPanel createSimpleDialogBox() {
		final int numButtons = 4;
		JRadioButton[] radioButtons = new JRadioButton[numButtons];
		final ButtonGroup group = new ButtonGroup();

		JButton showItButton = null;

		final String defaultMessageCommand = "default";
		final String yesNoCommand = "yesno";
		final String yeahNahCommand = "yeahnah";
		final String yncCommand = "ync";
		// 添加单选到数字
		radioButtons[0] = new JRadioButton("只有“OK”按钮");
		radioButtons[0].setActionCommand(defaultMessageCommand);

		radioButtons[1] = new JRadioButton("有“Yes/No”二个按钮");
		radioButtons[1].setActionCommand(yesNoCommand);

		radioButtons[2] = new JRadioButton("有“Yes/No”两个按钮 " + "(程序添加文字)");
		radioButtons[2].setActionCommand(yeahNahCommand);

		radioButtons[3] = new JRadioButton("有“Yes/No/Cancel”三个按钮 " + "(程序添加文字)");
		radioButtons[3].setActionCommand(yncCommand);
		// 将四个单选组成一个群
		for (int i = 0; i < numButtons; i++) {
			group.add(radioButtons[i]);
		}
		// 设置第一个为默认选择
		radioButtons[0].setSelected(true);
		// 定义“显示”按钮
		showItButton = new JButton("显示");
		showItButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = group.getSelection().getActionCommand();

				// ok对话窗
				if (command == defaultMessageCommand) {
					JOptionPane.showMessageDialog(frame, "鸡蛋不可能是绿色的！");

					// yes/no 对话窗
				} else if (command == yesNoCommand) {
					int n = JOptionPane.showConfirmDialog(frame, "你喜欢吃酸菜鱼吗?", "一个很无聊的问题！！", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {// 选择yes
						setLabel("哇！我也是！");
					} else if (n == JOptionPane.NO_OPTION) {// 选择no
						setLabel("唉！我喜欢吃！");
					} else {
						setLabel("快告诉我吧！");
					}

					// yes/no (自己输入选项)
				} else if (command == yeahNahCommand) {
					Object[] options = { "是的", "不喜欢" };
					int n = JOptionPane.showOptionDialog(frame, "你喜欢酸菜鱼吗?", "又一个无聊的问题！", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (n == JOptionPane.YES_OPTION) {
						setLabel("你哄人的吧，我也喜欢。");
					} else if (n == JOptionPane.NO_OPTION) {
						setLabel("其实我也不喜欢！");
					} else {
						setLabel("这都不肯告诉我，小气鬼！");
					}

					// yes/no/cancel 对话框
				} else if (command == yncCommand) {
					Object[] options = { "是的，给我来一份。", "不，谢谢！", "不，我要水煮鱼！" };
					// 构造对话框
					int n = JOptionPane.showOptionDialog(frame, "先生！我们这里有鲜美的酸菜鱼，您需要吗？", "服务生的问题。",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
					if (n == JOptionPane.YES_OPTION) {
						setLabel("你要的酸菜鱼来了！");
					} else if (n == JOptionPane.NO_OPTION) {
						setLabel("好的，你需要其它的。");
					} else if (n == JOptionPane.CANCEL_OPTION) {
						setLabel("好的，我们给你做水煮鱼！");
					} else {
						setLabel("对不起！你还没有点菜呢！");
					}
				}
				return;
			}

		});

		return createPane(simpleDialogDesc + ":", radioButtons, showItButton);
	}

	private JPanel createPane(String description, JRadioButton[] radioButtons, JButton showButton) {
		int numChoices = radioButtons.length;
		JPanel box = new JPanel();
		JLabel label = new JLabel(description);

		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		box.add(label);
		// 添加radio
		for (int i = 0; i < numChoices; i++) {
			box.add(radioButtons[i]);
		}

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.PAGE_START);
		pane.add(showButton, BorderLayout.PAGE_END);
		return pane;
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = DialogDemo.class.getResource(path);
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

		// 创建和设置一个窗体
		JFrame frame = new JFrame("DialogDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置一个面板
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(1, 1));
		contentPane.add(new DialogDemo(frame));

		// 显示窗体
		frame.pack();
		frame.setVisible(true);
	}

	public void setLabel(String newText) {
		label.setText(newText);
	}
}
