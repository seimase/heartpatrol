package com.unifam.heartpatrol.model;

import java.util.List;

/**
 * Created by setia.n on 10/20/2016.
 */

public class Promo {
    public List<Datum> data ;
    public boolean error ;
    public String message ;

    public class Datum
    {
        public String promo_code ;
        public String promo_description ;
        public String promo_type ;
        public double percent ;
        public double value ;
    }
}
