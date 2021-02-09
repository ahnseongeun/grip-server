package com.app.grip.src.review.models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "reviewPicture")
public class ReviewPicture {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pictureURL", columnDefinition = "TEXT")
    private String pictureURL;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;
}