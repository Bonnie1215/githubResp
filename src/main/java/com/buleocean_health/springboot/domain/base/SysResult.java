package com.buleocean_health.springboot.domain.base;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 统一的返回封装对象
 * @author huyanqiu
 *
 */
@Repository
public class SysResult {
	
	@Value("${sys.successCode}")
	private Integer _successCode;
	@Value("${sys.failedCode}")
	private Integer _failedCode;
	private static Integer successCode = 1;
	private static Integer failedCode = -1;
	
	@Value("${sys.successMsg}")
	private String _successMsg;
	@Value("${sys.failedMsg}")
	private String _failedMsg;
	private static String successMsg = "SUCCESS";
	private static String failedMsg = "FAILED";
	
	// 在初始化方法运行前执行
	@PostConstruct
	public void init() {
		if (_successCode != null) successCode = _successCode;
		if (_failedCode != null) failedCode = _failedCode;
		if (_successMsg != null) successMsg = _successMsg;
		if (_failedMsg != null) failedMsg = _failedMsg;
	}
	
	public static SysResult ok() {
		return new SysResult(successCode, successMsg);
	}
	
	public static SysResult ok(String msg) {
		return new SysResult(successCode, msg);
	}
	
	public static SysResult ok(Object data) {
		return new SysResult(successCode, successMsg, data);
	}
	
	public static SysResult ok(Integer code, String msg) {
		return new SysResult(code, msg);
	}
	
	public static SysResult ok(Integer code, Object data) {
		return new SysResult(code, successMsg, data);
	}
	
	public static SysResult ok(Integer code, String msg, Object data) {
		return new SysResult(code, msg, data);
	}
	
	public static SysResult error() {
		return new SysResult(failedCode, failedMsg);
	}
	
	public static SysResult error(String msg) {
		return new SysResult(failedCode, msg);
	}
	
	public static SysResult error(Object data) {
		return new SysResult(failedCode, failedMsg, data);
	}
	
	public static SysResult error(Integer code, String msg) {
		return new SysResult(code, msg);
	}
	
	public static SysResult error(Integer code, Object data) {
		return new SysResult(code, failedMsg, data);
	}
	
	public static SysResult error(Integer code, String msg, Object data) {
		return new SysResult(code, msg, data);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	private Integer code;
	private String msg;
	private Object data;
	
	///////////////////////////////////////
	///// 构造方法
	//////////////////////////////////////
	public SysResult() {
		
	}
	public SysResult(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public SysResult(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	///////////////////////////////////////
	///// get、set方法
	//////////////////////////////////////
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data==null ? new HashMap<>() : data; // 修改
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysResult other = (SysResult) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.WRITE_MAP_NULL_FEATURES);
	}
	
}
