package com.starkscode.webessentials;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

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
				getConfig().getString("gui_title")), 9,
				new IconMenu.OptionClickEventHandler() {
					public void onOptionClick(IconMenu.OptionClickEvent event) {
						String name = event.getName();
						Player player = event.getPlayer();
						if (name.contains(getConfig().getString(
								"vote_icon_name").replaceAll("(&([a-f0-9]))",
								""))) {
							for (String v_u : getConfig().getStringList(
									"vote_urls")) {
								try {
									Desktop.getDesktop()
											.browse(URI.create(v_u));
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						if (name.contains(getConfig().getString(
								"website_icon_name").replaceAll(
								"(&([a-f0-9]))", ""))) {
							for (String w_u : getConfig().getStringList(
									"website_urls")) {
								try {
									Desktop.getDesktop()
											.browse(URI.create(w_u));
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						if (name.contains(getConfig().getString(
								"donate_icon_name").replaceAll("(&([a-f0-9]))",
								""))) {
							for (String d_u : getConfig().getStringList(
									"donate_urls")) {
								try {
									Desktop.getDesktop()
											.browse(URI.create(d_u));
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						if (name.contains(getConfig().getString(
								"voice_server_icon_name").replaceAll(
								"(&([a-f0-9]))", ""))) {
							for (String v_s_u : getConfig().getStringList(
									"voice_server_urls")) {
								player.sendMessage(ChatColor
										.translateAlternateColorCodes('&',
												v_s_u));
							}
						}
						event.setWillClose(true);
					}
				}, this)
				.setOption(
						getConfig().getInt("vote_icon_position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("vote_icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("vote_icon_name")),
						new String[] { ChatColor.translateAlternateColorCodes(
								'&', getConfig().getString("vote_icon_lore")) })
				.setOption(
						getConfig().getInt("website_icon_position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("website_icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("website_icon_name")),
						new String[] { ChatColor
								.translateAlternateColorCodes('&', getConfig()
										.getString("website_icon_lore")) })
				.setOption(
						getConfig().getInt("donate_icon_position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("donate_icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("donate_icon_name")),
						new String[] { ChatColor.translateAlternateColorCodes(
								'&', getConfig().getString("donate_icon_lore")) })
				.setOption(
						getConfig().getInt("voice_server_icon_position"),
						new ItemStack(Material.getMaterial(getConfig()
								.getString("voice_server_icon")), 1),
						ChatColor.translateAlternateColorCodes('&', getConfig()
								.getString("voice_server_icon_name")),
						new String[] { ChatColor
								.translateAlternateColorCodes('&', getConfig()
										.getString("voice_server_icon_lore")) });
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
