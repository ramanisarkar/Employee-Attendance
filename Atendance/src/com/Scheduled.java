package com;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduled {
	public void sche(){
		ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
		ZonedDateTime nextRun = now.withHour(01).withMinute(21).withSecond(40);
		System.out.println(nextRun);
		if (now.compareTo(nextRun) > 0)
			nextRun = nextRun.plusDays(1);


		Duration duration = Duration.between(now, nextRun);
		long initalDelay = duration.getSeconds();


		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new Autoateendance(), initalDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);

	}

}
