package mc.euro.bukkitadapter;


import mc.euro.bukkitadapter.material.BattleEnchant;
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

public class EnchantAdapter {

    /**
     * This method can return null.
     * So do not dispatch methods like toString().
     *
     * @param ench - Enchant name or ID
     * @return org.bukkit.enchantments.Enchantment found on the running server or null.
     * @deprecated use parseEnchant()
     */
    @Deprecated
    public static Enchantment getEnchantment(String ench) {
       return BattleEnchant.fromString(ench).parseEnchant();
    }
}
