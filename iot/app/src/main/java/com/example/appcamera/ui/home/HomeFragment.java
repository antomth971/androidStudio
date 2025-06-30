package com.example.appcamera.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcamera.databinding.FragmentHomeBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class HomeFragment extends Fragment implements ZXingScannerView.ResultHandler{
    ZXingScannerView scannerView;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        scannerView = new ZXingScannerView(getActivity());
        List<BarcodeFormat> formats = new ArrayList<>();
        scannerView.setFormats(formats);

        FrameLayout contentFrame = binding.frame;
        contentFrame.addView(scannerView);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
//        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();// Prints the scan format (qrcode, pdf417 etc.)
        Log.d("AttendingFragment", rawResult.getText());
        // If you would like to resume scanning, call this method below:
        scannerView.resumeCameraPreview(this);
    }
}