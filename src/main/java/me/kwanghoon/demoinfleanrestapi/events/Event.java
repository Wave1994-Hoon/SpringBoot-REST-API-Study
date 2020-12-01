package me.kwanghoon.demoinfleanrestapi.events;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*

# 단축키
  > create or move test-code : command+shift+T
  > auto create var name : control + space

# lombok
  >  EqualsAndHashCode : 연관관계에 사옹되는 다른 엔티티와 묶는 건 좋지 않다 -> 상호 참조 -> Stack Overflow 발생 가
  >  lombok annotation에서 제공하는 것들은 메타로 사용 불가능 ex) 여러개 묶어서 @MyEntity 로 사용하고 싶지만 lombok은 안됨
  >  lombok 에서 @Data annotation도 제공하지만 사용하지 않는게 좋음 -> @EqualsAndHashCode 내장 -> 상호 참조 가능성
  >  @Builder를 추가하면 기본 생성자는 생성이 안됨 -> 모든 파라미터를 가진 생성자가 추가됨(디폴트 생성자로, public x)
      -> 그래서  @AllArgsConstructor @NoArgsConstructor @Getter @Setter 추가
*/

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
public class Event {

    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;

    public void update() {
        // update free
        if (this.basePrice == 0 && this.maxPrice == 0) {
            this.free = true;
        } else {
            this.free = false;
        }

        // update location
        // java 11에서는 isBlank()
        if (this.location == null) {
            this.offline = false;
        } else if (this.location.trim().isEmpty()) {
            this.offline = false;
        } else {
            this.offline = true;
        }


    }

}
