package com.piatsevich.simplecrudapp.service.impl;

import com.piatsevich.simplecrudapp.models.Project;
import com.piatsevich.simplecrudapp.repository.ProjectRepository;
import com.piatsevich.simplecrudapp.repository.jdbc.ProjectRepositoryImpl;
import com.piatsevich.simplecrudapp.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository = new ProjectRepositoryImpl();

    @Override
    public Project getById(Integer id) {
        return projectRepository.getById(id);
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    @Override
    public Project update(Project project) {
        return projectRepository.update(project);
    }

    @Override
    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }
}
