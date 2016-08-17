<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>
<form name="form" action=""></form>
<p id="demo">

</p>
<input type="text" id="demo2">
<script>
var getToken='{"code": 0, "message": null, "response": {"access_token": "4b1e8846b355a45becbd2226ea6a42d5e3f274bb", "now": 1471394595, "expired_at": 1471396222}}';
var getTokened=JSON.parse(getToken);
var token = getTokened.response.access_token;
document.getElementById("demo").innerText=getTokened.response.access_token;	
document.getElementById("demo2").innerValue=getTokened.response.access_token;
</script>

</body>
</html>