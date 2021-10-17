
# epsychiatry coordination and management system

---
## modules
* core
* facebookApi 
* web
---
# technologies
* java 11.0.6
* tomcat 9.x.x
* maven 3.6.X
* mysql 8.0.22
* apache-server 2.4.x
* linux 18 LTS & windows 10 (development)
---
## Prerequisites
* epsychiatry database should be there
---
## folder Structure
###### create folders in follow locations
* Linux environment <br/>
`/eps/images/avatar` <br/>
`/esy/policy`  <br/>
`/eps/images/avatar/default`  <br/>
* Windows environment <br/>
`C:\eps\images\avatar`   <br/>
`C:\eps\policy\`  <br/>
`C:\eps\images\avatar\default`  <br/>

 ***copy facebook/data/images/esy_logo.jpg image to `/eps/images/avatar/default` or `C:\eps\images\avatar\default`***
 
---
## DNS 
```aidl
# esychiatry dns
127.0.0.1 manage.epsychiatry.com.au
127.0.0.1 db.mysql.es
```
windows<br/>
***append new DNS to `C:\Windows\System32\drivers\etc\hosts` file*** <br/>
Linux <br>
***append new DNS to `/etc/hosts` file***

---
## tomcat configuration
Add following configuration to tomcat context.xml
```xml
<Context>                   
    <WatchedResource>WEB-INF/web.xml</WatchedResource>
    <WatchedResource>WEB-INF/tomcat-web.xml</WatchedResource>
    <WatchedResource>${catalina.base}/conf/web.xml</WatchedResource>
    <Resources cacheMaxSize="51200"/>
</Context>
```
## database set-up
```mysql
CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL ON *.* TO 'user'@'localhost'  WITH GRANT OPTION;
```
---
## How to build
```text
mvn clean install
```
---
## How to deploy
* copy `facebook/web/target/web.war` into `tomcat/webapps`

