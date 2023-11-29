package net.jorge.learningmod;

import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class Timer {
    private long startTime;
    private long pauseTime;
    private long elapsedTime;
    private boolean paused;

    public Timer() {
        this.startTime = 0;
        this.pauseTime = 0;
        this.elapsedTime = 0;
        this.paused = false;
    }

    public void reset() {
        startTime = System.nanoTime();
        pauseTime = System.nanoTime();
        elapsedTime = 0;
    }

    public void pause() {
        if (startTime != 0 && pauseTime == 0) {
            pauseTime = System.nanoTime();
            paused = true;
        }
    }

    public void resume() {
        if (startTime != 0 && pauseTime != 0) {
            long elapsedPauseTime = System.nanoTime() - pauseTime;
            startTime += elapsedPauseTime;
            pauseTime = 0;
            paused = false;
        }
    }

    public void show(ServerCommandSource source) {
        if (paused) {
            elapsedTime = pauseTime - startTime;
        } else if (startTime != 0) {
            elapsedTime = System.nanoTime() - startTime;
        }

        long hours = elapsedTime / 3600000000000L;
        long minutes = (elapsedTime % 3600000000000L) / 60000000000L;
        long seconds = (elapsedTime % 60000000000L) / 1000000000L;

        source.getPlayer().sendMessage(Text.of(hours + "h " + minutes + "m " + seconds + "s"), false);

//        ScoreboardObjective objective = source.getServer().getScoreboard().getObjective("timer");
//
//        if (objective == null) {
//            objective = source.getServer().getScoreboard().addObjective("timer", net.minecraft.scoreboard.ScoreboardCriterion.DUMMY, Text.of("timer"), ScoreboardCriterion.RenderType.HEARTS);
//        }
//
//        ScoreboardPlayerScore score = source.getServer().getScoreboard().getPlayerScore(source.getPlayer().getName().getString(), objective);
//        score.setScore((int) seconds);

    }
}
