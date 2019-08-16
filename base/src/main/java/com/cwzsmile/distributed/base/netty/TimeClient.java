package com.cwzsmile.distributed.base.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author csh9016
 * @date 2019/8/16
 */
public class TimeClient {

    public void connect(int port, String host) throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        //配置客户端NIO线程组
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            //发起异步操作
            ChannelFuture f = b.connect(host, port).sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new TimeClient().connect(443,"180.101.49.11");
    }
}
