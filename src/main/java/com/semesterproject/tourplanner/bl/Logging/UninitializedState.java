package com.semesterproject.tourplanner.bl.Logging;

import com.semesterproject.tourplanner.bl.Logging.LoggerStateBase;

public class UninitializedState extends LoggerStateBase {
    @Override
    public void debug(String message) {
        this.printUninitializedWarning();
        return;
    }

    @Override
    public void fatal(String message) {
        this.printUninitializedWarning();
        return;
    }

    @Override
    public void error(String message) {
        this.printUninitializedWarning();
        return;
    }

    @Override
    public void warn(String message) {
        this.printUninitializedWarning();
        return;
    }

    private void printUninitializedWarning() {
        System.out.println("Operation was called in state uninitialized.");
    }
}
