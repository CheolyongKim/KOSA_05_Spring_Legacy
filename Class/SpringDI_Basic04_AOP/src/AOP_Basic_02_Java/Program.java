package AOP_Basic_02_Java;

import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		// 1. 실 객체의 주소 
		Calc calc = new NewCalc();
		
		// 2. 가 객체(Proxy)
		Calc cal = (Calc) Proxy.newProxyInstance(
				calc.getClass().getClassLoader(),	// 실객체의 내부정보(메타정보) 제공 
				calc.getClass().getInterfaces(),	// 실객체의 행위정보 제공 
				new LogPrintHandler(calc));			// 보조관심객체 제공
		
		int result = cal.ADD(100000, 555555);
		System.out.println(result);
	}

}
