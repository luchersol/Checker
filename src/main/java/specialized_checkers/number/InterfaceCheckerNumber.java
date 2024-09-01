package specialized_checkers.number;


public interface InterfaceCheckerNumber<T extends InterfaceCheckerNumber<T>> {
    
    T isGreaterThan(Byte number);
    T isGreaterThan(Short number);
    T isGreaterThan(Integer number);
    T isGreaterThan(Long number);
    T isGreaterThan(Float number);
    T isGreaterThan(Double number);

    T isGreaterOrEqualTo(Byte number);
    T isGreaterOrEqualTo(Short number);
    T isGreaterOrEqualTo(Integer number);
    T isGreaterOrEqualTo(Long number);
    T isGreaterOrEqualTo(Float number);
    T isGreaterOrEqualTo(Double number);

    T isLessThan(Byte number);
    T isLessThan(Short number);
    T isLessThan(Integer number);
    T isLessThan(Long number);
    T isLessThan(Float number);
    T isLessThan(Double number);

    T isLessOrEqualTo(Byte number);
    T isLessOrEqualTo(Short number);
    T isLessOrEqualTo(Integer number);
    T isLessOrEqualTo(Long number);
    T isLessOrEqualTo(Float number);
    T isLessOrEqualTo(Double number);
    
}
