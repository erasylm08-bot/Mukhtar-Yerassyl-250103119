public class Main {
    public static void main(String[] args) {
        System.out.println("=== TASK 1: The Bouncer ===");
        Image guest = new ProxyImage("salary_sheet.png", "GUEST");
        Image admin = new ProxyImage("salary_sheet.png", "ADMIN");
        guest.show();
        admin.show();

        System.out.println("\n=== TASK 2: The View Counter ===");
        Video video = new ProxyVideo("Cat_Memes.mp4");
        video.play();
        video.play();
        video.play();

        System.out.println("\n=== TASK 3: School Wi-Fi Firewall ===");
        Internet schoolInternet = new SchoolProxyInternet();
        schoolInternet.connectTo("wikipedia.org");
        schoolInternet.connectTo("gaming.com");

        System.out.println("\n=== TASK 4: Smart Light ===");
        Device light = new SmartLight();
        AdvancedRemote remote = new AdvancedRemote(light);
        remote.powerOn();
        remote.mute();

        System.out.println("\n=== TASK 5: Party Remote ===");
        Device radio = new Radio();
        PartyRemote partyRemote = new PartyRemote(radio);
        partyRemote.partyMode();

        System.out.println("\n=== TASK 6: RPG Weapon System ===");
        Hero knightWithSword = new Knight(new Sword());
        Hero knightWithWand = new Knight(new MagicWand());
        knightWithSword.attack();
        knightWithWand.attack();
    }
}
