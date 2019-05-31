package mc.euro.bukkitadapter;

import mc.euro.bukkitadapter.material.BattleMaterial;
import org.bukkit.Material;

/**
 * Handles the conversion of Material renaming in Spigot 1.13.
 * Allows for the old (now legacy in 1.13) Material names to be used
 * as well as ID support in versions below 1.13.
 *
 * https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
 *
 */
public class MaterialAdapter {

    /**
     * @param mat - Material name (and/or ID in versions <1.13)
     * @return org.bukkit.Material found on the running server or AIR.
     * @deprecated use parseItem() or parseMaterial()
     */
    @Deprecated
    public static Material getMaterial(String mat) {
        // Try and get it from BattleMaterial first
        Material material = Material.AIR;
        try {
            material = BattleMaterial.fromString(mat).parseMaterial();

            // If it doesn't exist, try and get it from a legacy material
            if (material == null) {
                material = Material.matchMaterial("LEGACY_" + mat.toUpperCase());
            }
        } catch (NullPointerException ex) {

        }

        return (material == null) ? Material.AIR : material;
    }
}
