public class Task5 {
    public static void main(String[] args) {
        Device radio = new Radio();
        PartyRemote partyRemote = new PartyRemote(radio);
        partyRemote.partyMode();
    }
}
