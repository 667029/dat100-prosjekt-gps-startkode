package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitude = new double[gpspoints.length];

		for (int i = 0; i < latitude.length; i++) {
			latitude[i] = gpspoints[i].getLatitude();
		}

		return latitude;

	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitude = new double[gpspoints.length];

		for (int i = 0; i < longitude.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
		}

		return longitude;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d, a, c;
		double latitude1, longitude1, latitude2, longitude2, deltaLatitude, deltaLongitude;

		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());

		deltaLatitude = latitude2 - latitude1;
		deltaLongitude = longitude2 - longitude1;

		a = Math.pow(Math.sin(deltaLatitude / 2), 2)
				+ Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(deltaLongitude / 2), 2);
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		d = GPSUtils.R * c;

		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		secs = gpspoint2.getTime() - gpspoint1.getTime();

		speed = distance(gpspoint1, gpspoint2) / secs;
		speed = speed * 3.6;

		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		timestr = String.format("%02d:%02d:%02d", secs / 3600, (secs / 60) % 60, secs % 60);
        while (timestr.length() < TEXTWIDTH) {
            timestr = " " + timestr;
        }
        return timestr;

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		double rundet = Math.round(d * 100.0) / 100.0;

        str = "" + Double.toString(rundet);
        	while( str.length() < TEXTWIDTH) {
        		str = " " + str;
        	}
        	
        return str;

	}
}
