package com.unifam.heartpatrol.model;

import java.util.List;

/**
 * Created by setia.n on 10/12/2016.
 */

public class Profile {
    public boolean error;
    public String message;
    public List<Data> data ;

    public class Data
    {
        public String first_name ;
        public String last_name ;
        public String birth_date ;
        public String email ;
        public String gender ;
        public String weight ;
        public String height ;
    }


}
