package com.example.PopoacaGeorgian341A4.Notita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotitaTest {
    Notita n = new Notita("titlu", "subtitlu", "continut", "culoare");

    @Test
    void getTitluTest() {

        String titlu = n.getTitlu();
        Assertions.assertEquals("titlu", titlu);
    }

    @Test
    void getSubitluTest() {
        String subtitlu = n.getSubtitlu();
        Assertions.assertNotEquals("Hello", subtitlu);
    }

}