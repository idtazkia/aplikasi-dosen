package id.ac.tazkia.dosen.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity @Table(name = "c_password_reset_token")
@Data
public class PasswordResetToken implements Serializable{
    private static final int EXPIRATION = 60 * 24;
    private static final long serialVersionUID = -7371610187321351709L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String token;
  
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_user", unique = true)
    private User user;
  
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "expiry_date")
    private Date expiryDate;
    
    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }
    
    public final Date calculateExpiryDate() {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

}
