import java.util.Date;

public class FoodEntry {
    private String name;
    private int calories;
    private int count;
    private Date date;
    public FoodEntry(String name, int calories, int count, Date date){
        this.name = name;
        this.calories = calories;
        this.count = count;
        this.date = date;
    }

    public void updateTotalCalories(int calories){
        this.calories = calories;
    }

    public String getName(){
        return this.name;
    }
    public int getTotalCalories(){
        return this.calories;
    }

    public void updateCount(int count){
        this.count = count;
    }

    public void changeDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }

}
