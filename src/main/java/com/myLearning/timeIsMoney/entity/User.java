package com.mylearning.timeismoney.entity;

import com.mylearning.timeismoney.entity.enums.Role;

import java.util.List;

public class User {

    private int id;

    private String login;
    private String password;

    private Role role;

    private List<Mission> missions;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public List<Mission> getMissions() {
        return missions;
    }
    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }


    public static class Builder {

        private final User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder id(int id){
            newUser.id = id;
            return this;
        }

        public Builder login(String login) {
            newUser.login = login;
            return this;
        }

        public Builder password(String password) {
            newUser.password = password;
            return this;
        }

        public Builder role(Role role) {
            newUser.role = role;
            return this;
        }

        public Builder missions(List<Mission> missions) {
            newUser.missions = missions;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
