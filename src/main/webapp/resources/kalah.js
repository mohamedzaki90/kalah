$(document).ready(function() {
	var top = false;

	$("#start").click(function() {
		startNewGame();
	});

});

function play(index, player, stones) {
	if (stones == 0) {
		return;
	}

	$.ajax({
		url : "http://localhost:8080/backend/kalah/play",
		data : {
			index : index,
			player : player
		},
		success : function(data) {
			if (data) {
				if (data.gameOver) {
					alert("game over, Winner:" + data.winner);
				} else {
					updateBoard(data.leftKalah, data.topPits, data.rightKalah,
							data.downPits);
					if (!data.playAgain) {
						disablePlayer(player);
					} else {
						disableOther(player);
					}
					console.log("player: " + player + " index: " + index);
				}

			}
		}
	});
}

function disableOther(player) {
	if ( player == 0) {
		disablePlayer(1);
	} else {
		disablePlayer(0);
	}
}

function disablePlayer(player) {
	if (player == 0) {
		$(".top").attr("disabled", "disabled");
		$(".down").removeAttr("disabled");
	} else {
		$(".down").attr("disabled", "disabled");
		$(".top").removeAttr("disabled");
	}

}

function startNewGame() {
	$.ajax({
		url : "http://localhost:8080/backend/kalah/newGame",
		success : function(data) {
			if (data) {
				updateBoard(data.leftKalah, data.topPits, data.rightKalah,
						data.downPits);

				disablePlayer(0);
			}
		}
	});
}

function updateBoard(leftKalah, topPits, rightKalah, downPits) {
	$("#topPits").html("");
	$("#downPits").html("");
	$("#leftKalah").html("");
	$("#rightKalah").html("");

	for (i = 0; i < 6; i++) {
		var topStones = kalahPit(i, topPits[i], 0);
		var downStones = kalahPit(i, downPits[i], 1);
		$("#topPits").append(topStones);
		$("#downPits").append(downStones);
	}

	$("#leftKalah").append("<h3>" + leftKalah + "</h3>");
	$("#rightKalah").append("<h3>" + rightKalah + "</h3>");
}

function kalahPit(index, stones, player) {
	var playFunction = "play(" + index + "," + player + "," + stones + ")";
	var player = player == 0 ? "top" : "down"
	var pit = "<button onclick='" + playFunction
			+ "' class='btn btn-primary btn-xs pit " + player + "'>" + stones
			+ "</button>";
	return "<div class='col-sm-2'>" + pit + "</div>";
}