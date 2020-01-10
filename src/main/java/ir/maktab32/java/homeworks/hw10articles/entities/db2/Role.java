package ir.maktab32.java.homeworks.hw10articles.entities.db2;

import ir.maktab32.java.homeworks.hw10articles.entities.db2.User;
import ir.maktab32.java.homeworks.hw10articles.utilities.RoleTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private RoleTitle title;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", title=" + title +

                '}';
    }
}
