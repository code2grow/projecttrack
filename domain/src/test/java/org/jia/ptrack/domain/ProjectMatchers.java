package org.jia.ptrack.domain;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ProjectMatchers {

 
  public static Matcher<Date> withinInclusivePeriod(final Date startTime,
      final Date endTime) {
    return new TypeSafeMatcher<Date>() {

      public boolean matchesSafely(Date date) {
        return !date.before(startTime) && !date.after(endTime);
      }

      public void describeTo(Description description) {
        description.appendText(String.format("expected to be between <%s> and <%s>",
            startTime, endTime));
      }

    };
  }
}
