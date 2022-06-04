package com.flight;
import com.flight.business.AccessCityCodeIT;
import com.flight.business.AccessFlightIT;
import com.flight.business.AccessReservationIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessCityCodeIT.class,
        AccessFlightIT.class,
        AccessReservationIT.class
})
public class IntegrationTests {

}
