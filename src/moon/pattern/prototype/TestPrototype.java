package moon.pattern.prototype;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName: TestPrototype 
 * @Description: 
 * 样本与副本是两个独立的实例，除非某些状态有意设置为共享或者全局的，
 * 否则在克隆结束的时候，它们就已经被彻底割裂，两者可以相互独立变化。
 * 
 * @author liuzhao
 * @date 2013-4-18 上午1:10:38 
 */
public class TestPrototype {

	@Test
	public void testClone() throws CloneNotSupportedException {
		Employee employee = new Employee(UUID.randomUUID().toString());
		Employee eClone = (Employee) (employee.clone());
		// 副本与样本当时的内容一致
		Assert.assertEquals(employee.getId(), eClone.getId());
		
		Entry entry = new Order(UUID.randomUUID().toString());
		Entry clone = (Entry) (((AbstractEntry)entry).clone());
		// 副本与样本当时的内容一致
		Assert.assertEquals(entry.getId(), clone.getId());
		
		// 验证样本与副本是两个独立的实例
		entry.setId(UUID.randomUUID().toString());
		Assert.assertFalse(entry.getId().equals(clone.getId()));
	}
}
