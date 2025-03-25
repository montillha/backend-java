public class Main {
    public static void main(String[] args) {

        UserAccount userMaria = UserAccount.of("maria@gmail.com", "maria");
        UserAccount userClara = UserAccount.of("clara@gmail.com", "clara");
        UserAccount userManu = UserAccount.of("manu@gmail.com", "manu");

        // seguir usuários
        userMaria.acceptFollower(userClara);
        userMaria.acceptFollower(userManu);
        System.out.println(userMaria.showMyFriends());

        // Publicando post
        userMaria.publish("Bom dia pessoal!");
        System.out.println(userMaria.showMyPosts());

        // Bloqueando user
        userMaria.blockFollower(userManu);
        System.out.println(userMaria.showMyFriends());

        // Verificando timeline
        System.out.println(userManu.showMyTimeline());

        // Publicando mais posts
        userMaria.publish("Que dia lindo!");
        userClara.publish("Oi pessoal!");
        System.out.println(userMaria.showMyPosts());
        System.out.println(userClara.showMyPosts());
        System.out.println(userManu.showMyTimeline());

        // Interações nos posts
        userClara.clapPost(0);
        userClara.booPost(0);
        System.out.println(userClara.showMyTimeline());

        // Deletando post
        userMaria.delete(0);
        System.out.println(userMaria.showMyPosts());

        //verificando substituição de post:
        // Maria publica 12 posts (a timeline só suporta 10)
        for (int i = 1; i <= 9; i++) {
            userMaria.publish("Post número " + i);
        }

        // Exibindo a timeline de Clara
        System.out.println("Timeline da Clara após  11 posts de Maria:");
        System.out.println(userClara.showMyTimeline());
    }
}
