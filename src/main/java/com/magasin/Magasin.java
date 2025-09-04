package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case ItemType.COMTE:
                    updateComte(item);
                    break;
                case ItemType.KRYPTONITE:
                    // ne change pas
                    break;
                case ItemType.PASS_VIP:
                    updatePassVIP(item);
                    break;
                case ItemType.POUVOIR_MAGIQUE:
                    updatePouvoirMagique(item);
                    break;
                default:
                    updateProduitNormal(item);
                    break;
            }
        }
    }

    private void updateProduitNormal(Item item) {
        degrade(item, 1);
        item.sellIn--;
        if(item.sellIn < 0) {
            degrade(item, 1);
        }
    }

    private void updateComte(Item item) {
        improve(item, 1);
        item.sellIn--;
        if(item.sellIn < 0) {
            improve(item, 1);
        }
    }

    private void updatePassVIP(Item item) {
        improve(item, 1);
        if(item.sellIn <= 10) {
            improve(item, 1);
        }
        if(item.sellIn <= 5) {
            improve(item, 1);
        }
        item.sellIn--;
        if(item.sellIn < 0) {
            item.quality = 0;
        }
    }

    private void updatePouvoirMagique(Item item) {
        degrade(item, 2);
        item.sellIn--;
        if(item.sellIn < 0) {
            degrade(item, 2);
        }
    }

    private void degrade(Item item, int amount) {
        item.quality = item.quality - amount;
        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    private void improve(Item item, int amount) {
        item.quality = item.quality + amount;
        if (item.quality > 50) {
            item.quality = 50;
        }
    }
}

        /*
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Comté")
                    && !items[i].name.equals("Pass VIP Concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Kryptonite")) {
                        if (items[i].name.equals("Pouvoir magique")) {
                            items[i].quality = items[i].quality - 2;
                        } else {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Pass VIP Concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Kryptonite")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Comté")) {
                    if (!items[i].name.equals("Pass VIP Concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Kryptonite")) {
                                if (items[i].name.equals("Pouvoir magique")) {
                                    items[i].quality = items[i].quality - 2;
                                } else {
                                    items[i].quality = items[i].quality - 1;
                                }
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
            if (items[i].quality < 0) {
                items[i].quality = 0; // sécurité
            }
            if (items[i].quality > 50 && !items[i].name.equals("Kryptonite")) {
                items[i].quality = 50;
            }
        }
    }
}
*/