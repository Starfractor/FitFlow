import java.util.ArrayList;
import java.util.Date;

public class WaterLog {
    private ArrayList<WaterEntry> entries;
    private Date date;

    public WaterLog(Date date){
        entries = new ArrayList<WaterEntry>();
        this.date = date;
    }

    public void addEntry(WaterEntry water){
        this.entries.add(water);
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

    public ArrayList<WaterEntry> getWaterLog(){
        return entries;
    }
}