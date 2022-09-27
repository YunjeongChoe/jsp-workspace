<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@2.3.3"></script>
</head>
<body>

	<div id="app">
		<h2>{{message}}</h2>
	</div> 
	  
	<script>
	 var model = {
	 message : "Hello Vue.js~"
	 }
	 
	 var _app = new Vue({
	 el : "#app",
	 data : model
	 })
	</script>
</body>
</html>
