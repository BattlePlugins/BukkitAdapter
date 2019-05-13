package mc.euro.bukkitadapter;


import org.bukkit.enchantments.Enchantment;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the conversion of the enchantment ID removal in Minecraft 1.13.
 * Allows for old enchant IDs to be used in the config and maintain
 * support for BattleArena to use these IDs.
 *
 * https://hub.spigotmc.org/javadocs/spigot/org/bukkit/enchantments/Enchantment.html
 *
 */

// TODO: Refactor this class to be similar to MaterialAdapter
public class EnchantAdapter {

    private static final Map<Integer, String> emap = initMap();

    /**
     * This method can return null.
     * So do not dispatch methods like toString().
     *
     * @param ench - Enchant name or ID
     * @return org.bukkit.enchantments.Enchantment found on the running server or null.
     */
    public static Enchantment getEnchantment(String ench) {
        Enchantment enchantment = Enchantment.getByName(ench);
        if (enchantment == null) {
            int value = Integer.MIN_VALUE;

            try {
                value = Integer.valueOf(ench);
            } catch (NumberFormatException ignored) {/* do nothing*/}

            enchantment = Enchantment.getByName(emap.get(value));
        }

        return enchantment;
    }

    private static Map<Integer, String> initMap() {
        Map<Integer, String> temp = new HashMap<>();

        // Key = id
        // Value = name
        temp.put(0, "PROTECTION_ENVIRONMENTAL");
        temp.put(1, "PROTECTION_FIRE");
        temp.put(2, "PROTECTION_FALL");
        temp.put(3, "PROTECTION_EXPLOSIONS");
        temp.put(4, "PROTECTION_PROJECTILE");
        temp.put(5, "OXYGEN");
        temp.put(6, "WATER_WORKER");
        temp.put(7, "THORNS");
        temp.put(8, "DEPTH_STRIDER");
        temp.put(9, "FROST_WALKER");
        temp.put(10, "BINDING_CURSE");

        temp.put(16, "DAMAGE_ALL");
        temp.put(17, "DAMAGE_UNDEAD");
        temp.put(18, "DAMAGE_ARTHROPODS");
        temp.put(19, "KNOCKBACK");
        temp.put(20, "FIRE_ASPECT");
        temp.put(21, "LOOT_BONUS_MOBS");
        temp.put(22, "SWEEPING_EDGE");

        temp.put(32, "DIG_SPEED");
        temp.put(33, "SILK_TOUCH");
        temp.put(34, "DURABILITY");
        temp.put(35, "LOOT_BONUS_BLOCKS");

        temp.put(48, "ARROW_DAMAGE");
        temp.put(49, "ARROW_KNOCKBACK");
        temp.put(50, "ARROW_FIRE");
        temp.put(51, "ARROW_INFINITE");

        temp.put(61, "LUCK");
        temp.put(62, "LURE");

        temp.put(70, "MENDING");
        temp.put(71, "VANISHING_CURSE");

        // For enchantments that do not have an ID (>1.13)
        temp.put(-1, "CHANNELING");
        temp.put(-2, "IMPALING");
        temp.put(-3, "LOYALTY");
        temp.put(-4, "RIPTIDE");

        return Collections.unmodifiableMap(temp);
    }
}
