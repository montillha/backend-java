package soccer_teams;

public class Team {
    private String name;
    private String baseLocation;
    private String coachName;
    private Player captain;
    private final Player[] players=new Player[4];
    private int position=0;


    public Team(String name, String baseLocation, String coachName) {
        this.name = name;
        this.baseLocation = baseLocation;
        this.coachName = coachName;
    }

    private int searchPlayer(Player player){
        if(position<=0) return -1;
        for (int i = 0; i < position; i++) {
            if(players[i]==player)return i;
        }
        return -1;
    }

    public void addPlayer(Player player){
        if(searchPlayer(player)==-1 && position<4) players[position++]=player;

    }

    public void removePlayer(Player player){
        int remove_index=searchPlayer(player);
        if(remove_index!=-1) {
            for (int i = remove_index; i < position-1; i++) {
                players[i]= players[i+1];

            }
            position--;
        }
    }

    public void substitute(Player substitute,Player starter){
        int index_substitute=searchPlayer(substitute);
        int index_starter=searchPlayer(starter);
        if(index_substitute!=-1 && index_starter!=-1) {
            players[index_substitute].setField(false);
            players[index_starter].setField(true);

        }

    }
    public Player[] getFieldedplayers(){
        Player[] fieldedPlayers= new Player[4];
        int index=0;
        for (int i=0;i<position;i++) {
            if(players[i].isField()) fieldedPlayers[index++]=players[i];


        }
        return  fieldedPlayers;

    }
    public Player[] getOutfieldedplayers(){
        Player[] outfieldedPlayers= new Player[4];
        int index=0;
        for (int i=0;i<position;i++) {
            if (!players[i].isField()) outfieldedPlayers[index++] = players[i];


        }

        return  outfieldedPlayers;

    }

    public String getName() {
        return name;
    }

    public Player getCaptain() {
        return captain;
    }
    public void setCaptain(Player captain) {
        if(searchPlayer((captain))!=-1) this.captain = captain;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPosition() {
        return position;
    }


}
