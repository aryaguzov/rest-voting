package com.graduation.restvoting.service;

import com.graduation.restvoting.model.Vote;
import com.graduation.restvoting.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.graduation.restvoting.util.ValidationUtil.*;

/**
 * {@link VoteService} for {@link com.graduation.restvoting.repository.VoteRepository}.
 */
@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Vote create(Vote vote) {
        Objects.requireNonNull(vote, "Vote must not be null.");
        return voteRepository.save(vote);
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    public Vote get(int id) {
        return checkNotFoundWithId(voteRepository.get(id), id);
    }

    @Transactional
    public void delete(int id) {
        checkNotFoundWithId(voteRepository.get(id), id);
        voteRepository.delete(id);
    }

    @Transactional
    public void update(Vote vote) {
        Objects.requireNonNull(vote, "Vote must not be null.");
        checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }
}
