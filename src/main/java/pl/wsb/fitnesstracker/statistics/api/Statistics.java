package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User; // Pamiętaj o imporcie encji User

@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Relacja jednostronna OneToOne z tabelą User (Wymóg z zadania)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true) // unique = true gwarantuje relację 1:1 w bazie
    private User user;

    @Column(name = "totalTrainings", nullable = false)
    private int totalTrainings;

    @Column(name = "totalDistance", nullable = false)
    private double totalDistance;

    @Column(name = "totalCaloriesBurned", nullable = false)
    private int totalCaloriesBurned;

    public Statistics(
            final User user,
            final int totalTrainings,
            final double totalDistance,
            final int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}