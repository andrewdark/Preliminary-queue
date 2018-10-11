package ua.pp.darknsoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


public enum Action {
    ACTION01{
        public String toString() {
            return "this is one";
        }
    },ACTION02{
        public String toString() {
            return "this is two";
        }
    },ACTION03{
        public String toString() {
            return "this is three";
        }
    };
}
