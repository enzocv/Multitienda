package com.backend.multitienda.audit;

import org.springframework.data.auditing.DateTimeProvider;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.GregorianCalendar;
import java.util.Optional;

public class DateTimeProviderImpl implements DateTimeProvider {
  @Override
  public Optional<TemporalAccessor> getNow() {
    return Optional.of(LocalDateTime.now());
  }
}
