import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        //mettings
        LocalTime startTime = LocalTime.parse("11:30:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime endTime = LocalTime.parse("12:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        Meeting meeting1 = Meeting.of("Reunião com o departamento do RH", startTime, endTime);
        Meeting meeting2 = Meeting.of("Reunião com o departamento de TI", startTime.plusMinutes(40), endTime.plusMinutes(40));
        Meeting meeting3 = Meeting.of("Reunião com os professores", startTime.plusMinutes(80), endTime.plusMinutes(80));
        Meeting meeting4 = Meeting.of("Reunião sobre o novo sistema", startTime.plusMinutes(120), endTime.plusMinutes(120));
        Meeting meeting5 = Meeting.of("Reunião com a psicóloga", startTime.plusMinutes(160), endTime.plusMinutes(160));
        Meeting meeting6 = Meeting.of("Reunião semestral",startTime.plusMinutes(10), startTime.plusMinutes(20));

        System.out.println(meeting1.durationInMinutes());


        //schedule
        LocalDate date =LocalDate.parse("2025/03/24",DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalTime startTimeShedule=LocalTime.parse("12:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime endTimeShedule = startTimeShedule.plusHours(8);
        Schedule schedule=Schedule.of(date,startTimeShedule,endTimeShedule,10);

        schedule.addMeeting(meeting1);
        schedule.addMeeting(meeting2);
        schedule.addMeeting(meeting3);
        schedule.addMeeting(meeting4);
        schedule.addMeeting(meeting5);
        schedule.addMeeting(meeting6);

        System.out.println(schedule.scheduleAsString());

        schedule.removeMetting(meeting4);
        System.out.println(schedule.scheduleAsString());


        System.out.printf("Percentage spent in mettings: %.2f%%",schedule.percentageSpentInMettings());




    }
}
