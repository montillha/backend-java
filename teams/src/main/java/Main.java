public class Main {
    public static void main(String[] args) {
        Team team = Team.of("Team_A", "baseLocation1", "Pedro");
        Player player1 = Player.of("Carlos", 7, "atacante", true, team);
        Player player2 = Player.of("João", 5, "goleiro", true, team);
        Player player3 = Player.of("José", 10, "zagueiro", false, team);
        Player player4 = Player.of("Dudu", 33, "atacante", true, team);
        Player player5 = Player.of("Junior", 1, "lateral", false, team);

        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);

        System.out.println("Players:");
        for (int i = 0; i < team.getNumberOfPlayers(); i++) {
            System.out.println(team.getPlayers()[i].getStateAsString());
        }

        team.setCaptain(player5);
        System.out.println("Captain - " + (team.getCaptain() != null ? team.getCaptain().getStateAsString() : "undefined"));

        team.substitute(player2, player5);

        team.removePlayer(player3);

        System.out.println("Fielded players:");
        for (int i = 0; i < team.getFieldedplayers().length && team.getFieldedplayers()[i] != null; i++) {
            System.out.println(team.getFieldedplayers()[i].getStateAsString());

        }
        System.out.println("Out fielded player:");
        for (int i = 0; i < team.getOutfieldedplayers().length && team.getOutfieldedplayers()[i] != null; i++) {
            System.out.println(team.getOutfieldedplayers()[i].getStateAsString());

        }


    }
}
