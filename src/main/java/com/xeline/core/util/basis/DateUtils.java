package com.xeline.core.util.basis;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.Assert;

/**
 * @author xenron
 */
public class DateUtils {

  public static String YYYYMMDD = "yyyyMMdd";

  public static Date date() {
    return dateTime().toDate();
  }

  public static Date date(String text) {
    return dateTime(text).toDate();
  }

  public static Date date(String text, String pattern) {
    return dateTime(text, pattern).toDate();
  }

  public static long currentMillis() {
    return DateTimeUtils.currentTimeMillis();
  }

  public static long millis(Date date) {
    return millis(dateTime(date));
  }

  public static long millis(DateTime dateTime) {
    return dateTime.getMillis();
  }

  public static DateTime now() {
    return dateTime();
  }

  public static DateTime dateTime() {
    return new DateTime();
  }

  public static DateTime dateTime(String text) {
    return new DateTime(text);
  }

  public static DateTime dateTime(String text, String pattern) {
    return dateTime(text, pattern, Locale.getDefault());
  }

  public static DateTime dateTime(String text, String pattern, Locale locale) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern).withLocale(locale);
    return formatter.parseDateTime(text);
  }

  public static DateTime dateTime(Date date) {
    return new DateTime(date);
  }

  public static Date currentTimeWithDate(Date date) {
    return currentTimeWithDate(dateTime(date)).toDate();
  }

  public static DateTime currentTimeWithDate(DateTime dateTime) {
    DateTime current = dateTime();
    return baseTimeWithDate(current, dateTime);
  }

  public static Date baseTimeWithDate(Date date, Date ymdDate) {
    DateTime base = dateTime(date);
    DateTime ymd = dateTime(ymdDate);
    return baseTimeWithDate(base, ymd).toDate();
  }

  public static DateTime baseTimeWithDate(DateTime baseDateTime, DateTime ymdDateTime) {
    DateTime dateTime = new DateTime(baseDateTime);
    return dateTime.withDate(ymdDateTime.getYear(), ymdDateTime.getMonthOfYear(), ymdDateTime.getDayOfMonth());
  }

  public static String format(Date date, String pattern) {
    return format(date, pattern, Locale.getDefault());
  }

  public static String format(Date date, String pattern, Locale locale) {
    DateTime dateTime = dateTime(date);
    String result = dateTime.toString(pattern, locale);
    return result;
  }

  public static String nullableFormat(Date date, String pattern) {
    return nullableFormat(date, pattern, Locale.getDefault());
  }

  public static String nullableFormat(Date date, String pattern, Locale locale) {
    try {
      return format(date, pattern, locale);
    } catch (Exception e) {
      return null;
    }
  }

  public static Optional<String> optFormat(Date date, String pattern) {
    return Optional.ofNullable(nullableFormat(date, pattern));
  }

  public static Optional<String> optFormat(Date date, String pattern, Locale locale) {
    return Optional.ofNullable(nullableFormat(date, pattern, locale));
  }

  public static String format(Date date, DateTimeFormatter formatter) {
    DateTime dateTime = dateTime(date);
    String result = dateTime.toString(formatter);
    return result;
  }

  public static String nullableFormat(Date date, DateTimeFormatter formatter) {
    try {
      return format(date, formatter);
    } catch (Exception e) {
      return null;
    }
  }

  public static DateTime parse(String text, String pattern) {
    return parse(text, pattern, Locale.getDefault());
  }

  public static DateTime parse(String text, String pattern, Locale locale) {
    return DateTimeFormat.forPattern(pattern).withLocale(locale).parseDateTime(text);
  }

  public static DateTime nullableParse(String text, String pattern) {
    return nullableParse(text, pattern, Locale.getDefault());
  }

  public static DateTime nullableParse(String text, String pattern, Locale locale) {
    try {
      return parse(text, pattern, locale);
    } catch (Exception e) {
      return null;
    }
  }

  public static Optional<DateTime> optParse(String text, String pattern) {
    return Optional.ofNullable(nullableParse(text, pattern));
  }

  public static Optional<DateTime> optParse(String text, String pattern, Locale locale) {
    return Optional.ofNullable(nullableParse(text, pattern, locale));
  }

  public static Date parseDate(String text, String pattern) {
    return parse(text, pattern, Locale.getDefault()).toDate();
  }

  public static Date parseDate(String text, String pattern, Locale locale) {
    return parse(text, pattern, locale).toDate();
  }

  public static Date nullableParseDate(String text, String pattern) {
    return nullableParseDate(text, pattern, Locale.getDefault());
  }

  public static Date nullableParseDate(String text, String pattern, Locale locale) {
    try {
      return parseDate(text, pattern, locale);
    } catch (Exception e) {
      return null;
    }
  }

  public static Optional<Date> optParseDate(String text, String pattern) {
    return Optional.ofNullable(nullableParseDate(text, pattern));
  }

  public static Optional<Date> optParseDate(String text, String pattern, Locale locale) {
    return Optional.ofNullable(nullableParseDate(text, pattern, locale));
  }

  public static boolean isValid(String text, String pattern) {
    return isValid(text, pattern, Locale.getDefault());
  }

  public static boolean isValid(String text, String pattern, Locale locale) {
    Date date = nullableParseDate(text, pattern, locale);
    return date != null;
  }

  public static boolean isBefore(Date date1, Date date2) {
    DateTime d1 = dateTime(date1);
    DateTime d2 = dateTime(date2);
    return isBefore(d1, d2);
  }

  public static boolean isBefore(DateTime date1, DateTime date2) {
    return date1.isBefore(date2);
  }

  public static boolean isAfter(Date date1, Date date2) {
    DateTime d1 = dateTime(date1);
    DateTime d2 = dateTime(date2);
    return isAfter(d1, d2);
  }

  public static boolean isAfter(DateTime date1, DateTime date2) {
    return date1.isAfter(date2);
  }

  public static boolean isEqual(Date date1, Date date2) {
    DateTime d1 = dateTime(date1);
    DateTime d2 = dateTime(date2);
    return isEqual(d1, d2);
  }

  public static boolean isEqual(DateTime date1, DateTime date2) {
    return date1.isEqual(date2);
  }

  public static boolean isRange(String target, String start, String end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    DateTime dateTime = dateTime(target);

    return isRange(dateTime, startDateTime, endDateTime);
  }

  public static boolean isRange(String target, String start, String end, String pattern) {
    return isRange(target, start, end, pattern, Locale.getDefault());
  }

  public static boolean isRange(String target, String start, String end, String pattern, Locale locale) {

    DateTime startDateTime = dateTime(start, pattern, locale);
    DateTime endDateTime = dateTime(end, pattern, locale);
    DateTime dateTime = dateTime(target, pattern, locale);

    return isRange(dateTime, startDateTime, endDateTime);
  }

  public static boolean isRange(Date target, Date start, Date end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    DateTime dateTime = dateTime(target);

    return isRange(dateTime, startDateTime, endDateTime);
  }

  public static boolean isRange(DateTime target, DateTime start, DateTime end) {
    Interval interval = new Interval(start, end);
    return interval.contains(target) || end.isEqual(target);
  }

  public static boolean isLeap(Date date) {
    DateTime dateTime = dateTime(date);
    return dateTime.year().isLeap();
  }

  public static int year(Date date) {
    return dateTime(date).year().get();
  }

  public static int month(Date date) {
    return dateTime(date).monthOfYear().get();
  }

  public static int day(Date date) {
    return dateTime(date).dayOfMonth().get();
  }

  public static DateTime dateTimeMaxDayOfMonth(Date date) {
    DateTime dateTime = dateTime(date).dayOfMonth().withMaximumValue();
    return dateTime;
  }

  public static Date maxDayOfMonth() {
    return dateTimeMaxDayOfMonth(date()).toDate();
  }

  public static Date maxDayOfMonth(Date date) {
    return dateTimeMaxDayOfMonth(date).toDate();
  }

  public static DateTime dateTimeMinDayOfMonth(Date date) {
    DateTime dateTime = dateTime(date).dayOfMonth().withMinimumValue();
    return dateTime;
  }

  public static Date minDayOfMonth() {
    return dateTimeMinDayOfMonth(date()).toDate();
  }

  public static Date minDayOfMonth(Date date) {
    return dateTimeMinDayOfMonth(date).toDate();
  }

  public static Date plusDays(String text, int days) {
    return dateTime(text).plusDays(days).toDate();
  }

  public static Date plusDays(String text, int days, String pattern) {
    return plusDays(text, days, pattern, Locale.getDefault());
  }

  public static Date plusDays(String text, int days, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).plusDays(days).toDate();
  }

  public static String plusDaysText(String text, int days, String pattern) {
    return plusDaysText(text, days, pattern, Locale.getDefault());
  }

  public static String plusDaysText(String text, int days, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).plusDays(days).toString(pattern, locale);
  }

  public static Date plusDays(Date date, int days) {
    return dateTime(date).plusDays(days).toDate();
  }

  public static String plusDaysText(Date date, int days, String pattern) {
    return plusDaysText(date, days, pattern, Locale.getDefault());
  }

  public static String plusDaysText(Date date, int days, String pattern, Locale locale) {
    return dateTime(date).plusDays(days).toString(pattern, locale);
  }

  public static Date plusYears(String text, int years) {
    return dateTime(text).plusYears(years).toDate();
  }

  public static Date plusYears(String text, int years, String pattern) {
    return plusYears(text, years, pattern, Locale.getDefault());
  }

  public static Date plusYears(String text, int years, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).plusYears(years).toDate();
  }

  public static String plusYearsText(String text, int years, String pattern) {
    return plusYearsText(text, years, pattern, Locale.getDefault());
  }

  public static String plusYearsText(String text, int years, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).plusYears(years).toString(pattern, locale);
  }

  public static Date plusYears(Date date, int years) {
    return dateTime(date).plusYears(years).toDate();
  }

  public static String plusYearsText(Date date, int years, String pattern) {
    return plusYearsText(date, years, pattern, Locale.getDefault());
  }

  public static String plusYearsText(Date date, int years, String pattern, Locale locale) {
    return dateTime(date).plusYears(years).toString(pattern, locale);
  }

  public static Date minusDays(String text, int days) {
    return dateTime(text).minusDays(days).toDate();
  }

  public static Date minusDays(String text, int days, String pattern) {
    return minusDays(text, days, pattern, Locale.getDefault());
  }

  public static Date minusDays(String text, int days, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).minusDays(days).toDate();
  }

  public static Date minusDays(Date date, int days) {
    return dateTime(date).minusDays(days).toDate();
  }

  public static String minusDaysText(String text, int days, String pattern) {
    return minusDaysText(text, days, pattern, Locale.getDefault());
  }

  public static String minusDaysText(String text, int days, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).minusDays(days).toString(pattern, locale);
  }

  public static String minusDaysText(Date date, int days, String pattern) {
    return minusDaysText(date, days, pattern, Locale.getDefault());
  }

  public static String minusDaysText(Date date, int days, String pattern, Locale locale) {
    return dateTime(date).minusDays(days).toString(pattern, locale);
  }

  public static Date minusYears(String text, int years) {
    return dateTime(text).minusYears(years).toDate();
  }

  public static Date minusYears(String text, int years, String pattern) {
    return minusYears(text, years, pattern, Locale.getDefault());
  }

  public static Date minusYears(String text, int years, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).minusYears(years).toDate();
  }

  public static Date minusYears(Date date, int years) {
    return dateTime(date).minusYears(years).toDate();
  }

  public static String minusYearsText(String text, int years, String pattern) {
    return minusYearsText(text, years, pattern, Locale.getDefault());
  }

  public static String minusYearsText(String text, int years, String pattern, Locale locale) {
    return dateTime(text, pattern, locale).minusYears(years).toString(pattern, locale);
  }

  public static String minusYearsText(Date date, int years, String pattern) {
    return minusYearsText(date, years, pattern, Locale.getDefault());
  }

  public static String minusYearsText(Date date, int years, String pattern, Locale locale) {
    return dateTime(date).minusYears(years).toString(pattern, locale);
  }

  public static int periodDays(String start, String end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    return periodDays(startDateTime, endDateTime);
  }

  public static int periodDays(String start, String end, String pattern) {
    return periodDays(start, end, pattern, Locale.getDefault());
  }

  public static int periodDays(String start, String end, String pattern, Locale locale) {

    DateTime startDateTime = dateTime(start, pattern, locale);
    DateTime endDateTime = dateTime(end, pattern, locale);
    return periodDays(startDateTime, endDateTime);
  }

  public static int periodDays(Date start, Date end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    return periodDays(startDateTime, endDateTime);
  }

  public static int periodDays(DateTime start, DateTime end) {
    Period period = new Period(start, end);
    return period.toStandardDays().getDays();
  }

  public static long durationDays(String start, String end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    return durationDays(startDateTime, endDateTime);
  }

  public static long durationDays(String start, String end, String pattern) {
    return durationDays(start, end, pattern, Locale.getDefault());
  }

  public static long durationDays(String start, String end, String pattern, Locale locale) {

    DateTime startDateTime = dateTime(start, pattern, locale);
    DateTime endDateTime = dateTime(end, pattern, locale);
    return durationDays(startDateTime, endDateTime);
  }

  public static long durationDays(Date start, Date end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    return durationDays(startDateTime, endDateTime);
  }

  public static long durationDays(DateTime start, DateTime end) {
    Duration duration = new Duration(start, end);
    return duration.getStandardDays();
  }

  public static int durationLeapDays(String start, String end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    return durationLeapDays(startDateTime, endDateTime);
  }

  public static int durationLeapDays(String start, String end, String pattern) {
    return durationLeapDays(start, end, pattern, Locale.getDefault());
  }

  public static int durationLeapDays(String start, String end, String pattern, Locale locale) {

    DateTime startDateTime = dateTime(start, pattern, locale);
    DateTime endDateTime = dateTime(end, pattern, locale);
    return durationLeapDays(startDateTime, endDateTime);
  }

  public static int durationLeapDays(Date start, Date end) {

    DateTime startDateTime = dateTime(start);
    DateTime endDateTime = dateTime(end);
    return durationLeapDays(startDateTime, endDateTime);
  }

  public static int durationLeapDays(DateTime start, DateTime end) {

    DateTime base = dateTime();
    DateTime startDateTime = baseTimeWithDate(base, start);
    DateTime endDateTime = baseTimeWithDate(base, end);

    int startYear = startDateTime.year().get();
    int endYear = endDateTime.year().get();
    int leapDays = 0;
    for (int i = startYear; i <= endYear; i++) {

      DateTime current = dateTime().withYear(i);
      if (current.year().isLeap()) {

        DateTime leapDateTime = dateTime().withYear(i).withMonthOfYear(2).withDayOfMonth(29);
        leapDateTime = baseTimeWithDate(base, leapDateTime);
        int currentYear = leapDateTime.getYear();
        if (startYear == currentYear) {
          if (isBefore(startDateTime, leapDateTime) || isEqual(startDateTime, leapDateTime)) {
            leapDays++;
          }
        } else if (endYear == currentYear) {
          if (isBefore(leapDateTime, endDateTime) || isEqual(leapDateTime, endDateTime)) {
            leapDays++;
          }
        } else {
          leapDays++;
        }
      }
    }
    return leapDays;
  }

  private static void assertYyyyMMddLength(String... days) {
    Assert.notEmpty(days);
    for (String day : days) {
      Assert.notNull(day);
      if (YYYYMMDD.length() != day.length()) {
        throw new IllegalArgumentException(String.format("day[%s] is unacceptable format[%s].", day, YYYYMMDD));
      }
    }
  }

  public static Date yyyyMMdd(String text) {
    assertYyyyMMddLength(text);
    return yyyyMMddDateTime(text).toDate();
  }

  public static DateTime yyyyMMddDateTime(String text) {
    assertYyyyMMddLength(text);
    return dateTime(text, YYYYMMDD);
  }

  public static boolean isValidYyyyMMdd(String text) {
    try {
      assertYyyyMMddLength(text);
    } catch (Exception e) {
      return false;
    }
    return isValid(text, YYYYMMDD, Locale.getDefault());
  }

  public static String yyyyMMdd(Date date) {
    return format(date, YYYYMMDD, Locale.getDefault());
  }

  public static String yyyyMMddDateTime(DateTime dateTime) {
    return yyyyMMdd(dateTime.toDate());
  }

  public static boolean isRangeYyyyMMdd(String target, String start, String end) {
    assertYyyyMMddLength(target, start, end);
    return isRange(target, start, end, YYYYMMDD, Locale.getDefault());
  }

  public static int periodDaysYyyyMMdd(String start, String end) {
    assertYyyyMMddLength(start, end);
    return periodDays(start, end, YYYYMMDD, Locale.getDefault());
  }

  public static long durationDaysYyyyMMdd(String start, String end) {
    assertYyyyMMddLength(start, end);
    return durationDays(start, end, YYYYMMDD, Locale.getDefault());
  }

  public static int durationLeapDaysYyyyMMdd(String start, String end) {
    assertYyyyMMddLength(start, end);
    return durationLeapDays(start, end, YYYYMMDD, Locale.getDefault());
  }

  public static Date plusDaysYyyyMMdd(String text, int days) {
    assertYyyyMMddLength(text);
    return plusDays(text, days, YYYYMMDD);
  }

  public static String plusDaysTextYyyyMMdd(String text, int days) {
    assertYyyyMMddLength(text);
    return plusDaysText(text, days, YYYYMMDD);
  }

  public static Date plusYearsYyyyMMdd(String text, int years) {
    assertYyyyMMddLength(text);
    return plusYears(text, years, YYYYMMDD);
  }

  public static String plusYearsTextYyyyMMdd(String text, int years) {
    assertYyyyMMddLength(text);
    return plusYearsText(text, years, YYYYMMDD);
  }

  public static Date minusDaysYyyyMMdd(String text, int days) {
    assertYyyyMMddLength(text);
    return minusDays(text, days, YYYYMMDD);
  }

  public static String minusDaysTextYyyyMMdd(String text, int days) {
    assertYyyyMMddLength(text);
    return minusDaysText(text, days, YYYYMMDD);
  }

  public static Date minusYearsYyyyMMdd(String text, int years) {
    assertYyyyMMddLength(text);
    return minusYears(text, years, YYYYMMDD);
  }

  public static String minusYearsTextYyyyMMdd(String text, int years) {
    assertYyyyMMddLength(text);
    return minusYearsText(text, years, YYYYMMDD);
  }

}
