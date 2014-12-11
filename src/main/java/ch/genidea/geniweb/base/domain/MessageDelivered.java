package ch.genidea.geniweb.base.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="genidea_messageDelivered")
public class MessageDelivered implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3874965380926124192L;

	@Id@GeneratedValue
    private Long id;

    @ManyToOne
    Message message;
    @ManyToOne
    User destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }
}
