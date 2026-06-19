package DI_04_Spring;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greeting;
	
	// Constructor
	public MessageBeanImpl(String name) {
		this.name=name;
	}
	
	// Setter
	public void setGreeting(String greeting) {
		this.greeting=greeting;
	}

	@Override
	public void sayHello() {
		System.out.println(this.name + " / " + "Hello!");
	}

}
