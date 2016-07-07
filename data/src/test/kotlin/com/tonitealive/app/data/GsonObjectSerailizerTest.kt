package com.tonitealive.app.data

import com.google.gson.GsonBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class GsonObjectSerailizerTest {

    lateinit var serializer: GsonObjectSerializer

    @Before
    fun setup() {
        val gson = com.fatboyindustrial.gsonjodatime.Converters.registerAll(GsonBuilder())
        serializer = GsonObjectSerializer(gson.create())
    }

    @Test
    fun toStringAndFromString_shouldBeConjugates() {
        // With
        val first = "foo"
        val second = 234
        val third = 1.345
        val c = MyDummyClass(first, second, third)

        // When
        val str = serializer.toString(c)
        val obj = serializer.fromString(str, MyDummyClass::class.java)

        // Then
        assertThat(obj.first).isEqualTo(first)
        assertThat(obj.second).isEqualTo(second)
        assertThat(obj.third).isEqualTo(third)
    }

    data class MyDummyClass(val first: String,
                            val second: Int,
                            val third: Double)

}
