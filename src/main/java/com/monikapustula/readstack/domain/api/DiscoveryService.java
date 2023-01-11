package com.monikapustula.readstack.domain.api;

import com.monikapustula.readstack.domain.discovery.Discovery;
import com.monikapustula.readstack.domain.discovery.DiscoveryDao;
import com.monikapustula.readstack.domain.user.UserDao;
import com.monikapustula.readstack.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DiscoveryService {
    private final DiscoveryDao discoveryDao = new DiscoveryDao();
    private final DiscoveryMapper discoveryMapper = new DiscoveryMapper();

    public void add(DiscoverySaveRequest discoverySaveRequest) {
        Discovery discoveryToSave = discoveryMapper.map(discoverySaveRequest);
        discoveryDao.save(discoveryToSave);
    }

    public List<DiscoveryBasicInfo> findAll() {
        return discoveryDao.findAll()
                .stream()
                .map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    public List<DiscoveryBasicInfo> findByCategory(int categoryId) {
        return discoveryDao.findByCategory(categoryId)
                .stream()
                .map(discoveryMapper::map)
                .collect(Collectors.toList());
    }

    private static class DiscoveryMapper {
        private final UserDao userDao = new UserDao();
        private final VoteDao voteDao = new VoteDao();

        DiscoveryBasicInfo map(Discovery d) {
            return new DiscoveryBasicInfo(
                    d.getId(),
                    d.getTitle(),
                    d.getUrl(),
                    d.getDescription(),
                    d.getLocalDateTime(),
                    voteDao.countByDiscoveryId(d.getId()),
                    userDao.findById(d.getUserId()).orElseThrow().getUsername());
        }

        Discovery map(DiscoverySaveRequest dsr) {
            return new Discovery(
                    dsr.getTitle(),
                    dsr.getUrl(),
                    dsr.getDescription(),
                    LocalDateTime.now(),
                    dsr.getCategoryId(),
                    userDao.findByUsername(dsr.getAuthor()).orElseThrow().getId());
        }
    }

}
