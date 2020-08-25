package ques1;

public class Test {

	public static class C {

		static class B {
			static String A = "java";
		}
	}

	public static void main(String[] args) {

		if ((A.B.C) == (C.B.A))
			System.out.println("Condition True");
		else
			System.out.println("Condition False");
	}

}
