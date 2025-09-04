package com.magasin;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagasinGoldenMasterTest {
    private static final Path SNAPSHOT_FILE = Path.of("golden_master_snapshot.txt");

    @Test
    void goldenMaster15Jours() throws IOException {
        Item[] items = new Item[] {
                new Item(ItemType.PRODUIT_NORMAL, 10, 20), // produit normal
                new Item(ItemType.PRODUIT_NORMAL, 0, 7), // produit normal périmé
                new Item(ItemType.PRODUIT_NORMAL, 5, 5), // produit normal proche de 0
                new Item(ItemType.COMTE, 2, 0), // comté
                new Item(ItemType.COMTE, 0, 49), // comté périmé et proche de 50
                new Item(ItemType.KRYPTONITE, 0, 80), // kryptonite
                new Item(ItemType.PASS_VIP, 15, 20), // Pass VIP
                new Item(ItemType.PASS_VIP, 10, 30), // Pass VIP palier 10 jours
                new Item(ItemType.PASS_VIP, 5, 35), // Pass VIP palier 5 jours
                new Item(ItemType.PASS_VIP, 0, 40), // Pass VIP concert passé
                new Item(ItemType.POUVOIR_MAGIQUE, 10, 20), // Pouvoir magique
                new Item(ItemType.POUVOIR_MAGIQUE, 0, 10) // Pouvoir magique périmé
        };

        Magasin magasin = new Magasin(items);
        StringBuilder snapshot = new StringBuilder(); // automatisation

        for (int jour = 0; jour < 15; jour++) {
            snapshot.append("Jour ").append(jour).append(":\n");
            for (Item item : items) {
                snapshot.append(item).append("\n");
            }
            snapshot.append("\n");

            magasin.updateQuality();
        }

        // Vérifie si le fichier existe déjà
        if (Files.exists(SNAPSHOT_FILE)) {
            String expected = Files.readString(SNAPSHOT_FILE);
            assertEquals(expected, snapshot.toString(), "Golden Master mismatch!");
        } else {
            // Crée le snapshop initial
            Files.writeString(SNAPSHOT_FILE, snapshot.toString());
            System.out.println("Snapshot initial créé : " + SNAPSHOT_FILE.toAbsolutePath());
        }
    }
}

