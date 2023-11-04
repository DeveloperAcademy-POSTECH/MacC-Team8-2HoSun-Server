package com.twohoseon.app.entity.post;

import com.twohoseon.app.common.BaseTimeEntity;
import com.twohoseon.app.entity.member.Member;
import com.twohoseon.app.entity.post.vote.Vote;
import com.twohoseon.app.entity.post.vote.VoteId;
import com.twohoseon.app.enums.VoteType;
import com.twohoseon.app.enums.post.PostStatus;
import com.twohoseon.app.enums.post.VisibilityScope;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : hyunwoopark
 * @version : 1.0.0
 * @package : twohoseon
 * @name : Post
 * @date : 10/17/23 4:01 PM
 * @modifyed : $
 **/
@Entity
@Getter
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @Comment("작성자")
    protected Member author;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Comment("게시글 타입")
    private VisibilityScope visibilityScope;

    @NotNull
    @Column
    @Comment("제목")
    private String title;

    @Nullable
    @Column
    @Comment("텍스트 내용")
    private String contents;

    @Nullable
    @Column
    @Comment("제품 가격")
    private Integer price;

    @Comment("포스트 상태")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @NotNull
    @Column
    @Comment("댓글 수")
    @Builder.Default
    private Integer commentCount = 0;
    @Nullable
    @Column
    @Comment("외부 링크")
    private String externalURL;

    @NotNull
    @Column
    @Comment("좋아요 수")
    @Builder.Default
    private Integer likeCount = 0;

    @NotNull
    @Column
    @Comment("투표수")
    @Builder.Default
    private Integer voteCount = 0;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column
    @Comment("이미지 리스트")
    @Builder.Default
    private List<String> imageList = new ArrayList<>();

    @OneToMany(mappedBy = "id.post", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Vote> votes = new LinkedHashSet<>();

//    @Column
//    private Post review;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private Set<Member> subscribers = new LinkedHashSet<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "review_id")
    private Post review;

    public void setReview(Post review) {
        this.review = review;
    }

    public void setSubscribers(Set<Member> subscribers) {
        this.subscribers = subscribers;
    }

    public void incrementVoteCount() {
        this.voteCount += 1;
    }

    public void decrementVoteCount() {
        this.voteCount -= 1;
    }

    public void incrementLikeCount() {
        this.likeCount += 1;
    }

    public void decrementLike() {
        this.likeCount -= 1;
    }

    public void createVote(Member voter, VoteType voteType) {
        Vote vote = Vote.builder()
                .id(VoteId.builder()
                        .voter(voter)
                        .post(this)
                        .build())
                .isAgree(voteType == VoteType.AGREE)
                .build();
        this.votes.add(vote);
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public void incrementCommentCount() {
        this.commentCount += 1;
    }

    public void decrementComment() {
        this.commentCount -= 1;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
