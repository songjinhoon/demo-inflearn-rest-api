package com.study.demoinflearnrestapi.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping(value = "/api/event", produces = MediaTypes.HAL_JSON_VALUE)
@RestController
public class EventController {

    private final EventRepository eventRepository;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto) {
        Event event = EventMapper.INSTANCE.toEvent(eventDto);
        Event saveEvent = eventRepository.save(event);
        URI uri = linkTo(EventController.class).slash(saveEvent.getId()).toUri();
        return ResponseEntity.created(uri).body(event);
    }

}
/*
*  강의에서는 ModelMapper를 사용했으나 나는 Mapstruct로 진행해보겠음
* */
