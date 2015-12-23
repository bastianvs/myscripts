#! /bin/sh

if [[ $# -eq 0 ]] ; then
    echo 'Enter the Bucketname: Usage testmasterfile.sh <Bucketname>'
    exit 1
fi

echo "checking bucket: $1"
dbdump=/opt/couchbase/bin/couch_dbdump
masterfile=/opt/couchbase/var/lib/couchbase/data/$1/master.couch.*

command="$dbdump $masterfile"
      if $command >/dev/null
      then
          echo "$1 master.couch Verified"
     else
          echo "$1 master.couch Corrupted"
          # Email alert?
      fi


