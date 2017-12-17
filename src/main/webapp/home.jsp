<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
<link rel="stylesheet" type="text/css" href="resources/style.css"/>	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script 
	src="resources/kalah.js"></script>
<script>

	
</script>
</head>
<body>
	<div class="container" id="game">
		<div id="board">
			<div class="row">
				<button id="start" class="btn btn-primary">Start new game</button>
			</div>
			
			<div class="row" id="game">
				<div class="well col-sm-2 kalah text-center" id="leftKalah">				
				</div>
				
				<div class="well col-sm-6" id="pits">
					<div class="row text-center" id="topPits"></div>
					<div class="row text-center" id="downPits"></div>
				</div>
				
				<div class="well col-sm-2 kalah text-center" id="rightKalah">
				</div>
			</div>
		</div>
	</div>
</body>
</html>