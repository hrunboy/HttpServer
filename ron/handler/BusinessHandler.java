package ron.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import util.math.Value;



/** 
 * ClassName: BusinessHandler <br/> 
 * Function: business handler (short time task) <br/> 
 * date: 2016年8月31日 下午8:47:34 <br/> 
 * 
 * @author Ron 
 * @version  1.0 
 */
public class BusinessHandler extends ChannelInboundHandlerAdapter {
	
	private Logger log = Logger.getLogger(BusinessHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String mString = (String) msg;
		String clientMsg = "client said : " + (String) msg;
		log.info("BusinessHandler read msg from client :" + clientMsg);
		
		String inputspe = mString.substring(mString.indexOf("=")+1,mString.length());
		String output="";
		String split[] = inputspe.split(",");
		Value value = new Value(split.length);
		String reg = "^[1-9]+[0-9]*";
		
		for (String string : split) {
			if(string.matches(reg))
				value.add(new StringBuilder(string));
			else{
				output="error api controller";
				break;
			}
		}
		if("".equals(output))
			output=value.toString();
		ctx.write(output);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
