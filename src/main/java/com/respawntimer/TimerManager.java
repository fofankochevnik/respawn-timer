package com.respawntimer;

public class TimerManager {

    private static long endTimeMs = -1;
    private static boolean active = false;

    public static final int TOTAL_SECONDS = 300;
    public static final int WARN_SECONDS = 30;

    public static void startTimer() {
        endTimeMs = System.currentTimeMillis() + TOTAL_SECONDS * 1000L;
        active = true;
    }

    public static boolean isActive() {
        if (!active) return false;
        if (getRemainingSeconds() <= 0) {
            active = false;
            return false;
        }
        return true;
    }

    public static int getRemainingSeconds() {
        if (!active) return 0;
        long remaining = endTimeMs - System.currentTimeMillis();
        return (int) Math.max(0, (remaining + 999) / 1000);
    }

    public static boolean isWarning() {
        return getRemainingSeconds() <= WARN_SECONDS;
    }
}
