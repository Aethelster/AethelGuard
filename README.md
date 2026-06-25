# 🛡️ AethelGuard
[![sponsors](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/donate/ghsponsors-singular_vector.svg)](https://github.com/sponsors/Aethelster) 
[![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/plugin/aethelguard/) [![hangar](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/hangar_vector.svg)](https://hangar.papermc.io/Aethelster/AethelGuard)
[![github](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/github_vector.svg)](https://github.com/Aethelster/AethelGuard)

**AethelGuard** is a modern authentication and account-security plugin for Paper-based Minecraft servers, focused on clean logs and strong customization.

When players join the server, they are kept inside a controlled auth area until verification is complete. Before the captcha, login/register, PIN, 2FA or recovery step is finished, players cannot access the world, chat, inventory or risky commands. When auth is completed, their inventory is restored, the bossbar is cleared and the player returns to a safe location.

> The goal is simple: strong auth, clean console output, understandable config and a security system that admins can manage comfortably.

## ✅ Current Version

**Latest stable:** `v0.4.2-sentinel`

`v0.4.2-sentinel` is the final free release of Aethelguard. It builds on `v0.3-sentinel` with the PIN system, PIN GUI, diagnostics, stronger timeout/reload flow, required security questions and many polish/fix improvements.

> ⚠️ **Important warning:** Versions before `v0.4-sentinel` are no longer recommended. Older `dev` builds and previous sentinel releases may contain fixed issues in the auth flow, timeout handling, local YAML password storage, 2FA QR links or config/messages. For new installs and live servers, it is recommended to use **`v0.4-sentinel`** directly.

## ✨ Highlight Features

- 🔐 Password auth: `/register`, `/login`, `/changepassword`
- 🔢 PIN auth: `/pin`, `/setpin`, `/changepin`, `/authmode`
- 🧩 Hardcoded PIN GUI keypad and theme system
- 🛡️ Captcha: `MAP`, `TEXT`, `NUMERIC`, `ALPHANUMERIC`, `MATH`
- 🗺️ Map captcha, slot lock and inventory protection during auth
- 📱 TOTP 2FA: compatible with Google Authenticator, Microsoft Authenticator and Authy
- 🔗 Clickable QR link for 2FA setup
- 🧠 Adaptive security: trusted-IP captcha bypass and suspicious-IP extra captcha
- 🌐 VPN/proxy checks and manual suspicious IP list
- ❓ Required security question after first registration
- 🧾 Account recovery with security questions and backup codes
- 🔁 Session auto-login
- ⏳ Temporary auth lockout and attempt management
- 🔑 Password policy: length, username blocking, blocked word list, Turkish characters, punctuation and special-character settings
- 💾 Local YAML or MySQL storage
- 📊 `/aethelguard status`, `ipinfo`, `accounts`, `sessions`
- 🧪 `/aethelguard diagnostics` and diagnostic dump support
- 🧹 Suppression for vanilla join/quit/connection/command logs
- 🖥️ Console language: `en`, `tr native`, `tr ascii`
- 🌍 `messages_<code>.yml` language system
- ⚙️ Config sync system that automatically adds missing config keys and fixes layout
- 💬 Blocking unauthenticated players from seeing/sending chat
- 📌 Bossbar guidance based on the current auth step

## 📦 Installation

1. Download `aethelguard.jar` from GitHub Releases or Modrinth.
2. Put the JAR file into your server's `plugins` folder.
3. Start the server.
4. Edit `plugins/AethelGuard/config.yml`.
5. To change messages, use the `plugins/AethelGuard/messages/` folder.
6. To change security questions, edit the `plugins/AethelGuard/security_questions/` folder.

Most settings can be reloaded:

```text
/aethelguard reload
```

For database settings and some core auth settings, restarting the server may be safer.

## 🎮 Player Commands

| Command | Description |
| --- | --- |
| `/register <password> <password again>` | Creates an account with password auth. |
| `/login <password>` | Logs in with password auth. |
| `/captcha <code>` | Completes captcha verification. |
| `/pin <pin>` | Logs in with PIN. |
| `/setpin <pin> <pin>` | Creates the first PIN. |
| `/changepin <oldPin> <newPin> <newPinAgain>` | Changes the PIN. |
| `/authmode <password/pin>` | Changes the account login method. |
| `/changepassword <oldPassword> <newPassword> <newPasswordAgain>` | Lets the player change their own password. |
| `/twofactor <code>` | Verifies the 2FA code during login. |
| `/twofactor setup` | Starts authenticator setup. |
| `/twofactor confirm <code>` | Completes 2FA setup. |
| `/twofactor disable <code>` | Disables 2FA protection. |
| `/securityquestion setup` | Creates a security question. |
| `/securityquestion answer <answer>` | Saves the security-question answer. |
| `/backupcodes generate` | Generates one-time backup codes for recovery. |
| `/recoverymethod question` | Sets recovery method to security question. |
| `/recoverymethod backup-code` | Sets recovery method to backup code. |
| `/recover question <answer> <newPassword>` | Resets password with a security question. |
| `/recover code <code> <newPassword>` | Resets password with a backup code. |

## 👑 Admin Commands

| Command | Description |
| --- | --- |
| `/aethelguard reload` | Reloads config, messages and connections. |
| `/aethelguard status <player>` | Shows the player's auth state and account details. |
| `/aethelguard ipinfo <player/ip>` | Shows accounts related to an IP and limit information. |
| `/aethelguard accounts <player/ip>` | Lists accounts linked to the same IP. |
| `/aethelguard sessions` | Shows active auth sessions. |
| `/aethelguard session <player>` | Shows one player's session information. |
| `/aethelguard clearsession <player>` | Clears one player's session record. |
| `/aethelguard clearsessions` | Clears all session records. |
| `/aethelguard diagnostics` | Shows general system diagnostics. |
| `/aethelguard diagnostics player <player>` | Shows player-specific diagnostic output. |
| `/aethelguard diagnostics config` | Checks config risks. |
| `/aethelguard diagnostics dump` | Creates a diagnostic report file. |
| `/aethelguard pingui preview [theme]` | Previews a PIN GUI theme. |
| `/aethelguard pingui themes` | Lists available PIN GUI themes. |
| `/aethelguard unregister <player>` | Deletes a player's auth record. |
| `/aethelguard changepassword <player> <newPassword>` | Changes a player's password as admin. |
| `/aethelguard unlogin <player>` | Sends an online player back to the auth screen without kicking them. |

Admin aliases:

```text
/ag
/aeg
/ayarlar
```

## ⚙️ Config and Language System

Aethelguard settings are managed through `config.yml`. The file includes categorized comments, and missing keys are automatically added in newer versions.

Important config areas:

- `default-language`: Player message language. New language files can be added with the `messages_<code>.yml` pattern.
- `console-language`: Console language. Only `en` and `tr` are supported.
- `console-text-mode`: `native` or `ascii` for Turkish console output.
- `database`: MySQL or local YAML storage.
- `auth-settings`: Captcha, PIN, 2FA, timeout, session, bossbar, sounds and restriction settings.
- `adaptive-security`: IP trust, suspicious IPs, VPN/proxy checks and extra captcha.
- `recovery`: Security questions, backup codes and recovery behavior.
- `security-cooldowns`: Cooldown settings for sensitive commands.
- `status`: Local user snapshot and admin status system.
- `diagnostics`: Admin diagnostics and dump settings.
- `console-logging`: Vanilla connection log suppression and auth state logs.

## 🔐 Auth Flow

When a player joins the server:

1. Captcha verification is requested if needed.
2. Password or PIN is requested depending on the account mode.
3. If 2FA is enabled, an authenticator code is requested.
4. If the required first-register security question is enabled, an answer is requested.
5. If successful, a session is created, inventory is restored and the player is moved to a safe location.

Default flow for a 2FA account:

```text
30s captcha + 60s 2FA code + 30s security question
```

The normal auth flow uses a total default timeout of 90 seconds.

## 🧩 PIN GUI

With `v0.4-sentinel`, the PIN system is no longer command-only; it also supports a GUI.

- Fixed 4-digit PIN
- Theme support: quartz, forest-green, pumpkin, netherite, monitor-green, monitor-red
- Hidden or visible digit display
- Random number layout option
- Confirm, backspace, clear and exit buttons
- GUI close-spam protection
- Dedicated bossbar and sound effects

The PIN GUI is intentionally kept simple and hardcoded in the free version.

## 🧠 Adaptive Security

The adaptive security system evaluates player login behavior.

- Safe repeat logins from the same IP can bypass captcha.
- Suspicious IPs or VPN/proxy cases can require extra captcha.
- Admins can use a manual suspicious IP list.
- Extra captcha can be selected differently from the normal captcha type.

This system is designed to reduce unnecessary verification for trusted players while checking risky logins more strictly.

## 🧾 Recovery System

Players can recover their accounts through:

- Security question
- Backup code
- Admin password change

If the security question after first registration is required, the player is not allowed into the server before answering it. If the player times out during this step, the same security-question procedure starts again after their next successful login/PIN/2FA.

## 🧹 Clean Console

Aethelguard can optionally suppress these Paper/vanilla logs:

- joined the game
- left the game
- logged in with entity id
- lost connection
- issued server command

Instead, it produces more meaningful logs for auth states:

- login success
- register success
- auto-login success
- 2FA success
- timeout kick
- unauthenticated quit
- authenticated quit

Console language can be English or Turkish. Turkish console output supports `native` and `ascii` modes.

## 🆕 What Was Added After v0.3-sentinel?

`v0.4-sentinel` arrived as the final free package on top of `v0.3-sentinel`.

New main features:

- PIN login system
- PIN registration system
- PIN changing
- Password/PIN auth mode switching
- PIN GUI keypad
- PIN GUI theme system
- PIN GUI preview commands
- Admin diagnostics system
- Diagnostic dump
- `/ipinfo` and `/accounts`
- More detailed `/status`
- Config/messages backup infrastructure during reload
- Temporary auth lockout
- Required security question after first registration
- Chat input for captcha and security-question answers
- 2FA QR link polish
- Dedicated 2FA success sound
- Auth timeout polish
- PIN GUI close-spam kick
- Captcha inventory/slot/offhand protection
- MAP captcha cleanup improvements
- Config layout sync fixes
- Messages and MiniMessage cleanup
- 0.4 final release README cleanup

Important fixes:

- Local YAML password hash is now stored under `password.hash`.
- `password.usable` no longer overwrites the hash value.
- Migration support was added for the old local password hash format.
- Fixed `/login` becoming ineffective after captcha.
- Prevented timeout tasks from kicking/logging with an old deadline.
- Fixed raw MiniMessage tags appearing on kick screens.
- Prevented `&` characters inside URLs from being corrupted like color codes.

## 🧱 Storage

There are two storage modes:

- **Local YAML:** For small/medium servers and quick setup.
- **MySQL:** For servers that want more persistent and centralized storage.

In local mode, user files are stored by UUID. A user index system exists to help admins see name/UUID mappings more easily.

## 📌 Supported Platform

Aethelguard is developed with Paper API.

Recommended:

- Paper
- Purpur
- Paper-based forks

Target API:

```text
Minecraft / Paper API 1.21.x
Java 21
```

Because some Paper API behavior may be missing on Spigot/Bukkit, Paper or Purpur is the recommended platform.

## 📄 License

This project is shared under the custom license in the `LICENSE` file.

In short:

- It can be used.
- It can be shared.
- Derivative/improved versions from the source code cannot be produced.
- The license text must be preserved.

## 💬 Closing

Aethelguard aims to provide a clean and strong auth experience in the free version. `v0.4-sentinel` is the final release of this free line: password, PIN, captcha, 2FA, recovery, diagnostics and adaptive security are all packed together.

Enjoy. ✨
