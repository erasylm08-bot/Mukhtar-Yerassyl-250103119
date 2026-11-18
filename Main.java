
public class Main {

    public static void main(String[] args) {

        FlightBookingFacade bookingFacade = new FlightBookingFacade();
        System.out.println("=== TEST 1: Successful Booking ===");

        String ticket1 = bookingFacade.bookFlight(
                "Amina Suleimenova",
                "KC101",
                "14A",
                320.00,
                2,
                23.0,
                "EUR",
                "4532015112830366"
        );

        System.out.println("Result 1: " + ticket1);
        System.out.println();


        System.out.println("=== TEST 2: Blocked Seat ===");

        String ticket2 = bookingFacade.bookFlight(
                "Daniyar Kassymov",
                "KC101",
                "13C",
                320.00,
                1,
                15.0,
                "USD",
                "4532015112830366"
        );

        System.out.println("Result 2: " + ticket2);
        System.out.println();


        System.out.println("=== TEST 3: Payment Failure and Rollback ===");

        String ticket3 = bookingFacade.bookFlight(
                "Timur Bekov",
                "KC101",
                "07D",
                320.00,
                1,
                18.0,
                "KZT",
                "9999"
        );

        System.out.println("Result 3: " + ticket3);
        System.out.println();


        System.out.println("=== TEST 4: SILVER Loyalty Tier ===");

        String ticket4 = bookingFacade.bookFlight(
                "Erasyl Mukhtar",
                "KC101",
                "08C",
                320.00,
                3,
                30.0,
                "EUR",
                "4532015112830366",
                "SILVER"
        );

        System.out.println("Result 4: " + ticket4);
    }
}
