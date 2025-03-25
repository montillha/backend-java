public class Player {
    private String name;
    private  int number;
    private  String position;
    private boolean isField;
    private Team team;

    private Player(String name, int number, String position, boolean isField, Team team) {
        this.name = name;
        this.number = number;
        this.position = position;
        this.isField = isField;
        this.team = team;

    }
    public static Player of(String name, int number, String position, boolean isField, Team team){
        if (name == null ) return null;
        if (number <= 0) return null;
        if (position == null) return null;
        if (team == null) return null;

        return new Player(name,number,position,isField,team);
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
