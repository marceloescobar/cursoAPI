package com.mescobar.curso.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTToken {

	private String idToken;

	JWTToken(String idToken) {
		this.idToken = idToken;
	}

	@JsonProperty("id_token")
	String getIdToken() {
		return idToken;
	}

	void setIdToken(String idToken) {
		this.idToken = idToken;
	}

}
