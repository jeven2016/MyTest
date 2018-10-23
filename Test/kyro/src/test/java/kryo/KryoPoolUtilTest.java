package kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

public class KryoPoolUtilTest {

  @Test
  public void testInstance() {
    Kryo kryo = KryoPoolUtil.getKryoInstance();
    assertNotNull(kryo);
    Kryo kryo2 = KryoPoolUtil.getKryoInstance();
    assertFalse(kryo == kryo2);
  }

  @Test
  public void seria() {
    World world = HelloWorld.combineWorld();
    Kryo kryo = KryoPoolUtil.getKryoInstance();

    Output output = new Output(4096000);
    kryo.writeObjectOrNull(output, world, world.getClass());

    output.flush();
    byte[] data = output.toBytes();

    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

    Input input = new Input(byteArrayInputStream);
    World world2 = kryo.readObjectOrNull(input, World.class);
    System.out.println("world2.getCountry():" + world2.getCountry());
  }
}
