package specialized_checkers.lambda;

import static util.Message.*;

import util.AbstractChecker;

public class CheckerRunnable extends AbstractChecker<Runnable, CheckerRunnable> {

    private static final String INIT_RUNNABLE = "lambda.runnable";

    public CheckerRunnable(Runnable runnable, String name) {
        super(runnable, name);
    }

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
