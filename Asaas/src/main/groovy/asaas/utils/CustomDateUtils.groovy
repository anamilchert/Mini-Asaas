package asaas.utils

import java.text.SimpleDateFormat

class CustomDateUtils {

	public static final String DATABASE_DATE_FORMAT = "yyyy-MM-dd"

	public static Date fromString(String dateStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATABASE_DATE_FORMAT)
			return sdf.parse(dateStr)	

		} catch (Exception e) {
			return null
		}		
	}

    public static Date fromString(String dateStr, String formatPattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern)
            return simpleDateFormat.parse(dateStr)

        } catch (Exception e) {
            return null
        }
	}

    public static Date setTimeToEndOfDay(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATABASE_DATE_FORMAT)
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