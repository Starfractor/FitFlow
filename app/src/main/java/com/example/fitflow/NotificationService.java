package com.example.fitflow;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;


public class NotificationService {

    private static final String CHANNEL_ID = "com.example.fitflow.notification";
    private static int NOTIFICATION_ID = 0; // Initialize with a default value

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification Channel";
            String description = "Channel for my notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void setupDailyNotifications(Context context) {
        // Get current date and time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        int caloriesConsumed = activeLog.foodLog.totalCals;
        int caloriesRequested = activeLog.userInfo.recommendedCalories;
        int waterIntake = activeLog.waterLog.totalOz;
        int waterRequested = (int) (activeLog.userInfo.recommendedLiters * 33.814);

        // Calculate next meal time for the new day
        LocalTime nextMealTime = calculateNextMealTime(currentTime, caloriesConsumed, caloriesRequested);

        // Calculate next water intake time for the new day
        LocalTime nextWaterTime = calculateNextWaterTime(currentTime, waterIntake, waterRequested);

        // Schedule notifications for next meal and water intake
        if (nextMealTime != null) {
            scheduleNotification(context, "Meal Reminder", "Don't forget to eat!", nextMealTime);
        }
        if (nextWaterTime != null) {
            scheduleNotification(context, "Water Reminder", "Don't forget to drink water!", nextWaterTime);
        }
    }

    public static void setupMidnightAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            Intent intent = new Intent(context, MidnightAlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Set the alarm to trigger at midnight every day
            Calendar midnight = Calendar.getInstance();
            midnight.setTimeInMillis(System.currentTimeMillis());
            midnight.set(Calendar.HOUR_OF_DAY, 0);
            midnight.set(Calendar.MINUTE, 0);
            midnight.set(Calendar.SECOND, 0);
            midnight.set(Calendar.MILLISECOND, 0);
            midnight.add(Calendar.DAY_OF_MONTH, 1); // Set it for the next day

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, midnight.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public static class MidnightAlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // When the alarm triggers at midnight, set up daily notifications
            setupDailyNotifications(context);
        }
    }
    
    public static LocalTime calculateNextMealTime(LocalTime currentTime, int caloriesEaten, int totalCaloriesNeeded) {
        // Check if it's 8:00 PM or later, return no time
        if (currentTime.isAfter(LocalTime.of(19, 59))) {
            return null; 
        }

        // Calculate remaining calories
        int remainingCalories = totalCaloriesNeeded - caloriesEaten;
        long hoursUntilEndOfDay = currentTime.until(LocalTime.of(20, 0), ChronoUnit.HOURS);
        long remainingHours = Math.min(hoursUntilEndOfDay, 24 - currentTime.getHour());
        double caloriesPerHour = (double) remainingCalories / remainingHours;
        long hoursUntilNextMeal = (long) Math.ceil((double) remainingCalories / caloriesPerHour);
        LocalTime nextMealTime = currentTime.plusHours(hoursUntilNextMeal);

        // Check if next meal time exceeds 8:00 PM
        if (nextMealTime.isAfter(LocalTime.of(19, 59))) {
            return null; // No time
        }

        // Check if next meal time is before 8:00 AM, return 8:00 AM
        if (nextMealTime.isBefore(LocalTime.of(8, 0))) {
            nextMealTime = LocalTime.of(8, 0);
        }

        return nextMealTime;
    }

    public static LocalTime calculateNextWaterTime(LocalTime currentTime, int waterConsumed, int totalWaterNeeded) {
        // Check if it's 8:00 PM or later, return no time
        if (currentTime.isAfter(LocalTime.of(19, 59))) {
            return null; 
        }

        // Calculate remaining water intake
        int remainingWater = totalWaterNeeded - waterConsumed;
        long hoursUntilEndOfDay = currentTime.until(LocalTime.of(20, 0), ChronoUnit.HOURS);
        long remainingHours = Math.min(hoursUntilEndOfDay, 24 - currentTime.getHour());
        double waterPerHour = (double) remainingWater / remainingHours;
        long hoursUntilNextWater = (long) Math.ceil((double) remainingWater / waterPerHour);
        LocalTime nextWaterTime = currentTime.plusHours(hoursUntilNextWater);

        // Check if next water time exceeds 8:00 PM
        if (nextWaterTime.isAfter(LocalTime.of(19, 59))) {
            return null; 
        }

        if (nextWaterTime.isBefore(LocalTime.of(8, 0))) {
            nextWaterTime = LocalTime.of(8, 0);
        }

        return nextWaterTime;
    }

    public static void scheduleNotification(Context context, String title, String message, LocalTime notificationTime) {
        NOTIFICATION_ID++; // Increment the notification ID for each new notification
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, NOTIFICATION_ID);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_TITLE, title);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_MESSAGE, message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long triggerTimeMillis = calculateTriggerTimeMillis(notificationTime);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTimeMillis, pendingIntent);
        }
    }

    private static long calculateTriggerTimeMillis(LocalTime notificationTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime notificationDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), notificationTime);
    
        // If the notification time is before the current time, schedule it for the next day
        if (notificationDateTime.isBefore(currentDateTime)) {
            notificationDateTime = notificationDateTime.plusDays(1);
        }

        long millisecondsUntilNotification = ChronoUnit.MILLIS.between(currentDateTime, notificationDateTime);
    
        return System.currentTimeMillis() + millisecondsUntilNotification;
    }

    public static class NotificationPublisher extends BroadcastReceiver {
        public static String NOTIFICATION_ID = "notification_id";
        public static String NOTIFICATION_TITLE = "notification_title";
        public static String NOTIFICATION_MESSAGE = "notification_message";

        @Override
        public void onReceive(Context context, Intent intent) {
            int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
            String title = intent.getStringExtra(NOTIFICATION_TITLE);
            String message = intent.getStringExtra(NOTIFICATION_MESSAGE);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                createNotificationChannel(context);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                notificationManager.notify(notificationId, builder.build());
            }
        }
    }

    public static void cancelNotification(Context context, String title) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(title.hashCode()); // Cancel the notification based on its title
        }
    }
}
