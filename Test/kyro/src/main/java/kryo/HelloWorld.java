package kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.nio.ByteBuffer;

/**
 * 简单地测试
 */
public class HelloWorld {

  public static World combineWorld() {
    World world = new World();
    world.setAge(5000);
    world.setCountry("china");

    City jiangsu = new City();
    jiangsu.setName("nanjing");
    jiangsu.setCountOfPesons(500000000);
    world.getCitys().put(1111, jiangsu);

    jiangsu = new City();
    jiangsu.setName("shanghai");
    jiangsu.setCountOfPesons(900000000);
    world.getCitys().put(1211, jiangsu);


    jiangsu = new City();
    jiangsu.setName("beijing");
    jiangsu.setCountOfPesons(890000000);
    world.getCitys().put(1311, jiangsu);

    return world;

  }

  public Kryo getKryo() {
    Kryo kryo = new Kryo();
   /* kryo.register(World.class);   //可以使用kryo.writeClassAndObject代替
    kryo.register(City.class);*/

   /* By default, each appearance of an object in the graph after the first is stored
    as an integer ordinal. This allows multiple references to the same object and cyclic
    graphs to be serialized.
    This has a small amount of overhead and can be disabled to save space if it is not needed*/
    kryo.setReferences(false);

    /*
     * Kryo#setRegistrationRequired can be set to true to throw an exception when any unregistered
     * class is encountered.
     * This prevents an application from accidentally using class name strings
     */
    kryo.setRegistrationRequired(false);

    /*
     *When ReflectASM or reflection cannot be used, Kryo can be configured to use an InstantiatorStrategy to handle
     * creating instances of a class. Objenesis provides StdInstantiatorStrategy which uses JVM specific APIs to create
      * an instance of a class without calling any constructor at all. While this works on many JVMs,
      * a zero argument is generally more portable.
     */
    kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());

    return kryo;
  }

  public void doSeria() {
    Kryo kryo = getKryo();

    Output op = new Output(new byte[4096], 5000);
    World world = combineWorld();
    kryo.writeObject(op, world);

    op.flush();
    op.close();

    ByteBuffer byteBuffer = ByteBuffer.wrap(op.getBuffer());
    System.out.println("remaining=" + byteBuffer.position());
    Input input = new ByteBufferInput(byteBuffer);

    World world2 = kryo.readObjectOrNull(input, World.class);
    System.out.println(world2.getCountry());
  }

}
