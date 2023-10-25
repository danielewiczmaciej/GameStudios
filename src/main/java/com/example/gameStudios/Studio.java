package com.example.gameStudios;

import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Studio implements Serializable, Comparable<Studio> {
    private String name;
    private int yearOfFoundation;
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
