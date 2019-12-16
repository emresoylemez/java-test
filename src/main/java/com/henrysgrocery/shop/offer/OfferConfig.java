package com.henrysgrocery.shop.offer;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class OfferConfig {

    public static List<Offer> getOffers(final Clock clock) {
        final LocalDate now = now(clock);
        final LocalDate yesterday = now.minusDays(1);
        final LocalDate sevenDaysEhead = now.plusDays(7);
        final LocalDate threeDaysEhead = now.plusDays(3);
        final LocalDate endOfTheFollowingMonth = now.plusMonths(1).with(lastDayOfMonth());

        return Arrays.asList((new BuyTwoSoupsGetBreadHalfPriceOffer(yesterday, sevenDaysEhead)),
                new AppleTenPercentDiscountOffer(threeDaysEhead, endOfTheFollowingMonth));
    }
}
