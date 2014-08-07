#! bin/bash
sourcefile=listusers
users=`cat $sourcefile | cut -f1 -d,`
for virtualuser in $users
do
virtualpassword=`cat $sourcefile | grep $virtualuser | cut -f2 -d,`
useradd $virtualuser
echo "$virtualpassword" | passwd --stdin $virtualuser
echo "$virtualuser has been successfully added with password of $virtualpassword"
done

#cat listusers
#test,1234
#bastian,unimax
#bobo,test
