package edu.vt.cs3704.example.services;

import edu.vt.cs3704.example.entities.User;
import edu.vt.cs3704.example.models.CurrentUser;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public abstract class CurrentUserService {
  public abstract User getUser();
  public abstract CurrentUser getCurrentUser();
  public abstract Collection<? extends GrantedAuthority> getRoles();

  public final boolean isLoggedIn() {
    return getUser() != null;
  }

}
