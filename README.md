# file-downloader [![Build Status](https://travis-ci.org/rajat23/file-downloader.svg?branch=master)](https://travis-ci.org/rajat23/file-downloader)

Java utility to download the file given a url and location

# Run the tests:
`gradle test`

# Build the project:
`gradle clean build` 

# Run the download utitly:
`./download.sh {url} {location}`
 - e.g ./download.sh https://www.nginx.com/wp-content/uploads/2015/01/Building_Microservices_Nginx.pdf /Users/rajatc/Desktop

# We can also use this utility as command 
e.g `download {url} {location}`. 
- To make this happen we need to follow few steps as
https://stackoverflow.com/questions/8779951/how-do-i-run-a-shell-script-without-using-sh-or-bash-commands
