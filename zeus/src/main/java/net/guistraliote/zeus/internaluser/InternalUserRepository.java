package net.guistraliote.zeus.internaluser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternalUserRepository extends JpaRepository<InternalUser, Long> {

    Optional<InternalUser> findInternalUserByEmail(String email);
}
