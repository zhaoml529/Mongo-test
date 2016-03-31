package com.zml.mongo.dao.impl;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zml.mongo.dao.MongoDBDao;

public class MongoDBDaoImpl implements MongoDBDao {

	private static String DBName = "MongoDBTest";//数据库名
    private static String hostName = "localhost";//主机名
	
	 /** 
     * MongoClient的实例代表数据库连接池，是线程安全的，可以被多线程共享，客户端在多线程条件下仅维持一个实例即可 
     * Mongo是非线程安全的，目前mongodb API中已经建议用MongoClient替代Mongo 
     */  
    private MongoClient mongoClient = null;  
	
    
    /** 
     *  
     * 私有的构造函数 
     */  
    private MongoDBDaoImpl(){  
        if(mongoClient == null){  
            MongoClientOptions.Builder build = new MongoClientOptions.Builder();          
            build.connectionsPerHost(50);   //与目标数据库能够建立的最大connection数量为50  
            build.threadsAllowedToBlockForConnectionMultiplier(50); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待  
            /* 
             * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟 
             * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception 
             * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败 
             */  
            build.maxWaitTime(1000*60*2);  
            build.connectTimeout(1000*60*1);    //与数据库建立连接的timeout设置为1分钟  
              
            MongoClientOptions myOptions = build.build();         
            try {  
                //数据库连接实例  
                mongoClient = new MongoClient(hostName, myOptions);            
            } catch (MongoException e){  
                e.printStackTrace();  
            }  
              
        }  
    }  
    
    /********单例模式声明开始，采用饿汉式方式生成，保证线程安全********************/  
    
    //类初始化时，自行实例化，饿汉式单例模式  
    private static final MongoDBDaoImpl mongoDBDaoImpl = new MongoDBDaoImpl();  
    /** 
     *  
     * 方法名：getMongoDBDaoImplInstance 
     * 描述：单例的静态工厂方法 
     * @return 
     */  
    public static MongoDBDaoImpl getMongoDBDaoImplInstance(){  
        return mongoDBDaoImpl;  
    }  
    
  //获取数据库连接
    public MongoDatabase getDB(){
    	MongoDatabase db = this.mongoClient.getDatabase(DBName);
        return db;
    }

	public MongoCollection<Document> getCollection(String collectionName) {
		return this.getDB().getCollection(collectionName);
	}

	public boolean insert(String collectionName, BasicDBObject bean) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(String collectionName, String[] keys, Object[] values) {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<DBObject> find(String collectionName, String[] keys,
			Object[] values, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(String collectionName, DBObject oldValue,
			DBObject newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isExit(String collectionName, String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}


}
