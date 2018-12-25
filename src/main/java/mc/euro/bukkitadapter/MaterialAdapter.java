package mc.euro.bukkitadapter;

import org.bukkit.Material;

/**
 * Handles the conversion of Material renaming in Spigot 1.13.
 * Allows for the old (now legacy in 1.13) Material names to be used
 * as well as ID support in versions below 1.13.
 *
 * WARNING: This is only a TEMPORARY solution and will only work in
 * Spigot versions that contain the LEGACY version of a Material.
 *
 * https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
 *
 */
public class MaterialAdapter {

    /**
     * @param mat - Material name (and/or ID in versions <1.13)
     * @return org.bukkit.Material found on the running server or AIR.
     */
    public static Material getMaterial(String mat) {
        Material material = Material.matchMaterial(mat);
        if (material == null) {
            material = Material.matchMaterial("LEGACY_" + mat.toUpperCase());
        }

        return (material == null) ? Material.AIR : material;
    }
}
