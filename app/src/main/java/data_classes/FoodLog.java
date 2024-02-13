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
        this.entries.add(food);
    }
    public boolean removeEntry(String name){
        for(int i = 0; i< entries.size(); i++){
            if(this.entries.get(i).getName().equals(name)){
                this.entries.remove(i);
                return true;
            }
        }
        return false;
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
