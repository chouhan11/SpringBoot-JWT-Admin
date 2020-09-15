package com.emotions.api;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.ToString;



@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(as = Model.class)
public class Model {
	JSONObject emotion;

	
}
