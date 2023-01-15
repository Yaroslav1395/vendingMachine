package Payment;

public enum Paymet {
    PAY_BY_CASH("i", new PayByCash()),
    PAY_BY_PAY_PAL("p", new PayByPayPal()),
    PAY_BY_CREDIT_CARD("o", new PayByCreditCard());

    private final String strategyId;
    private final PayStrategy strategy;

    Paymet(String strategyId, PayStrategy strategy) {
        this.strategyId = strategyId;
        this.strategy = strategy;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public PayStrategy getStrategy() {
        return strategy;
    }

    public static PayStrategy getPayStrategy(String chosenStrategy){
        for (Paymet paymet: Paymet.values()) {
            if(paymet.getStrategyId().equals(chosenStrategy)){
                return paymet.getStrategy();
            }
        }
        throw new RuntimeException("Такой стратегии нет. Попробуйте снова");
    }
}
