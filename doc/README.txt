이클립스 단축키 지정
Run Spring Boot App : control + shift + B
Stop Application : control + shift + T


mvn clean compile

cd /workspace/luna/spring_boot_example
git diff
git add -A
git commit -a -m "ok"
git push


curl -v -H "Accept: application/json" "http://localhost:8080/mvc/members/member/11.json" | python -m json.tool
curl -v -H "Accept: */*" "http://localhost:8080/mvc/members/create"
curl -v -H "Accept: application/xml" "http://localhost:8080/mvc/members/account.xml"
http://localhost:8080/mvc/jsps/jsptest
http://localhost:8080/mvc/jsps/welcome
http://localhost:8080/mvc/file/
http://localhost:8080/mvc/filedownload/sample/tmpfile/ok
http://localhost:8080/mvc/filedownload/sample/tmpfile.json/ok
curl -v -E ./file.crt.pem --key ./file.key.pem -H "Accept: application/json" "https://localhost:8443/mvc/members/member/11.json" | python -m json.tool

curl -E ./file.crt.pem --key ./file.key.pem

mvn assembly:assembly

cd /workspace/luna/spring_boot_example
mvn clean install

cd /tools/tomcat-8.0.33/webapps
rm -rf /tools/tomcat-8.0.33/webapps/mvc
cp -R /workspace/luna/spring_boot_example/target/mvc .
cd /tools/tomcat-8.0.33/bin



org.springframework.scheduling.config.ScheduledTaskRegistrar
107