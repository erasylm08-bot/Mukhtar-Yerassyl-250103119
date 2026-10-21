public class Main {
    public static void main(String[] args) {
        Player player = new Player(new Walking());
        player.move();

        player.setMovementStrategy(new Skateboarding());
        player.move();

        // Switch movement mid-run without if/else.
        player.setMovementStrategy(new Jetpack());
        player.move();
    }
}
