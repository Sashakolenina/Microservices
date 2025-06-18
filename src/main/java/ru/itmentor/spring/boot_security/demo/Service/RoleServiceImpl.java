package ru.itmentor.spring.boot_security.demo.Service;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.Model.Role;
import ru.itmentor.spring.boot_security.demo.Repositories.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> findByIds(Set<Long> roles) {
        Set<Role> roleSet = new HashSet<>();
        for (Long role : roles) {
            roleSet.add(findById(role));
        }
        return roleSet;
    }
@Override
    public Role findById(Long role) {
        return roleRepository.findById(role).orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
