package mc.euro.bukkitadapter.material;

import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;

public enum BattleEnchant {

    PROTECTION_ENVIRONMENTAL(0, "Protection", "PROTECTION", "PROT"),
    PROTECTION_FIRE(1, "Fire Protection", "FIRE_PROTECTION", "FIRE_PROT"),
    PROTECTION_FALL(2, "Feather Falling", "FEATHER_FALLING", "FEATHER_FALL"),
    PROTECTION_EXPLOSIONS(3, "Blast Protection", "BLAST_PROTECTION", "BLAST_PROT"),
    PROTECTION_PROJECTILE(4, "Projectile Protection", "PROJECTILE_PROTECTION", "PROJECTILE_PROT"),
    OXYGEN(5, "Respiration", "RESPIRATION"),
    WATER_WORKER(6, "Aqua Affinity", "AQUA_AFFINITY", "AQUA"),
    THORNS(7, "Thorns"),
    DEPTH_STRIDER(8, "Depth Strider", "STRIDER"),
    FROST_WALKER(9, "Frost Walker"),
    BINDING_CURSE(10, "&cCurse of Binding", "CURSE_OF_BINDING"),
    DAMAGE_ALL(16, "Sharpness", "SHARPNESS", "SHARP"),
    DAMAGE_UNDEAD(17, "Smite", "SMITE"),
    DAMAGE_ARTHROPODS(18, "Bane of Arthropods", "BANE_OF_ARTHROPODS", "BANE"),
    KNOCKBACK(19, "Knockback"),
    FIRE_ASPECT(20, "Fire Aspect", "FIRE", "FIRE_ASPECT"),
    LOOT_BONUS_MOBS(21, "Looting", "LOOTING"),
    SWEEPING_EDGE(22, "Sweeping Edge", "SWEEP"),
    DIG_SPEED(32, "Efficiency", "EFFICIENCY"),
    SILK_TOUCH(33, "Silk Touch", "SILK_TOUCH", "SILK"),
    DURABILITY(34, "Unbreaking", "UNBREAKING"),
    LOOT_BONUS_BLOCKS(35, "Fortune", "FORTUNE"),
    ARROW_DAMAGE(48, "Power", "Power"),
    ARROW_KNOCKBACK(49, "Punch", "PUNCH"),
    ARROW_FIRE(50, "Flame", "FLAME"),
    ARROW_INFINITE(51, "Infinity", "INFINITY"),
    LUCK(61, "Luck of the Sea", "LUCK_OF_THE_SEA"),
    LURE(62, "Lure"),
    MENDING(70, "Mending"),
    VANISHING_CURSE(71, "Curse of Vanishing", "CURSE_OF_VANISHING"),
    CHANNELING(-1, "Channeling"),
    IMPALING(-2, "Impaling"),
    LOYALTY(-3, "Loyalty"),
    RIPTIDE(-4, "Riptide"),
    MULTISHOT(-5, "Multishot"),
    PIERCING(-6, "Piercing"),
    QUICK_CHARGE(-7, "Quick Charge");

    private int id;
    private String name;
    private String[] aliases;
    private Enchantment cached = null;

    BattleEnchant(int id, String name, String... aliases) {
        this.id = id;
        this.name = name;
        this.aliases = aliases;
    }

    public Enchantment parseEnchant() {
        if (cached != null)
            return cached;

        Enchantment enchant = Enchantment.getByName(this.toString());
        if (enchant != null) {
            return cached = enchant;
        }

        if (enchant == null && !isNewVersion()) {
            return cached = Enchantment.getById(id);
        }

        return cached = Enchantment.getByName(aliases[0]);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    private static HashMap<String, BattleEnchant> cachedSearch = new HashMap<String, BattleEnchant>();

    public static BattleEnchant fromEnchantment(Enchantment enchant) {
        try {
            return BattleEnchant.valueOf(enchant.toString());
        } catch (IllegalArgumentException ex) {
            for (BattleEnchant battleEnch : BattleEnchant.values()) {
                for (String test : battleEnch.aliases) {
                    if (test.equalsIgnoreCase(enchant.toString()))
                        return battleEnch;

                    if (!isNewVersion()) {
                        if (enchant.getId() == battleEnch.getId())
                            return battleEnch;
                    }
                }
            }
        }

        return null;
    }

    public static BattleEnchant fromString(String key) {
        String enchKey = key.replace("minecraft:", "").toUpperCase();
        BattleEnchant ench = null;

        try {
            ench = BattleEnchant.valueOf(enchKey);
            return ench;
        } catch (IllegalArgumentException ex) {
            return ench = requestBattleEnchant(enchKey);
        }
    }

    public static BattleEnchant requestBattleEnchant(String name) {
        if (cachedSearch.containsKey(name.toUpperCase()))
            return cachedSearch.get(name.toUpperCase());

        for (BattleEnchant ench : BattleEnchant.values()) {
            for (String test : ench.getAliases()) {
                if (name.toUpperCase().equals(test)) {
                    cachedSearch.put(test, ench);
                    return ench;
                }
            }

            if (!isNewVersion()) {
                if (!isInteger(name))
                    continue;

                if (Integer.parseInt(name) == ench.getId())
                    return ench;
            }
        }

        return null;
    }

    public static boolean isNewVersion() {
        Enchantment enchant = Enchantment.getByName("RIPTIDE");
        if (enchant != null)
            return true;

        return false;
    }

    private static boolean isInteger(String key) {
        try {
            Integer.parseInt(key);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
