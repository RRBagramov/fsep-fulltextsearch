package ru.fsep.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 12.07.2017
 *
 * @author Robert Bagramov.
 */
@Entity
@Table(name = "comment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
name = "viewHighlight",
        entities={
                @EntityResult(entityClass=Comment.class, fields={
                        @FieldResult(name="id", column="id"),
                        @FieldResult(name="text", column="text"),
                        @FieldResult(name="addingDate", column="adding_date")})},
        columns={
                @ColumnResult(name="highlight")}
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "adding_date", nullable = false)
    private Timestamp addingDate;

    @Transient
    private String highlight;

}
