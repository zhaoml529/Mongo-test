package com.zml.mongo.dao;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public interface MongoDBDao {
	/**
	 * 获取指定mongodb数据库的collection集合 
	 * @param collectionName	
	 * @return
	 */
    public MongoCollection<Document> getCollection(String collectionName);  
    
    /**
     * 插入一个document
     * @param collectionName
     * @param document
     */
    public void insertOne(String collectionName, Document document);  
    
    /**
     * 插入多个document
     * @param collectionName
     * @param documents
     */
    public void insertMany(String collectionName, List<Document> documents);
   
    /**
     * 删除指定collectionName中的document
     * @param collectionName
     * @param document
     * @return
     */
    public void deleteOne(String collectionName, Document document);  
    
    /**
     * 删除多个
     * @param collectionName
     * @param document
     * @return
     */
    public void deleteMoney(String collectionName, Document document);  
    
    /**
     * 根据collectionName查询所有
     * @param collectionName
     * @param keys
     * @param values
     * @param num
     * @return
     */
    public FindIterable<Document> findAll(String collectionName);  
    
    /**
     * 条件查询
     * @param collectionName
     * @param document
     * @return
     */
    public FindIterable<Document> find(String collectionName, Document document);
    
    /**
     * 更新
     * @param document1
     * @param document2
     * @return
     */
    public boolean updateOne(Document document1, Document document2);  
    /** 
     *  
     * 方法名：isExit 
     * 描述：判断给定的keys和相应的values在指定的dbName的collectionName集合中是否存在 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @return 
     */  
    public boolean isExit(String collectionName, String key, Object value);
}
