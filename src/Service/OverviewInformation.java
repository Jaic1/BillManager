package Service;

public class OverviewInformation {
    public static final String moneyKind = "￥";
    public String spendToday;
    public String spendAvg;
    public String spendMonth;
    public String leftMonth;
    public String leftByDay;
    public String dayLeft;
    public int usePercentage;
    public boolean isOverSpend;

    public OverviewInformation(int spendToday,int spendMonth,int budget,int dayUp,int dayLeft){
        if(dayUp == 0)dayUp++;
        if(dayLeft == 0)dayLeft++;
        this.spendToday = moneyKind + String.valueOf(spendToday);
        this.spendAvg = moneyKind + String.valueOf(spendMonth / dayUp) + "." +
                    String.valueOf(spendMonth*10 / dayUp %10);
        this.spendMonth = moneyKind + String.valueOf(spendMonth);
        if(spendMonth <= budget){
            this.leftMonth = moneyKind + String.valueOf(budget - spendMonth);
            int left = budget - spendMonth;
            this.leftByDay = moneyKind + String.valueOf(left / dayLeft) + "." +
                    String.valueOf(left*10 / dayLeft % 10);
            this.isOverSpend = false;
        }else {
            this.leftMonth = moneyKind + " -" + String.valueOf(spendMonth - budget);
            this.leftByDay = "-";
            this.isOverSpend = true;
        }
        this.dayLeft = String.valueOf(dayLeft) + "天";
        this.usePercentage = (budget == 0)?0:spendMonth*100 / budget;
    }
}
