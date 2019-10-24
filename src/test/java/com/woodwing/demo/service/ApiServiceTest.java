package com.woodwing.demo.service;

import com.woodwing.demo.domain.RequestBodyInput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceTest {

    @Autowired
    private ApiService apiService;

    @Test
    public void testMetersToYards() {
        Assert.assertEquals(1.0936f, apiService.metersToYards(1), 0.0);
    }

    @Test
    public void testYardsToMeters() {
        Assert.assertEquals(0.9144f, apiService.yardsToMeters(1), 0.0);
    }

    @Test
    public void testConvert() {
        Assert.assertEquals(1.0936f, apiService.convert(1, "yeards"), 0.0);
        Assert.assertEquals(0.9144f, apiService.convert(1, "meters"), 0.0);
    }

    @Test
    public void testSum() {
        // prepare the request data
        RequestBodyInput input = new RequestBodyInput();
        input.setSumType("yards");
        input.setSummandOneType("yards");
        input.setSummandTwoType("yards");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(20);
        Assert.assertEquals(30, apiService.sum(input), 0.1);

        input = new RequestBodyInput();
        input.setSumType("meters");
        input.setSummandOneType("meters");
        input.setSummandTwoType("meters");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(20);
        Assert.assertEquals(30, apiService.sum(input), 0.1);

        input = new RequestBodyInput();
        input.setSumType("meters");
        input.setSummandOneType("yards");
        input.setSummandTwoType("yards");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(10);
        Assert.assertEquals(18.288, apiService.sum(input), 0.1);

        input = new RequestBodyInput();
        input.setSumType("yards");
        input.setSummandOneType("meters");
        input.setSummandTwoType("yards");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(10);
        Assert.assertEquals(20.9361, apiService.sum(input), 0.1);

        input = new RequestBodyInput();
        input.setSumType("meters");
        input.setSummandOneType("meters");
        input.setSummandTwoType("yards");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(10);
        Assert.assertEquals(19.144, apiService.sum(input), 0.1);
    }
}
