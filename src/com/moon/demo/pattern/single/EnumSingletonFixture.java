package com.moon.demo.pattern.single;

import static org.junit.Assert.*;

import java.util.UUID;
import org.junit.Test;

/**
 * <h1>枚举方式的单件模式</h1>
 * <h2>设计</h2>
 * <br>Java的枚举允许有自己的构造函数，但是只能是private
 * <br>所有的枚举项都是static final形式，它们在使用前只能被构造一次。
 * <br>枚举项的INSTANCE的构造过程是线程安全的
 * <br>from effective java
 * 
 * <h2>缺陷</h2>
 * <br>枚举不能定义类型参数，即它不能是泛型的
 * <br>INSTANCE枚举项的构造过程是线程安全的，但其成员，方法，并不是线程安全的！！
 * 
 * 
 * @author liuzhao
 *
 */
public class EnumSingletonFixture {

	enum Singleton{
		INSTANCE;
		/**实例的唯一标识*/
		private String id = UUID.randomUUID().toString();
		private int counter;
		public String getId(){
			return this.id;
		}
		public void increment(){
			counter++;
		}
		public int getValue(){
			return counter;
		}
	}
	
	@Test
	public void testEnumSingleton(){
		assertEquals(Singleton.INSTANCE,Singleton.INSTANCE);
		assertEquals(Singleton.INSTANCE.getId(),Singleton.INSTANCE.getId());
		
		Singleton s1 = Singleton.INSTANCE;
		Singleton s2 = Singleton.INSTANCE;
		assertEquals(0,s1.getValue());
		assertEquals(0,s2.getValue());
		s1.increment();
		assertEquals(1,s1.getValue());
		assertEquals(1,s2.getValue());
	}
}
