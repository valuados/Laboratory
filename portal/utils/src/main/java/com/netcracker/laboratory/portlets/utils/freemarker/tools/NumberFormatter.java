package com.netcracker.laboratory.portlets.utils.freemarker.tools;

import com.liferay.portal.kernel.language.LanguageUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
@Component("numberFormatter")
public class NumberFormatter implements Tool {

    private final Pattern PRICE_PATTERN = Pattern.compile("(.*)([^\\d])(\\d+)$");
    private final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,##0.00");

    public PriceDetails splitPrice(BigDecimal price) {
        String formatted = PRICE_FORMAT.format(price);
        Matcher matcher = PRICE_PATTERN.matcher(formatted);
        PriceDetails priceDetails = new PriceDetails();
        if (matcher.matches()) {
            priceDetails.setDollars(matcher.group(1));
            priceDetails.setCents(matcher.group(3));
        }
        return priceDetails;
    }

    public String formatPrice(BigDecimal price) {
        return PRICE_FORMAT.format(price);
    }

    public String formatMessage(Locale locale, String key, Object object) {
        return LanguageUtil.format(locale, key, object);
    }

    public class PriceDetails {
        private String dollars;
        private String cents;

        public String getDollars() {
            return dollars;
        }

        public void setDollars(String dollars) {
            this.dollars = dollars;
        }

        public String getCents() {
            return cents;
        }

        public void setCents(String cents) {
            this.cents = cents;
        }
    }
}
