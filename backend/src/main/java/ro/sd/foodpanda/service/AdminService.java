package ro.sd.foodpanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.foodpanda.DTO.AdminDTO;
import ro.sd.foodpanda.model.Admin;
import ro.sd.foodpanda.model.Customer;
import ro.sd.foodpanda.repository.AdminRepository;
import ro.sd.foodpanda.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private Mappers mappers = new Mappers();

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public AdminDTO findByUsername(String username) {
        Admin admin = adminRepository.findAdminByUsername(username);
        return mappers.convertAdminToDTO(admin);
    }

    public Admin findAdminByUsername(String username) {
        return adminRepository.findAdminByUsername(username);
    }

    public List<AdminDTO> findAll() {
        List<Admin> admins = new ArrayList<>();
        List<AdminDTO> adminDTOList = new ArrayList<>();
        Iterable<Admin> all = adminRepository.findAll();
        all.forEach(admins::add);
        for (Admin admin : admins) {
            adminDTOList.add(mappers.convertAdminToDTO(admin));
        }
        return adminDTOList;
    }

}
