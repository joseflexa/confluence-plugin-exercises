package br.rnp.confluence.plugins.ao;

import net.java.ao.Entity;

public interface Birthday extends Entity {
    String getFullName();

    void setFullName(String fullName);

    String getDate();

    void setDate(String date);
}
