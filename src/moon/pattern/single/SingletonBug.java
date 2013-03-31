package moon.pattern.single;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;

/**
 * <h1>Singleton变质</h1>
 * <h2>Cloneable接口</h2>
 * <br>不要实现Cloneable接口或继承自其相关的子类，
 * <br>否则客户程序可跳过已经隐蔽起来的类构造函数
 * <h2>严防序列化</h2>
 * <br>序列化和反序列化生成的实例是两个不同的实例
 * <br>
 * <br>所有枚举类天生都会实现Serializable接口
 * <br>Enum类实现了Serializable，Comparable接口
 * <br>但是Enum的序列化和反序列化并没有形成新的实例
 * <h3>原因</h3>
 * <br>Java的序列化机制不会再序列化和反序列化过程中重新创建枚举类型实例
 * <br>即枚举类型在Java的序列化过程中是特殊的。
 * <br>
 * @author liuzhao
 *
 */
public class SingletonBug {

	@Test
	public void testCloneSingletonObject() throws CloneNotSupportedException{
		SingletonClone s1 = SingletonClone.getInstance();
		SingletonClone s2 = (SingletonClone)(SingletonClone.getInstance().clone());
		Assert.assertFalse(s1.hashCode() == s2.hashCode());
	}
	
	@Test
	public void testSerializableSingletonObject() { 
	}
	
}

class BaseClone implements Cloneable{
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class SingletonClone extends BaseClone{
	private static SingletonClone singletonClone;
	private SingletonClone(){}
	public static synchronized SingletonClone getInstance(){
		if (null == singletonClone){
			singletonClone = new SingletonClone();
		}
		return singletonClone;
	}
}

class BaseSerializable implements Serializable{
	private static final long serialVersionUID = 8976920223625685833L;}

class SingletonSerializable extends BaseSerializable{
	private static final long serialVersionUID = 8963347074149670304L;
	private static SingletonSerializable singletonSerializable;
	private SingletonSerializable(){}
	public static synchronized SingletonSerializable getInstance(){
		if (null == singletonSerializable){
			singletonSerializable = new SingletonSerializable();
		}
		return singletonSerializable;
	}
}