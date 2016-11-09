package com.unifam.heartpatrol.model;

import java.util.List;

/**
 * Created by setia.n on 10/12/2016.
 */

public class Register {
    public boolean error;
    public String message;
    public List<Datum> data ;
    public String name;
    public String first_name;
    public String last_name;

    public class Datum
    {
        public String user_name ;
        public String first_name ;
        public String last_name ;
        public String picture_link ;
    }

}
