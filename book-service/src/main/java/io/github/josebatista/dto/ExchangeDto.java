package io.github.josebatista.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ExchangeDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionFactor;
    private Double convertedValue;
    private String environment;

    public ExchangeDto() {
    }

    public ExchangeDto(Long id, String from, String to, BigDecimal conversionFactor, Double convertedValue, String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.convertedValue = convertedValue;
        this.environment = environment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeDto that = (ExchangeDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getFrom(), that.getFrom()) && Objects.equals(getTo(), that.getTo()) && Objects.equals(getConversionFactor(), that.getConversionFactor()) && Objects.equals(getConvertedValue(), that.getConvertedValue()) && Objects.equals(getEnvironment(), that.getEnvironment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getConversionFactor(), getConvertedValue(), getEnvironment());
    }
}
