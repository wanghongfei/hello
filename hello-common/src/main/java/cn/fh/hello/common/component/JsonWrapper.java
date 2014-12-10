package cn.fh.hello.common.component;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import cn.fh.hello.common.exception.unchecked.NotEnoughParameterException;

public class JsonWrapper extends AbstractWrapper {
	public static final Logger logger = LoggerFactory.getLogger(JsonWrapper.class);

	/**
	 * Construct JsonWrapper using Json String directly
	 * @param jsonString
	 */
	public JsonWrapper(String jsonString) {
		this.jsonString = jsonString;
	}
	
	/**
	 * 构造JSON对象.
	 * <p> {result: 'true / false', message: 'message'}
	 * 
	 * @param result
	 * @param message
	 */
	public JsonWrapper(boolean result, String message) {
		this.result = result;

		JsonObject obj = Json.createObjectBuilder()
				.add("result", result)
				.add("message", message)
				.build();
		
		this.jsonString = obj.toString();
	}

	/**
	 * 构造 {result: 'true', parm: {'p1': 'p1', 'p2': 'p2', ...} }
	 * 
	 * @param result 状态值，true or false
	 * @param eList 封装ObjectError的List对象
	 */	
	public JsonWrapper(boolean result, List<ObjectError> eList) {
		// 构造内部JSON对象
		JsonObjectBuilder innerBuilder = Json.createObjectBuilder();
		int ix = 0;
		String key = null;
		String value = null;
		// 遍历Error对象
		for (ObjectError err : eList) {
			if (err instanceof FieldError) {
				FieldError fErr = (FieldError)err;
				key = fErr.getField();
				value = fErr.getDefaultMessage();
			} else {
				key = Integer.toString(ix);
				value = err.getDefaultMessage();
			}
			
			// 把错误信息添加到JSON中
			innerBuilder.add(key, value);
			
			++ix;
		}
		
		// 构造外部JSON
		JsonObject json = Json.createObjectBuilder()
				.add("result", result)
				.add("parm", innerBuilder)
				.build();
		
		this.jsonString = json.toString();
	}
	/**
	 * 构造 {result: 'true', parm: {'p1': 'p1', 'p2': 'p2', ...} }
	 * 
	 * @param result 状态值，true or false
	 */	
	public JsonWrapper(boolean result, Map<String, String> parmMap) {
		// 遍历Map
		String key = null;
		String value = null;
		Set<Entry<String, String>> entrySet = parmMap.entrySet();
		// 构造内部JSON对象
		JsonObjectBuilder innerBuilder = Json.createObjectBuilder();
		for (Map.Entry<String, String> entry : entrySet) {
			key = entry.getKey();
			value = entry.getValue();
			
			// 添加到JSON中
			innerBuilder.add(key, value);
		}
		
		// 构造外部JSON对象
		JsonObject json = Json.createObjectBuilder()
				.add("result", result)
				.add("parm", innerBuilder)
				.build();
		
		this.jsonString = json.toString();
	}
	/**
	 * 构造 {result: 'true', parm: {'p1': 'p1', 'p2': 'p2', ...} }
	 * 
	 * @param result 状态值，true or false
	 * @param args 参数个数必须为2的倍数
	 */
	public JsonWrapper(boolean result, String... args) {
		this.result = result;

		int LEN = args.length;
		if (LEN  <= 0 || LEN % 2 != 0) {
			throw new NotEnoughParameterException("从第2个参数起，参数数量必须为2的倍数");
		}
		
		// 构造内部JSON对象
		String parmName = null;
		String parmValue = null;
		JsonObjectBuilder innerObjBuilder = Json.createObjectBuilder();
		for (int ix = 0 ; ix < LEN ; ix += 2) {
			parmName = args[ix];
			parmValue = args[ix + 1];

			innerObjBuilder.add(parmName, parmValue);
		}
		
		JsonObject innerObj = innerObjBuilder.build();
		
		JsonObject outerObj = Json.createObjectBuilder()
				.add("result", result)
				.add("parm", innerObj)
				.build();
		
		this.jsonString = outerObj.toString();
		
	}

}