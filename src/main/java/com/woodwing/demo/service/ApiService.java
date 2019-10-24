package com.woodwing.demo.service;

import com.woodwing.demo.domain.RequestBodyInput;
import com.woodwing.demo.domain.UnitType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ApiService {

    private static final float METER_TO_YARD_MULTIPLIER = 1.0936f;
    private static final float YARD_TO_METER_MULTIPLIER = 0.9144f;

    @Value("${result_decimal_places}")
    private int decimalPlaces;

    public float sum(@NotNull RequestBodyInput input) {
        float sum = 0.0f;

        if (!input.getSummandOneType().equals(input.getSumType())) {
            sum += convert(input.getSummandOneValue(), input.getSumType());
        } else {
            sum += input.getSummandOneValue();
        }

        if (!input.getSummandTwoType().equals(input.getSumType())) {
            sum += convert(input.getSummandTwoValue(), input.getSumType());
        } else {
            sum += input.getSummandTwoValue();
        }

        return round(sum);
    }

    public float metersToYards(float value) {
        return value * METER_TO_YARD_MULTIPLIER;
    }

    public float yardsToMeters(float value) {
        return value * YARD_TO_METER_MULTIPLIER;
    }

    private float round(float value) {
        return BigDecimal.valueOf(value).setScale(decimalPlaces, RoundingMode.HALF_UP).floatValue();
    }

    public float convert(float value, String toType) {
        if (UnitType.meters.getName().equals(toType)) {
            return yardsToMeters(value);
        } else {
            return metersToYards(value);
        }
    }
}
