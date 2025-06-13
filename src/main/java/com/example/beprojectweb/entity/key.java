package com.example.beprojectweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode // 👉 Lombok sẽ tự generate equals() và hashCode()
@Table(name = "game_keys")
public class key implements Serializable {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    String gameKey;
}