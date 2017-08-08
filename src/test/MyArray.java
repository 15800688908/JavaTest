package test;

/**
 * 
 * 演示一维数组和多维数组的初始化和基本操作
 *
 */
public class MyArray {
	// 初始化数组变量
	char[] cNum = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	char[] cStr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z' };
	int[] iMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	String[] sMail = { "@", "." };

	/**
	 * 
	 * TODO:校验电子邮件
	 * 
	 * @param: sPara
	 *             被校验的电子邮件字符
	 * @return boolean:如果校验的格式符合电子邮件格式返回true;否则返回false
	 */
	public boolean isMail(String sPara) {
		for (int i = 0; i < sMail.length; i++) {
			if (sPara.indexOf(sMail[i]) == -1) {
				return false;
			}
		}
		return true;
	}

	public boolean isNumber(String sPara) {
		int iPLength = sPara.length();
		for (int i = 0; i < iPLength; i++) {
			char cTemp = sPara.charAt(i);
			boolean bTemp = false;
			for (int j = 0; j < cNum.length; j++) {
				if (cTemp == cNum[j]) {
					bTemp = true;
					break;
				}
			}
			if (!bTemp)
				return false;
		}
		return true;
	}

	public boolean isString(String sPara) {
		int iPLength = sPara.length();
		for (int i = 0; i < iPLength; i++) {
			char cTemp = sPara.charAt(i);
			boolean bTemp = false;
			for (int j = 0; j < cStr.length; j++) {
				if (cTemp == cStr[j]) {
					bTemp = true;
					break;
				}
			}
			if (!bTemp)
				return false;
		}
		return true;
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param iPara
	 * @return boolean
	 */
	public boolean checkDay(int iPara) {
		return iPara % 100 == 0 && iPara % 4 == 0;
	}

	/**
	 * 
	 * TODO
	 * 
	 * @param sPara
	 * @return int:0日期格式正确，-1月或日不符合要求，-2年月日格式不正确
	 */
	public int checkDate(String sPara) {
		boolean bTemp = false;
		// 所输入日期长度不正确
		if (sPara.length() != 10)
			return -2;
		// 获取年
		String year = sPara.substring(0, 4);
		// 判断年是否是数字
		if (!isNumber(year))
			return -2;
		String month = sPara.substring(5, 7);
		if (!isNumber(month))
			return -2;
		String day = sPara.substring(8, 10);
		if (!isNumber(day))
			return -2;
		// 将年月日转换成数字
		int iYear = Integer.parseInt(year);
		int iMon = Integer.parseInt(month);
		int iDay = Integer.parseInt(day);
		if (iMon > 12)
			return -1;
		// 闰年二月处理
		if (iMon == 2 && checkDay(iYear)) {
			if (iDay > 29)
				return -1;
		} else {
			if (iDay > iMonth[iMon - 1])
				return -1;
		}
		return 0;
	}

	public static void main(String[] args) {
		MyArray ma = new MyArray();
		boolean bMail = ma.isMail("tom@163.com");
		System.out.println("1 bMail is " + bMail);
		bMail = ma.isMail("tom@163com");
		System.out.println("2 bMail is " + bMail);
		boolean bIsNum = ma.isNumber("1234");
		System.out.println("1:bIsNum=" + bIsNum);
		bIsNum = ma.isNumber("123r4");
		System.out.println("2:bIsNum=" + bIsNum);
		boolean bIsStr = ma.isString("wer");
		System.out.println("1:bIsStr=" + bIsStr);
		bIsStr = ma.isString("wer3");
		System.out.println("2:bIsStr=" + bIsStr);
		int iIsTime = ma.checkDate("2017-08-02");
		System.out.println("1:iIsTime=" + iIsTime);
		iIsTime = ma.checkDate("2017-111-02");
		System.out.println("2:iIsTime=" + iIsTime);
		iIsTime = ma.checkDate("2017-08-98");
		System.out.println("3:iIsTime=" + iIsTime);
		iIsTime = ma.checkDate("2000-02-30");
		System.out.println("4:iIsTime=" + iIsTime);
	}
}
