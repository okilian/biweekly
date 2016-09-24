// Copyright (C) 2006 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package biweekly.util.com.google.ical.iter;

import java.util.Arrays;

import biweekly.util.ByDay;
import biweekly.util.DayOfWeek;
import biweekly.util.com.google.ical.values.DateValueImpl;
import junit.framework.TestCase;

/**
 * @author mikesamuel+svn@gmail.com (Mike Samuel)
 */
public class UtilTest extends TestCase {
  public void testDayNumToDateInMonth() {
    //        March 2006
    // Su Mo Tu We Th Fr Sa
    //           1  2  3  4
    //  5  6  7  8  9 10 11
    // 12 13 14 15 16 17 18
    // 19 20 21 22 23 24 25
    // 26 27 28 29 30 31
    DayOfWeek dow0 = DayOfWeek.WEDNESDAY;
    int nDays = 31;
    int d0 = 0;

    DayOfWeek dow = DayOfWeek.WEDNESDAY;
    assertEquals(1, Util.dayNumToDate(dow0, nDays, 1, dow, d0, nDays));
    assertEquals(8, Util.dayNumToDate(dow0, nDays, 2, dow, d0, nDays));
    assertEquals(29, Util.dayNumToDate(dow0, nDays, -1, dow, d0, nDays));
    assertEquals(22, Util.dayNumToDate(dow0, nDays, -2, dow, d0, nDays));

    dow = DayOfWeek.FRIDAY;
    assertEquals(3, Util.dayNumToDate(dow0, nDays, 1, dow, d0, nDays));
    assertEquals(10, Util.dayNumToDate(dow0, nDays, 2, dow, d0, nDays));
    assertEquals(31, Util.dayNumToDate(dow0, nDays, -1, dow, d0, nDays));
    assertEquals(24, Util.dayNumToDate(dow0, nDays, -2, dow, d0, nDays));

    dow = DayOfWeek.TUESDAY;
    assertEquals(7, Util.dayNumToDate(dow0, nDays, 1, dow, d0, nDays));
    assertEquals(14, Util.dayNumToDate(dow0, nDays, 2, dow, d0, nDays));
    assertEquals(28, Util.dayNumToDate(dow0, nDays, 4, dow, d0, nDays));
    assertEquals(0, Util.dayNumToDate(dow0, nDays, 5, dow, d0, nDays));
    assertEquals(28, Util.dayNumToDate(dow0, nDays, -1, dow, d0, nDays));
    assertEquals(21, Util.dayNumToDate(dow0, nDays, -2, dow, d0, nDays));
    assertEquals(7, Util.dayNumToDate(dow0, nDays, -4, dow, d0, nDays));
    assertEquals(0, Util.dayNumToDate(dow0, nDays, -5, dow, d0, nDays));
  }

  public void testDayNumToDateInYear() {
    //        January 2006
    //  # Su Mo Tu We Th Fr Sa
    //  1  1  2  3  4  5  6  7
    //  2  8  9 10 11 12 13 14
    //  3 15 16 17 18 19 20 21
    //  4 22 23 24 25 26 27 28
    //  5 29 30 31

    //      February 2006
    //  # Su Mo Tu We Th Fr Sa
    //  5           1  2  3  4
    //  6  5  6  7  8  9 10 11
    //  7 12 13 14 15 16 17 18
    //  8 19 20 21 22 23 24 25
    //  9 26 27 28

    //           March 2006
    //  # Su Mo Tu We Th Fr Sa
    //  9           1  2  3  4
    // 10  5  6  7  8  9 10 11
    // 11 12 13 14 15 16 17 18
    // 12 19 20 21 22 23 24 25
    // 13 26 27 28 29 30 31

    DayOfWeek dow0 = DayOfWeek.SUNDAY;
    int nInMonth = 31;
    int nDays = 365;
    int d0 = 59;

    // TODO(msamuel): check that these answers are right
    DayOfWeek dow = DayOfWeek.WEDNESDAY;
    assertEquals(
        1, Util.dayNumToDate(dow0, nDays, 9, dow, d0, nInMonth));
    assertEquals(
        8, Util.dayNumToDate(dow0, nDays, 10, dow, d0, nInMonth));
    assertEquals(
        29, Util.dayNumToDate(dow0, nDays, -40, dow, d0, nInMonth));
    assertEquals(
        22, Util.dayNumToDate(dow0, nDays, -41, dow, d0, nInMonth));

    dow = DayOfWeek.FRIDAY;
    assertEquals(
        3, Util.dayNumToDate(dow0, nDays, 9, dow, d0, nInMonth));
    assertEquals(
        10, Util.dayNumToDate(dow0, nDays, 10, dow, d0, nInMonth));
    assertEquals(
        31, Util.dayNumToDate(dow0, nDays, -40, dow, d0, nInMonth));
    assertEquals(
        24, Util.dayNumToDate(dow0, nDays, -41, dow, d0, nInMonth));

    dow = DayOfWeek.TUESDAY;
    assertEquals(
        7, Util.dayNumToDate(dow0, nDays, 10, dow, d0, nInMonth));
    assertEquals(
        14, Util.dayNumToDate(dow0, nDays, 11, dow, d0, nInMonth));
    assertEquals(
        28, Util.dayNumToDate(dow0, nDays, 13, dow, d0, nInMonth));
    assertEquals(
        0, Util.dayNumToDate(dow0, nDays, 14, dow, d0, nInMonth));
    assertEquals(
        28, Util.dayNumToDate(dow0, nDays, -40, dow, d0, nInMonth));
    assertEquals(
        21, Util.dayNumToDate(dow0, nDays, -41, dow, d0, nInMonth));
    assertEquals(
        7, Util.dayNumToDate(dow0, nDays, -43, dow, d0, nInMonth));
    assertEquals(
        0, Util.dayNumToDate(dow0, nDays, -44, dow, d0, nInMonth));
  }

  public void testUniquify() {
    int[] ints = new int[] { 1, 4, 4, 2, 7, 3, 8, 0, 0, 3 };
    int[] actual = Util.uniquify(ints);
    int[] expected = {0, 1, 2, 3, 4, 7, 8};
    assertTrue(Arrays.equals(expected, actual));
  }

  public void testNextWeekStart() {
    assertEquals(new DateValueImpl(2006, 1, 24),
                 Util.nextWeekStart(new DateValueImpl(2006, 1, 23),
                                    DayOfWeek.TUESDAY));

    assertEquals(new DateValueImpl(2006, 1, 24),
                 Util.nextWeekStart(new DateValueImpl(2006, 1, 24),
                                    DayOfWeek.TUESDAY));

    assertEquals(new DateValueImpl(2006, 1, 31),
                 Util.nextWeekStart(new DateValueImpl(2006, 1, 25),
                                    DayOfWeek.TUESDAY));

    assertEquals(new DateValueImpl(2006, 1, 23),
                 Util.nextWeekStart(new DateValueImpl(2006, 1, 23),
                                    DayOfWeek.MONDAY));

    assertEquals(new DateValueImpl(2006, 1, 30),
                 Util.nextWeekStart(new DateValueImpl(2006, 1, 24),
                                    DayOfWeek.MONDAY));

    assertEquals(new DateValueImpl(2006, 1, 30),
                 Util.nextWeekStart(new DateValueImpl(2006, 1, 25),
                                    DayOfWeek.MONDAY));

    assertEquals(new DateValueImpl(2006, 2, 6),
                 Util.nextWeekStart(new DateValueImpl(2006, 1, 31),
                                    DayOfWeek.MONDAY));
  }

  public void testCountInPeriod() {
    //        January 2006
    //  Su Mo Tu We Th Fr Sa
    //   1  2  3  4  5  6  7
    //   8  9 10 11 12 13 14
    //  15 16 17 18 19 20 21
    //  22 23 24 25 26 27 28
    //  29 30 31
    assertEquals(5, Util.countInPeriod(DayOfWeek.SUNDAY, DayOfWeek.SUNDAY, 31));
    assertEquals(5, Util.countInPeriod(DayOfWeek.MONDAY, DayOfWeek.SUNDAY, 31));
    assertEquals(5, Util.countInPeriod(DayOfWeek.TUESDAY, DayOfWeek.SUNDAY, 31));
    assertEquals(4, Util.countInPeriod(DayOfWeek.WEDNESDAY, DayOfWeek.SUNDAY, 31));
    assertEquals(4, Util.countInPeriod(DayOfWeek.THURSDAY, DayOfWeek.SUNDAY, 31));
    assertEquals(4, Util.countInPeriod(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY, 31));
    assertEquals(4, Util.countInPeriod(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY, 31));

    //      February 2006
    //  Su Mo Tu We Th Fr Sa
    //            1  2  3  4
    //   5  6  7  8  9 10 11
    //  12 13 14 15 16 17 18
    //  19 20 21 22 23 24 25
    //  26 27 28
    assertEquals(4, Util.countInPeriod(DayOfWeek.SUNDAY, DayOfWeek.WEDNESDAY, 28));
    assertEquals(4, Util.countInPeriod(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, 28));
    assertEquals(4, Util.countInPeriod(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, 28));
    assertEquals(4, Util.countInPeriod(DayOfWeek.WEDNESDAY, DayOfWeek.WEDNESDAY, 28));
    assertEquals(4, Util.countInPeriod(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, 28));
    assertEquals(4, Util.countInPeriod(DayOfWeek.FRIDAY, DayOfWeek.WEDNESDAY, 28));
    assertEquals(4, Util.countInPeriod(DayOfWeek.SATURDAY, DayOfWeek.WEDNESDAY, 28));
  }

  public void testInvertWeekdayNum() {
    //        January 2006
    //  # Su Mo Tu We Th Fr Sa
    //  1  1  2  3  4  5  6  7
    //  2  8  9 10 11 12 13 14
    //  3 15 16 17 18 19 20 21
    //  4 22 23 24 25 26 27 28
    //  5 29 30 31

    // the 1st falls on a sunday, so dow0 == SU
    assertEquals(
        5,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.SUNDAY), DayOfWeek.SUNDAY, 31));
    assertEquals(
        5,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.MONDAY), DayOfWeek.SUNDAY, 31));
    assertEquals(
        5,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.TUESDAY), DayOfWeek.SUNDAY, 31));
    assertEquals(
        4,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.WEDNESDAY), DayOfWeek.SUNDAY, 31));
    assertEquals(
        3,
        Util.invertWeekdayNum(new ByDay(-2, DayOfWeek.WEDNESDAY), DayOfWeek.SUNDAY, 31));


    //      February 2006
    //  # Su Mo Tu We Th Fr Sa
    //  1           1  2  3  4
    //  2  5  6  7  8  9 10 11
    //  3 12 13 14 15 16 17 18
    //  4 19 20 21 22 23 24 25
    //  5 26 27 28

    assertEquals(
        4,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.SUNDAY), DayOfWeek.WEDNESDAY, 28));
    assertEquals(
        4,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.MONDAY), DayOfWeek.WEDNESDAY, 28));
    assertEquals(
        4,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.TUESDAY), DayOfWeek.WEDNESDAY, 28));
    assertEquals(
        4,
        Util.invertWeekdayNum(new ByDay(-1, DayOfWeek.WEDNESDAY), DayOfWeek.WEDNESDAY, 28));
    assertEquals(
        3,
        Util.invertWeekdayNum(new ByDay(-2, DayOfWeek.WEDNESDAY), DayOfWeek.WEDNESDAY, 28));
  }
}