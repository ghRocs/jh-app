package com.github.ghrocs.service.impl;

import com.github.ghrocs.domain.Region;
import com.github.ghrocs.repository.RegionRepository;
import com.github.ghrocs.service.RegionService;
import com.github.ghrocs.service.dto.RegionDTO;
import com.github.ghrocs.service.mapper.RegionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Region}.
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    private final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);

    private final RegionRepository regionRepository;

    private final RegionMapper regionMapper;

    public RegionServiceImpl(RegionRepository regionRepository, RegionMapper regionMapper) {
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    @Override
    public Mono<RegionDTO> save(RegionDTO regionDTO) {
        log.debug("Request to save Region : {}", regionDTO);
        return regionRepository.save(regionMapper.toEntity(regionDTO)).map(regionMapper::toDto);
    }

    @Override
    public Mono<RegionDTO> partialUpdate(RegionDTO regionDTO) {
        log.debug("Request to partially update Region : {}", regionDTO);

        return regionRepository
            .findById(regionDTO.getId())
            .map(
                existingRegion -> {
                    regionMapper.partialUpdate(existingRegion, regionDTO);
                    return existingRegion;
                }
            )
            .flatMap(regionRepository::save)
            .map(regionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<RegionDTO> findAll() {
        log.debug("Request to get all Regions");
        return regionRepository.findAll().map(regionMapper::toDto);
    }

    public Mono<Long> countAll() {
        return regionRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<RegionDTO> findOne(Long id) {
        log.debug("Request to get Region : {}", id);
        return regionRepository.findById(id).map(regionMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Region : {}", id);
        return regionRepository.deleteById(id);
    }
}
