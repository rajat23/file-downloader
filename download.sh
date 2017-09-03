#!/bin/sh

# sample url = https://www.nginx.com/wp-content/uploads/2015/01/Building_Microservices_Nginx.pdf
# sample location = Users/rajatc/Desktop

url=$1
location=$2

java -jar build/libs/file-downloader-1.0-SNAPSHOT.jar $url $location