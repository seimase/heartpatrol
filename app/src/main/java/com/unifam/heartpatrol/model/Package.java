package com.unifam.heartpatrol.model;

import java.util.List;

/**
 * Created by setia.n on 10/20/2016.
 */

public class Package {
    public List<Datum> data ;
    public boolean error ;
    public String message ;

    public class Datum
    {
        public int package_id ;
        public String package_name ;
        public int credits ;
        public int price ;
        public int qty ;
        public String currency ;
    }
}
