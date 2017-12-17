package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.KalahGame;

@RestController
@RequestMapping("/kalah")
public class KalahController {
    private KalahGame game;

    @RequestMapping("newGame")
    public KalahGame startNewGame() {
        game = new KalahGame();
        return game;
    }

    @RequestMapping("play")
    public KalahGame play(@RequestParam Integer index, @RequestParam Integer player) {
        game.play(index, player);
        return game;
    }
}
