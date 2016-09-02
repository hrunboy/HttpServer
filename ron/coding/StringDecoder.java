package ron.coding;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;

/** 
 * ClassName: StringDecoder <br/> 
 * Function: Message Decoder <br/> 
 * date: 2016年8月31日 下午8:42:56 <br/> 
 * 
 * @author Ron 
 * @version  1.0 
 */
public class StringDecoder extends ChannelInboundHandlerAdapter {
	
	private Logger log = Logger.getLogger(StringDecoder.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("StringDecoder : msg's type is " + msg.getClass());
		if (msg instanceof HttpRequest) {
			HttpRequest request = (HttpRequest) msg;
//			reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(request));
			String uri = request.uri();
			if(!"".equals(uri) && uri != null && uri.startsWith("/answer/?l")){
				ctx.fireChannelRead(uri);
			}
		}

	}
}
