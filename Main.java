import java.util.List;

public class Main {

    public static void main(String[] args) {

        LegacyBulb rawBulb = new LegacyBulb();
        LegacyThermostat rawThermostat = new LegacyThermostat();

        BulbAdapter bulbAdapter = new BulbAdapter(rawBulb);
        ThermostatAdapter thermostatAdapter =
                new ThermostatAdapter(rawThermostat);

        List<SmartDevice> deviceList =
                List.of(bulbAdapter, thermostatAdapter);

        ModernHub hub = new ModernHub(deviceList);

        System.out.println("=== ACTIVATE ALL ===");

        hub.activateAll();

        System.out.println("Bulb ON: " + bulbAdapter.isOn());
        System.out.println("Bulb Power: "
                + bulbAdapter.getPowerPercent() + "%");

        System.out.println("Thermostat ON: "
                + thermostatAdapter.isOn());

        System.out.println("Thermostat Power: "
                + thermostatAdapter.getPowerPercent() + "%");

        System.out.println("Average Power: "
                + hub.calculateAveragePowerUsage() + "%");


        // ModernHub badHub = new ModernHub(List.of(rawBulb)); // COMPILE ERROR

        /*
         * Java rejects this because LegacyBulb does not implement
         * the SmartDevice interface, so it cannot be used where
         * SmartDevice is required.
         *
         * The Object Adapter solves this by wrapping LegacyBulb
         * inside BulbAdapter without modifying the legacy class.
         */
        System.out.println("=== HARDWARE FAULT TEST ===");

        rawBulb.breakFilament();

        System.out.println("Broken Bulb ON: "
                + bulbAdapter.isOn());

        System.out.println("Broken Bulb Power: "
                + bulbAdapter.getPowerPercent() + "%");

        rawThermostat.rotateDial("STUCK");

        System.out.println("Corrupted Thermostat ON: "
                + thermostatAdapter.isOn());

        System.out.println("Corrupted Thermostat Power: "
                + thermostatAdapter.getPowerPercent());

        System.out.println("=== EMERGENCY SHUTDOWN ===");

        hub.emergencyShutdown();

        System.out.println("Bulb ON: " + bulbAdapter.isOn());
        System.out.println("Thermostat ON: "
                + thermostatAdapter.isOn());

        System.out.println("Average Power: "
                + hub.calculateAveragePowerUsage() + "%");
    }
}