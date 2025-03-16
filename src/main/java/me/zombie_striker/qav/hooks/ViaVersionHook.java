package me.zombie_striker.qav.hooks;

import com.cryptomorin.xseries.reflection.XReflection;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class ViaVersionHook {

    public static String getVersion(Player player) {
        if (!Bukkit.getPluginManager().isPluginEnabled("ViaVersion")) return XReflection.MINOR_NUMBER + "." + XReflection.PATCH_NUMBER;

        try {
            int version = Via.getAPI().getPlayerVersion(player.getUniqueId());
            return ProtocolVersion.getProtocols()
                    .stream()
                    .filter(pv -> pv.getVersion() == version)
                    .findFirst()
                    .map(pv -> {
                        String[] split = pv.getName().split("\\.");
                        return split[1] + "." + split[2];
                    })
                    .orElse(XReflection.MINOR_NUMBER + "." + XReflection.PATCH_NUMBER);
        } catch (Exception | Error e) {
            return XReflection.MINOR_NUMBER + "." + XReflection.PATCH_NUMBER;
        }
    }

}