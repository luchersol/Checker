package specialized_checkers.time.temporal;

public interface InterfaceCheckerDate<T extends InterfaceCheckerDate<T, U>, U> {
    
    T isBefore(U date);
    T isBeforeOrEqual(U date);
    T isAfter(U date);
    T isAfterOrEqual(U date);
    T inRange(U date_1, U date_2);

    T isPast();
    T isPastOrPresent();
    T isFuture();
    T isFutureOrPresent();
}
