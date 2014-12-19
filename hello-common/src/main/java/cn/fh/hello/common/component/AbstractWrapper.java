package cn.fh.hello.common.component;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import cn.fh.hello.common.component.utils.StringUtils;

/**
 * This is a JSON string wrapper class which encapsulate JSON string 
 * and provides multiply read methods
 * @author whf
 *
 */
public abstract class AbstractWrapper {
	protected boolean result = false;

	/**
	 * String representation of this Json object
	 */
	protected String jsonString;
	
	protected Map<String, String> jsonMap;
	

	/**
	 * Convert JSON string to JsonObject.
	 * <p> Derived class might override this method to perform its own parse logic.
	 */
	protected void parseJson() {
		// Json has already been parsed
		if (this.jsonMap != null) {
			return;
		}
		if (null == jsonString || jsonString.isEmpty()) {
			throw new IllegalStateException("json string is null or empty");
		}
		
		// Convert json string to JsonObject
		JsonReader reader = Json.createReader(new StringReader(jsonString));
		JsonObject obj = reader.readObject();

		// loop value
		Map<String, String> tempJsonMap = new HashMap<String, String>();
		Set<Entry<String, JsonValue>> entrySet = obj.entrySet();
		for (Entry<String, JsonValue> entry : entrySet) {
			String name = entry.getKey();
			// the value string is surrounded by quotations
			// so we need to trim them off
			String value = StringUtils.trimQuotation(entry.getValue().toString());
			
			tempJsonMap.put(name, value);
		}
		
		
		this.jsonMap = tempJsonMap;
	}
	
	public final String getValue(String key) {
		parseJson();
		return jsonMap.get(key);
	}
	
	public final int getObjectAmount() {
		return jsonMap.size();
	}
	
	public final boolean isSucceeded() {
		return this.result;
	}
	
	public final String getJsonString() {
		return this.jsonString;
	}
}
