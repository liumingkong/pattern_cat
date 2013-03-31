package moon.pattern.builder;

import org.junit.Assert;
import org.junit.Test;

/**
 * <h1>连贯接口形式的Builder</h1>
 * <br>有时候装配过程的复杂性不在于类型每个组成多复杂，而是由于构造参数排列组合太多引起的</br>
 * <br>编译过程中没办法做出决定,需要客户程序按照需要动态定义的构造过程</br>
 * <br>场景：过于复杂的构造函数
 * 
 * <br>例如：一份体检报告单
 * <br>可能会包含血压，血糖，视力，牙齿等几十项，每份报告单的内容差异比较大，还要根据年龄，性别，参考值判断是否应该提示异常
 * <br>如果开发这样一个报告单，判断身体指标是否异常的逻辑也许不是太复杂，
 * <br><b>但构造函数的挑战非常大</b></br>
 * <br>solution from effective java
 * 
 * <br>实现过程：
 * <br>由内部Builder管理外部Entry的实例化过程，如果需要还可以管理Entry实例的缓存，提高资源的利用率。
 * <br>可以解决不确定数量构造参数排列组合的问题
 * <br>可以支持private Setter的构造函数参数
 * <br>
 * 
 * @author liuzhao
 *
 */
public class FluentInnerBuilderFixture {

	private static final String ParamA = "a";
	private static final String ParamB = "b";
	private static final String ParamC = "c";
	private static final String ParamD = "d";
	private static final String ParamE = "e";
	Entry entry;
	
	@Test
	public void testOnlyEssentialParammsConstructor(){
		entry = new Entry.Builder(ParamA, ParamB).buildUp();
		Assert.assertEquals(ParamA, entry.getParamA());
		Assert.assertEquals(ParamB, entry.getParamB());
		Assert.assertNull(entry.getParamC());
	}
	
	@Test
	public void testFullParammsConstructor(){
		entry = new Entry.Builder(ParamA, ParamB)
			.setParamC(ParamC)
			.setParamD(ParamD)
			.setParamE(ParamE)
			.buildUp();
		Assert.assertEquals(ParamA, entry.getParamA());
		Assert.assertEquals(ParamB, entry.getParamB());
		Assert.assertEquals(ParamC, entry.getParamC());
		Assert.assertEquals(ParamD, entry.getParamD());
		Assert.assertEquals(ParamE, entry.getParamE());
	}
	
	@Test
	public void testPartialParammsConstructor(){
		entry = new Entry.Builder(ParamA, ParamB)
			.setParamC(ParamC)
			.setParamE(ParamE)
			.buildUp();
		Assert.assertEquals(ParamA, entry.getParamA());
		Assert.assertEquals(ParamB, entry.getParamB());
		Assert.assertEquals(ParamC, entry.getParamC());
		Assert.assertNull(entry.getParamD());
		Assert.assertEquals(ParamE, entry.getParamE());
	}
}

class Entry{
	private String paramA;
	private String paramB;
	private String paramC;
	private String paramD;
	private String paramE;
	
	/**实际构造过程仅在Entry类的private构造函数中一次完成，构造过程保持一致*/
	private Entry(Builder builder){
		this.paramA = builder.paramA;
		this.paramB = builder.paramB;
		this.paramC = builder.paramC;
		this.paramD = builder.paramD;
		this.paramE = builder.paramE;
	}
	
	public String getParamA() {
		return paramA;
	}

	public String getParamB() {
		return paramB;
	}

	public String getParamC() {
		return paramC;
	}

	public String getParamD() {
		return paramD;
	}

	public String getParamE() {
		return paramE;
	}

	public static class Builder{
		private String paramA;
		private String paramB;
		private String paramC;
		private String paramD;
		private String paramE;
		
		/**
		 * 构造方法
		 * @param paramA
		 * @param paramB
		 */
		public Builder(String paramA,String paramB) {
			this.paramA = paramA;
			this.paramB = paramB;
		}
	
		public Builder setParamC(String paramC) {
			this.paramC = paramC;
			return this;
		}
		
		public Builder setParamD(String paramD) {
			this.paramD = paramD;
			return this;
		}
		
		public Builder setParamE(String paramE) {
			this.paramE = paramE;
			return this;
		} 
		
		public Entry buildUp(){
			return new Entry(this);
		}
	}
}