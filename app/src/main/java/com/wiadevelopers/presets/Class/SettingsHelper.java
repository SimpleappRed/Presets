package com.wiadevelopers.presets.Class;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.wiadevelopers.presets.R;


public class SettingsHelper
{
    private static final String IN_APP_REFERRAL_SETTINGS = "&referrer=utm_source%3Din_app_referral%26utm_medium%3Dsettings";
    private static final String GOOGLE_PLAY_PRODUCER = "https://play.google.com/store/apps/developer?id=Skilltory";

    public static void rateUsOnGooglePlay(Context context)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName()));
        intent.setPackage("com.android.vending");
        context.startActivity(intent);
    }

    public static void shareApp(Context context)
    {
        try
        {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
            String shareMessage = "\n" + context.getString(R.string.share_text) + "\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + context.getPackageName() + IN_APP_REFERRAL_SETTINGS + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.settings_aboutus_invite_a_friend_via)));
        }
        catch (Exception e)
        {
        }
    }

    public static void otherApps(Context context)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(GOOGLE_PLAY_PRODUCER));
        intent.setPackage("com.android.vending");
        context.startActivity(intent);
    }

    public static void sendMail(Context context, String emailAddress)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emailAddress, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "YOUR_SUBJECT_HERE");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.send_email)));
    }

    public static void openPrivacyPolicy(Context context, String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }
}
