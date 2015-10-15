package com.srpc.communication;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"serviceName",
"methodName",
"params"
})
public class RequestPacket {

	@JsonProperty("serviceName")
	public String serviceName;
	
	@JsonProperty("methodName")
	public String methodName;
	
	@JsonProperty("params")
	public List<ParamPacket> params = new ArrayList<ParamPacket>();

}
