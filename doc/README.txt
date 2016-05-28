mvn clean compile

curl -v -H "Accept: application/json" "http://localhost:8080/mvc/members/member/11.json" | python -m json.tool

curl -v -H "Accept: */*" "http://localhost:8080/mvc/members/create"

curl -v -H "Accept: application/xml" "http://localhost:8080/mvc/members/account.xml"

http://localhost:8080/mvc/jsps/jsptest
