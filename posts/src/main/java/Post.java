import java.time.LocalDate;

public class Post {
    private String quote;
    private LocalDate date;
    private int claps;
    private int boos;
    private final UserAccount user;

    private Post(String quote,UserAccount user) {
        this.quote = quote;
        this.user = user;
        this.date = LocalDate.now();

    }
    public static Post of(String quote,UserAccount user) {
        if(quote == null) return null;
        if(user == null) return null;

        return new Post(quote,user);
    }

    public void claps(){
        claps++;
    }
    public void boos(){
        boos++;
    }
    public String show(){
        return String.format("%s %s says %s | Claps: %d | Boss: %d\n",date,user.getUserName(),quote,claps,boos);
    }

    public LocalDate getDate() {
        return date;
    }
}
