package DI_Annotation_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MonitorViewer {
	// MonitorViewer는 Recorder에 의존합니다
	// = MonitorViewer는 Recorder 객체의 주소가 필요합니다
	// = new Recorder() 메모리를 만들고 메모리의 주소를 요청해야 한다
	
	// Spring에서: DI (Constructor, Setter)
	// 설정: xml 또는 Annotation
	
	private Recorder recorder;	// null

	public Recorder getRecorder() {
		return recorder;
	}

	@Autowired
	@Qualifier("recorder_1")
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
		System.out.println("setRecorder: " + this.recorder);
	}
	
	// 일반함수
	@Autowired
	@Qualifier("recorder_2")
	public void gMethod(Recorder rec) {
		System.out.println("gMethod: " + rec);
	}
}
