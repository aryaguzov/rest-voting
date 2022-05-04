package com.graduation.restvoting.service;

import com.graduation.restvoting.model.Restaurant;
import com.graduation.restvoting.model.Role;
import com.graduation.restvoting.model.User;
import com.graduation.restvoting.model.Vote;
import com.graduation.restvoting.repository.VoteRepository;
import com.graduation.restvoting.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class VoteServiceTest extends AbstractServiceTest {

    @Mock
    public VoteRepository voteRepository;

    @InjectMocks
    public VoteService voteService;

    private Vote actual;

    private User user;

    private Restaurant restaurant;

    @BeforeEach
    void setup() {
        voteRepository = Mockito.mock(VoteRepository.class);
        voteService = new VoteService(voteRepository);
        user = new User(1, "Admin", "admin@gmail.com", "admin", true,
                Collections.singleton(Role.ADMIN));
        restaurant = new Restaurant(2, "Lucky Pub");
        actual = new Vote(3, LocalDateTime.of(2022, Month.APRIL, 20, 10, 0), user, restaurant);
    }

    @Test
    void create() {
        final Vote vote = new Vote(null, LocalDateTime.now(), user, restaurant);

        given(voteRepository.save(vote)).willAnswer(invocation -> invocation.getArgument(0));

        final Vote savedVote = voteService.create(vote);

        assertEquals(vote, savedVote);
        verify(voteRepository).save(any(Vote.class));
    }

    @Test
    void getAll() {
        final List<Vote> votes = new ArrayList<>();
        votes.add(actual);

        when(voteRepository.getAll()).thenReturn(votes);
        final List<Vote> allVotes = voteService.getAll();

        assertEquals(votes, allVotes);
        verify(voteRepository, times(1)).getAll();
    }

    @Test
    void get() {
        final int id = actual.getId();

        when(voteRepository.get(id)).thenReturn(actual);
        final Vote expected = voteService.get(id);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        final int id = actual.getId();
        voteRepository.delete(id);

        assertThrows(NotFoundException.class, () -> voteService.get(id));
    }

    @Test
    void update() {
        actual.setDateTime(LocalDateTime.of(2022, Month.MAY, 3, 5, 0));
        actual.setUser(new User(123, "Updated User", "user@gmail.com", "user", true,
                Collections.singleton(Role.USER)));
        actual.setRestaurant(new Restaurant(234, "Updated Restaurant"));

        given(voteRepository.save(actual)).willAnswer(invocation -> invocation.getArgument(0));
        voteService.update(actual);

        assertEquals(LocalDateTime.of(2022, Month.MAY, 3, 5, 0), actual.getDateTime());
    }
}