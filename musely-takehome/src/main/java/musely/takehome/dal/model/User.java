package musely.takehome.dal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User
{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("email")
    private String email;

    public User(Long id, String name, String lastname, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
