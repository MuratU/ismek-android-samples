package com.dnkilic.alarmy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;
import android.util.Log;

import java.util.Calendar;

public class AlarmSetter  {

    private static final int REQUEST_CODE_SET_ALARM = 102;
    private static final int REQUEST_CODE_SET_TIMER = 103;

    private static final int ERROR_INSUFFICIENT_ANDROID_VERSION = 0;
    private static final int ERROR_COUNTDOWN_LIMIT_OVERFLOW = 1;
    private static final int SUCCESSFUL = 2;

	private Activity mActivity;

	public AlarmSetter(Activity activity) {
		mActivity = activity;
	}

	//TODO Bu metodu çağırırken String duration formatı 00:00:00 şeklinde olmalı.... Örneğin 100dakikaya geri sayım 01:40:00
	public void setCountdownClock(String duration) {
		switch (setCountDownClock(duration))
		{
			case ERROR_COUNTDOWN_LIMIT_OVERFLOW:
				Log.i("Nergis", "Geri sayım kurma limiti aşıldı.");
				break;
			case ERROR_INSUFFICIENT_ANDROID_VERSION:
				Log.i("Nergis", "Geri sayım kurma işlemi cihazın android versiyonu yetersiz olduğu için yapılmadı.");
				break;
			case SUCCESSFUL:
				Log.i("Nergis", "Geri sayım kurma işlemi başarılı.");
				break;
		}
	}

	private int setCountDownClock(String duration) {

		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

			int seconds;
			int day, hour, minute, second;

			String[] durationArray = duration.split("\\.");

			if(durationArray.length > 1)
			{
				day = Integer.parseInt(durationArray[0]);

				String hours = durationArray[1];
				String[] hourArray = hours.split(":");

				hour = Integer.parseInt(hourArray[0]);
				minute = Integer.parseInt(hourArray[1]);
				second = Integer.parseInt(hourArray[2]);

				seconds = second + minute * 60 + hour * 60 * 60 + day * 60 * 60 * 24;
			}
			else
			{
				String[] hourArray = duration.split(":");

				hour = Integer.parseInt(hourArray[0]);
				minute = Integer.parseInt(hourArray[1]);
				second = Integer.parseInt(hourArray[2]);

				seconds = second + minute * 60 + hour * 60 * 60;
			}

			if(seconds > 360000)
			{
				return AlarmSetter.ERROR_COUNTDOWN_LIMIT_OVERFLOW;
			}

			return setCountDown(seconds);

		}
		else
		{
			return AlarmSetter.ERROR_INSUFFICIENT_ANDROID_VERSION;
		}
	}

	//TODO Bu metodu çağırırken  String time formatı 00:00 şeklinde olmalı....
	public void setAlarmClock(String time) {
		Calendar alarmTime = stringTimeToCalendar(time);
		setAlarm(alarmTime);
	}

	private void setAlarm(Calendar cal) {
		Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
		alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Bu alarm Alarmy tarafından kuruldu.");
		alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, cal.get(Calendar.HOUR_OF_DAY));
		alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, cal.get(Calendar.MINUTE));
		alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
		mActivity.startActivityForResult(alarmIntent, REQUEST_CODE_SET_ALARM);
    }

	@TargetApi(Build.VERSION_CODES.KITKAT)
	private int setCountDown(int seconds) {
		Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_TIMER);
		alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Bu geri sayım Alarmy tarafından kuruldu.");
		alarmIntent.putExtra(AlarmClock.EXTRA_LENGTH, seconds);
		alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
		mActivity.startActivityForResult(alarmIntent, REQUEST_CODE_SET_TIMER);
		return AlarmSetter.SUCCESSFUL;
	}

	private Calendar stringTimeToCalendar(String time) {
		String[] hourMin = time.split(":");
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hourMin[0]));
        alarmTime.set(Calendar.MINUTE, Integer.valueOf(hourMin[1]));
        return alarmTime;
    }
}
