package hexlet.code.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class User implements BaseEntity {

    /*
      id – уникальный идентификатор пользователя, генерируется автоматически
      firstName - имя пользователя, необязательное
      lastName - фамилия пользователя, необязательное
      email - адрес электронной почты, обязательное. Только формата email
      password - пароль, обязательное. Минимум 3 символа
      createdAt - дата создания (регистрации) пользователя, заполняется автоматически.
      updatedAt – дата обновления данных пользователя, заполняется автоматически.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = ".+@.+\\..{2,4}", message = "Please provide a valid email address")
    private String email;

    @NotBlank
    @Size(min = 3)
    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
