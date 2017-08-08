package test;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 
 * FileChooseDemo文件使用的文件过滤器
 *
 */
public class MyFilter extends FileFilter {
	private String files;

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals("java")) {// 定义过滤Java文件
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 
	 * TODO 获取文件扩展名
	 * 
	 * @param f
	 * @return String
	 */
	private String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	@Override
	public String getDescription() {
		return "Java";
	}

}
