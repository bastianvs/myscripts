package com.couchbase.examples;


import com.couchbase.client.*;
import com.couchbase.client.clustermanager.BucketType;
import java.net.*;
import java.util.*;


public class App {

   
    public static void main(String[] args ) throws Exception
    {
    ArrayList<URI> nodes = new ArrayList<URI>();


    // Add one or more nodes of your cluster (exchange the IP with yours)
    nodes.add(URI.create("http://10.4.2.105:8091/pools"));
    //nodes.add(URI.create("http://10.4.2.116:8091/pools"));
    // Try to connect to the client
    
  ClusterManager clusterManager = new ClusterManager (nodes, "ops", "password" );
//if (clusterManager.listBuckets().contains("bastian1"))
//{
  //  clusterManager.deleteBucket("beer-sample");
//}
  
//clusterManager.createNamedBucket(BucketType.COUCHBASE, "beer-sample", 250, 0, "", true );

    
   CouchbaseClient client = null;
    try {
      client = new CouchbaseClient(nodes, "beer-sample", "");
    } catch (Exception e) {
      System.err.println("Error connecting to Couchbase: " + e.getMessage());
      System.exit(1);
    }

    List <String> availableBuckets = clusterManager.listBuckets();
    for ( int i=0;i<availableBuckets.size();i++)
    {System.out.println(availableBuckets.get(i));
    }   
    System.out.println("HelloWorld"); 
    
    
    // Set your first document with a key of "hello" and a value of "couchbase!"
    //client.set("hello", "couchbase!").get();

    // Return the result and cast it to string
    //String result = (String) client.get("hello");
    //System.out.println(result);

    // Shutdown the client
    client.shutdown();
  }
}
