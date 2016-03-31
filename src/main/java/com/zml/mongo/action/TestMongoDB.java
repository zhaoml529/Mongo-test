package com.zml.mongo.action;

import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestMongoDB {
	private final OkHttpClient client = new OkHttpClient();
	//private final Gson gson = new Gson();
	
	public String run() throws IOException {
		
		Request request = new Request.Builder()
			      .url("https://api.github.com/users/zhaoml529/starred")
			      .build();
		Response response = client.newCall(request).execute();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.body().string());
        String prettyJsonString = gson.toJson(je);
		return prettyJsonString;
	}
	
	public static void main(String[] args) throws IOException {
		TestMongoDB mongo = new TestMongoDB();
		System.out.println(mongo.run());
	}
}
