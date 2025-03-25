public class Team {
    public static final int MAX_PLAYER = 4;
    private String name;
    private String baseLocation;
    private String coachName;
    private Player captain;
    private final Player[] players = new Player[MAX_PLAYER];
    private int position = 0;


    private Team(String name, String baseLocation, String coachName) {
        this.name = name;
        this.baseLocation = baseLocation;
        this.coachName = coachName;
    }
    public static Team of(String name, String baseLocation, String coachName) {
        if (name == null || name.isEmpty()) return null;
        if (baseLocation == null ) return null;
        if (coachName == null ) return null;

        return new Team(name,baseLocation,coachName);
    }


    private int searchPlayer(Player player) {
        if (position <= 0) return -1;
        for (int i = 0; i < position; i++) {
            if (players[i] == player) return i;
        }
        return -1;
    }

    public void addPlayer(Player player) {
        if (searchPlayer(player) == -1 && position < players.length)
            players[position++] = player;
    }

    public void removePlayer(Player player) {
        int removeIndex = searchPlayer(player);
        if (removeIndex != -1) {
            for (int i = removeIndex; i < position - 1; i++) {
                players[i] = players[i + 1];

            }
            players[position - 1] = null;
            position--;
        }
    }

    public void substitute(Player substitute, Player starter) {
        int index_substitute = searchPlayer(substitute);
        int index_starter = searchPlayer(starter);
        if (index_substitute != -1 && index_starter != -1) {
            players[index_substitute].setField(false);
            players[index_starter].setField(true);
        }
    }

    public Player[] getFieldedplayers() {
        return getPlayersByStatus(true);
    }

    public Player[] getOutfieldedplayers() {
        return getPlayersByStatus(false);
    }

    public Player[] getPlayersByStatus(boolean fielded) {
        Player[] result = new Player[MAX_PLAYER];
        int index = 0;
        for (int i = 0; i < position; i++) {
            if (players[i].isField() == fielded)
                result[index++] = players[i];
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        if (searchPlayer((captain)) != -1)
            this.captain = captain;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return position;
    }

}
