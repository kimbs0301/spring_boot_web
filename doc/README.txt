mvn clean compile

git diff
git add -A
git commit -a -m "ok"
git push


curl -v -H "Accept: application/json" "http://localhost:8080/mvc/members/member/11.json" | python -m json.tool

curl -v -H "Accept: */*" "http://localhost:8080/mvc/members/create"

curl -v -H "Accept: application/xml" "http://localhost:8080/mvc/members/account.xml"

http://localhost:8080/mvc/jsps/jsptest
http://localhost:8080/mvc/jsps/welcome
