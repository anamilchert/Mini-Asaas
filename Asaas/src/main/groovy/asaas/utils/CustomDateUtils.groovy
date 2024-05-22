package asaas.utils

import java.text.SimpleDateFormat

class CustomDateUtils {
  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd")

  public static Date fromString(String dateStr) {
		try {
			return sdf.parse(dateStr)	
		} catch (Exception e) {
			return null
		}		
	}

  public static Date fromString(String dateStr, String formatPattern) {
    try {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern)
		return simpleDateFormat.parse(dateStr)
    } catch (Exception e){
      return null
    }
	}

  public static Date setTimeToEndOfDay(String date) {
		return setTimeToEndOfDay(sdf.parse(date))
	}

  public static Calendar getInstanceOfCalendar(Date date) {
		Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        return calendar
	}

  public static Date setTimeToEndOfDay(Date date) {
		Calendar value = CustomDateUtils.getInstanceOfCalendar(date)
		value.set(Calendar.HOUR_OF_DAY, 23)
		value.set(Calendar.MINUTE, 59)
		value.set(Calendar.SECOND, 59)
		
		return value.getTime()
	}
}