
public class FlightBookingFacade {

    private final SeatInventorySystem seatSystem;
    private final BaggagePricingEngine baggageEngine;
    private final CurrencyConverter currencyConverter;
    private final PaymentProcessor paymentProcessor;
    private final TicketIssuer ticketIssuer;

    public FlightBookingFacade() {
        this.seatSystem = new SeatInventorySystem();
        this.baggageEngine = new BaggagePricingEngine();
        this.currencyConverter = new CurrencyConverter();
        this.paymentProcessor = new PaymentProcessor();
        this.ticketIssuer = new TicketIssuer();
    }

    public String bookFlight(String passengerName,
                             String flightCode,
                             String seatNumber,
                             double baseFareUSD,
                             int bagCount,
                             double bagWeightKg,
                             String currency,
                             String cardNumber) {

        return bookFlight(passengerName, flightCode, seatNumber,
                baseFareUSD, bagCount, bagWeightKg,
                currency, cardNumber, "STANDARD");
    }

    public String bookFlight(String passengerName,
                             String flightCode,
                             String seatNumber,
                             double baseFareUSD,
                             int bagCount,
                             double bagWeightKg,
                             String currency,
                             String cardNumber,
                             String loyaltyTier) {

        String lockToken = null;

        try {
            seatSystem.connectToReservationServer();

            lockToken = seatSystem.lockSeat(
                    flightCode, seatNumber, passengerName
            );

            double baggageFee = 0.0;

            boolean silver = "SILVER".equalsIgnoreCase(loyaltyTier);
            boolean gold = "GOLD".equalsIgnoreCase(loyaltyTier);

            if (!silver && !gold) {
                baggageFee = baggageEngine.calculateBaggageFeeUSD(
                        bagCount, bagWeightKg
                );
            }

            double totalUSD = baseFareUSD + baggageFee;

            double convertedTotal = currencyConverter.convertFromUSD(
                    totalUSD, currency
            );

            paymentProcessor.initializeGateway();

            if (silver) {
                paymentProcessor.applyPromoCode("SILVER-FREE-BAG");
            }

            String receiptId = paymentProcessor.chargeCard(
                    cardNumber, convertedTotal, currency
            );

            return ticketIssuer.generateTicket(
                    passengerName, flightCode, lockToken, receiptId
            );

        } catch (RuntimeException ex) {

            if (lockToken != null) {
                seatSystem.releaseSeatLock(lockToken);
            }

            System.out.println("Booking failed: " + ex.getMessage());

            return "BOOKING_FAILED: " + ex.getMessage();

        } finally {
            seatSystem.disconnect();
        }
    }
}
