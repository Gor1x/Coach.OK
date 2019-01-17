package UserStatsDatabase;

public class Users {

    private int times;
    private int height;
    private int weight;


    public Users(int times, int height, int weight) {
        this.times = times;
        this.height = height;
        this.weight = weight;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTimes() {
        return times;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return height + " | " + weight + " | " + times;
    }

}
