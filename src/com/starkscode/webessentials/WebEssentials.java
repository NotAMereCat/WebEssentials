package com.starkscode.webessentials;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class WebEssentials extends JavaPlugin {
	public IconMenu menu;

	public void onEnable() {
		getConfig();
		saveDefaultConfig();
		menu = new IconMenu(ChatColor.translateAlternateColorCodes('&',
				getConfig().getString("gui-title")), 9,
				new IconMenu.OptionClickEventHandler() {
					public void onOptionClick(IconMenu.OptionClickEvent event) {
						String name = event.getName();
						Player player = event.getPlayer();
						if (name.contains(getConfig().getString(
								"vote-icon-name").replaceAll("(&([a-f0-9]))",
								""))) {
							List<String> voteurls = getConfig().getStringList(
									"vote-urls");
							for (String vu : voteurls) {
								player.sendMessage(ChatColor
										.translateAlternateColorCodes('&', vu));
							}
						}
						if (name.contains(getConfig().getString(
								"website-icon-name").replaceAll(
								"(&([a-f0-9]))", ""))) {
							try {
								Desktop.getDesktop().browse(
										URI.create(getConfig().getString(
												"website-url")));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (name.contains(getConfig().getString(
								"donate-icon-name").replaceAll("(&([a-f0-9]))",
								""))) {
							try {
								Desktop.getDesktop().browse(
										URI.create(getConfig().getString(
												"donate-url")));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (name.contains(getConfig().getString(
								"voice-server-icon-name").replaceAll(
								"(&([a-f0-9]))", ""))) {
							player.sendMessage(ChatColor
									.translateAlternateColorCodes(
											'&',
											getConfig().getString(
													"voice-server-ip")));
						}
						event.setWillClose(true);
					}
				}, this)
				.setOption(
						getConfig().getInt("vote-icon-position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("vote-icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("vote-icon-name")),
						new String[] { ChatColor.translateAlternateColorCodes(
								'&', getConfig().getString("vote-icon-lore")) })
				.setOption(
						getConfig().getInt("website-icon-position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("website-icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("website-icon-name")),
						new String[] { ChatColor
								.translateAlternateColorCodes('&', getConfig()
										.getString("website-icon-lore")) })
				.setOption(
						getConfig().getInt("donate-icon-position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("donate-icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("donate-icon-name")),
						new String[] { ChatColor.translateAlternateColorCodes(
								'&', getConfig().getString("donate-icon-lore")) })
				.setOption(
						getConfig().getInt("voice-server-icon-position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("voice-server-icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("voice-server-icon-name")),
						new String[] { ChatColor
								.translateAlternateColorCodes('&', getConfig()
										.getString("voice-server-icon-lore")) });
	}

	public void onDisable() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("webe")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "You must be a player to perform this command!");
			} else {
				Player player = (Player) sender;
				menu.open(player);
			}
			return true;
		}
		return false;
	}
}
