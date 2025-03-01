package soccer_teams.model;

public class Player {
    private String name;
    private  int number;
    private  String position;
    private boolean isField;
    private Team team;

    public Player(String name, int number, String position, boolean isField, Team team) {
        this.name = name;
        this.number = number;
        this.position = position;
        this.isField = isField;
        this.team = team;
    }

    public boolean isField() {
        return isField;
    }

    public void setField(boolean field) {
        isField = field;
    }

    public String getStateAsString(){
        return "Name:" + name +", Number:" + number + ", Position:" + position+", Field:" + isField+", Team:"+team.getName();
    }

}
