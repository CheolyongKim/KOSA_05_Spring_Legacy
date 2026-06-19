package AOP_Basic_02_Java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

/*
 * 보조업무 클래스
 * 
 * invoke: 다른 함수를 대리해 실제 함수처럼 사용
 * invoke 함수 하나가 여러 개의 실제 함수(주업무)를 대리한다.
 * 
 * Reflection: Java에서 Runtime에 class, method, field 등에 정보를 조사하고 조작할 수 있는 기능
 * 즉 컴파일할 때가 아니라 프로그램 실행 중에 해당 객체의 클래스를 보고 메서드를 호출하거나 필드를 수정할 수 있는 능력
 */
public class LogPrintHandler implements InvocationHandler{
	// 실 객체의 주소
	private Object target;
	public LogPrintHandler(Object target) {
		System.out.println("LogPrintHandler");
		this.target=target;
	}
	
	// invoke 함수 대리 (ADD, MUL, SUB)
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("invoke 함수 호출");
		System.out.println("Method method: " + method);
		System.out.println("Method parameter: " + Arrays.toString(args));
		
		// 보조
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("timer start");
		
		// 주
		int result = (int) method.invoke(this.target, args);
		
		// 보조
		sw.stop();
		log.info("timer stop");
		log.info("timer: " + sw.getLastTaskTimeMillis());
		
		return result;
	}

}
