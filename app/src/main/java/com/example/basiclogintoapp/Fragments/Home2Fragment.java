package com.example.basiclogintoapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.basiclogintoapp.R;

import java.util.ArrayList;


public class Home2Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home2, container, false);

        // Get a reference to the WebView
        WebView pieChartWebView = rootView.findViewById(R.id.pieChartWebView);

        // Enable JavaScript in WebView
        WebSettings webSettings = pieChartWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load Google Chart URL for Pie Chart
        pieChartWebView.loadUrl("https://chart.googleapis.com/chart?cht=p&chs=300x150&chd=t:70,30&chl=Solved|In+Progress");

        // Ensure links open within the WebView
        pieChartWebView.setWebViewClient(new WebViewClient());

        return rootView;   }
}