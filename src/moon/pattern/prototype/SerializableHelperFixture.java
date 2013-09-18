package moon.pattern.prototype;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SerializableHelperFixture {
	
	@Test(expected = NotSerializableException.class)
	public void testNoSerializableObject () {
		U u = new U();
		String graph = SerializableHelper.toString(u);
	}
}

/**支持序列化的类型*/
class S implements Serializable {

	private static final long serialVersionUID = 7995554189296512862L;
	private int x;
	
	public int getX() {
		return x;
	}

	private int y;
	
	public int getY() {
		return y;
	}
	
	private List<Integer> list = new ArrayList<Integer>();
	
	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public void setXY(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
}

/**不支持序列化的类型*/
class U {}

/**序列化工具类*/
class SerializableHelper{
	
	static Object fromString(String graph){
		return null;
	}
	
	static String toString(Object target) {
		return null;
	}
}