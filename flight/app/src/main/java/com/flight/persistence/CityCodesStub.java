package com.flight.persistence;
//The array of cities and their codes
//construct with no parameters to create fake database
//can use search for a record using the name of the city

import com.flight.objects.CityCode;

public class CityCodesStub implements IHsqldbCityCodes {

    private final int NUMCITIES = 13;
    private CityCode[] myCityCodes;

    public CityCodesStub() {
        myCityCodes = new CityCode[NUMCITIES];
        this.initializeArray();
    }

    private void initializeArray() {
        //initialize array with city code values from wikipedia

        myCityCodes[0] = new CityCode("Calgary","YYC");
        myCityCodes[1] = new CityCode("Edmonton","YEG");
        myCityCodes[2] = new CityCode("Fredericton","YFC");
        myCityCodes[3] = new CityCode("Gander","YQX");
        myCityCodes[4] = new CityCode("Halifax","YHZ");
        myCityCodes[5] = new CityCode("Moncton","YQM");
        myCityCodes[6] = new CityCode("Montreal","YUL");
        myCityCodes[7] = new CityCode("Ottawa","YOW");
        myCityCodes[8] = new CityCode("Quebec City","YQB");
        myCityCodes[9] = new CityCode("St. John's","YYT");
        myCityCodes[10] = new CityCode("Toronto","YYZ");
        myCityCodes[11] = new CityCode("Vancouver","YVR");
        myCityCodes[12] = new CityCode("Winnipeg","YWG");
    }

    public CityCode search(String index) {
        //linear search through array to find a match on name
        //return null if match not found
        int i = 0;
        CityCode target = null;

        while(i < NUMCITIES && target == null) {
            if(index.equals(myCityCodes[i].getName())) {
                target = myCityCodes[i];
            }
            else if(index.equals(myCityCodes[i].getCode())) {
                target = myCityCodes[i];
            }
            i++;
        }

        return target;
    }

    public CityCode[] getArray() {
        return myCityCodes;
    }

    public void print() {
        for(int i = 0; i < NUMCITIES; i++) {
            myCityCodes[i].print();
        }
    }

}
