package kata.pp311.service;

import org.springframework.stereotype.Service;
import kata.pp311.dao.RoleDao;
import kata.pp311.model.Role;

import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService{
    RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDao.save(role);
    }
}
