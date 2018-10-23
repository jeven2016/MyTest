package kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KryoPoolUtil {

  private static KryoFactory factory = new KryoFactory() {
    @Override
    public Kryo create() {
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
  };

  private static KryoPool pool;

  private static Lock initialLock = new ReentrantLock();

  public static Kryo getKryoInstance() {
    if (pool == null) {
      try {
        initialLock.lock();
        if (pool == null) {
          //soft reference
          pool = new KryoPool.Builder(factory).softReferences().build();
        }
      } finally {
        initialLock.unlock();
      }
    }
    return pool.borrow();
  }

  public static void returnKryo(Kryo kryoObj) {
    pool.release(kryoObj);
  }

}
