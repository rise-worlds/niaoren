package com.stripe.android.view;

import android.support.annotation.NonNull;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/* renamed from: com.stripe.android.view.j */
/* loaded from: classes2.dex */
public class PaymentUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m17233a(long j, @NonNull Currency currency, String str) {
        if (j == 0) {
            return str;
        }
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol(currency.getSymbol(Locale.getDefault()));
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return m17234a(j, currency);
    }

    /* renamed from: a */
    static String m17234a(double d, @NonNull Currency currency) {
        double pow = d / Math.pow(10.0d, currency.getDefaultFractionDigits());
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        try {
            DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) currencyInstance).getDecimalFormatSymbols();
            decimalFormatSymbols.setCurrencySymbol(currency.getSymbol(Locale.getDefault()));
            ((DecimalFormat) currencyInstance).setDecimalFormatSymbols(decimalFormatSymbols);
            return currencyInstance.format(pow);
        } catch (ClassCastException unused) {
            return currencyInstance.format(pow);
        }
    }
}
