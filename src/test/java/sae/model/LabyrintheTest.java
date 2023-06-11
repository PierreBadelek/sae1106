package sae.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrintheTest {
    protected Labyrinthe laby;
    @BeforeEach
    void setUp() throws Exception {
        laby = new Labyrinthe(5,4);
    }
    @AfterEach
    public void tearDown() throws Exception {
        laby=null;
    }

    @Test
    void loadLaby() {
        assertThrows(Exception.class, ()-> {laby.loadLaby("5");});
    }

    @Test
    void trouverCaseXY() {
        assertThrows(Exception.class, ()-> {laby.TrouverCaseXY(0,-1);});

    }
}