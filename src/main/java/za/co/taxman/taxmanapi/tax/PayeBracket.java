package za.co.taxman.taxmanapi.tax;

public class PayeBracket implements Comparable<Integer>{
    public int minIncome;
    public int maxIncome;
    public int under65;
    public int from65To74;
    public int over74;

    @Override
    public int compareTo(Integer monthlyIncome) {
        if (monthlyIncome >= this.minIncome && monthlyIncome <= this.maxIncome){
            return 0;
        } else if (monthlyIncome > this.maxIncome){
            return -1;
        } else {
            return 1;
        }
    }
}
