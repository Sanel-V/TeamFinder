package hu.elte.teamfinder.security;

public enum AccountPermission {
  ACCOUNT_READ("account:read"),
  ACCOUNT_WRITE("account:write");

  private final String permission;

  AccountPermission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
