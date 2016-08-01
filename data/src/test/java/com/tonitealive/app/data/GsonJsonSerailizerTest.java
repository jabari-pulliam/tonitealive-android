package com.tonitealive.app.data;

import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GsonJsonSerailizerTest {

    private GsonJsonSerializer serializer;

    //public GsonJsonSerailizerTest() {}

    @Before
    public void setup() {
        GsonBuilder gson = com.fatboyindustrial.gsonjodatime.Converters.registerAll(new GsonBuilder());
        serializer = new GsonJsonSerializer(gson.create());
    }

    @Test
    public void toStringAndFromString_shouldBeConjugates() {
        // With
        String first = "foo";
        int second = 234;
        double third = 1.345;
        MyDummyClass c = new MyDummyClass(first, second, third);

        // When
        String str = serializer.toString(c);
        MyDummyClass obj = serializer.fromString(str, MyDummyClass.class);

        // Then
        assertThat(obj.first).isEqualTo(first);
        assertThat(obj.second).isEqualTo(second);
        assertThat(obj.third).isEqualTo(third);
    }

    private static class MyDummyClass {
        public final String first;
        public final int second;
        public final double third;

        public MyDummyClass(String first, int second, double third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
}
