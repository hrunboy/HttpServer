package ron.server.httpServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture; 
import io.netty.channel.ChannelInitializer; 
import io.netty.channel.ChannelOption; 
import io.netty.channel.EventLoopGroup; 
import io.netty.channel.nio.NioEventLoopGroup; 
import io.netty.channel.socket.SocketChannel; 
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder; 
import io.netty.handler.codec.http.HttpResponseEncoder;
import ron.coding.StringDecoder;
import ron.coding.StringEncoder;
import ron.handler.BusinessHandler;

/** 
 * ClassName: HttpServer <br/> 
 * Function: HttpServer boot <br/> 
 * date: 2016年8月31日 下午8:37:44 <br/> 
 * 
 * @author Ron 
 * @version  1.0 
 */
public class HttpServer {

	private  String url;
	
	private  int    port;
	
	private HttpServer(){
	}
	
	public static final HttpServer open(){
		return HttpServerHolder.SERVER;
	}
	
	public void conifg (String url,int port){
		this.url = url;
		this.port = port;
	}
	
	
	public void start(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
	
					 @Override
	                 public void initChannel(SocketChannel ch) throws Exception {
							// 都属于ChannelOutboundHandler，逆序执行
							ch.pipeline().addLast(new HttpResponseEncoder());
							ch.pipeline().addLast(new StringEncoder());
							
							// 都属于ChannelIntboundHandler，按照顺序执行
							ch.pipeline().addLast(new HttpRequestDecoder());
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new BusinessHandler());
	                 }
	             }).option(ChannelOption.SO_BACKLOG, 128) 
				.childOption(ChannelOption.SO_KEEPALIVE, true);
			   ChannelFuture f = bootstrap.bind(url,port).sync();
			   System.out.println("HTTP server 启动,开始监听 http://"+url+":"+port+"/");
	
			   f.channel().closeFuture().sync();
       } catch (InterruptedException e) {
		e.printStackTrace();
	} finally {
           workerGroup.shutdownGracefully();
           bossGroup.shutdownGracefully();
       }
	}
	
	private static class HttpServerHolder{
		private static final HttpServer SERVER = new HttpServer();
	}
	
}
