package win.sunshine.base.service.api.domain;

public class User {
	
	private Integer id;
	private String name;
	private Integer age;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public User() {}
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public User(Integer id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
}
