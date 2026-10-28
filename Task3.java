public class Task3 {
    public static void main(String[] args) {
        Internet schoolInternet = new SchoolProxyInternet();
        schoolInternet.connectTo("wikipedia.org");
        schoolInternet.connectTo("gaming.com");
    }
}
