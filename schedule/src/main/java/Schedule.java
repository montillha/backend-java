import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfMeetings=0;
    private final Meeting[] meetings;
    private int position = 0;

    private Schedule(LocalDate day, LocalTime startTime, LocalTime endTime, int numberOfMeetings) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numberOfMeetings = numberOfMeetings;
        this.meetings = new Meeting[numberOfMeetings];
    }
    public static Schedule of(LocalDate day, LocalTime startTime, LocalTime endTime, int numberOfMeetings) {
        if(day==null)return null;
        if(startTime==null) return null;
        if(endTime==null) return null;
        if(!startTime.isBefore(endTime)) return null;

        return new Schedule(day, startTime, endTime, numberOfMeetings);
    }


    public void addMeeting(Meeting meeting) {
        if (isFull() || isOutsideWorkingTime(meeting) || isOverlapingMeeting(meeting)) return;
        meetings[position++] = meeting;
    }

    private boolean isOutsideWorkingTime(Meeting meeting) {
        return meeting.getEndTime().isBefore(endTime) && meeting.getStartTime().isAfter(startTime);
    }

    private boolean isFull() {
        return position >= numberOfMeetings;
    }

    private boolean isOverlapingMeeting(Meeting meeting) {
        for (Meeting mtgs : meetings) {
            if (mtgs == null) break;
            if (meeting.getStartTime().isBefore(mtgs.getEndTime()) && meeting.getEndTime().isAfter(mtgs.getStartTime())) {
                return true;
            }

        }
        return false;
    }

    public void removeMetting(Meeting meeting) {
        for (int i = 0; i < position; i++) {
            if (meetings[i] == meeting) {
                for (int j = i; j < meetings.length - 1; j++) {
                    meetings[j] = meetings[j + 1];

                }
                meetings[position - 1] = null;
                position--;
                break;
            }
        }
    }

    public double percentageSpentInMettings() {
        double totalMinutos = 0;
        for (Meeting meeting : meetings) {
            if (meeting == null) break;
            totalMinutos += meeting.durationInMinutes();
        }
        return totalMinutos * 100 / Duration.between(startTime, endTime).toMinutes();

    }

    public String scheduleAsString() {
        var timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        var dayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartTime = startTime.format(timeFormatter);
        String formattedEndTime = endTime.format(timeFormatter);
        String formattedDAy = day.format(dayFormatter);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Day: %s, Start Time: %s, End Time: %s\n Meetings:\n",
                formattedDAy,
                formattedStartTime,
                formattedEndTime)
        );

        for (Meeting meeting : meetings) {
            if (meeting == null) break;
            stringBuilder.append(String.format("Descripition: %s\n %s to %s\n",
                    meeting.getDescription(),
                    meeting.getStartTime().format(timeFormatter),
                    meeting.getEndTime().format(timeFormatter))
            );
        }
        return String.valueOf(stringBuilder);
    }
}

