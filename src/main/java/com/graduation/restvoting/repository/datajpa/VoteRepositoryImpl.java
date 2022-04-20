package com.graduation.restvoting.repository.datajpa;

import com.graduation.restvoting.model.Vote;
import com.graduation.restvoting.repository.VoteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link com.graduation.restvoting.repository.VoteRepository} interface.
 */
@Repository
public class VoteRepositoryImpl implements VoteRepository {

    private final CrudVoteRepository crudVoteRepository;

    public VoteRepositoryImpl(CrudVoteRepository crudVoteRepository) {
        this.crudVoteRepository = crudVoteRepository;
    }

    @Override
    public Vote save(Vote vote) {
        return crudVoteRepository.save(vote);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    @Override
    public Vote get(int id) {
        return crudVoteRepository.getById(id);
    }

    @Override
    public void delete(int id) {
        crudVoteRepository.deleteById(id);
    }
}
