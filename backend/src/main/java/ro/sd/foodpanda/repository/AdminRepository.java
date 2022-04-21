package ro.sd.foodpanda.repository;

import ro.sd.foodpanda.model.Admin;

public interface AdminRepository extends AbstractRepository<Admin> {

    Admin findAdminByUsername(String username);
}
