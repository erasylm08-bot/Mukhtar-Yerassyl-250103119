public class Task6 {
    public static void main(String[] args) {
        Hero knightWithSword = new Knight(new Sword());
        Hero knightWithWand = new Knight(new MagicWand());
        knightWithSword.attack();
        knightWithWand.attack();
    }
}
