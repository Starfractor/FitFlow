import java.util.ArrayList;
import java.util.Date;
public class FoodLog {
    private ArrayList<FoodEntry> entries;
    private Date date;
    public FoodLog(Date date){
        entries = new ArrayList<FoodEntry>();
        this.date = date;
    }

    public void addEntry(FoodEntry food){
        entries.add(food);
    }
    public void removeEntry(String name){
        //Implementation incoming
    }

    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }

    public ArrayList<FoodEntry> getFoodLog(){
        return entries;
    }
}
