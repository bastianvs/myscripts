import urllib2
import json
import sys

def main():
    cluster_ip = sys.argv[1]
    bucket = sys.argv[2]
    bucket_map_url = "http://" + cluster_ip + ":8091/pools/default/buckets/" + bucket

    bucket_map_dump = json.loads(urllib2.urlopen(bucket_map_url).read())
    vbucket_map = bucket_map_dump['vBucketServerMap']['vBucketMap']
    server_list = bucket_map_dump['vBucketServerMap']['serverList']

    # Missing vbucket is denoted by -1 in vbucketMap
    identifier = -1

    # Index to keep track of vbucket
    index = -1
    missing_vbucket_count = 0
    print "Server list: ", ', '.join(server_list)
    for vbucket in vbucket_map:
        index += 1
        if vbucket[0] == identifier:
            print "Missing active vbucketid: ", index
            missing_vbucket_count += 1
        elif identifier in vbucket[1:]:
            print "Missing replica vbucketid: ", index,\
                " Index in server_list: ", vbucket.index(identifier)
            missing_vbucket_count += 1
        else:
            pass

    print "vbucket(active/replica) missing: ", missing_vbucket_count

if __name__ == '__main__':
    main()
