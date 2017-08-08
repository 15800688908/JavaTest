package test;
/**
 * 
 * 继承
 *
 */
class Tree {
	public void root() {
		String sSite = "土壤中";
		String sFunction = "吸收养分";
		print("位置：" + sSite);
		print("功能：" + sFunction);
	}

	public void bolo() {
		String sSite = "地面";
		String sFunction = "传递养份";
		print("位置：" + sSite);
		print("功能：" + sFunction);
	}

	public void branch() {
		String sSite = "树干上";
		String sFunction = "传递养份";
		print("位置：" + sSite);
		print("功能：" + sFunction);
	}

	public void leaf() {
		String sSite = "树梢";
		String sFunction = "光合作用";
		String sColor = "绿色";
		print("位置：" + sSite);
		print("功能：" + sFunction);
		print("颜色：" + sColor);
	}

	public void print(Object oPara) {
		System.out.println(oPara);
	}

	public static void main(String[] args) {
		Tree t = new Tree();
		t.print("描述一棵树：");
		t.print("树根：");
		t.root();
		t.print("树干：");
		t.bolo();
		t.print("树枝：");
		t.branch();
		t.print("树叶：");
		t.leaf();
	}
}

class Osier extends Tree {
	public void leaf() {
		super.leaf();
		String sShape = "长形";
		super.print("形状：" + sShape);
	}

	public void flower() {
		print("哈哈，柳树没有花！！");
	}

	public static void main(String[] args) {
		Osier o = new Osier();
		o.print("柳树的根：");
		o.root();
		o.print("柳树树干：");
		o.bolo();
		o.print("柳树树枝：");
		o.branch();
		o.print("柳树树叶：");
		o.leaf();
		o.print("柳树花：");
		o.flower();
	}
}
