package com.placeholder;

import org.opencv.android.*;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.core.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;

public class MainActivity extends Activity implements CvCameraViewListener2
{
	private BaseLoaderCallback _cameraLoadedCallback;
	private CameraBridgeViewBase _camera;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _cameraLoadedCallback = new BaseLoaderCallback(this)
		{
        	@Override
            public void onManagerConnected(int status) {
        		if (status == LoaderCallbackInterface.SUCCESS)
        		{
        			_camera.enableView();
        		}
        		else
        		{
        			super.onManagerConnected(status);
        		}
            }
		};
        
        _camera = (CameraBridgeViewBase)findViewById(R.id.cameraView);
        _camera.setVisibility(SurfaceView.VISIBLE);
        _camera.setCvCameraViewListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }
    
    public void onPause()
    {
    	super.onPause();
    	
        if (_camera != null)
        {
        	_camera.disableView();
        }
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this, _cameraLoadedCallback);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
        if (_camera != null)
        {
        	_camera.disableView();
        }
    }

	@Override
	public void onCameraViewStarted(int width, int height)
	{
	}

	@Override
	public void onCameraViewStopped()
	{
	}

	@Override
	public Mat onCameraFrame(CvCameraViewFrame inputFrame)
	{
		return inputFrame.rgba();
	}
}
