package me.kwanghoon.demoinfleanrestapi.events;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/*

# 단축키
  -  delete one line : option + controll + o
  -  run Test : controll + shift + r
  -  refac : option + command + v

# Details
  -  Builder를 사용하면 컴파일 단계에서 오류 확인 가능
*/

class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        // Given
        String name = "Event";
        String description = "Spring";

        // When
        Event event = new Event();
        event.setName("Event");
        event.setDescription(description);

        // Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);

    }

    @Test
    public void testFree() {
        // Given
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isFree()).isTrue();

        // Given
        event = Event.builder()
                .basePrice(100)
                .maxPrice(0)
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isFree()).isFalse();

        // Given
        event = Event.builder()
                .basePrice(100)
                .maxPrice(1000)
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isFree()).isFalse();
    }

    @Test
    public void testOffline() {
        // Given
        Event event = Event.builder()
                .location("gang")
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isOffline()).isTrue();

        // Given
        event = Event.builder()
                .build();

        // When
        event.update();

        // Then
        assertThat(event.isOffline()).isFalse();
    }
}