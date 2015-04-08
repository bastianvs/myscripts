package com.couchbase.directx;


import com.couchbase.client.java.Bucket; 
import com.couchbase.client.java.Cluster; 
import com.couchbase.client.java.CouchbaseCluster; 
import com.couchbase.client.*;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;
import java.net.*;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    
private final String host; 
private final String password; 
private final String bucketName; 
private final String fileName; 


public App(String host, String bucketName, String password, String fileName) { 
this.host = host; 
this.password = password; 
this.bucketName = bucketName; 
this.fileName = fileName;
}

public void run()  { 
System.out.println("HelloWorld"); 
Cluster cluster = CouchbaseCluster.create(Arrays.asList(host)); 
Bucket bucket = cluster.openBucket(bucketName, password); 
ViewResult result = bucket.query(ViewQuery.from("direct", "direct"));

//FileReader file = new FileReader(fileName); 
//BufferedReader in = new BufferedReader(file); 
//String line; 
//while ((line = in.readLine()) != null ) { 
//bucket.remove(line);
//}

for (ViewRow row : result) {
    bucket.remove(row.id());
    //System.out.println(row.id());
}


}
public static void main(String[] args) { 
//Parameters p = new Parameters(); 
App m = new App("10.4.2.105:8091", "gamesim-sample", "", ""); 
m.run(); 
}

 
}

