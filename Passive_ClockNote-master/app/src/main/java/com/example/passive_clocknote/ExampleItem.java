package com.example.passive_clocknote;

public class ExampleItem {
    private String mLine1;
    private String mLine2,mLine3,mLine4;

    public ExampleItem(String line1, String line2, String line3,String line4) {
        mLine1 = line1;
        mLine2 = line2;
        mLine3=line3;
        mLine4=line4;
    }

    public String getLine1() {
        return mLine1;
    }

    public String getLine2() {
        return mLine2;
    }
    public String getLine3() {
        return mLine3;
    }
    public String getLine4() {
        return mLine4;
    }
}
