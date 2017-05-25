package com.xeline.core.util.basis;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.format.number.NumberFormatter;

import com.xeline.core.exception.ParseRuntimeException;

/**
 * @author xenron
 */
public class NumberUtils {

  // ParseRuntimeException ex = null;

  public static NumberFormatter numberFormatter(String pattern) {
    return new NumberFormatter(pattern);
  }

  public static String format(Number number, String pattern, Locale locale) {
    NumberFormatter formatter = numberFormatter(pattern);
    String result = formatter.print(number, locale);
    return result;
  }

  public static String format(Number number, String pattern) {
    return format(number, pattern, Locale.getDefault());
  }

  public static Number parse(String text, String pattern, Locale locale) {
    NumberFormatter formatter = numberFormatter(pattern);
    try {
      Number number = formatter.parse(text, locale);
      return number;
    } catch (ParseException e) {
      throw new ParseRuntimeException(e);
    }
  }

  public static Number parse(String text, String pattern) {
    return parse(text, pattern, Locale.getDefault());
  }

  public static BigDecimal parseBigDecimal(String text, String pattern, Locale locale) {
    return (BigDecimal) parse(text, pattern, locale);
  }

  public static BigDecimal parseBigDecimal(String text, String pattern) {
    return parseBigDecimal(text, pattern, Locale.getDefault());
  }

  public static Integer parseInteger(String text, String pattern, Locale locale) {
    return Integer.valueOf(parseBigDecimal(text, pattern, locale).intValue());
  }

  public static Integer parseInteger(String text, String pattern) {
    return parseInteger(text, pattern, Locale.getDefault());
  }

  public static Long parseLong(String text, String pattern, Locale locale) {
    return Long.valueOf(parseBigDecimal(text, pattern, locale).longValue());
  }

  public static Long parseLong(String text, String pattern) {
    return parseLong(text, pattern, Locale.getDefault());
  }

  public static Double parseDouble(String text, String pattern, Locale locale) {
    return Double.valueOf(parseBigDecimal(text, pattern, locale).doubleValue());
  }

  public static Double parseDouble(String text, String pattern) {
    return parseDouble(text, pattern, Locale.getDefault());
  }

  public static Float parseFloat(String text, String pattern, Locale locale) {
    return Float.valueOf(parseBigDecimal(text, pattern, locale).floatValue());
  }

  public static Float parseFloat(String text, String pattern) {
    return parseFloat(text, pattern, Locale.getDefault());
  }

  public static Short parseShort(String text, String pattern, Locale locale) {
    return Short.valueOf(parseBigDecimal(text, pattern, locale).shortValue());
  }

  public static Short parseShort(String text, String pattern) {
    return parseShort(text, pattern, Locale.getDefault());
  }

  public static Number nullableParse(String text, String pattern, Locale locale) {
    try {
      return parse(text, pattern, locale);
    } catch (Exception e) {
      return null;
    }
  }

  public static Number nullableParse(String text, String pattern) {
    return nullableParse(text, pattern, Locale.getDefault());
  }

  public static Optional<Number> optParse(String text, String pattern, Locale locale) {
    return Optional.ofNullable(nullableParse(text, pattern, locale));
  }

  public static Optional<Number> optParse(String text, String pattern) {
    return Optional.ofNullable(nullableParse(text, pattern));
  }

}
