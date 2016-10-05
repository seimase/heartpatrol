package com.unifam.heartpatrol.model;

/**
 * Created by Unifam on 9/19/2016.
 */
public class model_ecg_result {

    private String Atr1="";
    private String Atr2="";
    private String Atr3="";
    private String Atr4="";
    private String Atr5="";
    private boolean AtrCheck1=false;

    public boolean getAtrCheck1() {
        return(AtrCheck1);
    }

    public void setAtrCheck1(boolean nama) {
        this.AtrCheck1=nama;
    }

    public String getAtr4() {
        return(Atr4);
    }

    public void setAtr4(String nama) {
        this.Atr4=nama;
    }

    public String getAtr3() {
        return(Atr3);
    }

    public void setAtr3(String nama) {
        this.Atr3=nama;
    }

    public String getAtr2() {
        return(Atr2);
    }

    public void setAtr2(String nama) {
        this.Atr2=nama;
    }

    public String getAtr1() {
        return(Atr1);
    }

    public void setAtr1(String nama) {
        this.Atr1=nama;
    }
}
