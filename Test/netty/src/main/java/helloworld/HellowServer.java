package helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HellowServer {
  public static void main(String[] args) {
    startUp();
  }

  public static void startUp() {
    EventLoopGroup group1 = new NioEventLoopGroup();
    EventLoopGroup group2 = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap().group(group1, group2)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 1024)
            .childHandler(new ChannelInit());

    ChannelFuture future = null;
    try {
      future = bootstrap.bind("127.0.0.1", 48992).sync();
      future.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      group1.shutdownGracefully();
      group2.shutdownGracefully();
    }
  }

  private static class ChannelInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
      socketChannel.pipeline().addLast(new HellowHandler());
    }
  }

  private static class HellowHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      ByteBuf buf = (ByteBuf) msg;
      byte[] bytes = new byte[buf.readableBytes()];
//      ByteBuffer buffer = ByteBuffer.allocate(buf.readableBytes());
      buf.readBytes(bytes);
      String string = new String(bytes, "UTF-8");

      String resp = "I get you from server :" + string;
      ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
      ctx.flush();
    }
  }

}
