package com.zml.mongo.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.zml.mongo.dao.impl.MongoDBDaoImpl;
import com.zml.mongo.entity.StarEntity;
import com.zml.mongo.util.TimestampTypeAdapter;

public class TestMongoDB {
	private final OkHttpClient client = new OkHttpClient();
	//private final Gson gson = new Gson();
	private final MongoDBDaoImpl db = new MongoDBDaoImpl();
	
	public String run() throws IOException {
		
		Request request = new Request.Builder()
			      .url("https://api.github.com/users/zhaoml529/starred")
			      .build();
		Response response = client.newCall(request).execute();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.body().string());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);
		return prettyJsonString;
	}
	
	/**
	 * 将获取的json数据封装到实体类中
	 * @param json
	 * @return
	 */
	public List<StarEntity> getEntity(String json) {
		//Gson gson = new Gson();//new一个Gson对象
		final Gson gson = new GsonBuilder()
		.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter())
		.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonParser parser = new JsonParser();
	    JsonArray Jarray = parser.parse(json).getAsJsonArray();
	    List<StarEntity> starList = new ArrayList<StarEntity>();
	    for(JsonElement obj : Jarray ){
	    	StarEntity star  = gson.fromJson( obj , StarEntity.class);
	    	starList.add(star);
	    }
        return starList;
	}
	
	/**
	 * 插入多个document
	 * @param list
	 */
	public void insertMany(List<StarEntity> list) {
		
		List<Document> listDoc = new ArrayList<Document>();
		for(StarEntity star: list) {
			Document document = new Document();
			document.put("id", star.getId());
			document.put("name", star.getName());
			document.put("full_name", star.getFull_name());
			document.put("description", star.getDescription());
			document.put("fork", star.getFork());
			document.put("url", star.getUrl());
			document.put("forks_url", star.getForks_url());
			//document.put("created_at", star.getCreated_at());
			//document.put("updated_at", star.getUpdated_at());
			document.put("forks_count", star.getForks_count());
			document.put("watchers_count", star.getWatchers_count());
			listDoc.add(document);
		}
		this.db.insertMany("star_info", listDoc);
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<StarEntity> findAll() {
		//final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		final Gson gson = new GsonBuilder()
				.registerTypeAdapter(Date.class,new TimestampTypeAdapter())
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		//final Gson gson = GsonBuilderUtil.create();
		final List<StarEntity> starList = new ArrayList<StarEntity>();
		FindIterable<Document> iterable = this.db.findAll("star_info");
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	System.out.println(document.toJson());
		    	StarEntity star = gson.fromJson(document.toJson(), StarEntity.class);
		    	starList.add(star);
		    }
		});
		return starList;
	}
	
	public List<StarEntity> find() {
		final Gson gson = new Gson();//new一个Gson对象
		final List<StarEntity> starList = new ArrayList<StarEntity>();
		Document document = new Document();
		document.put("id", "27437239");
		document.put("name", "SpringOA");
		FindIterable<Document> iterable = this.db.find("star_info", document);
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		    	System.out.println(document.toJson());
		    	StarEntity star = gson.fromJson(document.toJson(), StarEntity.class);
		    	starList.add(star);
		    }
		});
		return starList;
	}
	
	public void delete() {
		//this.db.deleteOne("star_info", new Document("id","25348756"));
		this.db.deleteMany("star_info", new Document());
	}
	
	public static void main(String[] args) throws IOException {
		TestMongoDB mongo = new TestMongoDB();
		//插入
		//List<StarEntity> list = mongo.getEntity(mongo.run());
		//mongo.insertMany(list);
		
		//查询
		/*List<StarEntity> starList = mongo.findAll();
		for(StarEntity star : starList) {
			System.out.println(star.get_id().get$oid()+"---"+star.getName()+"---"+star.getId());
		}*/
		
		mongo.find();
		
		//mongo.delete();
		
		//System.out.println(mongo.getEntity(mongo.run()));
	}
}
