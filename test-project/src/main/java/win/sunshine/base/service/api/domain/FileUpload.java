package win.sunshine.base.service.api.domain;

/**
 * 测试excel表上传并保存到数据库中
 * @author huyanqiu
 *
 */
public class FileUpload {
	private String name;
	private String number;
	private String sex;
	private String age;
	private String state;
	private String city;
	private String telphoneNum;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelphoneNum() {
		return telphoneNum;
	}
	public void setTelphoneNum(String telphoneNum) {
		this.telphoneNum = telphoneNum;
	}
	
	public FileUpload() {}
	
	public FileUpload(String name, String number, String sex, String age, String state, String city,
			String telphoneNum) {
		super();
		this.name = name;
		this.number = number;
		this.sex = sex;
		this.age = age;
		this.state = state;
		this.city = city;
		this.telphoneNum = telphoneNum;
	}
	
	
}
