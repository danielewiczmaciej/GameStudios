package com.example.gameStudios.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "studios")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Studio implements Serializable, Comparable<Studio> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="studio_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "studio_name")
    private String name;
    @Column(name = "year_of_foundation")
    private int yearOfFoundation;
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Game> games;

    @Override
    public int compareTo(Studio otherStudio) {
        // Compare studios based on their year of foundation
        return Integer.compare(this.yearOfFoundation, otherStudio.yearOfFoundation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfFoundation);
    }

    @Override
    public String toString() {
        return "Studio{" +
                "name='" + name + '\'' +
                ", yearOfFoundation=" + yearOfFoundation +
                '}';
    }
}
