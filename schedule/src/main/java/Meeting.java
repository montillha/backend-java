import java.time.Duration;
import java.time.LocalTime;

public class Meeting {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;

    private Meeting(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public static Meeting of(String description, LocalTime startTime, LocalTime endTime){
        if(description == null) return null;
        if(startTime == null) return null;
        if(endTime == null) return null;
        if(!startTime.isBefore(endTime)) return null;

        return new Meeting(description, startTime, endTime);
    }
    public long durationInMinutes(){
        return Duration.between(startTime,endTime).toMinutes();
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}
