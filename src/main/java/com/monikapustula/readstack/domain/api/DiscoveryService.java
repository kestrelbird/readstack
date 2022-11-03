package com.monikapustula.readstack.domain.api;

import com.monikapustula.readstack.domain.discovery.Discovery;
import com.monikapustula.readstack.domain.discovery.DiscoveryDao;

import java.util.List;
import java.util.stream.Collectors;

public class DiscoveryService {
    private final DiscoveryDao discoveryDao = new DiscoveryDao();

    public List<DiscoveryBasicInfo> findAll() {
        return discoveryDao.findAllDiscoveries()
                .stream()
                .map(DiscoveryService::discoveryMapper)
                .collect(Collectors.toList());
    }

    public List<DiscoveryBasicInfo> findByCategory(int categoryId) {
        return discoveryDao.findByCategory(categoryId)
                .stream()
                .map(DiscoveryService::discoveryMapper)
                .collect(Collectors.toList());
    }

     static private DiscoveryBasicInfo discoveryMapper(Discovery d) {
        return new DiscoveryBasicInfo(d.getTitle(), d.getUrl(), d.getDescription(), d.getLocalDateTime());
    }

}
