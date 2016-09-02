package ron.coding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders.Values;
import ron.handler.BusinessHandler;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


/** 
 * ClassName: StringEncoder <br/> 
 * Function: Message encoder <br/> 
 * date: 2016年8月31日 下午8:39:27 <br/> 
 * 
 * @author Ron 
 * @version  1.0 
 */
public class StringEncoder extends ChannelOutboundHandlerAdapter {
	
	private Logger log = Logger.getLogger(BusinessHandler.class);

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		log.info("StringEncoder response to client.");
		String serverMsg = (String) msg;

		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(serverMsg
				.getBytes()));
		response.headers().set(CONTENT_TYPE, "text/plain");
		response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
		response.headers().set(CONNECTION, Values.KEEP_ALIVE);
		ctx.write(response);
		ctx.flush();
	}
}
