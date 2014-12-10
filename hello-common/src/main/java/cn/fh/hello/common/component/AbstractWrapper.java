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

public abstract class AbstractWrapper {
	protected boolean result = false;

	/**
	 * String representation of this Json object
	 */
	protected String jsonString;
	
	protected Map<String, String> jsonMap;
	

	/**
	 * Derived class might override this method to perform its own parse logic.
	 */
	protected void parseJson() {
		// Json has already been parsed
		if (this.jsonMap != null) {
			return;
		}
		if (null == jsonString || jsonString.isEmpty()) {
			throw new IllegalStateException("json string is null or empty");
		}
		
		// read Json string
		JsonReader reader = Json.createReader(new StringReader(jsonString));
		JsonObject obj = reader.readObject();
		// loop value
		this.jsonMap = new HashMap<String, String>();
		Set<Entry<String, JsonValue>> entrySet = obj.entrySet();
		for (Entry<String, JsonValue> entry : entrySet) {
			String name = entry.getKey();
			String value = StringUtils.trimQuotation(entry.getValue().toString());
			
			jsonMap.put(name, value);
		}

	}
	
	public String getValue(String key) {
		parseJson();
		return jsonMap.get(key);
	}
	
	public boolean isSucceeded() {
		return this.result;
	}
	
	public String getJsonString() {
		return this.jsonString;
	}
}
