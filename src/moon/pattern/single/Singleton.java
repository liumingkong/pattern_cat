package moon.pattern.single;

/**
 * <h1>单例模式的Java定义</h1>
 * <h2>公共静态方法</h2>
 * <br>getInstance,客户程序获得类型实例的唯一入口
 * 
 * @author liuzhao
 *
 */
public class Singleton {
	/** 唯一实例*/
	private static Singleton singleton;
	/** 封闭客户程序，防止被直接实例化*/
	private Singleton(){
		
	}
	
	/** 
	 * <h1>Lazy 方式创建唯一实例的过程<h1>
	 * <br>设计缺陷</br>
	 * <br>if 部分，当多线程几乎同时调用Singleton类，可能被不同线程创建多次
	 */
	public static Singleton getInstanceLazy(){
		if (null == singleton){
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/**
	 * <br>解决了同步问题，但是带来了性能问题
	 * @return
	 */
	public static synchronized Singleton getInstanceSync(){
		if (null == singleton){
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/**
	 * <h1>Double Check版本</h1>
	 * <h2>改进1</h2>
	 * <br>多线程环境，如果没有外层if，客户程序每次执行时都要先synchronized为Singleton类型
	 * <br>绝大多数情况下，每次都锁定synchronized类型效率太低
	 * <br>这个锁定可能成为应用的瓶颈
	 * 
	 * <h2>改进2</h2>
	 * <br>一旦实例创建成功，后续的调用都不需要经过synchronized部分
	 * <br>直接在外层if判断之后就可获得既有的唯一实例引用
	 * <br>
	 * 
	 * <h2>设计缺陷</h2>
	 * <br>出问题的概率可能会很小，原因在于
	 * <br>null == singleton和Singleton完全构造完成之前有一个短暂的时间差
	 * <br>多线程环境不能保证null == singleton判断的原子性
	 * <br>因此在J2SE1.4以前的版本这个方式不能用
	 * 
	 * <br>J2SE5 可以用一个volatile关键字修饰静态成员
	 * <br>private static volatile Singleton singleton;
	 * <br>这个关键字用来确保，多线程访问时，每个线程都必须从共享内存中重读该成员变量的值
	 * <br>而当某个线程需要对变量修改时，必须将新值回写到共享内存中。
	 * <br>这样，两个不同的线程读取的null == singleton的结果是一致的。
	 * 
	 * <br> 以下是单例模式的完整版
	 */
	/** 唯一实例*/
	private static volatile Singleton singletonVolatile;
	
	public static Singleton getInstanceSyncDoubleCheck(){
		if (null == singletonVolatile){
			synchronized(Singleton.class){
				if (null == singletonVolatile){
					singletonVolatile = new Singleton();
				}
			}
		}
		return singletonVolatile;
	}
}
