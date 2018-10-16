package ua.pp.darknsoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


public enum Action {
    ACTION01("Дія №1"),ACTION02("Дія №2"),ACTION03("Дія №3");
    private final String displayName;

    Action(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
