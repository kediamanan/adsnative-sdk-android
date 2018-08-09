package com.adsnative.sampleads;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adsnative.ads.AdHelper;
import com.adsnative.header_bidding.DFP.PolymorphBidder;
import com.adsnative.util.ANLog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;

import java.net.URLEncoder;

public class DFPNativeAd extends Fragment {


    private static final String SIMPLE_TEMPLATE_ID = "10104090";
    private static final String DFP_AD_UNIT_ID = "/6499/example/native";
    //    private static String PM_AD_UNIT_ID = "FbkE_RjFNgdb42BbWdIABOBCtJGoCqPv3FhZsPhd";
    private static String PM_AD_UNIT_ID = "FvfkqOJvMC-y37a7WwDMJVNhgs1cWlHX24NsPFy9";

    public void setAdUnitId(String AD_UNIT_ID) {
        if (AD_UNIT_ID != null && !AD_UNIT_ID.isEmpty()) {
            ANLog.e("Placement id: " + AD_UNIT_ID);
            this.PM_AD_UNIT_ID = URLEncoder.encode(AD_UNIT_ID);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_native_ad, container, false);
        final RelativeLayout nativeAdContainer = (RelativeLayout) view.findViewById(R.id.native_ad);

        ANLog.e("DFP_AD_UNIT_ID: " + DFP_AD_UNIT_ID);
        AdLoader.Builder builder = new AdLoader.Builder(getContext(), DFP_AD_UNIT_ID);
//        builder.forCustomTemplateAd(SIMPLE_TEMPLATE_ID,
//                new NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener() {
//                    @Override
//                    public void onCustomTemplateAdLoaded(final NativeCustomTemplateAd ad) {
//                        final NativeCustomTemplateAd customAd = ad;
//
//                        View adView = inflater.inflate(R.layout.ad_simple_custom_template, null);
//
//                        TextView headline = (TextView) adView.findViewById(R.id.simplecustom_headline);
//                        TextView caption = (TextView) adView.findViewById(R.id.simplecustom_caption);
//
//                        headline.setText(ad.getText("Headline"));
//                        caption.setText(ad.getText("Caption"));
//
//                        FrameLayout mediaPlaceholder = (FrameLayout) adView.findViewById(R.id.simplecustom_media_placeholder);
//                        ImageView mainImage = new ImageView(getContext());
//                        mainImage.setAdjustViewBounds(true);
//                        mainImage.setImageDrawable(ad.getImage("MainImage").getDrawable());
//
//
//                        mediaPlaceholder.addView(mainImage);
//
//                        nativeAdContainer.addView(adView);
//                }
//        },null);
//                builder.forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
//            @Override
//            public void onAppInstallAdLoaded(final NativeAppInstallAd nativeAppInstallAd) {
//                NativeAppInstallAdView adView = (NativeAppInstallAdView) inflater
//                        .inflate(R.layout.ad_app_install, null);
//                adView.setHeadlineView(adView.findViewById(R.id.appinstall_headline));
//                adView.setBodyView(adView.findViewById(R.id.appinstall_body));
//                adView.setCallToActionView(adView.findViewById(R.id.appinstall_call_to_action));
//                adView.setIconView(adView.findViewById(R.id.appinstall_app_icon));
//                adView.setImageView(adView.findViewById(R.id.appinstall_image));
//                MediaView mediaView = (MediaView) adView.findViewById(R.id.appinstall_media);
//                mediaView.setVisibility(View.GONE);
//
//                ((TextView) adView.getHeadlineView()).setText(nativeAppInstallAd.getHeadline());
//                ((TextView) adView.getBodyView()).setText(nativeAppInstallAd.getBody());
//                ((Button) adView.getCallToActionView()).setText(nativeAppInstallAd.getCallToAction());
//                ((ImageView) adView.getIconView()).setImageDrawable(nativeAppInstallAd.getIcon()
//                        .getDrawable());
//                if (nativeAppInstallAd.getImages() != null) {
//                    ((ImageView) adView.getImageView()).setImageDrawable(nativeAppInstallAd.getImages().get(0).getDrawable());
//                }
//                adView.setNativeAd(nativeAppInstallAd);
//                nativeAdContainer.addView(adView);
//
//            }
//        });

        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            @Override
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                NativeContentAdView adView = (NativeContentAdView) inflater
                        .inflate(R.layout.ad_content_ad_view, null);
                adView.setHeadlineView(adView.findViewById(R.id.contentad_headline));
                adView.setBodyView(adView.findViewById(R.id.contentad_body));
                adView.setCallToActionView(adView.findViewById(R.id.contentad_call_to_action));
                adView.setLogoView(adView.findViewById(R.id.contentad_logo));
                adView.setAdvertiserView(adView.findViewById(R.id.contentad_advertiser));
                MediaView mediaView = (MediaView) adView.findViewById(R.id.contentad_media);
                mediaView.setVisibility(View.GONE);
                adView.setImageView(adView.findViewById(R.id.contentad_image));
                adView.setLogoView(adView.findViewById(R.id.contentad_logo));
                // Some assets are guaranteed to be in every NativeContentAd.
                ((TextView) adView.getHeadlineView()).setText(nativeContentAd.getHeadline());
                ((TextView) adView.getBodyView()).setText(nativeContentAd.getBody());
                ((TextView) adView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
                ((TextView) adView.getAdvertiserView()).setText(nativeContentAd.getAdvertiser());
                if (nativeContentAd.getImages() != null)
                    ((ImageView) adView.getImageView()).setImageDrawable(nativeContentAd.getImages().get(0).getDrawable());
                ANLog.e("nativeContentAd.getImages().get(0): " + nativeContentAd.getImages().get(0).getUri());
                ANLog.e("nativeContentAd.getImages().get(0): " + nativeContentAd.getImages().get(0).getScale());
                ANLog.e("nativeContentAd.getImages().get(0): " + nativeContentAd.getImages().get(0).getDrawable());
                if (nativeContentAd.getLogo() != null) {
                    ANLog.e("nativeContentAd.getLogo().getUri(): " + nativeContentAd.getLogo().getUri());
                    ((ImageView) adView.getLogoView()).setImageDrawable(nativeContentAd.getLogo().getDrawable());
                }
                adView.setNativeAd(nativeContentAd);
                nativeAdContainer.addView(adView);
            }
        });
        builder.forPublisherAdView(new OnPublisherAdViewLoadedListener() {
            @Override
            public void onPublisherAdViewLoaded(PublisherAdView publisherAdView) {
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView(publisherAdView);
            }
        }, AdSize.BANNER);
        builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getContext(), "Failed to load native ad: "
                        + errorCode, Toast.LENGTH_SHORT).show();
            }
        });
        AdLoader adLoader = builder.build();
//        PublisherAdRequest.Builder pubBuilder = new PublisherAdRequest.Builder();
//        pubBuilder.addCustomTargeting("key", "value").
//                setContentUrl("https://www.example.com");
        /* Native Request */
//        PolymorphBidder pm_bidder = new PolymorphBidder(getContext(), PM_AD_UNIT_ID, adLoader);
        /* Native-Banner request (needs banner size also) */
        PolymorphBidder pm_bidder = new PolymorphBidder(getContext(), PM_AD_UNIT_ID, adLoader, AdHelper.AdSize.BANNER_300x50);
//        pm_bidder.setBiddingInterval(0.05);
//        pm_bidder.setPubAdRequestBuilder(pubBuilder);
//        pm_bidder.setBannerSize(AdHelper.AdSize.BANNER_300x50);
        pm_bidder.loadDFPAd();
        return view;
    }


    @Override
    public void onDestroyView() {
        // You must call this or the ad adapter may cause a memory leak.
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        // AdsNative recommends loading new ads when the user returns to your activity.
        super.onResume();
    }
}
