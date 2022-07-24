package com.example.byblos;

public class Request {
    public String Service_type; //truck, car, or Moving

    //MOVING VARIABLES
    public String Movingname;
    public String Movingbirthday;
    public String Movingemail;
    public String Movingstartlocation;
    public String Movingendlocation;
    public String Movingmoversneeded;
    public String Movingboxesneeded;

    //CAR VARIABLES
    public String Carfullname;
    public String Carlicensetype;
    public String Carprefferred;
    public String Carpickuptime;
    public String Carpickupdate;
    public String CarreturnDate;
    public String CarreturnTime;

    //TRUCK VARIABLES:
    public String fullname;
    public String email;
    public String kilometres;
    public String area;
    public String licenseType;
    public String pickupdate;
    public String pickuptime;
    public String returndate;
    public String returntime;
    public String birthday;


        //TRUCK CONSTRUCTER
        public Request(String servicetype, String fullname, String birthday, String email, String kilometres, String area, String licenseType, String pickupdate, String pickuptime, String returnDate, String returnTime) {
            this.Service_type = servicetype;
            this.fullname = fullname;
            this.birthday = birthday;
            this.email = email;
            this.kilometres = kilometres;
            this.area = area;
            this.licenseType = licenseType;
            this.pickupdate = pickupdate;
            this.pickuptime = pickuptime;
            this.returndate = returnDate;
            this.returntime = returnTime;
        }


    //CAR CONSTRUCTER
    public Request(String service_type, String fullname, String license, String preferred, String pickupdate, String pickuptime, String returndate, String returntime) {

            this.Service_type = service_type;
            this.Carfullname = fullname;
            this.Carlicensetype = license;
            this.Carprefferred = preferred;
            this.Carpickupdate = pickupdate;
            this.Carpickuptime = pickuptime;
            this.CarreturnDate = returndate;
            this.CarreturnTime = returntime;

    }

    //MOVING CONSTRUCTER
    public Request(String service_type,String fullname, String birthday, String email, String startlocation, String endlocation, String moversneeded, String boxes, boolean is_moving) {
            this.Service_type = service_type;
            this.Movingname = fullname;
            this.Movingbirthday = birthday;
            this.Movingemail = email;
            this.Movingstartlocation = startlocation;
            this.Movingendlocation = endlocation;
            this.Movingmoversneeded = moversneeded;
            this.Movingboxesneeded = boxes;
    }



}
