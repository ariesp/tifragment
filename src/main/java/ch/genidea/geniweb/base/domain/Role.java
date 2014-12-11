package ch.genidea.geniweb.base.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name="genidea_role")
public class Role implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3717543368267689131L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;
    private Integer role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
