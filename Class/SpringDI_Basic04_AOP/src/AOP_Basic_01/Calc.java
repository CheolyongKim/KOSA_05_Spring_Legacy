package AOP_Basic_01;
/*
 * 간단한 사칙 연산기
 * 주업무: ADD, MUL
 * - 요구사항: 연산에 걸린 시간을 알고 싶다.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class Calc {

	public int Add(int x, int y) {
		// 공통(보조)업무(관심)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");

		// 주업무
		int result = x + y;

		// 공통업무
		sw.stop();
		log.info("[타이머 종료]");
		log.info("Time log method: Add");
		log.info("Time log method: " + sw.getTotalTimeMillis());
		return result;
	}

	public int Mul(int x, int y) {
		// 공통(보조)업무(관심)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");

		// 주업무
		int result = x * y;

		// 공통업무
		sw.stop();
		log.info("[타이머 종료]");
		log.info("Time log method: Mul");
		log.info("Time log method: " + sw.getTotalTimeMillis());
		return result;
	}
}
