package com.magasin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MagasinTest {

    /* @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        Magasin app = new Magasin(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    } */

    @Test
    void produitNormal_perd1QualityEt1Sellin() {
        Magasin magasin = new Magasin(new Item[] { new Item("Produit normal", 10, 20) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(9, produit.sellIn); // -1
        assertEquals(19, produit.quality); // -1
    }

    @Test
    void produitNormalPerime_perd2Quality() {
        Magasin magasin = new Magasin(new Item[] { new Item("Produit normal", 0, 10) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(-1, produit.sellIn);
        assertEquals(8, produit.quality); // -2
    }

    @Test
    void quality_jamaisNegative() {
        Magasin magasin = new Magasin(new Item[] { new Item("Produit normal", 5, 0) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(4, produit.sellIn);
        assertEquals(0, produit.quality); // jamais < 0
    }

    @Test
    void comte_gagne1Quality() {
        Magasin magasin = new Magasin(new Item[] { new Item("Comté", 10, 40) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(9, produit.sellIn);
        assertEquals(41, produit.quality); // +1
    }

    @Test
    void quality_jamaisPlusQue50() {
        Magasin magasin = new Magasin(new Item[] { new Item("Comté", 5, 50)});
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(4, produit.sellIn);
        assertEquals(50, produit.quality); // bloqué à 50
    }

    @Test
    void kryptonite_neChangeJamais() {
        Magasin magasin = new Magasin(new Item[] { new Item("Kryptonite", 5, 80) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(5, produit.sellIn); // pas de date de péremption
        assertEquals(80, produit.quality); // ne perd jamais en qualité
    }

    @Test
    void passVIP_gagne1Quality() {
        Magasin magasin = new Magasin(new Item[] { new Item("Pass VIP Concert", 15, 20) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(14, produit.sellIn);
        assertEquals(21, produit.quality); // +1
    }

    @Test
    void passVIP_10JoursOuMoins_gagne2Quality() {
        Magasin magasin = new Magasin(new Item[] { new Item("Pass VIP Concert", 10, 20) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(9, produit.sellIn);
        assertEquals(22, produit.quality); // +2
    }

    @Test
    void passVIP_5JoursOuMoins_gagne3Quality() {
        Magasin magasin = new Magasin(new Item[] { new Item("Pass VIP Concert", 5, 20) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(4, produit.sellIn);
        assertEquals(23, produit.quality); // +3
    }

    @Test
    void passVIP_apresConcert_qualityZero() {
        Magasin magasin = new Magasin(new Item[] { new Item("Pass VIP Concert", 0, 20) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(-1, produit.sellIn);
        assertEquals(0, produit.quality); // tombe à 0
    }

    @Test
    void pouvoirMagique_perd2Quality() {
        Magasin magasin = new Magasin(new Item[] { new Item("Pouvoir Magique", 10, 20) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(9, produit.sellIn);
        assertEquals(18, produit.quality); // -2 au lieu de -1
    }

    void pouvoirMagiquePerime_perd4Quality() {
        Magasin magasin = new Magasin(new Item[] { new Item("Pouvoir Magique", 0, 10) });
        magasin.updateQuality();
        Item produit = magasin.items[0];
        assertEquals(-1, produit.sellIn);
        assertEquals(6, produit.quality); // -4 au lieu de -2
    }

    @Test
    void goldenMaster15Jours() {
        Item[] items = new Item[] {
                new Item("Produit normal", 10, 20),
                new Item("Comté", 2, 0),
                new Item("Kryptonite", 0, 80),
                new Item("Pass VIP Concert", 15, 20),
                new Item("Produit normal", 0, 7),
                new Item("Produit normal", 5, 45)
        };

        Magasin magasin = new Magasin(items);

        for (int jour = 0; jour < 15; jour++) {
            System.out.println("Jour " +  jour + " :");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();

            magasin.updateQuality();
        }
    }
}
