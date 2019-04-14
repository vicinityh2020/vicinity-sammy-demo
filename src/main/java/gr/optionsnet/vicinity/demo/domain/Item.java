package gr.optionsnet.vicinity.demo.domain;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "item")
public class Item implements Serializable {

    /**
     * OID from configuration is the public ObjectID
     * InfraId from configuration is the internal ObjectID ans equals OID field from adapter/objects response
     */
    @Id
    @Column(name = "oid", columnDefinition = "varchar (36)")
    private String oid;

    @Column(name = "infraId", columnDefinition = "varchar (36)", unique = true)
    private String infraId;

    @Column(name = "password", columnDefinition = "varchar (255)")
    private String password;

    @Column(name = "name", columnDefinition = "varchar (255)")
    private String name;

    @Column(name = "type", columnDefinition = "varchar (255)")
    private String type;

    @Column(name = "pid", columnDefinition = "varchar (255)")
    private String pid;

    @Column(name = "monitors", columnDefinition = "varchar (255)")
    private String monitors;

    @Column(name = "units", columnDefinition = "varchar (255) default ''")
    private String units;

}
