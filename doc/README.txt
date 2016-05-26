curl -v -H "Accept: application/json" "http://localhost:8080/mvc/members/member/11.json" | python -m json.tool

curl -v -H "Accept: */*" "http://localhost:8080/mvc/members/create"

curl -v -H "Accept: application/xml" "http://localhost:8080/mvc/members/account.xml"

http://localhost:8080/mvc/jsps/jsptest.jsp


curl -v -H "Content-Type:application/xml; charset=utf-8" "http://localhost:8080/mvc/members/xml/xml"

curl -v -H "Accept: application/xml" "http://localhost:8080/mvc/members/xml"

curl "http://localhost:8080/mvc/members/user/create"

curl -v "http://localhost:8080/mvc/members/xml/xml.xml"