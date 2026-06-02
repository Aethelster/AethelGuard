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
        int currentWrongAttempts,
        int totalWrongAttempts,
        String createdAt,
        String lastLogin,
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
                0,
                0,
                "-",
                "-",
                "-",
                "-",
                "-"
        );
    }
}
