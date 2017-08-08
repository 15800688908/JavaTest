package test;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * 
 * 哈希表操作 这是一个权限认证的例子，使用了哈希表作为数据的存储
 *
 */
public class RoleRight {
	private static Hashtable rightList = new Hashtable();

	public void init() {
		String[] accRoleList = { "admin", "satrap", "manager", "user", "guest" };
		String[] rightCodeList = { "10001", "10011", "10021", "20011", "24011" };
		for (int i = 0; i < accRoleList.length; i++) {
			rightList.put(accRoleList[i], rightCodeList[i]);
		}
	}

	public String getRight(String accRole) {
		if (rightList.containsKey(accRole))
			return (String) rightList.get(accRole);
		else
			return null;
	}

	public void insert(String accRole, String rightCode) {
		rightList.put(accRole, rightCode);
	}

	public void delete(String accRole) {
		if (rightList.containsKey(accRole))
			rightList.remove(accRole);
	}

	public void update(String accRole, String rightCode) {
		this.insert(accRole, rightCode);
	}

	public void print() {
		Enumeration RLKey = rightList.keys();
		while (RLKey.hasMoreElements()) {
			String accRole = RLKey.nextElement().toString();
			print(accRole + "=" + this.getRight(accRole));
		}
	}

	private void print(Object oPara) {
		System.out.println(oPara);
	}

	public static void main(String[] args) {
		RoleRight RR = new RoleRight();
		RR.init();
		RR.print();
		RR.print("--------------------");
		RR.insert("presider", "10110");
		RR.print();
		RR.print("--------------------");
		RR.update("presider", "10100");
		RR.print();
		RR.print("--------------------");
		RR.delete("presider");
		RR.print();
	}
}
