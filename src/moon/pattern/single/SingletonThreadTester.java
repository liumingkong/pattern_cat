package moon.pattern.single;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * <h1>线程级单例模式</h1>
 * <br>每个线程兜希望自己的线程内单独Singleton对象，其他对象也独立操作自己的线程内Singleton
 * <br>线程级Singleton的实例总数=1(每个线程内部唯一的一个)*N(线程数)=N
 * <br>java程序可以通过java.lang.ThreadLocal<T>类型的静态成员定义仅在线程内部生效的全局对象
 * <br>确保它对于每个线程都是唯一的
 * 
 * 
 * @author liuzhao
 *
 */
public class SingletonThreadTester {

	public static final int THREADS =3;
	/** 保持各线程中Singleton对象hashCode()信息*/
	public static Set<Integer> hashCodeSet = new LinkedHashSet<Integer>();
	
	private void action(){
		// 验证线程内部的Singleton特征
		Assert.assertEquals(SingletonThread.getInstance(),SingletonThread.getInstance());
		// 登记当前线程中Singleton对象的hashCode()
		hashCodeSet.add(SingletonThread.getInstance().hashCode());
	}
	
	public void run(){
		for (int i = 0;i<THREADS;i++){
			new Thread(){
				@Override
				public void run(){
					action();
				}
			}.start();
		}
	}
	
	@Test
	public void testSingletonThread() throws InterruptedException{
		new SingletonThreadTester().run();
		Thread.sleep(10000); // 需要一定时间完成现成的运行
		Assert.assertEquals(THREADS, hashCodeSet.size());
	}
}

class SingletonThread{
	
	/** 为了配合ThreadLocal<E>要求定义的负责管理线程级SingletonThread实例类*/
	private static final ThreadLocal<SingletonThread> local = new ThreadLocal<SingletonThread>(){
		@Override
		protected SingletonThread initialValue(){
			return new SingletonThread();
		}
	};
	private SingletonThread(){}
	public static SingletonThread getInstance(){
		return local.get();
	}
}