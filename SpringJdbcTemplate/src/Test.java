public class Test {

	public static void main(String[] args) {
		testString();
	}

	public static void testString() {
		String str = "Yu";
		String strA = str + "ri";
		String result = "Yuri";
		System.out.println(strA == result); //false  结论：字符串变量+字符串常量  不入常量池
	}

}