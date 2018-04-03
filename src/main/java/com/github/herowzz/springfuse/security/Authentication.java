package com.github.herowzz.springfuse.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.json.JacksonJsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Authentication {

	private String id = "wyx";

	private String subject = "";

	private String issuer = "springfuse";

	private String type = "";

	private String securityKey = "";

	public Authentication() {
	}

	public String buildToken(String securityKey) throws JsonProcessingException {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("subject", subject);
		map.put("issuer", issuer);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		return json;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public static void main(String[] args) throws Exception {
		String securityKey = "snagf@#FG^%WFSDSDFds.,dsfd?fsdf?fd/dfg]d\f=";

		String headJson = "{'org' : 'springfuse', 'author' : 'wangzz'}";
		String head = Base64.getEncoder().encodeToString(headJson.getBytes(StandardCharsets.UTF_8));
		System.out.println("head:   " + head);

		Map<String, String> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		String payload = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
		System.out.println("payload:   " + payload);

		String signatureSource = head + "." + payload + "." + securityKey;
		String signature = DigestUtils.sha256Hex(signatureSource);
		System.out.println("signature:   " + signature);

		String result = head + "." + payload + "." + signature;
		System.out.println(result);

		String[] array = result.split("\\.");
		System.out.println(array.length);
		if (array.length == 3) {
			String mapJson = array[1];
			String s = new String(Base64.getDecoder().decode(mapJson));
			System.out.println(s);
			JacksonJsonParser jsonParser = new JacksonJsonParser();
			Map<String, Object> mmap = jsonParser.parseMap(s);
			for (Entry<String, Object> entry : mmap.entrySet()) {
				System.out.println(entry.getKey() + "---" + entry.getValue());
			}
		}
	}
}
