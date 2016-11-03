package com.unifam.heartpatrol.model;

import java.util.List;

/**
 * Created by setia.n on 10/12/2016.
 */

public class Ecg_Result_Model {
    public boolean error;
    public String message;
    public int credits;
    public List<Data> data ;

    public class Data
    {
        public String ecg_id ;
        public String ecg_date ;
        public String ecg_status_document ;
        public String ecg_status_document_description ;
        public String status_id ;
        public String status_description ;
        public String file_link ;
        public double file_size ;
        public boolean flag ;
    }


}
