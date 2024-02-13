import java.util.Date;

public class WaterEntry {
    private int ml;
    private Date date;
    private String name;
    public WaterEntry(String name, int ml, Date date){
        this.name = name;
        this.ml = ml;
        this.date = date;
    }
    public String getName(){
        return this.name;
    }
    public int getAmount(){
        return this.ml;
    }
    public void changeAmount(int ml){
        this.ml = ml;
    }
    public void changeDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
}
