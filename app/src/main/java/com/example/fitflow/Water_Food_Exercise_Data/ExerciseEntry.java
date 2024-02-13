import java.util.Date;

public class ExerciseEntry {
    private int caloriesBurned;
    private Date date;
    private String name;

    public ExerciseEntry(String name, int cals, Date date){
        this.name = name;
        this.caloriesBurned = cals;
        this.date = date;
    }
    public void changeDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
    public String getName(){
        return this.name;
    }
}
