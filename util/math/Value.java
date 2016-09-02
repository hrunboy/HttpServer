 
  
package util.math;

import java.util.Arrays;

/** 
 * ClassName: Value <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2016年8月31日 下午10:41:21 <br/> 
 * 
 * @author runboy 
 * @version   
 */
public class Value {
	private int index;
	private int size;
	private StringBuilder[] val1;
	
	public Value(int size){
		this.index = 0;
		this.size =size;
		this.val1 = new StringBuilder[size];
	}
	
	public  void add(StringBuilder value){
		for(int i=0;i < size; ++i){
			if(i==index)
				continue;
			if(val1[i]==null || "".equals(val1[i])){
				val1[i]=value;
				continue;
			}
			val1[i] = Math.multiply(val1[i], value);
		}
		++index;
	}

	@Override
	public String toString() {
		StringBuilder value=new StringBuilder();
		for(int i=0;i<this.size;++i){
			value = value.append(val1[i]).append(",");
		}
		return value.toString();
	}
	
}
  