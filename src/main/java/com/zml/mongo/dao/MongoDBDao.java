package com.zml.mongo.dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;

public interface MongoDBDao {
    /** 
     *  
     * 方法名：getCollection 
     * 描述：获取指定mongodb数据库的collection集合 
     * @param dbName    数据库名 
     * @param collectionName    数据库集合 
     * @return 
     */  
    public MongoCollection<Document> getCollection(String collectionName);  
    /** 
     *  
     * 方法名：inSert 
     * 描述：向指定的数据库中添加给定的keys和相应的values 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @return 
     */  
    public boolean insert(String collectionName, BasicDBObject bean);  
    /** 
     *  
     * 方法名：delete 
     * 描述：删除数据库dbName中，指定keys和相应values的值 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @return 
     */  
    public boolean delete(String collectionName, String[] keys, Object[] values);  
    /** 
     *  
     * 方法名：find 
     * 描述：从数据库dbName中查找指定keys和相应values的值 
     * @param dbName 
     * @param collectionName 
     * @param keys 
     * @param values 
     * @param num 
     * @return 
     */  
    public ArrayList<DBObject> find(String collectionName, String[] keys, Object[] values, int num);  
    /** 
     *  
     * 方法名：update 
     * 描述：更新数据库dbName，用指定的newValue更新oldValue 
     * @param dbName 
     * @param collectionName 
     * @param oldValue 
     * @param newValue 
     * @return 
     */  
    public boolean update(String collectionName, DBObject oldValue, DBObject newValue);  
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
