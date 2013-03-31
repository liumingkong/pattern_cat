package moon.pattern.builder;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * <h1>经典创建者模式</h1>
 * 
 * <br><b>需求描述</b></br>
 * <br>制造交通工具，这些工具有轮子也有车灯
 * <br>现有加工要求：汽车要4个轮子（前后各两个），4盏车灯（前后各两个），自行车两个轮子（前后各1个），没有车灯
 * 
 * <h2>汽车的构建过程</h2>
 * <ol>
 * <li>CarBuilder:构建汽车的实例</li>
 * <li>VehicleMaker(setBuilder):把汽车实例放入车子的构建过程中</li>
 * <li>VehicleMaker(construct):对车子进行构建(initialize,addWheels,addLights)</li>
 * <li>VehicleMaker(construct,initialize):所有车类型的初始化方法(公用)</li>
 * <li>VehicleMaker(construct,addWheels):调用汽车实例实现的addWheels，增加四个车轮</li>
 * <li>VehicleMaker(construct,addLights):调用汽车实例实现的addLights，增加四个车灯</li>
 * <li>VehicleMaker(getVehicle):构建完成后返回构建汽车实例的结果</li>
 * </ol>
 * 
 * @author liuzhao
 *
 */
public class ClassicBuilderFixture {

	VehicleMaker maker;
	
	@Before
	public void setUp(){
		maker = new VehicleMaker();
	}
	
	@Test
	public void testBuilderUp(){
		// 开始组装一辆汽车
		maker.setBuilder(new CarBuilder());
		maker.construct();
		Vehicle car = maker.getVehicle();
		Assert.assertEquals(4,car.getWheels().size());
		Assert.assertEquals(4,car.getLights().size());
		
		// 开始组装一辆自行车
		maker.setBuilder(new BikeBuilder());
		maker.construct();
		Vehicle bike = maker.getVehicle();
		Assert.assertEquals(2,bike.getWheels().size());
		Assert.assertEquals(0,bike.getLights().size());
		
	}
}

class Wheel{
	/** 轮子所在的方向*/
	private String directName;
	public Wheel(String directName){
		this.directName = directName;
	}
	public String getDirectName() {
		return directName;
	}
}
class Light{
	/** 轮子所在的方向*/
	private String directName;
	public Light(String directName){
		this.directName = directName;
	}
	public String getDirectName() {
		return directName;
	}
}

/**
 * <br>定义目标产品和抽象的创建者类型
 * <br><b>Builder：</b>描述创建一个产品各个组成的抽象接口</br>
 */
class Vehicle{
	private List<Wheel> wheels = new ArrayList<Wheel>();
	private List<Light> lights = new ArrayList<Light>();
	
	public Vehicle(){
		System.out.println("[Vehicle][构建车的实例]");
	}
	
	public List<Wheel> getWheels(){
		return wheels;
	}
	
	public List<Light> getLights(){
		return lights;
	}
	
	public Vehicle addWheel(String name){
		System.out.println("[Vehicle][增加一个车轮]");
		getWheels().add(new Wheel(name));
		return this;
	}
	
	public Vehicle addLight(String name){
		System.out.println("[Vehicle][增加一个车灯]");
		getLights().add(new Light(name));
		return this;
	}
}

/**
 * Concrete Builder：实现Builder要求的内容，提供一个获得最终产品的方法
 * 此处为抽象类，所有提供具体产品的类都继承该类
 * 具体的创建者类型的抽象类
 */
abstract class VehicleBuilder{
	public static final String FRONT = "front";
	public static final String BACK = "back";
	protected Vehicle vehicle;
	
	public Vehicle getVehicle(){
		System.out.println("[VehicleBuilder][获取车的实例]");
		return vehicle;
	}
	
	public void initialize(){
		System.out.println("[VehicleBuilder][车的实例初始化]");
		vehicle = new Vehicle();
	}
	
	public abstract void addWheels();
	public abstract void addLights();
}

/** 汽车类型：4个轮子，4盏灯 */
class CarBuilder extends VehicleBuilder{

	public CarBuilder(){
		System.out.println("[CarBuilder][构建汽车的实例]");
	}
	
	@Override
	public void addWheels() {
		System.out.println("[CarBuilder][增加车轮]");
		vehicle.addWheel(FRONT);
		vehicle.addWheel(FRONT);
		vehicle.addWheel(BACK);
		vehicle.addWheel(BACK);
	}

	@Override
	public void addLights() {
		System.out.println("[CarBuilder][增加车灯]");
		vehicle.addLight(FRONT);
		vehicle.addLight(FRONT);
		vehicle.addLight(BACK);
		vehicle.addLight(BACK);
	}
}

/** 自行车类型：2个轮子*/
class BikeBuilder extends VehicleBuilder{

	public BikeBuilder(){
		System.out.println("[BikeBuilder][构建自行车的实例]");
	}
	
	@Override
	public void addWheels() {
		System.out.println("[BikeBuilder][增加车轮]");
		vehicle.addWheel(FRONT);
		vehicle.addWheel(BACK);
	}

	@Override
	public void addLights() {
		System.out.println("[BikeBuilder][增加车灯]");
	}
	
}

/**
 * <br>定义实际指导创建者进行生产的Director
 * <br>组装过程相对是比较稳定的，如construct()方法
 * <br>汽车之间的不同之处在于，车的轮胎，发动机，车身的工艺，品牌有很大区别
 */
class VehicleMaker{
	private VehicleBuilder builder;
	
	public void setBuilder(VehicleBuilder builder){
		this.builder = builder;
	}
	
	/** 指导Builder如何加工的抽象工序描述*/
	public void construct(){
		builder.initialize();
		builder.addWheels();
		builder.addLights();
	}
	
	public Vehicle getVehicle(){
		return builder.getVehicle();
	}
}