package com.bidgely.library.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class DateUtilTest {

    @Test
    void testFormatDate() {
        LocalDate date = LocalDate.of(2023, 10, 15);
        assertThat(DateUtil.formatDate(date)).isEqualTo("2023-10-15");
    }

    @Test
    void testFormatDate_withNull() {
        assertThat(DateUtil.formatDate(null)).isNull();
    }

    @Test
    void testParseDate() {
        LocalDate date = DateUtil.parseDate("2023-10-15");
        assertThat(date).isEqualTo(LocalDate.of(2023, 10, 15));
    }

    @Test
    void testParseDate_withNull() {
        assertThat(DateUtil.parseDate(null)).isNull();
    }

    @Test
    void testParseDate_withEmptyString() {
        assertThat(DateUtil.parseDate("")).isNull();
    }

    @Test
    void testDaysBetween() {
        LocalDate start = LocalDate.of(2023, 10, 1);
        LocalDate end = LocalDate.of(2023, 10, 15);
        assertThat(DateUtil.daysBetween(start, end)).isEqualTo(14);
    }

    @Test
    void testDaysBetween_withNullStart() {
        LocalDate end = LocalDate.of(2023, 10, 15);
        assertThat(DateUtil.daysBetween(null, end)).isEqualTo(0);
    }

    @Test
    void testDaysBetween_withNullEnd() {
        LocalDate start = LocalDate.of(2023, 10, 1);
        assertThat(DateUtil.daysBetween(start, null)).isEqualTo(0);
    }

    @Test
    void testDaysBetween_withSameDate() {
        LocalDate date = LocalDate.of(2023, 10, 15);
        assertThat(DateUtil.daysBetween(date, date)).isEqualTo(0);
    }

    @Test
    void testAddDays() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        LocalDate result = DateUtil.addDays(date, 14);
        assertThat(result).isEqualTo(LocalDate.of(2023, 10, 15));
    }

    @Test
    void testAddDays_withNegativeDays() {
        LocalDate date = LocalDate.of(2023, 10, 15);
        LocalDate result = DateUtil.addDays(date, -14);
        assertThat(result).isEqualTo(LocalDate.of(2023, 10, 1));
    }

    @Test
    void testAddDays_withNull() {
        assertThat(DateUtil.addDays(null, 14)).isNull();
    }

    @Test
    void testIsPast() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertThat(DateUtil.isPast(pastDate)).isTrue();
    }

    @Test
    void testIsPast_withFutureDate() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertThat(DateUtil.isPast(futureDate)).isFalse();
    }

    @Test
    void testIsPast_withToday() {
        LocalDate today = LocalDate.now();
        assertThat(DateUtil.isPast(today)).isFalse();
    }

    @Test
    void testIsPast_withNull() {
        assertThat(DateUtil.isPast(null)).isFalse();
    }

    @Test
    void testIsFuture() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertThat(DateUtil.isFuture(futureDate)).isTrue();
    }

    @Test
    void testIsFuture_withPastDate() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertThat(DateUtil.isFuture(pastDate)).isFalse();
    }

    @Test
    void testIsFuture_withToday() {
        LocalDate today = LocalDate.now();
        assertThat(DateUtil.isFuture(today)).isFalse();
    }

    @Test
    void testIsFuture_withNull() {
        assertThat(DateUtil.isFuture(null)).isFalse();
    }

    @Test
    void testToday() {
        LocalDate today = DateUtil.today();
        assertThat(today).isEqualTo(LocalDate.now());
    }
}

