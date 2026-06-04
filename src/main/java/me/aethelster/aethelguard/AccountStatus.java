package me.aethelster.aethelguard;

import java.util.UUID;

public record AccountStatus(
        boolean found,
        String username,
        UUID uuid,
        String storage,
        boolean online,
        boolean authenticated,
        boolean waitingAuth,
        boolean activeSession,
        boolean temporaryLockout,
        String temporaryLockoutRemaining,
        String authMode,
        boolean passwordUsable,
        boolean pinSet,
        boolean twoFactorEnabled,
        String recoveryMethod,
        boolean securityQuestionSet,
        int backupCodeCount,
        int currentWrongAttempts,
        int currentPinAttempts,
        int totalWrongAttempts,
        int loginCount,
        String createdAt,
        String lastLogin,
        String lastWrongAttempt,
        String lastIp,
        String lastWorld,
        String lastLocation
) {
    public static AccountStatus notFound(String username, String storage) {
        return new AccountStatus(
                false,
                username,
                null,
                storage,
                false,
                false,
                false,
                false,
                false,
                "-",
                "-",
                false,
                false,
                false,
                "-",
                false,
                0,
                0,
                0,
                0,
                0,
                "-",
                "-",
                "-",
                "-",
                "-",
                "-"
        );
    }
}
