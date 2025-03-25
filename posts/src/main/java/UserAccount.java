import java.time.LocalDate;

public class UserAccount {
    private final int MAX_TIMELINE = 10;
    private final int MAX_PUBLISHED_POSTS = 2000;
    private final int MAX_FOLLOWERS = 3000;

    private String email;
    private String userName;
    private Post[] publishedPosts = new Post[MAX_PUBLISHED_POSTS];
    private Post[] timeline = new Post[MAX_TIMELINE];
    private UserAccount[] followers = new UserAccount[MAX_FOLLOWERS];
    private int sizepublishedPosts = 0;
    private int sizeTimeline = 0;
    private int sizeFollowers = 0;


    private UserAccount(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public static UserAccount of(String email, String userName) {
        if (email == null) return null;
        if (userName == null) return null;

        return new UserAccount(email, userName);
    }

    public String getUserName() {
        return userName;
    }

    public void publish(String quote) {
        Post newPost = Post.of(quote, this);
        publishedPosts[sizepublishedPosts++] = newPost;
        for (int i = 0; i < sizeFollowers; i++) {
            followers[i].updateTimeline(newPost);
        }

    }

    public void updateTimeline(Post newPost) {
        if (sizeTimeline < 10) {
            timeline[sizeTimeline++] = newPost;
        } else {
            timeline[findOldPost()] = newPost;

        }
    }

    private int findOldPost() {
        if (sizeTimeline == 0) return 0;
        int OldPostIndex = 0;
        LocalDate OldPostData = timeline[0].getDate();
        for (int i = 0; i < sizeTimeline; i++) {
            if (timeline[i].getDate().isBefore(OldPostData)) {
                OldPostIndex = i;
                OldPostData = timeline[i].getDate();
            }

        }
        return OldPostIndex;

    }

    public boolean delete(int postIdx) {
        if (postIdx >= sizepublishedPosts && sizepublishedPosts == 0) return false;

        for (int i = postIdx; i < sizepublishedPosts - 1; i++) {
            publishedPosts[i] = publishedPosts[i + 1];
        }
        publishedPosts[sizepublishedPosts - 1] = null;
        sizepublishedPosts--;
        return true;
    }

    public void clapPost(int postIdx) {
        if (postIdx < sizeTimeline && sizeTimeline != 0) {
            timeline[postIdx].claps();
        }
    }

    public void booPost(int postIdx) {
        if (postIdx < sizeTimeline && sizeTimeline != 0) {
            timeline[postIdx].boos();
        }
    }

    public void acceptFollower(UserAccount newFollower) {
        followers[sizeFollowers++] = newFollower;
    }

    public void blockFollower(UserAccount follower) {
        if (searchFollower(follower) == -1) return;
        for (int i = searchFollower(follower); i < sizeFollowers - 1; i++) {
            followers[i] = followers[i + 1];
        }
        followers[sizeFollowers - 1] = null;
        sizeFollowers--;

    }

    private int searchFollower(UserAccount userAccount) {
        if (sizeFollowers == 0) return -1;
        for (int i = 0; i < sizeFollowers; i++) {
            if (followers[i].equals(userAccount)) return i;

        }
        return -1;

    }

    public String showMyPosts() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("My Posts:\n");
        for (int i = 0; i < sizepublishedPosts; i++) {
            stringBuilder.append(publishedPosts[i].show());
        }
        return stringBuilder.toString();

    }

    public String showMyTimeline() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Timeline:\n");
        for (int i = 0; i < sizeTimeline; i++) {
            stringBuilder.append(timeline[i].show());

        }
        return stringBuilder.toString();

    }

    public String showMyFriends() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Friends:\n");
        for (int i = 0; i < sizeFollowers; i++) {
            stringBuilder.append(followers[i].getUserName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();

    }


}


