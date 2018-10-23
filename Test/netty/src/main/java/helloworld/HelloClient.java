package helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by jujucom on 6/12/15.
 */
public class HelloClient {
  public static void startUp(){
    EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap                                      bs = new Bootstrap().group(group)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY,true) ;
//            .handler()
  }
}
