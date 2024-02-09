public class FoodEntry {
    private String name;
    private int calories;
    public FoodEntry(String name, int calories){
        this.name = name;
        this.calories = calories;
    }

    public String getFoodName(){
        return this.name;
    }
    public int getCalories(){
        return this.calories;
    }

}
