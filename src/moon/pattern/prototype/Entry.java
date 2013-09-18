package moon.pattern.prototype;

/**
 * @ClassName: Entry 
 * @Description: 
 * Prototype接口定要符合原型模式要求的类型特征，需要clone方法
 * 采用原型模式，因为没有中间对象的new操作，会导致客户程序与具体原型类型直接依赖
 * 其实客户程序依赖的仅仅是Prototype接口，而且通过clone方法获得的也是Prototype
 * 
 * @author liuzhao
 * @date 2013-4-18 上午12:52:55 
 */
public interface Entry extends Cloneable {

	String getId();
	void setId(String id);
}

abstract class AbstractEntry implements Entry {
	private String id;
	
	public AbstractEntry(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}


class Employee extends AbstractEntry {

	public Employee(String id) {
		super(id);
	}
}

class Order extends AbstractEntry {

	public Order(String id) {
		super(id);
	}
	
}






