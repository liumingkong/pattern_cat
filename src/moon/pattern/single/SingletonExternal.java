package moon.pattern.single;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

/**
 * <h1>单例模式有两种实现方式</h1>
 * <h2>外部实现</h2>
 * <br>客户程序使用某些全局对象时，做一些try-use的工作，
 * <br>如果没有，就自己一个，把它放在全局的位置上。如果有就直接使用。
 * <br>外部实现显然不可靠
 * <h2>内部实现</h2>
 * <br>类型自己控制实例的数量
 * 
 * <h1>单件模式的主要意图</h1>
 * <br>控制该类只能够创建一个实例，同时向客户端程序提供一个访问它的全局访问点
 * <br>
 * @author liuzhao
 *
 */
public class SingletonExternal{

	@Test
	public void testExternalSingleton(){
		ExternalSingletonClient esClient1 = new ExternalSingletonClient();
		ExternalSingletonClient esClient2 = new ExternalSingletonClient();
		Assert.assertSame(esClient1.getTarget(),esClient2.getTarget());
		Assert.assertEquals(esClient1.getTarget().getId(),esClient2.getTarget().getId());
	}
}

/** 单例方式使用的目标类型*/
class Target{
	/** 每个实例的唯一标识*/
	private String id = UUID.randomUUID().toString();
	public String getId() { return this.id; }
}

/** 保证使用过程中 Target实例唯一性的外部类型*/
class ExternalSingletonClient{
	private static Target target;
	public ExternalSingletonClient(){
		if (target == null){
			target = new Target();
		}
	}
	public Target getTarget(){
		return target;
	}
}