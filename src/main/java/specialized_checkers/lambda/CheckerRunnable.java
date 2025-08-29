package specialized_checkers.lambda;

import static util.Message.*;

import util.AbstractChecker;

public class CheckerRunnable extends AbstractChecker<Runnable, CheckerRunnable> {

    private static final String INIT_RUNNABLE = "lambda.runnable";
    private static final String DEFAULT_NAME = "Runnable";

    protected CheckerRunnable(Runnable runnable, String name) {
        super(runnable, name);
    }

    /**
     * @param runnable
     * @param name
     * @return CheckerRunnable
     */
    public static CheckerRunnable check(Runnable runnable, String name) {
        return new CheckerRunnable(runnable, DEFAULT_NAME);
    }

    /**
     * @param runnable
     * @return CheckerRunnable
     */
    public static CheckerRunnable check(Runnable runnable) {
        return check(runnable, DEFAULT_NAME);
    }

    /**
     * @return CheckerRunnable
     */
    @Override
    protected CheckerRunnable self() {
        return this;
    }

    /**
     * @return CheckerRunnable
     */
    public CheckerRunnable runWithoutException() {
        return is(r -> {
            try {
                r.run();
                return true;
            } catch (Exception e) {
                return false;
            }
        }, sendMessage(INIT_RUNNABLE, "run_without_exception"));
    }
}
