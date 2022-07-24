package com.example.byblos;

public class AdminSettings {
    public static class CarRental {
        public static boolean toggle = true;
        public static String price = "0";
        public static boolean nameHidden = false;
        public static boolean licenseHidden = false;
        public static boolean pickupHidden = false;
        public static boolean typeHidden = false;
        public static boolean returnHidden = false;

    }
    public static class TruckRental {
        public static boolean toggle = true;
        public static String price = "0";

        public static boolean nameHidden = false;
        public static boolean licenseHidden = false;
        public static boolean pickupHidden = false;
        public static boolean emailHidden = false;
        public static boolean areaHidden = false;
        public static boolean returnHidden = false;


    }
    public static class MovingAssistance {
        public static boolean toggle = true;
        public static String price = "0";

        public static boolean nameAndBirthHidden = false;
        public static boolean emailHidden = false;
        public static boolean startLocationHidden = false;
        public static boolean endLocationHidden = false;
        public static boolean moversNeededHidden = false;
        public static boolean numberOfBoxesHidden = false;

    }

    public static void update() {
    }
}