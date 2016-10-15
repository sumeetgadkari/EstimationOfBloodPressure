package com.example.monitor;

import java.io.IOException;
import java.util.Random;

import android.media.MediaRecorder;

public class MockData {
	static final private double EMA_FILTER = 0.9;

	private static MediaRecorder mRecorder = null;
	private static double mEMA = 0.0;

	public static void start() {
		if (mRecorder == null) {
			mRecorder = new MediaRecorder();
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mRecorder.setOutputFile("/dev/null");
			try {
				mRecorder.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mRecorder.start();
			mEMA = 0.0;
		}
	}

	public static void stop() {
		if (mRecorder != null) {
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
		}
	}

	public static int getAmplitude() {
		if (mRecorder != null)
//			return (mRecorder.getMaxAmplitude());
			
			return (int)(mRecorder.getMaxAmplitude() );
		else
			return 0;

	}

	public static double getAmplitudeEMA() {
		double amp = getAmplitude();
		mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
		return mEMA;
	}

	
	// x is the day number, 0, 1, 2, 3
	public static Point getDataFromReceiver(int x)
	{	start();
		double temp=getAmplitudeEMA();
		if((int)temp==0){
			return new Point(x,0);
		}
		return new Point(x, (int)(20 * Math.log10(getAmplitudeEMA())));
	}
	
	
}
